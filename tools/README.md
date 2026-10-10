# 开发与参考资料工具

从仓库根目录运行；原始输入统一位于 [reference/inputs/](../reference/inputs/README.md)。Python 图片分析和打包工具需要 Pillow；Swift 图片与音乐工具使用 macOS 系统框架。

| 工具 | 用途 |
| --- | --- |
| [test.sh](test.sh) | 二代 Swift Package 测试，复用 `.noindex/` 缓存和日志 |
| [audit_localization.py](audit_localization.py) | 检查三种语言的键和格式化占位符 |
| [export_ios_content.py](export_ios_content.py) | 导出二代原始资源与游戏数据；`--check` 只校验 |
| [export_bonus_skirmish.py](export_bonus_skirmish.py) | 转换历史追加自由对战地图 |
| [restore_java_reference.py](restore_java_reference.py) | 从二代 JAR 重建反编译、字节码及符号索引 |
| [name_java_classes.py](name_java_classes.py) | 按人工职责映射生成语义命名阅读版 |
| [catalog_game_reference.py](catalog_game_reference.py) | 解码并验证二代地图、文本、精灵、音乐和战役资料 |
| [reconstruct_startup.py](reconstruct_startup.py) | 研究二代启动标题动画的原始字节码 |
| `package_*.swift` / `package_interface_art.py` | 将 `reference/art/` 源图打包为游戏资源 |
| [prepare_environment_art.swift](prepare_environment_art.swift) | 准备环境美术参考图 |
| [render_all_music.sh](render_all_music.sh) / [render_midi.swift](render_midi.swift) | 渲染原版 MIDI 音乐 |
| [game_reference_viewer.html](game_reference_viewer.html) | 原始资料浏览入口 |

反编译工具的安装、版本及限制见 [reference/README.md](../reference/README.md)。内容导出和美术打包会更新已有资源；日常检查优先使用审计工具与 `export_ios_content.py --check`。
