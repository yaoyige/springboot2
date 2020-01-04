package com.yyg.sbt.ipTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yyg
 * @Date: 2019/12/25 09:41
 * @Description:
 */
public class MyTest {

    public static void main(String[] args) {
        List<InitAble> list = new ArrayList<>();
        InitAble initAble = new InitAble() {
            @Override
            public boolean init() {
                return true;
            }

            @Override
            public void destory() {

            }
        };
        list.add(initAble);
        ResourceDetector resourceDetector = new ResourceDetector(list);
        InitAble detect = resourceDetector.detect();
        System.out.println("探测结果:"+detect);

    }
}
