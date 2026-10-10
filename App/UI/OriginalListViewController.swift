import AECore
import UIKit

@MainActor
final class OriginalListViewController: UIViewController {
    struct Entry {
        let title: String
        var identifier: String = ""
        let action: () -> Void
    }
    var entries: [Entry] = []
    var body: String?
    var contentHeader: UIView?
    var closeTitle = GameLocalization.text("story.31")
    var closeIdentifier = "panel.back"
    var onClose: (() -> Void)?
    private let heading: String
    private weak var contentStack: UIStackView?
    private weak var headingLabel: UILabel?
    private var panelHeight: NSLayoutConstraint?

    init(title: String) {
        heading = title
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.accessibilityViewIsModal = true
        view.backgroundColor = UIColor.black.withAlphaComponent(0.55)
        let panel = OriginalFrameView()
        panel.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(panel)
        let title = OriginalArt.label(heading, size: 21)
        headingLabel = title
        title.textAlignment = .center
        title.numberOfLines = 0
        title.translatesAutoresizingMaskIntoConstraints = false
        panel.addSubview(title)
        let scroll = UIScrollView()
        scroll.indicatorStyle = .white
        scroll.translatesAutoresizingMaskIntoConstraints = false
        panel.addSubview(scroll)
        let stack = UIStackView()
        contentStack = stack
        stack.axis = .vertical
        stack.spacing = 8
        stack.translatesAutoresizingMaskIntoConstraints = false
        scroll.addSubview(stack)
        if let contentHeader {
            contentHeader.heightAnchor.constraint(equalToConstant: 228).isActive = true
            stack.addArrangedSubview(contentHeader)
        }
        if let body {
            let label = OriginalArt.label(body, size: 16)
            label.numberOfLines = 0
            label.accessibilityIdentifier = "panel.body"
            label.font = .systemFont(ofSize: 17, weight: .medium)
            stack.addArrangedSubview(label)
        }
        for entry in entries {
            let button = OriginalGameButton(entry.title, identifier: entry.identifier, action: entry.action)
            button.heightAnchor.constraint(greaterThanOrEqualToConstant: 52).isActive = true
            stack.addArrangedSubview(button)
        }
        let back = OriginalGameButton(closeTitle, identifier: closeIdentifier) { [weak self] in
            guard let self else { return }
            self.dismiss(animated: false, completion: self.onClose)
        }
        back.translatesAutoresizingMaskIntoConstraints = false
        panel.addSubview(back)
        let height = panel.heightAnchor.constraint(equalToConstant: 320)
        panelHeight = height
        NSLayoutConstraint.activate([
            panel.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 12),
            panel.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -12),
            panel.centerYAnchor.constraint(equalTo: view.safeAreaLayoutGuide.centerYAnchor),
            height,
            title.leadingAnchor.constraint(equalTo: panel.leadingAnchor, constant: 18),
            title.trailingAnchor.constraint(equalTo: panel.trailingAnchor, constant: -18),
            title.topAnchor.constraint(equalTo: panel.topAnchor, constant: 18),
            scroll.topAnchor.constraint(equalTo: title.bottomAnchor, constant: 20),
            scroll.leadingAnchor.constraint(equalTo: panel.leadingAnchor, constant: 18),
            scroll.trailingAnchor.constraint(equalTo: panel.trailingAnchor, constant: -18),
            scroll.bottomAnchor.constraint(equalTo: back.topAnchor, constant: -12),
            stack.leadingAnchor.constraint(equalTo: scroll.contentLayoutGuide.leadingAnchor),
            stack.trailingAnchor.constraint(equalTo: scroll.contentLayoutGuide.trailingAnchor),
            stack.topAnchor.constraint(equalTo: scroll.contentLayoutGuide.topAnchor),
            stack.bottomAnchor.constraint(equalTo: scroll.contentLayoutGuide.bottomAnchor),
            stack.widthAnchor.constraint(equalTo: scroll.frameLayoutGuide.widthAnchor),
            back.heightAnchor.constraint(equalToConstant: 48),
            back.leadingAnchor.constraint(equalTo: panel.leadingAnchor, constant: 16),
            back.trailingAnchor.constraint(equalTo: panel.trailingAnchor, constant: -16),
            back.bottomAnchor.constraint(equalTo: panel.bottomAnchor, constant: -12),
        ])
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        guard let contentStack, let headingLabel, let panelHeight else { return }
        let safe = view.safeAreaLayoutGuide.layoutFrame
        let width = max(1, safe.width - 60)
        let content = contentStack.systemLayoutSizeFitting(CGSize(width: width, height: 0),
            withHorizontalFittingPriority: .required, verticalFittingPriority: .fittingSizeLevel).height
        let title = headingLabel.sizeThatFits(CGSize(width: width, height: .greatestFiniteMagnitude)).height
        let height = min(safe.height * 0.84, max(200, title + content + 110))
        if abs(panelHeight.constant - height) > 0.5 { panelHeight.constant = height }
    }
}
