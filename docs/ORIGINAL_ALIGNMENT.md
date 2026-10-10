# 原版对齐实施与验收

## 2026-10-03 最新基线

- 实现状态：纯 UIKit 版本已接通 m0–m7 战役、最终关 `HEAVEN'S FURY`、天怒、指挥官复活、最终预言与制作人员名单；iPhone 17 Pro 首关保持约 60pt 格子和全窗触屏地图。
- 自动化验证：`AEAppTests` **127/127 通过**、0 失败、0 跳过，其中 XCTest 46 项、Swift Testing 81 项；结果包为 `.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.03_00-28-28--0400.xcresult`。
- Simulator 流程验证：音频替换前的完整 `AEAppUITests` **14/14 通过**、0 失败、0 跳过，结果包为 `.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_23-52-02--0400.xcresult`；替换后标题到环形主菜单聚焦回归 **1/1 通过**，结果包为 `.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.03_00-30-01--0400.xcresult`。设备均为专用 iPhone 17 Pro / iOS 26.5 Simulator。
- 本轮构建修复：SwiftPM 不再整体复制 `Resources/`，改为分别复制顶层资源目录，避免 Xcode 26.6 将 iOS resource bundle 内的 `Resources/` 误判为 macOS bundle 而导致 `codesign` 报 `bundle format unrecognized, invalid, or unsuitable`。运行时仍兼容有无外层 `Resources/` 的两种布局。
- 音乐播放：9 首原 MIDI 已使用 Apple General MIDI 音色离线渲染为 44.1 kHz、128 kbps AAC `.m4a`，总计约 5.5 MB；iOS 改用 `AVAudioPlayer`，保留原曲目切换及循环/单次语义，原 MIDI 留作权威来源。转换流程可由 `Tools/render_all_music.sh` 重复执行。
- 本轮测试修复：UI 自动化等待实际敌军演出阶段并与敌军回合完成同步，消除固定时序造成的误判；完整 UI 流程已以上述 14 项结果复验。
- 尚未验证：物理 iPhone、iOS on Mac、原 J2ME 视频逐帧叠图、全部兵种完整战斗状态机，以及预渲染音乐与原手机音源的主观听感。这些缺口不能由 Simulator 自动化通过替代。

下文按实施批次保留历史记录；若旧批次数字或“尚未实现”描述与本节冲突，以本节和较后的批次记录为准。

2026-10-02，用户最新约束：保留当前触屏大格子，其它表现尽量对齐原版，覆盖全部界面。纯 UIKit。

## 不变的触屏适配

- 保留当前格宽计算（手机首关通常 60pt），整窗地图、拖动与双指缩放。
- 触控区域至少 44pt；原版像素图片最近邻放大。
- 不再采用 VIDEO_COMPARISON.md 中曾建议的 44–48pt 默认格宽。

## 完整范围与状态

以下均属于当前目标。分批实现只是执行次序，不代表缩减范围。

- [ ] 行动阶段：范围展开 → 路线预览 → 确认移动 → 行动菜单 → 目标选择 → 行动结束；教程门槛、变灰及存档恢复。
- [ ] 地图表现：原光标、范围色、路线、单位待机/移动帧、指挥官头部、HP、尘土、剧情镜头。
- [ ] HUD 与通用组件：深蓝渐变、银色像素边框、原图标、文字可读性、完整长文本。
- [ ] 启动与主菜单：原展开/淡入/扫光，封面环形图标菜单、子菜单、切换动画。
- [ ] 叙事界面：插画前情、滚字、章节标题、目标横幅、头像对话、胜败画面。
- [ ] 资料与操作界面：单位详情、地形信息、暂停、设置、帮助、存读档、地图总览、招募/复活等原版入口。
- [ ] 战斗表现：竖条转场、两侧地形、兵种动画、弹道、减员、伤害、反击、HP 变化与返回地图。
- [ ] 回合流程与声音：逐单位 AI 演出、回合/收入提示、原 MIDI 音乐及设置。
- [ ] 完整内容：经济、建筑、全部关卡的规则/剧情/切换，不将首关视为完整游戏。

## 证据与验收

原始依据：所选 v2.5 JAR 阅读版、AEIIRM_src、VIDEO_COMPARISON.md 中带时间戳的视频帧。
每一批都记录实际修改、原源码依据、自动化和模拟器截图/录屏；测试通过不能替代画面和时序对比。
测试中必须覆盖：移动落格仍为彩色且不弹地形教程；结束行动才发布事件；选择/移动/攻击中恢复不重复结算；长文本全部可阅读；大格宽和整窗地图保持。

## 当前执行

### 2026-10-02 已落地

- 行动分阶段：范围展开后才触发教程 197；路径预览、确认移动、取消落格、结束行动分别处理。单位仅在结束行动后变灰，198/199/200 不再由移动落格提前触发。已落格时核心 API 同样拒绝结束整回合或操作另一单位。
- 全窗战场和原来的格宽公式保留；402pt 手机首关格宽仍为 60pt。原光标、范围图块、路径、两帧待机、指挥官头部、受伤 HP 与移动尘土已接入。
- 主菜单改成原封面、环形图标、PLAY 子菜单；修正标题合成坐标。启动页适配长屏，保留原 240×320 逻辑合成和标题波形算法；这是显示布局适配，不等于原手机整屏像素完全相同。
- 首关插画前情、滚字、章节标题、头像对话、长文本滚动；首关目标改回文本 129，原先错误引用了第二关文本 130。
- 游戏内行动菜单依据 `createInGameMiniMenu / initMiniListAuxDisplayable`，使用角落短条和小圆图标，不能与主菜单环形布局混同。单位详情使用原图标、数字、经验条、军衔、攻击/防御/移动及完整描述。
- INSTRUCTIONS 使用原版 20 页标题/文字 ID，带前后翻页。设置为 Music / Vibrate / Help / Fight Animation 四项，持久化并连接实际行为。
- 新增原 `.sprite` 读取：图集/独立帧、FrameDef、水平镜像、逻辑帧大小、Align、Translate、FrameSeq、FrameDelay。四阵营所有可战斗兵种的资源均已加载验证。
- 侧面战斗有八条竖幕、原地形、阵列、兵种帧、指挥官头部、挥砍/箭矢/冲击、减员、飘字、先攻/反击及每逻辑帧 2HP 的数字变化。目标选择使用原两行攻击/防御/军衔条。
- 玩家战斗与敌军逐单位移动/战斗使用持久化演出队列。伤害、经验、随机状态只结算一次；退出后重播记录，播放完成才放行教程/增援。关闭 Fight Animation 直接消费已结算结果。
- 原 MIDI 序列接入主菜单、前情、地图、战斗、胜败；使用平台 General MIDI 音色。震动跟随命中时刻；后台暂停。

### 实际验证

- `AEAppTests`：28 项通过、0 失败（2026-10-02 17:31 UTC），包含取消移动、旧存档兼容、战斗恢复不重扣/重抽、AI 队列与即时结算一致、剧情等待、标题原图合成、兵种帧解析、MIDI 准备。
- 战斗队列版本的 `AEAppUITests`：3 项通过、0 失败（17:20 UTC 开始），包含首关完整胜利、教程/大格宽/移动后恢复、战斗中进程重启和敌军控制交还。
- 行动条、详情、设置及全窗启动页版本的 UI 回归：4 项通过、0 失败（17:32 UTC 开始，实际执行 311 秒）。工具等待超时不代表测试失败，已从 xcresult 确认。结果：`test_sim_2026-10-02T17-32-17-353Z_pid76416_9f7d249b.xcresult`；截图：`.noindex/artifacts/original-alignment-current/`。
- 图片证据：`.noindex/artifacts/original-alignment-latest/` 为上批主菜单/地图/前情；`.noindex/artifacts/combat-frames/` 为本批侧面战斗渲染帧。完整测试结果位于 XcodeBuildMCP result-bundles，未进行物理 iPhone / iOS on Mac 验收。

