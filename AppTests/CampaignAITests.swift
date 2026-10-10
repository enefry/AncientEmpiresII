import AECore
import AEContent
import Foundation
import Testing

struct CampaignAITests {
    private let castle = GridPoint(x: 1, y: 1)
    private var blueKing: CampaignUnitState { .init(id: 0, type: 9, fraction: 1, x: 19, y: 19) }

    @Test func existingUnitsActBeforePurchasesAndSoldiersPrecedeArchers() throws {
        var state = snapshot([blueKing, .init(id: 1, type: 11, fraction: 2, x: 8, y: 0)], money: 1000, unlock: 1)
        state.unitCap = 5
        var session = try make(state)
        let actions = try session.endPlayerTurn(recordPresentation: true)
        #expect(actions.first?.unitID == 1)
        #expect(actions.dropFirst().compactMap { session.unit(id: $0.unitID)?.type } == [0, 0, 1, 1])
        #expect(actions.dropFirst().allSatisfy { $0.origin == castle })
        #expect(Set(session.snapshot.units.filter(\.isAlive).map(\.position)).count == session.snapshot.units.filter(\.isAlive).count)
        #expect(session.money(for: 2) == 250) // 1,000 + 50 income - 2*150 - 2*250.
        #expect(session.snapshot.randomState == state.randomState)
        #expect(session.snapshot.pendingPresentations?.filter { $0.scene?.kind == .planning }.count == 5)
    }

    @Test func castleSelectionUsesNearestEnemyCommanderThenXMajorTies() throws {
        for (king, castles, expected) in [
            (GridPoint(x: 19, y: 19), [GridPoint(x: 1, y: 1), GridPoint(x: 15, y: 15)], GridPoint(x: 15, y: 15)),
            (GridPoint(x: 10, y: 10), [GridPoint(x: 10, y: 8), GridPoint(x: 8, y: 10)], GridPoint(x: 8, y: 10)),
            (GridPoint(x: 10, y: 10), [GridPoint(x: 8, y: 12), GridPoint(x: 8, y: 8)], GridPoint(x: 8, y: 8))
        ] {
            let units: [CampaignUnitState] = [.init(id: 0, type: 9, fraction: 1, x: king.x, y: king.y)]
            var state = snapshot(units, money: 150)
            state.tileOverrides = Dictionary(uniqueKeysWithValues: castles.map { ($0.y * 20 + $0.x, 42) })
            state.unitCap = 1
            var session = try make(state)
            let actions = try session.endPlayerTurn()
            #expect(actions.count == 1)
            #expect(actions.first?.origin == expected)
        }
    }

    @Test func commanderRevivesBeforeNewSoldiersWithoutLosingIdentity() throws {
        var king = CampaignUnitState(id: 1, type: 9, fraction: 2, x: -10, y: -10, hitPoints: 0, rank: 3, experience: 900)
        king.revivalCost = 600
        let state = snapshot([blueKing, king], money: 700)
        var session = try make(state)
        let actions = try session.endPlayerTurn()
        #expect(actions.map(\.unitID) == [1, 2])
        #expect(session.unit(id: 1)?.isAlive == true)
        #expect(session.unit(id: 1)?.rank == 3)
        #expect(session.unit(id: 1)?.experience == 900)
        #expect(session.unit(id: 1)?.revivalCost == 600)
        #expect(session.unit(id: 2)?.type == 0)
        #expect(session.money(for: 2) == 0)
        #expect(session.snapshot.randomState == state.randomState)
    }

    @Test func originalMultipleKingPurchaseLoopOnlyDeploysTheLastPaidKing() throws {
        let units = [blueKing,
            CampaignUnitState(id: 1, type: 9, fraction: 2, x: -10, y: -10, hitPoints: 0),
            CampaignUnitState(id: 2, type: 9, fraction: 2, x: -10, y: -10, hitPoints: 0)]
        var session = try make(snapshot(units, money: 750))
        let actions = try session.endPlayerTurn(recordPresentation: true)
        #expect(actions.map(\.unitID) == [2])
        #expect(session.unit(id: 1)?.hitPoints == 100)
        #expect(session.unit(id: 1)?.isAlive == false)
        #expect(session.unit(id: 1)?.revivalAwaitingMovement == true)
        #expect(session.unit(id: 2)?.isAlive == true)
        #expect(session.money(for: 2) == 0)
        let saved = try make(session.snapshot)
        #expect(saved.snapshot == session.snapshot)
    }

