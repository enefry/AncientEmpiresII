import UIKit

enum PixelPalette {
    static let ink = UIColor(red: 0.055, green: 0.063, blue: 0.075, alpha: 1)
    static let panel = UIColor(red: 0.105, green: 0.11, blue: 0.12, alpha: 1)
    static let crimson = UIColor(red: 0.64, green: 0.055, blue: 0.16, alpha: 1)
    static let gold = UIColor(red: 0.87, green: 0.73, blue: 0.43, alpha: 1)
    static let parchment = UIColor(red: 0.91, green: 0.87, blue: 0.77, alpha: 1)
    static let muted = UIColor(red: 0.64, green: 0.64, blue: 0.61, alpha: 1)
}

extension UIViewController {
    func makeActionButton(
        title: String,
        identifier: String,
        primary: Bool = false,
        action: @escaping () -> Void
    ) -> UIButton {
        var configuration = UIButton.Configuration.filled()
        configuration.title = title
        configuration.baseBackgroundColor = primary ? PixelPalette.crimson : PixelPalette.panel
        configuration.baseForegroundColor = PixelPalette.parchment
        configuration.cornerStyle = .small
        configuration.contentInsets = NSDirectionalEdgeInsets(top: 15, leading: 18, bottom: 15, trailing: 18)

        let button = UIButton(configuration: configuration)
        button.accessibilityIdentifier = identifier
        button.addAction(UIAction { _ in action() }, for: .touchUpInside)
        button.heightAnchor.constraint(greaterThanOrEqualToConstant: 52).isActive = true
        return button
    }
}
