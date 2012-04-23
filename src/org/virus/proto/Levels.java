package org.virus.proto;

import java.util.Random;

import org.virus.model.Callback;
import org.virus.model.CallbackGenerator;
import org.virus.model.Colors;
import org.virus.model.EnemyMover;
import org.virus.model.Position;

public class Levels {
	public static final int LEVELS_COUNT = 5;
	public static final LevelProto[] levels = new LevelProto[LEVELS_COUNT];
	public static final LevelProto survival;
	
	private static final Random rand = new Random();
	private static final int BORDER_DIST = 60;
	private static final Position TOP_LEFT = Position.Constructos.fixed(BORDER_DIST, BORDER_DIST);
	private static final Position TOP_RIGHT = Position.Constructos.fixed(760 - BORDER_DIST, BORDER_DIST);
	private static final Position BOTTOM_LEFT = Position.Constructos.fixed(100, 560 - BORDER_DIST);
	private static final Position BOTTOM_RIGHT = Position.Constructos.fixed(760 - BORDER_DIST, 560 - BORDER_DIST);
		
	static {
		levels[0] = makeLevel1();
		levels[1] = makeLevel2();
		levels[2] = makeLevel3();
		levels[3] = makeLevel4();
		levels[4] = makeLevel5();
		
		survival = makeSurvival();
	}
	
	private static LevelProto makeSurvival() {
		PlayerProto player = new PlayerProto(380, 280, 2, Colors.BLUE);
		
		LevelProto lp = new LevelProto(760, 560, player);
		
		CallbackGeneratorProto cgp = new CallbackGeneratorProto(CallbackGenerator.class);
		
		long time = 0;
		time = addWave(cgp, time, 1., true, Colors.BLUE);
		
		cgp.add(time, Callback.Constructors.addColor(Colors.RED));
		time = addWave(cgp, time, 1., true, Colors.RED);
		time = addWave(cgp, time, 1., false, Colors.BLUE, Colors.RED);
		time = addWave(cgp, time, 1., true, Colors.BLUE, Colors.RED);
		
		cgp.add(time, Callback.Constructors.addColor(Colors.GREEN));
		time = addWave(cgp, time, .95, false, Colors.BLUE, Colors.RED, Colors.GREEN);
		time = addWave(cgp, time, .95, true, Colors.BLUE, Colors.RED, Colors.GREEN);

		cgp.add(time, Callback.Constructors.addColor(Colors.MAGENTA));
		time = addWave(cgp, time, .95, false, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA);
		time = addWave(cgp, time, .95, true, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA);

		cgp.add(time, Callback.Constructors.addColor(Colors.YELLOW));
		time = addWave(cgp, time, 1., false, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW);
		time = addWave(cgp, time, 1., true, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW);

		cgp.add(time, Callback.Constructors.addColor(Colors.CYAN));
		time = addWave(cgp, time, .9, false, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN);
		time = addWave(cgp, time, .9, true, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN);

		cgp.add(time, Callback.Constructors.addColor(Colors.ORANGE));
		time = addWave(cgp, time, .95, false, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN, Colors.ORANGE);
		time = addWave(cgp, time, .95, true, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN, Colors.ORANGE);

		cgp.add(time, Callback.Constructors.addColor(Colors.DARK_BLUE));
		time = addWave(cgp, time, .9, false, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN, Colors.ORANGE, Colors.DARK_BLUE);
		time = addWave(cgp, time, .9, true, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN, Colors.ORANGE, Colors.DARK_BLUE);

		cgp.add(time, Callback.Constructors.addColor(Colors.GRAY));
		time = addWave(cgp, time, .9, false, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN, Colors.ORANGE, Colors.DARK_BLUE, Colors.GRAY);
		time = addWave(cgp, time, .9, true, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN, Colors.ORANGE, Colors.DARK_BLUE, Colors.GRAY);
		
		for(double d = 0.9; d > 0.5; d -= 0.05) {
			time = addWave(cgp, time, d, true, Colors.BLUE, Colors.RED, Colors.GREEN, Colors.MAGENTA, Colors.YELLOW, Colors.CYAN, Colors.ORANGE, Colors.DARK_BLUE, Colors.GRAY);
		}

		lp.generators.add(cgp);
		
		return lp;
	}