### 尚未达到完整还原

- 战役 m0–m7 的关卡状态机、剧情切换和最终结局均已接通；自由/组队对战、城镇伪目标攻击以及全部兵种的逐帧战斗状态机仍未完成。经济、招募、建筑、复活、状态和天怒的实现见下方各批记录。
- 首关开场与增援已接入可恢复演出；场景顺序、坐标和 tick 延迟以源码为依据，整段录屏的逐帧对比尚未完成。回合/收入提示和胜败界面已接入，逐帧计时与全部关卡的衔接仍需继续验收。
- 战斗使用源码帧序列和布局，但控制时间线是当前 Swift 实现，尚未逐帧复刻全部 Java 状态机。尤其幽灵/魔像/龙、随机特效、弹道/烟尘和精确帧时序不能标为 100% 对齐。
- 战斗地形装饰使用稳定的显示层取样，不消费玩法随机数；原版部分绘制会消费共享 RNG，这一差异仍未收敛。
- HUD 仍有触屏常驻区域，主菜单已接原整数角度旋转与闪光，原文字字体、所有长宽比布局仍需对照验收。
- 原手动槽位、地图总览、经济、招募和指挥官复活均已接入；自由/组队对战相关入口仍未完成。
- MIDI 文件能成功准备不等于音色和音画同步已听音验收；平台音色与旧手机合成器会有差异。

上方完整范围不据此整项勾选。整体目标保持进行中。

### 2026-10-02 剧情演出与截图修正

- 修复 `small_circle.sprite` 的双帧图集误用：按 20×21 裁切普通/选中帧，行动条与单位资料恢复单个圆形底图。
- 战斗地形先以整数逻辑像素离屏合成，再最近邻放大，避免 402pt/@3x 下逐块绘制产生的接缝。新的战斗帧位于 `.noindex/artifacts/original-alignment-scenes/combat/`，已查看命中帧。
- `victory/gameover/game_complete` 改成一次播放，刷新 UI 不会重新启动已经结束的结果曲。仍未完成实机听音。
- 首关进入地图自动开始剧情，三支蓝军以 4px/tick 同时沿原路线入场；镜头先到 `(5,9)`，再以 `mapStepMax=2` 到 `(9,3)`。守卫在文本 221 后红色受击并等待 10 tick；文本 222 后火花/烟尘消失并等待 20 tick，再以 `mapStepMax=12` 回指挥官，等待 10 tick。
- 开场文本 224 后必须先确认原任务目标 129，再释放教程 196。任务目标确认状态也纳入存档。
- 第一波敌军全灭后，两次增援依次从 `(1,1)→(1,2)`、`(10,10)→(10,9)` 入场，镜头返回指挥官后才显示 225/226。保存中记录每步的显示前状态；真实单位和随机状态只更新一次。
- 依据：`MainDisplayable.java` 的 `moveMapOneStep`、m0 `case 2…13 / 22…29`、`startNormalPlay`、`completeMission`。
- 30 项单元/资源测试通过、0 失败（17:48 UTC），新增“开场逐步恢复”“增援逐个入场不重复生成”两项。结果：`test_sim_2026-10-02T17-48-57-999Z_pid76416_76fafdfd.xcresult`。
- 带剧情演出的首关完整通关回归已通过（17:49 UTC 开始，单项 272 秒）。自动开场的过渡按钮竞态已消除；开场/任务目标跨进程恢复和 60pt 格宽/行动恢复两项复验通过（17:56 UTC）。存档界面及弹窗布局的回归见下。

### 手动存档与地图总览

- 按 v2.5 `MAX_SAVES = 10`（阅读版 `GameController` 同样为 10）实现 `SLOT 1/10…10/10` 横向选择，显示模式、关卡名和原 `currentTurn + 1` 计数。原槽位没有内容时显示文本 79，覆盖已有内容必须确认文本 88。
- 十个手动文件与自动存档分开保存，原子写入。NEW GAME 仅清理自动存档；手动存档保留。LOAD GAME 提供手动槽位及单独的 `CONTINUE AUTOSAVE`，加载失败保留原文件并显示错误。
- MAP 使用 `stiles0.sprite` 的 10px 原地形与 `mini_icons.sprite` 的阵营标记，复用 terrain 的 `minimapFrame`。轻点总览可返回大地图并定位。
- 短文本弹窗按内容高度布局，避免任务目标只有两行文字却占据大半屏；长内容仍可滚动。剧情时隐藏常驻顶栏，地图不再为顶栏留空。
- 单元测试新增独立槽位、自动/手动隔离、新游戏保留手动、非法槽号和损坏文件不被改写：31 项通过、0 失败（17:56 UTC）。
- 覆盖确认层已增加模态可访问性隔离和独立的取消标识。18:00 UTC 的 31 项单元/资源测试 + 3 项聚焦 UI 测试全部通过（34/34）；涵盖空/已有槽位保存、取消覆盖、自动存档到第 2 回合后从手动槽位读回第 1 回合、进程重启、地图点击定位、目标确认与开场恢复。结果：`test_sim_2026-10-02T18-00-12-568Z_pid76416_d6763bf3.xcresult`。

### 当前可视证据

均为专用 iPhone 17 Pro / iOS 26.5 模拟器的真实 UI 或 UIKit 渲染附件，没有使用概念效果图。

| 场景 | 当前截图 |
| --- | --- |
| 守卫对话 / 镜头定位 | [opening-guard.png](../.noindex/artifacts/original-alignment-scenes/ui-final/opening-guard.png) |
| 返回指挥官 | [opening-galamar.png](../.noindex/artifacts/original-alignment-scenes/ui-final/opening-galamar.png) |
| 原任务目标 | [objective.png](../.noindex/artifacts/original-alignment-scenes/ui-final/objective.png) |
| 60pt 格子 / 行动条 | [action-menu.png](../.noindex/artifacts/original-alignment-scenes/ui-final/action-menu.png) |
| 原单位资料 | [unit-details.png](../.noindex/artifacts/original-alignment-scenes/ui-final/unit-details.png) |
| 十个手动存档槽 | [manual-slots.png](../.noindex/artifacts/original-alignment-scenes/ui-final/manual-slots.png) |
| 地图总览 | [map-overview.png](../.noindex/artifacts/original-alignment-scenes/ui-final/map-overview.png) |

地图总览的三个黄色问号来自原包 `stiles0_07.png`，分别由 `tiles0.prop` 的 TileDef 31/32 映射。源码 AuxDisplayable mode 8 也直接使用此映射；当前保留该素材，并非加载失败后生成的占位图。

### 最终回归记录

- 首关通关：`test_sim_2026-10-02T18-03-17-244Z_pid76416_34813e74.xcresult`，1 项通过、0 失败，约 284 秒。覆盖新 LOAD GAME 入口恢复已结算战斗、首次战斗自然播完、增援恢复、结尾恢复及最终胜利。
- 与 18:00 UTC 的 34 项结果合计，31 项单元/资源测试及 4 项相关 UI 场景均已有通过记录。最后的短弹窗/独立标题栏/目标居中布局聚焦复验 2/2 通过（18:08 UTC），结果为 `test_sim_2026-10-02T18-08-22-931Z_pid76416_fc830719.xcresult`，对应截图已更新至 `ui-final/`。
- 该批次当时仍未进行物理设备、iOS on Mac、全兵种逐帧和 MIDI 听音验收，后续关卡、经济/建筑、招募/复活也尚未完成；这些玩法后来已经接入，当前仍缺的验收项目以本文顶部最新基线为准。


### 2026-10-02 主菜单、回合结算与结果画面

