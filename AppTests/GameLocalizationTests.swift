import AEContent
import AECore
import Foundation
import XCTest

final class GameLocalizationTests: XCTestCase {
    func testEveryOriginalStringIsAvailableInEverySupportedLanguage() throws {
        for language in GameLocalization.supportedLanguages {
            let locale = Locale(identifier: language)
            for id in 0...316 {
                let text = try XCTUnwrap(GameResources.campaignStoryText(id: id, locale: locale))
                if [176, 177, 178].contains(id) {
                    XCTAssertEqual(text, "")
                } else {
                    XCTAssertFalse(text.isEmpty, "\(language), story.\(id)")
                    XCTAssertNotEqual(text, "story.\(id)")
                }
                XCTAssertFalse(text.contains("%K"), "Obsolete keypad instruction: \(language), story.\(id)")
            }
            XCTAssertNil(try GameResources.campaignStoryText(id: 999, locale: locale))
        }
    }

    func testRegionalLanguagesAndUnsupportedLanguageFallback() {
        let cases = [
            ("en-US", "PLAY"), ("zh-CN", "开始游戏"),
            ("zh-TW", "開始遊戲"), ("zh-HK", "開始遊戲"), ("fr-FR", "PLAY"),
        ]
        for (language, expected) in cases {
            XCTAssertEqual(GameLocalization.text("story.1", locale: Locale(identifier: language)), expected)
        }
        XCTAssertEqual(GameLocalization.text("missing.key", defaultValue: "Fallback"), "Fallback")
        for (language, expected) in [("en", "CONTINUE GAME"), ("zh-Hans", "继续游戏"), ("zh-Hant", "繼續遊戲")] {
            XCTAssertEqual(GameLocalization.text("CONTINUE GAME", locale: Locale(identifier: language)), expected)
        }
    }

    func testMapsLocalizeAtPresentationWithoutChangingSavedNames() throws {
        let map = try GameResources.missionZero()
        XCTAssertEqual(GameResources.mapName(map, locale: Locale(identifier: "zh-Hans")), "勇气神殿")
        XCTAssertEqual(GameResources.mapName(map, locale: Locale(identifier: "zh-Hant")), "勇氣神殿")
        XCTAssertEqual(map.name, "The Temple of Courage")
        let imported = map.withIdentity("local-test", name: "My 自定义 Map")
        for language in GameLocalization.supportedLanguages {
            XCTAssertEqual(GameResources.mapName(imported, locale: Locale(identifier: language)), imported.name)
        }
    }

    func testOriginalUnitSchemaStillLoadsLocalizedNamesAndDescriptions() throws {
        let units = try GameResources.unitDefinitions()
        let nameIDs = [139, 143, 147, 151, 155, 159, 163, 167, 171, 175, 179, 183]
        XCTAssertEqual(units.count, nameIDs.count)
        for unit in units {
            XCTAssertEqual(unit.displayName, try GameResources.campaignStoryText(id: nameIDs[unit.id]))
            XCTAssertEqual(unit.description, try GameResources.campaignStoryText(id: 184 + unit.id))
        }
        XCTAssertEqual(GameLocalization.format("Starting Gold: %@", "500"),
                       GameLocalization.text("Starting Gold: %@").replacingOccurrences(of: "%@", with: "500"))
    }
}
