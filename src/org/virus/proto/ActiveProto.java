package org.virus.proto;

import org.virus.model.Colors;
import org.virus.model.Position;

public class ActiveProto {
	public final Position position;
	public final double speed;
	public final Colors[] colors;
	
	public ActiveProto(Position position, double speed, Colors[] colors) {
		this.position = position;
		this.speed = speed;
		this.colors = colors;
	}
}
