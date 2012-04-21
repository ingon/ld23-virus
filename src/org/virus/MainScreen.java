package org.virus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import org.game.basic.BasicGameScreen;

public class MainScreen extends BasicGameScreen<VirusGame> {
	private static final String GAME_TITLE = "Virus";
	private static final String GAME_START = "Start";
	private static final String GAME_EXIT = "Exit";
	
	private Font titleFont;
	private Point titlePosition;
	
	private Font buttonFont;
	private Rectangle startButtonRect;
	private Rectangle exitButtonRect;

	public MainScreen(VirusGame game) {
		super(game);
	}
	
	@Override
	public void paint(Graphics2D g) {
		if(titleFont == null) {
			titleFont = new Font("Verdana", Font.PLAIN, 32);
			
			Rectangle titleRect = titleFont.getStringBounds(GAME_TITLE, g.getFontRenderContext()).getBounds();
			titlePosition = new Point((800 - titleRect.width) / 2, (600 - titleRect.height) / 2);
			
			buttonFont = new Font("Verdana", Font.PLAIN, 28);
			
			startButtonRect = buttonFont.getStringBounds(GAME_START, g.getFontRenderContext()).getBounds();
			startButtonRect.setLocation((800 - startButtonRect.width) / 2, titlePosition.y + titleRect.height + 20);
			startButtonRect.x -= 10;
			startButtonRect.width += 20;
			
			exitButtonRect = buttonFont.getStringBounds(GAME_EXIT, g.getFontRenderContext()).getBounds();
			exitButtonRect.setLocation((800 - exitButtonRect.width) / 2, startButtonRect.y + startButtonRect.height + 20);
			exitButtonRect.x -= 10;
			exitButtonRect.width += 20;
			
			if(startButtonRect.getWidth() > exitButtonRect.getWidth()) {
				exitButtonRect.x = startButtonRect.x;
				exitButtonRect.width = startButtonRect.width;
			} else {
				startButtonRect.x = exitButtonRect.x;
				startButtonRect.width = exitButtonRect.width;
			}
		}
		
		Font originalFont = g.getFont();
		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.drawString(GAME_TITLE, titlePosition.x, titlePosition.y);
		
		g.setFont(buttonFont);
		g.fillRoundRect(startButtonRect.x, startButtonRect.y, startButtonRect.width, startButtonRect.height, 8, 8);
		g.fillRoundRect(exitButtonRect.x, exitButtonRect.y, exitButtonRect.width, exitButtonRect.height, 8, 8);
		
		g.setColor(Color.BLUE);
		g.drawString(GAME_START, startButtonRect.x + 10, startButtonRect.y + startButtonRect.height - 8);
		g.drawString(GAME_EXIT, exitButtonRect.x + 10, exitButtonRect.y + exitButtonRect.height - 8);
		
		g.setFont(originalFont);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Point p = e.getPoint();
		System.out.println("Click: " + p);
		if(startButtonRect.contains(p)) {
			game.showScreen(new PlayScreen(game));
		} else if(exitButtonRect.contains(p)) {
			System.exit(0);
		}
	}
}
