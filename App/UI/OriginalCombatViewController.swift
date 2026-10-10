import AECore
import AEContent
import UIKit

@MainActor
final class OriginalCombatViewController: UIViewController {
    var onComplete: (() -> Void)?
    private let canvas: OriginalCombatCanvas
    private var clock: CADisplayLink?
    private var previousTimestamp: CFTimeInterval = 0
    private var accumulator: CFTimeInterval = 0
    private var completed = false

    init(combat: CampaignCombatPresentation) throws {
        canvas = try OriginalCombatCanvas(combat: combat)
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }

    override func loadView() {
        view = canvas
        let skip = UIButton(type: .custom)
        skip.accessibilityIdentifier = "combat.skip"
        skip.accessibilityLabel = GameLocalization.text("Skip Battle Animation")
        skip.addAction(UIAction { [weak self] _ in self?.finish() }, for: .touchUpInside)
        skip.frame = canvas.bounds
        skip.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        canvas.addSubview(skip)
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        GameAudioPlayer.shared.play(canvas.combat.attacker.fraction == 1 ? "battle_good" : "battle_bad")
        let link = CADisplayLink(target: self, selector: #selector(tick(_:)))
        link.add(to: .main, forMode: .common)
        clock = link
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        clock?.invalidate()
        clock = nil
    }

    @objc private func tick(_ link: CADisplayLink) {
        defer { previousTimestamp = link.timestamp }
        guard previousTimestamp > 0, UIApplication.shared.applicationState == .active else { return }
        accumulator += min(0.2, link.timestamp - previousTimestamp)
        while accumulator >= 0.066 {
            accumulator -= 0.066
            canvas.tick += 1
            if canvas.timings.contains(where: { $0.hit == canvas.tick }), GamePreferences.enabled(.vibration) {
                UIImpactFeedbackGenerator(style: .medium).impactOccurred()
            }
        }
        if canvas.tick >= canvas.endTick { finish() }
    }

    private func finish() {
        guard !completed else { return }
        completed = true
        clock?.invalidate()
        clock = nil
        onComplete?()
    }
}

/// Uses the original frame sequences and formation coordinates on a full-height battlefield.
/// Tick values follow the 50ms game counter advanced at the original 66ms display cadence.
@MainActor
final class OriginalCombatCanvas: UIView {
    struct Side {
        let unit: CampaignUnitState
        let definition: UnitDefinition
        let profile: CampaignUnitProfile
        let terrain: TerrainTypeDefinition
        let sprite: OriginalSprite
        let ground: OriginalSprite
        let background: UIImage?
        let positions: [CGPoint]
        let healthAfter: Int
        let initialCount: Int
        let finalCount: Int
    }
    struct Timing {
        let start: Int
        let hit: Int
        let end: Int
    }
    let combat: CampaignCombatPresentation
    let sides: [Side]
    let timings: [Timing]
    var tick = 0 {
        didSet {
            guard tick != oldValue else { return }
            setNeedsDisplay()
            foreground.setNeedsDisplay()
        }
    }
    var endTick: Int { timings.last!.end + 6 }
    var displayedHealth: [Int] {
        sides.indices.map { side in
            let hit: Int?
            if side == 1 {
                hit = timings.first?.hit
            } else {
                hit = timings.count > 1 ? timings[1].hit : nil
            }
            guard let hit else { return sides[side].unit.hitPoints }
            let elapsed = max(0, tick - hit)
            return max(sides[side].healthAfter, sides[side].unit.hitPoints - elapsed * 2)
        }
    }
    private let header: OriginalCombatHeaderView
    private let footer = OriginalFrameView()
    private let foreground = CombatForeground()
    private let numbers: OriginalSprite
    private let heads: OriginalSprite
    private let backs: OriginalSprite
    private var effects: [String: OriginalSprite] = [:]
    private var fieldRect: CGRect = .zero
    private var terrainBitmap: UIImage?
    private var terrainBitmapHeight = 0

