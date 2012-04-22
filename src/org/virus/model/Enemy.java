package org.virus.model;

import org.virus.PlayScreen;
import org.virus.proto.EnemyProto;

public abstract class Enemy<E extends EnemyProto> extends ActiveObject<E> {
	public Enemy(PlayScreen screen, E proto) {
		super(screen, proto);
		
		speed.set(proto.speed);
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
