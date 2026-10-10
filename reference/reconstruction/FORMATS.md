# 游戏资源与存档格式

本次使用原字节提取和数据解码，不重新绘制、补造或压缩替换原图，也不渲染新的音频。所有原文件在 `reference/original`，可与 JAR 哈希逐项核对。

## 总量与分类

选定 JAR 355 个非目录文件：18 个 class、1 个 Manifest、336 个其它文件。其它文件包括 234 PNG（含根目录 `gameicon.png`）、58 `.sprite`、12 `.unit`、9 `.mid`、20 `.aem`、`lang.dat`、`tiles0.prop`、`m0.script.bak`。完整路径、SHA-256、大小以及图像/MIDI 元信息见 [assets.json](data/assets.json)。

四阵营图片在 `res/blue, red, green, black`，公共效果/UI/地形在 `res`。资源加载先尝试内部 `res.pak`，缺失时退回 `/res/名称`；选定包资源以普通文件形式提供，不需要构造 pak。

## 地图 .aem

读取依据：`MainDisplayable.loadLevel:3177`。全部多字节整数为 Java DataInputStream 大端。

| 次序 | 类型 | 内容 |
| --- | --- | --- |
| 1 | int32 signed | width |
| 2 | int32 signed | height |
| 3 | width*height 个 byte | tile ID，x 为外层、y 为内层 |
| 4 | int32 signed | 附加记录数量 N |
| 5 | N*4 byte | 原版加载器直接 skip 的附加块 |
| 6 | int32 signed | 单位数量 U |
| 7 | 重复 U 次，5 byte | packedUnit:int8，pixelX:int16 signed，pixelY:int16 signed |

`type=packedUnit%12`，`fractionColor=1+packedUnit/12`；颜色通过阵营轮转映射再转为 turnpos。单位坐标除 24，按 Java 向零截断。JSON 同时保留像素坐标与格坐标，地图网格为了便于消费转换成 `rows[y][x]`，明确记录 orientation。

验证结果：20 张全部完整消耗，没有尾随字节；4,400 个图格都在 0…47；103 条单位中 99 条像素坐标不整除 24，5 条格坐标出界，均保留。m1/m2 的出界记录与入场剧情对应。不要用“坐标应是格乘24”的假定过滤原记录。

附加块各图 N=24/36/48，合计 840 个 4-byte 记录，当前全为大端整数 2。加载代码跳过它们，无法从这批相同值可靠断言它们原来是什么；JSON 保存 hex，没有替换成猜测的碰撞、地形或概率字段。

编辑器 `saveLevel:3610` 写相同布局，但 N=0，单位写精确格坐标*24。它不保存 rank、HP、金钱、队伍、剧情等实时游戏状态。因此导出地图和保存对局是不同格式，也不保证编辑器再导出字节等于原 `.aem`。

解析结果每张同时提供 [JSON](data/maps) 和 [原始记录 PNG 预览](previews)；预览使用单位图标第一动画 bank，不执行开场脚本，也不显示地图外单位。

## 语言表 lang.dat

大端 int32 字符串数（317），接着每条 `uint16 字节长度 + Java modified UTF-8 字节`。它与普通 UTF-8 不完全相同：NUL 为 `C0 80`，非 BMP 字符可用 UTF-16 surrogate 的两个三字节序列。解析器按 UTF-16 code unit 解码并完整验证。

数据导出为 [strings.json](data/strings.json) 和 [TEXTS.md](TEXTS.md)，保留英文、换行、占位符与 ID。没有把说明中的错误内容改写成新的规则。UI 某些文字通过 `getReplacedLocaleString` 代入变量，移植前需保留替换语义。

| ID 区间 | 用途 |
| --- | --- |
| 0–60 | 语言、主菜单、设置、对战配置等 |
| 61–88 | 战场行动、经济/保存/结果提示 |
| 89–100 | 阵营、指挥官名、状态 |
| 101–112 | 12 张自由对战地图名 |
| 113–120 | 8 张战役地图名 |
| 121–138 | 任务名、目标、对战目标与更新后的 m6 目标 |
| 139–183 | 单位四档显示名 |
| 184–195 | 12 种单位介绍 |
| 196–214 | 教程与帮助 |
| 215–220 | 序幕、间章、预言、结局 |
| 221–287 | 战役对话 |
| 288 | 职员表 |
| 289–316 | 编辑器、文件导入导出、随机填充、调整尺寸、控制台 |

## 单位与地形文本配置

逐行读文本，分号后的内容是注释，忽略空行，命令大小写不敏感。`.unit` 包含 `MoveRange, Attack, Defence, AttackRange, Cost, CharCount, CharPos, HasProperty`。`AttackRange max min` 的顺序不要倒置；HasProperty 是位号。

`tiles0.prop`：`TypeCount` 创建地形费用/防御和战斗背景/前景数组；`TypeDef id steps defence background foreground`；`TileCount` 创建图块类型/小地图索引数组；`TileDef id type smallFrame`。读取 `.prop` 会覆盖源码的静态默认值。

