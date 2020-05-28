package com.wgb.utils.test.high.concurrency;

import com.wgb.utils.entity.list.Person;

import javax.annotation.concurrent.GuardedBy;

/**
 * 清单4.3 私有锁保护状态
 * @author INNERPEACE
 * @date 2020/4/24 17:41
 */
public class PrivateLock {
	private final Object myLock = new Object();
	@GuardedBy("myLock") Person person;

	void someMethod() {
		synchronized (myLock) {
			// 访问或修改Person的状态
		}
	}
}