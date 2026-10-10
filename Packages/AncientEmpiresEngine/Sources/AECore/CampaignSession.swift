import Foundation

public struct CampaignUnitProfile: Equatable, Sendable {
    public let id: Int
    public let moveBudget: Int
    public let attackMinimum: Int
    public let attackMaximum: Int
    public let baseDefense: Int
    public let minimumRange: Int
    public let maximumRange: Int
    public let characterCount: Int
    public let cost: Int
    public let propertyBits: Set<Int>

    public init(
        id: Int,
        moveBudget: Int,
        attackMinimum: Int,
        attackMaximum: Int,
        baseDefense: Int,
        minimumRange: Int,
        maximumRange: Int,
        characterCount: Int,
        cost: Int = 0,
        propertyBits: Set<Int> = []
    ) {
        self.id = id
        self.moveBudget = moveBudget
        self.attackMinimum = attackMinimum
        self.attackMaximum = attackMaximum
        self.baseDefense = baseDefense
        self.minimumRange = minimumRange
        self.maximumRange = maximumRange
        self.characterCount = characterCount
        self.cost = cost
        self.propertyBits = propertyBits
    }

    public var canFly: Bool { propertyBits.contains(0) }
    public var isAmphibious: Bool { propertyBits.contains(1) }
    public var canRaiseSkeleton: Bool { propertyBits.contains(5) }
    public var hasAntiAir: Bool { propertyBits.contains(6) }

    public func quality(atRank rank: Int) -> Int {
        attackMinimum + attackMaximum + baseDefense + rank * 6
    }

    public func nextRankExperience(atRank rank: Int) -> Int {
        quality(atRank: rank) * 100 * 2 / 3
    }
}

public struct CampaignRuleset: Sendable {
    public let terrainByTileID: [Int: Int]
    public let movementCostByTerrainID: [Int: Int]
    public let defenseByTerrainID: [Int: Int]
    public let waterTerrainID: Int
    public let mountainTerrainID: Int

    public init(
        terrainByTileID: [Int: Int],
        movementCostByTerrainID: [Int: Int],
        defenseByTerrainID: [Int: Int],
        waterTerrainID: Int = 5,
        mountainTerrainID: Int = 4
    ) {
        self.terrainByTileID = terrainByTileID
        self.movementCostByTerrainID = movementCostByTerrainID
        self.defenseByTerrainID = defenseByTerrainID
        self.waterTerrainID = waterTerrainID
        self.mountainTerrainID = mountainTerrainID
    }
}

public struct CampaignUnitState: Codable, Equatable, Sendable {
    public let id: Int
    public let type: Int
    public let fraction: Int
    public var x: Int
    public var y: Int
    public var hitPoints: Int
    public var rank: Int
    public var experience: Int
    public var hasMoved: Bool
    public var hasActed: Bool
    public var commanderVariant: Int?
    public var resolvedCommanderVariant: Int { commanderVariant ?? min(3, max(0, fraction - 1)) }
    public var revivalCost: Int?
    /// Java buyUnit changes health before plotRoute changes corpse state. This
    /// matters when its AI revival loop buys several registered kings at once.
    public var revivalAwaitingMovement: Bool?
    public var corpseTransitionsRemaining: Int?
    /// Removed units stay in the save's identity registry for story references.
    public var removedFromMap: Bool?
    /// Original effectState: bit 0 poison, bit 1 wisp blessing.
    public var effects: Int?
    public var poisonSourceFraction: Int?

    public init(
        id: Int,
        type: Int,
        fraction: Int,
        x: Int,
        y: Int,
        hitPoints: Int = 100,
        rank: Int = 0,
        experience: Int = 0,
        hasMoved: Bool = false,
        hasActed: Bool = false
    ) {
        self.id = id
        self.type = type
        self.fraction = fraction
        self.x = x
        self.y = y
        self.hitPoints = hitPoints
        self.rank = rank
        self.experience = experience
        self.hasMoved = hasMoved
        self.hasActed = hasActed
    }

    public var isAlive: Bool { hitPoints > 0 && revivalAwaitingMovement != true && removedFromMap != true }
    public var isCorpse: Bool { !isAlive && removedFromMap != true && (corpseTransitionsRemaining ?? 0) > 0 }
    public var isPoisoned: Bool { (effects ?? 0) & 1 != 0 }
    public var isBlessed: Bool { (effects ?? 0) & 2 != 0 }
    public var statusOffenseBonus: Int { (isPoisoned ? -10 : 0) + (isBlessed ? 10 : 0) }
    public var statusDefenseBonus: Int { isPoisoned ? -10 : 0 }
    public var position: GridPoint { GridPoint(x: x, y: y) }
    /// Unit.setRank selects one of four names, changing at ranks 2, 4 and 6.
    public var nameTextID: Int {
        type == 9 ? 93 + resolvedCommanderVariant : 139 + type * 4 + min(3, max(0, rank / 2))
    }
}

public enum CampaignPhase: String, Codable, Sendable {
    case opening
    case playerTurn
    case enemyTurn
    case story
    case victory
    case defeat
}

public enum CampaignStoryMode: String, Codable, Sendable {
    case opening
    case reinforcements
    case ending
}

public struct CampaignSelection: Codable, Equatable, Sendable {
    public let unitID: Int
    public var rangeReady: Bool
    public var moveOrigin: GridPoint?

    public init(unitID: Int, rangeReady: Bool = false) {
        self.unitID = unitID
        self.rangeReady = rangeReady
        moveOrigin = nil
    }
}

public struct CampaignSnapshot: Codable, Equatable, Sendable {
    public static let currentSchemaVersion = 1

    public let schemaVersion: Int
    public let mapID: String
    public var units: [CampaignUnitState]
    public var phase: CampaignPhase
    public var storyMode: CampaignStoryMode?
    public var storyMessages: [Int]
    public var storyIndex: Int
    public var currentTurn: Int
    public var activeFraction: Int
    public var reinforcementWave: Int
    public var nextUnitID: Int
    public var randomState: UInt64
    public var m0ScriptState: Int?
    public var pendingCampaignHelpID: Int?
    public var lastFinishedMoveUnitID: Int?
    public var selection: CampaignSelection?
    public var pendingPresentations: [CampaignPresentation]?
    public var storyPresentationsEnabled: Bool?
    /// Optional for v1 saves written before the economy was restored. m0 starts at zero.
    public var moneyByFraction: [Int: Int]?
    public var tileOverrides: [Int: Int]?
    /// Remains set until the purchased unit finishes its action; returning to the
    /// castle from the action menu is allowed, abandoning deployment is not.
    public var recruitingUnitID: Int?
    public var allowedUnitType: Int?
    public var unitCap: Int?
    public var missionRuntime: CampaignMissionRuntime?
    public var skirmish: SkirmishConfiguration?
    public var skirmishMap: CampaignMap?
    public var winningTeam: Int?
    public var aiDifficulty: AIDifficulty?

    public init(
        mapID: String,
        units: [CampaignUnitState],
        phase: CampaignPhase = .playerTurn,
        storyMode: CampaignStoryMode? = nil,
        storyMessages: [Int] = [],
        storyIndex: Int = -1,
        currentTurn: Int = 0,
        activeFraction: Int = 1,
        reinforcementWave: Int = 0,
        nextUnitID: Int? = nil,
        randomState: UInt64,
        m0ScriptState: Int? = nil,
        pendingCampaignHelpID: Int? = nil,
        lastFinishedMoveUnitID: Int? = nil,
        selection: CampaignSelection? = nil
    ) {
        schemaVersion = Self.currentSchemaVersion
        self.mapID = mapID
        self.units = units
        self.phase = phase
        self.storyMode = storyMode
        self.storyMessages = storyMessages
        self.storyIndex = storyIndex
        self.currentTurn = currentTurn
        self.activeFraction = activeFraction
        self.reinforcementWave = reinforcementWave
        self.nextUnitID = nextUnitID ?? ((units.map(\.id).max() ?? -1) + 1)
        self.randomState = randomState
        self.m0ScriptState = m0ScriptState
        self.pendingCampaignHelpID = pendingCampaignHelpID
        self.lastFinishedMoveUnitID = lastFinishedMoveUnitID
        self.selection = selection
        pendingPresentations = nil
        storyPresentationsEnabled = nil
        moneyByFraction = nil
    }

    public var currentStoryMessageID: Int? {
        guard storyMessages.indices.contains(storyIndex) else { return nil }
        return storyMessages[storyIndex]
    }
}

public struct CampaignAttackResult: Equatable, Sendable {
    public let attackerID: Int
    public let targetID: Int
    public let damage: Int
    public let counterDamage: Int
}

public struct CampaignAIAction: Equatable, Sendable {
    public let unitID: Int
    public let origin: GridPoint
    public let destination: GridPoint
    public let targetID: Int?
    public let estimatedScore: Int
    public let strategicTarget: GridPoint?
    public var summonedCorpseID: Int? = nil
}

private struct CampaignAIStrategy {
    let point: GridPoint
    var unitID: Int? = nil
    var supportBuilding: GridPoint? = nil
    var captureBuilding: GridPoint? = nil
}

private struct CampaignAITurnContext {
    var unitSupport: [Int: Int] = [:]
    var buildingSupport: [GridPoint: Int] = [:]
    var buildingReservations: [GridPoint: Int] = [:]
    var reservedDistances: [GridPoint: Int] = [:]
}

private struct CampaignEnemyChoice {
    let destination: GridPoint
    let targetID: Int?
    let score: Int
    let strategy: CampaignAIStrategy?
    var summonedCorpseID: Int? = nil
}

public enum CampaignSessionError: Error, Equatable, Sendable {
    case wrongMap
    case unsupportedSnapshotVersion
    case invalidSnapshot
    case gameNotReady
    case wrongTurn
    case unitUnavailable
    case destinationUnreachable
    case targetUnavailable
    case targetOutOfRange
    case unitAlreadyActed
    case buildingActionUnavailable
    case recruitmentUnavailable
    case summoningUnavailable
}

public struct CampaignSession: Sendable {
    public internal(set) var snapshot: CampaignSnapshot
    private let originalMap: CampaignMap
    public var map: CampaignMap { originalMap.applying(tileOverrides: snapshot.tileOverrides ?? [:]) }
    public var presentationMap: CampaignMap {
        originalMap.applying(tileOverrides: pendingPresentation?.tileOverridesBefore ?? snapshot.tileOverrides ?? [:])
    }
    private let profiles: [Int: CampaignUnitProfile]
    private let rules: CampaignRuleset

