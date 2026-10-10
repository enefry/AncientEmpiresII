import AECore
import UIKit

@MainActor
final class OriginalSettingsViewController: UIViewController {
    private let panel = OriginalFrameView()
    private let heading = OriginalArt.label(GameLocalization.text("story.8"), size: 23)
    private var rowLabels: [UILabel] = []
    private var toggles: [UIButton] = []
    private let difficultyLabel = OriginalArt.label(GameLocalization.text("AI Difficulty"), size: 19)
    private let difficultyHelp = OriginalArt.label(size: 14)
    private lazy var difficultyButton = OriginalGameButton("", identifier: "settings.aiDifficulty") { [weak self] in
        GamePreferences.aiDifficulty = GamePreferences.aiDifficulty.next
        self?.updateValues()
    }
    private lazy var back = OriginalGameButton(GameLocalization.text("story.31"), identifier: "panel.back") { [weak self] in self?.dismiss(animated: false) }

    override var prefersStatusBarHidden: Bool { true }
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = UIColor.black.withAlphaComponent(0.55)
        view.addSubview(panel)
        panel.addSubview(heading)
        panel.addSubview(back)
        panel.addSubview(difficultyLabel)
        panel.addSubview(difficultyButton)
        panel.addSubview(difficultyHelp)
        difficultyLabel.adjustsFontSizeToFitWidth = true
        difficultyButton.accessibilityLabel = GameLocalization.text("AI Difficulty")
        difficultyHelp.font = .systemFont(ofSize: 14)
        difficultyHelp.numberOfLines = 0
        heading.textAlignment = .center
        for setting in GamePreferences.Setting.allCases {
            let label = OriginalArt.label(setting.title, size: 19)
            label.adjustsFontSizeToFitWidth = true
            panel.addSubview(label)
            rowLabels.append(label)
            let button = OriginalGameButton("", identifier: "settings.\(setting.rawValue)") { [weak self] in
                GamePreferences.set(setting, enabled: !GamePreferences.enabled(setting))
                if setting == .music { GameAudioPlayer.shared.refreshPreference() }
                self?.updateValues()
            }
            button.accessibilityLabel = setting.title
            panel.addSubview(button)
            toggles.append(button)
        }
        updateValues()
    }
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let safe = view.bounds.inset(by: view.safeAreaInsets)
        panel.frame = CGRect(x: safe.minX + 8, y: safe.midY - 254, width: safe.width - 16, height: 508)
        heading.frame = CGRect(x: 16, y: 18, width: panel.bounds.width - 32, height: 34)
        for index in 0..<rowLabels.count {
            let y = CGFloat(70 + index * 64)
            rowLabels[index].frame = CGRect(x: 18, y: y, width: panel.bounds.width - 144, height: 48)
            toggles[index].frame = CGRect(x: panel.bounds.width - 118, y: y, width: 100, height: 48)
        }
        difficultyLabel.frame = CGRect(x: 18, y: 326, width: panel.bounds.width - 186, height: 48)
        difficultyButton.frame = CGRect(x: panel.bounds.width - 158, y: 326, width: 140, height: 48)
        difficultyHelp.frame = CGRect(x: 18, y: 380, width: panel.bounds.width - 36, height: 64)
        back.frame = CGRect(x: 18, y: 444, width: 90, height: 48)
    }
    private func updateValues() {
        let difficulty = GamePreferences.aiDifficulty
        difficultyButton.setTitle("◀ \(difficulty.title) ▶", for: .normal)
        difficultyButton.accessibilityValue = difficulty.title
        difficultyHelp.text = difficulty.summary + " " + GameLocalization.text("Applies to new battles. Saved battles keep their difficulty.")
        for (index, setting) in GamePreferences.Setting.allCases.enumerated() {
            let value = GamePreferences.enabled(setting) ? GameLocalization.text("story.29") : GameLocalization.text("story.30")
            toggles[index].setTitle("◀ \(value) ▶", for: .normal)
            toggles[index].accessibilityValue = value
        }
    }
}
