package org.virus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.game.core.TimeContext;
import org.game.tx.TxValue;
import org.game.utils.MathUtils;
import org.virus.PlayScreen;

public class Projectile extends MovingObject {
	public static final int SIZE = 8;
	public static final int HSIZE = SIZE / 2;
	
	public final Colors color;
	public final TxValue<Rectangle> bounds;
	
	public Projectile(PlayScreen screen, Colors color, int px, int py, double dx, double dy) {
		super(screen, Position.Constructos.fixed(px, py), 7);
		impulse.xy(dx, dy);
		
		this.color = color;
		bounds = new TxValue<Rectangle>(new Rectangle(position.ix() - HSIZE, position.iy() - HSIZE, SIZE, SIZE));
	}

	@Override
	public void update(TimeContext ctx) {
		position.ax(impulse.x() * speed.get());
		position.ay(impulse.y() * speed.get());
		
		if(! (MathUtils.inBound(position.x(), screen.playground.width) && MathUtils.inBound(position.y(), screen.playground.height))) {
			screen.playerFire.remove(this);
		}
		
		bounds.set(new Rectangle(position.ix() - HSIZE, position.iy() - HSIZE, SIZE, SIZE));
	}
	
	@Override
	public void paint(Graphics2D g) {
		g.setColor(color.main);
		g.fillRoundRect(bounds.get().x - screen.view.ix(), bounds.get().y - screen.view.iy(), bounds.get().width, bounds.get().height, 3, 3);
	}
	
	@Override
	protected Rectangle roughBounds() {
		throw new RuntimeException("Should not be used");
	}
}
