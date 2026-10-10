# AEIIRM_src 与 v2.5 JAR 的对照

日期：2026-10-01。用户提供 `AEIIRM_src` 后，对目录、构建配置、全部资源和部分关键规则进行检查；未修改提供的 Java 源码或资源，也未重新命名现有参考代码。

## 结论

`AEIIRM_src` 是同一 v2.5 游戏的一份高度相关、人工整理过的恢复源码，非常适合作为后续移植的主要阅读材料。选定 JAR 及字节码继续作为实际包内行为的核对依据。

不能把它视作已经证明与 JAR 完全等价的原作者源码：多份文件明确带有 `Decompiled by: Fernflower v0.6` 标记，仍有 `var_...` / `sub_...` 名称、注释掉的旧逻辑以及人工修改。项目的 `manifest.others` 声明版本 2.5、Vendor 为 Macrospace, SilentKnight，与选定 JAR 一致，但这不是作者或版本来源认证。

## 核实的资源关系

- 源码工程资源类路径根为 `reference/inputs/AEIIRM_src/res`，游戏图片等放在其内层 `res/`。
- 源码资源 336 个，JAR 游戏资源 336 个（均不含 class、目录和 Manifest）。
- 同名资源 335 个，**全部字节一致**，包括 234 个 PNG、58 个动画配置、20 个地图、12 个单位配置、9 个 MIDI、地形配置与语言表。
- 源码独有：`res/m0.script`。JAR 独有：`res/m0.script.bak`。
- 两份脚本文本仅在 `@Case 28` 存在一处行级差异：源码在 `Wait 5` 后多一条 `NextState`。

全部共同资源的 SHA-256、16 个 Java 文件的哈希、源 ZIP 与 JAR 哈希，以及完整脚本 diff，保存于 `aeiirm-source-comparison.json`。

## 第一关剧情路径区别

源码 [MainDisplayable.java](inputs/AEIIRM_src/src/aeii/MainDisplayable.java) 的 `loadScript`（7829 行）读取 `m<关卡>.script`。`loadLevel` 在战役加载后调用它（3438 行）。`updateScript` 优先使用非空脚本，否则执行硬编码战役状态机（8301 行附近）。

选定 JAR 的相同逻辑见 `java-named/aeii/GameController.java` 的 `method_200` 与 `method_202`。其包内没有有效 `.script`，也没有 `res.pak`，只有 `.script.bak`；按当前包内资源，第一关采用硬编码状态机。源码目录若按现有资源打包，则第一关会采用 `.script`。

源码的 Case 28：

```text
ShowDialog 225 5 4
Wait 5
NextState
```

JAR 备份没有最后一行。这不是可以不加区分地复制的资源差异；是否采用源码脚本、JAR 硬编码剧情或另行转换，需要在实现第一关时记录，并对照原版运行验证。本次尚未验证完整剧情时序是否等价。

## 类对应关系

这是按职责和调用路径核对的对应关系；JAR 的优化合并使部分关系为一对多。

| JAR 类 | 我们的阅读名称 | AEIIRM_src 中的类型 |
| --- | --- | --- |
| `aeii.Main` | `GameMIDlet` | `aeii.Main` |
| `aeii.c` | `GameCanvas` | `aeii.Renderer` |
| `aeii.f` | `GameScreen` | `aeii.PaintableObject` |
| `aeii.g` | `GameController` | `aeii.MainDisplayable` |
| `aeii.a` | `Unit` | `aeii.Unit` |
| `aeii.h` | `BattleSideView` | `aeii.CombatAnimation` |
| `aeii.e` | `MenuScreen` | `aeii.AuxDisplayable` |
| `aeii.d` | `SpriteImage` | `aeii.SpriteFrame` 与 `aeii.ImageProcessor` |
| `aeii.b` | `FileBrowser` | `aeii.FileSystemObject` |
| `a.a.a.g` | `Sprite` | `aeii.Sprite` 与 `com.one.file.Connector` |
| `a.a.a.a` | `FileSystemSupport` | `com.one.file.FileSystemRegistry` |
| `a.a.a.c` | `GameFileConnection` | `com.one.file.FileConnection` |
| `a.a.a.e` | `Jsr75FileConnectionAdapter` | `GenericFileConnection`（位于 `InternalConnections.java`） |
| `a.a.a.d` | `SiemensFileConnectionAdapter` | `SiemensFileConnection`（同上） |
| `a.a.a.b` | `MotorolaFileConnectionAdapter` | `MotorolaTypeAFileConnection`（同上） |
| `a.a.a.f` | `MotorolaFilePackageConnectionAdapter` | `MotorolaTypeBFileConnection`（同上） |
| `a.a.b` | `ChainedEnumeration` | `com.one.CompoundEnumeration` |
| `a.a.a` | `EmptyEnumeration` | `CompoundEnumeration` 构造器内的匿名空 Enumeration |

