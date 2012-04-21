package org.virus.model;

import java.awt.Color;

public enum Colors {
	BLUE(new Color(32, 32, 255), new Color(32, 32, 255, 64));
	
	public final Color main;
	public final Color shadow;
	
	private Colors(Color main, Color shadow) {
		this.main = main;
		this.shadow = shadow;
	}
}
