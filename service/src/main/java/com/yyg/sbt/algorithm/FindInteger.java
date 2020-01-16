package com.yyg.sbt.algorithm;

/**
 * @Auther: yyg
 * @Date: 2020/1/16 10:36
 * @Description:  字符串转换成整数
 */
public class FindInteger {

    public static int myAtoi(String str) {
        str = str.trim();
        if (str == null || str.length() == 0)
            return 0;

        char firstChar = str.charAt(0);
        int sign = 1; //是正数还是负数
        int start = 0; //开始
        long res = 0; //初始化的整数值
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }

        for (int i = start; i < str.length(); i++) {
            //判断字符是否为数字
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i)-'0'; //str.charAt(i)-'0' 字符串转成整数
            if (sign == 1 && res > Integer.MAX_VALUE) //当前整数不超过最大的整数值
                return Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }

    public static void main(String[] args) {
        String s = "4193 with words";
        System.out.println("输出的整数:"+ myAtoi(s));
    }

}
