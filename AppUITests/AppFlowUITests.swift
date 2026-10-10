import AECore
import AEContent
import XCTest

private struct PlannedCampaignInput {
    let destination: GridPoint
    let targetID: Int?
}

@MainActor
final class AppFlowUITests: XCTestCase {
    private func appStartingAtTitle() -> XCUIApplication {
        let app = XCUIApplication()
        app.launchArguments += ["--show-title", "-AppleLanguages", "(en)", "-AppleLocale", "en_US"]
        return app
    }

    func testAIDifficultyPersistsAcrossLaunchAndAppearsForCPU() {
        let app = appStartingAtTitle()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.settings"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let difficulty = app.buttons["settings.aiDifficulty"]
        XCTAssertTrue(difficulty.waitForExistence(timeout: 5))
        XCTAssertTrue(difficulty.isHittable)
        let original = difficulty.value as? String
        difficulty.tap()
        let changed = difficulty.value as? String
        XCTAssertNotEqual(changed, original)
        let frame = XCTAttachment(screenshot: app.screenshot())
        frame.name = "AI difficulty in settings"
        frame.lifetime = .keepAlways
        add(frame)
        app.terminate()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.settings"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        XCTAssertTrue(difficulty.waitForExistence(timeout: 5))
        XCTAssertEqual(difficulty.value as? String, changed)
        // Return the user's default to its original level.
        for _ in 0..<3 { difficulty.tap() }
        XCTAssertEqual(difficulty.value as? String, original)
        app.buttons["panel.back"].tap()
        app.buttons["menu.play"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.skirmish"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        XCTAssertTrue(app.buttons["skirmish.map.s0"].waitForExistence(timeout: 5))
        app.buttons["skirmish.map.s0"].tap()
        let cpu = app.buttons["skirmish.player.1.difficulty"]
        scrollTo(cpu, in: app)
        let initial = cpu.label
        cpu.tap()
        XCTAssertNotEqual(cpu.label, initial)
        let setup = XCTAttachment(screenshot: app.screenshot())
        setup.name = "Per-CPU skirmish difficulty"
        setup.lifetime = .keepAlways
        add(setup)
        app.buttons["skirmish.back"].tap()
    }

    func testSkirmishHotseatStartsFromMenuAndResumesAfterRestart() {
        let app = appStartingAtTitle()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.skirmish"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        XCTAssertTrue(app.buttons["skirmish.map.s0"].waitForExistence(timeout: 5))
        app.buttons["skirmish.map.s0"].tap()
        let redName = app.buttons["skirmish.player.2.control"].label.components(separatedBy: ":").first ?? "Red"
        let blueName = app.buttons["skirmish.player.1.control"].label.components(separatedBy: ":").first ?? "Blue"
        app.buttons["skirmish.preview"].tap()
        XCTAssertTrue(app.descendants(matching: .any)["overview.map"].waitForExistence(timeout: 5))
        let preview = XCTAttachment(screenshot: app.screenshot())
        preview.name = "Skirmish map preview"
        preview.lifetime = .keepAlways
        add(preview)
        app.buttons["panel.back"].tap()
        // Blue is the second player on s0. Cycle CPU -> off -> human.
        let blue = app.buttons["skirmish.player.1.control"]
        scrollTo(blue, in: app)
        blue.tap(); blue.tap()
        let start = app.buttons["skirmish.start"]
        scrollTo(start, in: app)
        start.tap()
        XCTAssertTrue(app.buttons["campaign.end-turn"].waitForExistence(timeout: 10))
        XCTAssertTrue(app.staticTexts["campaign.turn.status"].label.contains(redName))
        app.buttons["campaign.end-turn"].tap()
        let ready = XCTNSPredicateExpectation(predicate: NSPredicate(format: "enabled == true"), object: app.buttons["campaign.end-turn"])
        XCTAssertEqual(XCTWaiter.wait(for: [ready], timeout: 15), .completed)
        XCTAssertTrue(app.staticTexts["campaign.turn.status"].label.contains(blueName))
        XCTAssertTrue(app.buttons["campaign.cell.13.13"].isHittable)
        let turn = app.staticTexts["campaign.turn.status"].label
        let battle = XCTAttachment(screenshot: app.screenshot())
        battle.name = "Skirmish hotseat blue turn"
        battle.lifetime = .keepAlways
        add(battle)
        app.terminate()
        app.launchArguments.removeAll { $0 == "--show-title" }
        app.launch()
        continueFromMainMenu(app)
        XCTAssertTrue(app.buttons["campaign.end-turn"].waitForExistence(timeout: 10))
        XCTAssertEqual(app.staticTexts["campaign.turn.status"].label, turn)
        XCTAssertFalse(app.buttons["title.continue"].exists)
    }

    private func scrollTo(_ element: XCUIElement, in app: XCUIApplication) {
        for _ in 0..<8 {
            if element.isHittable { return }
            app.scrollViews.firstMatch.swipeUp()
        }
        XCTAssertTrue(element.isHittable)
    }

    func testBonusMapStartsFromMenuAndResumesAfterRestart() {
        let app = appStartingAtTitle()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.skirmish"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let map = app.buttons["skirmish.map.b7"]
        XCTAssertTrue(map.waitForExistence(timeout: 5))
        scrollTo(map, in: app)
        let catalog = XCTAttachment(screenshot: app.screenshot())
        catalog.name = "Preserved bonus map catalog"
        catalog.lifetime = .keepAlways
        add(catalog)
        map.tap()
        let setup = XCTAttachment(screenshot: app.screenshot())
        setup.name = "Glu setup with inline map preview"
        setup.lifetime = .keepAlways
        add(setup)
        app.buttons["skirmish.preview"].tap()
        XCTAssertTrue(app.descendants(matching: .any)["overview.map"].waitForExistence(timeout: 5))
        let preview = XCTAttachment(screenshot: app.screenshot())
        preview.name = "Glu bonus map preview"
        preview.lifetime = .keepAlways
        add(preview)
        app.buttons["panel.back"].tap()
        let start = app.buttons["skirmish.start"]
        scrollTo(start, in: app)
        start.tap()
        XCTAssertTrue(app.buttons["campaign.end-turn"].waitForExistence(timeout: 10))
        let turn = app.staticTexts["campaign.turn.status"].label
        let canvas = app.descendants(matching: .any)["campaign.map.canvas"]
        let units = canvas.value as? String
        XCTAssertNotNil(units)
        app.terminate()
        app.launchArguments.removeAll { $0 == "--show-title" }
        app.launch()
        continueFromMainMenu(app)
        XCTAssertTrue(app.buttons["campaign.end-turn"].waitForExistence(timeout: 10))
        XCTAssertEqual(app.staticTexts["campaign.turn.status"].label, turn)
        XCTAssertEqual(canvas.value as? String, units)
    }

    func testAutosaveContinuesFromMenuAfterBackgroundAndProcessRestart() {
        let app = appStartingAtTitle()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.campaign.mission-zero"].tap()
        skipIntroduction(app)
        finishOpening(app)
        app.buttons["campaign.tutorial.continue"].tap()
        app.buttons["campaign.end-turn"].tap()
        guard waitForEnemyPresentation(in: app) else { return }
        let turn = app.staticTexts["campaign.turn.status"].label
        XCTAssertTrue(turn.contains("TURN 2"))
        let canvas = app.descendants(matching: .any)["campaign.map.canvas"]
        let units = canvas.value as? String
        XCTAssertNotNil(units)
        app.buttons["campaign.back"].tap()
        XCTAssertTrue(app.buttons["pause.save"].waitForExistence(timeout: 5))

        XCUIDevice.shared.press(.home)
        XCTAssertTrue(app.wait(for: .runningBackground, timeout: 5))
        app.activate()
        XCTAssertTrue(app.buttons["pause.save"].waitForExistence(timeout: 5))
        app.buttons["panel.back"].tap()
        XCTAssertEqual(app.staticTexts["campaign.turn.status"].label, turn)
        XCTAssertEqual(canvas.value as? String, units)

        XCUIDevice.shared.press(.home)
        XCTAssertTrue(app.wait(for: .runningBackground, timeout: 5))
        app.terminate()
        app.launchArguments.removeAll { $0 == "--show-title" }
        app.launch()
        continueFromMainMenu(app)
        XCTAssertTrue(canvas.waitForExistence(timeout: 10))
        XCTAssertFalse(app.buttons["title.continue"].exists)
        XCTAssertEqual(app.staticTexts["campaign.turn.status"].label, turn)
        XCTAssertEqual(canvas.value as? String, units)
    }

    func testOriginalRingMenuRotatesAndReturnsFromSubmenu() {
        let app = appStartingAtTitle()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        waitForMenuIdle(app)
        let heading = app.staticTexts["menu.heading"]
        XCTAssertEqual(heading.label, "PLAY")
        let playPosition = app.buttons["menu.play"].frame.midY
        app.swipeLeft()
        waitForMenuIdle(app)
        XCTAssertEqual(heading.label, "CONTINUE GAME")
        XCTAssertEqual(app.buttons["menu.continue"].frame.midY, playPosition, accuracy: 2)
        XCTAssertFalse(app.buttons["menu.online"].exists)
        let menu = XCTAttachment(screenshot: app.screenshot())
        menu.name = "Continue game selected with remastered resume icon"
        menu.lifetime = .keepAlways
        add(menu)
        app.swipeRight()
        waitForMenuIdle(app)
        XCTAssertEqual(heading.label, "PLAY")
        app.buttons["menu.select"].tap()
        waitForMenuIdle(app)
        XCTAssertEqual(heading.label, "NEW GAME")
        app.buttons["menu.back"].tap()
        waitForMenuIdle(app)
        XCTAssertEqual(heading.label, "PLAY")
    }

    func testManualSaveSlotsAndOriginalMapOverview() {
        let app = appStartingAtTitle()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.campaign.mission-zero"].tap()
        skipIntroduction(app)
        finishOpening(app)
        app.buttons["campaign.tutorial.continue"].tap()
        app.buttons["campaign.back"].tap()
        app.buttons["pause.map"].tap()
        let overview = app.images["overview.map"]
        XCTAssertTrue(overview.waitForExistence(timeout: 5))
        let mapFrame = XCTAttachment(screenshot: app.screenshot())
        mapFrame.name = "Original minimap tiles and faction markers"
        mapFrame.lifetime = .keepAlways
        add(mapFrame)
        overview.tap()
        app.buttons["campaign.back"].tap()
        app.buttons["pause.save"].tap()
        XCTAssertTrue(app.staticTexts["saves.slot"].waitForExistence(timeout: 5))
        app.buttons["saves.previous"].tap()
        XCTAssertEqual(app.staticTexts["saves.slot"].label, "SLOT 10/10")
        app.buttons["saves.confirm"].tap()
        if app.buttons["saves.overwrite"].exists { app.buttons["saves.overwrite"].tap() }
        XCTAssertTrue(app.staticTexts["saves.information"].label.contains("Current turn: 1"))
        XCTAssertFalse(app.staticTexts["saves.status"].label.isEmpty)
        let savedFrame = XCTAttachment(screenshot: app.screenshot())
        savedFrame.name = "Original ten manual save slots"
        savedFrame.lifetime = .keepAlways
        add(savedFrame)
        app.buttons["saves.confirm"].tap()
        XCTAssertTrue(app.buttons["saves.overwrite"].waitForExistence(timeout: 5))
        app.buttons["saves.cancel-overwrite"].tap() // Leave the original checkpoint intact.
        app.buttons["panel.back"].tap()
        app.buttons["campaign.end-turn"].tap()
        guard waitForEnemyPresentation(in: app) else { return }
        XCTAssertTrue(app.staticTexts["campaign.turn.status"].label.contains("TURN 2"))
        app.terminate()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.campaign.continue"].tap()
        app.buttons["menu.select"].tap()
        XCTAssertTrue(app.staticTexts["saves.slot"].waitForExistence(timeout: 5))
        XCTAssertFalse(app.buttons["saves.autosave"].exists)
        app.buttons["saves.previous"].tap()
        XCTAssertTrue(app.staticTexts["saves.information"].label.contains("Current turn: 1"))
        app.buttons["saves.confirm"].tap()
        XCTAssertTrue(app.staticTexts["campaign.turn.status"].waitForExistence(timeout: 5))
        XCTAssertTrue(app.staticTexts["campaign.turn.status"].label.contains("TURN 1"))
    }

    func testOpeningSceneAndObjectiveResumeAfterProcessRestart() {
        let app = appStartingAtTitle()
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.campaign.mission-zero"].tap()
        XCTAssertTrue(app.buttons["intro.continue"].waitForExistence(timeout: 5))
        app.buttons["intro.continue"].tap()
        let canvas = app.descendants(matching: .any)["campaign.map.canvas"]
        XCTAssertTrue(canvas.waitForExistence(timeout: 5))
        XCTAssertTrue((canvas.value as? String)?.contains("scene=") == true)
        _ = restartAndContinueCampaign(app)
        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 20))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try? GameResources.campaignStoryText(id: 221))
        for _ in 0..<4 {
            XCTAssertTrue(story.waitForExistence(timeout: 20))
            story.tap()
        }
        let objective = app.buttons["campaign.objective.continue"]
        XCTAssertTrue(objective.waitForExistence(timeout: 5))
        _ = restartAndContinueCampaign(app)
        XCTAssertTrue(objective.waitForExistence(timeout: 5))
        XCTAssertFalse(app.buttons["campaign.tutorial.continue"].exists)
        XCTAssertEqual(app.staticTexts["panel.body"].label, try? GameResources.campaignStoryText(id: 129))
        objective.tap()
        XCTAssertTrue(app.buttons["campaign.tutorial.continue"].waitForExistence(timeout: 5))
    }

    func testOriginalSettingsPersistAndInstructionsArePaginated() {
        let app = appStartingAtTitle()
        app.launch()
        let title = app.buttons["title.continue"]
        XCTAssertTrue(title.waitForExistence(timeout: 15))
        let titleImage = XCTAttachment(screenshot: app.screenshot())
        titleImage.name = "Full-window original startup"
        titleImage.lifetime = .keepAlways
        add(titleImage)
        title.tap()
        waitForMenuIdle(app)
        app.buttons["menu.settings"].tap()
        app.buttons["menu.select"].tap()
        let animation = app.buttons["settings.fightAnimation"]
        XCTAssertTrue(animation.waitForExistence(timeout: 3))
        let original = animation.value as? String
        animation.tap()
        XCTAssertNotEqual(animation.value as? String, original)
        let settings = XCTAttachment(screenshot: app.screenshot())
        settings.name = "Original four settings"
        settings.lifetime = .keepAlways
        add(settings)
        app.terminate()
        app.launch()
        XCTAssertTrue(title.waitForExistence(timeout: 15))
        title.tap()
        waitForMenuIdle(app)
        app.buttons["menu.settings"].tap()
        app.buttons["menu.select"].tap()
        XCTAssertTrue(animation.waitForExistence(timeout: 3))
        XCTAssertNotEqual(animation.value as? String, original)
        animation.tap()
        XCTAssertEqual(animation.value as? String, original)
        app.buttons["panel.back"].tap()
        app.buttons["menu.instructions"].tap()
        app.buttons["menu.select"].tap()
        let page = app.staticTexts["instructions.page"]
        XCTAssertTrue(page.waitForExistence(timeout: 3))
        XCTAssertTrue(page.label.contains("0/19"))
        app.buttons["instructions.previous"].tap()
        XCTAssertTrue(page.label.contains("19/19"))
        XCTAssertFalse(app.staticTexts["instructions.text"].label.isEmpty)
        let instructions = XCTAttachment(screenshot: app.screenshot())
        instructions.name = "Original instructions last page"
        instructions.lifetime = .keepAlways
        add(instructions)
        app.buttons["instructions.next"].tap()
        XCTAssertTrue(page.label.contains("0/19"))
        app.buttons["panel.back"].tap()
    }

    func testFullscreenBattlefieldRestoresActionAfterProcessRestart() throws {
        let app = appStartingAtTitle()
        app.launch()

        let continueButton = app.buttons["title.continue"]
        XCTAssertTrue(continueButton.waitForExistence(timeout: 15))
        continueButton.tap()

        openPlayMenu(app)
        let campaignButton = app.buttons["menu.campaign.mission-zero"]
        XCTAssertTrue(campaignButton.waitForExistence(timeout: 5))
        campaignButton.tap()
        skipIntroduction(app)

        let mapCanvas = app.descendants(matching: .any)["campaign.map.canvas"]
        XCTAssertTrue(mapCanvas.waitForExistence(timeout: 5))
        let windowFrame = app.windows.firstMatch.frame
        XCTAssertEqual(mapCanvas.frame.minX, windowFrame.minX, accuracy: 1)
        XCTAssertEqual(mapCanvas.frame.minY, windowFrame.minY, accuracy: 1)
        XCTAssertEqual(mapCanvas.frame.width, windowFrame.width, accuracy: 1)
        XCTAssertEqual(mapCanvas.frame.height, windowFrame.height, accuracy: 1)
        let storyButton = app.buttons["campaign.story.continue"]
        XCTAssertTrue(storyButton.waitForExistence(timeout: 3))
        finishOpening(app)
        let tutorialButton = app.buttons["campaign.tutorial.continue"]
        XCTAssertTrue(tutorialButton.waitForExistence(timeout: 3))
        XCTAssertTrue(app.staticTexts["campaign.story.text"].label.contains("Tap a map cell"))
        tutorialButton.tap()
        XCTAssertTrue(app.staticTexts["campaign.turn.status"].label.contains("Blue's Turn"))
        XCTAssertTrue((mapCanvas.value as? String)?.contains("3 blue units, 2 red units") == true)

        let cellInfo = app.staticTexts["map.cell.inspection"]
        XCTAssertTrue(cellInfo.waitForExistence(timeout: 3), app.debugDescription)
        tapMapCell(GridPoint(x: 3, y: 8), in: app)
        XCTAssertTrue(tutorialButton.waitForExistence(timeout: 5))
        XCTAssertTrue(app.staticTexts["campaign.story.text"].label.contains("movement range"))
        tutorialButton.tap()
        XCTAssertTrue(cellInfo.label.contains("SOLDIER"))
        let sourceCell = app.buttons["campaign.cell.3.8"]
        XCTAssertEqual(sourceCell.frame.width, 60, accuracy: 1)
        tapMapCell(GridPoint(x: 4, y: 8), in: app)
        XCTAssertFalse(tutorialButton.exists)
        let confirm = app.buttons["campaign.confirm"]
        XCTAssertTrue(confirm.isEnabled)
        let route = XCTAttachment(screenshot: app.screenshot())
        route.name = "Original movement range and route with 60pt tiles"
        route.lifetime = .keepAlways
        add(route)
        confirm.tap()
        let endUnitButton = app.buttons["campaign.end-unit"]
        let landed = expectation(for: NSPredicate(format: "enabled == true"), evaluatedWith: endUnitButton)
        wait(for: [landed], timeout: 5)
        XCTAssertFalse(tutorialButton.exists)
        XCTAssertTrue(cellInfo.label.contains("HP"))
        let actionMenu = XCTAttachment(screenshot: app.screenshot())
        actionMenu.name = "Original corner action menu after movement"
        actionMenu.lifetime = .keepAlways
        add(actionMenu)
        app.buttons["campaign.unit-info"].tap()
        XCTAssertTrue(app.otherElements["unit.stats"].waitForExistence(timeout: 3))
        let details = XCTAttachment(screenshot: app.screenshot())
        details.name = "Original unit statistics and description"
        details.lifetime = .keepAlways
        add(details)
        app.buttons["panel.back"].tap()
        app.buttons["campaign.cell.4.8"].doubleTap()
        let moveTutorialButton = app.buttons["campaign.tutorial.continue"]
        XCTAssertTrue(moveTutorialButton.waitForExistence(timeout: 3), app.debugDescription)
        XCTAssertTrue(app.staticTexts["campaign.story.text"].label.contains("Terrain affects movement"))
        moveTutorialButton.tap()
        XCTAssertTrue(cellInfo.waitForExistence(timeout: 3))
        tapMapCell(GridPoint(x: 4, y: 8), in: app)
        XCTAssertFalse(endUnitButton.exists && endUnitButton.isEnabled)

        app.terminate()
        app.launch()
        continueFromMainMenu(app)

        let restoredCanvas = app.descendants(matching: .any)["campaign.map.canvas"]
        XCTAssertTrue(restoredCanvas.waitForExistence(timeout: 5))
        XCTAssertTrue(app.staticTexts["campaign.turn.status"].label.contains("Blue's Turn"))
        let restoredScreenshot = XCTAttachment(screenshot: app.screenshot())
        restoredScreenshot.name = "Restored campaign battlefield"
        restoredScreenshot.lifetime = .keepAlways
        add(restoredScreenshot)
        tapMapCell(GridPoint(x: 4, y: 8), in: app)
        XCTAssertFalse(app.buttons["campaign.end-unit"].exists && app.buttons["campaign.end-unit"].isEnabled, app.debugDescription)
        XCTAssertTrue(cellInfo.label.contains("SOLDIER"), app.debugDescription)
    }

    func testEndTurnExecutesRedTurnAndReturnsControlToBlue() throws {
        let app = appStartingAtTitle()
        app.launch()

        let continueButton = app.buttons["title.continue"]
        XCTAssertTrue(continueButton.waitForExistence(timeout: 15))
        continueButton.tap()

        openPlayMenu(app)
        let campaignButton = app.buttons["menu.campaign.mission-zero"]
        XCTAssertTrue(campaignButton.waitForExistence(timeout: 5))
        campaignButton.tap()
        skipIntroduction(app)

        let storyButton = app.buttons["campaign.story.continue"]
        XCTAssertTrue(storyButton.waitForExistence(timeout: 5))
        finishOpening(app)
        let tutorialButton = app.buttons["campaign.tutorial.continue"]
        XCTAssertTrue(tutorialButton.waitForExistence(timeout: 3))
        tutorialButton.tap()

        let turnStatus = app.staticTexts["campaign.turn.status"]
        XCTAssertTrue(turnStatus.waitForExistence(timeout: 5))
        XCTAssertTrue(turnStatus.label.contains("TURN 1"))
        let endTurnButton = app.buttons["campaign.end-turn"]
        XCTAssertTrue(endTurnButton.isEnabled)
        endTurnButton.tap()

        let turnIncome = app.staticTexts["campaign.new-turn.income"]
        XCTAssertTrue(turnIncome.waitForExistence(timeout: 3))
        XCTAssertEqual(turnIncome.label, "INCOME: ?")
        let redFrame = XCTAttachment(screenshot: app.screenshot())
        redFrame.name = "Original enemy turn hidden income"
        redFrame.lifetime = .keepAlways
        add(redFrame)
        _ = restartAndContinueCampaign(app)
        let blueIncome = XCTNSPredicateExpectation(predicate: NSPredicate(format: "exists == true AND label == %@", "INCOME: 60"), object: turnIncome)
        XCTAssertEqual(XCTWaiter.wait(for: [blueIncome], timeout: 30), .completed)
        let blueFrame = XCTAttachment(screenshot: app.screenshot())
        blueFrame.name = "Original blue turn income 60 after restore"
        blueFrame.lifetime = .keepAlways
        add(blueFrame)
        guard waitForEnemyPresentation(in: app) else { return }
        XCTAssertTrue(turnStatus.label.contains("TURN 2"))
        XCTAssertTrue(turnStatus.label.contains("Blue's Turn"))
    }

    func testMissionReachesVictoryThroughUIKitInputs() throws {
        let seed: Int64 = 42_610_01
        let map = try GameResources.missionZero()
        let profiles = try GameResources.campaignUnitProfiles()
        let rules = try GameResources.campaignRuleset()
        var session = try CampaignSession(map: map, profiles: profiles, rules: rules, seed: seed)
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-seed=\(seed)")
        app.launch()

        let titleButton = app.buttons["title.continue"]
        XCTAssertTrue(titleButton.waitForExistence(timeout: 15))
        titleButton.tap()

        openPlayMenu(app)
        let missionButton = app.buttons["menu.campaign.mission-zero"]
        XCTAssertTrue(missionButton.waitForExistence(timeout: 5))
        missionButton.tap()
        skipIntroduction(app)

        var mapCanvas = app.descendants(matching: .any)["campaign.map.canvas"]
        XCTAssertTrue(mapCanvas.waitForExistence(timeout: 5))
        let storyButton = app.buttons["campaign.story.continue"]
        XCTAssertTrue(storyButton.waitForExistence(timeout: 5))
        finishOpening(app)
        for _ in 0..<5 { try session.advanceStory() }
        let tutorialButton = app.buttons["campaign.tutorial.continue"]
        XCTAssertTrue(tutorialButton.waitForExistence(timeout: 5))
        for _ in 0..<1 {
            tutorialButton.tap()
            session.dismissCampaignHelp()
        }

        var restartedAtFirstReinforcement = false
        var restartedAtEnding = false
        var restartedDuringCombat = false
        var hasObservedNaturalCombat = false
        for _ in 0..<180 {
            if session.snapshot.phase == .victory {
                let result = app.staticTexts["campaign.result.text"]
                XCTAssertTrue(result.waitForExistence(timeout: 5))
                XCTAssertEqual(result.label, "MISSION COMPLETE")
                let frame = XCTAttachment(screenshot: app.screenshot())
                frame.name = "Original mission complete message"
                frame.lifetime = .keepAlways
                add(frame)
                XCTAssertTrue(app.buttons["campaign.result.back"].exists)
                return
            }
            if session.snapshot.phase == .defeat {
                XCTFail("The blue commander fell before the campaign was won.")
                return
            }

            if session.snapshot.pendingCampaignHelpID != nil {
                XCTAssertTrue(tutorialButton.waitForExistence(timeout: 5))
                tutorialButton.tap()
                session.dismissCampaignHelp()
                assertVisibleUnitCounts(in: mapCanvas, match: session)
                continue
            }

            switch session.snapshot.phase {
            case .opening, .story:
                if session.snapshot.storyMode == .reinforcements && !restartedAtFirstReinforcement {
                    restartedAtFirstReinforcement = true
                    XCTAssertEqual(session.snapshot.currentStoryMessageID, 225)
                    mapCanvas = restartAndContinueCampaign(app)
                    assertVisibleUnitCounts(in: mapCanvas, match: session)
                    XCTAssertEqual(session.snapshot.units.filter { $0.isAlive && $0.fraction == 2 }.count, 2)
                    continue
                }
                if session.snapshot.storyMode == .ending && !restartedAtEnding {
                    restartedAtEnding = true
                    XCTAssertEqual(session.snapshot.currentStoryMessageID, 227)
                    mapCanvas = restartAndContinueCampaign(app)
                    assertVisibleUnitCounts(in: mapCanvas, match: session)
                    XCTAssertEqual(session.snapshot.units.filter { $0.isAlive && $0.fraction == 2 }.count, 0)
                    continue
                }
                let nextStoryButton = app.buttons["campaign.story.continue"]
                XCTAssertTrue(nextStoryButton.waitForExistence(timeout: 5))
                nextStoryButton.tap()
                try session.advanceStory()
                if session.snapshot.phase != .victory { assertVisibleUnitCounts(in: mapCanvas, match: session) }
            case .playerTurn:
                if let unit = session.snapshot.units.first(where: {
                    $0.isAlive && $0.fraction == 1 && !$0.hasActed
                }) {
                    let input = try plannedInput(for: unit.id, in: session, profiles: profiles, rules: rules)
                    tapMapCell(unit.position, in: app)
                    try session.selectUnit(id: unit.id)
                    session.movementRangeDidAppear(for: unit.id)
                    if session.snapshot.pendingCampaignHelpID != nil {
                        XCTAssertTrue(tutorialButton.waitForExistence(timeout: 5))
                        tutorialButton.tap()
                        session.dismissCampaignHelp()
                    }
                    if input.destination != unit.position {
                        tapMapCell(input.destination, in: app)
                        app.buttons["campaign.confirm"].tap()
                        let landed = expectation(for: NSPredicate(format: "enabled == true"), evaluatedWith: app.buttons["campaign.end-unit"])
                        wait(for: [landed], timeout: 8)
                        try session.move(unitID: unit.id, to: input.destination)
                        if session.snapshot.pendingCampaignHelpID != nil {
                            XCTAssertTrue(tutorialButton.waitForExistence(timeout: 5))
                            tutorialButton.tap()
                            session.dismissCampaignHelp()
                        }
                    }

                    if let targetID = input.targetID,
                       let target = session.unit(id: targetID), target.isAlive {
                        XCTAssertTrue(session.attackableUnits(for: unit.id).contains { $0.id == targetID })
                        doubleTapMapCell(target.position, in: app)
                        _ = try session.attack(unitID: unit.id, targetID: targetID)
                        let skip = app.buttons["combat.skip"]
                        XCTAssertTrue(skip.waitForExistence(timeout: 5))
                        if !restartedDuringCombat {
                            restartedDuringCombat = true
                            let frame = XCTAttachment(screenshot: app.screenshot())
                            frame.name = "Original combat before process restart"
                            frame.lifetime = .keepAlways
                            add(frame)
                            mapCanvas = restartAndContinueCampaign(app)
                            XCTAssertTrue(skip.waitForExistence(timeout: 5))
                            let restored = XCTAttachment(screenshot: app.screenshot())
                            restored.name = "Restored committed combat"
                            restored.lifetime = .keepAlways
                            add(restored)
                        }
                        if restartedDuringCombat && !hasObservedNaturalCombat {
                            hasObservedNaturalCombat = true
                            let completed = expectation(for: NSPredicate(format: "exists == false"), evaluatedWith: skip)
                            wait(for: [completed], timeout: 20)
                        } else { skip.tap() }
                    } else {
                        let endUnitButton = app.buttons["campaign.end-unit"]
                        XCTAssertTrue(endUnitButton.waitForExistence(timeout: 5))
                        XCTAssertTrue(endUnitButton.isEnabled)
                        endUnitButton.tap()
                        try session.endUnitAction(unitID: unit.id)
                    }
                    assertVisibleUnitCounts(in: mapCanvas, match: session)
                } else {
                    let endTurnButton = app.buttons["campaign.end-turn"]
                    XCTAssertTrue(endTurnButton.waitForExistence(timeout: 5))
                    XCTAssertTrue(endTurnButton.isEnabled)
                    endTurnButton.tap()
                    _ = try session.endPlayerTurn()
                    guard waitForEnemyPresentation(in: app) else { return }
                    assertVisibleUnitCounts(in: mapCanvas, match: session)
                }
            case .enemyTurn:
                XCTFail("The red turn should resolve before control returns to this test.")
                return
            case .victory, .defeat:
                break
            }
        }

        XCTFail("The campaign did not reach victory within the UI action limit.")
    }

    func testUnlockedMissionOneStartsWithOriginalMapNameAndOpeningDialogue() throws {
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-unlock=1")
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.levels"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let mission = app.buttons["menu.level.1"]
        XCTAssertTrue(mission.waitForExistence(timeout: 5))
        mission.tap()

        let chapter = app.staticTexts["intro.chapter"]
        XCTAssertTrue(chapter.waitForExistence(timeout: 5))
        XCTAssertEqual(chapter.label, "The Temple of Wisdom")
        let chapterFrame = XCTAttachment(screenshot: app.screenshot())
        chapterFrame.name = "Mission one original map-name interstitial"
        chapterFrame.lifetime = .keepAlways
        add(chapterFrame)

        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 12))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try GameResources.campaignStoryText(id: 231))
        let opening = XCTAttachment(screenshot: app.screenshot())
        opening.name = "Mission one first original dialogue"
        opening.lifetime = .keepAlways
        add(opening)
    }

    func testUnlockedMissionTwoStartsWithOriginalMapNameAndOpeningDialogue() throws {
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-unlock=2")
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.levels"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let mission = app.buttons["menu.level.2"]
        XCTAssertTrue(mission.waitForExistence(timeout: 5))
        mission.tap()

        let chapter = app.staticTexts["intro.chapter"]
        XCTAssertTrue(chapter.waitForExistence(timeout: 5))
        XCTAssertEqual(chapter.label, "Forest of Mists")
        let chapterFrame = XCTAttachment(screenshot: app.screenshot())
        chapterFrame.name = "Mission two original map-name interstitial"
        chapterFrame.lifetime = .keepAlways
        add(chapterFrame)

        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 15))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try GameResources.campaignStoryText(id: 241))
        let opening = XCTAttachment(screenshot: app.screenshot())
        opening.name = "Mission two first original dialogue"
        opening.lifetime = .keepAlways
        add(opening)
    }

    func testUnlockedMissionThreeUsesIntroThreeAndOriginalOpeningDialogue() throws {
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-unlock=3")
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.levels"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let mission = app.buttons["menu.level.3"]
        XCTAssertTrue(mission.waitForExistence(timeout: 5))
        mission.tap()

        let narrative = app.staticTexts["intro.story"]
        XCTAssertTrue(narrative.waitForExistence(timeout: 5))
        XCTAssertEqual(narrative.label, try GameResources.campaignStoryText(id: 218))
        let intro = XCTAttachment(screenshot: app.screenshot())
        intro.name = "Mission three original intro3 narrative"
        intro.lifetime = .keepAlways
        add(intro)
        app.buttons["intro.continue"].tap()

        let chapter = app.staticTexts["intro.chapter"]
        XCTAssertTrue(chapter.waitForExistence(timeout: 5))
        XCTAssertEqual(chapter.label, "REINFORCEMENTS")
        let chapterFrame = XCTAttachment(screenshot: app.screenshot())
        chapterFrame.name = "Mission three original chapter interstitial"
        chapterFrame.lifetime = .keepAlways
        add(chapterFrame)

        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 20))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try GameResources.campaignStoryText(id: 249))
        let opening = XCTAttachment(screenshot: app.screenshot())
        opening.name = "Mission three first original dialogue"
        opening.lifetime = .keepAlways
        add(opening)
    }

    func testUnlockedMissionFourUsesOriginalMapNameAndOpeningDialogue() throws {
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-unlock=4")
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.levels"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let mission = app.buttons["menu.level.4"]
        XCTAssertTrue(mission.waitForExistence(timeout: 5))
        mission.tap()

        let chapter = app.staticTexts["intro.chapter"]
        XCTAssertTrue(chapter.waitForExistence(timeout: 5))
        XCTAssertEqual(chapter.label, "Pathway to Thorin")
        let chapterFrame = XCTAttachment(screenshot: app.screenshot())
        chapterFrame.name = "Mission four original map-name interstitial"
        chapterFrame.lifetime = .keepAlways
        add(chapterFrame)

        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 20))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try GameResources.campaignStoryText(id: 256))
        let opening = XCTAttachment(screenshot: app.screenshot())
        opening.name = "Mission four first original dialogue"
        opening.lifetime = .keepAlways
        add(opening)
    }

    func testUnlockedMissionFiveUsesOriginalMapNameAndOpeningDialogue() throws {
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-unlock=5")
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.levels"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let mission = app.buttons["menu.level.5"]
        XCTAssertTrue(mission.waitForExistence(timeout: 5))
        mission.tap()

        let chapter = app.staticTexts["intro.chapter"]
        XCTAssertTrue(chapter.waitForExistence(timeout: 5))
        XCTAssertEqual(chapter.label, "The Gates of Thorin")
        let chapterFrame = XCTAttachment(screenshot: app.screenshot())
        chapterFrame.name = "Mission five original map-name interstitial"
        chapterFrame.lifetime = .keepAlways
        add(chapterFrame)

        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 20))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try GameResources.campaignStoryText(id: 260))
        let opening = XCTAttachment(screenshot: app.screenshot())
        opening.name = "Mission five first original dialogue"
        opening.lifetime = .keepAlways
        add(opening)
    }

    func testUnlockedMissionSixUsesOriginalMapNameAndOpeningDialogue() throws {
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-unlock=6")
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.levels"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let mission = app.buttons["menu.level.6"]
        XCTAssertTrue(mission.waitForExistence(timeout: 5))
        mission.tap()

        let chapter = app.staticTexts["intro.chapter"]
        XCTAssertTrue(chapter.waitForExistence(timeout: 5))
        XCTAssertEqual(chapter.label, "Outside the City")
        let chapterFrame = XCTAttachment(screenshot: app.screenshot())
        chapterFrame.name = "Mission six original map-name interstitial"
        chapterFrame.lifetime = .keepAlways
        add(chapterFrame)

        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 20))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try GameResources.campaignStoryText(id: 262))
        let opening = XCTAttachment(screenshot: app.screenshot())
        opening.name = "Mission six first original dialogue"
        opening.lifetime = .keepAlways
        add(opening)
    }

    func testUnlockedFinalMissionUsesOriginalIntroChapterAndOpeningDialogue() throws {
        let app = appStartingAtTitle()
        app.launchArguments.append("--campaign-unlock=7")
        app.launch()
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        app.buttons["title.continue"].tap()
        openPlayMenu(app)
        app.buttons["menu.levels"].tap()
        waitForMenuIdle(app)
        app.buttons["menu.select"].tap()
        let mission = app.buttons["menu.level.7"]
        XCTAssertTrue(mission.waitForExistence(timeout: 5))
        mission.tap()

        let introduction = app.buttons["intro.continue"]
        XCTAssertTrue(introduction.waitForExistence(timeout: 5))
        XCTAssertEqual(app.staticTexts["intro.story"].label, try GameResources.campaignStoryText(id: 219))
        let introFrame = XCTAttachment(screenshot: app.screenshot())
        introFrame.name = "Mission seven original intro4 prophecy"
        introFrame.lifetime = .keepAlways
        add(introFrame)
        introduction.tap()

        let chapter = app.staticTexts["intro.chapter"]
        XCTAssertTrue(chapter.waitForExistence(timeout: 5))
        XCTAssertEqual(chapter.label, "HEAVEN'S FURY")
        let chapterFrame = XCTAttachment(screenshot: app.screenshot())
        chapterFrame.name = "Mission seven original chapter"
        chapterFrame.lifetime = .keepAlways
        add(chapterFrame)

        let story = app.buttons["campaign.story.continue"]
        XCTAssertTrue(story.waitForExistence(timeout: 20))
        XCTAssertEqual(app.staticTexts["campaign.story.text"].label, try GameResources.campaignStoryText(id: 271))
        let opening = XCTAttachment(screenshot: app.screenshot())
        opening.name = "Mission seven first original dialogue"
        opening.lifetime = .keepAlways
        add(opening)
    }

    private func finishOpening(_ app: XCUIApplication) {
        for index in 0..<4 {
            let story = app.buttons["campaign.story.continue"]
            XCTAssertTrue(story.waitForExistence(timeout: 20))
            if index == 0 || index == 2 {
                let frame = XCTAttachment(screenshot: app.screenshot())
                frame.name = index == 0 ? "Opening camera at temple guard" : "Opening camera returns to Galamar"
                frame.lifetime = .keepAlways
                add(frame)
            }
            story.tap()
        }
        let objective = app.buttons["campaign.objective.continue"]
        XCTAssertTrue(objective.waitForExistence(timeout: 5))
        XCTAssertFalse(app.buttons["campaign.tutorial.continue"].exists)
        let frame = XCTAttachment(screenshot: app.screenshot())
        frame.name = "Original objective before first tutorial"
        frame.lifetime = .keepAlways
        add(frame)
        objective.tap()
    }

    private func skipIntroduction(_ app: XCUIApplication) {
        let next = app.buttons["intro.continue"]
        XCTAssertTrue(next.waitForExistence(timeout: 5))
        let intro = XCTAttachment(screenshot: app.screenshot())
        intro.name = "Original illustrated introduction"
        intro.lifetime = .keepAlways
        add(intro)
        next.tap()
        XCTAssertTrue(app.buttons["campaign.story.continue"].waitForExistence(timeout: 20))
    }

    @discardableResult
    private func waitForEnemyPresentation(in app: XCUIApplication) -> Bool {
        let deadline = Date().addingTimeInterval(45)
        let mapCanvas = app.descendants(matching: .any)["campaign.map.canvas"]
        let turnStatus = app.staticTexts["campaign.turn.status"]
        while Date() < deadline {
            let skip = app.buttons["combat.skip"]
            if skip.exists { skip.tap(); continue }
            if app.buttons["campaign.story.continue"].exists || app.buttons["campaign.tutorial.continue"].exists { return true }
            if let value = mapCanvas.value as? String,
               !value.contains("presentation="),
               turnStatus.label.contains("Blue's Turn") {
                let endTurn = app.buttons["campaign.end-turn"]
                if endTurn.exists, endTurn.isEnabled { return true }
            }
            _ = skip.waitForExistence(timeout: 0.5)
        }
        let latestCanvas = app.descendants(matching: .any)["campaign.map.canvas"]
        let latestEndTurn = app.buttons["campaign.end-turn"]
        XCTFail("Enemy presentations did not finish: \(latestCanvas.value ?? "missing canvas value"); endTurnExists=\(latestEndTurn.exists); endTurnEnabled=\(latestEndTurn.isEnabled); \(turnStatus.label)")
        return false
    }

    private func openPlayMenu(_ app: XCUIApplication) {
        let play = app.buttons["menu.play"]
        XCTAssertTrue(play.waitForExistence(timeout: 5))
        waitForMenuIdle(app)
        let menu = XCTAttachment(screenshot: app.screenshot())
        menu.name = "Original ring menu"
        menu.lifetime = .keepAlways
        add(menu)
        play.tap()
        waitForMenuIdle(app)
    }

    private func waitForMenuIdle(_ app: XCUIApplication) {
        let heading = app.staticTexts["menu.heading"]
        let ready = XCTNSPredicateExpectation(predicate: NSPredicate(format: "exists == true AND value == %@", "idle"), object: heading)
        XCTAssertEqual(XCTWaiter.wait(for: [ready], timeout: 6), .completed)
    }

    private func restartAndContinueCampaign(_ app: XCUIApplication) -> XCUIElement {
        app.terminate()
        app.launchArguments.removeAll { $0 == "--show-title" }
        app.launch()
        continueFromMainMenu(app)

        let mapCanvas = app.descendants(matching: .any)["campaign.map.canvas"]
        XCTAssertTrue(mapCanvas.waitForExistence(timeout: 10))
        XCTAssertFalse(app.buttons["title.continue"].exists)
        return mapCanvas
    }

    private func continueFromMainMenu(_ app: XCUIApplication) {
        XCTAssertTrue(app.buttons["title.continue"].waitForExistence(timeout: 15))
        XCTAssertFalse(app.descendants(matching: .any)["campaign.map.canvas"].exists)
        app.buttons["title.continue"].tap()
        waitForMenuIdle(app)
        let resume = app.buttons["menu.continue"]
        XCTAssertTrue(resume.exists)
        XCTAssertTrue(resume.isEnabled)
        XCTAssertFalse(app.buttons["menu.online"].exists)
        resume.tap()
        waitForMenuIdle(app)
        XCTAssertEqual(app.staticTexts["menu.heading"].label, "CONTINUE GAME")
        let frame = XCTAttachment(screenshot: app.screenshot())
        frame.name = "Continue game before explicitly restoring autosave"
        frame.lifetime = .keepAlways
        add(frame)
        app.buttons["menu.select"].tap()
    }

    private func plannedInput(
        for unitID: Int,
        in session: CampaignSession,
        profiles: [Int: CampaignUnitProfile],
        rules: CampaignRuleset
    ) throws -> PlannedCampaignInput {
        guard let unit = session.unit(id: unitID) else {
            throw CampaignSessionError.unitUnavailable
        }
        let range = try session.movementRange(for: unitID)
        var destinations = [unit.position]
        destinations.append(contentsOf: range?.reachableDestinations ?? [])
        destinations = Array(Set(destinations)).sorted {
            $0.y == $1.y ? $0.x < $1.x : $0.y < $1.y
        }

        var bestScore = Int.min
        var bestInput = PlannedCampaignInput(destination: unit.position, targetID: nil)
        for destination in destinations {
            var projected = session
            if destination != unit.position {
                try projected.move(unitID: unitID, to: destination)
                if projected.snapshot.pendingCampaignHelpID != nil {
                    projected.dismissCampaignHelp()
                }
            }

            let target = projected.attackableUnits(for: unitID).sorted {
                if ($0.type == 9) != ($1.type == 9) { return $0.type == 9 }
                if $0.hitPoints != $1.hitPoints { return $0.hitPoints < $1.hitPoints }
                return $0.id < $1.id
            }.first
            let enemies = projected.snapshot.units.filter { $0.isAlive && $0.fraction != 1 }
            let nearestDistance = enemies.map { distance(destination, $0.position) }.min() ?? 0
            let terrainID = projected.map.tileID(x: destination.x, y: destination.y)
                .flatMap { rules.terrainByTileID[$0] } ?? 0
            let defense = rules.defenseByTerrainID[terrainID] ?? 0

            var score: Int
            if let target {
                score = 100_000 + (100 - target.hitPoints) * 30
                if let profile = profiles[target.type], profile.minimumRange == 1,
                   distance(destination, target.position) == 1 {
                    score -= 2_000
                }
            } else {
                score = -nearestDistance * 100 + defense * 2
                if unit.type == 9 && nearestDistance < 4 { score -= 5_000 }
            }

            if score > bestScore {
                bestScore = score
                bestInput = PlannedCampaignInput(destination: destination, targetID: target?.id)
            }
        }
        return bestInput
    }

    private func tapMapCell(_ point: GridPoint, in app: XCUIApplication) {
        let identifier = "campaign.cell.\(point.x).\(point.y)"
        let cell = app.descendants(matching: .any)[identifier]
        XCTAssertTrue(cell.waitForExistence(timeout: 3), "Missing map cell \(identifier)\n\(app.debugDescription)")
        cell.tap()
    }

    private func doubleTapMapCell(_ point: GridPoint, in app: XCUIApplication) {
        let identifier = "campaign.cell.\(point.x).\(point.y)"
        let cell = app.descendants(matching: .any)[identifier]
        XCTAssertTrue(cell.waitForExistence(timeout: 3), "Missing map cell \(identifier)\n\(app.debugDescription)")
        cell.doubleTap()
    }

    private func assertVisibleUnitCounts(in canvas: XCUIElement, match session: CampaignSession) {
        let units = session.snapshot.units.filter(\.isAlive)
        let blueCount = units.filter { $0.fraction == 1 }.count
        let redCount = units.filter { $0.fraction == 2 }.count
        let expected = "\(blueCount) blue units, \(redCount) red units"
        let settled = expectation(for: NSPredicate(format: "value == %@", expected), evaluatedWith: canvas)
        wait(for: [settled], timeout: 20)
    }

    private func distance(_ left: GridPoint, _ right: GridPoint) -> Int {
        abs(left.x - right.x) + abs(left.y - right.y)
    }
}