    init(combat: CampaignCombatPresentation) throws {
        self.combat = combat
        let definitions = try GameResources.unitDefinitions()
        let profiles = try GameResources.campaignUnitProfiles()
        let terrain = try GameResources.terrainCatalog()
        sides = try [combat.attacker, combat.defender].enumerated().map { index, unit in
            guard let definition = definitions.first(where: { $0.id == unit.type }),
                  let profile = profiles[unit.type],
                  let terrain = terrain.types.first(where: { $0.id == (index == 0 ? combat.attackerTerrain : combat.defenderTerrain) }) else {
                throw OriginalSprite.SpriteError.invalid("combat unit")
            }
            let source = try GameResources.originalResourceData(at: "res/\(definition.resourceName).unit")
            var positions: [CGPoint] = []
            for line in String(decoding: source, as: UTF8.self).split(whereSeparator: \.isNewline) {
                let fields = line.split(whereSeparator: \.isWhitespace)
                if fields.first == "CharPos", fields.count == 4, let x = Int(fields[2]), let y = Int(fields[3]) {
                    positions.append(CGPoint(x: x, y: y))
                }
            }
            let finalHP = index == 0 ? combat.attackerHealthAfter : combat.defenderHealthAfter
            return Side(unit: unit, definition: definition, profile: profile, terrain: terrain,
                        sprite: try OriginalSprite(definition.resourceName, fraction: unit.fraction),
                        ground: try OriginalSprite(terrain.battleForeground),
                        background: OriginalArt.image(terrain.battleBackground + "_bg"), positions: positions,
                        healthAfter: finalHP,
                        initialCount: CampaignCombatPresentation.characterCount(health: unit.hitPoints, maximum: positions.count),
                        finalCount: CampaignCombatPresentation.characterCount(health: finalHP, maximum: positions.count))
        }
        func timing(_ side: Side, start: Int, count: Int, damage: Int) -> Timing {
            let hit: Int
            let duration: Int
            switch side.unit.type {
            case 0, 5, 10:
                let travel = side.unit.type == 10 ? 16 : 13
                let pause = side.unit.type == 10 ? 8 : 1
                hit = 4 + count + travel
                duration = hit + pause + count + travel
            case 9:
                // Two kingwave passes at 9px/tick; 400ms pause before the hit.
                hit = 57
                duration = hit + 21
            default:
                let attackFrames = side.sprite.sequences[min(2, side.sprite.sequences.count - 1)].count
                let recoveryFrames = side.sprite.sequences[min(3, side.sprite.sequences.count - 1)].count
                hit = 5 + count + (attackFrames + recoveryFrames) * side.sprite.frameDelay + 8
                duration = hit + count + 21
            }
            return Timing(start: start, hit: start + hit, end: start + max(duration, hit + (damage + 1) / 2))
        }
        let first = timing(sides[0], start: 16, count: sides[0].initialCount, damage: combat.damage)
        if combat.didCounterattack && sides[1].finalCount > 0 {
            timings = [first, timing(sides[1], start: first.end, count: sides[1].finalCount, damage: combat.counterDamage)]
        } else { timings = [first] }
        numbers = try OriginalSprite("lchars")
        heads = try OriginalSprite("king_heads")
        backs = try OriginalSprite("king_heads_back")
        header = try OriginalCombatHeaderView(attacker: combat.attacker, defender: combat.defender,
                                             attackerTerrain: combat.attackerTerrain, defenderTerrain: combat.defenderTerrain)
        super.init(frame: .zero)
        isOpaque = true
        backgroundColor = .black
        contentMode = .redraw
        accessibilityIdentifier = "combat.canvas"
        addSubview(header)
        addSubview(footer)
        foreground.accessibilityIdentifier = "combat.foreground"
        foreground.isUserInteractionEnabled = false
        foreground.drawContent = { [weak self] context in self?.drawForeground(context) }
        addSubview(foreground)
        header.isUserInteractionEnabled = false
        footer.isUserInteractionEnabled = false
        for name in ["slash", "kingslash", "kingwave", "archer_arrow", "redspark", "smoke", "b_smoke", "spell", "watermagic", "fish", "crater"] {
            effects[name] = try OriginalSprite(name)
        }
    }
    required init?(coder: NSCoder) { nil }

    override func layoutSubviews() {
        super.layoutSubviews()
        let artScale = bounds.width / 240
        let headerHeight = max(44, 24 * artScale)
        let footerHeight = max(44, 28 * artScale)
        header.frame = CGRect(x: 0, y: safeAreaInsets.top, width: bounds.width, height: headerHeight)
        footer.frame = CGRect(x: 0, y: bounds.height - safeAreaInsets.bottom - footerHeight, width: bounds.width, height: footerHeight)
        foreground.frame = bounds
        fieldRect = CGRect(x: 0, y: header.frame.maxY, width: bounds.width, height: max(1, footer.frame.minY - header.frame.maxY))
        setNeedsDisplay()
        foreground.setNeedsDisplay()
    }

