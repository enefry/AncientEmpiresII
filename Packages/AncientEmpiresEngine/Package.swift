// swift-tools-version: 6.0

import PackageDescription

let package = Package(
    name: "AncientEmpiresEngine",
    defaultLocalization: "en",
    platforms: [.iOS(.v17)],
    products: [
        .library(name: "AECore", targets: ["AECore"]),
        .library(name: "AEContent", targets: ["AEContent"]),
    ],
    targets: [
        .target(name: "AECore", resources: [.process("Resources")]),
        .target(
            name: "AEContent",
            dependencies: ["AECore"],
            resources: [
                .copy("Resources/Audio"),
                .copy("Resources/CampaignMaps"),
                .copy("Resources/Enhanced"),
                .copy("Resources/GameData"),
                .copy("Resources/Images"),
                .copy("Resources/Original"),
                .copy("Resources/Previews"),
                .copy("Resources/StartupReference"),
            ]
        ),
        .testTarget(name: "AECoreTests", dependencies: ["AECore"]),
        .testTarget(name: "AEContentTests", dependencies: ["AEContent", "AECore"]),
    ]
)
