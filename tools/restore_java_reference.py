#!/usr/bin/env python3
"""Reproduce Java reference material from the selected original JAR."""

import argparse
import hashlib
import json
from pathlib import Path
import re
import subprocess
import zipfile

from name_java_classes import build_named_reference


ROOT = Path(__file__).resolve().parents[1]
DEFAULT_JAR = ROOT / "reference/inputs/Ancient-Empires-II_J2ME_EN_v25/Ancient Empires II (2012)(Macrospace, SilentKnight)(v2.5).jar"
DEFAULT_DECOMPILER = Path("/Applications/Android Studio.app/Contents/plugins/java-decompiler/lib/java-decompiler.jar")


def digest(data):
    return hashlib.sha256(data).hexdigest()


def build_symbol_map(reference):
    """Record aliases emitted by the decompiler, including original type signatures."""
    class_map = []
    members = []
    for source in sorted((reference / "java-normalized").rglob("*.java")):
        content = source.read_text()
        renamed = re.search(r"^// \$FF: renamed from: ([\w.]+)$", content, re.MULTILINE)
        name = renamed.group(1) if renamed else "aeii.Main"
        relative = str(source.relative_to(reference))
        class_map.append({"original_class": name, "normalized_source": relative,
                          "original_source": "java/" + name.replace(".", "/") + ".java"})
        lines = content.splitlines()
        for index, line in enumerate(lines[:-1]):
            prefix = "// $FF: renamed from: "
            if line.strip().startswith(prefix) and line.startswith("   "):
                members.append({"original_class": name,
                                "original_signature": line.strip()[len(prefix):],
                                "normalized_declaration": lines[index + 1].strip(),
                                "file": relative, "line": index + 2})
    mapping = {"classes": class_map, "members": members,
               "note": "Automatic identifier aliases, not recovered author names. Signatures are decompiler-emitted; use javap descriptors for exact JVM identity."}
    (reference / "symbol-map.json").write_text(json.dumps(mapping, indent=2, ensure_ascii=False) + "\n")
    return class_map


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--jar", type=Path, default=DEFAULT_JAR)
    parser.add_argument("--decompiler", type=Path, default=DEFAULT_DECOMPILER)
    parser.add_argument("--cfr", type=Path, help="Optional CFR JAR for a second independent reference view.")
    args = parser.parse_args()
    jar = args.jar.resolve()
    decompiler = args.decompiler.resolve()
    if not decompiler.is_file():
        parser.error("Decompiler JAR not found; supply --decompiler.")
    if args.cfr is not None and not args.cfr.is_file():
        parser.error("CFR JAR not found.")

    reference = ROOT / "reference"
    original = reference / "original"
    sources = reference / "java"
    normalized = reference / "java-normalized"
    bytecode = reference / "bytecode"
    logs = ROOT / ".noindex/logs/decompile"
    for directory in (original, sources, normalized, bytecode, logs):
        directory.mkdir(parents=True, exist_ok=True)

    entries = []
    classes = []
    with zipfile.ZipFile(jar) as archive:
        for info in archive.infolist():
            if info.is_dir():
                continue
            relative = Path(info.filename)
            if relative.is_absolute() or ".." in relative.parts:
                raise ValueError(f"Unsafe archive entry: {info.filename}")
            data = archive.read(info)
            destination = original / relative
            destination.parent.mkdir(parents=True, exist_ok=True)
            # Only overwrite a previous extraction with identical original bytes.
            if destination.exists() and destination.read_bytes() != data:
                raise ValueError(f"Existing reference differs: {destination}")
            destination.write_bytes(data)
            entries.append({"path": info.filename, "bytes": len(data), "sha256": digest(data)})
            if relative.suffix == ".class":
                classes.append(relative)

    # Preserve original obfuscated identifiers; avoid silent synthetic-member removal.
    options = ["-ren=0", "-rbr=0", "-rsy=0", "-hdc=0", "-hes=0", "-bsm=1", "-sef=1", "-log=INFO"]
    command = ["java", "-cp", str(decompiler),
               "org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler",
               *options, str(original), str(sources)]
    result = subprocess.run(command, capture_output=True, text=True)
    log = result.stdout + result.stderr
    (logs / "fernflower.log").write_text(log)
    (reference / "decompiler.log").write_text(log)
    if result.returncode:
        raise RuntimeError(f"Decompiler failed; see {logs / 'fernflower.log'}")

    # JVM members may share names with different descriptors, which Java source
    # cannot express. Keep the original view and also generate identifier aliases.
    normalized_options = ["-ren=1" if option == "-ren=0" else option for option in options]
    normalized_command = ["java", "-cp", str(decompiler),
                          "org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler",
                          *normalized_options, str(original), str(normalized)]
    normalized_result = subprocess.run(normalized_command, capture_output=True, text=True)
    normalized_log = normalized_result.stdout + normalized_result.stderr
    (logs / "fernflower-normalized.log").write_text(normalized_log)
    (reference / "decompiler-normalized.log").write_text(normalized_log)
    if normalized_result.returncode:
        raise RuntimeError("Identifier-normalized decompilation failed; see decompiler-normalized.log")

    cfr_report = None
    if args.cfr is not None:
        cfr = args.cfr.resolve()
        cfr_sources = reference / "java-cfr"
        cfr_sources.mkdir(parents=True, exist_ok=True)
        cfr_options = ["--rename", "true", "--outputdir", str(cfr_sources)]
        cfr_result = subprocess.run(["java", "-jar", str(cfr), str(jar), *cfr_options],
                                    capture_output=True, text=True)
        cfr_log = cfr_result.stdout + cfr_result.stderr
        (logs / "cfr.log").write_text(cfr_log)
        (reference / "decompiler-cfr.log").write_text(cfr_log)
        if cfr_result.returncode:
            raise RuntimeError("CFR failed; see decompiler-cfr.log")
        cfr_report = {"tool": str(cfr), "sha256": digest(cfr.read_bytes()),
                      "options": ["--rename", "true"],
                      "source_count": len(list(cfr_sources.rglob("*.java"))),
                      "missing_sources": [str(p) for p in classes if not (cfr_sources / p.with_suffix(".java")).is_file()],
                      "summary": (cfr_sources / "summary.txt").read_text()}

    for relative in classes:
        class_name = ".".join(relative.with_suffix("").parts)
        listing = subprocess.run(
            ["javap", "-classpath", str(jar), "-p", "-c", "-s", "-constants", class_name],
            capture_output=True, text=True, check=True,
        )
        destination = bytecode / relative.with_suffix(".javap.txt")
        destination.parent.mkdir(parents=True, exist_ok=True)
        destination.write_text(listing.stdout)

    missing = [str(p) for p in classes if not (sources / p.with_suffix(".java")).is_file()]
    diagnostics = [line for line in log.splitlines() if "WARN" in line or "ERROR" in line]
    placeholders = []
    for source in sorted(list(sources.rglob("*.java")) + list(normalized.rglob("*.java"))):
        for number, line in enumerate(source.read_text().splitlines(), 1):
            if "Couldn't be decompiled" in line or "$FF: Couldn't" in line:
                placeholders.append({"file": str(source.relative_to(reference)), "line": number, "text": line.strip()})

    report = {
        "input": str(jar.relative_to(ROOT)) if jar.is_relative_to(ROOT) else str(jar),
        "input_sha256": digest(jar.read_bytes()),
        "decompiler": str(decompiler),
        "decompiler_sha256": digest(decompiler.read_bytes()),
        "java_version": subprocess.run(["java", "-version"], capture_output=True, text=True, check=True).stderr.strip(),
        "decompiler_options": options,
        "normalized_decompiler_options": normalized_options,
        "class_count": len(classes),
        "source_count": len(list(sources.rglob("*.java"))),
        "normalized_source_count": len(list(normalized.rglob("*.java"))),
        "normalized_class_map": build_symbol_map(reference),
        "cfr": cfr_report,
        "missing_sources": missing,
        "diagnostics": diagnostics,
        "normalized_diagnostics": [line for line in normalized_log.splitlines() if "WARN" in line or "ERROR" in line],
        "method_placeholders": placeholders,
        "entries": entries,
        "validation": "Archive hashes, source coverage and javap generation only; not recompiled or runtime validated.",
    }
    (reference / "inventory.json").write_text(json.dumps(report, indent=2, ensure_ascii=False) + "\n")
    named_classes = build_named_reference(reference)
    print(f"Generated {len(named_classes)} semantic class references.")
    print(json.dumps({key: report[key] for key in ("input_sha256", "class_count", "source_count", "normalized_source_count", "missing_sources", "diagnostics", "normalized_diagnostics", "method_placeholders")}, indent=2))
    if missing or placeholders or report["normalized_source_count"] != len(classes) or (cfr_report and cfr_report["missing_sources"]):
        raise SystemExit("Reference is incomplete; inspect inventory.json and bytecode listings.")


if __name__ == "__main__":
    main()
