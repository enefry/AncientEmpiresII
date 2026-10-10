import AEContent
import AECore
import UIKit
import UniformTypeIdentifiers

@MainActor
final class SkirmishSetupViewController: UIViewController, UIDocumentPickerDelegate {
    var onStart: ((CampaignMap, SkirmishConfiguration) throws -> Void)?
    private let store: LocalSkirmishMapStore
    private var maps: [CampaignMap] = []
    private var selectedMap: CampaignMap?
    private var configuration: SkirmishConfiguration?
    private var previewImages: [String: UIImage] = [:]
    private weak var scrollView: UIScrollView?
    private let stack = UIStackView()
    private let status = OriginalArt.label(size: 16)

    init(store: LocalSkirmishMapStore = .shared) {
        self.store = store
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = PixelPalette.ink
        let scroll = UIScrollView()
        scrollView = scroll
        scroll.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(scroll)
        stack.axis = .vertical
        stack.spacing = 12
        stack.translatesAutoresizingMaskIntoConstraints = false
        scroll.addSubview(stack)
        let back = OriginalGameButton(GameLocalization.text("story.31"), identifier: "skirmish.back") { [weak self] in self?.dismiss(animated: false) }
        back.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(back)
        NSLayoutConstraint.activate([
            scroll.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 16),
            scroll.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -16),
            scroll.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 16),
            scroll.bottomAnchor.constraint(equalTo: back.topAnchor, constant: -12),
            stack.leadingAnchor.constraint(equalTo: scroll.contentLayoutGuide.leadingAnchor),
            stack.trailingAnchor.constraint(equalTo: scroll.contentLayoutGuide.trailingAnchor),
            stack.topAnchor.constraint(equalTo: scroll.contentLayoutGuide.topAnchor),
            stack.bottomAnchor.constraint(equalTo: scroll.contentLayoutGuide.bottomAnchor),
            stack.widthAnchor.constraint(equalTo: scroll.frameLayoutGuide.widthAnchor),
            back.leadingAnchor.constraint(equalTo: scroll.leadingAnchor),
            back.trailingAnchor.constraint(equalTo: scroll.trailingAnchor),
            back.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -12),
            back.heightAnchor.constraint(equalToConstant: 48),
        ])
        status.numberOfLines = 0
        status.font = .systemFont(ofSize: 16)
        status.accessibilityIdentifier = "skirmish.status"
        reloadMaps()
    }

    private func reloadMaps() {
        previewImages.removeAll()
        do {
            maps = try GameResources.skirmishMapIDs().map { try GameResources.campaignMap(named: $0) }
            maps += try store.maps()
        } catch { status.text = GameLocalization.format("Unable to read maps: %@", String(describing: error.localizedDescription)) }
        render()
    }

    private func button(_ title: String, id: String, action: @escaping () -> Void) {
        let button = OriginalGameButton(title, identifier: id, action: action)
        button.heightAnchor.constraint(greaterThanOrEqualToConstant: 52).isActive = true
        stack.addArrangedSubview(button)
    }

    private func label(_ text: String) {
        let label = OriginalArt.label(text, size: 20)
        label.numberOfLines = 0
        stack.addArrangedSubview(label)
    }

    private func mapButton(_ map: CampaignMap, title: String, id: String, featured: Bool = false, action: @escaping () -> Void) {
        let image: UIImage?
        do {
            if let cached = previewImages[map.id] {
                image = cached
            } else {
                let rendered = try OriginalMapPreview.image(map: map, units: map.units.map {
                    CampaignUnitState(id: $0.index, type: $0.type, fraction: $0.fraction, x: $0.x, y: $0.y)
                })
                previewImages[map.id] = rendered
                image = rendered
            }
        } catch {
            image = nil
            status.text = GameLocalization.text("Unable to preview the map.")
        }
        let button = SkirmishMapPreviewButton(title: title, image: image, map: map, identifier: id, featured: featured, action: action)
        button.heightAnchor.constraint(equalToConstant: featured ? 260 : 112).isActive = true
        stack.addArrangedSubview(button)
    }

    private func render() {
        stack.arrangedSubviews.forEach { stack.removeArrangedSubview($0); $0.removeFromSuperview() }
        label(GameLocalization.text("SKIRMISH"))
        stack.addArrangedSubview(status)
        guard let map = selectedMap, let configuration else {
            button(GameLocalization.text("Import Local Map"), id: "skirmish.import") { [weak self] in self?.importMap() }
            label(GameLocalization.text("Import original .aem or JSON maps from Files. Imported maps are stored on this device."))
            var previousGroup = ""
            for map in maps {
                let group = map.id.hasPrefix("local-") ? "Local Maps" : map.id.hasPrefix("b") ? "Bonus Maps" : "Original Maps"
                if group != previousGroup {
                    label(GameLocalization.text(group))
                    previousGroup = group
                }
                mapButton(map, title: "\(map.id.hasPrefix("local-") ? GameLocalization.text("Local · ") : "")\(GameResources.mapName(map))", id: "skirmish.map.\(map.id)") { [weak self] in
                    self?.selectedMap = map
                    self?.configuration = self?.defaultConfiguration(for: map)
                    self?.status.text = nil
                    self?.render()
                    self?.scrollView?.setContentOffset(.zero, animated: false)
                }
            }
            return
        }
        label("\(GameResources.mapName(map)) · \(map.width)×\(map.height)")
        mapButton(map, title: GameLocalization.text("Map Preview"), id: "skirmish.preview", featured: true) { [weak self] in
            do {
                let overview = try OriginalMapOverviewViewController(map: map, units: map.units.map {
                    CampaignUnitState(id: $0.index, type: $0.type, fraction: $0.fraction, x: $0.x, y: $0.y)
                })
                overview.modalPresentationStyle = .overFullScreen
                self?.present(overview, animated: false)
            } catch { self?.status.text = GameLocalization.text("Unable to preview the map.") }
        }
        for (index, player) in configuration.players.enumerated() {
            let controlName = [SkirmishControl.human: GameLocalization.text("Human"), .cpu: GameLocalization.text("story.36"), .off: GameLocalization.text("Off")][player.control] ?? ""
            button(GameLocalization.format("%@: %@", String(describing: Self.factionName(player.fraction)), String(describing: controlName)), id: "skirmish.player.\(player.fraction).control") { [weak self] in
                guard let self else { return }
                let choices = SkirmishControl.allCases
                self.configuration?.players[index].control = choices[(choices.firstIndex(of: player.control)! + 1) % choices.count]
                self.render()
            }
            button(GameLocalization.format("%@: Team %@", String(describing: Self.factionName(player.fraction)), String(describing: player.team)), id: "skirmish.player.\(player.fraction).team") { [weak self] in
                self?.configuration?.players[index].team = player.team % configuration.players.count + 1
                self?.render()
            }
            if player.control == .cpu {
                button(GameLocalization.format("CPU Difficulty: %@", player.resolvedDifficulty.title), id: "skirmish.player.\(player.fraction).difficulty") { [weak self] in
                    self?.configuration?.players[index].difficulty = player.resolvedDifficulty.next
                    self?.render()
                }
                label(player.resolvedDifficulty.summary)
            }
        }
        button(GameLocalization.format("Starting Gold: %@", String(describing: configuration.startingMoney)), id: "skirmish.money") { [weak self] in
            let choices = SkirmishConfiguration.moneyChoices
            self?.configuration?.startingMoney = choices[(choices.firstIndex(of: configuration.startingMoney)! + 1) % choices.count]
            self?.render()
        }
        button(GameLocalization.format("Unit Cap: %@", String(describing: configuration.unitCap)), id: "skirmish.cap") { [weak self] in
            let choices = SkirmishConfiguration.capChoices
            self?.configuration?.unitCap = choices[(choices.firstIndex(of: configuration.unitCap)! + 1) % choices.count]
            self?.render()
        }
        button(GameLocalization.text("Start Battle"), id: "skirmish.start") { [weak self] in self?.startBattle() }
        button(GameLocalization.text("Choose Another Map"), id: "skirmish.choose-map") { [weak self] in
            self?.selectedMap = nil; self?.configuration = nil; self?.status.text = nil; self?.render()
            self?.scrollView?.setContentOffset(.zero, animated: false)
        }
        if map.id.hasPrefix("local-") {
            button(GameLocalization.text("Delete Local Map"), id: "skirmish.delete") { [weak self] in self?.confirmDelete(map) }
        }
    }

    private func startBattle() {
        guard let selectedMap, let configuration else { return }
        do {
            try configuration.validate(for: selectedMap)
            try onStart?(selectedMap, configuration)
        } catch { status.text = error.localizedDescription; scrollView?.setContentOffset(.zero, animated: true) }
    }

    private func defaultConfiguration(for map: CampaignMap) -> SkirmishConfiguration {
        var configuration = SkirmishConfiguration(map: map)
        for index in configuration.players.indices {
            configuration.players[index].difficulty = GamePreferences.aiDifficulty
        }
        return configuration
    }

    private func importMap() {
        let picker = UIDocumentPickerViewController(forOpeningContentTypes: [.json, .data], asCopy: true)
        picker.delegate = self
        picker.allowsMultipleSelection = false
        present(picker, animated: true)
    }

    func documentPicker(_ controller: UIDocumentPickerViewController, didPickDocumentsAt urls: [URL]) {
        guard let url = urls.first else { return }
        do {
            let map = try store.importMap(from: url)
            selectedMap = map
            configuration = defaultConfiguration(for: map)
            status.text = GameLocalization.format("Imported %@", String(describing: GameResources.mapName(map)))
            reloadMaps()
            scrollView?.setContentOffset(.zero, animated: false)
        } catch { status.text = GameLocalization.format("Import failed: %@", String(describing: error.localizedDescription)) }
    }

    private func confirmDelete(_ map: CampaignMap) {
        let panel = OriginalListViewController(title: GameLocalization.text("Delete Map"))
        panel.body = GameLocalization.format("Delete %@? Saved battles can still be continued.", String(describing: GameResources.mapName(map)))
        panel.entries = [.init(title: GameLocalization.text("Delete"), identifier: "skirmish.delete.confirm", action: { [weak self, weak panel] in
            panel?.dismiss(animated: false) {
                guard let self else { return }
                do {
                    try self.store.delete(map)
                    self.selectedMap = nil; self.configuration = nil; self.status.text = nil
                    self.reloadMaps()
                    self.scrollView?.setContentOffset(.zero, animated: false)
                } catch { self.status.text = GameLocalization.format("Delete failed: %@", String(describing: error.localizedDescription)) }
            }
        })]
        panel.modalPresentationStyle = .overFullScreen
        present(panel, animated: false)
    }

    static func factionName(_ fraction: Int) -> String {
        [1: GameLocalization.text("Blue"), 2: GameLocalization.text("Red"), 3: GameLocalization.text("Green"), 4: GameLocalization.text("Black")][fraction] ?? GameLocalization.text("Neutral")
    }
}

