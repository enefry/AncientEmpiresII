import AECore
import AEContent
import UIKit
import XCTest
@testable import Ancient_Empires_II

@MainActor
final class CampaignStatusUIKitTests: XCTestCase {
    func testTapTurnTitleImmediatelyRestoresInputWithoutApplyingIncomeAgain() throws {
        var snapshot = fixture([.init(id: 0, type: 0, fraction: 1, x: 4, y: 8)])
        snapshot.moneyByFraction = [1: 350, 2: 100]
        snapshot.pendingPresentations = [.init(unitsBefore: snapshot.units, scene:
            .init(.newTurn, durationTicks: 1_000, turn: .init(fraction: 1, income: 50, balance: 350)))]
        let controller = try CampaignMapViewController(session: make(snapshot))
        var latest: CampaignSnapshot?
        controller.onSnapshotChange = { latest = $0 }
        controller.loadViewIfNeeded()
        controller.view.frame = CGRect(x: 0, y: 0, width: 402, height: 874)
        controller.view.layoutIfNeeded()
        controller.viewDidAppear(false)
        defer { controller.viewWillDisappear(false) }
        let banner = try XCTUnwrap(controller.view.subviews.compactMap { $0 as? OriginalTurnBanner }.first)
        banner.layoutIfNeeded()
        let scroll = try XCTUnwrap(button("campaign.cell.0.0", in: controller.view).superview?.superview as? UIScrollView)
        XCTAssertFalse(scroll.isUserInteractionEnabled)
        let titlePoint = CGPoint(x: banner.frame.midX, y: banner.frame.minY + 25)
        XCTAssertTrue(controller.view.hitTest(titlePoint, with: nil) === banner)
        banner.sendActions(for: .touchUpInside)
        XCTAssertNil(banner.superview)
        XCTAssertTrue(scroll.isUserInteractionEnabled)
        XCTAssertTrue(try button("campaign.end-turn", in: controller.view).isEnabled)
        XCTAssertNil(latest?.pendingPresentations)
        XCTAssertEqual(latest?.moneyByFraction, snapshot.moneyByFraction)
        XCTAssertEqual(latest?.units, snapshot.units)
        XCTAssertEqual(latest?.randomState, snapshot.randomState)
        XCTAssertEqual(latest?.currentTurn, snapshot.currentTurn)
    }

