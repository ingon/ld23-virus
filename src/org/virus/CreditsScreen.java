package org.virus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import org.game.basic.BasicGameScreen;

public class CreditsScreen extends BasicGameScreen<VirusGame> {
	private static final Font TEXT_FONT = new Font("Verdana", Font.PLAIN, 32);
	private static final String TEXT = "Ludum Dare #23 - Virus, by Ingon";
	
	public CreditsScreen(VirusGame game) {
		super(game);
	}

	@Override
	public void paint(Graphics2D g) {
		Rectangle2D textBounds = TEXT_FONT.getStringBounds(TEXT, g.getFontRenderContext());
		
		g.setColor(Color.WHITE);
		g.setFont(TEXT_FONT);
		g.drawString(TEXT, (int) ((800 - textBounds.getWidth()) / 2), 300);
	}
	
	public void keyTyped(KeyEvent e) {
		System.exit(0);
	}

	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}
}
