import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignMissionTwoUIKitTests: XCTestCase {
    func testPathOfShadowsUsesOriginalObjectiveGalamarAndSixtyPointTouchCells() async throws {
        var snapshot = try activeSnapshot()
        snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        snapshot.pendingPresentations = [CampaignPresentation(
            unitsBefore: snapshot.units,
            scene: CampaignScenePresentation(.objective, textID: 131)
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
        XCTAssertTrue(labels.contains(GameLocalization.text("story.123")))
        XCTAssertTrue(labels.contains(GameLocalization.text("story.131")))
        attach(objective.view, name: "Path of Shadows original objective")
        objective.dismiss(animated: false)

        snapshot.pendingPresentations = nil
        let detailsController = try CampaignMapViewController(session: restore(snapshot))
        window.rootViewController = detailsController
        window.layoutIfNeeded()
        try button("campaign.unit-info", in: detailsController.view).sendActions(for: .touchUpInside)
        await settle { detailsController.presentedViewController != nil }
        let details = try XCTUnwrap(detailsController.presentedViewController as? OriginalListViewController)
        XCTAssertTrue(allLabels(in: details.view).contains { $0.text == GameLocalization.text("story.93") })
        attach(detailsController.view, name: "Path of Shadows 60 point touch grid")
        attach(details.view, name: "Path of Shadows Galamar unit details")
    }

    func testLevelMenuAndMapNameExposeMissionTwo() async throws {
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let menu = MainMenuViewController(unlockedMissionIndices: [0, 1, 2])
        var selectedMission: Int?
        menu.onStartCampaign = { selectedMission = $0 }
        window.rootViewController = menu
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        try button("menu.play", in: menu.view).sendActions(for: .touchUpInside)
        let levels = try button("menu.levels", in: menu.view)
        levels.sendActions(for: .touchUpInside)
        levels.sendActions(for: .touchUpInside)
        await settle { menu.presentedViewController != nil }
        let panel = try XCTUnwrap(menu.presentedViewController as? OriginalListViewController)
        XCTAssertNotNil(findView(identifier: "menu.level.2", in: panel.view))
        try button("menu.level.2", in: panel.view).sendActions(for: .touchUpInside)
        await settle { selectedMission == 2 }
        XCTAssertEqual(selectedMission, 2)

        let chapter = OriginalIntroductionViewController(illustrationID: nil, chapterTitle: GameLocalization.text("story.115"))
        chapter.loadViewIfNeeded()
        chapter.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        chapter.view.layoutIfNeeded()
        XCTAssertTrue(allLabels(in: chapter.view).contains {
            $0.accessibilityIdentifier == "intro.chapter" && $0.text == GameLocalization.text("story.115")
        })
        attach(chapter.view, name: "Mission two original map-name interstitial")
    }

    private func activeSnapshot() throws -> CampaignSnapshot {
        let map = try GameResources.campaignMap(named: "m2")
        var snapshot = CampaignSnapshot(
            mapID: map.id,
            units: map.units.map {
                CampaignUnitState(id: $0.index, type: $0.type, fraction: $0.fraction,
                                  x: $0.index == 0 ? 8 : min($0.x, 8),
                                  y: $0.index == 0 ? 14 : min($0.y, 15))
            },
            phase: .playerTurn,
            randomState: JavaRandom(seed: 11).state
        )
        snapshot.moneyByFraction = [1: 0, 2: 0]
        snapshot.allowedUnitType = 0
        snapshot.missionRuntime = CampaignMissionRuntime(
            state: 21,
            active: true,
            references: ["king1": 0, "escortLeader": 1, "escortArcher": 2, "escortFollower": 3],
            objectiveTextID: 131
        )
        return snapshot
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m2"),
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
