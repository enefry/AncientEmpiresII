import AECore
import AEContent
import CoreGraphics
import UIKit

@MainActor
final class TitleFrameRenderer {
    private let logo: CGImage
    private let splash: CGImage
    private let glow: CGImage
    private let sineTable: [Int16]
    let scale: CGFloat

    init(useEnhanced: Bool = true) throws {
        guard
            let logo = OriginalArt.image("logo", useEnhanced: useEnhanced)?.cgImage,
            let splash = OriginalArt.image("splash", useEnhanced: useEnhanced)?.cgImage,
            let glow = OriginalArt.image("glow", useEnhanced: useEnhanced)?.cgImage
        else {
            throw RendererError.invalidImage
        }
        self.logo = logo
        self.splash = splash
        self.glow = glow
        scale = useEnhanced ? HighResolutionUnitArt.scale : 1
        sineTable = try GameResources.verifiedSineTable()
    }

    func image(for state: StartupAnimationState) throws -> CGImage {
        let width = Int(StartupAnimationState.logicalWidth)
        let height = Int(StartupAnimationState.logicalHeight)
        guard let context = CGContext(
            data: nil,
            width: width * Int(scale),
            height: height * Int(scale),
            bitsPerComponent: 8,
            bytesPerRow: width * Int(scale) * 4,
            space: CGColorSpaceCreateDeviceRGB(),
            bitmapInfo: CGBitmapInfo.byteOrder32Big.rawValue | CGImageAlphaInfo.premultipliedLast.rawValue
        ) else {
            throw RendererError.contextUnavailable
        }

        context.scaleBy(x: scale, y: scale)
        context.setFillColor(UIColor.black.cgColor)
        context.fill(CGRect(x: 0, y: 0, width: CGFloat(width), height: CGFloat(height)))

        switch state.phase {
        case .wave:
            drawWave(progress: state.progress, in: context)
        case .fade where state.progress < 16:
            drawSplash(in: context)
            let alpha = CGFloat(16 - state.progress) / 16
            context.setFillColor(UIColor.black.withAlphaComponent(alpha).cgColor)
            context.fill(CGRect(x: 0, y: 0, width: width, height: height))
            draw(logo, x: 0, y: 0, in: context)
        case .fade, .steady:
            drawSteadyFrame(glowX: state.glowX, in: context)
        }

        guard let image = context.makeImage() else { throw RendererError.contextUnavailable }
        return image
    }

    private func drawWave(progress: Int32, in context: CGContext) {
        let rows = StartupAnimationState.waveRows(
            width: StartupAnimationState.logicalWidth,
            height: Int32(logo.height / Int(scale)),
            progress: progress,
            sineTable: sineTable
        )
        for (row, geometry) in rows.enumerated() {
            context.saveGState()
            context.clip(to: CGRect(
                x: CGFloat(geometry.clipX),
                y: CGFloat(StartupAnimationState.logicalHeight) - CGFloat(row) - 1,
                width: CGFloat(geometry.visibleWidth),
                height: 1
            ))
            draw(logo, x: geometry.imageX, y: 0, in: context)
            context.restoreGState()
        }
    }

    private func drawSteadyFrame(glowX: Int32, in context: CGContext) {
        draw(logo, x: 0, y: 0, in: context)
        let glowHeight = glow.height / Int(scale)
        let rows = (logo.height / Int(scale) + glowHeight - 1) / glowHeight
        for row in 0..<rows {
            draw(
                glow,
                x: 4 + Int(glowX) - 38 * row,
                y: 6 + glowHeight * row,
                in: context
            )
        }
        drawSplash(in: context)
    }

    private func drawSplash(in context: CGContext) {
        draw(splash, x: 0, y: 0, in: context)
    }

    private func draw(_ image: CGImage, x: Int32, y: Int, in context: CGContext) {
        draw(image, x: Int(x), y: y, in: context)
    }

    private func draw(_ image: CGImage, x: Int, y: Int, in context: CGContext) {
        // J2ME uses a top-left origin; a bitmap CGContext uses a bottom-left origin.
        // Without this conversion the cover's transparent title reveals black instead of the logo.
        context.interpolationQuality = .none
        let width = image.width / Int(scale)
        let height = image.height / Int(scale)
        context.draw(image, in: CGRect(x: x, y: Int(StartupAnimationState.logicalHeight) - y - height, width: width, height: height))
    }

    func crop(_ image: CGImage, to rect: CGRect) -> UIImage? {
        image.cropping(to: rect.applying(CGAffineTransform(scaleX: scale, y: scale)))
            .map { UIImage(cgImage: $0, scale: scale, orientation: .up) }
    }

    private enum RendererError: Error {
        case invalidImage
        case contextUnavailable
    }
}
