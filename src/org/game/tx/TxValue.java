package org.game.tx;

public class TxValue<T> implements TxCell {
	private T read;
	private T readFuture;
	private T write;

	public TxValue(T value) {
		TxManager.getInstance().newCell(this);
		
		this.write = value;
	}

	public T get() {
		return TxManager.getInstance().isTxWriting() ? write : read; 
	}
	
	public void set(T value) {
		if(TxManager.getInstance().isTxReading()) {
			throw new RuntimeException("Writes are forbidden with in read mode.");
		}
		write = value;
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
