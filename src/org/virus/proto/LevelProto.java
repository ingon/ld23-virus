package org.virus.proto;

import java.util.ArrayList;
import java.util.List;

public class LevelProto {
	public final int width;
	public final int height;
	
	public final PlayerProto player;
	public final List<EnemyProto> enemies;
	
	public LevelProto(int width, int height, int px, int py) {
		this.width = width;
		this.height = height;
		
		player = new PlayerProto(px, py);
		enemies = new ArrayList<EnemyProto>();
	}
}
