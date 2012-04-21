package org.virus.model;

import java.awt.Graphics2D;
import java.awt.Image;

import org.game.core.GameObject;
import org.game.tx.TxPoint;
import org.virus.LevelScreen;

public abstract class ImageObject implements GameObject {
	public final LevelScreen screen;

	public final int width;
	public final int height;
	public final Image image;
	
	public final TxPoint position;
	
	public ImageObject(LevelScreen screen, int x, int y) {
		this.screen = screen;
		
		this.image = createImage();
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.position = new TxPoint(x, y);
	}
	
	protected abstract Image createImage();

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, position.ix() - screen.view.ix() - width / 2, position.iy() - screen.view.iy() - height / 2, null);
	}
}
