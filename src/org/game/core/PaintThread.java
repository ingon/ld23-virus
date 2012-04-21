package org.game.core;

import java.awt.Graphics2D;

import org.game.tx.TxManager;

public class PaintThread extends Thread {
	private final Game delegate;
	private final GraphicsContext ctx;
	private boolean running = true;
	
	private final Runnable paintRunnable = new Runnable() {
		@Override
		public void run() {
			delegate.paint(ctx.graphics());
		}
	};

	public PaintThread(Graphics2D screenGraphics, Game delegate) {
		this.delegate = delegate;
		this.ctx = new GraphicsContext(screenGraphics);
	}
	
	@Override
	public void run() {
		while(running) {
			ctx.paintStart();
			TxManager.getInstance().read(paintRunnable);
			ctx.paintEnd();
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
