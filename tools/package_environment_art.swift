import Foundation
import CoreGraphics
import ImageIO
import UniformTypeIdentifiers

let root = URL(fileURLWithPath: FileManager.default.currentDirectoryPath)
let sourceRoot = root.appendingPathComponent("reference/art/imagegen-environment")
let originalRoot = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Original/res")
let outputRoot = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Environment")
let colorSpace = CGColorSpace(name: CGColorSpace.sRGB)!
let bitmapInfo = CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue
try FileManager.default.createDirectory(at: outputRoot, withIntermediateDirectories: true)

func context(_ width: Int, _ height: Int) -> CGContext {
    let output = CGContext(data: nil, width: width, height: height, bitsPerComponent: 8, bytesPerRow: width * 4,
                           space: colorSpace, bitmapInfo: bitmapInfo)!
    output.interpolationQuality = .high
    return output
}

func read(_ directory: URL, _ name: String) -> CGImage {
    let url = directory.appendingPathComponent(name + ".png")
    guard let source = CGImageSourceCreateWithURL(url as CFURL, nil),
          let image = CGImageSourceCreateImageAtIndex(source, 0, nil) else { fatalError("Missing image: \(url.path)") }
    return image
}

func save(_ image: CGImage, _ name: String) {
    let destination = CGImageDestinationCreateWithURL(outputRoot.appendingPathComponent(name + ".png") as CFURL,
                                                    UTType.png.identifier as CFString, 1, nil)!
    CGImageDestinationAddImage(destination, image, nil)
    precondition(CGImageDestinationFinalize(destination))
}

func bytes(_ image: CGImage) -> [UInt8] {
    let output = context(image.width, image.height)
    output.draw(image, in: CGRect(x: 0, y: 0, width: image.width, height: image.height))
    return Array(UnsafeBufferPointer(start: output.data!.assumingMemoryBound(to: UInt8.self), count: image.width * image.height * 4))
}

func occupiedBounds(_ image: CGImage, excludingGround: Bool = false) -> CGRect {
    let pixels = bytes(image)
    var left = image.width, top = image.height, right = -1, bottom = -1
    for vertical in 0..<image.height {
        for horizontal in 0..<image.width {
            let offset = (vertical * image.width + horizontal) * 4
            guard pixels[offset + 3] > 8 else { continue }
            if excludingGround && Array(pixels[offset..<(offset + 3)]) == [244, 244, 230] { continue }
            left = min(left, horizontal); right = max(right, horizontal)
            top = min(top, vertical); bottom = max(bottom, vertical)
        }
    }
    precondition(right >= left && bottom >= top)
    return CGRect(x: left, y: top, width: right - left + 1, height: bottom - top + 1)
}

func trimmed(_ name: String) -> CGImage {
    let image = read(sourceRoot, name)
    let output = context(image.width, image.height)
    output.draw(image, in: CGRect(x: 0, y: 0, width: image.width, height: image.height))
    let pixels = output.data!.assumingMemoryBound(to: UInt8.self)
    for offset in stride(from: 0, to: image.width * image.height * 4, by: 4) where pixels[offset + 3] <= 8 {
        for channel in 0..<4 { pixels[offset + channel] = 0 }
    }
    let cleaned = output.makeImage()!
    return cleaned.cropping(to: occupiedBounds(cleaned))!
}

func fit(_ image: CGImage, into bounds: CGRect, on output: CGContext) {
    let ratio = min(bounds.width / CGFloat(image.width), bounds.height / CGFloat(image.height))
    let size = CGSize(width: CGFloat(image.width) * ratio, height: CGFloat(image.height) * ratio)
    output.draw(image, in: CGRect(x: bounds.midX - size.width / 2, y: bounds.minY, width: size.width, height: size.height))
}

