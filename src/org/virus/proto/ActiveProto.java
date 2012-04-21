package org.virus.proto;

import org.virus.model.Colors;

public class ActiveProto {
	public final int x;
	public final int y;
	public final Colors[] colors;
	
	public ActiveProto(int x, int y, Colors[] colors) {
		this.x = x;
		this.y = y;
		this.colors = colors;
	}
}
