package com.wgb.utils.test.thead;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author INNERPEACE
 * @date 2019/12/26 16:39
 */
@Slf4j
public class CountdownLatchTest2 {
	public static int threadNumber = 5;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = null;
		executorService = Executors.newFixedThreadPool(5);
		CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
		for (int i = 0; i < threadNumber; i++) {
			List<Callable<List<String>>> tasks = new ArrayList<>();
			/*Callable<List<String>> readList = new ObtainData(i);
			tasks.add(readList);*/
			FutureTask<List<String>> future = new FutureTask<>(new ObtainData(i));
			// List<Future<List<String>>> futures = executorService.invokeAll(tasks);
			executorService.submit(future);
			log.info("提前进入写{}操作", i + 1);
			BlockingDeque<List<String>> queue = new LinkedBlockingDeque<>();

			// 处理线程返回结果
//			Thread.sleep(3000);
			queue.add(future.get());
			FutureTask future1 = new FutureTask<>(new DataToFile(queue, countDownLatch));
			executorService.submit(future1);

			/*Future futureConsumer = executorService.submit(new DataToFile(queue, countDownLatch));
			if (futureConsumer.get() != null) {
				throw new RuntimeException("执行读取数据发生异常，需要重新生成数据！");
			}*/


			/*if (futures.size() > 0) {
				for (Future<List<String>> future : futures) {
					queue.add(future.get());
					Future futureConsumer = executorService.submit(new DataToFile(queue, countDownLatch));
					if (futureConsumer.get() != null) {
						throw new RuntimeException("执行读取数据发生异常，需要重新生成数据！");
					}
				}
			}*/
		}
		countDownLatch.await();
		executorService.shutdown();
		// 追加统计结果信息
		log.info("所有线程运行完毕，退出主程序！");
//		System.exit(1);
	}

}
