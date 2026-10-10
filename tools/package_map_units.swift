import Foundation
import CoreGraphics
import ImageIO
import UniformTypeIdentifiers

let root = URL(fileURLWithPath: FileManager.default.currentDirectoryPath)
let outputRoot = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/MapUnits")
let colorSpace = CGColorSpace(name: CGColorSpace.sRGB)!
let bitmapInfo = CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue
let units: [(String, [CGRect])] = [
    ("soldier", [CGRect(x: 0, y: 2, width: 24, height: 22), CGRect(x: 0, y: 1, width: 24, height: 23)]),
    ("lizard", [CGRect(x: 5, y: 1, width: 18, height: 23), CGRect(x: 5, y: 2, width: 18, height: 22)]),
    ("wizard", [CGRect(x: 2, y: 0, width: 21, height: 24), CGRect(x: 1, y: 0, width: 21, height: 24)]),
    ("wisp", [CGRect(x: 2, y: 2, width: 20, height: 22), CGRect(x: 1, y: 0, width: 22, height: 24)]),
    ("spider", [CGRect(x: 0, y: 3, width: 23, height: 18), CGRect(x: 0, y: 3, width: 24, height: 18)]),
    ("golem", [CGRect(x: 0, y: 0, width: 24, height: 24), CGRect(x: 0.5, y: 0.5, width: 23, height: 23)]),
    ("catapult", [CGRect(x: 0, y: 3, width: 24, height: 20), CGRect(x: 0, y: 4, width: 24, height: 19)]),
    ("wyvern", [CGRect(x: 1, y: 0, width: 23, height: 24), CGRect(x: 1, y: 1, width: 23, height: 23)]),
    ("king", [CGRect(x: 1, y: 9, width: 23, height: 15), CGRect(x: 1, y: 10, width: 23, height: 14)]),
    ("skeleton", [CGRect(x: 0, y: 0, width: 23, height: 24), CGRect(x: 0, y: 1, width: 23, height: 23)]),
    ("crystall", [CGRect(x: 3, y: 0, width: 19, height: 24), CGRect(x: 3, y: 1, width: 19, height: 23)]),
]
let palettes: [(String, [[Double]])] = [
    ("blue", [[0, 0, 0], [12, 53, 112], [0, 100, 198], [65, 149, 233], [150, 217, 244], [255, 255, 255]]),
    ("red", [[0, 0, 0], [95, 5, 120], [161, 0, 112], [219, 36, 113], [244, 158, 156], [255, 255, 255]]),
    ("green", [[0, 0, 0], [0, 85, 82], [0, 153, 55], [99, 190, 37], [171, 237, 90], [255, 255, 255]]),
    ("black", [[0, 0, 0], [0, 22, 48], [0, 43, 75], [0, 65, 114], [0, 118, 150], [255, 255, 255]]),
]
func luminance(_ color: [Double]) -> Double { color[0] * 0.2126 + color[1] * 0.7152 + color[2] * 0.0722 }
let stops = palettes[0].1.map(luminance)

func context(width: Int, height: Int) -> CGContext {
    CGContext(data: nil, width: width, height: height, bitsPerComponent: 8, bytesPerRow: width * 4,
              space: colorSpace, bitmapInfo: bitmapInfo)!
}

func save(_ image: CGImage, to url: URL) {
    let destination = CGImageDestinationCreateWithURL(url as CFURL, UTType.png.identifier as CFString, 1, nil)!
    CGImageDestinationAddImage(destination, image, nil)
    precondition(CGImageDestinationFinalize(destination))
}

