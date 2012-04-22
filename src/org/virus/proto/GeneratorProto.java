package org.virus.proto;

import java.util.ArrayList;
import java.util.List;

public class GeneratorProto {
	public final Class<?> type;
	public final List<Long> times;
	
	public GeneratorProto(Class<?> type) {
		this.type = type;
		times = new ArrayList<Long>();
	}
}