    public init(
        map: CampaignMap,
        profiles: [Int: CampaignUnitProfile],
        rules: CampaignRuleset,
        seed: Int64 = 1,
        difficulty: AIDifficulty = .normal,
        skirmish configuration: SkirmishConfiguration? = nil,
        snapshot restored: CampaignSnapshot? = nil
    ) throws {
        self.originalMap = map
        self.profiles = profiles
        self.rules = rules
        if map.kind == "skirmish" {
            guard let settings = restored?.skirmish ?? configuration else { throw SkirmishError.invalidConfiguration }
            try settings.validate(for: map)
        }
        if let restored {
            if let settings = restored.skirmish {
                guard restored.skirmishMap == map, settings.players.contains(where: { $0.fraction == restored.activeFraction && $0.control != .off }),
                      restored.units.allSatisfy({ unit in
                          settings.players.contains { $0.fraction == unit.fraction && $0.control != .off }
                              && ((!unit.isAlive && unit.type == 9) || ((0..<map.width).contains(unit.x) && (0..<map.height).contains(unit.y)))
                      }), Set(restored.units.map(\.id)).count == restored.units.count,
                      restored.nextUnitID > (restored.units.map(\.id).max() ?? -1) else { throw CampaignSessionError.invalidSnapshot }
            }
            guard restored.mapID == map.id else { throw CampaignSessionError.wrongMap }
            guard restored.schemaVersion == CampaignSnapshot.currentSchemaVersion else {
                throw CampaignSessionError.unsupportedSnapshotVersion
            }
            guard restored.units.allSatisfy({ profiles[$0.type] != nil }) else {
                throw CampaignSessionError.invalidSnapshot
            }
            let validOverrides: ([Int: Int]) -> Bool = { overrides in
                overrides.allSatisfy { $0.key >= 0 && $0.key < map.width * map.height && rules.terrainByTileID[$0.value] != nil }
            }
            guard validOverrides(restored.tileOverrides ?? [:]),
                  (restored.pendingPresentations ?? []).allSatisfy({ validOverrides($0.tileOverridesBefore ?? [:]) }),
                  (restored.allowedUnitType.map { (0...8).contains($0) } ?? true),
                  (restored.unitCap.map { (1...(restored.skirmish == nil ? 100 : 200)).contains($0) } ?? true) else {
                throw CampaignSessionError.invalidSnapshot
            }
            if let id = restored.recruitingUnitID {
                guard restored.selection?.unitID == id,
                      restored.units.contains(where: { $0.id == id && $0.isAlive && !$0.hasActed && $0.fraction == restored.activeFraction }) else {
                    throw CampaignSessionError.invalidSnapshot
                }
            }
            snapshot = restored
            // Mission runtimes are restored exactly at their last input or
            // presentation boundary. UIKit enables presentations before asking
            // the script to continue; advancing here would silently consume an
            // opening scene while the controller is still being constructed.
            if restored.missionRuntime == nil { evaluateCampaignScript() }
        } else {
            let random = JavaRandom(seed: seed)
            snapshot = CampaignSnapshot(
                mapID: map.id,
                units: map.units.map {
                    CampaignUnitState(
                        id: $0.index,
                        type: $0.type,
                        fraction: $0.fraction,
                        x: $0.x,
                        y: $0.y
                    )
                },
                phase: .opening,
                randomState: random.state,
                m0ScriptState: map.id == "m0" ? 14 : nil
            )
            snapshot.aiDifficulty = difficulty
            if let configuration, map.kind == "skirmish" {
                snapshot.skirmish = configuration
                snapshot.skirmishMap = map
                snapshot.allowedUnitType = 8
                snapshot.unitCap = configuration.unitCap
                snapshot.units.removeAll { unit in configuration.players.first { $0.fraction == unit.fraction }?.control == .off }
                snapshot.moneyByFraction = Dictionary(uniqueKeysWithValues: configuration.players.map { ($0.fraction, configuration.startingMoney) })
                for building in map.buildings where building.fraction > 0 {
                    if configuration.players.first(where: { $0.fraction == building.fraction })?.control == .off
                        || !configuration.players.contains(where: { $0.fraction == building.fraction }) {
                        if snapshot.tileOverrides == nil { snapshot.tileOverrides = [:] }
                        snapshot.tileOverrides?[building.y * map.width + building.x] = 37 + (building.tile - 37) % 2
                    }
                }
                snapshot.activeFraction = configuration.players.first { $0.control != .off }!.fraction
                snapshot.phase = .playerTurn
                if configuration.players.first(where: { $0.fraction == snapshot.activeFraction })?.control == .cpu {
                    snapshot.phase = .enemyTurn
                }
            } else { configureMissionScript() }
        }
    }

    public var isInputEnabled: Bool {
        snapshot.phase == .playerTurn
            && (snapshot.skirmish == nil ? snapshot.activeFraction == 1 : snapshot.skirmish?.players.first { $0.fraction == snapshot.activeFraction }?.control == .human)
            && snapshot.pendingCampaignHelpID == nil
            && pendingPresentation == nil
    }

    public var pendingPresentation: CampaignPresentation? { snapshot.pendingPresentations?.first }

    public mutating func enableStoryPresentations() { snapshot.storyPresentationsEnabled = true }

    public mutating func completePresentation() {
        guard pendingPresentation != nil else { return }
        snapshot.pendingPresentations?.removeFirst()
        if snapshot.pendingPresentations?.isEmpty == true {
            snapshot.pendingPresentations = nil
            evaluateCampaignScript()
            updateCampaignProgress()
        }
    }

    mutating func enqueue(_ presentation: CampaignPresentation) {
        if snapshot.pendingPresentations == nil { snapshot.pendingPresentations = [] }
        var recorded = presentation
        recorded.tileOverridesBefore = snapshot.tileOverrides ?? [:]
        snapshot.pendingPresentations?.append(recorded)
    }

    public func unit(id: Int) -> CampaignUnitState? {
        snapshot.units.first { $0.id == id }
    }

    public func units(at point: GridPoint) -> [CampaignUnitState] {
        snapshot.units.filter { $0.isAlive && $0.position == point }
    }

    public func movementRange(for unitID: Int) throws -> MovementRange? {
        // The range stays visible behind help 197, as in the original selection screen.
        guard isInputEnabled || (snapshot.pendingCampaignHelpID == 197 && snapshot.selection?.unitID == unitID) else { return nil }
        guard let unit = unit(id: unitID), unit.isAlive, unit.fraction == snapshot.activeFraction else {
            throw CampaignSessionError.unitUnavailable
        }
        guard !unit.hasMoved, !unit.hasActed else { return nil }
        return try calculateMovementRange(for: unit)
    }

    public func attackableUnits(for unitID: Int) -> [CampaignUnitState] {
        guard isInputEnabled, let attacker = unit(id: unitID), attacker.isAlive,
              attacker.fraction == snapshot.activeFraction, !attacker.hasActed,
              let profile = profiles[attacker.type], profile.characterCount > 0,
              !(attacker.type == 7 && attacker.hasMoved),
              snapshot.recruitingUnitID != attacker.id || attacker.hasMoved else { return [] }
        return snapshot.units.filter { target in
            target.isAlive && !areAllied(target.fraction, attacker.fraction) && isInRange(attacker, target, profile: profile)
        }
    }

    public mutating func advanceStory() throws {
        if snapshot.missionRuntime != nil {
            guard pendingPresentation == nil, snapshot.pendingCampaignHelpID == nil else {
                throw CampaignSessionError.gameNotReady
            }
            if snapshot.currentStoryMessageID != nil {
                snapshot.storyMessages = []
                snapshot.storyIndex = -1
            } else if snapshot.phase != .opening { throw CampaignSessionError.gameNotReady }
            evaluateCampaignScript()
            return
        }
        guard pendingPresentation == nil else { throw CampaignSessionError.gameNotReady }
        guard snapshot.phase == .opening || snapshot.phase == .story else {
            throw CampaignSessionError.gameNotReady
        }
        switch snapshot.storyMode ?? .opening {
        case .opening:
            if snapshot.storyIndex < 0 {
                moveOpeningUnits()
                snapshot.storyMessages = [221, 222, 223, 224]
                snapshot.storyIndex = 0
                snapshot.storyMode = .opening
            } else if snapshot.storyIndex == 0 {
                if snapshot.storyPresentationsEnabled == true, let guardUnit = templeGuard {
                    enqueueScene(.init(.hit, focus: guardUnit.position, durationTicks: 10, unitID: guardUnit.id))
                }
                snapshot.storyIndex += 1
            } else if snapshot.storyIndex == 1 {
                if let guardID = snapshot.units.first(where: {
                    $0.type == 0 && $0.fraction == 1 && $0.x == 9 && $0.y == 3
                })?.id {
                    if snapshot.storyPresentationsEnabled == true {
                        enqueueScene(.init(.disappearance, focus: GridPoint(x: 9, y: 3), durationTicks: 20, unitID: guardID))
                    }
                    snapshot.units.removeAll { $0.id == guardID }
                }
                if snapshot.storyPresentationsEnabled == true, let commander = snapshot.units.first(where: { $0.type == 9 && $0.fraction == 1 }) {
                    enqueueScene(.init(.camera, focus: commander.position, cameraOrigin: GridPoint(x: 9, y: 3), durationTicks: 10))
                }
                snapshot.storyIndex += 1
            } else if snapshot.storyIndex + 1 < snapshot.storyMessages.count {
                snapshot.storyIndex += 1
            } else {
                finishStory()
            }
        case .reinforcements:
            if snapshot.storyIndex + 1 < snapshot.storyMessages.count {
                snapshot.storyIndex += 1
            } else {
                finishStory()
            }
        case .ending:
            if snapshot.storyIndex + 1 < snapshot.storyMessages.count {
                snapshot.storyIndex += 1
            } else {
                snapshot.phase = .victory
                snapshot.storyMode = nil
                snapshot.storyMessages = []
                snapshot.storyIndex = -1
            }
        }
    }

    public mutating func dismissCampaignHelp() {
        guard snapshot.pendingCampaignHelpID != nil else { return }
        snapshot.pendingCampaignHelpID = nil
        evaluateCampaignScript()
        updateCampaignProgress()
    }

    public mutating func selectUnit(id: Int) throws {
        guard isInputEnabled else { throw CampaignSessionError.gameNotReady }
        guard let unit = unit(id: id), unit.isAlive, unit.fraction == snapshot.activeFraction, !unit.hasActed else {
            throw CampaignSessionError.unitUnavailable
        }
        if let selection = snapshot.selection, selection.moveOrigin != nil || snapshot.recruitingUnitID != nil {
            guard selection.unitID == id else { throw CampaignSessionError.gameNotReady }
            return
        }
        snapshot.selection = CampaignSelection(unitID: id)
        evaluateCampaignScript()
    }

    public mutating func movementRangeDidAppear(for unitID: Int) {
        guard snapshot.selection?.unitID == unitID,
              let unit = unit(id: unitID), !unit.hasMoved, !unit.hasActed else { return }
        snapshot.selection?.rangeReady = true
        evaluateCampaignScript()
    }

    public mutating func clearSelection() {
        guard snapshot.selection?.moveOrigin == nil, snapshot.recruitingUnitID == nil else { return }
        snapshot.selection = nil
    }

    public mutating func cancelMove() throws {
        guard isInputEnabled, let selection = snapshot.selection, let origin = selection.moveOrigin,
              let index = snapshot.units.firstIndex(where: { $0.id == selection.unitID }),
              !snapshot.units[index].hasActed else { throw CampaignSessionError.gameNotReady }
        snapshot.units[index].x = origin.x
        snapshot.units[index].y = origin.y
        snapshot.units[index].hasMoved = false
        snapshot.selection = CampaignSelection(unitID: selection.unitID, rangeReady: true)
    }

    public mutating func move(unitID: Int, to destination: GridPoint) throws {
        guard isInputEnabled else { throw CampaignSessionError.gameNotReady }
        guard snapshot.selection?.moveOrigin == nil,
              snapshot.recruitingUnitID == nil || snapshot.recruitingUnitID == unitID else { throw CampaignSessionError.gameNotReady }
        guard let index = snapshot.units.firstIndex(where: { $0.id == unitID }),
              snapshot.units[index].isAlive, snapshot.units[index].fraction == snapshot.activeFraction else {
            throw CampaignSessionError.unitUnavailable
        }
        guard !snapshot.units[index].hasMoved, !snapshot.units[index].hasActed else {
            throw CampaignSessionError.unitAlreadyActed
        }
        guard let range = try calculateMovementRange(for: snapshot.units[index]),
              range.reachableDestinations.contains(destination)
                || (snapshot.recruitingUnitID == unitID && destination == snapshot.units[index].position
                    && units(at: destination).allSatisfy({ $0.id == unitID })) else {
            throw CampaignSessionError.destinationUnreachable
        }
        snapshot.selection = CampaignSelection(unitID: unitID, rangeReady: true)
        snapshot.selection?.moveOrigin = snapshot.units[index].position
        snapshot.units[index].x = destination.x
        snapshot.units[index].y = destination.y
        snapshot.units[index].hasMoved = true
    }

    @discardableResult
    public mutating func attack(unitID: Int, targetID: Int, recordPresentation: Bool = false) throws -> CampaignAttackResult {
        guard isInputEnabled else { throw CampaignSessionError.gameNotReady }
        guard snapshot.selection?.moveOrigin == nil || snapshot.selection?.unitID == unitID else {
            throw CampaignSessionError.gameNotReady
        }
        guard let attackerIndex = snapshot.units.firstIndex(where: { $0.id == unitID }),
              snapshot.units[attackerIndex].isAlive, snapshot.units[attackerIndex].fraction == snapshot.activeFraction else {
            throw CampaignSessionError.unitUnavailable
        }
        guard !snapshot.units[attackerIndex].hasActed else { throw CampaignSessionError.unitAlreadyActed }
        guard !(snapshot.units[attackerIndex].type == 7 && snapshot.units[attackerIndex].hasMoved),
              snapshot.recruitingUnitID == nil || (snapshot.recruitingUnitID == unitID && snapshot.units[attackerIndex].hasMoved) else {
            throw CampaignSessionError.gameNotReady
        }
        guard let targetIndex = snapshot.units.firstIndex(where: { $0.id == targetID }),
              snapshot.units[targetIndex].isAlive,
              !areAllied(snapshot.units[targetIndex].fraction, snapshot.units[attackerIndex].fraction) else {
            throw CampaignSessionError.targetUnavailable
        }
        guard let profile = profiles[snapshot.units[attackerIndex].type], profile.characterCount > 0,
              isInRange(snapshot.units[attackerIndex], snapshot.units[targetIndex], profile: profile) else {
            throw CampaignSessionError.targetOutOfRange
        }
        let result = resolveAttack(attackerIndex: attackerIndex, targetIndex: targetIndex, recordPresentation: recordPresentation)
        finishAction(at: attackerIndex, applyUnitEffects: false)
        updateCampaignProgress()
        return result
    }

