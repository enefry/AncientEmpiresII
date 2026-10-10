import Foundation

public enum AIDifficulty: String, Codable, CaseIterable, Sendable {
    case easy, normal, hard, expert

    public var title: String {
        GameLocalization.text("ai.\(rawValue).title")
    }
    public var summary: String {
        GameLocalization.text("ai.\(rawValue).summary")
    }
    public var next: Self {
        let cases = Self.allCases
        return cases[(cases.firstIndex(of: self)! + 1) % cases.count]
    }
    var usesTactics: Bool { self == .hard || self == .expert }
}

public enum SkirmishControl: String, Codable, CaseIterable, Sendable {
    case human, cpu, off
}

public struct SkirmishPlayer: Codable, Equatable, Sendable {
    public let fraction: Int
    public var control: SkirmishControl
    public var team: Int
    /// Missing in older saves: retain the original normal AI.
    public var difficulty: AIDifficulty?
    public var resolvedDifficulty: AIDifficulty { difficulty ?? .normal }

    public init(fraction: Int, control: SkirmishControl, team: Int, difficulty: AIDifficulty = .normal) {
        self.fraction = fraction
        self.control = control
        self.team = team
        self.difficulty = difficulty
    }
}

public struct SkirmishConfiguration: Codable, Equatable, Sendable {
    public static let moneyChoices = [500, 1000, 2000, 5000, 10000, 25000, 50000, 75000, 100000, 150000, 200000]
    public static let capChoices = Array(stride(from: 10, through: 200, by: 10))
    public var players: [SkirmishPlayer]
    public var startingMoney: Int
    public var unitCap: Int

    public init(map: CampaignMap) {
        players = map.skirmishFractions.enumerated().map {
            SkirmishPlayer(fraction: $0.element, control: $0.offset == 0 ? .human : .cpu, team: $0.offset + 1)
        }
        startingMoney = 500
        unitCap = 10
    }

    public func validate(for map: CampaignMap) throws {
        try SkirmishMapImporter.validate(map)
        guard players.map(\.fraction) == map.skirmishFractions,
              players.allSatisfy({ (1...players.count).contains($0.team) }),
              Self.moneyChoices.contains(startingMoney), Self.capChoices.contains(unitCap) else {
            throw SkirmishError.invalidConfiguration
        }
        let enabled = players.filter { $0.control != .off }
        guard enabled.count >= 2, Set(enabled.map(\.team)).count >= 2 else {
            throw SkirmishError.insufficientTeams
        }
    }
}

public enum SkirmishError: LocalizedError, Equatable {
    case invalidMap, invalidConfiguration, insufficientTeams, oversizedFile
    public var errorDescription: String? {
        switch self {
        case .invalidMap: GameLocalization.text("Invalid map: requires castles for 2–4 factions, valid terrain, and non-overlapping units within the map. Map dimensions must be 2–64 cells.")
        case .invalidConfiguration: GameLocalization.text("Invalid skirmish configuration.")
        case .insufficientTeams: GameLocalization.text("At least two enabled factions and two teams are required.")
        case .oversizedFile: GameLocalization.text("The map file exceeds 1 MB.")
        }
    }
}

extension CampaignMap {
    /// The original loader discovers factions by scanning castles in column order.
    public var skirmishFractions: [Int] {
        var fractions: [Int] = []
        for x in 0..<width {
            for y in 0..<height {
                guard let tile = tileID(x: x, y: y), [40, 42, 44, 46].contains(tile) else { continue }
                let fraction = (tile - 37) / 2
                if !fractions.contains(fraction) { fractions.append(fraction) }
            }
        }
        return fractions
    }

    public func withIdentity(_ id: String, name: String? = nil) -> CampaignMap {
        CampaignMap(id: id, kind: "skirmish", index: index, name: name ?? self.name,
                    width: width, height: height, rows: rows, units: units, buildings: buildings)
    }
}

public enum SkirmishMapImporter {
    public static let maximumFileSize = 1_048_576