for (name, prefix, count, width, height) in [("king_head_icons", "head", 4, 13, 12), ("portraits", "portrait", 6, 85, 85)] {
    let atlas = context(count * width * 4, height * 4)
    let original = read(originalRoot, name)
    for index in 0..<count {
        let frame = original.cropping(to: CGRect(x: index * width, y: 0, width: width, height: height))!
        let occupied = occupiedBounds(frame).applying(CGAffineTransform(scaleX: 4, y: 4))
        let bounds = CGRect(x: CGFloat(index * width * 4) + occupied.minX,
                            y: CGFloat(height * 4) - occupied.maxY, width: occupied.width, height: occupied.height)
        fit(trimmed("\(prefix)-\(index)"), into: bounds, on: atlas)
    }
    save(atlas.makeImage()!, name)
}

let textures: [String: [UInt8]] = Dictionary(uniqueKeysWithValues: ["water", "earth", "ground"].map { name in
    let output = context(96, 96)
    output.draw(read(sourceRoot, name), in: CGRect(x: 0, y: 0, width: 96, height: 96))
    return (name, bytes(output.makeImage()!))
})
let averages = textures.mapValues { pixels in
    (0..<3).map { channel in (0..<(96 * 96)).reduce(0.0) { $0 + Double(pixels[$1 * 4 + channel]) } / Double(96 * 96) }
}

func ground(_ output: CGContext) {
    output.setFillColor(CGColor(colorSpace: colorSpace, components: [244 / 255.0, 244 / 255.0, 230 / 255.0, 1])!)
    output.fill(CGRect(x: 0, y: 0, width: 96, height: 96))
}

func materialize(_ original: CGImage) -> CGImage {
    let output = context(96, 96)
    output.draw(original, in: CGRect(x: 0, y: 0, width: 96, height: 96))
    let pixels = output.data!.assumingMemoryBound(to: UInt8.self)
    let originalPixels = bytes(original)
    for vertical in 0..<96 {
        for horizontal in 0..<96 {
            let offset = (vertical * 96 + horizontal) * 4
            if horizontal == 0 || horizontal == 95 || vertical == 0 || vertical == 95 {
                let originalOffset = ((vertical / 4) * 24 + horizontal / 4) * 4
                for channel in 0..<4 { pixels[offset + channel] = originalPixels[originalOffset + channel] }
                continue
            }
            let red = Int(pixels[offset]), green = Int(pixels[offset + 1]), blue = Int(pixels[offset + 2])
            let name: String
            if blue > 160 && blue > red * 2 && green < 140 { name = "water" }
            else if red > 100 && red > green && green > blue { name = "earth" }
            else if red > 235 && green > 235 && blue > 220 { name = "ground" }
            else { continue }
            let edge = Double(min(horizontal, vertical, 95 - horizontal, 95 - vertical))
            let amount = min(1, edge / 12) * (name == "ground" ? 0.12 : 0.48)
            for channel in 0..<3 {
                let variation = Double(textures[name]![offset + channel]) - averages[name]![channel]
                pixels[offset + channel] = UInt8(clamping: Int((Double(pixels[offset + channel]) + variation * amount).rounded()))
            }
        }
    }
    return output.makeImage()!
}

let objects: [Int: String] = [15: "trees", 16: "trees-sparse", 17: "mountain", 19: "hills", 27: "ruined-castle",
                            28: "wall", 31: "crater", 32: "temple", 33: "hills", 34: "ancient-tower",
                            35: "hills", 36: "ancient-palace", 47: "volcano"]
let palettes: [[[Double]]] = [
    [[0, 0, 0], [12, 53, 112], [0, 100, 198], [65, 149, 233], [150, 217, 244], [255, 255, 255]],
    [[0, 0, 0], [95, 5, 120], [161, 0, 112], [219, 36, 113], [244, 158, 156], [255, 255, 255]],
    [[0, 0, 0], [0, 85, 82], [0, 153, 55], [99, 190, 37], [171, 237, 90], [255, 255, 255]],
    [[0, 0, 0], [0, 22, 48], [0, 43, 75], [0, 65, 114], [0, 118, 150], [255, 255, 255]],
]
let stops = palettes[0].map { $0[0] * 0.2126 + $0[1] * 0.7152 + $0[2] * 0.0722 }

