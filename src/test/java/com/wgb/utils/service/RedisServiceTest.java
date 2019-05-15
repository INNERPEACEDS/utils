package com.wgb.utils.service;

import com.wgb.utils.dao.redis.RedisDao;
import com.wgb.utils.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author INNERPEACE
 * @date 2019/5/14 15:03
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {
	@Autowired
	RedisService redisService;

	@Autowired
	RedisDao redisDao;

	/**
	 * 存储Redis测试
	 */
	@Test
	public void redisPutTest() {
		int type = 4;
		switch (type) {
			case 1:
				// 设置过期时间，时间过期后Redis不进行缓存删除
				setForHash();
				break;
			case 2:
				setForValue();
				break;
			case 3:
				setForList();
				break;
			case 4:
				setForSet();
				break;
			case 5:
				setForZset();
				break;
			default:
		}
	}

	/**
	 * 获取Redis测试
	 */
	@Test
	public void redisGetTest() {
		int type = 4;
		switch (type) {
			case 1:
				getForHash();
				break;
			case 2:
				getForValue();
				break;
			case 3:
				getForList();
				break;
			case 4:
				getForSet();
				break;
			case 5:
				getForZset();
				break;
			default:
		}
	}

	/**
	 * 获取过期时间测试
	 */
	@Test
	public void expireTest() {
		// String key = "0515test";
		String key = "20190515";
		long time = redisDao.getExpireByKey(key);
		log.info("过期时间：{}", time);
	}

	@Test
	public void redisDeleteTest() {
		String key = "20190515test1";
		redisDao.remove(key);
	}
	public void getForHash(){
		for (int i = 0; i < 10; i++) {
			String value = redisService.get("20190515test1", "0515test");
			log.info("获取的值为：{}", value);
			try {
				Thread.sleep(i * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void getForValue() {
		String value = redisDao.getForValue("20190515");
		log.info("value={}", value);
	}

	public void getForList() {
		String key = "20190515";
		long timeout = 100;
		String forList = redisDao.getForList(key);
		// String forList = redisDao.getForList(key, timeout);
		log.info("list：{}", forList);
	}

	public void getForSet() {
		String key = "20190515";
		long timeout = 100;
		// String forSet = redisDao.getForSet(key);
		// List<String> forSet = redisDao.getForSet(key, timeout);
		// log.info("forSet:{}", forSet);
		Set<String> allForSet = redisDao.getAllForSet(key);
		log.info("allForSet:{}", allForSet);
	}

	public void getForZset() {

	}

	public void setForHash() {
		redisService.set("20190515test1", "0515test", "test", 100);
	}

	public void setForValue() {
		redisDao.setForValue("20190515", "value", 90);
	}

	public void setForList() {
		String key = "20190515";
		String[] values = {"list1", "list2", "list3"};
		long timeout = 600;
		redisDao.setForList(key, values, timeout);
	}

	public void setForSet() {
		String key = "20190515";
		String[] values = {"set1", "set2", "set3", "set4"};
		long[] timeouts = {100, 200, 300, 400};
		for (int i = 0; i < values.length; i++) {
			try {
				Thread.sleep((i+5)*100 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			redisDao.setForSet(key, values[i], timeouts[i]);
		}
	}

	public void setForZset() {

	}
}
