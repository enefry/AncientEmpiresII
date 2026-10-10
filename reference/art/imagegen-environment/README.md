# 英雄头像、建筑与地图地形

2026-10-03，按用户要求延续已确认的清晰美术，使用内置 imagegen 生成源图。原图是身份、布局和配色依据，弓箭手只作为线条与明暗风格参考。

## 内容

- `head-0...3.png`：地图、属性、招募中的四种英雄头部；保留棕发胡须、封闭钢盔、金发、双角面甲四种身份。
- `portrait-0...5.png`：六张剧情胸像；保留原发型、面部标记、服装、朝向和成熟人物比例。
- 森林、雪山、低丘、古代遗迹、废墟、村庄、城堡、桥梁、火山：按原地图地物生成，保持原地格占位。
- `water`、`earth`、`ground`：水面、泥路、浅色地面的低对比材质。水岸、道路连接形状沿用原图，仅在原地形区域内加入生成材质，边界不参与纹理变化。保留连接边界而非让模型猜测可拼接地图。
- 村庄屋顶、城堡旗帜共用轮廓与显式换色遮罩，使用原队伍色阶生成蓝、红、绿、黑版本及中立灰色。遮罩同时限制屋顶 / 中央旗帜位置，排除蓝灰石墙及地面阴影，石材与金边不随归属变化。

完整请求分别记录在 `hero-prompts.json` 和 `terrain-prompts.json`。`tools/prepare_environment_art.swift` 从原版导出参考图，参考图位于稳定目录 `.noindex/artifacts/environment-originals`。

## 打包与接入

```sh
swift -module-cache-path .noindex/swift-module-cache tools/prepare_environment_art.swift
swift -module-cache-path .noindex/swift-module-cache tools/package_environment_art.swift
```

打包输出到 `Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/Environment`：地图头部图集 208×48、剧情头像图集 2040×340、48 张 96×96 地块、2 张建筑换色遮罩。源图不打包到 app。

透明素材按原有占位等比适配。地图头部仍为 13×12 逻辑尺寸，保留国王身体合成的 `(7, 0/1)` 锚点；剧情头像仍为 85×85。地块仍为 24×24，地形类型、通行、防御、建筑占领、地图及小地图规则不变。原版 JAR 导出文件不修改。

`HighResolutionUnitArt` 优先读取生成资源并验证尺寸，复用限额缓存；缺图时可沿用原图增强。`--classic-environment` 回退本目录所有替换，`--original-art` 回退全部高清资源。战斗中的侧面头部和背景仍沿用此前原图增强，此次优化的是地图与剧情展示。

## 验证

构建通过，28/28 项模拟器测试通过，362 个原版资源校验通过。结果包 `.noindex/artifacts/environment-verified.xcresult`；资源和地图预览分别为 `.noindex/artifacts/environment-atlas.png`、`.noindex/artifacts/environment-map.png`。已检查普通启动画面，未验证真机。
