package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 清单14.11 有限缓存使用显式的条件变量
 * @author INNERPEACE
 * @date 2020/7/17
 */
@ThreadSafe
public class ConditionBoundedBuffer<T> {
	private final T[] items = (T[]) new Object[100];
	protected final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	/**
	 * head 用于拿数据的下标 0->n->0-n循环， tail属于放数据的下标 0->n->0-n循环，FIFO
	 */
	private int head, tail, count;

	public void put(T t) throws InterruptedException {
		lock.lock();
		try {
			// 条件谓语：isFull,notFull进行wait
			while (count == items.length) {
				notFull.await();
			}
			items[tail] = t;
			if (++tail == items.length) {
				tail = 0;
			}
			++count;
			// 向里面放数据，什么时候通知，通知谁？放完数据notEmpty变成true
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public T take() throws InterruptedException {
		lock.lock();
		try{
			// 条件谓语：isEmpty，notEmpty进行wait
			while (count == 0) {
				notEmpty.await();
			}
			T t = items[head];
			items[head] = null;
			if (++head == items.length) {
				head = 0;
			}
			--count;
			// 通知：由isFull到notFull状态
			notFull.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}
}
