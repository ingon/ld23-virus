package org.virus.model;

import java.awt.Color;
import java.util.Random;

import org.game.core.TimeContext;
import org.virus.LevelScreen;
import org.virus.proto.EnemyProto;

public class RandomEnemy extends Enemy {
	private final Random rand = new Random();
	private long lastTime = -1;
	private long updateInterval = 1500;
	
	public RandomEnemy(LevelScreen screen, EnemyProto proto) {
		super(screen, proto);
		speed.set(1.4);
	}

	@Override
	public void update(TimeContext ctx) {
		if(lastTime == -1 || lastTime + updateInterval < ctx.time) {
			int rx = rand.nextInt(21) - 10;
			int ry = rand.nextInt(21) - 10;
			double nt = Math.abs(rx) + Math.abs(ry);
			impulse.xy(rx / nt, ry / nt);
			
			lastTime = ctx.time;
		}
		
		super.update(ctx);
	}
	
	@Override
	protected Color shadowColor() {
		return Colors.RANDOM_SHADOW;
	}
}
