import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignMissionSixUIKitTests: XCTestCase {
    func testRendezvousUsesUpdatedObjectiveLargeCellsAndLevelEntry() async throws {
        let session = try CampaignSession(map: GameResources.campaignMap(named: "m6"),
                                          profiles: GameResources.campaignUnitProfiles(),
                                          rules: GameResources.campaignRuleset(), seed: 19)
        var snapshot = session.snapshot
        snapshot.phase = .playerTurn
        snapshot.missionRuntime?.state = 33
        snapshot.missionRuntime?.active = true
        snapshot.missionRuntime?.objectiveTextID = 138
        snapshot.pendingPresentations = [CampaignPresentation(
            unitsBefore: snapshot.units,
            scene: CampaignScenePresentation(.objective, textID: 138)
        )]
        snapshot.storyPresentationsEnabled = true

        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: restore(snapshot))
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()

        XCTAssertEqual(try button("campaign.cell.0.0", in: controller.view).bounds.width, 60, accuracy: 0.5)
        await settle { controller.presentedViewController != nil }
        let objective = try XCTUnwrap(controller.presentedViewController as? OriginalListViewController)
        let labels = allLabels(in: objective.view).compactMap(\.text)
        XCTAssertTrue(labels.contains(GameLocalization.text("story.127")))
        XCTAssertTrue(labels.contains(GameLocalization.text("story.138")))
        attach(objective.view, name: "Rendezvous updated original objective")
        objective.dismiss(animated: false)

        let menu = MainMenuViewController(unlockedMissionIndices: [0, 1, 2, 3, 4, 5, 6])
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
        XCTAssertNotNil(findView(identifier: "menu.level.6", in: panel.view))
        try button("menu.level.6", in: panel.view).sendActions(for: .touchUpInside)
        await settle { selectedMission == 6 }
        XCTAssertEqual(selectedMission, 6)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m6"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), snapshot: snapshot)
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

    private func allLabels(in root: UIView) -> [UILabel] {
        (root as? UILabel).map { [$0] } ?? root.subviews.flatMap(allLabels(in:))
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
