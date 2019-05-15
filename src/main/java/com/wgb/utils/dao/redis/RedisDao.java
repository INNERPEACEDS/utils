package com.wgb.utils.dao.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author INNERPEACE
 * @date 2019/5/14 14:19
 **/
@Repository
@Slf4j
public class RedisDao {
	@Resource
	private StringRedisTemplate template;

	/**
	 * 根据key获取过期时间
	 * @param key
	 */
	public long getExpireByKey(String key) {
		long time = template.getExpire(key);
		return time;
	}

	/**
	 *  存储redis(Hash)
	 * @param productId 存储数据类型
	 * @param key       存储数据键
	 * @param value     存储数据值
	 * @param timeout   超时跳出时间
	 */
	public void setForHash(String productId, String key, String value, Long timeout) {
		template.opsForHash().put(productId, key, value);
		template.expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 获取redis(Hash)
	 * @param productId 存储数据类型
	 * @param key       存储数据键
	 * @return
	 */
	public String getForHash(String productId, String key) {
		return Objects.requireNonNull(template.opsForHash().get(productId, key)).toString();
	}

	/**
	 * 删除redis(Hash)
	 * @param productId 存储数据类型
	 * @param key       存储数据键
	 */
	public void removeForHash(String productId, String key) {
		template.opsForHash().delete(productId, key);
	}

	/**
	 * 存储Redis（Value）
	 * @param key     存储数据键
	 * @param value   存储数据值
	 * @param timeout 存储数据缓存时间
	 */
	public void setForValue(String key, String value, long timeout) {
		template.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
		/*template.opsForValue().set(key, value);
		template.expire(key, timeout, TimeUnit.SECONDS);*/
	}

	/**
	 * 获取Redis（Value）
	 * @param key
	 * @return
	 */
	public String getForValue(String key) {
		return Objects.requireNonNull(template.opsForValue().get(key));
	}

	/**
	 * 存储Redis（List）
	 * @param key
	 * @param values
	 * @param timeout
	 */
	public void setForList(String key, String[] values, long timeout) {
		template.opsForList().leftPushAll(key, values);
		template.expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 移出并获取列表的第一个元素
	 * @param key
	 * @return
	 */
	public String getForList(String key) {
		return Objects.requireNonNull(template.opsForList().leftPop(key));
	}

	/**
	 * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时
	 * @param key
	 * @param timeout
	 * @return
	 */
	public String  getForList(String key, long timeout) {
		return Objects.requireNonNull(template.opsForList().leftPop(key, timeout, TimeUnit.SECONDS));
	}

	/**
	 * 存储Redis（Set）
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public void setForSet(String key, String value, long timeout) {
		template.opsForSet().add(key, value);
		template.expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 移出并获取列表的一个元素
	 * @param key
	 * @return
	 */
	public String getForSet(String key) {
		return Objects.requireNonNull(template.opsForSet().pop(key));
	}

	/**
	 * 移出并获取列表的一个元素， 如果列表没有元素会阻塞列表直到等待超时
	 * @param key
	 * @param timeout
	 * @return
	 */
	public List<String> getForSet(String key, long timeout) {
		return template.opsForSet().pop(key, timeout);
	}

	/**
	 * 根据key获取set集合(不移出)
	 * @param key
	 * @return
	 */
	public Set<String> getAllForSet(String key) {
		return template.opsForSet().members(key);
	}

	public void setForZset(String key, String value, double score,long timeout) {
		template.opsForZSet().add(key, value, score);
		template.expire(key, timeout, TimeUnit.SECONDS);
	}

	public Set<String> getForZset(String key, double score1, double score2) {
		return template.opsForZSet().rangeByScore(key, score1, score2);
	}

	/**
	 * 根据key删除缓存
	 * @param key
	 */
	public void remove(String key) {
		template.delete(key);
	}



}