    public mutating func endUnitAction(unitID: Int, recordPresentation: Bool = false) throws {
        guard isInputEnabled else { throw CampaignSessionError.gameNotReady }
        guard snapshot.selection?.moveOrigin == nil || snapshot.selection?.unitID == unitID else {
            throw CampaignSessionError.gameNotReady
        }
        guard let index = snapshot.units.firstIndex(where: { $0.id == unitID }),
              snapshot.units[index].isAlive, snapshot.units[index].fraction == snapshot.activeFraction else {
            throw CampaignSessionError.unitUnavailable
        }
        guard !snapshot.units[index].hasActed else { throw CampaignSessionError.unitAlreadyActed }
        guard snapshot.recruitingUnitID == nil || (snapshot.recruitingUnitID == unitID && snapshot.units[index].hasMoved) else {
            throw CampaignSessionError.gameNotReady
        }
        finishAction(at: index, recordPresentation: recordPresentation)
        updateCampaignProgress()
    }

    private mutating func finishAction(at index: Int, recordPresentation: Bool = false, applyUnitEffects: Bool = true, finalUnitID: Int? = nil) {
        if applyUnitEffects { finishUnitAction(at: index, recordPresentation: recordPresentation) }
        if snapshot.recruitingUnitID == snapshot.units[index].id { snapshot.recruitingUnitID = nil }
        // JAR Unit.method_56 / AEIIRM Unit.finishMove publish this only on END MOVE or attack.
        snapshot.lastFinishedMoveUnitID = finalUnitID ?? snapshot.units[index].id
        snapshot.selection = nil
        evaluateCampaignScript()
    }

    @discardableResult
    public mutating func endPlayerTurn(recordPresentation: Bool = false) throws -> [CampaignAIAction] {
        guard isInputEnabled, snapshot.selection?.moveOrigin == nil, snapshot.recruitingUnitID == nil else { throw CampaignSessionError.gameNotReady }
        snapshot.selection = nil
        snapshot.lastFinishedMoveUnitID = nil
        if snapshot.skirmish != nil { return advanceSkirmishTurns(recordPresentation: recordPresentation) }
        snapshot.phase = .enemyTurn
        beginTurn(for: 2, recordPresentation: recordPresentation)
        let enemyActions = runEnemyTurn(recordPresentation: recordPresentation)
        guard snapshot.phase != .defeat, snapshot.phase != .story else { return enemyActions }
        snapshot.phase = .playerTurn
        beginTurn(for: 1, recordPresentation: recordPresentation)
        evaluateCampaignScript()
        updateCampaignProgress()
        return enemyActions
    }

    public func money(for fraction: Int) -> Int { snapshot.moneyByFraction?[fraction] ?? 0 }

    public var isSkirmish: Bool { snapshot.skirmish != nil }

    public func team(for fraction: Int) -> Int {
        snapshot.skirmish?.players.first { $0.fraction == fraction }?.team ?? fraction
    }

    public func areAllied(_ lhs: Int, _ rhs: Int) -> Bool {
        lhs > 0 && rhs > 0 && team(for: lhs) == team(for: rhs)
    }

    private func skirmishSurvives(_ fraction: Int) -> Bool {
        snapshot.units.contains { $0.fraction == fraction && $0.type == 9 && $0.isAlive }
            || map.buildings.contains { $0.fraction == fraction && $0.terrain == 9 }
    }

    private mutating func updateSkirmishOutcome() {
        guard let settings = snapshot.skirmish else { return }
        let survivors = settings.players.filter { $0.control != .off && skirmishSurvives($0.fraction) }
        let teams = Set(survivors.map(\.team))
        guard teams.count <= 1 else { return }
        snapshot.winningTeam = teams.first
        snapshot.phase = settings.players.contains { $0.control == .human && teams.contains($0.team) } ? .victory : .defeat
        snapshot.selection = nil
        snapshot.recruitingUnitID = nil
    }

    @discardableResult
    public mutating func advanceAutomaticSkirmishTurn(recordPresentation: Bool = true) -> [CampaignAIAction] {
        guard isSkirmish, snapshot.phase == .enemyTurn, pendingPresentation == nil else { return [] }
        let actions = runEnemyTurn(recordPresentation: recordPresentation)
        updateSkirmishOutcome()
        guard snapshot.phase != .victory, snapshot.phase != .defeat else { return actions }
        return actions + advanceSkirmishTurns(recordPresentation: recordPresentation)
    }

    private mutating func advanceSkirmishTurns(recordPresentation: Bool) -> [CampaignAIAction] {
        guard let settings = snapshot.skirmish else { return [] }
        var actions: [CampaignAIAction] = []
        var current = settings.players.firstIndex { $0.fraction == snapshot.activeFraction } ?? 0
        // CPU-only games yield after each turn. Presentation completion drives
        // the next turn, keeping background/save/menu boundaries recoverable.
        for _ in 0..<settings.players.count {
            updateSkirmishOutcome()
            if snapshot.phase == .victory || snapshot.phase == .defeat { return actions }
            current = (current + 1) % settings.players.count
            let candidate = settings.players[current]
            guard candidate.control != .off, skirmishSurvives(candidate.fraction) else { continue }
            beginTurn(for: candidate.fraction, recordPresentation: recordPresentation)
            let player = settings.players[current]
            if player.control == .human {
                snapshot.phase = .playerTurn
                return actions
            }
            snapshot.phase = .enemyTurn
            if !settings.players.contains(where: { $0.control == .human && skirmishSurvives($0.fraction) }) {
                return actions
            }
            actions += runEnemyTurn(recordPresentation: recordPresentation)
        }
        updateSkirmishOutcome()
        if snapshot.phase != .victory && snapshot.phase != .defeat {
            snapshot.phase = .enemyTurn
            snapshot.selection = nil
        }
        return actions
    }

    /// MainDisplayable.sub_cff / gameState 8. Commit exactly once before presenting.
    private mutating func beginTurn(for fraction: Int, recordPresentation: Bool) {
        snapshot.activeFraction = fraction
        snapshot.currentTurn += 1
        resetActions()
        for index in snapshot.units.indices {
            if snapshot.units[index].isCorpse {
                if snapshot.units[index].type != 9 {
                    snapshot.units[index].corpseTransitionsRemaining = max(0, (snapshot.units[index].corpseTransitionsRemaining ?? 0) - 1)
                    if snapshot.units[index].corpseTransitionsRemaining == 0 { snapshot.units[index].removedFromMap = true }
                }
            } else if snapshot.units[index].isAlive {
                if snapshot.units[index].poisonSourceFraction == fraction {
                    snapshot.units[index].effects = (snapshot.units[index].effects ?? 0) & ~1
                    snapshot.units[index].poisonSourceFraction = nil
                }
                if snapshot.units[index].fraction == fraction, snapshot.units[index].isBlessed {
                    snapshot.units[index].effects = (snapshot.units[index].effects ?? 0) & ~2
                }
            }
        }
        let income = map.buildings.filter { $0.fraction == fraction }.reduce(0) {
            $0 + ($1.terrain == 8 ? 30 : ($1.terrain == 9 ? 50 : 0))
        }
        let balance = money(for: fraction) + income
        if snapshot.moneyByFraction == nil { snapshot.moneyByFraction = [:] }
        snapshot.moneyByFraction?[fraction] = balance
        if recordPresentation {
            let focus = isSkirmish ? snapshot.units.last(where: { $0.fraction == fraction && $0.type == 9 && $0.isAlive })?.position
                ?? map.buildings.first(where: { $0.fraction == fraction && $0.terrain == 9 }).map { GridPoint(x: $0.x, y: $0.y) } : nil
            enqueueScene(.init(.newTurn, focus: focus, durationTicks: 30,
                               turn: .init(fraction: fraction, income: income, balance: balance)))
        }
        let before = snapshot.units
        var healing: [CampaignHealingPresentation] = []
        for index in snapshot.units.indices {
            let unit = snapshot.units[index]
            guard unit.fraction == fraction, unit.isAlive, unit.hitPoints < 100,
                  let tile = map.tileID(x: unit.x, y: unit.y) else { continue }
            let friendlyBuilding = map.buildings.contains { $0.x == unit.x && $0.y == unit.y && areAllied($0.fraction, fraction) }
            guard rules.terrainByTileID[tile] == 7 || friendlyBuilding else { continue }
            let amount = min(20, 100 - unit.hitPoints)
            snapshot.units[index].hitPoints += amount
            healing.append(.init(unitID: unit.id, amount: amount))
        }
        if recordPresentation, !healing.isEmpty {
            enqueue(.init(unitsBefore: before, scene: .init(.healing, durationTicks: 25, healing: healing)))
        }
    }

    private func calculateMovementRange(
        for unit: CampaignUnitState,
        from origin: GridPoint? = nil,
        moveBudget overrideMoveBudget: Int? = nil,
        ignoringUnitID: Int? = nil
    ) throws -> MovementRange? {
        guard let profile = profiles[unit.type] else { throw CampaignSessionError.invalidSnapshot }
        let terrainIDs = try map.rows.map { row in
            try row.map { tileID -> Int in
                guard let terrainID = rules.terrainByTileID[tileID] else {
                    throw CampaignSessionError.invalidSnapshot
                }
                return terrainID
            }
        }
        return try MovementRangeCalculator.calculate(
            on: MovementGrid(
                terrainIDs: terrainIDs,
                stepCostByTerrainID: rules.movementCostByTerrainID,
                occupants: snapshot.units.filter { $0.id != ignoringUnitID }.map {
                    MovementOccupant(position: $0.position, teamID: team(for: $0.fraction), isAlive: $0.isAlive)
                },
                waterTerrainID: rules.waterTerrainID,
                mountainTerrainID: rules.mountainTerrainID
            ),
            from: origin ?? unit.position,
            teamID: team(for: unit.fraction),
            profile: MovementProfile(
                moveBudget: overrideMoveBudget ?? profile.moveBudget,
                canFly: profile.canFly,
                isAmphibious: profile.isAmphibious,
                isCrystal: unit.type == 11
            )
        )
    }

    private func isInRange(
        _ attacker: CampaignUnitState,
        _ target: CampaignUnitState,
        profile: CampaignUnitProfile
    ) -> Bool {
        let distance = abs(attacker.x - target.x) + abs(attacker.y - target.y)
        return distance >= profile.minimumRange && distance <= profile.maximumRange
    }

