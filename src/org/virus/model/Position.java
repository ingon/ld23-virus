package org.virus.model;

import java.awt.Point;
import java.util.Random;

import org.game.utils.MathUtils;
import org.virus.PlayScreen;

public interface Position {
	public Point generate(PlayScreen screen);
	
	public class Constructos {
		public static Position fixed(final int x, final int y) {
			return new Position() {
				@Override
				public Point generate(PlayScreen screen) {
					return new Point(x, y);
				}
			};
		}

		public static Position random(final int minDist) {
			final Random rand = new Random();
			return new Position() {
				@Override
				public Point generate(PlayScreen screen) {
					int x = rand.nextInt(screen.proto.width);
					int y = rand.nextInt(screen.proto.height);
					while(! isValid(screen, x, y)) {
						x = rand.nextInt(screen.proto.width);
						y = rand.nextInt(screen.proto.height);
					}
					return new Point(x, y);
				}
				
				private boolean isValid(PlayScreen screen, int x, int y) {
					return MathUtils.distance(screen.player.position.ix(), screen.player.position.iy(), x, y) >= minDist;
				}
			};
		}
	}
}
