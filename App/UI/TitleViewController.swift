import AECore
import UIKit

@MainActor
final class TitleViewController: UIViewController {
    var onContinue: (() -> Void)?

    private let continueButton = UIButton(type: .system)
    private var titleCanvas: TitleCanvasView?

    override var prefersStatusBarHidden: Bool { true }

    override func pressesBegan(_ presses: Set<UIPress>, with event: UIPressesEvent?) {
        if titleCanvas?.state.canContinue == true {
            finishTitle()
        } else {
            super.pressesBegan(presses, with: event)
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .black

        configureContinueButton()
        do {
            let canvas = try TitleCanvasView(renderer: TitleFrameRenderer())
            canvas.translatesAutoresizingMaskIntoConstraints = false
            canvas.onReady = { [weak self] in self?.continueButton.isHidden = false }
            canvas.onContinue = { [weak self] in self?.finishTitle() }
            view.insertSubview(canvas, at: 0)
            NSLayoutConstraint.activate([
                canvas.leadingAnchor.constraint(equalTo: view.leadingAnchor),
                canvas.trailingAnchor.constraint(equalTo: view.trailingAnchor),
                canvas.topAnchor.constraint(equalTo: view.topAnchor),
                canvas.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            ])
            titleCanvas = canvas
        } catch {
            showLoadFailure()
        }
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        titleCanvas?.startAnimation()
        GameAudioPlayer.shared.play("main_theme")
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        titleCanvas?.stopAnimation()
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        continueButton.frame = CGRect(
            x: 44,
            y: view.bounds.height - view.safeAreaInsets.bottom - 60,
            width: view.bounds.width - 88,
            height: 48
        )
    }

    private func configureContinueButton() {
        // The canvas owns the blinking prompt. This transparent control only provides a touch/accessibility target.
        continueButton.setTitle(nil, for: .normal)
        continueButton.setTitleColor(.white, for: .normal)
        continueButton.titleLabel?.font = .monospacedSystemFont(ofSize: 8, weight: .bold)
        continueButton.backgroundColor = .clear
        continueButton.accessibilityIdentifier = "title.continue"
        continueButton.accessibilityLabel = GameLocalization.text("Tap the screen to continue")
        continueButton.isHidden = true
        continueButton.addTarget(self, action: #selector(finishTitle), for: .touchUpInside)
        view.addSubview(continueButton)
    }

    @objc private func finishTitle() {
        guard titleCanvas?.state.canContinue == true else { return }
        onContinue?()
    }

    private func showLoadFailure() {
        let message = UILabel()
        message.text = GameLocalization.text("Unable to load the original title resources.")
        message.textColor = .white
        message.textAlignment = .center
        message.numberOfLines = 0
        message.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(message)
        NSLayoutConstraint.activate([
            message.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            message.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            message.leadingAnchor.constraint(greaterThanOrEqualTo: view.leadingAnchor, constant: 24),
            message.trailingAnchor.constraint(lessThanOrEqualTo: view.trailingAnchor, constant: -24),
        ])
    }
}

@MainActor
private final class TitleCanvasView: UIView {
    private static let promptOffsets = [
        (-1, -1), (-1, 0), (-1, 1), (0, -1),
        (0, 1), (1, -1), (1, 0), (1, 1),
    ]

    private let renderer: TitleFrameRenderer
    private let imageView = UIImageView()
    private let coverView = OriginalArt.pixelView()
    private let prompt = UILabel()
    private lazy var promptOutline = Self.promptOffsets.map { _ in UILabel() }
    private var stateValue = StartupAnimationState()
    private var displayLink: CADisplayLink?
    private var nextTickTime: TimeInterval?
    private var readyWasSent = false

    var state: StartupAnimationState { stateValue }
    var onReady: (() -> Void)?
    var onContinue: (() -> Void)?

    init(renderer: TitleFrameRenderer) throws {
        self.renderer = renderer
        super.init(frame: .zero)
        backgroundColor = .black

        imageView.contentMode = .scaleToFill
        imageView.layer.magnificationFilter = .linear
        imageView.layer.minificationFilter = .linear
        imageView.isAccessibilityElement = true
        imageView.accessibilityIdentifier = "title.animated-logo"
        imageView.accessibilityLabel = "Ancient Empires II"
        coverView.contentMode = .scaleAspectFill
        coverView.clipsToBounds = true
        coverView.layer.magnificationFilter = .linear
        coverView.layer.minificationFilter = .linear
        addSubview(coverView)
        addSubview(imageView)

        for (label, offset) in zip(promptOutline, Self.promptOffsets) {
            configurePromptLabel(label, color: .black)
            label.accessibilityElementsHidden = true
            label.isHidden = true
            addSubview(label)
            _ = offset
        }
        configurePromptLabel(prompt, color: .white)
        prompt.isHidden = true
        prompt.accessibilityElementsHidden = true
        addSubview(prompt)

        let tap = UITapGestureRecognizer(target: self, action: #selector(handleTap))
        addGestureRecognizer(tap)
        try updateFrame()
    }

    required init?(coder: NSCoder) {
        nil
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        let scale = bounds.width / 240
        imageView.frame = CGRect(x: 0, y: safeAreaInsets.top, width: bounds.width, height: 85 * scale)
        coverView.frame = CGRect(x: 0, y: imageView.frame.maxY, width: bounds.width, height: bounds.height - imageView.frame.maxY)
        let left: CGFloat = 0
        let top = bounds.height - safeAreaInsets.bottom - 42 - 299 * scale

        for (label, offset) in zip(promptOutline, Self.promptOffsets) {
            label.font = .monospacedSystemFont(ofSize: max(15, 8 * scale), weight: .bold)
            label.frame = promptFrame(scale: scale, left: left, top: top, offset: offset)
        }
        prompt.font = .monospacedSystemFont(ofSize: max(15, 8 * scale), weight: .bold)
        prompt.frame = promptFrame(scale: scale, left: left, top: top, offset: (0, 0))
    }

    func startAnimation() {
        guard displayLink == nil else { return }
        let displayLink = CADisplayLink(target: self, selector: #selector(advanceFrame(_:)))
        displayLink.add(to: .main, forMode: .common)
        self.displayLink = displayLink
    }

    func stopAnimation() {
        displayLink?.invalidate()
        displayLink = nil
        nextTickTime = nil
    }

    @objc private func advanceFrame(_ displayLink: CADisplayLink) {
        guard let nextTickTime else {
            self.nextTickTime = displayLink.timestamp + 1.0 / 15.0
            return
        }
        guard displayLink.timestamp >= nextTickTime else { return }

        stateValue.advanceOneTick()
        self.nextTickTime = displayLink.timestamp + 1.0 / 15.0
        try? updateFrame()
        if stateValue.canContinue && !readyWasSent {
            readyWasSent = true
            onReady?()
        }
    }

    @objc private func handleTap() {
        guard stateValue.canContinue else { return }
        onContinue?()
    }

    private func configurePromptLabel(_ label: UILabel, color: UIColor) {
        label.text = GameLocalization.text("TAP TO CONTINUE")
        label.font = .monospacedSystemFont(ofSize: 8, weight: .bold)
        label.textColor = color
        label.textAlignment = .center
        label.numberOfLines = 1
        label.adjustsFontSizeToFitWidth = false
    }

    private func promptFrame(
        scale: CGFloat,
        left: CGFloat,
        top: CGFloat,
        offset: (Int, Int)
    ) -> CGRect {
        CGRect(
            x: left + 20 + CGFloat(offset.0) * scale,
            y: top + CGFloat(299 - offset.1) * scale,
            width: bounds.width - 40,
            height: 24
        )
    }

    private func updateFrame() throws {
        let frame = try renderer.image(for: stateValue)
        imageView.image = renderer.crop(frame, to: CGRect(x: 0, y: 0, width: 240, height: 85))
        coverView.image = renderer.crop(frame, to: CGRect(x: 0, y: 85, width: 240, height: 235))
        let visible = stateValue.phase == .steady && stateValue.promptVisible
        prompt.isHidden = !visible
        promptOutline.forEach { $0.isHidden = !visible }
    }
}
