#!/usr/bin/env python3
"""Convert the pinned 2016 Reloaded copies of the eight downloadable maps.

These are community-preserved conversions, not original server response bytes.
The source hashes and explicit tile mapping prevent silently importing later edits.
"""

import argparse
import hashlib
import json
import struct
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = ROOT / "reference/bonus-skirmish"
OUTPUT = ROOT / "Packages/AncientEmpiresEngine/Sources/AEContent/Resources/GameData"
# Reloaded removed original tile 28, and its building tiles start at 36.
# All IDs used here were verified against unchanged original maps or PNG pixels.
TILES = {**{i: i for i in range(28)}, 28: 29, 29: 30,
         **{i: i + 1 for i in range(36, 46)}}


def convert(data, record, index):
    offset = 0

    def read(fmt):
        nonlocal offset
        result = struct.unpack_from(">" + fmt, data, offset)
        offset += struct.calcsize(">" + fmt)
        return result

    length, = read("H")
    author = data[offset:offset + length].decode("utf-8")
    offset += length
    assert author == "Macrospace"
    enabled = read("4?")
    width, height = read("ii")
    assert 2 <= width <= 64 and 2 <= height <= 64
    rows = [[0] * width for _ in range(height)]
    for x in range(width):
        for y in range(height):
            tile, = read("h")
            rows[y][x] = TILES[tile]
    count, = read("i")
    assert 0 <= count <= 800
    units = []
    for i in range(count):
        team, kind, x, y = read("iiii")
        assert 0 <= team < 4 and kind in (6, 9)
        assert 0 <= x < width and 0 <= y < height
        units.append(dict(index=i, type=kind, fraction=team + 1, x=x, y=y,
                          pixel_x=x * 24, pixel_y=y * 24, in_bounds=True))
    assert offset == len(data)
    buildings = [dict(x=x, y=y, tile=t, terrain=8 if t == 27 else 8 + (t - 37) % 2,
                      fraction=0 if t == 27 else (t - 37) // 2)
                 for y, row in enumerate(rows) for x, t in enumerate(row)
                 if t == 27 or 37 <= t <= 46]
    factions = {b["fraction"] for b in buildings if b["terrain"] == 9 and b["fraction"] > 0}
    assert factions == {i + 1 for i, access in enumerate(enabled) if access}
    assert 2 <= len(factions) <= 4
    assert len({(u["x"], u["y"]) for u in units}) == len(units)
    assert all(u["fraction"] in factions for u in units)
    return dict(id=record["id"], kind="skirmish", index=index,
                name=f'({len(factions)}){record["name"].title()}', width=width, height=height,
                rows=rows, units=units, buildings=buildings)


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--check", action="store_true")
    args = parser.parse_args()
    sources = json.loads((SOURCE / "sources.json").read_text())
    catalog = []
    files = {}
    original_maps = [json.loads(p.read_text()) for p in (OUTPUT / "maps").glob("[ms]*.json")]
    for index, record in enumerate(sources["maps"]):
        data = (SOURCE / "maps" / (record["id"] + ".aem")).read_bytes()
        assert hashlib.sha256(data).hexdigest() == record["sha256"]
        game_map = convert(data, record, index)
        assert not any(game_map["rows"] == m["rows"] for m in original_maps)
        catalog.append(dict(id=record["id"], name=game_map["name"],
                            source_sha256=record["sha256"], source_commit=sources["commit"]))
        files[OUTPUT / "maps" / (record["id"] + ".json")] = game_map
    files[OUTPUT / "bonus-maps.json"] = catalog
    for path, value in files.items():
        content = json.dumps(value, ensure_ascii=False, indent=2) + "\n"
        if args.check:
            assert path.read_text() == content, f"Stale bonus map content: {path}"
        else:
            path.write_text(content)
    print(f'{"Verified" if args.check else "Exported"} {len(catalog)} bonus skirmish maps')


if __name__ == "__main__":
    main()
