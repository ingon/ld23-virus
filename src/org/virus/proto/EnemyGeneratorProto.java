package org.virus.proto;

import java.util.ArrayList;
import java.util.List;

public class EnemyGeneratorProto {
	public final List<Long> times;
	public final List<EnemyProto> enemies;
	
	public EnemyGeneratorProto() {
		times = new ArrayList<Long>();
		enemies = new ArrayList<EnemyProto>();
	}
	
	public void add(long time, EnemyProto proto) {
		times.add(time);
		enemies.add(proto);
	}
}
