# 启动标题动画：原算法、原资源与验证

用户录屏中，`ANCIENT EMPIRES II` 的两行字从中央扭动展开，随后人物背景淡入，标题有斜向扫光。此段算法已经定位并执行原始 JAR 的对应函数，提供 [参考回放](startup/title-animation.png)、[逐帧图](startup/wave-contact.png)，也可在 [资料浏览页](viewer.html) 的“启动动画”查看与拖动帧进度。

**确定的结果：文字展开的全部测试裁切和位移指令与原始字节码一致；原状态函数的淡入、扫光和闪烁逻辑已执行。** 这不等于整段录屏的像素、音频、实际播放时刻都已达到 100% 对齐。手机字体、平台透明混合、录屏缩放和录制版本仍是独立验证项。本次没有开发 iOS 游戏。

## 输入版本和启动开关

基准 JAR SHA-256：`f2b3e06190014940a04da70677d6504dd3fd3d9fbc39a49192926293f830f5ae`。其清单包含 `Override-FPS: 15`、`Disable-Intro: true`。

`Disable-Intro` 被读取为 `Renderer.nointro`。每次启动过渡更新时，如果该值为 true 且 `introTransMode < 4`，代码创建 logo，设置状态 3、进度 40，然后同次更新将进度增加到 41，直接进入状态 4、淡入进度 0。**这份包按此配置会跳过扭动展开，而录屏实际播放了展开。** 因此录屏对应的运行配置或版本不能默认为这个未修改 JAR；仅靠画面无法确定具体版本。

参考回放明确从“未禁用展开”的状态 3、进度 0 开始。没有改 JAR 清单，也没有替换任何原图片。状态 0 的发行商 Logo、`intro0/intro1` 前情介绍，以及录屏最后的圆形菜单，是另外的阶段，不包含在此次标题回放中。

## 证据位置

| 功能 | 人工整理源码 | 选定 JAR 实际方法 / 字节码位置 |
| --- | --- | --- |
| 启动阶段更新 | [MainDisplayable.updateIntroTransition](../inputs/AEIIRM_src/src/aeii/MainDisplayable.java:3745) | `aeii.g.l()V`，[字节码](../bytecode/aeii/g.javap.txt:11101) |
| 启动阶段绘制 | [MainDisplayable.paintIntroTransition](../inputs/AEIIRM_src/src/aeii/MainDisplayable.java:5682) | `aeii.g.e(Graphics)V`，[字节码](../bytecode/aeii/g.javap.txt:16548) |
| 逐行扭动与展开 | [MainDisplayable.drawWavedImage](../inputs/AEIIRM_src/src/aeii/MainDisplayable.java:6611) | `aeii.g.a(Graphics,int,int,int,aeii.d,int,int,int)V`，[字节码](../bytecode/aeii/g.javap.txt:19676) |
| 整数正弦表生成 | [PaintableObject.createSinTab](../inputs/AEIIRM_src/src/aeii/PaintableObject.java:261) | `aeii.f.c()V`，[字节码](../bytecode/aeii/f.javap.txt:509) |
| 查表 | [PaintableObject.sin](../inputs/AEIIRM_src/src/aeii/PaintableObject.java:283) | `aeii.f.a(int)S`，[字节码](../bytecode/aeii/f.javap.txt:574) |
| PNG 缩放 / 绘制 | [ImageProcessor](../inputs/AEIIRM_src/src/aeii/ImageProcessor.java)、[SpriteFrame](../inputs/AEIIRM_src/src/aeii/SpriteFrame.java) | `aeii.d`，[字节码](../bytecode/aeii/d.javap.txt) |
| 主循环 | [Renderer.run](../inputs/AEIIRM_src/src/aeii/Renderer.java:674) | `aeii.c.run()V`，阅读版 [GameCanvas](../java-named/aeii/GameCanvas.java:610) |

源码 `drawWavedImage` 保留了方向与总进度参数；选定 JAR 的方法体已经把它们简化为横向偏移、总进度 40，忽略传入的方向参数。重制此段应按 JAR 实际横向行为，不将源码的纵向分支当作这个包已执行的路径。

## 原资源与坐标

| 资源 | 尺寸 | 用途 |
| --- | --- | --- |
| [logo.png](../original/res/logo.png) | 240×85 | 完整文字，包含透明背景 |
| [splash.png](../original/res/splash.png) | 240×320 | 人物和红色背景，标题部分含透明区域 |
| [glow.png](../original/res/glow.png) | 67×78 | 斜向扫光，绘制后被 splash 的不透明部分遮挡 |
| [ms_logo.png](../original/res/ms_logo.png) | 89×88 | 更早的发行商阶段 |