源码有 16 个 Java 文件，包含 19 个命名顶层类型，另有一个匿名空枚举器；`InternalConnections.java` 包含四个适配器。两组类的合并解释了 JAR 的 18 个 class。源码构建配置包含积极重载混淆选项，成员同名现象也与之相符。

这些对应说明前一轮按职责命名总体方向正确，也表明源码保留的模块边界更有助于阅读；无需为了名称统一立即改动用户提供的源码。

## 能直接补充的细节

| 功能 | 源码入口 | JAR 阅读版对应 |
| --- | --- | --- |
| 伤害与经验 | `Unit.attackUnit`，233 行 | `Unit.method_33`，253 行 |
| 攻击/防御加成 | `Unit.getOffenceBonusAgainstUnitEx` / `getDefenceBonusAgainstUnitEx`，180 / 215 行 | `Unit.method_30` / `method_32` |
| 随机区间 | `Renderer.randomFromRange`，120 行 | `GameCanvas.method_234` |
| 升级 | `Unit.setRank` / `promote`，87 / 265 行 | `Unit.method_22` / `method_36` |
| 反击资格 | `Unit.canPerformCloseAttack`，283 行 | `Unit.method_37`；源码多了一个未使用的 Unit 参数 |
| 攻击与反击顺序 | `MainDisplayable.createCombatAnimation`，9768 行 | `GameController.method_207` |
| 移动与地形代价 | `Unit.fillMoveRangeDataEx` / `getRequiredSteps`，637 / 701 行 | `Unit.method_52` / `method_53` |
| 地图读取 | `MainDisplayable.loadLevel`，3177 行 | `GameController.method_137` |
| 存档读写 | `getGameSaveData` / `loadGameSaveData`，995 / 1061 行 | `GameController.method_114` / `method_113`；JAR 将写入逻辑合入保存入口 |
| 剧情加载与执行 | `loadScript` / `updateCustomScript` / `updateScript`，7829 / 7924 / 8169 行 | `GameController.method_200` / `201` / `202` |
| 图像裁切和缩放 | `SpriteFrame` 与 `ImageProcessor.scaleImage` | `SpriteImage` 的实例方法和静态方法 |

本次核对的伤害、随机区间、反击条件与顺序、射程和移动代价，与此前读取的 JAR 逻辑一致；尚未做完整方法级等价审计或重新编译后的二进制对照。

### 两个可以明确解释的反编译问题

1. 当前反编译的脚本加载器把字符串行和分词数组错误合并到一个 `String[]` 局部变量。源码 `loadScript` 分别使用 `String s` 与 `String[] line`，正确表达了读取行、去注释、trim、分词的流程，可以辅助恢复这一段。
2. 当前反编译的 `Unit.method_22(byte)` 写成 `var1 <<= 1`，从 Java 源语言看会暗含 byte 截断。JAR 字节码实际只有 `ishl` 与 `istore`，没有 `i2b`；源码 `setRank` 使用 `int bonus = newrank * 2` 更准确表达了这个计算。进一步说明不能直接把反编译文本当作完全等价源码。

## 后续使用方式

1. 主读 `AEIIRM_src` 的类、字段、方法名和清楚的模块边界。
2. 记录源方法与 JAR 符号的关系；遇到差异或歧义，以选定 JAR 的字节码核对实际行为。
3. 第一关剧情单独记录资源差异，不因资源绝大多数相同就认定整份工程完全等价。
4. 在 Swift 中按单位规则、地图/回合、AI、剧情、渲染、存档逐步移植，保留可追溯的参考入口。

本次完成资源逐字节比较和关键代码路径检查；没有尝试构建旧 NetBeans 工程、启动 J2ME 模拟器或进行任何 iOS 验证。工程原配置依赖 Sony Ericsson Java ME SDK、CLDC 1.1 / MIDP 2.0 和 Java 1.3 源/目标设置，可编译性尚未确认。
