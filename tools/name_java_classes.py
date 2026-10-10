#!/usr/bin/env python3
"""Generate semantically named Java references without changing recovered logic."""

import hashlib
import json
from pathlib import Path
import re


ROOT = Path(__file__).resolve().parents[1]
REFERENCE = ROOT / "reference"
# Keep comments and string/character literals byte-for-byte. Only rename identifiers.
JAVA_TOKEN = re.compile(
    r'(?P<comment>//[^\n]*|/\*[\s\S]*?\*/)'
    r'|(?P<literal>"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\')'
    r'|(?P<identifier>[A-Za-z_$][A-Za-z0-9_$]*)'
    r'|(?P<other>[\s\S])'
)


def rename_identifiers(source, names):
    return "".join(
        names.get(token.group(), token.group()) if token.lastgroup == "identifier" else token.group()
        for token in JAVA_TOKEN.finditer(source)
    )


def digest(source):
    return hashlib.sha256(source.encode("utf-8")).hexdigest()


def build_named_reference(reference=REFERENCE):
    definitions = json.loads((reference / "class-names.json").read_text())["classes"]
    symbols = json.loads((reference / "symbol-map.json").read_text())["classes"]
    symbol_by_original = {item["original_class"]: item for item in symbols}
    if {item["original"] for item in definitions} != set(symbol_by_original):
        raise ValueError("Class definitions must cover exactly the recovered classes.")

    replacements = {}
    class_map = []
    for definition in definitions:
        symbol = symbol_by_original[definition["original"]]
        source = Path(symbol["normalized_source"])
        old_name = source.stem
        new_name = definition["name"]
        if not re.fullmatch(r"[A-Z][A-Za-z0-9]*", new_name):
            raise ValueError(f"Invalid semantic class name: {new_name}")
        if old_name in replacements or new_name in replacements.values():
            raise ValueError("Class aliases must be unique.")
        replacements[old_name] = new_name
        destination = Path("java-named") / source.relative_to("java-normalized").with_name(new_name + ".java")
        class_map.append({**definition, **symbol, "named_source": str(destination),
                          "named_class": definition["original"].rsplit(".", 1)[0] + "." + new_name})

    reverse = {new: old for old, new in replacements.items()}
    generated = []
    for item in class_map:
        source = (reference / item["normalized_source"]).read_text()
        source_identifiers = {t.group() for t in JAVA_TOKEN.finditer(source) if t.lastgroup == "identifier"}
        if source_identifiers & set(reverse):
            raise ValueError("A new class name collides with an existing source identifier.")
        renamed = rename_identifiers(source, replacements)
        if rename_identifiers(renamed, reverse) != source:
            raise ValueError(f"Renaming changed more than class identifiers: {item['original']}")
        if not re.search(r"\b(?:class|interface) " + re.escape(item["name"]) + r"\b", renamed):
            raise ValueError(f"Missing renamed declaration: {item['name']}")
        identifiers = {t.group() for t in JAVA_TOKEN.finditer(renamed) if t.lastgroup == "identifier"}
        if identifiers & set(replacements):
            raise ValueError(f"Old class references remain: {item['original']}")
        item["normalized_sha256"] = digest(source)
        item["named_sha256"] = digest(renamed)
        generated.append((reference / item["named_source"], renamed))

    # Check previous generated hashes before overwriting any edited reading files.
    manifest = reference / "semantic-class-map.json"
    if manifest.exists():
        for previous in json.loads(manifest.read_text())["classes"]:
            path = reference / previous["named_source"]
            if path.exists() and digest(path.read_text()) != previous["named_sha256"]:
                raise ValueError(f"Named reference was edited; preserve edits before regeneration: {path}")
    expected = {path for path, _ in generated}
    output = reference / "java-named"
    unexpected = set(output.rglob("*.java")) - expected
    if unexpected:
        raise ValueError(f"Unexpected existing named sources: {sorted(map(str, unexpected))}")
    for path, source in generated:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(source)
    manifest.write_text(json.dumps({
        "description": "Class names assigned by responsibility. Original packages, comments, literals, field/method aliases and decompiler limitations are preserved.",
        "validation": "18-class coverage; reversible identifier-only transformation verified per file. Not compiled or runtime validated.",
        "classes": class_map,
    }, indent=2, ensure_ascii=False) + "\n")

    lines = ["# 类语义命名映射", "", "按已核实职责命名全部 18 个类型（包含一个接口），保留原来的包结构。名称是人工赋予的阅读名称，不是恢复的原作者名称。", "",
             "阅读代码优先使用 `java-named/`；原版与编号版仍保留，用于核对。字段和方法继续使用 `field_N` / `method_N`，本次只重新命名类型。", "",
             "| 原名 | 新名称与代码 | 职责 | 命名依据 |", "| --- | --- | --- | --- |"]
    for item in class_map:
        lines.append(f"| `{item['original']}` | [{item['name']}]({item['named_source']}) | {item['role']} | {item['evidence']} |")
    lines.extend(["", "## 变换与验证", "",
                  "- 同步修改文件名、类型声明、构造器、继承/实现、import 和类型引用；保留包名。",
                  "- 注释、字符串、字符常量和 `renamed from` 原符号说明保持原样；没有改写 JAR、Manifest 或反射字符串。",
                  "- 每个文件都验证：逆向替换新类名后与编号版完全一致。因此该变换只修改类标识符，不修复原有反编译问题。",
                  "- 原名、编号版、新名称、文件路径与哈希记录在 `semantic-class-map.json`，字段/方法映射仍见 `symbol-map.json`。",
                  "- 单独重新生成：`python3 tools/name_java_classes.py`。重新反编译的主脚本也会生成这套阅读版。",
                  "- 仍属于参考源码，未重新编译或运行验证；`Sprite` 与 `GameController` 的多职责原样保留，后续 Swift 移植时再考虑拆分。", ""])
    (reference / "CLASS_NAMES.md").write_text("\n".join(lines))
    return class_map


if __name__ == "__main__":
    classes = build_named_reference()
    print(f"Named {len(classes)} classes; verified reversible identifier-only changes for every file.")
    for item in classes:
        print(f"{item['original']} -> {item['named_class']}")
