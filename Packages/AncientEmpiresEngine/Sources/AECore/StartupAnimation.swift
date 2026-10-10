import Foundation

public enum StartupPhase: String, Codable, Sendable {
    case wave
    case fade
    case steady
}

public struct StartupWaveRow: Equatable, Sendable {
    public let clipX: Int32
    public let visibleWidth: Int32
    public let imageX: Int32
}

public struct StartupAnimationState: Equatable, Sendable {
    public static let logicalWidth: Int32 = 240
    public static let logicalHeight: Int32 = 320

    public private(set) var phase: StartupPhase = .wave
    public private(set) var progress: Int32 = 0
    public private(set) var glowX: Int32 = -67
    public private(set) var logicalTick: UInt64 = 0
    public private(set) var delayCounter: Int64 = 0
    public private(set) var promptVisible = false

    public init() {}

    public var canContinue: Bool {
        phase == .steady || (phase == .fade && progress >= 16)
    }

    public mutating func advanceOneTick() {
        logicalTick &+= 1
        delayCounter &+= 50

        switch phase {
        case .wave:
            progress += 1
            if progress > 40 {
                phase = .fade
                progress = 0
            }
        case .fade:
            if progress < 16 {
                progress += 1
                glowX = -67
            } else {
                phase = .steady
                advanceSteadyFrame()
            }
        case .steady:
            advanceSteadyFrame()
        }
    }

    private mutating func advanceSteadyFrame() {
        if glowX >= Self.logicalWidth * 4 {
            glowX = -67
        } else {
            glowX += Self.logicalWidth / 6
        }
        if delayCounter % 100 == 0 {
            promptVisible.toggle()
        }
    }

    public static func sineTable() -> [Int16] {
        let step: Int32 = 360 * 10_000 / 2 / 31_415
        var velocity: Int32 = 1024 * step
        var position: Int32 = 0
        var result = [Int16]()
        result.reserveCapacity(360)

        for _ in 0..<360 {
            let value = JavaMath.divide(position, by: step)
            result.append(Int16(truncatingIfNeeded: value))
            velocity -= value
            position += JavaMath.divide(velocity, by: step)
        }
        result[180] = 0
        result[270] = -1024
        return result
    }

    public static func waveRows(
        width: Int32,
        height: Int32,
        progress: Int32,
        sineTable: [Int16]
    ) -> [StartupWaveRow] {
        precondition(width > 0 && height > 0 && (0...40).contains(progress))
        precondition(sineTable.count == 360)

        let visibleWidth = JavaMath.divide(width * progress, by: 40)
        let amplitude = JavaMath.divide(width * (40 - progress), by: 160)
        var angle = JavaMath.divide(360 * progress, by: 40)
        let angleStep = JavaMath.divide(360, by: height)
        var rows = [StartupWaveRow]()
        rows.reserveCapacity(Int(height))

        for _ in 0..<height {
            let index = Int(JavaMath.remainder(angle, by: 360))
            let offset = (amplitude * Int32(sineTable[index])) >> 10
            rows.append(
                StartupWaveRow(
                    clipX: width / 2 - visibleWidth / 2 + offset,
                    visibleWidth: visibleWidth,
                    imageX: offset
                )
            )
            angle += angleStep
        }
        return rows
    }
}