	private static long addWave(CallbackGeneratorProto cgp, long time, double speed, boolean sumOrRandom, Colors... colors) {
		cgp.add(time + speedInc(1000, speed), Callback.Constructors.addEnemy(genRandom(sumOrRandom, colors)));
		cgp.add(time + speedInc(5000, speed), Callback.Constructors.addEnemy(genRandom(sumOrRandom, colors)));
		
		cgp.add(time + speedInc(10000, speed), Callback.Constructors.addEnemy(genFollow(sumOrRandom, colors)));
		
		cgp.add(time + speedInc(13000, speed), Callback.Constructors.addEnemy(genRandom(sumOrRandom, colors)));
		cgp.add(time + speedInc(13500, speed), Callback.Constructors.addEnemy(genFollow(sumOrRandom, colors)));
		
		cgp.add(time + speedInc(18000, speed), Callback.Constructors.addEnemy(genFollow(sumOrRandom, colors)));
		cgp.add(time + speedInc(18000, speed), Callback.Constructors.addEnemy(genFollow(sumOrRandom, colors)));
		cgp.add(time + speedInc(18000, speed), Callback.Constructors.addEnemy(genFollow(sumOrRandom, colors)));
		
		for(int i = 0; i < 4; i++) {
			cgp.add(time + speedInc(24000 + i * 100, speed), Callback.Constructors.addEnemy(genRandomFixed(TOP_LEFT, sumOrRandom, colors)));
			cgp.add(time + speedInc(24000 + i * 100, speed), Callback.Constructors.addEnemy(genRandomFixed(TOP_RIGHT, sumOrRandom, colors)));
			cgp.add(time + speedInc(24000 + i * 100, speed), Callback.Constructors.addEnemy(genRandomFixed(BOTTOM_LEFT, sumOrRandom, colors)));
			cgp.add(time + speedInc(24000 + i * 100, speed), Callback.Constructors.addEnemy(genRandomFixed(BOTTOM_RIGHT, sumOrRandom, colors)));
		}

		for(int i = 0; i < 2; i++) {
			cgp.add(time + speedInc(32000 + i * 100, speed), Callback.Constructors.addEnemy(genFollowFixed(TOP_LEFT, sumOrRandom, colors)));
			cgp.add(time + speedInc(32000 + i * 100, speed), Callback.Constructors.addEnemy(genFollowFixed(TOP_RIGHT, sumOrRandom, colors)));
			cgp.add(time + speedInc(32000 + i * 100, speed), Callback.Constructors.addEnemy(genFollowFixed(BOTTOM_LEFT, sumOrRandom, colors)));
			cgp.add(time + speedInc(32000 + i * 100, speed), Callback.Constructors.addEnemy(genFollowFixed(BOTTOM_RIGHT, sumOrRandom, colors)));
		}

		return time + speedInc(42000, speed);
	}
	
	private static long speedInc(long time, double inc) {
		double ntime = time * inc;
		return (long) ntime;
	}
	
