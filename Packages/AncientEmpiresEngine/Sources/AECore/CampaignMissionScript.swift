import Foundation

/// Source scriptState and named references survive every dialogue/scene boundary.
/// Unit identities remain valid after they leave the map's living-unit collection.
public struct CampaignMissionRuntime: Codable, Equatable, Sendable {
    public var state: Int
    public var active: Bool
    public var references: [String: Int]
    public var portraitID: Int?
    public var afterDialogue: [CampaignScenePresentation]?
    public var blueOccupationObserved: Bool?
    public var commanderDeathObserved: Bool?
    public var screenObscured: Bool?
    public var objectiveTextID: Int

    public init(state: Int = 0, active: Bool = false, references: [String: Int] = [:], objectiveTextID: Int) {
        self.state = state
        self.active = active
        self.references = references
        self.objectiveTextID = objectiveTextID
    }
}

extension CampaignSession {
    public var objectiveTextID: Int { snapshot.skirmish != nil ? 137 : (snapshot.missionRuntime?.objectiveTextID ?? (129 + map.index)) }
    public var storyPortraitID: Int? {
        if let runtime = snapshot.missionRuntime { return runtime.portraitID }
        return snapshot.currentStoryMessageID.flatMap { [221: 2, 222: 2, 223: 0, 224: 5, 225: 5, 226: 0, 227: 2, 228: 0, 229: 2, 230: 0][$0] }
    }

    mutating func configureMissionScript() {
        switch map.id {
        case "m1":
            snapshot.moneyByFraction = [1: 300, 2: 50]
            snapshot.allowedUnitType = 1
            snapshot.missionRuntime = .init(objectiveTextID: 130)
            for fraction in [1, 2] {
                if let king = snapshot.units.last(where: { $0.fraction == fraction && $0.type == 9 }) {
                    snapshot.missionRuntime?.references["king\(fraction)"] = king.id
                    if fraction == 1, let index = snapshot.units.firstIndex(where: { $0.id == king.id }) {
                        snapshot.units[index].commanderVariant = 2
                    }
                }
            }
        case "m2":
            snapshot.moneyByFraction = [1: 0, 2: 0]
            snapshot.allowedUnitType = 0
            snapshot.missionRuntime = .init(objectiveTextID: 131)
            let references: [(String, GridPoint)] = [
                ("king1", GridPoint(x: 8, y: 16)),
                ("escortLeader", GridPoint(x: 8, y: 17)),
                ("escortArcher", GridPoint(x: 8, y: 18)),
                ("escortFollower", GridPoint(x: 8, y: 19)),
            ]
            for (key, point) in references {
                if let unit = snapshot.units.first(where: { $0.fraction == 1 && $0.position == point }) {
                    snapshot.missionRuntime?.references[key] = unit.id
                }
            }
        case "m3":
            snapshot.moneyByFraction = [1: 400, 2: 400]
            snapshot.allowedUnitType = 7
            snapshot.missionRuntime = .init(objectiveTextID: 132)
            if let king = snapshot.units.first(where: {
                $0.fraction == 1 && $0.type == 9 && $0.position == GridPoint(x: 0, y: 5)
            }) {
                snapshot.missionRuntime?.references["king1"] = king.id
            }
            if let catapult = snapshot.units.first(where: {
                $0.fraction == 2 && $0.type == 7 && $0.position == GridPoint(x: 10, y: 10)
            }) {
                snapshot.missionRuntime?.references["catapult"] = catapult.id
            }
            _ = scriptCreate(type: 0, fraction: 1, at: GridPoint(x: -1, y: 5), reference: "reinforcementSoldier")
            _ = scriptCreate(type: 2, fraction: 1, at: GridPoint(x: -2, y: 5), reference: "reinforcementElemental")
            _ = scriptCreate(type: 3, fraction: 1, at: GridPoint(x: -3, y: 5), reference: "reinforcementSorceress")
        case "m4":
            snapshot.moneyByFraction = [1: 0, 2: 0]
            snapshot.allowedUnitType = 0
            snapshot.missionRuntime = .init(objectiveTextID: 133)
            if let king = snapshot.units.first(where: {
                $0.fraction == 1 && $0.type == 9 && $0.position == GridPoint(x: 11, y: 1)
            }) {
                snapshot.missionRuntime?.references["king1"] = king.id
            }
            if let crystal = snapshot.units.first(where: {
                $0.fraction == 1 && $0.type == 11 && $0.position == GridPoint(x: 11, y: 0)
            }) {
                snapshot.missionRuntime?.references["crystal"] = crystal.id
            }
            // initStoryMission moves the existing escort off the north edge before
            // assigning all six routes back to their authored map positions.
            let entrances: [(GridPoint, GridPoint)] = [
                (GridPoint(x: 11, y: 2), GridPoint(x: 11, y: -3)),
                (GridPoint(x: 10, y: 1), GridPoint(x: 10, y: -5)),
                (GridPoint(x: 11, y: 1), GridPoint(x: 11, y: -5)),
                (GridPoint(x: 12, y: 1), GridPoint(x: 12, y: -5)),
                (GridPoint(x: 11, y: 0), GridPoint(x: 11, y: -7)),
                (GridPoint(x: 12, y: 0), GridPoint(x: 12, y: -7)),
            ]
            for (destination, origin) in entrances {
                if let index = snapshot.units.firstIndex(where: { $0.fraction == 1 && $0.position == destination }) {
                    snapshot.units[index].x = origin.x
                    snapshot.units[index].y = origin.y
                }
            }
        case "m5":
            snapshot.moneyByFraction = [1: 600, 2: 600]
            snapshot.allowedUnitType = 7
            snapshot.missionRuntime = .init(objectiveTextID: 134)
            if let index = snapshot.units.firstIndex(where: {
                $0.fraction == 1 && $0.type == 9 && $0.position == GridPoint(x: 5, y: 17)
            }) {
                snapshot.units[index].commanderVariant = 2
                snapshot.missionRuntime?.references["king1"] = snapshot.units[index].id
            }
        case "m6":
            snapshot.moneyByFraction = [1: 400, 2: 600]
            snapshot.allowedUnitType = 8
            snapshot.missionRuntime = .init(objectiveTextID: 135)
            if let king = snapshot.units.first(where: {
                $0.fraction == 1 && $0.type == 9 && $0.position == GridPoint(x: 13, y: 0)
            }) {
                snapshot.missionRuntime?.references["king1"] = king.id
            }
            _ = scriptCreate(type: 0, fraction: 1, at: GridPoint(x: 13, y: -1), reference: "escortSoldier")
            _ = scriptCreate(type: 1, fraction: 1, at: GridPoint(x: 13, y: -1), reference: "escortArcher")
            _ = scriptCreate(type: 3, fraction: 1, at: GridPoint(x: 13, y: -1), reference: "escortSorceress")
            _ = scriptCreate(type: 11, fraction: 1, at: GridPoint(x: 13, y: -1), reference: "crystal")
        case "m7":
            snapshot.moneyByFraction = [1: 800, 2: 200]
            snapshot.allowedUnitType = 8
            snapshot.missionRuntime = .init(objectiveTextID: 136)
            let references: [(String, GridPoint)] = [
                ("king1", GridPoint(x: 8, y: 15)),
                ("valadorn", GridPoint(x: 6, y: 15)),
                ("saeth", GridPoint(x: 7, y: 4)),
                ("crystalWest", GridPoint(x: 5, y: 2)),
                ("crystalCenter", GridPoint(x: 7, y: 3)),
                ("crystalEast", GridPoint(x: 9, y: 2)),
                ("heavenFuryDemo", GridPoint(x: 9, y: 15)),
            ]
            for (key, point) in references {
                if let unit = snapshot.units.first(where: { $0.position == point }) {
                    snapshot.missionRuntime?.references[key] = unit.id
                }
            }
            if let id = snapshot.missionRuntime?.references["king1"] { scriptSetCommanderVariant(id, to: 0) }
            if let id = snapshot.missionRuntime?.references["valadorn"] { scriptSetCommanderVariant(id, to: 2) }
            if let id = snapshot.missionRuntime?.references["saeth"] { scriptSetCommanderVariant(id, to: 3) }
        default:
            break
        }
    }

