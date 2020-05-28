package com.wgb.utils.test.high.concurrency;

import java.util.HashMap;
import java.util.Map;

/**
 *  清单5.16 尝试使用HashMap和同步来初始化缓存
 * @author INNERPEACE
 * @date 2020/5/7 10:43
 */
public class Memoizer1<A, V> implements Computable<A, V> {
	private final Map<A, V> cache = new HashMap<A, V>();
	private final Computable<A, V> c;

	public Memoizer1(Computable<A, V> c) {
		this.c = c;
	}
	
	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if (result == null) {
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}
