package org.game.tx;

enum TxEvent {
	READ_START,
	READ_END,
	WRITE_START,
	WRITE_COMMIT,
	WRITE_ROLLBACK;
}
