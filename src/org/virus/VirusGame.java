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
			LevelScreen levelScreen = new LevelScreen(this, lp);
			showScreen(new PreparePlayScreen(this, levelScreen, "Level " + (currentLevel + 1) + " will begin in ", 3000));
		} else {
			showScreen(new CreditsScreen(this));
		}
	}
	
	public void replayLevel() {
		LevelProto lp = Levels.levels[currentLevel];
		LevelScreen levelScreen = new LevelScreen(this, lp);
		showScreen(new PreparePlayScreen(this, levelScreen, "Died. Level " + (currentLevel + 1) + " will begin in ", 3000));
	}
	
	public static void main(String[] args) {
		VirusGame game = new VirusGame();
		BasicGameMain.run(game, "LD23 - Virus");
	}
}