    public static func decode(_ data: Data, filename: String) throws -> CampaignMap {
        guard data.count <= maximumFileSize else { throw SkirmishError.oversizedFile }
        let id = "local-" + UUID().uuidString.lowercased()
        let fallbackName = String((filename as NSString).deletingPathExtension.prefix(80))
        let map: CampaignMap
        if (filename as NSString).pathExtension.lowercased() == "json" {
            let document: Document
            do { document = try JSONDecoder().decode(Document.self, from: data) }
            catch { throw SkirmishError.invalidMap }
            guard document.kind == nil || document.kind == "skirmish" else { throw SkirmishError.invalidMap }
            guard (2...64).contains(document.width), (2...64).contains(document.height),
                  document.units.allSatisfy({ (0..<document.width).contains($0.x) && (0..<document.height).contains($0.y) }) else {
                throw SkirmishError.invalidMap
            }
            let units = document.units.enumerated().map { index, unit in
                CampaignUnit(index: index, type: unit.type, fraction: unit.fraction,
                             pixelX: unit.x * 24, pixelY: unit.y * 24, x: unit.x, y: unit.y, inBounds: true)
            }
            map = make(id: id, name: String((document.name ?? fallbackName).prefix(80)),
                       width: document.width, height: document.height, rows: document.rows, units: units)
        } else {
            var reader = Reader(bytes: Array(data))
            let width = try reader.integer(bytes: 4)
            let height = try reader.integer(bytes: 4)
            guard (2...64).contains(width), (2...64).contains(height) else { throw SkirmishError.invalidMap }
            var rows = Array(repeating: Array(repeating: 0, count: width), count: height)
            for x in 0..<width { for y in 0..<height { rows[y][x] = try reader.integer(bytes: 1) } }
            let records = try reader.integer(bytes: 4)
            guard records <= (reader.bytes.count - reader.offset) / 4 else { throw SkirmishError.invalidMap }
            reader.offset += records * 4
            let count = try reader.integer(bytes: 4)
            guard count <= 800, count <= (reader.bytes.count - reader.offset) / 5 else { throw SkirmishError.invalidMap }
            var units: [CampaignUnit] = []
            for index in 0..<count {
                let packed = try reader.integer(bytes: 1)
                let px = try reader.integer(bytes: 2), py = try reader.integer(bytes: 2)
                guard packed < 48 else { throw SkirmishError.invalidMap }
                units.append(CampaignUnit(index: index, type: packed % 12, fraction: 1 + packed / 12,
                                          pixelX: px, pixelY: py, x: px / 24, y: py / 24, inBounds: true))
            }
            guard reader.offset == reader.bytes.count else { throw SkirmishError.invalidMap }
            map = make(id: id, name: fallbackName, width: width, height: height, rows: rows, units: units)
        }
        try validate(map)
        return map
    }

    public static func validate(_ map: CampaignMap) throws {
        guard map.kind == "skirmish", !map.name.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty,
              (2...64).contains(map.width), (2...64).contains(map.height),
              map.rows.count == map.height,
              map.rows.allSatisfy({ $0.count == map.width && $0.allSatisfy { (0...46).contains($0) } }),
              map.units.count <= 800 else { throw SkirmishError.invalidMap }
        let fractions = Set(map.skirmishFractions)
        guard (2...4).contains(fractions.count),
              Set(map.units.map(\.index)).count == map.units.count,
              Set(map.units.map { GridPoint(x: $0.x, y: $0.y) }).count == map.units.count,
              map.units.allSatisfy({ (0...11).contains($0.type) && fractions.contains($0.fraction)
                  && (0..<map.width).contains($0.x) && (0..<map.height).contains($0.y) }) else {
            throw SkirmishError.invalidMap
        }
    }

    private static func make(id: String, name: String, width: Int, height: Int, rows: [[Int]], units: [CampaignUnit]) -> CampaignMap {
        let buildings = rows.enumerated().flatMap { y, row in
            row.enumerated().compactMap { x, tile -> CampaignBuilding? in
                guard (37...46).contains(tile) || tile == 27 else { return nil }
                return CampaignBuilding(x: x, y: y, tile: tile, terrain: tile == 27 ? 8 : 8 + (tile - 37) % 2,
                                        fraction: tile == 27 ? 0 : (tile - 37) / 2)
            }
        }
        return CampaignMap(id: id, kind: "skirmish", index: 0, name: name, width: width, height: height,
                           rows: rows, units: units, buildings: buildings)
    }

    private struct Document: Decodable {
        let name: String?
        let kind: String?
        let width: Int
        let height: Int
        let rows: [[Int]]
        let units: [Unit]
        struct Unit: Decodable { let type: Int; let fraction: Int; let x: Int; let y: Int }
    }

    private struct Reader {
        let bytes: [UInt8]
        var offset = 0
        mutating func integer(bytes count: Int) throws -> Int {
            guard offset <= bytes.count - count else { throw SkirmishError.invalidMap }
            var value = 0
            for _ in 0..<count { value = value * 256 + Int(bytes[offset]); offset += 1 }
            return value
        }
    }
}
