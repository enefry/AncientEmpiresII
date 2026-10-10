import AECore
import AEContent
import CoreGraphics
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignSessionTests: XCTestCase {
    func testOpeningMovesTheThreeBlueUnitsAndRemovesTheTempleGuard() throws {
        var session = try makeSession(seed: 7)

        XCTAssertEqual(session.snapshot.phase, .opening)
        XCTAssertEqual(session.snapshot.units.count, 6)
        try session.advanceStory()
        XCTAssertEqual(session.snapshot.currentStoryMessageID, 221)
        XCTAssertEqual(session.unit(id: 1)?.position, GridPoint(x: 3, y: 8))
        XCTAssertEqual(session.unit(id: 0)?.position, GridPoint(x: 4, y: 9))
        XCTAssertEqual(session.unit(id: 2)?.position, GridPoint(x: 3, y: 10))

        try session.advanceStory()
        XCTAssertEqual(session.snapshot.currentStoryMessageID, 222)
        try session.advanceStory()
        XCTAssertEqual(session.snapshot.currentStoryMessageID, 223)
        XCTAssertEqual(session.snapshot.units.count, 5)
        try session.advanceStory()
        XCTAssertEqual(session.snapshot.currentStoryMessageID, 224)
        try session.advanceStory()
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 196)
        XCTAssertFalse(session.isInputEnabled)

        session.dismissCampaignHelp()
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        XCTAssertTrue(session.isInputEnabled)
        try session.selectUnit(id: 1)
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        session.movementRangeDidAppear(for: 1)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 197)
        session.dismissCampaignHelp()
        XCTAssertTrue(session.isInputEnabled)
    }

    func testM0TutorialWaitsForSelectionRangeAndTwoDistinctFinishedActions() throws {
        var session = try makeSession(seed: 20)
        try completeOpeningStory(&session)

        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 196)
        XCTAssertThrowsError(try session.endPlayerTurn()) {
            XCTAssertEqual($0 as? CampaignSessionError, .gameNotReady)
        }
        session.dismissCampaignHelp()
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        try session.selectUnit(id: 1)
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        session.movementRangeDidAppear(for: 1)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 197)
        session.dismissCampaignHelp()
        XCTAssertEqual(session.snapshot.m0ScriptState, 16)

        try moveToFirstReachableDestination(unitID: 1, in: &session)
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        XCTAssertEqual(session.unit(id: 1)?.hasActed, false)
        let pendingMove = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(session.snapshot))
        session = try makeSession(seed: 0, snapshot: pendingMove)
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        XCTAssertEqual(session.snapshot.selection?.unitID, 1)
        try session.endUnitAction(unitID: 1)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 198)
        let savedHelp = try JSONEncoder().encode(session.snapshot)
        let restoredSnapshot = try JSONDecoder().decode(CampaignSnapshot.self, from: savedHelp)
        var restoredSession = try makeSession(seed: 0, snapshot: restoredSnapshot)
        XCTAssertEqual(restoredSession.snapshot.pendingCampaignHelpID, 198)
        XCTAssertFalse(restoredSession.isInputEnabled)
        restoredSession.dismissCampaignHelp()
        XCTAssertNil(restoredSession.snapshot.pendingCampaignHelpID)
        XCTAssertEqual(restoredSession.snapshot.m0ScriptState, 17)
        session = restoredSession

        XCTAssertThrowsError(try session.endUnitAction(unitID: 1))
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        try moveToFirstReachableDestination(unitID: 0, in: &session)
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        try session.endUnitAction(unitID: 0)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 199)
        session.dismissCampaignHelp()
        XCTAssertEqual(session.snapshot.m0ScriptState, 18)

        try moveToFirstReachableDestination(unitID: 2, in: &session)
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        try session.endUnitAction(unitID: 2)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 200)
        session.dismissCampaignHelp()
        XCTAssertEqual(session.snapshot.m0ScriptState, 19)

        try session.endPlayerTurn()
        XCTAssertEqual(session.snapshot.currentTurn, 2)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 201)
        session.dismissCampaignHelp()
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 202)
        session.dismissCampaignHelp()
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        try session.selectUnit(id: 0)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 203)
        XCTAssertEqual(session.snapshot.reinforcementWave, 0)
    }

    func testM0DoesNotCreateReinforcementsUntilTheLastHelpIsDismissed() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(
            mapID: map.id,
            units: [CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9)],
            nextUnitID: 1,
            randomState: JavaRandom(seed: 11).state,
            m0ScriptState: 21,
            pendingCampaignHelpID: 202
        )
        var session = try makeSession(seed: 11, snapshot: snapshot)

        session.dismissCampaignHelp()
        XCTAssertNil(session.snapshot.pendingCampaignHelpID)
        try session.selectUnit(id: 0)
        XCTAssertEqual(session.snapshot.pendingCampaignHelpID, 203)
        XCTAssertEqual(session.snapshot.phase, .playerTurn)
        XCTAssertEqual(session.snapshot.reinforcementWave, 0)

        session.dismissCampaignHelp()
        XCTAssertEqual(session.snapshot.phase, .story)
        XCTAssertEqual(session.snapshot.currentStoryMessageID, 225)
        XCTAssertEqual(session.snapshot.reinforcementWave, 1)
    }

    func testM0ProfilesAreReadFromTheOriginalUnitFiles() throws {
        let profiles = try GameResources.campaignUnitProfiles()
        XCTAssertEqual(profiles[0]?.attackMinimum, 50)
        XCTAssertEqual(profiles[0]?.attackMaximum, 55)
        XCTAssertEqual(profiles[0]?.baseDefense, 5)
        XCTAssertEqual(profiles[1]?.maximumRange, 2)
        XCTAssertEqual(profiles[9]?.baseDefense, 20)
        XCTAssertEqual(profiles[10]?.attackMinimum, 40)
    }

    func testCombatAppliesDamageAndAnAdjacentCounterattack() throws {
        let map = try GameResources.missionZero()
        let units = [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9),
            CampaignUnitState(id: 1, type: 0, fraction: 1, x: 3, y: 8),
            CampaignUnitState(id: 2, type: 10, fraction: 2, x: 4, y: 8),
        ]
        var session = try makeSession(
            seed: 42,
            snapshot: CampaignSnapshot(
                mapID: map.id,
                units: units,
                randomState: JavaRandom(seed: 42).state
            )
        )

        let result = try session.attack(unitID: 1, targetID: 2)

        XCTAssertGreaterThan(result.damage, 0)
        XCTAssertGreaterThan(result.counterDamage, 0)
        XCTAssertEqual(session.unit(id: 1)?.hitPoints, 100 - result.counterDamage)
        XCTAssertEqual(session.unit(id: 2)?.hitPoints, 100 - result.damage)
        XCTAssertTrue(session.unit(id: 1)?.hasActed == true)
    }

    func testFirstWaveClearSpawnsBothOriginalReinforcements() throws {
        let map = try GameResources.missionZero()
        let units = [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9),
            CampaignUnitState(id: 1, type: 0, fraction: 1, x: 3, y: 8, rank: 80),
            CampaignUnitState(id: 2, type: 10, fraction: 2, x: 4, y: 8, hitPoints: 1),
        ]
        var session = try makeSession(
            seed: 4,
            snapshot: CampaignSnapshot(
                mapID: map.id,
                units: units,
                randomState: JavaRandom(seed: 4).state
            )
        )

        _ = try session.attack(unitID: 1, targetID: 2)

        XCTAssertEqual(session.snapshot.phase, .story)
        XCTAssertEqual(session.snapshot.currentStoryMessageID, 225)
        XCTAssertEqual(session.snapshot.reinforcementWave, 1)
        XCTAssertEqual(session.snapshot.units.filter { $0.isAlive && $0.fraction == 2 }.map(\.type), [1, 0])
        XCTAssertEqual(
            session.snapshot.units.first { $0.fraction == 2 && $0.type == 1 }?.position,
            GridPoint(x: 1, y: 2)
        )
        XCTAssertEqual(
            session.snapshot.units.first { $0.fraction == 2 && $0.type == 0 }?.position,
            GridPoint(x: 10, y: 9)
        )

        let savedData = try JSONEncoder().encode(session.snapshot)
        let restoredSnapshot = try JSONDecoder().decode(CampaignSnapshot.self, from: savedData)
        var restoredSession = try makeSession(seed: 0, snapshot: restoredSnapshot)

        XCTAssertEqual(restoredSession.snapshot, session.snapshot)
        XCTAssertEqual(restoredSession.snapshot.reinforcementWave, 1)
        XCTAssertEqual(restoredSession.snapshot.units.filter { $0.isAlive && $0.fraction == 2 }.count, 2)

        try restoredSession.advanceStory()
        XCTAssertEqual(restoredSession.snapshot.currentStoryMessageID, 226)
        try restoredSession.advanceStory()
        XCTAssertTrue(restoredSession.isInputEnabled)
        XCTAssertEqual(restoredSession.snapshot.reinforcementWave, 1)
        XCTAssertEqual(restoredSession.snapshot.units.filter { $0.isAlive && $0.fraction == 2 }.count, 2)
    }

    func testSecondWaveMustBeClearedBeforeTheEndingAndVictory() throws {
        let map = try GameResources.missionZero()
        let units = [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 5, y: 5),
            CampaignUnitState(id: 1, type: 0, fraction: 1, x: 1, y: 1, rank: 80),
            CampaignUnitState(id: 2, type: 0, fraction: 1, x: 10, y: 10, rank: 80),
            CampaignUnitState(id: 3, type: 1, fraction: 2, x: 1, y: 2, hitPoints: 1),
            CampaignUnitState(id: 4, type: 0, fraction: 2, x: 10, y: 9, hitPoints: 1),
        ]
        var session = try makeSession(
            seed: 9,
            snapshot: CampaignSnapshot(
                mapID: map.id,
                units: units,
                reinforcementWave: 1,
                nextUnitID: 5,
                randomState: JavaRandom(seed: 9).state
            )
        )

        _ = try session.attack(unitID: 1, targetID: 3)
        XCTAssertEqual(session.snapshot.phase, .playerTurn)
        _ = try session.attack(unitID: 2, targetID: 4)
        XCTAssertEqual(session.snapshot.phase, .story)
        XCTAssertEqual(session.snapshot.currentStoryMessageID, 227)

        let savedData = try JSONEncoder().encode(session.snapshot)
        let restoredSnapshot = try JSONDecoder().decode(CampaignSnapshot.self, from: savedData)
        var restoredSession = try makeSession(seed: 0, snapshot: restoredSnapshot)

        XCTAssertEqual(restoredSession.snapshot, session.snapshot)
        XCTAssertEqual(restoredSession.snapshot.reinforcementWave, 1)
        XCTAssertEqual(restoredSession.snapshot.units.filter { $0.isAlive && $0.fraction == 2 }.count, 0)

        try restoredSession.advanceStory()
        try restoredSession.advanceStory()
        try restoredSession.advanceStory()
        XCTAssertEqual(restoredSession.snapshot.currentStoryMessageID, 230)
        try restoredSession.advanceStory()
        XCTAssertEqual(restoredSession.snapshot.phase, .victory)
    }

    func testSnapshotRestoresTheActionAndRandomState() throws {
        var original = try makeSession(seed: 125)
        try original.advanceStory()
        try original.advanceStory()
        let data = try JSONEncoder().encode(original.snapshot)
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: data)
        let restored = try makeSession(seed: 0, snapshot: decoded)

        XCTAssertEqual(restored.snapshot, original.snapshot)
        XCTAssertEqual(restored.snapshot.currentStoryMessageID, 222)
    }

    func testEnemyTurnMovesRedUnitsAndReturnsControlToBlue() throws {
        var session = try makeSession(seed: 31)
        try beginPlayerTurn(&session)
        let startingPositions = Dictionary(
            uniqueKeysWithValues: session.snapshot.units.filter { $0.fraction == 2 }.map { ($0.id, $0.position) }
        )

        try session.endPlayerTurn()

        XCTAssertEqual(session.snapshot.phase, .playerTurn)
        XCTAssertEqual(session.snapshot.activeFraction, 1)
        XCTAssertEqual(session.snapshot.currentTurn, 2)
        XCTAssertTrue(session.snapshot.units.contains {
            $0.fraction == 2 && startingPositions[$0.id] != $0.position
        })
    }

    func testEnemyTurnUsesStableUnitPriorityAndLowHPOrdering() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(
            mapID: map.id,
            units: [
                CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9),
                CampaignUnitState(id: 2, type: 1, fraction: 2, x: 10, y: 0),
                CampaignUnitState(id: 3, type: 0, fraction: 2, x: 9, y: 0, hitPoints: 40),
                CampaignUnitState(id: 4, type: 4, fraction: 2, x: 8, y: 0, hitPoints: 75),
                CampaignUnitState(id: 5, type: 0, fraction: 2, x: 7, y: 0, hitPoints: 20),
                CampaignUnitState(id: 6, type: 10, fraction: 2, x: 11, y: 0),
                CampaignUnitState(id: 7, type: 0, fraction: 2, x: 6, y: 0, hitPoints: 40),
            ],
            randomState: JavaRandom(seed: 31).state
        )
        var session = try makeSession(seed: 31, snapshot: snapshot)

        let actions = try session.endPlayerTurn()

        XCTAssertEqual(actions.map(\.unitID), [5, 3, 7, 4, 2, 6])
    }

    func testEnemyAIRecognizesOnlyKingUnitsAsCommanders() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(
            mapID: map.id,
            units: [
                CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9),
                CampaignUnitState(id: 1, type: 10, fraction: 2, x: 10, y: 0),
            ],
            randomState: JavaRandom(seed: 47).state
        )
        var session = try makeSession(seed: 47, snapshot: snapshot)

        let actions = try session.endPlayerTurn()

        XCTAssertEqual(actions.first?.unitID, 1)
        XCTAssertEqual(actions.first?.strategicTarget, GridPoint(x: 1, y: 9))
    }

    func testEnemyTurnChoosesTheExpectedTargetAndIsDeterministic() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(
            mapID: map.id,
            units: [
                CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9),
                CampaignUnitState(id: 1, type: 0, fraction: 1, x: 5, y: 4),
                CampaignUnitState(id: 2, type: 1, fraction: 2, x: 3, y: 4),
                CampaignUnitState(id: 3, type: 10, fraction: 2, x: 11, y: 0),
            ],
            randomState: JavaRandom(seed: 93).state
        )
        var first = try makeSession(seed: 93, snapshot: snapshot)
        var second = try makeSession(seed: 93, snapshot: snapshot)

        let firstActions = try first.endPlayerTurn()
        let secondActions = try second.endPlayerTurn()

        XCTAssertEqual(firstActions.first { $0.unitID == 2 }?.targetID, 1)
        XCTAssertEqual(firstActions, secondActions)
        XCTAssertEqual(first.snapshot, second.snapshot)
    }

    func testCommanderDeathEndsTheCampaign() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(
            mapID: map.id,
            units: [
                CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9, hitPoints: 1),
                CampaignUnitState(id: 1, type: 10, fraction: 2, x: 2, y: 9, rank: 80),
            ],
            randomState: JavaRandom(seed: 71).state
        )
        var session = try makeSession(seed: 71, snapshot: snapshot)

        _ = try session.attack(unitID: 0, targetID: 1)

        XCTAssertEqual(session.snapshot.phase, .defeat)
    }

    private func beginPlayerTurn(_ session: inout CampaignSession) throws {
        for _ in 0..<5 { try session.advanceStory() }
        while session.snapshot.pendingCampaignHelpID != nil {
            session.dismissCampaignHelp()
        }
    }

    private func completeOpeningStory(_ session: inout CampaignSession) throws {
        for _ in 0..<5 { try session.advanceStory() }
    }

    private func moveToFirstReachableDestination(unitID: Int, in session: inout CampaignSession) throws {
        let range = try XCTUnwrap(session.movementRange(for: unitID))
        let destination = try XCTUnwrap(range.reachableDestinations.sorted {
            $0.y == $1.y ? $0.x < $1.x : $0.y < $1.y
        }.first)
        try session.move(unitID: unitID, to: destination)
    }

    private func makeSession(seed: Int64, snapshot: CampaignSnapshot? = nil) throws -> CampaignSession {
        try CampaignSession(
            map: GameResources.missionZero(),
            profiles: GameResources.campaignUnitProfiles(),
            rules: GameResources.campaignRuleset(),
            seed: seed,
            snapshot: snapshot
        )
    }
}

