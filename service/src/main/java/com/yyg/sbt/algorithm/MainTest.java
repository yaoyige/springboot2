package com.yyg.sbt.algorithm;

/**
 * @Auther: yyg
 * @Date: 2019/12/20 14:14
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        String s = "abcdb";
        //String[] bs = s.split("b");
        for(int i=0;i<s.length();i++){
//            System.out.println(bs[i]);
            System.out.println(s.charAt(i));
            System.out.println("截取字符串："+s.substring(0,i));
        }




    }
}
