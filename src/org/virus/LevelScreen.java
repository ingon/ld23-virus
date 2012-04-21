package org.virus;

import java.awt.Graphics2D;
import java.awt.Point;

import org.game.basic.BasicGameScreen;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.virus.model.Background;
import org.virus.model.Player;
import org.virus.proto.LevelProto;

public class LevelScreen extends BasicGameScreen<VirusGame> {
	public final LevelProto proto;
	
	public final TxPoint view;

	public final Background background;
	public final Player player;
	
	public LevelScreen(VirusGame game, LevelProto proto) {
		super(game);
		this.proto = proto;
		
		this.view = new TxPoint(0, 0);
		
		this.background = new Background(this, proto);
		this.player = new Player(this, proto.player);
	}
	
	public void updateView(TxPoint position) {
		Point p = new Point(position.ix() - 400, position.iy() - 300);
		view.xy(p);
	}
	
	@Override
	public void update(TimeContext ctx) {
		player.update(ctx);
		background.update(ctx);
	}
	
	@Override
	public void paint(Graphics2D g) {
		background.paint(g);
		player.paint(g);
	}
}
