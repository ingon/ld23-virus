package org.game.tx;

import java.util.Iterator;

import org.sodeja.collections.PersistentSet;

public class TxSet<T> implements TxCell, Iterable<T> {
	private PersistentSet<T> read;
	private PersistentSet<T> readFuture;
	private PersistentSet<T> write;
	
	public TxSet() {
		this(new PersistentSet<T>());
	}
	
	public TxSet(PersistentSet<T> value) {
		TxManager.getInstance().newCell(this);
		
		this.write = value;
	}

	@Override
	public Iterator<T> iterator() {
		return TxManager.getInstance().isTxReading() ? read.iterator() : write.iterator();
	}
	
	public void add(T t) {
		if(TxManager.getInstance().isTxReading()) 
			throw new RuntimeException("No modifications in read mode");
		
		write = write.addValue(t);
	}
	
	public void remove(T t) {
		if(TxManager.getInstance().isTxReading()) 
			throw new RuntimeException("No modifications in read mode");
		
		write = write.removeValue(t);
	}

	public boolean isEmpty() {
		return TxManager.getInstance().isTxReading() ? read.isEmpty() : write.isEmpty();
	}
	
	@Override
	public void notify(TxEvent notification) {
		if(notification == TxEvent.READ_START) {
			read = readFuture;
		} else if(notification == TxEvent.READ_END) {
			read = readFuture;
		} else if(notification == TxEvent.WRITE_START) {
			write = readFuture;
		} else if(notification == TxEvent.WRITE_COMMIT) {
			readFuture = write;
		} else if(notification == TxEvent.WRITE_ROLLBACK) {
			readFuture = read;
		}
	}
}
