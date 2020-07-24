package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author INNERPEACE
 * @date 2020/7/20
 */
public class PseudoRandom {
	public int calculateNext(int seed) {
		return seed << 2;
	}

	public static void main(String[] args) {
		/*PseudoRandom pseudoRandom = new PseudoRandom();
		int i = pseudoRandom.calculateNext(10);
		System.out.println("i=" + i);*/
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(
				() -> {
					AtomicPseudoRandom atomicPseudoRandom = new AtomicPseudoRandom(2);
					int n = 2;
					while (true) {
						n = atomicPseudoRandom.nextInt(n);
					}
				}
		);
		executorService.submit(
				() -> {
					ReentrantLockPseudoRandom reentrantLockPseudoRandom = new ReentrantLockPseudoRandom(2);
					int n = 2;
					while (true) {
						n = reentrantLockPseudoRandom.nextInt(n);
					}
				}
		);
	}
}
