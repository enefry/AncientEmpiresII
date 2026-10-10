import AEContent
import UIKit

/// Reads the original .sprite frame mapping, mirror flag, alignment and Translate anchors.
/// Split images (king, skeleton...) must align inside the logical frame before translation.
@MainActor
final class OriginalSprite {
    struct Frame {
        let image: UIImage
        let offset: CGPoint
    }

    let size: CGSize
    let frames: [Frame]
    let sequences: [[Int]]
    let frameDelay: Int

    init(_ name: String, fraction: Int? = nil, useEnhanced: Bool = true) throws {
        let data = try GameResources.originalResourceData(at: "res/\(name).sprite")
        guard let source = String(data: data, encoding: .utf8) else { throw SpriteError.invalid(name) }
        var width = 0, height = 0, count = 0, alignment = 0, delay = 1
        var definitions: [Int: (image: Int, transform: Int)] = [:]
        var translations: [Int: CGPoint] = [:]
        var sequencesByID: [Int: [Int]] = [:]
        for line in source.split(whereSeparator: \.isNewline) {
            let fields = line.split(whereSeparator: \.isWhitespace)
            let values = fields.dropFirst().compactMap { Int($0) }
            guard let key = fields.first, !values.isEmpty else { continue }
            switch key {
            case "FrameCount": count = values[0]
            case "FrameWidth": width = values[0]
            case "FrameHeight": height = values[0]
            case "Align": alignment = values[0]
            case "FrameDelay": delay = values[0]
            case "FrameDef" where values.count == 3: definitions[values[0]] = (values[1], values[2])
            case "Translate" where values.count == 3: translations[values[0]] = CGPoint(x: values[1], y: values[2])
            case "FrameSeq" where values.count >= 2: sequencesByID[values[0]] = Array(values.dropFirst(2).prefix(values[1]))
            default: break
            }
        }
        guard width > 0, height > 0, count > 0, definitions.count == count else { throw SpriteError.invalid(name) }
        size = CGSize(width: width, height: height)
        frameDelay = max(1, delay)
        sequences = sequencesByID.isEmpty ? [Array(0..<count)] : sequencesByID.keys.sorted().compactMap { sequencesByID[$0] }
        let prefix = fraction.flatMap { (1...4).contains($0) ? ["blue", "red", "green", "black"][$0 - 1] + "/" : nil } ?? ""
        func load(_ resource: String) -> UIImage? {
            OriginalArt.image(prefix + resource, useEnhanced: useEnhanced) ?? OriginalArt.image(resource, useEnhanced: useEnhanced)
        }
        let atlasName = HighResolutionUnitArt.original(prefix + name) == nil ? name : prefix + name
        let atlasImage = HighResolutionUnitArt.original(atlasName)
        let atlas = atlasImage?.cgImage
        let atlasScale = atlasImage?.scale ?? 1
        var rawFrames: [Int: UIImage] = [:]
        for index in Set(definitions.values.map(\.image)) {
            if let atlas {
                let columns = Int(CGFloat(atlas.width) / atlasScale) / width
                guard columns > 0,
                      let crop = OriginalArt.frame(atlasName, x: index % columns * width, y: index / columns * height,
                                                   width: width, height: height, useEnhanced: useEnhanced) else {
                    throw SpriteError.invalid(name)
                }
                rawFrames[index] = crop
            } else {
                guard let image = load(String(format: "%@_%02d", name, index)) else { throw SpriteError.invalid(name) }
                rawFrames[index] = image
            }
        }
        frames = try (0..<count).map { index in
            guard let definition = definitions[index], let original = rawFrames[definition.image] else { throw SpriteError.invalid(name) }
            // All combat definitions use no transform or horizontal mirror.
            guard definition.transform == 0 || definition.transform == 1 else { throw SpriteError.invalid(name) }
            let mirrored = definition.transform == 1
            var offset = translations[index] ?? .zero
            if atlas == nil, alignment > 0 {
                if alignment & 1 != 0 { offset.x += (CGFloat(width) - original.size.width) / 2 }
                else if (alignment & 8 != 0) != mirrored { offset.x += CGFloat(width) - original.size.width }
                if alignment & 32 != 0 { offset.y += CGFloat(height) - original.size.height }
                else if alignment & 2 != 0 { offset.y += (CGFloat(height) - original.size.height) / 2 }
            }
            guard mirrored else { return Frame(image: original, offset: offset) }
            let format = UIGraphicsImageRendererFormat()
            format.scale = original.scale
            let image = UIGraphicsImageRenderer(size: original.size, format: format).image { renderer in
                renderer.cgContext.interpolationQuality = .none
                renderer.cgContext.translateBy(x: original.size.width, y: 0)
                renderer.cgContext.scaleBy(x: -1, y: 1)
                original.draw(at: .zero)
            }
            return Frame(image: image, offset: offset)
        }
    }

    func frameIndex(sequence: Int, tick: Int, mirrored: Bool = false, looping: Bool = true) -> Int {
        let values = sequences[min(max(0, sequence), sequences.count - 1)]
        let offset = max(0, tick) / frameDelay
        let frame = values[looping ? offset % values.count : min(offset, values.count - 1)]
        return frame + (mirrored ? frames.count / 2 : 0)
    }

    func draw(frame index: Int, at position: CGPoint, scale: CGFloat = 1) {
        guard frames.indices.contains(index) else { return }
        let frame = frames[index]
        frame.image.draw(in: CGRect(x: position.x + frame.offset.x * scale, y: position.y + frame.offset.y * scale,
                                    width: frame.image.size.width * scale, height: frame.image.size.height * scale))
    }

    enum SpriteError: Error { case invalid(String) }
}
