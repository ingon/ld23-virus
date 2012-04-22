package org.virus.proto;

import org.virus.model.Colors;
import org.virus.model.Position;

public class PlayerProto extends ActiveProto {
	public PlayerProto(int x, int y, Colors... colors) {
		this(x, y, 1., colors);
	}
	
	public PlayerProto(int x, int y, double speed, Colors... colors) {
		super(Position.Constructos.fixed(x, y), speed, colors);
	}
}
