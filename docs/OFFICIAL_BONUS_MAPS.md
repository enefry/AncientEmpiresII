# 历史追加自由对战地图

现已内置 8 张历史下载地图，与原版 12 张分组显示，共 20 张。运行时全部离线读取，仍可本地导入地图。

## 来源及证据边界

采用 Ancient Empires Reloaded 项目 2016-06-11 的固定提交：

- [提交 ef6d59f：add 8 official downloadable maps](https://github.com/AncientTree/project_aeii/commit/ef6d59f719f946d910fbd896290bbe7418c0e32b)
- 这笔提交把地图列表从 12 增至 20，并一次性添加下面 8 个文件。
- 该版本 8 个地图文件的作者字段均为 `Macrospace`。
- 下载的是社区项目保存并转换过格式的副本，不是从旧官方服务器恢复的原始 `.aem` 字节，也没有找到发行商签名或原始下载目录。官方归属依据是当时维护者的明确记录及文件作者字段。
- 固定采用上述早期提交，而非后来带 `200`、`300` 后缀的玩家修改版。

原版代码的下载接口为 `http://msaeii.scores.macrospace.com/connectx/in`，使用 POST 请求 `levels` 目录；本次查询互联网档案馆只得到一次空响应，无法恢复地图目录或下载正文。

## 地图目录

| ID | 保存时的名称 | 中文显示名 | 尺寸 | 阵营 | 初始单位 |
| --- | --- | --- | --- | --- | --- |
| b0 | classic 1 | 经典地图 1 | 16×15 | 蓝、红、绿、黑 | 4 名指挥官 |
| b1 | classic 2 | 经典地图 2 | 17×12 | 蓝、绿、黑 | 3 名指挥官 |
| b2 | Duel | 决斗 | 13×13 | 蓝、红 | 2 名指挥官、4 个石人 |
| b3 | Crossed swords | 交锋 | 11×18 | 蓝、红 | 2 名指挥官 |
| b4 | Critical mass | 临界点 | 15×15 | 蓝、红、绿、黑 | 4 名指挥官 |
| b5 | Midway | 中途 | 17×12 | 蓝、红、绿 | 3 名指挥官 |
| b6 | Swamplands | 沼泽地 | 10×10 | 蓝、红 | 2 名指挥官 |
| b7 | Glu | Glu | 13×19 | 蓝、红、绿 | 3 名指挥官 |

中文名称为本项目翻译，不声称是官方中文译名。阵营回合顺序仍按城堡的原版列扫描顺序决定。

## 转换与复现

固定源文件及 SHA-256 清单位于 [reference/bonus-skirmish/sources.json](../reference/bonus-skirmish/sources.json)，原样保存的社区格式文件在同目录的 `maps/`。包内的独立目录为 `GameData/bonus-maps.json`，地图为 `GameData/maps/b0.json`～`b7.json`。原始 JAR 的 20 张地图清单及其来源 manifest 保持真实的原始计数（8 张战役 + 12 张自由对战）。

运行 `python3 tools/export_bonus_skirmish.py` 可离线重新生成；`--check` 校验源哈希与全部输出，无需联网。

社区格式使用 UTF 作者名、4 个阵营布尔值、宽高、按列存储的 16 位地块、32 位阵营/兵种/格坐标。转换时使用明确的地块对应表：0～27 不变；28→29、29→30；36～45 各加 1。没有猜测未验证或未使用的地块。

映射通过 Icy Paths、Frozen fields、Liberty Port、Solitude 四张未修改原版地图逐格核对；缺少样本的地块 2、绿村庄 42、黑村庄 44 分别与原版地块 2、43、45 的 RGBA 像素完全一致。单位阵营由 0～3 转为 1～4，兵种 6/9 保留为石人/指挥官；格坐标换算到原版 24 像素单位。由于保存副本已经转换为格坐标，不能恢复下载原件可能包含的像素偏移。

转换同时校验边界、阵营城堡、单位重叠，并确认 8 张地图互不重复且不重复现有原版地图。存档通过现有自由对战快照保存地图与配置。

## 验证

引擎测试 34 个测试定义、7 个测试组全部通过，其中地图开局/电脑回合/存档恢复参数化覆盖全部 20 张地图。记录位于 `.noindex/logs/bonus-maps-package.log`。

模拟器构建及 7 个 UIKit 测试、1 个端到端 UI 测试全部通过，记录位于 `.noindex/logs/bonus-maps-ui.log`。UIKit 覆盖 8 张追加地图的选择、预览和开局；端到端验证菜单滚动选择 Glu、预览、开局和杀进程续玩。截图保存在 `.noindex/artifacts/bonus-maps-ui/`。未做真机验证。

4 个既有语言回归测试通过，见 `.noindex/logs/bonus-maps-localization.log`。本次修复了 SwiftPM 生成小写 `lproj` 目录时显式语言查询的回退问题；追加地图名称提供英文、简体和繁体中文。
