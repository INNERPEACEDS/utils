package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 清单14.6 有限缓存使用条件队列
 * @author INNERPEACE
 * @date 2020/7/15 18:18
 */
@Slf4j
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
	// 条件谓词: not-full (!isFul1())
	// 条件谓词: not - empty (!isEmpty())

	public BoundedBuffer(int size) { super (size); }

	/**
	 * 阻塞，直到: not-full
	 * @param v
	 * @throws InterruptedException
	 */
	public synchronized void put(V v) throws InterruptedException {
		while (isFull()) {
			log.info("线程{}put", Thread.currentThread().getName());
			wait();
		}
		doPut(v);
		log.info("线程{}put唤醒", Thread.currentThread().getName());
//		notifyAll(); // 会丢失通知
		notify();
	}

	/**
	 * 阻塞，直到: not - empty
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized V take() throws InterruptedException {
		String name = Thread.currentThread().getName();
		while (isEmpty() ){
			log.info("{}take", name);
			Thread.sleep(3000);
			wait();
		}
		V v = doTake() ;
		log.info("{}take唤醒", name);
		notifyAll();
//		notify(); // 会丢失通知
		return v;
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		final BoundedBuffer boundedBuffer = new BoundedBuffer(5);
		for (int i = 1; i < 7; i++) {
			int finalI = i;
			executorService.submit(()->{
				Thread.currentThread().setName("线程" + finalI);
				try {
					if (finalI == 6) {
						Thread.sleep(5000);
						boundedBuffer.put(finalI);
					} else {
						boundedBuffer.take();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}
}
