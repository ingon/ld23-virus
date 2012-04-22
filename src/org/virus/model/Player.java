package org.virus.model;

import java.awt.Color;

import org.game.core.TimeContext;
import org.game.tx.TxValue;
import org.virus.LevelScreen;
import org.virus.proto.PlayerProto;

public class Player extends ActiveObject<PlayerProto> {
	public final TxValue<Colors> color;
	private boolean[] currentDirections = new boolean[Direction.values().length];
	
	public Player(LevelScreen screen, PlayerProto proto) {
		super(screen, proto);
		
		color = new TxValue<Colors>(Colors.BLUE);
	}

	@Override
	public void update(TimeContext ctx) {
		super.update(ctx);
		
		screen.updateView(position);
	}
	
	@Override
	protected Color shadowColor() {
		return color.get().shadow;
	}
	
	public void addMove(Direction direction) {
		int num = direction.ordinal();
		if(! currentDirections[num]) {
			currentDirections[num] = true;
			impulse.axy(direction.normal);
			System.out.println("AImpulse: " + impulse);
		}
	}

	public void removeMove(Direction direction) {
		int num = direction.ordinal();
		if(currentDirections[num]) {
			currentDirections[num] = false;
			impulse.axy(direction.reverse);
			System.out.println("RImpulse: " + impulse);
		}
	}

	public void fire(int x, int y) {
		int px = screen.toAbsX(x);
		int py = screen.toAbsY(y);
		
		double dx = px - position.x();
		double dy = py - position.y();

		double nt = Math.abs(dx) + Math.abs(dy);
		if(nt != 0)
			screen.playerFire.add(new Projectile(screen, color.get(), position.ix(), position.iy(), dx / nt, dy / nt));
	}
	
	public void changeColor() {
		int ord = color.get().ordinal();
		for(int i = ord + 1; i < Colors.values().length; i++) {
			Colors c = Colors.values()[i];
			if(colors.contains(c)) {
				color.set(c);
			}
		}
		for(int i = 0; i < ord; i++) {
			Colors c = Colors.values()[i];
			if(colors.contains(c)) {
				color.set(c);
			}
		}
	}
}