    override func draw(_ rect: CGRect) {
        guard let context = UIGraphicsGetCurrentContext(), fieldRect.width > 0 else { return }
        context.interpolationQuality = .none
        let scale = fieldRect.width / 240
        let height = fieldRect.height / scale
        context.saveGState()
        context.translateBy(x: fieldRect.minX, y: fieldRect.minY)
        context.scaleBy(x: scale, y: scale)
        context.clip(to: CGRect(x: 0, y: 0, width: 240, height: height))
        // Compose touching tiles at integer source pixels. Independently scaled tile
        // edges otherwise expose hairline seams at fractional phone display scales.
        terrainImage(height: Int(ceil(height))).draw(at: .zero)
        UIColor.black.setFill()
        context.fill(CGRect(x: 119, y: 0, width: 2, height: height))
        for side in 0..<2 { drawCharacters(side: side, height: height, context: context) }
        for side in 0..<timings.count { drawEffects(side: side, height: height, context: context) }
        context.restoreGState()
    }

    private func drawForeground(_ context: CGContext) {
        let scale = bounds.width / 240
        for side in 0..<2 {
            drawNumber("\(displayedHealth[side])/100",
                       center: CGPoint(x: bounds.width * (side == 0 ? 0.25 : 0.75), y: footer.frame.midY),
                       scale: scale)
        }
        if tick < 16 {
            // MainDisplayable.drawDrap: eight staggered strips over sixteen ticks.
            let segment = floor(bounds.width / 8) + 1
            for index in 0..<8 {
                let opened = segment * CGFloat(min(9, max(0, tick - index))) / 9
                UIColor.black.setFill()
                context.fill(CGRect(x: CGFloat(index) * segment + opened, y: 0, width: segment - opened, height: bounds.height))
            }
        }
    }

    private func drawTerrain(side: Int, height: CGFloat, context: CGContext) {
        let data = sides[side]
        context.saveGState()
        context.clip(to: CGRect(x: CGFloat(side * 120), y: 0, width: 120, height: height))
        context.translateBy(x: side == 0 ? 0 : 240, y: 0)
        if side == 1 { context.scaleBy(x: -1, y: 1) }
        let top = data.background?.size.height ?? 0
        for x in 0..<6 {
            for y in 0..<max(1, Int(ceil((height - top) / 24))) {
                // Stable decoration across redraw/restore; never consumes gameplay's Java RNG.
                let frame = (x * 17 + y * 31 + data.unit.id * 7 + data.terrain.id) % data.ground.frames.count
                data.ground.draw(frame: frame, at: CGPoint(x: x * 24, y: Int(top) + y * 24))
            }
        }
        if let background = data.background {
            for x in stride(from: CGFloat.zero, to: 120, by: background.size.width) { background.draw(at: CGPoint(x: x, y: 0)) }
        }
        context.restoreGState()
    }

    private func terrainImage(height: Int) -> UIImage {
        if terrainBitmapHeight == height, let terrainBitmap { return terrainBitmap }
        let format = UIGraphicsImageRendererFormat()
        format.scale = HighResolutionUnitArt.scale
        format.opaque = true
        let image = UIGraphicsImageRenderer(size: CGSize(width: 240, height: height), format: format).image { renderer in
            let context = renderer.cgContext
            context.interpolationQuality = .none
            context.setShouldAntialias(false)
            UIColor.black.setFill()
            context.fill(CGRect(x: 0, y: 0, width: 240, height: height))
            for side in 0..<2 { drawTerrain(side: side, height: CGFloat(height), context: context) }
        }
        terrainBitmapHeight = height
        terrainBitmap = image
        return image
    }

    private func position(side: Int, slot: Int, height: CGFloat) -> CGPoint {
        let data = sides[side]
        let formation = data.positions[slot]
        let x = floor(formation.x * 240 / 128)
        return CGPoint(x: side == 0 ? x : 240 - x - data.sprite.size.width,
                       y: floor(formation.y * height / 128) - data.sprite.size.height)
    }

