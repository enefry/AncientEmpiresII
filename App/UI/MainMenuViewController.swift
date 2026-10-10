import AECore
import AEContent
import UIKit

@MainActor
final class MainMenuViewController: UIViewController {
    var onStartSkirmish: ((CampaignMap, SkirmishConfiguration) throws -> Void)?
    var onStartCampaign: ((Int) -> Void)?
    var onLoadCampaign: ((CampaignSnapshot) -> Void)?
    var onReplayTitle: (() -> Void)?
    var onContinueGame: (() -> Void)?
    private let cover = OriginalArt.pixelView(OriginalArt.frame("splash", x: 0, y: 85, width: 240, height: 235))
    private let titleArt = OriginalArt.pixelView(OriginalArt.frame("splash", x: 0, width: 240, height: 85))
    private let heading = OriginalArt.label(GameLocalization.text("story.1"), size: 25)
    private let headingPanel = OriginalFrameView()
    private var iconButtons: [UIButton] = []
    private var selectedIndex = 0
    private var isPlayMenu = false
    private var motion = CircleMenuAnimation(count: 6, radius: 74)
    private var clock: CADisplayLink?
    private var previousTime: CFTimeInterval = 0
    private var accumulator: CFTimeInterval = 0
    private let menuIcons = try? OriginalSprite("menu_icons")
    private let unlockedMissionIndices: [Int]
    private let canContinueGame: Bool
    private let sparkSprite = try? OriginalSprite("small_spark")
    private var sparks = (0..<3).map { _ in MenuSpark() }
    private struct MenuSpark {
        var x = Int.random(in: 0..<32)
        var y = Int.random(in: 0..<33)
        var frame = Int.random(in: 0..<5)
    }
    private lazy var backButton = OriginalGameButton(GameLocalization.text("story.31"), identifier: "menu.back") { [weak self] in self?.showMainMenu() }
    private lazy var selectButton = OriginalGameButton(GameLocalization.text("story.12"), identifier: "menu.select") { [weak self] in self?.activateSelection() }
    private var labels: [String] { isPlayMenu ? [GameLocalization.text("story.2"), GameLocalization.text("story.5"), GameLocalization.text("story.3"), GameLocalization.text("story.6")] : [GameLocalization.text("story.1"), GameLocalization.text("CONTINUE GAME"), GameLocalization.text("story.8"), GameLocalization.text("story.9"), GameLocalization.text("story.10"), GameLocalization.text("story.11")] }
    // Original MAIN_MENU_ITEMS = [0,6,5,7,8,9], PLAY_MENU_ITEMS = [1,2,3,4].
    private var iconFrames: [Int] { isPlayMenu ? [1,2,3,4] : [0,6,5,7,8,9] }

