package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.*;

/**
 * @author INNERPEACE
 * @date 2020/6/1 17:49
 */
public abstract class SocketUsingTask<T> {

}

interface CancellableTask<T> extends Callable<T> {
	void cancel();

	RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor {


	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableTask) {
			return ((CancellableTask<T>) callable).newTask();
		} else {
			return (RunnableFuture<T>) super.newTaskFor(callable);
		}
	}
}

