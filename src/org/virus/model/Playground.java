package org.virus.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.utils.ImageUtils;
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
		
		Image backgroundImage = ImageUtils.load("img/background.png");
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(backgroundImage, 0, 0, null);
	}

	@Override
	public void update(TimeContext ctx) {
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, -screen.view.ix(), -screen.view.iy(), null);
	}
}
