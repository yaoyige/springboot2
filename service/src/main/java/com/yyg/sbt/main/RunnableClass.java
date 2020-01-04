package com.yyg.sbt.main;

/**
 * @Auther: yyg
 * @Date: 2019/12/25 13:43
 * @Description:
 */
public class RunnableClass  implements Runnable{
    public int i;

    public RunnableClass(int i){
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+"正在执行");
    }
}
