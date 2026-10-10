import AECore
import AEContent
import UIKit

@MainActor
enum OriginalArt {
    // small_circle.sprite defines two 20 × 21 frames: idle and selected.
    static let smallCircle = frame("small_circle", x: 0, width: 20, height: 21)
    static let selectedSmallCircle = frame("small_circle", x: 20, width: 20, height: 21)

    static func image(_ name: String, useEnhanced: Bool = true) -> UIImage? {
        HighResolutionUnitArt.image(name, useEnhanced: useEnhanced)
    }

    static func resourceImage(at path: String) -> UIImage? {
        guard path.hasPrefix("res/"), path.hasSuffix(".png") else { return nil }
        return image(String(path.dropFirst(4).dropLast(4)))
    }

    static func frame(_ name: String, x: Int, y: Int = 0, width: Int, height: Int, useEnhanced: Bool = true) -> UIImage? {
        HighResolutionUnitArt.image(name, rect: CGRect(x: x, y: y, width: width, height: height), useEnhanced: useEnhanced)
    }

    static func pixelView(_ image: UIImage? = nil) -> UIImageView {
        let view = UIImageView(image: image)
        view.contentMode = .scaleAspectFit
        view.layer.magnificationFilter = .nearest
        view.layer.minificationFilter = .nearest
        return view
    }

    static func label(_ text: String? = nil, size: CGFloat = 18) -> UILabel {
        let label = UILabel()
        label.text = text
        label.font = .monospacedSystemFont(ofSize: size, weight: .bold)
        label.adjustsFontSizeToFitWidth = true
        label.minimumScaleFactor = 0.6
        label.textColor = .white
        label.shadowColor = UIColor.black.withAlphaComponent(0.8)
        label.shadowOffset = CGSize(width: 1, height: 1)
        return label
    }

}

/// AuxDisplayable.drawMenuFrameEx: eight original 16px border tiles around a blue panel.
@MainActor
final class OriginalFrameView: UIView {
    var highlighted = false { didSet { setNeedsDisplay() } }
    var gradientColor: UIColor? { didSet { setNeedsDisplay() } }
    private let tiles = (0..<8).map { OriginalArt.frame("menu", x: $0 * 16, width: 16, height: 16) }

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .clear
        isOpaque = false
        contentMode = .redraw
    }

    required init?(coder: NSCoder) { nil }

    override func draw(_ rect: CGRect) {
        guard let context = UIGraphicsGetCurrentContext(), bounds.width > 0, bounds.height > 0 else { return }
        context.interpolationQuality = .none
        UIColor(red: 36/255, green: 42/255, blue: 69/255, alpha: 1).setFill()
        context.fill(bounds)
        let top = gradientColor ?? (highlighted ? UIColor(red: 0.31, green: 0.41, blue: 0.61, alpha: 1) : UIColor(red: 0.12, green: 0.26, blue: 0.49, alpha: 1))
        if let gradient = CGGradient(colorsSpace: CGColorSpaceCreateDeviceRGB(), colors: [top.cgColor, UIColor(red: 36/255, green: 42/255, blue: 69/255, alpha: 1).cgColor] as CFArray, locations: [0, 1]) {
            context.drawLinearGradient(gradient, start: CGPoint(x: 0, y: 8), end: CGPoint(x: 0, y: bounds.height * 0.4), options: [])
        }
        let size: CGFloat = min(32, bounds.height / 2, bounds.width / 2)
        func tile(_ index: Int, _ x: CGFloat, _ y: CGFloat) {
            tiles[index]?.draw(in: CGRect(x: x, y: y, width: size, height: size))
        }
        context.saveGState()
        context.clip(to: CGRect(x: size, y: 0, width: max(0, bounds.width - size * 2), height: bounds.height))
        for x in stride(from: size, to: bounds.width - size, by: size) {
            tile(1, x, 0); tile(6, x, bounds.height - size)
        }
        context.restoreGState()
        context.saveGState()
        context.clip(to: CGRect(x: 0, y: size, width: bounds.width, height: max(0, bounds.height - size * 2)))
        for y in stride(from: size, to: bounds.height - size, by: size) {
            tile(3, 0, y); tile(4, bounds.width - size, y)
        }
        context.restoreGState()
        tile(0, 0, 0); tile(2, bounds.width - size, 0)
        tile(5, 0, bounds.height - size); tile(7, bounds.width - size, bounds.height - size)
    }
}