    private func drawCharacters(side: Int, height: CGFloat, context: CGContext) {
        let data = sides[side]
        let hit = side == 1 ? timings[0].hit : (timings.count > 1 ? timings[1].hit : Int.max)
        let killed = tick >= hit ? data.initialCount - data.finalCount : 0
        guard killed < data.initialCount else { return }
        let orderedSlots = (killed..<data.initialCount).sorted { data.positions[$0].y < data.positions[$1].y }
        for slot in orderedSlots {
            var point = position(side: side, slot: slot, height: height)
            var sequence = 0
            var sequenceTick = tick
            if side < timings.count {
                let time = timings[side]
                let t = tick - time.start - 4 - (slot - killed)
                if t >= 0, tick < time.end {
                    if [0, 5, 10].contains(data.unit.type) {
                        let travel = data.unit.type == 10 ? 16 : 13
                        let retreatStart = time.hit - time.start + (data.unit.type == 10 ? 8 : 1) + slot - killed
                        let retreat = tick - time.start - retreatStart
                        let dx = retreat >= 0 ? max(0, travel - retreat) : min(t, travel)
                        point.x += CGFloat(dx * (data.unit.type == 5 ? 6 : 3) * (side == 0 ? 1 : -1))
                        sequence = retreat >= 0 ? (retreat < travel ? 3 : 0) : (t < travel ? 1 : 2)
                        sequenceTick = retreat >= 0 ? retreat : t
                        if data.unit.type != 10 {
                            let jump = retreat >= 0 ? retreat : t
                            if jump < 13 { point.y += CGFloat(min(0, -6 * jump + jump * (jump - 1) / 2)) }
                        }
                    } else {
                        let duration = data.sprite.sequences[min(2, data.sprite.sequences.count - 1)].count * data.sprite.frameDelay
                        sequence = t < duration ? 2 : (t < duration + data.sprite.frameDelay ? 3 : 0)
                        sequenceTick = t < duration ? t : t - duration
                    }
                }
            }
            if tick >= hit && tick < hit + 6 { point.x += tick % 2 == 0 ? -2 : 2 }
            if [0, 4, 5, 6].contains(data.unit.type) {
                UIColor(white: 0.25, alpha: 1).setFill()
                context.fillEllipse(in: CGRect(x: point.x, y: position(side: side, slot: slot, height: height).y + data.sprite.size.height * 0.8,
                                              width: data.sprite.size.width, height: data.sprite.size.height / 4))
            }
            let frame = data.sprite.frameIndex(sequence: sequence, tick: sequenceTick, mirrored: side == 0)
            data.sprite.draw(frame: frame, at: point)
            if data.unit.type == 9 {
                let variant = data.unit.resolvedCommanderVariant
                let bodyFrame = frame % (data.sprite.frames.count / 2)
                if bodyFrame == 2 { backs.draw(frame: variant + (side == 0 ? 4 : 0), at: point) }
                else { heads.draw(frame: variant * 2 + bodyFrame + (side == 0 ? 8 : 0), at: point) }
            }
        }
    }

