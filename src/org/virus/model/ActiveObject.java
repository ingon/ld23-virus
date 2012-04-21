package org.virus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.game.core.GameObject;
import org.game.core.TimeContext;
import org.game.tx.TxPoint;
import org.game.tx.TxSet;
import org.game.tx.TxValue;
import org.game.utils.MathUtils;
import org.virus.LevelScreen;
import org.virus.proto.ActiveProto;

public abstract class ActiveObject<P extends ActiveProto> implements GameObject {
	
	public static final int EXTERNAL_RECT_SIZE = 20;
	public static final int EXTERNAL_RECT_HSIZE = EXTERNAL_RECT_SIZE / 2;
	public static final int INTERNAL_RECT_DIFF = 4;
	public static final int INTERNAL_RECT_DDIFF = INTERNAL_RECT_DIFF * 2;
	public static final int INTERNAL_RECT_SIZE = EXTERNAL_RECT_SIZE - INTERNAL_RECT_DDIFF;
	public static final int CURVE = 5;

	public final LevelScreen screen;
	public final P proto;
	
	public final TxSet<Colors> colors;
	public final TxPoint position;
	public final TxSet<TxPoint> partials;
	
	public final TxPoint impulse;
	public final TxValue<Double> speed;

	public ActiveObject(LevelScreen screen, P proto) {
		this.screen = screen;
		this.proto = proto;
		
		colors = new TxSet<Colors>();
		for(Colors c : proto.colors) {
			colors.add(c);
		}
		position = new TxPoint(proto.x, proto.y);
		partials = new TxSet<TxPoint>();
		initPartials();
		
		impulse = new TxPoint(0, 0);
		speed = new TxValue<Double>(1.);
	}

	protected void initPartials() {
		partials.removeAll();
		switch (colors.size()) {
		case 1:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			break;
		case 2:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy()));
			break;
		case 3:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_SIZE, position.iy()));
			partials.add(new TxPoint(position.ix(), position.iy()));
			break;
		case 4:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_SIZE, position.iy()));
			partials.add(new TxPoint(position.ix(), position.iy()));
			partials.add(new TxPoint(position.ix(), position.iy() - EXTERNAL_RECT_SIZE));
			break;
		case 5:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));
			break;
		case 6:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));
			break;
		case 7:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));
			break;
		case 8:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() + EXTERNAL_RECT_HSIZE));
			
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));

			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));

			break;
		case 9:
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE, position.iy() + EXTERNAL_RECT_HSIZE));
			
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));

			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE - EXTERNAL_RECT_SIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE));
			partials.add(new TxPoint(position.ix() + EXTERNAL_RECT_HSIZE, position.iy() + EXTERNAL_RECT_HSIZE));

			break;
		default:
			throw new RuntimeException("Unknown size");
		}
	}
	
	@Override
	public void update(TimeContext ctx) {
		double dx = impulse.x() * speed.get();
		double dy = impulse.y() * speed.get();

		double px = MathUtils.bound(position.x() + dx, screen.proto.width);
		double py = MathUtils.bound(position.y() + dy, screen.proto.height);

		double ax = px - position.x();
		double ay = py - position.y();

		position.ax(ax);
		position.ay(ay);
		
		for(TxPoint p : partials) {
			p.ax(ax);
			p.ay(ay);
		}
	}

	@Override
	public void paint(Graphics2D g) {
		Iterator<Colors> ci = colors.iterator();
		Iterator<TxPoint> pi = partials.iterator();
		while(ci.hasNext() && pi.hasNext()) {
			Colors col = ci.next();
			TxPoint pt = pi.next();
			
			g.setColor(shadowColor());
			g.fillRoundRect(pt.ix() - screen.view.ix(), pt.iy() - screen.view.iy(), EXTERNAL_RECT_SIZE, EXTERNAL_RECT_SIZE, CURVE, CURVE);
			
			g.setColor(col.main);
			g.fillRoundRect(pt.ix() + INTERNAL_RECT_DIFF - screen.view.ix(), pt.iy() + INTERNAL_RECT_DIFF - screen.view.iy(), INTERNAL_RECT_SIZE, INTERNAL_RECT_SIZE, CURVE, CURVE);
		}
	}
	
	protected abstract Color shadowColor();
	
	public Rectangle roughBounds() {
		switch (colors.size()) {
		case 1:
			return new Rectangle(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_HSIZE, EXTERNAL_RECT_SIZE, EXTERNAL_RECT_SIZE);
		case 2:
			return new Rectangle(position.ix() - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_SIZE, EXTERNAL_RECT_SIZE, EXTERNAL_RECT_SIZE * 2);
		case 3:
		case 4:
			return new Rectangle(position.ix() - EXTERNAL_RECT_SIZE, position.iy() - EXTERNAL_RECT_SIZE, EXTERNAL_RECT_SIZE * 2, EXTERNAL_RECT_SIZE * 2);
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return new Rectangle(position.ix() - EXTERNAL_RECT_SIZE - EXTERNAL_RECT_HSIZE, position.iy() - EXTERNAL_RECT_SIZE - EXTERNAL_RECT_HSIZE, EXTERNAL_RECT_SIZE * 3, EXTERNAL_RECT_SIZE * 3);
		default:
			throw new RuntimeException("Unknown size");
		}
	}
	
	public List<Rectangle> preciseBounds() {
		List<Rectangle> bounds = new ArrayList<Rectangle>();
		for(TxPoint p : partials) {
			bounds.add(new Rectangle(p.ix(), p.iy(), EXTERNAL_RECT_SIZE, EXTERNAL_RECT_SIZE));
		}
		return bounds;
	}
}
