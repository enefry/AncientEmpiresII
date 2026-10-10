import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignAIUIKitTests: XCTestCase {
    func testRecruitPlanningShowsRangeThenRouteAndResumesWithoutBuyingAgain() async throws {
        var state = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 9, fraction: 1, x: 1, y: 10),
            .init(id: 1, type: 11, fraction: 2, x: 10, y: 0)
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        state.tileOverrides = [100: 42]
        state.moneyByFraction = [2: 100]
        var session = try make(state)
        let actions = try session.endPlayerTurn(recordPresentation: true)
        let recruited = try XCTUnwrap(actions.first(where: { $0.unitID == 2 }))
        XCTAssertEqual(recruited.origin, GridPoint(x: 4, y: 8))
        let queue = try XCTUnwrap(session.snapshot.pendingPresentations)
        let planningIndex = try XCTUnwrap(queue.firstIndex { $0.scene?.kind == .planning && $0.unitID == 2 })
        var resumed = session.snapshot
        resumed.pendingPresentations = Array(queue[planningIndex...])
        let committedMoney = resumed.moneyByFraction
        let committedUnits = resumed.units
        var latest: CampaignSnapshot?
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        var controller = try CampaignMapViewController(session: make(resumed))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        let cursor = try XCTUnwrap(layer("campaign.cursor", in: controller.view.layer))
        let route = try XCTUnwrap(layer("campaign.route", in: controller.view.layer) as? CAShapeLayer)
        await settle { cursor.bounds.width > 0 }
        XCTAssertTrue(route.path?.isEmpty ?? true)
        XCTAssertEqual(cursor.bounds.width, 65, accuracy: 0.5)
        XCTAssertEqual(try cell(in: controller.view).bounds.width, 60, accuracy: 0.5)
        attach(controller.view, name: "AI recruited soldier movement range")
        await settle { !(route.path?.isEmpty ?? true) }
        XCTAssertFalse(route.path?.isEmpty ?? true)
        let actor = try XCTUnwrap(layer("campaign.unit.2", in: controller.view.layer))
        let siblings = try XCTUnwrap(route.superlayer?.sublayers)
        XCTAssertGreaterThan(try XCTUnwrap(siblings.firstIndex(of: actor)), try XCTUnwrap(siblings.firstIndex(of: route)))
        attach(controller.view, name: "AI recruited soldier original route and cursor")
        let interrupted = try XCTUnwrap(latest)
        XCTAssertEqual(interrupted.pendingPresentations?.first?.scene?.kind, .planning)
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(interrupted))
        controller = try CampaignMapViewController(session: make(decoded))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.layoutIfNeeded()
        await settle(timeout: 15) { latest?.pendingPresentations == nil }
        XCTAssertNil(latest?.pendingPresentations)
        XCTAssertEqual(latest?.moneyByFraction, committedMoney)
        XCTAssertEqual(latest?.units, committedUnits)
        XCTAssertEqual(latest?.units.filter { $0.id == 2 }.count, 1)
        XCTAssertEqual(try cell(in: controller.view).bounds.width, 60, accuracy: 0.5)
    }

    func testAttackPreviewUsesOriginalThreeFrameCursorAndReturnsControl() async throws {
        var state = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 0, fraction: 2, x: 5, y: 8)
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        state.tileOverrides = [100: 18, 101: 18]
        var session = try make(state)
        _ = try session.endPlayerTurn(recordPresentation: true)
        let queue = try XCTUnwrap(session.snapshot.pendingPresentations)
        let targeting = try XCTUnwrap(queue.firstIndex { $0.scene?.kind == .targeting })
        state = session.snapshot
        state.pendingPresentations = Array(queue[targeting...])
        let enabled = GamePreferences.enabled(.fightAnimation)
        GamePreferences.set(.fightAnimation, enabled: false)
        defer { GamePreferences.set(.fightAnimation, enabled: enabled) }
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: make(state))
        var latest: CampaignSnapshot?
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        let cursor = try XCTUnwrap(layer("campaign.cursor", in: controller.view.layer))
        await settle { cursor.bounds.width > 0 }
        let animation = try XCTUnwrap(cursor.animation(forKey: "cursor") as? CAKeyframeAnimation)
        XCTAssertEqual(animation.values?.count, 3)
        XCTAssertEqual(animation.duration, 0.792, accuracy: 0.001)
        XCTAssertEqual(cursor.bounds.width, 100, accuracy: 0.5)
        XCTAssertEqual(cursor.bounds.height, 102.5, accuracy: 0.5)
        attach(controller.view, name: "AI attack preview with original attack cursor")
        await settle { latest?.pendingPresentations == nil }
        XCTAssertNil(latest?.pendingPresentations)
        XCTAssertEqual(latest?.units, state.units)
        XCTAssertEqual(latest?.randomState, state.randomState)
        XCTAssertEqual(latest?.phase, .playerTurn)
    }

    func testMapCombatShowsDamageForAttackAndCounterattackWhenFightAnimationIsOff() async throws {
        var state = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 10, fraction: 2, x: 5, y: 8),
            .init(id: 10, type: 9, fraction: 1, x: 0, y: 11),
            .init(id: 11, type: 11, fraction: 2, x: 11, y: 0),
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        state.tileOverrides = [100: 18, 101: 18]
        var game = try make(state)
        let result = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
        let combat = try XCTUnwrap(game.pendingPresentation?.combat)
        XCTAssertTrue(combat.didCounterattack)
        XCTAssertGreaterThan(result.damage, 0)
        XCTAssertGreaterThan(result.counterDamage, 0)

        let enabled = GamePreferences.enabled(.fightAnimation)
        GamePreferences.set(.fightAnimation, enabled: false)
        defer { GamePreferences.set(.fightAnimation, enabled: enabled) }
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        var latest: CampaignSnapshot?
        let controller = try CampaignMapViewController(session: make(game.snapshot))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()

        await settle { self.layer("campaign.combat.damage.1", in: controller.view.layer) != nil }
        XCTAssertEqual((try XCTUnwrap(layer("campaign.unit.1.hp", in: controller.view.layer)) as? CATextLayer)?.string as? String,
                       String(combat.defenderHealthAfter))
        XCTAssertNil(layer("campaign.unit.0.hp", in: controller.view.layer))
        XCTAssertFalse(try scrollView(in: controller.view).isUserInteractionEnabled)
        try await Task.sleep(for: .milliseconds(100))
        attach(controller.view, name: "Map combat initial damage")

        await settle { self.layer("campaign.combat.damage.0", in: controller.view.layer) != nil }
        XCTAssertEqual((try XCTUnwrap(layer("campaign.unit.0.hp", in: controller.view.layer)) as? CATextLayer)?.string as? String,
                       String(combat.attackerHealthAfter))
        XCTAssertNotNil(layer("campaign.effect.redspark", in: controller.view.layer))
        try await Task.sleep(for: .milliseconds(100))
        attach(controller.view, name: "Map combat counterattack damage")

        await settle { latest?.pendingPresentations == nil }
        XCTAssertNil(latest?.pendingPresentations)
        XCTAssertEqual(latest?.units, game.snapshot.units)
        XCTAssertEqual(latest?.randomState, game.snapshot.randomState)
    }

    private func make(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }
    private func layer(_ name: String, in root: CALayer) -> CALayer? {
        if root.name == name { return root }
        return root.sublayers?.lazy.compactMap { self.layer(name, in: $0) }.first
    }
    private func cell(in root: UIView) throws -> UIView {
        func find(_ root: UIView) -> UIView? {
            if root.accessibilityIdentifier == "campaign.cell.4.8" { return root }
            return root.subviews.lazy.compactMap(find).first
        }
        return try XCTUnwrap(find(root))
    }
    private func scrollView(in root: UIView) throws -> UIScrollView {
        func find(_ root: UIView) -> UIScrollView? {
            if let scroll = root as? UIScrollView { return scroll }
            return root.subviews.lazy.compactMap(find).first
        }
        return try XCTUnwrap(find(root))
    }
    private func settle(timeout: TimeInterval = 5, _ predicate: () -> Bool) async {
        let deadline = Date().addingTimeInterval(timeout)
        while !predicate(), Date() < deadline { try? await Task.sleep(for: .milliseconds(15)) }
    }
    private func attach(_ view: UIView, name: String) {
        view.layoutIfNeeded()
        let image = UIGraphicsImageRenderer(bounds: view.bounds).image { _ in view.drawHierarchy(in: view.bounds, afterScreenUpdates: false) }
        let attachment = XCTAttachment(image: image)
        attachment.name = name
        attachment.lifetime = .keepAlways
        add(attachment)
    }
}
