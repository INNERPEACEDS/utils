package com.wgb.utils.util.concurrency.thread.pool;


import java.util.concurrent.*;

/**
 * @author INNERPEACE
 * @date 2019/7/2
 **/
public class ThreadPoolFactory {
	public static final ThreadPool THREAD_POOL = newDefaultThreadExecutor();

	/**
	 * 默认线程池
	 * @return
	 */
	public static ThreadPool newDefaultThreadExecutor() {
		return new ThreadPool();
	}

	/**
	 * 自定义线程池
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param namedThreadFactory
	 * @param workQueue
	 * @param handler
	 * @return
	 */
	public static ThreadPool newCustomizeThreadExecutor(int corePoolSize,
	                                                    int maximumPoolSize,
	                                                    long keepAliveTime,
	                                                    TimeUnit unit,
	                                                    ThreadFactory namedThreadFactory,
	                                                    BlockingQueue<Runnable> workQueue,
	                                                    RejectedExecutionHandler handler) {
		return new ThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, unit, namedThreadFactory,
				workQueue, handler);
	}
}