    private mutating func resolveAttack(attackerIndex: Int, targetIndex: Int, recordPresentation: Bool = false) -> CampaignAttackResult {
        let unitsBefore = snapshot.units
        let attackerID = snapshot.units[attackerIndex].id
        let targetID = snapshot.units[targetIndex].id
        let damage = applyDamage(attackerIndex: attackerIndex, targetIndex: targetIndex)
        var counterDamage = 0
        var didCounterattack = false
        if snapshot.units[targetIndex].isAlive,
           let targetProfile = profiles[snapshot.units[targetIndex].type],
           targetProfile.minimumRange == 1,
           abs(snapshot.units[attackerIndex].x - snapshot.units[targetIndex].x)
            + abs(snapshot.units[attackerIndex].y - snapshot.units[targetIndex].y) == 1 {
            didCounterattack = true
            counterDamage = applyDamage(attackerIndex: targetIndex, targetIndex: attackerIndex)
        }
        if recordPresentation {
            enqueue(CampaignPresentation(unitsBefore: unitsBefore, combat: CampaignCombatPresentation(
                attacker: unitsBefore[attackerIndex], defender: unitsBefore[targetIndex],
                attackerTerrain: terrainID(at: unitsBefore[attackerIndex].position),
                defenderTerrain: terrainID(at: unitsBefore[targetIndex].position),
                attackerHealthAfter: snapshot.units[attackerIndex].hitPoints,
                defenderHealthAfter: snapshot.units[targetIndex].hitPoints,
                didCounterattack: didCounterattack
            )))
        }
        // finishAttack applies poison only from the initiating attacker, after
        // counterattack. A defending wolf does not poison on its counterattack.
        if snapshot.units[targetIndex].isAlive, profiles[unitsBefore[attackerIndex].type]?.propertyBits.contains(7) == true {
            snapshot.units[targetIndex].effects = (snapshot.units[targetIndex].effects ?? 0) | 1
            snapshot.units[targetIndex].poisonSourceFraction = snapshot.activeFraction
            if recordPresentation { enqueueScene(.init(.poison, unitID: targetID)) }
        }
        var promotedIndices: [Int] = []
        for index in [attackerIndex, targetIndex] where snapshot.units[index].isAlive {
            if promoteIfReady(unitIndex: index) { promotedIndices.append(index) }
        }
        finishUnitAction(at: attackerIndex, recordPresentation: recordPresentation)
        for index in [attackerIndex, targetIndex] where snapshot.units[index].hitPoints <= 0 {
            if recordPresentation { enqueueScene(.init(.death, focus: snapshot.units[index].position, durationTicks: 6, unitID: snapshot.units[index].id)) }
            markDeadUnit(at: index)
        }
        // MainDisplayable processes deaths before the queued promotion effects.
        if recordPresentation {
            for index in promotedIndices {
                let unit = snapshot.units[index]
                enqueueScene(.init(.levelUp, focus: unit.position, durationTicks: 20, unitID: unit.id))
                if unit.type != 9, unit.rank > 0, unit.rank <= 6, unit.rank % 2 == 0 {
                    enqueueScene(.init(.rankName, durationTicks: 31, unitID: unit.id, textID: 80))
                }
            }
        }
        return CampaignAttackResult(
            attackerID: attackerID,
            targetID: targetID,
            damage: damage,
            counterDamage: counterDamage
        )
    }

    private mutating func applyDamage(attackerIndex: Int, targetIndex: Int) -> Int {
        let attacker = snapshot.units[attackerIndex]
        let target = snapshot.units[targetIndex]
        guard let attackerProfile = profiles[attacker.type],
              let targetProfile = profiles[target.type],
              attackerProfile.attackMaximum > attackerProfile.attackMinimum else { return 0 }

        let attackerRankBonus = attacker.rank * 2
        let targetRankBonus = target.rank * 2
        let randomRoll = attackRoll(minimum: attackerProfile.attackMinimum + attackerRankBonus,
                                    maximum: attackerProfile.attackMaximum + attackerRankBonus)
        var offenseBonus = attacker.statusOffenseBonus
        if attackerProfile.hasAntiAir && profiles[target.type]?.canFly == true { offenseBonus += 15 }
        if attacker.type == 4 && target.type == 10 { offenseBonus += 15 }
        if attackerProfile.isAmphibious && terrainID(at: attacker.position) == rules.waterTerrainID {
            offenseBonus += 10
        }
        if tileID(at: attacker.position) == 34 { offenseBonus += 25 }

        var defenseBonus = target.statusDefenseBonus + (rules.defenseByTerrainID[terrainID(at: target.position)] ?? 0)
        if targetProfile.isAmphibious && terrainID(at: target.position) == rules.waterTerrainID {
            defenseBonus += 15
        }
        if tileID(at: target.position) == 34 { defenseBonus += 15 }
        let rawDamage = (randomRoll + offenseBonus - (targetProfile.baseDefense + targetRankBonus + defenseBonus))
            * attacker.hitPoints / 100
        let damage = min(max(rawDamage, 0), target.hitPoints)
        snapshot.units[targetIndex].hitPoints -= damage
        if target.type == 9, target.isAlive, !snapshot.units[targetIndex].isAlive {
            snapshot.units[targetIndex].revivalCost = min(1000, (target.revivalCost ?? targetProfile.cost) + 200)
        }

        let targetQuality = targetProfile.quality(atRank: target.rank)
        snapshot.units[attackerIndex].experience += targetQuality * damage

        if snapshot.units[targetIndex].hitPoints <= 0 && (target.type == 10 || target.type == 11) {
            snapshot.units[targetIndex].hitPoints = 0
        }
        return damage
    }

    private mutating func attackRoll(minimum: Int, maximum: Int) -> Int {
        var random = JavaRandom(internalState: snapshot.randomState)
        let raw = JavaMath.absoluteValue(random.nextInt32())
        snapshot.randomState = random.state
        return minimum + Int(JavaMath.remainder(raw, by: Int32(maximum - minimum)))
    }

    @discardableResult
    private mutating func promoteIfReady(unitIndex: Int) -> Bool {
        let unit = snapshot.units[unitIndex]
        guard let profile = profiles[unit.type] else { return false }
        let threshold = profile.nextRankExperience(atRank: unit.rank)
        guard unit.experience >= threshold else { return false }
        snapshot.units[unitIndex].experience -= threshold
        snapshot.units[unitIndex].rank = Int(Int8(truncatingIfNeeded: unit.rank + 1))
        return true
    }

    private func tileID(at point: GridPoint) -> Int {
        guard let original = originalMap.tileID(x: point.x, y: point.y) else { return -1 }
        return snapshot.tileOverrides?[point.y * originalMap.width + point.x] ?? original
    }

    private func terrainID(at point: GridPoint) -> Int {
        rules.terrainByTileID[tileID(at: point)] ?? -1
    }

    private mutating func moveOpeningUnits() {
        let destinations: [Int: GridPoint] = [
            1: GridPoint(x: 3, y: 8),
            0: GridPoint(x: 4, y: 9),
            2: GridPoint(x: 3, y: 10),
        ]
        let before = snapshot.units
        var movements: [CampaignScenePresentation.Movement] = []
        for id in [1, 0, 2] {
            guard let point = destinations[id] else { continue }
            guard let index = snapshot.units.firstIndex(where: { $0.id == id }) else { continue }
            let origin = snapshot.units[index].position
            movements.append(.init(unitID: id, route: (origin.x...point.x).map { GridPoint(x: $0, y: point.y) }))
            snapshot.units[index].x = point.x
            snapshot.units[index].y = point.y
        }
        if snapshot.storyPresentationsEnabled == true {
            enqueue(CampaignPresentation(unitsBefore: before, scene: .init(.arrival,
                focus: GridPoint(x: 5, y: 9), cameraOrigin: GridPoint(x: 1, y: 9), cameraStep: 2,
                movements: movements, unitSpeed: 4)))
            enqueueScene(.init(.camera, focus: GridPoint(x: 9, y: 3), cameraOrigin: GridPoint(x: 5, y: 9), cameraStep: 2, durationTicks: 10))
        }
    }

    private var templeGuard: CampaignUnitState? {
        snapshot.units.first { $0.type == 0 && $0.fraction == 1 && $0.position == GridPoint(x: 9, y: 3) }
    }

    mutating func enqueueScene(_ scene: CampaignScenePresentation) {
        enqueue(CampaignPresentation(unitsBefore: snapshot.units, scene: scene))
    }

    private mutating func finishStory() {
        let finishedMode = snapshot.storyMode ?? .opening
        switch finishedMode {
        case .opening, .reinforcements:
            snapshot.phase = .playerTurn
            snapshot.activeFraction = 1
        case .ending:
            snapshot.phase = .victory
        }
        snapshot.storyMode = nil
        snapshot.storyMessages = []
        snapshot.storyIndex = -1
        if finishedMode == .opening {
            if snapshot.storyPresentationsEnabled == true {
                enqueueScene(.init(.objective, textID: 129))
            }
            evaluateCampaignScript()
        }
    }

    private mutating func resetActions() {
        for index in snapshot.units.indices where snapshot.units[index].isAlive {
            snapshot.units[index].hasMoved = false
            snapshot.units[index].hasActed = false
        }
    }

    private mutating func runEnemyTurn(recordPresentation: Bool) -> [CampaignAIAction] {
        var pendingUnits = snapshot.units.enumerated()
            .filter { $0.element.isAlive && $0.element.fraction == snapshot.activeFraction && !$0.element.hasActed }
            .sorted { left, right in
                let leftPriority = aiPriority(left.element.type)
                let rightPriority = aiPriority(right.element.type)
                if leftPriority != rightPriority { return leftPriority < rightPriority }
                if left.element.hitPoints != right.element.hitPoints { return left.element.hitPoints < right.element.hitPoints }
                return left.offset < right.offset
            }.map { $0.element.id }
        var context = CampaignAITurnContext()
        var actions: [CampaignAIAction] = []
        var recruitCount = 0

        while snapshot.phase == .enemyTurn {
            // Existing units move before recruitment. Every purchase spends a
            // positive cost and is immediately planned before buying again.
            if pendingUnits.isEmpty {
                if difficulty(for: snapshot.activeFraction) == .easy, recruitCount >= 1 { break }
                guard let id = recruitEnemyUnit() else { break }
                recruitCount += 1
                pendingUnits.append(id)
            }
            let requestedID = pendingUnits[0]
            guard var actor = unit(id: requestedID), actor.isAlive, !actor.hasActed else {
                pendingUnits.removeFirst()
                continue
            }
            if map.id == "m7", snapshot.missionRuntime?.state == 20,
               snapshot.missionRuntime?.references["saeth"] == actor.id {
                pendingUnits.removeFirst()
                if let action = performHeavenFury(actor: actor, recordPresentation: recordPresentation) {
                    actions.append(action)
                }
                updateDefeatState()
                continue
            }
            var choice = bestEnemyChoice(for: actor, context: &context, allowFriendlyDestination: true)
            // MainDisplayable.var_381f: move an unacted blocker first, then
            // reconsider the original unit. The blocker cannot displace another.
            if let blocker = units(at: choice.destination).first(where: { $0.id != actor.id && $0.fraction == actor.fraction && !$0.hasActed }) {
                actor = blocker
                choice = bestEnemyChoice(for: actor, context: &context, allowFriendlyDestination: false)
            }
            pendingUnits.removeAll { $0 == actor.id }
            guard let index = snapshot.units.firstIndex(where: { $0.id == actor.id }) else { continue }
            let range = try? calculateMovementRange(for: actor)
            let route = range?.route(to: choice.destination) ?? []
            if recordPresentation {
                enqueueScene(.init(.camera, focus: actor.position, durationTicks: 2, unitID: actor.id))
                let cells = (0..<map.width).flatMap { x in
                    (0..<map.height).compactMap { y -> GridPoint? in
                        let point = GridPoint(x: x, y: y)
                        return (range?.remainingBudget(at: point) ?? 0) > 0 ? point : nil
                    }
                }
                enqueueScene(.init(.planning, focus: actor.position, durationTicks: 8, unitID: actor.id,
                                   movementRange: cells, routePreview: [actor.position] + route))
            }
            if choice.destination != actor.position {
                if recordPresentation {
                    enqueue(CampaignPresentation(unitID: actor.id, unitsBefore: snapshot.units, route: route, unitSpeed: 12))
                }
                snapshot.units[index].x = choice.destination.x
                snapshot.units[index].y = choice.destination.y
                snapshot.units[index].hasMoved = true
            }
            if let targetID = choice.targetID,
               let targetIndex = snapshot.units.firstIndex(where: { $0.id == targetID && $0.isAlive }) {
                if recordPresentation, let profile = profiles[actor.type] {
                    let cells = (0..<map.width).flatMap { x in
                        (0..<map.height).compactMap { y -> GridPoint? in
                            let point = GridPoint(x: x, y: y)
                            return isInRange(from: choice.destination, to: point, profile: profile) ? point : nil
                        }
                    }
                    // sub_1043 state 5 holds the attack mask/cursor for 500 logical ms.
                    enqueueScene(.init(.targeting, focus: snapshot.units[targetIndex].position,
                                       durationTicks: 10, unitID: actor.id, attackRange: cells))
                }
                _ = resolveAttack(attackerIndex: index, targetIndex: targetIndex, recordPresentation: recordPresentation)
            } else if let corpseID = choice.summonedCorpseID {
                if recordPresentation, let corpse = unit(id: corpseID) {
                    enqueueScene(.init(.targeting, focus: corpse.position, durationTicks: 10, unitID: actor.id, attackRange: attackRangeCells(for: actor.id),
                                       affectedUnitIDs: [corpseID]))
                }
                _ = commitSummoning(casterIndex: index, corpseID: corpseID, recordPresentation: recordPresentation)
            } else if let action = buildingAction(for: snapshot.units[index]) {
                if let intended = choice.strategy?.captureBuilding, intended != choice.destination {
                    context.buildingReservations[intended] = context.buildingReservations[choice.destination]
                    context.buildingReservations[choice.destination] = actor.id
                }
                commitBuildingAction(action, unit: snapshot.units[index], recordPresentation: recordPresentation)
            }
            if choice.targetID == nil && choice.summonedCorpseID == nil { finishUnitAction(at: index, recordPresentation: recordPresentation) }
            actions.append(CampaignAIAction(unitID: actor.id, origin: actor.position, destination: choice.destination,
                                           targetID: choice.targetID, estimatedScore: choice.score, strategicTarget: choice.strategy?.point,
                                           summonedCorpseID: choice.summonedCorpseID))
            updateDefeatState()
        }
        return actions
    }

