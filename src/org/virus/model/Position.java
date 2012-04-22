package org.virus.model;

import java.awt.Point;

public interface Position {
	public Point generate();
	
	public class Constructos {
		public static Position fixed(final int x, final int y) {
			return new Position() {
				@Override
				public Point generate() {
					return new Point(x, y);
				}
			};
		}
	}
}