    mutating func evaluateMissionScript() {
        guard snapshot.missionRuntime != nil, pendingPresentation == nil,
              snapshot.pendingCampaignHelpID == nil, snapshot.currentStoryMessageID == nil,
              snapshot.phase != .victory, snapshot.phase != .defeat else { return }
        updateDefeatState()
        guard snapshot.phase != .defeat else { return }
        if let scenes = snapshot.missionRuntime?.afterDialogue {
            snapshot.missionRuntime?.afterDialogue = nil
            if snapshot.storyPresentationsEnabled == true {
                for scene in scenes { enqueueScene(scene) }
                if pendingPresentation != nil { return }
            }
        }
        if snapshot.missionRuntime?.active == true {
            snapshot.phase = snapshot.activeFraction == 1 ? .playerTurn : .enemyTurn
        } else {
            snapshot.phase = .opening
        }
        // Each iteration either advances one source case or stops on an input,
        // presentation, or gameplay condition. Bound malformed saves as well.
        for _ in 0..<64 {
            let before = snapshot.missionRuntime?.state
            switch map.id {
            case "m1": stepMissionOne()
            case "m2": stepMissionTwo()
            case "m3": stepMissionThree()
            case "m4": stepMissionFour()
            case "m5": stepMissionFive()
            case "m6": stepMissionSix()
            case "m7": stepMissionSeven()
            default: return
            }
            if pendingPresentation != nil || snapshot.pendingCampaignHelpID != nil || snapshot.currentStoryMessageID != nil
                || snapshot.phase == .victory || snapshot.phase == .defeat || snapshot.missionRuntime?.state == before { return }
        }
    }

