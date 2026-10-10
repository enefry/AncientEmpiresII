import Foundation

public enum JavaMath {
    public static func divide(_ dividend: Int32, by divisor: Int32) -> Int32 {
        precondition(divisor != 0)
        if dividend == .min && divisor == -1 { return .min }
        return dividend / divisor
    }

    public static func remainder(_ dividend: Int32, by divisor: Int32) -> Int32 {
        precondition(divisor != 0)
        if dividend == .min && divisor == -1 { return 0 }
        return dividend % divisor
    }

    public static func absoluteValue(_ value: Int32) -> Int32 {
        value == .min ? .min : Swift.abs(value)
    }
}

public struct JavaRandom: Sendable {
    private static let multiplier: UInt64 = 0x5DEECE66D
    private static let addend: UInt64 = 0xB
    private static let mask: UInt64 = (1 << 48) - 1

    public private(set) var state: UInt64

    public init(seed: Int64) {
        state = (UInt64(bitPattern: seed) ^ Self.multiplier) & Self.mask
    }

    public init(internalState: UInt64) {
        state = internalState & Self.mask
    }

    public mutating func nextInt32() -> Int32 {
        state = (state &* Self.multiplier &+ Self.addend) & Self.mask
        return Int32(truncatingIfNeeded: state >> 16)
    }
}