    /// MainDisplayable:6903-6913 and 4207-4259. Saeth replaces his normal AI
    /// move with one random strike for 25...49 HP, capped at the target's HP.
    private mutating func performHeavenFury(actor: CampaignUnitState,
                                            recordPresentation: Bool) -> CampaignAIAction? {
        guard let actorIndex = snapshot.units.firstIndex(where: { $0.id == actor.id }) else { return nil }
        let targets = snapshot.units.filter { $0.fraction == 1 && $0.isAlive }
        guard !targets.isEmpty else {
            finishUnitAction(at: actorIndex, recordPresentation: recordPresentation)
            return CampaignAIAction(unitID: actor.id, origin: actor.position, destination: actor.position,
                                    targetID: nil, estimatedScore: 0, strategicTarget: nil, summonedCorpseID: nil)
        }

        let target = targets[attackRoll(minimum: 0, maximum: targets.count)]
        guard let targetIndex = snapshot.units.firstIndex(where: { $0.id == target.id }) else { return nil }
        if recordPresentation { enqueueScene(.init(.camera, focus: actor.position, durationTicks: 2, unitID: actor.id)) }
        let unitsBefore = snapshot.units
        let damage = min(target.hitPoints, attackRoll(minimum: 25, maximum: 50))
        snapshot.units[targetIndex].hitPoints -= damage
        if target.type == 9, snapshot.units[targetIndex].hitPoints <= 0, let profile = profiles[target.type] {
            snapshot.units[targetIndex].revivalCost = min(1000, (target.revivalCost ?? profile.cost) + 200)
        }
        if recordPresentation {
            enqueue(CampaignPresentation(unitsBefore: unitsBefore, scene: .init(
                .heavenFury, focus: target.position, durationTicks: 20, unitID: target.id,
                textID: 280, affectedUnitIDs: [target.id], damage: damage
            )))
        }
        finishUnitAction(at: actorIndex, recordPresentation: recordPresentation)
        if snapshot.units[targetIndex].hitPoints <= 0 {
            if recordPresentation {
                enqueueScene(.init(.death, focus: target.position, durationTicks: 6, unitID: target.id))
            }
            markDeadUnit(at: targetIndex)
        }
        return CampaignAIAction(unitID: actor.id, origin: actor.position, destination: actor.position,
                                targetID: target.id, estimatedScore: damage,
                                strategicTarget: target.position, summonedCorpseID: nil)
    }

    private func bestEnemyChoice(for actor: CampaignUnitState, context: inout CampaignAITurnContext,
                                 allowFriendlyDestination: Bool) -> CampaignEnemyChoice {
        guard let profile = profiles[actor.type], let movementRange = try? calculateMovementRange(for: actor) else {
            return CampaignEnemyChoice(destination: actor.position, targetID: nil, score: 0, strategy: nil)
        }
        let strategic = strategicTarget(for: actor, range: movementRange, context: &context)
        let field = strategic.flatMap { try? calculateMovementRange(for: actor, from: $0.point, moveBudget: 10) }
        let level = difficulty(for: actor.fraction)
        let threats = level.usesTactics ? predictedThreats(for: actor, range: movementRange) : [:]
        let distances = level.usesTactics ? strategic.map { strategicDistances(for: actor, goal: $0.point) } ?? [:] : [:]
        let targets = snapshot.units.enumerated()
            .filter { $0.element.isAlive && !areAllied($0.element.fraction, actor.fraction) }
            .sorted {
                if $0.element.x != $1.element.x { return $0.element.x < $1.element.x }
                if $0.element.y != $1.element.y { return $0.element.y < $1.element.y }
                return $0.offset < $1.offset
            }.map(\.element)
        var best = CampaignEnemyChoice(destination: actor.position, targetID: nil, score: -10_000, strategy: strategic)
        for x in 0..<map.width {
            for y in 0..<map.height {
                let point = GridPoint(x: x, y: y)
                guard (movementRange.remainingBudget(at: point) ?? 0) > 0 else { continue }
                let occupants = units(at: point).filter { $0.id != actor.id }
                guard occupants.isEmpty || (allowFriendlyDestination && occupants.allSatisfy { $0.fraction == actor.fraction && !$0.hasActed }) else { continue }
                let pathScore = distances[actor.position].flatMap { start in distances[point].map { (start - $0) * 15 } } ?? 0
                if profile.characterCount > 0 && (!profile.propertyBits.contains(9) || point == actor.position) {
                    for target in targets where isInRange(from: point, to: target.position, profile: profile) {
                        let score = enemyActionScore(actor: actor, at: point, target: target, strategic: strategic, field: field, context: context)
                            + tacticalAdjustment(actor: actor, at: point, target: target, threats: threats[point] ?? [:]) + pathScore
                        if score > best.score { best = CampaignEnemyChoice(destination: point, targetID: target.id, score: score, strategy: strategic) }
                    }
                }
                for corpse in corpsesInRange(of: actor, at: point) {
                    let score = enemyActionScore(actor: actor, at: point, target: nil, strategic: strategic, field: field, context: context, corpse: corpse)
                        + tacticalAdjustment(actor: actor, at: point, target: nil, threats: threats[point] ?? [:]) + pathScore
                    if score > best.score {
                        best = CampaignEnemyChoice(destination: point, targetID: nil, score: score, strategy: strategic, summonedCorpseID: corpse.id)
                    }
                }
                let score = enemyActionScore(actor: actor, at: point, target: nil, strategic: strategic, field: field, context: context)
                    + tacticalAdjustment(actor: actor, at: point, target: nil, threats: threats[point] ?? [:]) + pathScore
                if score > best.score { best = CampaignEnemyChoice(destination: point, targetID: nil, score: score, strategy: strategic) }
            }
        }
        if let building = strategic?.captureBuilding {
            context.reservedDistances[building] = 10 - (field?.remainingBudget(at: best.destination) ?? 0)
        }
        return best
    }

    /// Java loads buildings x-major; keep that tie order even after sparse tile edits.
    private var orderedBuildings: [CampaignBuilding] {
        map.buildings.sorted { $0.x == $1.x ? $0.y < $1.y : $0.x < $1.x }
    }

    private func primaryCommander(fraction: Int) -> CampaignUnitState? {
        // The map loader assigns the last commander it reads as fractionKings.
        snapshot.units.last { $0.fraction == fraction && $0.type == 9 }
    }

    private func strategicTarget(for actor: CampaignUnitState, range: MovementRange,
                                 context: inout CampaignAITurnContext) -> CampaignAIStrategy? {
        let allies = snapshot.units.filter { $0.isAlive && areAllied($0.fraction, actor.fraction) }
        let opponents = snapshot.units.filter { $0.isAlive && !areAllied($0.fraction, actor.fraction) }
        if difficulty(for: actor.fraction) == .easy {
            return opponents.min(by: { manhattanDistance(actor.position, $0.position) < manhattanDistance(actor.position, $1.position) })
                .map { CampaignAIStrategy(point: $0.position, unitID: $0.id) }
        }
        if difficulty(for: actor.fraction) == .expert, actor.hitPoints >= 50,
           let castle = orderedBuildings.first(where: { building in
               building.fraction == actor.fraction && building.terrain == 9
                   && opponents.contains { enemy in
                       profiles[enemy.type]?.propertyBits.contains(4) == true
                           && manhattanDistance(enemy.position, GridPoint(x: building.x, y: building.y)) <= (profiles[enemy.type]?.moveBudget ?? 0)
                   }
                   && (context.buildingSupport[GridPoint(x: building.x, y: building.y)] ?? 0) < 200
           }) {
            let point = GridPoint(x: castle.x, y: castle.y)
            context.buildingSupport[point, default: 0] += estimatedCombatQuality(actor, at: actor.position, against: nil)
            let enemy = opponents.min { manhattanDistance($0.position, point) < manhattanDistance($1.position, point) }
            return CampaignAIStrategy(point: enemy?.position ?? point, supportBuilding: point)
        }
        let hasCommander = primaryCommander(fraction: actor.fraction) != nil
        var candidates: [(strategy: CampaignAIStrategy, threat: Int, assigned: Int)] = []
        // Original candidates are soldiers, then one registered king per faction.
        let soldiers = hasCommander ? allies.filter { $0.type == 0 } : []
        let kings = Set(snapshot.units.map(\.fraction)).sorted().compactMap { primaryCommander(fraction: $0) }
        for candidate in soldiers + kings where candidate.isAlive {
            let threat: Int
            if areAllied(candidate.fraction, actor.fraction) {
                threat = opponents.reduce(into: 0) { total, enemy in
                    if (1...5).contains(manhattanDistance(candidate.position, enemy.position)) {
                        total += estimatedCombatQuality(enemy, at: enemy.position, against: candidate)
                    }
                }
            } else if !hasCommander || (snapshot.currentTurn >= 15 && opponents.filter { $0.fraction == candidate.fraction }.count < 4 && allies.count >= 8) {
                threat = estimatedCombatQuality(candidate, at: candidate.position, against: nil) * 2
            } else {
                continue
            }
            if threat > 0 {
                candidates.append((CampaignAIStrategy(point: candidate.position, unitID: candidate.id), threat,
                    estimatedCombatQuality(candidate, at: candidate.position, against: nil) + (context.unitSupport[candidate.id] ?? 0)))
            }
        }
        var healing: (point: GridPoint, distance: Int)?
        var capture: (point: GridPoint, distance: Int)?
        for building in orderedBuildings {
            let point = GridPoint(x: building.x, y: building.y)
            let owned = areAllied(building.fraction, actor.fraction)
            let reserved = context.buildingReservations[point]
            let distance = manhattanDistance(actor.position, point)
            if owned || reserved != nil {
                var threat = opponents.reduce(into: 0) { total, enemy in
                    guard (1...5).contains(manhattanDistance(point, enemy.position)) else { return }
                    let canCapture = profiles[enemy.type]?.propertyBits.contains(building.terrain == 9 ? 4 : 3) == true
                    if (!owned && reserved != nil) || canCapture {
                        total += estimatedCombatQuality(enemy, at: enemy.position, against: nil)
                    }
                }
                var assigned = context.buildingSupport[point] ?? 0
                if threat == 0 && !owned && reserved != nil { threat = 100; assigned += 2000 }
                if threat > 0 { candidates.append((CampaignAIStrategy(point: point, supportBuilding: point), threat, assigned)) }
            }
            let occupants = units(at: point)
            let emptyOrFriendly = occupants.allSatisfy { areAllied($0.fraction, actor.fraction) }
            if owned {
                if emptyOrFriendly && distance < (healing?.distance ?? 666) { healing = (point, distance) }
            } else if (reserved == nil || reserved == actor.id),
                      profiles[actor.type]?.propertyBits.contains(building.terrain == 9 ? 4 : 3) == true {
                if distance < (capture?.distance ?? 666) { capture = (point, distance) }
                if distance < (healing?.distance ?? 666), (range.remainingBudget(at: point) ?? 0) > 0, emptyOrFriendly {
                    healing = (point, distance)
                }
            }
        }
        if actor.hitPoints < 50, let healing {
            context.buildingReservations[healing.point] = actor.id
            return CampaignAIStrategy(point: healing.point, captureBuilding: healing.point == capture?.point ? healing.point : nil)
        }
        if hasCommander, let capture {
            context.buildingReservations[capture.point] = actor.id
            return CampaignAIStrategy(point: capture.point, captureBuilding: capture.point)
        }
        guard let nearest = candidates.map({ max(1, manhattanDistance(actor.position, $0.strategy.point)) }).min() else {
            guard difficulty(for: actor.fraction).usesTactics else { return nil }
            return opponents.min(by: { manhattanDistance(actor.position, $0.position) < manhattanDistance(actor.position, $1.position) })
                .map { CampaignAIStrategy(point: $0.position, unitID: $0.id) }
        }
        var best: CampaignAIStrategy?
        var bestScore = -6666
        for candidate in candidates {
            let distance = max(1, manhattanDistance(actor.position, candidate.strategy.point))
            let score = candidate.threat * nearest / distance - candidate.assigned
            if score > bestScore { bestScore = score; best = candidate.strategy }
        }
        let strength = estimatedCombatQuality(actor, at: actor.position, against: nil)
        if let id = best?.unitID { context.unitSupport[id, default: 0] += strength }
        if let point = best?.supportBuilding { context.buildingSupport[point, default: 0] += strength }
        return best
    }

