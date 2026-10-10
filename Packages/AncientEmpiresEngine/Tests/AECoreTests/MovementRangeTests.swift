import AECore
import Testing

struct MovementRangeTests {
    @Test func roadBudgetRequiresPositiveRemainingMovement() throws {
        let grid = MovementGrid(
            terrainIDs: [[0, 0, 0, 0, 0]],
            stepCostByTerrainID: [0: 1]
        )
        let range = try MovementRangeCalculator.calculate(
            on: grid,
            from: GridPoint(x: 0, y: 0),
            teamID: 1,
            profile: MovementProfile(moveBudget: 5)
        )

        #expect(range.reachableDestinations == Set([
            GridPoint(x: 1, y: 0),
            GridPoint(x: 2, y: 0),
            GridPoint(x: 3, y: 0),
            GridPoint(x: 4, y: 0),
        ]))
        #expect(range.remainingBudget(at: GridPoint(x: 4, y: 0)) == 1)

        let exhausted = try MovementRangeCalculator.calculate(
            on: grid,
            from: GridPoint(x: 0, y: 0),
            teamID: 1,
            profile: MovementProfile(moveBudget: 4)
        )
        #expect(!exhausted.reachableDestinations.contains(GridPoint(x: 4, y: 0)))
        #expect(exhausted.remainingBudget(at: GridPoint(x: 4, y: 0)) == 0)
    }

    @Test func alliesCanBeTraversedButCannotBeLandingCellsAndEnemiesBlock() throws {
        let grid = MovementGrid(
            terrainIDs: [[0, 0, 0, 0, 0]],
            stepCostByTerrainID: [0: 1],
            occupants: [
                MovementOccupant(position: GridPoint(x: 1, y: 0), teamID: 1),
                MovementOccupant(position: GridPoint(x: 2, y: 0), teamID: 2),
            ]
        )
        let range = try MovementRangeCalculator.calculate(
            on: grid,
            from: GridPoint(x: 0, y: 0),
            teamID: 1,
            profile: MovementProfile(moveBudget: 5)
        )

        #expect(range.reachableDestinations.isEmpty)
        #expect(range.remainingBudget(at: GridPoint(x: 1, y: 0)) == 4)
        #expect(range.remainingBudget(at: GridPoint(x: 2, y: 0)) == 0)
    }

    @Test func routeTiesUseUpDownLeftRightWhenReconstructingFromDestination() throws {
        let grid = MovementGrid(
            terrainIDs: Array(repeating: [0, 0, 0], count: 3),
            stepCostByTerrainID: [0: 1]
        )
        let range = try MovementRangeCalculator.calculate(
            on: grid,
            from: GridPoint(x: 1, y: 1),
            teamID: 1,
            profile: MovementProfile(moveBudget: 5)
        )

        #expect(range.route(to: GridPoint(x: 0, y: 0)) == [
            GridPoint(x: 0, y: 1),
            GridPoint(x: 0, y: 0),
        ])
    }

    @Test func flightIgnoresTerrainCostButStillStopsAtEnemyUnits() throws {
        let grid = MovementGrid(
            terrainIDs: [[0, 4, 5, 0, 0]],
            stepCostByTerrainID: [0: 1, 4: 3, 5: 3],
            occupants: [MovementOccupant(position: GridPoint(x: 3, y: 0), teamID: 2)]
        )
        let range = try MovementRangeCalculator.calculate(
            on: grid,
            from: GridPoint(x: 0, y: 0),
            teamID: 1,
            profile: MovementProfile(moveBudget: 5, canFly: true)
        )

        #expect(range.reachableDestinations == Set([
            GridPoint(x: 1, y: 0),
            GridPoint(x: 2, y: 0),
        ]))

        let crystal = try MovementRangeCalculator.calculate(
            on: grid,
            from: GridPoint(x: 0, y: 0),
            teamID: 1,
            profile: MovementProfile(moveBudget: 5, isCrystal: true)
        )
        #expect(crystal.reachableDestinations.isEmpty)
    }
}
