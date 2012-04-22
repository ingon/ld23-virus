package org.virus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.virus.PlayScreen;
import org.virus.proto.LevelProto;

public class Playground implements GameObject {
	public final PlayScreen screen;
	public final BufferedImage image;
	
	public final int width;
	public final int height;
	
	public Playground(PlayScreen screen, LevelProto lp) {
		this.screen = screen;
		
		this.width = lp.width;
		this.height = lp.height;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(new Color(0xF0F0F0));
		g.fillRect(0, 0, width, height);
	}

	@Override
	public void update(TimeContext ctx) {
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, -screen.view.ix(), -screen.view.iy(), null);
	}
}
