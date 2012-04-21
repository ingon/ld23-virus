package org.virus;

import org.game.basic.BasicGame;
import org.game.basic.BasicGameMain;
import org.game.basic.BasicGameScreen;

public class VirusGame extends BasicGame {
	@Override
	protected BasicGameScreen<VirusGame> createInitialScreen() {
		return new MainScreen(this);
	}
	
	public static void main(String[] args) {
		VirusGame game = new VirusGame();
		BasicGameMain.run(game, "LD23 - Virus");
	}
}
