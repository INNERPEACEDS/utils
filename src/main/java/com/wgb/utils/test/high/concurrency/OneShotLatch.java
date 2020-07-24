package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 清单14.14 二元闭锁使用AbstractQueuedSynchronizer
 * @author INNERPEACE
 * @date 2020/7/20
 */
public class OneShotLatch {

	private Sync sync = new Sync();

	public void signal() {
		sync.releaseShared(0);
	}

	public void await() throws InterruptedException {
		sync.acquireSharedInterruptibly(0);
	}

	private class Sync extends AbstractQueuedSynchronizer {
		@Override
		protected int tryAcquireShared(int arg) {
			// 如果锁打开成功（state == 1）,否则失败
			return (getState() == 1) ? 1 : -1;
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			setState(1);
			return true;
		}
	}
}