    private func drawEffects(side: Int, height: CGFloat, context: CGContext) {
        let timing = timings[side]
        let data = sides[side]
        let t = tick - timing.start
        guard t >= 0 else { return }
        let targetSide = 1 - side
        if data.unit.type == 9, t >= 5, t < 49, let wave = effects["kingwave"] {
            let elapsed = CGFloat(t - 5)
            let rawX = 32 + elapsed * 9
            let x = rawX < 240 ? rawX : 72 + rawX - 240
            let original = position(side: side, slot: 0, height: height)
            context.saveGState()
            let clipSide = rawX < 240 ? side : targetSide
            context.clip(to: CGRect(x: CGFloat(clipSide * 120), y: 0, width: 120, height: height))
            for path in 0..<2 {
                wave.draw(frame: wave.frameIndex(sequence: 0, tick: t - 5, mirrored: side == 0),
                          at: CGPoint(x: side == 0 ? x : 240 - x - wave.size.width,
                                      y: original.y + data.sprite.size.height - wave.size.height + 2 + elapsed * (path == 0 ? -2 : 1)))
            }
            context.restoreGState()
            if t < 13, let slash = effects["kingslash"] {
                slash.draw(frame: slash.frameIndex(sequence: 0, tick: t - 5, mirrored: side == 0, looping: false), at: original)
            }
        }
        if [0, 5, 10].contains(data.unit.type), tick >= timing.hit - 5, tick < timing.hit,
           let slash = effects["slash"] {
            let count = side == 0 ? data.initialCount : data.finalCount
            for slot in (data.initialCount - count)..<data.initialCount {
                let point = position(side: side, slot: slot, height: height)
                slash.draw(frame: slash.frameIndex(sequence: 0, tick: tick - timing.hit + 5, mirrored: side == 0, looping: false),
                           at: CGPoint(x: point.x + (side == 0 ? 42 : -28), y: point.y + 3))
            }
        }
        if data.unit.type == 1, t >= 5, tick < timing.hit {
            if let arrow = effects["archer_arrow"] {
                for slot in 0..<data.initialCount {
                    let point = position(side: side, slot: slot, height: height)
                    let elapsed = max(0, t - 5 - slot)
                    if elapsed < 5 {
                        arrow.draw(frame: arrow.frameIndex(sequence: 1, tick: elapsed, mirrored: side == 0),
                                   at: CGPoint(x: point.x + (side == 0 ? data.sprite.size.width : -arrow.size.width), y: point.y))
                    }
                }
            }
        }
        let sinceHit = tick - timing.hit
        if sinceHit >= 0, sinceHit < 22 {
            let victim = sides[targetSide]
            let lost = victim.initialCount - victim.finalCount
            for slot in 0..<lost {
                let point = position(side: targetSide, slot: slot, height: height)
                if sinceHit < 6, let spark = effects["redspark"] {
                    spark.draw(frame: spark.frameIndex(sequence: 0, tick: sinceHit, looping: false),
                               at: CGPoint(x: point.x + (victim.sprite.size.width - spark.size.width) / 2, y: point.y + 8))
                }
                if sinceHit < 14, let smoke = effects["b_smoke"] {
                    for puff in -1...1 {
                        smoke.draw(frame: smoke.frameIndex(sequence: 0, tick: sinceHit / 2, looping: false),
                                   at: CGPoint(x: point.x + victim.sprite.size.width / 2 - smoke.size.width / 2 + CGFloat(puff * sinceHit),
                                               y: point.y + victim.sprite.size.height - smoke.size.height - CGFloat(sinceHit * 2)))
                    }
                }
            }
            if data.unit.type == 1, let arrow = effects["archer_arrow"] {
                for slot in 0..<data.initialCount where sinceHit >= slot {
                    arrow.draw(frame: arrow.frameIndex(sequence: 0, tick: 0, mirrored: side == 0),
                               at: CGPoint(x: CGFloat(targetSide * 120 + 15 + slot * 13), y: height * CGFloat(slot * 2 + 1) / CGFloat(data.initialCount * 2)))
                }
            }
            let effectName: String? = switch data.unit.type { case 2: "fish"; case 3: "spell"; case 6, 7: "crater"; default: nil }
            if let effectName, let effect = effects[effectName], sinceHit < 10 {
                for slot in 0..<max(1, data.initialCount) {
                    effect.draw(frame: effect.frameIndex(sequence: 0, tick: sinceHit, looping: false),
                                at: CGPoint(x: CGFloat(targetSide * 120 + 20 + slot * 7), y: height * CGFloat(slot * 2 + 1) / CGFloat(max(1, data.initialCount) * 2)))
                }
            }
            let damage = side == 0 ? combat.damage : combat.counterDamage
            drawNumber("-\(damage)", center: CGPoint(x: CGFloat(targetSide * 120 + 60), y: height / 2 - CGFloat(min(12, sinceHit) * 2)), scale: 1)
        }
    }

    private func drawNumber(_ text: String, center: CGPoint, scale: CGFloat) {
        let chars = Array(text)
        let origin = CGPoint(x: center.x - CGFloat(chars.count) * numbers.size.width * scale / 2,
                             y: center.y - numbers.size.height * scale / 2)
        for (index, char) in chars.enumerated() {
            let frame = char.wholeNumberValue ?? (char == "/" ? 10 : 11)
            numbers.draw(frame: frame, at: CGPoint(x: origin.x + CGFloat(index) * numbers.size.width * scale, y: origin.y), scale: scale)
        }
    }
}

@MainActor
private final class CombatForeground: UIView {
    var drawContent: ((CGContext) -> Void)?
    override init(frame: CGRect) { super.init(frame: frame); backgroundColor = .clear; isOpaque = false }
    required init?(coder: NSCoder) { nil }
    override func draw(_ rect: CGRect) {
        guard let context = UIGraphicsGetCurrentContext() else { return }
        context.interpolationQuality = .none
        drawContent?(context)
    }
}

/// The same two-row attack/defence/rank strip is used in target selection and combat.
@MainActor
final class OriginalCombatHeaderView: UIView {
    private let values: [[String]]
    private let fractions: [Int]
    private let statusUnits: [CampaignUnitState]
    private let status: OriginalSprite
    private let numbers: OriginalSprite
    private let icons: OriginalSprite

