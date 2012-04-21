package org.game.tx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TxManager {
	private static final TxManager INSTANCE = new TxManager();
	
	public static TxManager getInstance() {
		return INSTANCE;
	}
	
	private static final ThreadLocal<TxState> threadState = new ThreadLocal<TxState>();
	private final List<TxCell> cells = new ArrayList<TxCell>();
	
	private final ReentrantLock notificationLock = new ReentrantLock();
	private final ReentrantLock writeLock = new ReentrantLock();
	
	public void read(Runnable runner) {
		TxState state = threadState.get();
		if(state != null) {
			if(state == TxState.READ) {
				return;
			}
			if(state == TxState.WRITE) {
				throw new RuntimeException("Transaction already in writing state");
			}
		}
		
		threadState.set(TxState.READ);
		notify(TxEvent.READ_START);
		
		try {
			runner.run();
		} finally {
			threadState.set(null);
			notify(TxEvent.READ_END);
		}
	}
	
	public void write(Runnable runner) {
		TxState state = threadState.get();
		if(state != null) {
			if(state == TxState.READ) {
				throw new RuntimeException("Transaction already in reading state");
			}
			if(state == TxState.WRITE) {
				return;
			}
		}

		writeLock.lock();
		threadState.set(TxState.WRITE);
		notify(TxEvent.WRITE_START);
		
		try {
			runner.run();
			notify(TxEvent.WRITE_COMMIT);
		} catch(Throwable th) {
			notify(TxEvent.WRITE_ROLLBACK);
			throw new RuntimeException(th);
		} finally {
			threadState.set(null);
			writeLock.unlock();
		}
	}
	
	protected void newCell(TxCell cell) {
		notificationLock.lock();
		try {
			cells.add(cell);
		} finally {
			notificationLock.unlock();
		}
	}
	
	protected void notify(TxEvent event) {
		notificationLock.lock();
		try {
			for(TxCell cell : cells) {
				cell.notify(event);
			}
		} finally {
			notificationLock.unlock();
		}
	}

	protected boolean isTxReading() {
		TxState state = threadState.get();
		if(state == null) {
			throw new RuntimeException("No transaction available");
		}
		return state == TxState.READ;
	}
	
	protected boolean isTxWriting() {
		TxState state = threadState.get();
		if(state == null) {
			throw new RuntimeException("No transaction available");
		}
		return state == TxState.WRITE;
	}
}
