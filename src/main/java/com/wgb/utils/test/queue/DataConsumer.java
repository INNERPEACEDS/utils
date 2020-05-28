package com.wgb.utils.test.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消费者
 * @author INNERPEACE
 * @date 2019/11/14 14:15
 */
public class DataConsumer {

	/**
	 * 数据消费者线程池的最大线程数
	 */
	private static int threadNum = 5;

	/**
	 * 数据消费者线程池
	 */
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);

	public static void executeConsum() {
		for (int i = 0; i < threadNum; i++) {
			fixedThreadPool.execute(new DataHandler());
		}
	}
}
