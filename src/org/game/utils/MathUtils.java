package org.game.utils;

public class MathUtils {
	public static double distance(int x1, int y1, int x2, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public static int bound(int val, int max) {
		return bound(val, 0, max);
	}
	
	public static int bound(int val, int min, int max) {
		if(val < min) val = min;
		if(val > max) val = max;
		return val;
	}
	
	public static double bound(double val, int max) {
		return bound(val, 0, max);
	}
	
	public static double bound(double val, int min, int max) {
		if(val < min) val = min;
		if(val > max) val = max;
		return val;
	}

	public static boolean inBound(int val, int max) {
		return inBound(val, 0, max);
	}
	
	public static boolean inBound(int val, int min, int max) {
		return ! (val < min || val > max);
	}

	public static boolean inBound(double val, int max) {
		return inBound(val, 0, max);
	}
	
	public static boolean inBound(double val, int min, int max) {
		return ! (val < min || val > max);
	}
}
