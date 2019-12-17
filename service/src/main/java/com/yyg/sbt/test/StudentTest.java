package com.yyg.sbt.test;

import com.yyg.sbt.service.common.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: yyg
 * @Date: 2019/12/6 11:16
 * @Description:  防止用户调用接口频繁
 */
public class StudentTest {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     *
     * 功能描述: 防止用户调用接口频繁
     *
     * @param:
     * @return: 
     * @auther: yyg
     * @date: 2019/12/6 11:17
     */
    public int getCount(String id){
        //判断redis中是否存在该用户的id，true存在，false不存在
        if(!redisTemplate.hasKey(id)){//false 不存在
            //用户调用接口对应的次数
            int count = 1;
            redisTemplate.opsForValue().set(id,count);//设置该用户调用接口的次数为1，并设置过期时间
            //设置同一个用户调用同一个接口的时间一个小时不能超过多少次
            redisTemplate.expire(id,10, TimeUnit.SECONDS);
            return count;
        }
        //存在对应的id的话调用次数+1
        int i = Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(id)));//把object获取的数据转成int类型
        i++;
        //把加过之后的次数放到reids中
        redisTemplate.opsForValue().set(id,i);
        //在获取放到redis中的次数
        return Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(id)));
    }

    /**
     *
     * 功能描述:  获取的数据是否大于规定的次数
     *
     * @param:
     * @return:
     * @auther: yyg
     * @date: 2019/12/6 11:35
     */
    public boolean getCountBoolean(String id){
        int count = getCount(id);
        if(count>5){
            return false;
        }
        return true;
    }
    /**
     *
     * 功能描述: 调用接口前的判断
     *
     * @param:
     * @return:
     * @auther: yyg
     * @date: 2019/12/6 11:38
     */
    public JSONResult get(){
        String id = "";  //获取前端传递过来的用户的id
        if(getCountBoolean(id)){ //为true，可以访问，为false不能
            // 调用用户访问的接口
            Object data =null;
            //访问成功返回前端需要的数据
            return JSONResult.ok(data);
        }
        return JSONResult.errorMsg("不好意思,操作过于频繁请稍后再试!");
    }
}
