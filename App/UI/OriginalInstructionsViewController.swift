import AECore
import AEContent
import UIKit

@MainActor
final class OriginalInstructionsViewController: UIViewController {
    private let titles = [85, 83, 83, 83, 83, 83, 83, 83, 83, 175, 84, 84, 84, 175, 147, 159, 151, 155, 167, 171]
    private let texts = [15] + Array(196...214)
    private var page = 0
    private let heading = OriginalArt.label(GameLocalization.text("story.9"), size: 23)
    private let selector = OriginalArt.label(size: 19)
    private let text = OriginalArt.label(size: 18)
    private let panel = OriginalFrameView()
    private let scroll = UIScrollView()
    private lazy var previous = OriginalGameButton("◀", identifier: "instructions.previous") { [weak self] in self?.changePage(-1) }
    private lazy var nextPage = OriginalGameButton("▶", identifier: "instructions.next") { [weak self] in self?.changePage(1) }
    private lazy var back = OriginalGameButton(GameLocalization.text("story.31"), identifier: "panel.back") { [weak self] in self?.dismiss(animated: false) }

    override var prefersStatusBarHidden: Bool { true }
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .black
        view.addSubview(panel)
        [heading, selector, scroll, previous, nextPage, back].forEach(panel.addSubview)
        heading.textAlignment = .center
        selector.textAlignment = .center
        text.numberOfLines = 0
        text.accessibilityIdentifier = "instructions.text"
        selector.accessibilityIdentifier = "instructions.page"
        scroll.addSubview(text)
        scroll.indicatorStyle = .white
        changePage(0)
    }
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        panel.frame = view.bounds.inset(by: view.safeAreaInsets)
        heading.frame = CGRect(x: 12, y: 16, width: panel.bounds.width - 24, height: 36)
        previous.frame = CGRect(x: 12, y: 64, width: 48, height: 48)
        nextPage.frame = CGRect(x: panel.bounds.width - 60, y: 64, width: 48, height: 48)
        selector.frame = CGRect(x: 64, y: 64, width: panel.bounds.width - 128, height: 48)
        back.frame = CGRect(x: 12, y: panel.bounds.height - 64, width: 88, height: 48)
        scroll.frame = CGRect(x: 20, y: 128, width: panel.bounds.width - 40, height: max(1, back.frame.minY - 144))
        let size = text.sizeThatFits(CGSize(width: scroll.bounds.width, height: .greatestFiniteMagnitude))
        text.frame = CGRect(origin: .zero, size: CGSize(width: scroll.bounds.width, height: size.height))
        scroll.contentSize = text.bounds.size
    }
    private func changePage(_ delta: Int) {
        page = (page + delta + texts.count) % texts.count
        let name = (try? GameResources.campaignStoryText(id: titles[page])) ?? ""
        selector.text = "\(name) \(page)/19"
        text.text = ((try? GameResources.campaignStoryText(id: texts[page])) ?? "")
        scroll.setContentOffset(.zero, animated: false)
        view.setNeedsLayout()
    }
}