    private func enemyActionScore(actor: CampaignUnitState, at point: GridPoint, target: CampaignUnitState?,
                                  strategic: CampaignAIStrategy?, field: MovementRange?, context: CampaignAITurnContext,
                                  corpse: CampaignUnitState? = nil) -> Int {
        guard let profile = profiles[actor.type] else { return Int.min }
        if difficulty(for: actor.fraction) == .easy {
            let progress = strategic.map { manhattanDistance(actor.position, $0.point) - manhattanDistance(point, $0.point) } ?? 0
            return progress * 10 + (target == nil ? 0 : 80) + (corpse == nil ? 0 : 30)
        }
        var score = 0
        let remaining = field?.remainingBudget(at: point) ?? 0
        let progress = strategic.map { manhattanDistance(actor.position, $0.point) - manhattanDistance(point, $0.point) } ?? 0
        let moveDenominator = max(1, profile.moveBudget - 1)
        if strategic?.captureBuilding != nil, primaryCommander(fraction: actor.fraction) != nil {
            if remaining > 0 { score += 100 + 100 * remaining / 10 }
            else {
                score += 100 * progress / moveDenominator
                if (rules.movementCostByTerrainID[terrainID(at: point)] ?? 1000) <= 1 { score += 20 }
            }
            let tile = tileID(at: point)
            let ownBuilding = (37...46).contains(tile) && areAllied((tile - 37) / 2, actor.fraction)
            if target == nil && !ownBuilding {
                if profile.propertyBits.contains(4), terrainID(at: point) == 9 { score += 300 }
                else if profile.propertyBits.contains(3), terrainID(at: point) == 8 || tile == 27 { score += 200 }
            }
        }
        if actor.type == 2, terrainID(at: point) == rules.waterTerrainID { score += 25 }
        if corpse != nil, actor.type == 3 { score += 100 }
        if corpse != nil, actor.type == 4 {
            score += 25 * snapshot.units.filter { $0.isAlive && areAllied($0.fraction, actor.fraction) && (1...2).contains(manhattanDistance(point, $0.position)) }.count
        }
        if !difficulty(for: actor.fraction).usesTactics, let target, let targetProfile = profiles[target.type] {
            let quality = estimatedCombatQuality(actor, at: point, against: target)
            if targetProfile.minimumRange == 1 && manhattanDistance(point, target.position) == 1 {
                score += quality * 3 / 2 - estimatedCombatQuality(target, at: point, against: actor)
            } else { score += quality * 2 }
            if target.type == 9 { score += 25 }
            if target.type == 11 { score += 100 }
        }
        score += rules.defenseByTerrainID[terrainID(at: point)] ?? 0
        if actor.hitPoints < 100, map.buildings.contains(where: { $0.x == point.x && $0.y == point.y && areAllied($0.fraction, actor.fraction) }) {
            score += 100 - actor.hitPoints
        }
        if remaining > 0 {
            let denominator = max(1, 10 - profile.moveBudget / 2)
            score += 50 + 100 * min(remaining, denominator) / denominator
        } else if strategic != nil { score += 50 * progress / moveDenominator }
        if let id = context.buildingReservations[point], id != actor.id, let reserved = unit(id: id), !reserved.hasActed,
           (context.reservedDistances[point] ?? 0) < (profiles[reserved.type]?.moveBudget ?? 0) { score -= 200 }
        score += 20 * manhattanDistance(actor.position, point) / moveDenominator
        return score
    }

    private func estimatedCombatQuality(
        _ unit: CampaignUnitState,
        at point: GridPoint,
        against target: CampaignUnitState?
    ) -> Int {
        guard let profile = profiles[unit.type] else { return 0 }
        let rankBonus = unit.rank * 2
        var offenseBonus = unit.statusOffenseBonus
        if profile.hasAntiAir, target.flatMap({ profiles[$0.type]?.canFly }) == true { offenseBonus += 15 }
        if unit.type == 4 && target?.type == 10 { offenseBonus += 15 }
        if profile.isAmphibious && terrainID(at: point) == rules.waterTerrainID { offenseBonus += 10 }

        var defenseBonus = unit.statusDefenseBonus + (rules.defenseByTerrainID[terrainID(at: point)] ?? 0)
        if profile.isAmphibious && terrainID(at: point) == rules.waterTerrainID { defenseBonus += 15 }
        if tileID(at: point) == 34 {
            offenseBonus += 25
            defenseBonus += 15
        }
        let rawQuality = profile.attackMinimum + rankBonus
            + profile.attackMaximum + rankBonus
            + profile.baseDefense + rankBonus
            + offenseBonus + defenseBonus
        return rawQuality * unit.hitPoints / 100
    }

    private func isInRange(from origin: GridPoint, to destination: GridPoint, profile: CampaignUnitProfile) -> Bool {
        let distance = manhattanDistance(origin, destination)
        return distance >= profile.minimumRange && distance <= profile.maximumRange
    }

    private func manhattanDistance(_ left: GridPoint, _ right: GridPoint) -> Int {
        abs(left.x - right.x) + abs(left.y - right.y)
    }

    private func isCommander(_ type: Int) -> Bool {
        type == 9
    }

    private func aiPriority(_ unitType: Int) -> Int {
        switch unitType {
        case 0: 0
        case 4: 1
        case 1: 2
        default: 3
        }
    }

    private mutating func updateCampaignProgress() {
        if snapshot.skirmish != nil { updateSkirmishOutcome(); return }
        if snapshot.missionRuntime != nil {
            evaluateCampaignScript()
            return
        }
        guard pendingPresentation == nil else { return }
        updateDefeatState()
        guard snapshot.phase == .playerTurn || snapshot.phase == .enemyTurn else { return }
        guard snapshot.pendingCampaignHelpID == nil,
              (snapshot.m0ScriptState ?? 22) >= 22 else { return }
        let enemyCount = snapshot.units.filter { $0.isAlive && $0.fraction == 2 }.count
        guard enemyCount == 0 else { return }
        if snapshot.reinforcementWave == 0 {
            spawnReinforcements()
            snapshot.reinforcementWave = 1
            snapshot.phase = .story
            snapshot.storyMode = .reinforcements
            snapshot.storyMessages = [225, 226]
            snapshot.storyIndex = 0
        } else if snapshot.reinforcementWave == 1 {
            snapshot.phase = .story
            snapshot.storyMode = .ending
            snapshot.storyMessages = [227, 228, 229, 230]
            snapshot.storyIndex = 0
        }
    }

    mutating func evaluateCampaignScript() {
        guard snapshot.skirmish == nil else { return }
        if snapshot.missionRuntime != nil { evaluateMissionScript() }
        else { evaluateM0Script() }
    }

    private mutating func evaluateM0Script() {
        guard pendingPresentation == nil, map.id == "m0",
              snapshot.phase == .playerTurn,
              snapshot.activeFraction == 1,
              snapshot.pendingCampaignHelpID == nil,
              var state = snapshot.m0ScriptState else { return }

        while true {
            switch state {
            case 14:
                state = 15
                snapshot.pendingCampaignHelpID = 196
            case 15:
                guard snapshot.selection?.rangeReady == true else {
                    snapshot.m0ScriptState = state
                    return
                }
                state = 16
                snapshot.pendingCampaignHelpID = 197
            case 16, 17:
                guard snapshot.lastFinishedMoveUnitID != nil else {
                    snapshot.m0ScriptState = state
                    return
                }
                snapshot.lastFinishedMoveUnitID = nil
                state += 1
                snapshot.pendingCampaignHelpID = 196 + state - 15
            case 18:
                snapshot.lastFinishedMoveUnitID = nil
                let finishedBlueUnitCount = snapshot.units.filter { $0.isAlive && $0.fraction == 1 && $0.hasActed }.count
                if finishedBlueUnitCount >= 3 {
                    state = 19
                    snapshot.pendingCampaignHelpID = 200
                } else if snapshot.currentTurn >= 1 {
                    state = 19
                    continue
                } else {
                    snapshot.m0ScriptState = state
                    return
                }
            case 19:
                guard snapshot.currentTurn >= 2 else {
                    snapshot.m0ScriptState = state
                    return
                }
                state = 20
                snapshot.pendingCampaignHelpID = 201
            case 20:
                state = 21
                snapshot.pendingCampaignHelpID = 202
            case 21:
                guard snapshot.selection != nil else {
                    snapshot.m0ScriptState = state
                    return
                }
                state = 22
                snapshot.pendingCampaignHelpID = 203
            case 22:
                snapshot.m0ScriptState = state
                return
            default:
                snapshot.m0ScriptState = nil
                return
            }

            snapshot.m0ScriptState = state
            if snapshot.pendingCampaignHelpID != nil { return }
        }
    }

    private mutating func spawnReinforcements() {
        let animated = snapshot.storyPresentationsEnabled == true
        if animated {
            enqueueScene(.init(.pause, durationTicks: 20))
            enqueueScene(.init(.camera, focus: GridPoint(x: 1, y: 1)))
        }
        snapshot.units.append(CampaignUnitState(
            id: snapshot.nextUnitID,
            type: 1,
            fraction: 2,
            x: 1,
            y: animated ? 1 : 2
        ))
        if animated {
            enqueueScene(.init(.arrival, focus: GridPoint(x: 1, y: 1),
                movements: [.init(unitID: snapshot.nextUnitID, route: [GridPoint(x: 1, y: 1), GridPoint(x: 1, y: 2)])], durationTicks: 10))
            snapshot.units[snapshot.units.count - 1].y = 2
            enqueueScene(.init(.camera, focus: GridPoint(x: 10, y: 10), cameraOrigin: GridPoint(x: 1, y: 1)))
        }
        snapshot.nextUnitID += 1
        snapshot.units.append(CampaignUnitState(
            id: snapshot.nextUnitID,
            type: 0,
            fraction: 2,
            x: 10,
            y: animated ? 10 : 9
        ))
        if animated {
            enqueueScene(.init(.arrival, focus: GridPoint(x: 10, y: 10),
                movements: [.init(unitID: snapshot.nextUnitID, route: [GridPoint(x: 10, y: 10), GridPoint(x: 10, y: 9)])], durationTicks: 10))
            snapshot.units[snapshot.units.count - 1].y = 9
            if let commander = snapshot.units.first(where: { $0.type == 9 && $0.fraction == 1 }) {
                enqueueScene(.init(.camera, focus: commander.position, cameraOrigin: GridPoint(x: 10, y: 10)))
            }
        }
        snapshot.nextUnitID += 1
    }

    mutating func updateDefeatState() {
        if snapshot.skirmish != nil { updateSkirmishOutcome(); return }
        let commanderSurvives = snapshot.units.contains {
            $0.fraction == 1 && $0.type == 9 && $0.isAlive
        }
        let castleSurvives = map.buildings.contains { $0.fraction == 1 && $0.terrain == 9 }
        if !commanderSurvives && !castleSurvives {
            snapshot.phase = .defeat
            snapshot.storyMode = nil
            snapshot.storyMessages = []
            snapshot.storyIndex = -1
        }
    }
}

