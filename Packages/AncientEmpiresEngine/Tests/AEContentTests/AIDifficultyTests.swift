import AECore
import AEContent
import Foundation
import Testing

struct AIDifficultyTests {
    private var blueKing: CampaignUnitState { .init(id: 0, type: 9, fraction: 1, x: 19, y: 19) }

    @Test func oldSavesKeepNormalAndExplicitNormalMatchesOriginalDecisions() throws {
        var state = fixture([blueKing, .init(id: 1, type: 0, fraction: 2, x: 5, y: 5)])
        var object = try #require(JSONSerialization.jsonObject(with: JSONEncoder().encode(state)) as? [String: Any])
        object.removeValue(forKey: "aiDifficulty")
        let old = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONSerialization.data(withJSONObject: object))
        var legacy = try make(old)
        #expect(legacy.difficulty(for: 2) == .normal)
        state.aiDifficulty = .normal
        var normal = try make(state)
        #expect(try normal.endPlayerTurn() == legacy.endPlayerTurn())
        #expect(normal.snapshot.units == legacy.snapshot.units)
        #expect(normal.snapshot.randomState == legacy.snapshot.randomState)
        let player = try JSONDecoder().decode(SkirmishPlayer.self, from: Data(#"{"fraction":2,"control":"cpu","team":2}"#.utf8))
        #expect(player.resolvedDifficulty == .normal)
    }

    @Test func easyRecruitsOneUnitWhileNormalDeploysMore() throws {
        var state = fixture([blueKing], money: 1000, unlock: 0)
        state.tileOverrides = [21: 42]
        state.aiDifficulty = .easy
        var easy = try make(state)
        #expect(try easy.endPlayerTurn().count == 1)
        state.aiDifficulty = .normal
        var normal = try make(state)
        #expect(try normal.endPlayerTurn().count == 2)
        #expect(easy.money(for: 2) > normal.money(for: 2))
    }

    @Test func hardFinishesWoundedEnemyInsteadOfAttackingHealthyCrystal() throws {
        var state = fixture([blueKing,
            .init(id: 1, type: 1, fraction: 2, x: 5, y: 5),
            .init(id: 2, type: 0, fraction: 1, x: 7, y: 5, hitPoints: 5),
            .init(id: 3, type: 11, fraction: 1, x: 5, y: 7)])
        var normal = try make(state)
        #expect(try normal.endPlayerTurn().first?.targetID == 3)
        state.aiDifficulty = .hard
        var hard = try make(state)
        #expect(try hard.endPlayerTurn().first?.targetID == 2)
        #expect(hard.unit(id: 2)?.isAlive == false)
    }

    @Test func expertRetreatsWoundedCommanderFromMultipleAttackers() throws {
        var state = fixture([blueKing,
            .init(id: 1, type: 9, fraction: 2, x: 5, y: 5, hitPoints: 30),
            .init(id: 2, type: 8, fraction: 1, x: 7, y: 5),
            .init(id: 3, type: 8, fraction: 1, x: 5, y: 7),
            .init(id: 4, type: 8, fraction: 1, x: 7, y: 7)])
        var normal = try make(state)
        let reckless = try #require(normal.endPlayerTurn().first)
        state.aiDifficulty = .expert
        var expert = try make(state)
        let careful = try #require(expert.endPlayerTurn().first)
        #expect(careful.targetID == nil)
        #expect(expert.unit(id: 1)?.hitPoints == 30)
        #expect(careful.destination.x + careful.destination.y < reckless.destination.x + reckless.destination.y)
    }

    @Test func expertRecruitsAntiAirAgainstFlyingArmy() throws {
        var state = fixture([blueKing,
            .init(id: 1, type: 9, fraction: 2, x: 1, y: 1),
            .init(id: 2, type: 0, fraction: 2, x: 1, y: 2),
            .init(id: 3, type: 0, fraction: 2, x: 2, y: 1),
            .init(id: 4, type: 1, fraction: 2, x: 2, y: 2),
            .init(id: 5, type: 1, fraction: 2, x: 3, y: 2),
            .init(id: 6, type: 8, fraction: 1, x: 18, y: 18),
            .init(id: 7, type: 8, fraction: 1, x: 17, y: 18),
            .init(id: 8, type: 8, fraction: 1, x: 18, y: 17)], money: 1000, unlock: 8)
        state.aiDifficulty = .expert
        state.tileOverrides = [21: 42]
        state.unitCap = 6
        var expert = try make(state)
        _ = try expert.endPlayerTurn()
        #expect(expert.unit(id: 9)?.type == 1)
        #expect(expert.money(for: 2) == 800)
        #expect(expert.snapshot.randomState == state.randomState)
    }

    @Test(arguments: AIDifficulty.allCases) func savedTurnAndPresentationReplayAreDeterministic(level: AIDifficulty) throws {
        var state = fixture([blueKing,
            .init(id: 1, type: 1, fraction: 2, x: 5, y: 5),
            .init(id: 2, type: 0, fraction: 1, x: 7, y: 5)])
        state.aiDifficulty = level
        var immediate = try make(state)
        var animated = try make(state)
        #expect(try immediate.endPlayerTurn() == animated.endPlayerTurn(recordPresentation: true))
        let committed = animated.snapshot
        while animated.pendingPresentation != nil {
            animated = try make(JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(animated.snapshot)))
            #expect(animated.difficulty(for: 2) == level)
            #expect(animated.snapshot.units == committed.units)
            #expect(animated.snapshot.randomState == committed.randomState)
            animated.completePresentation()
        }
        #expect(animated.snapshot == immediate.snapshot)
    }

