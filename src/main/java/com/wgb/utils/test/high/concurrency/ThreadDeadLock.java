package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 清单8.1 在单线程化的Executor中死锁的任务（不要这样做）
 * @author INNERPEACE
 * @date 2020/6/28
 */
@Slf4j
public class ThreadDeadLock {
	ExecutorService exec = Executors.newSingleThreadExecutor();

	public class RenderPageTask implements Callable<String> {

		@Override
		public String call() throws Exception {
			Future<String> header, footer;
			header = (Future<String>) exec.submit(
					() -> {
						String head = "页面头数据";
						return head;
					}
			);
			footer = (Future<String>) exec.submit(
					() -> {
						String foot = "页面尾数据";
						return foot;
					}
			);
			String page = "body数据";
			return header.get() + page + footer.get();
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		Future<String> submit = executorService.submit((new ThreadDeadLock()).new RenderPageTask());
//		log.info("结果：{}", submit.get());
//		executorService.shutdown();

		// 在单线程化的Executor中死锁的任务
		ThreadDeadLock threadDeadLock = new ThreadDeadLock();
		Future<String> submit = threadDeadLock.exec.submit(threadDeadLock.new RenderPageTask());
		log.info("结果：{}", submit.get());
		threadDeadLock.exec.shutdown();
	}
}
