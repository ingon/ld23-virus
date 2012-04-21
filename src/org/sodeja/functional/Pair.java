package org.sodeja.functional;

public class Pair<First, Second> {
	
	public final First first;
	public final Second second;
	
	protected Pair(First first, Second second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public int hashCode() {
		if (first == null) return (second == null) ? 0 : second.hashCode() + 1;
		else if (second == null) return first.hashCode() + 2;
		else return first.hashCode() * 17 + second.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	   	return obj instanceof Pair && equals(first, ((Pair<?, ?>) obj).first) && equals(second, ((Pair<?, ?>) obj).second);
	}

    static boolean equals(Object x, Object y) {
		return (x == null && y == null) || (x != null && x.equals(y));
	}
	
	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
	
	public static <F, S> Pair<F, S> of(F first, S second) {
		return new Pair<F, S>(first, second);
	}
}
