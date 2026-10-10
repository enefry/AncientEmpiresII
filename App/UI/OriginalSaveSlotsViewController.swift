import AECore
import AEContent
import UIKit

/// MainDisplayable.sub_375: horizontal SLOT n/10 selector and campaign/turn card.
@MainActor
final class OriginalSaveSlotsViewController: UIViewController {
    var onLoad: ((CampaignSnapshot) -> Void)?
    private let snapshotToSave: CampaignSnapshot?
    private let store: CampaignSaveStore
    private var slot = 0
    private let panel = OriginalFrameView()
    private let titlePanel = OriginalFrameView()
    private let slotPanel = OriginalFrameView()
    private let heading = OriginalArt.label(size: 23)
    private let slotLabel = OriginalArt.label(size: 21)
    private let information = OriginalArt.label(size: 18)
    private let status = OriginalArt.label(size: 15)
    private lazy var previous = OriginalGameButton("◀", identifier: "saves.previous") { [weak self] in self?.selectSlot(-1) }
    private lazy var nextButton = OriginalGameButton("▶", identifier: "saves.next") { [weak self] in self?.selectSlot(1) }
    private lazy var confirm = OriginalGameButton(snapshotToSave == nil ? GameLocalization.text("LOAD") : GameLocalization.text("SAVE"), identifier: "saves.confirm") { [weak self] in self?.activate() }
    private lazy var back = OriginalGameButton(GameLocalization.text("story.31"), identifier: "panel.back") { [weak self] in self?.dismiss(animated: false) }

    init(snapshotToSave: CampaignSnapshot? = nil, store: CampaignSaveStore = .shared) {
        self.snapshotToSave = snapshotToSave
        self.store = store
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.accessibilityViewIsModal = true
        view.backgroundColor = UIColor.black.withAlphaComponent(0.78)
        [panel, slotPanel, titlePanel, heading, slotLabel, information, status, previous, nextButton, confirm, back].forEach(view.addSubview)
        heading.text = snapshotToSave == nil ? GameLocalization.text("story.5") : GameLocalization.text("story.4")
        [heading, slotLabel, information, status].forEach { $0.textAlignment = .center; $0.numberOfLines = 0 }
        information.accessibilityIdentifier = "saves.information"
        slotLabel.accessibilityIdentifier = "saves.slot"
        status.accessibilityIdentifier = "saves.status"
        refresh()
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let safe = view.safeAreaLayoutGuide.layoutFrame
        let margin: CGFloat = 12
        heading.frame = CGRect(x: margin, y: safe.minY + 18, width: safe.width - margin * 2, height: 52)
        titlePanel.frame = heading.frame.insetBy(dx: 0, dy: -6)
        let width = safe.width - margin * 2
        let cardHeight = min(220, safe.height * 0.32)
        let top = max(heading.frame.maxY + 24, safe.midY - cardHeight / 2 - 52)
        slotPanel.frame = CGRect(x: margin, y: top, width: width, height: 56)
        previous.frame = CGRect(x: margin, y: top, width: 56, height: 56)
        nextButton.frame = CGRect(x: safe.maxX - margin - 56, y: top, width: 56, height: 56)
        slotLabel.frame = slotPanel.frame.insetBy(dx: 60, dy: 8)
        panel.frame = CGRect(x: margin, y: slotPanel.frame.maxY, width: width, height: cardHeight)
        information.frame = panel.frame.insetBy(dx: 24, dy: 20)
        status.frame = CGRect(x: margin + 12, y: panel.frame.maxY + 8, width: width - 24, height: 60)
        back.frame = CGRect(x: margin, y: safe.maxY - 56, width: 100, height: 48)
        confirm.frame = CGRect(x: safe.maxX - margin - 100, y: safe.maxY - 56, width: 100, height: 48)
    }

    private func selectSlot(_ direction: Int) {
        slot = (slot + direction + CampaignSaveStore.manualSlotCount) % CampaignSaveStore.manualSlotCount
        status.text = nil
        refresh()
    }

    private func refresh() {
        slotLabel.text = GameLocalization.format("SLOT %@/%@", String(describing: slot + 1), String(describing: CampaignSaveStore.manualSlotCount))
        do {
            let saved = try store.load(slot: slot)
            if let saved {
                let mission = Int(saved.mapID.dropFirst()) ?? 0
                let title = saved.skirmishMap.map { GameResources.mapName($0) } ?? (try? GameResources.campaignStoryText(id: 121 + mission)) ?? saved.mapID
                let mode = saved.skirmish == nil ? ((try? GameResources.campaignStoryText(id: 32)) ?? GameLocalization.text("story.32")) : GameLocalization.text("story.33")
                information.text = GameLocalization.format("%@\n\n%@\n\nCurrent turn: %@", String(describing: mode), String(describing: title), String(describing: saved.currentTurn + 1))
            } else { information.text = (try? GameResources.campaignStoryText(id: 79)) ?? GameLocalization.text("EMPTY SLOT") }
            confirm.isEnabled = snapshotToSave != nil || saved != nil
        } catch {
            information.text = GameLocalization.text("SAVE COULD NOT BE READ")
            confirm.isEnabled = snapshotToSave != nil
        }
    }

    private func activate() {
        guard snapshotToSave != nil else { load(slot: slot); return }
        guard store.hasManualSave(slot: slot) else { save(); return }
        let question = OriginalListViewController(title: GameLocalization.text("story.4"))
        question.body = (try? GameResources.campaignStoryText(id: 88)) ?? GameLocalization.text("Overwrite this saved game?")
        question.entries = [.init(title: GameLocalization.text("YES"), identifier: "saves.overwrite", action: { [weak self, weak question] in
            question?.dismiss(animated: false) { self?.save() }
        })]
        question.closeTitle = GameLocalization.text("NO")
        question.closeIdentifier = "saves.cancel-overwrite"
        question.modalPresentationStyle = .overFullScreen
        present(question, animated: false)
    }

    private func save() {
        guard let snapshotToSave else { return }
        do {
            try store.save(snapshotToSave, slot: slot)
            refresh()
            status.text = (try? GameResources.campaignStoryText(id: 77)) ?? GameLocalization.text("story.77")
        } catch { status.text = GameLocalization.text("SAVE FAILED. TRY AGAIN.") }
    }

    private func load(slot: Int) {
        do {
            guard let saved = try store.load(slot: slot) else { return }
            let callback = onLoad
            dismiss(animated: false) { callback?(saved) }
        } catch { status.text = GameLocalization.text("SAVE COULD NOT BE LOADED") }
    }
}
