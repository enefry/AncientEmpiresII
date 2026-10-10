import Foundation
import CoreGraphics
import ImageIO
import UniformTypeIdentifiers

let root = URL(fileURLWithPath: FileManager.default.currentDirectoryPath)
let sourceURL = root.appendingPathComponent("reference/art/imagegen-faithful-preview/archer-blue-preview.png")
let destination = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Archer")
let colorSpace = CGColorSpace(name: CGColorSpace.sRGB)!
let bitmapInfo = CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue

func context(width: Int, height: Int) -> CGContext {
    CGContext(data: nil, width: width, height: height, bitsPerComponent: 8, bytesPerRow: width * 4,
              space: colorSpace, bitmapInfo: bitmapInfo)!
}

func save(_ image: CGImage, named name: String) {
    let output = CGImageDestinationCreateWithURL(destination.appendingPathComponent(name).appendingPathExtension("png") as CFURL,
                                               UTType.png.identifier as CFString, 1, nil)!
    CGImageDestinationAddImage(output, image, nil)
    precondition(CGImageDestinationFinalize(output))
}

let source = CGImageSourceCreateImageAtIndex(CGImageSourceCreateWithURL(sourceURL as CFURL, nil)!, 0, nil)!
let original = context(width: source.width, height: source.height)
original.draw(source, in: CGRect(x: 0, y: 0, width: source.width, height: source.height))
let bytes = original.data!.assumingMemoryBound(to: UInt8.self)
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
let crop = original.makeImage()!.cropping(to: CGRect(x: minimumX, y: minimumY,
                                                   width: maximumX - minimumX + 1, height: maximumY - minimumY + 1))!
let fitted = context(width: 96, height: 96)
fitted.interpolationQuality = .high
let fittedWidth = CGFloat(crop.width) * 96 / CGFloat(crop.height)
fitted.draw(crop, in: CGRect(x: (92 - fittedWidth) / 2, y: 0, width: fittedWidth, height: 96))
let base = fitted.data!.assumingMemoryBound(to: UInt8.self)
let maskContext = context(width: 96, height: 96)
let mask = maskContext.data!.assumingMemoryBound(to: UInt8.self)
let palettes: [(String, [[Double]])] = [
    ("blue", [[0, 0, 0], [12, 53, 112], [0, 100, 198], [65, 149, 233], [150, 217, 244], [255, 255, 255]]),
    ("red", [[0, 0, 0], [95, 5, 120], [161, 0, 112], [219, 36, 113], [244, 158, 156], [255, 255, 255]]),
    ("green", [[0, 0, 0], [0, 85, 82], [0, 153, 55], [99, 190, 37], [171, 237, 90], [255, 255, 255]]),
    ("black", [[0, 0, 0], [0, 22, 48], [0, 43, 75], [0, 65, 114], [0, 118, 150], [255, 255, 255]]),
]
func luminance(_ color: [Double]) -> Double { color[0] * 0.2126 + color[1] * 0.7152 + color[2] * 0.0722 }
let stops = palettes[0].1.map(luminance)
for pixel in 0..<(96 * 96) {
    let offset = pixel * 4
    let red = Double(base[offset]), green = Double(base[offset + 1]), blue = Double(base[offset + 2])
    let selected = base[offset + 3] > 0 && blue > red * 1.35 && blue > green * 1.05 && blue - red > 8
    for channel in 0..<3 { mask[offset + channel] = selected ? 255 : 0 }
    mask[offset + 3] = 255
}
try FileManager.default.createDirectory(at: destination, withIntermediateDirectories: true)
save(maskContext.makeImage()!, named: "team-mask")
for (faction, palette) in palettes {
    let tinted = context(width: 96, height: 96)
    let output = tinted.data!.assumingMemoryBound(to: UInt8.self)
    output.update(from: base, count: 96 * 96 * 4)
    if faction != "blue" {
        for pixel in 0..<(96 * 96) {
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
    let frame = tinted.makeImage()!
    let atlas = context(width: 96, height: 192)
    atlas.interpolationQuality = .none
    atlas.draw(frame, in: CGRect(x: 0, y: 96, width: 96, height: 96))
    atlas.draw(frame, in: CGRect(x: 4, y: 0, width: 96, height: 96))
    save(atlas.makeImage()!, named: faction)
}
print("Packaged four 96x192 archer map atlases and shared team mask at \(destination.path)")
