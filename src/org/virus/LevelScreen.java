package org.virus;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import org.game.basic.BasicGameScreen;
import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.tx.TxSet;
import org.virus.model.Background;
import org.virus.model.Enemy;
import org.virus.model.FollowEnemy;
import org.virus.model.Player;
import org.virus.model.PlayerCursor;
import org.virus.model.Playground;
import org.virus.model.Projectile;
import org.virus.model.RandomEnemy;
import org.virus.proto.EnemyProto;
import org.virus.proto.FollowEnemyProto;
import org.virus.proto.LevelProto;
import org.virus.proto.RandomEnemyProto;

public class LevelScreen extends BasicGameScreen<VirusGame> {
	public final LevelProto proto;
	
	public final TxPoint view;

	public final Background background;
	public final Playground playground;
	
	public final Player player;
	public final PlayerCursor playerTarget;
	public final TxSet<Projectile> playerFire;
	
	public final TxSet<Enemy<?>> enemies;
	
	public LevelScreen(VirusGame game, LevelProto proto) {
		super(game);
		this.proto = proto;
		
		this.view = new TxPoint(0, 0);
		
		this.background = new Background();
		this.playground = new Playground(this, proto);
		
		this.player = new Player(this, proto.player);
		this.playerTarget = new PlayerCursor();
		this.playerFire = new TxSet<Projectile>();
		
		this.enemies = new TxSet<Enemy<?>>();
		for(EnemyProto ep : proto.enemies) {
			this.enemies.add(createEnemy(ep));
		}
	}
	
	private Enemy<?> createEnemy(EnemyProto proto) {
		if(RandomEnemy.class.equals(proto.type)) {
			return new RandomEnemy(this, (RandomEnemyProto) proto);
		} else if(FollowEnemy.class.equals(proto.type)) {
			return new FollowEnemy(this, (FollowEnemyProto) proto);
		} else {
			throw new RuntimeException("Unknown type");
		}
	}
	
	public void updateView(TxPoint position) {
		Point p = new Point(position.ix() - 400, position.iy() - 300);
		view.xy(p);
	}
	
	@Override
	public void update(TimeContext ctx) {
		background.update(ctx);
		playground.update(ctx);
		player.update(ctx);
		
		update(ctx, playerFire);
		update(ctx, enemies);
		
		OUTER:
		for(Enemy<?> e : enemies) {
			Rectangle eb = e.roughBounds();
			List<Rectangle> epb = null;
			for(Projectile p : playerFire) {
				Rectangle pp = p.bounds.get();
				if(pp.intersects(eb)) {
					if(epb == null)
						epb = e.preciseBounds();
					
					if(intersects(pp, epb)) {
						if(e.colorHit(p.color)) {
							if(e.colors.isEmpty())
								enemies.remove(e);
						}
						playerFire.remove(p);
						continue OUTER;
					}
				}
			}
		}
		
		if(enemies.isEmpty()) {
			game.showNextLevel();
		}
	}
	
	protected static boolean intersects(Rectangle targetRect, List<Rectangle> rects) {
		for(Rectangle rect : rects) {
			if(targetRect.intersects(rect)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void paint(Graphics2D g) {
		background.paint(g);
		playground.paint(g);
		
		paint(g, playerFire);
		player.paint(g);
		
		paint(g, enemies);
		
		playerTarget.paint(g);
	}
	
	public int toAbsX(int x) {
		return x + view.ix();
	}
	
	public int toAbsY(int y) {
		return y + view.iy();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("KP: " + "w");
			player.impulse(0, -1);
			break;
		case KeyEvent.VK_S:
			System.out.println("KP: " + "s");
			player.impulse(0, 1);
			break;
		case KeyEvent.VK_A:
			System.out.println("KP: " + "a");
			player.impulse(-1, 0);
			break;
		case KeyEvent.VK_D:
			System.out.println("KP: " + "d");
			player.impulse(1, 0);
			break;
		case KeyEvent.VK_SPACE:
			player.changeColor();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("KR: " + "w");
			player.impulse(0, 1);
			break;
		case KeyEvent.VK_S:
			System.out.println("KR: " + "s");
			player.impulse(0, -1);
			break;
		case KeyEvent.VK_A:
			System.out.println("KR: " + "a");
			player.impulse(1, 0);
			break;
		case KeyEvent.VK_D:
			System.out.println("KR: " + "d");
			player.impulse(-1, 0);
			break;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point position = e.getPoint();
		switch(e.getButton()) {
		case MouseEvent.BUTTON1:
			player.fire(position.x, position.y);
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
	
	private static void update(TimeContext ctx, TxSet<? extends GameObject> vals) {
		for(GameObject go : vals) {
			go.update(ctx);
		}
	}
	
	private static void paint(Graphics2D g, TxSet<? extends GameObject> vals) {
		for(GameObject go : vals) {
			go.paint(g);
		}
	}
}
