package org.virus.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.virus.PlayScreen;
import org.virus.proto.GeneratorProto;

public abstract class Generator<EGP extends GeneratorProto> implements GameObject {
	
	public final PlayScreen screen;
	public final EGP proto;
	
	public final List<Long> times;

	private long beginTime = -1;
	
	public Generator(PlayScreen screen, EGP proto) {
		this.screen = screen;
		this.proto = proto;
		
		this.times = new ArrayList<Long>(proto.times);
	}
	
	@Override
	public void update(TimeContext ctx) {
		if(beginTime == -1) {
			beginTime = ctx.time;
		}
		
		long dist = ctx.time - beginTime;
		while(! times.isEmpty() && dist >= times.get(0)) {
			times.remove(0);
			executeNext();
		}
		
		if(times.isEmpty()) {
			screen.enemyGenerators.remove(this);
		}
	}
	
	protected abstract void executeNext();

	@Override
	public void paint(Graphics2D g) {
	}
}