@MainActor
final class CampaignUIKitFlowTests: XCTestCase {
    func testEndTurnControlShowsDefeatWhenEnemyKillsCommander() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(
            mapID: map.id,
            units: [
                CampaignUnitState(id: 0, type: 9, fraction: 1, x: 1, y: 9, hitPoints: 1),
                CampaignUnitState(id: 1, type: 0, fraction: 2, x: 2, y: 9),
            ],
            randomState: JavaRandom(seed: 71).state
        )
        let session = try CampaignSession(
            map: map,
            profiles: GameResources.campaignUnitProfiles(),
            rules: GameResources.campaignRuleset(),
            snapshot: snapshot
        )
        // Enemy results are committed before the recoverable NEW TURN/combat queue plays.
        var committed: CampaignSnapshot?
        let liveController = try CampaignMapViewController(session: session)
        liveController.onSnapshotChange = { committed = $0 }
        liveController.loadViewIfNeeded()
        let liveEnd = try XCTUnwrap(findView(identifier: "campaign.end-turn", in: liveController.view) as? UIButton)
        liveEnd.sendActions(for: .touchUpInside)
        var restored = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: XCTUnwrap(committed))
        XCTAssertEqual(restored.snapshot.phase, .defeat)
        XCTAssertEqual(restored.pendingPresentation?.scene?.kind, .newTurn)
        while restored.pendingPresentation != nil { restored.completePresentation() }
        let resultController = try CampaignMapViewController(session: restored)
        resultController.loadViewIfNeeded()
        resultController.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        resultController.viewDidAppear(false)
        let result = try XCTUnwrap(resultController.children.first as? OriginalResultViewController)
        XCTAssertFalse(result.canvas.victory)
        result.canvas.tick = 53
        result.canvas.layoutIfNeeded()
        let message = try XCTUnwrap(findView(identifier: "campaign.result.text", in: result.view) as? UILabel)
        XCTAssertEqual(message.text, GameLocalization.text("story.57"))
        let resultButton = try XCTUnwrap(findView(identifier: "campaign.result.back", in: result.view) as? UIButton)
        var exited = false
        resultController.onExit = { exited = true }
        resultButton.sendActions(for: .touchUpInside)
        XCTAssertTrue(exited)
        resultController.viewWillDisappear(false)
    }

    func testCompletedCampaignRestoresResultWithoutActionControls() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 0, fraction: 1, x: 3, y: 8),
        ], phase: .victory, randomState: JavaRandom(seed: 1).state)
        let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: snapshot)
        let controller = try CampaignMapViewController(session: session)
        controller.loadViewIfNeeded()
        controller.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        controller.view.layoutIfNeeded()
        controller.viewDidAppear(false)
        controller.view.layoutIfNeeded()
        let result = try XCTUnwrap(controller.children.first as? OriginalResultViewController)
        result.canvas.layoutIfNeeded()
        let endTurn = try XCTUnwrap(findView(identifier: "campaign.end-turn", in: controller.view))
        XCTAssertTrue(endTurn.isHidden || endTurn.superview?.isHidden == true)
        let header = try XCTUnwrap(findView(identifier: "campaign.turn.status", in: controller.view))
        XCTAssertTrue(header.superview?.isHidden == true)
        let image = UIGraphicsImageRenderer(bounds: controller.view.bounds).image { controller.view.layer.render(in: $0.cgContext) }
        let frame = XCTAttachment(image: image)
        frame.name = "Restored victory clean battlefield UIKit render"
        frame.lifetime = .keepAlways
        add(frame)
        var exited = false
        controller.onExit = { exited = true }
        let back = try XCTUnwrap(findView(identifier: "campaign.result.back", in: result.view) as? UIButton)
        back.sendActions(for: .touchUpInside)
        XCTAssertTrue(exited)
        controller.viewWillDisappear(false)
    }

    private func findView(identifier: String, in root: UIView) -> UIView? {
        if root.accessibilityIdentifier == identifier { return root }
        for child in root.subviews {
            if let match = findView(identifier: identifier, in: child) { return match }
        }
        return nil
    }
}

