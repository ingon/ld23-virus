package org.game.core;

import java.awt.Graphics2D;

public interface GameObject {
	public void update(TimeContext ctx);
	public void paint(Graphics2D g);
}
