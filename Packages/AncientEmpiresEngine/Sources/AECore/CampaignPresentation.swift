import Foundation

/// A committed result awaiting presentation. Replaying this data never resolves combat again.
public struct CampaignPresentation: Codable, Equatable, Sendable {
    public enum Kind: String, Codable, Sendable { case movement, combat, scene }
    public let kind: Kind
    public let unitID: Int
    public let unitsBefore: [CampaignUnitState]
    public let route: [GridPoint]
    public var unitSpeed: Int?
    public var tileOverridesBefore: [Int: Int]?
    public let combat: CampaignCombatPresentation?
    public let scene: CampaignScenePresentation?

    public init(unitID: Int, unitsBefore: [CampaignUnitState], route: [GridPoint], unitSpeed: Int = 6) {
        kind = .movement
        self.unitID = unitID
        self.unitsBefore = unitsBefore
        self.route = route
        self.unitSpeed = unitSpeed
        combat = nil
        scene = nil
    }

    public init(unitsBefore: [CampaignUnitState], combat: CampaignCombatPresentation) {
        kind = .combat
        unitID = combat.attacker.id
        self.unitsBefore = unitsBefore
        route = []
        self.combat = combat
        scene = nil
    }

    public init(unitsBefore: [CampaignUnitState], scene: CampaignScenePresentation) {
        kind = .scene
        unitID = scene.unitID ?? -1
        self.unitsBefore = unitsBefore
        route = []
        combat = nil
        self.scene = scene
    }
}

/// MainDisplayable m0 script presentation. Coordinates stay in original map cells;
/// source-pixel speeds and delays are applied on the 66 ms game tick by UIKit.
public struct CampaignScenePresentation: Codable, Equatable, Sendable {
    public enum Kind: String, Codable, Sendable {
        case camera, arrival, hit, disappearance, appearance, objective, message, pause
        case newTurn, healing, building, planning, targeting, death, summoning, poison, blessing
        case fadeToBlack, fadeFromBlack, buildingDestruction, heavenFury, quake
        case levelUp, rankName
    }
    public struct Movement: Codable, Equatable, Sendable {
        public let unitID: Int
        public let route: [GridPoint]
        public let delayTicks: Int?
        public init(unitID: Int, route: [GridPoint], delayTicks: Int? = nil) {
            self.unitID = unitID; self.route = route; self.delayTicks = delayTicks
        }
    }
    public let kind: Kind
    public let focus: GridPoint?
    public let cameraOrigin: GridPoint?
    public let cameraStep: Int
    public let movements: [Movement]
    public let unitSpeed: Int
    public let durationTicks: Int
    public let unitID: Int?
    public let textID: Int?
    public let turn: CampaignTurnPresentation?
    public let healing: [CampaignHealingPresentation]?
    public let movementRange: [GridPoint]?
    public let routePreview: [GridPoint]?
    public let attackRange: [GridPoint]?
    public let affectedUnitIDs: [Int]?
    public let tileChange: CampaignTileChangePresentation?
    public let damage: Int?

    public init(_ kind: Kind, focus: GridPoint? = nil, cameraOrigin: GridPoint? = nil,
                cameraStep: Int = 12, movements: [Movement] = [], unitSpeed: Int = 6,
                durationTicks: Int = 0, unitID: Int? = nil, textID: Int? = nil,
                turn: CampaignTurnPresentation? = nil, healing: [CampaignHealingPresentation]? = nil, movementRange: [GridPoint]? = nil, routePreview: [GridPoint]? = nil,
                attackRange: [GridPoint]? = nil, affectedUnitIDs: [Int]? = nil,
                tileChange: CampaignTileChangePresentation? = nil, damage: Int? = nil) {
        self.kind = kind
        self.focus = focus
        self.cameraOrigin = cameraOrigin
        self.cameraStep = cameraStep
        self.movements = movements
        self.unitSpeed = unitSpeed
        self.durationTicks = durationTicks
        self.unitID = unitID
        self.textID = textID
        self.turn = turn
        self.healing = healing
        self.movementRange = movementRange
        self.routePreview = routePreview
        self.attackRange = attackRange
        self.affectedUnitIDs = affectedUnitIDs
        self.tileChange = tileChange
        self.damage = damage
    }
}

public struct CampaignTileChangePresentation: Codable, Equatable, Sendable {
    public let point: GridPoint
    public let tileID: Int
    public let tick: Int

    public init(point: GridPoint, tileID: Int, tick: Int) {
        self.point = point
        self.tileID = tileID
        self.tick = tick
    }
}

public struct CampaignCombatPresentation: Codable, Equatable, Sendable {
    public let attacker: CampaignUnitState
    public let defender: CampaignUnitState
    public let attackerTerrain: Int
    public let defenderTerrain: Int
    public let attackerHealthAfter: Int
    public let defenderHealthAfter: Int
    public let didCounterattack: Bool

    public var damage: Int { defender.hitPoints - defenderHealthAfter }
    public var counterDamage: Int { attacker.hitPoints - attackerHealthAfter }

    /// Unit.getCharacterCount uses integer HP per character, then rounds up.
    public static func characterCount(health: Int, maximum: Int) -> Int {
        guard maximum > 0, health > 0 else { return 0 }
        let healthPerCharacter = max(1, 100 / maximum)
        return min(maximum, (health + healthPerCharacter - 1) / healthPerCharacter)
    }
}

public struct CampaignTurnPresentation: Codable, Equatable, Sendable {
    public let fraction: Int
    public let income: Int
    public let balance: Int
    public init(fraction: Int, income: Int, balance: Int) {
        self.fraction = fraction; self.income = income; self.balance = balance
    }
}

public struct CampaignHealingPresentation: Codable, Equatable, Sendable {
    public let unitID: Int
    public let amount: Int
    public init(unitID: Int, amount: Int) { self.unitID = unitID; self.amount = amount }
}
