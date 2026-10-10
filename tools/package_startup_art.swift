import Foundation
import CoreGraphics
import ImageIO
import UniformTypeIdentifiers

let root = URL(fileURLWithPath: FileManager.default.currentDirectoryPath)
let originalURL = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Original/res/splash.png")
let sourceURL = root.appendingPathComponent("reference/art/imagegen-startup/splash-characters.png")
let outputURL = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Startup/splash.png")
try FileManager.default.createDirectory(at: outputURL.deletingLastPathComponent(), withIntermediateDirectories: true)

func read(_ url: URL) -> CGImage {
    guard let source = CGImageSourceCreateWithURL(url as CFURL, nil),
          let image = CGImageSourceCreateImageAtIndex(source, 0, nil) else { fatalError("Missing image: \(url.path)") }
    return image
}

let original = read(originalURL)
let generated = read(sourceURL)
let output = CGContext(data: nil, width: 960, height: 1280, bitsPerComponent: 8, bytesPerRow: 960 * 4,
                       space: CGColorSpace(name: CGColorSpace.sRGB)!,
                       bitmapInfo: CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue)!

output.interpolationQuality = .none
output.draw(original, in: CGRect(x: 0, y: 0, width: 960, height: 1280))

let targetRatio = 960.0 / 940.0
let cropHeight = min(generated.height, Int(Double(generated.width) / targetRatio))
let generatedCrop = generated.cropping(to: CGRect(x: 0, y: 0, width: generated.width, height: cropHeight))!
output.interpolationQuality = .high
output.draw(generatedCrop, in: CGRect(x: 0, y: 0, width: 960, height: 940))

let destination = CGImageDestinationCreateWithURL(outputURL as CFURL, UTType.png.identifier as CFString, 1, nil)!
CGImageDestinationAddImage(destination, output.makeImage()!, nil)
precondition(CGImageDestinationFinalize(destination))
print("splash.png: 960x1280")
