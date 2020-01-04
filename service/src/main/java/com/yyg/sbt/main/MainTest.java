package com.yyg.sbt.main;

import com.yyg.sbt.ipTest.ThreadPoolExecutorStu;
import com.yyg.sbt.store.domain.Student;
import com.yyg.sbt.thread.IPThreadService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: yyg
 * @Date: 2019/12/25 11:14
 * @Description:
 */
public class MainTest {
    /*public static void main(String[] args) {
        String[] s = new String[10];
        System.out.println("长度:"+s.length+"数据:"+s[9]);

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorStu.ITDragonThreadPoolExecutor();
        for(int i=0;i<=10;i++){
            threadPoolExecutor.execute(new RunnableClass(i));
            System.out.println("正在习性的是："+i);
        }


    }*/

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        // 创建线程实例
       CallableClass callableClass = new CallableClass("张三");
        // 创建一个大小为10的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //存放future的集合
        List<Future<Integer>> list = new ArrayList<>();
        //执行次数
        for (int i = 0; i < 10; i++) {
            // 将线程提交到线程池执行
            Future<Integer> future = executor.submit(callableClass);
            list.add(future);
        }
        //线程池关闭
        executor.shutdown();
        //线程运行完成
        int i=1;
        for ( Future<Integer> future : list) {
            while (true) {
                if (future.isDone() && !future.isCancelled()) {
                    Integer integer = 0;

                    try {
                        integer = future.get();
                             System.out.println("第"+i+"次姓名："+integer);
                    } catch (Exception e) {
                        break;
                    }
                    break;
                }else {
                    try {
                        Thread.sleep(1);//每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU---》新手别忘记这个
                    }catch (Exception e){
                        System.out.println("cpu轮询休息出错。。。"+e);
                    }
                }
            }
            i++;
        }
        System.out.println(Thread.currentThread().getName() + "执行时间：" + (System.currentTimeMillis() - l));
    }
}
