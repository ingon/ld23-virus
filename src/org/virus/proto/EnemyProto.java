package org.virus.proto;

import org.virus.model.Colors;

public class EnemyProto {
	public final int x;
	public final int y;
	public final Colors color;
	
	public EnemyProto(int x, int y, Colors color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
