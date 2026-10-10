import Foundation

/// Shared native localization for the UIKit interface and bundled game content.
/// The default bundle lookup follows iOS's system and per-app language settings.
public enum GameLocalization {
    public static let supportedLanguages = ["en", "zh-Hans", "zh-Hant"]

    public static func text(_ key: String, defaultValue: String? = nil, locale: Locale? = nil) -> String {
        let bundle = localizedBundle(for: locale)
        return bundle.localizedString(forKey: key, value: defaultValue, table: nil)
    }

    public static func format(_ key: String, _ arguments: String...) -> String {
        String(format: text(key), locale: Locale.current, arguments: arguments)
    }

    private static func localizedBundle(for locale: Locale?) -> Bundle {
        guard let locale else { return .module }
        let language = Bundle.preferredLocalizations(
            from: supportedLanguages, forPreferences: [locale.identifier]
        ).first ?? "en"
        // SwiftPM lowercases language folder names in its generated bundles.
        guard let path = Bundle.module.path(forResource: language, ofType: "lproj")
            ?? Bundle.module.path(forResource: language.lowercased(), ofType: "lproj"),
              let bundle = Bundle(path: path) else { return .module }
        return bundle
    }
}
