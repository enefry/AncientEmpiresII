# 启动页高清重绘

`splash-characters.png` 使用内置 imagegen 对照原版 `splash.png` 重绘，仅负责标题下方的四角色画面。原版英文标题和透明光扫结构由打包脚本原位保留，避免生成模型改变文字。原厂商标识在现代长屏的铺满显示下会被放大并截断，因此不叠加到高清角色层。

运行 `swift -module-cache-path .noindex/ModuleCache tools/package_startup_art.swift` 输出 960x1280 的 `Resources/Enhanced/Startup/splash.png`。游戏逻辑尺寸仍为 240x320，标题光扫、点击提示和音乐时序不变。`--classic-startup` 可回退此前的原图增强，`--original-art` 读取 1x 原图。

生成提示词要求保留中央金发国王、左侧有面部伤疤的兄弟、右侧骷髅战士、后方角盔反派及红黑构图；不生成标题、文字、标识或额外人物。