- 主菜单入场按 `AuxDisplayable:637–705,1212–1290` 扩大半径并旋转一周减速；改选按 `856–939` 的整数角度余量逐 tick 减半。使用原 360 项整数正弦表，保持触屏布局尺寸，移除直线插值。Swift 位移优先级与 Java 不同，坐标乘法必须先加括号再 `>> 10`；新增固定角度/半径轨迹和反向改选连续性测试。
- 菜单图标直接读取 `menu_icons.sprite`：原 frame 0/1 本来就指向同一幅剑图，原映射没有错位。大圆保留 32×33 宽高比；三颗 `small_spark` 使用原五帧映射、末帧重新定位，旋转时隐藏。闪光位置仍用显示层随机源，未声称复用 Java 共享 RNG。
- `sub_cff:6365–6460` 的回合结算：每座己方城镇 +30、城堡 +50；切换时所有存活单位恢复可行动状态。新可选存档字段 `moneyByFraction` 兼容旧首关存档（原 m0 初始金币为 0）。资金在生成演出记录时只结算一次，读档重播不再加钱。
- `gameState 8:4060–4122` 的治疗：轮到该阵营时，在 Type 7 公共地形或己方建筑上的存活单位最多 +20 HP，上限 100。中立/敌方建筑不治疗，死亡单位不复活。此处目前限已接入的蓝红对战；未来组队对战需要扩展到同队建筑。
- 原 `NEW TURN! / INCOME: %U` 两行横幅、阵营渐变、敌军收入 `?` 和玩家实际收入，按 30 个 66ms tick 播放（源码 1500ms 的逻辑计数每 tick +50）。横幅不预留对话框底部空间；短地图在完整视口居中，格宽公式保持。
- 治疗飘字用原 `lchars` 的 `+` 帧及 `Sprite mode 5` 反弹公式（初速度 -4，每 tick +1，每次回弹速度减半，落地停留 8 tick，共 25 tick）。当前作为可恢复队列独立播放，原版飘字与后续操作并行的细节尚未合并。
- 胜利使用原文 72 `MISSION COMPLETE`，居中面板显示 60 tick；失败等待 20 tick 后八条竖幕关闭，显示原 `gameover.png` 和原文 57 `GAME OVER`，淡入 16 tick，计时结束或点击返回。结果从已提交的胜败状态恢复，不重新扣血。
- 本条记录当时首关胜利后仍返回菜单；后续批次已经接通 m0→m1→m2→m3→m4→m5→m6→m7，并在 m7 胜利后直接进入原 `intro5`、最终预言和制作人员名单。失败阶段的渲染与输入已覆盖，整段逐帧录像验收仍未完成。
- `bg_story` 按原 `startPlayer_(1,1)` 改为一次播放；其它已接入地图/战斗背景曲循环，结果曲一次。未做物理设备听音验证。

本批初步验证：18:27 UTC 的 `test_sim_2026-10-02T18-27-18-692Z_pid76416_40dc2619.xcresult`，36 项单元/资源与 2 项 UI 测试共 38 项通过、0 失败。覆盖菜单改选与返回、红军回合中断读档、蓝军收入显示、钱/HP/RNG 不重复结算、原失败图像及 402pt/768pt 布局。后续新增边界与完整通关回归的结果另记。

图片目录：`.noindex/artifacts/original-alignment-turns/`。菜单/回合为模拟器截图；胜败 402pt/768pt 附件为 UIKit 渲染测试，不等同于真实 iPad 或物理设备验收。


本批后续验证：

- 最终生产代码的 37 项单元/资源测试已于 18:33 UTC 全部通过（`test_sim_2026-10-02T18-33-59-764Z_pid76416_e56d5c54.xcresult` 中的 AEAppTests）。新增城堡 +50、村庄 +30、神庙回血封顶、中立/敌方/废墟不回血、死亡单位保持死亡、全阵营行动标记重置。测试夹具按原 `tiles0.prop` 修正：神庙 34 为 Type 7，废墟 27 为 Type 8。
- 18:31 UTC 的三项 UI 回归均通过：红蓝回合及中断恢复、60pt 大格子/移动后读档、设置与 20 页帮助（`test_sim_2026-10-02T18-31-03-162Z_pid76416_f4c700a7.xcresult`）。该结果包的一项单元测试夹具失败已由上述 18:33 UTC 复验关闭。
- 完整通关回归通过：`test_sim_2026-10-02T18-36-26-930Z_pid76416_a4ff95ce.xcresult`，1 项、0 失败，实际 310.661 秒，最终出现原 `MISSION COMPLETE` 画面。工具 300 秒超时后未重跑，读取原日志及结果包确认成功。覆盖自然战斗播放、战斗中重启、增援与结尾恢复、原胜利横幅。
- 测试辅助方法先检查按钮存在，再读取 enabled，避免在教程盖住行动按钮时误报；结果面板出现后不再查询被模态界面遮住的地图。

| 本批场景 | 最终截图 |
| --- | --- |
| 主菜单 | [main-menu.png](../.noindex/artifacts/original-alignment-turns/ui-final/main-menu.png) |
| 沿圆周改选 ONLINE | [menu-online.png](../.noindex/artifacts/original-alignment-turns/ui-final/menu-online.png) |
| 敌军回合 / 隐藏收入 | [enemy-turn.png](../.noindex/artifacts/original-alignment-turns/ui-final/enemy-turn.png) |
| 玩家回合 / 实际收入 60 | [blue-turn.png](../.noindex/artifacts/original-alignment-turns/ui-final/blue-turn.png) |
| 实际通关（收起操作栏前） | [mission-complete.png](../.noindex/artifacts/original-alignment-turns/ui-final/mission-complete.png) |
| 最终胜利布局 / UIKit 渲染 | [victory-clean-render.png](../.noindex/artifacts/original-alignment-turns/ui-final/victory-clean-render.png) |
| GAME OVER / UIKit 渲染 | [game-over-render.png](../.noindex/artifacts/original-alignment-turns/ui-final/game-over-render.png) |

后续仍需处理：回合切换时恢复原阵营光标/镜头位置、飘字与操作并行、自由/组队对战、所有兵种的完整战斗状态机。保持整体目标进行中。

本批最终状态：37 项单元/资源测试全部通过，4 项受影响的 UI 流程（完整通关、回合恢复、大格子与行动恢复、设置/帮助）均通过；另有主菜单旋转/返回专项通过记录。已查看最新回合居中、主菜单与结果图片。未进行物理设备、iOS on Mac 或全部界面的逐帧录像验收。

最后的结果界面布局复验：胜败时收起常驻顶栏、资金条与 END TURN 操作，地图使用完整视口。`test_sim_2026-10-02T18-44-04-043Z_pid76416_a71b0d28.xcresult`，3 项聚焦测试通过（包含新增的胜利存档恢复/操作栏隐藏/返回菜单，及失败与结果渲染回归）。累计 38 项不同的单元/资源/UIKit 组件测试已有通过记录。最后布局变更未重新跑整关，完整通关证据为 18:36 UTC 结果；最终布局另有 UIKit 渲染附件。

### 2026-10-02 建筑操作与玩家招募/复活

已实现并接入纯 UIKit；触屏格宽公式保持，专用手机模拟器实测 60pt。

