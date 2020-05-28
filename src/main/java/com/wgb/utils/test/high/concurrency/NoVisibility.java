package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * 1.在没有同步的情况下共享变量（不要这样做）
 * @author INNERPEACE
 * @date 2020/3/19 15:56
 */
@Slf4j
public class NoVisibility {
	private static boolean ready;
	private  static int number;

	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while (!ready) {
				// Thread.yield();
			}
			log.info("thread，ready={},number={}", ready, number);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new ReaderThread().start();
		Thread.sleep(1000);
		number = 42;
		ready = true;
		log.info("main,ready={},number={}", ready, number);
	}

}
