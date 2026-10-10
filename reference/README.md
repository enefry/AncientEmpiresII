# 原版 Java 参考资料

这套资料来自用户选定的 v2.5 JAR，供 iOS 重制逐项对照。它是反编译参考，**不是原作者源码，也不是已经修复、能够重新编译运行的 Java 工程**。

## 从哪里开始

产品范围、用户已确认方向、启动动画专项要求、剧情与规则验收及待决定项，统一见 [完整需求文档](../docs/REQUIREMENTS.md)。工程结构、规则 / 表现边界、时序、剧情、保存及开发顺序见 [实现规划](../docs/IMPLEMENTATION_PLAN.md)；游戏实现与验证记录见 [原版对齐清单](../docs/ORIGINAL_ALIGNMENT.md)。

本目录保存原始研究资料。[还原资料总入口](reconstruction/README.md) 包含单位/地形表、战斗与移动算法、AI、八关剧情、完整文本、20 张地图、资源格式及离线浏览页；验证状态与待核对项分别记录。原始输入集中在 [inputs/](inputs/README.md)，高清美术源文件位于 `art/`。

新增用户提供的 `AEIIRM_src`：包含大量人工整理的类名、字段名和方法名，建议后续作为主要阅读材料；与 JAR 的关系、完整类对应及第一关脚本区别见 [源码对照报告](AEIIRM_SOURCE_COMPARISON.md)。选定 JAR 和字节码仍用于核对包内实际行为。

1. 阅读 [类命名映射](CLASS_NAMES.md) 和 [代码职责与细节索引](SOURCE_INDEX.md)，定位要研究的功能。
2. 优先使用 `java-named/` 中的语义命名版，例如 `Unit`、`GameController` 和 `BattleSideView`；字段和方法仍保留编号别名。`java-normalized/` 保存命名前的阅读版。
3. 与 `java-cfr/` 中另一款反编译器的结果交叉对照。
4. 发生冲突时，用 `bytecode/` 的 JVM 描述符和指令核对，再查看 `original/` 的原始 class / 数据文件。运行效果仍需原版运行环境验证。

已经核对伤害和随机数核心指令，示例见 [规则参考笔记](RULES_NOTES.md)。其余索引表示定位结果，不表示每项规则都已完整验证。

## 保存内容

| 路径 | 用途 |
| --- | --- |
| `original/` | 从选定 JAR 提取的 355 个非目录文件，包含 class、图片、音乐、地图、配置和语言表；逐个记录 SHA-256 |
| `java/` | Fernflower 反编译的 18 个类，保留混淆名称，14,531 行；可能有同名字段、同签名不同返回类型的方法及类型歧义 |
| `java-normalized/` | Fernflower 自动改名版，18 个类，15,509 行；`field_N` / `method_N` / `class_N` 是工具生成的别名，注释记录原符号和类型 |
| `java-named/` | 按职责重命名全部 18 个类型，同步修改构造器、继承/实现、import 和引用；仅替换类型标识符 |
| `java-cfr/` | CFR 0.152 独立反编译结果，18 个类；名称处理策略与 Fernflower 不同，作为第二参考 |
| `bytecode/` | 全部 18 个类的 `javap -p -c -s -constants` 清单，保留方法签名、描述符和指令偏移 |
| `symbol-map.json` | 原类到阅读版文件的 18 组映射，以及 949 个自动改名成员的原签名、声明和行号 |
| `class-names.json` / `semantic-class-map.json` | 人工命名配置及生成映射；记录职责、依据、原名、编号版、新名、路径与源文件哈希 |
| `inventory.json` | 输入和工具哈希、运行选项、文件清单、覆盖率和诊断 |
| `decompiler*.log` | 两种工具的完整运行日志；CFR 方法诊断另见 `java-cfr/summary.txt` |

输入：`reference/inputs/Ancient-Empires-II_J2ME_EN_v25/Ancient Empires II (2012)(Macrospace, SilentKnight)(v2.5).jar`。

输入 SHA-256：`f2b3e06190014940a04da70677d6504dd3fd3d9fbc39a49192926293f830f5ae`。

## 已验证与已知限制

- 已核对输入 JAR 哈希、355 个提取文件的哈希，以及每个类的原名版、阅读版和字节码文件覆盖。
- 两种工具均生成 18 个类。Fernflower 未发现方法反编译失败占位，但在 `aeii.d`（图片处理类）报告 `Heavily obfuscated exception ranges found!`。
- CFR 明确报告 `aeii.g.run()` 和 `aeii.h.b()` 无法完全结构化；对应代码带有警告，应参考 Fernflower 和字节码，不可直接移植。
- 两个工具都存在局部变量类型合并错误。例如剧情脚本加载器原符号 `aeii.g.e(int):void`：一个局部变量先装行文本，后装分词数组，两个工具都错误地把部分字符串操作写在 `String[]` 上。语义命名版位于 `GameController.method_200`，约第 6459 行。没有静默修补生成结果。
- 缺少 J2ME 和部分 Siemens / Motorola 厂商 API。即使消除了名称冲突，这些输出仍不能视作现代 JDK 可编译工程；本次没有尝试重新编译或运行游戏。
- 原作者注释、混淆前名称和源代码结构不能从普通 class 文件可靠恢复。人工理解得到的职责和字段含义放在旁边的文档中，不覆盖生成文件。

## 重现

在工程根目录运行：

```sh
python3 tools/restore_java_reference.py --cfr .noindex/tools/cfr-0.152.jar
```

默认使用本机 Android Studio 附带的 Fernflower。其它机器可用 `--decompiler /path/to/java-decompiler.jar` 指定工具；省略 `--cfr` 可只生成 Fernflower 和字节码资料。脚本不会自行下载工具。

CFR 来自 `https://www.benf.org/other/cfr/cfr-0.152.jar`，保存于稳定的 `.noindex/tools/`；工具 SHA-256 见 `inventory.json`。Java 版本及 Fernflower 工具哈希也已记录。

再次运行会更新脚本生成的代码、清单、映射和日志，并根据 `class-names.json` 生成语义命名版和 `CLASS_NAMES.md`。只需重新命名时可运行 `python3 tools/name_java_classes.py`。人工笔记应放在独立 Markdown 中，不修改生成文件；命名脚本发现语义命名版被编辑后会停止覆盖。`original/` 如有与输入不一致的既存文件，脚本会报错，不覆盖它们。

## 后续还原方式

先以这套未改写的资料固定原版依据，再逐个功能建立：原符号及 JVM 描述符 → 人工命名和规则解释 → 原版数据例子 → Swift 实现和对应测试。

遇到反编译歧义时先核对字节码；需要验证播放时序、画面、随机行为或剧情交互时，再运行原版作比较。提取完成、规则分析完成、重新编译成功、原版运行验证和 iOS 验证应分别记录。
