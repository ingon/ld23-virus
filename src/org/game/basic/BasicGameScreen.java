package org.game.basic;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.game.core.GameObject;
import org.game.core.TimeContext;

public abstract class BasicGameScreen<T extends BasicGame> implements GameObject, KeyListener, MouseListener, MouseMotionListener {
	public final T game;
	
	public BasicGameScreen(T game) {
		this.game = game;
	}
	
	@Override
	public void update(TimeContext ctx) {}
	@Override
	public void paint(Graphics2D g) {}
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}
