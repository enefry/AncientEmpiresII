import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignMissionOneUIKitTests: XCTestCase {
    func testMissionOneUsesTouchSizedCellsAndValadornIdentity() async throws {
        var snapshot = try activeSnapshot()
        snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: restore(snapshot))
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()

        let firstCell = try button("campaign.cell.0.0", in: controller.view)
        let nextCell = try button("campaign.cell.1.0", in: controller.view)
        XCTAssertEqual(firstCell.bounds.width, 60, accuracy: 0.5)
        XCTAssertEqual(nextCell.frame.minX - firstCell.frame.minX, 60, accuracy: 0.5)

        try button("campaign.unit-info", in: controller.view).sendActions(for: .touchUpInside)
        await settle { controller.presentedViewController != nil }
        let details = try XCTUnwrap(controller.presentedViewController as? OriginalListViewController)
        details.view.layoutIfNeeded()
        XCTAssertTrue(allLabels(in: details.view).contains { $0.text == GameLocalization.text("story.95") })
        attach(controller.view, name: "Mission one 60 point touch grid")
        attach(details.view, name: "Mission one Valadorn unit details")
    }

    func testMissionOneObjectivePanelUsesItsOwnTitleAndText() async throws {
        var snapshot = try activeSnapshot()
        snapshot.pendingPresentations = [CampaignPresentation(
            unitsBefore: snapshot.units,
            scene: CampaignScenePresentation(.objective, textID: 130)
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

        await settle { controller.presentedViewController != nil }
        let objective = try XCTUnwrap(controller.presentedViewController as? OriginalListViewController)
        objective.view.layoutIfNeeded()
        let labels = allLabels(in: objective.view).compactMap(\.text)
        XCTAssertTrue(labels.contains(GameLocalization.text("story.122")))
        XCTAssertTrue(labels.contains(GameLocalization.text("story.130")))
        attach(objective.view, name: "Mission one original objective panel")
    }

    func testUnlockedLevelMenuAndChapterScreenExposeMissionOne() async throws {
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let menu = MainMenuViewController(unlockedMissionIndices: [0, 1])
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
        XCTAssertNotNil(findView(identifier: "menu.level.0", in: panel.view))
        XCTAssertNotNil(findView(identifier: "menu.level.1", in: panel.view))
        XCTAssertNil(findView(identifier: "menu.level.2", in: panel.view))
        try button("menu.level.1", in: panel.view).sendActions(for: .touchUpInside)
        await settle { selectedMission == 1 }
        XCTAssertEqual(selectedMission, 1)

        let chapter = OriginalIntroductionViewController(illustrationID: nil, chapterTitle: GameLocalization.text("story.114"))
        chapter.loadViewIfNeeded()
        chapter.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        chapter.view.layoutIfNeeded()
        XCTAssertTrue(allLabels(in: chapter.view).contains { $0.accessibilityIdentifier == "intro.chapter" && $0.text == GameLocalization.text("story.114") })
        let story = try XCTUnwrap(findView(identifier: "intro.story", in: chapter.view))
        XCTAssertTrue(story.isHidden || story.superview?.isHidden == true)
        attach(chapter.view, name: "Mission one original map-name interstitial")
    }

    private func activeSnapshot() throws -> CampaignSnapshot {
        let map = try GameResources.campaignMap(named: "m1")
        var snapshot = CampaignSnapshot(
            mapID: map.id,
            units: map.units.map {
                CampaignUnitState(id: $0.index, type: $0.type, fraction: $0.fraction,
                                  x: $0.x, y: $0.index == 0 ? 9 : min($0.y, 11))
            },
            phase: .playerTurn,
            randomState: JavaRandom(seed: 7).state
        )
        snapshot.units[0].commanderVariant = 2
        snapshot.moneyByFraction = [1: 300, 2: 50]
        snapshot.allowedUnitType = 1
        snapshot.missionRuntime = CampaignMissionRuntime(
            state: 20,
            active: true,
            references: ["king1": 0, "king2": 3],
            objectiveTextID: 130
        )
        return snapshot
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m1"),
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