    @Test func fundsUnlockAndCapPreventPurchasesButSkeletonsDoNotUseCap() throws {
        let units = [blueKing, CampaignUnitState(id: 1, type: 10, fraction: 2, x: 8, y: 0)]
        var state = snapshot(units, money: 99)
        var session = try make(state)
        #expect(try session.endPlayerTurn().map(\.unitID) == [1])
        #expect(session.money(for: 2) == 149)
        state.moneyByFraction = [2: 1000]
        state.unitCap = 1
        session = try make(state)
        let actions = try session.endPlayerTurn()
        #expect(actions.compactMap { session.unit(id: $0.unitID)?.type } == [10, 0])
        #expect(session.money(for: 2) == 900)
        // No archer can be bought at unlock 0, even with funds and cap remaining.
        state.unitCap = 10
        session = try make(state)
        let unlocked = try session.endPlayerTurn()
        #expect(unlocked.compactMap { session.unit(id: $0.unitID)?.type } == [10, 0, 0])
    }

    @Test func occupiedCastleWithoutEmptyReachableCellDoesNotSpendMoney() throws {
        var state = snapshot([blueKing, .init(id: 1, type: 11, fraction: 2, x: 1, y: 1)], money: 1000, unlock: 0)
        state.unitCap = 10
        var session = try make(state, impassable: true)
        let actions = try session.endPlayerTurn()
        #expect(actions.map(\.unitID) == [1])
        #expect(session.snapshot.units.count == 2)
        #expect(session.money(for: 2) == 1050)
        #expect(session.snapshot.randomState == state.randomState)
    }

    @Test func randomPurchaseConsumesOneDrawAndEveryPresentationCanBeRestored() throws {
        let units = [blueKing,
            CampaignUnitState(id: 1, type: 0, fraction: 2, x: 7, y: 0),
            CampaignUnitState(id: 2, type: 0, fraction: 2, x: 9, y: 0),
            CampaignUnitState(id: 3, type: 1, fraction: 2, x: 11, y: 0),
            CampaignUnitState(id: 4, type: 1, fraction: 2, x: 13, y: 0)]
        var state = snapshot(units, money: 500, unlock: 2)
        state.unitCap = 5
        var session = try make(state)
        var immediate = session
        let actions = try session.endPlayerTurn(recordPresentation: true)
        #expect(actions == (try immediate.endPlayerTurn()))
        #expect(session.unit(id: 5)?.type == 2)
        var random = JavaRandom(internalState: state.randomState)
        _ = random.nextInt32()
        #expect(session.snapshot.randomState == random.state)
        let committed = session.snapshot
        while session.pendingPresentation != nil {
            session = try make(session.snapshot)
            #expect(!session.isInputEnabled)
            #expect(session.snapshot.units == committed.units)
            #expect(session.snapshot.moneyByFraction == committed.moneyByFraction)
            #expect(session.snapshot.randomState == random.state)
            session.completePresentation()
        }
        #expect(session.snapshot == immediate.snapshot)
    }

    @Test func flyingRecruitCanLeaveCastleWhenGroundUnitsCannot() throws {
        let state = snapshot([blueKing, .init(id: 1, type: 11, fraction: 2, x: 1, y: 1)], money: 1000, unlock: 8)
        var session = try make(state, impassable: true)
        let actions = try session.endPlayerTurn()
        #expect(actions.map(\.unitID) == [1, 2])
        #expect(session.unit(id: 2)?.type == 8)
        #expect(session.unit(id: 2)?.position != castle)
        #expect(session.money(for: 2) == 50)
    }

    @Test func buildingReservationsSplitCapturersAndLowHealthPrefersHealing() throws {
        var state = snapshot([blueKing,
            .init(id: 1, type: 0, fraction: 2, x: 0, y: 0),
            .init(id: 2, type: 0, fraction: 2, x: 1, y: 0),
            .init(id: 3, type: 9, fraction: 2, x: 19, y: 0)])
        state.tileOverrides = [10: 37, 200: 37]
        var session = try make(state)
        let actions = try session.endPlayerTurn()
        #expect(actions.first(where: { $0.unitID == 1 })?.strategicTarget == GridPoint(x: 0, y: 10))
        #expect(actions.first(where: { $0.unitID == 2 })?.strategicTarget == GridPoint(x: 10, y: 0))
        state.units[1].hitPoints = 20
        state.tileOverrides = [60: 41, 10: 37]
        session = try make(state)
        let wounded = try session.endPlayerTurn()
        #expect(wounded.first(where: { $0.unitID == 1 })?.strategicTarget == GridPoint(x: 0, y: 3))
        #expect(wounded.first(where: { $0.unitID == 1 })?.destination == GridPoint(x: 0, y: 3))
    }

    @Test func unactedBlockerMovesFirstThenOriginalUnitReplansWithoutOverlap() throws {
        var state = snapshot([blueKing,
            .init(id: 1, type: 0, fraction: 2, x: 1, y: 1),
            .init(id: 2, type: 1, fraction: 2, x: 1, y: 4),
            .init(id: 3, type: 9, fraction: 2, x: 19, y: 0)])
        state.tileOverrides = [81: 37] // Archer blocks the soldier's capture target.
        var session = try make(state)
        let actions = try session.endPlayerTurn()
        #expect(actions.prefix(2).map(\.unitID) == [2, 1])
        #expect(actions.first(where: { $0.unitID == 1 })?.destination == GridPoint(x: 1, y: 4))
        #expect(session.map.tileID(x: 1, y: 4) == 41)
        let living = session.snapshot.units.filter(\.isAlive)
        #expect(Set(living.map(\.position)).count == living.count)
        #expect(Set(actions.map(\.unitID)).count == actions.count)
    }

