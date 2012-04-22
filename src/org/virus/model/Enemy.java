package org.virus.model;

import java.awt.Color;

import org.game.core.TimeContext;
import org.virus.PlayScreen;
import org.virus.proto.EnemyProto;

public class Enemy extends ActiveObject<EnemyProto> {
	private long directionChangeTime = -1;
	
	public Enemy(PlayScreen screen, EnemyProto proto) {
		super(screen, proto);
	}

	@Override
	public void update(TimeContext ctx) {
		if(directionChangeTime == -1 || directionChangeTime + proto.directionKeepTime < ctx.time) {
			if(proto.mover.move(screen, this)) {
				directionChangeTime = ctx.time;
			}
		}
		
		super.update(ctx);
	}
	
	@Override
	protected Color shadowColor() {
		return proto.mover.shadowColor();
	}

	public boolean colorHit(Colors color) {
		if(colors.contains(color)) {
			colors.remove(color);
			if(! colors.isEmpty())
				initPartials();
			return true;
		} else {
			colors.add(color);
			initPartials();
			return false;
		}
	}
}
