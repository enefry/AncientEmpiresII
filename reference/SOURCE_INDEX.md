# 代码职责与细节索引

以下职责来自本次阅读反编译代码；名称是人工说明，不代表原作者使用的名称。当前阅读版按职责重新命名；完整命名依据见 [类命名映射](CLASS_NAMES.md)，原名与新名的路径映射见 `semantic-class-map.json`，字段和方法的编号映射见 `symbol-map.json`。

## 类职责

| 原类 | 阅读版 | 职责与依据 |
| --- | --- | --- |
| `aeii.Main` | [GameMIDlet.java](java-named/aeii/GameMIDlet.java) | MIDlet 入口；创建 Canvas，处理销毁 |
| `aeii.c` | [GameCanvas.java](java-named/aeii/GameCanvas.java) | Canvas、主循环、按键、资源读取、随机数、RecordStore、MIDI、振动；`run`、`keyPressed`、`createPlayer` 等入口 |
| `aeii.f` | [GameScreen.java](java-named/aeii/GameScreen.java) | 界面基类、语言表读取、文字换行和占位符替换；读取 `lang.dat` 的 `readInt/readUTF` 路径 |
| `aeii.g` | [GameController.java](java-named/aeii/GameController.java) | 主游戏控制器；地图、阵营、存档、回合、AI、战役剧情、菜单、地图编辑及旧网络功能；最大类 |
| `aeii.a` | [Unit.java](java-named/aeii/Unit.java) | 单位属性与实例、伤害、升级、状态、射程、移动可达范围、寻路和单位绘制；读取 `.unit` |
| `aeii.h` | [BattleSideView.java](java-named/aeii/BattleSideView.java) | 战斗画面与兵种演出；读取战斗背景、角色动画和残存人物数量 |
| `aeii.e` | [MenuScreen.java](java-named/aeii/MenuScreen.java) | 游戏内菜单、对话框、选择项和文字/图像面板；输入处理与 Graphics 绘制 |
| `aeii.d` | [SpriteImage.java](java-named/aeii/SpriteImage.java) | 图片加载、裁切、变换、缩放和 PNG 调色板处理；包含本次 Fernflower 警告 |
| `aeii.b` | [FileBrowser.java](java-named/aeii/FileBrowser.java) | 文件浏览、扩展名筛选和目录/文件排序 |
| `a.a.a.g` | [Sprite.java](java-named/a/a/a/Sprite.java) | 精灵帧与序列、更新/绘制和特效；也包含打开连接和包装文件 API 的静态工具 |
| `a.a.a.a` | [FileSystemSupport.java](java-named/a/a/a/FileSystemSupport.java) | 检测可用的标准及厂商文件 API，枚举存储根目录 |
| `a.a.a.c` | [GameFileConnection.java](java-named/a/a/a/GameFileConnection.java) | 文件连接统一接口 |
| `a.a.a.e` | [Jsr75FileConnectionAdapter.java](java-named/a/a/a/Jsr75FileConnectionAdapter.java) | 标准 JSR-75 FileConnection 适配 |
| `a.a.a.d` | [SiemensFileConnectionAdapter.java](java-named/a/a/a/SiemensFileConnectionAdapter.java) | Siemens FileConnection 适配 |
| `a.a.a.b` | [MotorolaFileConnectionAdapter.java](java-named/a/a/a/MotorolaFileConnectionAdapter.java) | Motorola `com.motorola.io` FileConnection 适配 |
| `a.a.a.f` | [MotorolaFilePackageConnectionAdapter.java](java-named/a/a/a/MotorolaFilePackageConnectionAdapter.java) | Motorola `com.motorola.io.file` FileConnection 适配 |
| `a.a.b` | [ChainedEnumeration.java](java-named/a/a/ChainedEnumeration.java) | Enumeration 合并和遍历工具 |
| `a.a.a` | [EmptyEnumeration.java](java-named/a/a/EmptyEnumeration.java) | 空 Enumeration 实现 |

## 常用查找入口

行号针对当前生成文件；重跑不同工具版本后应通过成员名和 `symbol-map.json` 重新定位。

