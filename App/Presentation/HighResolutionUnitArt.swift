import AEContent
import UIKit

@MainActor
enum HighResolutionUnitArt {
    private struct Sample {
        let reference: UInt32
        let color: UInt32
    }

    static let scale: CGFloat = ProcessInfo.processInfo.arguments.contains("--original-art") ? 1 : 4
    private static let cache: NSCache<NSString, UIImage> = {
        let cache = NSCache<NSString, UIImage>()
        cache.totalCostLimit = 48 * 1024 * 1024
        return cache
    }()
    private static var originals: [String: UIImage] = [:]
    private static var missing: Set<String> = []

    static func original(_ name: String) -> UIImage? {
        if let image = originals[name] { return image }
        guard !missing.contains(name) else { return nil }
        guard let data = try? GameResources.originalResourceData(at: "res/\(name).png"),
              let image = UIImage(data: data) else {
            missing.insert(name)
            return nil
        }
        originals[name] = image
        return image
    }

    static func image(_ name: String, rect: CGRect? = nil, useEnhanced: Bool = true) -> UIImage? {
        guard let original = original(name), let source = original.cgImage else { return nil }
        let bounds = CGRect(x: 0, y: 0, width: source.width, height: source.height)
        let cropRect = rect ?? bounds
        guard cropRect.width > 0, cropRect.height > 0, bounds.contains(cropRect), cropRect == cropRect.integral else { return nil }
        guard useEnhanced, scale > 1 else {
            guard let crop = source.cropping(to: cropRect) else { return nil }
            return UIImage(cgImage: crop)
        }
        let key = "\(name):\(rect.map { NSCoder.string(for: $0) } ?? "atlas")" as NSString
        if let image = cache.object(forKey: key) { return image }
        let image: UIImage
        if let generated = generatedArtwork(name, rect: cropRect, source: source) {
            image = generated
        } else if let generated = generatedMapUnit(name, rect: rect) {
            image = generated
        } else if rect == nil, let frameSize = frameSize(name), frameSize != bounds.size,
           source.width % Int(frameSize.width) == 0, source.height % Int(frameSize.height) == 0 {
            let format = UIGraphicsImageRendererFormat()
            format.scale = scale
            format.preferredRange = .standard
            var frames: [(CGPoint, UIImage)] = []
            for vertical in stride(from: 0, to: source.height, by: Int(frameSize.height)) {
                for horizontal in stride(from: 0, to: source.width, by: Int(frameSize.width)) {
                    let origin = CGPoint(x: horizontal, y: vertical)
                    guard let frame = self.image(name, rect: CGRect(origin: origin, size: frameSize)) else { return nil }
                    frames.append((origin, frame))
                }
            }
            image = UIGraphicsImageRenderer(size: bounds.size, format: format).image { context in
                context.cgContext.interpolationQuality = .none
                for (origin, frame) in frames { frame.draw(at: origin) }
            }
        } else {
            let components = name.split(separator: "/")
            let referenceName = components.count == 2 && ["blue", "red", "green", "black"].contains(String(components[0]))
                ? "blue/\(components[1])" : name
            guard let reference = self.original(referenceName)?.cgImage,
                  let crop = source.cropping(to: cropRect),
                  let referenceCrop = reference.cropping(to: cropRect),
                  let enlarged = upscale(crop, reference: referenceCrop) else { return nil }
            image = UIImage(cgImage: enlarged, scale: scale, orientation: .up)
        }
        cache.setObject(image, forKey: key, cost: Int(image.size.width * image.size.height * scale * scale * 4))
        return image
    }

    private static func generatedArtwork(_ name: String, rect: CGRect, source: CGImage) -> UIImage? {
        let story = GameResources.storyIllustrationNames.contains(name)
        let startup = name == "splash"
        let interface = GameResources.interfaceArtworkNames.contains(name)
        let fallbackArgument = interface ? "--classic-interface" : story ? "--classic-story" : (startup ? "--classic-startup" : "--classic-environment")
        guard !ProcessInfo.processInfo.arguments.contains(fallbackArgument),
              interface || story || startup || name == "king_head_icons" || name == "portraits" || name.hasPrefix("tiles0_") else { return nil }
        let category = interface ? "interface" : story ? "story" : (startup ? "startup" : "environment")
        let key = "\(category):\(name)" as NSString
        let atlas: UIImage
        if let cached = cache.object(forKey: key) {
            atlas = cached
        } else {
            guard let data = try? (interface ? GameResources.enhancedInterfaceData(named: name)
                                   : story ? GameResources.enhancedStoryData(named: name)
                                   : startup ? GameResources.enhancedStartupData(named: name)
                                   : GameResources.enhancedEnvironmentData(named: name)),
                  let decoded = UIImage(data: data), let image = decoded.cgImage,
                  image.width == source.width * 4, image.height == source.height * 4 else { return nil }
            atlas = decoded
            cache.setObject(decoded, forKey: key, cost: image.width * image.height * 4)
        }
        guard let crop = atlas.cgImage?.cropping(to: rect.applying(CGAffineTransform(scaleX: 4, y: 4))) else { return nil }
        return UIImage(cgImage: crop, scale: 4, orientation: .up)
    }

