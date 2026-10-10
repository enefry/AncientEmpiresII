import AECore
import AEContent
import Foundation
import Testing

struct CampaignMissionFiveTests {
    @Test func initializationMatchesNorthboundSourceData() throws {
        let session = try makeSession()
        #expect(session.map.id == "m5")
        #expect(session.map.width == 12)
        #expect(session.map.height == 20)
        #expect(session.money(for: 1) == 600)
        #expect(session.money(for: 2) == 600)
        #expect(session.allowedUnitType == 7)
        #expect(session.objectiveTextID == 134)
        #expect(session.snapshot.units.count == 13)
        #expect(session.snapshot.missionRuntime?.references["king1"] == 0)
        #expect(session.unit(id: 0)?.position == GridPoint(x: 5, y: 17))
        #expect(session.unit(id: 0)?.commanderVariant == 2)
        #expect(session.map.buildings.contains {
            $0.x == 5 && $0.y == 5 && $0.fraction == 2 && $0.terrain == 9
        })
    }

    @Test func openingUsesSouthCameraDialogueAndObjectiveOrder() throws {
        var session = try makeSession()
        session.enableStoryPresentations()
        try session.advanceStory()

        let wait = try #require(session.pendingPresentation?.scene)
        #expect(wait.kind == .pause)
        #expect(wait.focus == GridPoint(x: 5, y: 17))
        #expect(wait.cameraStep == 4)
        #expect(wait.durationTicks == 10)
        #expect(session.snapshot.missionRuntime?.state == 1)

        session = try restoreThroughJSON(session.snapshot)
        #expect(session.pendingPresentation?.scene == wait)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 260)
        #expect(session.storyPortraitID == 1)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 134)
        #expect(session.snapshot.missionRuntime?.state == 3)
        session.completePresentation()
        #expect(session.isInputEnabled)
    }

    @Test func victoryRequiresBothEnemyArmyAndCastleToBeRemoved() throws {
        var armyOnlySnapshot = try activeSession().snapshot
        eliminateRedUnits(in: &armyOnlySnapshot)
        var armyOnly = try restore(armyOnlySnapshot)
        try armyOnly.selectUnit(id: 0)
        #expect(armyOnly.snapshot.missionRuntime?.state == 3)
        #expect(armyOnly.pendingPresentation == nil)

        var castleOnlySnapshot = try activeSession().snapshot
        captureRedCastle(in: &castleOnlySnapshot)
        var castleOnly = try restore(castleOnlySnapshot)
        try castleOnly.selectUnit(id: 0)
        #expect(castleOnly.snapshot.missionRuntime?.state == 3)
        #expect(castleOnly.pendingPresentation == nil)
    }

    @Test func victoryReturnsToCommanderAndUsesFinalDialogueDelays() throws {
        var snapshot = try activeSession().snapshot
        eliminateRedUnits(in: &snapshot)
        captureRedCastle(in: &snapshot)
        var session = try restore(snapshot)

        try session.selectUnit(id: 0)
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.pendingPresentation?.scene?.durationTicks == 15)
        #expect(session.snapshot.missionRuntime?.state == 4)

        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 5, y: 17))
        #expect(session.snapshot.missionRuntime?.state == 5)

        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 261)
        #expect(session.storyPortraitID == 0)
        #expect(session.snapshot.missionRuntime?.state == 6)

        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.pendingPresentation?.scene?.durationTicks == 10)
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
        return session
    }

    private func eliminateRedUnits(in snapshot: inout CampaignSnapshot) {
        for index in snapshot.units.indices where snapshot.units[index].fraction == 2 {
            snapshot.units[index].hitPoints = 0
        }
    }

    private func captureRedCastle(in snapshot: inout CampaignSnapshot) {
        if snapshot.tileOverrides == nil { snapshot.tileOverrides = [:] }
        snapshot.tileOverrides?[5 * 12 + 5] = 40
    }

    private func makeSession() throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m5"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), seed: 17)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m5"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }

    private func restoreThroughJSON(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try restore(decoded)
    }
}
