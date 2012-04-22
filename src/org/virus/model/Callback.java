package org.virus.model;

import org.virus.PlayScreen;

public interface Callback {
	public void execute(PlayScreen screen);
	
	public class Constructors {
		public static Callback addColor(final Colors c) {
			return new Callback() {
				@Override
				public void execute(PlayScreen screen) {
					screen.player.colors.add(c);
				}
			};
		}
	}
}