- `MainDisplayable.getInGameMenuOptionsAvailabe:3703`、`sub_d41/sub_d5e:6463–6471`：只有对应属性的兵种能修复/占领。士兵和指挥官可处理村庄，城堡要求 bit 4；己方建筑不再提供占领。落格不会自动占领。修复把废墟改为中立村庄 37，不扣金币；修复与随后占领分别消耗一次行动。
- `MainDisplayable:2827–2850`：先提交建筑变化，再显示原文 73 `OCCUPIED!` / 74 `REPAIRED!`。消息显示 20 个 66ms tick（原逻辑 1000ms、每 tick +50），同时保存行动结束状态。新增可选 `tileOverrides`；旧存档缺省仍使用原地图，非法位置/未知地形拒绝恢复。
- 地图归属同步到收入、治疗、地形查询、AI 当前地图及地图总览。演出记录新增可选 `tileOverridesBefore`，敌方整回合预结算后，前面的动画仍显示当时的归属；读档不会重复修复/占领。AI 在当前所选落点没有攻击目标时执行占领或修复，依据 `MainDisplayable:6780–6825`。
- `Unit.listAvailableUnits:874`、`MainDisplayable.canBuyUnit:6703–6726`：死亡指挥官在选择器前方，其后按兵种编号排列已解锁单位；检查资金、单位上限（战役 100，不计骷髅）和可达空落点。空城堡可原地布置；城堡被占时需有空格可走，不能因购买重叠而直接结束行动。首关仅开放士兵；存档可记录脚本调整后的解锁上限。
- `buyUnit:2995`、`prepareUnitMovement:1320`、购买确认 `1855`、行动菜单取消 `1638`：确认立即扣费并创建单位，然后必须布置。购买前可取消；购买后不能放弃/退款，也不能切换其它单位或结束回合。已落格时可从行动菜单取消，回到城堡重新选择落点，费用保持已扣。`recruitingUnitID` 与选择状态一起存档，布置中及落格后恢复均覆盖。
- `MainDisplayable:4282`：指挥官死亡后复活费 +200，最高 1000；初始 200、首次复活 400。复活保留同一单位 ID、等级和经验，HP 恢复 100。旧死亡指挥官缺少费用字段时按首次 400 兼容。当前 m0 的主指挥官死亡仍按关卡条件失败；通用复活能力不改变这一失败条件。
- `AuxDisplayable mode 2/10/3:1527,1646`：新增全窗招募页，上方原单位图、金币价格、攻防移动属性，中部完整原文说明，下方原 `big_circle_00/01` 圆环横向选择及 `side_arrow`，支持触摸和左右循环。超出资金的单位在选择器中变灰；确认禁用。资料页与招募页共用属性绘制，招募页不显示 HP/经验条。
- 原 `action_icons.sprite` 的 frame 1/2 共享同一建筑图片，实际图片偏移不能直接使用逻辑帧号。占领/修复共用该图片；攻击及结束行动保持原映射。投石车移动后不能攻击。已行动单位不提供 END MOVE，仍可查看资料，在己方城堡上仍可 BUY。

验证与截图：

- `test_sim_2026-10-02T18-58-51-198Z_pid76416_9da774d4.xcresult`：47 项单元/资源/组件测试通过，包括新增 9 项建筑及招募规则测试。
- `test_sim_2026-10-02T19-03-51-214Z_pid76416_ae5f1029.xcresult`：AEAppTests 全部 50 项通过，包含 3 项 UIKit 交互/布局测试。覆盖购买前取消、确认扣费、重建控制器后布置/结束、占领信息自动退出、地图总览、402pt/768pt 布局及全部已解锁兵种切换。
- 上述结果包的一个旧 UI 测试仍读取已移除的 END MOVE 按钮。已修正为先判断存在再检查可用；`test_sim_2026-10-02T19-05-31-885Z_pid76416_5e3c865b.xcresult` 跨进程恢复专项通过（1/1，约 65 秒）。
- `test_sim_2026-10-02T19-01-04-201Z_pid76416_253958e2.xcresult` 的红蓝回合及中断恢复 UI 测试通过；其中旧 END MOVE 断言失败已由前述复验关闭。
- 截图审查发现并修正了消息标签的 autoresizing 偏移，以及圆环资源属于拆分图片而非单张图集的问题。最终占领横幅和招募页已再次查看，并加了标签边界、圆环存在断言。
- 图片保存在 `.noindex/artifacts/original-alignment-buildings/ui-final/`：`recruitment.png`、`deployment.png`、`occupied.png`、`minimap.png`、`dragon-phone.png`、`dragon-tablet.png`。它们来自模拟器上的 UIKit 测试夹具/渲染；768pt 图不是实际 iPad 验收。首关原地图没有城堡，测试通过存档覆盖地形构造城堡场景，没有把城堡添加到原始首关。
- 本批未重复整关通关；上一批 18:36 UTC 的通关记录仍是整关证据。没有实机或 iOS on Mac 验收。

完整目标继续进行，本批之后，AI 城堡选择、购买兵种与建筑战略分配继续在下节接入；组队归属尚未还原；后续关卡运行中的解锁脚本、剧情接续和自由对战仍待实现。招募选择器的原滚动时序/闪光尚未逐帧对齐；此次没有宣称全局 RNG、所有兵种动画或全部菜单已 100% 一致。AEIIRM 的 `startPlayer(9,1)` 超出该源码九个 MIDI 条目的数组，暂未臆造占领音效，需继续对照 v2.5 JAR。


### 2026-10-02 AI 招募、建筑战略与逐步演出

本批仍保持 UIKit、整窗地图和原格宽公式。402pt 手机上的首关格宽为 60pt。

- AI 先处理已有单位，再选择城堡购买；购买一支就立即安排一支，随后继续。城堡参考敌方可占城堡单位的曼哈顿距离，缺少该类活单位时参考其它方城堡；平手按建筑 x 外层、y 内层顺序保留首项。
- 购买顺序：复活登记的死亡指挥官 → 士兵补到两支 → 弓箭手补到两支 → 满足资金/兵力条件后从其它可买兵种中抽一个。保留资金、解锁、可达空格和兵力上限检查，骷髅不占购买上限。地面部队无法离开城堡时，仍按飞行兵种的移动能力判断是否可买。
- 建筑战略加入支援记账与预约：先考虑 HP<50 的恢复位置，再考虑占领目标，再按原威胁/距离评分分配；士兵、各阵营主指挥官、建筑的候选顺序和严格大于的平手规则保持源码顺序。主指挥官使用地图最后登记的一位。
- 同方未行动单位挡住最佳落点时，先安排阻挡者，再重算原单位；新兵不会永久叠放在城堡上的友军上。
- AI 演出保存为：镜头到位后等 2 tick → 范围 4 tick → 路线 4 tick → 12 源像素/tick 移动 → 攻击范围与瞄准 10 tick → 战斗。原逻辑 50ms 以当前 66ms 渲染 tick 播放。玩家移动仍为 6 源像素/tick。
- 普通光标使用原帧 0/1，攻击光标使用帧 2/3/4，每 4 tick 换帧；恢复帧 5 路线终点标记，路线改为原 8 源像素宽的矩形拼接。当前单位绘制于范围/路线之上。
- 演出队列保存范围、路线、移动速度及行动前地图；存档重放不再次购买、扣费、抽样或结算战斗。旧存档缺少这些可选字段仍可读取。

源码依据：`reference/inputs/AEIIRM_src/src/aeii/MainDisplayable.java` 的 `sub_1022 / sub_1043 / sub_109f / sub_10cb`，与所选 v2.5 JAR 阅读版 `GameController.method_191 / method_192 / method_193` 对照。光标和路线依据 `CURSOR_FRAME_SEQUENCES`、`sprCursor.nextFrame`、地图绘制中的 `var_3685=8`。

保留的边界及明确差异：

- 原版多指挥官复活循环没有 `break`，购买只设 HP/坐标，随后规划移动才离开尸体状态。按源码建模了“可能付多人的钱，只立即安排最后一位”的行为；此前付费的其它指挥官以可选 `revivalAwaitingMovement` 保留状态。该边界已经有 Swift 回归，但尚无原 Java 运行轨迹对照。
- 随机购买仍只抽一次 Java `nextInt` 再取绝对值余数；对 `Int.MIN_VALUE` 先拓宽再取绝对值，避免复刻 Java 的负索引崩溃。这一极端输入不宣称行为完全相同。
- 后续批次已接入尸体生命周期、召唤、状态加成及 m7 天怒。组队、城镇伪目标攻击、AI 全分支和全局 RNG 仍不能标为 100% 还原。
- 构建发现 Xcode 在单独 Build 与 Test 间切换自动静态/动态包产物，旧顶层 `AECore.swiftmodule` 会遮蔽新 framework。仅清理确认过时的 AECore/AEContent 模块，继续复用 `.noindex/DerivedData`；工程仍使用原自动链接配置。

