import AEContent
import AECore
import Foundation
import Testing

struct BonusSkirmishMapsTests {
    @Test func catalogIncludesEightDistinctBonusMapsAndPreservesOriginalCatalog() throws {
        let ids = try GameResources.skirmishMapIDs()
        #expect(ids == (0..<12).map { "s\($0)" } + (0..<8).map { "b\($0)" })
        #expect(try GameResources.campaignMapIDs().count == 20)
        #expect(try GameResources.sourceManifest().mapCount == 20)
        let original = try (0..<12).map { try GameResources.campaignMap(named: "s\($0)") }
        let expected = [(16, 15, 4, 4), (17, 12, 3, 3), (13, 13, 2, 6), (11, 18, 2, 2),
                        (15, 15, 4, 4), (17, 12, 3, 3), (10, 10, 2, 2), (13, 19, 3, 3)]
        var bonus: [CampaignMap] = []
        for (index, dimensions) in expected.enumerated() {
            let map = try GameResources.campaignMap(named: "b\(index)")
            try SkirmishMapImporter.validate(map)
            #expect(map.width == dimensions.0 && map.height == dimensions.1)
            #expect(map.skirmishFractions.count == dimensions.2)
            #expect(map.units.count == dimensions.3)
            #expect(!original.contains { $0.rows == map.rows })
            #expect(!bonus.contains { $0.rows == map.rows })
            bonus.append(map)
        }
        // Classic 2 has blue, green and black: faction IDs must not be renumbered.
        #expect(Set(bonus[1].skirmishFractions) == [1, 3, 4])
        #expect(bonus[2].units.filter { $0.type == 6 }.count == 4)
        #expect(bonus[2].units.filter { $0.type == 9 }.count == 2)
        #expect(bonus[6].tileID(x: 3, y: 6) == 42)
        #expect(bonus[6].tileID(x: 6, y: 3) == 40)
    }

    @Test func bonusNamesTranslateAndImportedNamesStayIntact() throws {
        let map = try GameResources.campaignMap(named: "b2")
        #expect(GameResources.mapName(map, locale: Locale(identifier: "en")) == "(2)Duel")
        #expect(GameResources.mapName(map, locale: Locale(identifier: "zh-Hans")) == "(2)决斗")
        #expect(GameResources.mapName(map, locale: Locale(identifier: "zh-Hant")) == "(2)決鬥")
        let imported = map.withIdentity("local-player-map", name: "My Duel")
        #expect(GameResources.mapName(imported, locale: Locale(identifier: "zh-Hans")) == "My Duel")
    }
}
