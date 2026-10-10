import AECore
import Testing

struct CircleMenuAnimationTests {
    @Test func entranceMatchesOriginalSixItemTickTrace() {
        var state = CircleMenuAnimation(count: 6, radius: 66)
        let angles = [30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330, 345, 352, 356, 358, 359, 0]
        let radii = [13, 26, 39, 52, 65] + Array(repeating: 66, count: 12)
        for (index, angle) in angles.enumerated() {
            state.advanceOneTick()
            #expect(state.angles[0] == angle)
            #expect(state.radius == radii[index])
            #expect(state.isEntering == (index < 16))
        }
        #expect(state.offset(at: 0).x == 0)
        #expect(state.offset(at: 0).y == -66)
    }

    @Test func rotationWrapsAlongCircleAndCanReverseWithoutJumping() {
        var state = CircleMenuAnimation(count: 6, radius: 66)
        state.finishEntrance()
        let start = state.offset(at: 1)
        state.select(step: 1)
        #expect(state.offset(at: 1).x == start.x)
        #expect(state.offset(at: 1).y == start.y)
        for shift in [30, 15, 8, 4, 2, 1, 0] {
            state.advanceOneTick()
            #expect(state.dynamicShift == shift)
            let point = state.offset(at: 1)
            let radiusSquared = point.x * point.x + point.y * point.y
            #expect((4_200...4_500).contains(radiusSquared))
        }
        #expect(state.offset(at: 1).x == 0)
        #expect(state.offset(at: 1).y == -66)
        state.select(step: -2)
        #expect(state.selectedIndex == 5)
        state.advanceOneTick()
        let beforeReverse = state.offset(at: 0)
        state.select(step: 1)
        #expect(state.selectedIndex == 0)
        #expect(state.offset(at: 0).x == beforeReverse.x)
        #expect(state.offset(at: 0).y == beforeReverse.y)
    }
}
