# Imagegen faithful clarity preview

Created with the built-in imagegen tool, 2026-10-03. The blue archer is now packaged as a small in-game trial. The screenshot remains an art-direction preview.

- `battlefield-preview.png`: generated edit of the user's battle screenshot, preserving its broad layout, original unit identities, winter terrain and team colors. This is a generated preview, not an app screenshot or verified UI output.
- `archer-blue-preview.png`: transparent, isolated blue archer concept. Generated from the original blue unit atlas and the previous all-team map capture, then refined to remove coarse pixel stairs. The hood, bow, colors and compact character are recognizable, but contours and small details are redrawn rather than pixel-identical.

## In-game trial

`tools/package_archer_trial.swift` removes nearly transparent background noise, fits the sprite into the original 24×24 logical frame at 96×96 pixels, and builds two map frames. The second frame shifts the same pose one logical pixel to the right to follow the original icon's lateral idle movement; it is not a newly drawn walking pose. The combat side-view sprite is unchanged.

The package contains four 96×192 atlases and an explicit shared `team-mask.png` in `Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Archer`. Only the isolated blue fabric regions are recolored. The other teams use the original game's shadow/mid/light/highlight palette ramp; skin, bow, leather, metal and transparency remain fixed. Runtime never guesses colors from the rendered scene.

The generated archer appears wherever the map icon is used, including the map, unit details and recruitment selector. Acted units use the existing grayscale processor. `--classic-archer` restores the Scale2x archer; `--original-art` restores all original images. Neither switch changes saved games.

Repackage with `swift -module-cache-path .noindex/swift-module-cache tools/package_archer_trial.swift`. Source images and original JAR exports remain intact.

## Screenshot prompt constraints

Faithful high-resolution remaster of the supplied full portrait screenshot; keep map layout, positions, poses, head/body proportions, equipment, team colors, acted grayscale units, buildings, terrain and UI. Replace coarse pixel stairs with clean, sharply defined contours and compact cel-shaded color planes. Preserve the original bright palette and dark outlines. No blur, watercolor, photorealism, 3D, ornate armor or new equipment. The original screenshot is the edit target; the all-team map is the identity reference.

## Final archer refinement prompt

Precise edit: remove the large chunky pixel stair-steps from this archer sprite, replacing ONLY the raster edge geometry with beautifully crisp continuous high-resolution drawn contours. It currently looks like an enlarged 48px pixel sprite; the requested result must look like a native 512px hand-inked 2D sprite with clean vector-like curves, not a pixelated or filtered sprite. Trace and smooth the SAME hood outline, bow curvature, string, metal, face, arms, body, boots and interior color boundaries. Preserve this exact full-body silhouette, proportions, pose, facial identity, equipment, layout, position and palette. Do not add or remove costume details. Same compact big-headed nostalgic game character. Flat cel shading, separate small blue light/mid/dark regions for recoloring, gold face, dark brown bow/leather, silver-gray accents, sharp deep dark outlines, no blending between unrelated materials. NO square staircase artifacts, NO mosaic texture, NO blur, NO painterly brushwork, NO soft airbrush, NO 3D shading, NO realistic anatomy. The bow is a continuous elegant curved line and hood is a continuous round contour. Actual transparent background. Single full-body isolated sprite with clear margins. Preserve everything except improve edge precision and clarity.
