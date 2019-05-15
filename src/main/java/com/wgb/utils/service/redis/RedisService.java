package com.wgb.utils.service.redis;

/**
 * @author INNERPEACE
 * @date 2019/5/14 14:49
 **/
public interface RedisService {
	/**
	 * 存储redis
	 * @param productId
	 * @param key
	 * @param value
	 * @param timeout
	 */
	void set(String productId, String key, String value, long timeout);

	/**
	 * 获取redis
	 * @param productId
	 * @param key
	 * @return
	 */
	String get(String productId, String key);

	/**
	 * 移除redis
	 * @param productId
	 * @param key
	 */
	void remove(String productId, String key);
}
