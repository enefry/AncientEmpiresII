import AECore
import AEContent
import UIKit

@MainActor
final class OriginalUnitStatsView: UIView {
    private let unit: CampaignUnitState
    private let profile: CampaignUnitProfile
    private let purchaseCost: Int?
    private let terrainDefense: Int
    private let numbers: OriginalSprite
    private let largeNumbers: OriginalSprite
    private let icons: OriginalSprite
    private let actions: OriginalSprite
    private let status: OriginalSprite
    private let expLabel = OriginalArt.label(GameLocalization.text("story.97"), size: 16)
    private let rangeLabel = OriginalArt.label(size: 15)

    init(unit: CampaignUnitState, profile: CampaignUnitProfile, terrainDefense: Int, purchaseCost: Int? = nil) throws {
        self.purchaseCost = purchaseCost
        self.unit = unit
        self.profile = profile
        self.terrainDefense = terrainDefense
        numbers = try OriginalSprite("chars")
        largeNumbers = try OriginalSprite("lchars")
        icons = try OriginalSprite("hud_icons")
        actions = try OriginalSprite("action_icons")
        status = try OriginalSprite("status")
        super.init(frame: .zero)
        backgroundColor = .clear
        contentMode = .redraw
        rangeLabel.text = GameLocalization.format("RANGE  %@–%@", String(describing: profile.minimumRange), String(describing: profile.maximumRange))
        [expLabel, rangeLabel].forEach(addSubview)
        expLabel.isHidden = purchaseCost != nil
        rangeLabel.isHidden = purchaseCost != nil
        isAccessibilityElement = true
        accessibilityLabel = GameLocalization.format("HP %@, EXP %@, RANK %@, ATTACK %@–%@, DEFENCE %@, MOVE %@", String(describing: unit.hitPoints), String(describing: unit.experience), String(describing: unit.rank), String(describing: profile.attackMinimum + unit.rank * 2 + unit.statusOffenseBonus), String(describing: profile.attackMaximum + unit.rank * 2 + unit.statusOffenseBonus), String(describing: profile.baseDefense + unit.rank * 2 + unit.statusDefenseBonus + terrainDefense), String(describing: profile.moveBudget))
        accessibilityIdentifier = purchaseCost == nil ? "unit.stats" : "recruit.stats"
        if let purchaseCost { accessibilityLabel = GameLocalization.format("COST %@, ATTACK %@–%@, DEFENCE %@, MOVE %@", String(describing: purchaseCost), String(describing: profile.attackMinimum + unit.rank * 2 + unit.statusOffenseBonus), String(describing: profile.attackMaximum + unit.rank * 2 + unit.statusOffenseBonus), String(describing: profile.baseDefense + unit.rank * 2 + unit.statusDefenseBonus), String(describing: profile.moveBudget)) }
    }
    required init?(coder: NSCoder) { nil }
    override func layoutSubviews() {
        super.layoutSubviews()
        expLabel.frame = CGRect(x: 0, y: 68, width: 46, height: 24)
        rangeLabel.frame = CGRect(x: 0, y: 192, width: bounds.width, height: 25)
    }
    override func draw(_ rect: CGRect) {
        guard let context = UIGraphicsGetCurrentContext() else { return }
        context.interpolationQuality = .none
        let faction = ["blue", "red", "green", "black"][min(3, max(0, unit.fraction - 1))]
        OriginalArt.frame("\(faction)/unit_icons", x: unit.type * 24, width: 24, height: 24)?.draw(in: CGRect(x: 0, y: 0, width: 60, height: 60))
        if unit.type == 9 {
            OriginalArt.frame("king_head_icons", x: unit.resolvedCommanderVariant * 13, width: 13, height: 12)?.draw(in: CGRect(x: 17.5, y: 0, width: 32.5, height: 30))
        }
        let headline = purchaseCost.map(String.init) ?? "\(unit.hitPoints)/100"
        drawNumber(headline, sprite: largeNumbers, x: bounds.width - CGFloat(headline.count * 16), y: 22, scale: 2)
        if purchaseCost != nil {
            OriginalArt.frame("hud_icons_2", x: 11, width: 11, height: 11)?.draw(in: CGRect(x: bounds.width - CGFloat(headline.count * 16) - 30, y: 20, width: 22, height: 22))
        }
        if purchaseCost == nil {
        let threshold = max(1, profile.nextRankExperience(atRank: unit.rank))
        UIColor.lightGray.setFill()
        let track = CGRect(x: 50, y: 71, width: max(30, bounds.width - 106), height: 18)
        context.fill(track)
        UIColor.black.setFill()
        context.fill(track.insetBy(dx: 2, dy: 2))
        UIColor(red: 0.13, green: 0.32, blue: 0.61, alpha: 1).setFill()
        context.fill(CGRect(x: track.minX + 2, y: track.minY + 2, width: (track.width - 4) * min(1, CGFloat(unit.experience) / CGFloat(threshold)), height: track.height - 4))
        icons.draw(frame: 2, at: CGPoint(x: bounds.width - 48, y: 68), scale: 1.6)
        drawNumber("\(unit.rank)", sprite: numbers, x: bounds.width - 22, y: 74, scale: 2)
        }
        let values = ["\(profile.attackMinimum + unit.rank * 2 + unit.statusOffenseBonus)-\(profile.attackMaximum + unit.rank * 2 + unit.statusOffenseBonus)", "\(profile.baseDefense + unit.rank * 2 + unit.statusDefenseBonus + terrainDefense)", "\(profile.moveBudget)"]
        for index in 0..<3 {
            let x = index == 1 ? bounds.width / 2 + 4 : 0
            let y: CGFloat = (index == 2 ? 151 : 104) - (purchaseCost == nil ? 0 : 31)
            OriginalArt.smallCircle?.draw(in: CGRect(x: x, y: y, width: 40, height: 42))
            if index == 2 { actions.draw(frame: 5, at: CGPoint(x: x + 8, y: y + 8), scale: 1.5) }
            else { icons.draw(frame: index, at: CGPoint(x: x + 10, y: y + 8), scale: 1.5) }
            drawNumber(values[index], sprite: numbers, x: x + 44, y: y + 13, scale: 1.6)
            if index < 2 {
                var statusX = x + bounds.width / 2 - 20
                if index == 0, unit.isBlessed {
                    status.draw(frame: 1, at: CGPoint(x: statusX, y: y + 13), scale: 1.6)
                    statusX -= 12
                }
                if unit.isPoisoned { status.draw(frame: 0, at: CGPoint(x: statusX, y: y + 13), scale: 1.6) }
            }
        }
        UIColor.lightGray.setStroke()
        context.setLineWidth(1)
        for y in (purchaseCost == nil ? [65, 97, 222] : [65, 174]) { context.move(to: CGPoint(x: 0, y: y)); context.addLine(to: CGPoint(x: bounds.width, y: CGFloat(y))) }
        context.strokePath()
    }
    private func drawNumber(_ value: String, sprite: OriginalSprite, x: CGFloat, y: CGFloat, scale: CGFloat) {
        for (index, char) in value.enumerated() {
            let glyph = char.wholeNumberValue ?? (sprite === largeNumbers ? (char == "/" ? 10 : 11) : 10)
            sprite.draw(frame: glyph, at: CGPoint(x: x + CGFloat(index) * sprite.size.width * scale, y: y), scale: scale)
        }
    }
}
