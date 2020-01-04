package com.yyg.sbt.thread;

/**
 * 线程实现
 */
public class ThreadServiceImpl extends Thread {
    public void run()
    {
        for (int i=0; i<5 ; i++)
            System.out.println(Thread.currentThread().getName() + " in control");
    }
}
