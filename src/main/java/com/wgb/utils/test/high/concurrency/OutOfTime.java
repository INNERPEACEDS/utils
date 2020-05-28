package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author INNERPEACE
 * @date 2020/5/18 17:53
 */
@Slf4j
public class OutOfTime {
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		log.info("开始时间：{}", LocalTime.now());
		timer.schedule(new ThrowTime("一"), 1);
		SECONDS.sleep(2);
		log.info("运行主线程");
		timer.schedule(new ThrowTime("二"), 1);
		SECONDS.sleep(4);
	}

	public static class ThrowTime extends TimerTask {
		private String flag;

		public ThrowTime(String flag) {
			this.flag = flag;
		}
		@Override
		public void run() {
			log.info("标识：{},时间：{}", flag, LocalTime.now());
			throw new RuntimeException();
		}
	}
}
