import AECore
import AEContent
import Foundation
import Testing

struct CampaignOrderTests {
    @Test func cancelRestoredMoveReturnsToOriginWithoutConsumingTutorialOrRandomness() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(mapID: map.id, units: [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 4, y: 9),
            CampaignUnitState(id: 1, type: 0, fraction: 2, x: 9, y: 3),
        ], randomState: JavaRandom(seed: 40).state, m0ScriptState: 16)
        var session = try make(snapshot)
        try session.selectUnit(id: 0)
        try session.move(unitID: 0, to: GridPoint(x: 4, y: 8))
        #expect(session.snapshot.pendingCampaignHelpID == nil)
        let restored = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(session.snapshot))
        session = try make(restored)
        try session.cancelMove()
        #expect(session.unit(id: 0)?.position == GridPoint(x: 4, y: 9))
        #expect(session.unit(id: 0)?.hasMoved == false)
        #expect(session.unit(id: 0)?.hasActed == false)
        #expect(session.snapshot.randomState == snapshot.randomState)
        #expect(session.snapshot.m0ScriptState == 16)
        #expect(session.snapshot.pendingCampaignHelpID == nil)
    }

    @Test func endingWithoutMovingCountsAsFinishedAction() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(mapID: map.id, units: [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 4, y: 9, hasActed: true),
            CampaignUnitState(id: 1, type: 0, fraction: 1, x: 3, y: 8, hasActed: true),
            CampaignUnitState(id: 2, type: 1, fraction: 1, x: 3, y: 10),
            CampaignUnitState(id: 3, type: 0, fraction: 2, x: 9, y: 3),
        ], randomState: JavaRandom(seed: 40).state, m0ScriptState: 18)
        var session = try make(snapshot)
        try session.endUnitAction(unitID: 2)
        #expect(session.snapshot.pendingCampaignHelpID == 200)
        #expect(session.unit(id: 2)?.hasMoved == false)
    }

    @Test func oldSnapshotWithoutSelectionRemainsReadable() throws {
        let map = try GameResources.missionZero()
        let snapshot = CampaignSnapshot(mapID: map.id, units: [
            CampaignUnitState(id: 0, type: 9, fraction: 1, x: 4, y: 9),
        ], randomState: JavaRandom(seed: 1).state, m0ScriptState: 15)
        var object = try #require(JSONSerialization.jsonObject(with: JSONEncoder().encode(snapshot)) as? [String: Any])
        object.removeValue(forKey: "selection")
        let restored = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONSerialization.data(withJSONObject: object))
        let session = try make(restored)
        #expect(session.snapshot.selection == nil)
        #expect(session.snapshot.pendingCampaignHelpID == nil)
        #expect(session.snapshot.m0ScriptState == 15)
    }

    private func make(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }
}