@MainActor
final class OriginalGameButton: UIButton {
    private let panel = OriginalFrameView()
    override var isHighlighted: Bool { didSet { panel.highlighted = isHighlighted } }
    override var isEnabled: Bool { didSet { alpha = isEnabled ? 1 : 0.5 } }

    init(_ title: String, identifier: String, action: @escaping () -> Void) {
        super.init(frame: .zero)
        panel.isUserInteractionEnabled = false
        insertSubview(panel, at: 0)
        setTitle(title, for: .normal)
        setTitleColor(.white, for: .normal)
        titleLabel?.font = .monospacedSystemFont(ofSize: 16, weight: .bold)
        titleLabel?.adjustsFontSizeToFitWidth = true
        titleLabel?.minimumScaleFactor = 0.75
        accessibilityIdentifier = identifier
        addAction(UIAction { _ in action() }, for: .touchUpInside)
    }

    required init?(coder: NSCoder) { nil }

    override func layoutSubviews() {
        super.layoutSubviews()
        panel.frame = bounds
        sendSubviewToBack(panel)
    }
}

@MainActor
final class OriginalDialogView: UIView {
    private let panel = OriginalFrameView()
    private let portrait = OriginalArt.pixelView()
    private let scrollView = UIScrollView()
    let textLabel = OriginalArt.label(size: 17)
    let continueButton = UIButton(type: .custom)
    var onContinue: (() -> Void)?
    private var portraitID: Int?

    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubview(panel)
        addSubview(portrait)
        addSubview(scrollView)
        textLabel.numberOfLines = 0
        textLabel.accessibilityIdentifier = "campaign.story.text"
        scrollView.addSubview(textLabel)
        scrollView.indicatorStyle = .white
        continueButton.setImage(OriginalArt.frame("arrow", x: 14, width: 14, height: 8), for: .normal)
        continueButton.imageView?.layer.magnificationFilter = .nearest
        continueButton.imageView?.contentMode = .scaleAspectFit
        continueButton.accessibilityLabel = GameLocalization.text("Continue")
        continueButton.addAction(UIAction { [weak self] _ in self?.onContinue?() }, for: .touchUpInside)
        addSubview(continueButton)
    }

    required init?(coder: NSCoder) { nil }

    func configure(text: String, portraitID: Int?, identifier: String) {
        if textLabel.text != text { scrollView.setContentOffset(.zero, animated: false) }
        textLabel.text = text
        self.portraitID = portraitID
        portrait.image = portraitID.flatMap { OriginalArt.frame("portraits", x: $0 * 85, width: 85, height: 85) }
        portrait.isHidden = portraitID == nil
        continueButton.accessibilityIdentifier = identifier
        setNeedsLayout()
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        panel.frame = bounds
        let portraitSize = min(150, bounds.width * 0.36)
        portrait.frame = CGRect(x: -8, y: bounds.height - portraitSize - 8, width: portraitSize, height: portraitSize)
        let left = portraitID == nil ? 18 : portraitSize - 3
        scrollView.frame = CGRect(x: left, y: 16, width: bounds.width - left - 22, height: bounds.height - 42)
        let textSize = textLabel.sizeThatFits(CGSize(width: scrollView.bounds.width, height: .greatestFiniteMagnitude))
        textLabel.frame = CGRect(origin: .zero, size: CGSize(width: scrollView.bounds.width, height: textSize.height))
        scrollView.contentSize = textLabel.bounds.size
        continueButton.frame = CGRect(x: bounds.width - 52, y: bounds.height - 44, width: 44, height: 44)
    }
}
