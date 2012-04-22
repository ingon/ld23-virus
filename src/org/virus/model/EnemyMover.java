package org.virus.model;

import java.awt.Color;

import org.virus.PlayScreen;

public interface EnemyMover {
	public boolean move(PlayScreen screen, Enemy enemy);
	public Color shadowColor();
	
	public class Kinds {
		public static final EnemyMover RANDOM = new EnemyMover() {
			private final java.util.Random rand = new java.util.Random();
			
			@Override
			public boolean move(PlayScreen screen, Enemy enemy) {
				int screenPartWidth = screen.playground.width / 10;
				int screenPartHeight = screen.playground.height / 10;
				
				int rx = 0;
				int ry = 0;
				
				if(enemy.position.ix() < screenPartWidth) {
					rx = rand.nextInt(11);
				} else if(enemy.position.ix() > screen.playground.width - screenPartWidth) {
					rx = rand.nextInt(11) - 10;
				} else {
					rx = rand.nextInt(21) - 10;
				}
				
				if(enemy.position.iy() < screenPartHeight) {
					ry = rand.nextInt(11);
				} else if(enemy.position.iy() > screen.playground.height - screenPartHeight) {
					ry = rand.nextInt(11) - 10;
				} else {
					ry = rand.nextInt(21) - 10;
				}
				
				double nt = Math.abs(rx) + Math.abs(ry);
				if(nt != 0)
					enemy.impulse.xy(rx / nt, ry / nt);
				
				return nt != 0;
			}
			
			@Override
			public Color shadowColor() {
				return Colors.RANDOM_SHADOW;
			}
		};
		
		public static final EnemyMover FOLLOW = new EnemyMover() {
			@Override
			public boolean move(PlayScreen screen, Enemy enemy) {
				double dx = screen.player.position.x() - enemy.position.x();
				double dy = screen.player.position.y() - enemy.position.y();
				double nt = Math.abs(dx) + Math.abs(dy);
				if(nt != 0)
					enemy.impulse.xy(dx / nt, dy / nt);
				
				return nt != 0;
			}
			
			@Override
			public Color shadowColor() {
				return Colors.FOLLOW_SHADOW;
			}
		};
	}
}
