import AECore
import AEContent
import Foundation
import Testing

struct CampaignMissionFourTests {
    @Test func initializationAndNorthernEscortArrivalMatchSource() throws {
        var session = try makeSession()
        #expect(session.map.id == "m4")
        #expect(session.money(for: 1) == 0)
        #expect(session.money(for: 2) == 0)
        #expect(session.allowedUnitType == 0)
        #expect(session.objectiveTextID == 133)
        #expect(session.snapshot.missionRuntime?.references["king1"] == 0)
        #expect(session.snapshot.missionRuntime?.references["crystal"] == 2)
        #expect(session.unit(id: 0)?.position == GridPoint(x: 11, y: -5))
        #expect(session.unit(id: 2)?.position == GridPoint(x: 11, y: -7))

        session.enableStoryPresentations()
        try session.advanceStory()
        let arrival = try #require(session.pendingPresentation?.scene)
        #expect(arrival.kind == .arrival)
        #expect(arrival.movements.map(\.unitID) == [5, 1, 0, 4, 2, 3])
        #expect(arrival.movements.map { $0.route.last } == [
            GridPoint(x: 11, y: 2), GridPoint(x: 10, y: 1),
            GridPoint(x: 11, y: 1), GridPoint(x: 12, y: 1),
            GridPoint(x: 11, y: 0), GridPoint(x: 12, y: 0),
        ])
        #expect(arrival.cameraOrigin == GridPoint(x: 11, y: 1))
        #expect(arrival.focus == nil)
        #expect(arrival.unitSpeed == 4)
        #expect(arrival.durationTicks == 50)
        #expect(session.snapshot.missionRuntime?.state == 1)

        session = try restoreThroughJSON(session.snapshot)
        #expect(session.pendingPresentation?.scene == arrival)
        #expect(session.snapshot.units.count == 6)
    }

    @Test func openingDialogueAndObjectiveFollowEscortDutyOrder() throws {
        var session = try makeSession()
        session.enableStoryPresentations()
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 256)
        #expect(session.storyPortraitID == 5)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.pendingPresentation?.scene?.durationTicks == 5)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 257)
        #expect(session.storyPortraitID == 0)

        try session.advanceStory()
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 133)
        #expect(session.snapshot.missionRuntime?.state == 4)
        session.completePresentation()
        #expect(session.isInputEnabled)
    }

    @Test func fourAmbushWavesUseOriginalTriggersUnitsAndStaggering() throws {
        var first = try triggeredSession(state: 4, actorPoint: GridPoint(x: 8, y: 1))
        #expect(first.pendingPresentation?.scene?.kind == .pause)
        #expect(first.pendingPresentation?.scene?.durationTicks == 5)
        first.completePresentation()
        #expect(first.pendingPresentation?.scene?.kind == .camera)
        #expect(first.pendingPresentation?.scene?.focus == GridPoint(x: 4, y: 4))
        first.completePresentation()
        let firstWave = try #require(first.pendingPresentation?.scene)
        #expect(firstWave.movements.map(\.unitID) == [6, 7, 8])
        #expect(firstWave.movements.map(\.delayTicks) == [nil, 10, 20])
        #expect(firstWave.movements.map { $0.route.last } == [
            GridPoint(x: 4, y: 1), GridPoint(x: 5, y: 2), GridPoint(x: 4, y: 3),
        ])
        let restored = try restoreThroughJSON(first.snapshot)
        #expect(restored.pendingPresentation?.scene == firstWave)
        #expect(restored.snapshot.units.count == 9)

        var second = try triggeredSession(state: 11, actorPoint: GridPoint(x: 10, y: 7))
        #expect(second.pendingPresentation?.scene?.focus == GridPoint(x: 6, y: 10))
        second.completePresentation()
        let secondWave = try #require(second.pendingPresentation?.scene)
        #expect(secondWave.movements.map(\.delayTicks) == [nil, 10, 25])
        #expect(secondWave.movements.map { $0.route.last } == [
            GridPoint(x: 5, y: 10), GridPoint(x: 7, y: 8), GridPoint(x: 7, y: 9),
        ])

        var third = try triggeredSession(state: 15, actorPoint: GridPoint(x: 8, y: 6))
        #expect(third.pendingPresentation?.scene?.focus == GridPoint(x: 12, y: 5))
        third.completePresentation()
        let thirdWave = try #require(third.pendingPresentation?.scene)
        #expect(thirdWave.movements.map(\.delayTicks) == [nil, 15, 25])
        #expect(thirdWave.movements.map { $0.route.last } == [
            GridPoint(x: 12, y: 7), GridPoint(x: 12, y: 6), GridPoint(x: 12, y: 5),
        ])

        var fourth = try triggeredSession(state: 19, actorPoint: GridPoint(x: 15, y: 8))
        #expect(fourth.pendingPresentation?.scene?.focus == GridPoint(x: 18, y: 8))
        fourth.completePresentation()
        let fourthWave = try #require(fourth.pendingPresentation?.scene)
        #expect(fourthWave.movements.map(\.delayTicks) == [nil, 10, 20, 30])
        #expect(fourthWave.movements.map { $0.route.last } == [
            GridPoint(x: 16, y: 10), GridPoint(x: 17, y: 10),
            GridPoint(x: 18, y: 10), GridPoint(x: 18, y: 9),
        ])
        fourth.completePresentation()
        #expect(fourth.snapshot.currentStoryMessageID == 259)
        #expect(fourth.storyPortraitID == 0)
    }

    @Test func crystalDeathFailsAndEitherSourceCompletionConditionWins() throws {
        var deadSnapshot = try activeSession().snapshot
        deadSnapshot.missionRuntime?.state = 4
        deadSnapshot.missionRuntime?.active = true
        deadSnapshot.units[2].hitPoints = 0
        var dead = try restore(deadSnapshot)
        try dead.selectUnit(id: 0)
        #expect(dead.snapshot.phase == .defeat)

        var eliminationSnapshot = try activeSession().snapshot
        eliminationSnapshot.missionRuntime?.state = 25
        eliminationSnapshot.missionRuntime?.active = true
        var elimination = try restore(eliminationSnapshot)
        try elimination.selectUnit(id: 0)
        #expect(elimination.pendingPresentation?.scene?.kind == .pause)
        #expect(elimination.pendingPresentation?.scene?.durationTicks == 10)
        elimination.completePresentation()
        #expect(elimination.snapshot.phase == .victory)

        var escapeSnapshot = try activeSession().snapshot
        escapeSnapshot.missionRuntime?.state = 25
        escapeSnapshot.missionRuntime?.active = true
        escapeSnapshot.units[2].x = 15
        escapeSnapshot.units[2].y = 11
        escapeSnapshot.units[2].hasActed = true
        escapeSnapshot.units.append(.init(id: 100, type: 5, fraction: 2, x: 18, y: 8))
        var escape = try restore(escapeSnapshot)
        try escape.selectUnit(id: 0)
        #expect(escape.pendingPresentation?.scene?.kind == .pause)
        escape.completePresentation()
        #expect(escape.snapshot.phase == .victory)
        #expect(escape.unit(id: 100)?.isAlive == true)
    }

    private func triggeredSession(state: Int, actorPoint: GridPoint) throws -> CampaignSession {
        var snapshot = try activeSession().snapshot
        snapshot.missionRuntime?.state = state
        snapshot.missionRuntime?.active = true
        snapshot.phase = .playerTurn
        snapshot.selection = nil
        snapshot.pendingPresentations = nil
        snapshot.storyMessages = []
        snapshot.storyIndex = -1
        snapshot.units[5].x = actorPoint.x
        snapshot.units[5].y = actorPoint.y
        snapshot.units[5].hasActed = true
        snapshot.units[0].hasActed = false
        var session = try restore(snapshot)
        try session.selectUnit(id: 0)
        return session
    }

    private func activeSession() throws -> CampaignSession {
        var session = try makeSession()
        session.enableStoryPresentations()
        try session.advanceStory()
        session.completePresentation()
        try session.advanceStory()
        session.completePresentation()
        try session.advanceStory()
        session.completePresentation()
        session.completePresentation()
        return session
    }

    private func makeSession() throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m4"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), seed: 17)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m4"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }

    private func restoreThroughJSON(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try restore(decoded)
    }
}
