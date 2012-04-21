package org.game.utils;

public class MathUtils {
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