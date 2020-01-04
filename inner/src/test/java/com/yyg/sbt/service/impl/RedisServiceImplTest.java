package com.yyg.sbt.service.impl;

import com.yyg.sbt.BaseTest;
import com.yyg.sbt.redis.RedisServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashSet;
import java.util.Set;

public class RedisServiceImplTest extends BaseTest {
    @Autowired
    RedisServiceImpl redisService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ValueOperations valueOperations;
    @Test
    public void getRedisExpire() {
        String key = "userName1";
        String value  = "姚伊歌";
        boolean redisExpire = redisService.getRedisExpire(key, value);
        if(redisExpire){
            Long expire = redisTemplate.getExpire(key);
            System.out.println("userName1的过期时间是："+expire);
            Object o = valueOperations.get(key);
            System.out.println("获取key对应的value值："+o);
        }
    }
    @Test
    public void getSetRedis(){
        String key = "key1";
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        redisService.getSetRedis(key,set);
        Object o = redisTemplate.boundSetOps(key);
        System.out.println("获取set集合："+o);
    }
}
