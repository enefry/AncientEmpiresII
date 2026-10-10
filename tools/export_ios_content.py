#!/usr/bin/env python3
"""Export verified game data and original JAR resources for the iOS bundle."""

from __future__ import annotations

import argparse
import hashlib
import json
import zipfile
from pathlib import Path, PurePosixPath


ROOT = Path(__file__).resolve().parents[1]
JAR = ROOT / "reference/inputs/Ancient-Empires-II_J2ME_EN_v25/Ancient Empires II (2012)(Macrospace, SilentKnight)(v2.5).jar"
DATA = ROOT / "reference/reconstruction/data"
OUTPUT = ROOT / "Packages/AncientEmpiresEngine/Sources/AEContent/Resources"
BASELINE_JAR_SHA256 = "f2b3e06190014940a04da70677d6504dd3fd3d9fbc39a49192926293f830f5ae"
DATA_FILES = (
    "assets.json",
    "campaign-states.json",
    "maps.json",
    "sprites.json",
    "strings.json",
    "terrain.json",
    "units.json",
)
FACTIONS = ("blue", "red", "green", "black")


def sha256(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


def json_bytes(value: object) -> bytes:
    return (json.dumps(value, ensure_ascii=False, indent=2, sort_keys=True) + "\n").encode()


def png_dimensions(data: bytes) -> tuple[int, int]:
    if data[:8] != b"\x89PNG\r\n\x1a\n" or data[12:16] != b"IHDR":
        raise ValueError("Expected a PNG image")
    return int.from_bytes(data[16:20], "big"), int.from_bytes(data[20:24], "big")


def read_json(name: str) -> object:
    return json.loads((DATA / name).read_text(encoding="utf-8"))


def validate_and_build() -> dict[Path, bytes]:
    jar_bytes = JAR.read_bytes()
    jar_hash = sha256(jar_bytes)
    if jar_hash != BASELINE_JAR_SHA256:
        raise ValueError(f"Unexpected source JAR SHA-256: {jar_hash}")

    metadata = {name: read_json(name) for name in DATA_FILES}
    terrain = metadata["terrain.json"]
    units = metadata["units.json"]
    sprites = metadata["sprites.json"]
    map_index = metadata["maps.json"]

    tiles_by_id = {item["id"]: item for item in terrain["tiles"]}
    units_by_id = {item["id"]: item for item in units}
    if terrain["tile_count"] != 48 or sorted(tiles_by_id) != list(range(48)):
        raise ValueError("Terrain catalog must contain tile IDs 0 through 47")
    if sorted(units_by_id) != list(range(12)):
        raise ValueError("Unit catalog must contain unit IDs 0 through 11")

    files: dict[Path, bytes] = {}
    inputs: dict[str, str] = {}
    missing_sprite_images: list[dict[str, str]] = []
    for name in DATA_FILES:
        source = DATA / name
        inputs[str(source.relative_to(ROOT))] = sha256(source.read_bytes())
        files[Path("GameData") / name] = source.read_bytes()

    with zipfile.ZipFile(JAR) as archive:
        entries = {entry.filename: entry for entry in archive.infolist() if not entry.is_dir()}
        resource_names = sorted(
            name for name in entries
            if name.startswith("res/") and ".." not in PurePosixPath(name).parts
        )
        if not resource_names:
            raise ValueError("The source JAR does not contain res/ assets")

        def require_resource(name: str) -> bytes:
            if name not in entries:
                raise ValueError(f"Missing JAR resource referenced by metadata: {name}")
            data = archive.read(name)
            files[Path("Original") / name] = data
            return data

        map_ids = [item["id"] for item in map_index]
        if len(map_ids) != len(set(map_ids)) or len(map_ids) != 20:
            raise ValueError("Expected 20 uniquely named campaign and skirmish maps")

        for map_id in map_ids:
            if not map_id or any(character not in "abcdefghijklmnopqrstuvwxyz0123456789" for character in map_id):
                raise ValueError(f"Unsafe map ID: {map_id}")
            source = DATA / "maps" / f"{map_id}.json"
            if not source.is_file():
                raise ValueError(f"Missing decoded map: {source}")
            game_map = json.loads(source.read_text(encoding="utf-8"))
            if len(game_map["rows"]) != game_map["height"]:
                raise ValueError(f"Incorrect row count in {map_id}")
            for row in game_map["rows"]:
                if len(row) != game_map["width"] or any(tile not in tiles_by_id for tile in row):
                    raise ValueError(f"Invalid tile grid in {map_id}")
            for unit in game_map["units"]:
                if unit["type"] not in units_by_id or not 1 <= unit["fraction"] <= 4:
                    raise ValueError(f"Invalid unit record in {map_id}: {unit}")
            inputs[str(source.relative_to(ROOT))] = sha256(source.read_bytes())
            files[Path("GameData/maps") / f"{map_id}.json"] = source.read_bytes()

        for tile in terrain["tiles"]:
            image = require_resource(tile["image"])
            if png_dimensions(image) != (24, 24):
                raise ValueError(f"Terrain tile must be 24x24: {tile['image']}")

        for unit in units:
            if unit["source"] not in entries:
                raise ValueError(f"Missing unit definition: {unit['source']}")

        for sprite in sprites:
            if sprite["source"] not in entries:
                raise ValueError(f"Missing sprite definition: {sprite['source']}")
            for variant in sprite["image_variants"]:
                for source in variant["sources"]:
                    require_resource(source)
                for source in variant["missing_sources"]:
                    missing_sprite_images.append({"sprite": sprite["name"], "path": source})

        for faction in FACTIONS:
            atlas_name = f"res/{faction}/unit_icons.png"
            if png_dimensions(require_resource(atlas_name)) != (288, 48):
                raise ValueError(f"Unexpected unit icon atlas dimensions: {atlas_name}")

        for name in resource_names:
            require_resource(name)

    manifest = {
        "schemaVersion": 1,
        "sourceJar": {
            "path": str(JAR.relative_to(ROOT)),
            "sha256": jar_hash,
        },
        "sourceInputs": dict(sorted(inputs.items())),
        "resourceCount": len(resource_names),
        "resourceHashes": {
            path.as_posix().removeprefix("Original/"): sha256(data)
            for path, data in sorted(files.items())
            if path.parts[:1] == ("Original",)
        },
        "mapCount": len(map_ids),
        "tileCount": len(tiles_by_id),
        "unitCount": len(units_by_id),
        "knownMissingSpriteImages": sorted(missing_sprite_images, key=lambda item: (item["sprite"], item["path"])),
    }
    files[Path("GameData/manifest.json")] = json_bytes(manifest)
    return files


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--check",
        action="store_true",
        help="verify that exported bundle files match the source without writing",
    )
    args = parser.parse_args()
    files = validate_and_build()

    if args.check:
        stale = [
            str(path)
            for relative_path, expected in files.items()
            if not (path := OUTPUT / relative_path).is_file() or path.read_bytes() != expected
        ]
        if stale:
            raise SystemExit("Export is missing or stale: " + ", ".join(stale))
        print(f"Verified {len(files)} exported files in {OUTPUT}")
        return 0

    for relative_path, data in files.items():
        destination = OUTPUT / relative_path
        destination.parent.mkdir(parents=True, exist_ok=True)
        if not destination.is_file() or destination.read_bytes() != data:
            destination.write_bytes(data)
    print(f"Exported {len(files)} files to {OUTPUT}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
