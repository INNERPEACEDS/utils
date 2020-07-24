package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 清单14.9 使用wait和notify实现可重关闭的阀门
 * @author INNERPEACE
 * @date 2020/7/16
 */
@ThreadSafe
public class ThreadGate {
	@GuardedBy("this")
	private boolean isOpen;

	@GuardedBy("this")
	private int generation;

	public synchronized void close() {
		isOpen = false;
	}

	public synchronized void open() {
		++generation;
		isOpen = true;
		notifyAll();
	}

	public synchronized void await() throws InterruptedException {
		int arrivalGeneration = generation;
		while (!isOpen && arrivalGeneration == generation) {
			wait();
		}
	}
}
