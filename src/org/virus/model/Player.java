package org.virus.model;

import java.awt.Color;
import java.awt.Graphics2D;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.tx.TxValue;
import org.game.utils.MathUtils;
import org.virus.LevelScreen;
import org.virus.proto.PlayerProto;

public class Player implements GameObject {
	
	public static final int SIZE = 20;
	public static final int HSIZE = SIZE / 2;
	
	public final LevelScreen screen;
	public final PlayerProto proto;
	public final TxPoint position;
	public final TxPoint impulse;	
	public final TxValue<Double> speed;
	
	public Player(LevelScreen screen, PlayerProto proto) {
		this.screen = screen;
		this.proto = proto;
		
		position = new TxPoint(proto.x, proto.y);
		impulse = new TxPoint(0, 0);
		speed = new TxValue<Double>(1.);
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
		g.setColor(new Color(128, 128, 128, 128));
		g.fillRoundRect(position.ix() - screen.view.ix() - HSIZE - 1, position.iy() - screen.view.iy() - HSIZE - 1, SIZE + 2, SIZE + 2, 6, 6);
		
		g.setColor(new Color(32, 32, 255));
		g.fillRoundRect(position.ix() - screen.view.ix() - HSIZE, position.iy() - screen.view.iy() - HSIZE, SIZE, SIZE, 5, 5);
	}

	public void impulse(int x, int y) {
		impulse.ax(x).ay(y);
	}
}
