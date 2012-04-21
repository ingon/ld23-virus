package org.game.core;

import org.game.tx.TxManager;

public class UpdateThread extends Thread {
	public static final int DEFAULT_SLEEP_TIME = 10;
	public static final int DEFAULT_MAX_SLEEP_TIME = 1000;
	
	private final Object lock = new Object();
	private long sleepTime;
	private long currentSleepTime;
	private long maxSleepTime;
	private boolean running = true;
	
	private final Game delegate;
	private final TimeContext ctx = new TimeContext();
	private final Runnable initRunnable = new Runnable() {
		@Override
		public void run() {
			delegate.init();
		}
	};
	private final Runnable updateRunnable = new Runnable() {
		@Override
		public void run() {
			delegate.update(ctx);
		}
	};
	
	public UpdateThread(Game delegate) {
		this(delegate, DEFAULT_SLEEP_TIME, DEFAULT_MAX_SLEEP_TIME);
	}
	
	public UpdateThread(Game delegate, long sleepTime, long maxSleepTime) {
		this.sleepTime = sleepTime;
		this.currentSleepTime = sleepTime;
		this.maxSleepTime = maxSleepTime;
		this.delegate = delegate;
	}
	
	public void run() {
		ctx.init();
		TxManager.getInstance().write(initRunnable);
		
		try {
			while(running) {
				synchronized (lock) {
					lock.wait(currentSleepTime);
				}
	            ctx.tick();
	            TxManager.getInstance().write(updateRunnable);
			}
		} catch(InterruptedException exc) {
		}
	}
	
	public void slowdown() {
		if(currentSleepTime < maxSleepTime) {
			currentSleepTime *= 2;
		}
	}
	
	public void speedup() {
		currentSleepTime = sleepTime;
		synchronized (lock) {
			lock.notify();
		}
	}
}