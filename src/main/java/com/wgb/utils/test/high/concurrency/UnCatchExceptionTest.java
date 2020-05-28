package com.wgb.utils.test.high.concurrency;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 清单7.8 在外部线程中安排中断（不要这样做,1.调用线程可能在中断线程中）
 * @author INNERPEACE
 * @date 2020/5/22 9:27
 */
@Slf4j
public class UnCatchExceptionTest {
	private static final ScheduledExecutorService se = Executors.newScheduledThreadPool(5);

	public static void main(String[] args) {
		PrimeGenerator primeGenerator = new PrimeGenerator();
		timedRun(primeGenerator, 5, TimeUnit.SECONDS);
//		se.shutdownNow();
		se.shutdown();
	}

	public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
		final Thread taskThread = Thread.currentThread();
		se.schedule(() -> {((PrimeGenerator) r).cancel();taskThread.interrupt();}, timeout, unit);
		try {
			// 该方式可以捕获异常，因为是直接运行的run()方法，没有达到多线程的效果，这是掉用线程的执行方法顺序执行
			r.run();
//			new Thread(r).start();
		} catch (Exception e) {
			log.info("异常：", e);
		}
	}
}
