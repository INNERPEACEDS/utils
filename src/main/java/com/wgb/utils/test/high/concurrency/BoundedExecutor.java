package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 清单8.4 使用Semaphore来遏制任务的提交
 * @author INNERPEACE
 * @date 2020/6/28
 */
public class BoundedExecutor {
	private final ExecutorService exec;
	private final Semaphore semaphore;

	public BoundedExecutor(ExecutorService exec, int bound) {
		this.exec = exec;
		this.semaphore = new Semaphore(bound);
	}

	public void submitTask(final Runnable command) throws InterruptedException {
		semaphore.acquire();
		try {
			exec.submit(() -> {
				try {
					command.run();
				} finally {
					semaphore.release();
				}
			});
		} catch (RejectedExecutionException e) {
			semaphore.release();
		}
	}


}
