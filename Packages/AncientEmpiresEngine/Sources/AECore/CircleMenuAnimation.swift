/// AuxDisplayable mode 0: integer angles, expanding entrance and halving rotation.
/// Radius is in original pixels; UIKit applies the touch-screen scale afterwards.
public struct CircleMenuAnimation: Equatable, Sendable {
    public private(set) var angles: [Int]
    public private(set) var radius = 0
    public private(set) var targetRadius: Int
    public private(set) var selectedIndex = 0
    public private(set) var isEntering = true
    public private(set) var dynamicShift = 0
    public private(set) var staticShift = 0
    private var angleStep: Int
    private let sector: Int
    private static let sine = StartupAnimationState.sineTable()

    public init(count: Int, radius: Int) {
        precondition(count > 1 && count <= 360)
        sector = 360 / count
        angles = (0..<count).map { $0 * (360 / count) }
        targetRadius = max(1, radius)
        angleStep = sector / 2
    }

    public mutating func resize(radius: Int) {
        targetRadius = max(1, radius)
        self.radius = isEntering ? min(self.radius, targetRadius) : targetRadius
    }

    public mutating func finishEntrance() {
        isEntering = false
        radius = targetRadius
        angles = angles.indices.map { $0 * sector }
    }

    public mutating func select(step: Int) {
        finishEntrance()
        dynamicShift += step * sector
        staticShift = Self.normalize(staticShift - step * sector)
        selectedIndex = (selectedIndex + step % angles.count + angles.count) % angles.count
    }

    public mutating func advanceOneTick() {
        if isEntering {
            if radius < targetRadius {
                radius = min(targetRadius, radius + max(1, targetRadius / 5))
            } else {
                angleStep = min(sector / 2, max(1, abs(360 - angles[0]) / 2))
            }
            angles = angles.map { ($0 + angleStep) % 360 }
            if radius >= targetRadius && angles[0] == 0 { finishEntrance() }
        } else if dynamicShift != 0 {
            let step = -dynamicShift / 2 // Java and Swift both truncate toward zero.
            dynamicShift = step == 0 ? 0 : dynamicShift + step
        }
    }

    public func offset(at index: Int) -> (x: Int, y: Int) {
        let angle = Self.normalize(angles[index] + staticShift + dynamicShift)
        // Swift shifts bind more tightly than multiplication; preserve Java grouping.
        return ((Int(Self.sine[angle]) * radius) >> 10,
                -((Int(Self.sine[(angle + 90) % 360]) * radius) >> 10))
    }

    private static func normalize(_ angle: Int) -> Int { (angle % 360 + 360) % 360 }
}
