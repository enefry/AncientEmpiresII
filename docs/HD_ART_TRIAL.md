# 全量原版美术 4× 显示增强

## 启动页高清重绘与菜单直达

2026-10-04，用户真机录屏确认中文标题覆盖了原版动画图层。按用户要求，启动页和菜单均保留原版英文文案图片，不替换为中文标题；移除本地化标题覆盖，恢复原版波浪展开、透明光扫与背景渐显。菜单文字及其他界面的本地化保持不变。

验证：启动画面原版/高清像素合成 2 项参数化测试（共 4 例）和英文、简体、繁体 3 项完整本地化 UI 流程通过，启动页和菜单均显示原版标题图片且无中文文字覆盖。结果位于 `.noindex/artifacts/original-title-tests.xcresult`，中文系统下的原版标题动画录屏位于 `.noindex/artifacts/startup-recording-review/original-title.mp4`。本次修复尚未在真机验证。

启动行为已调整为每次启动均显示启动页，取消启动时自动续局；主菜单原“在线”位置现在是“继续游戏”。新图标由内置 image_gen 生成，使用卷轴、绿色继续三角和金色回转箭头，源图在 `reference/art/imagegen-interface/continue-game.png`，完整提示词在同目录 `continue-game-prompt.json`，通过 `package_interface_art.py` 打包到 `Enhanced/Interface/menu_icons.png` 原在线图标帧。无自动存档时灰显，点击继续才读取自动存档，原手动存档入口保持可用。

验证：5 项存档/Scene 控制器测试、4 项多语言测试、1 项界面图集测试、2 项启动合成参数化测试（共 4 个用例）及 3 项菜单/战役/遭遇战 UI 流程通过。存档控制器和战役流程分别复测通过；结果位于 `.noindex/artifacts/continue-game-coordinator-tests.xcresult`、`continue-game-campaign-ui.xcresult`，其余通过用例和菜单截图位于 `continue-game-tests.xcresult`。原版 362 个导出文件校验通过。

2026-10-03，使用内置 imagegen 对照原启动图重绘标题下方角色群像，保留中央金发国王、左侧有伤疤的兄弟、右侧骷髅战士、后方角盔反派及红黑构图。原版英文标题和透明光扫结构原位保留，避免生成文字失真；原厂商标识在现代长屏铺满显示时会被放大截断，因此不叠加到高清角色层。输出 `Enhanced/Startup/splash.png` 为 960×1280，逻辑尺寸仍为 240×320；生成源图、完整提示词与说明位于 `reference/art/imagegen-startup`。

启动页的波浪展开、标题光扫、点击提示和音乐仍保留；点击启动页进入主菜单时，导航控制器改为无转场立即切换，菜单圆盘自身的进入和交互动画不变。`--classic-startup` 可回退此前的启动图增强。

最终构建成功，24 项模拟器测试通过（美术 20、标题合成 2×2 参数组合、菜单动画 2），并通过 1 项完整 UI 流程：启动页点击、菜单直达、圆盘旋转、进入与退出子菜单。362 个原版导出文件校验通过。结果包为 `.noindex/artifacts/startup-art-final.xcresult` 和 `.noindex/artifacts/startup-menu-ui.xcresult`；最终运行截图为 `.noindex/artifacts/startup-hd-final.png`。未验证真机。

## 剧情插画高清重绘

2026-10-03，使用内置 imagegen 对照原图重绘 intro0～intro5 六张剧情插画：兄弟肖像、预言地图、神殿遇袭、生命神殿、城堡废墟和结局预言。保留原构图、人物服装及标志性特征、原色调和底部暗色过渡。原图 240×139，打包后 960×556，剧情页面采用等比显示和平滑采样。

生成源图和完整提示词位于 `reference/art/imagegen-story`，可通过 `tools/package_story_art.swift` 重新打包。游戏读取 `Resources/Enhanced/Story`，约增加 4.3 MiB 图片资源。原文、章节入口、滚动与结局跳转不变；正式流程使用 intro2～intro5，intro0/intro1 仅同步增强已有资源。`--classic-story` 可回退此前增强效果。

构建成功，21/21 项模拟器测试通过（美术 19、第三章插画入口 1、结局预言及字幕 1），362 个原版导出文件校验通过。新增验证覆盖包内像素实际加载、逻辑尺寸、局部裁剪、非法资源名和六张插画在真实 UIKit 页面中的淡入与显示；未验证真机。结果包：`.noindex/artifacts/story-art-tests.xcresult`；资源总览：`.noindex/artifacts/story-art-overview.png`；实际页面测试截图：`.noindex/artifacts/story-art-ui`。

## 地图单位连续动画

