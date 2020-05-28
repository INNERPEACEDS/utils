package com.wgb.utils.test.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 数据队列实体类
 * @author INNERPEACE
 * @date 2019/11/14 14:20
 */
public class DataQueue {
	private static LinkedBlockingQueue<String> dataQueue = new LinkedBlockingQueue<String>();

	public static LinkedBlockingQueue<String> getDataQueue() {
		return dataQueue;
	}

	public static void setDataQueue(LinkedBlockingQueue<String> dataQueue) {
		DataQueue.dataQueue = dataQueue;
	}
}