public enum CampaignBuildingAction: String, Sendable {
    case occupy, repair
    public var textID: Int { self == .occupy ? 73 : 74 }
}

public struct CampaignRecruitmentOption: Equatable, Sendable {
    public let unit: CampaignUnitState
    public let cost: Int
    public let commanderID: Int?
    public let isAvailable: Bool
}

extension CampaignSession {
    public var allowedUnitType: Int {
        snapshot.allowedUnitType ?? [0: 0, 1: 1, 2: 0, 3: 7, 4: 0, 5: 7, 6: 8, 7: 8][map.index] ?? 0
    }

    public func availableBuildingAction(unitID: Int) -> CampaignBuildingAction? {
        guard isInputEnabled, let unit = unit(id: unitID), unit.fraction == snapshot.activeFraction, unit.isAlive, !unit.hasActed,
              snapshot.selection?.moveOrigin == nil || snapshot.selection?.unitID == unitID,
              snapshot.recruitingUnitID == nil || (snapshot.recruitingUnitID == unitID && unit.hasMoved) else { return nil }
        return buildingAction(for: unit)
    }

    private func buildingAction(for unit: CampaignUnitState) -> CampaignBuildingAction? {
        guard let profile = profiles[unit.type] else { return nil }
        let tile = tileID(at: unit.position)
        let terrain = rules.terrainByTileID[tile]
        if terrain == 8, profile.propertyBits.contains(3), !(37...46).contains(tile) { return .repair }
        guard (37...46).contains(tile), !areAllied((tile - 37) / 2, unit.fraction) else { return nil }
        if terrain == 8, profile.propertyBits.contains(3) { return .occupy }
        if terrain == 9, profile.propertyBits.contains(4) { return .occupy }
        return nil
    }

    public mutating func performBuildingAction(unitID: Int, recordPresentation: Bool = false) throws {
        guard let action = availableBuildingAction(unitID: unitID),
              let index = snapshot.units.firstIndex(where: { $0.id == unitID }) else {
            throw CampaignSessionError.buildingActionUnavailable
        }
        commitBuildingAction(action, unit: snapshot.units[index], recordPresentation: recordPresentation)
        finishAction(at: index, recordPresentation: recordPresentation)
        updateCampaignProgress()
    }

    private mutating func commitBuildingAction(_ action: CampaignBuildingAction, unit: CampaignUnitState, recordPresentation: Bool) {
        let tile = action == .repair ? 37 : 37 + unit.fraction * 2 + (tileID(at: unit.position) - 37) % 2
        if snapshot.tileOverrides == nil { snapshot.tileOverrides = [:] }
        let index = unit.y * originalMap.width + unit.x
        snapshot.tileOverrides?[index] = tile
        if action == .occupy, unit.fraction == 1, snapshot.missionRuntime?.state == 20 {
            snapshot.missionRuntime?.blueOccupationObserved = true
        }
        if recordPresentation {
            // The original changes the tile before showing OCCUPIED!/REPAIRED!.
            enqueueScene(.init(.building, focus: unit.position, durationTicks: 20, unitID: unit.id, textID: action.textID))
        }
    }

    public func canOpenRecruitment(at castle: GridPoint) -> Bool {
        isInputEnabled && tileID(at: castle) == 38 + snapshot.activeFraction * 2 && snapshot.selection?.moveOrigin == nil && snapshot.recruitingUnitID == nil
    }

    public func recruitmentOptions(at castle: GridPoint) -> [CampaignRecruitmentOption] {
        guard canOpenRecruitment(at: castle) else { return [] }
        let kings = snapshot.units.filter { $0.type == 9 && $0.fraction == snapshot.activeFraction && !$0.isAlive }
        let candidates: [(CampaignUnitState, Int?)] = kings.map { ($0, $0.id) }
            + (0...allowedUnitType).compactMap { type in
                guard profiles[type] != nil else { return nil }
                return (CampaignUnitState(id: -1, type: type, fraction: snapshot.activeFraction, x: castle.x, y: castle.y), nil)
            }
        return candidates.map { unit, commanderID in
            let cost = commanderID == nil ? (profiles[unit.type]?.cost ?? 0) : (unit.revivalCost ?? 400)
            let count = snapshot.units.filter { $0.isAlive && $0.fraction == snapshot.activeFraction && $0.type != 10 }.count
            let available = cost > 0 && cost <= money(for: snapshot.activeFraction) && count < (snapshot.unitCap ?? 100)
                && canDeploy(type: unit.type, fraction: snapshot.activeFraction, at: castle)
            return CampaignRecruitmentOption(unit: unit, cost: cost, commanderID: commanderID, isAvailable: available)
        }
    }

    private func canDeploy(type: Int, fraction: Int, at castle: GridPoint) -> Bool {
        if units(at: castle).isEmpty { return true }
        let candidate = CampaignUnitState(id: -1, type: type, fraction: fraction, x: castle.x, y: castle.y)
        return (try? calculateMovementRange(for: candidate))?.reachableDestinations.isEmpty == false
    }

    /// Deduction and the new unit are committed together. Deployment may be saved
    /// even when another friendly unit is still standing on the castle.
    @discardableResult
    public mutating func recruit(type: Int, commanderID: Int? = nil, at castle: GridPoint) throws -> Int {
        guard let option = recruitmentOptions(at: castle).first(where: { $0.unit.type == type && $0.commanderID == commanderID }),
              option.isAvailable else { throw CampaignSessionError.recruitmentUnavailable }
        let id = purchase(type: type, commanderID: commanderID, cost: option.cost, fraction: snapshot.activeFraction, at: castle)
        snapshot.recruitingUnitID = id
        snapshot.selection = CampaignSelection(unitID: id, rangeReady: true)
        return id
    }

    private mutating func purchase(type: Int, commanderID: Int?, cost: Int, fraction: Int,
                                   at castle: GridPoint, awaitRevivalMovement: Bool = false) -> Int {
        let id: Int
        if let commanderID, let index = snapshot.units.firstIndex(where: { $0.id == commanderID }) {
            id = commanderID
            snapshot.units[index].x = castle.x
            snapshot.units[index].y = castle.y
            snapshot.units[index].hitPoints = 100
            snapshot.units[index].hasMoved = false
            snapshot.units[index].hasActed = false
            snapshot.units[index].revivalAwaitingMovement = awaitRevivalMovement ? true : nil
            snapshot.units[index].corpseTransitionsRemaining = awaitRevivalMovement ? 3 : nil
            snapshot.units[index].removedFromMap = nil
        } else {
            id = snapshot.nextUnitID
            snapshot.nextUnitID += 1
            snapshot.units.append(.init(id: id, type: type, fraction: fraction, x: castle.x, y: castle.y))
        }
        if snapshot.moneyByFraction == nil { snapshot.moneyByFraction = [:] }
        let remainingMoney = money(for: fraction) - cost
        snapshot.moneyByFraction?[fraction] = remainingMoney
        return id
    }

    private func canEnemyBuy(type: Int, cost: Int, at castle: GridPoint) -> Bool {
        guard (type <= allowedUnitType || type == 9), cost > 0, cost <= money(for: snapshot.activeFraction),
              snapshot.units.filter({ $0.isAlive && $0.fraction == snapshot.activeFraction && $0.type != 10 }).count < (snapshot.unitCap ?? 100) else { return false }
        return canDeploy(type: type, fraction: snapshot.activeFraction, at: castle)
    }

    /// MainDisplayable.sub_1043 / JAR GameController.method_191.
    private mutating func recruitEnemyUnit() -> Int? {
        let castles = orderedBuildings.filter { $0.fraction == snapshot.activeFraction && $0.terrain == 9 }
        guard let first = castles.first else { return nil }
        var selected = GridPoint(x: first.x, y: first.y)
        var nearestDistance = 6666
        let capturers = snapshot.units.reversed().filter { $0.removedFromMap != true && !areAllied($0.fraction, snapshot.activeFraction) && profiles[$0.type]?.propertyBits.contains(4) == true }
        for castle in castles {
            let point = GridPoint(x: castle.x, y: castle.y)
            let references = capturers.isEmpty
                ? orderedBuildings.filter { $0.terrain == 9 && !areAllied($0.fraction, snapshot.activeFraction) }.map { GridPoint(x: $0.x, y: $0.y) }
                : capturers.map(\.position)
            for reference in references {
                let distance = manhattanDistance(point, reference)
                if distance < nearestDistance { nearestDistance = distance; selected = point }
            }
        }
        // Preserve the source loop's lack of break: only its last purchased king
        // starts moving. Earlier paid kings retain corpse state until bought and
        // selected on a later pass; this is an original multiple-king edge case.
        var revived: Int?
        for king in snapshot.units.filter({ $0.type == 9 && $0.fraction == snapshot.activeFraction && !$0.isAlive }) {
            let cost = king.revivalCost ?? 400
            if canEnemyBuy(type: 9, cost: cost, at: selected) {
                revived = purchase(type: 9, commanderID: king.id, cost: cost, fraction: snapshot.activeFraction, at: selected, awaitRevivalMovement: true)
                if difficulty(for: snapshot.activeFraction).usesTactics { break }
            }
        }
        if let revived, let index = snapshot.units.firstIndex(where: { $0.id == revived }) {
            snapshot.units[index].revivalAwaitingMovement = nil
            snapshot.units[index].corpseTransitionsRemaining = nil
            return revived
        }
        if difficulty(for: snapshot.activeFraction).usesTactics {
            return recruitTacticalUnit(at: selected)
        }
        let ownUnits = snapshot.units.filter { $0.isAlive && $0.fraction == snapshot.activeFraction }
        for type in [0, 1] {
            let cost = profiles[type]?.cost ?? 0
            if ownUnits.filter({ $0.type == type }).count < 2, canEnemyBuy(type: type, cost: cost, at: selected) {
                return purchase(type: type, commanderID: nil, cost: cost, fraction: snapshot.activeFraction, at: selected)
            }
        }
        let opponentCount = snapshot.units.filter { $0.isAlive && !areAllied($0.fraction, snapshot.activeFraction) }.count
        guard money(for: snapshot.activeFraction) >= 1000 || ownUnits.count < 8 || ownUnits.count < opponentCount else { return nil }
        let candidates = (1..<max(1, min(8, allowedUnitType) + 1)).filter { type in
            let cost = profiles[type]?.cost ?? 0
            return (ownUnits.allSatisfy { $0.type != type } || cost >= 600) && canEnemyBuy(type: type, cost: cost, at: selected)
        }
        guard !candidates.isEmpty else { return nil }
        var random = JavaRandom(internalState: snapshot.randomState)
        // Same single nextInt draw and remainder as Java. Widen first so the
        // legacy abs(Int.MIN_VALUE) out-of-range bug cannot crash the iOS app.
        let choice = Int(Int64(random.nextInt32()).magnitude % UInt64(candidates.count))
        snapshot.randomState = random.state
        let type = candidates[choice]
        return purchase(type: type, commanderID: nil, cost: profiles[type]!.cost, fraction: snapshot.activeFraction, at: selected)
    }

}

extension CampaignSession {
    public func corpse(at point: GridPoint) -> CampaignUnitState? {
        snapshot.units.first { $0.isCorpse && $0.position == point }
    }

    public func attackRangeCells(for unitID: Int) -> [GridPoint] {
        guard let unit = unit(id: unitID), let profile = profiles[unit.type] else { return [] }
        return (0..<map.width).flatMap { x in
            (0..<map.height).compactMap { y in
                let point = GridPoint(x: x, y: y)
                return isInRange(from: unit.position, to: point, profile: profile) ? point : nil
            }
        }
    }

    public func summonableCorpses(for unitID: Int) -> [CampaignUnitState] {
        guard isInputEnabled, let caster = unit(id: unitID), caster.isAlive, caster.fraction == snapshot.activeFraction, !caster.hasActed,
              snapshot.selection?.moveOrigin == nil || snapshot.selection?.unitID == unitID,
              snapshot.recruitingUnitID == nil || (snapshot.recruitingUnitID == unitID && caster.hasMoved) else { return [] }
        return corpsesInRange(of: caster, at: caster.position)
    }

