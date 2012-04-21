package org.virus.proto;

public class LevelProto {
	public final int width;
	public final int height;
	
	public final PlayerProto player;
	
	public LevelProto() {
		width = 400;
		height = 300;
		
		player = new PlayerProto(200, 150);
	}
}