    private mutating func stepMissionOne() {
        guard let runtime = snapshot.missionRuntime else { return }
        let king = scriptUnit("king1")
        switch runtime.state {
        case 0: // initStoryMission:7686–7690. Camera and three arrivals run together.
            let moves = [(GridPoint(x: 7, y: 12), GridPoint(x: 7, y: 10)),
                         (GridPoint(x: 8, y: 11), GridPoint(x: 8, y: 9)),
                         (GridPoint(x: 9, y: 12), GridPoint(x: 9, y: 10))].compactMap { from, to in
                snapshot.units.first { $0.position == from && $0.isAlive }.map { ($0.id, to, 0) }
            }
            scriptNext()
            scriptMove(moves, focus: GridPoint(x: 7, y: 3), cameraOrigin: king?.position, cameraStep: 2, speed: 4)
        case 1:
            scriptNext(); scriptScene(.init(.pause, durationTicks: 10))
        case 2:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 12, y: 3), cameraStep: 4))
        case 3:
            scriptNext(); scriptDialogue(231, portrait: 1, after: [.init(.pause, durationTicks: 10)])
        case 4:
            scriptNext(); scriptDialogue(232, portrait: 3, after: [.init(.camera, focus: king?.position, cameraStep: 4)])
        case 5:
            scriptNext(); scriptDialogue(233, portrait: 5, after: [.init(.camera, focus: GridPoint(x: 7, y: 3), cameraStep: 4)])
        case 6:
            let leader = scriptCreate(type: 0, fraction: 2, at: GridPoint(x: 7, y: 3), reference: "escortLeader")
            let crystal = scriptCreate(type: 11, fraction: 2, at: GridPoint(x: 7, y: 3), reference: "escortCrystal")
            let follower = scriptCreate(type: 0, fraction: 2, at: GridPoint(x: 7, y: 3), reference: "escortFollower")
            scriptNext()
            // Source case 7 waits until the last follower reaches (6,1).
            // At 2px/tick the chain trails by one and two 24px cells.
            scriptMove([(leader, GridPoint(x: 6, y: -1), 0),
                        (crystal, GridPoint(x: 6, y: 0), 12),
                        (follower, GridPoint(x: 6, y: 1), 24)], focus: GridPoint(x: 7, y: 3), speed: 2)
        case 7:
            _ = scriptCreate(type: 0, fraction: 1, at: GridPoint(x: 7, y: 3), reference: "templeWarrior")
            scriptNext(); scriptDialogue(234, portrait: 2)
        case 8:
            let moves: [(String, GridPoint)] = [("escortLeader", GridPoint(x: 6, y: -2)),
                                              ("escortCrystal", GridPoint(x: 6, y: -1)),
                                              ("templeWarrior", GridPoint(x: 6, y: 2))]
            scriptNext()
            scriptMove(moves.compactMap { key, to in scriptUnit(key).map { ($0.id, to, 0) } }, focus: GridPoint(x: 7, y: 3), speed: 4)
            if let warrior = scriptUnit("templeWarrior") {
                scriptScene(.init(.hit, focus: warrior.position, durationTicks: 10, unitID: warrior.id))
            }
        case 9:
            scriptNext(); scriptDialogue(235, portrait: 2, after: [.init(.pause, durationTicks: 5)])
        case 10:
            scriptNext()
            if let warrior = scriptUnit("templeWarrior") {
                scriptScene(.init(.disappearance, focus: warrior.position, durationTicks: 15, unitID: warrior.id))
                scriptRemove(warrior.id)
            }
        case 11:
            if let leader = scriptUnit("escortLeader") { scriptRemove(leader.id) }
            if let crystal = scriptUnit("escortCrystal") { scriptRemove(crystal.id) }
            // Source intentionally keeps the last red soldier at (6,1).
            scriptNext(); scriptScene(.init(.camera, focus: king?.position, cameraStep: 12))
        case 12:
            scriptNext(); scriptDialogue(236, portrait: 5)
        case 13:
            scriptNext(); scriptDialogue(237, portrait: 1)
        case 14:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 3, y: 5)))
        case 15:
            scriptNext(); scriptDialogue(238, portrait: 5)
        case 16:
            scriptNext(); scriptScene(.init(.camera, focus: king?.position))
        case 17:
            scriptNext(); snapshot.pendingCampaignHelpID = 204
        case 18:
            scriptNext(); snapshot.pendingCampaignHelpID = 205
        case 19:
            scriptNext(); scriptStartPlay()
        case 20:
            guard runtime.blueOccupationObserved == true else { return }
            snapshot.missionRuntime?.blueOccupationObserved = nil
            scriptNext(); snapshot.pendingCampaignHelpID = 206
        case 21:
            scriptNext(); snapshot.pendingCampaignHelpID = 207
        case 22:
            guard runtime.commanderDeathObserved == true || [scriptUnit("king1"), scriptUnit("king2")].compactMap({ $0 }).contains(where: { $0.isCorpse }) else { return }
            snapshot.missionRuntime?.commanderDeathObserved = nil
            scriptNext(); snapshot.pendingCampaignHelpID = 208
        case 23:
            guard !snapshot.units.contains(where: { $0.fraction == 2 && $0.isAlive }),
                  !map.buildings.contains(where: { $0.fraction == 2 && $0.terrain == 9 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 20))
        case 24:
            scriptNext(); scriptScene(.init(.camera, focus: king?.position))
        case 25:
            scriptNext(); scriptDialogue(239, portrait: 1)
        case 26:
            scriptNext(); scriptDialogue(240, portrait: 5, after: [.init(.pause, durationTicks: 10)])
        case 27:
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .victory
            snapshot.storyMode = nil
        default: break
        }
    }

    private mutating func stepMissionTwo() {
        guard let runtime = snapshot.missionRuntime else { return }
        switch runtime.state {
        case 0:
            // initStoryMission:7692–7710 starts all four routes together. The
            // soldier and archer receive their second routes as they arrive.
            let routes: [(String, [GridPoint])] = [
                ("king1", [GridPoint(x: 8, y: 16), GridPoint(x: 8, y: 15), GridPoint(x: 8, y: 14)]),
                ("escortLeader", [GridPoint(x: 8, y: 17), GridPoint(x: 8, y: 16), GridPoint(x: 8, y: 15), GridPoint(x: 7, y: 15), GridPoint(x: 7, y: 14)]),
                ("escortArcher", [GridPoint(x: 8, y: 18), GridPoint(x: 8, y: 17), GridPoint(x: 8, y: 16), GridPoint(x: 8, y: 15), GridPoint(x: 7, y: 15)]),
                ("escortFollower", [GridPoint(x: 8, y: 19), GridPoint(x: 8, y: 18), GridPoint(x: 8, y: 17), GridPoint(x: 8, y: 16), GridPoint(x: 8, y: 15)]),
            ]
            snapshot.missionRuntime?.state = 2
            scriptMoveRoutes(routes.compactMap { key, route in
                scriptUnit(key).map { ($0.id, route, 0) }
            }, focus: nil, cameraOrigin: GridPoint(x: 8, y: 16), speed: 4, durationTicks: 38)
        case 2:
            scriptNext(); scriptDialogue(241, portrait: 5, after: [.init(.pause, durationTicks: 5)])
        case 3:
            scriptNext(); scriptDialogue(242, portrait: 0, after: [.init(.pause, durationTicks: 5)])
        case 4:
            scriptNext(); scriptDialogue(243, portrait: 5, after: [.init(.pause, durationTicks: 5)])
        case 5:
            scriptNext(); snapshot.pendingCampaignHelpID = 210
        case 6:
            scriptNext(); scriptStartPlay()
        case 7, 8:
            if runtime.state == 7, snapshot.activeFraction == 1,
               snapshot.units.contains(where: { $0.isCorpse }) {
                scriptNext()
                snapshot.pendingCampaignHelpID = 211
                return
            }
            let reachedAmbush = snapshot.units.contains {
                $0.fraction == 1 && $0.isAlive && $0.hasActed && ($0.x <= 4 || $0.y <= 10)
            }
            guard reachedAmbush else { return }
            snapshot.missionRuntime?.active = false
            snapshot.missionRuntime?.state = 9
            snapshot.phase = .story
            scriptScene(.init(.pause, durationTicks: 10))
        case 9:
            let first = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: -1, y: 8), reference: "ambushLeft0")
            let second = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: -2, y: 7), reference: "ambushLeft1")
            scriptNext()
            scriptMove([(first, GridPoint(x: 0, y: 8), 0), (second, GridPoint(x: 1, y: 7), 0)],
                       focus: GridPoint(x: 0, y: 8), speed: 2, durationTicks: 20)
        case 10:
            let wolf = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 12, y: 6), reference: "ambushRight")
            scriptNext()
            scriptMove([(wolf, GridPoint(x: 8, y: 6), 0)], focus: GridPoint(x: 8, y: 6), speed: 2, durationTicks: 20)
        case 11:
            let left = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 1, y: -2), reference: "ambushNorth0")
            let right = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 3, y: -2), reference: "ambushNorth1")
            let wisp = scriptCreate(type: 4, fraction: 2, at: GridPoint(x: 2, y: -1), reference: "ambushWisp")
            scriptNext()
            scriptMove([(left, GridPoint(x: 1, y: 2), 0),
                        (right, GridPoint(x: 3, y: 2), 0),
                        (wisp, GridPoint(x: 2, y: 1), 0)], focus: GridPoint(x: 2, y: 1), speed: 2, durationTicks: 20)
        case 12:
            scriptNext(); scriptDialogue(244, portrait: 5, after: [.init(.pause, durationTicks: 10)])
        case 13:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 4, y: 8), durationTicks: 15))
        case 14:
            let elementals = [GridPoint(x: 3, y: 8), GridPoint(x: 4, y: 7), GridPoint(x: 5, y: 8)].enumerated().map {
                scriptCreate(type: 2, fraction: 2, at: $0.element, reference: "neutralElemental\($0.offset)")
            }
            scriptNext(); scriptScene(.init(.appearance, focus: GridPoint(x: 4, y: 8), durationTicks: 10, affectedUnitIDs: elementals))
        case 15:
            scriptNext(); scriptDialogue(245, portrait: 5, after: [.init(.pause, durationTicks: 10)])
        case 16:
            scriptNext(); scriptDialogue(246, portrait: nil, after: [.init(.pause, durationTicks: 5)])
        case 17:
            scriptNext(); scriptDialogue(247, portrait: 0, after: [.init(.pause, durationTicks: 10)])
        case 18:
            scriptNext(); scriptScene(.init(.message, durationTicks: 23, textID: 248))
        case 19:
            let points = [GridPoint(x: 3, y: 8), GridPoint(x: 4, y: 7), GridPoint(x: 5, y: 8)]
            for point in points {
                if let enemy = snapshot.units.first(where: { $0.type == 2 && $0.fraction == 2 && $0.position == point && $0.isAlive }) {
                    scriptRemove(enemy.id)
                }
            }
            let allies = points.enumerated().map {
                scriptCreate(type: 2, fraction: 1, at: $0.element, reference: "alliedElemental\($0.offset)")
            }
            scriptNext(); scriptScene(.init(.appearance, focus: GridPoint(x: 4, y: 8), durationTicks: 10, affectedUnitIDs: allies))
        case 20:
            snapshot.missionRuntime?.active = true
            snapshot.phase = .playerTurn
            scriptNext(); snapshot.pendingCampaignHelpID = 209
        case 21:
            guard !snapshot.units.contains(where: { $0.fraction == 2 && $0.isAlive }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 10))
        case 22:
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .victory
            snapshot.storyMode = nil
        default:
            break
        }
    }

    private mutating func stepMissionThree() {
        guard let runtime = snapshot.missionRuntime else { return }
        let king = scriptUnit("king1")
        switch runtime.state {
        case 0: // initStoryMission:7712-7732. The camera follows Galamar during the four arrivals.
            let routes: [(String, GridPoint)] = [
                ("king1", GridPoint(x: 3, y: 3)),
                ("reinforcementSoldier", GridPoint(x: 3, y: 4)),
                ("reinforcementElemental", GridPoint(x: 4, y: 4)),
                ("reinforcementSorceress", GridPoint(x: 2, y: 4)),
            ]
            snapshot.missionRuntime?.state = 2
            scriptMove(routes.compactMap { key, destination in
                scriptUnit(key).map { ($0.id, destination, 0) }
            }, focus: GridPoint(x: 3, y: 3), cameraOrigin: GridPoint(x: 0, y: 5),
                       speed: 4, durationTicks: 50)
        case 2:
            snapshot.missionRuntime?.screenObscured = true
            scriptNext(); scriptScene(.init(.fadeToBlack, durationTicks: 20))
        case 3:
            scriptNext(); scriptDialogue(249, portrait: 2, after: [.init(.pause, durationTicks: 10)])
        case 4:
            scriptNext(); scriptDialogue(250, portrait: 0, after: [.init(.pause, durationTicks: 10)])
        case 5:
            scriptSetPosition("king1", to: GridPoint(x: 7, y: 1))
            scriptSetPosition("reinforcementSoldier", to: GridPoint(x: 5, y: 4))
            scriptSetPosition("reinforcementElemental", to: GridPoint(x: 7, y: 5))
            scriptSetPosition("reinforcementSorceress", to: GridPoint(x: 3, y: 3))
            scriptNext(); scriptDialogue(251, portrait: 5, after: [.init(.pause, durationTicks: 5)])
        case 6:
            snapshot.missionRuntime?.screenObscured = false
            scriptNext(); scriptScene(.init(.fadeFromBlack, durationTicks: 5))
        case 7:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 13, y: 10), cameraStep: 2))
        case 8:
            snapshot.missionRuntime?.state = 10
            if let catapult = scriptUnit("catapult") {
                scriptMove([(catapult.id, GridPoint(x: 6, y: 10), 0)],
                           focus: GridPoint(x: 6, y: 10), cameraStep: 4,
                           speed: 2, durationTicks: 58)
            }
        case 10:
            let village = GridPoint(x: 4, y: 9)
            snapshot.missionRuntime?.state = 12
            scriptScene(.init(.buildingDestruction, focus: village, cameraStep: 4,
                              durationTicks: 35,
                              tileChange: .init(point: village, tileID: 27, tick: 21)))
            let villageIndex = village.y * map.width + village.x
            if snapshot.tileOverrides == nil { snapshot.tileOverrides = [:] }
            snapshot.tileOverrides?[villageIndex] = 27
        case 12:
            scriptNext(); scriptDialogue(252, portrait: 5, after: [
                .init(.camera, focus: king?.position, cameraStep: 12),
            ])
        case 13:
            scriptNext(); scriptDialogue(253, portrait: 0, after: [.init(.pause, durationTicks: 5)])
        case 14:
            scriptNext(); snapshot.pendingCampaignHelpID = 213
        case 15:
            scriptNext(); scriptStartPlay()
        case 16:
            guard !snapshot.units.contains(where: { $0.fraction == 2 && $0.isAlive }),
                  !map.buildings.contains(where: { $0.fraction == 2 && $0.terrain == 9 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 15))
        case 17:
            scriptNext(); scriptDialogue(254, portrait: 5, after: [.init(.pause, durationTicks: 5)])
        case 18:
            scriptNext(); scriptDialogue(255, portrait: 0, after: [.init(.pause, durationTicks: 10)])
        case 19:
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .victory
            snapshot.storyMode = nil
        default:
            break
        }
    }

    private mutating func stepMissionFour() {
        guard let runtime = snapshot.missionRuntime else { return }
        let king = scriptUnit("king1")
        let crystal = scriptUnit("crystal")

        // MainDisplayable:8986-8990 fails this mission as soon as the escorted
        // Crystal enters the dead state, independently of Galamar's defeat rule.
        if crystal?.isAlive != true {
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .defeat
            snapshot.storyMode = nil
            snapshot.storyMessages = []
            snapshot.storyIndex = -1
            return
        }

        let blueReached: (CampaignUnitState) -> Bool = {
            $0.fraction == 1 && $0.isAlive && $0.hasActed
        }
        switch runtime.state {
        case 0: // initStoryMission:7737-7759; all six units enter together at 4px/tick.
            let moves: [(Int, GridPoint, Int)] = [
                (snapshot.units.first { $0.fraction == 1 && $0.type == 0 }?.id ?? -1, GridPoint(x: 11, y: 2), 0),
                (snapshot.units.first { $0.fraction == 1 && $0.type == 5 }?.id ?? -1, GridPoint(x: 10, y: 1), 0),
                (king?.id ?? -1, GridPoint(x: 11, y: 1), 0),
                (snapshot.units.first { $0.fraction == 1 && $0.type == 3 }?.id ?? -1, GridPoint(x: 12, y: 1), 0),
                (crystal?.id ?? -1, GridPoint(x: 11, y: 0), 0),
                (snapshot.units.first { $0.fraction == 1 && $0.type == 1 }?.id ?? -1, GridPoint(x: 12, y: 0), 0),
            ].filter { $0.0 >= 0 }
            scriptNext()
            scriptMove(moves, focus: nil, cameraOrigin: GridPoint(x: 11, y: 1),
                       speed: 4, durationTicks: 50)
        case 1:
            scriptNext(); scriptDialogue(256, portrait: 5, after: [.init(.pause, durationTicks: 5)])
        case 2:
            scriptNext(); scriptDialogue(257, portrait: 0, after: [.init(.pause, durationTicks: 5)])
        case 3:
            scriptNext(); scriptStartPlay()
        case 4:
            guard snapshot.units.contains(where: { blueReached($0) && $0.x <= 8 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.missionRuntime?.state = 5
            snapshot.phase = .story
            scriptScene(.init(.pause, durationTicks: 5))
        case 5:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 4, y: 4)))
        case 6:
            let golem0 = scriptCreate(type: 10, fraction: 2, at: GridPoint(x: 4, y: 4), reference: "ambushOneGolem0")
            let archer = scriptCreate(type: 1, fraction: 2, at: GridPoint(x: 4, y: 4), reference: "ambushOneArcher")
            let golem1 = scriptCreate(type: 10, fraction: 2, at: GridPoint(x: 4, y: 4), reference: "ambushOneGolem1")
            snapshot.missionRuntime?.state = 9
            scriptMove([(golem0, GridPoint(x: 4, y: 1), 0),
                        (archer, GridPoint(x: 5, y: 2), 10),
                        (golem1, GridPoint(x: 4, y: 3), 20)],
                       focus: GridPoint(x: 4, y: 4), speed: 4, durationTicks: 20)
        case 9:
            snapshot.missionRuntime?.state = 10
            scriptDialogue(258, portrait: 5, after: [
                .init(.camera, focus: king?.position),
            ])
        case 10:
            snapshot.missionRuntime?.active = true
            snapshot.phase = .playerTurn
            scriptNext()
        case 11:
            guard snapshot.units.contains(where: { blueReached($0) && $0.y >= 7 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.missionRuntime?.state = 12
            snapshot.phase = .story
            scriptScene(.init(.camera, focus: GridPoint(x: 6, y: 10)))
        case 12:
            let archer = scriptCreate(type: 1, fraction: 2, at: GridPoint(x: 6, y: 10), reference: "ambushTwoArcher")
            let wolf0 = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 6, y: 10), reference: "ambushTwoWolf0")
            let wolf1 = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 6, y: 10), reference: "ambushTwoWolf1")
            snapshot.missionRuntime?.state = 15
            snapshot.missionRuntime?.active = true
            snapshot.phase = .playerTurn
            scriptMove([(archer, GridPoint(x: 5, y: 10), 0),
                        (wolf0, GridPoint(x: 7, y: 8), 10),
                        (wolf1, GridPoint(x: 7, y: 9), 25)],
                       focus: GridPoint(x: 6, y: 10), speed: 4, durationTicks: 25)
        case 15:
            guard snapshot.units.contains(where: { blueReached($0) && $0.x >= 8 && $0.y >= 6 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.missionRuntime?.state = 16
            snapshot.phase = .story
            scriptScene(.init(.camera, focus: GridPoint(x: 12, y: 5)))
        case 16:
            let wolf0 = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 12, y: 5), reference: "ambushThreeWolf0")
            let lizard = scriptCreate(type: 6, fraction: 2, at: GridPoint(x: 12, y: 5), reference: "ambushThreeLizard")
            let wolf1 = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 12, y: 5), reference: "ambushThreeWolf1")
            snapshot.missionRuntime?.state = 19
            snapshot.missionRuntime?.active = true
            snapshot.phase = .playerTurn
            scriptMove([(wolf0, GridPoint(x: 12, y: 7), 0),
                        (lizard, GridPoint(x: 12, y: 6), 15),
                        (wolf1, GridPoint(x: 12, y: 5), 25)],
                       focus: GridPoint(x: 12, y: 5), speed: 4, durationTicks: 25)
        case 19:
            guard snapshot.units.contains(where: { blueReached($0) && $0.x >= 15 && $0.y >= 8 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.missionRuntime?.state = 20
            snapshot.phase = .story
            scriptScene(.init(.camera, focus: GridPoint(x: 18, y: 8)))
        case 20:
            let wolf0 = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 18, y: 8), reference: "ambushFourWolf0")
            let lizard = scriptCreate(type: 6, fraction: 2, at: GridPoint(x: 18, y: 8), reference: "ambushFourLizard")
            let wolf1 = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 18, y: 8), reference: "ambushFourWolf1")
            let archer = scriptCreate(type: 1, fraction: 2, at: GridPoint(x: 18, y: 8), reference: "ambushFourArcher")
            snapshot.missionRuntime?.state = 24
            scriptMove([(wolf0, GridPoint(x: 16, y: 10), 0),
                        (lizard, GridPoint(x: 17, y: 10), 10),
                        (wolf1, GridPoint(x: 18, y: 10), 20),
                        (archer, GridPoint(x: 18, y: 9), 30)],
                       focus: GridPoint(x: 18, y: 8), speed: 4, durationTicks: 40)
        case 24:
            snapshot.missionRuntime?.active = true
            snapshot.phase = .playerTurn
            scriptNext(); scriptDialogue(259, portrait: 0)
        case 25:
            let crystalEscaped = crystal.map { $0.isAlive && $0.hasActed && $0.x >= 15 && $0.y >= 11 } == true
            let oppositionDestroyed = !snapshot.units.contains { $0.fraction == 2 && $0.isAlive }
            guard crystalEscaped || oppositionDestroyed else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 10))
        case 26:
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .victory
            snapshot.storyMode = nil
        default:
            break
        }
    }

    private mutating func stepMissionFive() {
        guard let runtime = snapshot.missionRuntime else { return }
        let king = scriptUnit("king1")

        switch runtime.state {
        case 0:
            // initStoryMission starts the camera at (5,0) and pans toward the
            // commander behind the map-name screen. The playable view opens at
            // the destination and preserves the source's final 10-tick wait.
            scriptNext()
            scriptScene(.init(.pause, focus: king?.position, cameraStep: 4, durationTicks: 10))
        case 1:
            scriptNext(); scriptDialogue(260, portrait: 1)
        case 2:
            scriptNext(); scriptStartPlay()
        case 3:
            guard !snapshot.units.contains(where: { $0.fraction == 2 && $0.isAlive }),
                  !map.buildings.contains(where: { $0.fraction == 2 && $0.terrain == 9 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 15))
        case 4:
            scriptNext(); scriptScene(.init(.camera, focus: king?.position))
        case 5:
            scriptNext(); scriptDialogue(261, portrait: 0, after: [.init(.pause, durationTicks: 10)])
        case 6:
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .victory
            snapshot.storyMode = nil
        default:
            break
        }
    }

    private mutating func stepMissionSix() {
        guard let runtime = snapshot.missionRuntime else { return }
        let king = scriptUnit("king1")
        let crystal = scriptUnit("crystal")

        // MainDisplayable:9237-9249 guards the Crystal only before the authored
        // abduction. Once state 11 starts, removing it is part of the story.
        if runtime.state <= 10, crystal?.isAlive != true {
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .defeat
            snapshot.storyMode = nil
            snapshot.storyMessages = []
            snapshot.storyIndex = -1
            return
        }

        switch runtime.state {
        case 0:
            // The four followers copy each cell Galamar leaves. At 4px/tick the
            // chain is spaced by six ticks and settles one cell apart.
            let routes: [(String, [GridPoint], Int)] = [
                ("king1", [GridPoint(x: 13, y: 0), GridPoint(x: 14, y: 0), GridPoint(x: 14, y: 1),
                           GridPoint(x: 14, y: 2), GridPoint(x: 14, y: 3)], 0),
                ("escortSoldier", [GridPoint(x: 13, y: -1), GridPoint(x: 13, y: 0), GridPoint(x: 14, y: 0),
                                   GridPoint(x: 14, y: 1), GridPoint(x: 14, y: 2)], 0),
                ("escortArcher", [GridPoint(x: 13, y: -1), GridPoint(x: 13, y: 0),
                                  GridPoint(x: 14, y: 0), GridPoint(x: 14, y: 1)], 6),
                ("escortSorceress", [GridPoint(x: 13, y: -1), GridPoint(x: 13, y: 0),
                                     GridPoint(x: 14, y: 0)], 12),
                ("crystal", [GridPoint(x: 13, y: -1), GridPoint(x: 13, y: 0)], 18),
            ]
            scriptNext()
            scriptMoveRoutes(routes.compactMap { key, route, delay in
                scriptUnit(key).map { ($0.id, route, delay) }
            }, focus: GridPoint(x: 14, y: 3), cameraOrigin: GridPoint(x: 13, y: 0),
            speed: 4, durationTicks: 34)
        case 1:
            scriptNext(); scriptDialogue(262, portrait: 5, after: [.init(.pause, durationTicks: 5)])
        case 2:
            scriptNext(); scriptDialogue(263, portrait: 0, after: [.init(.pause, durationTicks: 5)])
        case 3:
            scriptNext(); scriptStartPlay()
        case 4:
            guard snapshot.currentTurn >= 2 else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 11, y: 7), durationTicks: 15))
        case 5:
            let wolf = scriptCreate(type: 5, fraction: 2, at: GridPoint(x: 11, y: 8), reference: "ambushWolf")
            let soldier = scriptCreate(type: 0, fraction: 2, at: GridPoint(x: 11, y: 8), reference: "ambushSoldier")
            let sorceress = scriptCreate(type: 3, fraction: 2, at: GridPoint(x: 11, y: 8), reference: "ambushSorceress")
            let archer = scriptCreate(type: 1, fraction: 2, at: GridPoint(x: 11, y: 8), reference: "ambushArcher")
            snapshot.missionRuntime?.state = 9
            scriptMove([(wolf, GridPoint(x: 14, y: 7), 0),
                        (soldier, GridPoint(x: 13, y: 7), 10),
                        (sorceress, GridPoint(x: 12, y: 7), 20),
                        (archer, GridPoint(x: 13, y: 8), 30)],
                       focus: GridPoint(x: 11, y: 7), speed: 4, durationTicks: 50)
        case 9:
            snapshot.missionRuntime?.active = true
            snapshot.missionRuntime?.state = 10
            scriptDialogue(264, portrait: 5, after: [.init(.camera, focus: king?.position)])
        case 10:
            let crossedBoundary = snapshot.units.contains {
                $0.fraction == 1 && $0.isAlive && $0.hasActed && ($0.x <= 9 || $0.y >= 10)
            }
            let ambushDestroyed = !snapshot.units.contains { $0.fraction == 2 && $0.isAlive }
            guard crossedBoundary || ambushDestroyed else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 10))
        case 11:
            guard let crystal else { return }
            _ = scriptCreate(type: 8, fraction: 2,
                             at: GridPoint(x: map.width, y: crystal.y), reference: "abductorDragon")
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: map.width - 1, y: crystal.y)))
        case 12:
            guard let dragon = scriptUnit("abductorDragon"), let crystal else { return }
            scriptNext()
            scriptMove([(dragon.id, crystal.position, 0)], focus: crystal.position,
                       speed: 4, durationTicks: 5)
        case 13:
            scriptNext(); scriptDialogue(265, portrait: 5)
        case 14:
            scriptNext(); scriptDialogue(266, portrait: 0)
        case 15:
            guard let dragon = scriptUnit("abductorDragon"), let crystal else { return }
            snapshot.missionRuntime?.state = 16
            let exit = GridPoint(x: -1, y: dragon.y)
            scriptMove([(dragon.id, exit, 0), (crystal.id, exit, 0)],
                       focus: GridPoint(x: 0, y: dragon.y), speed: 4, durationTicks: 10)
        case 16:
            if let crystal { scriptRemove(crystal.id) }
            if let dragon = scriptUnit("abductorDragon") { scriptRemove(dragon.id) }
            scriptNext()
        case 17:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 1, y: 9)))
        case 18:
            let commander = scriptCreate(type: 9, fraction: 2, at: GridPoint(x: -2, y: 8), reference: "enemyCommander")
            let soldier0 = scriptCreate(type: 0, fraction: 2, at: GridPoint(x: -1, y: 8), reference: "westernSoldier0")
            let soldier1 = scriptCreate(type: 0, fraction: 2, at: GridPoint(x: -1, y: 10), reference: "westernSoldier1")
            let dragon0 = scriptCreate(type: 8, fraction: 2, at: GridPoint(x: -3, y: 7), reference: "westernDragon0")
            let dragon1 = scriptCreate(type: 8, fraction: 2, at: GridPoint(x: -3, y: 11), reference: "westernDragon1")
            let wisp = scriptCreate(type: 4, fraction: 2, at: GridPoint(x: -2, y: 9), reference: "westernWisp")
            let golem0 = scriptCreate(type: 6, fraction: 2, at: GridPoint(x: -4, y: 9), reference: "westernGolem0")
            let golem1 = scriptCreate(type: 6, fraction: 2, at: GridPoint(x: -6, y: 9), reference: "westernGolem1")
            scriptNext()
            scriptMove([(commander, GridPoint(x: 0, y: 8), 0),
                        (soldier0, GridPoint(x: 3, y: 8), 0),
                        (soldier1, GridPoint(x: 1, y: 10), 0),
                        (dragon0, GridPoint(x: 4, y: 8), 0),
                        (dragon1, GridPoint(x: 2, y: 10), 0),
                        (wisp, GridPoint(x: 2, y: 9), 0),
                        (golem0, GridPoint(x: 4, y: 9), 0),
                        (golem1, GridPoint(x: 5, y: 10), 0)],
                       focus: GridPoint(x: 1, y: 9), speed: 4, durationTicks: 50)
        case 19:
            scriptNext(); scriptDialogue(267, portrait: 3)
        case 20:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 13, y: 14)))
        case 21:
            let soldier = scriptCreate(type: 0, fraction: 2, at: GridPoint(x: 13, y: 14), reference: "southernSoldier")
            let golem = scriptCreate(type: 6, fraction: 2, at: GridPoint(x: 13, y: 14), reference: "southernGolem")
            let elemental = scriptCreate(type: 2, fraction: 2, at: GridPoint(x: 13, y: 14), reference: "southernElemental")
            let sorceress = scriptCreate(type: 3, fraction: 2, at: GridPoint(x: 13, y: 14), reference: "southernSorceress")
            snapshot.missionRuntime?.state = 25
            scriptMove([(soldier, GridPoint(x: 12, y: 14), 0),
                        (golem, GridPoint(x: 14, y: 14), 5),
                        (elemental, GridPoint(x: 13, y: 12), 10),
                        (sorceress, GridPoint(x: 13, y: 15), 15)],
                       focus: GridPoint(x: 13, y: 14), speed: 4, durationTicks: 30)
        case 25:
            scriptNext(); scriptDialogue(268, portrait: 5, after: [.init(.pause, durationTicks: 10)])
        case 26:
            scriptNext(); scriptDialogue(269, portrait: 0, after: [.init(.pause, durationTicks: 10)])
        case 27:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 13, y: 17)))
        case 28:
            let valadorn = scriptCreate(type: 9, fraction: 1, at: GridPoint(x: 13, y: 18), reference: "valadorn")
            let golem = scriptCreate(type: 6, fraction: 1, at: GridPoint(x: 12, y: 18), reference: "reinforcementGolem")
            let dragon = scriptCreate(type: 8, fraction: 1, at: GridPoint(x: 14, y: 19), reference: "reinforcementDragon")
            let wisp = scriptCreate(type: 4, fraction: 1, at: GridPoint(x: 13, y: 19), reference: "reinforcementWisp")
            let archer = scriptCreate(type: 1, fraction: 1, at: GridPoint(x: 12, y: 19), reference: "reinforcementArcher")
            scriptSetCommanderVariant(valadorn, to: 2)
            scriptNext()
            scriptMove([(valadorn, GridPoint(x: 13, y: 16), 0),
                        (golem, GridPoint(x: 12, y: 16), 0),
                        (dragon, GridPoint(x: 14, y: 16), 0),
                        (wisp, GridPoint(x: 13, y: 17), 0),
                        (archer, GridPoint(x: 12, y: 17), 0)],
                       focus: GridPoint(x: 13, y: 17), speed: 4, durationTicks: 20)
        case 29:
            scriptNext(); scriptDialogue(270, portrait: 1, after: [.init(.pause, durationTicks: 10)])
        case 30:
            scriptNext(); scriptScene(.init(.camera, focus: king?.position))
        case 31:
            scriptNext(); snapshot.pendingCampaignHelpID = 214
        case 32:
            snapshot.missionRuntime?.active = true
            snapshot.missionRuntime?.objectiveTextID = 138
            scriptNext(); scriptScene(.init(.objective, textID: 138))
        case 33:
            guard !snapshot.units.contains(where: { $0.fraction == 2 && $0.isAlive }),
                  !map.buildings.contains(where: { $0.fraction == 2 && $0.terrain == 9 }) else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 10))
        case 34:
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .victory
            snapshot.storyMode = nil
        default:
            break
        }
    }

    private mutating func stepMissionSeven() {
        guard let runtime = snapshot.missionRuntime else { return }
        let king = scriptUnit("king1")
        let saeth = scriptUnit("saeth")

        switch runtime.state {
        case 0:
            // initStoryMission shows the authored map name after intro4, then
            // reveals Galamar at the southern edge of the 14x18 battlefield.
            scriptNext(); scriptScene(.init(.pause, focus: king?.position, durationTicks: 10))
        case 1:
            scriptNext(); scriptDialogue(271, portrait: 0)
        case 2:
            scriptNext(); scriptScene(.init(.camera, focus: saeth?.position))
        case 3:
            scriptNext(); scriptDialogue(272, portrait: 4, after: [.init(.pause, durationTicks: 10)])
        case 4:
            scriptNext(); scriptDialogue(273, portrait: 1, after: [.init(.pause, durationTicks: 10)])
        case 5:
            scriptNext(); scriptDialogue(274, portrait: 4)
        case 6:
            guard let crystal = scriptUnit("crystalWest") else { return }
            scriptNext(); scriptMove([(crystal.id, GridPoint(x: 7, y: 2), 0)], focus: GridPoint(x: 7, y: 2), speed: 4)
        case 7:
            guard let first = scriptUnit("crystalWest"), let crystal = scriptUnit("crystalCenter") else { return }
            scriptRemove(first.id)
            scriptNext(); scriptMove([(crystal.id, GridPoint(x: 7, y: 2), 0)], focus: GridPoint(x: 7, y: 2), speed: 4)
        case 8:
            guard let second = scriptUnit("crystalCenter"), let crystal = scriptUnit("crystalEast") else { return }
            scriptRemove(second.id)
            scriptNext(); scriptMove([(crystal.id, GridPoint(x: 7, y: 2), 0)], focus: GridPoint(x: 7, y: 2), speed: 4)
        case 9:
            guard let third = scriptUnit("crystalEast"), let saeth else { return }
            scriptRemove(third.id)
            scriptNext(); scriptMove([(saeth.id, GridPoint(x: 7, y: 2), 0)], focus: GridPoint(x: 7, y: 2), speed: 4, durationTicks: 20)
        case 10:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 9, y: 15)))
        case 11:
            scriptNext(); scriptScene(.init(.message, durationTicks: 30, textID: 279))
        case 12:
            guard let target = scriptUnit("heavenFuryDemo") else { return }
            scriptNext(); scriptScene(.init(.heavenFury, focus: target.position, durationTicks: 20,
                                            unitID: target.id, textID: 280, affectedUnitIDs: [target.id], damage: 0))
        case 13:
            if let target = scriptUnit("heavenFuryDemo") { scriptRemove(target.id) }
            scriptNext(); scriptDialogue(275, portrait: 0, after: [.init(.pause, durationTicks: 20)])
        case 14:
            scriptNext(); scriptDialogue(276, portrait: 4)
        case 15:
            scriptNext(); scriptDialogue(277, portrait: 1, after: [.init(.pause, durationTicks: 10)])
        case 16:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 3, y: 9), cameraStep: 4))
        case 17:
            scriptNext(); scriptScene(.init(.camera, focus: GridPoint(x: 13, y: 4), cameraStep: 4))
        case 18:
            scriptNext(); scriptDialogue(278, portrait: 5, after: [.init(.camera, focus: king?.position)])
        case 19:
            scriptNext(); scriptStartPlay()
        case 20:
            guard saeth?.isAlive != true else { return }
            snapshot.missionRuntime?.active = false
            snapshot.phase = .story
            scriptNext(); scriptScene(.init(.pause, durationTicks: 20))
        case 21:
            snapshot.missionRuntime?.screenObscured = true
            scriptNext(); scriptScene(.init(.fadeToBlack, durationTicks: 16))
        case 22:
            for index in snapshot.units.indices { snapshot.units[index].removedFromMap = true }
            let endingSaeth = scriptCreate(type: 9, fraction: 2, at: GridPoint(x: 7, y: 2), reference: "endingSaeth")
            let endingGalamar = scriptCreate(type: 9, fraction: 1, at: GridPoint(x: 6, y: 3), reference: "endingGalamar")
            let endingValadorn = scriptCreate(type: 9, fraction: 1, at: GridPoint(x: 8, y: 3), reference: "endingValadorn")
            _ = scriptCreate(type: 0, fraction: 1, at: GridPoint(x: 6, y: 1), reference: "endingSoldierWest")
            _ = scriptCreate(type: 0, fraction: 1, at: GridPoint(x: 8, y: 1), reference: "endingSoldierEast")
            scriptSetCommanderVariant(endingSaeth, to: 3)
            scriptSetCommanderVariant(endingGalamar, to: 0)
            scriptSetCommanderVariant(endingValadorn, to: 2)
            scriptNext(); scriptScene(.init(.pause, focus: GridPoint(x: 7, y: 2), durationTicks: 10))
        case 23:
            snapshot.missionRuntime?.screenObscured = false
            scriptNext(); scriptScene(.init(.fadeFromBlack, focus: GridPoint(x: 7, y: 2), durationTicks: 16))
        case 24:
            scriptNext(); scriptDialogue(281, portrait: 4, after: [.init(.pause, durationTicks: 15)])
        case 25:
            scriptNext(); scriptDialogue(282, portrait: 0, after: [.init(.pause, durationTicks: 8)])
        case 26:
            scriptNext(); scriptDialogue(283, portrait: 4, after: [.init(.pause, durationTicks: 15)])
        case 27:
            guard let endingSaeth = scriptUnit("endingSaeth") else { return }
            scriptNext(); scriptScene(.init(.disappearance, focus: endingSaeth.position,
                                            durationTicks: 15, unitID: endingSaeth.id))
            scriptRemove(endingSaeth.id)
        case 28:
            scriptNext(); scriptScene(.init(.quake, focus: GridPoint(x: 7, y: 2), durationTicks: 10))
        case 29:
            scriptNext(); scriptDialogue(284, portrait: 5, after: [.init(.pause, durationTicks: 5)])
        case 30:
            scriptNext(); scriptDialogue(285, portrait: 2, after: [.init(.pause, durationTicks: 5)])
        case 31:
            scriptNext(); scriptDialogue(286, portrait: 1, after: [.init(.pause, durationTicks: 5)])
        case 32:
            scriptNext(); scriptDialogue(287, portrait: 0, after: [.init(.fadeToBlack, durationTicks: 16)])
        case 33:
            snapshot.missionRuntime?.screenObscured = true
            scriptNext(); scriptScene(.init(.pause, durationTicks: 10))
        case 34:
            // Source states 34-36 display intro5, text 220 and credits 288.
            // UIKit owns that full-screen sequence after the battle controller exits.
            snapshot.missionRuntime?.state = -1
            snapshot.missionRuntime?.active = false
            snapshot.phase = .victory
            snapshot.storyMode = nil
        default:
            break
        }
    }

    private mutating func scriptNext() { snapshot.missionRuntime?.state += 1 }

    private func scriptUnit(_ key: String) -> CampaignUnitState? {
        snapshot.missionRuntime?.references[key].flatMap { unit(id: $0) }
    }

    private mutating func scriptDialogue(_ id: Int, portrait: Int?, after: [CampaignScenePresentation] = []) {
        snapshot.storyMessages = [id]
        snapshot.storyIndex = 0
        snapshot.storyMode = .opening
        snapshot.phase = .story
        snapshot.missionRuntime?.portraitID = portrait
        snapshot.missionRuntime?.afterDialogue = after.isEmpty ? nil : after
    }

    private mutating func scriptScene(_ scene: CampaignScenePresentation) {
        if snapshot.storyPresentationsEnabled == true { enqueueScene(scene) }
    }

    private mutating func scriptStartPlay() {
        snapshot.missionRuntime?.active = true
        snapshot.phase = .playerTurn
        snapshot.storyMode = nil
        snapshot.storyMessages = []
        snapshot.storyIndex = -1
        scriptScene(.init(.objective, textID: objectiveTextID))
    }

    @discardableResult
    private mutating func scriptCreate(type: Int, fraction: Int, at point: GridPoint, reference: String) -> Int {
        let id = snapshot.nextUnitID
        snapshot.nextUnitID += 1
        snapshot.units.append(.init(id: id, type: type, fraction: fraction, x: point.x, y: point.y))
        snapshot.missionRuntime?.references[reference] = id
        return id
    }

    private mutating func scriptRemove(_ id: Int) {
        if let index = snapshot.units.firstIndex(where: { $0.id == id }) { snapshot.units[index].removedFromMap = true }
    }

    private mutating func scriptSetPosition(_ reference: String, to point: GridPoint) {
        guard let id = snapshot.missionRuntime?.references[reference],
              let index = snapshot.units.firstIndex(where: { $0.id == id }) else { return }
        snapshot.units[index].x = point.x
        snapshot.units[index].y = point.y
    }

    private mutating func scriptSetCommanderVariant(_ id: Int, to variant: Int) {
        guard let index = snapshot.units.firstIndex(where: { $0.id == id }) else { return }
        snapshot.units[index].commanderVariant = variant
    }

    private mutating func scriptMove(_ moves: [(Int, GridPoint, Int)], focus: GridPoint?, cameraOrigin: GridPoint? = nil,
                                     cameraStep: Int = 12, speed: Int = 4, durationTicks: Int = 0) {
        var movements: [CampaignScenePresentation.Movement] = []
        for (id, destination, delay) in moves {
            guard let unit = unit(id: id) else { continue }
            var route = [unit.position]
            var point = unit.position
            // Unit.plotRouteEx(false): horizontal first, then vertical, including off-map cells.
            while point.x != destination.x { point = GridPoint(x: point.x + (destination.x > point.x ? 1 : -1), y: point.y); route.append(point) }
            while point.y != destination.y { point = GridPoint(x: point.x, y: point.y + (destination.y > point.y ? 1 : -1)); route.append(point) }
            movements.append(.init(unitID: id, route: route, delayTicks: delay == 0 ? nil : delay))
        }
        scriptScene(.init(.arrival, focus: focus, cameraOrigin: cameraOrigin, cameraStep: cameraStep,
                          movements: movements, unitSpeed: speed, durationTicks: durationTicks))
        for (id, destination, _) in moves {
            if let index = snapshot.units.firstIndex(where: { $0.id == id }) {
                snapshot.units[index].x = destination.x
                snapshot.units[index].y = destination.y
            }
        }
    }

    private mutating func scriptMoveRoutes(_ moves: [(Int, [GridPoint], Int)], focus: GridPoint?,
                                           cameraOrigin: GridPoint? = nil, cameraStep: Int = 12,
                                           speed: Int = 4, durationTicks: Int = 0) {
        let movements = moves.map {
            CampaignScenePresentation.Movement(unitID: $0.0, route: $0.1, delayTicks: $0.2 == 0 ? nil : $0.2)
        }
        scriptScene(.init(.arrival, focus: focus, cameraOrigin: cameraOrigin, cameraStep: cameraStep,
                          movements: movements, unitSpeed: speed, durationTicks: durationTicks))
        for (id, route, _) in moves {
            guard let destination = route.last,
                  let index = snapshot.units.firstIndex(where: { $0.id == id }) else { continue }
            snapshot.units[index].x = destination.x
            snapshot.units[index].y = destination.y
        }
    }
}
