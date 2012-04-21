package org.game.basic;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.game.core.GameObject;
import org.game.core.TimeContext;

public abstract class BasicGameScreen<T extends BasicGame> implements GameObject {
	public final T game;
	
	public BasicGameScreen(T game) {
		this.game = game;
	}
	
	@Override
	public void update(TimeContext ctx) {}
	@Override
	public void paint(Graphics2D g) {}
	
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
