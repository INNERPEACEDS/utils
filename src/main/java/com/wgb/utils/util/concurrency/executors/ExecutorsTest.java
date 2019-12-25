package com.wgb.utils.util.concurrency.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author INNERPEACE
 * @date 2019/12/19 10:06
 */
@Slf4j
public class ExecutorsTest {

	public static void main(String[] args) throws InterruptedException {
//		newSingleThreadExecutorTest();
		newFixedThreadPoolTest();
		log.info("主线程退出");
	}

	public static void newSingleThreadExecutorTest() throws InterruptedException {
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(
				()->{
					log.info("进入子线程");
					try {
						Thread.sleep(3000);
						log.info("线程：{}，刚刚睡了3秒", Thread.currentThread().getName());
						Thread.sleep(5000);
						log.info("刚刚睡了5秒");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						log.info("异常：", e);
					}
					long count1 = 20;
					count1 = count1 + 2;
					log.info("线程：{}，count1:{}", Thread.currentThread().getName(), count1);
				});
		service.execute(() -> {
			log.info("线程2：{}", Thread.currentThread().getName());
		});
		log.info("开始关闭：");
		service.shutdown();
		/*Thread.sleep(3000);
		if (service.isShutdown()) {
			log.info("子线程已退出");
		} else {
			log.info("子线程还在运行");
		}
		Thread.sleep(7000);
		if (service.isShutdown()) {
			log.info("子线程已退出");
		} else {
			log.info("子线程还在运行");
		}*/
	}

	public static void newFixedThreadPoolTest() {
		ExecutorService service = Executors.newFixedThreadPool(5);
		service.execute(() -> {
			log.info("进入1");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("线程1：{}", Thread.currentThread().getName());
		});
		service.execute(() -> {
			log.info("进入2");
			log.info("线程2：{}", Thread.currentThread().getName());
		});
		service.execute(() -> {
			log.info("进入3");
			log.info("线程3：{}", Thread.currentThread().getName());
		});
		service.execute(() -> {
			log.info("进入4");
			log.info("线程4：{}", Thread.currentThread().getName());
		});
		service.execute(() -> {
			log.info("进入5");
			log.info("线程5：{}", Thread.currentThread().getName());
		});
		service.execute(() -> {
			log.info("进入6");
			log.info("线程6：{}", Thread.currentThread().getName());
		});
		service.shutdown();
	}


}