本批验证：

- 最终 `AEAppTests` **65/65 通过**（19:36 UTC）。包含 13 项 AI 规则/恢复测试、2 项实际 UIKit AI 演出测试，以及此前的招募、建筑、经济、剧情、战斗、菜单和存档回归。结果包 `test_sim_2026-10-02T19-36-41-973Z_pid76416_0e82ffb0.xcresult`。
- `AEAppUITests` **2/2 通过**，实际执行 416.5 秒：敌军回合后交还控制、首关完整通关（含增援/结尾处进程重启）。结果包 `test_sim_2026-10-02T19-28-51-507Z_pid76416_0555bb42.xcresult`。该次通关在最后的路线/当前单位层级调整前构建；层级调整由上述最终 UIKit 测试及截图复验。
- 已查看实际 UIKit 截图：`.noindex/artifacts/original-alignment-ai/ui-final/range.png`、`route.png`、`targeting.png`，附原测试时间与设备信息的 `manifest.json`。这些是专用 iPhone 模拟器上的 UIKit 测试夹具，城堡通过测试快照覆盖构造，没有修改首关原始地图。
- 上述结果包均位于 `/Users/chenrenwei/Library/Developer/XcodeBuildMCP/workspaces/AncientEmpiresII-b36230553e3f/result-bundles/`。

尚未进行原 Java 同种子行动轨迹、全部兵种逐帧、物理 iPhone 和 iOS on Mac 验收。后续批次已完成 m5–m7 剧情与切换；完整目标继续进行，剩余为自由/组队对战和未逐帧验收的界面。


### 2026-10-02 尸体、召唤与状态效果

本批继续使用纯 UIKit、原图片和最近邻采样；没有改变格宽计算、全窗地图或触屏拖动缩放。

- 普通战斗死亡先播放 6 tick 火花/镜头等待，再显示原 `tombstone` 和向上烟雾；墓碑以玩家切换次数计数，3 次后移出地图。骷髅/水晶死亡不留墓碑；指挥官移至 `(−10,−10)`、清除状态并保留身份/复活费用。移动经过、落格和取消均不删除墓碑，结束行动才删除落格首个墓碑。
- 女术士行动条增加原 `RAISE` 图标，显示完整射程及普通光标，可切换墓碑目标、取消或确认。四角 spark 按原位移与帧序列播放；8 tick 后显示满血、已行动的灰色骷髅，火花继续完成三次循环。可对已有活单位的墓碑格施法，不扣钱、不检查购买上限。
- 中毒为攻击/防御各 −10，无持续扣血、无移动惩罚；到施毒行动方再次开始回合才移除。仅主动攻击的凶狼施毒，防守狼反击不施毒；主动狼被反击杀死仍施毒。幽灵结束行动时给 1…2 格内同方单位 +10 攻击，重复不叠加，受益方回合开始清除；反击死亡的幽灵仍触发。
- 原 `status` 图标接入单位属性和战斗头，单位描述加入原状态说明；中毒使用原弹跳图标，光环产生 spark。粒子独立于场景刷新，允许效果尾段与后续操作同时存在。
- 真实伤害、AI 评分、女术士召唤候选及演出均接入状态。升级移到先攻/反击全部结束后，存活者单次最多升一级。AI 新召唤骷髅不在同一轮再次行动。
- 快照新增可选墓碑寿命、移出标志、状态与施毒行动方字段，旧存档仍可解码；没有年龄的旧死亡对象不臆测墓碑寿命。单位身份记录与地图集合分开，后续剧情仍能引用已死亡对象。死亡/召唤队列恢复不再扣血、抽随机数或生成第二支骷髅。

依据：`Unit.addEffect/updateEffects/getUnitsWithinRange/finishMove`，`MainDisplayable.finishAttack`、死亡分支、`sub_726`、轮转 `sub_cff`、状态资料文本 98–100，并与所选 v2.5 阅读版死亡分支核对。

当前边界：上述流程已实现双阵营，后续批次也已完成 m0–m7 和天怒；组队对战仍未完成。粒子尾段尚未单独持久化，应用重启会重放尚未消费的场景，已经消费的效果尾段不会恢复。本批不代表原 Java 全局 RNG、全部动画或全部界面达到 100% 一致。


本批验证与最终图片：

- `test_sim_2026-10-02T19-55-14-373Z_pid76416_1b314c05.xcresult`：**82/82 通过**（80 项单元/资源/UIKit 组件 + 2 项端到端 UI），0 失败、0 跳过。端到端部分实际 416.747 秒，包含首关完整通关、战斗/增援/结尾处重启、红蓝回合交还控制。工具等待超时后读取同一次日志及 xcresult 确认成功，没有重复启动。
- 新增规则测试共 12 项，覆盖死亡类型、轮转寿命、移动取消、毒的真实伤害与清除时机、光环/重叠单位/死亡幽灵、升级反击顺序、召唤成本/上限/重叠/恢复、AI 召唤队列和旧字段兼容。
- 最后明确了粒子每 66ms 的关键帧边界，保证末帧也占完整一 tick；`test_sim_2026-10-02T20-03-42-819Z_pid76416_88a2ce2f.xcresult` 的 3 项相关 UIKit 测试再次通过。20:04 UTC 的 `test_sim_2026-10-02T20-04-50-965Z_pid76416_0846d775.xcresult` 两项复验增加特效自然消失后的截图及状态不变检查，同样通过。完整通关在这次纯显示时序调整前验证，最后变化由组件实测覆盖。
- 测试初期的一项生命周期断言在 `viewDidAppear` 前执行，改为等待实际呈现后通过；截图改为等待屏幕刷新，独立属性组件采用 layer 渲染，清理了黑色的早期附件。
- 最终图片位于 `.noindex/artifacts/original-alignment-status/ui-final/`，附每幅图的时间、设备及结果包记录。来自专用 iPhone 17 Pro / iOS 26.5 的实际 UIKit 流程；768pt 属性图是布局测试，不等同于 iPad 或 iOS on Mac 验收。场景用测试快照构造女术士/墓碑，没有改动原始首关地图。

| 场景 | 截图 |
| --- | --- |
| RAISE 行动条与墓碑 | [raise-menu.png](../.noindex/artifacts/original-alignment-status/ui-final/raise-menu.png) |
| 召唤 8 tick 后仍在播放的火花 | [summoning.png](../.noindex/artifacts/original-alignment-status/ui-final/summoning.png) |
| 火花结束后的灰色骷髅 | [skeleton.png](../.noindex/artifacts/original-alignment-status/ui-final/skeleton.png) |
| 墓碑与上升烟雾 | [death.png](../.noindex/artifacts/original-alignment-status/ui-final/death.png) |
| 状态属性与战斗头 / 402pt | [status-phone.png](../.noindex/artifacts/original-alignment-status/ui-final/status-phone.png) |
| 状态属性与战斗头 / 768pt | [status-tablet.png](../.noindex/artifacts/original-alignment-status/ui-final/status-tablet.png) |

整体目标保持进行中：m6–m7 及最终结局已在后续批次接通；自由/组队对战、建筑攻击、全部兵种战斗状态机、所有界面的逐帧对比与物理设备/iOS on Mac 验收继续保留在范围内。

### 2026-10-02 第二关 TO THE RESCUE 与战役衔接

