package org.virus.model;

import java.util.ArrayList;
import java.util.List;

import org.virus.PlayScreen;
import org.virus.proto.CallbackGeneratorProto;

public class CallbackGenerator extends Generator<CallbackGeneratorProto> {
	public final List<Callback> callbacks;
	
	public CallbackGenerator(PlayScreen screen, CallbackGeneratorProto proto) {
		super(screen, proto);
		
		this.callbacks = new ArrayList<Callback>(proto.callbacks);
	}

	@Override
	protected void executeNext() {
		Callback r = callbacks.remove(0);
		r.execute(screen);
	}
}
