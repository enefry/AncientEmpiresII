import AEContent
import AVFAudio
import Testing

@MainActor
struct RenderedMusicTests {
    @Test func bundledAACTracksDecodeWithExpectedDurations() throws {
        let expectedDurations = [
            "main_theme": 90.958,
            "bg_story": 20.213,
            "bg_good": 84.008,
            "bg_bad": 56.571,
            "battle_good": 12.800,
            "battle_bad": 12.800,
            "victory": 3.462,
            "gameover": 12.202,
            "game_complete": 64.000,
        ]
        #expect(Set(GameResources.musicResourceNames) == Set(expectedDurations.keys))
        for (name, expectedDuration) in expectedDurations {
            let player = try AVAudioPlayer(data: GameResources.musicData(named: name))
            player.prepareToPlay()
            #expect(abs(player.duration - expectedDuration) < 0.05)
        }
    }

    @Test func unknownMusicNameIsRejected() {
        #expect(throws: (any Error).self) {
            try GameResources.musicData(named: "../main_theme")
        }
    }
}