源码还有二进制 `.unitbin`/`.sprbin` 和地形 `.bin` fallback；选定 JAR 没有使用这些资源文件，本次没有为它们生成虚构实例。来源：`Unit.readUnitData:977`、`MainDisplayable:585`。

## 精灵 .sprite

58 份都解析成 [sprites.json](data/sprites.json)，保留原数字与图源，不做游戏动画移植。

| 命令 | 含义 |
| --- | --- |
| FrameCount n | 逻辑帧数，含变换后复用帧 |
| FrameWidth / FrameHeight | 网格切片尺寸；分图模式下锚点参考尺寸 |
| FrameDef index sourceIndex transformBits | 第 index 帧复用 sourceIndex 图源并应用变换 |
| Align flags | MIDP anchor 位，用于相对逻辑尺寸对齐 |
| FrameSeqCount n | 序列数；非正表示没有自定义序列 |
| FrameDelay n | n 次更新，读取为 n*50 逻辑计数 |
| FrameSeq index count frame... | 帧索引列表，重复帧可以表示停顿 |
| Translate index dx dy | 对该帧追加位移 |

优先找 `名称.png` atlas，按行扫描切片（y 外层、x 内层）。没有 atlas 时尝试 `名称_00.png` 等单图。图源数可能小于 FrameCount，因为后半帧是同一批图的镜像；不能要求每个逻辑帧都存在一张独立 PNG。

阵营索引参数不是颜色 ID：`fractionIndex/2` 选择 blue/red/green/black，偶数彩色、奇数灰色。先找阵营目录图片，缺失才退回公共图片。地图单位 `unit_icons.sprite` 有 24 帧：第一 bank 0…11 是每兵种第一图，第二 bank 12…23 是第二图。**不是 type*2 连续存储**。已耗尽行动灰图取第一 bank；指挥官头部另由 `king_head_icons` 按变体与地图动画帧叠加。依据：`MainDisplayable:878`。

变换的低位按 if/else-if 优先匹配（并非可任意组合的标准 MIDP transform 编码）：

| FrameDef 位 | MIDP drawRegion transform | 效果 |
| --- | --- | --- |
| 1 | 2 | MIRROR |
| 2 | 1 | MIRROR_ROT180 |
| 4 | 6 | ROT270 |
| 8 | 3 | ROT180 |
| 16 | 5 | ROT90 |

Align 使用 MIDP HCENTER=1, VCENTER=2, LEFT=4, RIGHT=8, TOP=16, BOTTOM=32。原实现仅 `!useTransform` 时计算对齐偏移，部分镜像会改变对齐位，且存在局部变量赋值细节；不能未经验证统一成现代 center anchor。每帧 Translate 在对齐后追加。依据：`Sprite.readSpriteData:83`、`SpriteFrame:36,86`。

`setFrameSequence(seq, mirrored)` 镜像时把序列每个索引加 FrameCount/2；部分战斗角色用这种方式选后半镜像帧。序列 0 通常待机，1/2/3 由兵种动画不同阶段引用，不能统一强制命名成跑步/攻击/死亡。

唯一缺图定义是 `buildings.sprite` 的 2 个逻辑帧：没有公共或四阵营的配套 buildings PNG。提供源码旧的 buildings 拼接路径已注释，实际直接加载完整 48 帧 tiles0。浏览器和 JSON 明确列出 missing_sources；资料完整不等于所有历史定义都可独立播放。

## 图片与调色板

234 PNG 已逐块 CRC 校验及完整像素解码，导出 bit depth、color type、PLTE。每个原 PNG 的字节哈希保留；不能只保存转成 RGBA 后的结果，否则会丢失原调色板与透明表依据。

灰色单位处理直接修改 PNG PLTE：除 `(244,244,230)` 外，RGB 各分量替换为 `(R+G+B)/3`，再重算 PLTE CRC，透明表保持。源码注释中的任意阵营换色路径不生效；本版本阵营彩色图本来就各有文件。来源：`SpriteFrame.replacePaletteColor:193`。

侧面战斗人物图片与地图图标不同：soldier/archer/lizard/wizard 等使用大人物 atlas 或分图，`.unit CharPos` 控制多人阵列；地形背景使用 `background+'_bg'` 单图，前景使用地形 `.sprite`，可能随机挑帧。魔法/攻击资源：弓箭手 `archer_arrow`，指挥官 `kingwave/kingslash/king_heads/king_heads_back`，元素生物 `watermagic/fish`，女术士 `spell`，投石车/魔像 `crater`，士兵/骷髅/凶狼 `slash`。依据：`CombatAnimation:98,199`。

有 `chars/lchars` 图形数字字符用于部分 HUD/数值，正文/菜单也调用 J2ME 系统 Font。仅提取精灵字体不能保证现代平台全部文字排版等于原版。

