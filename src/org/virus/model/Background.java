package org.virus.model;

import java.awt.Color;
import java.awt.Graphics2D;

import org.game.core.GameObject;
import org.game.core.TimeContext;

public class Background implements GameObject {
	@Override
	public void update(TimeContext ctx) {
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 800, 600);
	}
}
