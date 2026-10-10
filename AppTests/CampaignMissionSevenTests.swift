import AECore
import AEContent
import Foundation
import Testing

struct CampaignMissionSevenTests {
    @Test func initializationMatchesHeavensFurySource() throws {
        let session = try makeSession()
        #expect(session.map.id == "m7")
        #expect(session.map.width == 14)
        #expect(session.map.height == 18)
        #expect(session.money(for: 1) == 800)
        #expect(session.money(for: 2) == 200)
        #expect(session.allowedUnitType == 8)
        #expect(session.objectiveTextID == 136)
        #expect(session.snapshot.units.count == 19)

        let references = try #require(session.snapshot.missionRuntime?.references)
        #expect(session.unit(id: try #require(references["king1"]))?.position == GridPoint(x: 8, y: 15))
        #expect(session.unit(id: try #require(references["king1"]))?.commanderVariant == 0)
        #expect(session.unit(id: try #require(references["valadorn"]))?.commanderVariant == 2)
        #expect(session.unit(id: try #require(references["saeth"]))?.commanderVariant == 3)
        #expect(session.unit(id: try #require(references["crystalWest"]))?.position == GridPoint(x: 5, y: 2))
        #expect(session.unit(id: try #require(references["crystalCenter"]))?.position == GridPoint(x: 7, y: 3))
        #expect(session.unit(id: try #require(references["crystalEast"]))?.position == GridPoint(x: 9, y: 2))
    }