    func testRepeatedSkipAndAccessibilityActivationOnlyFinishTheirOwnTurnBanner() async throws {
        var snapshot = fixture([.init(id: 0, type: 0, fraction: 1, x: 4, y: 8)])
        let first = CampaignPresentation(unitsBefore: snapshot.units, scene:
            .init(.newTurn, durationTicks: 1_000, turn: .init(fraction: 2, income: 50, balance: 100)))
        let second = CampaignPresentation(unitsBefore: snapshot.units, scene:
            .init(.newTurn, durationTicks: 1_000, turn: .init(fraction: 1, income: 50, balance: 350)))
        let camera = CampaignPresentation(unitsBefore: snapshot.units, scene: .init(.camera, durationTicks: 1_000))
        snapshot.pendingPresentations = [first, second, camera]
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: make(snapshot))
        var latest: CampaignSnapshot?
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        await settle { controller.view.subviews.contains { $0 is OriginalTurnBanner } }
        let banner = try XCTUnwrap(controller.view.subviews.compactMap { $0 as? OriginalTurnBanner }.first)
        banner.sendActions(for: .touchUpInside)
        XCTAssertEqual(latest?.pendingPresentations, [second, camera])
        banner.sendActions(for: .touchUpInside)
        XCTAssertFalse(banner.accessibilityActivate())
        XCTAssertEqual(latest?.pendingPresentations, [second, camera])
        let next = try XCTUnwrap(controller.view.subviews.compactMap { $0 as? OriginalTurnBanner }.first)
        XCTAssertFalse(next === banner)
        XCTAssertTrue(next.accessibilityActivate())
        XCTAssertEqual(latest?.pendingPresentations, [camera])
        XCTAssertFalse(next.accessibilityActivate())
        XCTAssertEqual(latest?.pendingPresentations, [camera])
        let scroll = try XCTUnwrap(button("campaign.cell.0.0", in: controller.view).superview?.superview as? UIScrollView)
        XCTAssertFalse(scroll.isUserInteractionEnabled)
        XCTAssertEqual(latest?.moneyByFraction, snapshot.moneyByFraction)
        XCTAssertEqual(latest?.units, snapshot.units)
        XCTAssertEqual(latest?.randomState, snapshot.randomState)
    }

    func testPromotionPlaysAfterBothCombatModesAndRestoresWithoutAwardingAgain() async throws {
        let oldAnimation = UserDefaults.standard.object(forKey: GamePreferences.Setting.fightAnimation.key)
        let oldHelp = UserDefaults.standard.object(forKey: GamePreferences.Setting.help.key)
        defer {
            UserDefaults.standard.set(oldAnimation, forKey: GamePreferences.Setting.fightAnimation.key)
            UserDefaults.standard.set(oldHelp, forKey: GamePreferences.Setting.help.key)
        }
        GamePreferences.showsHelp = false
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        for animation in [false, true] {
            GamePreferences.set(.fightAnimation, enabled: animation)
            var snapshot = fixture([
                .init(id: 0, type: 0, fraction: 1, x: 4, y: 8, rank: 1, experience: 100_000),
                .init(id: 1, type: 0, fraction: 2, x: 5, y: 8)
            ])
            snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
            var latest: CampaignSnapshot?
            var controller = try CampaignMapViewController(session: make(snapshot))
            controller.onSnapshotChange = { latest = $0 }
            window.rootViewController = controller
            window.makeKeyAndVisible()
            window.layoutIfNeeded()
            controller.confirmCellDoubleSelection(x: 5, y: 8)
            let committed = try XCTUnwrap(latest)
            XCTAssertEqual(committed.pendingPresentations?.first?.kind, .combat)
            XCTAssertEqual(committed.units.first { $0.id == 0 }?.rank, 2)
            XCTAssertLessThan(try XCTUnwrap(committed.units.first { $0.id == 0 }?.hitPoints), 100)
            await settle({ self.layer("campaign.effect.levelup", in: controller.view.layer) != nil }, timeout: 20)
            let label = try XCTUnwrap(layer("campaign.effect.levelup", in: controller.view.layer))
            XCTAssertGreaterThan(label.bounds.width, 100)
            XCTAssertNotNil(layer("campaign.effect.small_spark", in: controller.view.layer))
            attach(controller.view, name: "LEVEL UP after \(animation ? "side battle" : "map battle")")
            let interrupted = try XCTUnwrap(latest)
            XCTAssertEqual(interrupted.pendingPresentations?.first?.scene?.kind, .levelUp)
            XCTAssertFalse(try button("campaign.end-turn", in: controller.view).isEnabled)
            let restored = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(interrupted))
            controller = try CampaignMapViewController(session: make(restored))
            controller.onSnapshotChange = { latest = $0 }
            window.rootViewController = controller
            window.layoutIfNeeded()
            await settle { latest?.pendingPresentations?.first?.scene?.kind == .rankName }
            func findLabel(_ view: UIView) -> UILabel? {
                if view.accessibilityIdentifier == "campaign.rank-name" { return view as? UILabel }
                return view.subviews.lazy.compactMap(findLabel).first
            }
            let notice = try XCTUnwrap(findLabel(controller.view))
            let promotedName = try XCTUnwrap(GameResources.campaignStoryText(id: 140))
            XCTAssertEqual(notice.text, "PROMOTION:\n" + promotedName)
            attach(controller.view, name: "Restored promotion new rank name")
            await settle { latest?.pendingPresentations == nil }
            XCTAssertNil(latest?.pendingPresentations)
            XCTAssertEqual(latest?.units, committed.units)
            XCTAssertEqual(latest?.randomState, committed.randomState)
            XCTAssertNil(layer("campaign.effect.levelup", in: controller.view.layer))
            XCTAssertTrue(try button("campaign.end-turn", in: controller.view).isEnabled)
            try button("campaign.cell.4.8", in: controller.view).sendActions(for: .touchUpInside)
            try button("campaign.unit-info", in: controller.view).sendActions(for: .touchUpInside)
            let panel = try XCTUnwrap(controller.presentedViewController)
            func containsTitle(_ view: UIView) -> Bool {
                (view as? UILabel)?.text == promotedName || view.subviews.contains(where: containsTitle)
            }
            XCTAssertTrue(containsTitle(panel.view))
            func findStats(_ view: UIView) -> UIView? {
                if view.accessibilityIdentifier == "unit.stats" { return view }
                return view.subviews.lazy.compactMap(findStats).first
            }
            let stats = try XCTUnwrap(findStats(panel.view))
            XCTAssertTrue(stats.accessibilityLabel?.contains("RANK 2") == true)
            attach(panel.view, name: "Promoted rank and EXP statistics")
            controller.dismiss(animated: false)
        }
    }

    func testDoubleTapConfirmsAttackAndWaitExactlyOnce() throws {
        var attackSnapshot = fixture([
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 10, fraction: 2, x: 5, y: 8),
        ])
        attackSnapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        var attackResult: CampaignSnapshot?
        let attackController = try CampaignMapViewController(session: make(attackSnapshot))
        attackController.onSnapshotChange = { attackResult = $0 }
        attackController.loadViewIfNeeded()

        let cell = try button("campaign.cell.5.8", in: attackController.view)
        let doubleTap = try XCTUnwrap(cell.superview?.gestureRecognizers?.compactMap { $0 as? UITapGestureRecognizer }
            .first(where: { $0.numberOfTapsRequired == 2 }))
        XCTAssertTrue(doubleTap.cancelsTouchesInView)
        XCTAssertFalse(doubleTap.delaysTouchesEnded)

        cell.sendActions(for: .touchUpInside)
        attackController.confirmCellDoubleSelection(x: 5, y: 8)
        let attacked = try XCTUnwrap(attackResult)
        XCTAssertTrue(try XCTUnwrap(attacked.units.first { $0.id == 0 }).hasActed)
        XCTAssertLessThan(try XCTUnwrap(attacked.units.first { $0.id == 1 }).hitPoints, 100)
        XCTAssertEqual(attacked.pendingPresentations?.first?.kind, .combat)
        attackController.confirmCellDoubleSelection(x: 5, y: 8)
        XCTAssertEqual(attackResult, attacked)

        var waitSnapshot = fixture([.init(id: 0, type: 0, fraction: 1, x: 4, y: 8)])
        waitSnapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        var waitResult: CampaignSnapshot?
        let waitController = try CampaignMapViewController(session: make(waitSnapshot))
        waitController.onSnapshotChange = { waitResult = $0 }
        waitController.loadViewIfNeeded()
        try button("campaign.cell.4.8", in: waitController.view).sendActions(for: .touchUpInside)
        waitController.confirmCellDoubleSelection(x: 4, y: 8)
        let waited = try XCTUnwrap(waitResult)
        XCTAssertTrue(try XCTUnwrap(waited.units.first { $0.id == 0 }).hasActed)
        XCTAssertNil(waited.selection)
        XCTAssertEqual(waited.lastFinishedMoveUnitID, 0)
        waitController.confirmCellDoubleSelection(x: 4, y: 8)
        XCTAssertEqual(waitResult, waited)
    }

    func testDoubleTapDoesNotTurnAMovementPreviewIntoWait() throws {
        var snapshot = fixture([.init(id: 0, type: 0, fraction: 1, x: 4, y: 8)])
        snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        var latest: CampaignSnapshot?
        let controller = try CampaignMapViewController(session: make(snapshot))
        controller.onSnapshotChange = { latest = $0 }
        controller.loadViewIfNeeded()

        try button("campaign.cell.4.7", in: controller.view).sendActions(for: .touchUpInside)
        controller.confirmCellDoubleSelection(x: 4, y: 8)

        XCTAssertFalse(try XCTUnwrap(latest?.units.first { $0.id == 0 }).hasActed)
        XCTAssertEqual(latest?.selection?.unitID, 0)
    }

    func testTabletBattlefieldFillsPlayableViewportWithoutElasticBlackEdges() throws {
        let controller = try CampaignMapViewController(session: make(fixture([
            .init(id: 0, type: 0, fraction: 1, x: 4, y: 8),
        ])))
        controller.loadViewIfNeeded()
        controller.view.frame = CGRect(x: 0, y: 0, width: 834, height: 1_194)
        controller.view.setNeedsLayout()
        controller.view.layoutIfNeeded()

        let cell = try button("campaign.cell.0.0", in: controller.view)
        let canvas = try XCTUnwrap(cell.superview)
        let scrollView = try XCTUnwrap(canvas.superview as? UIScrollView)
        XCTAssertGreaterThan(cell.bounds.width, 60)
        XCTAssertGreaterThanOrEqual(canvas.bounds.width, scrollView.bounds.width - 0.5)
        XCTAssertGreaterThanOrEqual(canvas.bounds.height, scrollView.bounds.height * 0.85 - 0.5)
        XCTAssertFalse(scrollView.bounces)
        XCTAssertFalse(scrollView.bouncesZoom)
        attach(controller.view, name: "Tablet battlefield fills the playable viewport")
    }

    func testTempleRaidOpeningShowsGuardHitAndDisappearanceEffects() async throws {
        let map = try GameResources.missionZero()
        var snapshot = CampaignSnapshot(
            mapID: map.id,
            units: map.units.map {
                CampaignUnitState(id: $0.index, type: $0.type, fraction: $0.fraction, x: $0.x, y: $0.y)
            },
            phase: .opening,
            randomState: JavaRandom(seed: 7).state,
            m0ScriptState: 14
        )
        snapshot.storyMessages = [221, 222, 223, 224]
        snapshot.storyIndex = 0
        snapshot.storyMode = .opening
        snapshot.storyPresentationsEnabled = true

        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        var latest: CampaignSnapshot?
        let controller = try CampaignMapViewController(session: make(snapshot))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()

        try button("campaign.story.continue", in: controller.view).sendActions(for: .touchUpInside)
        await settle { self.layer("campaign.effect.redspark", in: controller.view.layer) != nil }
        let guardSprite = try XCTUnwrap(layer("campaign.unit.3.sprite", in: controller.view.layer))
        XCTAssertNotNil(guardSprite.animation(forKey: "story-hit"))
        attach(controller.view, name: "Temple guard hit after the Crystal of Courage report")

        await settle { latest?.currentStoryMessageID == 222 && latest?.pendingPresentations == nil }
        try button("campaign.story.continue", in: controller.view).sendActions(for: .touchUpInside)
        await settle {
            self.layer("campaign.effect.spark", in: controller.view.layer) != nil
                && self.layer("campaign.effect.smoke", in: controller.view.layer) != nil
        }
        XCTAssertTrue(try XCTUnwrap(layer("campaign.unit.3", in: controller.view.layer)).isHidden)
        attach(controller.view, name: "Temple guard disappears in spark and smoke")

        await settle({ latest?.currentStoryMessageID == 223 && latest?.pendingPresentations == nil }, timeout: 5)
        XCTAssertEqual(latest?.units.contains { $0.id == 3 }, false)
    }

    func testBottomRowRemainsHittableAboveControlsAfterScrollingAndRelayout() throws {
        let map = try GameResources.missionZero()
        for size in [CGSize(width: 402, height: 874), CGSize(width: 834, height: 1_194), CGSize(width: 402, height: 1_600)] {
            for zoom: CGFloat in [1, 2] {
                for selected in [false, true] {
                    var snapshot = fixture([.init(id: 0, type: 0, fraction: 1, x: 4, y: 8)])
                    if selected { snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true) }
                    let controller = try CampaignMapViewController(session: make(snapshot))
                    controller.loadViewIfNeeded()
                    controller.view.frame = CGRect(origin: .zero, size: size)
                    controller.view.setNeedsLayout()
                    controller.view.layoutIfNeeded()

                    let cell = try button("campaign.cell.0.\(map.height - 1)", in: controller.view)
                    let canvas = try XCTUnwrap(cell.superview)
                    let scroll = try XCTUnwrap(canvas.superview as? UIScrollView)
                    scroll.setZoomScale(zoom, animated: false)
                    controller.view.layoutIfNeeded()
                    let context = "size=\(size), zoom=\(zoom), selected=\(selected)"
                    let maximumY = max(-scroll.contentInset.top, scroll.contentSize.height - scroll.bounds.height + scroll.contentInset.bottom)
                    scroll.setContentOffset(CGPoint(x: -scroll.contentInset.left, y: maximumY), animated: false)
                    controller.view.setNeedsLayout()
                    controller.view.layoutIfNeeded()
                    XCTAssertEqual(scroll.contentOffset.y, maximumY, accuracy: 0.5, context)

                    let cellFrame = cell.convert(cell.bounds, to: controller.view)
                    let controls = selected
                        ? try XCTUnwrap(button("campaign.unit-info", in: controller.view).superview)
                        : try XCTUnwrap(button("campaign.end-turn", in: controller.view).superview)
                    // The status panel is above End Turn; the action menu is above both.
                    let status = controller.view.subviews.first { $0.subviews.contains { $0.accessibilityIdentifier == "map.cell.inspection" } }
                    let controlsTop = min(controls.frame.minY, try XCTUnwrap(status).frame.minY)
                    XCTAssertLessThanOrEqual(cellFrame.maxY + cellFrame.height, controlsTop + 0.5, context)
                    XCTAssertTrue(controller.view.hitTest(CGPoint(x: cellFrame.midX, y: cellFrame.midY), with: nil) === cell, context)
                }
            }
        }
    }

    func testRaiseMenuCancelConfirmAndRestoreKeepLargeCellsAndLingeringSparks() async throws {
        var grave = CampaignUnitState(id: 1, type: 0, fraction: 2, x: 5, y: 8, hitPoints: 0)
        grave.corpseTransitionsRemaining = 3
        var snapshot = fixture([.init(id: 0, type: 3, fraction: 1, x: 4, y: 8), grave])
        snapshot.selection = CampaignSelection(unitID: 0, rangeReady: true)
        var latest: CampaignSnapshot?
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        var controller = try CampaignMapViewController(session: make(snapshot))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        XCTAssertEqual(try button("campaign.cell.5.8", in: controller.view).accessibilityValue, GameLocalization.text("Tombstone"))
        let tombstone = try XCTUnwrap(layer("campaign.unit.1", in: controller.view.layer))
        XCTAssertNil(tombstone.animation(forKey: "idle"))
        XCTAssertTrue(tombstone.sublayers?.isEmpty ?? true)
        XCTAssertFalse(try button("campaign.raise", in: controller.view).isHidden)
        attach(controller.view, name: "Sorceress RAISE and original tombstone")
        try button("campaign.raise", in: controller.view).sendActions(for: .touchUpInside)
        XCTAssertEqual(try button("campaign.confirm", in: controller.view).title(for: .normal), GameLocalization.text("story.69"))
        let cursor = try XCTUnwrap(layer("campaign.cursor", in: controller.view.layer))
        XCTAssertEqual((cursor.animation(forKey: "cursor") as? CAKeyframeAnimation)?.values?.count, 2)
        try button("campaign.cancel", in: controller.view).sendActions(for: .touchUpInside)
        XCTAssertFalse(try button("campaign.raise", in: controller.view).isHidden)
        XCTAssertEqual(latest?.units.filter { $0.type == 10 }.count, 0)
        try button("campaign.raise", in: controller.view).sendActions(for: .touchUpInside)
        try button("campaign.confirm", in: controller.view).sendActions(for: .touchUpInside)
        let committed = try XCTUnwrap(latest)
        XCTAssertEqual(committed.pendingPresentations?.first?.scene?.kind, .summoning)
        XCTAssertEqual(layers("campaign.effect.spark", in: controller.view.layer).count, 4)
        let skeleton = try XCTUnwrap(committed.units.first { $0.type == 10 })
        XCTAssertTrue(skeleton.hasActed)
        XCTAssertNil(layer("campaign.unit.\(skeleton.id)", in: controller.view.layer))
        let resumed = try JSONDecoder().decode(CampaignSnapshot.self, from: JSONEncoder().encode(committed))
        controller = try CampaignMapViewController(session: make(resumed))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.layoutIfNeeded()
        await settle { latest?.pendingPresentations == nil }
        XCTAssertNil(latest?.pendingPresentations)
        XCTAssertEqual(latest?.units, committed.units)
        XCTAssertEqual(latest?.randomState, committed.randomState)
        XCTAssertEqual(latest?.moneyByFraction, committed.moneyByFraction)
        XCTAssertNil(layer("campaign.unit.1", in: controller.view.layer))
        let raised = try XCTUnwrap(layer("campaign.unit.\(skeleton.id)", in: controller.view.layer))
        let raisedSprite = try XCTUnwrap(raised.sublayers?.first { $0.name == "campaign.unit.\(skeleton.id).sprite" })
        XCTAssertNil(raisedSprite.animation(forKey: "idle"))
        let sparks = layers("campaign.effect.spark", in: controller.view.layer)
        XCTAssertEqual(sparks.count, 4)
        XCTAssertTrue(sparks.allSatisfy { $0.animation(forKey: "original-effect") != nil })
        XCTAssertEqual(try button("campaign.cell.5.8", in: controller.view).bounds.width, 60, accuracy: 0.5)
        attach(controller.view, name: "Raised grey skeleton with sparks continuing after eight ticks")
        await settle { sparks.allSatisfy { $0.animation(forKey: "original-effect") == nil } }
        XCTAssertTrue(sparks.allSatisfy { $0.animation(forKey: "original-effect") == nil })
        XCTAssertEqual(latest?.units, committed.units)
        attach(controller.view, name: "Summoning complete with grey skeleton and large cells")
    }

    func testDeathFinishesWithTombstoneAndSmokeAfterRestoration() async throws {
        var game = try make(fixture([
            .init(id: 0, type: 9, fraction: 1, x: 4, y: 8),
            .init(id: 1, type: 0, fraction: 2, x: 5, y: 8, hitPoints: 1)
        ]))
        _ = try game.attack(unitID: 0, targetID: 1, recordPresentation: true)
        game.completePresentation() // Isolate the post-combat death phase.
        XCTAssertEqual(game.pendingPresentation?.scene?.kind, .death)
        let committed = game.snapshot
        var latest: CampaignSnapshot?
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: make(committed))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        XCTAssertNotNil(layer("campaign.unit.1", in: controller.view.layer))
        await settle { latest?.pendingPresentations == nil }
        XCTAssertNil(latest?.pendingPresentations)
        XCTAssertEqual(latest?.units, committed.units)
        XCTAssertEqual(try button("campaign.cell.5.8", in: controller.view).accessibilityValue, GameLocalization.text("Tombstone"))
        XCTAssertNotNil(layer("campaign.effect.smoke", in: controller.view.layer)?.animation(forKey: "original-effect"))
        try await Task.sleep(for: .milliseconds(132))
        attach(controller.view, name: "Original death smoke and persistent grave")
    }

    func testStatusStatsAndZeroDelayParticlesAtPhoneAndTabletWidths() async throws {
        var unit = CampaignUnitState(id: 0, type: 0, fraction: 1, x: 4, y: 8)
        unit.effects = 3
        unit.poisonSourceFraction = 2
        let profile = try XCTUnwrap(GameResources.campaignUnitProfiles()[0])
        let stats = try OriginalUnitStatsView(unit: unit, profile: profile, terrainDefense: 0)
        XCTAssertTrue(stats.accessibilityLabel?.contains("ATTACK 50–55") == true)
        XCTAssertTrue(stats.accessibilityLabel?.contains("DEFENCE -5") == true)
        for width in [CGFloat(402), 768] {
            let view = OriginalFrameView()
            view.frame = CGRect(x: 0, y: 0, width: width, height: 360)
            stats.frame = CGRect(x: 18, y: 12, width: width - 36, height: 235)
            view.addSubview(stats)
            let header = try OriginalCombatHeaderView(attacker: unit,
                defender: .init(id: 1, type: 6, fraction: 2, x: 5, y: 8), attackerTerrain: 0, defenderTerrain: 0)
            header.frame = CGRect(x: 0, y: 260, width: width, height: 72)
            view.addSubview(header)
            attach(view, name: "Poison and blessing original status icons at \(Int(width))pt")
        }
        var snapshot = fixture([unit])
        snapshot.pendingPresentations = [.init(unitsBefore: snapshot.units, scene: .init(.poison, unitID: 0)),
            .init(unitsBefore: snapshot.units, scene: .init(.blessing, affectedUnitIDs: [0]))]
        var latest: CampaignSnapshot?
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.first as? UIWindowScene)
        let originalKey = scene.keyWindow
        let window = UIWindow(windowScene: scene)
        defer { window.isHidden = true; originalKey?.makeKey() }
        let controller = try CampaignMapViewController(session: make(snapshot))
        controller.onSnapshotChange = { latest = $0 }
        window.rootViewController = controller
        window.makeKeyAndVisible()
        window.layoutIfNeeded()
        await settle { latest != nil && latest?.pendingPresentations == nil }
        XCTAssertNil(latest?.pendingPresentations)
        XCTAssertEqual(latest?.units, snapshot.units)
        XCTAssertEqual(layers("campaign.effect.spark", in: controller.view.layer).count, 2)
        XCTAssertNotNil(layer("campaign.effect.status", in: controller.view.layer))
    }

    private func fixture(_ units: [CampaignUnitState]) -> CampaignSnapshot {
        var snapshot = CampaignSnapshot(mapID: "m0", units: units + [
            .init(id: 10, type: 9, fraction: 1, x: 0, y: 11),
            .init(id: 11, type: 11, fraction: 2, x: 11, y: 0)
        ], randomState: JavaRandom(seed: 42).state, m0ScriptState: 22)
        snapshot.tileOverrides = [100: 18, 101: 18]
        return snapshot
    }
    private func make(_ snapshot: CampaignSnapshot) throws -> CampaignSession {
        try CampaignSession(map: GameResources.missionZero(), profiles: GameResources.campaignUnitProfiles(), rules: GameResources.campaignRuleset(), snapshot: snapshot)
    }
    private func button(_ id: String, in root: UIView) throws -> UIButton {
        func find(_ view: UIView) -> UIButton? {
            if view.accessibilityIdentifier == id { return view as? UIButton }
            return view.subviews.lazy.compactMap(find).first
        }
        return try XCTUnwrap(find(root), id)
    }
    private func layer(_ name: String, in root: CALayer) -> CALayer? { layers(name, in: root).first }
    private func layers(_ name: String, in root: CALayer) -> [CALayer] {
        (root.name == name ? [root] : []) + (root.sublayers ?? []).flatMap { layers(name, in: $0) }
    }
    private func settle(_ predicate: () -> Bool, timeout: TimeInterval = 5) async {
        let deadline = Date().addingTimeInterval(timeout)
        while !predicate(), Date() < deadline { try? await Task.sleep(for: .milliseconds(15)) }
    }
    private func attach(_ view: UIView, name: String) {
        view.layoutIfNeeded()
        let image = UIGraphicsImageRenderer(bounds: view.bounds).image { context in
            if view.window == nil { view.layer.render(in: context.cgContext) }
            else { XCTAssertTrue(view.drawHierarchy(in: view.bounds, afterScreenUpdates: true)) }
        }
        let attachment = XCTAttachment(image: image)
        attachment.name = name
        attachment.lifetime = .keepAlways
        add(attachment)
    }
}
