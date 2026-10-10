import AECore
import Foundation

@MainActor
enum GamePreferences {
    static var aiDifficulty: AIDifficulty {
        get { UserDefaults.standard.string(forKey: "game.aiDifficulty").flatMap(AIDifficulty.init(rawValue:)) ?? .normal }
        set { UserDefaults.standard.set(newValue.rawValue, forKey: "game.aiDifficulty") }
    }
    enum Setting: String, CaseIterable {
        case music, vibration, help, fightAnimation
        var title: String {
            switch self { case .music: GameLocalization.text("story.26"); case .vibration: GameLocalization.text("story.28"); case .help: GameLocalization.text("story.25"); case .fightAnimation: GameLocalization.text("story.24") }
        }
        var key: String { self == .help ? "game.showsHelp" : "game.\(rawValue)" }
    }
    static func enabled(_ setting: Setting) -> Bool {
        let defaults = UserDefaults.standard
        return defaults.object(forKey: setting.key) == nil ? true : defaults.bool(forKey: setting.key)
    }
    static func set(_ setting: Setting, enabled: Bool) { UserDefaults.standard.set(enabled, forKey: setting.key) }
    static var showsHelp: Bool {
        get { enabled(.help) }
        set { set(.help, enabled: newValue) }
    }
}
