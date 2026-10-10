import UIKit

/// AuxDisplayable mode 13: selected action text followed by compact circular action icons.
@MainActor
final class OriginalActionMenuView: UIView {
    private let title = OriginalArt.label(size: 18)
    private var controls: [UIButton] = []
    private var icons: [UIImageView] = []

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = UIColor(red: 0.47, green: 0.48, blue: 0.53, alpha: 1)
        title.textAlignment = .right
        title.adjustsFontSizeToFitWidth = true
        title.minimumScaleFactor = 0.7
        accessibilityIdentifier = "campaign.action-menu"
        addSubview(title)
    }
    required init?(coder: NSCoder) { nil }

    func configure(title: String, controls: [(UIButton, UIImage?)]) {
        self.title.text = title
        for control in self.controls { control.removeFromSuperview() }
        icons.forEach { $0.removeFromSuperview() }
        icons = []
        self.controls = controls.map(\.0)
        for (index, control) in controls.enumerated() {
            let (button, image) = control
            button.setTitle(nil, for: .normal)
            button.backgroundColor = .clear
            let selected = button.accessibilityLabel == title || (index == 0 && !controls.contains { $0.0.accessibilityLabel == title })
            button.setBackgroundImage(selected ? OriginalArt.selectedSmallCircle : OriginalArt.smallCircle, for: .normal)
            button.setBackgroundImage(OriginalArt.selectedSmallCircle, for: .highlighted)
            button.layer.magnificationFilter = .nearest
            button.layer.minificationFilter = .nearest
            addSubview(button)
            let icon = OriginalArt.pixelView(image)
            icon.isUserInteractionEnabled = false
            button.addSubview(icon)
            icons.append(icon)
        }
        setNeedsLayout()
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        let size: CGFloat = 48
        for (index, control) in controls.enumerated() {
            control.frame = CGRect(x: bounds.width - CGFloat(controls.count - index) * size, y: 0, width: size, height: size)
            icons[index].frame = control.bounds.insetBy(dx: 8, dy: 8)
        }
        title.frame = CGRect(x: 8, y: 0, width: max(1, bounds.width - CGFloat(controls.count) * size - 16), height: size)
    }
}
