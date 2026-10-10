#!/bin/zsh

set -euo pipefail

PROJECT_ROOT="${0:A:h:h}"
MIDI_DIR="$PROJECT_ROOT/Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Original/res"
OUTPUT_DIR="$PROJECT_ROOT/Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Audio"
WORK_DIR="$PROJECT_ROOT/.noindex/tmp/audio-render"
MODULE_CACHE="$PROJECT_ROOT/.noindex/ModuleCache.noindex"
RENDERER="$WORK_DIR/render-midi"

mkdir -p "$OUTPUT_DIR" "$WORK_DIR" "$MODULE_CACHE"
command -v ffmpeg >/dev/null || { echo "ffmpeg is required" >&2; exit 1; }

SWIFT_MODULECACHE_PATH="$MODULE_CACHE" \
CLANG_MODULE_CACHE_PATH="$MODULE_CACHE" \
swiftc "$PROJECT_ROOT/Tools/render_midi.swift" -o "$RENDERER"

for midi in "$MIDI_DIR"/*.mid; do
    name="${midi:t:r}"
    caf="$WORK_DIR/$name.caf"
    m4a="$OUTPUT_DIR/$name.m4a"
    "$RENDERER" "$midi" "$caf"
    ffmpeg -hide_banner -loglevel error -y \
        -i "$caf" -c:a aac -b:a 128k -movflags +faststart "$m4a"
    rm -f "$caf"
    echo "rendered $name.m4a"
done

rm -f "$RENDERER"
