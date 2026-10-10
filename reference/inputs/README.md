# 原始参考输入

| 路径 | 用途 |
| --- | --- |
| [Ancient-Empires-II_J2ME_EN_v25/](Ancient-Empires-II_J2ME_EN_v25/) | 用户提供的二代 v2.5 JAR 和原始提取内容 |
| [AEIIRM_src/](AEIIRM_src/) | 人工整理的 Java 参考源码、原始资源与 NetBeans 构建配置 |
| [Ancient-Empires_icon/](Ancient-Empires_icon/) | 原图标包附带的使用说明 |
| [Restored Ancient Empires II Logo.png](<Restored Ancient Empires II Logo.png>) | 原提供的恢复版标志图片 |

二代基准包为 `Ancient-Empires-II_J2ME_EN_v25/Ancient Empires II (2012)(Macrospace, SilentKnight)(v2.5).jar`，SHA-256：`f2b3e06190014940a04da70677d6504dd3fd3d9fbc39a49192926293f830f5ae`。源码与包内脚本的区别见 [源码对照报告](../AEIIRM_SOURCE_COMPARISON.md)。

上述输入的文件内容保留不变，工具从本目录读取。重复的 ZIP 包保留在仓库本地 `.noindex/reference-archives/`，原始 MP4 保留在 `.noindex/reference-videos/`，历史美术备份仍在 `资源备份/`；它们均被 Git 忽略。Java 工程中的 `nbproject/private/` 为本机设置，也不提交。
