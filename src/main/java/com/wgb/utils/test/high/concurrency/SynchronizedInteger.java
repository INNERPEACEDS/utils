package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 清单3.3 线程安全的可变整数访问器
 * 并发性标注 @GuardedBy @NotThreadSafe @ThreadSafe 这三个类级别的标注可以描述类的线程安全保证性,属于类公开文档的一部分.它只是标注了该类是否是线程安全的,但实际上没法保证线程安全.
 * @author INNERPEACE
 * @date 2020/4/21 13:37
 */
@ThreadSafe
public class SynchronizedInteger {
	/**
	 * GuardedBy(lock) 表示只有在持有了某个特定的锁时才能访问这个域或方法
	 */
	@GuardedBy("this") private int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}
}