2026-10-03，地图待机由两张图片每 0.66 秒跳切改为 24 段姿态关键帧的连续插值，由 Core Animation 按显示刷新率播放。地面单位轻微呼吸，水元素起伏，精灵、飞龙和水晶缓慢漂浮；重型单位幅度更小、节奏更慢。单位 ID 决定错开的循环相位，刷新选择和路径时不会重新齐步开始。行进增加轻微起伏，沿用原来的路径与移动时长。

角色画面独立于血量图层和格子按钮，保留原图、头像合成、四队换色及行动后灰化。阵亡和已行动单位不播放待机；系统“减少动态效果”关闭附加姿态变化，仍保留可理解的路线移动。剧情移动使用显示帧之间的小数 tick 插值，事件、伤害、音效和回合逻辑仍按原来的 66 毫秒 tick 触发。

这是现有高清角色图的连续姿态动画，没有新增逐帧绘制的四肢动作或战斗攻击帧。标题动画与地形动画不在本轮范围。

构建成功，模拟器 28/28 项测试通过：新增动画 5、美术 17、AI 与地图战斗 3、第一章界面 3。覆盖循环闭合、幅度、错峰与相位连续、减少动态效果、血量与点击区域稳定、剧情移动延迟和小数插值、行进完成一次以及存档恢复。结果包：`.noindex/artifacts/map-motion-tests.xcresult`。真实游戏画布测试场景录屏：`.noindex/artifacts/map-motion-preview.mp4`，GIF：`map-motion-preview.gif`；未验证真机帧率。

## 英雄头像、建筑与地形清晰化

2026-10-03，新增 4 种地图英雄头部和 6 张剧情头像，保留原角色发型、胡须、头盔、面部标记及头身锚点；地图、属性、招募、剧情对话同步使用。森林、雪山、低丘、村庄、城堡、桥梁、遗迹和火山按原图重绘。48 张地块均接入高清资源，水面与泥路采用生成材质，河岸、道路保留原拼接边界与地形规则。建筑四队和中立版本共享轮廓，换色仅限屋顶或旗帜，保留石墙、金边和地面阴影。

本轮使用内置 imagegen，源图和完整提示词保存在 `reference/art/imagegen-environment`；打包脚本为 `tools/package_environment_art.swift`，输出 `Resources/Enhanced/Environment`，新增打包资源约 1.5 MiB。`--classic-environment` 可回退本轮美术；原版资源未修改，战斗侧面头部及战斗背景沿用此前增强。

最终构建成功，28/28 项模拟器测试通过（美术 17、招募 / 小地图 4、战斗渲染 4、第一章 UI 3），362 个原版导出文件校验通过。新增检查覆盖资源实际加载、头像分帧与身份区分、建筑换色范围、全部可拼接地形边界。结果包为 `.noindex/artifacts/environment-verified.xcresult`；资源预览为 `environment-atlas.png`，真实地图画布测试场景为 `environment-map.png`，普通启动运行截图为 `environment-runtime.png`，均位于 `.noindex/artifacts`。已目视检查并在模拟器启动，未验证真机；运行时临时静音，不改持久化音乐设置。

## 水元素、精灵和凶狼形象修正

2026-10-03，根据用户反馈修正三个地图图标。此前错误地把 `lizard`、`spider` 历史资源键解释成蜥蜴、蜘蛛；实际身份应以单位描述和原图为准，分别是水元素和凶狼。使用内置 imagegen 对照原地图图标、原战斗图及已确认弓箭手画风重新生成：水元素为连续液体身形和涟漪底座；精灵为圆形光环及白色光核；凶狼为长吻、四足低伏及上翘尾巴。

新源图仍保存到 `reference/art/imagegen-map-units/{lizard,wisp,spider}.png`，完整提示词见该目录的 `identity-corrections.json`。仅这三类单位的 12 张队伍图集和 3 张遮罩发生变化，其余打包单位文件 SHA-256 保持不变。构建成功，25/25 项模拟器测试通过，362 个原版导出文件校验通过；未验证真机。结果包为 `.noindex/artifacts/unit-identity-tests.xcresult`，已目视检查的真实地图画布测试截图为 `.noindex/artifacts/unit-identity-map.png`。更新后的游戏已在模拟器启动。

## 全兵种地图 Imagegen 替换

弓箭手实际效果确认后，同画风已扩展至全部 12 种地图单位、详情和招募图标。新增 11 种透明底源图保存在 `reference/art/imagegen-map-units`；游戏打包资源位于 `Resources/Enhanced/MapUnits`。弓箭手沿用先前已确认的资源。

国王身体兼容原四种头像；水晶保持固定粉色；其他单位支持四队遮罩换色和行动后灰化。每张图等比适配原逻辑占位，两帧待机通过轻微平移 / 缩放得到。战斗侧面图及地形等仍使用原版增强路径。`--classic-units` 可回退全部地图单位。

