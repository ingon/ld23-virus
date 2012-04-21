package org.virus.model;

import java.awt.Graphics2D;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.virus.LevelScreen;
import org.virus.proto.PlayerProto;

public class Player implements GameObject {
	
	public static final int OVAL_SIZE = 20;
	
	public final LevelScreen screen;
	public final PlayerProto proto;
	public final TxPoint position;
	
	public Player(LevelScreen screen, PlayerProto proto) {
		this.screen = screen;
		this.proto = proto;
		
		position = new TxPoint(proto.x, proto.y);
	}
	
	@Override
	public void update(TimeContext ctx) {
		screen.updateView(position);
	}
	
	@Override
	public void paint(Graphics2D g) {
		g.fillOval(position.ix() - screen.view.ix() - OVAL_SIZE / 2, position.iy() - screen.view.iy() - OVAL_SIZE / 2, OVAL_SIZE, OVAL_SIZE);
	}
}
