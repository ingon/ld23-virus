package org.virus.model;

import java.awt.Graphics2D;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.virus.PlayScreen;

public class Background implements GameObject {
	public final PlayScreen screen;
	
	public Background(PlayScreen screen) {
		this.screen = screen;
	}
	
	@Override
	public void update(TimeContext ctx) {
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(screen.player.color.get().shadow);
		g.fillRect(0, 0, 800, 600);
	}
}
