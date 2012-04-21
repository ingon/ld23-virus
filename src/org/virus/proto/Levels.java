package org.virus.proto;

import org.virus.model.Colors;

public class Levels {
	public static final int LEVELS_COUNT = 1;
	public static final LevelProto[] levels = new LevelProto[LEVELS_COUNT];
	
	static {
		levels[0] = makeLevel1();
	}
	
	private static LevelProto makeLevel1() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new EnemyProto(350, 150, Colors.BLUE));
		
		return lp;
	}
}
