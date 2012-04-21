package org.virus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.game.basic.BasicGameScreen;

public class CreditsScreen extends BasicGameScreen<VirusGame> {
	public CreditsScreen(VirusGame game) {
		super(game);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawString("Ludum Dare #23, by Ingon", 300, 300);
	}
	
	public void keyTyped(KeyEvent e) {
		System.exit(0);
	}

	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}
}
