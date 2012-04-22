package org.virus.proto;

import java.util.ArrayList;
import java.util.List;

public class RunnableGeneratorProto extends GeneratorProto {
	public final List<Runnable> callbacks;
	
	public RunnableGeneratorProto(Class<?> type) {
		super(type);
		
		callbacks = new ArrayList<Runnable>();
	}

	public void add(long time, Runnable callback) {
		times.add(time);
		callbacks.add(callback);
	}
}
