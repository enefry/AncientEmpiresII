import AEContent
import AECore
import Foundation
import Testing

@Suite struct SkirmishTests {
    @Test(arguments: (0..<12).map { "s\($0)" } + (0..<8).map { "b\($0)" })
    func everyBuiltinMapStartsRunsCPUAndRestores(id: String) throws {
        let map = try GameResources.campaignMap(named: id)
        let settings = SkirmishConfiguration(map: map)
        var game = try make(map, settings)
        #expect(game.isInputEnabled)
        #expect(game.snapshot.activeFraction == map.skirmishFractions.first)
        #expect(game.allowedUnitType == 8)
        for _ in 0..<2 {
            _ = try game.endPlayerTurn(recordPresentation: true)
            let encoded = try JSONEncoder().encode(game.snapshot)
            let restored = try JSONDecoder().decode(CampaignSnapshot.self, from: encoded)
            game = try CampaignSession(map: #require(restored.skirmishMap), profiles: GameResources.campaignUnitProfiles(),
                                       rules: GameResources.campaignRuleset(), snapshot: restored)
            #expect(game.snapshot == restored)
            while game.pendingPresentation != nil { game.completePresentation() }
            if game.snapshot.phase == .victory || game.snapshot.phase == .defeat { break }
            #expect(game.isInputEnabled)
        }
        #expect(game.snapshot.missionRuntime == nil)
        #expect(game.snapshot.storyMessages.isEmpty)
    }

    @Test func fourHumanSidesRotateAndRecruitUsingTheirOwnGold() throws {
        let map = try GameResources.campaignMap(named: "s3")
        var settings = SkirmishConfiguration(map: map)
        for index in settings.players.indices { settings.players[index].control = .human }
        var game = try make(map, settings)
        for player in settings.players {
            #expect(game.snapshot.activeFraction == player.fraction)
            let king = try #require(game.snapshot.units.first { $0.fraction == player.fraction })
            try game.selectUnit(id: king.id)
            #expect(try game.movementRange(for: king.id) != nil)
            try game.endUnitAction(unitID: king.id)
            let castle = try #require(game.map.buildings.first { $0.fraction == player.fraction && $0.terrain == 9 })
            let point = GridPoint(x: castle.x, y: castle.y)
            #expect(game.canOpenRecruitment(at: point))
            let balance = game.money(for: player.fraction)
            let otherBalances = Dictionary(uniqueKeysWithValues: settings.players.filter { $0.fraction != player.fraction }.map { ($0.fraction, game.money(for: $0.fraction)) })
            let option = try #require(game.recruitmentOptions(at: point).first { $0.isAvailable })
            let id = try game.recruit(type: option.unit.type, at: point)
            #expect(game.unit(id: id)?.fraction == player.fraction)
            #expect(game.money(for: player.fraction) == balance - option.cost)
            for (fraction, money) in otherBalances { #expect(game.money(for: fraction) == money) }
            let unit = try #require(game.unit(id: id))
            let range = try #require(try game.movementRange(for: id))
            let destination = try #require(range.reachableDestinations.sorted { $0.x == $1.x ? $0.y < $1.y : $0.x < $1.x }.first)
            try game.move(unitID: unit.id, to: destination)
            try game.endUnitAction(unitID: unit.id)
            _ = try game.endPlayerTurn()
        }
        #expect(game.snapshot.activeFraction == settings.players.first?.fraction)
    }

    @Test func sameTeamMovementAttackCaptureAndHealing() throws {
        let map = try importedMap()
        var settings = SkirmishConfiguration(map: map)
        for index in settings.players.indices { settings.players[index].control = .human }
        settings.players[0].team = 1
        settings.players[1].team = 1
        var game = try make(map, settings)
        let king = try #require(game.snapshot.units.first { $0.fraction == 1 })
        let ally = try #require(game.snapshot.units.first { $0.fraction == 2 })
        #expect(!game.attackableUnits(for: king.id).contains { $0.id == ally.id })
        #expect(throws: CampaignSessionError.targetUnavailable) { try game.attack(unitID: king.id, targetID: ally.id) }
        let range = try #require(try game.movementRange(for: king.id))
        #expect(!range.reachableDestinations.contains(ally.position))
        #expect(range.remainingBudget(at: GridPoint(x: 2, y: 0)) != nil)
        // Put a blue king on the allied red castle without sharing its unit's cell.
        var state = game.snapshot
        state.units[0].x = 1; state.units[0].y = 0; state.units[0].hitPoints = 60
        state.units[1].x = 2; state.units[1].y = 0
        game = try make(map, settings, snapshot: state)
        #expect(game.availableBuildingAction(unitID: king.id) == nil)
        #expect(!game.canOpenRecruitment(at: GridPoint(x: 1, y: 0)))
        for _ in 0..<3 { _ = try game.endPlayerTurn() }
        #expect(game.unit(id: king.id)?.hitPoints == 80)
    }

    @Test func disabledSideIsRemovedAndBuildingsBecomeNeutral() throws {
        let map = try GameResources.campaignMap(named: "s3")
        var settings = SkirmishConfiguration(map: map)
        settings.players[1].control = .off
        let game = try make(map, settings)
        let disabled = settings.players[1].fraction
        #expect(!game.snapshot.units.contains { $0.fraction == disabled })
        #expect(!game.map.buildings.contains { $0.fraction == disabled })
        for building in map.buildings where building.fraction == disabled {
            #expect(game.map.tileID(x: building.x, y: building.y) == 37 + (building.tile - 37) % 2)
        }
    }

    @Test func hotseatTurnPresentationFocusesTheNextCommander() throws {
        let map = try GameResources.campaignMap(named: "s0")
        var settings = SkirmishConfiguration(map: map)
        for index in settings.players.indices { settings.players[index].control = .human }
        var game = try make(map, settings)
        _ = try game.endPlayerTurn(recordPresentation: true)
        let scene = try #require(game.pendingPresentation?.scene)
        let commander = try #require(game.snapshot.units.first { $0.fraction == game.snapshot.activeFraction && $0.type == 9 })
        #expect(scene.kind == .newTurn)
        #expect(scene.focus == commander.position)
        #expect(scene.turn?.fraction == commander.fraction)
    }

    @Test func finalCastleCaptureWinsForTeamAndDeadKingSaveRestores() throws {
        let map = try importedMap()
        var settings = SkirmishConfiguration(map: map)
        for index in settings.players.indices { settings.players[index].control = .human }
        settings.players[1].team = 1
        var game = try make(map, settings)
        var state = game.snapshot
        state.units[2].hitPoints = 0; state.units[2].x = -10; state.units[2].y = -10
        state.units[0].x = 4; state.units[0].y = 4
        game = try make(map, settings, snapshot: state)
        try game.performBuildingAction(unitID: state.units[0].id, recordPresentation: true)
        #expect(game.snapshot.phase == .victory)
        #expect(game.snapshot.winningTeam == 1)
        let restored = try make(map, settings, snapshot: JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(game.snapshot)))
        #expect(restored.snapshot == game.snapshot)
    }

