package org.virus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.virus.LevelScreen;
import org.virus.proto.LevelProto;

public class Playground implements GameObject {
	public final LevelScreen screen;
	public final BufferedImage image;
	
	public Playground(LevelScreen screen, LevelProto lp) {
		this.screen = screen;
		
		image = new BufferedImage(lp.width, lp.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(new Color(0xF0F0F0));
		g.fillRect(0, 0, lp.width, lp.height);
	}

	@Override
	public void update(TimeContext ctx) {
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, -screen.view.ix(), -screen.view.iy(), null);
	}
}