@MainActor
private final class SkirmishMapPreviewButton: UIButton {
    private let panel = OriginalFrameView()
    private let picture: UIImageView
    private let caption = OriginalArt.label(size: 18)
    private let dimensions = OriginalArt.label(size: 14)
    private let featured: Bool

    override var isHighlighted: Bool { didSet { panel.highlighted = isHighlighted } }

    init(title: String, image: UIImage?, map: CampaignMap, identifier: String, featured: Bool, action: @escaping () -> Void) {
        self.featured = featured
        picture = OriginalArt.pixelView(image)
        super.init(frame: .zero)
        caption.text = title
        caption.numberOfLines = 2
        caption.textAlignment = featured ? .center : .left
        dimensions.text = "\(map.width)×\(map.height)"
        dimensions.textColor = .lightGray
        dimensions.isHidden = featured
        picture.accessibilityIdentifier = identifier + ".image"
        [panel, picture, caption, dimensions].forEach {
            $0.isUserInteractionEnabled = false
            $0.isAccessibilityElement = false
            addSubview($0)
        }
        isAccessibilityElement = true
        accessibilityIdentifier = identifier
        accessibilityLabel = title
        accessibilityValue = GameLocalization.format("Battlefield overview, %@ by %@ cells", String(map.width), String(map.height))
        addAction(UIAction { _ in action() }, for: .touchUpInside)
    }

    required init?(coder: NSCoder) { nil }

    override func layoutSubviews() {
        super.layoutSubviews()
        panel.frame = bounds
        if featured {
            picture.frame = CGRect(x: 18, y: 18, width: bounds.width - 36, height: bounds.height - 64)
            caption.frame = CGRect(x: 18, y: bounds.height - 40, width: bounds.width - 36, height: 24)
        } else {
            picture.frame = CGRect(x: 18, y: 18, width: 76, height: 76)
            caption.frame = CGRect(x: 110, y: 24, width: bounds.width - 128, height: 40)
            dimensions.frame = CGRect(x: 110, y: 68, width: bounds.width - 128, height: 20)
        }
    }
}
