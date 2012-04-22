package org.virus.proto;

import java.util.ArrayList;
import java.util.List;

import org.virus.model.Callback;

public class CallbackGeneratorProto extends GeneratorProto {
	public final List<Callback> callbacks;
	
	public CallbackGeneratorProto(Class<?> type) {
		super(type);
		
		callbacks = new ArrayList<Callback>();
	}

	public void add(long time, Callback callback) {
		times.add(time);
		callbacks.add(callback);
	}
}