- m1 按 `MainDisplayable.java:8517–8671` 实现 `scriptState 0…27`：原金币 300/50、兵种解锁至弓箭手、Valadorn 第三套指挥官身份、文本 231…240、守卫消失、建筑/复活教程、清空红军与红城堡后的胜利条件。
- 剧情演出保存状态、命名单位引用、头像、对话后队列和触发标记；应用重启不会重复创建护送队列、守卫或扣结算。三人跟随链按 0/12/24 tick 延迟播放，路线仍使用原版先横后纵规则。
- 指挥官的名称、地图头部、资料页、招募/复活和战斗头像统一使用 `commanderVariant`，不再由阵营推断 Galamar 或 Valadorn。源码审计没有发现剩余的阵营写死角色展示。
- 战役进度独立保存在 `campaign-progress.json`。m0 胜利解锁并自动进入 m1；SELECT LEVEL 只显示已解锁且已经实现的关卡。NEW GAME 清自动存档，不删除手动档或解锁进度。
- m1 使用地图名 `The Temple of Wisdom` 过渡，任务标题/正文分别读取文本 122/130；m0 保留 `intro2`，m3 的 `intro3` 已接入，m7 的 `intro4` 入口继续保留。

验证：工程内 `.noindex/DerivedData` 的 `Test-AncientEmpiresII-2026.10.02_17-52-44--0400.xcresult` 为 `AEAppTests` **87/87 通过**；`Test-AncientEmpiresII-2026.10.02_17-57-31--0400.xcresult` 为 `AEAppUITests` **8/8 通过**。m1 单项入口结果为 `Test-AncientEmpiresII-2026.10.02_17-55-19--0400.xcresult`。截图位于 `.noindex/artifacts/original-alignment-m1-run2/` 与 `.noindex/artifacts/original-alignment-m1-e2e/`。

仍有边界：AI 当前整回合预结算，指挥官死亡后的复活教程会在当前演出队列结束后出现；m1 尚无整关真实通关自动操作。地图名过渡与开场演出已有 UIKit 实测，仍未与原 J2ME 录屏逐帧重叠比较。

### 2026-10-02 第三关 PATH OF SHADOWS 与长地图触屏适配

- m2 按 `MainDisplayable.java:7692–7710` 与 `8678–8840` 实现 `scriptState 0…22`：双方初始金币 0、兵种上限 0、任务文本 131，Galamar 死亡失败，红军全灭后等待 10 tick 并胜利。
- 开场四人从南侧按原路线入场：Galamar `(8,16)→(8,14)`、士兵 `(8,17)→(8,15)→(7,14)`、弓箭手 `(8,18)→(8,15)→(7,15)`、女术士 `(8,19)→(8,15)`。完整演出为 38 tick；镜头固定在源码初始化的 Galamar 起点 `(8,16)`，不跟随至路线终点。路线仍使用 `Unit.plotRouteEx(false)` 的先横后纵规则。
- 对话 241…248、头像和延迟按源码顺序接入：Captain 使用头像 5，Galamar 使用头像 0，文本 246 无头像，248 使用中央定时消息。教程按 210 狼毒、211 墓碑召唤、209 水元素的原条件出现，任务标题为 `PATH OF SHADOWS`。
- 任一已完成行动的蓝军进入 `x <= 4 || y <= 10` 后，依次生成左侧两狼、右侧一狼、北侧两狼与一只 Wisp。三只 Elemental 先以红军和原 spark 出现，完成对话后删除并在相同位置重建为蓝军。新增 `.appearance` 与 `.message` 演出；单位、阵营转换和待播场景均写入快照，进程恢复不会重复生成。
- 该批次将战役入口提升到 m4：当时 m1 胜利进入 m2，随后可自动衔接 m3、m4，SELECT LEVEL 可选择 m0–m4。后续 m5 已实现，现在 m4 胜利会自动进入 m5，菜单实现上限也已提升到 m5。
- 402pt 手机上的 m2 继续使用 60pt 格子；10×17 长地图通过全窗滚动展示，不缩成旧手机大小。已检查目标面板、Galamar 资料、地图局部、章节标题和首句对话，未见文字截断或画布缩成小块。

源码与验证证据：

