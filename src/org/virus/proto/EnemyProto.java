package org.virus.proto;

import org.virus.model.Colors;

public class EnemyProto extends ActiveProto {
	public final Class<?> type;
	public final double speed;
	
	public EnemyProto(Class<?> type, int x, int y, double speed, Colors... colors) {
		super(x, y, speed, colors);
		
		this.type = type;
		this.speed = speed;
	}
}
