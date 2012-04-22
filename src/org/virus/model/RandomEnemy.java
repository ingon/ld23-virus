package org.virus.model;

import java.awt.Color;
import java.util.Random;

import org.game.core.TimeContext;
import org.virus.LevelScreen;
import org.virus.proto.RandomEnemyProto;

public class RandomEnemy extends Enemy<RandomEnemyProto> {
	private final Random rand = new Random();
	private long directionChangeTime = -1;
	
	public RandomEnemy(LevelScreen screen, RandomEnemyProto proto) {
		super(screen, proto);
	}

	@Override
	public void update(TimeContext ctx) {
		if(directionChangeTime == -1 || directionChangeTime + proto.directionKeepTime < ctx.time) {
			int rx = rand.nextInt(21) - 10;
			int ry = rand.nextInt(21) - 10;
			double nt = Math.abs(rx) + Math.abs(ry);
			if(nt != 0)
				impulse.xy(rx / nt, ry / nt);
			
			directionChangeTime = ctx.time;
		}
		
		super.update(ctx);
	}
	
	@Override
	protected Color shadowColor() {
		return Colors.RANDOM_SHADOW;
	}
}