    private static func generatedMapUnit(_ name: String, rect: CGRect?) -> UIImage? {
        guard !ProcessInfo.processInfo.arguments.contains("--classic-units"),
              let rect, rect.minX.truncatingRemainder(dividingBy: 24) == 0, rect.width == 24, rect.height == 24,
              rect.minY == 0 || rect.minY == 24 else { return nil }
        let unitID = Int(rect.minX) / 24
        guard unitID != 1 || !ProcessInfo.processInfo.arguments.contains("--classic-archer") else { return nil }
        let components = name.split(separator: "/")
        guard components.count == 2, components[1] == "unit_icons",
              let data = try? GameResources.generatedMapUnitData(unitID: unitID, named: String(components[0])),
              let atlas = UIImage(data: data)?.cgImage,
              let crop = atlas.cropping(to: CGRect(x: 0, y: rect.minY * 4, width: 96, height: 96)) else { return nil }
        return UIImage(cgImage: crop, scale: 4, orientation: .up)
    }

    private static func frameSize(_ name: String) -> CGSize? {
        let basename = (name as NSString).lastPathComponent
        guard let data = try? GameResources.originalResourceData(at: "res/\(basename).sprite"),
              let source = String(data: data, encoding: .utf8) else { return nil }
        var width = 0, height = 0
        for line in source.split(whereSeparator: \.isNewline) {
            let fields = line.split(whereSeparator: \.isWhitespace)
            guard fields.count == 2, let value = Int(fields[1]) else { continue }
            if fields[0] == "FrameWidth" { width = value }
            if fields[0] == "FrameHeight" { height = value }
        }
        return width > 0 && height > 0 ? CGSize(width: width, height: height) : nil
    }

    private static func upscale(_ image: CGImage, reference: CGImage) -> CGImage? {
        guard image.width == reference.width, image.height == reference.height,
              let colors = pixels(image), let references = pixels(reference) else { return nil }
        var samples = zip(references, colors).map { Sample(reference: $0.0, color: $0.1) }
        var width = image.width
        var height = image.height
        for _ in 0..<2 {
            var enlarged = Array(repeating: Sample(reference: 0, color: 0), count: width * height * 4)
            for row in 0..<height {
                for column in 0..<width {
                    let center = samples[row * width + column]
                    let above = samples[max(0, row - 1) * width + column]
                    let below = samples[min(height - 1, row + 1) * width + column]
                    let left = samples[row * width + max(0, column - 1)]
                    let right = samples[row * width + min(width - 1, column + 1)]
                    let offset = row * 2 * width * 2 + column * 2
                    let edge = above.reference != below.reference && left.reference != right.reference
                    enlarged[offset] = edge && left.reference == above.reference ? left : center
                    enlarged[offset + 1] = edge && above.reference == right.reference ? right : center
                    enlarged[offset + width * 2] = edge && left.reference == below.reference ? left : center
                    enlarged[offset + width * 2 + 1] = edge && below.reference == right.reference ? right : center
                }
            }
            samples = enlarged
            width *= 2
            height *= 2
        }
        let enlargedColors = samples.map(\.color)
        let data = enlargedColors.withUnsafeBytes { Data($0) }
        guard let provider = CGDataProvider(data: data as CFData),
              let colorSpace = CGColorSpace(name: CGColorSpace.sRGB) else { return nil }
        return CGImage(width: width, height: height, bitsPerComponent: 8, bitsPerPixel: 32,
                       bytesPerRow: width * 4, space: colorSpace,
                       bitmapInfo: CGBitmapInfo(rawValue: CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue),
                       provider: provider, decode: nil, shouldInterpolate: false, intent: .defaultIntent)
    }

    private static func pixels(_ image: CGImage) -> [UInt32]? {
        guard let colorSpace = CGColorSpace(name: CGColorSpace.sRGB),
              let context = CGContext(data: nil, width: image.width, height: image.height,
                                      bitsPerComponent: 8, bytesPerRow: image.width * 4,
                                      space: colorSpace,
                                      bitmapInfo: CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue) else { return nil }
        context.interpolationQuality = .none
        context.draw(image, in: CGRect(x: 0, y: 0, width: image.width, height: image.height))
        guard let pointer = context.data?.assumingMemoryBound(to: UInt32.self) else { return nil }
        return Array(UnsafeBufferPointer(start: pointer, count: image.width * image.height))
    }
}
