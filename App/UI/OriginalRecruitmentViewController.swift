import AECore
import AEContent
import UIKit

/// AuxDisplayable modes 2 + 10 + 3: stats, full description, horizontal unit selector.
@MainActor
final class OriginalRecruitmentViewController: UIViewController {
    var onBuy: ((CampaignRecruitmentOption) -> Void)?
    private let options: [CampaignRecruitmentOption]
    private let money: Int
    private var selection = 0
    private let panel = OriginalFrameView()
    private let contentScroll = UIScrollView()
    private let content = UIStackView()
    private let statsContainer = UIView()
    private let nameLabel = OriginalArt.label(size: 20)
    private let descriptionLabel = OriginalArt.label(size: 18)
    private let balanceLabel = OriginalArt.label(size: 16)
    private let selector = UIScrollView()
    private var unitButtons: [UIButton] = []
    private lazy var buy = OriginalGameButton(GameLocalization.text("story.63"), identifier: "recruit.buy") { [weak self] in
        guard let self, self.options.indices.contains(self.selection), self.options[self.selection].isAvailable else { return }
        self.onBuy?(self.options[self.selection])
    }
    private lazy var back = OriginalGameButton(GameLocalization.text("story.65"), identifier: "recruit.cancel") { [weak self] in self?.dismiss(animated: false) }
    private lazy var previous = arrow(forward: false)
    private lazy var nextButton = arrow(forward: true)

    init(options: [CampaignRecruitmentOption], money: Int) {
        self.options = options
        self.money = money
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.accessibilityViewIsModal = true
        view.backgroundColor = PixelPalette.ink
        view.addSubview(panel)
        panel.addSubview(contentScroll)
        content.axis = .vertical
        content.spacing = 12
        content.translatesAutoresizingMaskIntoConstraints = false
        contentScroll.addSubview(content)
        contentScroll.indicatorStyle = .white
        nameLabel.numberOfLines = 0
        nameLabel.accessibilityIdentifier = "recruit.name"
        descriptionLabel.font = .systemFont(ofSize: 18, weight: .medium)
        descriptionLabel.numberOfLines = 0
        descriptionLabel.accessibilityIdentifier = "recruit.description"
        balanceLabel.accessibilityIdentifier = "recruit.balance"
        balanceLabel.numberOfLines = 0
        [nameLabel, statsContainer, descriptionLabel, balanceLabel].forEach(content.addArrangedSubview)
        statsContainer.heightAnchor.constraint(equalToConstant: 180).isActive = true
        NSLayoutConstraint.activate([
            content.leadingAnchor.constraint(equalTo: contentScroll.contentLayoutGuide.leadingAnchor),
            content.trailingAnchor.constraint(equalTo: contentScroll.contentLayoutGuide.trailingAnchor),
            content.topAnchor.constraint(equalTo: contentScroll.contentLayoutGuide.topAnchor),
            content.bottomAnchor.constraint(equalTo: contentScroll.contentLayoutGuide.bottomAnchor),
            content.widthAnchor.constraint(equalTo: contentScroll.frameLayoutGuide.widthAnchor),
        ])
        [selector, previous, nextButton, buy, back].forEach(panel.addSubview)
        selector.showsHorizontalScrollIndicator = false
        for (index, option) in options.enumerated() {
            let button = UIButton(type: .custom)
            button.accessibilityIdentifier = "recruit.option.\(index)"
            button.accessibilityLabel = "\((try? GameResources.unitDefinitions().first(where: { $0.id == option.unit.type })?.displayName) ?? GameLocalization.text("UNIT")) \(option.cost)"
            button.addAction(UIAction { [weak self] _ in self?.select(index) }, for: .touchUpInside)
            let source = OriginalArt.frame("\([1: "blue", 2: "red", 3: "green", 4: "black"][option.unit.fraction] ?? "blue")/unit_icons", x: option.unit.type * 24, width: 24, height: 24)
            let disabled = source?.cgImage.flatMap(UnitSpriteImageProcessor.actedVariant(of:)).map { UIImage(cgImage: $0, scale: source?.scale ?? 1, orientation: .up) }
            let icon = OriginalArt.pixelView(option.cost > money ? disabled : source)
            icon.frame = CGRect(x: 9, y: 10, width: 48, height: 48)
            button.addSubview(icon)
            if option.unit.type == 9 {
                let head = OriginalArt.pixelView(OriginalArt.frame("king_head_icons", x: option.unit.resolvedCommanderVariant * 13, width: 13, height: 12))
                head.frame = CGRect(x: 23, y: 10, width: 26, height: 24)
                button.addSubview(head)
            }
            selector.addSubview(button)
            unitButtons.append(button)
        }
        select(0)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        panel.frame = view.safeAreaLayoutGuide.layoutFrame
        let width = panel.bounds.width
        let height = panel.bounds.height
        back.frame = CGRect(x: 10, y: height - 60, width: (width - 28) / 2, height: 48)
        buy.frame = CGRect(x: back.frame.maxX + 8, y: back.frame.minY, width: back.bounds.width, height: 48)
        let selectorY = back.frame.minY - 88
        previous.frame = CGRect(x: 6, y: selectorY + 10, width: 44, height: 54)
        nextButton.frame = CGRect(x: width - 50, y: selectorY + 10, width: 44, height: 54)
        selector.frame = CGRect(x: 52, y: selectorY, width: width - 104, height: 78)
        let stripWidth = CGFloat(options.count) * 72
        let left = max(0, (selector.bounds.width - stripWidth) / 2)
        for (index, button) in unitButtons.enumerated() {
            button.frame = CGRect(x: left + CGFloat(index) * 72, y: 4, width: 66, height: 68)
        }
        selector.contentSize = CGSize(width: max(stripWidth, selector.bounds.width), height: 78)
        contentScroll.frame = CGRect(x: 18, y: 20, width: width - 36, height: max(1, selectorY - 30))
        statsContainer.subviews.first?.frame = statsContainer.bounds
    }

