# 远古帝国 II：设定、算法与资源还原

本目录保存资料还原阶段的研究结果，让游戏实现能够找到原包数据、实际逻辑与证据。当前游戏实现和验证状态见 [原版对齐清单](../../docs/ORIGINAL_ALIGNMENT.md)。

基准是用户选定的 Macrospace / SilentKnight v2.5 JAR（SHA-256 `f2b3e06190014940a04da70677d6504dd3fd3d9fbc39a49192926293f830f5ae`），不是未经修改的 2005 首发版。`AEIIRM_src` 是人工整理过的反编译源码：用于解释逻辑，不能默认其所有行为与选定 JAR 相同。版本关系见 [源码对照](../AEIIRM_SOURCE_COMPARISON.md)。

## 阅读入口

| 资料 | 内容 |
| --- | --- |
| [游戏设定](SETTINGS.md) | 世界背景、模式、12 种单位、属性位、11 种地形、经济与行动 |
| [核心算法](ALGORITHMS.md) | 伤害、反击、升级、移动与路线、状态、回合、胜负、时钟 |
| [启动标题动画](STARTUP_ANIMATION.md) | 原始字节码执行、逐行扭动、背景淡入、扫光、逐帧回放与录屏对照 |
| [AI 决策](AI.md) | 行动顺序、战略目标、候选评分、招聘与最终关特殊行为 |
| [战役还原](CAMPAIGN.md) | 8 关初始参数、剧情触发、增援和代码胜负条件 |
| [资源格式](FORMATS.md) | 地图、语言表、精灵、图片、音乐、存档与编辑器 |
| [地图与音乐清单](CATALOG.md) | 全部地图尺寸、原始单位数、预览、MIDI 时长 |
| [完整英文文本](TEXTS.md) | 317 条文本，保留 ID 和英文原文 |
| [逐状态证据](CAMPAIGN_STATES.md) | 213 个战役 case 的源码摘录、行号与关联文本 |
| [差异及待验证项](OPEN_QUESTIONS.md) | 已发现的行为差异、原版边界、后续运行核对路线 |
| [离线资源浏览页](viewer.html) | 启动动画及逐帧查看、地图切换、比例/网格/单位开关、图格详情、原图搜索、精灵定义 |

请打开本目录的 [viewer.html](viewer.html)。`tools/game_reference_viewer.html.template` 是生成模板，不能直接浏览；旧的 `tools/game_reference_viewer.html` 入口会跳转到完整浏览页。

浏览页是资料查看器，不是可玩的游戏。原图以内嵌 PNG 保存，可在普通浏览器打开，不依赖网络；本次已检查数据和脚本语法，未完成浏览器交互验证。地图预览仅展示 `.aem` 原始记录，开场剧情会移动、增删单位，不能当作正式开局截图。

## 已恢复的数据

- 12 种单位的数值、属性位与战斗角色坐标：[units.json](data/units.json)。
- 11 种地形、48 个图块及小地图映射：[terrain.json](data/terrain.json)。
- 20 张地图的 4,400 个图格、103 条原始单位记录、建筑及保留块：[maps.json](data/maps.json)，每张另有独立 JSON。
- 58 组精灵的帧映射、变换位、锚点、位移、序列、延迟和各阵营图源：[sprites.json](data/sprites.json)。
- 全部 234 PNG、9 MIDI 和其它资源的大小、哈希、图片尺寸/调色板、MIDI 事件摘要：[assets.json](data/assets.json)。原始字节仍在 [original](../original)。
- 完整文本 [strings.json](data/strings.json)；逐状态证据 [campaign-states.json](data/campaign-states.json)。

## 证据等级

**数据已验证**：所有 355 个提取文件逐项与 JAR 相等；地图/语言/MIDI 完整消耗输入；PNG CRC 与像素解码通过；配置数量、帧/序列索引与图源已检查。`buildings.sprite` 唯一缺少配套图源，已登记，未补造。99 条像素未对齐记录、5 条地图外记录均保留，没有“修正”。

**字节码已核对**：伤害、随机数、等级加成、晋级无上限判断、反击资格、状态效果、移动预算严格比较的核心指令。证据锚点在算法文档。

**标题函数已隔离执行**：原 JAR 的启动更新、绘制、扭动与正弦查表函数在 JVM 中执行，记录绘制指令；369 组 / 31,980 行指令与参考实现一致。已比较用户录屏的文字展开形状与大致节奏。此项不是完整 J2ME 运行验证，也不是 iOS 实现；范围和限制见 [启动动画](STARTUP_ANIMATION.md)。

**源码还原**：其余回合、经济、AI、战役、存档与动画机制主要通过 `AEIIRM_src` 调用链解释；部分另与 JAR 反编译结果交叉查看。213 个 case 的导出代表证据覆盖，不代表已逐个执行，也不代表每一帧动画都已完整还原。

**未验证**：J2ME 实机/模拟器运行、每关全过程、实际音源与循环、动画节奏、历史存档兼容；iOS 工程尚未开始。不能把成功解析资料说成“已完成游戏还原”。

## 复现

在工程根目录运行：

```sh
python3 tools/catalog_game_reference.py
```

需要 Python 3 和 Pillow（当前环境已有）。脚本不下载东西、不改原包与提供的源码。会更新 `data/`、预览、浏览页、文本、状态摘录、清单与 [VALIDATION.json](VALIDATION.json)；人工编写的规则文档不会覆盖。

当前阶段交付的是可审查的还原资料。进入 iOS 开发之前，先用原版运行对照补齐 [待验证项](OPEN_QUESTIONS.md)，尤其是行动、战役与视觉时间线。
