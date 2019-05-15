package com.wgb.utils.service.redis.impl;

import com.wgb.utils.dao.redis.RedisDao;
import com.wgb.utils.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author INNERPEACE
 * @date 2019/5/14 14:54
 **/
@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private RedisDao redisDao;

	@Override
	public void set(String productId, String key, String value, long timeout) {
		redisDao.setForHash(productId, key, value, timeout);
	}

	@Override
	public String get(String productId, String key) {
		return redisDao.getForHash(productId, key);
	}

	@Override
	public void remove(String productId, String key) {
		redisDao.removeForHash(productId, key);
	}
}
