import AEContent
import AECore
import Testing

struct GameResourcesTests {
    @Test func originalSoldierArtIsAvailableForEveryTeam() throws {
        for faction in ["blue", "red", "green", "black"] {
            for name in ["soldier", "unit_icons"] {
                #expect(try !GameResources.originalResourceData(at: "res/\(faction)/\(name).png").isEmpty)
            }
        }
        #expect(try GameResources.sourceManifest().resourceCount == 334)
    }

    @Test func bundledMissionAndReferenceDataAreComplete() throws {
        let map = try GameResources.missionZero()
        let expectedSineTable = try GameResources.verifiedSineTable()

        #expect(map.id == "m0")
        #expect(map.name == "The Temple of Courage")
        #expect(map.width == 12 && map.height == 12)
        #expect(map.rows.count == map.height)
        #expect(map.units.count == 6)
        #expect(map.tileID(x: 0, y: 0) == 17)
        #expect(map.tileID(x: 12, y: 0) == nil)
        #expect(expectedSineTable == StartupAnimationState.sineTable())
    }

    @Test func exportedContentContainsEveryMapAndOriginalRenderAsset() throws {
        let mapIDs = try GameResources.campaignMapIDs()
        let maps = try mapIDs.map { try GameResources.campaignMap(named: $0) }
        let terrain = try GameResources.terrainCatalog()
        let units = try GameResources.unitDefinitions()
        let manifest = try GameResources.sourceManifest()

        #expect(mapIDs.count == 20)
        #expect(mapIDs.first == "m0")
        #expect(maps.allSatisfy { map in
            map.rows.count == map.height && map.rows.allSatisfy { row in row.count == map.width }
        })
        #expect(terrain.tileCount == 48)
        #expect(terrain.tiles.map(\.id).sorted() == Array(0..<48))
        #expect(units.count == 12)
        #expect(units[0].resourceName == "soldier")
        #expect(try !GameResources.originalResourceData(at: "res/tiles0_17.png").isEmpty)
        #expect(try !GameResources.originalResourceData(at: "res/blue/unit_icons.png").isEmpty)
        #expect(manifest.schemaVersion == 1)
        #expect(manifest.mapCount == 20)
        #expect(manifest.resourceCount == 334)
        #expect(manifest.sourceJar.sha256 == "f2b3e06190014940a04da70677d6504dd3fd3d9fbc39a49192926293f830f5ae")
    }

    @Test func missionZeroMovementUsesExportedTerrainAndUnitDefinitions() throws {
        let map = try GameResources.missionZero()
        let terrain = try GameResources.terrainCatalog()
        let units = try GameResources.unitDefinitions()
        let terrainByTileID = Dictionary(uniqueKeysWithValues: terrain.tiles.map { ($0.id, $0.terrain) })
        let terrainIDs = map.rows.map { row in
            row.compactMap { terrainByTileID[$0] }
        }
        #expect(terrainIDs.map(\.count) == Array(repeating: map.width, count: map.height))
        guard
            let soldierRecord = map.units.first(where: { $0.type == 0 && $0.fraction == 1 }),
            let soldier = units.first(where: { $0.id == soldierRecord.type })
        else {
            #expect(Bool(false))
            return
        }
        let grid = MovementGrid(
            terrainIDs: terrainIDs,
            stepCostByTerrainID: Dictionary(uniqueKeysWithValues: terrain.types.map { ($0.id, $0.steps) }),
            occupants: map.units.filter(\.inBounds).map {
                MovementOccupant(position: GridPoint(x: $0.x, y: $0.y), teamID: $0.fraction)
            }
        )
        let origin = GridPoint(x: soldierRecord.x, y: soldierRecord.y)
        let range = try MovementRangeCalculator.calculate(
            on: grid,
            from: origin,
            teamID: soldierRecord.fraction,
            profile: soldier.movementProfile
        )

        #expect(range.reachableDestinations.contains(GridPoint(x: 0, y: 7)))
        #expect(range.reachableDestinations.contains(GridPoint(x: 0, y: 9)))
        #expect(!range.reachableDestinations.contains(origin))
        #expect(units.first(where: { $0.id == 2 })?.movementProfile.isAmphibious == true)
        #expect(units.first(where: { $0.id == 8 })?.movementProfile.canFly == true)
        #expect(units.first(where: { $0.id == 11 })?.movementProfile.isCrystal == true)
    }
}
