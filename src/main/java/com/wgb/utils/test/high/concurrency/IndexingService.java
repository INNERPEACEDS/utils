package com.wgb.utils.test.high.concurrency;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 清单7.17 使用致命药丸来关闭
 * 清单7.18 IndexingService的生产者线程 CrawlerThread
 * 清单7.19 IndexingService的消费者线程 IndexerThread
 * @author INNERPEACE
 * @date 2020/6/4
 */
public class IndexingService {
	private final IndexerThread consumer = new IndexerThread();
	private final CrawlerThread producer = new CrawlerThread();
	private final BlockingQueue<File> fileQueue;
	private final FileFilter fileFilter;
	private final File root;
	private static final File POISON = new File("");

	public IndexingService(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
		this.fileQueue = fileQueue;
		this.fileFilter = fileFilter;
		this.root = root;
	}

	public void stat() {
		producer.start();
		consumer.start();
	}

	public void stop() {
		producer.interrupt();
	}

	public void awaitTermination() throws InterruptedException {
		consumer.join();
	}

	public class CrawlerThread extends Thread {
		@Override
		public void run() {
			try {
				crawl(root);
			} catch (InterruptedException e) {
//				e.printStackTrace();
				/*失败*/
			} finally {
				while (true) {
					try {
						fileQueue.put(POISON);
						break;
					} catch (InterruptedException e) {
//						e.printStackTrace();
						/*重试*/
					}
				}
			}
		}

		private void crawl(File root) throws InterruptedException {
			File[] entries = root.listFiles(fileFilter);
			if (entries != null) {
				for (File entry : entries) {
					if (entry.isDirectory()) {
						crawl(entry);
					} else if (!alreadyIndexed(entry)) {
						fileQueue.put(entry);
					}
				}
			}
		}
		private boolean alreadyIndexed(File file){
			return false;
		};
	}

	public class IndexerThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					File file = fileQueue.take();
					if (file == POISON) {
						break;
					}
					indexFile(file);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private void indexFile(File file) {
			System.out.println(Thread.currentThread().getName() + "文件名：" + file.getPath());
		}
	}


}
