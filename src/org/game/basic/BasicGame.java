package org.game.basic;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.game.core.Game;
import org.game.core.TimeContext;
import org.game.tx.TxManager;
import org.game.tx.TxValue;

public abstract class BasicGame implements Game, KeyListener, MouseListener, MouseMotionListener {
	private boolean inited = false;
	private TxValue<BasicGameScreen<?>> currentScreen;
	
	public BasicGame() {
	}
	
	@Override
	public void init() {
		currentScreen = new TxValue<BasicGameScreen<?>>(createInitialScreen());
		inited = true;
	}

	@Override
	public boolean update(TimeContext ctx) {
		currentScreen.get().update(ctx);
		return true;
	}

	@Override
	public void paint(Graphics2D g) {
		if(inited)
			currentScreen.get().paint(g);
	}
	
	protected abstract BasicGameScreen<?> createInitialScreen();
	
	public void showScreen(BasicGameScreen<?> screen) {
		currentScreen.set(screen);
	}

	public void keyTyped(final KeyEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().keyTyped(e);
			}
		});
	}
	public void keyPressed(final KeyEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().keyPressed(e);
			}
		});
	}
	public void keyReleased(final KeyEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().keyReleased(e);
			}
		});
	}
	
	public void mouseClicked(final MouseEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().mouseClicked(e);
			}
		});
	}
	public void mouseEntered(final MouseEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().mouseEntered(e);
			}
		});
	}
	public void mouseExited(final MouseEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().mouseExited(e);
			}
		});
	}
	public void mousePressed(final MouseEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().mousePressed(e);
			}
		});
	}
	public void mouseReleased(final MouseEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().mouseReleased(e);
			}
		});
	}
	
	public void mouseDragged(final MouseEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().mouseDragged(e);
			}
		});
	}
	public void mouseMoved(final MouseEvent e) {
		TxManager.getInstance().write(new Runnable() {
			@Override
			public void run() {
				currentScreen.get().mouseMoved(e);
			}
		});
	}
}
