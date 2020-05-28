package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.BlockingQueue;

/**
 * 清5.10 恢复中断状态，避免掩盖中断
 * @author INNERPEACE
 * @date 2020/5/6 13:47
 */
public class TaskRunnable implements Runnable{
	private BlockingQueue queue;

	TaskRunnable(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			queue.take();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
