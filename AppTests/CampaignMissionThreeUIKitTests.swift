import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignMissionThreeUIKitTests: XCTestCase {
    func testReinforcementsUsesOriginalObjectiveBlackoutAndSixtyPointTouchCells() async throws {
        var snapshot = try activeSnapshot()
        snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        snapshot.pendingPresentations = [CampaignPresentation(
            unitsBefore: snapshot.units,
            scene: CampaignScenePresentation(.objective, textID: 132)
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
        XCTAssertTrue(labels.contains(GameLocalization.text("story.124")))
        XCTAssertTrue(labels.contains(GameLocalization.text("story.132")))
        attach(objective.view, name: "Reinforcements original objective")
        objective.dismiss(animated: false)

        var blackout = try activeSnapshot()
        blackout.phase = .story
        blackout.storyMode = .opening
        blackout.storyMessages = [249]
        blackout.storyIndex = 0
        blackout.missionRuntime?.state = 4
        blackout.missionRuntime?.portraitID = 2
        blackout.missionRuntime?.screenObscured = true
        let blackoutController = try CampaignMapViewController(session: restore(blackout))
        window.rootViewController = blackoutController
        window.layoutIfNeeded()
        let drape = try XCTUnwrap(findView(identifier: "campaign.story.drape", in: blackoutController.view))
        XCTAssertEqual(drape.alpha, 1, accuracy: 0.001)
        XCTAssertTrue(allLabels(in: blackoutController.view).contains {
            $0.text == GameLocalization.text("story.249")
        })
        attach(blackoutController.view, name: "Reinforcements restored blackout dialogue")
    }

    func testLevelMenuAndIllustratedChapterExposeMissionThree() async throws {
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let menu = MainMenuViewController(unlockedMissionIndices: [0, 1, 2, 3])
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
        XCTAssertNotNil(findView(identifier: "menu.level.3", in: panel.view))
        try button("menu.level.3", in: panel.view).sendActions(for: .touchUpInside)
        await settle { selectedMission == 3 }
        XCTAssertEqual(selectedMission, 3)

        let chapter = OriginalIntroductionViewController(illustrationID: 3, chapterTitle: GameLocalization.text("story.124"))
        chapter.loadViewIfNeeded()
        chapter.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        chapter.view.layoutIfNeeded()
        XCTAssertTrue(allLabels(in: chapter.view).contains {
            $0.accessibilityIdentifier == "intro.story" && $0.text == (try? GameResources.campaignStoryText(id: 218))
        })
        try button("intro.continue", in: chapter.view).sendActions(for: .touchUpInside)
        XCTAssertTrue(allLabels(in: chapter.view).contains {
            $0.accessibilityIdentifier == "intro.chapter" && $0.text == GameLocalization.text("story.124")
        })
        attach(chapter.view, name: "Mission three illustrated chapter")
    }

    private func activeSnapshot() throws -> CampaignSnapshot {
        var session = try CampaignSession(map: GameResources.campaignMap(named: "m3"),
                                          profiles: GameResources.campaignUnitProfiles(),
                                          rules: GameResources.campaignRuleset(), seed: 13)
        var snapshot = session.snapshot
        snapshot.phase = .playerTurn
        snapshot.missionRuntime?.state = 16
        snapshot.missionRuntime?.active = true
        snapshot.missionRuntime?.screenObscured = false
        snapshot.tileOverrides = [9 * 16 + 4: 27]
        return snapshot
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m3"),
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
