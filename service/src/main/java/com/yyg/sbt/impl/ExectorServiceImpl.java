package com.yyg.sbt.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: yyg
 * @Date: 2019/11/28 15:08
 * @Description:  线程调用
 */
public class ExectorServiceImpl {

    public static void main(String[] args) {
        //线程数
        int num = 10;
        //CountDownLatch是一个同步辅助类也可以使用AtomicInteger替代
        //总共10个线程
        CountDownLatch doneSignal = new CountDownLatch(num);
        //创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        //使用指定的线程池大小 ，可重用线程 在线程使用完成之后需要关闭线程池
        //ExecutorService pool = Executors.newFixedThreadPool(num);
        for(int i=0;i<num;i++)
            //在未来某个时间执行给定的命令
            pool.execute(new WorkRunnable(doneSignal, i));
        try {
            //所有子线程执行完之后才能执行此线程
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //子线程执行完毕，可以开始后续任务处理了
        System.out.println("所有任务执行完毕");
    }
 }