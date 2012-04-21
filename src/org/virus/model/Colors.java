package org.virus.model;

import java.awt.Color;

public enum Colors {
	BLUE(new Color(32, 32, 255), new Color(32, 32, 255, 160)),
	RED(new Color(255, 32, 32), new Color(255, 32, 32, 160)),
	GREEN(new Color(32, 255, 32), new Color(32, 255, 32, 160)),
	MAGENTA(new Color(255, 32, 255), new Color(255, 32, 255, 160)),
	YELLOW(new Color(255, 255, 32), new Color(255, 255, 32, 160)),
	CYAN(new Color(32, 255, 255), new Color(32, 255, 255, 160)),
	ORANGE(new Color(255, 128, 32), new Color(255, 128, 32, 160)),
	DARK_BLUE(new Color(64, 0, 128), new Color(64, 0, 128, 160)),
	GRAY(new Color(64, 64, 64), new Color(64, 64, 64, 160)),
	;
	
	public static final Color RANDOM_SHADOW = new Color(128, 255, 128, 128);
	public static final Color FOLLOW_SHADOW = new Color(128, 128, 255, 128);
	public static final Color HIDE_SHADOW = new Color(255, 128, 128, 128);
	
	public final Color main;
	public final Color shadow;
	
	private Colors(Color main, Color shadow) {
		this.main = main;
		this.shadow = shadow;
	}
}
