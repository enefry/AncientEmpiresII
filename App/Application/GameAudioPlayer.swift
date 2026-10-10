import AEContent
import AVFAudio
import UIKit

/// Plays AAC files rendered from the original MIDI sequences with Apple's General MIDI sound bank.
@MainActor
final class GameAudioPlayer: NSObject {
    static let shared = GameAudioPlayer()
    private(set) var resourceName: String?
    private var player: AVAudioPlayer?
    private var generation = 0
    private var completedResource: String?
    private let oneShotResources = Set(["bg_story", "victory", "gameover", "game_complete"])

    private override init() {
        super.init()
        NotificationCenter.default.addObserver(self, selector: #selector(pause), name: UIApplication.willResignActiveNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(resume), name: UIApplication.didBecomeActiveNotification, object: nil)
    }

    @objc private func pause() { generation += 1; player?.stop() }
    @objc private func resume() { refreshPreference() }

    func play(_ name: String) {
        if resourceName == name && player?.isPlaying == true && GamePreferences.enabled(.music) { return }
        if resourceName == name && completedResource == name && GamePreferences.enabled(.music) { return }
        if resourceName != name { completedResource = nil }
        generation += 1
        player?.stop()
        player = nil
        resourceName = name
        guard GamePreferences.enabled(.music) else { completedResource = nil; return }
        do {
            try AVAudioSession.sharedInstance().setCategory(.ambient, mode: .default)
            try AVAudioSession.sharedInstance().setActive(true)
            let audio = try AVAudioPlayer(data: GameResources.musicData(named: name))
            audio.numberOfLoops = oneShotResources.contains(name) ? 0 : -1
            audio.prepareToPlay()
            player = audio
            beginPlayback(audio, resource: name, generation: generation)
        } catch {
            // Playback failure must not stop input, save restoration, or the game clock.
            NSLog("Rendered music %@ could not play: %@", name, String(describing: error))
        }
    }

    func refreshPreference() {
        if let resourceName { play(resourceName) }
    }

    private func beginPlayback(_ audio: AVAudioPlayer, resource: String, generation expected: Int) {
        guard expected == generation, GamePreferences.enabled(.music), UIApplication.shared.applicationState == .active else { return }
        audio.currentTime = 0
        guard audio.play() else {
            NSLog("Rendered music %@ could not start", resource)
            return
        }
        guard oneShotResources.contains(resource) else { return }
        DispatchQueue.main.asyncAfter(deadline: .now() + audio.duration) { [weak self, weak audio] in
            guard let self, let audio,
                  expected == self.generation,
                  self.player === audio,
                  !audio.isPlaying else { return }
            self.completedResource = resource
        }
    }
}
