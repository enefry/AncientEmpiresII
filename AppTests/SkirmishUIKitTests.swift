import AEContent
import AECore
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class SkirmishUIKitTests: XCTestCase {
    func testBonusMapsCanBeSelectedPreviewedAndStarted() throws {
        for index in 0..<8 {
            let controller = SkirmishSetupViewController()
            controller.loadViewIfNeeded()
            tap("skirmish.map.b\(index)", in: controller.view)
            XCTAssertNotNil(find("skirmish.preview", in: controller.view))
            var started = false
            controller.onStart = { map, settings in
                XCTAssertEqual(map.id, "b\(index)")
                let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(),
                    rules: GameResources.campaignRuleset(), skirmish: settings)
                let battle = try CampaignMapViewController(session: session)
                battle.loadViewIfNeeded()
                XCTAssertNotNil(self.find("campaign.end-turn", in: battle.view))
                let preview = try OriginalMapOverviewViewController(map: map, units: session.snapshot.units)
                preview.loadViewIfNeeded()
                XCTAssertNotNil(self.find("overview.map", in: preview.view))
                started = true
            }
            tap("skirmish.start", in: controller.view)
            XCTAssertTrue(started)
        }
    }

    func testDifficultyControlsPersistAndEachCPUCanChooseItsOwnLevel() throws {
        let defaults = UserDefaults.standard
        let previous = defaults.object(forKey: "game.aiDifficulty")
        defer { defaults.set(previous, forKey: "game.aiDifficulty") }
        GamePreferences.aiDifficulty = .normal
        let settings = OriginalSettingsViewController()
        settings.loadViewIfNeeded()
        settings.view.frame = CGRect(x: 0, y: 0, width: 390, height: 844)
        settings.view.layoutIfNeeded()
        let selector = try XCTUnwrap(find("settings.aiDifficulty", in: settings.view) as? UIButton)
        XCTAssertEqual(selector.accessibilityValue, AIDifficulty.normal.title)
        tap("settings.aiDifficulty", in: settings.view)
        XCTAssertEqual(GamePreferences.aiDifficulty, .hard)
        XCTAssertEqual(selector.accessibilityValue, AIDifficulty.hard.title)

        let controller = SkirmishSetupViewController()
        controller.loadViewIfNeeded()
        tap("skirmish.map.s3", in: controller.view)
        let map = try GameResources.campaignMap(named: "s3")
        let cpu = map.skirmishFractions[1]
        XCTAssertNil(find("skirmish.player.\(map.skirmishFractions[0]).difficulty", in: controller.view))
        tap("skirmish.player.\(cpu).difficulty", in: controller.view)
        var started = false
        controller.onStart = { _, configuration in
            XCTAssertEqual(configuration.players[1].resolvedDifficulty, .expert)
            XCTAssertEqual(configuration.players[2].resolvedDifficulty, .hard)
            started = true
        }
        tap("skirmish.start", in: controller.view)
        XCTAssertTrue(started)
        XCTAssertEqual(GamePreferences.aiDifficulty, .hard)
    }

    func testCPUOnlyStartsAndRestoresCommittedPresentation() async throws {
        let map = try GameResources.campaignMap(named: "s0")
        var settings = SkirmishConfiguration(map: map)
        for index in settings.players.indices { settings.players[index].control = .cpu }
        let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(),
            rules: GameResources.campaignRuleset(), seed: 42, skirmish: settings)
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let previousKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; previousKey?.makeKey() }
        let controller = try CampaignMapViewController(session: session)
        var latest: CampaignSnapshot?
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        let deadline = Date().addingTimeInterval(5)
        while latest?.pendingPresentations == nil, Date() < deadline { try await Task.sleep(for: .milliseconds(20)) }
        let committed = try XCTUnwrap(latest)
        XCTAssertFalse(committed.pendingPresentations?.isEmpty ?? true)
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(committed))
        let restored = try CampaignSession(map: XCTUnwrap(decoded.skirmishMap), profiles: GameResources.campaignUnitProfiles(),
            rules: GameResources.campaignRuleset(), snapshot: decoded)
        XCTAssertFalse(restored.isInputEnabled)
        let resumedController = try CampaignMapViewController(session: restored)
        resumedController.onSnapshotChange = { latest = $0 }
        window.rootViewController = resumedController
        window.layoutIfNeeded()
        XCTAssertEqual(latest?.units, committed.units)
        XCTAssertEqual(latest?.randomState, committed.randomState)
        XCTAssertEqual(latest?.moneyByFraction, committed.moneyByFraction)
        XCTAssertEqual(latest?.currentTurn, committed.currentTurn)
    }

    func testSetupListsAllBuiltinMapsAndStartsWithChosenSettings() throws {
        let controller = SkirmishSetupViewController()
        controller.loadViewIfNeeded()
        for id in try GameResources.skirmishMapIDs() { XCTAssertNotNil(find("skirmish.map.\(id)", in: controller.view)) }
        tap("skirmish.map.s0", in: controller.view)
        let map = try GameResources.campaignMap(named: "s0")
        let first = try XCTUnwrap(map.skirmishFractions.first)
        XCTAssertEqual(first, 2) // The original column scan starts red on this map.
        tap("skirmish.money", in: controller.view)
        tap("skirmish.cap", in: controller.view)
        var started = false
        controller.onStart = { map, settings in
            XCTAssertEqual(map.id, "s0")
            XCTAssertEqual(settings.startingMoney, 1000)
            XCTAssertEqual(settings.unitCap, 20)
            XCTAssertEqual(settings.players[0].control, .human)
            let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(),
                rules: GameResources.campaignRuleset(), skirmish: settings)
            let battle = try CampaignMapViewController(session: session)
            battle.loadViewIfNeeded()
            XCTAssertTrue((self.find("campaign.turn.status", in: battle.view) as? UILabel)?.text?.contains(GameLocalization.text("Red")) == true)
            XCTAssertTrue((self.find("campaign.end-turn", in: battle.view) as? UIButton)?.isEnabled == true)
            started = true
        }
        tap("skirmish.start", in: controller.view)
        XCTAssertTrue(started)
    }

    func testInvalidTeamSetupKeepsPanelAndShowsReason() throws {
        let controller = SkirmishSetupViewController()
        controller.loadViewIfNeeded()
        tap("skirmish.map.s0", in: controller.view)
        let map = try GameResources.campaignMap(named: "s0")
        tap("skirmish.player.\(map.skirmishFractions[1]).team", in: controller.view)
        var started = false
        controller.onStart = { _, _ in started = true }
        tap("skirmish.start", in: controller.view)
        XCTAssertFalse(started)
        XCTAssertTrue((find("skirmish.status", in: controller.view) as? UILabel)?.text?.contains(SkirmishError.insufficientTeams.localizedDescription) == true)
    }

    func testImportPersistsAndManualSaveSurvivesMapDeletion() throws {
        let directory = FileManager.default.temporaryDirectory.appendingPathComponent("AE-SkirmishUIKitTests", isDirectory: true)
        try FileManager.default.createDirectory(at: directory, withIntermediateDirectories: true)
        defer { try? FileManager.default.removeItem(at: directory) }
        let source = directory.appendingPathComponent("import.aem")
        try GameResources.originalResourceData(at: "res/s3.aem").write(to: source)
        let mapsDirectory = directory.appendingPathComponent("maps", isDirectory: true)
        let store = LocalSkirmishMapStore(directory: mapsDirectory)
        let map = try store.importMap(from: source)
        XCTAssertEqual(try LocalSkirmishMapStore(directory: mapsDirectory).maps(), [map])
        let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(),
            rules: GameResources.campaignRuleset(), skirmish: SkirmishConfiguration(map: map))
        let saves = CampaignSaveStore(directoryURL: directory.appendingPathComponent("saves"))
        try saves.save(session.snapshot, slot: 0)
        try store.delete(map)
        XCTAssertTrue(try store.maps().isEmpty)
        let snapshot = try XCTUnwrap(saves.load(slot: 0))
        let restored = try CampaignSession(map: XCTUnwrap(snapshot.skirmishMap), profiles: GameResources.campaignUnitProfiles(),
            rules: GameResources.campaignRuleset(), snapshot: snapshot)
        XCTAssertEqual(restored.snapshot, session.snapshot)
        let card = OriginalSaveSlotsViewController(store: saves)
        card.loadViewIfNeeded()
        let information = try XCTUnwrap(find("saves.information", in: card.view) as? UILabel)
        XCTAssertTrue(information.text?.contains(GameLocalization.text("story.33")) == true)
        XCTAssertTrue(information.text?.contains("import") == true)
    }

    func testRedHumanCanSelectRedKingAndFourSideAXSummaryIsPresent() throws {
        let map = try GameResources.campaignMap(named: "s3")
        let settings = SkirmishConfiguration(map: map)
        let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(),
            rules: GameResources.campaignRuleset(), skirmish: settings)
        let controller = try CampaignMapViewController(session: session)
        controller.loadViewIfNeeded()
        let king = try XCTUnwrap(session.snapshot.units.first { $0.fraction == session.snapshot.activeFraction })
        var latest: CampaignSnapshot?
        controller.onSnapshotChange = { latest = $0 }
        tap("campaign.cell.\(king.x).\(king.y)", in: controller.view)
        XCTAssertEqual(latest?.selection?.unitID, king.id)
        let canvas = try XCTUnwrap(find("campaign.map.canvas", in: controller.view))
        XCTAssertTrue(canvas.accessibilityValue?.contains("green units") == true)
        XCTAssertTrue(canvas.accessibilityValue?.contains("black units") == true)
    }

    private func tap(_ identifier: String, in view: UIView) {
        guard let button = find(identifier, in: view) as? UIButton else { XCTFail("Missing \(identifier)"); return }
        button.sendActions(for: .touchUpInside)
    }
    private func find(_ identifier: String, in view: UIView) -> UIView? {
        if view.accessibilityIdentifier == identifier { return view }
        return view.subviews.lazy.compactMap { self.find(identifier, in: $0) }.first
    }
}
