package org.virus.model;

import org.virus.PlayScreen;
import org.virus.proto.EnemyProto;

public interface Callback {
	public void execute(PlayScreen screen);
	
	public class Constructors {
		public static Callback addColor(final Colors c) {
			return new Callback() {
				@Override
				public void execute(PlayScreen screen) {
					screen.player.addColor(c);
				}
			};
		}
		
		public static Callback addEnemy(final EnemyProto proto) {
			return new Callback() {
				@Override
				public void execute(PlayScreen screen) {
					screen.spawn(proto);
				}
			};
		}
	}
}