    init(unlockedMissionIndices: [Int] = [0], canContinueGame: Bool = false) {
        self.unlockedMissionIndices = unlockedMissionIndices.isEmpty ? [0] : unlockedMissionIndices
        self.canContinueGame = canContinueGame
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = UIColor(red: 0.10, green: 0.01, blue: 0.02, alpha: 1)
        cover.contentMode = .scaleAspectFill
        cover.clipsToBounds = true
        cover.layer.magnificationFilter = .linear
        cover.layer.minificationFilter = .linear
        view.addSubview(cover)
        if let renderer = try? TitleFrameRenderer() {
            var state = StartupAnimationState()
            for _ in 0..<57 { state.advanceOneTick() }
            if let frame = try? renderer.image(for: state) {
                titleArt.image = renderer.crop(frame, to: CGRect(x: 0, y: 0, width: 240, height: 85))
            }
        }
        view.addSubview(titleArt)
        titleArt.layer.magnificationFilter = .linear
        titleArt.layer.minificationFilter = .linear
        titleArt.isAccessibilityElement = true
        titleArt.accessibilityIdentifier = "menu.logo"
        titleArt.accessibilityLabel = "Ancient Empires II"
        view.addSubview(headingPanel)
        heading.textAlignment = .center
        headingPanel.addSubview(heading)
        view.addSubview(backButton)
        view.addSubview(selectButton)
        showMainMenu()
        let left = UISwipeGestureRecognizer(target: self, action: #selector(nextItem))
        left.direction = .left
        view.addGestureRecognizer(left)
        let right = UISwipeGestureRecognizer(target: self, action: #selector(previousItem))
        right.direction = .right
        view.addGestureRecognizer(right)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let titleHeight = view.bounds.width * 85 / 240
        titleArt.frame = CGRect(x: 0, y: view.safeAreaInsets.top, width: view.bounds.width, height: titleHeight)
        cover.frame = CGRect(x: 0, y: titleArt.frame.maxY, width: view.bounds.width, height: view.bounds.height - titleArt.frame.maxY)
        let safe = view.safeAreaInsets
        let headerY = max(safe.top + 12, view.bounds.height * 0.26)
        headingPanel.frame = CGRect(x: 6, y: headerY, width: view.bounds.width - 12, height: 52)
        heading.frame = headingPanel.bounds.insetBy(dx: 12, dy: 8)
        let bottom = view.bounds.height - safe.bottom - 54
        backButton.frame = CGRect(x: 6, y: bottom, width: 88, height: 48)
        selectButton.frame = CGRect(x: view.bounds.width - 94, y: bottom, width: 88, height: 48)
        layoutIcons()
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        previousTime = 0
        accumulator = 0
        let link = CADisplayLink(target: MenuClockTarget(self), selector: #selector(MenuClockTarget.tick(_:)))
        link.add(to: .main, forMode: .common)
        clock = link
    }

    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        clock?.invalidate()
        clock = nil
    }

    fileprivate func tick(_ link: CADisplayLink) {
        defer { previousTime = link.timestamp }
        guard UIApplication.shared.applicationState == .active, presentedViewController == nil,
              previousTime > 0 else { return }
        accumulator += min(0.2, link.timestamp - previousTime)
        while accumulator >= 0.066 {
            accumulator -= 0.066
            let wasTurning = motion.dynamicShift != 0
            motion.advanceOneTick()
            if wasTurning && motion.dynamicShift == 0 {
                sparks = (0..<3).map { _ in MenuSpark() }
            } else {
                for index in sparks.indices {
                    if sparks[index].frame == 4 {
                        sparks[index].x = Int.random(in: 0..<25)
                        sparks[index].y = Int.random(in: 0..<26)
                    }
                    sparks[index].frame = (sparks[index].frame + 1) % 5
                }
            }
        }
        layoutIcons()
    }

    private func layoutIcons() {
        let scale = min(2.2, view.bounds.width / 240)
        let diameter = max(52, 33 * scale)
        let sourceScale = diameter / 33
        let radius = min(view.bounds.width * 0.31, (selectButton.frame.minY - headingPanel.frame.maxY - diameter) / 2)
        motion.resize(radius: Int(max(1, radius / sourceScale)))
        let center = CGPoint(x: view.bounds.midX, y: headingPanel.frame.maxY + radius + diameter / 2 + 18)
        for (index, button) in iconButtons.enumerated() {
            let offset = motion.offset(at: index)
            button.bounds = CGRect(x: 0, y: 0, width: 32 * sourceScale, height: diameter)
            button.center = CGPoint(x: center.x + CGFloat(offset.x) * sourceScale, y: center.y + CGFloat(offset.y) * sourceScale)
            let selected = !motion.isEntering && index == selectedIndex
            button.setBackgroundImage(OriginalArt.image(selected ? "big_circle_01" : "big_circle_00"), for: .normal)
            button.accessibilityTraits = index == selectedIndex ? [.button, .selected] : .button
            if let icon = button as? OriginalMenuIconButton {
                icon.updateSparks(sparks.map { (sparkSprite?.frames[$0.frame].image, CGPoint(x: $0.x, y: $0.y)) },
                                  visible: selected && motion.dynamicShift == 0, scale: sourceScale)
            }
        }
        headingPanel.isHidden = motion.isEntering
        selectButton.isEnabled = isPlayMenu || selectedIndex != 1 || canContinueGame
        heading.accessibilityValue = GameLocalization.text(motion.isEntering ? "entering" : (motion.dynamicShift == 0 ? "idle" : "rotating"))
    }

    private func rebuildIcons() {
        motion = CircleMenuAnimation(count: labels.count, radius: 74)
        iconButtons.forEach { $0.removeFromSuperview() }
        iconButtons = labels.enumerated().map { index, title in
            let frame = iconFrames[index]
            let button = OriginalMenuIconButton(type: .custom)
            button.accessibilityLabel = title
            button.accessibilityIdentifier = isPlayMenu ? ["menu.campaign.mission-zero", "menu.campaign.continue", "menu.levels", "menu.skirmish"][index] : ["menu.play", "menu.continue", "menu.settings", "menu.instructions", "menu.about", "menu.exit"][index]
            button.isEnabled = isPlayMenu || index != 1 || canContinueGame
            button.alpha = button.isEnabled ? 1 : 0.45
            button.setImage(menuIcons?.frames[frame].image, for: .normal)
            button.imageView?.layer.magnificationFilter = .nearest
            button.imageView?.layer.minificationFilter = .nearest
            button.imageView?.contentMode = .scaleAspectFit

            button.addAction(UIAction { [weak self] _ in
                guard let self else { return }
                if self.selectedIndex == index { self.activateSelection() }
                else { self.select(index) }
            }, for: .touchUpInside)
            view.addSubview(button)
            return button
        }
        iconButtons.reversed().forEach { view.bringSubviewToFront($0) }
        heading.text = labels[selectedIndex]
        heading.accessibilityIdentifier = "menu.heading"
        backButton.isHidden = !isPlayMenu
        view.setNeedsLayout()
    }

    private func showMainMenu() {
        isPlayMenu = false
        selectedIndex = 0
        rebuildIcons()
    }

    private func select(_ index: Int) {
        let target = (index + iconButtons.count) % iconButtons.count
        var delta = target - selectedIndex
        if delta > iconButtons.count / 2 { delta -= iconButtons.count }
        if delta < -iconButtons.count / 2 { delta += iconButtons.count }
        motion.select(step: delta)
        selectedIndex = motion.selectedIndex
        heading.text = labels[selectedIndex]
        layoutIcons()
    }

    @objc private func nextItem() { select(selectedIndex + 1) }
    @objc private func previousItem() { select(selectedIndex - 1) }

    private func activateSelection() {
        motion.finishEntrance()
        if isPlayMenu {
            switch selectedIndex {
            case 0: onStartCampaign?(0)
            case 1:
                let panel = OriginalSaveSlotsViewController()
                panel.onLoad = { [weak self] in self?.onLoadCampaign?($0) }
                show(panel)
            case 2:
                let panel = OriginalListViewController(title: GameLocalization.text("story.3"))
                panel.entries = unlockedMissionIndices.map { mission in
                    let title = (try? GameResources.campaignStoryText(id: 121 + mission)) ?? GameLocalization.format("MISSION %@", String(describing: mission + 1))
                    return .init(title: "\(mission + 1)  \(title)", identifier: "menu.level.\(mission)", action: { [weak self, weak panel] in
                        panel?.dismiss(animated: false) { self?.onStartCampaign?(mission) }
                    })
                }
                show(panel)
            default:
                let panel = SkirmishSetupViewController()
                panel.onStart = onStartSkirmish
                show(panel)
            }
            return
        }
        switch selectedIndex {
        case 0:
            isPlayMenu = true
            selectedIndex = 0
            rebuildIcons()
        case 1:
            guard canContinueGame else { return }
            onContinueGame?()
        case 2: show(OriginalSettingsViewController())
        case 3: show(OriginalInstructionsViewController())
        case 4: showText(GameLocalization.text("story.10"), text: ((try? GameResources.campaignStoryText(id: 16)) ?? "").replacingOccurrences(of: "%U", with: "2.5"))
        default: onReplayTitle?()
        }
    }

    private func showText(_ title: String, text: String) {
        let panel = OriginalListViewController(title: title)
        panel.body = text
        show(panel)
    }

    private func show(_ panel: UIViewController) {
        panel.modalPresentationStyle = .overFullScreen
        present(panel, animated: false)
    }
}

@MainActor
private final class OriginalMenuIconButton: UIButton {
    private var sparkViews: [UIImageView] = []

    func updateSparks(_ frames: [(UIImage?, CGPoint)], visible: Bool, scale: CGFloat) {
        if sparkViews.isEmpty {
            sparkViews = (0..<3).map { _ in
                let spark = OriginalArt.pixelView()
                if let imageView { insertSubview(spark, belowSubview: imageView) }
                else { addSubview(spark) }
                return spark
            }
        }
        for (index, spark) in sparkViews.enumerated() {
            spark.isHidden = !visible
            spark.image = frames[index].0
            spark.frame = CGRect(x: frames[index].1.x * scale, y: frames[index].1.y * scale, width: 7 * scale, height: 7 * scale)
        }
    }

    override func imageRect(forContentRect contentRect: CGRect) -> CGRect {
        let size = contentRect.width * 25 / 32
        return CGRect(x: contentRect.midX - size / 2, y: contentRect.midY - size / 2, width: size, height: size)
    }
}

@MainActor
private final class MenuClockTarget {
    weak var owner: MainMenuViewController?
    init(_ owner: MainMenuViewController) { self.owner = owner }
    @objc func tick(_ link: CADisplayLink) { owner?.tick(link) }
}
