package org.virus.model;

import java.util.ArrayList;
import java.util.List;

import org.virus.PlayScreen;
import org.virus.proto.RunnableGeneratorProto;

public class RunnableGenerator extends Generator<RunnableGeneratorProto> {
	public final List<Runnable> callbacks;
	
	public RunnableGenerator(PlayScreen screen, RunnableGeneratorProto proto) {
		super(screen, proto);
		
		this.callbacks = new ArrayList<Runnable>(proto.callbacks);
	}

	@Override
	protected void executeNext() {
		Runnable r = callbacks.remove(0);
		r.run();
	}
}
