package org.game.core;

import java.awt.Graphics2D;

public interface Game {
	public void init();
	public boolean update(TimeContext ctx);
	public void paint(Graphics2D g);
}
