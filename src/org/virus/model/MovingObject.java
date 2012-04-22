package org.virus.model;

import java.awt.Rectangle;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.tx.TxValue;
import org.game.utils.MathUtils;
import org.virus.PlayScreen;

public abstract class MovingObject implements GameObject {
	public final PlayScreen screen;

	public final TxPoint position;
	public final TxPoint impulse;
	public final TxValue<Double> speed;

	public MovingObject(PlayScreen screen, Position position, double speed) {
		this.screen = screen;
		this.position = new TxPoint(position.generate());
		this.impulse = new TxPoint(0, 0);
		this.speed = new TxValue<Double>(speed);
	}
	
	@Override
	public void update(TimeContext ctx) {
		double dx = impulse.x() * speed.get();
		double dy = impulse.y() * speed.get();

		Rectangle bounds = roughBounds();
		int hwidth = bounds.width / 2;
		int hheight = bounds.height / 2;
		
		double px = MathUtils.bound(position.x() + dx, hwidth, screen.proto.width - hwidth);
		double py = MathUtils.bound(position.y() + dy, hheight, screen.proto.height - hheight);

		double ax = px - position.x();
		double ay = py - position.y();

		position.ax(ax);
		position.ay(ay);
		
		updateConnected(ax, ay);
	}
	
	protected void updateConnected(double ax, double ay) {
	}
	
	protected abstract Rectangle roughBounds();
}
