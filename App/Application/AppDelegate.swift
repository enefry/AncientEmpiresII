import AECore
import AEContent
import UIKit

@main
@MainActor
final class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        return true
    }

    func application(
        _ application: UIApplication,
        configurationForConnecting connectingSceneSession: UISceneSession,
        options: UIScene.ConnectionOptions
    ) -> UISceneConfiguration {
        let configuration = UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
        configuration.delegateClass = SceneDelegate.self
        return configuration
    }

    func applicationWillTerminate(_ application: UIApplication) {
        application.connectedScenes.compactMap { $0.delegate as? SceneDelegate }.forEach { $0.saveActiveCampaign() }
    }
}

@MainActor
final class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    private var coordinator: AppCoordinator?

    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = scene as? UIWindowScene else { return }
        let window = UIWindow(windowScene: windowScene)
        let coordinator = AppCoordinator(window: window)
        self.window = window
        self.coordinator = coordinator
        coordinator.start()
    }

    func sceneWillResignActive(_ scene: UIScene) {
        saveActiveCampaign()
    }

    func sceneDidEnterBackground(_ scene: UIScene) {
        saveActiveCampaign()
    }

    func sceneDidDisconnect(_ scene: UIScene) {
        saveActiveCampaign()
    }

    func saveActiveCampaign() {
        coordinator?.saveActiveCampaign()
    }
}

@MainActor
final class AppCoordinator {
    private static let seedArgumentPrefix = "--campaign-seed="
    private static let unlockArgumentPrefix = "--campaign-unlock="
    private static let highestImplementedMission = 7

    private let window: UIWindow
    private let navigationController = GameNavigationController()
    private let saveStore: CampaignSaveStore

    init(window: UIWindow, saveStore: CampaignSaveStore = .shared) {
        self.window = window
        self.saveStore = saveStore
        navigationController.setNavigationBarHidden(true, animated: false)
    }

    func start() {
        window.rootViewController = navigationController
        window.makeKeyAndVisible()
        showTitle()
    }

    private func resumeSavedGame() {
        do {
            guard let snapshot = try saveStore.load() else { return }
            continueCampaign(snapshot: snapshot)
        } catch {
            let controller = StartupErrorViewController(message: GameLocalization.text("The autosaved battle could not be restored. Manual saves are available from LOAD GAME."))
            controller.onExit = { [weak self] in self?.showMenu() }
            navigationController.setViewControllers([controller], animated: false)
        }
    }

    func saveActiveCampaign() {
        // The battle remains the navigation root while pause/settings/save panels are open.
        (navigationController.topViewController as? CampaignMapViewController)?.saveSnapshot()
    }

    func showTitle() {
        let controller = TitleViewController()
        controller.onContinue = { [weak self] in self?.showMenu() }
        navigationController.setViewControllers([controller], animated: false)
    }

    private func showMenu() {
        GameAudioPlayer.shared.play("main_theme")
        var unlocked = saveStore.highestUnlockedMission
#if DEBUG
        if let argument = ProcessInfo.processInfo.arguments.first(where: { $0.hasPrefix(Self.unlockArgumentPrefix) }),
           let value = Int(argument.dropFirst(Self.unlockArgumentPrefix.count)) {
            unlocked = max(unlocked, value)
        }
#endif
        let highest = min(Self.highestImplementedMission, unlocked)
        let controller = MainMenuViewController(unlockedMissionIndices: Array(0...highest), canContinueGame: saveStore.hasSave)
        controller.onContinueGame = { [weak self] in self?.resumeSavedGame() }
        controller.onStartSkirmish = { [weak self] map, configuration in
            guard let self else { return }
            let session = try CampaignSession(map: map, profiles: GameResources.campaignUnitProfiles(),
                rules: GameResources.campaignRuleset(), seed: self.newCampaignSeed, skirmish: configuration)
            // Validate both the session and renderer before replacing any saved battle.
            let battle = try self.makeBattleController(session)
            try self.saveStore.save(session.snapshot)
            self.navigationController.dismiss(animated: false) {
                self.navigationController.setViewControllers([battle], animated: false)
            }
        }
        controller.onStartCampaign = { [weak self] mission in self?.startCampaign(mission) }
        controller.onLoadCampaign = { [weak self] snapshot in self?.continueCampaign(snapshot: snapshot) }
        controller.onReplayTitle = { [weak self] in self?.showTitle() }
        navigationController.setViewControllers([controller], animated: false)
    }

