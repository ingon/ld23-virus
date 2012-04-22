package org.virus;

import org.game.core.TimeContext;
import org.virus.proto.LevelProto;

public class LevelScreen extends PlayScreen {
	public LevelScreen(VirusGame game, LevelProto proto) {
		super(game, proto);
	}
	
	@Override
	protected void playerWins(TimeContext ctx) {
		game.showNextLevel();
	}
	
	@Override
	protected void playerLooses(TimeContext ctx) {
		game.replayLevel();
	}
}
