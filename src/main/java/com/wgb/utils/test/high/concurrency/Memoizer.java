package com.wgb.utils.test.high.concurrency;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 清单5.19 Memoizer最终实现
 * @author INNERPEACE
 * @date 2020/5/7 11:56
 */
public class Memoizer<A, V> implements Computable<A, V>  {
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable<A, ? extends V> c;

	public Memoizer(Computable<A, ? extends V> c) {
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		Future<V> vFuture = cache.get(arg);
		if (vFuture == null) {
			Callable<V> callable = () -> c.compute(arg);
			FutureTask<V> futureTask = new FutureTask<V>(callable);
			vFuture = cache.putIfAbsent(arg, futureTask);
			if (vFuture == null) {
				vFuture = futureTask;
				futureTask.run();
			}
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
		} else if (t instanceof Error){
			throw (Error) t;
		} else {
			throw new IllegalStateException("Not unchecked", t);
		}
	}
}