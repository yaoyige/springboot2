package com.yyg.sbt.impl;

import sun.applet.Main;

public class ThreadTickServiceImpl extends Thread{
    /**
     *构造方法
     * @param name
     */
    public ThreadTickServiceImpl(String name){
       super(name);//线程名称
    }
    //票数20
    static int ticket = 20;
    //静态锁资源
    static Object ob ="yy";
    @Override
    public void run(){
        while (ticket>0){//如果存在票
            synchronized (ob){//然后对改票进行枷锁
                if(ticket>0){
                    System.out.println(getName()+"出售了第"+ticket+"张票成功");
                    ticket--;
                }else{
                    System.out.println("票出售完毕");
                }
            }
            try {
                sleep(1000);//休息一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程实现
     * @throws InterruptedException
     */
    public static void currentry() throws InterruptedException{
        long l = System.currentTimeMillis();//当前时间
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //需要实现得代码

            }
        });
    }
}
