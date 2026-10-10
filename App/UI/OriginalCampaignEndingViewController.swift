import AECore
import AEContent
import UIKit

/// MainDisplayable m7 states 34-36: intro5 and prophecy 220, followed by the
/// full-screen credits text 288. Both screens advance one logical pixel per tick.
@MainActor
final class OriginalCampaignEndingViewController: UIViewController {
    var onComplete: (() -> Void)?

    private enum Phase { case prophecy, credits }

    private let illustration = OriginalArt.pixelView(OriginalArt.image("intro5"))
    private let textViewport = UIView()
    private let textLabel = OriginalArt.label(size: 18)
    private lazy var nextButton = OriginalGameButton(GameLocalization.text("story.14"), identifier: "ending.continue") { [weak self] in
        self?.advance()
    }
    private var phase: Phase = .prophecy
    private var displayLink: CADisplayLink?
    private var lastTimestamp: CFTimeInterval?
    private var elapsed: TimeInterval = 0
    private var textOffset: CGFloat = 0
    private var completed = false

    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .black
        illustration.alpha = 0
        illustration.layer.magnificationFilter = .linear
        illustration.layer.minificationFilter = .linear
        illustration.accessibilityIdentifier = "ending.illustration"
        view.addSubview(illustration)

        textViewport.clipsToBounds = true
        view.addSubview(textViewport)
        textLabel.numberOfLines = 0
        textLabel.textAlignment = .center
        textLabel.accessibilityIdentifier = "ending.text"
        textViewport.addSubview(textLabel)
        view.addSubview(nextButton)
        applyPhase()
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let safe = view.safeAreaInsets
        switch phase {
        case .prophecy:
            let imageHeight = view.bounds.width * 139 / 240
            illustration.frame = CGRect(x: 0, y: safe.top + 12, width: view.bounds.width, height: imageHeight)
            textViewport.frame = CGRect(x: 18, y: illustration.frame.maxY + 16,
                                        width: view.bounds.width - 36,
                                        height: max(80, view.bounds.height - safe.bottom - illustration.frame.maxY - 82))
        case .credits:
            textViewport.frame = CGRect(x: 18, y: safe.top + 12, width: view.bounds.width - 36,
                                        height: view.bounds.height - safe.top - safe.bottom - 24)
        }
        let size = textLabel.sizeThatFits(CGSize(width: textViewport.bounds.width, height: .greatestFiniteMagnitude))
        textLabel.frame = CGRect(x: 0, y: textViewport.bounds.height - textOffset,
                                 width: textViewport.bounds.width, height: size.height)
        nextButton.frame = CGRect(x: view.bounds.width - 100,
                                  y: view.bounds.height - safe.bottom - 54, width: 92, height: 48)
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        GameAudioPlayer.shared.play("game_complete")
        let link = CADisplayLink(target: self, selector: #selector(tick(_:)))
        link.add(to: .main, forMode: .common)
        displayLink = link
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        displayLink?.invalidate()
        displayLink = nil
        lastTimestamp = nil
    }

    @objc private func tick(_ link: CADisplayLink) {
        let delta = min(0.1, lastTimestamp.map { link.timestamp - $0 } ?? 0)
        lastTimestamp = link.timestamp
        elapsed += delta
        if phase == .prophecy {
            illustration.alpha = min(1, elapsed / (16 * 0.066))
            guard elapsed >= 16 * 0.066 else { return }
        }
        textOffset += CGFloat(delta / 0.066) * view.bounds.width / 240
        view.setNeedsLayout()
        if textOffset > textViewport.bounds.height + textLabel.bounds.height { advance() }
    }

    private func applyPhase() {
        illustration.isHidden = phase == .credits
        textLabel.text = try? GameResources.campaignStoryText(id: phase == .prophecy ? 220 : 288)
        textLabel.accessibilityLabel = phase == .prophecy ? GameLocalization.text("Final prophecy") : GameLocalization.text("Ancient Empires II credits")
        nextButton.setTitle(phase == .prophecy ? GameLocalization.text("story.14") : GameLocalization.text("DONE"), for: .normal)
        nextButton.accessibilityLabel = phase == .prophecy ? GameLocalization.text("Continue to Credits") : GameLocalization.text("Return to Main Menu")
        textOffset = 0
        elapsed = 0
        illustration.alpha = phase == .prophecy ? 0 : 1
        view.setNeedsLayout()
    }

    private func advance() {
        switch phase {
        case .prophecy:
            phase = .credits
            applyPhase()
        case .credits:
            guard !completed else { return }
            completed = true
            displayLink?.invalidate()
            displayLink = nil
            onComplete?()
        }
    }
}
