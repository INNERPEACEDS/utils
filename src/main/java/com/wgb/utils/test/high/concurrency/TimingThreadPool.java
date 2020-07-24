package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 清单8.9 扩展线程池以提供日志和计时功能
 * @author INNERPEACE
 * @date 2020/6/29
 */
public class TimingThreadPool extends ThreadPoolExecutor {
	private final ThreadLocal<Long> startTime = new ThreadLocal<>();
	private final Logger log = Logger.getLogger("TimingThreadPool");
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		log.fine(String.format("Thread %s: start %s", t, r));
		startTime.set(System.nanoTime());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			log.fine(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
		} finally {
			super.afterExecute(r, t);
		}
	}

	@Override
	protected void terminated() {
		try {
			log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get() ));
		} finally {
			super.terminated();
		}
	}

	public static void main(String[] args) {
		ExecutorService timingThreadPool = new TimingThreadPool(2, 3, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
		timingThreadPool.submit(() -> {
			System.out.println("开始执行");
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("完成");
			return "结束";

		});
		timingThreadPool.shutdown();
	}
}
