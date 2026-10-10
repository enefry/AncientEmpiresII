import AECore
import AEContent
import Foundation
import Testing

struct CampaignStatusTests {
    @Test func deathKeepsOrdinaryGravesButRemovesSkeletonsCrystalsAndRelocatesKings() throws {
        for type in [0, 9, 10, 11] {
            var target = CampaignUnitState(id: 1, type: type, fraction: 2, x: 5, y: 8, hitPoints: 1)
            target.effects = 3
            target.poisonSourceFraction = 2
            var game = try make(state([
                .init(id: 0, type: 9, fraction: 1, x: 4, y: 8), target
            ]))
            _ = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
            let dead = try #require(game.unit(id: 1))
            #expect(dead.hitPoints == 0)
            #expect(dead.isCorpse == (type == 0 || type == 9))
            #expect(dead.removedFromMap == ((type == 10 || type == 11) ? true : nil))
            #expect(game.snapshot.pendingPresentations?.last?.scene?.kind == .death)
            #expect(game.snapshot.pendingPresentations?.last?.scene?.durationTicks == 6)
            if type == 9 {
                #expect(dead.position == GridPoint(x: -10, y: -10))
                #expect(dead.effects == nil)
                #expect(dead.poisonSourceFraction == nil)
                #expect(dead.revivalCost == 400)
            }
            let committed = game.snapshot
            while game.pendingPresentation != nil {
                game = try make(roundTrip(game.snapshot))
                game.completePresentation()
            }
            #expect(game.snapshot.units == committed.units)
            #expect(game.snapshot.randomState == committed.randomState)
        }
    }

    @Test func graveLifetimeCountsEveryPlayerTransitionAndKingDoesNotExpire() throws {
        var king = grave(id: 2, x: -10, y: -10, type: 9)
        king.revivalCost = 400
        var game = try make(state([grave(id: 1, x: 4, y: 8), king]), immobilized: true)
        _ = try game.endPlayerTurn(recordPresentation: true)
        let turns = game.snapshot.pendingPresentations?.filter { $0.scene?.kind == .newTurn } ?? []
        try #require(turns.count == 2)
        #expect(turns[0].unitsBefore.first { $0.id == 1 }?.corpseTransitionsRemaining == 2)
        #expect(turns[1].unitsBefore.first { $0.id == 1 }?.corpseTransitionsRemaining == 1)
        while game.pendingPresentation != nil { game.completePresentation() }
        _ = try game.endPlayerTurn(recordPresentation: true)
        #expect(game.snapshot.pendingPresentations?.first?.unitsBefore.first { $0.id == 1 }?.removedFromMap == true)
        #expect(game.unit(id: 2)?.corpseTransitionsRemaining == 3)
        #expect(game.unit(id: 2)?.removedFromMap == nil)
    }

