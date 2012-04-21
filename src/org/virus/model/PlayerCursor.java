package org.virus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.utils.MathUtils;

public class PlayerCursor implements GameObject {
	public final TxPoint position;
	
	public PlayerCursor() {
		position = new TxPoint(MouseInfo.getPointerInfo().getLocation());
	}
	
	@Override
	public void update(TimeContext ctx) {
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.drawLine(position.ix(), position.iy(), position.ix(), position.iy());
		g.drawOval(position.ix() - 3, position.iy() - 3, 6, 6);
		g.drawOval(position.ix() - 6, position.iy() - 6, 12, 12);
		g.drawOval(position.ix() - 10, position.iy() - 10, 20, 20);
	}

	public void move(int x, int y) {
		x = MathUtils.bound(x, 799);
		y = MathUtils.bound(y, 599);
		position.xy(x, y);
	}
}
