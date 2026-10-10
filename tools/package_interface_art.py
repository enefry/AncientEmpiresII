import argparse
import json
from pathlib import Path

from PIL import Image, ImageDraw, ImageFont


ROOT = Path(__file__).resolve().parents[1]
ORIGINAL = ROOT / "Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Original/res"
SOURCE = ROOT / "reference/art/imagegen-interface"
OUTPUT = ROOT / "Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Interface"
ARTIFACTS = ROOT / ".noindex/artifacts/interface-art"
CELL = 256
COLUMNS = 6
GROUPS = {
    "core": [("menu_icons", 25, 25, 10), ("action_icons", 16, 16, 7),
             ("hud_icons", 13, 16, 3), ("hud_icons_2", 11, 11, 2), ("status", 7, 9, 2)],
    "controls": [("arrow", 14, 8, 2), ("arrow_icons", 9, 7, 3), ("buttons", 14, 12, 4),
                 ("side_arrow", 8, 14, 2), ("big_circle_00", 32, 33, 1),
                 ("big_circle_01", 32, 33, 1), ("small_circle", 20, 21, 2),
                 *[(f"cursor_{index:02d}", 0, 0, 1) for index in range(6)],
                 ("tombstone", 24, 24, 1), ("mini_icons", 10, 10, 4), ("menu", 16, 16, 8)],
}


def entries(group):
    result = []
    for name, width, height, count in GROUPS[group]:
        original = Image.open(ORIGINAL / f"{name}.png").convert("RGBA")
        width = width or original.width
        height = height or original.height
        for index in range(count):
            result.append((name, index, original.crop((index * width, 0, (index + 1) * width, height))))
    return result


