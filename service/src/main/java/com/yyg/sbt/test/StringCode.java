package com.yyg.sbt.test;

/**
 * @Auther: yyg
 * @Date: 2019/12/9 14:50
 * @Description: 身份证计算性别和年龄
 */
public class StringCode {

    /**
     * @Description:  根据身份证号计算年龄和性别
     * @param cardCode   身份证号
     * @return: java.lang.String
     * @auther: yyg
     * @date: 2019/12/9 14:51
     */
    public static String getSex(String cardCode){
        String substring = cardCode.substring(16).substring(0,1);
        return substring;
    }

    public static void main(String[] args) {
//        String cardCode ="412724199504292923";
        String cardCode ="411325199610026016";
        System.out.println("返回来的字段是多少？"+getSex(cardCode));
    }
}
