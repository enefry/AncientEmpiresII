import AECore
import AEContent
import Foundation
import Testing

struct CampaignBuildingTests {
    private let castle = GridPoint(x: 4, y: 8)

    @Test func repairingThenOccupyingConsumesTwoActionsAndChangesIncome() throws {
        var snapshot = state(units: [
            .init(id: 0, type: 9, fraction: 1, x: 1, y: 10),
            .init(id: 1, type: 0, fraction: 1, x: 4, y: 8),
            .init(id: 2, type: 11, fraction: 2, x: 10, y: 0)
        ])
        snapshot.tileOverrides = [100: 27]
        snapshot.moneyByFraction = [1: 100]
        var session = try restore(snapshot)
        #expect(session.availableBuildingAction(unitID: 1) == .repair)
        try session.performBuildingAction(unitID: 1, recordPresentation: true)
        #expect(session.map.tileID(x: 4, y: 8) == 37)
        #expect(session.money(for: 1) == 100)
        #expect(session.unit(id: 1)?.hasActed == true)
        #expect(session.pendingPresentation?.scene?.textID == 74)
        #expect(session.availableBuildingAction(unitID: 1) == nil)
        let committed = session.snapshot
        session = try restore(committed)
        #expect(session.snapshot == committed)
        session.completePresentation()
        _ = try session.endPlayerTurn()
        #expect(session.money(for: 1) == 100) // Repaired village is neutral.
        #expect(session.availableBuildingAction(unitID: 1) == .occupy)
        try session.performBuildingAction(unitID: 1, recordPresentation: true)
        #expect(session.map.tileID(x: 4, y: 8) == 39)
        #expect(session.presentationMap.tileID(x: 4, y: 8) == 39)
        #expect(session.pendingPresentation?.scene?.textID == 73)
        session = try restore(session.snapshot)
        session.completePresentation()
        _ = try session.endPlayerTurn()
        #expect(session.money(for: 1) == 130)
    }

