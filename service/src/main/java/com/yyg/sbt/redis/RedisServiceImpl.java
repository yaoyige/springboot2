package com.yyg.sbt.redis;

import com.yyg.sbt.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 测试redis
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ValueOperations valueOperations;

    public  boolean getRedisExpire(String key,Object value){
        //往redis中添加数据
        valueOperations.set(key,value);
        Boolean expire = redisTemplate.expire(key, 1000, TimeUnit.MINUTES);
        return expire;
    }

    /**
     * set集合
     * @param key
     * @param value
     */
    public  void getSetRedis(String key,Object value){
       stringRedisTemplate.opsForSet().add(key,"1","2","3","4");
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add(key,"1");
    }
}
