package com.wgb.utils.util.google.guava.caches;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author INNERPEACE
 * @date 2019/6/20 10:13
 **/
@Slf4j
public class CacheTest extends Thread {

	/**
	 * 缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
	 */
	private static final LoadingCache<Integer, Student> STUDENT_CACHE
			// CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
			= CacheBuilder.newBuilder()
			// 设置并发级别为8，并发级别是指可以同时写缓存的线程数
			.concurrencyLevel(8)
			// 设置写缓存后8秒钟过期
			.expireAfterWrite(8, TimeUnit.SECONDS)
			// 设置缓存容器的初始容量为10
			.initialCapacity(2)
			// 设置缓存最大容量为100，超过100之后就会按照LRU最近最少使用算法来移除缓存项
			.maximumSize(10)
			// 设置要统计缓存的命中率
			.recordStats()
			// 设置缓存的移除通知
			.removalListener(new RemovalListener<Object, Object>() {
				@Override
				public void onRemoval(RemovalNotification<Object, Object> notification) {
					System.out.println(
							notification.getKey() + " was removed, cause is " + notification.getCause());
				}
			})

			// build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
			.build(new CacheLoader<Integer, Student>() {
				@Override
				public Student load(Integer key) throws Exception {
					System.out.println("load student " + key);
					Student student = new Student();
					student.setId(key);
					student.setName("name " + key);
					return student;
				}
			});

	private String key = "payCacheKey";

	public static class Student {
		private int id;
		public String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return id + " | " + name;
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		for (int i = 0; i < 20; i++) {
			// 从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
			Student student = STUDENT_CACHE.get(i);
			log.info("缓存数据[{}]：{}", i, student);
			// 休眠1秒
			TimeUnit.SECONDS.sleep(1);
		}
        // 最后打印缓存的命中率等情况
 	    log.info("cache stats:{}", STUDENT_CACHE.stats().toString());
	}

	@Override
	public void run() {

	}
}
