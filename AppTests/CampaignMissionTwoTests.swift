import AECore
import AEContent
import Foundation
import Testing

struct CampaignMissionTwoTests {
    @Test func initializationAndOpeningRoutesMatchPathOfShadows() throws {
        var session = try makeSession()
        #expect(session.map.id == "m2")
        #expect(session.money(for: 1) == 0)
        #expect(session.money(for: 2) == 0)
        #expect(session.allowedUnitType == 0)
        #expect(session.objectiveTextID == 131)
        #expect(session.snapshot.missionRuntime?.references["king1"] == 0)
        #expect(session.snapshot.missionRuntime?.references["escortLeader"] == 1)
        #expect(session.snapshot.missionRuntime?.references["escortArcher"] == 2)
        #expect(session.snapshot.missionRuntime?.references["escortFollower"] == 3)

        session = try restore(session.snapshot)
        #expect(session.snapshot.missionRuntime?.state == 0)
        #expect(session.pendingPresentation == nil)
        session.enableStoryPresentations()
        try session.advanceStory()

        let arrival = try #require(session.pendingPresentation?.scene)
        #expect(arrival.kind == .arrival)
        #expect(arrival.movements.map(\.unitID) == [0, 1, 2, 3])
        #expect(arrival.movements.map { $0.route.last } == [
            GridPoint(x: 8, y: 14), GridPoint(x: 7, y: 14),
            GridPoint(x: 7, y: 15), GridPoint(x: 8, y: 15),
        ])
        #expect(arrival.unitSpeed == 4)
        #expect(arrival.durationTicks == 38)
        #expect(arrival.cameraOrigin == GridPoint(x: 8, y: 16))
        #expect(arrival.focus == nil)
        #expect(session.snapshot.missionRuntime?.state == 2)

        let unitCount = session.snapshot.units.count
        session = try restore(session.snapshot)
        #expect(session.pendingPresentation?.scene == arrival)
        #expect(session.snapshot.units.count == unitCount)
        #expect(session.unit(id: 0)?.position == GridPoint(x: 8, y: 14))
    }

    @Test func openingDialogueTutorialAndObjectiveOrderMatchSourceCases() throws {
        var session = try makeSession()
        session.enableStoryPresentations()
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 241)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 242)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 243)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.pendingCampaignHelpID == 210)

        session.dismissCampaignHelp()
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 131)
        session.completePresentation()
        #expect(session.isInputEnabled)
        #expect(session.snapshot.missionRuntime?.state == 7)
    }

    @Test func corpseTutorialAmbushAndElementalAllianceAreRecoverable() throws {
        var corpseSnapshot = try activeSnapshot(state: 7)
        corpseSnapshot.units[4].hitPoints = 0
        corpseSnapshot.units[4].corpseTransitionsRemaining = 3
        var session = try restore(corpseSnapshot)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.pendingCampaignHelpID == 211)
        #expect(session.snapshot.missionRuntime?.state == 8)

        var ambushSnapshot = try activeSnapshot(state: 8)
        ambushSnapshot.units[1].x = 4
        ambushSnapshot.units[1].y = 11
        session = try restore(ambushSnapshot)
        session.enableStoryPresentations()
        try session.selectUnit(id: 1)
        try session.endUnitAction(unitID: 1)
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.snapshot.missionRuntime?.state == 9)

        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.movements.map(\.unitID).count == 2)
        #expect(session.snapshot.missionRuntime?.state == 10)
        let firstWaveCount = session.snapshot.units.count
        session = try restore(session.snapshot)
        #expect(session.snapshot.units.count == firstWaveCount)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.movements.count == 1)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.movements.count == 3)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 244)

        try session.advanceStory()
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .appearance)
        #expect(session.snapshot.units.filter { $0.type == 2 && $0.fraction == 2 && $0.isAlive }.count == 3)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 245)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 246)
        #expect(session.storyPortraitID == nil)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 247)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .message)
        #expect(session.pendingPresentation?.scene?.textID == 248)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .appearance)
        #expect(session.snapshot.units.filter { $0.type == 2 && $0.fraction == 1 && $0.isAlive }.count == 3)
        #expect(session.snapshot.units.filter { $0.type == 2 && $0.fraction == 2 && $0.isAlive }.isEmpty)

        session = try restore(session.snapshot)
        #expect(session.snapshot.units.filter { $0.type == 2 && $0.fraction == 1 && $0.isAlive }.count == 3)
        session.completePresentation()
        #expect(session.snapshot.pendingCampaignHelpID == 209)
        #expect(session.snapshot.missionRuntime?.state == 21)
        session.dismissCampaignHelp()
        #expect(session.isInputEnabled)
    }

    @Test func missionTwoVictoryAndGalamarDefeatUseOriginalConditions() throws {
        var victory = try activeSnapshot(state: 21)
        for index in victory.units.indices where victory.units[index].fraction == 2 {
            victory.units[index].removedFromMap = true
        }
        var session = try restore(victory)
        session.enableStoryPresentations()
        try session.selectUnit(id: 0)
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.snapshot.missionRuntime?.state == 22)
        session.completePresentation()
        #expect(session.snapshot.phase == .victory)

        var defeat = try activeSnapshot(state: 8)
        defeat.units[0].hitPoints = 0
        defeat.units[0].corpseTransitionsRemaining = 3
        session = try restore(defeat)
        try session.selectUnit(id: 1)
        #expect(session.snapshot.phase == .defeat)
    }

    private func makeSession() throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m2"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), seed: 11)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try CampaignSession(map: GameResources.campaignMap(named: "m2"),
                                   profiles: GameResources.campaignUnitProfiles(),
                                   rules: GameResources.campaignRuleset(), snapshot: decoded)
    }

    private func activeSnapshot(state: Int) throws -> CampaignSnapshot {
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
            state: state,
            active: true,
            references: ["king1": 0, "escortLeader": 1, "escortArcher": 2, "escortFollower": 3],
            objectiveTextID: 131
        )
        return snapshot
    }
}
