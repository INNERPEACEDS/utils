package com.wgb.utils.test.high.concurrency;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 清单7.21 关闭之后，ExecutorService获取被取消的任务
 * @author INNERPEACE
 * @date 2020/6/5
 */
public class TrackingExecutor extends AbstractExecutorService {
	private final ExecutorService exec;
	private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

	public TrackingExecutor(ExecutorService executorService) {
		this.exec = executorService;
	}

	public List<Runnable> getCancelledTasks() {
		if (!exec.isTerminated()) {
			throw new IllegalStateException("程序未中断，不能获取中断线程");
		}
		return new ArrayList<Runnable> (tasksCancelledAtShutdown);
	}

	@Override
	public void execute(final Runnable runnable) {
		exec.execute(() -> {
			try {
				runnable.run();
			} finally {
				if (isShutdown() && Thread.currentThread().isInterrupted()) {
					tasksCancelledAtShutdown.add(runnable);
				}
			}
		});
	}

	@Override
	public void shutdown() {
		exec.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return exec.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return exec.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return exec.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return exec.awaitTermination(timeout, unit);
	}
}

