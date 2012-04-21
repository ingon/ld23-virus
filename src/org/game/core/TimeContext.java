package org.game.core;

public class TimeContext {
	public long beginTime;
	public long lastTime;
	public long time;
	public long interval;
	
	public void init() {
		beginTime = time = System.currentTimeMillis();
	}
	
	public void tick() {
		lastTime = time;
		time = System.currentTimeMillis();
		interval = time - lastTime;
	}
}
