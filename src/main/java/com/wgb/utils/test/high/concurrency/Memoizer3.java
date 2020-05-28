package com.wgb.utils.test.high.concurrency;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author INNERPEACE
 * @date 2020/5/7 11:19
 */
public class Memoizer3<A, V> implements Computable<A, V> {
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable c;

	public Memoizer3(Computable<A, V> c) {
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		Future<V> vFuture = cache.get(arg);
		if (vFuture == null) {
			Callable<V> callable = () -> (V) c.compute(arg);
			FutureTask futureTask = new FutureTask<V>(callable);
			vFuture = futureTask;
			cache.put(arg, futureTask);
			futureTask.run();
		}
		try {
			return vFuture.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw launderThrowable(e.getCause());
		}
	}

	public static RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		} else if (t instanceof java.lang.Error){
			throw (Error) t;
		} else {
			throw new IllegalStateException("Not unchecked", t);
		}
	}
}
