import AECore
import Foundation

public enum GameResources {
    public static let musicResourceNames = [
        "main_theme", "bg_story", "bg_good", "bg_bad", "battle_good",
        "battle_bad", "victory", "gameover", "game_complete",
    ]

    public static func imageData(named name: String) throws -> Data {
        try resourceData(named: "\(name).png", in: "Images")
    }

    public static func mapPreviewData(named name: String) throws -> Data {
        try resourceData(named: "\(name).png", in: "Previews")
    }

    public static func archerTrialData(named name: String) throws -> Data {
        guard ["blue", "red", "green", "black", "team-mask"].contains(name) else {
            throw ResourceError.invalidPath(name)
        }
        return try resourceData(named: "\(name).png", in: "Enhanced/Archer")
    }

    public static func generatedMapUnitData(unitID: Int, named name: String) throws -> Data {
        let units = ["soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton", "crystall"]
        guard units.indices.contains(unitID), ["blue", "red", "green", "black", "team-mask"].contains(name) else {
            throw ResourceError.invalidPath("\(unitID)/\(name)")
        }
        if unitID == 1 { return try archerTrialData(named: name) }
        return try resourceData(named: "\(name).png", in: "Enhanced/MapUnits/\(units[unitID])")
    }

    public static func enhancedEnvironmentData(named name: String) throws -> Data {
        let names = ["king_head_icons", "portraits", "village-team-mask", "castle-team-mask"]
            + (0..<48).map { String(format: "tiles0_%02d", $0) }
        guard names.contains(name) else { throw ResourceError.invalidPath(name) }
        return try resourceData(named: "\(name).png", in: "Enhanced/Environment")
    }

    public static let storyIllustrationNames = (0..<6).map { "intro\($0)" }

    public static func enhancedStoryData(named name: String) throws -> Data {
        guard storyIllustrationNames.contains(name) else { throw ResourceError.invalidPath(name) }
        return try resourceData(named: "\(name).png", in: "Enhanced/Story")
    }

    public static func enhancedStartupData(named name: String) throws -> Data {
        guard name == "splash" else { throw ResourceError.invalidPath(name) }
        return try resourceData(named: "\(name).png", in: "Enhanced/Startup")
    }

    public static let interfaceArtworkNames = [
        "menu_icons", "action_icons", "hud_icons", "hud_icons_2", "status", "arrow", "arrow_icons",
        "buttons", "side_arrow", "big_circle_00", "big_circle_01", "small_circle", "tombstone",
        "mini_icons", "menu", "unit_icons_s",
    ] + (0..<6).map { String(format: "cursor_%02d", $0) }
      + (0..<18).map { String(format: "stiles0_%02d", $0) }

    public static func enhancedInterfaceData(named name: String) throws -> Data {
        guard interfaceArtworkNames.contains(name) else { throw ResourceError.invalidPath(name) }
        return try resourceData(named: "\(name).png", in: "Enhanced/Interface")
    }

    public static func missionZero() throws -> CampaignMap {
        try campaignMap(named: "m0")
    }

    public static func campaignMap(named id: String) throws -> CampaignMap {
        guard id.range(of: "^[msb][0-9]+$", options: .regularExpression) != nil else {
            throw ResourceError.invalidPath(id)
        }
        return try decode(CampaignMap.self, at: "GameData/maps/\(id).json")
    }

    public static func campaignMapIDs() throws -> [String] {
        try decode([CampaignMapReference].self, at: "GameData/maps.json").map(\.id)
    }

    /// Original JAR maps followed by the preserved downloadable bonus maps.
    public static func skirmishMapIDs() throws -> [String] {
        let original = try campaignMapIDs().filter { $0.hasPrefix("s") }
        let bonus = try decode([CampaignMapReference].self, at: "GameData/bonus-maps.json").map(\.id)
        return original + bonus
    }

    public static func terrainCatalog() throws -> TerrainCatalog {
        try decode(TerrainCatalog.self, at: "GameData/terrain.json")
    }