- m2 核心状态机 4 项与 UIKit 组件 2 项合并定向测试 **6/6 通过**：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_18-30-01--0400.xcresult`。
- 真实入口专项 **1/1 通过**：从 SELECT LEVEL 进入 `Forest of Mists`，等待 38 tick 入场后显示文本 241；结果为 `Test-AncientEmpiresII-2026.10.02_18-30-38--0400.xcresult`。
- 最终全量 `AEAppTests` **93/93 通过**（34 项 XCTest + 59 项 Swift Testing）：`Test-AncientEmpiresII-2026.10.02_18-32-02--0400.xcresult`。
- 最终全量 `AEAppUITests` **9/9 通过**，0 失败，实际 685.561 秒；包含首关完整通关、多阶段进程恢复、m1 与 m2 真实入口：`Test-AncientEmpiresII-2026.10.02_18-32-45--0400.xcresult`。
- UIKit 组件附件位于 `.noindex/artifacts/original-alignment-m2-components/`，真实入口附件位于 `.noindex/artifacts/original-alignment-m2-entry/`；两处均保留 `manifest.json` 对应原测试名称。

仍有边界：m2 尚无整关真实通关自动操作，开场与原 J2ME 录屏尚未逐帧叠加；后续批次已完成 m6–m7 和天怒，自由/组队对战、全部兵种完整战斗状态机、物理 iPhone 与 iOS on Mac 验收继续保留在范围内。

### 2026-10-02 第四关 REINFORCEMENTS 与村庄摧毁演出

- m3 按 `MainDisplayable.java:7712–7732` 与 `8841–8968` 实现：双方初始金币 400、兵种上限 7、任务文本 132。战役入口使用原 `intro3` 插画和文本 218，章节标题为 `REINFORCEMENTS`。
- Galamar、士兵、水元素和女术士从地图左侧按原路线同时入场，目标点分别为 `(3,3)`、`(3,4)`、`(4,4)`、`(2,4)`；速度 4px/tick，演出记录为 50 tick。四个稳定单位引用、待播路线和状态均进入快照，恢复不会重复创建增援。
- 入场后黑幕淡入 20 tick，按头像显示文本 249、250、251；黑幕期间把四人移至源码指定坐标，再用 5 tick 淡出。镜头移至 `(13,10)` 后，敌方投石车以 2px/tick 从 `(10,10)` 移至 `(6,10)`。
- 村庄 `(4,9)` 的摧毁演出为 35 tick，第 21 tick 从地形 39 切换为废墟 27。显示文本 252、253、教程 213 和任务目标后才开放操作；演出前地图、已提交地形和黑幕状态均可从 JSON 快照恢复。
- 胜利条件按源码保持为“红军活单位清空且红方城堡清空”，随后等待 15 tick，显示文本 254、255 并完成任务。m2 胜利进入 m3，m3 胜利自动进入 m4。
- 402pt 手机上仍使用 60pt 格子，地图维持全窗滚动。目标面板、黑幕对话、插画章节和真实入口附件分别位于 `.noindex/artifacts/original-alignment-m3-components/` 与 `.noindex/artifacts/original-alignment-m3-entry/`。

验证：m3 的 3 项状态机测试与 2 项 UIKit 组件测试包含在最终 `AEAppTests` **103/103 通过**结果中；真实 `intro3`、`REINFORCEMENTS` 章节和文本 249 入口包含在最终 `AEAppUITests` **11/11 通过**结果中。m3 尚未执行整关自动通关，开场、投石车和村庄摧毁也未与原 J2ME 录像逐帧叠加。

### 2026-10-02 第五关 ESCORT DUTY 与四波伏兵

- m4 按 `MainDisplayable.java:7737–7759` 与 `8970–9196` 实现：双方初始金币 0、兵种上限 0、任务文本 133。地图名过场为 `Pathway to Thorin`，目标标题为 `ESCORT DUTY`。
- Galamar 与 Crystal 使用稳定单位引用。六名蓝军先移到地图北侧的源码坐标，再以 4px/tick 同时返回 `(11,2)`、`(10,1)`、`(11,1)`、`(12,1)`、`(11,0)`、`(12,0)`；演出记录为 50 tick。随后依次显示文本 256、257 与原头像，确认任务目标后开放操作。
- 第一波在任一已行动蓝军进入 `x <= 8` 时触发：镜头到 `(4,4)`，两只魔像和一名弓箭手以 0/10/20 tick 交错进入 `(4,1)`、`(5,2)`、`(4,3)`，随后显示文本 258。
- 第二波在任一已行动蓝军进入 `y >= 7` 时触发：镜头到 `(6,10)`，一名弓箭手和两只狼以 0/10/25 tick 交错进入 `(5,10)`、`(7,8)`、`(7,9)`。
- 第三波在任一已行动蓝军进入 `x >= 8 && y >= 6` 时触发：镜头到 `(12,5)`，狼、蜥蜴人与狼以 0/15/25 tick 交错进入 `(12,7)`、`(12,6)`、`(12,5)`。
- 第四波在任一已行动蓝军进入 `x >= 15 && y >= 8` 时触发：镜头到 `(18,8)`，狼、蜥蜴人、狼和弓箭手以 0/10/20/30 tick 交错进入 `(16,10)`、`(17,10)`、`(18,10)`、`(18,9)`，随后显示文本 259。
- Crystal 阵亡会立即失败。源码最终条件是逻辑“或”：红军全灭，或 Crystal 已结束行动并进入 `x >= 15 && y >= 11`；满足任一条件后等待 10 tick 并胜利。后续 m5 实现已将 m4 胜利衔接为自动进入 m5。
- 四波伏兵的兵种、触发区域、出生点、终点、交错延迟和最终状态已对齐。当前 Swift 把每波组合为一个可恢复的 `.arrival` 演出，整波路线播放完毕后才恢复输入；原 Java 某些状态会在最后一名单位仍移动时恢复操作，这一移动尾段并行时序尚未逐 tick 对齐。

验证与可视证据：

- m4 的 4 项状态机测试与 1 项 UIKit 组件测试 **5/5 通过**：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_19-20-54--0400.xcresult`。
- 最终完整 `AEAppTests` **103/103 通过**，0 失败、0 跳过：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_19-23-15--0400.xcresult`。
- m4 真实入口专项 **1/1 通过**，覆盖 SELECT LEVEL、`Pathway to Thorin`、50 tick 入场和文本 256：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_19-24-50--0400.xcresult`。
- 最终完整 `AEAppUITests` **11/11 通过**，0 失败、0 跳过，实际测试 728.422 秒：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_19-30-30--0400.xcresult`。覆盖首关完整通关与恢复、m1–m4 真实入口、菜单、设置、帮助、存档、地图总览和 60pt 全窗战场。
- 目标面板截图：[escort-duty-objective.png](../.noindex/artifacts/original-alignment-m4-components/escort-duty-objective.png)；地图名过场：[pathway-to-thorin.png](../.noindex/artifacts/original-alignment-m4-entry/pathway-to-thorin.png)；首句对话：[dialogue-256.png](../.noindex/artifacts/original-alignment-m4-entry/dialogue-256.png)。三张均已人工检查，标题、正文、头像和地图无裁切或重叠。

当前自动化已覆盖 m4 开场、触发算法、胜负条件和恢复，但尚无 m3/m4 整关真实通关操作，也未完成物理 iPhone、iOS on Mac 或与原版录像逐帧叠加。此记录之后已完成 m5 的证据审计与实现，详见下节。

### 2026-10-02 第六关 NORTHBOUND 实现与录像对齐

- m5 对应玩家界面的 Campaign 6。源码依据为 `MainDisplayable.java:7761-7773` 与 `9198-9233`，资源依据为 `res/m5.aem`、`maps/m5.json` 和 `strings.json`；公开视频依据为 [Ancient empires II campaign 6](https://www.youtube.com/watch?v=mRqT4ow0IrY)。
- 地图名为 `The Gates of Thorin`，章节标题文本 126 为 `NORTHBOUND`，任务文本 134 为 `Occupy the enemy castle and destroy all enemy troops.`。地图是 12x20 雪地长图，保持 60pt 触屏格子和全窗滚动，不缩放成 240x320 小画面。
- 双方初始金币均为 600，兵种解锁上限为 7。蓝方指挥官位于 `(5,17)` 并使用 `commanderVariant = 2`；源码先把镜头放在 `(5,0)`，以 `mapStepMax = 4` 移向蓝方指挥官。该移动发生在地图名过场期间，录像起始画面已经落在南端蓝军城堡，因此 UIKit 进入战场后应直接落在指挥官位置，再保留状态 0 的 10 tick 等待。
- 蓝军初始单位为指挥官 `(5,17)`、士兵 `(8,17)`、投石车 `(3,17)`、狼 `(4,16)`、弓箭手 `(7,17)`、蜥蜴人 `(8,16)`；红军为指挥官 `(5,5)`、士兵 `(5,7)`、士兵 `(6,6)`、蜥蜴人 `(4,6)`、投石车 `(5,6)`、Wisp `(6,5)`、狼 `(7,6)`，共 13 个单位。
- 蓝方建筑为城堡 `(5,17)` 与城镇 `(2,14)`、`(8,17)`；红方建筑为城堡 `(5,5)` 与城镇 `(5,7)`、`(7,8)`；中立城镇位于 `(2,7)`、`(4,12)`、`(10,6)`，废墟位于 `(9,13)`、`(10,10)`。
- 状态 1 显示文本 260、头像 1：`The enemy is planning to attack King Galamar before he reaches Thorin! We must defeat them!`；状态 2 显示任务目标并开放正常操作。公开视频 0:00 附近可见相同的南端构图和头像，0:10 可见金币 600。
- 胜利条件必须同时满足红军活单位为 0、红方城堡为 0。满足后等待 15 tick，镜头回到蓝方指挥官；文本 261 使用头像 0：`Let us hurry North to meet Galamar. The enemy knows of his plans and he may need help!`，再等待 10 tick 后完成任务。公开视频 16:02 可见镜头回到指挥官和对应头像。

- m5 已接入 `CampaignMissionScript`：初始金币、兵种上限、指挥官变体、10 tick 开场等待、文本 260、任务目标、双重胜利条件、15 tick 胜利等待、镜头回指挥官、文本 261 及最后 10 tick 等待均进入可持久化状态机。m4 胜利自动进入 m5，后续批次已继续接通 m6 和 m7。
- m5 的 4 项状态机测试与 1 项 UIKit 组件测试 **5/5 通过**：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_20-02-32--0400.xcresult`。
- 最终完整 `AEAppTests` **108/108 通过**，0 失败：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_20-06-15--0400.xcresult`。m5 真实入口专项 **1/1 通过**：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_20-03-01--0400.xcresult`。
- 最终完整 `AEAppUITests` **12/12 通过**，0 失败、0 跳过，实际测试 745.843 秒：`.noindex/DerivedData/Logs/Test/Test-AncientEmpiresII-2026.10.02_20-07-04--0400.xcresult`。覆盖首关完整通关与恢复、m1–m5 真实入口、主菜单、设置、帮助、存档、地图总览和 60pt 全窗战场。
- 目标面板截图：[northbound-objective.png](../.noindex/artifacts/original-alignment-m5-components/northbound-objective.png)；真实入口截图：[the-gates-of-thorin.png](../.noindex/artifacts/original-alignment-m5-entry/the-gates-of-thorin.png)、[dialogue-260.png](../.noindex/artifacts/original-alignment-m5-entry/dialogue-260.png)、[level-selection-transition.png](../.noindex/artifacts/original-alignment-m5-entry/level-selection-transition.png)。已人工检查标题、正文、头像、南端蓝军编队和 60pt 全窗地图，无裁切或重叠。
- 目前的视频证据对齐到开场构图、600 金币和结尾镜头/头像；尚未对 m5 整关进行逐帧叠加或真实通关自动操作，也未进行物理 iPhone 与 iOS on Mac 验收。

