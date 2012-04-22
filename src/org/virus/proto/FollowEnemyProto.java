package org.virus.proto;

import org.virus.model.Colors;
import org.virus.model.FollowEnemy;

public class FollowEnemyProto extends EnemyProto {
	public final long directionKeepTime;
	
	public FollowEnemyProto(int x, int y, double speed, long directionKeepTime, Colors... colors) {
		super(FollowEnemy.class, x, y, speed, colors);
		
		this.directionKeepTime = directionKeepTime;
	}
}
