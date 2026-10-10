import AECore
import AEContent
import UIKit
import Testing
@testable import Ancient_Empires_II

@MainActor
struct TitleCompositingTests {
    @Test(arguments: [false, true]) func fullyExpandedWaveIsAtTheTopAndMatchesOriginalLogoPixels(useEnhanced: Bool) throws {
        var state = StartupAnimationState()
        for _ in 0..<40 { state.advanceOneTick() }
        let renderer = try TitleFrameRenderer(useEnhanced: useEnhanced)
        let actual = try renderer.image(for: state)
        let logo = try #require(OriginalArt.image("logo", useEnhanced: useEnhanced))
        let format = UIGraphicsImageRendererFormat()
        format.scale = renderer.scale
        format.opaque = true
        let expected = UIGraphicsImageRenderer(size: CGSize(width: 240, height: 320), format: format).image { context in
            UIColor.black.setFill()
            context.fill(CGRect(x: 0, y: 0, width: 240, height: 320))
            context.cgContext.interpolationQuality = .none
            logo.draw(at: .zero)
        }
        #expect(imagesMatch(actual, try #require(expected.cgImage)))
    }

    @Test(arguments: [false, true]) func completedFadeShowsTheLogoThroughTheCover(useEnhanced: Bool) throws {
        var state = StartupAnimationState()
        for _ in 0..<57 { state.advanceOneTick() }
        let renderer = try TitleFrameRenderer(useEnhanced: useEnhanced)
        let actual = try renderer.image(for: state)
        let logo = try #require(OriginalArt.image("logo", useEnhanced: useEnhanced))
        let cover = try #require(OriginalArt.image("splash", useEnhanced: useEnhanced))
        let format = UIGraphicsImageRendererFormat()
        format.scale = renderer.scale
        format.opaque = true
        let expected = UIGraphicsImageRenderer(size: CGSize(width: 240, height: 320), format: format).image { context in
            UIColor.black.setFill()
            context.fill(CGRect(x: 0, y: 0, width: 240, height: 320))
            context.cgContext.interpolationQuality = .none
            logo.draw(at: .zero)
            cover.draw(at: .zero)
        }
        // The initial shine starts outside the title except for its first four columns.
        let crop = CGRect(x: 4, y: 0, width: 236, height: 320).applying(CGAffineTransform(scaleX: renderer.scale, y: renderer.scale))
        #expect(imagesMatch(try #require(actual.cropping(to: crop)), try #require(expected.cgImage?.cropping(to: crop))))
        #expect(actual.width == 240 * Int(renderer.scale))
        #expect(actual.height == 320 * Int(renderer.scale))
        let title = try #require(renderer.crop(actual, to: CGRect(x: 0, y: 0, width: 240, height: 85)))
        #expect(title.size == CGSize(width: 240, height: 85))
        #expect(title.scale == renderer.scale)
    }

    private func imagesMatch(_ lhs: CGImage, _ rhs: CGImage) -> Bool { pixels(lhs) == pixels(rhs) }

    private func pixels(_ image: CGImage) -> [UInt8] {
        let context = CGContext(data: nil, width: image.width, height: image.height, bitsPerComponent: 8, bytesPerRow: image.width * 4, space: CGColorSpaceCreateDeviceRGB(), bitmapInfo: CGImageAlphaInfo.premultipliedLast.rawValue)!
        context.draw(image, in: CGRect(x: 0, y: 0, width: image.width, height: image.height))
        return Array(UnsafeBufferPointer(start: context.data!.assumingMemoryBound(to: UInt8.self), count: image.width * image.height * 4))
    }
}
