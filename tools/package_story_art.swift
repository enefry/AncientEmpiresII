import Foundation
import CoreGraphics
import ImageIO
import UniformTypeIdentifiers

let root = URL(fileURLWithPath: FileManager.default.currentDirectoryPath)
let sourceRoot = root.appendingPathComponent("reference/art/imagegen-story")
let resourceRoot = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources")
let outputRoot = resourceRoot.appendingPathComponent("Enhanced/Story")
try FileManager.default.createDirectory(at: outputRoot, withIntermediateDirectories: true)

func read(_ url: URL) -> CGImage {
    guard let source = CGImageSourceCreateWithURL(url as CFURL, nil),
          let image = CGImageSourceCreateImageAtIndex(source, 0, nil) else { fatalError("Missing image: \(url.path)") }
    return image
}

for index in 0..<6 {
    let name = "intro\(index).png"
    let original = read(resourceRoot.appendingPathComponent("Original/res/\(name)"))
    let image = read(sourceRoot.appendingPathComponent(name))
    let width = original.width * 4
    let height = original.height * 4
    let output = CGContext(data: nil, width: width, height: height, bitsPerComponent: 8,
                           bytesPerRow: width * 4, space: CGColorSpace(name: CGColorSpace.sRGB)!,
                           bitmapInfo: CGImageAlphaInfo.noneSkipLast.rawValue)!
    let bounds = CGRect(x: 0, y: 0, width: width, height: height)
    output.setFillColor(CGColor(gray: 0, alpha: 1))
    output.fill(bounds)
    let ratio = min(CGFloat(width) / CGFloat(image.width), CGFloat(height) / CGFloat(image.height))
    let size = CGSize(width: CGFloat(image.width) * ratio, height: CGFloat(image.height) * ratio)
    output.interpolationQuality = .high
    output.draw(image, in: CGRect(x: (CGFloat(width) - size.width) / 2, y: (CGFloat(height) - size.height) / 2,
                                 width: size.width, height: size.height))
    let destination = CGImageDestinationCreateWithURL(outputRoot.appendingPathComponent(name) as CFURL,
                                                     UTType.png.identifier as CFString, 1, nil)!
    CGImageDestinationAddImage(destination, output.makeImage()!, nil)
    precondition(CGImageDestinationFinalize(destination))
    print("\(name): \(width)×\(height)")
}