final class UnitSpriteImageProcessorTests: XCTestCase {
    func testActedVariantUsesPaletteAverageAndPreservesLightOutlineAndAlpha() throws {
        let source = try makeImage(pixels: [
            90, 60, 30, 255,
            244, 244, 230, 255,
            0, 0, 0, 0,
            80, 40, 20, 128,
        ])

        let acted = try XCTUnwrap(UnitSpriteImageProcessor.actedVariant(of: source))
        let output = try XCTUnwrap(readPixels(from: acted))

        XCTAssertEqual(Array(output[0..<4]), [60, 60, 60, 255])
        XCTAssertEqual(Array(output[4..<8]), [244, 244, 230, 255])
        XCTAssertEqual(Array(output[8..<12]), [0, 0, 0, 0])
        XCTAssertEqual(Array(output[12..<16]), [23, 23, 23, 128])
    }

    private func makeImage(pixels: [UInt8]) throws -> CGImage {
        let data = Data(pixels) as CFData
        let provider = try XCTUnwrap(CGDataProvider(data: data))
        let colorSpace = try XCTUnwrap(CGColorSpace(name: CGColorSpace.sRGB))
        return try XCTUnwrap(CGImage(
            width: 4,
            height: 1,
            bitsPerComponent: 8,
            bitsPerPixel: 32,
            bytesPerRow: 16,
            space: colorSpace,
            bitmapInfo: CGBitmapInfo(rawValue: CGImageAlphaInfo.last.rawValue),
            provider: provider,
            decode: nil,
            shouldInterpolate: false,
            intent: .defaultIntent
        ))
    }

    private func readPixels(from image: CGImage) -> [UInt8]? {
        guard let colorSpace = CGColorSpace(name: CGColorSpace.sRGB) else { return nil }
        let bitmapInfo = CGBitmapInfo.byteOrder32Big.union(
            CGBitmapInfo(rawValue: CGImageAlphaInfo.premultipliedLast.rawValue)
        )
        guard let context = CGContext(
            data: nil,
            width: image.width,
            height: image.height,
            bitsPerComponent: 8,
            bytesPerRow: image.width * 4,
            space: colorSpace,
            bitmapInfo: bitmapInfo.rawValue
        ) else { return nil }
        context.draw(image, in: CGRect(x: 0, y: 0, width: image.width, height: image.height))
        guard let data = context.data?.assumingMemoryBound(to: UInt8.self) else { return nil }
        return Array(UnsafeBufferPointer(start: data, count: image.width * image.height * 4))
    }
}
