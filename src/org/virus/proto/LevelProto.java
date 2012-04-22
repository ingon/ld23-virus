package org.virus.proto;

import java.util.ArrayList;
import java.util.List;

public class LevelProto {
	public final int width;
	public final int height;
	
	public final PlayerProto player;
	
	public final List<EnemyProto> enemies;
	public final List<EnemyGeneratorProto> enemyGenerators;
	
	public LevelProto(int width, int height, PlayerProto player) {
		this.width = width;
		this.height = height;
		
		this.player = player;
		
		this.enemies = new ArrayList<EnemyProto>();
		this.enemyGenerators = new ArrayList<EnemyGeneratorProto>();
	}
}
