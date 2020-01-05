package com.sharingcard.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharingcard.redis.util.RedisUtil;

import redis.clients.jedis.JedisPoolConfig;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.jedis.pool.max-idle}")
	private Integer maxIdle;

	@Value("${spring.redis.jedis.pool.max-active}")
	private Integer maxTotal;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private Integer maxWaitMillis;
 
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.password}")
	private String password;
//	@Value("${spring.redis.cluster.max-redirects}")
//	private Integer mmaxRedirectsac;

	/**
	 * JedisPoolConfig 连接池
	 * 
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 连接池的最大数据库连接数
		jedisPoolConfig.setMaxTotal(maxTotal);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		//jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		jedisPoolConfig.setMinEvictableIdleTimeMillis(1800000);
		// 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		//jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		jedisPoolConfig.setNumTestsPerEvictionRun(3);
	
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		//jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		// 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
		//jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		// 在空闲时检查有效性, 默认false
		//jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		return jedisPoolConfig;
	}
	
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //连接池  
        JedisConnectionFactory.setPoolConfig(jedisPoolConfig);  
        //IP地址  
        JedisConnectionFactory.setHostName(host);  
        //端口号  
        JedisConnectionFactory.setPort(port);  
        //如果Redis设置有密码  
        JedisConnectionFactory.setPassword(password);  
        //客户端超时时间单位是毫秒  
        JedisConnectionFactory.setTimeout(timeout);  
        return JedisConnectionFactory; 
    }


	/**
	 * 实例化 RedisTemplate 对象
	 *
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * 设置数据存入 redis 的序列化方式,并开启事务
	 * 
	 * @param redisTemplate
	 * @param factory
	 */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		// 如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to
		// String！
		// 设置value的序列化规则和 key的序列化规则
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //redisTemplate.afterPropertiesSet();
	
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		// 服务层开启事务 取不到 值
		//redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setConnectionFactory(factory);
	}

	/**
     * 注入封装RedisTemplate
    * @Title: redisUtil 
    * @return RedisUtil
    * @autor lpl
    * @date 2017年12月21日
    * @throws
     */
//    @Bean(name = "redisUtil")
//    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
//        RedisUtil redisUtil = new RedisUtil();
//        redisUtil.setRedisTemplate(redisTemplate);
//        return redisUtil;
//    }
}
//--------------------- 
//作者：w奔跑的蜗牛 
//来源：CSDN 
//原文：https://blog.csdn.net/plei_yue/article/details/79362372 
//版权声明：本文为博主原创文章，转载请附上博文链接！