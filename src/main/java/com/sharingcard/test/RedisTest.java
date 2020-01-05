package com.sharingcard.test;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTest {
	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Test
	public void redisTest() {
		String key = "redisTestKey";
		String value = "I am test value";
		
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		
		//数据插入测试：
		opsForValue.set(key, value);
		String valueFromRedis = opsForValue.get(key);
		//logger.info("redis value after set: {}", valueFromRedis);
		//assertThat(valueFromRedis, is(value));
		
		//数据删除测试：
		redisTemplate.delete(key);
		valueFromRedis = opsForValue.get(key);
		//logger.info("redis value after delete: {}", valueFromRedis);
		//assertThat(valueFromRedis, equalTo(null));
	}
}
