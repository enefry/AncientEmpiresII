# 剧情插画高清重绘

使用内置 imagegen 对照 `reference/original/res/intro0.png` 至 `intro5.png` 分别编辑，保留原版构图、人物特征、服装、场景与色调。完整生成提示词保存在 `prompts.json`，此目录 PNG 为生成源图。

- intro0：加拉玛与瓦拉多恩兄弟背靠背肖像。
- intro1：水晶预言的羊皮卷地图。
- intro2：勇气神殿遇袭，骷髅兵与蓝甲守卫。
- intro3：抵达生命神殿。
- intro4：远古城堡废墟。
- intro5：结局预言中的黑暗施法者。

运行 `swift -module-cache-path .noindex/ModuleCache tools/package_story_art.swift`，将源图等比适配至原图宽高的 4 倍，输出到 `Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Story`。等比适配的小幅尺寸差以黑边填充，不裁去人物、武器或建筑。

`OriginalArt` 统一加载新资源，原逻辑尺寸、剧情文字、滚动与章节跳转保留。当前正式流程使用 intro2、intro3、intro4、intro5；intro0 与 intro1 同时完成资源增强，不新增剧情入口。`--classic-story` 回退至此前 Scale2x 增强，`--original-art` 或 `useEnhanced: false` 读取原图。原版导出文件不修改。