| 想核对的细节 | 原版符号 / 位置 | 阅读版定位 |
| --- | --- | --- |
| 单位配置怎么读 | `aeii.a.a(aeii.g):void`；原名版 711 行 | `Unit.method_60`，803 行；搜 `MoveRange` / `AttackRange` / `HasProperty` |
| 伤害与经验积累 | `aeii.a.c(aeii.a):int`；原名版 190 行 | `Unit.method_33`，253 行 |
| 攻击和防御加成 | `aeii.a.a(aeii.a,int,int):int`、`a(int,int):int` | `Unit.method_30`、`method_32`，209 / 238 行 |
| 中毒等状态的数值修改 | `aeii.a.b():void`；原名版 241 行 | `Unit.method_40`，311 行；状态名称需结合语言和触发路径确认 |
| 升级与阈值 | `aeii.a.a():boolean`、`a(byte):void` | `Unit.method_36`、`method_22`；阈值 `method_35` |
| 反击条件 | `aeii.a.a(int,int):boolean`；原名版 223 行 | `Unit.method_37`，290 行 |
| 先攻击还是先反击 | `aeii.g.b(aeii.a,aeii.a):void`；原名版 7597 行 | `GameController.method_207`，8060 行附近 |
| 随机数边界 | `aeii.c.a(int,int):int`；原名版 104 行 | `GameCanvas.method_234`，148 行 |
| 射程形状和最小射程 | `aeii.a.a(byte[][],int,int):void` | `Unit.method_44`，351 行；曼哈顿距离筛选 |
| 移动范围与地形消耗 | `aeii.a.a(byte[][],int,int,int,int,byte,byte,boolean):boolean` 和 `a(int,int,byte,byte):int` | `Unit.method_52` / `method_53`，577 / 600 行 |
| 地形防御、移动消耗和图块映射 | `aeii.g.d():void` 初始化；原名版 525 行附近 | `GameController` 搜 `TypeDef` / `TileDef`，913 / 922 行；结合 `original/res/tiles0.prop` |
| 地图二进制读取 | `aeii.g.c(int):void`；原名版 2435 行 | `GameController.method_137`，2827 行；宽高、按 x/y 顺序读图块、附加记录和单位 |
| 存档读取 / 写入 | `aeii.g.a(byte[]):void`、`a(int,aeii.f):void`；原名版 785 / 865 行 | `GameController.method_113` / `method_114`，1153 / 1234 行；结合 Canvas 的 RecordStore 工具 |
| AI 行动和候选评分 | `aeii.g.o()`、`p()`、`e(aeii.a)`、`a(aeii.a,int,int,aeii.a,aeii.a):int` | `GameController.method_190` / `191` / `192` / `193`；原名版 5143 / 5171 / 5430 / 5709 行 |
| 剧情脚本读取 / 执行 | `aeii.g.e(int):void`、`r():void` | `GameController.method_200` / `method_201`，6459 / 6504 行附近；读取方法有类型推断错误 |
| 包内战役剧情状态机 | `aeii.g.s():void`；原名版 6161 行 | `GameController.method_202`，6620 行；第一关 `case 36` 调用通关处理 |
| 关卡完成处理 | `aeii.g.u():void`；原名版 7579 行 | `GameController.method_205`，8041 行 |
| 动画帧切片、序列和延时 | `a.a.a.g.a(String,int):void`；原名版 122 行 | `Sprite` 搜 `FrameDef` / `FrameSeq` / `FrameDelay` |
| MIDI 加载和循环 | `aeii.c.e(int):void`、`b(int,int):void`；原名版 607 / 635 行 | `GameCanvas` 搜 `Manager.createPlayer` / `setLoopCount` |
| 语言、剧情文本和按键占位符 | `aeii.f.a(String):int`、`a(int,boolean):String` | `GameScreen` 搜 `readUTF` / `%KM`；结合 `original/lang.dat` |

## 使用约定

- 不能只写“方法 a”，必须同时记录类名、参数和返回类型；JVM 可以存在 Java 源语言不允许的同名成员。
- 字节码中的 `descriptor` 和成员类型是精确身份；阅读版数字别名只是定位辅助。
- 新发现写进人工笔记，包含证据位置和验证状态；生成 Java 保持工具输出原样。
- 疑难控制流优先核对字节码，必要时用原 JAR 运行验证。不能因为两个反编译器恰好给出相似错误就认定其正确。
