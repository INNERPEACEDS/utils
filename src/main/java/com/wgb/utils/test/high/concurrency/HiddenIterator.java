package com.wgb.utils.test.high.concurrency;

import com.wgb.utils.util.concurrency.thread.pool.ThreadPoolFactory;
import com.wgb.utils.util.concurrency.thread.pool.ThreadPoolTool;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 清单5.6 迭代隐藏在字符串的拼接中
 * @author INNERPEACE
 * @date 2020/4/29 13:26
 */
@NotThreadSafe
public class HiddenIterator {
	// 如果改为 HashSet包装成synchronizedSet则可以保证线程安全
	@GuardedBy("this")
	private final Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
//	private final Set<Integer> set = new HashSet<Integer>();

	public synchronized void add(Integer i) {
		set.add(i);
	}

	public synchronized void remove(Integer i) {
		set.remove(i);
	}

	public void addRenThings(Integer j,Integer k) {
		Random r = new Random();
		for (int i = 0; i < j; i++) {
			add(r.nextInt());
		}
		for (int i = 0; i < k; i++) {
			System.out.println("DEBUG: added ten elements to " + set);
		}
	}

	public void addRenThings() {
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			add(r.nextInt());
		}
		for (int i = 0; i < 1; i++) {
			System.out.println("DEBUG: added ten elements to " + set);
		}
	}

	public static void main(String[] args) {
		try {
			HiddenIterator hiddenIterator = new HiddenIterator();
			for (int i = 0; i <10000; i++) {
				ThreadPoolTool add1 = new ThreadPoolTool<HiddenIterator,Void>(hiddenIterator, "addRenThings");
				ThreadPoolFactory.THREAD_POOL.addJob(add1);
			}
			ThreadPoolFactory.THREAD_POOL.close();
		} catch (Exception e) {
			System.out.println("异常：" + e);
			System.exit(1);
		}
	}
}
