package com.yyg.sbt.ipTest;

/**
 * @Auther: yyg
 * @Date: 2019/12/24 10:20
 * @Description:
 */
public abstract class InitAble {

    /**
     * 初始化
     * @return true 初始化成功
     */
    public abstract boolean init();

    /**
     * 销毁该资源，如关闭连接等
     */
    public abstract void destory();

}