func trimmed(_ source: CGImage) -> CGImage {
    let canvas = context(width: source.width, height: source.height)
    canvas.draw(source, in: CGRect(x: 0, y: 0, width: source.width, height: source.height))
    let bytes = canvas.data!.assumingMemoryBound(to: UInt8.self)
    var minimumX = source.width, minimumY = source.height, maximumX = 0, maximumY = 0
    for vertical in 0..<source.height {
        for horizontal in 0..<source.width {
            let offset = (vertical * source.width + horizontal) * 4
            if bytes[offset + 3] <= 8 {
                for channel in 0..<4 { bytes[offset + channel] = 0 }
            } else {
                minimumX = min(minimumX, horizontal)
                minimumY = min(minimumY, vertical)
                maximumX = max(maximumX, horizontal)
                maximumY = max(maximumY, vertical)
            }
        }
    }
    precondition(maximumX >= minimumX && maximumY >= minimumY)
    return canvas.makeImage()!.cropping(to: CGRect(x: minimumX, y: minimumY,
                                                 width: maximumX - minimumX + 1, height: maximumY - minimumY + 1))!
}

for (name, bounds) in units {
    let sourceURL = root.appendingPathComponent("reference/art/imagegen-map-units/\(name).png")
    let source = trimmed(CGImageSourceCreateImageAtIndex(CGImageSourceCreateWithURL(sourceURL as CFURL, nil)!, 0, nil)!)
    let destination = outputRoot.appendingPathComponent(name)
    try FileManager.default.createDirectory(at: destination, withIntermediateDirectories: true)
    let canvas = context(width: 96, height: 192)
    canvas.interpolationQuality = .high
    for (index, bounds) in bounds.enumerated() {
        let box = bounds.applying(CGAffineTransform(scaleX: 4, y: 4))
        let ratio = min(box.width / CGFloat(source.width), box.height / CGFloat(source.height))
        let size = CGSize(width: CGFloat(source.width) * ratio, height: CGFloat(source.height) * ratio)
        let idleLift: CGFloat = index == 1 && ["lizard", "wyvern", "skeleton"].contains(name) ? 4 : 0
        let rect = CGRect(x: box.midX - size.width / 2, y: CGFloat(1 - index) * 96 + 96 - box.maxY + idleLift,
                          width: size.width, height: size.height)
        canvas.draw(source, in: rect)
    }
    let base = canvas.data!.assumingMemoryBound(to: UInt8.self)
    let maskContext = context(width: 96, height: 192)
    let mask = maskContext.data!.assumingMemoryBound(to: UInt8.self)
    for pixel in 0..<(96 * 192) {
        let offset = pixel * 4
        let red = Double(base[offset]), green = Double(base[offset + 1]), blue = Double(base[offset + 2])
        let selected = name != "crystall" && base[offset + 3] > 0 && blue > red * 1.35 && blue > green * 1.05 && blue - red > 8
        for channel in 0..<3 { mask[offset + channel] = selected ? 255 : 0 }
        mask[offset + 3] = 255
    }
    save(maskContext.makeImage()!, to: destination.appendingPathComponent("team-mask.png"))
    for (faction, palette) in palettes {
        let tinted = context(width: 96, height: 192)
        let output = tinted.data!.assumingMemoryBound(to: UInt8.self)
        output.update(from: base, count: 96 * 192 * 4)
        if faction != "blue" {
            for pixel in 0..<(96 * 192) {
                let offset = pixel * 4
                guard mask[offset] > 0 else { continue }
                let alpha = Double(base[offset + 3]) / 255
                let color = (0..<3).map { Double(base[offset + $0]) / alpha }
                let tone = luminance(color)
                let upper = stops.firstIndex(where: { $0 >= tone }) ?? (stops.count - 1)
                let lower = max(0, upper - 1)
                let progress = upper == lower ? 0 : (tone - stops[lower]) / (stops[upper] - stops[lower])
                for channel in 0..<3 {
                    let value = palette[lower][channel] * (1 - progress) + palette[upper][channel] * progress
                    output[offset + channel] = UInt8(clamping: Int((value * alpha).rounded()))
                }
            }
        }
        save(tinted.makeImage()!, to: destination.appendingPathComponent("\(faction).png"))
    }
    print("Packaged \(name): four team atlases, two frames, shared mask")
}
