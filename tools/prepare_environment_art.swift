import AppKit
import ImageIO
import UniformTypeIdentifiers

let root = URL(fileURLWithPath: FileManager.default.currentDirectoryPath)
let originals = root.appendingPathComponent("Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Original/res")
let destination = root.appendingPathComponent(".noindex/artifacts/environment-originals")
try FileManager.default.createDirectory(at: destination, withIntermediateDirectories: true)

func read(_ name: String) -> CGImage {
    CGImageSourceCreateImageAtIndex(CGImageSourceCreateWithURL(originals.appendingPathComponent(name + ".png") as CFURL, nil)!, 0, nil)!
}

func save(_ image: CGImage, _ name: String) {
    let output = CGImageDestinationCreateWithURL(destination.appendingPathComponent(name + ".png") as CFURL, UTType.png.identifier as CFString, 1, nil)!
    CGImageDestinationAddImage(output, image, nil)
    precondition(CGImageDestinationFinalize(output))
}

func canvas(_ width: Int, _ height: Int) -> CGContext {
    let context = CGContext(data: nil, width: width, height: height, bitsPerComponent: 8, bytesPerRow: width * 4,
                            space: CGColorSpace(name: CGColorSpace.sRGB)!, bitmapInfo: CGImageAlphaInfo.premultipliedLast.rawValue)!
    context.interpolationQuality = .none
    return context
}

let tiles = canvas(1152, 1008)
tiles.setFillColor(NSColor.darkGray.cgColor)
tiles.fill(CGRect(x: 0, y: 0, width: 1152, height: 1008))
NSGraphicsContext.current = NSGraphicsContext(cgContext: tiles, flipped: false)
for index in 0..<48 {
    let image = read(String(format: "tiles0_%02d", index))
    let column = index % 8, row = index / 8
    let rect = CGRect(x: column * 144, y: 1008 - (row + 1) * 168 + 24, width: 144, height: 144)
    tiles.draw(image, in: rect)
    (String(index) as NSString).draw(at: CGPoint(x: CGFloat(column * 144 + 8), y: rect.minY - 21),
                                  withAttributes: [.foregroundColor: NSColor.white, .font: NSFont.systemFont(ofSize: 16)])
}
save(tiles.makeImage()!, "tiles")
for (name, count, width, height) in [("king_head_icons", 4, 13, 12), ("portraits", 6, 85, 85)] {
    let source = read(name)
    for index in 0..<count {
        let crop = source.cropping(to: CGRect(x: index * width, y: 0, width: width, height: height))!
        let output = canvas(width * 8, height * 8)
        output.draw(crop, in: CGRect(x: 0, y: 0, width: width * 8, height: height * 8))
        save(output.makeImage()!, "\(name)-\(index)")
    }
}
print(destination.path)
