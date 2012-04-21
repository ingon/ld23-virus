package org.sodeja.collections;

public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static int compare(int[] a, int[] b) {
    	int cmpLen = a.length > b.length ? b.length : a.length;
    	for(int i = 0; i < cmpLen; i++) {
    		if(a[i] == b[i]) {
    			continue;
    		}
    		return a[i] - b[i];
    	}
    	return a.length - b.length;
    }
}
