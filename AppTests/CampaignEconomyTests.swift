import AECore
import AEContent
import Foundation
import Testing

struct CampaignEconomyTests {
    @Test func turnIncomeAndHealingAreCommittedOnceAcrossEveryRestore() throws {
        let snapshot = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 8, hitPoints: 70),
            .init(id: 1, type: 0, fraction: 1, x: 8, y: 8, hitPoints: 95),
            .init(id: 2, type: 0, fraction: 1, x: 2, y: 10, hitPoints: 40),
            .init(id: 3, type: 9, fraction: 2, x: 6, y: 1, hitPoints: 70)
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        var session = try restore(snapshot)
        var immediate = session
        _ = try session.endPlayerTurn(recordPresentation: true)
        _ = try immediate.endPlayerTurn()
        #expect(session.money(for: 1) == 60)
        #expect(session.money(for: 2) == 60)
        #expect(session.unit(id: 0)?.hitPoints == 90)
        #expect(session.unit(id: 1)?.hitPoints == 100)
        #expect(session.unit(id: 2)?.hitPoints == 40)
        #expect(session.unit(id: 3)?.hitPoints == 90)
        let queue = session.snapshot.pendingPresentations ?? []
        #expect(queue.compactMap { $0.scene?.turn?.fraction } == [2, 1])
        #expect(queue.compactMap { $0.scene?.turn?.income } == [60, 60])
        let healed = queue.flatMap { $0.scene?.healing ?? [] }
        #expect(healed == [.init(unitID: 3, amount: 20), .init(unitID: 0, amount: 20), .init(unitID: 1, amount: 5)])
        let committed = session.snapshot
        while session.pendingPresentation != nil {
            session = try restore(session.snapshot)
            #expect(!session.isInputEnabled)
            #expect(session.snapshot.units == committed.units)
            #expect(session.snapshot.moneyByFraction == committed.moneyByFraction)
            #expect(session.snapshot.randomState == committed.randomState)
            session.completePresentation()
        }
        #expect(session.snapshot == immediate.snapshot)
    }

    @Test func oldSaveWithoutMoneyDefaultsToZeroAndAccumulatesEachTurn() throws {
        let snapshot = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 9),
            .init(id: 1, type: 9, fraction: 2, x: 6, y: 1)
        ], randomState: JavaRandom(seed: 1).state, m0ScriptState: 22)
        let data = try JSONEncoder().encode(snapshot)
        #expect(!String(decoding: data, as: UTF8.self).contains("moneyByFraction"))
        var session = try restore(snapshot)
        #expect(session.money(for: 1) == 0)
        _ = try session.endPlayerTurn()
        session = try restore(session.snapshot)
        _ = try session.endPlayerTurn()
        #expect(session.money(for: 1) == 120)
        #expect(session.money(for: 2) == 120)
    }

    @Test func castleIncomeNeutralBuildingsPublicHealingAndDeadUnits() throws {
        var rows = Array(repeating: Array(repeating: 1, count: 12), count: 12)
        rows[8][3] = 40 // Blue castle.
        rows[8][5] = 37 // Neutral village.
        rows[8][6] = 34 // Type 7 temple.
        rows[8][7] = 27 // Ruined village, type 8 with no owner.
        rows[8][8] = 39 // Blue village, occupied by a dead unit.
        rows[0][10] = 42 // Enemy castle, occupied by a blue unit.
        let buildings = [(3, 8, 40, 9, 1), (5, 8, 37, 8, 0), (8, 8, 39, 8, 1), (10, 0, 42, 9, 2)]
        let object: [String: Any] = ["id": "m0", "kind": "campaign", "index": 0, "name": "Economy fixture", "width": 12, "height": 12,
            "rows": rows, "units": [], "buildings": buildings.map { ["x": $0.0, "y": $0.1, "tile": $0.2, "terrain": $0.3, "fraction": $0.4] }]
        let map = try JSONDecoder().decode(CampaignMap.self, from: JSONSerialization.data(withJSONObject: object))
        let snapshot = CampaignSnapshot(mapID: "m0", units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 9, hasActed: true),
            .init(id: 1, type: 0, fraction: 1, x: 3, y: 8, hitPoints: 95),
            .init(id: 2, type: 0, fraction: 1, x: 5, y: 8, hitPoints: 30),
            .init(id: 3, type: 0, fraction: 1, x: 6, y: 8, hitPoints: 30),
            .init(id: 4, type: 0, fraction: 1, x: 8, y: 8, hitPoints: 0),
            .init(id: 5, type: 0, fraction: 1, x: 10, y: 0, hitPoints: 30),
            .init(id: 6, type: 9, fraction: 2, x: 0, y: 0),
            .init(id: 7, type: 0, fraction: 1, x: 7, y: 8, hitPoints: 30)
        ], randomState: JavaRandom(seed: 1).state, m0ScriptState: 22)
        var session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: snapshot)
        _ = try session.endPlayerTurn(recordPresentation: true)
        #expect(session.money(for: 1) == 80)
        #expect(session.money(for: 2) == 50)
        #expect(session.unit(id: 1)?.hitPoints == 100)
        #expect(session.unit(id: 2)?.hitPoints == 30)
        #expect(session.unit(id: 3)?.hitPoints == 50)
        #expect(session.unit(id: 4)?.hitPoints == 0)
        #expect(session.unit(id: 5)?.hitPoints == 30)
        #expect(session.unit(id: 7)?.hitPoints == 30)
        #expect(session.pendingPresentation?.unitsBefore.first(where: { $0.id == 0 })?.hasActed == false)
        #expect(session.unit(id: 6)?.hasActed == false)
    }

    private func restore(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: decoded)
    }
}
