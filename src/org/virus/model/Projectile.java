package org.virus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.game.core.TimeContext;
import org.game.tx.TxValue;
import org.game.utils.MathUtils;
import org.virus.LevelScreen;

public class Projectile extends MovingObject {
	public final Colors color;
	public final TxValue<Rectangle> bounds;
	
	public Projectile(LevelScreen screen, Colors color, int px, int py, double dx, double dy) {
		super(screen, px, py, 3);
		impulse.xy(dx, dy);
		
		this.color = color;
		bounds = new TxValue<Rectangle>(new Rectangle(position.ix() - 3, position.iy() - 3, 6, 6));
	}

	@Override
	public void update(TimeContext ctx) {
		position.ax(impulse.x() * speed.get());
		position.ay(impulse.y() * speed.get());
		
		if(! (MathUtils.inBound(position.x(), screen.playground.width) && MathUtils.inBound(position.y(), screen.playground.height))) {
			screen.playerFire.remove(this);
		}
		
		bounds.set(new Rectangle(position.ix() - 3, position.iy() - 3, 6, 6));
	}
	
	@Override
	public void paint(Graphics2D g) {
		g.setColor(color.main);
		g.fillRect(bounds.get().x - screen.view.ix(), bounds.get().y - screen.view.iy(), bounds.get().width, bounds.get().height);
	}
}
