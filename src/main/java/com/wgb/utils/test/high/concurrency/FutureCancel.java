package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.*;

/**
 * 清单7.10 通过Future来取消任务
 * @author INNERPEACE
 * @date 2020/6/1 16:43
 */
public class FutureCancel {
	private static final ExecutorService executorService = Executors.newCachedThreadPool();

	public static void timedRun(final Runnable runnable, long timeOut, TimeUnit timeUnit) throws InterruptedException {
		Future<?> submit = executorService.submit(runnable);
		executorService.execute(runnable);
		try {
			submit.get(timeOut, timeUnit);
		} catch (InterruptedException e) {
			throw e;
		} catch (ExecutionException e) {
			throw launderThrowable(e.getCause());
		} catch (TimeoutException e) {
		} finally {
			submit.cancel(true);
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
