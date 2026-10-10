import AECore
import AEContent
import CoreGraphics
import UIKit

@MainActor
final class CampaignMapViewController: UIViewController {
    private static let terrainNamesByID = [
        0: GameLocalization.text("Road"), 1: GameLocalization.text("Grassland"), 2: GameLocalization.text("Forest"), 3: GameLocalization.text("Hills"), 4: GameLocalization.text("Mountains"), 5: GameLocalization.text("Water"),
        6: GameLocalization.text("Bridge"), 7: GameLocalization.text("Town"), 8: GameLocalization.text("Town"), 9: GameLocalization.text("Town"), 10: GameLocalization.text("Lava"),
    ]

    var onExit: (() -> Void)?
    var onMissionComplete: (() -> Void)?
    var onSnapshotChange: ((CampaignSnapshot) -> Void)?

    private var session: CampaignSession
    private let profiles: [Int: CampaignUnitProfile]
    private let unitDefinitions: [Int: UnitDefinition]
    private let terrainNameByTileID: [Int: String]
    private let mapCanvas: CampaignMapCanvasView
    private let mapContainer = UIView()
    private let mapAccessibilitySummary = UIView()
    private let mapScrollView = UIScrollView()
    private let storyDrape = UIView()
    private let actionsStack = UIStackView()
    private let turnLabel = UILabel()
    private let objectiveLabel = UILabel()
    private let cellInfo = UILabel()
    private let storyPanel = OriginalDialogView()
    private var storyLabel: UILabel { storyPanel.textLabel }
    private var storyButton: UIButton { storyPanel.continueButton }
    private let endUnitButton = UIButton(type: .system)
    private let endTurnButton = UIButton(type: .system)
    private var selectedCell: GridPoint?
    private var skirmishTurnFocus: GridPoint?
    private var buildingBanner: OriginalFrameView?
    private var selectedUnitID: Int?
    private var previewRoute: [GridPoint] = []
    private var pendingTargetID: Int?
    private var pendingCorpseID: Int?
    private var isPresentingAction = false
    private var turnBanner: OriginalTurnBanner?
    private var resultController: OriginalResultViewController?
    private var didCompleteFinalMission = false
    private var sceneClock: CADisplayLink?
    private var scenePreviousTime: CFTimeInterval = 0
    private var sceneAccumulator: CFTimeInterval = 0
    private var sceneTick = 0
    private var sceneCameraArrivalTick: Int?
    private lazy var raiseButton = OriginalGameButton(GameLocalization.text("story.69"), identifier: "campaign.raise") { [weak self] in self?.chooseRaiseTarget() }
    private lazy var confirmButton = OriginalGameButton(GameLocalization.text("story.61"), identifier: "campaign.confirm") { [weak self] in self?.confirmOrder() }
    private lazy var cancelButton = OriginalGameButton(GameLocalization.text("story.65"), identifier: "campaign.cancel") { [weak self] in self?.cancelOrder() }
    private let hudFrame = OriginalFrameView()
    private let headerFrame = OriginalFrameView()
    private let terrainImage = OriginalArt.pixelView()
    private let goldLabel = OriginalArt.label("0", size: 18)
    private var combatHeader: OriginalCombatHeaderView?
    private let actionMenu = OriginalActionMenuView()
    private lazy var attackMenuButton: UIButton = {
        let button = UIButton(type: .custom)
        button.accessibilityIdentifier = "campaign.attack-menu"
        button.accessibilityLabel = GameLocalization.text("story.62")
        button.addAction(UIAction { [weak self] _ in self?.chooseAttackTarget() }, for: .touchUpInside)
        return button
    }()
    private lazy var infoMenuButton: UIButton = {
        let button = UIButton(type: .custom)
        button.accessibilityIdentifier = "campaign.unit-info"
        button.accessibilityLabel = GameLocalization.text("UNIT INFORMATION")
        button.addAction(UIAction { [weak self] _ in self?.showUnitDetails() }, for: .touchUpInside)
        return button
    }()
    private lazy var buildingButton: UIButton = {
        let button = UIButton(type: .custom)
        button.accessibilityIdentifier = "campaign.building-action"
        button.addAction(UIAction { [weak self] _ in self?.performBuildingAction() }, for: .touchUpInside)
        return button
    }()
    private lazy var buyButton: UIButton = {
        let button = UIButton(type: .custom)
        button.accessibilityIdentifier = "campaign.buy"
        button.accessibilityLabel = GameLocalization.text("story.63")
        button.addAction(UIAction { [weak self] _ in self?.showRecruitment() }, for: .touchUpInside)
        return button
    }()
    private var storyPanelHeightConstraint: NSLayoutConstraint!

    override var prefersStatusBarHidden: Bool { true }