    @Test(arguments: AIDifficulty.allCases) func skirmishDifficultyRoundTripsAndRespectsTeams(level: AIDifficulty) throws {
        let map = try GameResources.campaignMap(named: "s3")
        var settings = SkirmishConfiguration(map: map)
        settings.players[1].difficulty = level
        settings.players[2].team = settings.players[1].team
        var game = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), skirmish: settings)
        let cpu = settings.players[1].fraction
        #expect(game.difficulty(for: cpu) == level)
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(game.snapshot))
        let restored = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), difficulty: .easy, snapshot: decoded)
        #expect(restored.difficulty(for: cpu) == level)
        let before = game.snapshot.units
        let actions = try game.endPlayerTurn()
        for action in actions {
            if let targetID = action.targetID, let attacker = before.first(where: { $0.id == action.unitID }), let target = before.first(where: { $0.id == targetID }) {
                #expect(!game.areAllied(attacker.fraction, target.fraction))
            }
        }
        let alive = game.snapshot.units.filter(\.isAlive)
        #expect(Set(alive.map(\.position)).count == alive.count)
    }

    @Test func expertDefendsThreatenedCastleBeforeDistantNeutralTown() throws {
        var state = fixture([blueKing,
            .init(id: 1, type: 0, fraction: 2, x: 6, y: 6),
            .init(id: 2, type: 9, fraction: 2, x: 19, y: 0),
            .init(id: 3, type: 9, fraction: 1, x: 3, y: 1)])
        state.tileOverrides = [21: 42, 206: 37]
        var normal = try make(state)
        #expect(try normal.endPlayerTurn().first?.strategicTarget == GridPoint(x: 6, y: 10))
        state.aiDifficulty = .expert
        var expert = try make(state)
        #expect(try expert.endPlayerTurn().first?.strategicTarget == GridPoint(x: 3, y: 1))
    }

    @Test func hardTakesDetourAroundWallInsteadOfFollowingBlockedStraightLine() throws {
        let original = try GameResources.campaignRuleset()
        let mountain = try #require(original.terrainByTileID.filter { $0.value == original.mountainTerrainID }.keys.sorted().first)
        var costs = original.movementCostByTerrainID
        costs[original.mountainTerrainID] = 1000
        let rules = CampaignRuleset(terrainByTileID: original.terrainByTileID, movementCostByTerrainID: costs, defenseByTerrainID: original.defenseByTerrainID)
        var state = fixture([blueKing, .init(id: 1, type: 0, fraction: 2, x: 6, y: 10)])
        state.aiDifficulty = .hard
        state.tileOverrides = Dictionary(uniqueKeysWithValues: (1..<20).map { ($0 * 20 + 8, mountain) })
        var game = try make(state, rules: rules)
        let action = try #require(game.endPlayerTurn().first)
        #expect(action.destination.y < 10)
        #expect(action.destination.x < 8)
    }

    @Test(arguments: 0..<12, [AIDifficulty.hard, .expert])
    func advancedLevelsRunAllBuiltinMapsWithoutOverlapOrOverspending(index: Int, level: AIDifficulty) throws {
        let map = try GameResources.campaignMap(named: "s\(index)")
        var settings = SkirmishConfiguration(map: map)
        for player in settings.players.indices { settings.players[player].difficulty = level }
        var game = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), skirmish: settings)
        for _ in 0..<3 where game.snapshot.phase == .playerTurn {
            _ = try game.endPlayerTurn()
            let alive = game.snapshot.units.filter(\.isAlive)
            #expect(Set(alive.map(\.position)).count == alive.count)
            #expect((game.snapshot.moneyByFraction ?? [:]).values.allSatisfy { $0 >= 0 })
            for player in settings.players {
                #expect(alive.filter { $0.fraction == player.fraction && $0.type != 10 }.count <= settings.unitCap)
            }
        }
    }

    private func fixture(_ units: [CampaignUnitState], money: Int = 0, unlock: Int = 0) -> CampaignSnapshot {
        var state = CampaignSnapshot(mapID: "m0", units: units, randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        state.moneyByFraction = [2: money]
        state.allowedUnitType = unlock
        state.unitCap = 10
        return state
    }

    private func make(_ snapshot: CampaignSnapshot, rules: CampaignRuleset? = nil) throws -> CampaignSession {
        let object: [String: Any] = ["id": "m0", "kind": "campaign", "index": 0, "name": "AI fixture",
            "width": 20, "height": 20, "rows": Array(repeating: Array(repeating: 18, count: 20), count: 20), "units": [], "buildings": []]
        let map = try JSONDecoder().decode(CampaignMap.self, from: JSONSerialization.data(withJSONObject: object))
        return try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: rules ?? GameResources.campaignRuleset(), snapshot: snapshot)
    }
}
