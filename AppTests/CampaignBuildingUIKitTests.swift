import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignBuildingUIKitTests: XCTestCase {
    func testRecruitmentControlsAndInterruptedDeploymentUseRealUIKitFlow() async throws {
        var snapshot = fixture()
        snapshot.tileOverrides = [100: 40]
        snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        snapshot.allowedUnitType = 1
        var committed: CampaignSnapshot?
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: session(snapshot))
        controller.onSnapshotChange = { committed = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        try button("campaign.buy", in: controller.view).sendActions(for: .touchUpInside)
        let recruit = try XCTUnwrap(controller.presentedViewController as? OriginalRecruitmentViewController)
        recruit.view.layoutIfNeeded()
        XCTAssertEqual(try label("recruit.name", in: recruit.view).text, GameLocalization.text("story.139"))
        XCTAssertTrue(try button("recruit.buy", in: recruit.view).isEnabled)
        try button("recruit.next", in: recruit.view).sendActions(for: .touchUpInside)
        XCTAssertEqual(try label("recruit.name", in: recruit.view).text, GameLocalization.text("story.143"))
        try button("recruit.previous", in: recruit.view).sendActions(for: .touchUpInside)
        attach(recruit.view, name: "Recruitment soldier with original stats and selector")
        try button("recruit.cancel", in: recruit.view).sendActions(for: .touchUpInside)
        await settle { controller.presentedViewController == nil }
        XCTAssertEqual(committed?.moneyByFraction?[1], 500)
        try button("campaign.buy", in: controller.view).sendActions(for: .touchUpInside)
        let reopened = try XCTUnwrap(controller.presentedViewController as? OriginalRecruitmentViewController)
        try button("recruit.buy", in: reopened.view).sendActions(for: .touchUpInside)
        await settle { controller.presentedViewController == nil }
        let bought = try XCTUnwrap(committed)
        let id = try XCTUnwrap(bought.recruitingUnitID)
        XCTAssertEqual(bought.moneyByFraction?[1], 350)
        XCTAssertFalse(try button("campaign.cancel", in: controller.view).isEnabled)
        XCTAssertFalse(try button("campaign.end-unit", in: controller.view).isEnabled)
        let encoded = try JSONEncoder().encode(bought)
        let restored = try CampaignMapViewController(session: session(JSONDecoder().decode(CampaignSnapshot.self, from: encoded)))
        restored.onSnapshotChange = { committed = $0 }
        window.rootViewController = restored
        window.layoutIfNeeded()
        XCTAssertFalse(try button("campaign.cancel", in: restored.view).isEnabled)
        try button("campaign.cell.4.7", in: restored.view).sendActions(for: .touchUpInside)
        try button("campaign.confirm", in: restored.view).sendActions(for: .touchUpInside)
        await settle { committed?.units.first(where: { $0.id == id })?.hasMoved == true }
        XCTAssertEqual(committed?.units.first(where: { $0.id == id })?.position, GridPoint(x: 4, y: 7))
        attach(restored.view, name: "Recruitment restored deployment with large cells")
        try button("campaign.end-unit", in: restored.view).sendActions(for: .touchUpInside)
        XCTAssertNil(committed?.recruitingUnitID)
        XCTAssertEqual(committed?.units.first(where: { $0.id == id })?.hasActed, true)
        XCTAssertEqual(committed?.moneyByFraction?[1], 350)
        XCTAssertEqual(try button("campaign.cell.4.7", in: restored.view).bounds.width, 60, accuracy: 0.5)
    }

    func testOccupiedMessageAndMinimapUsePersistedOwnership() async throws {
        var snapshot = fixture()
        snapshot.tileOverrides = [100: 38]
        snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        var committed: CampaignSnapshot?
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: session(snapshot))
        controller.onSnapshotChange = { committed = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        let occupy = try button("campaign.building-action", in: controller.view)
        XCTAssertEqual(occupy.accessibilityLabel, GameLocalization.text("story.67"))
        occupy.sendActions(for: .touchUpInside)
        XCTAssertEqual(committed?.tileOverrides?[100], 40)
        XCTAssertEqual(try label("campaign.building-message", in: controller.view).text, GameLocalization.text("story.73"))
        controller.view.layoutIfNeeded()
        let message = try label("campaign.building-message", in: controller.view)
        XCTAssertTrue(try XCTUnwrap(message.superview).bounds.contains(message.frame))
        XCTAssertEqual(message.center.x, try XCTUnwrap(message.superview).bounds.midX, accuracy: 1)
        attach(controller.view, name: "Occupied castle original message")
        await settle { committed?.pendingPresentations == nil }
        XCTAssertNil(committed?.pendingPresentations)
        let saved = try session(XCTUnwrap(committed))
        XCTAssertEqual(saved.map.buildings.first(where: { $0.x == 4 && $0.y == 8 })?.fraction, 1)
        try button("campaign.cell.4.8", in: controller.view).sendActions(for: .touchUpInside)
        XCTAssertFalse(try button("campaign.buy", in: controller.view).isHidden)
        let overview = try OriginalMapOverviewViewController(map: saved.map, units: saved.snapshot.units)
        overview.loadViewIfNeeded()
        overview.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        overview.view.layoutIfNeeded()
        attach(overview.view, name: "Minimap after castle ownership change")
    }

    func testRecruitmentLayoutAtPhoneAndTabletWidthsAndUnavailableUnitSelection() throws {
        var snapshot = fixture()
        snapshot.tileOverrides = [100: 40]
        snapshot.allowedUnitType = 8
        snapshot.moneyByFraction = [1: 150]
        let options = try session(snapshot).recruitmentOptions(at: GridPoint(x: 4, y: 8))
        for size in [CGSize(width: 402, height: 874), CGSize(width: 768, height: 1024)] {
            let controller = OriginalRecruitmentViewController(options: options, money: 150)
            controller.loadViewIfNeeded()
            controller.view.frame = CGRect(origin: .zero, size: size)
            controller.view.layoutIfNeeded()
            for index in 0..<options.count {
                let buy = try button("recruit.buy", in: controller.view)
                XCTAssertEqual(buy.isEnabled, options[index].isAvailable)
                XCTAssertNotNil(try button("recruit.option.\(index)", in: controller.view).backgroundImage(for: .normal))
                XCTAssertTrue(controller.view.bounds.contains(buy.convert(buy.bounds, to: controller.view)))
                XCTAssertGreaterThanOrEqual(buy.bounds.height, 44)
                let description = try label("recruit.description", in: controller.view)
                XCTAssertEqual(description.numberOfLines, 0)
                if index == 8 { attach(controller.view, name: "Recruitment dragon unaffordable at \(Int(size.width))pt") }
                try button("recruit.next", in: controller.view).sendActions(for: .touchUpInside)
            }
            XCTAssertEqual(try label("recruit.name", in: controller.view).text, GameLocalization.text("story.139"))
        }
    }

    func testEndMoveAndOccupyMenusKeepTheSameTouchPosition() throws {
        var occupySnapshot = fixture()
        occupySnapshot.tileOverrides = [100: 38]
        occupySnapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        let occupyController = try CampaignMapViewController(session: session(occupySnapshot))
        occupyController.loadViewIfNeeded()
        occupyController.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        occupyController.view.layoutIfNeeded()
        let occupyMenu = try XCTUnwrap(find("campaign.action-menu", in: occupyController.view))
        let occupyFrame = occupyMenu.convert(occupyMenu.bounds, to: occupyController.view)
        let occupyButton = try button("campaign.building-action", in: occupyController.view)
        XCTAssertEqual(occupyButton.accessibilityLabel, GameLocalization.text("story.67"))
        XCTAssertTrue(occupyFrame.contains(occupyButton.convert(occupyButton.bounds, to: occupyController.view)))

        var waitSnapshot = fixture()
        waitSnapshot.units[0].x = 4
        waitSnapshot.units[0].y = 1
        waitSnapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        let waitController = try CampaignMapViewController(session: session(waitSnapshot))
        waitController.loadViewIfNeeded()
        waitController.view.frame = occupyController.view.frame
        waitController.view.layoutIfNeeded()
        let waitMenu = try XCTUnwrap(find("campaign.action-menu", in: waitController.view))
        let waitFrame = waitMenu.convert(waitMenu.bounds, to: waitController.view)
        let waitButton = try button("campaign.end-unit", in: waitController.view)

        XCTAssertEqual(waitFrame, occupyFrame)
        XCTAssertTrue(waitFrame.contains(waitButton.convert(waitButton.bounds, to: waitController.view)))
        XCTAssertGreaterThanOrEqual(waitButton.bounds.height, 44)
    }

    private func fixture() -> CampaignSnapshot {
        var snapshot = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 11, fraction: 2, x: 10, y: 0)
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        snapshot.moneyByFraction = [1: 500]
        return snapshot
    }
    private func session(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }
    private func find(_ identifier: String, in view: UIView) -> UIView? {
        if view.accessibilityIdentifier == identifier { return view }
        return view.subviews.lazy.compactMap { self.find(identifier, in: $0) }.first
    }
    private func button(_ identifier: String, in view: UIView) throws -> UIButton {
        try XCTUnwrap(find(identifier, in: view) as? UIButton, identifier)
    }
    private func label(_ identifier: String, in view: UIView) throws -> UILabel {
        try XCTUnwrap(find(identifier, in: view) as? UILabel, identifier)
    }
    private func settle(_ predicate: () -> Bool) async {
        let deadline = Date().addingTimeInterval(5)
        while !predicate(), Date() < deadline { try? await Task.sleep(for: .milliseconds(50)) }
    }
    private func attach(_ view: UIView, name: String) {
        view.layoutIfNeeded()
        let image = UIGraphicsImageRenderer(bounds: view.bounds).image { _ in view.drawHierarchy(in: view.bounds, afterScreenUpdates: true) }
        let attachment = XCTAttachment(image: image)
        attachment.name = name
        attachment.lifetime = .keepAlways
        add(attachment)
    }
}
