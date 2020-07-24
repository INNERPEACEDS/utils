package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.ThreadSafe;

/**
 * 清单15.2 使用CAS实现的非阻塞计数器
 * @author INNERPEACE
 * @date 2020/7/20
 */
@ThreadSafe
public class CasCounter {
	private SimulatedCAS value;
	public int getValue() {
		return value.get();
	}

	public int increment() {
		int v;
		do {
			v = value.get();
		} while (v != value.compareAndSwap(v, v + 1));
		return v + 1;
	}
}

