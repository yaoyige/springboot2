package com.yyg.sbt.impl;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

/**
 * @Auther: yyg
 * @Date: 2019/12/23 10:13
 * @Description:
 */
@Order(value = 1)
public class ShowModelService implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("需要启动之后执行的代码段。。。");
    }
}
