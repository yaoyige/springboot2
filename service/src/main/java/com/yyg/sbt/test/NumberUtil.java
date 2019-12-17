package com.yyg.sbt.test;

import java.math.BigDecimal;

/**
 * @Auther: yyg
 * @Date: 2019/12/9 15:57
 * @Description:  小数格式规整和转换
 */
public class NumberUtil {
    public BigDecimal getNumber(BigDecimal bigDecimal){
        //与0进行比较
        bigDecimal.compareTo(BigDecimal.ZERO);
        //保留小数点后两位数字
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
        return bigDecimal;
    }
}
