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
	
	public static class ActiveSizes {
		public final int extRect;
		public final int extRectHalf;
		public final int intRect;
		public final int intRectDiff;
		public final int curve;
		
		public ActiveSizes(int extRect, int intRectDiff, int curve) {
			this.extRect = extRect;
			this.extRectHalf = extRect / 2;
			this.intRectDiff = intRectDiff;
			this.intRect = extRect - intRectDiff * 2;
			this.curve = curve;
		}
	}
	
	public static final ActiveSizes SMALL_COUNT = new ActiveSizes(20, 4, 5);
	public static final ActiveSizes MID_COUNT = new ActiveSizes(16, 2, 5);
	public static final ActiveSizes BIG_COUNT = new ActiveSizes(12, 2, 5);

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
			partials.add(new TxPoint(position.ix() - SMALL_COUNT.extRectHalf, position.iy() - SMALL_COUNT.extRectHalf));
			break;
		case 2:
			partials.add(new TxPoint(position.ix() - MID_COUNT.extRectHalf, position.iy() - MID_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - MID_COUNT.extRectHalf, position.iy()));
			break;
		case 3:
			partials.add(new TxPoint(position.ix() - MID_COUNT.extRectHalf, position.iy() - MID_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - MID_COUNT.extRect, position.iy()));
			partials.add(new TxPoint(position.ix(), position.iy()));
			break;
		case 4:
			partials.add(new TxPoint(position.ix() - MID_COUNT.extRect, position.iy() - MID_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - MID_COUNT.extRect, position.iy()));
			partials.add(new TxPoint(position.ix(), position.iy()));
			partials.add(new TxPoint(position.ix(), position.iy() - MID_COUNT.extRect));
			break;
		case 5:
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));
			break;
		case 6:
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));
			break;
		case 7:
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));
			break;
		case 8:
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() + BIG_COUNT.extRectHalf));
			
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));

			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));

			break;
		case 9:
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect, position.iy() + BIG_COUNT.extRectHalf));
			
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() - BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));

			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf - BIG_COUNT.extRect));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRectHalf));
			partials.add(new TxPoint(position.ix() + BIG_COUNT.extRectHalf, position.iy() + BIG_COUNT.extRectHalf));

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
		ActiveSizes asz = getActiveSizes();
		Iterator<Colors> ci = colors.iterator();
		Iterator<TxPoint> pi = partials.iterator();
		while(ci.hasNext() && pi.hasNext()) {
			Colors col = ci.next();
			TxPoint pt = pi.next();
			
			g.setColor(shadowColor());
			g.fillRoundRect(pt.ix() - screen.view.ix(), pt.iy() - screen.view.iy(), asz.extRect, asz.extRect, asz.curve, asz.curve);
			
			g.setColor(col.main);
			g.fillRoundRect(pt.ix() + asz.intRectDiff - screen.view.ix(), pt.iy() + asz.intRectDiff - screen.view.iy(), asz.intRect, asz.intRect, asz.curve, asz.curve);
		}
	}
	
	protected abstract Color shadowColor();
	
	public Rectangle roughBounds() {
		switch (colors.size()) {
		case 1:
			return new Rectangle(position.ix() - SMALL_COUNT.extRectHalf, position.iy() - SMALL_COUNT.extRectHalf, SMALL_COUNT.extRect, SMALL_COUNT.extRect);
		case 2:
			return new Rectangle(position.ix() - MID_COUNT.extRectHalf, position.iy() - MID_COUNT.extRect, MID_COUNT.extRect, MID_COUNT.extRect * 2);
		case 3:
		case 4:
			return new Rectangle(position.ix() - MID_COUNT.extRect, position.iy() - MID_COUNT.extRect, MID_COUNT.extRect * 2, MID_COUNT.extRect * 2);
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return new Rectangle(position.ix() - BIG_COUNT.extRect - BIG_COUNT.extRectHalf, position.iy() - BIG_COUNT.extRect - BIG_COUNT.extRectHalf, BIG_COUNT.extRect * 3, BIG_COUNT.extRect * 3);
		default:
			throw new RuntimeException("Unknown size");
		}
	}
	
	private ActiveSizes getActiveSizes() {
		switch (colors.size()) {
		case 1:
			return SMALL_COUNT;
		case 2: case 3: case 4:
			return MID_COUNT;
		case 5: case 6: case 7: case 8:
			return BIG_COUNT;
		default:
			throw new RuntimeException("Unknown size");
		}
	}
	
	public List<Rectangle> preciseBounds() {
		ActiveSizes asz = getActiveSizes();
		List<Rectangle> bounds = new ArrayList<Rectangle>();
		for(TxPoint p : partials) {
			bounds.add(new Rectangle(p.ix(), p.iy(), asz.extRect, asz.extRect));
		}
		return bounds;
	}
}
