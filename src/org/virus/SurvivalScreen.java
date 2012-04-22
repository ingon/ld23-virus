package org.virus;

import org.game.core.TimeContext;
import org.virus.proto.Levels;

public class SurvivalScreen extends PlayScreen {
	private long beginTime = -1;
	
	public SurvivalScreen(VirusGame game) {
		super(game, Levels.survival);
	}

	@Override
	public void update(TimeContext ctx) {
		if(beginTime == -1)
			beginTime = ctx.time;
		
		super.update(ctx);
	}
	
	@Override
	protected void playerLooses(TimeContext ctx) {
		game.showMain();
	}

	@Override
	protected void playerWins(TimeContext ctx) {
		game.showMain();
	}
}
