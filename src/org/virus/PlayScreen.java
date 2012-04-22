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
import org.virus.model.CallbackGenerator;
import org.virus.model.Direction;
import org.virus.model.Enemy;
import org.virus.model.FixedGenerator;
import org.virus.model.Generator;
import org.virus.model.Player;
import org.virus.model.PlayerCursor;
import org.virus.model.Playground;
import org.virus.model.Projectile;
import org.virus.proto.CallbackGeneratorProto;
import org.virus.proto.EnemyProto;
import org.virus.proto.FixedGeneratorProto;
import org.virus.proto.GeneratorProto;
import org.virus.proto.LevelProto;

public abstract class PlayScreen extends BasicGameScreen<VirusGame> {
	public final LevelProto proto;
	
	public final TxPoint view;
	
	public final Background background;
	public final Playground playground;
	
	public final Player player;
	public final PlayerCursor playerTarget;
	public final TxSet<Projectile> playerFire;

	public final TxSet<Enemy> enemies;
	public final TxSet<Generator<?>> enemyGenerators;

	public PlayScreen(VirusGame game, LevelProto proto) {
		super(game);
		
		this.proto = proto;
		
		this.view = new TxPoint((proto.width - 800) / 2, (proto.height - 600) / 2);
		
		this.background = new Background(this);
		this.playground = new Playground(this, proto);
		
		this.player = new Player(this, proto.player);
		this.playerTarget = new PlayerCursor();
		this.playerFire = new TxSet<Projectile>();
		
		this.enemies = new TxSet<Enemy>();
		for(EnemyProto ep : proto.enemies) {
			this.enemies.add(createEnemy(ep));
		}
		
		this.enemyGenerators = new TxSet<Generator<?>>();
		for(GeneratorProto egp : proto.generators) {
			this.enemyGenerators.add(createEnemyGenerator(egp));
		}
	}

	private Enemy createEnemy(EnemyProto proto) {
		return new Enemy(this, proto);
	}
	
	private Generator<?> createEnemyGenerator(GeneratorProto proto) {
		if(FixedGenerator.class.equals(proto.type)) {
			return new FixedGenerator(this, (FixedGeneratorProto) proto);
		} else if(CallbackGenerator.class.equals(proto.type)) {
			return new CallbackGenerator(this, (CallbackGeneratorProto) proto);
		} else {
			throw new RuntimeException("Unknown type");
		}
	}
	
	public void spawn(EnemyProto proto) {
		Enemy enemy = createEnemy(proto);
		this.enemies.add(enemy);
	}
	
	public void updateView(TxPoint position) {
//		Point p = new Point(position.ix() - 400, position.iy() - 300);
//		view.xy(p);
	}

	@Override
	public void update(TimeContext ctx) {
		background.update(ctx);
		playground.update(ctx);
		player.update(ctx);
		
		update(ctx, playerFire);
		update(ctx, enemies);
		update(ctx, enemyGenerators);
		
		Rectangle pb = player.roughBounds();
		List<Rectangle> ppb = null;
		
		OUTER:
		for(Enemy e : enemies) {
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
			
			if(eb.intersects(pb)) {
				if(epb == null)
					epb = e.preciseBounds();
				if(ppb == null)
					ppb = player.preciseBounds();
				
				if(intersects(epb, ppb)) {
					player.die();
					break OUTER;
				}
			}
		}
		
		if(player.dead()) {
			playerLooses(ctx);
		} else if(enemies.isEmpty() && enemyGenerators.isEmpty()) {
			playerWins(ctx);
		}
	}
	
	protected abstract void playerLooses(TimeContext ctx);
	protected abstract void playerWins(TimeContext ctx);

	protected static boolean intersects(List<Rectangle> targetRects, List<Rectangle> rects) {
		for(Rectangle rect : targetRects) {
			if(intersects(rect, rects)) {
				return true;
			}
		}
		return false;
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
			player.addMove(Direction.NORTH);
			break;
		case KeyEvent.VK_S:
			player.addMove(Direction.SOUTH);
			break;
		case KeyEvent.VK_A:
			player.addMove(Direction.WEST);
			break;
		case KeyEvent.VK_D:
			player.addMove(Direction.EAST);
			break;
		case KeyEvent.VK_SPACE:
			player.changeColor();
			break;
		case KeyEvent.VK_ESCAPE:
			game.showMain();
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.removeMove(Direction.NORTH);
			break;
		case KeyEvent.VK_S:
			player.removeMove(Direction.SOUTH);
			break;
		case KeyEvent.VK_A:
			player.removeMove(Direction.WEST);
			break;
		case KeyEvent.VK_D:
			player.removeMove(Direction.EAST);
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
