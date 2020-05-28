package com.wgb.utils.test.queue;

/**
 * @author INNERPEACE
 * @date 2019/11/14 14:14
 */
public class DataHandler implements Runnable{
	@Override
	public void run() {
		//移除并返问队列头部的元素,如果队列为空则返回null
		String data = DataQueue.getDataQueue().poll();
		if(data != null) {
			System.out.println("处理" + data + "完成");
		} else {
			System.out.println("队列中没有任务");
		}

	}
}