来源、提示词、打包及范围见 `reference/art/imagegen-map-units/README.md`。以下保留前两阶段的实施记录。

2026-10-03 验证：构建成功，模拟器 25/25 项测试通过（美术 14、招募 / 小地图 4、战斗渲染 4、第一章 UI 3），362 个原版导出文件校验通过。检查了全部新单位两帧加载、四队透明度一致、遮罩外颜色不变、水晶固定色、灰化和国王头像合成。结果包为 `.noindex/artifacts/map-units-imagegen-verified.xcresult`。全兵种四队真实地图画布测试截图为 `.noindex/artifacts/map-units-imagegen-map.png`；普通存档运行截图为 `.noindex/artifacts/map-units-imagegen-runtime.png`，已检查士兵灰化、弓箭手和国王的显示。游戏保留在模拟器运行；过程临时静音，不修改保存设置，尚未验证真机。

## Imagegen 弓箭手小范围替换

用户确认清晰化方向后，地图弓箭手改用 `reference/art/imagegen-faithful-preview/archer-blue-preview.png` 生成样图。打包资源位于 `Resources/Enhanced/Archer`，四队共用同一轮廓与显式换色遮罩，原队伍调色板控制衣帽色，脸、木弓和金属保持不变。地图、单位详情、招募图标同步生效；战斗侧面图和其它美术继续采用下述原版增强路径。

试用图为 96×96 像素，仍占原 24×24 逻辑格子；第二待机帧只横移一逻辑像素，没有新增绘制的动作。添加 `--classic-archer` 可恢复此前的弓箭手。打包脚本、来源与限制见 `reference/art/imagegen-faithful-preview/README.md`。

本次构建和模拟器测试通过：美术 13 项、招募 / 小地图 4 项、战斗渲染 4 项，共 21/21。验证了包内新图实际被加载、四队透明轮廓一致、遮罩外颜色完全不变、双帧横移和灰化透明度；362 个原版导出文件校验通过。结果包为 `.noindex/artifacts/archer-imagegen-tests.xcresult`。真实游戏代码的测试场景截图为 `archer-imagegen-map.png` 和 `archer-imagegen-recruitment.png`；普通恢复存档运行截图为 `archer-imagegen-runtime.png`，均位于 `.noindex/artifacts`。运行时临时关闭音乐，未改变保存的音乐设置。

## 本次范围

士兵试用效果确认后，将同一方法推广到 `Original/res` 下全部 233 张 PNG：12 种单位的四队地图图标、全部战斗兵种及镜像、国王头像、地形与建筑、战斗背景、技能与粒子、菜单边框、HUD、数字字形、人物肖像、章节插图、启动和结算画面。小地图、伤害数字、粒子动画、国王身体与头像合成和战斗地形缓存也以 4× 分辨率绘制。

按用户“保留原来士兵特征，只是提高分辨率，保留情怀”的要求，直接读取原版图片，在运行时用两次 Scale2x 得到宽高各 4× 的像素画面。只细化像素阶梯边缘，保留原轮廓、装备、姿势和配色。不会凭空增加原图不存在的细节，不增加动画帧数，不改规则、时序或格子尺寸。

## 资源和换色

- 战斗图集：408×144 像素，逻辑尺寸 102×36，单帧 136×144 对应原 34×36。
- 每队完整地图图集：1152×192 像素，逻辑尺寸 288×48，单帧 96×96 对应原 24×24。
- 唯一美术来源是 `Original/res` 原版资源。原始文件不修改。
- 继续支持蓝、红、绿、黑四队，直接使用原版各队颜色，不引入重绘时的新调色板，也不通过相近色阈值猜测换色区域。
- 四队统一用蓝队原图判定边缘，复制像素时取目标队伍的对应颜色。这样黑队颜色即使与轮廓颜色接近，也不会改变角色轮廓。
- 手动裁帧和 `.sprite` 动画先裁原图，再逐帧增强，避免相邻帧参与边缘计算；整张图集按原 `.sprite` 网格独立增强后合成。分离帧保持各自尺寸与对齐信息。输出像素仅复制原图颜色，不混合新色。
- 原图解码复用；增强图与帧使用按资源名、裁剪范围区分的 `NSCache`，设置 48 MiB 成本上限，允许系统回收，避免每次绘制重新增强。
- 保持原始逻辑尺寸、锚点和镜像映射。UIImage.scale 为 4，裁帧把逻辑坐标乘以 scale，镜像保留该分辨率。
- 重绘版基础图和遮罩已移到 `reference/art/soldier-trial/rejected/`，不打包进 app；旧生图只留作历史参考，旧打包工具已移除。
- `Original/` 目录、JAR 导出和原 SHA-256 清单保持不变。
- 启动画布从 240×320 提高到 960×1280，波浪、渐显、扫光仍用 240×320 逻辑坐标计算；标题与封面按同一倍率裁切，保持原布局。

