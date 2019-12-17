package com.yyg.sbt.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: yyg
 * @Date: 2019/11/28 15:09
 * @Description:  多线程的实现
 */
//@Service
public class WorkRunnable implements Runnable {
    private final CountDownLatch doneSignal;
    private final int i;
    WorkRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }
    @Override
    public void run() {
        //子线程的任务
        try{
            doWork(i);
        }catch (Exception e) {
            e.printStackTrace();
        }
        //任务执行完毕递减锁存器的计数
        //一个线程完成任务就计数减去1
        doneSignal.countDown();
    }

    void doWork(int i) {
        System.out.println("这是第"+(i+1)+"个任务");
    }
}
