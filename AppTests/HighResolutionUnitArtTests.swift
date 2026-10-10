import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignMapMotionTests: XCTestCase {
    func testMotionLoopsAreContinuousBoundedAndRespectReducedMotion() throws {
        for type in 0..<12 {
            for moving in [false, true] {
                let animation = try XCTUnwrap(CampaignUnitMotion.animation(type: type, unitID: 3, size: 60,
                    moving: moving, reduceMotion: false, time: 100))
                let values = try XCTUnwrap(animation.values as? [NSValue])
                XCTAssertEqual(values.count, 25)
                XCTAssertEqual(values.first, values.last)
                XCTAssertGreaterThan(Set(values).count, 10)
                XCTAssertEqual(animation.calculationMode, .linear)
                for value in values {
                    let transform = value.caTransform3DValue
                    XCTAssertLessThanOrEqual(abs(transform.m41), 0.4)
                    XCTAssertLessThanOrEqual(abs(transform.m42), 2.5)
                    XCTAssertLessThan(abs(transform.m22 - 1), 0.03)
                    if !moving, ![4, 8, 11].contains(type) { XCTAssertEqual(transform.m42, 0) }
                }
                XCTAssertNil(CampaignUnitMotion.animation(type: type, unitID: 3, size: 60,
                    moving: moving, reduceMotion: true))
            }
        }
    }

    func testRerenderKeepsPhaseAndUnitsDoNotBreatheInUnison() throws {
        func motion(_ id: Int, _ time: Double) throws -> CAKeyframeAnimation {
            try XCTUnwrap(CampaignUnitMotion.animation(type: 0, unitID: id, size: 60,
                moving: false, reduceMotion: false, time: time))
        }
        let first = try motion(1, 100)
        let rerendered = try motion(1, 100.2)
        XCTAssertEqual(first.beginTime, rerendered.beginTime, accuracy: 0.0001)
        XCTAssertNotEqual(first.beginTime, try motion(2, 100).beginTime)
    }

    func testUnitMotionLeavesHealthCellsAndActedUnitsStill() throws {
        var acted = CampaignUnitState(id: 1, type: 1, fraction: 1, x: 3, y: 4)
        acted.hasActed = true
        var corpse = CampaignUnitState(id: 2, type: 0, fraction: 2, x: 4, y: 4, hitPoints: 0)
        corpse.corpseTransitionsRemaining = 3
        let canvas = try makeCanvas(units: [
            .init(id: 0, type: 0, fraction: 1, x: 2, y: 4, hitPoints: 75), acted, corpse
        ])
        let unit = try unitLayer(0, canvas)
        let sprite = try XCTUnwrap(unit.sublayers?.first { $0.name?.hasSuffix(".sprite") == true })
        let health = try XCTUnwrap(unit.sublayers?.first { $0.name?.hasSuffix(".hp") == true })
        XCTAssertEqual(sprite.anchorPoint, CGPoint(x: 0.5, y: 1))
        XCTAssertEqual(sprite.animation(forKey: "idle") != nil, !UIAccessibility.isReduceMotionEnabled)
        XCTAssertNil(unit.animation(forKey: "idle"))
        XCTAssertTrue(health.animationKeys()?.isEmpty ?? true)
        XCTAssertTrue(CATransform3DIsIdentity(health.transform))
        XCTAssertNil(try unitLayer(1, canvas).sublayers?.first?.animation(forKey: "idle"))
        XCTAssertTrue(try unitLayer(2, canvas).sublayers?.isEmpty ?? true)
        let cell = try XCTUnwrap(canvas.subviews.first { $0.accessibilityIdentifier == "campaign.cell.2.4" })
        XCTAssertEqual(cell.frame, unit.frame)
        canvas.configureTileSize(72)
        canvas.layoutIfNeeded()
        XCTAssertEqual(sprite.bounds.size, unit.bounds.size)
        XCTAssertEqual(cell.frame, unit.frame)
    }

    func testScriptedMovementInterpolatesBetweenTicksHonorsDelayAndStopsAtDestination() throws {
        let canvas = try makeCanvas(units: [.init(id: 0, type: 0, fraction: 1, x: 2, y: 4)])
        let unit = try unitLayer(0, canvas)
        let scene = CampaignScenePresentation(.arrival, movements: [
            .init(unitID: 0, route: [.init(x: 2, y: 4), .init(x: 3, y: 4), .init(x: 3, y: 5)], delayTicks: 2)
        ], unitSpeed: 6)
        canvas.presentSceneMovement(scene, tick: 1.5)
        let origin = unit.position
        canvas.presentSceneMovement(scene, tick: 2.5)
        XCTAssertEqual(unit.position.x - origin.x, 7.5, accuracy: 0.001)
        XCTAssertEqual(unit.position.y, origin.y)
        canvas.presentSceneMovement(scene, tick: 6.5)
        XCTAssertEqual(unit.position.x - origin.x, 60, accuracy: 0.001)
        XCTAssertEqual(unit.position.y - origin.y, 7.5, accuracy: 0.001)
        canvas.presentSceneMovement(scene, tick: 100)
        XCTAssertEqual(unit.position, CGPoint(x: origin.x + 60, y: origin.y + 60))
        XCTAssertNil(unit.sublayers?.first?.animation(forKey: "travel"))
    }

    func testMovementCompletesOnceAndReturnsToIdleInLiveCanvas() async throws {
        let units = (0..<12).map { type in
            CampaignUnitState(id: type, type: type, fraction: type % 4 + 1,
                x: 2 + type % 6, y: 4 + type / 6 * 2, hitPoints: type == 0 ? 75 : 100)
        }
        let canvas = try makeCanvas(units: units)
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = UIViewController()
        controller.view.backgroundColor = .black
        controller.view.addSubview(canvas)
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        canvas.frame.origin = CGPoint(x: -120, y: -80)
        try await Task.sleep(for: .milliseconds(500))
        var completions = 0
        let start = CACurrentMediaTime()
        canvas.animateMovement(unitID: 0, from: units[0].position,
            route: [.init(x: 2, y: 5), .init(x: 3, y: 5), .init(x: 4, y: 5)]) { completions += 1 }
        let unit = try unitLayer(0, canvas)
        let movement = try XCTUnwrap(unit.animation(forKey: "movement"))
        XCTAssertEqual(movement.duration, 0.792, accuracy: 0.0001)
        while completions == 0, CACurrentMediaTime() - start < 2 {
            try await Task.sleep(for: .milliseconds(20))
        }
        XCTAssertEqual(completions, 1)
        XCTAssertLessThan(CACurrentMediaTime() - start, 1.5)
        XCTAssertNil(unit.sublayers?.first?.animation(forKey: "travel"))
        XCTAssertEqual(unit.sublayers?.first?.animation(forKey: "idle") != nil, !UIAccessibility.isReduceMotionEnabled)
        try await Task.sleep(for: .milliseconds(800))
        XCTAssertEqual(completions, 1)
        let attachment = XCTAttachment(image: UIGraphicsImageRenderer(bounds: window.bounds).image { _ in
            window.drawHierarchy(in: window.bounds, afterScreenUpdates: true)
        })
        attachment.name = "Smooth map animation in live canvas"
        attachment.lifetime = .keepAlways
        add(attachment)
    }

    private func makeCanvas(units: [CampaignUnitState]) throws -> CampaignMapCanvasView {
        let map = try GameResources.missionZero()
        let canvas = try CampaignMapCanvasView(map: map, terrain: GameResources.terrainCatalog(),
                                              unitDefinitions: GameResources.unitDefinitions())
        canvas.configureTileSize(60)
        canvas.frame = CGRect(origin: .zero, size: canvas.contentSize)
        canvas.render(map: map, units: units, selected: nil, movement: [], attackable: [])
        canvas.layoutIfNeeded()
        return canvas
    }

    private func unitLayer(_ id: Int, _ canvas: CampaignMapCanvasView) throws -> CALayer {
        try XCTUnwrap(canvas.layer.sublayers?.first { $0.name == "campaign.unit.\(id)" })
    }
}