    @Test func lastRegisteredCommanderIsTheStrategicTarget() throws {
        var state = snapshot([blueKing,
            .init(id: 2, type: 9, fraction: 1, x: 18, y: 19),
            .init(id: 3, type: 10, fraction: 2, x: 0, y: 0)])
        state.tileOverrides = [:]
        var session = try make(state)
        #expect(try session.endPlayerTurn().first?.strategicTarget == GridPoint(x: 18, y: 19))
    }

    @Test func movementAndAttackPreviewUseOriginalTicksAndCPUUnitSpeed() throws {
        var state = snapshot([
            .init(id: 0, type: 9, fraction: 1, x: 10, y: 10),
            .init(id: 1, type: 0, fraction: 2, x: 7, y: 10)])
        state.tileOverrides = [:]
        var session = try make(state)
        let actions = try session.endPlayerTurn(recordPresentation: true)
        #expect(actions.first?.targetID == 0)
        let queue = try #require(session.snapshot.pendingPresentations)
        #expect(queue.map { $0.scene?.kind.rawValue ?? $0.kind.rawValue } == ["newTurn", "camera", "planning", "movement", "targeting", "combat", "newTurn"])
        let camera = try #require(queue.first { $0.scene?.kind == .camera }?.scene)
        let plan = try #require(queue.first { $0.scene?.kind == .planning }?.scene)
        #expect(camera.durationTicks == 2)
        #expect(plan.durationTicks == 8)
        #expect(plan.routePreview?.first == GridPoint(x: 7, y: 10))
        #expect(plan.routePreview?.last == actions.first?.destination)
        #expect(plan.movementRange?.contains(GridPoint(x: 7, y: 10)) == true)
        #expect(queue.first { $0.kind == .movement }?.unitSpeed == 12)
        let target = try #require(queue.first { $0.scene?.kind == .targeting }?.scene)
        #expect(target.durationTicks == 10)
        #expect(target.focus == GridPoint(x: 10, y: 10))
        #expect(target.attackRange?.contains(GridPoint(x: 10, y: 10)) == true)
        #expect(target.attackRange?.count == 4)
    }

    @Test func oldPresentationsWithoutNewFieldsStillDecode() throws {
        let unit = CampaignUnitState(id: 1, type: 0, fraction: 2, x: 0, y: 0)
        let movement = CampaignPresentation(unitID: 1, unitsBefore: [unit], route: [GridPoint(x: 1, y: 0)])
        var object = try #require(JSONSerialization.jsonObject(with: JSONEncoder().encode(movement)) as? [String: Any])
        object.removeValue(forKey: "unitSpeed")
        let restored = try JSONDecoder().decode(CampaignPresentation.self, from: JSONSerialization.data(withJSONObject: object))
        #expect(restored.unitSpeed == nil)
        #expect(restored.unitsBefore[0].revivalAwaitingMovement == nil)
        let scene = CampaignScenePresentation(.camera, focus: .init(x: 1, y: 1))
        let decoded = try JSONDecoder().decode(CampaignScenePresentation.self, from: JSONEncoder().encode(scene))
        #expect(decoded.movementRange == nil)
        #expect(decoded.routePreview == nil)
        #expect(decoded.attackRange == nil)
    }

    private func snapshot(_ units: [CampaignUnitState], money: Int = 0, unlock: Int = 0) -> CampaignSnapshot {
        var state = CampaignSnapshot(mapID: "m0", units: units, randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        state.moneyByFraction = [2: money]
        state.allowedUnitType = unlock
        state.tileOverrides = [21: 42]
        return state
    }

    private func make(_ snapshot: CampaignSnapshot, impassable: Bool = false) throws -> CampaignSession {
        let object: [String: Any] = ["id": "m0", "kind": "campaign", "index": 0, "name": "AI fixture",
            "width": 20, "height": 20, "rows": Array(repeating: Array(repeating: 18, count: 20), count: 20), "units": [], "buildings": []]
        let map = try JSONDecoder().decode(CampaignMap.self, from: JSONSerialization.data(withJSONObject: object))
        let original = try GameResources.campaignRuleset()
        let rules = impassable ? CampaignRuleset(terrainByTileID: original.terrainByTileID,
            movementCostByTerrainID: original.movementCostByTerrainID.mapValues { _ in 1000 },
            defenseByTerrainID: original.defenseByTerrainID) : original
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: rules, snapshot: decoded)
    }
}
