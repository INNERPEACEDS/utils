package com.wgb.utils.test.queue;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 消费者唤醒器
 * @author INNERPEACE
 * @date 2019/11/14 14:18
 */
public class ConsumerWakeUp {
	public static void wakeUp() {
//		ScheduledExecutorService service = Executors.newScheduledThreadPool();

		Timer timer = new Timer();
		// 定期唤醒消费者：首次5秒后执行，之后间隔5秒执行
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("-----唤醒消费者-----");
				DataConsumer.executeConsum();
			}
		}, 1000 * 5, 1000 * 5);
	}
}
