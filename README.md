# Ancient Empires II

本仓库保存《远古帝国 II》的 UIKit / iOS 重制工程，包含游戏源码、资源、配置、测试和必要参考资料。

## 目录入口

| 路径 | 内容 |
| --- | --- |
| [App/](App/) | 二代 UIKit 应用、界面、绘制与生命周期 |
| [Packages/AncientEmpiresEngine/](Packages/AncientEmpiresEngine/) | 二代 AECore 规则与 AEContent 游戏内容、资源及 Swift Package 测试 |
| [AppTests/](AppTests/) / [AppUITests/](AppUITests/) | 二代应用单元测试与模拟器 UI 测试 |
| [project.yml](project.yml) / [AncientEmpiresII.xcodeproj/](AncientEmpiresII.xcodeproj/) | XcodeGen 配置来源和可直接打开的共享 Xcode 工程 |
| [docs/](docs/README.md) | 二代需求、实现记录、功能说明和验收边界 |
| [Examples/](Examples/) | 可导入的自由对战地图示例 |
| [tools/](tools/README.md) | 原始资料分析、内容导出、美术打包和本地化审计工具 |
| [reference/](reference/README.md) | 原版规则研究、字节码、恢复源码、地图和画面证据 |
| [reference/inputs/](reference/inputs/README.md) | 原始 JAR、人工整理的 Java 源码和输入素材 |
| [reference/art/](reference/art/) | 高清美术源图、生成提示词和打包依据 |

## 开发与验证

需要 Xcode / Swift 6 和 XcodeGen；iOS 工程当前最低版本为 iOS 17。以下命令从仓库根目录执行：

```sh
xcodegen generate --spec project.yml
open AncientEmpiresII.xcodeproj
python3 tools/audit_localization.py
python3 tools/export_ios_content.py --check
./tools/test.sh
```

二代模拟器构建复用仓库内的 DerivedData：

```sh
xcodebuild -project AncientEmpiresII.xcodeproj -scheme AncientEmpiresII \
  -destination 'generic/platform=iOS Simulator' \
  -derivedDataPath "$PWD/.noindex/DerivedData" \
  CODE_SIGNING_ALLOWED=NO build
```

在 Xcode 中选择 `AncientEmpiresII` scheme 运行或测试。真机构建使用自己的 Development Team；模拟器测试需选择实际可用的模拟器。多个 worktree 使用各自的 `.noindex/DerivedData/<worktree-name>`，不创建一次性 DerivedData。

已实现功能与历史验证记录见 [文档索引](docs/README.md)。其中记录的模拟器、真机和发布验收边界按原记录保留；整理目录不代表新增运行或发布验收。

## Git 管理范围

源码、游戏运行资源、测试、共享 Xcode 工程、生成配置、开发工具、必要参考输入及美术源文件纳入 Git。`project.yml` 是 Xcode 配置来源，修改配置后重新生成并提交共享工程。

`.noindex/`、SwiftPM 缓存、Xcode 用户配置、历史 `资源备份/` 不纳入 Git。原始视频已归档到 `.noindex/reference-videos/`，重复的原始压缩包在 `.noindex/reference-archives/`；这些本地资料保留原文件，不是游戏编译或运行依赖。参考视频的抽帧证据保存在 `reference/video-comparison/`。

原 J2ME 资料保留原文件名、内容与来源记录；反编译材料用于研究，具体来源和已知限制见 [reference/README.md](reference/README.md)。
