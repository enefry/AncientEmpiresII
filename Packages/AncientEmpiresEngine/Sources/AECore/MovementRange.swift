import Foundation

public struct GridPoint: Codable, Hashable, Sendable {
    public let x: Int
    public let y: Int

    public init(x: Int, y: Int) {
        self.x = x
        self.y = y
    }
}

public struct MovementProfile: Sendable {
    public let moveBudget: Int
    public let canFly: Bool
    public let isAmphibious: Bool
    public let isCrystal: Bool

    public init(moveBudget: Int, canFly: Bool = false, isAmphibious: Bool = false, isCrystal: Bool = false) {
        self.moveBudget = moveBudget
        self.canFly = canFly
        self.isAmphibious = isAmphibious
        self.isCrystal = isCrystal
    }
}

public struct MovementOccupant: Sendable {
    public let position: GridPoint
    public let teamID: Int
    public let isAlive: Bool

    public init(position: GridPoint, teamID: Int, isAlive: Bool = true) {
        self.position = position
        self.teamID = teamID
        self.isAlive = isAlive
    }
}

public struct MovementGrid: Sendable {
    public let terrainIDs: [[Int]]
    public let stepCostByTerrainID: [Int: Int]
    public let occupants: [MovementOccupant]
    public let waterTerrainID: Int
    public let mountainTerrainID: Int

    public init(
        terrainIDs: [[Int]],
        stepCostByTerrainID: [Int: Int],
        occupants: [MovementOccupant] = [],
        waterTerrainID: Int = 5,
        mountainTerrainID: Int = 4
    ) {
        self.terrainIDs = terrainIDs
        self.stepCostByTerrainID = stepCostByTerrainID
        self.occupants = occupants
        self.waterTerrainID = waterTerrainID
        self.mountainTerrainID = mountainTerrainID
    }
}

public enum MovementRangeError: Error, Equatable, Sendable {
    case emptyGrid
    case nonRectangularGrid
    case invalidMovementBudget
    case originOutOfBounds
    case missingTerrainCost(Int)
    case invalidTerrainCost(Int)
}

public struct MovementRange: Sendable {
    public let origin: GridPoint
    public let reachableDestinations: Set<GridPoint>
    private let width: Int
    private let height: Int
    private let remainingBudgets: [Int]

    fileprivate init(origin: GridPoint, width: Int, height: Int, remainingBudgets: [Int], occupied: Set<GridPoint>) {
        self.origin = origin
        self.width = width
        self.height = height
        self.remainingBudgets = remainingBudgets
        self.reachableDestinations = Set((0..<height).flatMap { y in
            (0..<width).compactMap { x -> GridPoint? in
                let point = GridPoint(x: x, y: y)
                guard point != origin, !occupied.contains(point), remainingBudgets[y * width + x] > 0 else {
                    return nil
                }
                return point
            }
        })
    }

    public func remainingBudget(at point: GridPoint) -> Int? {
        guard point.x >= 0, point.y >= 0, point.x < width, point.y < height else { return nil }
        return remainingBudgets[point.y * width + point.x]
    }

    public func route(to destination: GridPoint) -> [GridPoint]? {
        guard reachableDestinations.contains(destination) else { return nil }
        var current = destination
        var reverseRoute = [destination]
        var visited: Set<GridPoint> = [destination]

        while current != origin {
            guard let currentBudget = remainingBudget(at: current) else { return nil }
            var next: GridPoint?
            var greatestBudget = currentBudget

            for direction in MovementDirection.routeOrder {
                let candidate = direction.neighbor(of: current)
                guard let budget = remainingBudget(at: candidate), budget > greatestBudget else { continue }
                greatestBudget = budget
                next = candidate
            }

            guard let next, visited.insert(next).inserted else { return nil }
            current = next
            reverseRoute.append(current)
        }

        return Array(reverseRoute.reversed().dropFirst())
    }
}

public enum MovementRangeCalculator {
    public static func calculate(
        on grid: MovementGrid,
        from origin: GridPoint,
        teamID: Int,
        profile: MovementProfile
    ) throws -> MovementRange {
        guard let firstRow = grid.terrainIDs.first, !firstRow.isEmpty else {
            throw MovementRangeError.emptyGrid
        }
        let width = firstRow.count
        let height = grid.terrainIDs.count
        guard grid.terrainIDs.allSatisfy({ $0.count == width }) else {
            throw MovementRangeError.nonRectangularGrid
        }
        guard profile.moveBudget > 0 else { throw MovementRangeError.invalidMovementBudget }
        guard contains(origin, width: width, height: height) else {
            throw MovementRangeError.originOutOfBounds
        }

        for terrainID in Set(grid.terrainIDs.flatMap { $0 }) {
            guard let stepCost = grid.stepCostByTerrainID[terrainID] else {
                throw MovementRangeError.missingTerrainCost(terrainID)
            }
            guard stepCost > 0 else { throw MovementRangeError.invalidTerrainCost(terrainID) }
        }

        var teamsAtPosition: [GridPoint: Set<Int>] = [:]
        for occupant in grid.occupants where occupant.isAlive && contains(occupant.position, width: width, height: height) {
            teamsAtPosition[occupant.position, default: []].insert(occupant.teamID)
        }

        var remainingBudgets = [Int](repeating: 0, count: width * height)
        func visit(_ point: GridPoint, budget: Int, forbiddenNext: MovementDirection?, remaining: inout [Int]) {
            let index = point.y * width + point.x
            guard budget > remaining[index] else { return }
            remaining[index] = budget

            for direction in MovementDirection.rangeOrder where direction != forbiddenNext {
                let next = direction.neighbor(of: point)
                guard contains(next, width: width, height: height) else { continue }
                let terrainID = grid.terrainIDs[next.y][next.x]
                let occupiedByEnemy = teamsAtPosition[next]?.contains(where: { $0 != teamID }) == true
                let cost: Int
                if occupiedByEnemy || (profile.isCrystal && terrainID == grid.mountainTerrainID) {
                    cost = 1000
                } else if profile.canFly || (profile.isAmphibious && terrainID == grid.waterTerrainID) {
                    cost = 1
                } else {
                    cost = grid.stepCostByTerrainID[terrainID]!
                }

                let nextBudget = budget - cost
                if nextBudget >= 0 {
                    visit(next, budget: nextBudget, forbiddenNext: direction.opposite, remaining: &remaining)
                }
            }
        }

        visit(origin, budget: profile.moveBudget, forbiddenNext: nil, remaining: &remainingBudgets)
        let occupied = Set(teamsAtPosition.keys)
        return MovementRange(
            origin: origin,
            width: width,
            height: height,
            remainingBudgets: remainingBudgets,
            occupied: occupied
        )
    }

    private static func contains(_ point: GridPoint, width: Int, height: Int) -> Bool {
        point.x >= 0 && point.y >= 0 && point.x < width && point.y < height
    }
}

private enum MovementDirection: CaseIterable {
    case up
    case down
    case left
    case right

    static let rangeOrder: [Self] = [.up, .down, .left, .right]
    static let routeOrder: [Self] = [.up, .down, .left, .right]

    var opposite: Self {
        switch self {
        case .up: .down
        case .down: .up
        case .left: .right
        case .right: .left
        }
    }

    func neighbor(of point: GridPoint) -> GridPoint {
        switch self {
        case .up: GridPoint(x: point.x, y: point.y - 1)
        case .down: GridPoint(x: point.x, y: point.y + 1)
        case .left: GridPoint(x: point.x - 1, y: point.y)
        case .right: GridPoint(x: point.x + 1, y: point.y)
        }
    }
}
