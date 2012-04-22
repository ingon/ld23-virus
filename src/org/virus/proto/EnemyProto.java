package org.virus.proto;

import org.virus.model.Colors;
import org.virus.model.EnemyMover;
import org.virus.model.Position;

public class EnemyProto extends ActiveProto {
	public final EnemyMover mover;
	public final double speed;
	public final long directionKeepTime;

	public EnemyProto(EnemyMover mover, Position position, double speed, long directionKeepTime, Colors... colors) {
		super(position, speed, colors);
		
		this.mover = mover;
		this.speed = speed;
		this.directionKeepTime = directionKeepTime;
	}
}
