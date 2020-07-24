package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.Immutable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 清单15.3  使用CAS避免多元的不变约束
 * @author INNERPEACE
 * @date 2020/7/20
 */
public class CasNumberRange {
	@Immutable
	private static class IntPair {
		final int lower; // Invariant: lower <= upper
		final int upper;

		IntPair(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
	}

	private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0, 0));
	public int getLower() { return values.get().lower; }
	public int getUpper() { return values.get().upper; }

	public void setLower(int i) {
		while (true) {
			IntPair oldV = values.get();
			if (i > oldV.upper) {
				throw new IllegalArgumentException("Can’t set lower to " + i + " > upper");
			}
			IntPair newV = new IntPair(i, oldV.upper);
			if (values.compareAndSet(oldV, newV)) {
				return;
			}
		}
	} // similarly for setUpper

	public void setUpper(int i) {
		while (true) {
			IntPair oldV = values.get();
			if (i < oldV.lower) {
				throw new IllegalArgumentException("Can't set upper to " + i + " < lower");
			}
			IntPair newV = new IntPair(oldV.lower, i);
			if (values.compareAndSet(oldV, newV)) {
				return;
			}
		}
	}

}

