package org.virus.model;

import org.virus.LevelScreen;
import org.virus.proto.EnemyProto;

public abstract class Enemy extends ActiveObject<EnemyProto> {
	public Enemy(LevelScreen screen, EnemyProto proto) {
		super(screen, proto);
	}

	public boolean removeColor(Colors color) {
		if(colors.contains(color)) {
			colors.remove(color);
			if(! colors.isEmpty())
				initPartials();
			return true;
		} else {
			return false;
		}
	}
}
