package com.bootdo.testDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import com.sharingcard.redis.util.RedisUtil;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
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
        redisTemplate.opsForValue().set("map", a);
        redisTemplate.opsForValue().set("a", "b");
        System.out.println(redisTemplate.opsForValue().get("a"));
        System.out.println( ((List) ((Map)redisTemplate.opsForValue().get("map")).get("l")).get(0));
//        redisUtil.set("b", "a");
//        System.out.println(redisUtil.get("b"));
        
    }

    ;
}
