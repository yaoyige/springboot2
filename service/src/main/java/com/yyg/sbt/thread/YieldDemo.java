package com.yyg.sbt.thread;

import com.yyg.sbt.thread.ThreadServiceImpl;

public class YieldDemo {
    public static void main(String[]args) throws Exception {
        ThreadServiceImpl t = new ThreadServiceImpl();
        t.start();

        for (int i=0; i<5; i++)
        {
            // Control passes to child thread
            t.join();
            //Thread.yield();//讲main线程由运行状态到就绪状态
            // After execution of child Thread
            // main thread takes over
            System.out.println(Thread.currentThread().getName()
                    + " in control");
        }
    }
}
