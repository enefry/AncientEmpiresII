import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CombatRenderingTests: XCTestCase {
    func testOriginalResultArtworkAndTurnMessageLayouts() throws {
        let sizes = [CGSize(width: 402, height: 874), CGSize(width: 768, height: 1024)]
        XCTAssertNotNil(OriginalArt.image("gameover"))
        for size in sizes {
            for victory in [true, false] {
                let canvas = OriginalResultCanvas(victory: victory)
                canvas.frame = CGRect(origin: .zero, size: size)
                canvas.tick = victory ? 1 : 53
                canvas.layoutIfNeeded()
                let message = try XCTUnwrap(canvas.subviews.compactMap { $0 as? UILabel }.first)
                XCTAssertEqual(message.text, victory ? GameLocalization.text("story.72") : GameLocalization.text("story.57"))
                XCTAssertTrue(canvas.bounds.contains(message.frame))
                let image = UIGraphicsImageRenderer(bounds: canvas.bounds).image { canvas.layer.render(in: $0.cgContext) }
                let attachment = XCTAttachment(image: image)
                attachment.name = "Original \(victory ? "victory" : "defeat") at \(Int(size.width))pt"
                attachment.lifetime = .keepAlways
                add(attachment)
            }
        }
    }

    func testOriginalCombatSpriteResourcesAndSplitImageAlignment() throws {
        for definition in try GameResources.unitDefinitions() where definition.characterCount > 0 {
            for fraction in 1...4 {
                let sprite = try OriginalSprite(definition.resourceName, fraction: fraction)
                XCTAssertGreaterThan(sprite.frames.count, 0)
                for sequence in sprite.sequences {
                    XCTAssertFalse(sequence.isEmpty)
                    XCTAssertTrue(sequence.allSatisfy { sprite.frames.indices.contains($0) })
                }
            }
        }
        let king = try OriginalSprite("king", fraction: 1)
        XCTAssertEqual(king.size, CGSize(width: 61, height: 75))
        XCTAssertEqual(king.frames[0].offset.y + king.frames[0].image.size.height, 75)
        XCTAssertEqual(king.frames[3].offset.x + king.frames[3].image.size.width, 61)
    }

    func testCombatFramesAtPhoneSize() throws {
        let combat = try makeCombat()
        let canvas = try OriginalCombatCanvas(combat: combat)
        canvas.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        canvas.layoutIfNeeded()
        for tick in [23, 76, 109] {
            canvas.tick = tick
            canvas.setNeedsDisplay()
            let image = UIGraphicsImageRenderer(bounds: canvas.bounds).image { context in canvas.layer.render(in: context.cgContext) }
            let attachment = XCTAttachment(image: image)
            attachment.name = "Original combat tick \(tick) at 402pt"
            attachment.lifetime = .keepAlways
            add(attachment)
        }
    }

    func testCombatHealthForegroundCountsDownAndRedrawsEveryTick() throws {
        let combat = try makeCombat()
        XCTAssertGreaterThan(combat.damage, 6)
        let canvas = try OriginalCombatCanvas(combat: combat)
        canvas.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        canvas.layoutIfNeeded()
        XCTAssertEqual(canvas.subviews.filter { $0.accessibilityIdentifier == "combat.foreground" }.count, 1)

        let hit = try XCTUnwrap(canvas.timings.first?.hit)
        canvas.tick = hit
        let before = render(canvas)
        XCTAssertEqual(canvas.displayedHealth[1], combat.defender.hitPoints)

        canvas.tick = hit + 3
        let during = render(canvas)
        XCTAssertEqual(canvas.displayedHealth[1], combat.defender.hitPoints - 6)
        XCTAssertNotEqual(try healthCrop(before, side: 1), try healthCrop(during, side: 1))

        canvas.tick = hit + combat.damage
        XCTAssertEqual(canvas.displayedHealth[1], combat.defenderHealthAfter)
    }

    private func makeCombat() throws -> CampaignCombatPresentation {
        var session = try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot:
            CampaignSnapshot(mapID: "m0", units: [
                CampaignUnitState(id: 0, type: 9, fraction: 1, x: 4, y: 9),
                CampaignUnitState(id: 1, type: 10, fraction: 2, x: 5, y: 9),
            ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22))
        _ = try session.attack(unitID: 0, targetID: 1, recordPresentation: true)
        return try XCTUnwrap(session.pendingPresentation?.combat)
    }

    private func render(_ view: UIView) -> UIImage {
        view.layoutIfNeeded()
        return UIGraphicsImageRenderer(bounds: view.bounds).image { view.layer.render(in: $0.cgContext) }
    }

    private func healthCrop(_ image: UIImage, side: Int) throws -> Data {
        let image = try XCTUnwrap(image.cgImage)
        let width = CGFloat(image.width)
        let height = CGFloat(image.height)
        let rect = CGRect(x: width * (side == 0 ? 0.08 : 0.58), y: height * 0.94,
                          width: width * 0.34, height: height * 0.06).integral
        return try XCTUnwrap(image.cropping(to: rect)?.dataProvider?.data as Data?)
    }
}
