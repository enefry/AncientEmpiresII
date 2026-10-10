import XCTest

@MainActor
final class LocalizationUITests: XCTestCase {
    func testEnglishSystemLanguage() {
        verifyFlow(language: "en", play: "PLAY", idle: "idle", music: "Music", map: "(2)Icy Paths", tutorial: "Tap a map cell")
    }

    func testSimplifiedChineseSystemLanguage() {
        verifyFlow(language: "zh-Hans", play: "开始游戏", idle: "等待选择", music: "音乐", map: "(2)冰封小径", tutorial: "轻点地图格")
    }

    func testTraditionalChineseSystemLanguage() {
        verifyFlow(language: "zh-Hant", play: "開始遊戲", idle: "等待選擇", music: "音樂", map: "(2)冰封小徑", tutorial: "輕點地圖格")
    }

    private func verifyFlow(language: String, play: String, idle: String, music: String, map: String, tutorial: String) {
        continueAfterFailure = false
        let app = XCUIApplication()
        app.launchArguments = ["--show-title", "-AppleLanguages", "(\(language))", "-AppleLocale", language]
        app.launch()
        defer { app.terminate() }
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        XCTAssertTrue(app.images["title.animated-logo"].exists)
        XCTAssertFalse(app.staticTexts["game.localized-title"].exists)
        XCTAssertEqual(app.images["title.animated-logo"].label, "Ancient Empires II")
        attach(app, name: language + " animated title")
        app.buttons["title.continue"].tap()
        let heading = app.staticTexts["menu.heading"]
        let ready = XCTNSPredicateExpectation(predicate: NSPredicate(format: "exists == true AND value == %@", idle), object: heading)
        XCTAssertEqual(XCTWaiter.wait(for: [ready], timeout: 8), .completed)
        XCTAssertEqual(heading.label, play)
        XCTAssertFalse(app.staticTexts["game.localized-title"].exists)
        XCTAssertTrue(app.images["menu.logo"].exists)
        XCTAssertEqual(app.images["menu.logo"].label, "Ancient Empires II")
        attach(app, name: language + " menu")

        openMenuItem("menu.settings", app: app)
        XCTAssertTrue(app.buttons["settings.music"].waitForExistence(timeout: 5))
        XCTAssertEqual(app.buttons["settings.music"].label, music)
        attach(app, name: language + " settings")
        app.buttons["panel.back"].tap()

        openMenuItem("menu.instructions", app: app)
        XCTAssertTrue(app.staticTexts["instructions.text"].waitForExistence(timeout: 5))
        app.buttons["instructions.next"].tap()
        XCTAssertTrue(app.staticTexts["instructions.text"].label.contains(tutorial))
        XCTAssertFalse(app.staticTexts["instructions.text"].label.contains("%K"))
        attach(app, name: language + " instructions")
        app.buttons["panel.back"].tap()

        openMenuItem("menu.play", app: app)
        XCTAssertTrue(app.buttons["menu.skirmish"].waitForExistence(timeout: 5))
        openMenuItem("menu.skirmish", app: app)
        XCTAssertTrue(app.buttons["skirmish.map.s0"].waitForExistence(timeout: 5))
        XCTAssertEqual(app.buttons["skirmish.map.s0"].label, map)
        app.buttons["skirmish.map.s0"].tap()
        XCTAssertTrue(app.buttons["skirmish.start"].waitForExistence(timeout: 5))
        attach(app, name: language + " skirmish")
        app.buttons["skirmish.back"].tap()

        openMenuItem("menu.campaign.mission-zero", app: app)
        XCTAssertTrue(app.staticTexts["intro.story"].waitForExistence(timeout: 5))
        let intro = app.staticTexts["intro.story"].label
        XCTAssertTrue(intro.contains(language == "en" ? "messenger" : "信使"))
        app.buttons["intro.continue"].tap()
        let deadline = Date().addingTimeInterval(45)
        while !app.buttons["campaign.tutorial.continue"].exists && Date() < deadline {
            let objective = app.buttons["campaign.objective.continue"]
            if objective.exists { objective.tap(); continue }
            let next = app.buttons["campaign.story.continue"]
            if next.waitForExistence(timeout: 1) { next.tap() }
        }
        XCTAssertTrue(app.buttons["campaign.tutorial.continue"].exists)
        XCTAssertTrue(app.staticTexts["campaign.story.text"].label.contains(tutorial))
        attach(app, name: language + " campaign tutorial")
    }

    private func openMenuItem(_ identifier: String, app: XCUIApplication) {
        let button = app.buttons[identifier]
        XCTAssertTrue(button.waitForExistence(timeout: 5))
        button.tap()
        if app.buttons["menu.select"].isHittable { app.buttons["menu.select"].tap() }
    }

    private func attach(_ app: XCUIApplication, name: String) {
        let attachment = XCTAttachment(screenshot: app.screenshot())
        attachment.name = name
        attachment.lifetime = .keepAlways
        add(attachment)
    }
}
