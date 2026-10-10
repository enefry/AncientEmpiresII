import AECore
import AEContent
import UIKit

/// MainDisplayable.setIntroMode/updateIntro: original illustration and one logical pixel per 66ms scroll.
@MainActor
final class OriginalIntroductionViewController: UIViewController {
    var onComplete: (() -> Void)?
    private let illustration: UIImageView
    private let textLabel = OriginalArt.label(size: 18)
    private let textViewport = UIView()
    private lazy var nextButton = OriginalGameButton(GameLocalization.text("story.14"), identifier: "intro.continue") { [weak self] in self?.showChapter() }
    private let chapter = OriginalFrameView()
    private let chapterLabel = OriginalArt.label(GameLocalization.text("story.121"), size: 23)
    private var displayLink: CADisplayLink?
    private var lastTimestamp: CFTimeInterval?
    private var elapsed: TimeInterval = 0
    private var textOffset: CGFloat = 0
    private var showingChapter = false
    private var hasCompleted = false
    private let hasNarrative: Bool

    init(illustrationID: Int? = 2, chapterTitle: String = GameLocalization.text("story.121")) {
        hasNarrative = illustrationID != nil
        illustration = OriginalArt.pixelView(illustrationID.flatMap { OriginalArt.image("intro\($0)") })
        super.init(nibName: nil, bundle: nil)
        textLabel.text = illustrationID.flatMap { try? GameResources.campaignStoryText(id: 215 + $0) } ?? ""
        chapterLabel.text = chapterTitle
        showingChapter = illustrationID == nil
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .black
        illustration.alpha = 0
        illustration.accessibilityIdentifier = "intro.illustration"
        illustration.layer.magnificationFilter = .linear
        illustration.layer.minificationFilter = .linear
        illustration.isHidden = showingChapter
        view.addSubview(illustration)
        textViewport.clipsToBounds = true
        textViewport.isHidden = showingChapter
        view.addSubview(textViewport)
        textLabel.numberOfLines = 0
        textLabel.textAlignment = .center
        textLabel.accessibilityIdentifier = "intro.story"
        textViewport.addSubview(textLabel)
        nextButton.isHidden = showingChapter
        view.addSubview(nextButton)
        chapter.isHidden = !showingChapter
        view.addSubview(chapter)
        chapterLabel.textAlignment = .center
        chapterLabel.accessibilityIdentifier = "intro.chapter"
        chapter.addSubview(chapterLabel)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let safe = view.safeAreaInsets
        let imageHeight = view.bounds.width * 139 / 240
        illustration.frame = CGRect(x: 0, y: safe.top + 12, width: view.bounds.width, height: imageHeight)
        textViewport.frame = CGRect(x: 18, y: illustration.frame.maxY + 16, width: view.bounds.width - 36, height: max(80, view.bounds.height - safe.bottom - illustration.frame.maxY - 82))
        let size = textLabel.sizeThatFits(CGSize(width: textViewport.bounds.width, height: .greatestFiniteMagnitude))
        textLabel.frame = CGRect(x: 0, y: textViewport.bounds.height - textOffset, width: textViewport.bounds.width, height: size.height)
        nextButton.frame = CGRect(x: view.bounds.width - 100, y: view.bounds.height - safe.bottom - 54, width: 92, height: 48)
        chapter.frame = CGRect(x: 0, y: view.bounds.midY - 44, width: view.bounds.width, height: 88)
        chapterLabel.frame = chapter.bounds.insetBy(dx: 16, dy: 12)
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if hasNarrative { GameAudioPlayer.shared.play("bg_story") }
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
        if showingChapter {
            if elapsed >= 2 { finish() }
            return
        }
        illustration.alpha = min(1, elapsed / (16 * 0.066))
        if elapsed >= 16 * 0.066 {
            textOffset += CGFloat(delta / 0.066) * view.bounds.width / 240
            view.setNeedsLayout()
            if textOffset > textViewport.bounds.height + textLabel.bounds.height { showChapter() }
        }
    }

    private func showChapter() {
        guard !showingChapter else { finish(); return }
        showingChapter = true
        elapsed = 0
        illustration.isHidden = true
        textViewport.isHidden = true
        nextButton.isHidden = true
        chapter.isHidden = false
    }

    private func finish() {
        guard !hasCompleted else { return }
        hasCompleted = true
        displayLink?.invalidate()
        displayLink = nil
        onComplete?()
    }
}
