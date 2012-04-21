package org.game.tx;

import java.awt.Point;

public class TxPoint {
	private final TxValue<Double> x;
	private final TxValue<Double> y;
	
	public TxPoint(double x, double y) {
		this.x = new TxValue<Double>(x);
		this.y = new TxValue<Double>(y);
	}

	public int ix() {
		return (int) x();
	}
	
	public double x() {
		return x.get();
	}
	
	public TxPoint ax(double x) {
		return this.x(this.x() + x);
	}
	
	public TxPoint x(double x) {
		this.x.set(x);
		return this;
	}
	
	public int iy() {
		return (int) y();
	}
	
	public double y() {
		return y.get();
	}
	
	public TxPoint ay(double y) {
		return this.y(this.y() + y);
	}
	
	public TxPoint y(double y) {
		this.y.set(y);
		return this;
	}

	public TxPoint xy(Point p) {
		this.x.set(p.getX());
		this.y.set(p.getY());
		return this;
	}
	
	public void copyTo(TxPoint other) {
		other.x(other.x());
		other.y(other.y());
	}
	
	public double distanceTo(TxPoint other) {
		double xd = x.get() - other.x.get();
		double yd = y.get() - other.y.get();
		return Math.sqrt(xd * xd + yd * yd);
	}
	
	@Override
	public String toString() {
		return "[" + x() + "," + y() + "]";
	}
}