@MainActor
final class HighResolutionUnitArtTests: XCTestCase {
    func testGeneratedInterfaceArtworkLoadsEveryPackagedAssetAndPreservesAtlasCrops() throws {
        XCTAssertEqual(GameResources.interfaceArtworkNames.count, 40)
        for name in GameResources.interfaceArtworkNames {
            let packaged = try XCTUnwrap(UIImage(data: GameResources.enhancedInterfaceData(named: name))?.cgImage, name)
            let original = try XCTUnwrap(OriginalArt.image(name, useEnhanced: false)?.cgImage, name)
            let enhanced = try XCTUnwrap(OriginalArt.image(name), name)
            XCTAssertEqual(packaged.width, original.width * 4, name)
            XCTAssertEqual(packaged.height, original.height * 4, name)
            XCTAssertEqual(enhanced.size, CGSize(width: original.width, height: original.height), name)
            XCTAssertEqual(try pixels(XCTUnwrap(enhanced.cgImage)), try pixels(packaged), name)
        }
        for (name, width, height) in [("menu_icons", 25, 25), ("action_icons", 16, 16),
                                     ("hud_icons", 13, 16), ("hud_icons_2", 11, 11), ("status", 7, 9),
                                     ("arrow", 14, 8), ("arrow_icons", 9, 7), ("buttons", 14, 12),
                                     ("side_arrow", 8, 14), ("small_circle", 20, 21), ("mini_icons", 10, 10),
                                     ("menu", 16, 16), ("unit_icons_s", 10, 10)] {
            let atlas = try XCTUnwrap(OriginalArt.image(name)?.cgImage)
            for horizontal in stride(from: 0, to: atlas.width / 4, by: width) {
                let frame = try XCTUnwrap(OriginalArt.frame(name, x: horizontal, width: width, height: height)?.cgImage)
                let crop = try XCTUnwrap(atlas.cropping(to: CGRect(x: horizontal * 4, y: 0, width: width * 4, height: height * 4)))
                let colors = try pixels(frame)
                XCTAssertEqual(colors, try pixels(crop), name)
                XCTAssertTrue(stride(from: 3, to: colors.count, by: 4).contains { colors[$0] > 128 }, name)
            }
        }
        XCTAssertThrowsError(try GameResources.enhancedInterfaceData(named: "../portraits"))
    }

    func testGeneratedCursorsKeepEmptyCentersAndMinimapTeamsKeepIdenticalRims() throws {
        for index in 0..<5 {
            let cursor = try XCTUnwrap(OriginalArt.image(String(format: "cursor_%02d", index))?.cgImage)
            let colors = try pixels(cursor)
            XCTAssertEqual(colors[((cursor.height / 2) * cursor.width + cursor.width / 2) * 4 + 3], 0)
        }
        let teams = try (0..<4).map { team in
            try pixels(XCTUnwrap(OriginalArt.frame("mini_icons", x: team * 10, width: 10, height: 10)?.cgImage))
        }
        for vertical in 0..<40 {
            for horizontal in 0..<40 {
                let offset = (vertical * 40 + horizontal) * 4
                for team in teams.dropFirst() {
                    XCTAssertEqual(team[offset + 3], teams[0][offset + 3])
                    if (Double(horizontal) - 19.5) * (Double(horizontal) - 19.5)
                        + (Double(vertical) - 19.5) * (Double(vertical) - 19.5) >= 144 {
                        XCTAssertEqual(Array(team[offset..<(offset + 4)]), Array(teams[0][offset..<(offset + 4)]))
                    }
                }
            }
        }
        XCTAssertEqual(Set(teams).count, 4)
    }

    func testGeneratedStartupArtLoadsPackagedPixelsAndPreservesTitleGeometry() throws {
        let packaged = try XCTUnwrap(UIImage(data: GameResources.enhancedStartupData(named: "splash"))?.cgImage)
        let enhanced = try XCTUnwrap(OriginalArt.image("splash"))
        let original = try XCTUnwrap(OriginalArt.image("splash", useEnhanced: false))
        XCTAssertEqual(packaged.width, 960)
        XCTAssertEqual(packaged.height, 1280)
        XCTAssertEqual(enhanced.size, original.size)
        XCTAssertEqual(enhanced.scale, 4)
        XCTAssertEqual(try pixels(XCTUnwrap(enhanced.cgImage)), try pixels(packaged))

        let title = try XCTUnwrap(OriginalArt.frame("splash", x: 0, width: 240, height: 85)?.cgImage)
        let cover = try XCTUnwrap(OriginalArt.frame("splash", x: 0, y: 85, width: 240, height: 235)?.cgImage)
        XCTAssertEqual(title.width, 960)
        XCTAssertEqual(title.height, 340)
        XCTAssertEqual(cover.width, 960)
        XCTAssertEqual(cover.height, 940)
        XCTAssertThrowsError(try GameResources.enhancedStartupData(named: "../splash"))
        XCTAssertThrowsError(try GameResources.enhancedStartupData(named: "logo"))
    }

