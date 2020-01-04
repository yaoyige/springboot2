package com.yyg.sbt.impl;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

/**
 * @Auther: yyg
 * @Date: 2019/12/23 10:21
 * @Description:
 */
@Order(value = 2)
public class ShowModelService2 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("执行顺序2.。。");

    }
}
