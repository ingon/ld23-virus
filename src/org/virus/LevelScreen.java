package org.virus;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.game.basic.BasicGameScreen;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.virus.model.Background;
import org.virus.model.Player;
import org.virus.model.PlayerCursor;
import org.virus.model.Playground;
import org.virus.proto.LevelProto;

public class LevelScreen extends BasicGameScreen<VirusGame> {
	public final LevelProto proto;
	
	public final TxPoint view;

	public final Background background;
	public final Playground playground;
	public final Player player;
	public final PlayerCursor playerTarget;
	
	public LevelScreen(VirusGame game, LevelProto proto) {
		super(game);
		this.proto = proto;
		
		this.view = new TxPoint(0, 0);
		
		this.background = new Background();
		this.playground = new Playground(this, proto);
		this.player = new Player(this, proto.player);
		this.playerTarget = new PlayerCursor();
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
		playground.paint(g);
		player.paint(g);
		playerTarget.paint(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 87: // W
			player.impulse(0, -1);
			break;
		case 83: // S
			player.impulse(0, 1);
			break;
		case 65: // A
			player.impulse(-1, 0);
			break;
		case 68: // D
			player.impulse(1, 0);
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 87: // W
			player.impulse(0, 1);
			break;
		case 83: // S
			player.impulse(0, -1);
			break;
		case 65: // A
			player.impulse(1, 0);
			break;
		case 68: // D
			player.impulse(-1, 0);
			break;
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		playerTarget.move(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		playerTarget.move(e.getX(), e.getY());
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		playerTarget.move(e.getX(), e.getY());
	}
}
