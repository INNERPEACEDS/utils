package com.wgb.utils.util.concurrency.thread.pool;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

/**
 * 简化线程池的创建
 * @author INNERPEACE
 * @date 2019/7/2
 **/
@Slf4j
public class ThreadPool {
	/**
	 * 常驻核心线程数
	 */
	private static final int CORE_POOL_SIZE = 10;

	/**
	 * 线程池能够容纳同时执行的最大线程数
	 */
	private static final int MAXIMUM_POOL_SIZE = 15;

	/**
	 * 线程池中的线程空闲时间
	 */
	private static final long KEEP_ALIVE_TIME = 60;

	/**
	 * 任务队列容量
	 */
	private static final int QUEUE_CAPACITY = 20;

	/**
	 * 任务队列（线程共享）
	 */
	private BlockingQueue<Runnable> queue;

	/**
	 * 实例化线程池（线程共享）
	 */
	private ThreadPoolExecutor threadPoolExecutor;

	ThreadPool() {
		log.info("默认方式创建线程池");
		this.queue = new LinkedBlockingDeque<>(QUEUE_CAPACITY);
		// 创建线程池
		this.threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.AbortPolicy());
		// 启动核心线程，使其处于等待工作的空闲状态。
		this.threadPoolExecutor.prestartCoreThread();
		status();
	}

	ThreadPool(int corePoolSize,
              int maximumPoolSize,
              long keepAliveTime,
              TimeUnit unit,
               ThreadFactory namedThreadFactory,
              BlockingQueue<Runnable> workQueue,
              RejectedExecutionHandler handler) {
		/*ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
				.setNameFormat("demo-pool-%d").build();*/
		log.info("自定义方式创建线程池");
		this.queue = workQueue;
		threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, queue,
				namedThreadFactory, handler);
		this.threadPoolExecutor.prestartCoreThread();
		status();
	}

	/**
	 * 关闭容器
	 */
	public void close() {
		if (!threadPoolExecutor.isShutdown()) {
			threadPoolExecutor.shutdown();
		}
	}

	/**
	 * 加入到执行队列
	 *
	 * @param obj 线程任务
	 */
	public void addJob(Runnable obj) {
		log.info("放入线程池队列，当前等待：{}", queue.size());
		if (queue.size() == QUEUE_CAPACITY) {
			log.info("没有线程可以处理任务了，任务将被丢弃...");
		}
		try {
			threadPoolExecutor.execute(obj);
		} catch (Exception e) {
			log.error("丢弃任务{}", e);
		}
	}

	/**
	 * 线程池状态
	 */
	private void status() {
		log.info("当前线程池大小为：{}，当前队列大小为：{}", threadPoolExecutor.getCorePoolSize(), queue.size());
	}
}
