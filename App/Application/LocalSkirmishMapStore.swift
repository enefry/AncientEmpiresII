import AECore
import Foundation

@MainActor
final class LocalSkirmishMapStore {
    static let shared = LocalSkirmishMapStore()
    private let directory: URL

    init(directory: URL? = nil) {
        self.directory = directory ?? FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
            .appendingPathComponent("AncientEmpiresII/LocalMaps", isDirectory: true)
    }

    func maps() throws -> [CampaignMap] {
        guard FileManager.default.fileExists(atPath: directory.path) else { return [] }
        return try FileManager.default.contentsOfDirectory(at: directory, includingPropertiesForKeys: nil)
            .filter { $0.pathExtension == "json" }.map { url in
                let map = try JSONDecoder().decode(CampaignMap.self, from: Data(contentsOf: url))
                try SkirmishMapImporter.validate(map)
                return map
            }.sorted { $0.name.localizedStandardCompare($1.name) == .orderedAscending }
    }

    @discardableResult
    func importMap(from url: URL) throws -> CampaignMap {
        let access = url.startAccessingSecurityScopedResource()
        defer { if access { url.stopAccessingSecurityScopedResource() } }
        let coordinator = NSFileCoordinator()
        var coordinationError: NSError?
        var imported: Result<CampaignMap, Error>?
        coordinator.coordinate(readingItemAt: url, options: .withoutChanges, error: &coordinationError) { source in
            imported = Result {
                let size = try source.resourceValues(forKeys: [.fileSizeKey]).fileSize ?? 0
                guard size <= SkirmishMapImporter.maximumFileSize else { throw SkirmishError.oversizedFile }
                return try SkirmishMapImporter.decode(Data(contentsOf: source), filename: source.lastPathComponent)
            }
        }
        if let coordinationError { throw coordinationError }
        guard let imported else { throw SkirmishError.invalidMap }
        let map = try imported.get()
        try FileManager.default.createDirectory(at: directory, withIntermediateDirectories: true)
        try JSONEncoder().encode(map).write(to: file(for: map), options: .atomic)
        return map
    }

    func delete(_ map: CampaignMap) throws { try FileManager.default.removeItem(at: file(for: map)) }

    private func file(for map: CampaignMap) throws -> URL {
        guard map.id.hasPrefix("local-"), UUID(uuidString: String(map.id.dropFirst(6))) != nil else {
            throw SkirmishError.invalidMap
        }
        return directory.appendingPathComponent(map.id + ".json")
    }
}