func building(_ kind: String, faction: Int?) -> CGImage {
    let output = context(96, 96)
    ground(output)
    let overlay = context(96, 96)
    fit(trimmed(kind), into: CGRect(x: 0, y: 0, width: 96, height: 96), on: overlay)
    let pixels = overlay.data!.assumingMemoryBound(to: UInt8.self)
    let mask = context(96, 96)
    let selection = mask.data!.assumingMemoryBound(to: UInt8.self)
    for offset in stride(from: 0, to: 96 * 96 * 4, by: 4) {
        let red = Double(pixels[offset]), green = Double(pixels[offset + 1]), blue = Double(pixels[offset + 2])
        let alpha = Double(pixels[offset + 3]) / 255
        let horizontal = offset / 4 % 96, vertical = offset / 4 / 96
        let teamRegion = kind == "castle"
            ? (35...60).contains(horizontal) && (23...65).contains(vertical)
            : vertical < 72
        let selected = teamRegion && alpha > 0 && blue > red * 1.35 && blue > green * 1.05 && blue - red > 8
        for channel in 0..<3 { selection[offset + channel] = selected ? 255 : 0 }
        selection[offset + 3] = 255
        guard selected else { continue }
        let tone = (red * 0.2126 + green * 0.7152 + blue * 0.0722) / alpha
        if let faction {
            if faction == 0 { continue }
            let palette = palettes[faction]
            let upper = stops.firstIndex(where: { $0 >= tone }) ?? (stops.count - 1)
            let lower = max(0, upper - 1)
            let progress = upper == lower ? 0 : (tone - stops[lower]) / (stops[upper] - stops[lower])
            for channel in 0..<3 {
                let color = palette[lower][channel] * (1 - progress) + palette[upper][channel] * progress
                pixels[offset + channel] = UInt8(clamping: Int((color * alpha).rounded()))
            }
        } else {
            for channel in 0..<3 { pixels[offset + channel] = UInt8(clamping: Int(tone * alpha)) }
        }
    }
    if faction == 0 { save(mask.makeImage()!, kind + "-team-mask") }
    output.draw(overlay.makeImage()!, in: CGRect(x: 0, y: 0, width: 96, height: 96))
    return output.makeImage()!
}

for index in 0..<48 {
    let name = String(format: "tiles0_%02d", index)
    let original = read(originalRoot, name)
    let image: CGImage
    if index >= 37 && index <= 46 {
        image = building(index % 2 == 1 ? "village" : "castle", faction: index < 39 ? nil : (index - 39) / 2)
    } else if index == 29 || index == 30 {
        let output = context(96, 96)
        if index == 30 {
            output.translateBy(x: 96, y: 0)
            output.rotate(by: .pi / 2)
        }
        output.draw(trimmed("bridge"), in: CGRect(x: 0, y: 0, width: 96, height: 96))
        image = output.makeImage()!
    } else if let object = objects[index] {
        let output = context(96, 96)
        if index != 28 { ground(output) }
        let occupied = occupiedBounds(original, excludingGround: true).applying(CGAffineTransform(scaleX: 4, y: 4))
        let bounds = CGRect(x: occupied.minX, y: 96 - occupied.maxY, width: occupied.width, height: occupied.height)
        if index == 33 || index == 35 {
            output.translateBy(x: 96, y: 0)
            output.scaleBy(x: -1, y: 1)
        }
        fit(trimmed(object), into: bounds, on: output)
        image = output.makeImage()!
    } else {
        image = materialize(original)
    }
    save(image, name)
}
print("Packaged 4 map heads, 6 portraits and 48 terrain/building tiles")