    @Test func invalidTeamsAreRejectedAndAllCPUsYieldBetweenTurns() throws {
        let map = try importedMap()
        var settings = SkirmishConfiguration(map: map)
        for index in settings.players.indices { settings.players[index].team = 1 }
        #expect(throws: SkirmishError.insufficientTeams) { try settings.validate(for: map) }
        settings = SkirmishConfiguration(map: map)
        for index in settings.players.indices { settings.players[index].control = .cpu }
        try settings.validate(for: map)
        var game = try make(map, settings)
        #expect(game.snapshot.phase == .enemyTurn)
        for _ in 0..<2 {
            _ = game.advanceAutomaticSkirmishTurn()
            #expect(game.pendingPresentation != nil)
            let committed = game.snapshot
            #expect(game.advanceAutomaticSkirmishTurn().isEmpty)
            #expect(game.snapshot == committed)
            while game.pendingPresentation != nil { game.completePresentation() }
        }
        #expect(game.snapshot.phase == .enemyTurn || game.snapshot.phase == .defeat)
        #expect(!game.isInputEnabled)
    }

    @Test func binaryMapsMatchExportedContent() throws {
        for index in 0..<12 {
            let map = try GameResources.campaignMap(named: "s\(index)")
            let data = try GameResources.originalResourceData(at: "res/s\(index).aem")
            let parsed = try SkirmishMapImporter.decode(data, filename: "s\(index).aem")
            #expect(parsed.rows == map.rows)
            #expect(parsed.units == map.units)
            #expect(parsed.skirmishFractions == map.skirmishFractions)
            #expect(throws: SkirmishError.invalidMap) { try SkirmishMapImporter.decode(data.dropLast(), filename: "bad.aem") }
            #expect(throws: SkirmishError.invalidMap) { try SkirmishMapImporter.decode(data + Data([0]), filename: "bad.aem") }
        }
    }

    @Test func rejectsMalformedImportIncludingOverflowAndDuplicateCoordinates() throws {
        let map = try importedMap()
        let json = try JSONEncoder().encode(map)
        let roundtrip = try SkirmishMapImporter.decode(json, filename: "map.json")
        #expect(roundtrip.rows == map.rows)
        var document = try #require(JSONSerialization.jsonObject(with: json) as? [String: Any])
        document["width"] = Int.max
        #expect(throws: SkirmishError.invalidMap) { try SkirmishMapImporter.decode(JSONSerialization.data(withJSONObject: document), filename: "bad.json") }
        document["width"] = map.width
        document["units"] = [["type": 9, "fraction": 1, "x": 0, "y": 0], ["type": 9, "fraction": 2, "x": 0, "y": 0]]
        #expect(throws: SkirmishError.invalidMap) { try SkirmishMapImporter.decode(JSONSerialization.data(withJSONObject: document), filename: "bad.json") }
        #expect(throws: SkirmishError.oversizedFile) { try SkirmishMapImporter.decode(Data(repeating: 0, count: SkirmishMapImporter.maximumFileSize + 1), filename: "bad.aem") }
    }

    private func importedMap() throws -> CampaignMap {
        let json = """
        {"name":"Team test","width":5,"height":5,
         "rows":[[40,42,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,44]],
         "units":[{"type":9,"fraction":1,"x":0,"y":0},{"type":9,"fraction":2,"x":1,"y":0},{"type":9,"fraction":3,"x":4,"y":4}]}
        """
        return try SkirmishMapImporter.decode(Data(json.utf8), filename: "team.json")
    }

    private func make(_ map: CampaignMap, _ settings: SkirmishConfiguration, snapshot: CampaignSnapshot? = nil) throws -> CampaignSession {
        try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(),
                            seed: 42, skirmish: settings, snapshot: snapshot)
    }
}