### 2026-10-02 第七关 RENDEZVOUS 与水晶劫持

- m6 对应玩家界面的 Campaign 7。地图名为 `Outside the City`，章节标题为 `RENDEZVOUS`；双方初始金币为 400/600，兵种上限 8，初始目标使用文本 135。Galamar、士兵、弓箭手、女术士和水晶从北侧按 0/0/6/12/18 tick 的跟随链进入，速度 4px/tick，完整入场 34 tick。
- 第二回合触发北侧四人伏兵，按 0/10/20/30 tick 进入。任一已行动蓝军进入 `x <= 9 || y >= 10`，或首批伏兵被消灭后，龙从东侧接近水晶；文本 265、266 后龙与水晶一起飞出西侧。状态 11 之前水晶死亡会失败，剧情劫持后移除水晶不会误判失败。
- 随后按源码顺序生成西侧八名敌军、南侧四名敌军，再由 Valadorn 带领魔像、龙、Wisp 和弓箭手从南侧入场。教程 214 后目标更新为文本 138；最终必须同时消灭红军并占领全部红方城堡。
- 所有剧情单位引用、交错延迟、目标切换、移除状态和待播队列均进入快照。m5 胜利自动进入 m6，m6 胜利自动进入 m7；SELECT LEVEL 已开放到 m7。
- m6 的 5 项状态机测试和 1 项 UIKit 组件测试覆盖入场链、第二回合伏兵、水晶劫持、两侧增援、目标更新、胜负条件及恢复；真实入口专项覆盖 `Outside the City` 和文本 262。截图与 manifest 位于 `.noindex/artifacts/original-alignment-m6-components/` 和 `.noindex/artifacts/original-alignment-m6-entry/`。

### 2026-10-02 最终关 HEAVEN'S FURY、天怒与结局

- m7 对应玩家界面的 Campaign 8。入口先显示原 `intro4` 和文本 219，再显示章节标题 `HEAVEN'S FURY`。地图 `The Ancient Citadel` 为 14x18；蓝/红初始金币 800/200，兵种上限 8，任务文本 136。Galamar、Valadorn、Saeth 分别使用指挥官外观 0/2/3。
- 开场按 `MainDisplayable.java:9485–9604` 接入文本 271–278、头像、镜头和等待；三枚水晶依次进入 `(7,2)` 并消失，Saeth 随后进入。文本 279 后对 `(9,15)` 单位播放不扣血的天怒演示，再开放“击败 Saeth 及其部队”的正式目标。
- 红方回合中 Saeth 按 `MainDisplayable.java:6903–6913,4207–4259` 以天怒替代普通 AI 行动：从存活蓝军中随机选目标，造成 25…49 HP 伤害并截断到剩余 HP。伤害与 Java 随机状态在演出前提交；读档只重放提示、落雷、烟雾、火花、伤害数字、震动与震屏，不会再次扣血或抽样。
- 天怒击杀普通单位、水晶和指挥官沿用既有死亡语义；指挥官身份、递增复活费用和城堡复活路径保留。新增回归专门覆盖 Galamar 被天怒击杀、JSON 恢复和在 `(8,15)` 城堡复活同一身份。
- Saeth 阵亡后等待 20 tick，黑幕淡入并清空战场，再在 `(7,2)`、`(6,3)`、`(8,3)` 重建 Saeth、Galamar、Valadorn及两名士兵。文本 281–287、Saeth 消失、长震屏和最终淡出按源码顺序播放；m7 跳过普通 `MISSION COMPLETE`，直接进入原 `intro5`、文本 220 最终预言和文本 288 完整制作人员名单，再返回主菜单。
- m7 组件专项 `Test-AncientEmpiresII-2026.10.02_21-21-52--0400.xcresult` 为 **6/6 通过**，真实入口 `Test-AncientEmpiresII-2026.10.02_21-22-21--0400.xcresult` 为 **1/1 通过**；新增指挥官复活聚焦结果 `Test-AncientEmpiresII-2026.10.02_21-27-56--0400.xcresult` 为 **5/5 通过**。截图与 manifest 位于 `.noindex/artifacts/original-alignment-m7-components/` 和 `.noindex/artifacts/original-alignment-m7-entry/`。
- 视频依据为本地 `.noindex/reference-videos/Ancient empires II campaign 8 (Final) .mp4`（21:56，480x616），抽帧联系表位于 `.noindex/artifacts/m7-reference/`。已对照开场单位布局、战斗中的天怒和 `intro5` 结局构图；尚未完成整关逐帧重叠、物理 iPhone 或 iOS on Mac 验收。


### 2026-10-03 经验、晋级演出与等级称号

- 本次检查发现经验和单次晋级计算已经存在，缺失的是 `MainDisplayable` 的晋级演出队列，以及 `Unit.setRank` 的等级称号选择。原版依据：`reference/inputs/AEIIRM_src/src/aeii/Unit.java:87,233,255,260,265`、`MainDisplayable.java:1255,4307–4367`；数值规则同时对照 `reference/reconstruction/ALGORITHMS.md` 与选定 JAR 字节码。
- 经验仍为目标当前基础攻击下界、上界及防御之和乘实际伤害；晋级门槛为自身当前基础属性之和乘 `100 * 2 / 3`，沿用整数截断。先攻和反击全部结算后，双方存活者各最多升一级，扣一次门槛并保留剩余经验，不回复 HP。每级攻击上下界、防御各加 2，移动和射程不变；原版没有 9 级上限，晋级沿用有符号 byte 绕回。
- 新增可存档的 `levelUp` / `rankName` 场景。死亡演出结束后，按发起者、反击者顺序播放升级：镜头到达单位后播放原 `levelup` 和 `small_spark` 素材，LEVEL UP 在前五个 tick 逐步上移，普通兵种到 2、4、6 级时显示原文本 80 `PROMOTION:` 和新称号，指挥官保留自身名字。
- 详情、地图信息、指挥官复活招募名称都使用单位当前等级对应的原文本。等级名由 `139 + type * 4 + min(rank / 2, 3)` 选取；指挥官沿用 `93 + commanderVariant`。经验条复用核心层的晋级门槛，地图辅助信息包含当前军衔。
- 升级结果在战斗时一次性提交，重播/恢复场景不再次增加等级、经验或扣血。演出火花使用确定性位置，不消耗存档中的战斗/AI 随机状态。战斗动画关闭和开启均进入相同的战后晋级队列。
- 模拟器：iPhone 17 Pro / iOS 26.5，复用 `.noindex/DerivedData`。`promotion-alignment-tested.xcresult` 实际执行 **55 项，54 通过、1 失败**；新增的 4 项规则测试和 1 项 UIKit 升级流程测试全部通过。覆盖门槛差一/相等/多一、反击双方晋级、死亡不晋级、单次只升一级、不回血、2/4/6 级称号、9 级之后继续晋级、指挥官身份、逐场景 JSON 恢复、两种战斗画面、详情军衔和剩余 HP。复活、AI、原战斗画面及待播队列相关规则测试通过。
- 唯一失败为既有 `testRaiseMenuCancelConfirmAndRestoreKeepLargeCellsAndLingeringSparks` 在等待召唤火花 CAAnimation 移除时断言失败；独立复查 `promotion-particle-recheck.xcresult` 的测试进程被 signal kill，不能确认该项通过。尚未完成物理设备验收。
- 已查看升级截图：`.noindex/artifacts/promotion-captures/` 含两种战斗后的 LEVEL UP、新等级称号和属性面板，共 6 张升级流程截图。当前 JAR 仅含 9 首 MIDI，原代码调用的晋级音效索引 13 没有对应音频资源，本次没有合成替代音效。
