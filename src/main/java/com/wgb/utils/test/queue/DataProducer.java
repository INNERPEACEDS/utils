package com.wgb.utils.test.queue;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据生产者
 * @author INNERPEACE
 * @date 2019/11/14 14:12
 */
public class DataProducer {

	/**
	 * 自增量
	 */
	private static AtomicInteger counter =  new AtomicInteger(0);

	public static int getCount() {
		// 线程安全,以原子方式将当前值加1，注意：这里返回的是自增前的值。
		return counter.getAndIncrement();
	}

	// 定期向队列塞入数据
	public static void addData2DataQueue() {
		Timer timer = new Timer();
		// 首次5秒后执行，之后间隔3秒执行一次
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 5; i++) {
						// Thread.sleep(RandomTest.getRandom() * 1l);
						String tmp = getCount() + "";
						System.out.println("-----向队列增加任务 " + tmp + "-----");
						//添加一个元素,如果队列满,则阻塞
						DataQueue.getDataQueue().put(tmp);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1000 *5, 1000 * 3);
	}
}
