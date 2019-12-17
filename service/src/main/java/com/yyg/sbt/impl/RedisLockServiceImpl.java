package com.yyg.sbt.impl;

import com.yyg.sbt.service.RedisService;
import com.yyg.sbt.service.common.util.SpringContextUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 简单的redis分布式锁
 */
@Service
public class RedisLockServiceImpl {

    private static final int defaultExpire = 60;

    /**
     * 加锁
     * @param key 键
     * @param expireTime 超时时间
     * @return true 加锁成功 false 枷锁失败
     */
    public static  boolean lockUtilOne(String key , int expireTime){
        RedisTemplate redisTemplate = SpringContextUtil.getBean(RedisTemplate.class);
      /*  Jedis jedis = new Jedis();
        jedis.setnx();*/
        //RedisService redisService = SpringContextUtil.getBean(RedisService.class);
        //设置值
        redisTemplate.opsForValue().set(key,System.currentTimeMillis()+expireTime);
        //设置过期时间
        redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);//设置过期时间
        //获取key
        redisTemplate.opsForValue().get(key);
        //获取原先的值并设置现在的值
        redisTemplate.opsForValue().getAndSet(key,System.currentTimeMillis()+expireTime);
        //redisTemplate.set(key,System.currentTimeMillis()+expireTime);
        return true;
    }

    /**
     * 添加锁第二种方式
     * @param key 枷锁的key
     * @param expireTime 过期时间
     * @return boolean
     */
    public static boolean lockUtilTwo(String key,int expireTime){
        Jedis jedis = SpringContextUtil.getBean(Jedis.class);
        long value = System.currentTimeMillis()+expireTime;//自动类型提升
        //设置key的过期时间  1  设置成功   0 设置失败
        Long setnx = jedis.setnx(key, String.valueOf(value));
        if(setnx==1){ //设置成功,获取锁成功
            //设置锁的过期时间
            return true;
        }
        //获取原先的过期时间
        long oldExpireTime = Long.parseLong(jedis.get(key));
        if(oldExpireTime<System.currentTimeMillis()){//设置的过期时间<当前时间
          //超时 1. 设置新的过期时间
            Long newValue = System.currentTimeMillis()+expireTime;
            Long currentExpireTime = Long.parseLong(jedis.getSet(key, String.valueOf(newValue)));
            if(currentExpireTime == oldExpireTime){//设置新的过期时间成功
                return true;
            }
        }
        return false;
    }

    /**
     * 过期时间没有超时就直接del
     * @param key key值
     */
    public static void unlockTwo(String key){
        Jedis jedis = SpringContextUtil.getBean(Jedis.class);
        long oldTime = Long.parseLong(jedis.get(key));
        if(oldTime>System.currentTimeMillis()){
            //没有过期
            jedis.del(key);
        }
    }

    /**
     * 调用redis的分布式锁
     * @param userId key
     */
    public void drawLock(String userId){
        String key = "draw.redpacket.userid:"+userId;
        boolean lock = lockUtilTwo(key, defaultExpire);
        if(lock) {
           try{
            //进行操作
           }finally {//释放锁
               unlockTwo(key);
            }
        }else{//没有拿到锁
          new RuntimeException("重复操作");
        }
    }
}
