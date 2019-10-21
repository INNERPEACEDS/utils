package com.wgb.utils.util.concurrency;

import com.wgb.utils.entity.user.UserSession;
import com.wgb.utils.test.Calculate1;
import com.wgb.utils.test.Calculate2;
import com.wgb.utils.util.concurrency.thread.pool.ThreadPool;
import com.wgb.utils.util.concurrency.thread.pool.ThreadPoolFactory;
import com.wgb.utils.util.concurrency.thread.pool.ThreadPoolTool;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author INNERPEACE
 * @date 2019/7/1 16:25
 **/
@Slf4j
public class Test {
	public static void main(String[] args) throws Exception {
		// Test test = new Test();
		// test.hashTest();
		test1();
		// test2();
		test3();
	}

	public static void test3 () {
		int a = -14 << 2;
		log.info("值：{}", a);

	}

	public static void test2() throws Exception {
		Calculate1 calculate1 = new Calculate1();
		Calculate2 calculate2 = new Calculate2();
		ThreadPoolTool add1 = new ThreadPoolTool<Calculate1,Void>(calculate1, "count", 1000);
		ThreadPoolFactory.THREAD_POOL.addJob(add1);
		ThreadPoolTool add2 = new ThreadPoolTool<Calculate2,Void>(calculate2, "count", 1000);
		ThreadPoolFactory.THREAD_POOL.addJob(add2);
		ThreadPoolFactory.THREAD_POOL.close();
		Thread.sleep(5000);
		log.info("1:{};2:{}", calculate1.getCount1(), calculate2.getCount2());

	}

	public static void test1() {
		// int类型变量
		int i = 1;
		UserSession userSession = new UserSession();
		// 打印变量类型为in
		System.out.println(getType(i));
		System.out.println(getType(userSession));
	}

	/**
	 * 获取变量类型方法
	 * @param o
	 * @return
	 */
	public static String getType(Object o){
		// 使用int类型的getClass()方法
		System.out.println(o.getClass());
		return o.getClass().toString();
	}

	public void hashTest() {
		System.out.println((11111111 << 15) ^ 0xffffcd7d);
		System.out.println(Integer.parseInt("0000001", 2));

		System.out.println(Integer.parseInt("0001111", 2) & 15);
		System.out.println(Integer.parseInt("0011111", 2) & 15);
		System.out.println(Integer.parseInt("0111111", 2) & 15);
		System.out.println(Integer.parseInt("1111111", 2) & 15);

		System.out.println(hash(Integer.parseInt("0001111", 2)) & 31);
		System.out.println(hash(Integer.parseInt("0011111", 2)) & 31);
		System.out.println(hash(Integer.parseInt("0111111", 2)) & 31);
		System.out.println(hash(Integer.parseInt("1111111", 2)) & 31);
	}

	private static int hash(int h) {
		h += (h << 15) ^ 0xffffcd7d;
		h ^= (h >>> 10);
		h += (h << 3);
		h ^= (h >>> 6);
		h += (h << 2) + (h << 14);
		return h ^ (h >>> 16);
	}

	public void concurrencyHashMapTest() throws Exception {
		final HashMap<String, String> map = new HashMap<>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
			}
		}, "ftf");
		t.start();
		t.join();
	}
}