    init(attacker: CampaignUnitState, defender: CampaignUnitState, attackerTerrain: Int, defenderTerrain: Int) throws {
        let profiles = try GameResources.campaignUnitProfiles()
        let rules = try GameResources.campaignRuleset()
        let units = [attacker, defender]
        let terrains = [attackerTerrain, defenderTerrain]
        values = (0..<2).map { index in
            let unit = units[index], opponent = units[1 - index]
            guard let profile = profiles[unit.type] else { return ["0-0", "0", "0"] }
            var offense = unit.rank * 2 + unit.statusOffenseBonus
            if profile.hasAntiAir && profiles[opponent.type]?.canFly == true { offense += 15 }
            if unit.type == 4 && opponent.type == 10 { offense += 15 }
            if profile.isAmphibious && terrains[index] == 5 { offense += 10 }
            var defense = profile.baseDefense + unit.rank * 2 + unit.statusDefenseBonus + (rules.defenseByTerrainID[terrains[index]] ?? 0)
            if profile.isAmphibious && terrains[index] == 5 { defense += 15 }
            let canCounter = profile.minimumRange == 1 && abs(unit.x - opponent.x) + abs(unit.y - opponent.y) == 1
            return [index == 1 && !canCounter ? "0-0" : "\(profile.attackMinimum + offense)-\(profile.attackMaximum + offense)", "\(defense)", "\(unit.rank)"]
        }
        fractions = units.map(\.fraction)
        statusUnits = units
        status = try OriginalSprite("status")
        numbers = try OriginalSprite("chars")
        icons = try OriginalSprite("hud_icons")
        super.init(frame: .zero)
        backgroundColor = UIColor(red: 173/255, green: 183/255, blue: 173/255, alpha: 1)
        contentMode = .redraw
    }
    required init?(coder: NSCoder) { nil }
    override func draw(_ rect: CGRect) {
        guard let context = UIGraphicsGetCurrentContext() else { return }
        context.interpolationQuality = .none
        let scale = bounds.width / 240
        let columns: [CGFloat] = [0, 100, 179, 240]
        for part in 0..<3 {
            icons.draw(frame: part, at: CGPoint(x: columns[part] * scale, y: (bounds.height - 16 * scale) / 2), scale: scale)
            let x = (columns[part] + 15) * scale
            for row in 0..<2 {
                let y = CGFloat(row) * bounds.height / 2 + 1
                UIColor(red: 33/255, green: 40/255, blue: 66/255, alpha: 1).setFill()
                context.fill(CGRect(x: x, y: y, width: (columns[part + 1] - columns[part] - 16) * scale, height: bounds.height / 2 - 2))
                (fractions[row] == 1 ? UIColor.systemBlue : UIColor.systemPink).setFill()
                context.fill(CGRect(x: x + scale, y: y + scale, width: 3 * scale, height: bounds.height / 2 - 2 - 2 * scale))
                var statusX = (columns[part + 1] - 10) * scale
                let statusY = y + max(1, (bounds.height / 2 - 9 * scale) / 2)
                if part == 0, statusUnits[row].isBlessed {
                    status.draw(frame: 1, at: CGPoint(x: statusX, y: statusY), scale: scale)
                    statusX -= 7 * scale
                }
                if part < 2, statusUnits[row].isPoisoned {
                    status.draw(frame: 0, at: CGPoint(x: statusX, y: statusY), scale: scale)
                }
                for (index, char) in values[row][part].enumerated() {
                    numbers.draw(frame: char.wholeNumberValue ?? 10,
                                 at: CGPoint(x: x + CGFloat(5 + index * 6) * scale, y: y + max(1, (bounds.height / 2 - 7 * scale) / 2)), scale: scale)
                }
            }
        }
    }
}