    func testGeneratedStoryImagesLoadPackagedPixelsAndPreserveLogicalSize() throws {
        for name in GameResources.storyIllustrationNames {
            let packaged = try XCTUnwrap(UIImage(data: GameResources.enhancedStoryData(named: name))?.cgImage)
            let enhanced = try XCTUnwrap(OriginalArt.image(name))
            let original = try XCTUnwrap(OriginalArt.image(name, useEnhanced: false))
            XCTAssertEqual(enhanced.size, original.size)
            XCTAssertEqual(enhanced.scale, 4)
            XCTAssertEqual(packaged.width, 960)
            XCTAssertEqual(packaged.height, Int(original.size.height) * 4)
            XCTAssertEqual(try pixels(XCTUnwrap(enhanced.cgImage)), try pixels(packaged))
            let crop = try XCTUnwrap(OriginalArt.frame(name, x: 20, y: 10, width: 120, height: 80)?.cgImage)
            let expected = try XCTUnwrap(packaged.cropping(to: CGRect(x: 80, y: 40, width: 480, height: 320)))
            XCTAssertEqual(try pixels(crop), try pixels(expected))
            XCTAssertNil(OriginalArt.frame(name, x: 239, y: 0, width: 2, height: 20))
        }
        XCTAssertThrowsError(try GameResources.enhancedStoryData(named: "../intro0"))
        XCTAssertThrowsError(try GameResources.enhancedStoryData(named: "intro6"))
    }

    func testStoryIllustrationsDisplayInActualIntroductionAndEndingViews() async throws {
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        for index in 0..<6 {
            let controller: UIViewController = index == 5 ? OriginalCampaignEndingViewController()
                : OriginalIntroductionViewController(illustrationID: index)
            window.rootViewController = controller
            window.makeKeyAndVisible()
            window.layoutIfNeeded()
            try await Task.sleep(for: .milliseconds(1200))
            let illustration = try XCTUnwrap(controller.view.subviews.first {
                $0.accessibilityIdentifier == (index == 5 ? "ending.illustration" : "intro.illustration")
            } as? UIImageView)
            XCTAssertEqual(illustration.image?.cgImage?.width, 960)
            XCTAssertEqual(illustration.alpha, 1, accuracy: 0.05)
            XCTAssertEqual(illustration.contentMode, .scaleAspectFit)
            XCTAssertEqual(illustration.layer.magnificationFilter, .linear)
            XCTAssertEqual(illustration.bounds.width / illustration.bounds.height, 240 / 139.0, accuracy: 0.001)
            attach(controller.view, name: "Enhanced story intro\(index) in game UI")
        }
    }

    func testTemporaryMusicLaunchOverrideDoesNotChangeSavedPreference() {
        let defaults = UserDefaults.standard
        let arguments = defaults.volatileDomain(forName: UserDefaults.argumentDomain)
        let saved = defaults.persistentDomain(forName: Bundle.main.bundleIdentifier!)
        defer { defaults.setVolatileDomain(arguments, forName: UserDefaults.argumentDomain) }
        var override = arguments
        override["game.music"] = GameLocalization.text("NO")
        defaults.setVolatileDomain(override, forName: UserDefaults.argumentDomain)
        XCTAssertFalse(GamePreferences.enabled(.music))
        override["game.music"] = GameLocalization.text("YES")
        defaults.setVolatileDomain(override, forName: UserDefaults.argumentDomain)
        XCTAssertTrue(GamePreferences.enabled(.music))
        XCTAssertEqual(defaults.persistentDomain(forName: Bundle.main.bundleIdentifier!) as NSDictionary?, saved as NSDictionary?)
    }

    func testSoldierUsesFourTimesPixelsWithoutChangingLogicalGeometry() throws {
        for fraction in 1...4 {
            let sprite = try OriginalSprite("soldier", fraction: fraction)
            let original = try OriginalSprite("soldier", fraction: fraction, useEnhanced: false)
            XCTAssertEqual(sprite.size, CGSize(width: 34, height: 36))
            XCTAssertEqual(sprite.sequences, original.sequences)
            XCTAssertEqual(sprite.frameDelay, original.frameDelay)
            XCTAssertEqual(sprite.frames.count, 6)
            for index in sprite.frames.indices {
                XCTAssertEqual(sprite.frames[index].offset, original.frames[index].offset)
                XCTAssertEqual(sprite.frames[index].image.size, original.frames[index].image.size)
                XCTAssertEqual(sprite.frames[index].image.scale, 4)
                XCTAssertEqual(sprite.frames[index].image.cgImage?.width, 136)
                XCTAssertEqual(sprite.frames[index].image.cgImage?.height, 144)
            }
            let faction = ["blue", "red", "green", "black"][fraction - 1]
            let atlas = try XCTUnwrap(OriginalArt.image("\(faction)/soldier")?.cgImage)
            for frame in 0..<3 {
                let expected = try XCTUnwrap(atlas.cropping(to: CGRect(x: frame * 136, y: 0, width: 136, height: 144)))
                XCTAssertEqual(try pixels(expected), try pixels(XCTUnwrap(sprite.frames[frame].image.cgImage)))
                let forward = try pixels(XCTUnwrap(sprite.frames[frame].image.cgImage))
                let mirrored = try pixels(XCTUnwrap(sprite.frames[frame + 3].image.cgImage))
                for vertical in 0..<144 {
                    for horizontal in 0..<136 {
                        let source = (vertical * 136 + horizontal) * 4
                        let destination = (vertical * 136 + 135 - horizontal) * 4
                        for channel in 0..<4 {
                            XCTAssertLessThanOrEqual(abs(Int(forward[source + channel]) - Int(mirrored[destination + channel])), 1)
                        }
                    }
                }
            }
        }
    }

