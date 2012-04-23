package org.virus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import org.game.basic.BasicGameScreen;
import org.virus.model.PlayerCursor;

public class MainScreen extends BasicGameScreen<VirusGame> {
	private static final String GAME_TITLE = "Virus Wars (LD23 by Ingon)";
	
	private static final String[] BUTTONS = new String[] {"Survival", "Tutorial", "Exit"};
	
	private Font titleFont;
	private Point titlePosition;
	
	private Font buttonFont;
	private Rectangle[] buttons = new Rectangle[BUTTONS.length];
	
	private Font textFont;
	private final String text;
	private Point textPosition;
	
	public final PlayerCursor playerTarget;

	public MainScreen(VirusGame game) {
		this(game, null);
	}
	
	public MainScreen(VirusGame game, String text) {
		super(game);
		this.text = text;
		
		this.playerTarget = new PlayerCursor();
	}

	@Override
	public void paint(Graphics2D g) {
		if(titleFont == null) {
			titleFont = new Font("Verdana", Font.PLAIN, 32);
			
			Rectangle titleRect = titleFont.getStringBounds(GAME_TITLE, g.getFontRenderContext()).getBounds();
			titlePosition = new Point((800 - titleRect.width) / 2, (600 - titleRect.height) / 2);
			
			textFont = new Font("Verdana", Font.PLAIN, 32);
			if(text != null) {
				Rectangle textRect = textFont.getStringBounds(text, g.getFontRenderContext()).getBounds();
				textPosition = new Point((800 - textRect.width) / 2, (600 - textRect.height) / 2 - 100);
			}
			
			buttonFont = new Font("Verdana", Font.PLAIN, 28);

			int maxWidth = -1;
			for(int i = 0; i < BUTTONS.length; i++) {
				buttons[i] = buttonFont.getStringBounds(BUTTONS[i], g.getFontRenderContext()).getBounds();
				buttons[i].width += 20;
				if(buttons[i].width > maxWidth) {
					maxWidth = buttons[i].width;
				}
			}

			int initialY = titlePosition.y + titleRect.height + 20;
			for(int i = 0; i < buttons.length; i++) {
				buttons[i].width = maxWidth;
				buttons[i].setLocation((800 - buttons[i].width) / 2, initialY);
				
				initialY = buttons[i].y + buttons[i].height + 20;
			}
		}
		
		Font originalFont = g.getFont();
		
		if(text != null) {
			g.setColor(Color.WHITE);
			g.setFont(textFont);
			g.drawString(text, textPosition.x, textPosition.y);
		}
		
		g.setColor(new Color(255, 32, 32));
		g.setFont(titleFont);
		g.drawString(GAME_TITLE, titlePosition.x, titlePosition.y);
		
		g.setFont(buttonFont);
		
		for(int i = 0; i < buttons.length; i++) {
			g.setColor(new Color(255, 32, 32));
			g.fillRoundRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height, 8, 8);
			
			g.setColor(new Color(32, 32, 255));
			g.drawString(BUTTONS[i], buttons[i].x + 10, buttons[i].y + buttons[i].height - 8);
		}
		
		g.setFont(originalFont);
		
		playerTarget.paint(g);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Point p = e.getPoint();
		switch (findButton(p)) {
		case 0:
			game.startSurvival();
			break;
		case 1:
			game.showFirstLevel();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private int findButton(Point p) {
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i].contains(p)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		playerTarget.move(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		playerTarget.move(e.getX(), e.getY());
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		playerTarget.move(e.getX(), e.getY());
	}
}
