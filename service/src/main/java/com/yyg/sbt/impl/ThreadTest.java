package com.yyg.sbt.impl;

/**
 * @Auther: yyg
 * @Date: 2019/11/29 14:08
 * @Description:
 */
public class ThreadTest {

    public String  threadTest(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "执行完毕";
    }
}
