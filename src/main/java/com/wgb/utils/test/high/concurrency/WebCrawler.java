package com.wgb.utils.test.high.concurrency;


import lombok.extern.slf4j.Slf4j;

import javax.annotation.concurrent.GuardedBy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 清单7.22 使用TrackingExecutorService为后续执行来保存未完成的任务
 *
 * @author INNERPEACE
 * @date 2020/6/5
 */
@Slf4j
public abstract class WebCrawler {
	private volatile TrackingExecutor exec;
	@GuardedBy("this")
	private final Set<String> urlsToCrawl = new HashSet<String>();
	@GuardedBy("this")
	private final Set<String> urlsToCrawl1 = new HashSet<String>();

	private static final int TIMEOUT = 5;
	private static final TimeUnit UNIT = TimeUnit.SECONDS;

	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		try {
			for (int i = 0; i < 100; i++) {
				urlsToCrawl.add(new String("parent.com" + (i + 1)));
			}
			log.info("创建地址成功");
		} catch (Exception e) {
			log.error("创建地址异常", e);
			return;
		}
		for (String url : urlsToCrawl) {
			submitCrawlTask(url);
		}
		urlsToCrawl.clear();
	}

	public synchronized void stop() throws InterruptedException {
		log.info("开始停止");
		try {
			saveUncrawled(exec.shutdownNow());
			if (exec.awaitTermination(TIMEOUT, UNIT)) {
				saveUncrawled1(exec.getCancelledTasks());
			}
		} finally {
			exec = null;
		}
	}

	private void saveUncrawled1(List<Runnable> uncrawled) {
		for (Runnable task : uncrawled) {
			urlsToCrawl1.add(((CrawlTask) task).getPage());
		}
	}

	private void saveUncrawled(List<Runnable> uncrawled) {
		for (Runnable task : uncrawled) {
			urlsToCrawl.add(((CrawlTask) task).getPage());
		}
	}

	protected abstract List<String> processPage(String url);

	private void submitCrawlTask(String url) {
		exec.execute(new CrawlTask(url));
	}

	private class CrawlTask implements Runnable {
		private final String url;

		public CrawlTask(String url) {
			this.url = url;
		}

		@Override
		public void run() {
			for (String link : processPage(url)) {
				if (Thread.currentThread().isInterrupted()) {
					return;
				}
				submitCrawlTask(link);
			}
		}

		public String getPage() {
			return url;
		}
	}
}
