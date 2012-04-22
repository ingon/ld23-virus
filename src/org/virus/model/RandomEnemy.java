package org.virus.model;

import java.awt.Color;
import java.util.Random;

import org.game.core.TimeContext;
import org.virus.PlayScreen;
import org.virus.proto.RandomEnemyProto;

public class RandomEnemy extends Enemy<RandomEnemyProto> {
	private final Random rand = new Random();
	private long directionChangeTime = -1;
	
	public RandomEnemy(PlayScreen screen, RandomEnemyProto proto) {
		super(screen, proto);
	}

	@Override
	public void update(TimeContext ctx) {
		if(directionChangeTime == -1 || directionChangeTime + proto.directionKeepTime < ctx.time) {
			int screenPartWidth = screen.playground.width / 10;
			int screenPartHeight = screen.playground.height / 10;
			
			int rx = 0;
			int ry = 0;
			
			if(position.ix() < screenPartWidth) {
				rx = rand.nextInt(11);
			} else if(position.ix() > screen.playground.width - screenPartWidth) {
				rx = rand.nextInt(11) - 10;
			} else {
				rx = rand.nextInt(21) - 10;
			}
			
			if(position.iy() < screenPartHeight) {
				ry = rand.nextInt(11);
			} else if(position.iy() > screen.playground.height - screenPartHeight) {
				ry = rand.nextInt(11) - 10;
			} else {
				ry = rand.nextInt(21) - 10;
			}
			
			double nt = Math.abs(rx) + Math.abs(ry);
			if(nt != 0) {
				impulse.xy(rx / nt, ry / nt);
				directionChangeTime = ctx.time;
			}
		}
		
		super.update(ctx);
	}
	
	@Override
	protected Color shadowColor() {
		return Colors.RANDOM_SHADOW;
	}
}
