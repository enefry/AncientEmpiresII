# 类语义命名映射

按已核实职责命名全部 18 个类型（包含一个接口），保留原来的包结构。名称是人工赋予的阅读名称，不是恢复的原作者名称。

阅读代码优先使用 `java-named/`；原版与编号版仍保留，用于核对。字段和方法继续使用 `field_N` / `method_N`，本次只重新命名类型。

| 原名 | 新名称与代码 | 职责 | 命名依据 |
| --- | --- | --- | --- |
| `aeii.Main` | [GameMIDlet](java-named/aeii/GameMIDlet.java) | J2ME 游戏应用入口和生命周期 | 继承 MIDlet；startApp 创建 Canvas，destroyApp 关闭游戏。 |
| `aeii.c` | [GameCanvas](java-named/aeii/GameCanvas.java) | 画布、主循环、输入与平台服务 | 继承 Canvas，实现 Runnable；处理 paint、keyPressed、RecordStore、MIDI 和资源读取。 |
| `aeii.f` | [GameScreen](java-named/aeii/GameScreen.java) | 游戏界面基类和文字工具 | 提供界面尺寸、更新和绘制入口，派生类为主游戏与菜单；兼有语言表、文字换行和占位符处理。 |
| `aeii.g` | [GameController](java-named/aeii/GameController.java) | 游戏总控制器 | 管理地图、单位、阵营、回合、AI、存档、剧情、菜单、编辑器及旧网络功能；保留原有多职责结构。 |
| `aeii.a` | [Unit](java-named/aeii/Unit.java) | 战场单位与兵种规则 | 继承精灵类；读取 .unit，维护位置和生命值，计算伤害、升级、射程、移动范围和路径。 |
| `aeii.h` | [BattleSideView](java-named/aeii/BattleSideView.java) | 战斗演出中的单方画面 | 构造器接收控制器、一个单位和对方视图；左右双方分别实例化并互相引用，维护各自背景、人物与特效。 |
| `aeii.e` | [MenuScreen](java-named/aeii/MenuScreen.java) | 菜单、对话框与选择面板 | 继承界面基类；根据面板类型处理选项、文字、图像、按键和绘制。 |
| `aeii.d` | [SpriteImage](java-named/aeii/SpriteImage.java) | 精灵图像封装与图像处理 | 封装 J2ME Image、尺寸和区域；支持裁切、变换、缩放、绘制及 PNG 调色板处理。 |
| `aeii.b` | [FileBrowser](java-named/aeii/FileBrowser.java) | 文件浏览与筛选 | 维护当前路径，切换目录，枚举目录与文件，按扩展名过滤并排序。 |
| `a.a.a.g` | [Sprite](java-named/a/a/a/Sprite.java) | 精灵、帧序列、动画与特效 | 读取 .sprite，保存帧图像与序列，执行更新和绘制；同时混入静态连接工具，本次不拆分。 |
| `a.a.a.a` | [FileSystemSupport](java-named/a/a/a/FileSystemSupport.java) | 文件 API 检测与根目录枚举 | 用 Class.forName 检测标准、Siemens 和 Motorola 文件 API，再选择相应的根目录枚举接口。 |
| `a.a.a.c` | [GameFileConnection](java-named/a/a/a/GameFileConnection.java) | 游戏使用的统一文件连接接口 | 继承 StreamConnection，统一目录枚举、创建、存在检查与截断操作。 |
| `a.a.a.e` | [Jsr75FileConnectionAdapter](java-named/a/a/a/Jsr75FileConnectionAdapter.java) | 标准 JSR-75 文件 API 适配 | 包装 javax.microedition.io.file.FileConnection，并实现统一文件连接接口。 |
| `a.a.a.d` | [SiemensFileConnectionAdapter](java-named/a/a/a/SiemensFileConnectionAdapter.java) | Siemens 文件 API 适配 | 包装 com.siemens.mp.io.file.FileConnection，并实现统一文件连接接口。 |
| `a.a.a.b` | [MotorolaFileConnectionAdapter](java-named/a/a/a/MotorolaFileConnectionAdapter.java) | Motorola io 命名空间的文件 API 适配 | 包装 com.motorola.io.FileConnection；与 io.file 版本区分。 |
| `a.a.a.f` | [MotorolaFilePackageConnectionAdapter](java-named/a/a/a/MotorolaFilePackageConnectionAdapter.java) | Motorola io.file 命名空间的文件 API 适配 | 包装 com.motorola.io.file.FileConnection；FilePackage 指原 API 的 .file 包，并非功能更强或版本更新。 |
| `a.a.b` | [ChainedEnumeration](java-named/a/a/ChainedEnumeration.java) | 串联多个枚举器 | 保存一组 Enumeration，并在当前枚举器耗尽后依次切换。 |
| `a.a.a` | [EmptyEnumeration](java-named/a/a/EmptyEnumeration.java) | 空枚举器 | hasMoreElements 始终为 false，nextElement 抛出 NoSuchElementException；用于枚举链初始状态。 |

## 变换与验证

- 同步修改文件名、类型声明、构造器、继承/实现、import 和类型引用；保留包名。
- 注释、字符串、字符常量和 `renamed from` 原符号说明保持原样；没有改写 JAR、Manifest 或反射字符串。
- 每个文件都验证：逆向替换新类名后与编号版完全一致。因此该变换只修改类标识符，不修复原有反编译问题。
- 原名、编号版、新名称、文件路径与哈希记录在 `semantic-class-map.json`，字段/方法映射仍见 `symbol-map.json`。
- 单独重新生成：`python3 tools/name_java_classes.py`。重新反编译的主脚本也会生成这套阅读版。
- 仍属于参考源码，未重新编译或运行验证；`Sprite` 与 `GameController` 的多职责原样保留，后续 Swift 移植时再考虑拆分。