    private func corpsesInRange(of caster: CampaignUnitState, at point: GridPoint) -> [CampaignUnitState] {
        guard let profile = profiles[caster.type], profile.canRaiseSkeleton else { return [] }
        var seen: Set<GridPoint> = []
        return snapshot.units.filter {
            $0.isCorpse && map.tileID(x: $0.x, y: $0.y) != nil
                && isInRange(from: point, to: $0.position, profile: profile) && seen.insert($0.position).inserted
        }.sorted { $0.x == $1.x ? $0.y < $1.y : $0.x < $1.x }
    }

    @discardableResult
    public mutating func raiseSkeleton(unitID: Int, corpseID: Int, recordPresentation: Bool = false) throws -> Int {
        guard summonableCorpses(for: unitID).contains(where: { $0.id == corpseID }),
              let index = snapshot.units.firstIndex(where: { $0.id == unitID }) else { throw CampaignSessionError.summoningUnavailable }
        let id = commitSummoning(casterIndex: index, corpseID: corpseID, recordPresentation: recordPresentation)
        finishAction(at: index, applyUnitEffects: false, finalUnitID: id)
        updateCampaignProgress()
        return id
    }

    private mutating func commitSummoning(casterIndex: Int, corpseID: Int, recordPresentation: Bool) -> Int {
        let corpseIndex = snapshot.units.firstIndex { $0.id == corpseID }!
        let point = snapshot.units[corpseIndex].position
        let fraction = snapshot.units[casterIndex].fraction
        finishUnitAction(at: casterIndex, recordPresentation: recordPresentation)
        if recordPresentation { enqueueScene(.init(.summoning, focus: point, durationTicks: 8, unitID: corpseID)) }
        snapshot.units[corpseIndex].removedFromMap = true
        let id = snapshot.nextUnitID
        snapshot.nextUnitID += 1
        snapshot.units.append(.init(id: id, type: 10, fraction: fraction, x: point.x, y: point.y))
        // Original createUnit(...).finishMove(): full HP, already acted; no cost/cap check.
        finishUnitAction(at: snapshot.units.count - 1, recordPresentation: false)
        return id
    }

    private mutating func finishUnitAction(at index: Int, recordPresentation: Bool) {
        snapshot.units[index].hasActed = true
        let actor = snapshot.units[index]
        if let grave = snapshot.units.firstIndex(where: { $0.isCorpse && $0.position == actor.position }) {
            snapshot.units[grave].removedFromMap = true
        }
        guard profiles[actor.type]?.propertyBits.contains(8) == true else { return }
        var recipients: [Int] = []
        var seen: Set<GridPoint> = []
        for index in snapshot.units.indices {
            let unit = snapshot.units[index]
            guard unit.isAlive,
                  (1...2).contains(manhattanDistance(actor.position, unit.position)), seen.insert(unit.position).inserted,
                  areAllied(unit.fraction, actor.fraction) else { continue }
            snapshot.units[index].effects = (unit.effects ?? 0) | 2
            recipients.append(unit.id)
        }
        if recordPresentation, !recipients.isEmpty { enqueueScene(.init(.blessing, affectedUnitIDs: recipients)) }
    }

    private mutating func markDeadUnit(at index: Int) {
        if snapshot.units[index].type == 9, snapshot.missionRuntime?.state == 22 {
            snapshot.missionRuntime?.commanderDeathObserved = true
        }
        snapshot.units[index].corpseTransitionsRemaining = 3
        snapshot.units[index].revivalAwaitingMovement = nil
        switch snapshot.units[index].type {
        case 9:
            snapshot.units[index].x = -10
            snapshot.units[index].y = -10
            snapshot.units[index].effects = nil
            snapshot.units[index].poisonSourceFraction = nil
        case 10, 11:
            snapshot.units[index].removedFromMap = true
        default:
            break
        }
    }
}

extension CampaignSession {
    public func difficulty(for fraction: Int) -> AIDifficulty {
        if let settings = snapshot.skirmish {
            return settings.players.first { $0.fraction == fraction }?.resolvedDifficulty ?? .normal
        }
        return snapshot.aiDifficulty ?? .normal
    }

    /// Uses the combat formula's mean roll; planning never consumes combat RNG.
    private func predictedDamage(_ attacker: CampaignUnitState, at origin: GridPoint,
                                 against defender: CampaignUnitState, at destination: GridPoint) -> Int {
        guard let offense = profiles[attacker.type], let defense = profiles[defender.type],
              offense.characterCount > 0, offense.attackMaximum > offense.attackMinimum else { return 0 }
        var attack = (offense.attackMinimum + offense.attackMaximum - 1) / 2 + attacker.rank * 2 + attacker.statusOffenseBonus
        if offense.hasAntiAir && defense.canFly { attack += 15 }
        if attacker.type == 4 && defender.type == 10 { attack += 15 }
        if offense.isAmphibious && terrainID(at: origin) == rules.waterTerrainID { attack += 10 }
        if tileID(at: origin) == 34 { attack += 25 }
        var armor = defense.baseDefense + defender.rank * 2 + defender.statusDefenseBonus
            + (rules.defenseByTerrainID[terrainID(at: destination)] ?? 0)
        if defense.isAmphibious && terrainID(at: destination) == rules.waterTerrainID { armor += 15 }
        if tileID(at: destination) == 34 { armor += 15 }
        return min(defender.hitPoints, max(0, (attack - armor) * attacker.hitPoints / 100))
    }

    /// Each enemy contributes its strongest legal attack at each candidate cell,
    /// rather than counting all of its possible moves as separate attackers.
    private func predictedThreats(for actor: CampaignUnitState, range: MovementRange) -> [GridPoint: [Int: Int]] {
        let cells = range.reachableDestinations.union([actor.position])
        var threats: [GridPoint: [Int: Int]] = [:]
        for enemy in snapshot.units where enemy.isAlive && !areAllied(enemy.fraction, actor.fraction) {
            guard let profile = profiles[enemy.type], profile.characterCount > 0,
                  manhattanDistance(enemy.position, actor.position) < profile.moveBudget + profile.maximumRange + (profiles[actor.type]?.moveBudget ?? 0) else { continue }
            let origins: Set<GridPoint>
            if profile.propertyBits.contains(9) { origins = [enemy.position] }
            else if let movement = try? calculateMovementRange(for: enemy, ignoringUnitID: actor.id) {
                origins = movement.reachableDestinations.union([enemy.position])
            } else { continue }
            for cell in cells {
                var damage = 0
                for origin in origins where origin != cell && isInRange(from: origin, to: cell, profile: profile) {
                    damage = max(damage, predictedDamage(enemy, at: origin, against: actor, at: cell))
                }
                if damage > 0 { threats[cell, default: [:]][enemy.id] = damage }
            }
        }
        return threats
    }

    private func tacticalAdjustment(actor: CampaignUnitState, at point: GridPoint,
                                    target: CampaignUnitState?, threats: [Int: Int]) -> Int {
        let level = difficulty(for: actor.fraction)
        guard level.usesTactics else { return 0 }
        var score = 0
        var counterDamage = 0
        var targetHealth = target?.hitPoints ?? 0
        if var target, let profile = profiles[target.type] {
            let damage = predictedDamage(actor, at: point, against: target, at: target.position)
            targetHealth = max(0, target.hitPoints - damage)
            score += damage * 6
            if targetHealth == 0 {
                score += 200 + profile.cost / 3 + (target.type == 9 ? 300 : 0)
            } else {
                target.hitPoints = targetHealth
                if profile.minimumRange == 1 && manhattanDistance(point, target.position) == 1 {
                    counterDamage = predictedDamage(target, at: target.position, against: actor, at: point)
                    score -= counterDamage * 6
                }
                if level == .expert {
                    score += (100 - targetHealth) / 2
                    if profiles[actor.type]?.hasAntiAir == true && profile.canFly { score += 60 }
                }
            }
        }
        let incoming = threats.map { id, damage in
            id == target?.id ? damage * targetHealth / max(1, target?.hitPoints ?? 1) : damage
        }.sorted(by: >)
        // Hard considers the strongest attack plus some supporting damage;
        // expert evaluates up to three attacks and protects commanders more.
        let risk = level == .expert ? incoming.prefix(3).reduce(0, +)
            : (incoming.first ?? 0) + incoming.dropFirst().prefix(2).reduce(0, +) / 2
        let healthAfterCounter = max(0, actor.hitPoints - counterDamage)
        score -= risk * (level == .expert ? 4 : 2)
        if counterDamage >= actor.hitPoints || (risk > 0 && risk >= healthAfterCounter) {
            score -= (level == .expert ? 600 : 300) * (actor.type == 9 ? 2 : 1)
        }
        if level == .expert, actor.type == 9 { score -= risk * 2 }
        return score
    }

    private mutating func recruitTacticalUnit(at castle: GridPoint) -> Int? {
        let allies = snapshot.units.filter { $0.isAlive && $0.fraction == snapshot.activeFraction }
        let enemies = snapshot.units.filter { $0.isAlive && !areAllied($0.fraction, snapshot.activeFraction) }
            .sorted { manhattanDistance($0.position, castle) < manhattanDistance($1.position, castle) }
        let needsCapturer = orderedBuildings.contains { !areAllied($0.fraction, snapshot.activeFraction) }
            && allies.filter { $0.type == 0 || $0.type == 9 }.count < 3
        let level = difficulty(for: snapshot.activeFraction)
        var best: (type: Int, score: Int)?
        for type in 0...allowedUnitType {
            guard let profile = profiles[type], canEnemyBuy(type: type, cost: profile.cost, at: castle) else { continue }
            let recruit = CampaignUnitState(id: -1, type: type, fraction: snapshot.activeFraction, x: castle.x, y: castle.y)
            let damage = enemies.prefix(4).map { predictedDamage(recruit, at: castle, against: $0, at: $0.position) }
            var score = damage.reduce(0, +) * 4 / max(1, damage.count)
                + profile.baseDefense + profile.moveBudget * 4 - profile.cost / 20
                - allies.filter { $0.type == type }.count * 55
            if needsCapturer && type == 0 { score += 180 }
            if profile.maximumRange > 1 { score += 45 }
            if type == 3 && snapshot.units.contains(where: { $0.isCorpse }) { score += 60 }
            if level == .expert {
                if profile.hasAntiAir { score += min(3, enemies.filter { profiles[$0.type]?.canFly == true }.count) * 90 }
                if type == 4 { score += min(3, enemies.filter { $0.type == 10 }.count) * 60 }
                if profile.canFly && enemies.contains(where: { profiles[$0.type]?.minimumRange ?? 1 > 1 }) { score += 50 }
            }
            if best == nil || score > best!.score { best = (type, score) }
        }
        guard let best, let profile = profiles[best.type] else { return nil }
        return purchase(type: best.type, commanderID: nil, cost: profile.cost, fraction: snapshot.activeFraction, at: castle)
    }
}

extension CampaignSession {
    /// Reverse shortest paths account for terrain and obstacles beyond the
    /// original ten-cell strategic field. Allied units may move out of the way.
    private func strategicDistances(for actor: CampaignUnitState, goal: GridPoint) -> [GridPoint: Int] {
        guard let profile = profiles[actor.type] else { return [:] }
        let blocked = Set(snapshot.units.filter { $0.isAlive && !areAllied($0.fraction, actor.fraction) }.map(\.position))
        var distances = [goal: 0]
        var queue = [goal]
        var head = 0
        while head < queue.count {
            let current = queue[head]
            head += 1
            let terrain = terrainID(at: current)
            if actor.type == 11 && terrain == rules.mountainTerrainID { continue }
            let cost = profile.canFly || (profile.isAmphibious && terrain == rules.waterTerrainID)
                ? 1 : rules.movementCostByTerrainID[terrain] ?? 1000
            guard cost < 1000 else { continue }
            let nextDistance = distances[current]! + cost
            for next in [GridPoint(x: current.x, y: current.y - 1), GridPoint(x: current.x, y: current.y + 1),
                         GridPoint(x: current.x - 1, y: current.y), GridPoint(x: current.x + 1, y: current.y)] {
                guard originalMap.tileID(x: next.x, y: next.y) != nil,
                      !blocked.contains(next) || next == goal,
                      nextDistance < (distances[next] ?? Int.max) else { continue }
                distances[next] = nextDistance
                queue.append(next)
            }
        }
        return distances
    }
}
