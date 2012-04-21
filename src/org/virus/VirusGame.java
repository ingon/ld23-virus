package org.virus;

import org.game.basic.BasicGame;
import org.game.basic.BasicGameMain;
import org.game.basic.BasicGameScreen;
import org.virus.proto.LevelProto;
import org.virus.proto.Levels;

public class VirusGame extends BasicGame {
	private int currentLevel = -1;
	
	@Override
	protected BasicGameScreen<VirusGame> createInitialScreen() {
		return new MainScreen(this);
	}
	
	public void showNextLevel() {
		currentLevel++;
		if(currentLevel < Levels.LEVELS_COUNT) {
			LevelProto lp = Levels.levels[currentLevel];
			showScreen(new LevelScreen(this, lp));
		} else {
			showScreen(new CreditsScreen(this));
		}
	}
	
	public static void main(String[] args) {
		VirusGame game = new VirusGame();
		BasicGameMain.run(game, "LD23 - Virus");
	}
}