    @Test func landingAndCancelLeaveGraveUntilActionFinishes() throws {
        var game = try make(state([
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8),
            grave(id: 1, x: 5, y: 8), grave(id: 2, x: 5, y: 8)
        ]))
        try game.move(unitID: 0, to: GridPoint(x: 5, y: 8))
        #expect(game.corpse(at: GridPoint(x: 5, y: 8))?.id == 1)
        try game.cancelMove()
        #expect(game.unit(id: 1)?.removedFromMap == nil)
        try game.move(unitID: 0, to: GridPoint(x: 5, y: 8))
        try game.endUnitAction(unitID: 0)
        #expect(game.unit(id: 1)?.removedFromMap == true)
        #expect(game.corpse(at: GridPoint(x: 5, y: 8))?.id == 2)
    }

    @Test func onlyInitiatingWolfPoisonsEvenWhenCounterattackKillsIt() throws {
        for health in [1, 100] {
            var game = try make(state([
                .init(id: 0, type: 5, fraction: 1, x: 4, y: 8, hitPoints: health),
                .init(id: 1, type: 6, fraction: 2, x: 5, y: 8)
            ]))
            _ = try game.attack(unitID: 0, targetID: 1)
            #expect(game.unit(id: 1)?.isPoisoned == true)
            #expect(game.unit(id: 1)?.poisonSourceFraction == 1)
            #expect(game.unit(id: 0)?.isAlive == (health == 100))
        }
        var defendingWolf = try make(state([
            .init(id: 0, type: 6, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 5, fraction: 2, x: 5, y: 8)
        ]))
        _ = try defendingWolf.attack(unitID: 0, targetID: 1)
        #expect(defendingWolf.unit(id: 0)?.isPoisoned == false)
    }

    @Test func poisonLowersCombatStatsWithoutChangingMovementOrHealthEachTurn() throws {
        var affected = CampaignUnitState(id: 0, type: 0, fraction: 1, x: 4, y: 8)
        affected.effects = 1
        affected.poisonSourceFraction = 1
        var game = try make(state([affected]))
        let plain = try make(state([.init(id: 0, type: 0, fraction: 1, x: 4, y: 8)]))
        #expect(try game.movementRange(for: 0)?.reachableDestinations == plain.movementRange(for: 0)?.reachableDestinations)
        #expect(affected.statusOffenseBonus == -10)
        #expect(affected.statusDefenseBonus == -10)
        game = try make(state([affected]), immobilized: true)
        _ = try game.endPlayerTurn(recordPresentation: true)
        let turns = game.snapshot.pendingPresentations?.filter { $0.scene?.kind == .newTurn } ?? []
        try #require(turns.count == 2)
        #expect(turns[0].unitsBefore.first { $0.id == 0 }?.isPoisoned == true)
        #expect(turns[1].unitsBefore.first { $0.id == 0 }?.isPoisoned == false)
        #expect(game.unit(id: 0)?.hitPoints == 100)
    }

    @Test func poisonAndBlessingModifiersReachActualCombatDamage() throws {
        let units: [CampaignUnitState] = [
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 0, fraction: 2, x: 5, y: 8)
        ]
        var baseline = try make(state(units))
        let ordinary = try baseline.attack(unitID: 0, targetID: 1).damage
        for (index, effect, delta) in [(0, 1, -10), (0, 2, 10), (0, 3, 0), (1, 1, 10)] {
            var modified = units
            modified[index].effects = effect
            var game = try make(state(modified))
            #expect(try game.attack(unitID: 0, targetID: 1).damage == ordinary + delta)
        }
        // The victim is blue; red's turn clears poison applied during red's action.
        var poisoned = units[0]
        poisoned.effects = 1
        poisoned.poisonSourceFraction = 2
        var game = try make(state([poisoned]), immobilized: true)
        _ = try game.endPlayerTurn(recordPresentation: true)
        #expect(game.pendingPresentation?.unitsBefore.first { $0.id == 0 }?.isPoisoned == false)
        #expect(game.unit(id: 0)?.hitPoints == 100)
    }

    @Test func wispKilledByCounterattackStillBlessesNearbyAllyWhenActionEnds() throws {
        var game = try make(state([
            .init(id: 0, type: 4, fraction: 1, x: 4, y: 8, hitPoints: 1),
            .init(id: 1, type: 6, fraction: 2, x: 5, y: 8),
            .init(id: 2, type: 0, fraction: 1, x: 4, y: 9)
        ]))
        _ = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
        #expect(game.unit(id: 0)?.isCorpse == true)
        #expect(game.unit(id: 2)?.isBlessed == true)
        #expect(game.snapshot.pendingPresentations?.compactMap { $0.scene?.kind } == [.blessing, .death])
    }

    @Test func blessingOnFinishUsesDistanceOneToTwoAndFirstLivingOccupantWithoutStacking() throws {
        var poisoned = CampaignUnitState(id: 1, type: 0, fraction: 1, x: 5, y: 8)
        poisoned.effects = 1
        poisoned.poisonSourceFraction = 1
        var game = try make(state([
            .init(id: 0, type: 4, fraction: 1, x: 4, y: 8), poisoned,
            .init(id: 2, type: 4, fraction: 1, x: 4, y: 9),
            .init(id: 3, type: 0, fraction: 1, x: 6, y: 8),
            .init(id: 4, type: 0, fraction: 1, x: 7, y: 8),
            .init(id: 5, type: 0, fraction: 2, x: 3, y: 8),
            .init(id: 6, type: 0, fraction: 1, x: 3, y: 8)
        ]))
        try game.endUnitAction(unitID: 0, recordPresentation: true)
        #expect(game.unit(id: 0)?.isBlessed == false)
        #expect(game.unit(id: 1)?.effects == 3)
        #expect(game.unit(id: 1)?.statusOffenseBonus == 0)
        #expect(game.unit(id: 3)?.isBlessed == true)
        #expect(game.unit(id: 4)?.isBlessed == false)
        #expect(game.unit(id: 6)?.isBlessed == false) // Enemy is the first living occupant.
        #expect(game.pendingPresentation?.scene?.durationTicks == 0)
        game.completePresentation()
        try game.endUnitAction(unitID: 2)
        #expect(game.unit(id: 1)?.effects == 3)
        var turnState = game.snapshot
        turnState.units.removeAll { $0.id == 5 || $0.id == 6 }
        game = try make(turnState, immobilized: true)
        _ = try game.endPlayerTurn(recordPresentation: true)
        let firstTurn = try #require(game.pendingPresentation)
        #expect(firstTurn.unitsBefore.first { $0.id == 1 }?.isBlessed == true)
        #expect(game.unit(id: 1)?.effects == 0)
    }

    @Test func promotionHappensAfterCounterattackAndOnlyOnceForSurvivors() throws {
        let units: [CampaignUnitState] = [
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 0, fraction: 2, x: 5, y: 8)
        ]
        var baseline = try make(state(units))
        let expected = try baseline.attack(unitID: 0, targetID: 1)
        var experienced = units
        experienced[0].experience = 100_000
        var game = try make(state(experienced))
        let result = try game.attack(unitID: 0, targetID: 1)
        #expect(result == expected)
        #expect(game.unit(id: 0)?.rank == 1)
        experienced[0].hitPoints = 1
        game = try make(state(experienced))
        _ = try game.attack(unitID: 0, targetID: 1)
        #expect(game.unit(id: 0)?.isAlive == false)
        #expect(game.unit(id: 0)?.rank == 0)
    }

    @Test func promotionUsesExactDamageWeightedThresholdWithoutHealing() throws {
        let units: [CampaignUnitState] = [
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 0, fraction: 2, x: 5, y: 8)
        ]
        let profiles = try GameResources.campaignUnitProfiles()
        let soldier = try #require(profiles[0])
        #expect(soldier.nextRankExperience(atRank: 0) == 7333)
        #expect(soldier.nextRankExperience(atRank: 1) == 7733)
        var baseline = try make(state(units))
        let combat = try baseline.attack(unitID: 0, targetID: 1)
        let gained = soldier.quality(atRank: 0) * combat.damage
        #expect(baseline.unit(id: 0)?.experience == gained)
        #expect(baseline.unit(id: 1)?.experience == soldier.quality(atRank: 0) * combat.counterDamage)
        for offset in [-1, 0, 1] {
            var prepared = units
            prepared[0].experience = 7333 - gained + offset
            var game = try make(state(prepared))
            let result = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
            #expect(result == combat)
            #expect(game.unit(id: 0)?.rank == (offset < 0 ? 0 : 1))
            #expect(game.unit(id: 0)?.experience == (offset < 0 ? 7332 : offset))
            #expect(game.unit(id: 0)?.hitPoints == baseline.unit(id: 0)?.hitPoints)
            #expect(game.snapshot.pendingPresentations?.filter { $0.scene?.kind == .levelUp }.count == (offset < 0 ? 0 : 1))
        }
    }

    @Test func bothSurvivorsPromoteAfterCombatAndRestoreEachSceneWithoutRepeatingRewards() throws {
        var game = try make(state([
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8, rank: 1, experience: 100_000),
            .init(id: 1, type: 0, fraction: 2, x: 5, y: 8, rank: 1, experience: 100_000)
        ]))
        _ = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
        let queue = try #require(game.snapshot.pendingPresentations)
        #expect(queue.map { $0.scene?.kind } == [nil, .levelUp, .rankName, .levelUp, .rankName])
        #expect(queue.dropFirst().map(\.unitID) == [0, 0, 1, 1])
        #expect(queue[0].combat?.attacker.rank == 1)
        #expect(queue[0].combat?.defender.rank == 1)
        #expect(game.unit(id: 0)?.rank == 2)
        #expect(game.unit(id: 1)?.rank == 2)
        let committed = game.snapshot
        while game.pendingPresentation != nil {
            game = try make(roundTrip(game.snapshot))
            #expect(!game.isInputEnabled)
            game.completePresentation()
            #expect(game.snapshot.units == committed.units)
            #expect(game.snapshot.randomState == committed.randomState)
        }
        #expect(game.isInputEnabled)
    }

    @Test func deathPresentationPrecedesPromotionAndDeadDefenderCannotPromote() throws {
        var game = try make(state([
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8, experience: 100_000),
            .init(id: 1, type: 0, fraction: 2, x: 5, y: 8, hitPoints: 1, experience: 100_000)
        ]))
        _ = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
        #expect(game.snapshot.pendingPresentations?.map { $0.scene?.kind } == [nil, .death, .levelUp])
        #expect(game.unit(id: 1)?.rank == 0)
        #expect(game.unit(id: 0)?.rank == 1)
    }

    @Test func rankNamesChangeOnlyAtTwoFourAndSixAndCommanderKeepsIdentity() throws {
        for type in 0...11 where type != 9 {
            for rank in 0...12 {
                let unit = CampaignUnitState(id: 0, type: type, fraction: 1, x: 0, y: 0, rank: rank)
                #expect(unit.nameTextID == 139 + type * 4 + min(rank / 2, 3))
                let text = try GameResources.campaignStoryText(id: unit.nameTextID)
                let name = try #require(text)
                #expect(!name.isEmpty)
            }
        }
        for type in [0, 9] {
            for rank in [1, 3, 5, 7, 9] {
                var game = try make(state([
                    .init(id: 0, type: type, fraction: 1, x: 4, y: 8, rank: rank, experience: 100_000),
                    .init(id: 1, type: 0, fraction: 2, x: 5, y: 8)
                ]))
                _ = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
                #expect(game.unit(id: 0)?.rank == rank + 1)
                #expect(game.snapshot.pendingPresentations?.contains { $0.scene?.kind == .rankName } == (type != 9 && rank < 6))
                if type == 9 { #expect(game.unit(id: 0)?.nameTextID == 93) }
            }
        }
    }

    @Test func summoningAllowsOccupiedGraveCostsNothingIgnoresCapAndRestoresOnce() throws {
        var snapshot = state([
            .init(id: 0, type: 3, fraction: 1, x: 4, y: 8), grave(id: 1, x: 5, y: 8),
            .init(id: 2, type: 0, fraction: 2, x: 5, y: 8), grave(id: 3, x: 6, y: 8)
        ])
        snapshot.moneyByFraction = [1: 0]
        snapshot.unitCap = 1
        var game = try make(snapshot)
        #expect(game.summonableCorpses(for: 0).map(\.id) == [1])
        let id = try game.raiseSkeleton(unitID: 0, corpseID: 1, recordPresentation: true)
        let committed = game.snapshot
        #expect(game.pendingPresentation?.scene?.durationTicks == 8)
        #expect(game.pendingPresentation?.unitsBefore.first { $0.id == 1 }?.isCorpse == true)
        #expect(game.pendingPresentation?.unitsBefore.contains { $0.id == id } == false)
        #expect(game.unit(id: id)?.type == 10)
        #expect(game.unit(id: id)?.hitPoints == 100)
        #expect(game.unit(id: id)?.hasActed == true)
        #expect(game.snapshot.lastFinishedMoveUnitID == id)
        #expect(game.money(for: 1) == 0)
        #expect(game.units(at: GridPoint(x: 5, y: 8)).count == 2)
        game = try make(roundTrip(committed))
        #expect(throws: CampaignSessionError.summoningUnavailable) { try game.raiseSkeleton(unitID: 0, corpseID: 1) }
        game.completePresentation()
        #expect(game.snapshot.units == committed.units)
        #expect(game.snapshot.randomState == committed.randomState)
        #expect(throws: CampaignSessionError.summoningUnavailable) { try game.raiseSkeleton(unitID: 0, corpseID: 1) }
    }

    @Test func enemySorceressRaisesBeforeTurnEndsAndSkeletonDoesNotActAgain() throws {
        let snapshot = state([.init(id: 0, type: 3, fraction: 2, x: 4, y: 8), grave(id: 1, x: 5, y: 8)])
        var game = try make(snapshot, immobilized: true)
        let actions = try game.endPlayerTurn(recordPresentation: true)
        #expect(actions.first { $0.unitID == 0 }?.summonedCorpseID == 1)
        let skeleton = try #require(game.snapshot.units.first { $0.type == 10 })
        #expect(actions.contains { $0.unitID == skeleton.id } == false)
        let queue = try #require(game.snapshot.pendingPresentations)
        #expect(queue.contains { $0.scene?.kind == .targeting && $0.scene?.attackRange?.count == 4 })
        let raising = try #require(queue.firstIndex { $0.scene?.kind == .summoning })
        #expect(queue[raising].scene?.durationTicks == 8)
        let nextTurn = try #require(queue.last { $0.scene?.kind == .newTurn })
        #expect(nextTurn.unitsBefore.contains { $0.id == skeleton.id && $0.hitPoints == 100 })
        let committed = game.snapshot
        while game.pendingPresentation != nil {
            game = try make(roundTrip(game.snapshot), immobilized: true)
            game.completePresentation()
        }
        #expect(game.snapshot.units == committed.units)
        #expect(game.snapshot.moneyByFraction == committed.moneyByFraction)
    }

    @Test func legacyUnitWithoutOptionalFieldsStillDecodes() throws {
        let json = #"{"id":1,"type":0,"fraction":2,"x":5,"y":8,"hitPoints":0,"rank":0,"experience":0,"hasMoved":false,"hasActed":true}"#
        let unit = try JSONDecoder().decode(CampaignUnitState.self, from: Data(json.utf8))
        #expect(!unit.isAlive && !unit.isCorpse)
        #expect(unit.effects == nil && unit.removedFromMap == nil)
    }

    private func grave(id: Int, x: Int, y: Int, type: Int = 0) -> CampaignUnitState {
        var result = CampaignUnitState(id: id, type: type, fraction: 2, x: x, y: y, hitPoints: 0)
        result.corpseTransitionsRemaining = 3
        return result
    }
    private func state(_ units: [CampaignUnitState]) -> CampaignSnapshot {
        var result = CampaignSnapshot(mapID: "m0", units: units + [
            .init(id: 900, type: 9, fraction: 1, x: 0, y: 11),
            .init(id: 901, type: 11, fraction: 2, x: 11, y: 0)
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        result.tileOverrides = Dictionary(uniqueKeysWithValues: (0..<144).map { ($0, 18) })
        return result
    }
    private func make(_ state: CampaignSnapshot, immobilized: Bool = false) throws -> CampaignSession {
        let original = try GameResources.campaignRuleset()
        let rules = CampaignRuleset(terrainByTileID: original.terrainByTileID,
            movementCostByTerrainID: immobilized ? original.movementCostByTerrainID.mapValues { _ in 1000 } : original.movementCostByTerrainID,
            defenseByTerrainID: original.defenseByTerrainID)
        return try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: rules, snapshot: state)
    }
    private func roundTrip(_ value: CampaignSnapshot) throws -> CampaignSnapshot {
        try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(value))
    }
}
