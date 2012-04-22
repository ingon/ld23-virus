package org.virus.proto;

import org.virus.model.Colors;

public class Levels {
	public static final int LEVELS_COUNT = 6;
	public static final LevelProto[] levels = new LevelProto[LEVELS_COUNT];
	public static final LevelProto survival;
	
	static {
		levels[0] = makeLevel1();
		levels[1] = makeLevel2();
		levels[2] = makeLevel3();
		levels[3] = makeLevel4();
		levels[4] = makeLevel5();
		levels[5] = makeLevel6();
		
		survival = makeSurvival();
	}
	
	private static LevelProto makeSurvival() {
		PlayerProto player = new PlayerProto(380, 280, 2, Colors.values());
		
		LevelProto lp = new LevelProto(760, 560, player);
		
		lp.enemies.add(new RandomEnemyProto(350, 150, 1.2, 1500, Colors.BLUE));
		lp.enemies.add(new RandomEnemyProto(350, 350, 1.2, 1500, Colors.BLUE, Colors.RED));
		
		return lp;
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

	private static LevelProto makeLevel4() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE, Colors.RED);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		FixedGeneratorProto gen = new FixedGeneratorProto();
		gen.add(100, new RandomEnemyProto(300, 150, 1.4, 1500, Colors.BLUE));
		gen.add(500, new RandomEnemyProto(300, 150, 1.3, 1200, Colors.RED));
		gen.add(1000, new RandomEnemyProto(300, 150, 1.5, 1300, Colors.BLUE));
		gen.add(1500, new RandomEnemyProto(300, 150, 1.5, 1500, Colors.RED));
		gen.add(2500, new RandomEnemyProto(300, 150, 1.3, 1400, Colors.BLUE, Colors.RED));
		lp.generators.add(gen);

		return lp;
	}
	
	private static LevelProto makeLevel5() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new FollowEnemyProto(350, 150, 1.2, 500, Colors.BLUE));

		return lp;
	}

	private static LevelProto makeLevel6() {
		PlayerProto player = new PlayerProto(300, 150, Colors.BLUE, Colors.RED);
		
		LevelProto lp = new LevelProto(600, 300, player);
		
		FixedGeneratorProto gen1 = new FixedGeneratorProto();
		gen1.add(100, new RandomEnemyProto(150, 150, 1.4, 1500, Colors.BLUE));
		gen1.add(500, new RandomEnemyProto(150, 150, 1.3, 1200, Colors.RED));
		gen1.add(1000, new RandomEnemyProto(150, 150, 1.5, 1300, Colors.BLUE));
		gen1.add(1500, new RandomEnemyProto(150, 150, 1.5, 1500, Colors.RED));
		gen1.add(2500, new RandomEnemyProto(150, 150, 1.3, 1400, Colors.BLUE, Colors.RED));
		lp.generators.add(gen1);

		FixedGeneratorProto gen2 = new FixedGeneratorProto();
		gen2.add(100, new RandomEnemyProto(450, 150, 1.4, 1500, Colors.BLUE));
		gen2.add(500, new RandomEnemyProto(450, 150, 1.3, 1200, Colors.RED));
		gen2.add(1000, new RandomEnemyProto(450, 150, 1.5, 1300, Colors.BLUE));
		gen2.add(1500, new RandomEnemyProto(450, 150, 1.5, 1500, Colors.RED));
		gen2.add(2500, new RandomEnemyProto(450, 150, 1.3, 1400, Colors.BLUE, Colors.RED));
		lp.generators.add(gen2);

		return lp;
	}
}