标题位于 `(0,0)`，不是放在屏幕中央；居中的是展开的可见窗口。图片先按画布宽度等比缩放，目标高度使用整数 `原高×目标宽/原宽`。240 像素画布下，logo 为原生 240×85，不需任何浮点缩放。

JAR 图片缩放采用 16 位定点坐标：放大取对应源点，缩小时按整数扫描窗口平均 ARGB，各分量分别计算。不要自动换成系统双线性插值。未来 iOS 显示可以先在固定的逻辑像素画布执行原算法，再整体显示到屏幕；不能让每条逻辑扫描行随屏幕倍率改变厚度或取整规则。

## 文字展开的精确算法

令 `p=0…40`、`W=logo宽度`、`H=logo高度`，标题使用 `waves=1`，坐标 `(x,y)=(0,0)`。所有除法按 Java 整数除法，负值向零截断；右移为有符号算术右移。

```text
visibleWidth = W * p / 40
amplitude    = W * (40 - p) / 160
angle        = 360 * p / 40
angleStep    = 360 * waves / H

对 row = 0…H-1：
    offset = (amplitude * sineTable[angle % 360]) >> 10
    setClip(x + W/2 - visibleWidth/2 + offset,
            y + row, visibleWidth, 1)
    drawEntireLogo(x + offset, y)
    angle += angleStep
```

每行绘制的是完整原图，只允许当前的一像素高窗口显示。原图和窗口同向位移，窗口逐渐变宽；没有横向压缩文字、没有拉伸字形、没有随机数。初始波幅最大，可见宽度为 0；最终波幅为 0，原图完整显现。

240×85 时 `visibleWidth=6p`、`amplitude=(240×(40−p))/160`、初始角度 `9p`、每行角度增量 `360/85=4`。**不要改成浮点的 `360.0/85`**：最后一行相位相对首行增加 336°，不是严格的一整圈。

| p | 可见宽度 | 波幅 | 首行相位 |
| --- | --- | --- | --- |
| 0 | 0 | 60 | 0° |
| 10 | 60 | 45 | 90° |
| 20 | 120 | 30 | 180° |
| 30 | 180 | 15 | 270° |
| 40 | 240 | 0 | 360° → 查表 0° |

正弦表不是 `round(sin(angle)×1024)`，原表的 360 个值保存在 [algorithm-verification.json](startup/algorithm-verification.json)。生成规则为：

```text
step = 360 * 10000 / 2 / 31415   // =57
velocity = 1024 * step
position = 0
对 angle = 0…359：
    value = position / step
    sineTable[angle] = short(value)
    velocity -= value
    position += velocity / step
最后强制 sineTable[180]=0、sineTable[270]=-1024
```

直接使用系统正弦会改变部分行的偏移。Python 的 `//` 对负数向下取整，也不能代替 Java 除法；参考工具显式使用向零截断。位移计算的 `>>10` 则按原版算术右移，不能改成向零除以 1024。

## 展开后的状态和层叠顺序

状态 3 每次更新先 `p++`。`p≤40` 时绘制展开；`p>40` 时加载 splash/glow，切换状态 4 并设 `q=0`，清除按键状态。资源缺失时有另一条降级路径，参考回放使用资源完整的正常路径。

状态 4 的 `q<16` 阶段，每次更新 `q++`，扫光位置重置为 `−67`。绘制顺序是：splash → 黑色透明覆盖层 → 完整 logo。覆盖层 alpha 为 `255×(16−q)/16`，因此 logo 一直保持清晰，人物背景由黑色逐步显现。`q=16` 时进入稳定绘制。

稳定阶段顺序：完整 logo → 若干条 glow → splash → 可选 `PRESS ANY KEY`。**splash 最后覆盖 glow 是效果的一部分**，不能将扫光画在整个封面最上面。

```text
若 glowX >= canvasWidth * 4：glowX = -glowWidth
否则：glowX += logoWidth / 6

count = (logoHeight + glowHeight - 1) / glowHeight
对 i = 0…count-1：
    drawGlow(4 + glowX - 38*i, 6 + glowHeight*i)
```

240 像素画布下每次移动 40 像素；初始 `glowX=−67`，之后为 `−27,13,53,…,973`，下一次重置 `−67`。总循环为 27 次更新，理想约 1.782 秒；多数时间扫光在标题外。此处绝不是线性重复 240 像素宽度就立即复位。

