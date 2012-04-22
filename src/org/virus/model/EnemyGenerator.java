package org.virus.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.virus.PlayScreen;
import org.virus.proto.EnemyGeneratorProto;
import org.virus.proto.EnemyProto;

public class EnemyGenerator implements GameObject {
	
	public final PlayScreen screen;
	public final EnemyGeneratorProto proto;
	
	public final List<Long> times;
	public final List<EnemyProto> enemies;

	private long beginTime = -1;
	
	public EnemyGenerator(PlayScreen screen, EnemyGeneratorProto proto) {
		this.screen = screen;
		this.proto = proto;
		
		this.times = new ArrayList<Long>(proto.times);
		this.enemies = new ArrayList<EnemyProto>(proto.enemies);
	}
	
	@Override
	public void update(TimeContext ctx) {
		if(beginTime == -1) {
			beginTime = ctx.time;
		}
		
		long dist = ctx.time - beginTime;
		while(! times.isEmpty() && dist >= times.get(0)) {
			times.remove(0);
			screen.spawn(enemies.remove(0));
		}
		
		if(times.isEmpty()) {
			screen.enemyGenerators.remove(this);
		}
	}

	@Override
	public void paint(Graphics2D g) {
	}
}