ImageProcessor 放大使用固定点取源位置、缩小时对整数采样块分别平均 RGB/ARGB；内存不足会分片处理，异常时返回原图。它不是现代双线性插值，也不是所有场景都严格 nearest；本次预览只用原图和整数倍 nearest 显示，不将预览渲染策略写回原资源。来源：`ImageProcessor.scaleImage/scaleRGB:29,145`。

## 音乐

MIDI ID 0…8：main_theme、bg_story、bg_good、bg_bad、battle_good、battle_bad、victory、gameover、game_complete。蓝/绿使用 good 背景与战斗曲，红/黑使用 bad。传入 loopCount 0 转成 J2ME −1 无限循环；1 表示单次。源码仅维持一个 currentPlayer，新播放会停旧音乐，不是多个轨道独立混音。

9 文件全部解析 MThd/MTrk、delta VLQ、running status、tempo、program change、note-on 及 EOT；曲长由 tempo 与末 tick 计算，见 [清单](CATALOG.md)，曲长不等于游戏中实际播放时长。只有 bg_story 为 format 1 / 4 轨，其它 format 0 / 1 轨，均 PPQN 120。原始乐器使用合成器 program，换平台音源可能改变听感，尚未采样原手机音源。

iOS 运行时使用 `Resources/Audio/*.m4a`：由 `Tools/render_all_music.sh` 调用 Apple `AVAudioSequencer` / `AVAudioUnitSampler`、系统 `gs_instruments.dls` 离线合成，再以 FFmpeg 编码为 44.1 kHz 双声道、128 kbps AAC。原 `.mid` 不删除，继续作为音符、节拍和乐器 program 的权威来源。预渲染消除了设备实时 MIDI 合成器和 Simulator Audio Unit 状态差异，但音色仍是 Apple General MIDI，并非已采样的原手机音源。

提供源码还调用 `startPlayer(9…14)` 表示一些动作声，但播放器数组按 9 个 MUSIC_NAMES 创建，访问越界由 catch(Exception) 吞掉；JAR 的 GameCanvas 反编译亦保留这种数组长度。因此不能凭动作调用名假定包里有未提取的 WAV/音效。需要原版实测确认这些调用在此版本的听觉表现；本次不补音效。

## 对局存档

不是 `.aem`。Java RMS `save` 保存字节记录；加载时先根据 mode/mission 重新读原地图，再恢复建筑图块和动态单位。没有嵌入完整地图，也没有保存随机数状态。格式依据：`MainDisplayable.getGameSaveData:995`、`loadGameSaveData:1061`。

| 顺序 | 字段 |
| --- | --- |
| 头部 | mode:B、mission:B、turnQueueLength:B、currentPlayer:B、当前颜色:B（读取丢弃）、currentTurn:S、allowedUnits:B、turnQueueLength:B（再一次） |
| 按玩家重复 | team:B、playerMode:B、money:I、cursorX:B、cursorY:B |
| 上限 | unitCap:S |
| 建筑修改 | 按原地图扫描建筑列表，每处一个 tile:B；**数量不在存档中** |
| 单位总数 | units.size:B |
| 每单位基础记录 | type:B、turnpos:B、state:B、effects:B、HP:B、rank:B、experience:S、mapX:S、mapY:S、tombstoneLife:B、poisonSourcePlayer:B（共14字节） |
| 指挥官追加 | variant:B、reviveCost:S、isPrimary:B（共4字节） |
| 尾部 | scriptState:S、var_3cee:I（写入前截成 short）、var_3d08:I、var_3d48 ? 0 : 1:B |

B=int8、S=int16、I=int32，均大端有符号读取。`experience` 内存为 int，存档却写 short；单位数量写 byte、读取也用有符号 byte，>=128 有兼容边界，而对战配置上限可以到 200。不能说旧存档能可靠承载所有配置。源码最后布尔值写时反转、读时直接 !=0，另有截短长值，是需要保留研究证据的差异；字段确切业务含义仍待追踪。没有历史用户存档样本，本次未验证旧存档导入。

RMS `settings` 记录 0 使用一个 byte 的 4 位设置开关，默认全 true；记录 1 存进度，记录 2 存其它地图相关信息。不要在新平台直接照搬 RMS 的编号/错误边界；当前只还原格式，不做存档设计或修复。

## 编辑器算法

`Renderer.convertProbabilities(weights,true)` 就地求前缀和；随机取 `[0,total)`，选择首个 cumulative>随机值的图块 ID；false 从后向前差分恢复原权重。不是按地形 Type 选，是按图块 ID 选。总权重为0沿用原随机函数会除零，表单约束需另外实测。

`createLevel/fillLevel` 按 x 再 y 顺序独立随机图块，没有自动连通性、道路、城堡或公平性保证。`resizeLevel` 的新格 `(nx,ny)` 对应旧格 `(nx−leftDelta,ny−topDelta)`；旧范围外按权重填充，所有单位坐标加 leftDelta/topDelta，之后 `rescanLevel` 重建建筑/阵营列表。不要把地图编辑器的随机填充描述成战役生成算法。来源：`Renderer:130,145`、`MainDisplayable:3029,3447,3467`。
