import AECore
import AEContent
import Foundation
import Testing

struct CampaignPresentationTests {
    @Test func openingScenesRestoreWithoutSkippingDialogueOrApplyingTwice() throws {
        var session = try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), seed: 7)
        session.enableStoryPresentations()
        try session.advanceStory()
        let arrival = try #require(session.pendingPresentation?.scene)
        #expect(arrival.movements.map(\.unitID) == [1, 0, 2])
        #expect(arrival.movements.map { $0.route.last } == [GridPoint(x: 3, y: 8), GridPoint(x: 4, y: 9), GridPoint(x: 3, y: 10)])
        #expect(arrival.unitSpeed == 4)
        #expect(session.pendingPresentation?.unitsBefore.first(where: { $0.id == 1 })?.position == GridPoint(x: 0, y: 8))
        try drainRestoring(&session)
        #expect(session.snapshot.currentStoryMessageID == 221)
        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .hit)
        #expect(session.pendingPresentation?.scene?.durationTicks == 10)
        try drainRestoring(&session)
        #expect(session.snapshot.currentStoryMessageID == 222)
        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .disappearance)
        #expect(session.pendingPresentation?.scene?.durationTicks == 20)
        #expect(session.snapshot.units.count == 5)
        #expect(session.pendingPresentation?.unitsBefore.count == 6)
        try drainRestoring(&session)
        #expect(session.snapshot.currentStoryMessageID == 223)
        try session.advanceStory()
        #expect(session.snapshot.currentStoryMessageID == 224)
        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 129)
        #expect(session.snapshot.pendingCampaignHelpID == nil)
        session = try restore(session.snapshot)
        #expect(!session.isInputEnabled)
        #expect(session.snapshot.pendingCampaignHelpID == nil)
        session.completePresentation()
        #expect(session.snapshot.pendingCampaignHelpID == 196)
    }

    @Test func reinforcementsEnterOneAtATimeAndRestoreWithoutRespawning() throws {
        var snapshot = CampaignSnapshot(mapID: "m0", units: [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 4, y: 9),
            CampaignUnitState(id: 1, type: 10, fraction: 2, x: 5, y: 9, hitPoints: 1)
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        snapshot.storyPresentationsEnabled = true
        var session = try restore(snapshot)
        _ = try session.attack(unitID: 0, targetID: 1, recordPresentation: true)
        while session.pendingPresentation?.kind == .combat || session.pendingPresentation?.scene?.kind == .death {
            session.completePresentation()
        }
        #expect(session.snapshot.reinforcementWave == 1)
        let arrivals = session.snapshot.pendingPresentations?.filter { $0.scene?.kind == .arrival } ?? []
        try #require(arrivals.count == 2)
        #expect(arrivals[0].unitsBefore.filter { $0.fraction == 2 && $0.isAlive }.map(\.position) == [GridPoint(x: 1, y: 1)])
        #expect(arrivals[1].unitsBefore.filter { $0.fraction == 2 && $0.isAlive }.map(\.position) == [GridPoint(x: 1, y: 2), GridPoint(x: 10, y: 10)])
        try drainRestoring(&session)
        #expect(session.snapshot.currentStoryMessageID == 225)
        #expect(session.snapshot.units.filter { $0.fraction == 2 && $0.isAlive }.map(\.position) == [GridPoint(x: 1, y: 2), GridPoint(x: 10, y: 9)])
    }

    private func drainRestoring(_ session: inout CampaignSession) throws {
        let committed = session.snapshot.units
        let random = session.snapshot.randomState
        while session.pendingPresentation != nil {
            session = try restore(session.snapshot)
            #expect(!session.isInputEnabled)
            #expect(throws: CampaignSessionError.gameNotReady) { try session.advanceStory() }
            session.completePresentation()
            #expect(session.snapshot.units == committed)
            #expect(session.snapshot.randomState == random)
        }
    }

    @Test func restoredCombatDoesNotRerollOrApplyDamageTwice() throws {
        var session = try makeSession()
        let original = session.snapshot
        let result = try session.attack(unitID: 0, targetID: 1, recordPresentation: true)
        let committed = session.snapshot
        #expect(committed.randomState != original.randomState)
        #expect(!session.isInputEnabled)
        #expect(session.pendingPresentation?.combat?.damage == result.damage)
        #expect(session.pendingPresentation?.combat?.defender.hitPoints == 100)
        session = try restore(committed)
        #expect(throws: CampaignSessionError.gameNotReady) { try session.attack(unitID: 0, targetID: 1) }
        #expect(throws: CampaignSessionError.gameNotReady) { try session.endPlayerTurn() }
        session.completePresentation()
        session.completePresentation()
        #expect(session.snapshot.units == committed.units)
        #expect(session.snapshot.randomState == committed.randomState)
        #expect(session.pendingPresentation == nil)
        #expect(session.isInputEnabled)
    }

    @Test func tutorialWaitsUntilCombatFinishesEvenAfterRestore() throws {
        var session = try makeSession(scriptState: 16)
        _ = try session.attack(unitID: 0, targetID: 1, recordPresentation: true)
        #expect(session.snapshot.pendingCampaignHelpID == nil)
        session = try restore(session.snapshot)
        #expect(session.snapshot.pendingCampaignHelpID == nil)
        session.completePresentation()
        #expect(session.snapshot.pendingCampaignHelpID == 198)
    }

    @Test func enemyPresentationQueueMatchesNonanimatedResolution() throws {
        var animated = try makeSession()
        var immediate = animated
        let actions = try animated.endPlayerTurn(recordPresentation: true)
        let immediateActions = try immediate.endPlayerTurn()
        #expect(actions == immediateActions)
        #expect(animated.snapshot.units == immediate.snapshot.units)
        #expect(animated.snapshot.randomState == immediate.snapshot.randomState)
        #expect(animated.pendingPresentation != nil)
        while animated.pendingPresentation != nil {
            animated = try restore(animated.snapshot)
            #expect(!animated.isInputEnabled)
            animated.completePresentation()
        }
        #expect(animated.snapshot == immediate.snapshot)
    }

    @Test func pendingMoveCannotBeAbandonedThroughAnotherUnitsCommand() throws {
        var session = try makeSession()
        try session.move(unitID: 0, to: GridPoint(x: 4, y: 8))
        #expect(throws: CampaignSessionError.gameNotReady) { try session.endPlayerTurn() }
        #expect(throws: CampaignSessionError.gameNotReady) { try session.endUnitAction(unitID: 2) }
        #expect(throws: CampaignSessionError.gameNotReady) { try session.move(unitID: 2, to: GridPoint(x: 3, y: 9)) }
        #expect(session.unit(id: 0)?.hasActed == false)
    }

    @Test func originalCharacterThresholds() {
        #expect(CampaignCombatPresentation.characterCount(health: 100, maximum: 5) == 5)
        #expect(CampaignCombatPresentation.characterCount(health: 80, maximum: 5) == 4)
        #expect(CampaignCombatPresentation.characterCount(health: 81, maximum: 5) == 5)
        #expect(CampaignCombatPresentation.characterCount(health: 34, maximum: 3) == 2)
        #expect(CampaignCombatPresentation.characterCount(health: 33, maximum: 3) == 1)
        #expect(CampaignCombatPresentation.characterCount(health: 1, maximum: 1) == 1)
        #expect(CampaignCombatPresentation.characterCount(health: 0, maximum: 5) == 0)
    }

    private func makeSession(scriptState: Int = 22) throws -> CampaignSession {
        let snapshot = CampaignSnapshot(mapID: "m0", units: [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 4, y: 9),
            CampaignUnitState(id: 1, type: 10, fraction: 2, x: 5, y: 9),
            CampaignUnitState(id: 2, type: 0, fraction: 1, x: 3, y: 8),
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: scriptState)
        return try restore(snapshot)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: decoded)
    }
}
