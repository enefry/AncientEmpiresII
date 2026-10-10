#!/usr/bin/env python3
"""Validate native localization coverage and formatting without building Xcode."""
import json
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
RESOURCES = ROOT / "Packages/AncientEmpiresEngine/Sources/AECore/Resources"
LANGUAGES = ("en", "zh-Hans", "zh-Hant")


def catalog(language):
    result = {}
    for line in (RESOURCES / f"{language}.lproj/Localizable.strings").read_text().splitlines():
        if not line.startswith('"'):
            continue
        key, value = line.removesuffix(";").split(" = ", 1)
        key, value = json.loads(key), json.loads(value)
        assert key not in result, f"Duplicate key: {language}/{key}"
        result[key] = value
    return result


def audit():
    catalogs = {language: catalog(language) for language in LANGUAGES}
    reference = catalogs["en"]
    placeholders = re.compile(r"%(?:\d+\$)?(?:0?\d+)?(?:@|d|U)")
    for language, values in catalogs.items():
        assert values.keys() == reference.keys(), f"Incomplete catalog: {language}"
        for key, value in values.items():
            assert placeholders.findall(value) == placeholders.findall(reference[key]), (language, key)
            assert "%K" not in value, f"Keypad token: {language}/{key}"
            assert value or key in {"story.176", "story.177", "story.178"}, (language, key)
        assert {f"story.{i}" for i in range(317)} <= values.keys()
        for kind in ("easy", "normal", "hard", "expert"):
            assert {f"ai.{kind}.title", f"ai.{kind}.summary"} <= values.keys()
    for folder in (ROOT / "App", ROOT / "Packages/AncientEmpiresEngine/Sources"):
        for source in folder.rglob("*.swift"):
            for key in re.findall(r'GameLocalization\.(?:text|format)\("((?:[^"\\]|\\.)*)"', source.read_text()):
                if "\\(" not in key:
                    key = json.loads('"' + key + '"')
                    assert key in reference, f"Missing key: {source.relative_to(ROOT)}: {key}"
    print(f"PASS: {len(reference)} keys × {len(LANGUAGES)} languages; original content, native lookup keys and placeholders complete")


if __name__ == "__main__":
    audit()