    private func arrow(forward: Bool) -> UIButton {
        let button = UIButton(type: .custom)
        button.setImage(OriginalArt.frame("side_arrow", x: forward ? 8 : 0, width: 8, height: 14), for: .normal)
        button.imageView?.layer.magnificationFilter = .nearest
        button.accessibilityIdentifier = forward ? "recruit.next" : "recruit.previous"
        button.accessibilityLabel = forward ? GameLocalization.text("Next unit") : GameLocalization.text("Previous unit")
        button.addAction(UIAction { [weak self] _ in
            guard let self, !self.options.isEmpty else { return }
            self.select((self.selection + (forward ? 1 : self.options.count - 1)) % self.options.count)
        }, for: .touchUpInside)
        return button
    }

    private func select(_ index: Int) {
        guard options.indices.contains(index) else { buy.isEnabled = false; return }
        selection = index
        let option = options[index]
        let definition = try? GameResources.unitDefinitions().first { $0.id == option.unit.type }
        nameLabel.text = (try? GameResources.campaignStoryText(id: option.unit.nameTextID)) ?? definition?.displayName
        descriptionLabel.text = definition?.description
        balanceLabel.text = GameLocalization.format("GOLD: %@", String(describing: money))
        buy.isEnabled = option.isAvailable
        statsContainer.subviews.forEach { $0.removeFromSuperview() }
        if let profile = try? GameResources.campaignUnitProfiles()[option.unit.type],
           let stats = try? OriginalUnitStatsView(unit: option.unit, profile: profile, terrainDefense: 0, purchaseCost: option.cost) {
            stats.frame = statsContainer.bounds
            stats.autoresizingMask = [.flexibleWidth, .flexibleHeight]
            statsContainer.addSubview(stats)
        }
        for (item, button) in unitButtons.enumerated() {
            button.setBackgroundImage(OriginalArt.image(item == index ? "big_circle_01" : "big_circle_00"), for: .normal)
            button.layer.magnificationFilter = .nearest
            button.accessibilityTraits = item == index ? [.button, .selected] : .button
        }
        view.layoutIfNeeded()
        selector.scrollRectToVisible(unitButtons[index].frame, animated: false)
        contentScroll.setContentOffset(.zero, animated: false)
    }

    func showUnavailable() { balanceLabel.text = GameLocalization.text("BUY UNAVAILABLE") }
}
