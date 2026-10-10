import AECore
import AEContent
import UIKit

/// completeMission / sub_131d / gameState 11. Original delays use a 50ms
/// counter stepped every 66ms, so 3000ms means 60 simulation ticks.
@MainActor
final class OriginalResultViewController: UIViewController {
    var onComplete: (() -> Void)?
    let canvas: OriginalResultCanvas
    private var clock: CADisplayLink?
    private var previousTime: CFTimeInterval = 0
    private var accumulator: CFTimeInterval = 0
    private var completed = false

    init(victory: Bool, message: String? = nil) {
        canvas = OriginalResultCanvas(victory: victory, message: message)
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override func loadView() {
        view = canvas
        view.accessibilityViewIsModal = true
        let next = UIButton(type: .custom)
        next.accessibilityIdentifier = "campaign.result.back"
        next.accessibilityLabel = GameLocalization.text("Return to Menu")
        next.addAction(UIAction { [weak self] _ in
            guard let self, self.canvas.victory || self.canvas.tick >= 37 else { return }
            self.finish()
        }, for: .touchUpInside)
        next.frame = view.bounds
        next.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        view.addSubview(next)
    }
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        previousTime = 0
        let link = CADisplayLink(target: ResultClockTarget(self), selector: #selector(ResultClockTarget.tick(_:)))
        link.add(to: .main, forMode: .common)
        clock = link
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        clock?.invalidate()
        clock = nil
    }
    fileprivate func advance(_ link: CADisplayLink) {
        defer { previousTime = link.timestamp }
        guard previousTime > 0, UIApplication.shared.applicationState == .active else { return }
        accumulator += min(0.2, link.timestamp - previousTime)
        while accumulator >= 0.066 {
            accumulator -= 0.066
            canvas.tick += 1
        }
        if canvas.tick >= (canvas.victory ? 60 : 97) { finish() }
    }
    private func finish() {
        guard !completed else { return }
        completed = true
        clock?.invalidate()
        clock = nil
        onComplete?()
    }
}

@MainActor
final class OriginalResultCanvas: UIView {
    let victory: Bool
    var tick = 0 { didSet { updateFrame(); setNeedsDisplay() } }
    private let panel = OriginalFrameView()
    private let message = OriginalArt.label(size: 23)
    private let art = OriginalArt.pixelView(OriginalArt.image("gameover"))

    init(victory: Bool, message customMessage: String? = nil) {
        self.victory = victory
        super.init(frame: .zero)
        backgroundColor = .clear
        isOpaque = false
        contentMode = .redraw
        message.textAlignment = .center
        message.text = customMessage ?? (try? GameResources.campaignStoryText(id: victory ? 72 : 57))
        message.accessibilityIdentifier = "campaign.result.text"
        addSubview(panel)
        addSubview(art)
        addSubview(message)
        updateFrame()
    }
    required init?(coder: NSCoder) { nil }

    private func updateFrame() {
        panel.isHidden = !victory
        message.isHidden = !victory && tick < 37
        art.isHidden = victory || tick < 37
        let fade = min(1, max(0, CGFloat(tick - 37) / 16))
        art.alpha = fade
        if !victory { message.alpha = fade }
    }
    override func layoutSubviews() {
        super.layoutSubviews()
        panel.frame = CGRect(x: 0, y: bounds.midY - 36, width: bounds.width, height: 72)
        if victory { message.frame = panel.frame.insetBy(dx: 12, dy: 10) }
        else {
            let scale = min(3, bounds.width / 240)
            let size = art.image?.size ?? .zero
            art.frame = CGRect(x: bounds.midX - size.width * scale / 2, y: bounds.midY - size.height * scale / 2,
                               width: size.width * scale, height: size.height * scale)
            message.frame = CGRect(x: 12, y: bounds.height - safeAreaInsets.bottom - 36, width: bounds.width - 24, height: 30)
        }
    }
    override func draw(_ rect: CGRect) {
        guard !victory, tick >= 20, let context = UIGraphicsGetCurrentContext() else { return }
        if tick >= 37 {
            UIColor.black.setFill()
            context.fill(bounds)
        } else {
            let segment = floor(bounds.width / 8) + 1
            for index in 0..<8 {
                let fraction = CGFloat(min(9, max(0, tick - 20 - index))) / 9
                UIColor.black.withAlphaComponent(fraction).setFill()
                context.fill(CGRect(x: CGFloat(index) * segment, y: 0, width: floor(segment * fraction), height: bounds.height))
            }
        }
    }
}

@MainActor
private final class ResultClockTarget {
    weak var owner: OriginalResultViewController?
    init(_ owner: OriginalResultViewController) { self.owner = owner }
    @objc func tick(_ link: CADisplayLink) { owner?.advance(link) }
}
