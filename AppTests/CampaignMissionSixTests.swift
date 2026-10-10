import AECore
import AEContent
import Foundation
import Testing

struct CampaignMissionSixTests {
    @Test func initializationAndFollowerArrivalMatchRendezvousSource() throws {
        var session = try makeSession()
        #expect(session.map.id == "m6")
        #expect(session.map.width == 16)
        #expect(session.map.height == 18)
        #expect(session.money(for: 1) == 400)
        #expect(session.money(for: 2) == 600)
        #expect(session.allowedUnitType == 8)
        #expect(session.objectiveTextID == 135)
        #expect(session.snapshot.units.count == 5)
        #expect(session.snapshot.missionRuntime?.references["king1"] == 0)
        #expect(session.snapshot.missionRuntime?.references["crystal"] == 4)
        #expect(session.unit(id: 0)?.position == GridPoint(x: 13, y: 0))
        #expect(session.unit(id: 4)?.position == GridPoint(x: 13, y: -1))

        session.enableStoryPresentations()
        try session.advanceStory()
        let arrival = try #require(session.pendingPresentation?.scene)
        #expect(arrival.kind == .arrival)
        #expect(arrival.cameraOrigin == GridPoint(x: 13, y: 0))
        #expect(arrival.focus == GridPoint(x: 14, y: 3))
        #expect(arrival.movements.map(\.unitID) == [0, 1, 2, 3, 4])
        #expect(arrival.movements.map(\.delayTicks) == [nil, nil, 6, 12, 18])
        #expect(arrival.movements.map { $0.route.last } == [
            GridPoint(x: 14, y: 3), GridPoint(x: 14, y: 2), GridPoint(x: 14, y: 1),
            GridPoint(x: 14, y: 0), GridPoint(x: 13, y: 0),
        ])
        #expect(arrival.unitSpeed == 4)
        #expect(arrival.durationTicks == 34)

        session = try restoreThroughJSON(session.snapshot)
        #expect(session.pendingPresentation?.scene == arrival)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 262)
        #expect(session.storyPortraitID == 5)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.pendingPresentation?.scene?.durationTicks == 5)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 263)
        #expect(session.storyPortraitID == 0)

        try session.advanceStory()
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 135)
        #expect(session.snapshot.missionRuntime?.state == 4)
        session.completePresentation()
        #expect(session.isInputEnabled)
    }

    @Test func turnTwoAmbushUsesOriginalUnitsDestinationsAndStaggering() throws {
        var snapshot = try activeSession().snapshot
        snapshot.currentTurn = 2
        var session = try restore(snapshot)

        try session.selectUnit(id: 0)
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 11, y: 7))
        #expect(session.pendingPresentation?.scene?.durationTicks == 15)
        #expect(session.snapshot.missionRuntime?.state == 5)

        session.completePresentation()
        let ambush = try #require(session.pendingPresentation?.scene)
        #expect(ambush.kind == .arrival)
        #expect(ambush.movements.map(\.delayTicks) == [nil, 10, 20, 30])
        #expect(ambush.movements.map { $0.route.last } == [
            GridPoint(x: 14, y: 7), GridPoint(x: 13, y: 7),
            GridPoint(x: 12, y: 7), GridPoint(x: 13, y: 8),
        ])
        #expect(ambush.movements.compactMap { session.unit(id: $0.unitID)?.type } == [5, 0, 3, 1])
        #expect(ambush.durationTicks == 50)
        #expect(session.snapshot.missionRuntime?.state == 9)

        session = try restoreThroughJSON(session.snapshot)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 264)
        #expect(session.storyPortraitID == 5)
        #expect(session.snapshot.missionRuntime?.state == 10)
        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 14, y: 3))
        session.completePresentation()
        #expect(session.isInputEnabled)
    }

    @Test func crystalAbductionRemovesEscortWithoutTriggeringDefeat() throws {
        var failureSnapshot = try activeSession().snapshot
        failureSnapshot.missionRuntime?.state = 10
        if let crystal = failureSnapshot.missionRuntime?.references["crystal"],
           let index = failureSnapshot.units.firstIndex(where: { $0.id == crystal }) {
            failureSnapshot.units[index].hitPoints = 0
        }
        var failed = try restore(failureSnapshot)
        try failed.selectUnit(id: 0)
        #expect(failed.snapshot.phase == .defeat)

        var snapshot = try activeSession().snapshot
        snapshot.missionRuntime?.state = 10
        if let soldier = snapshot.missionRuntime?.references["escortSoldier"],
           let index = snapshot.units.firstIndex(where: { $0.id == soldier }) {
            snapshot.units[index].x = 9
            snapshot.units[index].hasActed = true
        }
        appendRedUnit(to: &snapshot, at: GridPoint(x: 14, y: 7))
        var session = try restore(snapshot)

        try session.selectUnit(id: 0)
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.pendingPresentation?.scene?.durationTicks == 10)
        #expect(session.snapshot.missionRuntime?.state == 11)
        session.completePresentation()

        let crystalID = try #require(session.snapshot.missionRuntime?.references["crystal"])
        let dragonID = try #require(session.snapshot.missionRuntime?.references["abductorDragon"])
        #expect(session.unit(id: dragonID)?.position == GridPoint(x: 16, y: 0))
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 15, y: 0))
        session.completePresentation()

        #expect(session.pendingPresentation?.scene?.kind == .arrival)
        #expect(session.pendingPresentation?.scene?.movements.first?.route.last == GridPoint(x: 13, y: 0))
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 265)
        #expect(session.storyPortraitID == 5)
        try session.advanceStory()
        #expect(session.snapshot.currentStoryMessageID == 266)
        #expect(session.storyPortraitID == 0)
        try session.advanceStory()

        let exit = try #require(session.pendingPresentation?.scene)
        #expect(exit.kind == .arrival)
        #expect(exit.movements.map(\.unitID) == [dragonID, crystalID])
        #expect(exit.movements.map { $0.route.last } == [GridPoint(x: -1, y: 0), GridPoint(x: -1, y: 0)])
        session = try restoreThroughJSON(session.snapshot)
        session.completePresentation()
        #expect(session.unit(id: crystalID)?.removedFromMap == true)
        #expect(session.unit(id: dragonID)?.removedFromMap == true)
        #expect(session.snapshot.phase != .defeat)
        #expect(session.snapshot.missionRuntime?.state == 18)
        #expect(session.pendingPresentation?.scene?.kind == .camera)

        session.completePresentation()
        #expect(session.snapshot.missionRuntime?.state == 19)
        #expect(session.pendingPresentation?.scene?.movements.count == 8)
        #expect(session.pendingPresentation?.scene?.movements.map { $0.route.last } == [
            GridPoint(x: 0, y: 8), GridPoint(x: 3, y: 8), GridPoint(x: 1, y: 10),
            GridPoint(x: 4, y: 8), GridPoint(x: 2, y: 10), GridPoint(x: 2, y: 9),
            GridPoint(x: 4, y: 9), GridPoint(x: 5, y: 10),
        ])
    }

    @Test func enemyAndValadornReinforcementsLeadToUpdatedObjective() throws {
        var snapshot = try makeSession().snapshot
        snapshot.missionRuntime?.state = 18
        snapshot.missionRuntime?.active = false
        snapshot.phase = .opening
        snapshot.storyPresentationsEnabled = true
        if let crystal = snapshot.missionRuntime?.references["crystal"],
           let index = snapshot.units.firstIndex(where: { $0.id == crystal }) {
            snapshot.units[index].removedFromMap = true
        }
        var session = try restore(snapshot)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.movements.count == 8)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 267)
        #expect(session.storyPortraitID == 3)
        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 13, y: 14))
        session.completePresentation()

        let southern = try #require(session.pendingPresentation?.scene)
        #expect(southern.movements.map(\.delayTicks) == [nil, 5, 10, 15])
        #expect(southern.movements.compactMap { session.unit(id: $0.unitID)?.type } == [0, 6, 2, 3])
        #expect(southern.movements.map { $0.route.last } == [
            GridPoint(x: 12, y: 14), GridPoint(x: 14, y: 14),
            GridPoint(x: 13, y: 12), GridPoint(x: 13, y: 15),
        ])
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 268)
        #expect(session.storyPortraitID == 5)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 269)
        #expect(session.storyPortraitID == 0)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 13, y: 17))
        session.completePresentation()

        let reinforcements = try #require(session.pendingPresentation?.scene)
        #expect(reinforcements.movements.count == 5)
        #expect(reinforcements.movements.compactMap { session.unit(id: $0.unitID)?.type } == [9, 6, 8, 4, 1])
        #expect(reinforcements.movements.map { $0.route.last } == [
            GridPoint(x: 13, y: 16), GridPoint(x: 12, y: 16), GridPoint(x: 14, y: 16),
            GridPoint(x: 13, y: 17), GridPoint(x: 12, y: 17),
        ])
        let valadornID = try #require(session.snapshot.missionRuntime?.references["valadorn"])
        #expect(session.unit(id: valadornID)?.commanderVariant == 2)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 270)
        #expect(session.storyPortraitID == 1)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 13, y: 0))
        session.completePresentation()
        #expect(session.snapshot.pendingCampaignHelpID == 214)
        #expect(session.snapshot.missionRuntime?.state == 32)

        session.dismissCampaignHelp()
        #expect(session.objectiveTextID == 138)
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 138)
        #expect(session.snapshot.missionRuntime?.state == 33)
        session = try restoreThroughJSON(session.snapshot)
        #expect(session.objectiveTextID == 138)
        session.completePresentation()
        #expect(session.isInputEnabled)
    }

    @Test func finalVictoryRequiresRedArmyAndRedCastlesWhileMissingCrystalIsAllowed() throws {
        var armyOnly = try finalStageSnapshot()
        armyOnly.tileOverrides?[8 * 16] = 42
        var session = try restore(armyOnly)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.missionRuntime?.state == 33)
        #expect(session.snapshot.phase != .victory)

        var castleOnly = try finalStageSnapshot()
        appendRedUnit(to: &castleOnly, at: GridPoint(x: 3, y: 8))
        session = try restore(castleOnly)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.missionRuntime?.state == 33)
        #expect(session.snapshot.phase != .victory)

        var complete = try finalStageSnapshot()
        if let crystal = complete.missionRuntime?.references["crystal"],
           let index = complete.units.firstIndex(where: { $0.id == crystal }) {
            complete.units[index].hitPoints = 0
            complete.units[index].removedFromMap = true
        }
        session = try restore(complete)
        try session.selectUnit(id: 0)
        #expect(session.snapshot.phase != .defeat)
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.pendingPresentation?.scene?.durationTicks == 10)
        #expect(session.snapshot.missionRuntime?.state == 34)
        session.completePresentation()
        #expect(session.snapshot.phase == .victory)
        #expect(session.snapshot.missionRuntime?.state == -1)
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

    private func finalStageSnapshot() throws -> CampaignSnapshot {
        var snapshot = try makeSession().snapshot
        snapshot.missionRuntime?.state = 33
        snapshot.missionRuntime?.active = true
        snapshot.missionRuntime?.objectiveTextID = 138
        snapshot.phase = .playerTurn
        snapshot.storyPresentationsEnabled = true
        snapshot.tileOverrides = [:]
        return snapshot
    }

    private func appendRedUnit(to snapshot: inout CampaignSnapshot, at point: GridPoint) {
        snapshot.units.append(.init(id: snapshot.nextUnitID, type: 0, fraction: 2, x: point.x, y: point.y))
        snapshot.nextUnitID += 1
    }

    private func makeSession() throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m6"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), seed: 19)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m6"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }

    private func restoreThroughJSON(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try restore(decoded)
    }
}
