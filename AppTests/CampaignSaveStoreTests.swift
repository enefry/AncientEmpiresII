import AECore
import AEContent
import Foundation
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignSaveStoreTests: XCTestCase {
    func testApplicationUsesWindowSceneDelegateConfiguration() throws {
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let delegate = try XCTUnwrap(scene.delegate as? SceneDelegate)
        XCTAssertTrue(delegate.window?.windowScene === scene)
        XCTAssertNotNil(delegate.window?.rootViewController)

        let manifest = try XCTUnwrap(Bundle.main.object(forInfoDictionaryKey: "UIApplicationSceneManifest") as? [String: Any])
        XCTAssertEqual(manifest["UIApplicationSupportsMultipleScenes"] as? Bool, false)
        let configurations = try XCTUnwrap(manifest["UISceneConfigurations"] as? [String: [[String: Any]]])
        let application = try XCTUnwrap(configurations["UIWindowSceneSessionRoleApplication"]?.first)
        XCTAssertEqual(application["UISceneConfigurationName"] as? String, "Default Configuration")
        XCTAssertEqual(application["UISceneDelegateClassName"] as? String, "Ancient_Empires_II.SceneDelegate")
    }

    func testCoordinatorStartsAtTitleAndContinuesAutosaveFromMenuThenFlushesBattleUnderModal() async throws {
        let directory = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
            .appendingPathComponent(".noindex/AE-AutosaveLifecycleTests", isDirectory: true)
        defer { try? FileManager.default.removeItem(at: directory) }
        let store = CampaignSaveStore(directoryURL: directory)
        var session = try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), seed: 7)
        try session.advanceStory()
        try store.save(session.snapshot)
        try store.save(session.snapshot, slot: 0)
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let coordinator = AppCoordinator(window: window, saveStore: store)
        coordinator.start()
        let navigation = try XCTUnwrap(window.rootViewController as? UINavigationController)
        let title = try XCTUnwrap(navigation.topViewController as? TitleViewController)
        XCTAssertEqual(try store.load(), session.snapshot)
        title.onContinue?()
        let menu = try XCTUnwrap(navigation.topViewController as? MainMenuViewController)
        menu.loadViewIfNeeded()
        let resume = try XCTUnwrap(menu.view.subviews.first { $0.accessibilityIdentifier == "menu.continue" } as? UIButton)
        XCTAssertTrue(resume.isEnabled)
        XCTAssertNil(menu.view.subviews.first { $0.accessibilityIdentifier == "menu.online" })
        resume.sendActions(for: .touchUpInside)
        XCTAssertTrue(navigation.topViewController === menu)
        resume.sendActions(for: .touchUpInside)
        for _ in 0..<50 where !(navigation.topViewController is CampaignMapViewController) {
            try await Task.sleep(for: .milliseconds(20))
        }
        let battle = try XCTUnwrap(navigation.topViewController as? CampaignMapViewController)
        battle.loadViewIfNeeded()
        coordinator.saveActiveCampaign()
        let expected = try XCTUnwrap(store.load())
        XCTAssertEqual(expected.currentStoryMessageID, session.snapshot.currentStoryMessageID)
        XCTAssertEqual(expected.units, session.snapshot.units)

        battle.present(OriginalListViewController(title: GameLocalization.text("PAUSED")), animated: false)
        XCTAssertNotNil(battle.presentedViewController)
        try store.clear()
        coordinator.saveActiveCampaign()
        XCTAssertEqual(try CampaignSaveStore(directoryURL: directory).load(), expected)
        XCTAssertEqual(try store.load(slot: 0), session.snapshot)

        battle.dismiss(animated: false)
        coordinator.showTitle()
        try store.clear()
        coordinator.saveActiveCampaign()
        XCTAssertFalse(store.hasSave, "Leaving the battle must not write an old controller's state")
    }

    func testStartupWithoutAutosaveShowsTitleAndCorruptionIsPreserved() throws {
        let directory = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
            .appendingPathComponent(".noindex/AE-AutosaveStartupTests", isDirectory: true)
        defer { try? FileManager.default.removeItem(at: directory) }
        let store = CampaignSaveStore(directoryURL: directory)
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let coordinator = AppCoordinator(window: window, saveStore: store)
        coordinator.start()
        let navigation = try XCTUnwrap(window.rootViewController as? UINavigationController)
        XCTAssertTrue(navigation.topViewController is TitleViewController)
        (navigation.topViewController as? TitleViewController)?.onContinue?()
        let emptyMenu = try XCTUnwrap(navigation.topViewController as? MainMenuViewController)
        emptyMenu.loadViewIfNeeded()
        let disabled = try XCTUnwrap(emptyMenu.view.subviews.first { $0.accessibilityIdentifier == "menu.continue" } as? UIButton)
        XCTAssertFalse(disabled.isEnabled)

        let corrupt = Data("invalid autosave".utf8)
        let file = directory.appendingPathComponent("campaign-autosave.json")
        try corrupt.write(to: file, options: .atomic)
        coordinator.start()
        XCTAssertTrue(navigation.topViewController is TitleViewController)
        (navigation.topViewController as? TitleViewController)?.onContinue?()
        let menu = try XCTUnwrap(navigation.topViewController as? MainMenuViewController)
        menu.loadViewIfNeeded()
        let resume = try XCTUnwrap(menu.view.subviews.first { $0.accessibilityIdentifier == "menu.continue" } as? UIButton)
        XCTAssertTrue(resume.isEnabled)
        resume.sendActions(for: .touchUpInside)
        resume.sendActions(for: .touchUpInside)
        XCTAssertTrue(navigation.topViewController is StartupErrorViewController)
        coordinator.saveActiveCampaign()
        XCTAssertEqual(try Data(contentsOf: file), corrupt)
    }

    func testManualSlotsSurviveNewGameAndRestorePendingPresentation() throws {
        let directory = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
            .appendingPathComponent(".noindex/AE-SaveStoreTests", isDirectory: true)
        defer { try? FileManager.default.removeItem(at: directory) }
        let store = CampaignSaveStore(directoryURL: directory)
        var session = try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), seed: 7)
        session.enableStoryPresentations()
        try session.advanceStory()
        let saved = session.snapshot
        try store.save(saved, slot: 0)
        try store.save(saved, slot: 9)
        session.completePresentation()
        try store.save(session.snapshot)
        XCTAssertEqual(try store.load(slot: 0), saved)
        XCTAssertEqual(try store.load(slot: 9), saved)
        XCTAssertEqual(try store.load(), session.snapshot)
        XCTAssertNil(try store.load(slot: 5))
        try store.clear()
        XCTAssertNil(try store.load())
        XCTAssertEqual(try store.load(slot: 9), saved)
        XCTAssertThrowsError(try store.save(saved, slot: 10))
        XCTAssertThrowsError(try store.load(slot: -1))
        let corruptURL = directory.appendingPathComponent("campaign-slot-1.json")
        try Data("invalid save".utf8).write(to: corruptURL, options: .atomic)
        XCTAssertThrowsError(try store.load(slot: 0))
        XCTAssertEqual(try store.load(slot: 9), saved)
        XCTAssertEqual(try Data(contentsOf: corruptURL), Data("invalid save".utf8))
    }

    func testCampaignUnlockProgressSurvivesAutosaveClearAndNeverMovesBackward() throws {
        let directory = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
            .appendingPathComponent(".noindex/AE-CampaignProgressTests", isDirectory: true)
        defer { try? FileManager.default.removeItem(at: directory) }
        let store = CampaignSaveStore(directoryURL: directory)
        XCTAssertEqual(store.highestUnlockedMission, 0)
        try store.unlock(mission: 1)
        XCTAssertEqual(store.highestUnlockedMission, 1)
        try store.unlock(mission: 0)
        XCTAssertEqual(store.highestUnlockedMission, 1)
        try store.clear()
        XCTAssertEqual(CampaignSaveStore(directoryURL: directory).highestUnlockedMission, 1)
        try store.unlock(mission: 99)
        XCTAssertEqual(store.highestUnlockedMission, 7)
    }
}