    func testMapFramesAndOtherUnitsKeepTheirOriginalBoundaries() throws {
        for faction in ["blue", "red", "green", "black"] {
            for vertical in [0, 24] {
                let frame = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: 0, y: vertical, width: 24, height: 24))
                XCTAssertEqual(frame.size, CGSize(width: 24, height: 24))
                XCTAssertEqual(frame.cgImage?.width, 96)
                XCTAssertEqual(frame.cgImage?.height, 96)
                let original = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: 0, y: vertical, width: 24, height: 24, useEnhanced: false))
                XCTAssertEqual(original.cgImage?.width, 24)
                let archer = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: 24, y: vertical, width: 24, height: 24))
                XCTAssertEqual(archer.cgImage?.width, 96)
            }
        }
        XCTAssertNil(OriginalArt.image("missing-art"))
        XCTAssertNil(OriginalArt.frame("blue/unit_icons", x: 0, y: 48, width: 24, height: 24))
        XCTAssertEqual(OriginalArt.image("blue/archer")?.scale, 4)
    }

    func testCombatUpscalePreservesOriginalPaletteAndLocalFeaturesForEveryTeam() throws {
        var alphaByFrame: [String: [UInt8]] = [:]
        var variants: [[UInt8]] = []
        for (index, faction) in ["blue", "red", "green", "black"].enumerated() {
            for (name, width, height, count) in [("soldier", 34, 36, 3)] {
                for frame in 0..<count {
                    let original = try XCTUnwrap(OriginalArt.frame("\(faction)/\(name)", x: name == "soldier" ? frame * width : 0,
                                                                 y: name == "soldier" ? 0 : frame * height,
                                                                 width: width, height: height, useEnhanced: false)?.cgImage)
                    let enhanced = try XCTUnwrap(OriginalArt.frame("\(faction)/\(name)", x: name == "soldier" ? frame * width : 0,
                                                                 y: name == "soldier" ? 0 : frame * height,
                                                                 width: width, height: height)?.cgImage)
                    let source = try pixels(original)
                    let result = try pixels(enhanced)
                    let palette = Set(stride(from: 0, to: source.count, by: 4).map { Array(source[$0..<($0 + 4)]) })
                    let usedColors = Set(stride(from: 0, to: result.count, by: 4).map { Array(result[$0..<($0 + 4)]) })
                    XCTAssertTrue(usedColors.isSubset(of: palette), "No new colors in \(faction)/\(name)/\(frame)")
                    for row in 0..<height {
                        for column in 0..<width {
                            let offset = (row * width + column) * 4
                            let originalColor = Array(source[offset..<(offset + 4)])
                            let block = (0..<4).flatMap { subrow in
                                (0..<4).map { subcolumn in
                                    let location = ((row * 4 + subrow) * width * 4 + column * 4 + subcolumn) * 4
                                    return Array(result[location..<(location + 4)])
                                }
                            }
                            XCTAssertTrue(block.contains(originalColor), "Original detail retained at \(column),\(row)")
                        }
                    }
                    let alpha = stride(from: 3, to: result.count, by: 4).map { result[$0] }
                    let key = "\(name):\(frame)"
                    if index == 0 { alphaByFrame[key] = alpha }
                    else { XCTAssertEqual(alpha, alphaByFrame[key], "Changing faction must not change the silhouette") }
                    if name == "soldier", frame == 0 { variants.append(result) }
                }
            }
        }
        XCTAssertEqual(Set(variants).count, 4)
    }

    func testActedSoldierKeepsHighResolutionAndAlpha() throws {
        let original = try XCTUnwrap(OriginalArt.frame("blue/unit_icons", x: 0, width: 24, height: 24)?.cgImage)
        let acted = try XCTUnwrap(UnitSpriteImageProcessor.actedVariant(of: original))
        XCTAssertEqual(acted.width, 96)
        XCTAssertEqual(acted.height, 96)
        let source = try pixels(original)
        let gray = try pixels(acted)
        for offset in stride(from: 0, to: gray.count, by: 4) {
            XCTAssertEqual(gray[offset + 3], source[offset + 3])
            if Array(source[offset..<(offset + 4)]) == [244, 244, 230, 255] {
                XCTAssertEqual(Array(gray[offset..<(offset + 4)]), [244, 244, 230, 255])
                continue
            }
            XCTAssertEqual(gray[offset], gray[offset + 1])
            XCTAssertEqual(gray[offset + 1], gray[offset + 2])
        }
    }

    func testEveryUnitKeepsAnimationGeometryPaletteAndTeamSilhouette() throws {
        for definition in try GameResources.unitDefinitions() {
            var silhouettes: [[UInt8]] = []
            for (teamIndex, faction) in ["blue", "red", "green", "black"].enumerated() {
                let icons = try OriginalSprite("unit_icons", fraction: teamIndex + 1)
                for row in 0..<2 {
                    let index = definition.id + row * 12
                    let original = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: definition.id * 24, y: row * 24,
                                                                 width: 24, height: 24, useEnhanced: false)?.cgImage)
                    XCTAssertEqual(icons.frames[index].image.size, CGSize(width: original.width, height: original.height))
                    XCTAssertEqual(icons.frames[index].image.scale, 4)
                }
                guard definition.characterCount > 0 else { continue }
                let original = try OriginalSprite(definition.resourceName, fraction: teamIndex + 1, useEnhanced: false)
                let enhanced = try OriginalSprite(definition.resourceName, fraction: teamIndex + 1)
                XCTAssertEqual(enhanced.size, original.size)
                XCTAssertEqual(enhanced.sequences, original.sequences)
                XCTAssertEqual(enhanced.frameDelay, original.frameDelay)
                XCTAssertEqual(enhanced.frames.count, original.frames.count)
                for index in enhanced.frames.indices {
                    let frame = enhanced.frames[index]
                    XCTAssertEqual(frame.offset, original.frames[index].offset)
                    try assertPalette(frame.image, original: XCTUnwrap(original.frames[index].image.cgImage))
                    let colors = try pixels(XCTUnwrap(frame.image.cgImage))
                    let alpha = stride(from: 3, to: colors.count, by: 4).map { colors[$0] }
                    if teamIndex == 0 { silhouettes.append(alpha) }
                    else { XCTAssertEqual(alpha, silhouettes[index], "\(definition.resourceName):\(index) \(faction)") }
                }
            }
        }
    }

    func testAllBundledArtworkAndAtlasBoundaries() throws {
        let bundle = try XCTUnwrap(Bundle.main.url(forResource: "AncientEmpiresEngine_AEContent", withExtension: "bundle"))
        let root = bundle.appendingPathComponent("Original/res")
        let enumerator = try XCTUnwrap(FileManager.default.enumerator(at: root, includingPropertiesForKeys: nil))
        let images = enumerator.compactMap { $0 as? URL }.filter { $0.pathExtension == "png" }
        XCTAssertGreaterThan(images.count, 200)
        for url in images {
            let name = String(url.path.dropFirst(root.path.count + 1).dropLast(4))
            let original = try XCTUnwrap(OriginalArt.image(name, useEnhanced: false), name)
            let enhanced = try XCTUnwrap(OriginalArt.image(name), name)
            XCTAssertEqual(enhanced.size, original.size, name)
            XCTAssertEqual(enhanced.scale, 4, name)
            XCTAssertEqual(enhanced.cgImage?.width, original.cgImage!.width * 4, name)
            XCTAssertEqual(enhanced.cgImage?.height, original.cgImage!.height * 4, name)
        }
        for (name, width, height) in [("menu", 16, 16), ("portraits", 85, 85), ("king_head_icons", 13, 12),
                                     ("blue/unit_icons", 24, 24), ("spark", 24, 24)] {
            let atlas = try XCTUnwrap(OriginalArt.image(name)?.cgImage)
            let source = try XCTUnwrap(OriginalArt.image(name, useEnhanced: false)?.cgImage)
            for vertical in stride(from: 0, through: source.height - height, by: height) {
                for horizontal in stride(from: 0, through: source.width - width, by: width) {
                    let independent = try XCTUnwrap(OriginalArt.frame(name, x: horizontal, y: vertical, width: width, height: height)?.cgImage)
                    let crop = try XCTUnwrap(atlas.cropping(to: CGRect(x: horizontal * 4, y: vertical * 4, width: width * 4, height: height * 4)))
                    XCTAssertEqual(try pixels(independent), try pixels(crop), name)
                }
            }
        }
        let overview = try OriginalMapOverviewViewController(map: GameResources.missionZero(), units: [])
        overview.loadViewIfNeeded()
        let picture = try XCTUnwrap(overview.view.subviews.first { $0.accessibilityIdentifier == "overview.map" } as? UIImageView)
        XCTAssertEqual(picture.image?.scale, 4)
    }

    func testFullArtworkPreview() throws {
        let definitions = try GameResources.unitDefinitions()
        let format = UIGraphicsImageRendererFormat()
        format.scale = 1
        let image = UIGraphicsImageRenderer(size: CGSize(width: 1120, height: 1040), format: format).image { context in
            UIColor(red: 0.11, green: 0.14, blue: 0.19, alpha: 1).setFill()
            context.fill(CGRect(x: 0, y: 0, width: 1120, height: 1040))
            context.cgContext.interpolationQuality = .none
            let attributes: [NSAttributedString.Key: Any] = [.font: UIFont.monospacedSystemFont(ofSize: 20, weight: .medium), .foregroundColor: UIColor.white]
            ("ALL 12 UNITS / 4 ORIGINAL TEAM PALETTES / 4x" as NSString).draw(at: CGPoint(x: 20, y: 16), withAttributes: attributes)
            for (teamIndex, faction) in ["blue", "red", "green", "black"].enumerated() {
                for definition in definitions {
                    OriginalArt.frame("\(faction)/unit_icons", x: definition.id * 24, width: 24, height: 24)?
                        .draw(in: CGRect(x: 20 + definition.id * 90, y: 58 + teamIndex * 85, width: 80, height: 80))
                }
            }
            ("COMBAT / ORIGINAL POSES AND SILHOUETTES" as NSString).draw(at: CGPoint(x: 20, y: 408), withAttributes: attributes)
            for definition in definitions where definition.characterCount > 0 {
                if let sprite = try? OriginalSprite(definition.resourceName, fraction: 1) {
                    let column = definition.id % 6
                    let row = definition.id / 6
                    sprite.draw(frame: 0, at: CGPoint(x: 20 + column * 180, y: 445 + row * 185), scale: 2)
                }
            }
            ("TERRAIN / PORTRAITS / EFFECTS" as NSString).draw(at: CGPoint(x: 20, y: 828), withAttributes: attributes)
            for index in 0..<8 {
                OriginalArt.image(String(format: "tiles0_%02d", index))?.draw(in: CGRect(x: 20 + index * 80, y: 870, width: 72, height: 72))
            }
            OriginalArt.frame("portraits", x: 0, width: 85, height: 85)?.draw(in: CGRect(x: 680, y: 865, width: 160, height: 160))
            if let spark = try? OriginalSprite("spark") { spark.draw(frame: 1, at: CGPoint(x: 870, y: 890), scale: 4) }
            if let spell = try? OriginalSprite("spell") { spell.draw(frame: 0, at: CGPoint(x: 990, y: 890), scale: 4) }
        }
        let attachment = XCTAttachment(image: image)
        attachment.name = "Full original artwork faithful 4x preview"
        attachment.lifetime = .keepAlways
        add(attachment)
    }

    func testAllUnitMapCompositesRetainFourTimesPixels() throws {
        let map = try GameResources.missionZero()
        let definitions = try GameResources.unitDefinitions()
        let units = (1...4).flatMap { fraction in
            definitions.map { CampaignUnitState(id: (fraction - 1) * 12 + $0.id, type: $0.id, fraction: fraction, x: $0.id, y: fraction + 3) }
        }
        let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot:
            CampaignSnapshot(mapID: "m0", units: units, randomState: JavaRandom(seed: 42).state, m0ScriptState: 22))
        let controller = try CampaignMapViewController(session: session)
        controller.loadViewIfNeeded()
        controller.view.frame = CGRect(x: 0, y: 0, width: 768, height: 1024)
        controller.view.layoutIfNeeded()
        func descendants(_ view: UIView) -> [UIView] { [view] + view.subviews.flatMap(descendants) }
        let canvas = try XCTUnwrap(descendants(controller.view).first { view in
            view.layer.sublayers?.contains { $0.name == "campaign.unit.0" } == true
        })
        for unit in units {
            let layer = try XCTUnwrap(canvas.layer.sublayers?.first { $0.name == "campaign.unit.\(unit.id)" })
            let image = try XCTUnwrap(layer.sublayers?.first { $0.name == "campaign.unit.\(unit.id).sprite" }?.contents) as! CGImage
            XCTAssertEqual(image.width, 96)
            XCTAssertEqual(image.height, 96)
            if unit.type == 9 {
                let faction = ["blue", "red", "green", "black"][unit.fraction - 1]
                let body = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: 9 * 24, width: 24, height: 24))
                let head = try XCTUnwrap(OriginalArt.frame("king_head_icons", x: unit.resolvedCommanderVariant * 13, width: 13, height: 12))
                let format = UIGraphicsImageRendererFormat()
                format.scale = 4
                let expected = UIGraphicsImageRenderer(size: CGSize(width: 24, height: 24), format: format).image { context in
                    context.cgContext.interpolationQuality = .none
                    body.draw(at: .zero)
                    head.draw(at: CGPoint(x: 7, y: 0))
                }
                XCTAssertEqual(try pixels(image), try pixels(XCTUnwrap(expected.cgImage)))
            }
        }
        attach(canvas, name: "All twelve units and four teams on enhanced terrain")
    }

    func testGeneratedArcherUsesSharedMaskAndKeepsNonTeamPixels() throws {
        let mask = try pixels(XCTUnwrap(UIImage(data: GameResources.archerTrialData(named: "team-mask"))?.cgImage))
        let blue = try pixels(XCTUnwrap(OriginalArt.frame("blue/unit_icons", x: 24, width: 24, height: 24)?.cgImage))
        let original = try XCTUnwrap(OriginalArt.frame("blue/unit_icons", x: 24, width: 24, height: 24, useEnhanced: false))
        XCTAssertEqual(original.cgImage?.width, 24)
        let packaged = try XCTUnwrap(UIImage(data: GameResources.archerTrialData(named: "blue"))?.cgImage?.cropping(to: CGRect(x: 0, y: 0, width: 96, height: 96)))
        XCTAssertEqual(blue, try pixels(packaged))
        var variants: [[UInt8]] = []
        for faction in ["blue", "red", "green", "black"] {
            let image = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: 24, width: 24, height: 24))
            XCTAssertEqual(image.size, CGSize(width: 24, height: 24))
            let colors = try pixels(XCTUnwrap(image.cgImage))
            variants.append(colors)
            var fixedColors: [UInt8] = [], expectedColors: [UInt8] = []
            for offset in stride(from: 0, to: colors.count, by: 4) {
                XCTAssertEqual(colors[offset + 3], blue[offset + 3])
                if mask[offset] == 0 {
                    fixedColors.append(contentsOf: colors[offset..<(offset + 4)])
                    expectedColors.append(contentsOf: blue[offset..<(offset + 4)])
                }
            }
            XCTAssertEqual(fixedColors, expectedColors, "Skin, bow, leather and metal must not change with team")
            let second = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: 24, y: 24, width: 24, height: 24)?.cgImage)
            let shifted = try pixels(second)
            XCTAssertNotEqual(colors, shifted)
            for row in 0..<96 {
                XCTAssertEqual(Array(colors[(row * 96 * 4)..<((row * 96 + 92) * 4)]),
                               Array(shifted[((row * 96 + 4) * 4)..<((row + 1) * 96 * 4)]))
            }
            let acted = try XCTUnwrap(UnitSpriteImageProcessor.actedVariant(of: XCTUnwrap(image.cgImage)))
            XCTAssertEqual(acted.width, 96)
            let gray = try pixels(acted)
            XCTAssertEqual(stride(from: 3, to: gray.count, by: 4).map { gray[$0] },
                           stride(from: 3, to: colors.count, by: 4).map { colors[$0] })
        }
        XCTAssertEqual(Set(variants).count, 4)
    }

    func testAllGeneratedMapUnitsLoadBothFramesAndPreserveFixedColors() throws {
        for definition in try GameResources.unitDefinitions() where definition.id != 1 {
            let blueAtlas = try XCTUnwrap(UIImage(data: GameResources.generatedMapUnitData(unitID: definition.id, named: "blue"))?.cgImage)
            let maskAtlas = try XCTUnwrap(UIImage(data: GameResources.generatedMapUnitData(unitID: definition.id, named: "team-mask"))?.cgImage)
            XCTAssertEqual(blueAtlas.width, 96)
            XCTAssertEqual(blueAtlas.height, 192)
            var blueFrames: [[UInt8]] = []
            for frame in 0..<2 {
                let rect = CGRect(x: 0, y: frame * 96, width: 96, height: 96)
                let blue = try pixels(XCTUnwrap(blueAtlas.cropping(to: rect)))
                let mask = try pixels(XCTUnwrap(maskAtlas.cropping(to: rect)))
                blueFrames.append(blue)
                let selectedCount = stride(from: 0, to: mask.count, by: 4).filter { mask[$0] > 0 }.count
                if definition.id == 11 { XCTAssertEqual(selectedCount, 0) }
                else { XCTAssertGreaterThan(selectedCount, 20, definition.resourceName) }
                var variants: [[UInt8]] = []
                for faction in ["blue", "red", "green", "black"] {
                    let image = try XCTUnwrap(OriginalArt.frame("\(faction)/unit_icons", x: definition.id * 24, y: frame * 24, width: 24, height: 24))
                    let colors = try pixels(XCTUnwrap(image.cgImage))
                    XCTAssertEqual(image.size, CGSize(width: 24, height: 24))
                    XCTAssertEqual(image.scale, 4)
                    if faction == "blue" { XCTAssertEqual(colors, blue, "Generated resource must be loaded: \(definition.resourceName)") }
                    variants.append(colors)
                    let alpha = stride(from: 3, to: colors.count, by: 4).map { colors[$0] }
                    XCTAssertEqual(alpha, stride(from: 3, to: blue.count, by: 4).map { blue[$0] })
                    XCTAssertTrue(alpha.contains { $0 > 200 })
                    var fixed: [UInt8] = [], expected: [UInt8] = []
                    for offset in stride(from: 0, to: colors.count, by: 4) where mask[offset] == 0 {
                        fixed.append(contentsOf: colors[offset..<(offset + 4)])
                        expected.append(contentsOf: blue[offset..<(offset + 4)])
                    }
                    XCTAssertEqual(fixed, expected, "Fixed materials changed: \(definition.resourceName)/\(faction)")
                    let gray = try pixels(XCTUnwrap(UnitSpriteImageProcessor.actedVariant(of: XCTUnwrap(image.cgImage))))
                    XCTAssertEqual(alpha, stride(from: 3, to: gray.count, by: 4).map { gray[$0] })
                    for offset in stride(from: 0, to: gray.count, by: 4) {
                        if Array(colors[offset..<(offset + 4)]) == [244, 244, 230, 255] { continue }
                        XCTAssertEqual(gray[offset], gray[offset + 1])
                        XCTAssertEqual(gray[offset + 1], gray[offset + 2])
                    }
                    XCTAssertEqual(OriginalArt.frame("\(faction)/unit_icons", x: definition.id * 24, y: frame * 24, width: 24, height: 24, useEnhanced: false)?.scale, 1)
                }
                XCTAssertEqual(Set(variants).count, definition.id == 11 ? 1 : 4, definition.resourceName)
            }
            XCTAssertTrue(blueFrames[0] != blueFrames[1], "Idle frames must differ: \(definition.resourceName)")
        }
    }

    func testGeneratedArcherInMapAndRecruitment() throws {
        var units = (1...4).map { faction in
            CampaignUnitState(id: faction, type: 1, fraction: faction, x: 3 + (faction - 1) % 2, y: 7 + (faction - 1) / 2)
        }
        units.append(.init(id: 5, type: 1, fraction: 1, x: 5, y: 8, hitPoints: 64, hasActed: true))
        units.append(.init(id: 6, type: 0, fraction: 1, x: 2, y: 7))
        var snapshot = CampaignSnapshot(mapID: "m0", units: units, randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        snapshot.tileOverrides = [112: 40]
        snapshot.moneyByFraction = [1: 500]
        snapshot.allowedUnitType = 1
        let session = try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(),
                                          rules: GameResources.campaignRuleset(), snapshot: snapshot)
        let controller = try CampaignMapViewController(session: session)
        controller.loadViewIfNeeded()
        controller.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        controller.view.layoutIfNeeded()
        attach(controller.view, name: "Imagegen archer four teams and acted variant in game")
        let recruit = OriginalRecruitmentViewController(options: session.recruitmentOptions(at: GridPoint(x: 4, y: 9)), money: 500)
        recruit.loadViewIfNeeded()
        recruit.view.frame = controller.view.frame
        recruit.view.layoutIfNeeded()
        func descendants(_ view: UIView) -> [UIView] { [view] + view.subviews.flatMap(descendants) }
        let next = try XCTUnwrap(descendants(recruit.view).first { $0.accessibilityIdentifier == "recruit.next" } as? UIButton)
        next.sendActions(for: .touchUpInside)
        recruit.view.layoutIfNeeded()
        let label = try XCTUnwrap(descendants(recruit.view).first { $0.accessibilityIdentifier == "recruit.name" } as? UILabel)
        XCTAssertEqual(label.text, GameLocalization.text("story.143"))
        attach(recruit.view, name: "Imagegen archer recruitment actual size")
    }

    private func assertPalette(_ enhanced: UIImage, original: CGImage) throws {
        XCTAssertEqual(enhanced.size, CGSize(width: original.width, height: original.height))
        XCTAssertEqual(enhanced.scale, 4)
        let colors = try pixels(original)
        let enlarged = try pixels(XCTUnwrap(enhanced.cgImage))
        let palette = colors.withUnsafeBytes { Set($0.bindMemory(to: UInt32.self)) }
        let result = enlarged.withUnsafeBytes { Set($0.bindMemory(to: UInt32.self)) }
        XCTAssertTrue(result.isSubset(of: palette))
    }

    func testOriginalAndFaithfulUpscaleComparison() throws {
        let original = try XCTUnwrap(OriginalArt.image("blue/soldier", useEnhanced: false))
        let enhanced = try XCTUnwrap(OriginalArt.image("blue/soldier"))
        let format = UIGraphicsImageRendererFormat()
        format.scale = 1
        let image = UIGraphicsImageRenderer(size: CGSize(width: 920, height: 460), format: format).image { context in
            UIColor(red: 0.11, green: 0.14, blue: 0.19, alpha: 1).setFill()
            context.fill(CGRect(x: 0, y: 0, width: 920, height: 460))
            context.cgContext.interpolationQuality = .none
            let attributes: [NSAttributedString.Key: Any] = [.font: UIFont.monospacedSystemFont(ofSize: 16, weight: .medium), .foregroundColor: UIColor.white]
            ("ORIGINAL / nearest 4x" as NSString).draw(at: CGPoint(x: 24, y: 20), withAttributes: attributes)
            ("ORIGINAL / edge-aware 4x" as NSString).draw(at: CGPoint(x: 484, y: 20), withAttributes: attributes)
            original.draw(in: CGRect(x: 24, y: 60, width: 408, height: 144))
            enhanced.draw(in: CGRect(x: 484, y: 60, width: 408, height: 144))
            for (index, faction) in ["blue", "red", "green", "black"].enumerated() {
                OriginalArt.frame("\(faction)/unit_icons", x: 0, width: 24, height: 24, useEnhanced: false)?.draw(in: CGRect(x: 24 + index * 102, y: 250, width: 96, height: 96))
                OriginalArt.frame("\(faction)/unit_icons", x: 0, width: 24, height: 24)?.draw(in: CGRect(x: 484 + index * 102, y: 250, width: 96, height: 96))
            }
            ("Same source sprites, original team palettes and animation poses" as NSString).draw(at: CGPoint(x: 24, y: 400), withAttributes: attributes)
        }
        let attachment = XCTAttachment(image: image)
        attachment.name = "Original soldier faithful 4x comparison"
        attachment.lifetime = .keepAlways
        add(attachment)
    }

    func testSoldierTrialInActualMapAndCombatCanvases() throws {
        let units = (1...4).map { fraction in
            CampaignUnitState(id: fraction - 1, type: 0, fraction: fraction, x: fraction, y: 9)
        }
        var session = try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot:
            CampaignSnapshot(mapID: "m0", units: units, randomState: JavaRandom(seed: 42).state, m0ScriptState: 22))
        let controller = try CampaignMapViewController(session: session)
        controller.loadViewIfNeeded()
        controller.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        controller.view.layoutIfNeeded()
        attach(controller.view, name: "HD soldier four factions on map")
        _ = try session.attack(unitID: 0, targetID: 1, recordPresentation: true)
        let combat = try XCTUnwrap(session.pendingPresentation?.combat)
        let canvas = try OriginalCombatCanvas(combat: combat)
        canvas.frame = controller.view.frame
        canvas.layoutIfNeeded()
        for tick in [23, 76, 109] {
            canvas.tick = tick
            attach(canvas, name: "HD soldier combat tick \(tick)")
        }
    }

    func testGeneratedEnvironmentLoadsEveryTileAndHeroAtlas() throws {
        for name in ["king_head_icons", "portraits"] + (0..<48).map({ String(format: "tiles0_%02d", $0) }) {
            let packaged = try XCTUnwrap(UIImage(data: GameResources.enhancedEnvironmentData(named: name))?.cgImage)
            let runtime = try XCTUnwrap(OriginalArt.image(name))
            let original = try XCTUnwrap(OriginalArt.image(name, useEnhanced: false))
            XCTAssertEqual(runtime.size, original.size, name)
            XCTAssertEqual(runtime.scale, 4, name)
            XCTAssertTrue(try pixels(XCTUnwrap(runtime.cgImage)) == pixels(packaged), "Generated artwork was not loaded: \(name)")
        }
        for (name, count, width, height) in [("king_head_icons", 4, 13, 12), ("portraits", 6, 85, 85)] {
            var frames = Set<[UInt8]>()
            for index in 0..<count {
                let image = try XCTUnwrap(OriginalArt.frame(name, x: index * width, width: width, height: height)?.cgImage)
                let colors = try pixels(image)
                let alpha = stride(from: 3, to: colors.count, by: 4).map { colors[$0] }
                XCTAssertTrue(alpha.contains(0))
                XCTAssertTrue(alpha.contains(255))
                frames.insert(colors)
            }
            XCTAssertEqual(frames.count, count, "Hero identities must remain distinct")
        }
        let format = UIGraphicsImageRendererFormat()
        format.scale = 1
        let preview = UIGraphicsImageRenderer(size: CGSize(width: 1152, height: 1080), format: format).image { context in
            UIColor(red: 244 / 255, green: 244 / 255, blue: 230 / 255, alpha: 1).setFill()
            context.fill(CGRect(x: 0, y: 0, width: 1152, height: 1080))
            for index in 0..<48 {
                OriginalArt.image(String(format: "tiles0_%02d", index))?.draw(in: CGRect(x: index % 8 * 144, y: index / 8 * 120, width: 120, height: 120))
            }
            for index in 0..<6 {
                OriginalArt.frame("portraits", x: index * 85, width: 85, height: 85)?.draw(in: CGRect(x: index * 192, y: 750, width: 192, height: 192))
            }
            for index in 0..<4 {
                OriginalArt.frame("king_head_icons", x: index * 13, width: 13, height: 12)?.draw(in: CGRect(x: 300 + index * 132, y: 960, width: 104, height: 96))
            }
        }
        let attachment = XCTAttachment(image: preview)
        attachment.name = "Generated terrain buildings and heroes"
        attachment.lifetime = .keepAlways
        add(attachment)
    }

    func testBuildingOwnershipChangesOnlyTeamRegions() throws {
        for (kind, firstTile) in [("village", 37), ("castle", 38)] {
            let mask = try pixels(XCTUnwrap(UIImage(data: GameResources.enhancedEnvironmentData(named: kind + "-team-mask"))?.cgImage))
            XCTAssertGreaterThan(stride(from: 0, to: mask.count, by: 4).filter { mask[$0] > 0 }.count, 100)
            for offset in stride(from: 0, to: mask.count, by: 4) {
                let horizontal = offset / 4 % 96, vertical = offset / 4 / 96
                if kind == "castle" && (horizontal < 35 || horizontal > 60 || vertical < 23 || vertical > 65)
                    || kind == "village" && vertical >= 72 {
                    XCTAssertEqual(mask[offset], 0, "Stone towers and ground shadows must not be selected")
                }
            }
            let neutral = try pixels(XCTUnwrap(OriginalArt.image(String(format: "tiles0_%02d", firstTile))?.cgImage))
            var variants = Set<[UInt8]>()
            for owner in 0..<5 {
                let colors = try pixels(XCTUnwrap(OriginalArt.image(String(format: "tiles0_%02d", firstTile + owner * 2))?.cgImage))
                variants.insert(colors)
                var fixed = [UInt8](), expected = [UInt8]()
                for offset in stride(from: 0, to: colors.count, by: 4) where mask[offset] == 0 {
                    fixed.append(contentsOf: colors[offset..<(offset + 4)])
                    expected.append(contentsOf: neutral[offset..<(offset + 4)])
                }
                XCTAssertTrue(fixed == expected, "Stone and gold must stay unchanged when ownership changes")
            }
            XCTAssertEqual(variants.count, 5)
        }
    }

    func testTerrainPreservesOriginalConnectionBoundaries() throws {
        for index in Array(0...14) + [18] + Array(20...26) {
            let name = String(format: "tiles0_%02d", index)
            let original = try pixels(XCTUnwrap(OriginalArt.image(name, useEnhanced: false)?.cgImage))
            let enhanced = try pixels(XCTUnwrap(OriginalArt.image(name)?.cgImage))
            for position in 0..<96 {
                for (horizontal, vertical) in [(position, 0), (position, 95), (0, position), (95, position)] {
                    let expected = ((vertical / 4) * 24 + horizontal / 4) * 4
                    let actual = (vertical * 96 + horizontal) * 4
                    XCTAssertTrue(Array(original[expected..<(expected + 4)]) == Array(enhanced[actual..<(actual + 4)]), name)
                }
            }
        }
    }

    private func attach(_ view: UIView, name: String) {
        view.setNeedsDisplay()
        let image = UIGraphicsImageRenderer(bounds: view.bounds).image { view.layer.render(in: $0.cgContext) }
        let attachment = XCTAttachment(image: image)
        attachment.name = name
        attachment.lifetime = .keepAlways
        add(attachment)
    }

    private func pixels(_ image: CGImage) throws -> [UInt8] {
        let context = try XCTUnwrap(CGContext(data: nil, width: image.width, height: image.height,
                                             bitsPerComponent: 8, bytesPerRow: image.width * 4,
                                             space: CGColorSpace(name: CGColorSpace.sRGB)!,
                                             bitmapInfo: CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue))
        context.interpolationQuality = .none
        context.draw(image, in: CGRect(x: 0, y: 0, width: image.width, height: image.height))
        let pointer = try XCTUnwrap(context.data?.assumingMemoryBound(to: UInt8.self))
        return Array(UnsafeBufferPointer(start: pointer, count: image.width * image.height * 4))
    }
}
