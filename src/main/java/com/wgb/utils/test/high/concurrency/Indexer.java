package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author INNERPEACE
 * @date 2020/4/30 10:37
 */
@Slf4j
public class Indexer implements Runnable {
	private final BlockingQueue<File> queue;
	private  static final int BOUND = 20;
	private  static final int N_CONSUMERS = 10;

	public Indexer(BlockingQueue<File> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				indexFile(queue.take());
			}
		} catch (InterruptedException e) {
			log.error("线程中断");
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 清单5.9 开始桌面搜索
	 * @param roots
	 */
	public static void startIndexing(File[] roots) {
		BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
		FileFilter filter = pathname -> true;
		int j = 0;
		for(File root : roots) {
			new Thread(new FileCrawler(queue, filter, root), "生产者"+ j).start();
			j++;
		}
		for(int i = 0;i < N_CONSUMERS; i++) {
			new Thread(new Indexer(queue), "消费者" + i).start();
		}
	}

	private void indexFile(File file) {
		System.out.println(Thread.currentThread().getName() + "文件名：" + file.getPath());
	}

	public static void main(String[] args)  throws Exception {
		Thread.sleep(10000);
		startIndexing(new File("E:\\courseStudy").listFiles());
	}
}