    private func startCampaign(_ mission: Int) {
        do {
            let map = try GameResources.campaignMap(named: "m\(mission)")
            let session = try CampaignSession(
                map: map,
                profiles: GameResources.campaignUnitProfiles(),
                rules: GameResources.campaignRuleset(),
                seed: newCampaignSeed,
                difficulty: GamePreferences.aiDifficulty
            )
            // Replace the previous autosave only once the new session is valid.
            // This also protects progress if the app closes during the introduction.
            try saveStore.save(session.snapshot)
            let campaignTitle = (try? GameResources.campaignStoryText(id: 121 + mission)) ?? GameResources.mapName(map).uppercased()
            let introduction: OriginalIntroductionViewController
            switch mission {
            case 0: introduction = OriginalIntroductionViewController(illustrationID: 2, chapterTitle: campaignTitle)
            case 3: introduction = OriginalIntroductionViewController(illustrationID: 3, chapterTitle: campaignTitle)
            case 7: introduction = OriginalIntroductionViewController(illustrationID: 4, chapterTitle: campaignTitle)
            default: introduction = OriginalIntroductionViewController(illustrationID: nil, chapterTitle: GameResources.mapName(map))
            }
            introduction.onComplete = { [weak self] in self?.presentCampaign(session) }
            navigationController.setViewControllers([introduction], animated: false)
        } catch {
            let controller = StartupErrorViewController(message: GameLocalization.text("Campaign data could not be loaded."))
            controller.onExit = { [weak self] in self?.showMenu() }
            navigationController.setViewControllers([controller], animated: true)
        }
    }

    private var newCampaignSeed: Int64 {
#if DEBUG
        if let argument = ProcessInfo.processInfo.arguments.first(where: {
            $0.hasPrefix(Self.seedArgumentPrefix)
        }), let seed = Int64(String(argument.dropFirst(Self.seedArgumentPrefix.count))) {
            return seed
        }
#endif
        return Int64(Date().timeIntervalSince1970 * 1_000)
    }

    private func continueCampaign(snapshot: CampaignSnapshot) {
        do {
            let session = try CampaignSession(
                map: snapshot.skirmishMap ?? GameResources.campaignMap(named: snapshot.mapID),
                profiles: GameResources.campaignUnitProfiles(),
                rules: GameResources.campaignRuleset(),
                snapshot: snapshot
            )
            presentCampaign(session)
        } catch {
            let controller = StartupErrorViewController(message: GameLocalization.text("The saved battle could not be restored."))
            controller.onExit = { [weak self] in self?.showMenu() }
            navigationController.setViewControllers([controller], animated: true)
        }
    }

    private func presentCampaign(_ session: CampaignSession) {
        do {
            let controller = try makeBattleController(session)
            try saveStore.save(session.snapshot)
            navigationController.setViewControllers([controller], animated: true)
        } catch {
            let controller = StartupErrorViewController(message: GameLocalization.text("Campaign data could not be loaded."))
            controller.onExit = { [weak self] in self?.showMenu() }
            navigationController.setViewControllers([controller], animated: true)
        }
    }

    private func makeBattleController(_ session: CampaignSession) throws -> CampaignMapViewController {
        let controller = try CampaignMapViewController(session: session)
        controller.onSnapshotChange = { [weak self, weak controller] snapshot in
            do { try self?.saveStore.save(snapshot) }
            catch { controller?.showSaveFailure() }
        }
        controller.onExit = { [weak self] in self?.showMenu() }
        if !session.isSkirmish {
            controller.onMissionComplete = { [weak self] in self?.finishMission(mapID: session.map.id) }
        }
        return controller
    }

    private func finishMission(mapID: String) {
        let mission = Int(mapID.dropFirst()) ?? 0
        let next = mission + 1
        do {
            try saveStore.unlock(mission: next)
            try saveStore.clear()
            if mission == 7 {
                let ending = OriginalCampaignEndingViewController()
                ending.onComplete = { [weak self] in self?.showMenu() }
                navigationController.setViewControllers([ending], animated: false)
            } else if next <= Self.highestImplementedMission { startCampaign(next) }
            else { showMenu() }
        } catch {
            showMenu()
        }
    }
}

@MainActor
private final class GameNavigationController: UINavigationController {
    override var childForStatusBarHidden: UIViewController? { topViewController }
}
