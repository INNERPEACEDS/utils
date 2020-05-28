package com.wgb.utils.test.high.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 清单5.14 使用信号量来约束容器
 * @author INNERPEACE
 * @date 2020/5/6 17:06
 */
public class BoundedHashSet<T> {
	private final Set<T> set;
	private final Semaphore semaphore;

	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.semaphore = new Semaphore(bound);
	}

	public boolean add(T t) throws InterruptedException{
		semaphore.acquire();
		boolean wasAdded = false;
		try {
			wasAdded = set.add(t);
			return wasAdded;
		} finally {
			if (!wasAdded) {
				semaphore.release();
			}
		}
	}

	public boolean remove(Object o) {
		boolean wasRemoved = set.remove(o);
		if (wasRemoved) {
			semaphore.release();
		}
		return wasRemoved;
	}

}