    @Test func onlyEligibleUnitsCanCaptureAndLandingDoesNotCapture() throws {
        var snapshot = state(units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 9),
            .init(id: 1, type: 0, fraction: 1, x: 5, y: 8),
            .init(id: 2, type: 1, fraction: 1, x: 6, y: 8),
            .init(id: 3, type: 11, fraction: 2, x: 10, y: 0)
        ])
        snapshot.tileOverrides = [100: 42, 101: 38, 102: 37]
        var session = try restore(snapshot)
        #expect(session.availableBuildingAction(unitID: 1) == nil) // Soldier cannot capture a castle.
        #expect(session.availableBuildingAction(unitID: 2) == nil) // Archer cannot capture towns.
        let before = session.snapshot
        #expect(throws: CampaignSessionError.buildingActionUnavailable) { try session.performBuildingAction(unitID: 1) }
        #expect(session.snapshot == before)
        try session.move(unitID: 0, to: castle)
        #expect(session.map.tileID(x: 4, y: 8) == 42)
        #expect(session.availableBuildingAction(unitID: 0) == .occupy)
        try session.performBuildingAction(unitID: 0)
        #expect(session.map.tileID(x: 4, y: 8) == 40)
        #expect(session.availableBuildingAction(unitID: 0) == nil)
        #expect(session.snapshot.selection == nil)
    }

    @Test func enemyCaptureShowsOldOwnershipUntilItsActionAndRestoresEachStep() throws {
        var snapshot = state(units: [
            .init(id: 0, type: 9, fraction: 1, x: 1, y: 10),
            .init(id: 1, type: 0, fraction: 2, x: 10, y: 0)
        ])
        snapshot.tileOverrides = [10: 37]
        let original = try GameResources.campaignRuleset()
        let rules = CampaignRuleset(terrainByTileID: original.terrainByTileID,
            movementCostByTerrainID: original.movementCostByTerrainID.mapValues { _ in 1000 },
            defenseByTerrainID: original.defenseByTerrainID)
        var session = try restore(snapshot, rules: rules)
        _ = try session.endPlayerTurn(recordPresentation: true)
        #expect(session.map.tileID(x: 10, y: 0) == 41)
        #expect(session.presentationMap.tileID(x: 10, y: 0) == 37)
        let committed = session.snapshot
        session = try restore(committed, rules: rules)
        while session.pendingPresentation != nil, session.pendingPresentation?.scene?.kind != .building {
            #expect(session.presentationMap.tileID(x: 10, y: 0) == 37)
            session.completePresentation()
        }
        #expect(session.pendingPresentation?.scene?.textID == 73)
        #expect(session.presentationMap.tileID(x: 10, y: 0) == 41)
        while session.pendingPresentation != nil {
            session = try restore(session.snapshot, rules: rules)
            session.completePresentation()
        }
        #expect(session.snapshot.units == committed.units)
        #expect(session.snapshot.moneyByFraction == committed.moneyByFraction)
        #expect(session.map.buildings.first?.fraction == 2)
    }

    @Test func recruitmentDeductsOnceAndCannotAbandonDeploymentAcrossRestore() throws {
        var snapshot = state(units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 11, fraction: 2, x: 10, y: 0)
        ])
        snapshot.tileOverrides = [100: 40]
        snapshot.moneyByFraction = [1: 500]
        var session = try restore(snapshot)
        let id = try session.recruit(type: 0, at: castle)
        #expect(session.money(for: 1) == 350)
        #expect(session.units(at: castle).count == 2)
        let committed = session.snapshot
        session = try restore(committed)
        session.clearSelection()
        #expect(session.snapshot.selection?.unitID == id)
        #expect(throws: CampaignSessionError.gameNotReady) { try session.selectUnit(id: 0) }
        #expect(throws: CampaignSessionError.gameNotReady) { try session.endPlayerTurn() }
        #expect(throws: CampaignSessionError.gameNotReady) { try session.endUnitAction(unitID: id) }
        #expect(throws: CampaignSessionError.destinationUnreachable) { try session.move(unitID: id, to: castle) }
        #expect(throws: CampaignSessionError.recruitmentUnavailable) { try session.recruit(type: 0, at: castle) }
        #expect(session.snapshot == committed)
        let destination = GridPoint(x: 4, y: 7)
        try session.move(unitID: id, to: destination)
        session = try restore(session.snapshot)
        try session.cancelMove() // Original action-menu cancel returns to the castle, without refund.
        #expect(session.unit(id: id)?.position == castle)
        #expect(session.snapshot.recruitingUnitID == id)
        #expect(session.money(for: 1) == 350)
        try session.move(unitID: id, to: destination)
        try session.endUnitAction(unitID: id)
        #expect(session.snapshot.recruitingUnitID == nil)
        #expect(session.unit(id: id)?.hasActed == true)
        #expect(session.money(for: 1) == 350)
    }

    @Test func recruitmentChecksFundsUnlockCapAndEmptyCastlePlacement() throws {
        var snapshot = state(units: [
            .init(id: 0, type: 9, fraction: 1, x: 1, y: 10),
            .init(id: 1, type: 10, fraction: 1, x: 3, y: 10),
            .init(id: 2, type: 11, fraction: 2, x: 10, y: 0)
        ])
        snapshot.tileOverrides = [100: 40]
        snapshot.moneyByFraction = [1: 149]
        snapshot.unitCap = 2
        var session = try restore(snapshot)
        #expect(session.recruitmentOptions(at: castle).map(\.unit.type) == [0])
        #expect(session.recruitmentOptions(at: castle).first?.isAvailable == false)
        let before = session.snapshot
        #expect(throws: CampaignSessionError.recruitmentUnavailable) { try session.recruit(type: 1, at: castle) }
        #expect(throws: CampaignSessionError.recruitmentUnavailable) { try session.recruit(type: 0, at: castle) }
        #expect(session.snapshot == before)
        snapshot.moneyByFraction = [1: 150]
        session = try restore(snapshot)
        let id = try session.recruit(type: 0, at: castle) // Skeleton excluded from the cap.
        try session.move(unitID: id, to: castle)
        try session.endUnitAction(unitID: id)
        #expect(session.money(for: 1) == 0)
        #expect(session.unit(id: id)?.position == castle)
        snapshot = session.snapshot
        snapshot.moneyByFraction = [1: 1000]
        session = try restore(snapshot)
        #expect(session.recruitmentOptions(at: castle).first?.isAvailable == false)
    }

    @Test func occupiedCastleWithoutReachableEmptySquareRejectsPurchaseAtomically() throws {
        var snapshot = state(units: [
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 0, fraction: 2, x: 4, y: 7),
            .init(id: 2, type: 0, fraction: 2, x: 4, y: 9),
            .init(id: 3, type: 0, fraction: 2, x: 3, y: 8),
            .init(id: 4, type: 0, fraction: 2, x: 5, y: 8)
        ])
        snapshot.tileOverrides = [100: 40]
        snapshot.moneyByFraction = [1: 1000]
        var session = try restore(snapshot)
        #expect(session.recruitmentOptions(at: castle).first?.isAvailable == false)
        #expect(throws: CampaignSessionError.recruitmentUnavailable) { try session.recruit(type: 0, at: castle) }
        #expect(session.snapshot == snapshot)
    }

    @Test func deadCommanderIsListedFirstAndRevivesWithIdentityRankAndExperience() throws {
        var dead = CampaignUnitState(id: 5, type: 9, fraction: 1, x: -10, y: -10, hitPoints: 0, rank: 3, experience: 900)
        dead.revivalCost = 600
        var snapshot = state(units: [
            .init(id: 0, type: 9, fraction: 1, x: 1, y: 10), dead,
            .init(id: 6, type: 11, fraction: 2, x: 10, y: 0)
        ])
        snapshot.tileOverrides = [100: 40]
        snapshot.moneyByFraction = [1: 600]
        var session = try restore(snapshot)
        let options = session.recruitmentOptions(at: castle)
        #expect(options.map(\.unit.type) == [9, 0])
        #expect(options.first?.cost == 600)
        #expect(try session.recruit(type: 9, commanderID: 5, at: castle) == 5)
        session = try restore(session.snapshot)
        #expect(session.unit(id: 5)?.hitPoints == 100)
        #expect(session.unit(id: 5)?.rank == 3)
        #expect(session.unit(id: 5)?.experience == 900)
        #expect(session.unit(id: 5)?.revivalCost == 600)
        #expect(session.snapshot.units.count == 3)
        #expect(session.money(for: 1) == 0)
    }

    @Test func commanderDeathRaisesRevivalPriceWithThousandGoldCap() throws {
        for price in [200, 600, 1000] {
            var target = CampaignUnitState(id: 1, type: 9, fraction: 2, x: 5, y: 8, hitPoints: 1)
            target.revivalCost = price
            let snapshot = state(units: [.init(id: 0, type: 9, fraction: 1, x: 4, y: 8, rank: 20), target,
                                        .init(id: 2, type: 11, fraction: 2, x: 10, y: 0)])
            var session = try restore(snapshot)
            _ = try session.attack(unitID: 0, targetID: 1)
            #expect(session.unit(id: 1)?.hitPoints == 0)
            #expect(session.unit(id: 1)?.revivalCost == min(1000, price + 200))
        }
    }

    @Test func oldSavesAndInvalidOverridesAreHandledWithoutCrashes() throws {
        var snapshot = state(units: [.init(id: 0, type: 9, fraction: 1, x: 1, y: 10)])
        let old = try restore(snapshot)
        #expect(old.snapshot.tileOverrides == nil)
        for overrides in [[-1: 40], [144: 40], [10: 999]] {
            snapshot.tileOverrides = overrides
            #expect(throws: CampaignSessionError.invalidSnapshot) { try restore(snapshot) }
        }
    }

    private func state(units: [CampaignUnitState]) -> CampaignSnapshot {
        CampaignSnapshot(mapID: "m0", units: units, randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
    }

    private func restore(_ snapshot: CampaignSnapshot, rules: CampaignRuleset? = nil) throws -> CampaignSession {
        let object: [String: Any] = ["id": "m0", "kind": "campaign", "index": 0, "name": "Building fixture",
            "width": 12, "height": 12, "rows": Array(repeating: Array(repeating: 1, count: 12), count: 12), "units": [], "buildings": []]
        let map = try JSONDecoder().decode(CampaignMap.self, from: JSONSerialization.data(withJSONObject: object))
        let decoded = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(snapshot))
        return try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: rules ?? GameResources.campaignRuleset(), snapshot: decoded)
    }
}
