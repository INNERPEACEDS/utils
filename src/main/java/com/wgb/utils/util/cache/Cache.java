package com.wgb.utils.util.cache;

/**
 * 缓存,超时时间单位：分钟
 * @author INNERPEACE
 * @date 2019/3/29 17:14
 **/

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * 缓存
 * @author INNERPEACE
 * @param <K>
 * @param <V>
 */
public class Cache<K, V> {

	/**
	 * 声明缓存
	 */
	private LoadingCache<K, V> graphs;

	/**
	 *
	 * @param timeOut
	 * 分钟
	 */
	public Cache(long timeOut) {
		initialize(TimeUnit.MINUTES, timeOut);
	}

	/**
	 * 使用具体的时间类型和具体的时间来定时构造
	 * @param timeUnit 时间类型（）
	 * @param timeOut
	 */
	public Cache(TimeUnit timeUnit, Long timeOut) {
		initialize(timeUnit, timeOut);
	}

	/**
	 * 初始化缓存容器
	 */
	private void initialize(TimeUnit timeUnit, long timeOut) {
		if (timeOut > 0) {
			graphs = CacheBuilder.newBuilder().expireAfterWrite(timeOut, timeUnit)
					.build(new CacheLoader<K, V>() {
						@Override
						public V load(K key) {
							// 取消默认的自动缓存添加器
							return null;
						}
					});
		} else {
			graphs = CacheBuilder.newBuilder().build(new CacheLoader<K, V>() {
				@Override
				public V load(K key) {
					// 取消默认的自动缓存添加器
					return null;
				}
			});
		}
	}

	/**
	 * 获取缓存信息
	 *
	 * @param key
	 * @return
	 */
	public V get(K key) {
		V v = null;
		try {
			v = graphs.get(key);
		} catch (ExecutionException e) {
		} finally {
			return v;
		}
	}

	/**
	 * 设置缓存信息
	 *
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		if (null != key && null != value) {
			graphs.put(key, value);
		}
	}

	/**
	 * 删除一个缓存
	 *
	 * @param key
	 */
	public void remove(String key) {
		graphs.invalidate(key);
	}

	/**
	 * 清空全部缓存
	 */
	public void clearAll() {
		graphs.invalidateAll();
	}

}
