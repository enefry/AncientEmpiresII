import AECore
import AEContent
import UIKit

@MainActor
enum OriginalMapPreview {
    /// AuxDisplayable mode 8: the original 10px terrain tiles and faction markers.
    static func image(map: CampaignMap, units: [CampaignUnitState]) throws -> UIImage {
        let tiles = try OriginalSprite("stiles0")
        let markers = try OriginalSprite("mini_icons")
        let definitions = Dictionary(uniqueKeysWithValues: try GameResources.terrainCatalog().tiles.map { ($0.id, $0.minimapFrame) })
        let format = UIGraphicsImageRendererFormat()
        format.scale = HighResolutionUnitArt.scale
        format.opaque = true
        return UIGraphicsImageRenderer(size: CGSize(width: map.width * 10, height: map.height * 10), format: format).image { renderer in
            renderer.cgContext.interpolationQuality = .none
            renderer.cgContext.setShouldAntialias(false)
            UIColor.black.setFill()
            renderer.fill(CGRect(x: 0, y: 0, width: map.width * 10, height: map.height * 10))
            for (y, row) in map.rows.enumerated() {
                for (x, tile) in row.enumerated() {
                    tiles.draw(frame: definitions[tile] ?? 0, at: CGPoint(x: x * 10, y: y * 10))
                }
            }
            for unit in units where unit.isAlive {
                markers.draw(frame: unit.fraction - 1, at: CGPoint(x: unit.x * 10, y: unit.y * 10))
            }
        }
    }
}

@MainActor
final class OriginalMapOverviewViewController: UIViewController {
    var onFocus: ((GridPoint) -> Void)?
    private let map: CampaignMap
    private let picture: UIImageView
    private let panel = OriginalFrameView()
    private let titlePanel = OriginalFrameView()
    private let heading = OriginalArt.label(GameLocalization.text("story.70"), size: 23)
    private lazy var back = OriginalGameButton(GameLocalization.text("story.31"), identifier: "panel.back") { [weak self] in self?.dismiss(animated: false) }

    init(map: CampaignMap, units: [CampaignUnitState]) throws {
        self.map = map
        picture = OriginalArt.pixelView(try OriginalMapPreview.image(map: map, units: units))
        super.init(nibName: nil, bundle: nil)
    }
    required init?(coder: NSCoder) { nil }
    override var prefersStatusBarHidden: Bool { true }
    override func viewDidLoad() {
        super.viewDidLoad()
        view.accessibilityViewIsModal = true
        view.backgroundColor = UIColor.black.withAlphaComponent(0.86)
        [panel, titlePanel, heading, picture, back].forEach(view.addSubview)
        heading.textAlignment = .center
        picture.isUserInteractionEnabled = true
        picture.isAccessibilityElement = true
        picture.accessibilityIdentifier = "overview.map"
        picture.accessibilityLabel = GameLocalization.format("Battlefield overview, %@ by %@ cells", String(describing: map.width), String(describing: map.height))
        picture.accessibilityHint = GameLocalization.text("Tap a location to return to the battlefield and focus on it.")
        picture.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(focus(_:))))
    }
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let safe = view.safeAreaLayoutGuide.layoutFrame
        heading.frame = CGRect(x: safe.minX + 12, y: safe.minY + 16, width: safe.width - 24, height: 52)
        titlePanel.frame = heading.frame.insetBy(dx: 0, dy: -6)
        let cell = min((safe.width - 48) / CGFloat(map.width), (safe.height - 180) / CGFloat(map.height))
        let size = CGSize(width: cell * CGFloat(map.width), height: cell * CGFloat(map.height))
        picture.frame = CGRect(x: safe.midX - size.width / 2, y: safe.midY - size.height / 2, width: size.width, height: size.height)
        panel.frame = picture.frame.insetBy(dx: -12, dy: -12)
        back.frame = CGRect(x: safe.minX + 12, y: safe.maxY - 58, width: 100, height: 48)
    }
    @objc private func focus(_ gesture: UITapGestureRecognizer) {
        let location = gesture.location(in: picture)
        let x = min(map.width - 1, max(0, Int(location.x * CGFloat(map.width) / picture.bounds.width)))
        let y = min(map.height - 1, max(0, Int(location.y * CGFloat(map.height) / picture.bounds.height)))
        let callback = onFocus
        dismiss(animated: false) { callback?(GridPoint(x: x, y: y)) }
    }
}
