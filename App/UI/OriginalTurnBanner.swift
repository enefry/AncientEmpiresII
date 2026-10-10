import AECore
import AEContent
import UIKit

/// Original two-line NEW TURN / INCOME message, centred over the battlefield.
@MainActor
final class OriginalTurnBanner: UIControl {
    var onSkip: (() -> Void)?
    private let panel = OriginalFrameView()
    private let title = OriginalArt.label(size: 23)
    private let income = OriginalArt.label(size: 22)
    private let divider = UIView()

    init(turn: CampaignTurnPresentation, revealIncome: Bool? = nil) {
        super.init(frame: .zero)
        title.text = try? GameResources.campaignStoryText(id: 75)
        income.text = ((try? GameResources.campaignStoryText(id: 76)) ?? GameLocalization.text("story.76"))
            .replacingOccurrences(of: "%U", with: (revealIncome ?? (turn.fraction == 1)) ? String(turn.income) : "?")
        title.textAlignment = .center
        income.textAlignment = .center
        title.accessibilityIdentifier = "campaign.new-turn.title"
        income.accessibilityIdentifier = "campaign.new-turn.income"
        accessibilityIdentifier = "campaign.new-turn"
        accessibilityValue = GameLocalization.format("fraction=%@", String(turn.fraction))
        isAccessibilityElement = true
        accessibilityLabel = [title.text, income.text].compactMap { $0 }.joined(separator: ", ")
        accessibilityTraits = .button
        panel.isUserInteractionEnabled = false
        divider.isUserInteractionEnabled = false
        addAction(UIAction { [weak self] _ in self?.skip() }, for: .touchUpInside)
        panel.gradientColor = [
            1: UIColor(red: 0, green: 101/255, blue: 198/255, alpha: 1),
            2: UIColor(red: 232/255, green: 0, blue: 82/255, alpha: 1),
            3: UIColor(red: 0.05, green: 0.55, blue: 0.25, alpha: 1),
            4: UIColor(white: 0.15, alpha: 1),
        ][turn.fraction]
        divider.backgroundColor = UIColor(white: 0.65, alpha: 1)
        addSubview(panel)
        addSubview(title)
        addSubview(income)
        addSubview(divider)
    }
    required init?(coder: NSCoder) { nil }

    override func accessibilityActivate() -> Bool {
        guard isEnabled else { return false }
        skip()
        return true
    }

    private func skip() {
        guard isEnabled else { return }
        isEnabled = false
        onSkip?()
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        panel.frame = bounds
        title.frame = CGRect(x: 16, y: 8, width: bounds.width - 32, height: bounds.height / 2 - 8)
        divider.frame = CGRect(x: 4, y: bounds.midY, width: bounds.width - 8, height: 1)
        income.frame = CGRect(x: 16, y: bounds.midY + 1, width: bounds.width - 32, height: bounds.height / 2 - 9)
    }
}