    init(session: CampaignSession) throws {
        var activeSession = session
        activeSession.enableStoryPresentations()
        self.session = activeSession
        if session.isSkirmish {
            skirmishTurnFocus = session.snapshot.units.last { $0.fraction == session.snapshot.activeFraction && $0.type == 9 && $0.isAlive }?.position
                ?? session.map.buildings.first { $0.fraction == session.snapshot.activeFraction && $0.terrain == 9 }.map { GridPoint(x: $0.x, y: $0.y) }
        }
        selectedUnitID = session.snapshot.selection?.unitID
        selectedCell = selectedUnitID.flatMap(session.unit(id:))?.position
        profiles = try GameResources.campaignUnitProfiles()
        let definitions = try GameResources.unitDefinitions()
        unitDefinitions = Dictionary(uniqueKeysWithValues: definitions.map { ($0.id, $0) })
        let terrain = try GameResources.terrainCatalog()
        terrainNameByTileID = Dictionary(uniqueKeysWithValues: terrain.tiles.map {
            ($0.id, Self.terrainNamesByID[$0.terrain] ?? GameLocalization.text("Unknown Terrain"))
        })
        mapCanvas = try CampaignMapCanvasView(
            map: session.map,
            terrain: terrain,
            unitDefinitions: definitions
        )
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        nil
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = PixelPalette.ink
        configureHeader()
        configureActions()
        configureStatus()
        configureMap()
        configureStoryPanel()
        // Commit the opening queue before the first frame/AX snapshot, so an old
        // manual-start button cannot flash between the chapter and the arrival.
        if session.snapshot.phase == .opening, session.snapshot.storyIndex < 0, session.pendingPresentation == nil {
            try? session.advanceStory()
        }
        refresh()
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        guard mapContainer.bounds.width > 0 else { return }
        mapContainer.layoutIfNeeded()

        let widthBasedTileSize = mapContainer.bounds.width / 10
        let heightBasedTileSize = mapContainer.bounds.height * 0.85 / CGFloat(session.map.height)
        let usesTabletViewport = mapContainer.bounds.width >= 600
        let minimumTouchTileSize: CGFloat = usesTabletViewport ? 44 : 60
        var tileSize = max(minimumTouchTileSize, max(widthBasedTileSize, heightBasedTileSize))
        if !usesTabletViewport { tileSize = min(60, tileSize) }
        let displayScale = max(1, view.window?.screen.scale ?? traitCollection.displayScale)
        tileSize = ceil(tileSize * displayScale) / displayScale
        if abs(mapCanvas.tileSize - tileSize) > 0.5 {
            mapScrollView.setZoomScale(1, animated: false)
            mapCanvas.configureTileSize(tileSize)
            let contentSize = mapCanvas.contentSize
            mapCanvas.frame = CGRect(origin: .zero, size: contentSize)
            mapScrollView.contentSize = contentSize
            mapScrollView.setContentOffset(.zero, animated: false)
        }
        let menuWidth = min(420, view.bounds.width - 8)
        let menuY = max(headerFrame.frame.maxY + 4, hudFrame.frame.minY - 52)
        actionMenu.frame = CGRect(x: view.bounds.width - menuWidth, y: menuY, width: menuWidth, height: 48)
        updateMapScrollInsets()
        if session.isSkirmish, session.isInputEnabled, let focus = skirmishTurnFocus {
            // Recenter after NEW TURN releases its full-screen viewport and the
            // action bars return, so edge commanders stay above the HUD.
            mapScrollView.setContentOffset(cameraOffset(for: focus), animated: false)
            skirmishTurnFocus = nil
        }
        turnBanner?.frame = CGRect(x: 0, y: view.bounds.midY - 54, width: view.bounds.width, height: 108)
        buildingBanner?.frame = CGRect(x: 0, y: view.bounds.midY - 36, width: view.bounds.width, height: 72)
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        playNextPresentation()
        presentResultIfNeeded()
    }

    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        if sceneClock != nil {
            sceneClock?.invalidate()
            sceneClock = nil
            isPresentingAction = false
        }
    }

    func showSaveFailure() {
        objectiveLabel.text = GameLocalization.text("Autosave failed · The battle is still running")
        objectiveLabel.textColor = PixelPalette.crimson
    }

    private func configureHeader() {
        headerFrame.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(headerFrame)
        let back = OriginalGameButton(GameLocalization.text("MENU"), identifier: "campaign.back") { [weak self] in self?.showPauseMenu() }
        back.translatesAutoresizingMaskIntoConstraints = false
        headerFrame.addSubview(back)
        turnLabel.font = .monospacedSystemFont(ofSize: 14, weight: .bold)
        turnLabel.textColor = .white
        turnLabel.accessibilityIdentifier = "campaign.turn.status"
        turnLabel.translatesAutoresizingMaskIntoConstraints = false
        headerFrame.addSubview(turnLabel)
        NSLayoutConstraint.activate([
            headerFrame.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
            headerFrame.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
            headerFrame.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            headerFrame.heightAnchor.constraint(equalToConstant: 52),
            back.leadingAnchor.constraint(equalTo: headerFrame.leadingAnchor, constant: 4),
            back.topAnchor.constraint(equalTo: headerFrame.topAnchor, constant: 4),
            back.widthAnchor.constraint(equalToConstant: 86),
            back.heightAnchor.constraint(equalToConstant: 44),
            turnLabel.leadingAnchor.constraint(equalTo: back.trailingAnchor, constant: 12),
            turnLabel.trailingAnchor.constraint(equalTo: headerFrame.trailingAnchor, constant: -12),
            turnLabel.centerYAnchor.constraint(equalTo: headerFrame.centerYAnchor),
        ])
    }

    private func configureMap() {
        mapContainer.backgroundColor = .black
        mapContainer.clipsToBounds = true
        mapContainer.translatesAutoresizingMaskIntoConstraints = false
        mapContainer.isAccessibilityElement = false
        view.insertSubview(mapContainer, at: 0)

        mapCanvas.translatesAutoresizingMaskIntoConstraints = true
        mapCanvas.accessibilityElementsHidden = false
        mapAccessibilitySummary.isAccessibilityElement = true
        mapAccessibilitySummary.isUserInteractionEnabled = false
        mapAccessibilitySummary.accessibilityIdentifier = "campaign.map.canvas"
        mapAccessibilitySummary.accessibilityLabel = GameLocalization.text("Campaign battlefield")
        mapAccessibilitySummary.accessibilityHint = GameLocalization.text("Drag to explore, pinch to zoom, and tap a cell to inspect or command units.")
        mapAccessibilitySummary.translatesAutoresizingMaskIntoConstraints = false
        mapContainer.addSubview(mapAccessibilitySummary)
        mapScrollView.delegate = self
        mapScrollView.minimumZoomScale = 1
        mapScrollView.maximumZoomScale = 2
        mapScrollView.bouncesZoom = false
        mapScrollView.bounces = false
        mapScrollView.showsHorizontalScrollIndicator = false
        mapScrollView.showsVerticalScrollIndicator = false
        mapScrollView.contentInsetAdjustmentBehavior = .never
        mapScrollView.translatesAutoresizingMaskIntoConstraints = false
        mapContainer.addSubview(mapScrollView)
        mapScrollView.addSubview(mapCanvas)
        storyDrape.backgroundColor = .black
        storyDrape.alpha = 0
        storyDrape.isUserInteractionEnabled = false
        storyDrape.accessibilityIdentifier = "campaign.story.drape"
        storyDrape.translatesAutoresizingMaskIntoConstraints = false
        mapContainer.addSubview(storyDrape)
        NSLayoutConstraint.activate([
            mapAccessibilitySummary.leadingAnchor.constraint(equalTo: mapContainer.leadingAnchor),
            mapAccessibilitySummary.trailingAnchor.constraint(equalTo: mapContainer.trailingAnchor),
            mapAccessibilitySummary.topAnchor.constraint(equalTo: mapContainer.topAnchor),
            mapAccessibilitySummary.bottomAnchor.constraint(equalTo: mapContainer.bottomAnchor),
            mapScrollView.leadingAnchor.constraint(equalTo: mapContainer.leadingAnchor),
            mapScrollView.trailingAnchor.constraint(equalTo: mapContainer.trailingAnchor),
            mapScrollView.topAnchor.constraint(equalTo: mapContainer.topAnchor),
            mapScrollView.bottomAnchor.constraint(equalTo: mapContainer.bottomAnchor),
            storyDrape.leadingAnchor.constraint(equalTo: mapContainer.leadingAnchor),
            storyDrape.trailingAnchor.constraint(equalTo: mapContainer.trailingAnchor),
            storyDrape.topAnchor.constraint(equalTo: mapContainer.topAnchor),
            storyDrape.bottomAnchor.constraint(equalTo: mapContainer.bottomAnchor),
        ])
        mapCanvas.onCellSelection = { [weak self] x, y in self?.selectCell(x: x, y: y) }
        mapCanvas.onCellDoubleSelection = { [weak self] x, y in self?.confirmCellDoubleSelection(x: x, y: y) }

        NSLayoutConstraint.activate([
            mapContainer.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            mapContainer.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            mapContainer.topAnchor.constraint(equalTo: view.topAnchor),
            mapContainer.bottomAnchor.constraint(equalTo: view.bottomAnchor),
        ])
        [headerFrame, hudFrame, actionsStack].forEach(view.bringSubviewToFront)
    }

    private func updateMapScrollInsets() {
        let scaledSize = CGSize(
            width: mapCanvas.bounds.width * mapScrollView.zoomScale,
            height: mapCanvas.bounds.height * mapScrollView.zoomScale
        )
        let isResult = session.pendingPresentation == nil && (session.snapshot.phase == .victory || session.snapshot.phase == .defeat)
        let isStory = isResult || session.snapshot.phase == .opening || session.snapshot.phase == .story || session.pendingPresentation?.kind == .scene
        let centeredOverlay = isResult || (session.pendingPresentation?.scene.map {
            [.objective, .newTurn, .healing, .building, .planning, .targeting, .camera, .death, .summoning, .poison, .blessing].contains($0.kind)
        } ?? false)
        let topBar = isStory ? 0 : view.safeAreaInsets.top + 52
        var bottomBar = centeredOverlay ? 0 : view.safeAreaInsets.bottom + (isStory ? 200 : 126)
        if !isStory {
            for overlay in [hudFrame, actionsStack, actionMenu] where !overlay.isHidden && overlay.frame.height > 0 {
                let frame = overlay.convert(overlay.bounds, to: mapScrollView)
                bottomBar = max(bottomBar, mapScrollView.bounds.maxY - frame.minY)
            }
        }
        // Leave one displayed tile of room above the controls at the bottom edge.
        let bottomScrollMargin = isStory ? 0 : mapCanvas.tileSize * mapScrollView.zoomScale
        let availableHeight = max(0, mapScrollView.bounds.height - topBar - bottomBar)
        let verticalMargin = max(0, (availableHeight - scaledSize.height) / 2)
        let inset = UIEdgeInsets(
            top: topBar + verticalMargin,
            left: max(0, (mapScrollView.bounds.width - scaledSize.width) / 2),
            bottom: bottomBar + verticalMargin + bottomScrollMargin,
            right: max(0, (mapScrollView.bounds.width - scaledSize.width) / 2)
        )
        if mapScrollView.contentInset != inset { mapScrollView.contentInset = inset }
        var offset = mapScrollView.contentOffset
        if scaledSize.width <= mapScrollView.bounds.width { offset.x = -inset.left }
        let minimumY = -inset.top
        let maximumY = max(minimumY, scaledSize.height - mapScrollView.bounds.height + inset.bottom)
        offset.y = min(maximumY, max(minimumY, offset.y))
        let currentOffset = mapScrollView.contentOffset
        if abs(currentOffset.x - offset.x) > 0.25 || abs(currentOffset.y - offset.y) > 0.25 {
            mapScrollView.setContentOffset(offset, animated: false)
        }
    }

    private func configureStatus() {
        hudFrame.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(hudFrame)
        cellInfo.font = .monospacedSystemFont(ofSize: 12, weight: .bold)
        cellInfo.textColor = .white
        cellInfo.numberOfLines = 2
        cellInfo.text = GameLocalization.text("Tap a unit to view its stats")
        cellInfo.accessibilityIdentifier = "map.cell.inspection"
        cellInfo.translatesAutoresizingMaskIntoConstraints = false
        hudFrame.addSubview(cellInfo)
        terrainImage.translatesAutoresizingMaskIntoConstraints = false
        hudFrame.addSubview(terrainImage)
        let coin = OriginalArt.pixelView(OriginalArt.frame("hud_icons_2", x: 11, width: 11, height: 11))
        coin.translatesAutoresizingMaskIntoConstraints = false
        hudFrame.addSubview(coin)
        goldLabel.translatesAutoresizingMaskIntoConstraints = false
        hudFrame.addSubview(goldLabel)
        NSLayoutConstraint.activate([
            hudFrame.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
            hudFrame.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
            hudFrame.bottomAnchor.constraint(equalTo: actionsStack.topAnchor, constant: -2),
            hudFrame.heightAnchor.constraint(equalToConstant: 66),
            coin.leadingAnchor.constraint(equalTo: hudFrame.leadingAnchor, constant: 12),
            coin.centerYAnchor.constraint(equalTo: hudFrame.centerYAnchor),
            coin.widthAnchor.constraint(equalToConstant: 20),
            coin.heightAnchor.constraint(equalToConstant: 26),
            goldLabel.leadingAnchor.constraint(equalTo: coin.trailingAnchor, constant: 4),
            goldLabel.centerYAnchor.constraint(equalTo: hudFrame.centerYAnchor),
            goldLabel.widthAnchor.constraint(equalToConstant: 38),
            terrainImage.trailingAnchor.constraint(equalTo: hudFrame.trailingAnchor, constant: -8),
            terrainImage.centerYAnchor.constraint(equalTo: hudFrame.centerYAnchor),
            terrainImage.widthAnchor.constraint(equalToConstant: 48),
            terrainImage.heightAnchor.constraint(equalToConstant: 48),
            cellInfo.leadingAnchor.constraint(equalTo: goldLabel.trailingAnchor, constant: 4),
            cellInfo.trailingAnchor.constraint(equalTo: terrainImage.leadingAnchor, constant: -4),
            cellInfo.centerYAnchor.constraint(equalTo: hudFrame.centerYAnchor),
        ])
        let details = UITapGestureRecognizer(target: self, action: #selector(showUnitDetails))
        hudFrame.addGestureRecognizer(details)
    }

    private func configureStoryPanel() {
        storyPanel.translatesAutoresizingMaskIntoConstraints = false
        storyPanel.onContinue = { [weak self] in self?.continueCampaignFlow() }
        view.addSubview(storyPanel)
        NSLayoutConstraint.activate([
            storyPanel.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
            storyPanel.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
            storyPanel.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
        ])
        storyPanelHeightConstraint = storyPanel.heightAnchor.constraint(equalToConstant: 200)
        storyPanelHeightConstraint.isActive = true
    }

    private func configureActions() {
        endUnitButton.setTitle(GameLocalization.text("End Unit Action"), for: .normal)
        endUnitButton.titleLabel?.font = .systemFont(ofSize: 13, weight: .semibold)
        endUnitButton.setTitleColor(PixelPalette.parchment, for: .normal)
        endUnitButton.backgroundColor = PixelPalette.panel
        endUnitButton.layer.cornerRadius = 4
        endUnitButton.accessibilityIdentifier = "campaign.end-unit"
        endUnitButton.addAction(UIAction { [weak self] _ in self?.endUnitAction() }, for: .touchUpInside)

        endTurnButton.setTitle(GameLocalization.text("End Turn"), for: .normal)
        endTurnButton.titleLabel?.font = .systemFont(ofSize: 13, weight: .bold)
        endTurnButton.setTitleColor(PixelPalette.parchment, for: .normal)
        endTurnButton.backgroundColor = PixelPalette.crimson
        endTurnButton.layer.cornerRadius = 4
        endTurnButton.accessibilityIdentifier = "campaign.end-turn"
        endTurnButton.addAction(UIAction { [weak self] _ in self?.endTurn() }, for: .touchUpInside)

        for button in [endTurnButton] {
            button.backgroundColor = .clear
            button.layer.cornerRadius = 0
            button.setTitleColor(.white, for: .normal)
            let frame = OriginalFrameView()
            frame.isUserInteractionEnabled = false
            frame.translatesAutoresizingMaskIntoConstraints = false
            button.insertSubview(frame, at: 0)
            NSLayoutConstraint.activate([
                frame.leadingAnchor.constraint(equalTo: button.leadingAnchor), frame.trailingAnchor.constraint(equalTo: button.trailingAnchor),
                frame.topAnchor.constraint(equalTo: button.topAnchor), frame.bottomAnchor.constraint(equalTo: button.bottomAnchor),
            ])
        }
        endUnitButton.setTitle(GameLocalization.text("story.64"), for: .normal)
        endTurnButton.setTitle(GameLocalization.text("story.66"), for: .normal)
        actionsStack.addArrangedSubview(cancelButton)
        actionsStack.addArrangedSubview(confirmButton)
        actionsStack.addArrangedSubview(endTurnButton)
        actionsStack.axis = .horizontal
        actionsStack.spacing = 8
        actionsStack.distribution = .fillEqually
        actionsStack.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(actionsStack)
        endUnitButton.accessibilityLabel = GameLocalization.text("story.64")
        view.addSubview(actionMenu)
        NSLayoutConstraint.activate([
            actionsStack.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 4),
            actionsStack.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -4),
            actionsStack.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -10),
            actionsStack.heightAnchor.constraint(equalToConstant: 48),
        ])
    }

    private func selectCell(x: Int, y: Int) {
        guard !isPresentingAction, session.snapshot.pendingCampaignHelpID == nil else { return }
        let point = GridPoint(x: x, y: y)
        guard let tile = session.map.tileID(x: x, y: y) else { return }
        guard session.isInputEnabled else { return }
        let occupants = session.units(at: point)
        if pendingCorpseID != nil, let selectedUnitID {
            if let corpse = session.summonableCorpses(for: selectedUnitID).first(where: { $0.position == point }) {
                pendingCorpseID = corpse.id
                refresh()
            }
            return
        }
        if session.snapshot.recruitingUnitID == nil { selectedCell = point }
        if let id = session.snapshot.recruitingUnitID, let unit = session.unit(id: id), !unit.hasMoved,
           unit.position == point, occupants.allSatisfy({ $0.id == id }) {
            if previewRoute.last == point { confirmOrder(); return }
            previewRoute = [point]
            refresh()
            return
        }
        if let selectedUnitID,
           let target = occupants.first(where: { !session.areAllied($0.fraction, session.snapshot.activeFraction) }),
           session.attackableUnits(for: selectedUnitID).contains(where: { $0.id == target.id }) {
            pendingTargetID = target.id
            pendingCorpseID = nil
            previewRoute = []
            showCellDetails(point: point, tile: tile, unit: target)
            refresh()
            return
        }
        if let selectedUnitID,
           let range = try? session.movementRange(for: selectedUnitID),
           let route = range.route(to: point) {
            if previewRoute.last == point { confirmOrder(); return }
            previewRoute = route
            pendingTargetID = nil
            pendingCorpseID = nil
            showCellDetails(point: point, tile: tile)
            refresh()
            return
        }
        if session.snapshot.recruitingUnitID != nil { return }
        if let friendly = occupants.first(where: { $0.fraction == session.snapshot.activeFraction }) {
            if let committed = session.snapshot.selection, committed.moveOrigin != nil, committed.unitID != friendly.id { return }
            let isCurrentSelection = session.snapshot.selection?.unitID == friendly.id
            selectedUnitID = friendly.id
            previewRoute = []
            pendingTargetID = nil
            pendingCorpseID = nil
            if !friendly.hasActed {
                if !isCurrentSelection { try? session.selectUnit(id: friendly.id) }
            } else {
                session.clearSelection()
            }
            showCellDetails(point: point, tile: tile, unit: friendly)
            refresh()
            if !friendly.hasActed && !friendly.hasMoved && session.snapshot.selection?.rangeReady != true
                && session.snapshot.pendingCampaignHelpID == nil {
                isPresentingAction = true
                updateInputAvailability()
                mapCanvas.revealMovementRange { [weak self] in
                    guard let self else { return }
                    self.isPresentingAction = false
                    self.session.movementRangeDidAppear(for: friendly.id)
                    self.refresh()
                }
            }
            return
        }
        guard session.snapshot.selection?.moveOrigin == nil else { return }
        selectedUnitID = nil
        session.clearSelection()
        previewRoute = []
        pendingTargetID = nil
        pendingCorpseID = nil
        showCellDetails(point: point, tile: tile, unit: occupants.first)
        refresh()
        mapCanvas.selectCell(x: x, y: y)
    }

    func confirmCellDoubleSelection(x: Int, y: Int) {
        guard !isPresentingAction, session.isInputEnabled,
              session.snapshot.pendingCampaignHelpID == nil,
              session.map.tileID(x: x, y: y) != nil,
              let selectedUnitID,
              let selectedUnit = session.unit(id: selectedUnitID),
              !selectedUnit.hasActed,
              pendingCorpseID == nil else { return }

        let point = GridPoint(x: x, y: y)
        if previewRoute.isEmpty,
           let target = session.units(at: point).first(where: { $0.fraction != selectedUnit.fraction }),
           session.attackableUnits(for: selectedUnitID).contains(where: { $0.id == target.id }) {
            pendingTargetID = target.id
            confirmOrder()
            return
        }

        let isStationaryPreview = previewRoute.isEmpty || previewRoute.allSatisfy { $0 == point }
        let deploymentIsReady = session.snapshot.recruitingUnitID == nil
            || (session.snapshot.recruitingUnitID == selectedUnitID && selectedUnit.hasMoved)
        guard pendingTargetID == nil,
              isStationaryPreview,
              deploymentIsReady,
              selectedUnit.position == point else { return }
        previewRoute = []
        endUnitAction()
    }

    private func performBuildingAction() {
        guard let id = selectedUnitID else { return }
        do {
            try session.performBuildingAction(unitID: id, recordPresentation: true)
            selectedUnitID = nil
            previewRoute = []
            pendingTargetID = nil
            pendingCorpseID = nil
            refresh()
        } catch { cellInfo.text = GameLocalization.text("This building action is unavailable here") }
    }

    private func showRecruitment() {
        guard let castle = selectedUnitID.flatMap(session.unit(id:))?.position ?? selectedCell,
              session.canOpenRecruitment(at: castle) else { return }
        let controller = OriginalRecruitmentViewController(options: session.recruitmentOptions(at: castle), money: session.money(for: session.snapshot.activeFraction))
        controller.onBuy = { [weak self, weak controller] option in
            guard let self else { return }
            do {
                let id = try self.session.recruit(type: option.unit.type, commanderID: option.commanderID, at: castle)
                self.selectedUnitID = id
                self.previewRoute = []
                self.pendingTargetID = nil
                self.pendingCorpseID = nil
                self.saveSnapshot()
                controller?.dismiss(animated: false) { self.refresh() }
            } catch { controller?.showUnavailable() }
        }
        controller.modalPresentationStyle = .overFullScreen
        present(controller, animated: false)
    }

    private func confirmOrder() {
        guard !isPresentingAction, session.isInputEnabled, let id = selectedUnitID else { return }
        if let targetID = pendingTargetID { attack(targetID: targetID); return }
        if let corpseID = pendingCorpseID {
            do {
                try session.raiseSkeleton(unitID: id, corpseID: corpseID, recordPresentation: true)
                selectedUnitID = nil
                pendingCorpseID = nil
                previewRoute = []
                refresh()
            } catch { cellInfo.text = GameLocalization.text("Cannot raise a skeleton here") }
            return
        }
        guard let destination = previewRoute.last,
              let unit = session.unit(id: id) else { return }
        let route = previewRoute
        isPresentingAction = true
        previewRoute = []
        refresh()
        mapCanvas.animateMovement(unitID: id, from: unit.position, route: route) { [weak self] in
            guard let self else { return }
            do { try self.session.move(unitID: id, to: destination) }
            catch { self.cellInfo.text = GameLocalization.text("This cell cannot be reached") }
            self.isPresentingAction = false
            self.selectedCell = destination
            if let tile = self.session.map.tileID(x: destination.x, y: destination.y) {
                self.showCellDetails(point: destination, tile: tile, unit: self.session.unit(id: id))
            }
            self.refresh()
        }
    }

    private func cancelOrder() {
        guard !isPresentingAction else { return }
        if pendingTargetID != nil || pendingCorpseID != nil || !previewRoute.isEmpty {
            pendingTargetID = nil
            pendingCorpseID = nil
            previewRoute = []
        } else if session.snapshot.selection?.moveOrigin != nil {
            try? session.cancelMove()
        } else if session.snapshot.recruitingUnitID == nil {
            selectedUnitID = nil
            session.clearSelection()
        }
        refresh()
    }

    private func updateInputAvailability() {
        let enabled = session.isInputEnabled && !isPresentingAction
        let requiresPlacement = session.snapshot.recruitingUnitID.flatMap(session.unit(id:)).map { !$0.hasMoved } == true
        endUnitButton.isEnabled = enabled && !requiresPlacement && selectedUnitID.flatMap(session.unit(id:)).map { !$0.hasActed } == true
        endTurnButton.isEnabled = enabled && session.snapshot.selection?.moveOrigin == nil && session.snapshot.recruitingUnitID == nil
        confirmButton.isEnabled = enabled
        cancelButton.isEnabled = enabled && (!requiresPlacement || !previewRoute.isEmpty || pendingTargetID != nil || pendingCorpseID != nil)
        buildingButton.isEnabled = enabled
        buyButton.isEnabled = enabled
        raiseButton.isEnabled = enabled
        attackMenuButton.isEnabled = enabled
        infoMenuButton.isEnabled = !isPresentingAction
    }

    private func chooseAttackTarget() {
        guard let id = selectedUnitID, let target = session.attackableUnits(for: id).first else { return }
        pendingTargetID = target.id
        pendingCorpseID = nil
        previewRoute = []
        if let tile = session.map.tileID(x: target.x, y: target.y) { showCellDetails(point: target.position, tile: tile, unit: target) }
        refresh()
    }

    private func chooseRaiseTarget() {
        guard let id = selectedUnitID, let corpse = session.summonableCorpses(for: id).first else { return }
        pendingCorpseID = corpse.id
        pendingTargetID = nil
        previewRoute = []
        refresh()
    }

    private func showCellDetails(point: GridPoint, tile: Int, unit: CampaignUnitState? = nil) {
        if let definition = try? GameResources.terrainCatalog().tiles.first(where: { $0.id == tile }),
           let image = OriginalArt.resourceImage(at: definition.image) { terrainImage.image = image }
        let terrainName = terrainNameByTileID[tile] ?? GameLocalization.text("Unknown Terrain")
        let coordinate = String(format: GameLocalization.text("Cell %02d,%02d  ·  %@"), point.x + 1, point.y + 1, terrainName)
        guard let unit else {
            cellInfo.text = coordinate
            mapAccessibilitySummary.accessibilityValue = coordinate
            return
        }
        let name = unitName(for: unit)
        let line = GameLocalization.format("%@  HP %@  R%@  ·  %@", String(describing: name), String(describing: unit.hitPoints), String(describing: unit.rank), String(describing: SkirmishSetupViewController.factionName(unit.fraction)))
        cellInfo.text = "\(coordinate)\n\(line)"
        mapAccessibilitySummary.accessibilityValue = line
    }

    private func attack(targetID: Int) {
        guard let selectedUnitID else { return }
        do {
            let result = try session.attack(unitID: selectedUnitID, targetID: targetID, recordPresentation: true)
            let counter = result.counterDamage == 0 ? "" : GameLocalization.format(" · Counterattack %@", String(describing: result.counterDamage))
            cellInfo.text = GameLocalization.format("Dealt %@ damage%@", String(describing: result.damage), String(describing: counter))
            self.selectedUnitID = nil
            pendingTargetID = nil
            pendingCorpseID = nil
            previewRoute = []
            saveSnapshot()
            refresh()
        } catch {
            cellInfo.text = GameLocalization.text("The target is out of attack range")
        }
    }

    private func endUnitAction() {
        guard let selectedUnitID else { return }
        do {
            try session.endUnitAction(unitID: selectedUnitID, recordPresentation: true)
            self.selectedUnitID = nil
            cellInfo.text = GameLocalization.text("Unit action ended")
            saveSnapshot()
            refresh()
        } catch {
            cellInfo.text = GameLocalization.text("Cannot end this unit's action")
        }
    }

    private func endTurn() {
        do {
            selectedUnitID = nil
            try session.endPlayerTurn(recordPresentation: true)
            cellInfo.text = session.isSkirmish ? GameLocalization.text("Turn passed to the next faction") : (session.snapshot.phase == .story ? GameLocalization.text("Enemy reinforcements have arrived") : GameLocalization.text("Red's turn ended"))
            saveSnapshot()
            refresh()
        } catch {
            cellInfo.text = GameLocalization.text("Cannot end the current turn")
        }
    }

    private func continueCampaignFlow() {
        if session.snapshot.phase == .victory || session.snapshot.phase == .defeat {
            onExit?()
            return
        }
        let dismissingTutorialHelp = session.snapshot.pendingCampaignHelpID != nil
        if dismissingTutorialHelp {
            session.dismissCampaignHelp()
        } else {
            do {
                try session.advanceStory()
            } catch {
                cellInfo.text = GameLocalization.text("Cannot continue the story")
            }
        }
        if !dismissingTutorialHelp || session.snapshot.phase != .playerTurn {
            selectedUnitID = nil
        }
        saveSnapshot()
        refresh()
    }

    private func refresh() {
        if !GamePreferences.showsHelp {
            while session.snapshot.pendingCampaignHelpID != nil { session.dismissCampaignHelp() }
        }
        let state = session.snapshot
        if sceneClock == nil {
            switch session.pendingPresentation?.scene?.kind {
            case .fadeToBlack: storyDrape.alpha = 0
            case .fadeFromBlack: storyDrape.alpha = 1
            default: storyDrape.alpha = state.missionRuntime?.screenObscured == true ? 1 : 0
            }
        }
        if session.pendingPresentation?.kind != .combat {
            let track: String
            if let pending = session.pendingPresentation {
                if let turn = pending.scene?.turn { track = turn.fraction == 1 ? "bg_good" : "bg_bad" }
                else if pending.scene?.kind == .levelUp || pending.scene?.kind == .rankName { track = pending.unitsBefore.first(where: { $0.id == pending.unitID })?.fraction == 2 ? "bg_bad" : "bg_good" }
                else if pending.kind == .scene { track = pending.scene?.kind == .healing ? (pending.unitsBefore.first(where: { $0.id == pending.scene?.healing?.first?.unitID })?.fraction == 2 ? "bg_bad" : "bg_good") : "bg_story" }
                else { track = pending.unitsBefore.first(where: { $0.id == pending.unitID })?.fraction == 2 ? "bg_bad" : "bg_good" }
            } else {
                switch state.phase {
                case .opening, .story: track = "bg_story"
                case .victory: track = "victory"
                case .defeat: track = "gameover"
                default: track = "bg_good"
                }
            }
            GameAudioPlayer.shared.play(track)
        }
        goldLabel.text = String(session.money(for: session.pendingPresentation?.scene?.turn?.fraction ?? state.activeFraction))
        let visibleUnits = session.pendingPresentation?.unitsBefore ?? state.units
        let activeUnits = visibleUnits.filter { $0.isAlive }
        let blueCount = activeUnits.filter { $0.fraction == 1 }.count
        let redCount = activeUnits.filter { $0.fraction == 2 }.count
        let presentationDescription = session.pendingPresentation.map { GameLocalization.format("; presentation=%@", GameLocalization.text("presentation.\($0.kind.rawValue)")) } ?? ""
        let sceneDescription = session.pendingPresentation?.scene.map { GameLocalization.format("; scene=%@", GameLocalization.text("presentation.\($0.kind.rawValue)")) } ?? ""
        let greenCount = activeUnits.filter { $0.fraction == 3 }.count
        let blackCount = activeUnits.filter { $0.fraction == 4 }.count
        mapAccessibilitySummary.accessibilityValue = GameLocalization.format("%@ blue units, %@ red units", String(describing: blueCount), String(describing: redCount)) + (session.isSkirmish ? GameLocalization.format(", %@ green units, %@ black units", String(describing: greenCount), String(describing: blackCount)) : "") + presentationDescription + sceneDescription

        switch state.phase {
        case .opening, .story:
            turnLabel.text = state.phase == .opening ? GameLocalization.text("Prologue") : GameLocalization.text("Battlefield Dialogue")
        case .playerTurn:
            turnLabel.text = session.isSkirmish ? GameLocalization.format("%@ · Team %@ · TURN %@", String(describing: SkirmishSetupViewController.factionName(state.activeFraction)), String(describing: session.team(for: state.activeFraction)), String(describing: state.currentTurn + 1)) : GameLocalization.format("Blue's Turn  ·  TURN %@", String(describing: max(1, (state.currentTurn + 2) / 2)))
        case .enemyTurn:
            turnLabel.text = GameLocalization.format("%@ is taking its turn", String(describing: SkirmishSetupViewController.factionName(state.activeFraction)))
        case .victory:
            turnLabel.text = GameLocalization.text("Campaign Complete")
        case .defeat:
            turnLabel.text = GameLocalization.text("Campaign Failed")
        }


        let selected = selectedUnitID.flatMap(session.unit(id:))
        let movement = selected.flatMap { unit -> Set<GridPoint> in
            guard let range = try? session.movementRange(for: unit.id) else { return [] }
            return range.reachableDestinations
        } ?? []
        let attackable = selected.map { unit in
            Set(session.attackableUnits(for: unit.id).map(\.position))
        } ?? []
        mapCanvas.render(
            map: session.presentationMap,
            units: visibleUnits.filter { $0.isAlive || $0.isCorpse || (session.pendingPresentation?.scene?.kind == .death && session.pendingPresentation?.unitID == $0.id) },
            selected: selected?.position,
            movement: pendingCorpseID == nil ? movement : [],
            attackable: pendingCorpseID == nil ? attackable : Set(selectedUnitID.map(session.attackRangeCells(for:)) ?? []),
            route: selected.map { [$0.position] + previewRoute } ?? [],
            target: pendingTargetID.flatMap(session.unit(id:))?.position,
            raiseTarget: pendingCorpseID.flatMap(session.unit(id:))?.position
        )

        let showCampaignPanel = session.pendingPresentation == nil && (state.pendingCampaignHelpID != nil
            || state.phase == .opening || state.phase == .story)
        storyPanel.isHidden = !showCampaignPanel
        hudFrame.isHidden = showCampaignPanel
        cellInfo.isHidden = showCampaignPanel
        endUnitButton.isHidden = showCampaignPanel
        endTurnButton.isHidden = showCampaignPanel
        cancelButton.isHidden = showCampaignPanel || selectedUnitID == nil
        confirmButton.isHidden = showCampaignPanel || (previewRoute.isEmpty && pendingTargetID == nil && pendingCorpseID == nil)
        confirmButton.setTitle(pendingCorpseID != nil ? GameLocalization.text("story.69") : (pendingTargetID == nil ? GameLocalization.text("story.61") : GameLocalization.text("story.62")), for: .normal)
        endUnitButton.isHidden = showCampaignPanel || !previewRoute.isEmpty || pendingTargetID != nil || pendingCorpseID != nil
        endTurnButton.isHidden = showCampaignPanel || selectedUnitID != nil
        storyPanelHeightConstraint.constant = state.pendingCampaignHelpID == nil ? 200 : 190
        if let helpID = state.pendingCampaignHelpID {
            storyLabel.text = campaignHelpText(id: helpID)
            storyButton.setTitle(GameLocalization.text("Got It"), for: .normal)
            storyButton.accessibilityIdentifier = "campaign.tutorial.continue"
        } else {
            let message: String
            if let id = state.currentStoryMessageID, let original = try? GameResources.campaignStoryText(id: id) {
                message = original
            } else if state.phase == .opening {
                message = GameLocalization.text("The Temple of Courage is under attack. Begin the story.")
            } else {
                message = ""
            }
            storyLabel.text = message
            storyButton.setTitle(state.phase == .opening && state.currentStoryMessageID == nil ? GameLocalization.text("Begin Prologue") : GameLocalization.text("Continue"), for: .normal)
            storyButton.accessibilityIdentifier = "campaign.story.continue"
        }
        storyPanel.configure(text: storyLabel.text ?? "", portraitID: session.storyPortraitID,
                             identifier: storyButton.accessibilityIdentifier ?? "campaign.story.continue")
        let castle = selected?.position ?? selectedCell
        let canBuy = castle.map { session.canOpenRecruitment(at: $0) } == true
        actionMenu.isHidden = showCampaignPanel || (selected == nil && !canBuy) || !previewRoute.isEmpty || pendingTargetID != nil || pendingCorpseID != nil || session.pendingPresentation != nil
        var controls: [(UIButton, UIImage?)] = []
        var actionTitle = selected?.hasActed == true ? GameLocalization.text("UNIT INFORMATION") : GameLocalization.text("story.64")
        if canBuy {
            controls.append((buyButton, OriginalArt.frame("action_icons", x: 0, width: 16, height: 16)))
            actionTitle = GameLocalization.text("story.63")
        }
        if let selected {
            if let action = session.availableBuildingAction(unitID: selected.id) {
                buildingButton.accessibilityLabel = action == .occupy ? GameLocalization.text("story.67") : GameLocalization.text("story.68")
                controls.append((buildingButton, OriginalArt.frame("action_icons", x: 16, width: 16, height: 16)))
                if !canBuy { actionTitle = buildingButton.accessibilityLabel! }
            }
            let canAttack = !session.attackableUnits(for: selected.id).isEmpty
            if canAttack {
                controls.append((attackMenuButton, OriginalArt.frame("action_icons", x: 32, width: 16, height: 16)))
                if controls.count == 1 { actionTitle = GameLocalization.text("story.62") }
            }
            if !session.summonableCorpses(for: selected.id).isEmpty {
                controls.append((raiseButton, OriginalArt.frame("action_icons", x: 48, width: 16, height: 16)))
                if controls.count == 1 { actionTitle = GameLocalization.text("story.69") }
            }
            controls.append((infoMenuButton, OriginalArt.frame("menu_icons", x: 150, width: 25, height: 25)))
            if !selected.hasActed { controls.append((endUnitButton, OriginalArt.frame("action_icons", x: 80, width: 16, height: 16))) }
        }
        actionMenu.configure(title: actionTitle, controls: controls)
        view.setNeedsLayout()
        updateCombatHeader()
        if state.phase == .opening || state.phase == .story { headerFrame.isHidden = true }
        if session.pendingPresentation?.kind == .scene {
            headerFrame.isHidden = true
            hudFrame.isHidden = true
            actionsStack.isHidden = true
        } else {
            actionsStack.isHidden = false
        }
        updateInputAvailability()
        saveSnapshot()
        if view.window != nil { playNextPresentation(); presentResultIfNeeded() }
    }

    private func updateCombatHeader() {
        combatHeader?.removeFromSuperview()
        combatHeader = nil
        headerFrame.isHidden = false
        guard let attacker = selectedUnitID.flatMap(session.unit(id:)),
              let defender = pendingTargetID.flatMap(session.unit(id:)),
              let catalog = try? GameResources.terrainCatalog(),
              let attackTile = session.map.tileID(x: attacker.x, y: attacker.y),
              let defendTile = session.map.tileID(x: defender.x, y: defender.y),
              let attackTerrain = catalog.tiles.first(where: { $0.id == attackTile })?.terrain,
              let defendTerrain = catalog.tiles.first(where: { $0.id == defendTile })?.terrain,
              let header = try? OriginalCombatHeaderView(attacker: attacker, defender: defender, attackerTerrain: attackTerrain, defenderTerrain: defendTerrain) else { return }
        header.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(header)
        NSLayoutConstraint.activate([
            header.leadingAnchor.constraint(equalTo: headerFrame.leadingAnchor), header.trailingAnchor.constraint(equalTo: headerFrame.trailingAnchor),
            header.topAnchor.constraint(equalTo: headerFrame.topAnchor), header.bottomAnchor.constraint(equalTo: headerFrame.bottomAnchor),
        ])
        combatHeader = header
        headerFrame.isHidden = true
    }

    private func playNextPresentation() {
        if !isPresentingAction, session.isSkirmish, session.snapshot.phase == .enemyTurn,
           session.pendingPresentation == nil, presentedViewController == nil,
           UIApplication.shared.applicationState == .active {
            session.advanceAutomaticSkirmishTurn()
            saveSnapshot()
            refresh()
            return
        }
        guard !isPresentingAction, presentedViewController == nil, let presentation = session.pendingPresentation else { return }
        isPresentingAction = true
        updateInputAvailability()
        mapScrollView.isUserInteractionEnabled = false
        turnLabel.text = presentation.unitsBefore.first(where: { $0.id == presentation.unitID })?.fraction == 2 ? GameLocalization.text("Red is taking its turn") : GameLocalization.text("Battle")
        switch presentation.kind {
        case .scene:
            guard let scene = presentation.scene else { finishPresentation(); return }
            playScene(scene)
        case .movement:
            guard let unit = presentation.unitsBefore.first(where: { $0.id == presentation.unitID }) else { finishPresentation(); return }
            let visible = CGRect(x: CGFloat(unit.x) * mapCanvas.tileSize - 60, y: CGFloat(unit.y) * mapCanvas.tileSize - 80,
                                 width: mapCanvas.tileSize + 120, height: mapCanvas.tileSize + 160)
            mapScrollView.scrollRectToVisible(visible, animated: false)
            mapCanvas.animateMovement(unitID: unit.id, from: unit.position, route: presentation.route, unitSpeed: presentation.unitSpeed ?? 6) { [weak self] in self?.finishPresentation() }
        case .combat:
            guard let combat = presentation.combat else { finishPresentation(); return }
            guard GamePreferences.enabled(.fightAnimation) else {
                playMapCombat(combat)
                return
            }
            do {
                let controller = try OriginalCombatViewController(combat: combat)
                controller.onComplete = { [weak self, weak controller] in
                    controller?.willMove(toParent: nil)
                    controller?.view.removeFromSuperview()
                    controller?.removeFromParent()
                    self?.finishPresentation()
                }
                addChild(controller)
                controller.view.frame = view.bounds
                controller.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
                view.addSubview(controller.view)
                controller.didMove(toParent: self)
            } catch {
                cellInfo.text = GameLocalization.text("Unable to load the battle scene. The result has been preserved.")
                finishPresentation()
            }
        }
    }

    private func playMapCombat(_ combat: CampaignCombatPresentation) {
        sceneTick = 0
        scenePreviousTime = 0
        sceneAccumulator = 0
        mapScrollView.setContentOffset(cameraOffset(for: combat.defender.position), animated: false)
        presentMapCombatStrike(amount: combat.damage, target: combat.defender,
                               healthAfter: combat.defenderHealthAfter)
        let link = CADisplayLink(target: self, selector: #selector(advanceMapCombat(_:)))
        link.add(to: .main, forMode: .common)
        sceneClock = link
    }

    @objc private func advanceMapCombat(_ link: CADisplayLink) {
        defer { scenePreviousTime = link.timestamp }
        guard UIApplication.shared.applicationState == .active, scenePreviousTime > 0,
              let combat = session.pendingPresentation?.combat else { return }
        sceneAccumulator += min(0.2, link.timestamp - scenePreviousTime)
        while sceneAccumulator >= 0.066 {
            sceneAccumulator -= 0.066
            sceneTick += 1
            if sceneTick == 12 {
                guard combat.didCounterattack else {
                    finishPresentation()
                    return
                }
                mapScrollView.setContentOffset(cameraOffset(for: combat.attacker.position), animated: false)
                presentMapCombatStrike(amount: combat.counterDamage, target: combat.attacker,
                                       healthAfter: combat.attackerHealthAfter)
            } else if sceneTick == 24 {
                finishPresentation()
                return
            }
        }
    }

    private func presentMapCombatStrike(amount: Int, target: CampaignUnitState, healthAfter: Int) {
        mapCanvas.selectCell(x: target.x, y: target.y)
        mapCanvas.presentCombatStrike(amount: amount, targetID: target.id, healthAfter: healthAfter)
        if GamePreferences.enabled(.vibration) { UIImpactFeedbackGenerator(style: .medium).impactOccurred() }
    }

    private func presentResultIfNeeded() {
        guard resultController == nil, session.pendingPresentation == nil,
              session.snapshot.phase == .victory || session.snapshot.phase == .defeat else { return }
        if session.snapshot.phase == .victory, session.map.id == "m7" {
            guard !didCompleteFinalMission else { return }
            didCompleteFinalMission = true
            onMissionComplete?()
            return
        }
        headerFrame.isHidden = true
        hudFrame.isHidden = true
        actionsStack.isHidden = true
        cellInfo.isHidden = true
        actionMenu.isHidden = true
        view.setNeedsLayout()
        view.layoutIfNeeded()
        let resultMessage = session.isSkirmish ? session.snapshot.winningTeam.map { GameLocalization.format("Team %@ Wins", String(describing: $0)) } ?? GameLocalization.text("All human factions have been defeated") : nil
        let controller = OriginalResultViewController(victory: session.snapshot.phase == .victory, message: resultMessage)
        controller.onComplete = { [weak self] in
            guard let self else { return }
            if self.session.snapshot.phase == .victory, let onMissionComplete = self.onMissionComplete {
                onMissionComplete()
            } else {
                self.onExit?()
            }
        }
        resultController = controller
        addChild(controller)
        controller.view.frame = view.bounds
        controller.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        view.addSubview(controller.view)
        controller.didMove(toParent: self)
    }

    private func finishPresentation() {
        buildingBanner?.removeFromSuperview()
        buildingBanner = nil
        turnBanner?.removeFromSuperview()
        turnBanner = nil
        sceneClock?.invalidate()
        sceneClock = nil
        if let scene = session.pendingPresentation?.scene {
            mapCanvas.finishScene(scene)
            updateStoryDrape(for: scene, tick: scene.durationTicks)
        }
        session.completePresentation()
        isPresentingAction = false
        mapScrollView.isUserInteractionEnabled = true
        refresh()
    }

    private func playScene(_ scene: CampaignScenePresentation) {
        if session.isSkirmish, scene.kind == .newTurn { skirmishTurnFocus = scene.focus }
        turnLabel.text = GameLocalization.text("Story Scene")
        if scene.kind == .objective {
            let panel = OriginalListViewController(title: campaignTitle)
            panel.body = try? GameResources.campaignStoryText(id: scene.textID ?? session.objectiveTextID)
            panel.closeTitle = GameLocalization.text("story.12")
            panel.closeIdentifier = "campaign.objective.continue"
            panel.onClose = { [weak self] in self?.finishPresentation() }
            panel.modalPresentationStyle = .overFullScreen
            present(panel, animated: false)
            return
        }
        if scene.kind == .building || scene.kind == .message || scene.kind == .heavenFury || scene.kind == .rankName {
            let banner = OriginalFrameView()
            var text = (try? GameResources.campaignStoryText(id: scene.textID ?? 73)) ?? ""
            if scene.kind == .rankName, let unit = session.pendingPresentation?.unitsBefore.first(where: { $0.id == scene.unitID }) {
                text += "\n" + unitName(for: unit)
            }
            let label = OriginalArt.label(text, size: 22)
            label.textAlignment = .center
            label.numberOfLines = 0
            label.accessibilityIdentifier = scene.kind == .heavenFury
                ? "campaign.heaven-fury.message"
                : (scene.kind == .rankName ? "campaign.rank-name" : (scene.kind == .message ? "campaign.story.message" : "campaign.building-message"))
            label.translatesAutoresizingMaskIntoConstraints = false
            banner.addSubview(label)
            NSLayoutConstraint.activate([
                label.leadingAnchor.constraint(equalTo: banner.leadingAnchor, constant: 8),
                label.trailingAnchor.constraint(equalTo: banner.trailingAnchor, constant: -8),
                label.topAnchor.constraint(equalTo: banner.topAnchor),
                label.bottomAnchor.constraint(equalTo: banner.bottomAnchor),
            ])
            buildingBanner = banner
            view.addSubview(banner)
            banner.frame = CGRect(x: 0, y: view.bounds.midY - 36, width: view.bounds.width, height: 72)
        }
        if let turn = scene.turn {
            let banner = OriginalTurnBanner(turn: turn, revealIncome: session.isSkirmish ? session.snapshot.skirmish?.players.first { $0.fraction == turn.fraction }?.control == .human : nil)
            banner.onSkip = { [weak self, weak banner] in
                guard let self, let banner, self.turnBanner === banner,
                      self.session.pendingPresentation?.scene?.kind == .newTurn else { return }
                self.finishPresentation()
            }
            turnBanner = banner
            view.addSubview(banner)
            banner.frame = CGRect(x: 0, y: view.bounds.midY - 54, width: view.bounds.width, height: 108)
        }
        view.layoutIfNeeded()
        sceneTick = 0
        scenePreviousTime = 0
        sceneAccumulator = 0
        sceneCameraArrivalTick = nil
        updateStoryDrape(for: scene, tick: 0)
        if let origin = scene.cameraOrigin ?? (scene.kind == .camera || scene.kind == .levelUp ? nil : scene.focus) {
            mapScrollView.setContentOffset(cameraOffset(for: origin), animated: false)
        }
        if scene.kind != .levelUp { mapCanvas.presentScene(scene, tick: 0) }
        if scene.kind == .quake { shakeMap(duration: 5) }
        if scene.kind == .poison || scene.kind == .blessing {
            finishPresentation()
            return
        }
        if scene.kind == .hit, GamePreferences.enabled(.vibration) { UIImpactFeedbackGenerator(style: .medium).impactOccurred() }
        let link = CADisplayLink(target: self, selector: #selector(advanceScene(_:)))
        link.add(to: .main, forMode: .common)
        sceneClock = link
    }

    @objc private func advanceScene(_ link: CADisplayLink) {
        defer { scenePreviousTime = link.timestamp }
        guard UIApplication.shared.applicationState == .active, scenePreviousTime > 0,
              let scene = session.pendingPresentation?.scene else { return }
        sceneAccumulator += min(0.2, link.timestamp - scenePreviousTime)
        while sceneAccumulator >= 0.066 {
            sceneAccumulator -= 0.066
            sceneTick += 1
            let arrived = stepSceneCamera(scene)
            if arrived, sceneCameraArrivalTick == nil { sceneCameraArrivalTick = sceneTick }
            let movementTicks = scene.movements.map {
                ($0.delayTicks ?? 0) + max(0, $0.route.count - 1) * 24 / max(1, scene.unitSpeed)
            }.max() ?? 0
            let delayStart = scene.kind == .camera || scene.kind == .levelUp ? (sceneCameraArrivalTick ?? sceneTick) : 0
            updateStoryDrape(for: scene, tick: sceneTick)
            if scene.kind != .levelUp || arrived {
                mapCanvas.presentScene(scene, tick: scene.kind == .levelUp ? sceneTick - delayStart : sceneTick)
            }
            if scene.kind == .heavenFury, sceneTick == 12 {
                if GamePreferences.enabled(.vibration) { UIImpactFeedbackGenerator(style: .heavy).impactOccurred() }
                shakeMap(duration: 0.5)
            }
            if arrived, sceneTick >= max(movementTicks, delayStart + scene.durationTicks) {
                finishPresentation()
                return
            }
        }
        mapCanvas.presentSceneMovement(scene, tick: Double(sceneTick) + sceneAccumulator / 0.066)
    }

    private func shakeMap(duration: TimeInterval) {
        let shake = CAKeyframeAnimation(keyPath: "transform.translation.x")
        shake.values = [0, -6, 6, -5, 5, -3, 3, 0]
        shake.keyTimes = [0, 0.12, 0.25, 0.38, 0.5, 0.65, 0.82, 1]
        shake.duration = min(0.5, duration)
        shake.repeatCount = Float(max(1, duration / shake.duration))
        mapScrollView.layer.add(shake, forKey: "campaign.shake")
    }

    private func updateStoryDrape(for scene: CampaignScenePresentation, tick: Int) {
        guard scene.kind == .fadeToBlack || scene.kind == .fadeFromBlack else { return }
        let progress = scene.durationTicks > 0
            ? min(1, CGFloat(max(0, tick)) / CGFloat(scene.durationTicks))
            : 1
        storyDrape.alpha = scene.kind == .fadeToBlack ? progress : 1 - progress
    }

    private func cameraOffset(for point: GridPoint) -> CGPoint {
        let scale = mapScrollView.zoomScale
        let cell = mapCanvas.tileSize * scale
        let insets = mapScrollView.contentInset
        let lower = CGPoint(x: -insets.left, y: -insets.top)
        let upper = CGPoint(x: max(lower.x, mapScrollView.contentSize.width - mapScrollView.bounds.width + insets.right),
                            y: max(lower.y, mapScrollView.contentSize.height - mapScrollView.bounds.height + insets.bottom))
        // Keep the source cell centered in the usable battlefield at the touch scale.
        let centerY = (mapScrollView.bounds.height + insets.top - insets.bottom) / 2
        return CGPoint(x: min(upper.x, max(lower.x, (CGFloat(point.x) + 0.5) * cell - mapScrollView.bounds.width / 2)),
                       y: min(upper.y, max(lower.y, (CGFloat(point.y) + 0.5) * cell - centerY)))
    }

    private func stepSceneCamera(_ scene: CampaignScenePresentation) -> Bool {
        guard let point = scene.focus else { return true }
        let target = cameraOffset(for: point)
        let current = mapScrollView.contentOffset
        let scale = mapCanvas.tileSize * mapScrollView.zoomScale / 24
        // MainDisplayable.moveMapOneStep: delta / 2, clamped to 1...mapStepMax.
        func step(_ from: CGFloat, _ to: CGFloat) -> CGFloat {
            let delta = to - from
            let pixels = abs(delta) / scale
            guard pixels > 0.01 else { return to }
            let amount = min(pixels, max(1, min(CGFloat(scene.cameraStep), floor(pixels / 2)))) * scale
            return from + (delta < 0 ? -amount : amount)
        }
        let next = CGPoint(x: step(current.x, target.x), y: step(current.y, target.y))
        mapScrollView.setContentOffset(next, animated: false)
        return abs(next.x - target.x) < 0.01 && abs(next.y - target.y) < 0.01
    }

    private func showPauseMenu() {
        guard !isPresentingAction else { return }
        let panel = OriginalListViewController(title: GameLocalization.text("PAUSED"))
        panel.entries = [
            .init(title: GameLocalization.text("story.70"), identifier: "pause.map", action: { [weak self, weak panel] in
                panel?.dismiss(animated: false) {
                    guard let self else { return }
                    do {
                        let overview = try OriginalMapOverviewViewController(map: self.session.map, units: self.session.snapshot.units)
                        overview.onFocus = { [weak self] point in
                            guard let self else { return }
                            self.mapScrollView.setContentOffset(self.cameraOffset(for: point), animated: false)
                        }
                        overview.modalPresentationStyle = .overFullScreen
                        self.present(overview, animated: false)
                    } catch { self.cellInfo.text = GameLocalization.text("Unable to load the map preview") }
                }
            }),
            .init(title: GameLocalization.text("story.71"), action: { [weak self, weak panel] in
                panel?.dismiss(animated: false) { self?.showObjective() }
            }),
            .init(title: GameLocalization.text("story.4"), identifier: "pause.save", action: { [weak self, weak panel] in
                panel?.dismiss(animated: false) {
                    guard let self else { return }
                    let slots = OriginalSaveSlotsViewController(snapshotToSave: self.session.snapshot)
                    slots.modalPresentationStyle = .overFullScreen
                    self.present(slots, animated: false)
                }
            }),
            .init(title: GameLocalization.text("story.8"), identifier: "pause.settings", action: { [weak self, weak panel] in
                panel?.dismiss(animated: false) {
                    let settings = OriginalSettingsViewController()
                    settings.modalPresentationStyle = .overFullScreen
                    self?.present(settings, animated: false)
                }
            }),
            .init(title: GameLocalization.text("story.60"), action: { [weak self, weak panel] in panel?.dismiss(animated: false) { self?.onExit?() } }),
        ]
        panel.modalPresentationStyle = .overFullScreen
        present(panel, animated: false)
    }

    private func showObjective() {
        let panel = OriginalListViewController(title: campaignTitle)
        panel.body = (try? GameResources.campaignStoryText(id: session.objectiveTextID)) ?? ""
        panel.modalPresentationStyle = .overFullScreen
        present(panel, animated: false)
    }

    private var campaignTitle: String {
        if session.isSkirmish { return GameResources.mapName(session.map) }
        return (try? GameResources.campaignStoryText(id: 121 + session.map.index)) ?? GameResources.mapName(session.map).uppercased()
    }

    private func unitName(for unit: CampaignUnitState) -> String {
        (try? GameResources.campaignStoryText(id: unit.nameTextID)) ?? unitDefinitions[unit.type]?.displayName ?? GameLocalization.text("UNIT")
    }

    @objc private func showUnitDetails() {
        guard let unit = selectedUnitID.flatMap(session.unit(id:)), let definition = unitDefinitions[unit.type],
              let profile = profiles[unit.type] else { return }
        let panel = OriginalListViewController(title: unitName(for: unit))
        let rules = try? GameResources.campaignRuleset()
        let terrain = session.map.tileID(x: unit.x, y: unit.y).flatMap { rules?.terrainByTileID[$0] }
        let defense = terrain.flatMap { rules?.defenseByTerrainID[$0] } ?? 0
        panel.contentHeader = try? OriginalUnitStatsView(unit: unit, profile: profile, terrainDefense: defense)
        var effects: [String] = []
        if unit.isBlessed { effects.append((try? GameResources.campaignStoryText(id: 100)) ?? "") }
        if unit.isPoisoned { effects.append((try? GameResources.campaignStoryText(id: 99)) ?? "") }
        panel.body = effects.isEmpty ? definition.description : ([(try? GameResources.campaignStoryText(id: 98)) ?? ""] + effects).joined(separator: "\n") + "\n-----------\n" + definition.description
        panel.modalPresentationStyle = .overFullScreen
        present(panel, animated: false)
    }

    func saveSnapshot() {
        onSnapshotChange?(session.snapshot)
    }

    private func campaignHelpText(id: Int) -> String {
        (try? GameResources.campaignStoryText(id: id))
            ?? GameLocalization.text("Continue fighting and protect the Blue commander.")
    }
}

extension CampaignMapViewController: UIScrollViewDelegate {
    func viewForZooming(in scrollView: UIScrollView) -> UIView? {
        scrollView === mapScrollView ? mapCanvas : nil
    }

    func scrollViewDidZoom(_ scrollView: UIScrollView) {
        updateMapScrollInsets()
    }
}

@MainActor
final class CampaignMapCanvasView: UIView {
    private static let sourceTileSize = 24

    private var map: CampaignMap
    private var tileImages: [Int: CGImage] = [:]
    private(set) var tileSize: CGFloat = 32
    private var mapWidth: CGFloat { CGFloat(map.width) * tileSize }
    private var mapHeight: CGFloat { CGFloat(map.height) * tileSize }
    var contentSize: CGSize { CGSize(width: mapWidth, height: mapHeight) }
    private var tileLayers: [(layer: CALayer, point: GridPoint)] = []
    private var unitLayers: [(layer: CALayer, point: GridPoint)] = []
    private var overlayLayers: [(layer: CALayer, point: GridPoint)] = []
    private var cellButtons: [(button: UIButton, point: GridPoint)] = []
    private let selectedLayer = CALayer()
    private let routeEndLayer = CALayer()
    private var isAttackCursor = false
    private var imageOrigin = CGPoint.zero
    private var imageScale: CGFloat = 1
    private var units: [CampaignUnitState] = []
    private var selectedPoint: GridPoint?
    private var movementPoints: Set<GridPoint> = []
    private var attackPoints: Set<GridPoint> = []
    private var unitIcons: [String: CGImage] = [:]
    private var actedUnitIcons: [String: CGImage] = [:]
    private var secondUnitIcons: [String: CGImage] = [:]
    private var unitLayerByID: [Int: CALayer] = [:]
    private var unitVisualByID: [Int: CALayer] = [:]
    private var movingUnitIDs: Set<Int> = []
    private var healthLayerByUnitID: [Int: CATextLayer] = [:]
    private var sceneEffects: [String: OriginalSprite] = [:]
    private var sceneEffectLayers: [String: CALayer] = [:]
    // Original map sprites outlive the action that created them.
    private let ambientEffects = CALayer()
    private var routePoints: [GridPoint] = []
    private let routeLayer = CAShapeLayer()
    private let moveAlpha = OriginalArt.frame("alpha", x: 0, width: 24, height: 24)?.cgImage
    private let attackAlpha = OriginalArt.frame("alpha", x: 24, width: 24, height: 24)?.cgImage
    var onCellSelection: ((Int, Int) -> Void)?
    var onCellDoubleSelection: ((Int, Int) -> Void)?

    init(map: CampaignMap, terrain: TerrainCatalog, unitDefinitions: [UnitDefinition]) throws {
        self.map = map
        super.init(frame: .zero)
        NotificationCenter.default.addObserver(self, selector: #selector(refreshMotionPreference),
                                               name: UIAccessibility.reduceMotionStatusDidChangeNotification, object: nil)
        backgroundColor = .black
        isOpaque = true
        let doubleTap = UITapGestureRecognizer(target: self, action: #selector(handleDoubleTap(_:)))
        doubleTap.numberOfTapsRequired = 2
        doubleTap.cancelsTouchesInView = true
        doubleTap.delaysTouchesEnded = false
        addGestureRecognizer(doubleTap)

        let tileDefinitions = Dictionary(uniqueKeysWithValues: terrain.tiles.map { ($0.id, $0) })
        for definition in terrain.tiles {
            guard let image = OriginalArt.resourceImage(at: definition.image)?.cgImage else { throw CanvasError.invalidImage(definition.image) }
            tileImages[definition.id] = image
        }
        for (y, row) in map.rows.enumerated() {
            for (x, tileID) in row.enumerated() {
                guard let definition = tileDefinitions[tileID] else {
                    throw CanvasError.missingAsset("terrain tile \(tileID)")
                }
                let image: CGImage
                if let cached = tileImages[tileID] {
                    image = cached
                } else {
                    guard let decoded = OriginalArt.resourceImage(at: definition.image)?.cgImage else {
                        throw CanvasError.invalidImage(definition.image)
                    }
                    tileImages[tileID] = decoded
                    image = decoded
                }
                let layer = imageLayer(contents: image)
                tileLayers.append((layer, GridPoint(x: x, y: y)))
                self.layer.addSublayer(layer)
            }
        }

        let factionNames = ["blue", "red", "green", "black"]
        for (fraction, faction) in factionNames.enumerated() {
            let path = "res/\(faction)/unit_icons.png"
            for definition in unitDefinitions {
                guard let icon = OriginalArt.frame("\(faction)/unit_icons", x: definition.id * 24, width: 24, height: 24)?.cgImage else {
                    throw CanvasError.invalidImage(path)
                }
                guard let actedIcon = UnitSpriteImageProcessor.actedVariant(of: icon) else {
                    throw CanvasError.invalidImage(path)
                }
                let second = OriginalArt.frame("\(faction)/unit_icons", x: definition.id * 24, y: 24, width: 24, height: 24)?.cgImage ?? icon
                if definition.id == 9 {
                    for variant in 0..<4 {
                        let images = [icon, second].enumerated().map { frame, body -> CGImage in
                            let head = OriginalArt.frame("king_head_icons", x: variant * 13, width: 13, height: 12)
                            let format = UIGraphicsImageRendererFormat()
                            format.scale = HighResolutionUnitArt.scale
                            return UIGraphicsImageRenderer(size: CGSize(width: 24, height: 24), format: format).image { context in
                                context.cgContext.interpolationQuality = .none
                                UIImage(cgImage: body, scale: HighResolutionUnitArt.scale, orientation: .up).draw(at: .zero)
                                head?.draw(at: CGPoint(x: 7, y: frame))
                            }.cgImage ?? body
                        }
                        let key = "\(fraction + 1):\(definition.id):\(variant)"
                        unitIcons[key] = images[0]
                        secondUnitIcons[key] = images[1]
                        actedUnitIcons[key] = UnitSpriteImageProcessor.actedVariant(of: images[0])
                    }
                } else {
                    let key = "\(fraction + 1):\(definition.id)"
                    unitIcons[key] = icon
                    secondUnitIcons[key] = second
                    actedUnitIcons[key] = actedIcon
                }
            }
        }

        selectedLayer.name = "campaign.cursor"
        selectedLayer.magnificationFilter = .nearest
        selectedLayer.minificationFilter = .nearest
        routeEndLayer.contents = OriginalArt.image("cursor_05")?.cgImage
        routeEndLayer.magnificationFilter = .nearest
        routeEndLayer.minificationFilter = .nearest
        routeEndLayer.isHidden = true
        routeLayer.name = "campaign.route"
        routeLayer.fillColor = UIColor(red: 225/255, green: 0, blue: 82/255, alpha: 1).cgColor
        selectedLayer.isHidden = true
        layer.addSublayer(selectedLayer)
        configureCursor(attacking: false)


        for y in 0..<map.height {
            for x in 0..<map.width {
                let point = GridPoint(x: x, y: y)
                let button = UIButton(type: .custom)
                button.backgroundColor = .clear
                button.accessibilityIdentifier = "campaign.cell.\(x).\(y)"
                button.accessibilityLabel = String(format: GameLocalization.text("Cell %02d,%02d"), x + 1, y + 1)
                button.addAction(UIAction { [weak self] _ in
                    self?.onCellSelection?(point.x, point.y)
                }, for: .touchUpInside)
                cellButtons.append((button, point))
                addSubview(button)
            }
        }
    }

    required init?(coder: NSCoder) {
        nil
    }

    private enum CanvasError: Error {
        case missingAsset(String)
        case invalidImage(String)
    }

    func configureTileSize(_ size: CGFloat) {
        guard abs(tileSize - size) > 0.01 else { return }
        tileSize = size
        bounds = CGRect(origin: .zero, size: contentSize)
        for item in tileLayers + unitLayers {
            item.layer.bounds = CGRect(origin: .zero, size: CGSize(width: size, height: size))
        }
        for hp in healthLayerByUnitID.values {
            hp.frame = CGRect(x: 0, y: size - 16, width: size - 3, height: 14)
        }
        setNeedsLayout()
    }

    func render(
        map: CampaignMap,
        units: [CampaignUnitState],
        selected: GridPoint?,
        movement: Set<GridPoint>,
        attackable: Set<GridPoint>,
        route: [GridPoint] = [],
        target: GridPoint? = nil,
        raiseTarget: GridPoint? = nil
    ) {
        CATransaction.begin()
        CATransaction.setDisableActions(true)
        for item in tileLayers {
            let point = item.point
            if let tile = map.tileID(x: point.x, y: point.y), tile != self.map.tileID(x: point.x, y: point.y) {
                item.layer.contents = tileImages[tile]
            }
        }
        CATransaction.commit()
        self.map = map
        self.units = units
        sceneEffectLayers.values.forEach { $0.removeFromSuperlayer() }
        sceneEffectLayers.removeAll()
        ambientEffects.sublayers?.filter { $0.animationKeys()?.isEmpty != false }.forEach { $0.removeFromSuperlayer() }
        selectedPoint = raiseTarget ?? target ?? selected
        configureCursor(attacking: target != nil)
        movementPoints = movement
        attackPoints = attackable
        routePoints = route.count > 1 ? route : []
        unitLayerByID.removeAll()
        unitVisualByID.removeAll()
        movingUnitIDs.removeAll()
        healthLayerByUnitID.removeAll()
        unitLayers.forEach { $0.layer.removeFromSuperlayer() }
        overlayLayers.forEach { $0.layer.removeFromSuperlayer() }
        unitLayers.removeAll(keepingCapacity: true)
        overlayLayers.removeAll(keepingCapacity: true)

        for point in movement {
            addOverlay(at: point, image: moveAlpha)
        }
        for point in attackable {
            addOverlay(at: point, image: attackAlpha)
        }
        // Graves are beneath living units, including the original overlap case.
        for unit in units.filter(\.isCorpse) + units.filter({ !$0.isCorpse }) {
            let key = unit.type == 9
                ? "\(unit.fraction):\(unit.type):\(unit.resolvedCommanderVariant)"
                : "\(unit.fraction):\(unit.type)"
            guard let image = unit.isCorpse ? OriginalArt.image("tombstone")?.cgImage : (unit.hasActed ? actedUnitIcons[key] : unitIcons[key]) else { continue }
            let unitLayer = unit.isCorpse ? imageLayer(contents: image) : CALayer()
            unitLayer.bounds = CGRect(x: 0, y: 0, width: tileSize, height: tileSize)
            unitLayer.name = "campaign.unit.\(unit.id)"
            unitLayerByID[unit.id] = unitLayer
            if !unit.isCorpse {
                let visual = imageLayer(contents: image)
                visual.name = "campaign.unit.\(unit.id).sprite"
                visual.anchorPoint = CGPoint(x: 0.5, y: 1)
                visual.position = CGPoint(x: tileSize / 2, y: tileSize)
                visual.magnificationFilter = .linear
                visual.minificationFilter = .linear
                unitLayer.addSublayer(visual)
                unitVisualByID[unit.id] = visual
                updateUnitMotion(unit)
            }
            if !unit.isCorpse, unit.hitPoints < 100 {
                let hp = makeHealthLayer(unitID: unit.id, hitPoints: unit.hitPoints)
                healthLayerByUnitID[unit.id] = hp
                unitLayer.addSublayer(hp)
            }
            unitLayers.append((unitLayer, GridPoint(x: unit.x, y: unit.y)))
            layer.addSublayer(unitLayer)
        }
        if let selectedPoint {
            selectedLayer.frame = rect(for: selectedPoint)
            selectedLayer.isHidden = false
        } else {
            selectedLayer.isHidden = true
        }
        for item in cellButtons {
            var traits: UIAccessibilityTraits = .button
            if item.point == selected { traits.insert(.selected) }
            item.button.accessibilityTraits = traits
            let grave = units.first { $0.position == item.point && $0.isCorpse }
            if let unit = units.first(where: { $0.position == item.point && !$0.isCorpse }) {
                let faction = SkirmishSetupViewController.factionName(unit.fraction)
                item.button.accessibilityValue = GameLocalization.format("%@, HP %@, RANK %@", String(describing: faction), String(describing: unit.hitPoints), String(describing: unit.rank)) + (grave == nil ? "" : GameLocalization.text(", tombstone"))
            } else {
                item.button.accessibilityValue = grave == nil ? nil : GameLocalization.text("Tombstone")
            }
        }
        layer.addSublayer(routeLayer)
        layer.addSublayer(routeEndLayer)
        if let selected, let unit = units.first(where: { $0.position == selected && !$0.isCorpse }), let unitLayer = unitLayerByID[unit.id] {
            layer.addSublayer(unitLayer)
        }
        layer.addSublayer(selectedLayer)
        layer.addSublayer(ambientEffects)
        setNeedsLayout()
    }

    func presentCombatStrike(amount: Int, targetID: Int, healthAfter: Int) {
        guard let index = units.firstIndex(where: { $0.id == targetID }),
              let unitLayer = unitLayerByID[targetID] else { return }
        layoutIfNeeded()
        units[index].hitPoints = healthAfter
        if healthAfter < 100 {
            let hp: CATextLayer
            if let existing = healthLayerByUnitID[targetID] {
                hp = existing
            } else {
                hp = makeHealthLayer(unitID: targetID, hitPoints: healthAfter)
                healthLayerByUnitID[targetID] = hp
                unitLayer.addSublayer(hp)
            }
            hp.string = String(healthAfter)
        }
        let visual = unitVisualByID[targetID] ?? unitLayer
        visual.removeAnimation(forKey: "idle")
        visual.removeAnimation(forKey: "travel")
        let unit = units[index]
        let key = unit.type == 9
            ? "\(unit.fraction):\(unit.type):\(unit.resolvedCommanderVariant)"
            : "\(unit.fraction):\(unit.type)"
        if let first = visual.contents, let second = secondUnitIcons[key] {
            let hit = CAKeyframeAnimation(keyPath: "contents")
            hit.values = [first, second, first, second, first, second, first]
            hit.calculationMode = .discrete
            hit.duration = 0.396
            visual.add(hit, forKey: "combat-hit")
        }
        emit("redspark", at: units[index].position, repeats: 2)
        emitDamage(amount, targetID: targetID, at: units[index].position)
        updateAccessibility(for: units[index])
    }

    private func makeHealthLayer(unitID: Int, hitPoints: Int) -> CATextLayer {
        let hp = CATextLayer()
        hp.name = "campaign.unit.\(unitID).hp"
        hp.string = String(hitPoints)
        hp.font = UIFont.monospacedSystemFont(ofSize: 10, weight: .bold)
        hp.fontSize = 10
        hp.foregroundColor = UIColor.white.cgColor
        hp.shadowColor = UIColor.black.cgColor
        hp.shadowOpacity = 1
        hp.shadowOffset = CGSize(width: 1, height: 1)
        hp.shadowRadius = 0
        hp.alignmentMode = .right
        hp.contentsScale = 3
        hp.frame = CGRect(x: 0, y: tileSize - 16, width: tileSize - 3, height: 14)
        return hp
    }

    private func updateAccessibility(for unit: CampaignUnitState) {
        guard let cell = cellButtons.first(where: { $0.point == unit.position })?.button else { return }
        let faction = SkirmishSetupViewController.factionName(unit.fraction)
        let hasGrave = units.contains { $0.position == unit.position && $0.isCorpse }
        cell.accessibilityValue = GameLocalization.format("%@, HP %@", String(describing: faction), String(describing: unit.hitPoints)) + (hasGrave ? GameLocalization.text(", tombstone") : "")
    }

    private func emitDamage(_ amount: Int, targetID: Int, at point: GridPoint) {
        if sceneEffects["lchars"] == nil { sceneEffects["lchars"] = try? OriginalSprite("lchars") }
        guard let numbers = sceneEffects["lchars"] else { return }
        let characters = Array("-\(amount)")
        let size = CGSize(width: characters.count * 8, height: 11)
        let format = UIGraphicsImageRendererFormat()
        format.scale = HighResolutionUnitArt.scale
        let image = UIGraphicsImageRenderer(size: size, format: format).image { context in
            context.cgContext.interpolationQuality = .none
            for (index, character) in characters.enumerated() {
                numbers.draw(frame: character == "-" ? 11 : (character.wholeNumberValue ?? 0),
                             at: CGPoint(x: index * 8, y: 0))
            }
        }
        let effect = CALayer()
        effect.name = "campaign.combat.damage.\(targetID)"
        effect.contents = image.cgImage
        effect.bounds = CGRect(origin: .zero, size: size)
        effect.magnificationFilter = .nearest
        effect.minificationFilter = .nearest
        effect.opacity = 0

        let ticks = 16
        let halfWidth = size.width / 2
        let centerX = min(CGFloat(map.width * 24) - halfWidth,
                          max(halfWidth, CGFloat(point.x * 24 + 12)))
        let base = CGPoint(x: centerX, y: CGFloat(point.y * 24) + 18.5)
        var displacement = 0
        var speed = -4
        var positions: [NSValue] = []
        for _ in 0..<ticks {
            positions.append(NSValue(cgPoint: CGPoint(x: base.x, y: base.y + CGFloat(displacement))))
            displacement += speed
            if displacement < 0 { speed += 1 }
            else { displacement = 0; speed = -speed / 2 }
        }
        let keyTimes = (0...ticks).map { NSNumber(value: Double($0) / Double(ticks)) }
        let position = CAKeyframeAnimation(keyPath: "position")
        position.values = positions + [positions[positions.count - 1]]
        position.keyTimes = keyTimes
        position.calculationMode = .discrete
        let opacity = CABasicAnimation(keyPath: "opacity")
        opacity.fromValue = 1
        opacity.toValue = 1
        let duration = Double(ticks) * 0.066
        position.duration = duration
        opacity.duration = duration
        let animation = CAAnimationGroup()
        animation.animations = [position, opacity]
        animation.duration = duration
        ambientEffects.addSublayer(effect)
        layer.addSublayer(ambientEffects)
        effect.add(animation, forKey: "original-effect")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        imageScale = min(bounds.width / mapWidth, bounds.height / mapHeight)
        imageOrigin = CGPoint(
            x: (bounds.width - mapWidth * imageScale) / 2,
            y: (bounds.height - mapHeight * imageScale) / 2
        )
        CATransaction.begin()
        CATransaction.setDisableActions(true)
        ambientEffects.anchorPoint = .zero
        ambientEffects.bounds = CGRect(x: 0, y: 0, width: map.width * 24, height: map.height * 24)
        ambientEffects.position = imageOrigin
        ambientEffects.setAffineTransform(CGAffineTransform(scaleX: tileSize * imageScale / 24, y: tileSize * imageScale / 24))
        for item in tileLayers + unitLayers {
            item.layer.frame = CGRect(
                x: imageOrigin.x + CGFloat(item.point.x) * tileSize * imageScale,
                y: imageOrigin.y + CGFloat(item.point.y) * tileSize * imageScale,
                width: tileSize * imageScale,
                height: tileSize * imageScale
            )
        }
        for unit in units {
            guard let visual = unitVisualByID[unit.id], let container = unitLayerByID[unit.id] else { continue }
            let changedSize = visual.bounds.size != container.bounds.size
            visual.bounds = container.bounds
            visual.position = CGPoint(x: container.bounds.midX, y: container.bounds.maxY)
            if changedSize { updateUnitMotion(unit) }
        }
        for item in overlayLayers { item.layer.frame = rect(for: item.point) }
        if let selectedPoint { layoutCursor(at: routePoints.last ?? selectedPoint) }
        let path = UIBezierPath()
        let sourceScale = tileSize * imageScale / 24
        for (index, point) in routePoints.enumerated() {
            let frame = rect(for: point)
            // paintMap: 8px-wide rectangles, 14px long, overlapping each joint.
            let neighbors = [index > 0 ? routePoints[index - 1] : nil,
                             index + 1 < routePoints.count ? routePoints[index + 1] : nil].compactMap { $0 }
            for neighbor in neighbors {
                let segment: CGRect
                if neighbor.x > point.x { segment = CGRect(x: 10, y: 8, width: 14, height: 8) }
                else if neighbor.x < point.x { segment = CGRect(x: 0, y: 8, width: 14, height: 8) }
                else if neighbor.y > point.y { segment = CGRect(x: 8, y: 10, width: 8, height: 14) }
                else { segment = CGRect(x: 8, y: 0, width: 8, height: 14) }
                path.append(UIBezierPath(rect: CGRect(x: frame.minX + segment.minX * sourceScale,
                    y: frame.minY + segment.minY * sourceScale, width: segment.width * sourceScale, height: segment.height * sourceScale)))
            }
        }
        routeLayer.path = path.cgPath
        routeEndLayer.isHidden = routePoints.isEmpty
        if let end = routePoints.last { routeEndLayer.frame = rect(for: end).insetBy(dx: -sourceScale, dy: -sourceScale) }
        for item in cellButtons { item.button.frame = rect(for: item.point) }
        CATransaction.commit()
    }

    func selectCell(x: Int, y: Int) {
        guard map.tileID(x: x, y: y) != nil else { return }
        selectedPoint = GridPoint(x: x, y: y)
        layoutCursor(at: GridPoint(x: x, y: y))
        selectedLayer.isHidden = false
    }

    private func configureCursor(attacking: Bool) {
        isAttackCursor = attacking
        let frames = (attacking ? [2, 3, 4] : [0, 1]).compactMap {
            OriginalArt.image(String(format: "cursor_%02d", $0))?.cgImage
        }
        selectedLayer.contents = frames.first
        let animation = CAKeyframeAnimation(keyPath: "contents")
        animation.values = frames
        animation.calculationMode = .discrete
        animation.duration = Double(frames.count * 4) * 0.066
        animation.repeatCount = .infinity
        selectedLayer.add(animation, forKey: "cursor")
    }

    private func layoutCursor(at point: GridPoint) {
        let cell = rect(for: point)
        let scale = tileSize * imageScale / 24
        let size = isAttackCursor ? CGSize(width: 40, height: 41) : CGSize(width: 26, height: 26)
        selectedLayer.frame = CGRect(x: cell.midX - size.width * scale / 2, y: cell.midY - size.height * scale / 2,
                                     width: size.width * scale, height: size.height * scale)
    }

    @objc private func handleDoubleTap(_ gesture: UITapGestureRecognizer) {
        guard imageScale > 0 else { return }
        let point = gesture.location(in: self)
        let mapX = (point.x - imageOrigin.x) / imageScale
        let mapY = (point.y - imageOrigin.y) / imageScale
        guard mapX >= 0, mapY >= 0, mapX < mapWidth, mapY < mapHeight else { return }
        onCellDoubleSelection?(Int(mapX / tileSize), Int(mapY / tileSize))
    }

    private func addOverlay(at point: GridPoint, image: CGImage?) {
        let overlay = CALayer()
        overlay.contents = image
        overlay.magnificationFilter = .nearest
        overlay.minificationFilter = .nearest
        overlayLayers.append((overlay, point))
        if let unit = unitLayers.first?.layer { layer.insertSublayer(overlay, below: unit) }
        else { layer.addSublayer(overlay) }
    }

    func revealMovementRange(completion: @escaping () -> Void) {
        CATransaction.begin()
        CATransaction.setCompletionBlock(completion)
        for item in overlayLayers {
            let animation = CAKeyframeAnimation(keyPath: "transform.scale")
            animation.values = [0.01, 1.0/3, 2.0/3, 1]
            animation.keyTimes = [0, 0.33, 0.66, 1]
            animation.calculationMode = .discrete
            animation.duration = 0.198 // alphaWindowSize 12 → 8 → 4 → 0 at 66 ms per tick.
            item.layer.add(animation, forKey: "range")
        }
        CATransaction.commit()
    }

    func animateMovement(unitID: Int, from origin: GridPoint, route: [GridPoint], unitSpeed: Int = 6, completion: @escaping () -> Void) {
        guard let unitLayer = unitLayerByID[unitID], !route.isEmpty else { completion(); return }
        layoutIfNeeded()
        let points = [origin] + route
        let positions = points.map { point -> NSValue in
            let rect = rect(for: point)
            return NSValue(cgPoint: CGPoint(x: rect.midX, y: rect.midY))
        }
        let stepDuration = Double(max(1, 24 / max(1, unitSpeed))) * 0.066 // Player 6px/tick; CPU 12px/tick.
        let duration = Double(route.count) * stepDuration
        if let unit = units.first(where: { $0.id == unitID }) {
            movingUnitIDs.insert(unitID)
            updateUnitMotion(unit, duration: duration)
        }
        CATransaction.begin()
        CATransaction.setCompletionBlock { [weak self] in
            if let self, let unit = self.units.first(where: { $0.id == unitID }) {
                self.movingUnitIDs.remove(unitID)
                self.updateUnitMotion(unit)
            }
            completion()
        }
        let movement = CAKeyframeAnimation(keyPath: "position")
        movement.values = positions
        movement.duration = duration
        movement.calculationMode = .linear
        movement.fillMode = .forwards
        movement.isRemovedOnCompletion = false
        unitLayer.add(movement, forKey: "movement")
        for (index, point) in points.dropLast().enumerated() {
            guard let smoke = OriginalArt.frame("smoke", x: 0, width: 24, height: 20)?.cgImage else { continue }
            let puff = imageLayer(contents: smoke)
            puff.frame = rect(for: point)
            puff.opacity = 0
            layer.insertSublayer(puff, below: unitLayer)
            let animation = CAKeyframeAnimation(keyPath: "contents")
            animation.values = (0..<4).compactMap { OriginalArt.frame("smoke", x: $0 * 24, width: 24, height: 20)?.cgImage }
            animation.calculationMode = .discrete
            animation.duration = 0.264
            let opacity = CAKeyframeAnimation(keyPath: "opacity")
            opacity.values = [0.7, 0.5, 0.25, 0]
            opacity.duration = 0.264
            let group = CAAnimationGroup()
            group.animations = [animation, opacity]
            group.duration = 0.264
            group.beginTime = CACurrentMediaTime() + Double(index) * stepDuration
            puff.add(group, forKey: "dust")
            // The next render removes all transient effects, including after interrupted movement.
            overlayLayers.append((puff, point))
        }
        CATransaction.commit()
    }

    private func presentHealing(_ changes: [CampaignHealingPresentation], tick: Int) {
        if sceneEffects["lchars"] == nil { sceneEffects["lchars"] = try? OriginalSprite("lchars") }
        guard let numbers = sceneEffects["lchars"] else { return }
        // Sprite mode 5: bounceDelta=-4, gravity=1, each rebound halves velocity.
        var displacement = 0, velocity = -4
        for _ in 0..<min(tick, 17) {
            displacement += velocity
            if displacement < 0 { velocity += 1 }
            else { displacement = 0; velocity = -velocity / 2 }
        }
        let scale = tileSize * imageScale / 24
        for change in changes {
            guard let unit = units.first(where: { $0.id == change.unitID }) else { continue }
            let key = "heal-\(change.unitID)"
            let effect: CALayer
            if let existing = sceneEffectLayers[key] { effect = existing }
            else {
                effect = CALayer()
                effect.magnificationFilter = .nearest
                let characters = Array("+\(change.amount)")
                let format = UIGraphicsImageRendererFormat()
                format.scale = HighResolutionUnitArt.scale
                let image = UIGraphicsImageRenderer(size: CGSize(width: characters.count * 8, height: 11), format: format).image { context in
                    context.cgContext.interpolationQuality = .none
                    for (index, character) in characters.enumerated() {
                        numbers.draw(frame: character.wholeNumberValue ?? 12, at: CGPoint(x: index * 8, y: 0))
                    }
                }
                effect.contents = image.cgImage
                layer.addSublayer(effect)
                sceneEffectLayers[key] = effect
            }
            let cell = rect(for: unit.position)
            let width = CGFloat(String(change.amount).count + 1) * 8 * scale
            effect.frame = CGRect(x: cell.midX - width / 2, y: cell.maxY + CGFloat(displacement - 11) * scale, width: width, height: 11 * scale)
        }
    }

    func presentSceneMovement(_ scene: CampaignScenePresentation, tick: Double) {
        layoutIfNeeded()
        CATransaction.begin()
        CATransaction.setDisableActions(true)
        defer { CATransaction.commit() }
        for movement in scene.movements {
            guard let unitLayer = unitLayerByID[movement.unitID], let last = movement.route.last else { continue }
            let elapsed = max(0, tick - Double(movement.delayTicks ?? 0))
            let traveled = min(CGFloat(movement.route.count - 1), CGFloat(elapsed * Double(max(1, scene.unitSpeed))) / 24)
            let segment = min(Int(traveled), movement.route.count - 1)
            let origin = movement.route[segment]
            let destination = segment + 1 < movement.route.count ? movement.route[segment + 1] : last
            let progress = traveled - CGFloat(segment)
            let x = CGFloat(origin.x) + CGFloat(destination.x - origin.x) * progress
            let y = CGFloat(origin.y) + CGFloat(destination.y - origin.y) * progress
            unitLayer.position = CGPoint(x: imageOrigin.x + (x + 0.5) * tileSize * imageScale,
                                         y: imageOrigin.y + (y + 0.5) * tileSize * imageScale)
            let moving = elapsed > 0 && traveled < CGFloat(movement.route.count - 1)
            if moving != movingUnitIDs.contains(movement.unitID),
               let unit = units.first(where: { $0.id == movement.unitID }) {
                if moving { movingUnitIDs.insert(unit.id) }
                else { movingUnitIDs.remove(unit.id) }
                updateUnitMotion(unit)
            }
        }
    }

    func presentScene(_ scene: CampaignScenePresentation, tick: Int) {
        presentSceneMovement(scene, tick: Double(tick))
        CATransaction.begin()
        CATransaction.setDisableActions(true)
        defer { CATransaction.commit() }
        if scene.kind == .planning {
            if tick == 0 {
                for point in scene.movementRange ?? [] { addOverlay(at: point, image: moveAlpha) }
                selectedPoint = scene.focus
                selectedLayer.isHidden = false
                layer.addSublayer(selectedLayer)
            }
            if tick >= 4 {
                routePoints = scene.routePreview ?? []
                layer.addSublayer(routeLayer)
                layer.addSublayer(routeEndLayer)
                if let id = scene.unitID, let unitLayer = unitLayerByID[id] { layer.addSublayer(unitLayer) }
                layer.addSublayer(selectedLayer)
            }
            setNeedsLayout()
            layoutIfNeeded()
        }
        if scene.kind == .targeting, tick == 0 {
            for point in scene.attackRange ?? [] { addOverlay(at: point, image: attackAlpha) }
            configureCursor(attacking: scene.affectedUnitIDs == nil)
            selectedPoint = scene.focus
            selectedLayer.isHidden = false
            layer.addSublayer(selectedLayer)
            setNeedsLayout()
            layoutIfNeeded()
        }
        if tick == 0 { startAmbientEffects(for: scene) }
        if let change = scene.tileChange, tick >= change.tick,
           let tile = tileLayers.first(where: { $0.point == change.point }),
           let image = tileImages[change.tileID] {
            tile.layer.contents = image
        }
        if scene.kind == .healing { presentHealing(scene.healing ?? [], tick: tick) }
        if scene.kind == .levelUp { presentLevelUp(scene, tick: tick) }
        if scene.kind == .heavenFury { presentHeavenFury(scene, tick: tick) }
        if scene.kind == .camera, let focus = scene.focus { selectCell(x: focus.x, y: focus.y) }
        guard let id = scene.unitID, let unit = units.first(where: { $0.id == id }) else { return }
        if scene.kind == .disappearance { unitLayerByID[id]?.isHidden = true }
        if scene.kind == .hit, tick == 0, let visual = unitVisualByID[id] {
            visual.removeAnimation(forKey: "idle")
            let key = unit.type == 9
                ? "\(unit.fraction):\(unit.type):\(unit.resolvedCommanderVariant)"
                : "\(unit.fraction):\(unit.type)"
            if let first = visual.contents, let second = secondUnitIcons[key] {
                let hit = CAKeyframeAnimation(keyPath: "contents")
                hit.values = [first, second, first, second, first, second, first]
                hit.calculationMode = .discrete
                hit.duration = 0.396
                visual.add(hit, forKey: "story-hit")
            }
        }
        let names = scene.kind == .hit ? ["redspark"] : (scene.kind == .disappearance ? ["spark", "smoke"] : [])
        let scale = tileSize * imageScale / 24
        let cell = rect(for: unit.position)
        for name in names {
            if sceneEffects[name] == nil { sceneEffects[name] = try? OriginalSprite(name) }
            guard let sprite = sceneEffects[name] else { continue }
            let effect: CALayer
            if let existing = sceneEffectLayers[name] { effect = existing }
            else {
                effect = CALayer()
                effect.name = "campaign.effect.\(name)"
                effect.magnificationFilter = .nearest
                effect.minificationFilter = .nearest
                layer.addSublayer(effect)
                sceneEffectLayers[name] = effect
            }
            let frameTick = name == "smoke" ? tick / 2 : tick
            let lifetime = sprite.frames.count * (name == "redspark" ? 2 : 1)
            effect.isHidden = frameTick >= lifetime
            let frame = sprite.frames[frameTick % sprite.frames.count]
            effect.contents = frame.image.cgImage
            effect.frame = CGRect(x: cell.midX - sprite.size.width * scale / 2,
                                  y: cell.midY - sprite.size.height * scale / 2 - (name == "smoke" ? CGFloat(tick * 3) * scale : 0),
                                  width: frame.image.size.width * scale, height: frame.image.size.height * scale)
        }
    }

    private func presentLevelUp(_ scene: CampaignScenePresentation, tick: Int) {
        guard let id = scene.unitID, let unit = units.first(where: { $0.id == id }) else { return }
        selectCell(x: unit.x, y: unit.y)
        let cell = rect(for: unit.position)
        let scale = tileSize * imageScale / 24
        for name in ["levelup", "small_spark"] {
            if sceneEffects[name] == nil { sceneEffects[name] = try? OriginalSprite(name) }
        }
        // Original phases 1...5 place LEVEL UP four source pixels higher each tick.
        // The first four copies last 200 ms; the final copy lasts 1000 ms.
        for spawnTick in 0...tick {
            for name in (spawnTick < 5 ? ["levelup", "small_spark"] : ["small_spark"]) {
                guard let sprite = sceneEffects[name] else { continue }
                let lifetime = name == "levelup" ? (spawnTick == 4 ? 16 : 4) : sprite.frames.count
                let age = tick - spawnTick
                let key = "promotion-\(name)-\(spawnTick)"
                if age >= lifetime {
                    sceneEffectLayers.removeValue(forKey: key)?.removeFromSuperlayer()
                    continue
                }
                let effect: CALayer
                if let existing = sceneEffectLayers[key] { effect = existing }
                else {
                    effect = CALayer()
                    effect.name = "campaign.effect.\(name)"
                    effect.magnificationFilter = .nearest
                    effect.minificationFilter = .nearest
                    layer.addSublayer(effect)
                    sceneEffectLayers[key] = effect
                }
                let frame = sprite.frames[name == "levelup" ? 0 : age % sprite.frames.count]
                let point: CGPoint
                if name == "levelup" {
                    let mapRight = imageOrigin.x + CGFloat(map.width) * tileSize * imageScale
                    point = CGPoint(x: max(imageOrigin.x, min(mapRight - sprite.size.width * scale,
                                                             cell.midX - sprite.size.width * scale / 2)),
                                    y: max(imageOrigin.y, cell.minY - CGFloat((spawnTick + 1) * 4) * scale))
                } else {
                    // Visual jitter must not advance the saved combat/AI random generator.
                    point = CGPoint(x: cell.minX + CGFloat((spawnTick * 11 + id * 3) % 24) * scale,
                                    y: cell.minY + CGFloat((spawnTick * 7 + id * 5) % 24) * scale)
                }
                effect.contents = frame.image.cgImage
                effect.frame = CGRect(origin: CGPoint(x: point.x + frame.offset.x * scale, y: point.y + frame.offset.y * scale),
                                      size: CGSize(width: frame.image.size.width * scale, height: frame.image.size.height * scale))
            }
        }
    }

    private func presentHeavenFury(_ scene: CampaignScenePresentation, tick: Int) {
        guard let id = scene.unitID, let unit = units.first(where: { $0.id == id }) else { return }
        let impactTick = 12
        let cell = rect(for: unit.position)
        if sceneEffects["spark"] == nil { sceneEffects["spark"] = try? OriginalSprite("spark") }
        if let sprite = sceneEffects["spark"] {
            let key = "heaven-fury"
            let bolt: CALayer
            if let existing = sceneEffectLayers[key] { bolt = existing }
            else {
                bolt = CALayer()
                bolt.magnificationFilter = .nearest
                bolt.minificationFilter = .nearest
                layer.addSublayer(bolt)
                sceneEffectLayers[key] = bolt
            }
            let frame = sprite.frames[min(tick, impactTick - 1) % sprite.frames.count]
            let scale = tileSize * imageScale / 24
            let progress = CGFloat(min(tick, impactTick)) / CGFloat(impactTick)
            let startY = max(imageOrigin.y, cell.midY - 8 * tileSize * imageScale)
            let y = startY + (cell.midY - startY) * progress
            bolt.contents = frame.image.cgImage
            bolt.frame = CGRect(x: cell.midX - frame.image.size.width * scale / 2,
                                y: y - frame.image.size.height * scale / 2,
                                width: frame.image.size.width * scale,
                                height: frame.image.size.height * scale)
            bolt.isHidden = tick >= impactTick
        }
        if tick == impactTick {
            emit("b_smoke", at: unit.position, velocity: CGPoint(x: 0, y: -1), repeats: 2)
            emit("redspark", at: unit.position, repeats: 2)
        }
        if let damage = scene.damage, damage > 0, tick >= impactTick {
            presentDamage(damage, unit: unit, tick: tick - impactTick)
        }
    }

    private func presentDamage(_ amount: Int, unit: CampaignUnitState, tick: Int) {
        if sceneEffects["lchars"] == nil { sceneEffects["lchars"] = try? OriginalSprite("lchars") }
        guard let numbers = sceneEffects["lchars"] else { return }
        var displacement = 0, velocity = -4
        for _ in 0..<min(tick, 17) {
            displacement += velocity
            if displacement < 0 { velocity += 1 }
            else { displacement = 0; velocity = -velocity / 2 }
        }
        let key = "heaven-fury-damage"
        let effect: CALayer
        if let existing = sceneEffectLayers[key] { effect = existing }
        else {
            effect = CALayer()
            effect.magnificationFilter = .nearest
            let characters = Array("-\(amount)")
            let format = UIGraphicsImageRendererFormat()
            format.scale = HighResolutionUnitArt.scale
            let image = UIGraphicsImageRenderer(size: CGSize(width: characters.count * 8, height: 11), format: format).image { _ in
                for (index, character) in characters.enumerated() {
                    numbers.draw(frame: character == "-" ? 11 : (character.wholeNumberValue ?? 0),
                                 at: CGPoint(x: index * 8, y: 0))
                }
            }
            effect.contents = image.cgImage
            layer.addSublayer(effect)
            sceneEffectLayers[key] = effect
        }
        let scale = tileSize * imageScale / 24
        let cell = rect(for: unit.position)
        let width = CGFloat(String(amount).count + 1) * 8 * scale
        effect.frame = CGRect(x: cell.midX - width / 2,
                              y: cell.maxY + CGFloat(displacement - 11) * scale,
                              width: width, height: 11 * scale)
    }

    private func startAmbientEffects(for scene: CampaignScenePresentation) {
        if scene.kind == .blessing || scene.kind == .appearance {
            for id in scene.affectedUnitIDs ?? [] {
                if let unit = units.first(where: { $0.id == id }) { emit("spark", at: unit.position) }
            }
        }
        if scene.kind == .buildingDestruction, let point = scene.focus {
            emit("redspark", at: point)
        }
        guard let id = scene.unitID, let unit = units.first(where: { $0.id == id }) else { return }
        switch scene.kind {
        case .death:
            emit("spark", at: unit.position)
        case .summoning:
            for x in [-8, 8] {
                for y in [-8, 8] {
                    emit("spark", at: unit.position, offset: CGPoint(x: x, y: y),
                         velocity: CGPoint(x: x < 0 ? 1 : -1, y: y < 0 ? 1 : -1), repeats: 3)
                }
            }
        case .poison:
            emit("spark", at: unit.position)
            emit("status", at: unit.position, offset: CGPoint(x: 8, y: -9), fixedFrame: 0, bounce: true)
        default: break
        }
    }

    func finishScene(_ scene: CampaignScenePresentation) {
        if scene.kind == .death, let point = scene.focus {
            emit("smoke", at: point, velocity: CGPoint(x: 0, y: -3), ticksPerFrame: 2)
        }
    }

    private func emit(_ name: String, at point: GridPoint, offset: CGPoint = .zero,
                      velocity: CGPoint = .zero, repeats: Int = 1, ticksPerFrame: Int = 1,
                      fixedFrame: Int? = nil, bounce: Bool = false) {
        if sceneEffects[name] == nil { sceneEffects[name] = try? OriginalSprite(name) }
        guard let sprite = sceneEffects[name] else { return }
        let ticks = bounce ? 16 : sprite.frames.count * repeats * ticksPerFrame
        let effect = CALayer()
        effect.name = "campaign.effect.\(name)"
        effect.bounds = CGRect(origin: .zero, size: sprite.size)
        effect.magnificationFilter = .nearest
        effect.minificationFilter = .nearest
        effect.opacity = 0
        let format = UIGraphicsImageRendererFormat()
        format.scale = HighResolutionUnitArt.scale
        let frames = sprite.frames.indices.map { index in
            UIGraphicsImageRenderer(size: sprite.size, format: format).image { context in
                context.cgContext.interpolationQuality = .none
                sprite.draw(frame: index, at: .zero)
            }.cgImage!
        }
        let base = CGPoint(x: CGFloat(point.x * 24) + offset.x + sprite.size.width / 2,
                           y: CGFloat(point.y * 24) + offset.y + sprite.size.height / 2)
        var displacement = 0, speed = -4
        var positions: [NSValue] = []
        for tick in 0..<ticks {
            positions.append(NSValue(cgPoint: CGPoint(x: base.x + CGFloat(tick) * velocity.x,
                                                      y: base.y + CGFloat(tick) * velocity.y + CGFloat(displacement))))
            if bounce {
                displacement += speed
                if displacement < 0 { speed += 1 }
                else { displacement = 0; speed = -speed / 2 }
            }
        }
        let contents = CAKeyframeAnimation(keyPath: "contents")
        // Explicit tick boundaries also give the final sprite frame a full tick.
        let keyTimes = (0...ticks).map { NSNumber(value: Double($0) / Double(ticks)) }
        contents.values = (0...ticks).map { frames[fixedFrame ?? ((min($0, ticks - 1) / ticksPerFrame) % frames.count)] }
        contents.keyTimes = keyTimes
        contents.calculationMode = .discrete
        let position = CAKeyframeAnimation(keyPath: "position")
        position.values = positions + [positions[positions.count - 1]]
        position.keyTimes = keyTimes
        position.calculationMode = .discrete
        let opacity = CABasicAnimation(keyPath: "opacity")
        opacity.fromValue = 1
        opacity.toValue = 1
        let duration = Double(ticks) * 0.066
        let animations = [contents, position, opacity]
        animations.forEach { $0.duration = duration }
        let group = CAAnimationGroup()
        group.animations = animations
        group.duration = duration
        ambientEffects.addSublayer(effect)
        layer.addSublayer(ambientEffects)
        effect.add(group, forKey: "original-effect")
    }

    private func rect(for point: GridPoint) -> CGRect {
        CGRect(
            x: imageOrigin.x + CGFloat(point.x) * tileSize * imageScale,
            y: imageOrigin.y + CGFloat(point.y) * tileSize * imageScale,
            width: tileSize * imageScale,
            height: tileSize * imageScale
        )
    }

    private func imageLayer(contents: CGImage) -> CALayer {
        let image = CALayer()
        image.contents = contents
        image.contentsGravity = .resize
        image.magnificationFilter = .nearest
        image.minificationFilter = .nearest
        image.bounds = CGRect(x: 0, y: 0, width: tileSize, height: tileSize)
        return image
    }

    @objc private func refreshMotionPreference() {
        for unit in units { updateUnitMotion(unit) }
    }

    private func updateUnitMotion(_ unit: CampaignUnitState, duration: TimeInterval? = nil) {
        guard let visual = unitVisualByID[unit.id] else { return }
        visual.removeAnimation(forKey: "idle")
        visual.removeAnimation(forKey: "travel")
        let moving = movingUnitIDs.contains(unit.id)
        guard !unit.isCorpse, moving || !unit.hasActed,
              let animation = CampaignUnitMotion.animation(type: unit.type, unitID: unit.id,
                  size: visual.bounds.width, moving: moving, reduceMotion: UIAccessibility.isReduceMotionEnabled) else { return }
        if let duration {
            animation.beginTime = 0
            animation.repeatCount = 0
            animation.repeatDuration = duration
        }
        visual.add(animation, forKey: moving ? "travel" : "idle")
    }
}

enum CampaignUnitMotion {
    static func animation(type: Int, unitID: Int, size: CGFloat, moving: Bool,
                          reduceMotion: Bool, time: TimeInterval = CACurrentMediaTime()) -> CAKeyframeAnimation? {
        guard !reduceMotion else { return nil }
        let floating = [4, 8, 11].contains(type)
        let heavy = [6, 7].contains(type)
        let duration: TimeInterval = moving ? (floating ? 0.72 : heavy ? 0.66 : 0.44)
            : (floating ? 2.4 : heavy ? 3.2 : type == 2 ? 1.8 : 2.0)
        let animation = CAKeyframeAnimation(keyPath: "transform")
        animation.values = (0...24).map { index in
            let angle = CGFloat(index == 24 ? 0 : index) / 24 * .pi * 2
            let pulse = (1 - cos(angle)) / 2
            var lift: CGFloat = 0
            var sway: CGFloat = 0
            var rotation: CGFloat = 0
            var stretch: CGFloat = 0
            if floating {
                lift = -size * (moving ? 0.04 : 0.025) * pulse
                sway = size * 0.006 * sin(angle)
                rotation = (type == 8 ? 0.012 : 0.006) * sin(angle)
            } else if moving {
                lift = -size * (heavy ? 0.008 : 0.022) * pow(sin(angle), 2)
                rotation = (heavy ? 0.006 : 0.018) * sin(angle)
                stretch = (heavy ? 0.003 : 0.01) * pulse
            } else {
                stretch = (heavy ? 0.004 : type == 2 ? 0.028 : 0.018) * pulse
            }
            var transform = CATransform3DMakeTranslation(sway, lift, 0)
            transform = CATransform3DRotate(transform, rotation, 0, 0, 1)
            transform = CATransform3DScale(transform, 1 - stretch * 0.25, 1 + stretch, 1)
            return NSValue(caTransform3D: transform)
        }
        animation.calculationMode = .linear
        animation.duration = duration
        animation.repeatCount = .infinity
        let phase = (Double(unitID) * 0.61803398875).truncatingRemainder(dividingBy: 1) * duration
        animation.beginTime = time - (time + phase).truncatingRemainder(dividingBy: duration)
        return animation
    }
}

enum UnitSpriteImageProcessor {
    static func actedVariant(of image: CGImage) -> CGImage? {
        let width = image.width
        let height = image.height
        guard width > 0, height > 0,
              let colorSpace = CGColorSpace(name: CGColorSpace.sRGB) else { return nil }
        let bitmapInfo = CGBitmapInfo.byteOrder32Big.union(
            CGBitmapInfo(rawValue: CGImageAlphaInfo.premultipliedLast.rawValue)
        )
        guard let context = CGContext(
            data: nil,
            width: width,
            height: height,
            bitsPerComponent: 8,
            bytesPerRow: width * 4,
            space: colorSpace,
            bitmapInfo: bitmapInfo.rawValue
        ) else { return nil }
        context.interpolationQuality = .none
        context.draw(image, in: CGRect(x: 0, y: 0, width: width, height: height))

        guard let pixels = context.data?.assumingMemoryBound(to: UInt8.self) else { return nil }
        for y in 0..<height {
            for x in 0..<width {
                let offset = (y * width + x) * 4
                let alpha = Int(pixels[offset + 3])
                guard alpha > 0 else { continue }
                let red = min(255, (Int(pixels[offset]) * 255 + alpha / 2) / alpha)
                let green = min(255, (Int(pixels[offset + 1]) * 255 + alpha / 2) / alpha)
                let blue = min(255, (Int(pixels[offset + 2]) * 255 + alpha / 2) / alpha)
                if red == 244, green == 244, blue == 230 { continue }

                let gray = (red + green + blue) / 3
                let premultipliedGray = (gray * alpha + 127) / 255
                pixels[offset] = UInt8(premultipliedGray)
                pixels[offset + 1] = UInt8(premultipliedGray)
                pixels[offset + 2] = UInt8(premultipliedGray)
            }
        }
        return context.makeImage()
    }
}

@MainActor
final class StartupErrorViewController: UIViewController {
    var onExit: (() -> Void)?
    private let message: String

    init(message: String) {
        self.message = message
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        nil
    }

    override var prefersStatusBarHidden: Bool { true }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = PixelPalette.ink
        let label = UILabel()
        label.text = message
        label.textColor = PixelPalette.parchment
        label.textAlignment = .center
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(label)
        let back = makeActionButton(title: GameLocalization.text("Return to Menu"), identifier: "error.back") { [weak self] in
            self?.onExit?()
        }
        back.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(back)
        NSLayoutConstraint.activate([
            label.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 24),
            label.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -24),
            label.centerYAnchor.constraint(equalTo: view.centerYAnchor, constant: -32),
            back.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 24),
            back.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -24),
            back.topAnchor.constraint(equalTo: label.bottomAnchor, constant: 24),
        ])
    }
}