    public static func unitDefinitions() throws -> [UnitDefinition] {
        try decode([UnitDefinition].self, at: "GameData/units.json")
    }

    public static func campaignUnitProfiles() throws -> [Int: CampaignUnitProfile] {
        try unitDefinitions().reduce(into: [:]) { result, definition in
            let data = try originalResourceData(at: "res/\(definition.resourceName).unit")
            guard let source = String(data: data, encoding: .utf8) else {
                throw ResourceError.invalidUnitDefinition(definition.resourceName)
            }
            var values: [String: [Int]] = [:]
            for line in source.split(whereSeparator: \.isNewline) {
                let fields = line.split(whereSeparator: \.isWhitespace)
                guard let key = fields.first, fields.count > 1 else { continue }
                let numbers = fields.dropFirst().compactMap { Int($0) }
                if key.caseInsensitiveCompare("HasProperty") == .orderedSame {
                    values["HasProperty", default: []].append(contentsOf: numbers)
                } else {
                    values[String(key)] = numbers
                }
            }
            guard let movement = values["MoveRange"]?.first,
                  let attack = values["Attack"], attack.count == 2,
                  let defense = values["Defence"]?.first,
                  let characterCount = values["CharCount"]?.first,
                  movement == definition.moveBudget else {
                throw ResourceError.invalidUnitDefinition(definition.resourceName)
            }
            result[definition.id] = CampaignUnitProfile(
                id: definition.id,
                moveBudget: movement,
                attackMinimum: attack[0],
                attackMaximum: attack[1],
                baseDefense: defense,
                minimumRange: definition.rangeMin,
                maximumRange: definition.rangeMax,
                characterCount: characterCount,
                cost: definition.cost,
                propertyBits: Set(values["HasProperty"] ?? [])
            )
        }
    }

    public static func campaignRuleset() throws -> CampaignRuleset {
        let catalog = try terrainCatalog()
        return CampaignRuleset(
            terrainByTileID: Dictionary(uniqueKeysWithValues: catalog.tiles.map { ($0.id, $0.terrain) }),
            movementCostByTerrainID: Dictionary(uniqueKeysWithValues: catalog.types.map { ($0.id, $0.steps) }),
            defenseByTerrainID: Dictionary(uniqueKeysWithValues: catalog.types.map { ($0.id, $0.defence) })
        )
    }

    public static func campaignStoryText(id: Int, locale: Locale? = nil) throws -> String? {
        guard let original = try decode([CampaignStoryText].self, at: "GameData/strings.json")
            .first(where: { $0.id == id }) else { return nil }
        // Empty source entries are intentional, rather than missing translations.
        guard !original.text.isEmpty else { return "" }
        return GameLocalization.text("story.\(id)", defaultValue: original.text, locale: locale)
    }

    public static func mapName(_ map: CampaignMap, locale: Locale? = nil) -> String {
        if map.id == "b\(map.index)", (0..<8).contains(map.index) {
            return GameLocalization.text("map.\(map.id).name", defaultValue: map.name, locale: locale)
        }
        // Imported names belong to the player; never translate or rewrite saved data.
        guard map.id == "m\(map.index)" || map.id == "s\(map.index)" else { return map.name }
        let textID = map.kind == "skirmish" ? 101 + map.index : 113 + map.index
        return GameLocalization.text("story.\(textID)", defaultValue: map.name, locale: locale)
    }

    public static func sourceManifest() throws -> GameContentManifest {
        try decode(GameContentManifest.self, at: "GameData/manifest.json")
    }

    public static func originalResourceData(at path: String) throws -> Data {
        guard
            !path.hasPrefix("/"),
            !path.split(separator: "/").contains(".."),
            path.hasPrefix("res/")
        else {
            throw ResourceError.invalidPath(path)
        }
        return try resourceData(path: "Original/\(path)")
    }

    public static func musicData(named name: String) throws -> Data {
        guard musicResourceNames.contains(name) else {
            throw ResourceError.invalidPath(name)
        }
        return try resourceData(path: "Audio/\(name).m4a")
    }

