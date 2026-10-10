import AECore
import AEContent
import Foundation
import Testing

struct CampaignMissionThreeTests {
    @Test func initializationAndOpeningRoutesMatchTempleOfLife() throws {
        var session = try makeSession()
        #expect(session.map.id == "m3")
        #expect(session.money(for: 1) == 400)
        #expect(session.money(for: 2) == 400)
        #expect(session.allowedUnitType == 7)
        #expect(session.objectiveTextID == 132)
        #expect(session.snapshot.missionRuntime?.references["king1"] == 0)
        #expect(session.snapshot.missionRuntime?.references["catapult"] == 7)
        #expect(session.snapshot.units.count == 13)

        session = try restore(session.snapshot)
        session.enableStoryPresentations()
        try session.advanceStory()

        let arrival = try #require(session.pendingPresentation?.scene)
        #expect(arrival.kind == .arrival)
        #expect(arrival.movements.map(\.unitID) == [0, 10, 11, 12])
        #expect(arrival.movements.map { $0.route.last } == [
            GridPoint(x: 3, y: 3), GridPoint(x: 3, y: 4),
            GridPoint(x: 4, y: 4), GridPoint(x: 2, y: 4),
        ])
        #expect(arrival.cameraOrigin == GridPoint(x: 0, y: 5))
        #expect(arrival.focus == GridPoint(x: 3, y: 3))
        #expect(arrival.unitSpeed == 4)
        #expect(arrival.durationTicks == 50)
        #expect(session.snapshot.missionRuntime?.state == 2)

        let encoded = try JSONEncoder().encode(session.snapshot)
        session = try restore(JSONDecoder().decode(CampaignSnapshot.self, from: encoded))
        #expect(session.pendingPresentation?.scene == arrival)
        #expect(session.snapshot.units.count == 13)
    }

    @Test func blackoutTeleportAndVillageDestructionAreRecoverable() throws {
        var session = try makeSession()
        session.enableStoryPresentations()
        try session.advanceStory()
        session.completePresentation()

        #expect(session.pendingPresentation?.scene?.kind == .fadeToBlack)
        #expect(session.pendingPresentation?.scene?.durationTicks == 20)
        #expect(session.snapshot.missionRuntime?.screenObscured == true)
        session = try restoreThroughJSON(session.snapshot)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 249)
        #expect(session.storyPortraitID == 2)

        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 250)
        #expect(session.storyPortraitID == 0)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 251)
        #expect(session.storyPortraitID == 5)
        #expect(session.unit(id: 0)?.position == GridPoint(x: 7, y: 1))
        #expect(session.unit(id: 10)?.position == GridPoint(x: 5, y: 4))
        #expect(session.unit(id: 11)?.position == GridPoint(x: 7, y: 5))
        #expect(session.unit(id: 12)?.position == GridPoint(x: 3, y: 3))
        #expect(session.snapshot.missionRuntime?.screenObscured == true)

        try session.advanceStory()
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .fadeFromBlack)
        #expect(session.pendingPresentation?.scene?.durationTicks == 5)
        #expect(session.snapshot.missionRuntime?.screenObscured == false)
        session.completePresentation()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 13, y: 10))
        session.completePresentation()

        let catapultMove = try #require(session.pendingPresentation?.scene)
        #expect(catapultMove.kind == .arrival)
        #expect(catapultMove.movements.map(\.unitID) == [7])
        #expect(catapultMove.movements.first?.route.last == GridPoint(x: 6, y: 10))
        #expect(catapultMove.unitSpeed == 2)
        #expect(catapultMove.durationTicks == 58)
        session.completePresentation()

        let destruction = try #require(session.pendingPresentation?.scene)
        #expect(destruction.kind == .buildingDestruction)
        #expect(destruction.focus == GridPoint(x: 4, y: 9))
        #expect(destruction.durationTicks == 35)
        #expect(destruction.tileChange == .init(point: GridPoint(x: 4, y: 9), tileID: 27, tick: 21))
        #expect(session.presentationMap.tileID(x: 4, y: 9) == 39)
        #expect(session.map.tileID(x: 4, y: 9) == 27)

        session = try restoreThroughJSON(session.snapshot)
        #expect(session.pendingPresentation?.scene == destruction)
        #expect(session.presentationMap.tileID(x: 4, y: 9) == 39)
        #expect(session.map.tileID(x: 4, y: 9) == 27)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 252)
        #expect(session.map.tileID(x: 4, y: 9) == 27)
    }

    @Test func tutorialObjectiveAndEndingMatchSourceOrder() throws {
        var session = try sessionAtDestroyedVillageDialogue()
        #expect(session.snapshot.currentStoryMessageID == 252)
        try session.advanceStory()
        #expect(session.pendingPresentation?.scene?.kind == .camera)
        #expect(session.pendingPresentation?.scene?.focus == GridPoint(x: 7, y: 1))
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 253)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.pendingCampaignHelpID == 213)

        session.dismissCampaignHelp()
        #expect(session.pendingPresentation?.scene?.kind == .objective)
        #expect(session.pendingPresentation?.scene?.textID == 132)
        session.completePresentation()
        #expect(session.isInputEnabled)
        #expect(session.snapshot.missionRuntime?.state == 16)

        var victory = session.snapshot
        for index in victory.units.indices where victory.units[index].fraction == 2 {
            victory.units[index].removedFromMap = true
        }
        if victory.tileOverrides == nil { victory.tileOverrides = [:] }
        victory.tileOverrides?[9 * 16 + 13] = 27
        session = try restore(victory)
        session.enableStoryPresentations()
        try session.selectUnit(id: 0)
        #expect(session.pendingPresentation?.scene?.kind == .pause)
        #expect(session.pendingPresentation?.scene?.durationTicks == 15)
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 254)
        #expect(session.storyPortraitID == 5)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.currentStoryMessageID == 255)
        #expect(session.storyPortraitID == 0)
        try session.advanceStory()
        session.completePresentation()
        #expect(session.snapshot.phase == .victory)
    }

    private func sessionAtDestroyedVillageDialogue() throws -> CampaignSession {
        var session = try makeSession()
        session.enableStoryPresentations()
        try session.advanceStory()
        session.completePresentation() // arrival
        session.completePresentation() // fade to black
        for _ in 0..<3 {
            try session.advanceStory()
            session.completePresentation() // dialogue delay
        }
        session.completePresentation() // fade from black
        session.completePresentation() // camera to the enemy
        session.completePresentation() // catapult movement
        session.completePresentation() // village destruction
        return session
    }

    private func makeSession() throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m3"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), seed: 13)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.campaignMap(named: "m3"),
                            profiles: GameResources.campaignUnitProfiles(),
                            rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }

    private func restoreThroughJSON(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try restore(decoded)
    }
}
