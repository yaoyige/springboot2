package com.yyg.sbt.store.domain;

import lombok.Data;

/**
 * @Auther: yyg
 * @Date: 2019/12/6 11:45
 * @Description:  封装接口,限制用户调用某一个接口的次数
 */
@Data
public class StuInterfaceCount {
    /**
     *
     * 功能描述:   调用的接口id
     *
     * @param:
     * @return:
     * @auther: yyg
     * @date: 2019/12/6 11:46
     */
    String interfaceId;
    /**
     *
     * 功能描述:  调用接口的次数
     *
     * @param:
     * @return:
     * @auther: yyg
     * @date: 2019/12/6 11:46
     */
    int count;
}
