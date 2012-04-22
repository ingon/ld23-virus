package org.virus;

import java.awt.Font;

import org.game.basic.BasicGameScreen;

public class PreparePlayScreen extends BasicGameScreen<VirusGame> {
	private static final Font TEXT_FONT = new Font("Verdana", Font.PLAIN, 28);
	
	public PreparePlayScreen(VirusGame game) {
		super(game);
	}
}
