#!/bin/bash
set -euo pipefail

AEII_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
AEII_BUILD_ROOT="$AEII_ROOT/.noindex"
mkdir -p "$AEII_BUILD_ROOT/logs" "$AEII_BUILD_ROOT/swift-module-cache"

env CLANG_MODULE_CACHE_PATH="$AEII_BUILD_ROOT/swift-module-cache" \
    SWIFT_MODULECACHE_PATH="$AEII_BUILD_ROOT/swift-module-cache" \
    swift test --package-path "$AEII_ROOT/Packages/AncientEmpiresEngine" \
    --scratch-path "$AEII_BUILD_ROOT/SwiftPM/AncientEmpiresEngine" \
    --cache-path "$AEII_BUILD_ROOT/SwiftPM/cache" \
    --config-path "$AEII_BUILD_ROOT/SwiftPM/configuration" \
    --security-path "$AEII_BUILD_ROOT/SwiftPM/security" --disable-sandbox "$@" \
    2>&1 | tee "$AEII_BUILD_ROOT/logs/ancient-empires-ii-tests.log"
