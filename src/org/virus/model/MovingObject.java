package org.virus.model;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.tx.TxValue;
import org.game.utils.MathUtils;
import org.virus.LevelScreen;

public abstract class MovingObject implements GameObject {
	public final LevelScreen screen;

	public final TxPoint position;
	public final TxPoint impulse;
	public final TxValue<Double> speed;

	public MovingObject(LevelScreen screen, int px, int py, double speed) {
		this.screen = screen;
		this.position = new TxPoint(px, py);
		this.impulse = new TxPoint(0, 0);
		this.speed = new TxValue<Double>(speed);
	}
	
	@Override
	public void update(TimeContext ctx) {
		double dx = impulse.x() * speed.get();
		double dy = impulse.y() * speed.get();

		double px = MathUtils.bound(position.x() + dx, screen.proto.width);
		double py = MathUtils.bound(position.y() + dy, screen.proto.height);

		double ax = px - position.x();
		double ay = py - position.y();

		position.ax(ax);
		position.ay(ay);
		
		updateConnected(ax, ay);
	}
	
	protected void updateConnected(double ax, double ay) {
	}
}
