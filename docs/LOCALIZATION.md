# iOS 系统多语言

游戏使用 iOS 原生 Bundle 本地化，支持英文（en）、简体中文（zh-Hans）和繁体中文（zh-Hant）。默认跟随系统语言，也支持 iOS 设置中的单独应用语言。未支持的语言回退为英文。

## 覆盖范围

- 标题文字、主菜单、设置、自由对战配置、本地地图导入与错误提示。
- 全部 317 个原版文本 ID：地图和章节名、任务目标、剧情、单位名称与军衔、描述、教程、通关预言和制作人员名单。
- 战场操作、回合与状态提示、存档、招募、地图概览和 VoiceOver 文本。
- 中英文教程共用触屏操作说明，不再显示原版 `%K5` 等键盘占位符。
- 启动页和菜单标题保留原版英文文案图片及动画，不使用中文文字覆盖。制作人员姓名、网址、电子邮箱与原版版权署名保留。

## 资源与维护

`Packages/AncientEmpiresEngine/Sources/AECore/Resources/{en,zh-Hans,zh-Hant}.lproj/Localizable.strings` 保存界面和内容翻译。`GameLocalization` 为 app 与资源模块提供共享查找和格式化；默认不覆盖系统语言。

`App/{en,zh-Hans,zh-Hant}.lproj/InfoPlist.strings` 本地化应用显示名称。`project.yml` 声明支持语言，Swift Package 的 `defaultLocalization` 为英文。

`story.<id>` 对应原版数据中的固定 ID。存档和原始 JSON 保持原有标识与名称，仅显示时翻译。用户导入地图的名称始终原样显示。动态界面消息使用格式化资源，参数不参与语言键拼接。

添加语言时，补齐同名 `.strings`，更新 `GameLocalization.supportedLanguages` 和 `project.yml` 的 `CFBundleLocalizations`，添加 `InfoPlist.strings`，然后执行 `xcodegen generate`。

## 验证

```sh
python3 tools/audit_localization.py
xcodebuild -project AncientEmpiresII.xcodeproj -scheme AncientEmpiresII \
  -destination 'platform=iOS Simulator,id=<模拟器 ID>' \
  -derivedDataPath .noindex/DerivedData -collect-test-diagnostics never \
  -only-testing:AEAppTests/GameLocalizationTests \
  -only-testing:AEAppUITests/LocalizationUITests test
```

资源审计检查三种语言的键集合、原版文本完整性、格式参数、键盘占位符与 Swift 中引用的静态键。数据测试覆盖地区语言匹配、未知语言回退、原版单位解码和地图名称保存兼容性。界面测试分别用三种语言启动，检查标题、菜单、设置、教程、对战地图与战役剧情，保存截图。

资源审计目前覆盖 494 个键、三种语言。原始文件修改前的快照位于 `.noindex/localization-backup/`，便于当前无 Git 元数据的工作目录对照审阅。

## 本次验证（2026-10-03）

- 源码与资源审计：494 个键 × 三种语言，键集合、参数和原版文本 ID 检查通过；实际 app 包声明三种语言，默认语言为英文。
- SwiftPM：28 项引擎／内容测试通过。
- iOS 26.5 模拟器：12 项本地化、招募和战斗渲染测试；三种语言的完整界面流程；两项菜单、手动存档、地图概览和重启载入回归，共 17 项通过。
- 本轮未进行真机验证。

测试日志：`.noindex/logs/localization-verified-tests.log`、`.noindex/logs/localization-save-regression.log` 和 `.noindex/logs/localization-package-tests.log`。

三语言结果包：`.noindex/artifacts/localization-verified-tests.xcresult`；存档回归结果包：`.noindex/artifacts/localization-save-regression.xcresult`。

截图示例：[简体中文菜单](../.noindex/artifacts/localization-screenshots/47C0A18F-F671-4BBD-8420-2B2FA9495E43.png)、[简体中文设置](../.noindex/artifacts/localization-screenshots/C27BE3A0-3F60-472D-BF88-D6CDD1D21AE2.png)、[繁体中文战役教程](../.noindex/artifacts/localization-screenshots/EE52202A-81D6-4DBC-AEDF-D5FF34F011A4.png)。完整截图及名称索引位于 `.noindex/artifacts/localization-screenshots/manifest.json`。

Xcode 的自动系统诊断采集曾阻塞测试收尾，最终使用 `-collect-test-diagnostics never` 验证；测试输出日志仍正常保留。
