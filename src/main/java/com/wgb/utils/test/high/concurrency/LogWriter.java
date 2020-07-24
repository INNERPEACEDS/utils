package com.wgb.utils.test.high.concurrency;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 清单7.13 不支持关闭的生产者-消费者日志服务
 * @author INNERPEACE
 * @date 2020/6/2
 */
public class LogWriter {
	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;

	public LogWriter(PrintWriter printWriter) {
		this.queue = new LinkedBlockingQueue<String>();
		this.loggerThread = new LoggerThread(printWriter);
	}

	public void start() {
		loggerThread.start();
	}

	public void log(String msg) throws InterruptedException {
		queue.put(msg);
	}

	private class LoggerThread extends Thread{
		private final PrintWriter writer;

		private LoggerThread(PrintWriter writer) {
			this.writer = writer;
		}

		@Override
		public void run() {

			try {
				while (true) {
					writer.println(queue.take());
				}
			} catch (InterruptedException e) {
				writer.close();
//				e.printStackTrace();
			}
		}
	}
}
