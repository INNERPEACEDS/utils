package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * 清单3.2 非线程安全的可变整数访问器
 * @author INNERPEACE
 * @date 2020/4/21 13:31
 */
@NotThreadSafe
public class MutableInteger {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
