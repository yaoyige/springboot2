package com.yyg.sbt.ipTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: yyg
 * @Date: 2019/12/25 10:32
 * @Description:
 */
public class ThreadPoolExecutorStu {

    // 线程池中初始线程个数
    private final static Integer CORE_POOL_SIZE = 3;
    // 线程池中允许的最大线程数
    private final static Integer MAXIMUM_POOL_SIZE = 8;
    // 当线程数大于初始线程时。终止多余的空闲线程等待新任务的最长时间
    private final static Long KEEP_ALIVE_TIME = 10L;
    // 任务缓存队列 ，即线程数大于初始线程数时先进入队列中等待，此数字可以稍微设置大点，避免线程数超过最大线程数时报错。或者直接用无界队列
    private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<Runnable>(5);

    // 自定义线程池，开发推荐使用
    public static ThreadPoolExecutor ITDragonThreadPoolExecutor() {
        // 构建一个，初始线程数量为3，最大线程数据为8，等待时间10分钟 ，队列长度为5 的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES, WORK_QUEUE);
        return threadPoolExecutor;
    }


}
