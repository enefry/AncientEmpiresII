# 战役状态机逐项证据

来自 AEIIRM_src 的 updateScript。保留条件、坐标与调用；尚未逐状态核对 JAR 字节码。

moveMapAndCursor 自带 scriptState++，不能把没有显式 ++ 的 case 误判为不前进。

## m0 — TEMPLE RAIDERS

### Case 0（源码约 8313 行）

```java
case 0:
	++scriptState;
	break;
```

### Case 1（源码约 8316 行）

```java
case 1:
	PaintableObject.currentRenderer.setCurrentDisplayable(auxMapNameMessage);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 2（源码约 8321 行）

```java
case 2:
	updateAlphaDrap = true;
	getUnit(0, 8, (byte)0).plotRoute(3, 8, false);
	getUnit(1, 9, (byte)0).plotRoute(4, 9, false);
	getUnit(0, 10, (byte)0).plotRoute(3, 10, false);
	moveMapAndCursor(5, 9);
	break;
```

### Case 3（源码约 8328 行）

```java
case 3:
	moveMapAndCursor(9, 3);
	drawCursorFlag = true;
	break;
```

### Case 4（源码约 8332 行）

```java
case 4:
	setScriptDelay(10);
	mapStepMax = 12;
	Unit.speed = Unit.UNIT_SPEED_SLOW;
	++scriptState;
	break;
