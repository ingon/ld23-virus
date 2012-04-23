package org.virus;

import java.awt.Color;
import java.awt.Graphics2D;

import org.game.core.TimeContext;
import org.game.tx.TxValue;
import org.virus.proto.Levels;

public class SurvivalScreen extends PlayScreen {
	private long beginTime = -1;
	public final TxValue<Integer> passedTime;
	
	public SurvivalScreen(VirusGame game) {
		super(game, Levels.survival);
		
		passedTime = new TxValue<Integer>(0);
	}

	@Override
	public void update(TimeContext ctx) {
		if(beginTime == -1)
			beginTime = ctx.time;
		
		passedTime.set((int) (ctx.time - beginTime) / 1000);
		
		super.update(ctx);
	}
	
	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		
		g.setColor(Color.WHITE);
		g.drawString("T: " + passedTime.get(), 2, 12);
	}
	
	@Override
	protected void playerLooses(TimeContext ctx) {
		game.showMain("Survived for " + passedTime.get() + " seconds");
	}

	@Override
	protected void playerWins(TimeContext ctx) {
		game.showMain("You WON!");
	}
}
