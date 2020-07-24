package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 清单15.1 模拟CAS操作
 * @author INNERPEACE
 * @date 2020/7/20
 */
@ThreadSafe
public class SimulatedCAS {
	@GuardedBy("this")
	private int value;

	public synchronized int get() {
		return value;
	}

	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (oldValue == expectedValue) {
			value = newValue;
		}
		return oldValue;
	}

	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return (expectedValue == compareAndSwap(expectedValue, newValue));
	}

}