import Foundation

public struct CampaignMap: Codable, Equatable, Sendable {
    public let id: String
    public let kind: String
    public let index: Int
    public let name: String
    public let width: Int
    public let height: Int
    public let rows: [[Int]]
    public let units: [CampaignUnit]
    public let buildings: [CampaignBuilding]

    public func tileID(x: Int, y: Int) -> Int? {
        guard rows.indices.contains(y), rows[y].indices.contains(x) else { return nil }
        return rows[y][x]
    }

    /// Buildings are derived from the current tiles, including repaired villages.
    public func applying(tileOverrides: [Int: Int]) -> CampaignMap {
        guard !tileOverrides.isEmpty else { return self }
        var tiles = rows
        for (index, tile) in tileOverrides where index >= 0 && index < width * height {
            tiles[index / width][index % width] = tile
        }
        let buildings = tiles.enumerated().flatMap { y, row in
            row.enumerated().compactMap { x, tile -> CampaignBuilding? in
                guard (37...46).contains(tile) || tile == 27 else { return nil }
                return CampaignBuilding(x: x, y: y, tile: tile,
                    terrain: tile == 27 ? 8 : 8 + (tile - 37) % 2,
                    fraction: tile == 27 ? 0 : (tile - 37) / 2)
            }
        }
        return CampaignMap(id: id, kind: kind, index: index, name: name, width: width,
                           height: height, rows: tiles, units: units, buildings: buildings)
    }

    public func units(x: Int, y: Int) -> [CampaignUnit] {
        units.filter { $0.x == x && $0.y == y }
    }
}

public struct CampaignBuilding: Codable, Equatable, Sendable {
    public let x: Int
    public let y: Int
    public let tile: Int
    public let terrain: Int
    public let fraction: Int
}

public struct CampaignUnit: Codable, Equatable, Sendable {
    public let index: Int
    public let type: Int
    public let fraction: Int
    public let pixelX: Int
    public let pixelY: Int
    public let x: Int
    public let y: Int
    public let inBounds: Bool
}
