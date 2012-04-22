package org.virus.model;

import java.awt.Point;

public enum Direction {
	NORTH(0, -1),
	SOUTH(0, 1),
	WEST(-1, 0),
	EAST(1, 0);
	
	public final Point normal;
	public final Point reverse;
	
	private Direction(int x, int y) {
		normal = new Point(x, y);
		reverse = new Point(-x, -y);
	}
}
