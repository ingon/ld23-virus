package org.virus.model;

import java.awt.Graphics2D;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.tx.TxValue;
import org.game.utils.MathUtils;
import org.virus.LevelScreen;
import org.virus.proto.PlayerProto;

public class Player implements GameObject {
	
	public static final int SIZE = 15;
	public static final int HSIZE = SIZE / 2;
	public static final int CURVE = 5;
	public static final int SHADINC = 5;
	public static final int DSHADINC = SHADINC * 2;
	
	public final LevelScreen screen;
	public final PlayerProto proto;
	
	public final TxPoint position;
	public final TxPoint impulse;	
	public final TxValue<Double> speed;
	
	public final TxValue<Colors> color;
	
	public Player(LevelScreen screen, PlayerProto proto) {
		this.screen = screen;
		this.proto = proto;
		
		position = new TxPoint(proto.x, proto.y);
		impulse = new TxPoint(0, 0);
		speed = new TxValue<Double>(1.);
		
		color = new TxValue<Colors>(Colors.BLUE);
	}
	
	@Override
	public void update(TimeContext ctx) {
		position.ax(impulse.x() * speed.get());
		position.ay(impulse.y() * speed.get());
		
		position.x(MathUtils.bound(position.x(), screen.proto.width));
		position.y(MathUtils.bound(position.y(), screen.proto.height));
		
		screen.updateView(position);
	}
	
	@Override
	public void paint(Graphics2D g) {
		g.setColor(color.get().shadow);
		g.fillRoundRect(position.ix() - screen.view.ix() - HSIZE - SHADINC, position.iy() - screen.view.iy() - HSIZE - SHADINC, SIZE + DSHADINC, SIZE + DSHADINC, CURVE + SHADINC, CURVE + SHADINC);
		
		g.setColor(color.get().main);
		g.fillRoundRect(position.ix() - screen.view.ix() - HSIZE, position.iy() - screen.view.iy() - HSIZE, SIZE, SIZE, CURVE, CURVE);
	}

	public void impulse(int x, int y) {
		impulse.ax(x).ay(y);
	}

	public void fire(int x, int y) {
		int px = screen.toAbsX(x);
		int py = screen.toAbsY(y);
		
		double dx = px - position.x();
		double dy = py - position.y();

		double nt = Math.abs(dx) + Math.abs(dy);
		screen.playerFire.add(new Projectile(screen, color.get(), position.ix(), position.iy(), dx / nt, dy / nt));
	}
}
