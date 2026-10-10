import AECore
import Foundation

@MainActor
final class CampaignSaveStore {
    static let shared = CampaignSaveStore()
    static let manualSlotCount = 10 // v2.5 MainDisplayable.MAX_SAVES

    private let fileURL: URL
    private let progressURL: URL
    private let encoder: JSONEncoder
    private let decoder = JSONDecoder()

    init(fileManager: FileManager = .default, directoryURL: URL? = nil) {
        let support = fileManager.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
        let directory = directoryURL ?? support.appendingPathComponent("AncientEmpiresII", isDirectory: true)
        fileURL = directory.appendingPathComponent("campaign-autosave.json")
        progressURL = directory.appendingPathComponent("campaign-progress.json")
        encoder = JSONEncoder()
        encoder.outputFormatting = [.sortedKeys]
        try? fileManager.createDirectory(at: directory, withIntermediateDirectories: true)
    }

    var hasSave: Bool { FileManager.default.fileExists(atPath: fileURL.path) }
    var highestUnlockedMission: Int {
        guard FileManager.default.fileExists(atPath: progressURL.path),
              let data = try? Data(contentsOf: progressURL),
              let progress = try? decoder.decode(CampaignProgress.self, from: data) else { return 0 }
        return min(7, max(0, progress.highestUnlockedMission))
    }

    func unlock(mission: Int) throws {
        let value = min(7, max(highestUnlockedMission, mission))
        let progress = CampaignProgress(highestUnlockedMission: value)
        try FileManager.default.createDirectory(
            at: progressURL.deletingLastPathComponent(),
            withIntermediateDirectories: true
        )
        try encoder.encode(progress).write(to: progressURL, options: .atomic)
    }

    func hasManualSave(slot: Int) -> Bool {
        guard let url = try? url(for: slot) else { return false }
        return FileManager.default.fileExists(atPath: url.path)
    }

    func load(slot: Int? = nil) throws -> CampaignSnapshot? {
        let source = try url(for: slot)
        guard FileManager.default.fileExists(atPath: source.path) else { return nil }
        let snapshot = try decoder.decode(CampaignSnapshot.self, from: Data(contentsOf: source))
        guard snapshot.schemaVersion == CampaignSnapshot.currentSchemaVersion else {
            throw SaveError.unsupportedVersion(snapshot.schemaVersion)
        }
        return snapshot
    }

    func save(_ snapshot: CampaignSnapshot, slot: Int? = nil) throws {
        let destination = try url(for: slot)
        try FileManager.default.createDirectory(
            at: fileURL.deletingLastPathComponent(),
            withIntermediateDirectories: true
        )
        try encoder.encode(snapshot).write(to: destination, options: .atomic)
    }

    func clear() throws {
        guard hasSave else { return }
        try FileManager.default.removeItem(at: fileURL)
    }

    private func url(for slot: Int?) throws -> URL {
        guard let slot else { return fileURL }
        guard (0..<Self.manualSlotCount).contains(slot) else { throw SaveError.invalidSlot }
        return fileURL.deletingLastPathComponent().appendingPathComponent("campaign-slot-\(slot + 1).json")
    }

    enum SaveError: Error {
        case invalidSlot
        case unsupportedVersion(Int)
    }

    private struct CampaignProgress: Codable {
        let highestUnlockedMission: Int
    }
}
