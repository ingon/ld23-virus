package org.virus.proto;

import java.util.ArrayList;
import java.util.List;

import org.virus.model.FixedGenerator;

public class FixedGeneratorProto extends GeneratorProto {
	public final List<EnemyProto> enemies;

	public FixedGeneratorProto() {
		super(FixedGenerator.class);
		enemies = new ArrayList<EnemyProto>();
	}

	public void add(long time, EnemyProto proto) {
		times.add(time);
		enemies.add(proto);
	}
}
