import AECore
import AEContent
import Foundation
import Testing

struct CampaignMissionOneTests {
    @Test func initializationAndRestorePreserveTheOriginalMissionState() throws {
        var session = try makeSession()
        #expect(session.map.id == "m1")
        #expect(session.money(for: 1) == 300)
        #expect(session.money(for: 2) == 50)
        #expect(session.allowedUnitType == 1)
        #expect(session.objectiveTextID == 130)
        #expect(session.snapshot.missionRuntime?.state == 0)
        #expect(session.unit(id: 0)?.resolvedCommanderVariant == 2)
        #expect(session.snapshot.missionRuntime?.references["king1"] == 0)
        #expect(session.snapshot.missionRuntime?.references["king2"] == 3)

        session = try restore(session.snapshot)
        #expect(session.snapshot.missionRuntime?.state == 0)
        #expect(session.pendingPresentation == nil)
        #expect(session.unit(id: 0)?.position == GridPoint(x: 8, y: 11))

        session.enableStoryPresentations()
        try session.advanceStory()
        let arrival = try #require(session.pendingPresentation?.scene)
        #expect(arrival.kind == .arrival)
        #expect(arrival.movements.map(\.unitID) == [1, 0, 2])
        #expect(arrival.movements.map { $0.route.last } == [
            GridPoint(x: 7, y: 10), GridPoint(x: 8, y: 9), GridPoint(x: 9, y: 10),
        ])
        #expect(arrival.unitSpeed == 4)
        #expect(arrival.cameraStep == 2)
    }

    @Test func openingEscortChainAndTutorialBoundaryMatchMissionOne() throws {
        var session = try makeSession()
        session.enableStoryPresentations()
        try session.advanceStory()

        session.completePresentation() // arrival
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 231)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 232)
        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 233)
        try session.advanceStory()
        session.completePresentation() // camera to the temple

        let chain = try #require(session.pendingPresentation?.scene)
        #expect(chain.kind == .arrival)
        #expect(chain.unitSpeed == 2)
        #expect(chain.movements.map(\.delayTicks) == [nil, 12, 24])
        #expect(chain.movements.map { $0.route.last } == [
            GridPoint(x: 6, y: -1), GridPoint(x: 6, y: 0), GridPoint(x: 6, y: 1),
        ])
        let committedCount = session.snapshot.units.count
        session = try restore(session.snapshot)
        #expect(session.snapshot.units.count == committedCount)
        #expect(session.pendingPresentation?.scene == chain)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 234)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .arrival)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .hit)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 235)
        try session.advanceStory()
        session.completePresentation() // pause
        #expect(session.pendingPresentation?.scene?.kind == .disappearance)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 236)
        try session.advanceStory()
        #expect(session.snapshot.currentStoryMessageID == 237)
        try session.advanceStory()
        session.completePresentation() // neutral castle camera
        #expect(session.snapshot.currentStoryMessageID == 238)
        try session.advanceStory()
        session.completePresentation() // return to Valadorn
        #expect(session.snapshot.pendingCampaignHelpID == 204)
        session.dismissCampaignHelp()
        #expect(session.snapshot.pendingCampaignHelpID == 205)
        session.dismissCampaignHelp()
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 130)
        session.completePresentation()

        #expect(session.isInputEnabled)
        #expect(session.snapshot.missionRuntime?.state == 20)
        #expect(session.unit(id: 0)?.position == GridPoint(x: 8, y: 9))
        #expect(session.snapshot.units.contains { $0.fraction == 2 && $0.type == 0 && $0.position == GridPoint(x: 6, y: 1) && $0.isAlive })
        #expect(session.snapshot.units.filter { $0.removedFromMap == true }.count == 3)
    }

    @Test func occupationDeathAndVictoryChecksUseMissionOneConditions() throws {
        var occupation = try activeSnapshot(state: 20)
        occupation.missionRuntime?.blueOccupationObserved = true
        var session = try restore(occupation)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.pendingCampaignHelpID == 206)
        session.dismissCampaignHelp()
        #expect(session.snapshot.pendingCampaignHelpID == 207)

        var death = try activeSnapshot(state: 22)
        death.missionRuntime?.commanderDeathObserved = true
        session = try restore(death)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.pendingCampaignHelpID == 208)

        var blocked = try activeSnapshot(state: 23, removeRedUnits: true)
        session = try restore(blocked)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.missionRuntime?.state == 23)
        #expect(session.snapshot.phase == .playerTurn)

        blocked.tileOverrides = [3 * 15 + 12: 40]
        session = try restore(blocked)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.missionRuntime?.state == 26)
        #expect(session.snapshot.currentStoryMessageID == 239)
    }

    private func makeSession() throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m1"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), seed: 7)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try CampaignSession(map: GameResources.campaignMap(named: "m1"),
                                   profiles: GameResources.campaignUnitProfiles(),
                                   rules: GameResources.campaignRuleset(), snapshot: decoded)
    }

    private func activeSnapshot(state: Int, removeRedUnits: Bool = false) throws -> CampaignSnapshot {
        let map = try GameResources.campaignMap(named: "m1")
        var snapshot = CampaignSnapshot(
            mapID: map.id,
            units: map.units.map {
                CampaignUnitState(id: $0.index, type: $0.type, fraction: $0.fraction,
                                  x: $0.x, y: $0.y)
            },
            phase: .playerTurn,
            randomState: JavaRandom(seed: 7).state
        )
        if removeRedUnits {
            for index in snapshot.units.indices where snapshot.units[index].fraction == 2 {
                snapshot.units[index].removedFromMap = true
            }
        }
        snapshot.units[0].commanderVariant = 2
        snapshot.moneyByFraction = [1: 300, 2: 50]
        snapshot.allowedUnitType = 1
        snapshot.missionRuntime = CampaignMissionRuntime(
            state: state,
            active: true,
            references: ["king1": 0, "king2": 3],
            objectiveTextID: 130
        )
        return snapshot
    }
}