```

### Case 5（源码约 8338 行）

```java
case 5:
	createDialogScreen(PaintableObject.getLocaleString(221), (byte)2, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 221: Help us! We are under attack! These skeleton raiders have stolen the Crystal of Courage we swore to protect!

### Case 6（源码约 8343 行）

```java
case 6:
	Unit var12 = getUnit(9, 3, (byte)0);
	Renderer.vibrate(100);
	var12.scheduleIdleAnimationStop(400);
	createSimpleSparkSprite(sprRedSpark, var12.currentX, var12.currentY, 0, 0, 2, 50);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 7（源码约 8351 行）

```java
case 7:
	createDialogScreen(PaintableObject.getLocaleString(222), (byte)2, (byte)4);
	++scriptState;
	break;
```

> 文本 222: Aargh... we've failed!

### Case 8（源码约 8355 行）

```java
case 8:
	var11 = getUnit(9, 3, (byte)0);
	createSimpleSparkSprite(sprSpark, var11.currentX, var11.currentY, 0, 0, 1, 50);
	createSimpleSparkSprite(sprSmoke, var11.currentX, var11.currentY, 0, -3, 1, 100);
	var11.removeThisUnit();
	setScriptDelay(20);
	++scriptState;
	break;
```

### Case 9（源码约 8363 行）

```java
case 9:
	drawCursorFlag = false;
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

### Case 10（源码约 8367 行）

```java
case 10:
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 11（源码约 8371 行）

```java
case 11:
	createDialogScreen(PaintableObject.getLocaleString(223), (byte)0, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 223: Captain, we must stop this menace at once!

### Case 12（源码约 8376 行）

```java
case 12:
	createDialogScreen(PaintableObject.getLocaleString(224), (byte)5, (byte)4);
	++scriptState;
	break;
```

> 文本 224: Forward troops! Make these raiders pay!

### Case 13（源码约 8380 行）

```java
case 13:
	startNormalPlay();
	++scriptState;
	break;
```

### Case 14（源码约 8384 行）

```java
case 14:
	if(statusBarOffset == 0)
	{
		currentHelp = 0;
		++scriptState;
	}
	break;
```

### Case 15（源码约 8391 行）

```java
case 15:
	if(currentTurningPlayer == 0 && gameState == 1 && alphaWindowSize == 0)
	{
		currentHelp = 1;
		++scriptState;
	}
	break;
```

### Case 16（源码约 8398 行）

```java
case 16:
	if(currentTurningPlayer == 0 && lastFinishedMoveUnit != null)
	{
		currentHelp = 2;
		++scriptState;
	}
	break;
```

### Case 17（源码约 8405 行）

```java
case 17:
	if(currentTurningPlayer == 0 && lastFinishedMoveUnit != null)
	{
		currentHelp = 3;
		++scriptState;
	}
	break;
```

### Case 18（源码约 8412 行）

```java
case 18:
	if(countUnits(-1, 2, (byte)0) >= 3)
	{
		currentHelp = 4;
		++scriptState;
	}
	else if(currentTurn >= 1)
	{
		++scriptState;
	}
	break;
```

### Case 19（源码约 8423 行）

```java
case 19:
	if(currentTurn >= 2)
	{
		currentHelp = 5;
		++scriptState;
	}
	break;
```

### Case 20（源码约 8430 行）

```java
case 20:
	currentHelp = 6;
	++scriptState;
	break;
```

### Case 21（源码约 8434 行）

```java
case 21:
	if(gameState == 1 && currentTurningPlayer == 0)
	{
		currentHelp = 7;
		++scriptState;
	}
	break;
```

### Case 22（源码约 8441 行）

```java
case 22:
	if(countUnits(-1, -1, (byte)1) == 0)
	{
		activeFlag = false;
		setScriptDelay(20);
		++scriptState;
	}
	break;
```

### Case 23（源码约 8449 行）

```java
case 23:
	moveMapAndCursor(1, 1);
	break;
```

### Case 24（源码约 8452 行）

```java
case 24:
	Unit.createUnit((byte)1, (byte)1, 1, 1).plotRouteEx(1, 2, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 25（源码约 8457 行）

```java
case 25:
	moveMapAndCursor(10, 10);
	break;
```

### Case 26（源码约 8460 行）

```java
case 26:
	Unit.createUnit((byte)0, (byte)1, 10, 10).plotRouteEx(10, 9, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 27（源码约 8465 行）

```java
case 27:
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

### Case 28（源码约 8468 行）

```java
case 28:
	createDialogScreen(PaintableObject.getLocaleString(225), (byte)5, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 225: More attackers!

### Case 29（源码约 8473 行）

```java
case 29:
	createDialogScreen(PaintableObject.getLocaleString(226), (byte)0, (byte)4);
	activeFlag = true;
	++scriptState;
	break;
```

> 文本 226: These are no raiders! They look like enemy troops! Be careful, Captain!

### Case 30（源码约 8478 行）

```java
case 30:
	if(countUnits(-1, -1, (byte)1) == 0 && gameState == 0)
	{
		activeFlag = false;
		drawCursorFlag = false;
		setScriptDelay(30);
		++scriptState;
	}
	break;
```

### Case 31（源码约 8487 行）

```java
case 31:
	drawAlphaDrap = true;
	drapAlphaValue = 0;
	setScriptDelay(20);
	++scriptState;
	break;
```

### Case 32（源码约 8493 行）

```java
case 32:
	createDialogScreen(PaintableObject.getLocaleString(227), (byte)2, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 227: Thank you, your Majesty, you must help us return the Crystal as quickly as possible. Such an important relic must not fall into the wrong hands!

### Case 33（源码约 8498 行）

```java
case 33:
	createDialogScreen(PaintableObject.getLocaleString(228), (byte)0, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 228: I am at your service. Who could possibly be after the Crystal?

### Case 34（源码约 8503 行）

```java
case 34:
	createDialogScreen(PaintableObject.getLocaleString(229), (byte)2, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 229: I fear these criminals may be allied to a greater evil. It would be wise to consult with the High Priest at the Temple of Wisdom. He may know more of this.

### Case 35（源码约 8508 行）

```java
case 35:
	createDialogScreen(PaintableObject.getLocaleString(230), (byte)0, (byte)4);
	setScriptDelay(15);
	++scriptState;
	break;
```

> 文本 230: I shall send my brother Valadorn there immediately. I will take my troops and follow the attackers who got away.

### Case 36（源码约 8513 行）

```java
case 36:
	completeMission();

```

## m1 — TO THE RESCUE

### Case 1（源码约 8521 行）

```java
case 1:
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 2（源码约 8525 行）

```java
case 2:
	mapStepMax = 4;
	moveMapAndCursor(12, 3);
	break;
```

### Case 3（源码约 8529 行）

```java
case 3:
	createDialogScreen(PaintableObject.getLocaleString(231), (byte)1, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 231: How can this be? The Temple of Wisdom is also under attack!

### Case 4（源码约 8534 行）

```java
case 4:
	createDialogScreen(PaintableObject.getLocaleString(232), (byte)3, (byte)4);
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

> 文本 232: Well, well... Valadorn, I presume? Prepare to be defeated! 

### Case 5（源码约 8538 行）

```java
case 5:
	createDialogScreen(PaintableObject.getLocaleString(233), (byte)5, (byte)4);
	moveMapAndCursor(7, 3);
	break;
```

> 文本 233: Sir, a part of the enemy force is retreating!

### Case 6（源码约 8542 行）

```java
case 6:
	Unit.speed = 2;

	crystallEscortLeader = Unit.createUnit((byte)0, (byte)1, 7, 3);
	crystallEscortCrystall = Unit.createUnit((byte)11, (byte)1, 7, 3);
	crystallEscortFollower = Unit.createUnit((byte)0, (byte)1, 7, 3);

	crystallEscortLeader.followerUnit = crystallEscortCrystall;
	crystallEscortCrystall.followerUnit = crystallEscortFollower;

	crystallEscortLeader.plotRoute(6, -2, false);

	setScriptDelay(30);
	++scriptState;
	break;
```

### Case 7（源码约 8557 行）

```java
case 7:
	if(crystallEscortFollower.currentMapPosX == 6 && crystallEscortFollower.currentMapPosY == 1)
	{
		Unit.speed = 4;
		templeWarrior = Unit.createUnit((byte)0, (byte)0, 7, 3);
		templeWarrior.plotRoute(6, 2, false);
		crystallEscortCrystall.followerUnit = null;
		createDialogScreen(PaintableObject.getLocaleString(234), (byte)2, (byte)4);
		++scriptState;
	}
	break;
```

> 文本 234: The Crystal of Wisdom! Do not let them escape!

### Case 8（源码约 8568 行）

```java
case 8:
	if(templeWarrior.unitState != 1)
	{
		Renderer.vibrate(100);
		templeWarrior.scheduleIdleAnimationStop(400);
		createSimpleSparkSprite(sprRedSpark, templeWarrior.currentX, templeWarrior.currentY, 0, 0, 2, 50);
		setScriptDelay(10);
		++scriptState;
	}
	break;
```

### Case 9（源码约 8578 行）

```java
case 9:
	createDialogScreen(PaintableObject.getLocaleString(235), (byte)2, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 235: Aarrghh!

### Case 10（源码约 8583 行）

```java
case 10:
	createSimpleSparkSprite(sprSmoke, templeWarrior.currentX, templeWarrior.currentY, 0, -3, 1, 100);
	createSimpleSparkSprite(sprSpark, templeWarrior.currentX, templeWarrior.currentY, 0, 0, 1, 50);
	templeWarrior.removeThisUnit();
	templeWarrior = null;
	setScriptDelay(15);
	++scriptState;
	break;
```

### Case 11（源码约 8591 行）

```java
case 11:
	Unit.speed = Unit.UNIT_SPEED_SLOW;
	crystallEscortLeader.removeThisUnit();
	crystallEscortCrystall.removeThisUnit();
	crystallEscortLeader = null;
	crystallEscortCrystall = null;
	crystallEscortFollower = null;
	mapStepMax = 12;
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

### Case 12（源码约 8601 行）

```java
case 12:
	createDialogScreen(PaintableObject.getLocaleString(236), (byte)5, (byte)4);
	++scriptState;
	break;
```

> 文本 236: Sir, we must follow them!

### Case 13（源码约 8605 行）

```java
case 13:
	createDialogScreen(PaintableObject.getLocaleString(237), (byte)1, (byte)4);
	++scriptState;
	break;
```

> 文本 237: Let us stop this attack first. The temple guards will not stand a chance otherwise!

### Case 14（源码约 8609 行）

```java
case 14:
	moveMapAndCursor(3, 5);
	break;
```

### Case 15（源码约 8612 行）

```java
case 15:
	createDialogScreen(PaintableObject.getLocaleString(238), (byte)5, (byte)4);
	++scriptState;
	break;
```

> 文本 238: Let us make our stand at this castle!

### Case 16（源码约 8616 行）

```java
case 16:
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

### Case 17（源码约 8619 行）

```java
case 17:
	currentHelp = 8;
	++scriptState;
	break;
```

### Case 18（源码约 8623 行）

```java
case 18:
	currentHelp = 9;
	++scriptState;
	break;
```

### Case 19（源码约 8627 行）

```java
case 19:
	startNormalPlay();
	++scriptState;
	break;
```

### Case 20（源码约 8631 行）

```java
case 20:
	if(gameState == 9 && currentTurningPlayer == 0)
	{
		currentHelp = 10;
		++scriptState;
	}
	break;
```

### Case 21（源码约 8638 行）

```java
case 21:
	currentHelp = 11;
	++scriptState;
	break;
```

### Case 22（源码约 8642 行）

```java
case 22:
	if(fractionKings[0].unitState == 3 || fractionKings[1].unitState == 3)
	{
		currentHelp = 12;
		++scriptState;
	}
	break;
```

### Case 23（源码约 8649 行）

```java
case 23:
	if(countUnits(-1, -1, (byte)1) == 0 && countCastles(1) == 0)
	{
		activeFlag = false;
		setScriptDelay(20);
		++scriptState;
	}
	break;
```

### Case 24（源码约 8657 行）

```java
case 24:
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

### Case 25（源码约 8660 行）

```java
case 25:
	createDialogScreen(PaintableObject.getLocaleString(239), (byte)1, (byte)4);
	++scriptState;
	break;
```

> 文本 239: Well done, Captain. But there is no time to rest. The Crystal of Life may also be in danger. We must warn King Galamar!

### Case 26（源码约 8664 行）

```java
case 26:
	createDialogScreen(PaintableObject.getLocaleString(240), (byte)5, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 240: Yes, Sir. I will ready the troops.

### Case 27（源码约 8669 行）

```java
case 27:
	completeMission();

```

## m2 — PATH OF SHADOWS

### Case 0（源码约 8683 行）

```java
case 0:
	if(crystallEscortLeader.unitState != 1)
	{
		crystallEscortLeader.plotRoute(7, 14, false);
		++scriptState;
	}
	break;
```

### Case 1（源码约 8690 行）

```java
case 1:
	if(crystallEscortCrystall.unitState != 1)
	{
		crystallEscortCrystall.plotRoute(7, 15, false);
		setScriptDelay(20);
		++scriptState;
	}
	break;
```

### Case 2（源码约 8698 行）

```java
case 2:
	Unit.speed = Unit.UNIT_SPEED_SLOW;
	crystallEscortLeader = null;
	crystallEscortCrystall = null;
	crystallEscortFollower = null;
	createDialogScreen(PaintableObject.getLocaleString(241), (byte)5, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 241: Your Majesty, this forest is known to be home of spirits, elementals, and other magical creatures. Travelling at night is not safe.

### Case 3（源码约 8707 行）

```java
case 3:
	createDialogScreen(PaintableObject.getLocaleString(242), (byte)0, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 242: Captain, you heard Valadorn's messenger - we must press on! Nothing must stand between us and the Temple of Life.

### Case 4（源码约 8712 行）

```java
case 4:
	createDialogScreen(PaintableObject.getLocaleString(243), (byte)5, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 243: Understood. Troops, keep your eyes open and protect King Galamar!

### Case 5（源码约 8717 行）

```java
case 5:
	currentHelp = 14;
	++scriptState;
	break;
```

### Case 6（源码约 8721 行）

```java
case 6:
	startNormalPlay();
	++scriptState;
	break;
```

### Case 7（源码约 8725 行）

```java
case 7:
```

### Case 8（源码约 8726 行）

```java
case 8:
	if(scriptState == 7 && currentTurningPlayer == 0 && statusBarOffset == 0 && countUnits(-1, 3, (byte)-1) >= 1)
	{
		currentHelp = 15;
		++scriptState;
	}

	var14 = listUnits(-1, 2, (byte)0);
	var2 = 0;

	while(true)
	{
		if(var2 >= var14.length)
		{
			break label597;
		}

		if(var14[var2].currentMapPosX <= 4 || var14[var2].currentMapPosY <= 10)
		{
			setScriptDelay(10);
			activeFlag = false;
			drawCursorFlag = false;
			scriptState = 9;
			break label597;
		}

		++var2;
	}
```

### Case 9（源码约 8754 行）

```java
case 9:
	moveMapAndCursor(0, 8);
	Unit.createUnit((byte)5, (byte)1, -1, 8).plotRoute(0, 8, false);
	Unit.createUnit((byte)5, (byte)1, -2, 7).plotRoute(1, 7, false);
	setScriptDelay(20);
	break;
```

### Case 10（源码约 8760 行）

```java
case 10:
	moveMapAndCursor(8, 6);
	Unit.createUnit((byte)5, (byte)1, 12, 6).plotRoute(8, 6, false);
	setScriptDelay(20);
	break;
```

### Case 11（源码约 8765 行）

```java
case 11:
	moveMapAndCursor(2, 1);
	Unit.createUnit((byte)5, (byte)1, 1, -2).plotRoute(1, 2, false);
	Unit.createUnit((byte)5, (byte)1, 3, -2).plotRoute(3, 2, false);
	Unit.createUnit((byte)4, (byte)1, 2, -1).plotRoute(2, 1, false);
	setScriptDelay(20);
	break;
```

### Case 12（源码约 8772 行）

```java
case 12:
	createDialogScreen(PaintableObject.getLocaleString(244), (byte)5, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 244: More wolves! Your Majesty, this does not look good...

### Case 13（源码约 8777 行）

```java
case 13:
	moveMapAndCursor(4, 8);
	setScriptDelay(15);
	break;
```

### Case 14（源码约 8781 行）

```java
case 14:
	var11 = Unit.createUnit((byte)2, (byte)1, 3, 8);
	var3 = Unit.createUnit((byte)2, (byte)1, 4, 7);
	var4 = Unit.createUnit((byte)2, (byte)1, 5, 8);
	createSimpleSparkSprite(sprSpark, var11.currentX, var11.currentY, 0, 0, 1, 50);
	createSimpleSparkSprite(sprSpark, var3.currentX, var3.currentY, 0, 0, 1, 50);
	createSimpleSparkSprite(sprSpark, var4.currentX, var4.currentY, 0, 0, 1, 50);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 15（源码约 8791 行）

```java
case 15:
	createDialogScreen(PaintableObject.getLocaleString(245), (byte)5, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 245: By the Creator! What are those things!

### Case 16（源码约 8796 行）

```java
case 16:
	createDialogScreen(PaintableObject.getLocaleString(246), (byte)-1, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 246: Greetings, men of Thorin! We, the Elementals, have heard of your quest to protect our lands from evil and offer our allegiance to our King.

### Case 17（源码约 8801 行）

```java
case 17:
	createDialogScreen(PaintableObject.getLocaleString(247), (byte)0, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 247: We gladly accept your offer. Your loyalty to the kingdom shall not be forgotten.

### Case 18（源码约 8806 行）

```java
case 18:
	PaintableObject.currentRenderer.setCurrentDisplayable(createMessageScreen((String)null, PaintableObject.getLocaleString(248), height_, 1500));
	++scriptState;
	break;
```

> 文本 248: The Water Elementals have joined the party.

### Case 19（源码约 8810 行）

```java
case 19:
	getUnit(3, 8, (byte)0).removeThisUnit();
	getUnit(4, 7, (byte)0).removeThisUnit();
	getUnit(5, 8, (byte)0).removeThisUnit();
	var11 = Unit.createUnit((byte)2, (byte)0, 3, 8);
	var3 = Unit.createUnit((byte)2, (byte)0, 4, 7);
	var4 = Unit.createUnit((byte)2, (byte)0, 5, 8);
	createSimpleSparkSprite(sprSpark, var11.currentX, var11.currentY, 0, 0, 1, 50);
	createSimpleSparkSprite(sprSpark, var3.currentX, var3.currentY, 0, 0, 1, 50);
	createSimpleSparkSprite(sprSpark, var4.currentX, var4.currentY, 0, 0, 1, 50);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 20（源码约 8823 行）

```java
case 20:
	activeFlag = true;
	drawCursorFlag = true;
	currentHelp = 13;
	++scriptState;
	break;
```

### Case 21（源码约 8829 行）

```java
case 21:
	if(countUnits(-1, -1, (byte)1) == 0)
	{
		activeFlag = false;
		setScriptDelay(10);
		++scriptState;
	}
	break;
```

### Case 22（源码约 8837 行）

```java
case 22:
	completeMission();

```

## m3 — REINFORCEMENTS

### Case 0（源码约 8845 行）

```java
case 0:
	PaintableObject.currentRenderer.setCurrentDisplayable(auxMapNameMessage);
	updateAlphaDrap = true;
	++scriptState;
	break;
```

### Case 1（源码约 8850 行）

```java
case 1:
	if(fractionKings[0].unitState != 1)
	{
		var_31be = null;
		setScriptDelay(20);
		++scriptState;
	}
	break;
```

### Case 2（源码约 8858 行）

```java
case 2:
	drawAlphaDrap = true;
	updateAlphaDrap = true;
	drapAlphaValue = 0;
	setScriptDelay(20);
	++scriptState;
	break;
```

### Case 3（源码约 8865 行）

```java
case 3:
	createDialogScreen(PaintableObject.getLocaleString(249), (byte)2, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 249: Your Majesty, thank the Creator you are here! Please help us protect the Crystal!

### Case 4（源码约 8870 行）

```java
case 4:
	createDialogScreen(PaintableObject.getLocaleString(250), (byte)0, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 250: Only in the heavily guarded fortresses of Thorin will the Crystal be safe. We must deliver it there.

### Case 5（源码约 8875 行）

```java
case 5:
	createDialogScreen(PaintableObject.getLocaleString(251), (byte)5, (byte)4);
	moveCursor(13, 3);
	moveMapShowPointPixels(312, 72);
	fractionKings[0].setMapPosition(7, 1);
	crystallEscortLeader.setMapPosition(5, 4);
	crystallEscortCrystall.setMapPosition(7, 5);
	crystallEscortFollower.setMapPosition(3, 3);
	crystallEscortLeader = null;
	crystallEscortCrystall = null;
	crystallEscortFollower = null;
	mapStepMax = 2;
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 251: Your Majesty, our scouts report enemy troops nearby!

### Case 6（源码约 8890 行）

```java
case 6:
	drawAlphaDrap = false;
	inverseDrapAlpha = true;
	drapAlphaValue = 0;
	setScriptDelay(5);
	++scriptState;
	break;
```

### Case 7（源码约 8897 行）

```java
case 7:
	moveMapAndCursor(13, 10);
	break;
```

### Case 8（源码约 8900 行）

```java
case 8:
	Unit.speed = 2;
	mapStepMax = 4;
	crystallEscortLeader = getUnit(10, 10, (byte)0);
	crystallEscortLeader.plotRoute(6, 10, false);
	moveCursor(6, 10);
	moveMapAndCursor(6, 10);
	break;
```

### Case 9（源码约 8908 行）

```java
case 9:
	if(crystallEscortLeader.unitState != 1)
	{
		crystallEscortLeader = null;
		drawCursorFlag = true;
		setScriptDelay(10);
		++scriptState;
	}
	break;
```

### Case 10（源码约 8917 行）

```java
case 10:
	moveCursor(4, 9);
	moveMapAndCursor(4, 9);
	createSimpleSparkSprite(sprRedSpark, 96, 216, 0, 0, 1, 50);
	setScriptDelay(15);
	break;
```

### Case 11（源码约 8923 行）

```java
case 11:
	var_16e5 = Unit.createUnitEx((byte)0, (byte)0, 4, 9, false);
	var_16e5.type = -1;
	var_16e5.unitState = 4;
	var_17d5 = 6;
	setScriptDelay(20);
	++scriptState;
	break;
```

### Case 12（源码约 8931 行）

```java
case 12:
	createDialogScreen(PaintableObject.getLocaleString(252), (byte)5, (byte)4);
	moveMapAndCursor(7, 1);
	break;
```

> 文本 252: They are destroying our buildings to cripple our supply of gold! They must be stopped!

### Case 13（源码约 8935 行）

```java
case 13:
	createDialogScreen(PaintableObject.getLocaleString(253), (byte)0, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 253: Captain, we must save the Crystal, prepare the troops for battle!

### Case 14（源码约 8940 行）

```java
case 14:
	currentHelp = 17;
	++scriptState;
	break;
```

### Case 15（源码约 8944 行）

```java
case 15:
	startNormalPlay();
	++scriptState;
	break;
```

### Case 16（源码约 8948 行）

```java
case 16:
	if(countUnits(-1, -1, (byte)1) == 0 && countCastles(1) == 0)
	{
		setScriptDelay(15);
		activeFlag = false;
		++scriptState;
	}
	break;
```

### Case 17（源码约 8956 行）

```java
case 17:
	createDialogScreen(PaintableObject.getLocaleString(254), (byte)5, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 254: Your Majesty, We have stopped the attack.

### Case 18（源码约 8961 行）

```java
case 18:
	createDialogScreen(PaintableObject.getLocaleString(255), (byte)0, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 255: Well done, Captain! Prepare the troops to march to Thorin!

### Case 19（源码约 8966 行）

```java
case 19:
	completeMission();

```

## m4 — ESCORT DUTY

### Case 0（源码约 8996 行）

```java
case 0:
	setScriptDelay(50);
	++scriptState;
	break;
```

### Case 1（源码约 9000 行）

```java
case 1:
	createDialogScreen(PaintableObject.getLocaleString(256), (byte)5, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 256: Your Highness, I do not like the look of this forest, we must be careful!

### Case 2（源码约 9005 行）

```java
case 2:
	createDialogScreen(PaintableObject.getLocaleString(257), (byte)0, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 257: Very well, Captain. Let us follow this path and stay on our guard.

### Case 3（源码约 9010 行）

```java
case 3:
	startNormalPlay();
	++scriptState;
	break;
```

### Case 4（源码约 9014 行）

```java
case 4:
	var14 = listUnits(-1, -1, (byte)0);
	var2 = 0;

	while(true)
	{
		if(var2 >= var14.length)
		{
			break label580;
		}

		if(var14[var2].unitState == 2 && var14[var2].currentMapPosX <= 8)
		{
			activeFlag = false;
			setScriptDelay(5);
			++scriptState;
			break label580;
		}

		++var2;
	}
```

### Case 5（源码约 9035 行）

```java
case 5:
	drawCursorFlag = false;
	moveMapAndCursor(4, 4);
	break;
```

### Case 6（源码约 9039 行）

```java
case 6:
	Unit.createUnit((byte)10, (byte)1, 4, 4).plotRouteEx(4, 1, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 7（源码约 9044 行）

```java
case 7:
	Unit.createUnit((byte)1, (byte)1, 4, 4).plotRouteEx(5, 2, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 8（源码约 9049 行）

```java
case 8:
	Unit.createUnit((byte)10, (byte)1, 4, 4).plotRouteEx(4, 3, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 9（源码约 9054 行）

```java
case 9:
	createDialogScreen(PaintableObject.getLocaleString(258), (byte)5, (byte)4);
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

> 文本 258: Ambush! Protect the Crystal at all cost!

### Case 10（源码约 9058 行）

```java
case 10:
	activeFlag = true;
	drawCursorFlag = true;
	++scriptState;
	break;
```

### Case 11（源码约 9063 行）

```java
case 11:
	var14 = listUnits(-1, -1, (byte)0);
	var2 = 0;

	while(true)
	{
		if(var2 >= var14.length)
		{
			break label580;
		}

		if(var14[var2].unitState == 2 && var14[var2].currentMapPosY >= 7)
		{
			activeFlag = false;
			drawCursorFlag = false;
			moveMapAndCursor(6, 10);
			break label580;
		}

		++var2;
	}
```

### Case 12（源码约 9084 行）

```java
case 12:
	Unit.createUnit((byte)1, (byte)1, 6, 10).plotRouteEx(5, 10, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 13（源码约 9089 行）

```java
case 13:
	Unit.createUnit((byte)5, (byte)1, 6, 10).plotRouteEx(7, 8, false, true);
	setScriptDelay(15);
	++scriptState;
	break;
```

### Case 14（源码约 9094 行）

```java
case 14:
	Unit.createUnit((byte)5, (byte)1, 6, 10).plotRouteEx(7, 9, false, true);
	activeFlag = true;
	drawCursorFlag = true;
	++scriptState;
	break;
```

### Case 15（源码约 9100 行）

```java
case 15:
	var14 = listUnits(-1, -1, (byte)0);
	var2 = 0;

	while(true)
	{
		if(var2 >= var14.length)
		{
			break label580;
		}

		if(var14[var2].unitState == 2 && var14[var2].currentMapPosX >= 8 && var14[var2].currentMapPosY >= 6)
		{
			activeFlag = false;
			drawCursorFlag = false;
			moveMapAndCursor(12, 5);
			break label580;
		}

		++var2;
	}
```

### Case 16（源码约 9121 行）

```java
case 16:
	Unit.createUnit((byte)5, (byte)1, 12, 5).plotRouteEx(12, 7, false, true);
	setScriptDelay(15);
	++scriptState;
	break;
```

### Case 17（源码约 9126 行）

```java
case 17:
	Unit.createUnit((byte)6, (byte)1, 12, 5).plotRouteEx(12, 6, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 18（源码约 9131 行）

```java
case 18:
	Unit.createUnit((byte)5, (byte)1, 12, 5).plotRouteEx(12, 5, false, true);
	activeFlag = true;
	drawCursorFlag = true;
	++scriptState;
	break;
```

### Case 19（源码约 9137 行）

```java
case 19:
	var14 = listUnits(-1, -1, (byte)0);
	var2 = 0;

	while(true)
	{
		if(var2 >= var14.length)
		{
			break label580;
		}

		if(var14[var2].unitState == 2 && var14[var2].currentMapPosX >= 15 && var14[var2].currentMapPosY >= 8)
		{
			activeFlag = false;
			drawCursorFlag = false;
			moveMapAndCursor(18, 8);
			break label580;
		}

		++var2;
	}
```

### Case 20（源码约 9158 行）

```java
case 20:
	Unit.createUnit((byte)5, (byte)1, 18, 8).plotRouteEx(16, 10, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 21（源码约 9163 行）

```java
case 21:
	Unit.createUnit((byte)6, (byte)1, 18, 8).plotRouteEx(17, 10, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 22（源码约 9168 行）

```java
case 22:
	Unit.createUnit((byte)5, (byte)1, 18, 8).plotRouteEx(18, 10, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 23（源码约 9173 行）

```java
case 23:
	Unit.createUnit((byte)1, (byte)1, 18, 8).plotRouteEx(18, 9, false, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 24（源码约 9178 行）

```java
case 24:
	createDialogScreen(PaintableObject.getLocaleString(259), (byte)0, (byte)4);
	activeFlag = true;
	drawCursorFlag = true;
	++scriptState;
	break;
```

> 文本 259: This seems to be the last of them...

### Case 25（源码约 9184 行）

```java
case 25:
	if(countUnits(-1, -1, (byte)1) == 0)
	{
		activeFlag = false;
		drawCursorFlag = false;
		setScriptDelay(10);
		++scriptState;
	}
	break;
```

### Case 26（源码约 9193 行）

```java
case 26:
	completeMission();
	++scriptState;

```

## m5 — NORTHBOUND

### Case 0（源码约 9202 行）

```java
case 0:
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 1（源码约 9206 行）

```java
case 1:
	createDialogScreen(PaintableObject.getLocaleString(260), (byte)1, (byte)4);
	++scriptState;
	break;
```

> 文本 260: The enemy is planning to attack King Galamar before he reaches Thorin! We must defeat them!

### Case 2（源码约 9210 行）

```java
case 2:
	startNormalPlay();
	++scriptState;
	break;
```

### Case 3（源码约 9214 行）

```java
case 3:
	if(countUnits(-1, -1, (byte)1) == 0 && countCastles(1) == 0)
	{
		activeFlag = false;
		drawCursorFlag = false;
		setScriptDelay(15);
		++scriptState;
	}
	break;
```

### Case 4（源码约 9223 行）

```java
case 4:
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

### Case 5（源码约 9226 行）

```java
case 5:
	createDialogScreen(PaintableObject.getLocaleString(261), (byte)0, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 261: Let us hurry North to meet Galamar. The enemy knows of his plans and he may need help!

### Case 6（源码约 9231 行）

```java
case 6:
	completeMission();

```

## m6 — RENDEZVOUS

### Case 0（源码约 9254 行）

```java
case 0:
	if(fractionKings[0].unitState != 1)
	{
		var_31be = null;
		setScriptDelay(10);
		++scriptState;
	}
	break;
```

### Case 1（源码约 9262 行）

```java
case 1:
	createDialogScreen(PaintableObject.getLocaleString(262), (byte)5, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 262: Your Highness, the troops are exhausted! I suggest we camp here for the night

### Case 2（源码约 9267 行）

```java
case 2:
	createDialogScreen(PaintableObject.getLocaleString(263), (byte)0, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 263: We must not delay, Captain. The sooner we get to Thorin the better. The troops can rest when we are safe.

### Case 3（源码约 9272 行）

```java
case 3:
	var14 = listUnits(-1, -1, (byte)0);

	for(var2 = 0; var2 < var14.length; ++var2)
	{
		var14[var2].followerUnit = null;
	}

	startNormalPlay();
	++scriptState;
	break;
```

### Case 4（源码约 9283 行）

```java
case 4:
	if(currentTurn >= 2)
	{
		setScriptDelay(15);
		activeFlag = false;
		drawCursorFlag = false;
		moveMapAndCursor(11, 7);
	}
	break;
```

### Case 5（源码约 9292 行）

```java
case 5:
	(var11 = Unit.createUnit((byte)5, (byte)1, 11, 8)).fillMoveRangeData(mapAlphaData);
	var11.plotRoute(14, 7, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 6（源码约 9298 行）

```java
case 6:
	(var3 = Unit.createUnit((byte)0, (byte)1, 11, 8)).fillMoveRangeData(mapAlphaData);
	var3.plotRoute(13, 7, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 7（源码约 9304 行）

```java
case 7:
	(var4 = Unit.createUnit((byte)3, (byte)1, 11, 8)).fillMoveRangeData(mapAlphaData);
	var4.plotRoute(12, 7, true);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 8（源码约 9310 行）

```java
case 8:
	Unit.createUnit((byte)1, (byte)1, 11, 8).plotRoute(13, 8, false);
	setScriptDelay(20);
	++scriptState;
	break;
```

### Case 9（源码约 9315 行）

```java
case 9:
	createDialogScreen(PaintableObject.getLocaleString(264), (byte)5, (byte)4);
	activeFlag = true;
	drawCursorFlag = true;
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

> 文本 264: Another ambush! Don't let the Crystal leave your sight!

### Case 10（源码约 9321 行）

```java
case 10:
	boolean var6 = false;
	Unit[] var7 = listUnits(-1, 2, (byte)0);

	for(int var15 = 0; var15 < var7.length; ++var15)
	{
		if(var7[var15].currentMapPosX <= 9 || var7[var15].currentMapPosY >= 10)
		{
			var6 = true;
			break;
		}
	}

	if(var6 || countUnits(-1, -1, (byte)1) == 0)
	{
		drawCursorFlag = false;
		activeFlag = false;
		setScriptDelay(10);
		++scriptState;
	}
	break;
```

### Case 11（源码约 9342 行）

```java
case 11:
	crystallEscortLeader = listUnits(11, -1, (byte)0)[0];
	crystallEscortCrystall = Unit.createUnit((byte)8, (byte)1, mapWidth, crystallEscortLeader.currentMapPosY);
	moveMapAndCursor(mapWidth - 1, crystallEscortLeader.currentMapPosY);
	drawCursorFlag = false;
	break;
```

### Case 12（源码约 9348 行）

```java
case 12:
	crystallEscortCrystall.plotRoute(crystallEscortLeader.currentMapPosX, crystallEscortLeader.currentMapPosY, false);
	setScriptDelay(5);
	++scriptState;
	break;
```

### Case 13（源码约 9353 行）

```java
case 13:
	createDialogScreen(PaintableObject.getLocaleString(265), (byte)5, (byte)4);
	var_31be = crystallEscortCrystall;
	++scriptState;
	break;
```

> 文本 265: Troops! That dragon is heading towards us - protect the King!

### Case 14（源码约 9358 行）

```java
case 14:
	if(crystallEscortCrystall.unitState != 1)
	{
		createDialogScreen(PaintableObject.getLocaleString(266), (byte)0, (byte)4);
		crystallEscortCrystall.plotRoute(-1, crystallEscortCrystall.currentMapPosY, false);
		setScriptDelay(3);
		++scriptState;
	}
	break;
```

> 文本 266: It is the Crystal it is after! May the Creator protect us!

### Case 15（源码约 9367 行）

```java
case 15:
	crystallEscortLeader.plotRoute(-1, crystallEscortCrystall.currentMapPosY, false);
	++scriptState;
	break;
```

### Case 16（源码约 9371 行）

```java
case 16:
	if(crystallEscortCrystall.unitState != 1)
	{
		setScriptDelay(10);
		crystallEscortLeader.removeThisUnit();
		crystallEscortCrystall.removeThisUnit();
		moveCursor(0, crystallEscortCrystall.currentMapPosY);
		crystallEscortLeader = null;
		crystallEscortCrystall = null;
		var_31be = null;
		++scriptState;
	}
	break;
```

### Case 17（源码约 9384 行）

```java
case 17:
	moveMapAndCursor(1, 9);
	break;
```

### Case 18（源码约 9387 行）

```java
case 18:
	fractionKings[1] = Unit.createUnit((byte)9, (byte)1, -2, 8);
	fractionKings[1].plotRoute(0, 8, false);
	Unit.createUnit((byte)0, (byte)1, -1, 8).plotRoute(3, 8, false);
	Unit.createUnit((byte)0, (byte)1, -1, 10).plotRoute(1, 10, false);
	Unit.createUnit((byte)8, (byte)1, -3, 7).plotRoute(4, 8, false);
	Unit.createUnit((byte)8, (byte)1, -3, 11).plotRoute(2, 10, false);
	Unit.createUnit((byte)4, (byte)1, -2, 9).plotRoute(2, 9, false);
	Unit.createUnit((byte)6, (byte)1, -4, 9).plotRoute(4, 9, false);
	Unit.createUnit((byte)6, (byte)1, -6, 9).plotRoute(5, 10, false);
	setScriptDelay(50);
	++scriptState;
	break;
```

### Case 19（源码约 9400 行）

```java
case 19:
	createDialogScreen(PaintableObject.getLocaleString(267), (byte)3, (byte)4);
	++scriptState;
	break;
```

> 文本 267: Run, pitiful human, before it is too late!

### Case 20（源码约 9404 行）

```java
case 20:
	moveMapAndCursor(13, 14);
	break;
```

### Case 21（源码约 9407 行）

```java
case 21:
	Unit.createUnit((byte)0, (byte)1, 13, 14).plotRoute(12, 14, false);
	setScriptDelay(5);
	++scriptState;
	break;
```

### Case 22（源码约 9412 行）

```java
case 22:
	Unit.createUnit((byte)6, (byte)1, 13, 14).plotRoute(14, 14, false);
	setScriptDelay(5);
	++scriptState;
	break;
```

### Case 23（源码约 9417 行）

```java
case 23:
	Unit.createUnit((byte)2, (byte)1, 13, 14).plotRoute(13, 12, false);
	setScriptDelay(5);
	++scriptState;
	break;
```

### Case 24（源码约 9422 行）

```java
case 24:
	Unit.createUnit((byte)3, (byte)1, 13, 14).plotRoute(13, 15, false);
	setScriptDelay(15);
	++scriptState;
	break;
```

### Case 25（源码约 9427 行）

```java
case 25:
	createDialogScreen(PaintableObject.getLocaleString(268), (byte)5, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 268: The enemy force looks formidable, I recommend retreat.

### Case 26（源码约 9432 行）

```java
case 26:
	createDialogScreen(PaintableObject.getLocaleString(269), (byte)0, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 269: We have no choice but to attack - we must go after the Crystal!

### Case 27（源码约 9437 行）

```java
case 27:
	moveMapAndCursor(13, 17);
	break;
```

### Case 28（源码约 9440 行）

```java
case 28:
	Unit var8;
	(var8 = Unit.createUnit((byte)9, (byte)0, 13, 18)).setKingVariant(2);
	var8.plotRoute(13, 16, false);
	Unit.createUnit((byte)6, (byte)0, 12, 18).plotRoute(12, 16, false);
	Unit.createUnit((byte)8, (byte)0, 14, 19).plotRoute(14, 16, false);
	Unit.createUnit((byte)4, (byte)0, 13, 19).plotRoute(13, 17, false);
	Unit.createUnit((byte)1, (byte)0, 12, 19).plotRoute(12, 17, false);
	setScriptDelay(20);
	++scriptState;
	break;
```

### Case 29（源码约 9451 行）

```java
case 29:
	createDialogScreen(PaintableObject.getLocaleString(270), (byte)1, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 270: Galamar! We came as fast as we could! Let us fight side by side!

### Case 30（源码约 9456 行）

```java
case 30:
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

### Case 31（源码约 9459 行）

```java
case 31:
	currentHelp = 18;
	++scriptState;
	break;
```

### Case 32（源码约 9463 行）

```java
case 32:
	activeFlag = true;
	drawCursorFlag = true;
	auxObjectivesMessage = createMessageScreen(PaintableObject.getLocaleString(121 + currentMission), PaintableObject.getLocaleString(138), height_, -1);
	auxObjectivesMessage.setDrawSoftButtonFlag((byte)0, true);
	auxObjectivesMessage.setNextDisplayable((PaintableObject)null);
	PaintableObject.currentRenderer.setCurrentDisplayable(auxObjectivesMessage);
	++scriptState;
	break;
```

> 文本 138: Carve a path to the west through the enemy troops, defeat their commander and occupy all castles!

### Case 33（源码约 9472 行）

```java
case 33:
	if(countUnits(-1, -1, (byte)1) == 0 && countCastles(1) == 0)
	{
		activeFlag = false;
		drawCursorFlag = false;
		setScriptDelay(10);
		++scriptState;
	}
	break;
```

### Case 34（源码约 9481 行）

```java
case 34:
	completeMission();

```

## m7 — HEAVEN'S FURY

### Case 0（源码约 9489 行）

```java
case 0:
	PaintableObject.currentRenderer.setCurrentDisplayable(auxMapNameMessage);
	updateAlphaDrap = true;
	++scriptState;
	break;
```

### Case 1（源码约 9494 行）

```java
case 1:
	if(!inverseDrapAlpha)
	{
		createDialogScreen(PaintableObject.getLocaleString(271), (byte)0, (byte)4);
		++scriptState;
	}
	break;
```

> 文本 271: The Ruins of the Ancient Citadel... and Saeth! With the Crystals!

### Case 2（源码约 9501 行）

```java
case 2:
	moveMapAndCursor(fractionKings[1].currentMapPosX, fractionKings[1].currentMapPosY);
	break;
```

### Case 3（源码约 9504 行）

```java
case 3:
	createDialogScreen(PaintableObject.getLocaleString(272), (byte)4, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 272: At last, Galamar and Valadorn! How very brave of you to confront me! All the keys are now in place, and soon the greatest power ever created on this earth will roar and bend to my will. Your destruction will be testimony to its might and glory!

### Case 4（源码约 9509 行）

```java
case 4:
	createDialogScreen(PaintableObject.getLocaleString(273), (byte)1, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 273: Your ruses can no longer fool us, Saeth!

### Case 5（源码约 9514 行）

```java
case 5:
	createDialogScreen(PaintableObject.getLocaleString(274), (byte)4, (byte)4);
	++scriptState;
	break;
```

> 文本 274: You do not believe me? Fools! I will favour you with a glimpse of this ancient power. Behold!

### Case 6（源码约 9518 行）

```java
case 6:
	crystallEscortLeader = getUnit(5, 2, (byte)0);
	crystallEscortLeader.plotRoute(7, 2, false);
	++scriptState;
	break;
```

### Case 7（源码约 9523 行）

```java
case 7:
	if(crystallEscortLeader.unitState != 1)
	{
		crystallEscortLeader.removeThisUnit();
		crystallEscortLeader = null;
		crystallEscortCrystall = getUnit(7, 3, (byte)0);
		crystallEscortCrystall.plotRoute(7, 2, false);
		++scriptState;
	}
	break;
```

### Case 8（源码约 9533 行）

```java
case 8:
	if(crystallEscortCrystall.unitState != 1)
	{
		crystallEscortCrystall.removeThisUnit();
		crystallEscortCrystall = null;
		crystallEscortFollower = getUnit(9, 2, (byte)0);
		crystallEscortFollower.plotRoute(7, 2, false);
		++scriptState;
	}
	break;
```

### Case 9（源码约 9543 行）

```java
case 9:
	if(crystallEscortFollower.unitState != 1)
	{
		crystallEscortFollower.removeThisUnit();
		crystallEscortFollower = null;
		fractionKings[1].plotRoute(7, 2, false);
		setScriptDelay(20);
		++scriptState;
	}
	break;
```

### Case 10（源码约 9553 行）

```java
case 10:
	moveMapAndCursor(9, 15);
	drawCursorFlag = true;
	break;
```

### Case 11（源码约 9557 行）

```java
case 11:
	AuxDisplayable var9;
	(var9 = createMessageScreen((String)null, PaintableObject.getLocaleString(279), height_, 2000)).setPosition(halfWidth_, 2, 17);
	PaintableObject.currentRenderer.setCurrentDisplayable(var9);
	++scriptState;
	break;
```

> 文本 279: TARGET AQUIRED...

### Case 12（源码约 9563 行）

```java
case 12:
	var_29c1 = false;
	sub_26e(getUnit(9, 15, (byte)0));
	++scriptState;
	break;
```

### Case 13（源码约 9568 行）

```java
case 13:
	if(var_2985 >= 2)
	{
		createDialogScreen(PaintableObject.getLocaleString(275), (byte)0, (byte)4);
		getUnit(9, 15, (byte)0).removeThisUnit();
		var_29c1 = true;
		setScriptDelay(20);
		++scriptState;
	}
	break;
```

> 文本 275: What in the name of the Creator is that!

### Case 14（源码约 9578 行）

```java
case 14:
	drawCursorFlag = false;
	createDialogScreen(PaintableObject.getLocaleString(276), (byte)4, (byte)4);
	++scriptState;
	break;
```

> 文本 276: Pathetic creatures, this is something ancient that you would not understand even if you lived a thousand years! Witness the power of Heaven's Fury!

### Case 15（源码约 9583 行）

```java
case 15:
	createDialogScreen(PaintableObject.getLocaleString(277), (byte)1, (byte)4);
	setScriptDelay(10);
	++scriptState;
	break;
```

> 文本 277: Galamar! We must hurry if our troops are to stand a chance!

### Case 16（源码约 9588 行）

```java
case 16:
	drawCursorFlag = true;
	mapStepMax = 4;
	moveMapAndCursor(3, 9);
	break;
```

### Case 17（源码约 9593 行）

```java
case 17:
	moveMapAndCursor(13, 4);
	break;
```

### Case 18（源码约 9596 行）

```java
case 18:
	mapStepMax = 12;
	createDialogScreen(PaintableObject.getLocaleString(278), (byte)5, (byte)4);
	moveMapAndCursor(fractionKings[0].currentMapPosX, fractionKings[0].currentMapPosY);
	break;
```

> 文本 278: Sire, we should attack these castles first before we launch an attack on the Saeth and the Ruins.

### Case 19（源码约 9601 行）

```java
case 19:
	startNormalPlay();
	++scriptState;
	break;
```

### Case 20（源码约 9605 行）

```java
case 20:
	if(var_29de)
	{
		drawCursorFlag = false;
		activeFlag = false;
		setScriptDelay(20);
		++scriptState;
	}
	break;
```

### Case 21（源码约 9614 行）

```java
case 21:
	drawAlphaDrap = true;
	drapAlphaValue = 0;
	updateAlphaDrap = true;
	++scriptState;
	break;
```

### Case 22（源码约 9620 行）

```java
case 22:
	if(drapAlphaValue >= 16)
	{
		moveMapShowPoint(7, 2);
		moveCursor(7, 2);
		sub_58e();
		fractionKings[1] = Unit.createUnit((byte)9, (byte)1, 7, 2);
		fractionKings[1].setKingVariant(3);
		Unit.createUnit((byte)9, (byte)0, 6, 3);
		Unit.createUnit((byte)9, (byte)0, 8, 3).setKingVariant(2);
		Unit.createUnit((byte)0, (byte)0, 6, 1);
		Unit.createUnit((byte)0, (byte)0, 8, 1);
		setScriptDelay(10);
		++scriptState;
		Renderer.startPlayer_(8, 0);
	}
	break;
```

### Case 23（源码约 9637 行）

```java
case 23:
	drawAlphaDrap = false;
	inverseDrapAlpha = true;
	drapAlphaValue = 0;
	++scriptState;
	break;
```

### Case 24（源码约 9643 行）

```java
case 24:
	if(drapAlphaValue >= 16)
	{
		createDialogScreen(PaintableObject.getLocaleString(281), (byte)4, (byte)4);
		setScriptDelay(15);
		++scriptState;
	}
	break;
```

> 文本 281: Pitiful wretches. You will not live long enough to see your precious Kingdom engulfed in shadow...

### Case 25（源码约 9651 行）

```java
case 25:
	createDialogScreen(PaintableObject.getLocaleString(282), (byte)0, (byte)4);
	setScriptDelay(8);
	++scriptState;
	break;
```

> 文本 282: Saeth! This time your defeat is complete!

### Case 26（源码约 9656 行）

```java
case 26:
	createDialogScreen(PaintableObject.getLocaleString(283), (byte)4, (byte)4);
	setScriptDelay(15);
	++scriptState;
	break;
```

> 文本 283: In your insignificant life and narrow mind, perhaps... Nevertheless everything is now set in motion - the invisible forces that govern time are beyond your knowledge. You cannot stop me from rising again!

### Case 27（源码约 9661 行）

```java
case 27:
	createSimpleSparkSprite(sprSpark, 168, 48, 0, 0, 1, 50);
	units.removeElement(fractionKings[1]);
	setScriptDelay(15);
	++scriptState;
	break;
```

### Case 28（源码约 9667 行）

```java
case 28:
	Renderer.vibrate(400);
	shakeMap(5000);
	setScriptDelay(10);
	++scriptState;
	break;
```

### Case 29（源码约 9673 行）

```java
case 29:
	createDialogScreen(PaintableObject.getLocaleString(284), (byte)5, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 284: Your Majesty, the Ruins are falling apart! We should leave immediately!

### Case 30（源码约 9678 行）

```java
case 30:
	createDialogScreen(PaintableObject.getLocaleString(285), (byte)2, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 285: But the Crystals... They are still in there!

### Case 31（源码约 9683 行）

```java
case 31:
	createDialogScreen(PaintableObject.getLocaleString(286), (byte)1, (byte)4);
	setScriptDelay(5);
	++scriptState;
	break;
```

> 文本 286: The only thing we can save now is ourselves!

### Case 32（源码约 9688 行）

```java
case 32:
	createDialogScreen(PaintableObject.getLocaleString(287), (byte)0, (byte)4);
	drawAlphaDrap = true;
	drapAlphaValue = 0;
	++scriptState;
	break;
```

> 文本 287: You have served me well. Now move!

### Case 33（源码约 9694 行）

```java
case 33:
	if(drapAlphaValue >= 16)
	{
		setScriptDelay(10);
		shakeMapFlag = false;
		++scriptState;
	}
	break;
```

### Case 34（源码约 9702 行）

```java
case 34:
	setIntroMode(5, 2, 2);
	++scriptState;
	break;
```

### Case 35（源码约 9706 行）

```java
case 35:
	Renderer.startPlayer_(0, 0);
	sub_a6b(PaintableObject.getLocaleString(288));
	++scriptState;
	break;
```

> 文本 288: 
Congratulations!

 And thank you for playing Ancient Empires II - we very much hope you enjoyed playing it as much as we enjoyed making it!

CREDITS:
Tommy "King" Chuang
Kenneth "Pixel" Fejer
Michelle "Artful" Chuang
Kim Daniel "Ninja" Arthur
Kristian "Saeth" Segerstråle
Silje "Nullpointer" Brevik

and the rest of the Macrospace Team without whom this product would not have been possible to make! And let's not forget the excellent service provider that delivered this game successfully to your handset!

Watch out for further Macrospace releases at www.macrospace.com and your local service provider!

### Case 36（源码约 9711 行）

```java
case 36:
	var_3e0c = 0;
	gameState = 14;
	++scriptState;

```
