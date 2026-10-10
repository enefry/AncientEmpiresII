# 全兵种地图图标清晰化

2026-10-03，使用内置 imagegen，沿用已获认可的透明底弓箭手画风。`prompts.json` 保存公共提示词和每种兵种的生成提示词。参考图为原版四队地图渲染 `.noindex/artifacts/full-art-map.png` 和 `reference/art/imagegen-faithful-preview/archer-blue-preview.png`。

本目录保存 11 种新生成的透明底源图：士兵、水元素、法师、精灵、凶狼、石魔、投石车、飞龙、国王身体、骷髅、水晶。弓箭手继续使用已经接入并确认的原文件和打包结果，不重新生成。生成图保留主要配色、装备和辨识特征，轮廓与小细节经过重绘，不是原图逐像素复刻。

用户指出形象偏差后，按原地图图标、战斗原图和单位描述重新生成水元素、精灵、凶狼。`lizard`、`spider` 是历史资源键，实际身份分别为 WATER ELEMENTAL 和 DIRE WOLF，不能按文件名理解为蜥蜴、蜘蛛。水元素保留液体身形及涟漪底座；精灵保留圆形光环与白色光核；凶狼保留低伏、四足、长吻和上翘尾巴。三者当前提示词及参考图以 `identity-corrections.json` 为准，覆盖初版 `prompts.json` 中的对应条目。

## 游戏中的范围

- 12 种地图单位、单位详情、招募选择器共用高清图标。
- 国王只更换身体；保留原四种头部图标和原有偏移，维持角色身份。
- 水晶保持原固定粉色，四队完全一致；其他兵种各生成蓝、红、绿、黑四队版本。
- 战斗侧面动作、地形、建筑、菜单和特效继续使用此前原版 4× 增强资源。
- 两帧地图待机使用同一生成姿势，按原帧包围盒轻微平移 / 缩放，不声称已重新绘制行走或攻击动作。

## 打包

在项目根目录运行：

```sh
swift -module-cache-path .noindex/swift-module-cache tools/package_map_units.swift
```

打包工具去除近透明背景杂点，按原图每帧包围盒等比缩放、居中并落地，输出 96×192 两帧图集（单帧 96×96，逻辑尺寸 24×24）。水元素、飞龙和骷髅第二帧上移 4 像素形成轻微起伏，避免宽度约束导致两帧落地结果相同。国王身体保留头部区域的透明留白。

资源输出至 `Packages/AncientEmpiresEngine/Sources/AEContent/Resources/Enhanced/MapUnits/<兵种>/`：四队 PNG 和共用的 `team-mask.png`。面部、木材、金属、骨骼、翅膜、橙色岩石等非队伍区域不换色；饱和蓝色区域使用原游戏队伍的阴影、中间色、亮色、高光色梯度生成其他队伍。水晶遮罩全黑。运行时直接加载验证后的资源，不对场景进行颜色猜测。

弓箭手的原打包脚本与 `Enhanced/Archer` 保持兼容。`GameResources.generatedMapUnitData` 统一选择资源；`HighResolutionUnitArt` 在原图集裁帧入口替换地图单位，维持逻辑尺寸、灰化及调用方式。

## 回退

- `--classic-units`：所有地图单位恢复此前的原图 Scale2x 版本。
- `--classic-archer`：仅弓箭手恢复此前版本，兼容上一轮试用。
- `--original-art`：所有图片恢复原版。

均为启动参数，不修改保存数据或原 JAR 导出资源。