    @Test func threeCrystalsAndSaethEnterTheCitadelInSourceOrder() throws {
        var snapshot = try makeSession().snapshot
        snapshot.phase = .opening
        snapshot.storyPresentationsEnabled = true
        snapshot.missionRuntime?.state = 6
        var session = try restore(snapshot)

        try session.advanceStory()
        let west = try #require(session.pendingPresentation?.scene)
        #expect(west.movements.first?.route == [
            GridPoint(x: 5, y: 2), GridPoint(x: 6, y: 2), GridPoint(x: 7, y: 2),
        ])
        session.completePresentation()

        let center = try #require(session.pendingPresentation?.scene)
        #expect(center.movements.first?.route == [GridPoint(x: 7, y: 3), GridPoint(x: 7, y: 2)])
        #expect(session.unit(id: try reference("crystalWest", in: session))?.removedFromMap == true)
        session.completePresentation()

        let east = try #require(session.pendingPresentation?.scene)
        #expect(east.movements.first?.route == [
            GridPoint(x: 9, y: 2), GridPoint(x: 8, y: 2), GridPoint(x: 7, y: 2),
        ])
        session.completePresentation()

        let saeth = try #require(session.pendingPresentation?.scene)
        #expect(saeth.movements.first?.route == [
            GridPoint(x: 7, y: 4), GridPoint(x: 7, y: 3), GridPoint(x: 7, y: 2),
        ])
        #expect(saeth.durationTicks == 20)
        #expect(session.unit(id: try reference("crystalCenter", in: session))?.removedFromMap == true)
        #expect(session.unit(id: try reference("crystalEast", in: session))?.removedFromMap == true)
    }

    @Test func saethUsesRecoverableHeavenFuryInsteadOfNormalAI() throws {
        var snapshot = try activeBattleSnapshot()
        let saethID = try #require(snapshot.missionRuntime?.references["saeth"])
        let kingID = try #require(snapshot.missionRuntime?.references["king1"])
        for index in snapshot.units.indices where snapshot.units[index].id != saethID && snapshot.units[index].id != kingID {
            snapshot.units[index].removedFromMap = true
        }
        if let index = snapshot.units.firstIndex(where: { $0.id == kingID }) {
            snapshot.units[index].x = 9
            snapshot.units[index].y = 15
        }
        snapshot.moneyByFraction?[2] = 0
        var session = try restore(snapshot)

        let actions = try session.endPlayerTurn(recordPresentation: true)
        let action = try #require(actions.first)
        #expect(action.unitID == saethID)
        #expect(action.origin == action.destination)
        #expect(action.targetID == kingID)
        let scene = try #require(session.snapshot.pendingPresentations?.compactMap(\.scene).first(where: { $0.kind == .heavenFury }))
        let damage = try #require(scene.damage)
        #expect((25...49).contains(damage))
        #expect(scene.textID == 280)
        #expect(scene.unitID == kingID)
        #expect(session.unit(id: kingID)?.hitPoints == 100 - damage)

        let restored = try restoreThroughJSON(session.snapshot)
        #expect(restored.unit(id: kingID)?.hitPoints == 100 - damage)
        #expect(restored.snapshot.pendingPresentations?.compactMap(\.scene).contains(where: { $0.kind == .heavenFury }) == true)
    }

    @Test func heavenFuryKilledCommanderKeepsOriginalCastleRevivalFlow() throws {
        var snapshot = try activeBattleSnapshot()
        let saethID = try #require(snapshot.missionRuntime?.references["saeth"])
        let kingID = try #require(snapshot.missionRuntime?.references["king1"])
        for index in snapshot.units.indices where snapshot.units[index].id != saethID && snapshot.units[index].id != kingID {
            snapshot.units[index].removedFromMap = true
        }
        let kingIndex = try #require(snapshot.units.firstIndex(where: { $0.id == kingID }))
        snapshot.units[kingIndex].hitPoints = 1
        snapshot.moneyByFraction?[2] = 0
        var session = try restore(snapshot)

        _ = try session.endPlayerTurn(recordPresentation: true)
        #expect(session.snapshot.phase == .playerTurn)
        #expect(session.unit(id: kingID)?.hitPoints == 0)
        #expect(session.unit(id: kingID)?.position == GridPoint(x: -10, y: -10))
        #expect(session.unit(id: kingID)?.revivalCost != nil)

        session = try restoreThroughJSON(session.snapshot)
        while session.pendingPresentation != nil { session.completePresentation() }
        let castle = GridPoint(x: 8, y: 15)
        let revival = try #require(session.recruitmentOptions(at: castle).first(where: { $0.commanderID == kingID }))
        #expect(revival.isAvailable)
        let revivedID = try session.recruit(type: 9, commanderID: kingID, at: castle)
        #expect(revivedID == kingID)
        #expect(session.unit(id: kingID)?.position == castle)
        #expect(session.unit(id: kingID)?.hitPoints == 100)
    }

    @Test func saethDeathRebuildsTheFinalTableauAndCompletesWithoutMissionCompleteScreen() throws {
        var snapshot = try activeBattleSnapshot()
        let saethID = try #require(snapshot.missionRuntime?.references["saeth"])
        if let index = snapshot.units.firstIndex(where: { $0.id == saethID }) {
            snapshot.units[index].hitPoints = 0
        }
        var session = try restore(snapshot)
        try session.selectUnit(id: try reference("king1", in: session))
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.snapshot.missionRuntime?.state == 21)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .fadeToBlack)
        session.completePresentation()

        #expect(session.snapshot.missionRuntime?.state == 23)
        #expect(session.snapshot.units.filter(\.isAlive).count == 5)
        let endingSaethID = try reference("endingSaeth", in: session)
        let endingGalamarID = try reference("endingGalamar", in: session)
        let endingValadornID = try reference("endingValadorn", in: session)
        #expect(session.unit(id: endingSaethID)?.position == GridPoint(x: 7, y: 2))
        #expect(session.unit(id: endingSaethID)?.commanderVariant == 3)
        #expect(session.unit(id: endingGalamarID)?.position == GridPoint(x: 6, y: 3))
        #expect(session.unit(id: endingValadornID)?.position == GridPoint(x: 8, y: 3))
        #expect(session.pendingPresentation?.scene?.kind == .pause)

        var completion = session.snapshot
        completion.pendingPresentations = nil
        completion.phase = .opening
        completion.missionRuntime?.state = 34
        completion.missionRuntime?.active = false
        session = try restore(completion)
        try session.advanceStory()
        #expect(session.snapshot.phase == .victory)
        #expect(session.snapshot.missionRuntime?.state == -1)
    }

    private func activeBattleSnapshot() throws -> CampaignSnapshot {
        var snapshot = try makeSession().snapshot
        snapshot.phase = .playerTurn
        snapshot.activeFraction = 1
        snapshot.storyPresentationsEnabled = true
        snapshot.missionRuntime?.state = 20
        snapshot.missionRuntime?.active = true
        return snapshot
    }

    private func reference(_ key: String, in session: CampaignSession) throws -> Int {
        try #require(session.snapshot.missionRuntime?.references[key])
    }

    private func makeSession() throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m7"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), seed: 23)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m7"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }

    private func restoreThroughJSON(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try restore(decoded)
    }
}