def prepare():
    ARTIFACTS.mkdir(parents=True, exist_ok=True)
    SOURCE.mkdir(parents=True, exist_ok=True)
    manifest = {}
    for group in GROUPS:
        frames = entries(group)
        rows = (len(frames) + COLUMNS - 1) // COLUMNS
        sheet = Image.new("RGBA", (CELL * COLUMNS, CELL * rows), (30, 37, 51, 255))
        draw = ImageDraw.Draw(sheet)
        manifest[group] = []
        for position, (name, index, frame) in enumerate(frames):
            column, row = position % COLUMNS, position // COLUMNS
            box = frame.getbbox()
            symbol = frame.crop(box)
            ratio = min(184 / symbol.width, 184 / symbol.height)
            symbol = symbol.resize((round(symbol.width * ratio), round(symbol.height * ratio)), Image.Resampling.NEAREST)
            sheet.alpha_composite(symbol, (column * CELL + (CELL - symbol.width) // 2,
                                           row * CELL + (224 - symbol.height) // 2))
            draw.text((column * CELL + 10, row * CELL + 230), f"{position + 1}: {name} {index}",
                      font=ImageFont.load_default(size=14), fill="white")
            manifest[group].append({"cell": position + 1, "name": name, "frame": index,
                                    "size": list(frame.size), "occupied": list(box)})
        sheet.save(ARTIFACTS / f"{group}-reference.png")
    (SOURCE / "manifest.json").write_text(json.dumps(manifest, indent=2) + "\n")
    print(f"Prepared {sum(len(frames) for frames in manifest.values())} icon references in {ARTIFACTS}")


def package():
    OUTPUT.mkdir(parents=True, exist_ok=True)
    atlas_images = {}
    for group in GROUPS:
        frames = entries(group)
        rows = (len(frames) + COLUMNS - 1) // COLUMNS
        sheet = Image.open(SOURCE / f"{group}.png").convert("RGBA")
        for position, (name, index, frame) in enumerate(frames):
            column, row = position % COLUMNS, position // COLUMNS
            box = (round(column * sheet.width / COLUMNS), round(row * sheet.height / rows),
                   round((column + 1) * sheet.width / COLUMNS), round((row + 1) * sheet.height / rows))
            symbol = sheet.crop(box)
            if name == "menu_icons" and index == 5:
                symbol = Image.open(SOURCE / "continue-game.png").convert("RGBA")
            alpha = symbol.getchannel("A").point(lambda value: 0 if value <= 8 else value)
            symbol.putalpha(alpha)
            occupied = symbol.getbbox()
            if occupied is None:
                raise ValueError(f"Empty generated icon: {group} {position + 1}")
            symbol = symbol.crop(occupied)
            original_box = frame.getbbox()
            target_width = (original_box[2] - original_box[0]) * 4
            target_height = (original_box[3] - original_box[1]) * 4
            if name == "menu":
                fitted = symbol.resize((target_width, target_height), Image.Resampling.LANCZOS)
            else:
                ratio = min(target_width / symbol.width, target_height / symbol.height)
                fitted = symbol.resize((max(1, round(symbol.width * ratio)), max(1, round(symbol.height * ratio))),
                                       Image.Resampling.LANCZOS)
            original = Image.open(ORIGINAL / f"{name}.png")
            atlas = atlas_images.setdefault(name, Image.new("RGBA", (original.width * 4, original.height * 4)))
            left = index * frame.width * 4 + original_box[0] * 4 + (target_width - fitted.width) // 2
            top = original_box[1] * 4 + (target_height - fitted.height) // 2
            atlas.alpha_composite(fitted, (left, top))
    marker = atlas_images["mini_icons"].crop((0, 0, 40, 40))
    palettes = [(38, 151, 225), (221, 30, 105), (30, 185, 59), (0, 67, 113)]
    for team, color in enumerate(palettes):
        tinted = marker.copy()
        for vertical in range(40):
            for horizontal in range(40):
                red, green, blue, alpha = marker.getpixel((horizontal, vertical))
                inside = (horizontal - 19.5) ** 2 + (vertical - 19.5) ** 2 < 12 ** 2
                if inside and alpha > 8 and blue > red * 1.25 and blue > green * 1.05:
                    brightness = (red * 0.2126 + green * 0.7152 + blue * 0.0722) / 137
                    tinted.putpixel((horizontal, vertical), tuple(min(255, round(channel * brightness)) for channel in color) + (alpha,))
        atlas_images["mini_icons"].paste(tinted, (team * 40, 0))
    for name, atlas in atlas_images.items():
        atlas.save(OUTPUT / f"{name}.png")
    enhanced = OUTPUT.parent
    miniature = Image.new("RGBA", (480, 40))
    units = ["soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton", "crystall"]
    for index, name in enumerate(units):
        directory = enhanced / ("Archer" if name == "archer" else f"MapUnits/{name}")
        unit = Image.open(directory / "blue.png").convert("RGBA").crop((0, 0, 96, 96))
        unit.thumbnail((40, 40), Image.Resampling.LANCZOS)
        miniature.alpha_composite(unit, (index * 40 + (40 - unit.width) // 2, 40 - unit.height))
    miniature.save(OUTPUT / "unit_icons_s.png")
    terrain = json.loads((ROOT / "Packages/AncientEmpiresEngine/Sources/AEContent/Resources/GameData/terrain.json").read_text())
    representatives = {}
    for tile in terrain["tiles"]:
        representatives.setdefault(tile["minimap_frame"], tile["id"])
    for index in range(18):
        tile = Image.open(enhanced / "Environment" / f"tiles0_{representatives[index]:02d}.png").convert("RGBA")
        tile.resize((40, 40), Image.Resampling.LANCZOS).save(OUTPUT / f"stiles0_{index:02d}.png")
    preview = Image.new("RGBA", (1536, 1024), (30, 37, 51, 255))
    draw = ImageDraw.Draw(preview)
    for position, (name, atlas) in enumerate(sorted(atlas_images.items())):
        column, row = position % 3, position // 3
        picture = atlas.copy()
        picture.thumbnail((480, 110), Image.Resampling.LANCZOS)
        preview.alpha_composite(picture, (column * 512 + 16, row * 128 + 8))
        draw.text((column * 512 + 16, row * 128 + 111), name, font=ImageFont.load_default(size=14), fill="white")
    preview.convert("RGB").save(ARTIFACTS / "packaged-overview.png")
    print(f"Packaged {len(atlas_images) + 19} interface atlases and miniature terrain tiles")


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("mode", choices=["prepare", "package"])
    arguments = parser.parse_args()
    prepare() if arguments.mode == "prepare" else package()