提示文本在稳定阶段，当 `delayCounter % 100 == 0` 翻转显示标志；逻辑计数每次更新加 50，因此每两次更新翻转一次，完整亮灭周期为四次更新。字体为 MIDP 系统粗体小号，白字黑色八邻域描边，锚点 33（水平居中、底部对齐），位于 `height−按钮高度−1`。字体本身不在 PNG 内；手机或模拟器字体会影响实际像素。参考回放省略该字体，只记录原始显隐标志。标志相位还依赖此前启动流程累计的逻辑计数。

## 计时与录屏对照

目标循环间隔使用 `1000/15=66` 毫秒，扣除本次更新、绘制所耗时长，再 sleep；不是精确的 20 FPS，也不是逻辑计数中的 50 毫秒。耗时超过目标时不会补帧追赶。隐藏窗口和平台暂停也会停更新。

从 p=0 到 p=40 理想经过 40 次更新，即 2.640 秒；再一次更新进入淡入 q=0，然后 16 次更新到 q=16，约再 1.122 秒。录屏开始时已经在展开过程中，不能把录屏第 0 秒认定为 p=0。

提供的录屏为 978×1294、120 FPS、5.720 秒，仅检测到视频流。针对可见标题区域，使用固定裁切、重采样、亮度前景形状比较，匹配到 p=6…39 中 31 个不同进度。估计每步 65.028 毫秒，与 66 毫秒目标接近；此估计受 30 FPS 采样、录屏缩放、压缩、裁切和阈值影响，不用于宣称像素一致。完整结果见 [录屏比较](startup/recording-comparison.json)。

APNG 回放使用 66 毫秒逻辑间隔、最近邻 2 倍显示。GIF 另有 10 毫秒时间粒度和调色板限制，仅为方便预览。浏览器实际播放调度也不是原手机计时证据。

## 验证方式与复现

录屏末尾的圆形菜单另已定位到 [AuxDisplayable.initCircleMenuAuxDisplayable](../inputs/AEIIRM_src/src/aeii/AuxDisplayable.java:637)、[开场推进](../inputs/AEIIRM_src/src/aeii/AuxDisplayable.java:1227)、[圆周绘制](../inputs/AEIIRM_src/src/aeii/AuxDisplayable.java:1417)，选定 JAR 阅读版分别在 [MenuScreen.method_101](../java-named/aeii/MenuScreen.java:616)、[开场推进](../java-named/aeii/MenuScreen.java:1058)、[绘制](../java-named/aeii/MenuScreen.java:1194)。初始角度均分圆周、半径从 0 以 `max(1,目标半径/5)` 增长，角度每步增加半扇区；半径到位后，角度步长改为 `clamp(abs(360−首项角度)/2,1,半扇区)`，转回首项角度 0 才结束演出。图标圆心采用同一整数 sin/cos 查表。按键可以直接结束演出；按键时间不能从静态资源推断。菜单半径和绝对坐标受 MIDP 字体基线影响，此段目前只有源码与 JAR 反编译交叉定位，尚未纳入本次隔离执行或参考回放。

工具 [reconstruct_startup.py](../../tools/reconstruct_startup.py) 从原始 JAR 的 `aeii.f/aeii.g` 中保留上述方法的原 `Code` 属性与常量池，移除无关成员以隔离厂商 API 依赖。JVM 实际执行这些未改写的指令；平台替身只记录 `setClip`、图片位置和透明覆盖调用。没有把反编译 Java 重新编译后当作原版验证。

原正弦表 360 项与参考实现相等；3 种宽度（176/240/320）×3 种波数（1/2/4）×41 个进度，共 369 组、31,980 行绘制指令，逐项比较全部相等，差异 0。还实际执行了展开至淡入、扫光步进和复位、闪烁标志、禁用 Intro 直接跳过的路径。所有保留方法的 Code 属性和原 class 哈希都在 [验证报告](startup/algorithm-verification.json) 中。

```sh
python3 tools/reconstruct_startup.py \
  --recording '/Users/chenrenwei/Desktop/Screen Recording 2026-10-01 at 8.05.02 AM.mov'
python3 tools/catalog_game_reference.py
```

需要 Python 3、Pillow、Java/Javac；录屏比较另需 FFmpeg。省略 `--recording` 可独立复现 JAR 验证。产物放在 `reference/reconstruction/startup/`，辅助 JVM 文件复用 `.noindex/artifacts/startup/jvm/`。

要将“100%”扩大到整个屏幕的播放效果，还须固定录屏对应 JAR/配置、逻辑画布尺寸、系统字体、J2ME `drawRGB` 透明混合、输入时点、MIDI 音源，并验证前情屏幕和圆形菜单。当前已经具备精确复刻这段文字扭动算法的依据，不能以参考回放代替这些尚未完成的验证。
