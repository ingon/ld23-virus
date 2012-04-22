package org.virus.model;

import java.util.ArrayList;
import java.util.List;

import org.virus.PlayScreen;
import org.virus.proto.EnemyProto;
import org.virus.proto.FixedGeneratorProto;

public class FixedGenerator extends Generator<FixedGeneratorProto> {
	public final List<EnemyProto> enemies;
	
	public FixedGenerator(PlayScreen screen, FixedGeneratorProto proto) {
		super(screen, proto);
		
		this.enemies = new ArrayList<EnemyProto>(proto.enemies);
	}

	@Override
	protected void executeNext() {
		screen.spawn(enemies.remove(0));
	}
}
