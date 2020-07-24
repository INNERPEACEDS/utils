package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author INNERPEACE
 * @date 2020/7/20 17:46
 */
@ThreadSafe
public class AtomicPseudoRandom extends PseudoRandom {
	private AtomicInteger seed;
	AtomicPseudoRandom(int seed) { this.seed = new AtomicInteger(seed); }

	public int nextInt(int n) {
		while (true) {
			int s = seed.get();
			int nextSeed = calculateNext(s);
			if (seed.compareAndSet(s, nextSeed)) {
				int remainder = s % n;
				return remainder > 0 ? remainder : remainder + n;
			}
		}
	}
}