    public static func verifiedSineTable() throws -> [Int16] {
        struct Verification: Decodable {
            let lut: [Int16]
        }

        let data = try resourceData(
            named: "algorithm-verification.json",
            in: "StartupReference"
        )
        return try JSONDecoder().decode(Verification.self, from: data).lut
    }

    private static func decode<Value: Decodable>(_ type: Value.Type, at path: String) throws -> Value {
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        return try decoder.decode(type, from: resourceData(path: path))
    }

    private static func resourceData(named name: String, in directory: String) throws -> Data {
        try resourceData(path: "\(directory)/\(name)")
    }

    private static func resourceData(path: String) throws -> Data {
        let resourceName = (path as NSString).lastPathComponent
        let fileExtension = (resourceName as NSString).pathExtension
        let basename = (resourceName as NSString).deletingPathExtension
        let directory = (path as NSString).deletingLastPathComponent
        let url = ["Resources/\(directory)", directory].compactMap { subdirectory in
            Bundle.module.url(
                forResource: basename,
                withExtension: fileExtension.isEmpty ? nil : fileExtension,
                subdirectory: subdirectory
            )
        }.first
        guard let url else { throw ResourceError.missing(path) }
        do {
            return try Data(contentsOf: url)
        } catch {
            throw ResourceError.unreadable(path)
        }
    }
}

public struct CampaignMapReference: Decodable, Sendable {
    public let id: String
}

private struct CampaignStoryText: Decodable {
    let id: Int
    let text: String
}

public struct TerrainCatalog: Decodable, Sendable {
    public let terrainCount: Int
    public let tileCount: Int
    public let types: [TerrainTypeDefinition]
    public let tiles: [TerrainTileDefinition]
}

public struct TerrainTypeDefinition: Decodable, Sendable {
    public let id: Int
    public let steps: Int
    public let defence: Int
    public let battleBackground: String
    public let battleForeground: String
}

public struct TerrainTileDefinition: Decodable, Sendable {
    public let id: Int
    public let terrain: Int
    public let minimapFrame: Int
    public let image: String
}

public struct UnitDefinition: Decodable, Sendable {
    public let id: Int
    public let resourceName: String
    public let chineseReferenceName: String
    private let sourceDisplayName: String
    private let sourceDescription: String
    public var displayName: String {
        let textIDs = [139, 143, 147, 151, 155, 159, 163, 167, 171, 175, 179, 183]
        guard textIDs.indices.contains(id) else { return sourceDisplayName }
        return GameLocalization.text("story.\(textIDs[id])", defaultValue: sourceDisplayName)
    }
    public var description: String {
        GameLocalization.text("story.\(184 + id)", defaultValue: sourceDescription)
    }
    public let moveBudget: Int
    public let rangeMin: Int
    public let rangeMax: Int
    public let cost: Int
    public let characterCount: Int
    public let properties: [UnitProperty]

    private enum CodingKeys: String, CodingKey {
        case id, resourceName, chineseReferenceName, moveBudget, rangeMin, rangeMax, cost, characterCount, properties
        case sourceDisplayName = "displayName"
        case sourceDescription = "description"
    }

    public var movementProfile: MovementProfile {
        MovementProfile(
            moveBudget: moveBudget,
            canFly: properties.contains { $0.bit == 0 },
            isAmphibious: properties.contains { $0.bit == 1 },
            isCrystal: id == 11
        )
    }
}

public struct UnitProperty: Decodable, Sendable {
    public let bit: Int
    public let mask: Int
    public let interpretation: String
}

public struct GameContentManifest: Decodable, Sendable {
    public let schemaVersion: Int
    public let sourceJar: SourceArtifact
    public let resourceCount: Int
    public let mapCount: Int
    public let tileCount: Int
    public let unitCount: Int

    public struct SourceArtifact: Decodable, Sendable {
        public let path: String
        public let sha256: String
    }
}

private enum ResourceError: Error {
    case missing(String)
    case unreadable(String)
    case invalidPath(String)
    case invalidUnitDefinition(String)
}
