package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 清单5.11 在时序测试中，使用CountDownLatch来启动和停止线程
 * @author INNERPEACE
 * @date 2020/5/6 14:12
 */
@Slf4j
public class TestHarness {
	public long timeTask(int nThreads, final Runnable task) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread(){
				@Override
				public void run(){
					try {
						log.info("我在等待开始阀门");
						startGate.await();
						log.info("等待开始阀门结束");
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException e) {
//						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		long start = System.nanoTime();
		log.info("开始时间：{}", start);
		startGate.countDown();
		log.info("我在等待结束阀门");
		endGate.await();
		log.info("等待结束阀门结束");
		long end = System.nanoTime();
		return end - start;
	}

	public static void main(String[] args) throws InterruptedException {
		new TestHarness().timeTask(10, () -> {
			int a = 1;
			int b = 2;
			int c;
			c = a + b;
			log.info("线程：{}，c:{}", Thread.currentThread().getName(), c);
		});
	}
}