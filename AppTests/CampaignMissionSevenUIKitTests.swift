import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignMissionSevenUIKitTests: XCTestCase {
    func testHeavensFuryKeepsLargeCellsAndRendersOriginalWarning() async throws {
        let base = try CampaignSession(map: GameResources.campaignMap(named: "m7"),
                                       profiles: GameResources.campaignUnitProfiles(),
                                       rules: GameResources.campaignRuleset(), seed: 23)
        var snapshot = base.snapshot
        snapshot.phase = .enemyTurn
        snapshot.activeFraction = 2
        snapshot.missionRuntime?.state = 20
        snapshot.missionRuntime?.active = true
        snapshot.storyPresentationsEnabled = true
        let target = try XCTUnwrap(snapshot.units.first(where: { $0.position == GridPoint(x: 8, y: 15) }))
        snapshot.pendingPresentations = [CampaignPresentation(
            unitsBefore: snapshot.units,
            scene: CampaignScenePresentation(.heavenFury, focus: target.position, durationTicks: 20,
                                               unitID: target.id, textID: 280,
                                               affectedUnitIDs: [target.id], damage: 37)
        )]

        let (window, originalKey) = try makeWindow()
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: restore(snapshot))
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()

        XCTAssertEqual(try button("campaign.cell.0.0", in: controller.view).bounds.width, 60, accuracy: 0.5)
        await settle { self.findView(identifier: "campaign.heaven-fury.message", in: controller.view) != nil }
        let warning = try XCTUnwrap(findView(identifier: "campaign.heaven-fury.message", in: controller.view) as? UILabel)
        XCTAssertEqual(warning.text, GameLocalization.text("story.280"))
        XCTAssertTrue((findView(identifier: "campaign.map.canvas", in: controller.view)?.accessibilityValue as? String)?.contains("scene=heavenFury") == true)
        attach(controller.view, name: "Heaven's Fury on the touch-scale battlefield")
    }

    func testFinalProphecyCreditsAndMissionSevenMenuEntry() async throws {
        let (window, originalKey) = try makeWindow()
        defer { window.isHidden = true; originalKey?.makeKey() }
        let ending = OriginalCampaignEndingViewController()
        window.rootViewController = ending
        window.makeKeyAndVisible()
        window.layoutIfNeeded()

        let text = try XCTUnwrap(findView(identifier: "ending.text", in: ending.view) as? UILabel)
        XCTAssertEqual(text.text, try GameResources.campaignStoryText(id: 220))
        XCTAssertNotNil(findView(identifier: "ending.illustration", in: ending.view))
        attach(ending.view, name: "Final Crystal Prophecy with intro5")

        try button("ending.continue", in: ending.view).sendActions(for: .touchUpInside)
        window.layoutIfNeeded()
        XCTAssertEqual(text.text, try GameResources.campaignStoryText(id: 288))
        XCTAssertEqual(text.accessibilityLabel, GameLocalization.text("Ancient Empires II credits"))
        attach(ending.view, name: "Complete original credits")

        let menu = MainMenuViewController(unlockedMissionIndices: Array(0...7))
        var selectedMission: Int?
        menu.onStartCampaign = { selectedMission = $0 }
        window.rootViewController = menu
        window.layoutIfNeeded()
        try button("menu.play", in: menu.view).sendActions(for: .touchUpInside)
        let levels = try button("menu.levels", in: menu.view)
        levels.sendActions(for: .touchUpInside)
        levels.sendActions(for: .touchUpInside)
        await settle { menu.presentedViewController != nil }
        let panel = try XCTUnwrap(menu.presentedViewController as? OriginalListViewController)
        XCTAssertNotNil(findView(identifier: "menu.level.7", in: panel.view))
        try button("menu.level.7", in: panel.view).sendActions(for: .touchUpInside)
        await settle { selectedMission == 7 }
        XCTAssertEqual(selectedMission, 7)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m7"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }

    private func makeWindow() throws -> (UIWindow, UIWindow?) {
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        return (UIWindow(windowScene: scene), scene.keyWindow)
    }

    private func button(_ identifier: String, in root: UIView) throws -> UIButton {
        try XCTUnwrap(findView(identifier: identifier, in: root) as? UIButton)
    }

    private func findView(identifier: String, in root: UIView) -> UIView? {
        if root.accessibilityIdentifier == identifier { return root }
        for subview in root.subviews {
            if let found = findView(identifier: identifier, in: subview) { return found }
        }
        return nil
    }

    private func settle(_ condition: @escaping () -> Bool) async {
        for _ in 0..<60 where !condition() {
            await Task.yield()
            try? await Task.sleep(for: .milliseconds(10))
        }
    }

    private func attach(_ view: UIView, name: String) {
        view.layoutIfNeeded()
        let image = UIGraphicsImageRenderer(bounds: view.bounds).image { view.layer.render(in: $0.cgContext) }
        let attachment = XCTAttachment(image: image)
        attachment.name = name
        attachment.lifetime = .keepAlways
        add(attachment)
    }
}
