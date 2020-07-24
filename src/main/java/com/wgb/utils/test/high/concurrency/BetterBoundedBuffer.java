package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 清单14.8 在BoundedBuffer中的take和put方法使用“依据条件通知”
 * 即：take方法在“满”状态变为“非满”状态 或者 put方法“空”状态变为“非空”状态
 * @author INNERPEACE
 * @date 2020/7/15 18:18
 */
@Slf4j
public class BetterBoundedBuffer<V> extends BaseBoundedBuffer<V> {
	// 条件谓词: not-full (!isFul1())
	// 条件谓词: not - empty (!isEmpty())

	public BetterBoundedBuffer(int size) { super (size); }

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
		// 对BoundedBuffer进行优化
		boolean empty = isEmpty();
		doPut(v);
		if (empty) {
			//		notifyAll(); // 会丢失通知
			notify();
		}
		log.info("线程{}put唤醒", Thread.currentThread().getName());
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
		boolean ful1 = isFull();
		V v = doTake() ;
		if (ful1) {
			notifyAll();
//			notify(); // 会丢失通知
		}
		log.info("{}take唤醒", name);
		return v;
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		final BetterBoundedBuffer boundedBuffer = new BetterBoundedBuffer(5);
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
