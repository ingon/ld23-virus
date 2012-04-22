package org.virus.model;

import java.awt.Color;

import org.game.core.TimeContext;
import org.virus.LevelScreen;
import org.virus.proto.FollowEnemyProto;

public class FollowEnemy extends Enemy<FollowEnemyProto> {
	private long directionChangeTime = -1;

	public FollowEnemy(LevelScreen screen, FollowEnemyProto proto) {
		super(screen, proto);
	}

	@Override
	public void update(TimeContext ctx) {
		if(directionChangeTime == -1 || directionChangeTime + proto.directionKeepTime < ctx.time) {
			double dx = screen.player.position.x() - position.x();
			double dy = screen.player.position.y() - position.y();
			double nt = Math.abs(dx) + Math.abs(dy);
			if(nt != 0)
				impulse.xy(dx / nt, dy / nt);
		}
		
		super.update(ctx);
	}
	
	@Override
	protected Color shadowColor() {
		return Colors.FOLLOW_SHADOW;
	}
}
