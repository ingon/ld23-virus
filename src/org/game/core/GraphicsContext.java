package org.game.core;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class GraphicsContext {
	private final Graphics2D screenGraphics;
	
	private BufferedImage buffer;
	private Graphics2D bufferGraphics;
	
	public GraphicsContext(Graphics2D screenGraphics) {
		this.screenGraphics = screenGraphics;
	}
	
	public void paintStart() {
		if(buffer == null) {
			buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
			bufferGraphics = (Graphics2D) buffer.getGraphics();
			
			bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		bufferGraphics.clearRect(0, 0, 800, 600);
	}
	
	public Graphics2D graphics() {
		return bufferGraphics;
	}
	
	public void paintEnd() {
		screenGraphics.drawImage(buffer, 0, 0, null);
	}
}
