package com.bootdo.testDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.sharingcard.redis.util.RedisUtil;

//获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
//让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
@SpringBootConfiguration
@EnableAutoConfiguration
public class EmployeeServiceImplTest {
    @Autowired
    RedisTemplate redisTemplate;
//    @Autowired
//    RedisUtil redisUtil;
    
    @Test
    public void test() {
    	Map a = new HashMap();
    	a.put("key", "value");
    	a.put("key1", "value1");
    	a.put("key2", "value2"); 	
    	List l  =  new ArrayList();
    	l.add("testvalues");
    	a.put("l", l);
        redisTemplate.opsForValue().set("a", a);
        System.out.println( ((List) ((Map)redisTemplate.opsForValue().get("a")).get("l")).get(0));
        
        redisTemplate.opsForValue().set("bf", "20191111",100,TimeUnit.SECONDS);
        //redisTemplate.opsForValue().set("bf", "20191111", 100L);
        System.out.println(redisTemplate.opsForValue().get("bf"));
            
        //RedisUtil redisUtil = new RedisUtil() ;
        //redisUtil.set("b", "bvalue");
//        System.out.println("aaaaaa"+redisUtil.get("b"));
//        
//        String key = "key";
//        System.out.println(redisUtil.get(key));
//        String value = "sdd";
//        redisUtil.set(key, value);
//        System.out.println(redisUtil.get(key));
    }
}
