package com.yyg.sbt.algorithm;

import java.util.regex.Pattern;

/**
 * @Auther: yyg
 * @Date: 2019/12/23 14:21
 * @Description:
 */
public class RegisterTest {
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println("是不是数字字符串:"+isInteger("姚伊歌"));
    }
}
