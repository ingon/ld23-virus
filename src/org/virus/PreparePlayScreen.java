package org.virus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import org.game.basic.BasicGameScreen;
import org.game.core.TimeContext;
import org.game.tx.TxValue;

public class PreparePlayScreen extends BasicGameScreen<VirusGame> {
	private static final Font TEXT_FONT = new Font("Verdana", Font.PLAIN, 28);
	
	public final LevelScreen screen;
	public final String text;
	public final long showTime;
	
	private TxValue<Integer> timeLeft;
	private long beginTime = -1;
	
	public PreparePlayScreen(VirusGame game, LevelScreen screen, String text, long showTime) {
		super(game);
		
		this.screen = screen;
		this.text = text;
		this.showTime = showTime;
		
		timeLeft = new TxValue<Integer>(((int) showTime / 1000));
	}
	
	@Override
	public void update(TimeContext ctx) {
		if(beginTime == -1) {
			beginTime = ctx.time;
		}
		
		long passedTime = ctx.time - beginTime;
		if(passedTime >= showTime) {
			game.showScreen(screen);
		} else {
			long leftTime = showTime - passedTime;
			int mainTime = (int) (leftTime / 1000);
			if(leftTime % 1000 > 0)
				mainTime++;
			timeLeft.set(mainTime);
		}
	}
	
	@Override
	public void paint(Graphics2D g) {
		String currentText = text + timeLeft.get() + " ...";
		Rectangle2D textBounds = TEXT_FONT.getStringBounds(currentText, g.getFontRenderContext());
		
		g.setColor(Color.WHITE);
		g.setFont(TEXT_FONT);
		g.drawString(currentText, (int) ((800 - textBounds.getWidth()) / 2), 300);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			game.showMain();
			break;
		}
	}
}