## 复现与回退

```sh
python3 tools/export_ios_content.py --check
```

不需要生成高清 PNG 或维护两份调色板。通用增强实现沿用 `App/Presentation/HighResolutionUnitArt.swift`，统一入口为 `OriginalArt.image` / `frame`。`OriginalSprite` 保持原动画定义。测试输出士兵原图对照、全兵种预览、全队地图及真实游戏画布截图。

默认全量启用。通过 Xcode Scheme 的 Arguments Passed On Launch 添加 `--original-art`，可不改资源地恢复全原版显示，包括启动动画和各合成画布。重新启动才切换模式，不改变保存数据。

## 验证边界

`HighResolutionUnitArtTests` 检查全部 PNG 的 4× 像素与原逻辑尺寸、全部战斗兵种四队配色和透明轮廓、分帧边界、动画时序与锚点、国王头像合成、小地图和行动后灰化。士兵保留逐像素局部特征检查。`TitleCompositingTests` 同时检查原版和增强版的波浪、封面合成、输出尺寸和裁切。灰化保留原游戏的米白色豁免规则。模拟器验证不代替真机及美术观感验收。

模拟器渲染测试使用 `-game.music NO` 临时静音，避开本机 MIDI 音频 RPC 问题，不改持久化音乐设置。复用 `.noindex/DerivedData`，无需清空构建缓存。

验证结果与全量截图位于 `.noindex/artifacts/full-art-*`；构建和测试日志位于 `.noindex/logs/full-art-*`。

本版构建通过；362 个原版导出文件校验通过。模拟器执行 27 项 XCTest，26 项通过，另有 2 项启动画面参数化测试（原版 / 增强共 4 例）全部通过。其中全量美术 11 项、战斗渲染 4 项、招募 / 小地图 4 项、第一章 UI 3 项全部通过。233 张 PNG 均验证了原逻辑尺寸与 4× 像素尺寸，全部兵种四队动画通过配色和透明轮廓检查。

未通过的是既有 `CampaignStatusUIKitTests.testRaiseMenuCancelConfirmAndRestoreKeepLargeCellsAndLingeringSparks` 第 121 行：等待召唤粒子动画结束超时。用 `--original-art` 单独复验仍在同一断言失败，结果记录于 `full-art-original-check.xcresult`；本次未改动动画结束逻辑。

- 主结果包：`.noindex/artifacts/full-art-tests.xcresult`
- 实际地图画布的全兵种四队测试场景：`.noindex/artifacts/full-art-map.png`
- 实际战斗画布测试场景：`.noindex/artifacts/full-art-combat.png`
- 普通启动恢复存档后的模拟器屏幕：`.noindex/artifacts/full-art-runtime-map.png`
# 界面图标高清重绘

剩余界面图标使用内置 image_gen 按原版参考图重绘，保留剑、叶片、城堡、放大镜、书本、行动符号、属性与状态含义，以及银色边框和粉色选择光标。58 个独立图标帧打包为 21 张图集；另外从现有高清地图资源生成 12 种单位的缩略图集和 18 张小地图地形，合计 40 张新增 PNG。

- 资源：`Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Interface`。
- 原始生成图、逐格清单和提示词：`reference/art/imagegen-interface`。
- 复现：`python3 tools/package_interface_art.py prepare` 准备原版参考图，生成 `core.png` 与 `controls.png` 后运行 `python3 tools/package_interface_art.py package`。脚本使用 Pillow。
- 加载：`OriginalArt` 默认优先使用高清界面图集；保持原逻辑尺寸、帧索引、透明中心和裁剪坐标，像素尺寸为原版 4×。`--classic-interface` 可以单独回退到原版算法增强，`--original-art` 仍回退全部原图。
- 四队小地图标记共享同一轮廓及银色边缘，只替换中心队伍颜色；既有地图单位与建筑的队伍遮罩保持原有机制。
- 应用图标已具备独立 1024 像素源图。本轮范围为界面和地图图标；数字字形、标题文字、战斗角色、战斗背景与粒子动画仍使用此前的 4× 算法增强。
- 验证：362 个原版导出文件校验通过，40 张新增 PNG 均为原像素尺寸 4×；26 项资源/招募 XCTest 与 1 项真实菜单 UI 测试通过，已检查菜单、招募和小地图截图。结果保存在 `.noindex/artifacts/interface-art-tests.xcresult`，截图在 `.noindex/artifacts/interface-art/test-captures`。
