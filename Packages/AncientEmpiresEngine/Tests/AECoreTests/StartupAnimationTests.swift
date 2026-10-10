import AECore
import Testing

struct StartupAnimationTests {
    @Test func sineTableUsesOriginalIntegerRecurrence() {
        let table = StartupAnimationState.sineTable()
        #expect(table.count == 360)
        #expect(table[0] == 0)
        #expect(table[90] == 1024)
        #expect(table[180] == 0)
        #expect(table[270] == -1024)
    }

    @Test func waveRowsRetainIntegerClippingAndOffset() {
        let table = StartupAnimationState.sineTable()
        let collapsed = StartupAnimationState.waveRows(width: 240, height: 85, progress: 0, sineTable: table)
        let complete = StartupAnimationState.waveRows(width: 240, height: 85, progress: 40, sineTable: table)
        let middle = StartupAnimationState.waveRows(width: 240, height: 85, progress: 20, sineTable: table)

        #expect(collapsed.count == 85)
        #expect(collapsed.allSatisfy { $0.visibleWidth == 0 })
        #expect(complete.allSatisfy { $0.visibleWidth == 240 && $0.clipX == 0 && $0.imageX == 0 })
        #expect(middle[0].visibleWidth == 120)
        #expect(middle[0].clipX == 60)
        #expect(middle[1].imageX == -3)
    }

    @Test func titleAdvancesAtMostOneExplicitLogicalTick() {
        var state = StartupAnimationState()
        for _ in 0..<57 { state.advanceOneTick() }

        #expect(state.phase == .fade)
        #expect(state.progress == 16)
        #expect(state.canContinue)

        state.advanceOneTick()
        #expect(state.phase == .steady)
        #expect(state.glowX == -27)
    }
}

struct JavaRandomTests {
    @Test func matchesJavaUtilRandomSequence() {
        var random = JavaRandom(seed: 0)
        #expect(random.nextInt32() == -1_155_484_576)
        #expect(random.nextInt32() == -723_955_400)
    }

    @Test func preservesJavaInt32ArithmeticEdges() {
        #expect(JavaMath.divide(-7, by: 3) == -2)
        #expect(JavaMath.remainder(-7, by: 3) == -1)
        #expect(JavaMath.absoluteValue(.min) == .min)
        #expect(JavaMath.divide(.min, by: -1) == .min)
    }
}
