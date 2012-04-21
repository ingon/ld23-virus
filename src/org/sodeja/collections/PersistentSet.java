package org.sodeja.collections;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class PersistentSet<E> extends AbstractSet<E> {
	private final PersistentMap<E, Object> map;
	private static final Object INTERNAL_VALUE = new Object();
	
	public PersistentSet() {
		this.map = new PersistentMap<E, Object>();
	}
	
	private PersistentSet(PersistentMap<E, Object> map) {
		this.map = map;
	}
	
	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	public PersistentSet<E> addValue(E e) {
		return new PersistentSet<E>(map.putValue(e, INTERNAL_VALUE));
	}

	public PersistentSet<E> addAllValues(final Collection<E> values) {
		return new PersistentSet<E>(map.putAllKeys(values, INTERNAL_VALUE));
	}
	
	public PersistentSet<E> removeValue(E e) {
		return new PersistentSet<E>(map.removeValue(e));
	}
}
