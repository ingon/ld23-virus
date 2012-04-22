package org.virus.proto;

import org.virus.model.Colors;

public class Levels {
	public static final int LEVELS_COUNT = 3;
	public static final LevelProto[] levels = new LevelProto[LEVELS_COUNT];
	
	static {
		levels[0] = makeLevel1();
		levels[1] = makeLevel2();
		levels[2] = makeLevel3();
	}
	
	private static LevelProto makeLevel1() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new RandomEnemyProto(350, 150, 1.2, 1500, Colors.BLUE));

		return lp;
	}

	private static LevelProto makeLevel2() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new RandomEnemyProto(300, 50, 1.4, 1500, Colors.BLUE));
		lp.enemies.add(new RandomEnemyProto(350, 250, 1.5, 1000, Colors.BLUE));

		return lp;
	}

	private static LevelProto makeLevel3() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE, Colors.RED);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new RandomEnemyProto(300, 50, 1.4, 1500, Colors.BLUE));
		lp.enemies.add(new RandomEnemyProto(350, 250, 1.5, 1000, Colors.RED));

		return lp;
	}
}
