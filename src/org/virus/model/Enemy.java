package org.virus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.tx.TxValue;
import org.virus.LevelScreen;
import org.virus.proto.EnemyProto;

public class Enemy implements GameObject {
	public static final int SIZE = 15;
	public static final int HSIZE = SIZE / 2;
	public static final int CURVE = 5;
	public static final int SHADINC = 5;
	public static final int DSHADINC = SHADINC * 2;
	
	public static final Color SHADOW = new Color(128, 128, 128, 128);
	
	public final LevelScreen screen;
	public final EnemyProto proto;
	
	public final Colors color;
	
	public final TxPoint position;
	public final TxValue<Rectangle> bounds;
	
	public Enemy(LevelScreen screen, EnemyProto proto) {
		this.screen = screen;
		this.proto = proto;
		
		this.color = proto.color;
		
		this.position = new TxPoint(proto.x, proto.y);
		this.bounds = new TxValue<Rectangle>(new Rectangle(position.ix() - HSIZE - SHADINC, position.iy() - HSIZE - SHADINC, SIZE + DSHADINC, SIZE + DSHADINC));
	}
	
	@Override
	public void update(TimeContext ctx) {
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(SHADOW);
		g.fillRoundRect(bounds.get().x - screen.view.ix(), bounds.get().y - screen.view.iy(), bounds.get().width, bounds.get().height, CURVE + SHADINC, CURVE + SHADINC);
		
		g.setColor(color.main);
		g.fillRoundRect(position.ix() - screen.view.ix() - HSIZE, position.iy() - screen.view.iy() - HSIZE, SIZE, SIZE, CURVE, CURVE);
	}
}
