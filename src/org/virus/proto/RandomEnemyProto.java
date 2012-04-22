package org.virus.proto;

import org.virus.model.Colors;
import org.virus.model.RandomEnemy;

public class RandomEnemyProto extends EnemyProto {
	public final long directionKeepTime;
	
	public RandomEnemyProto(int x, int y, double speed, long directionKeepTime, Colors... colors) {
		super(RandomEnemy.class, x, y, speed, colors);
		
		this.directionKeepTime = directionKeepTime;
	}
}
