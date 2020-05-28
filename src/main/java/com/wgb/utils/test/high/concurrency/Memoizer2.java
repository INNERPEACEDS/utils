package com.wgb.utils.test.high.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 清单5.17 用ConcurrentHashMap替换HashMap，会造成多个线程进行compute大量计算
 * @author INNERPEACE
 * @date 2020/5/7 11:03
 */
public class Memoizer2<A, V> implements Computable<A, V> {

	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	private final Computable<A, V> c;

	public Memoizer2(Computable<A, V> c) {
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if (result == null) {
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}
