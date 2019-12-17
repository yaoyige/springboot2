package com.yyg.sbt.test;

import com.yyg.sbt.service.StudentService;
import com.yyg.sbt.service.common.util.JSONResult;
import com.yyg.sbt.store.domain.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: yyg
 * @Date: 2019/12/6 13:52
 * @Description:
 */
public class RedisMainTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StudentService studentService;
    /**
     *
     * 功能描述: 用户调用某一个接口的次数限制
     *
     * @param:
     * @return:
     * @auther: yyg
     * @date: 2019/12/6 11:47
     */
    public int getCount(String id,String interfaceId){
        //判断redis中是否存在该用户的id，true存在，false不存在
        if(!redisTemplate.hasKey(id)){//false 不存在
            int count = 1;
            //设置用户对应的接口id是调用的次数1(一个用户可以对应多个接口)
            redisTemplate.opsForHash().put(id,interfaceId,count);
            //设置同一个用户调用同一个接口的时间一个小时不能超过多少次
            redisTemplate.expire(interfaceId,1000, TimeUnit.SECONDS);
            return count;
        }else if(!redisTemplate.opsForHash().hasKey(id,interfaceId)){
            //判断该用户是否存在该接口对应的id
            int count = 1;
            //设置用户对应的接口id是调用的次数1(一个用户可以对应多个接口)
            redisTemplate.opsForHash().put(id,interfaceId,count);
            //设置同一个用户调用同一个接口的时间一个小时不能超过多少次
            redisTemplate.expire(id,1000,TimeUnit.SECONDS);//

        }
        //存在对应的id的话调用次数+1
        int i = Integer.parseInt(String.valueOf(redisTemplate.opsForHash().get(id,interfaceId)));//把object获取的数据转成int类型
        i++;
        //把加过之后的次数放到reids中
        redisTemplate.opsForHash().put(id,interfaceId,i);
        //在获取放到redis中的次数
        return Integer.parseInt(String.valueOf(redisTemplate.opsForHash().get(id,interfaceId)));

    }
    /**
     * @Description:
     * @param id
     * @param interfaceId
     * @return: com.yyg.sbt.service.common.util.JSONResult
     * @auther: yyg
     * @date: 2019/12/6 16:35
     */
    public JSONResult getHashCount(String id, String interfaceId){
            //先判断id是否存在redis中 ,存在
        if(redisTemplate.hasKey(id)){
            //获取id对应的接口集合
            List listId = redisTemplate.opsForList().range(id, 0, -1);
            //判断集合中是否存在这个接口id,并且在redis中存在这个interfaceId
            if((listId.contains(interfaceId))){//存在
                if(redisTemplate.hasKey(interfaceId)) { //reids中存在该interfaceId
                    int i = Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(interfaceId)));//把object获取的数据转成int类型
                    if (i > 5) {
                        //规定时间内访问接口次数过多，限制访问
                        return JSONResult.errorMsg("操作频繁,请扫后重试!");
                    }
                    i++;//没有超过规定次数,继续加
                    //放到redis中并返回true
                    redisTemplate.opsForValue().set(interfaceId, i);
                    return JSONResult.ok();
                }else{//interfaceId中对应的id是过期的
                    //设置首次访问接口的次数(规定时间内)
                    redisTemplate.opsForValue().set(interfaceId,1);
                    //设置过期时间
                    redisTemplate.expire(interfaceId,10,TimeUnit.SECONDS);
                    return JSONResult.ok();//首次设置成功
                }
            }else{//不存在
                //该接口放入放到redis中
                redisTemplate.opsForList().leftPush(id,interfaceId);
                //设置首次访问接口的次数(规定时间内)
                redisTemplate.opsForValue().set(interfaceId,1);
                //设置过期时间
                redisTemplate.expire(interfaceId,10,TimeUnit.SECONDS);
                return JSONResult.ok();//首次设置成功
            }
        }else{//不包含key,此用户没有访问过接口
                redisTemplate.opsForList().leftPush(id,interfaceId);

        }
            return JSONResult.errorMsg("操作频繁,请扫后重试!");
    }

    /**
     * @Description: redis和数据库中的数据进行同步(根据学生的id获取学生的信息)
     * @param studentId  学生Id
     * @return: com.yyg.sbt.service.common.util.JSONResult
     * @auther: yyg
     * @date: 2019/12/9 13:55
     */
    public JSONResult getSqlAndRedis(String studentId){
        //判断id是否为空
        if(StringUtils.isBlank(studentId)){
            return JSONResult.errorMsg("学生Id不能为空");
        }
        //首先判断此学生是否已经存储在redis中
        if (redisTemplate.opsForHash().hasKey("student",studentId)){//存在
            //获取redis中的数据信息
            Student student = (Student)redisTemplate.opsForHash().get("student", studentId);
            if(student!=null) {
                return JSONResult.ok(student);
            }
            return JSONResult.errorMsg("获取的数据不存在");
        }else { //不存在
            /**
             *不存在缓存中
             * 1. 首先从数据库中拿到相对应的学生数据
             * 2. 存放到缓存中并返回给前端
             */
            Student student = studentService.getById(studentId);
            redisTemplate.opsForHash().put("student",studentId,student);
            return JSONResult.ok(student);
        }
    }
}