	private static EnemyProto genRandom(boolean sumOrRandom, Colors... colors) {
		if(sumOrRandom) {
			return new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.random(300), 1.5, 1500, colors);
		} else {
			int num = rand.nextInt(colors.length);
			return new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.random(300), 1.5, 1500, colors[num]);
		}
	}

	private static EnemyProto genRandomFixed(Position position, boolean sumOrRandom, Colors... colors) {
		if(sumOrRandom) {
			return new EnemyProto(EnemyMover.Kinds.RANDOM, position, 1.5, 1500, colors);
		} else {
			int num = rand.nextInt(colors.length);
			return new EnemyProto(EnemyMover.Kinds.RANDOM, position, 1.5, 1500, colors[num]);
		}
	}

	private static EnemyProto genFollow(boolean sumOrRandom, Colors... colors) {
		if(sumOrRandom) {
			return new EnemyProto(EnemyMover.Kinds.FOLLOW, Position.Constructos.random(300), 2, 300, colors);
		} else {
			int num = rand.nextInt(colors.length);
			return new EnemyProto(EnemyMover.Kinds.FOLLOW, Position.Constructos.random(300), 2, 300, colors[num]);
		}
	}

	private static EnemyProto genFollowFixed(Position position, boolean sumOrRandom, Colors... colors) {
		if(sumOrRandom) {
			return new EnemyProto(EnemyMover.Kinds.FOLLOW, position, 2, 300, colors);
		} else {
			int num = rand.nextInt(colors.length);
			return new EnemyProto(EnemyMover.Kinds.FOLLOW, position, 2, 300, colors[num]);
		}
	}

	private static LevelProto makeLevel1() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(350, 150), 1.2, 1500, Colors.BLUE));

		return lp;
	}

	private static LevelProto makeLevel2() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(300, 50), 1.4, 1500, Colors.BLUE));
		lp.enemies.add(new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(350, 250), 1.5, 1000, Colors.BLUE));

		return lp;
	}

	private static LevelProto makeLevel3() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE, Colors.RED);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		lp.enemies.add(new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(300, 50), 1.4, 1500, Colors.BLUE));
		lp.enemies.add(new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(350, 250), 1.5, 1000, Colors.RED));

		return lp;
	}

	private static LevelProto makeLevel4() {
		PlayerProto player = new PlayerProto(50, 150, Colors.BLUE, Colors.RED);
		
		LevelProto lp = new LevelProto(400, 300, player);
		
		FixedGeneratorProto gen = new FixedGeneratorProto();
		gen.add(100, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(300, 150), 1.4, 1500, Colors.BLUE));
		gen.add(500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(300, 150), 1.3, 1200, Colors.RED));
		gen.add(1000, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(300, 150), 1.5, 1300, Colors.BLUE));
		gen.add(1500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(300, 150), 1.5, 1500, Colors.RED));
		gen.add(2500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(300, 150), 1.3, 1400, Colors.BLUE, Colors.RED));
		lp.generators.add(gen);

		return lp;
	}

	private static LevelProto makeLevel5() {
		PlayerProto player = new PlayerProto(300, 150, Colors.BLUE, Colors.RED);
		
		LevelProto lp = new LevelProto(600, 300, player);
		
		FixedGeneratorProto gen1 = new FixedGeneratorProto();
		gen1.add(100, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(150, 150), 1.4, 1500, Colors.BLUE));
		gen1.add(500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(150, 150), 1.3, 1200, Colors.RED));
		gen1.add(1000, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(150, 150), 1.5, 1300, Colors.BLUE));
		gen1.add(1500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(150, 150), 1.5, 1500, Colors.RED));
		gen1.add(2500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(150, 150), 1.3, 1400, Colors.BLUE, Colors.RED));
		lp.generators.add(gen1);

		FixedGeneratorProto gen2 = new FixedGeneratorProto();
		gen2.add(100, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(450, 150), 1.4, 1500, Colors.BLUE));
		gen2.add(500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(450, 150), 1.3, 1200, Colors.RED));
		gen2.add(1000, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(450, 150), 1.5, 1300, Colors.BLUE));
		gen2.add(1500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(450, 150), 1.5, 1500, Colors.RED));
		gen2.add(2500, new EnemyProto(EnemyMover.Kinds.RANDOM, Position.Constructos.fixed(450, 150), 1.3, 1400, Colors.BLUE, Colors.RED));
		lp.generators.add(gen2);

		return lp;
	}
}
