package org.virus.proto;

import org.virus.model.Colors;

public class Levels {
	public static final int LEVELS_COUNT = 1;
	public static final LevelProto[] levels = new LevelProto[LEVELS_COUNT];
	
	static {
		levels[0] = makeLevel1();
	}
	
	private static LevelProto makeLevel1() {
		LevelProto lp = new LevelProto(400, 300, 100, 150);
		
		lp.enemies.add(new EnemyProto(300, 150, Colors.BLUE));
		
		return lp;
	}
}
