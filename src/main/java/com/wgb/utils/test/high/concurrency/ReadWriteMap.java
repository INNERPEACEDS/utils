package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 清单13.7 用读写锁包装的map
 * @author INNERPEACE
 * @date 2020/7/8
 */
@Slf4j
public class ReadWriteMap<K,V> {
	private final Map<K, V> map;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock r = lock.readLock();
	private final Lock w = lock.writeLock();

	public ReadWriteMap(Map<K, V> map) {
		this.map = map;
	}

	public V put(K key, V value) {
		w.lock();
		try {
			log.info("获取写锁{}", key);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.put(key, value);
		} finally {
			w.unlock();
			log.info("释放写锁{}", key);

		}

	}

	// remove()， putAll()， clear()也完全类似

	public V get (Object key){
		r.lock();
		try {
			log.info("获取读锁{}", key);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
//			log.info("释放读锁{}", key);
			r.unlock();
		}
		
		// 对于其他的只读Map方法也完全类似
	}

	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			map.put(i, i);
		}
		final ReadWriteMap readWriteMap = new ReadWriteMap(map);
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(() -> {
			log.info("线程4开始执行");
			readWriteMap.put(4, 5);
			log.info("线程4执行完成");
		});
		executorService.submit(() -> {
			Integer integer1 = (Integer) readWriteMap.get(1);
			log.info("线程1获得结果：{}", integer1);
		});
		executorService.submit(() -> {
			Integer integer2 = (Integer) readWriteMap.get(2);
			log.info("线程2获得结果：{}", integer2);
		});
		executorService.submit(() -> {
			Integer integer3 = (Integer) readWriteMap.get(3);
			log.info("线程3获得结果：{}", integer3);
		});
		executorService.shutdown();
	}

}

