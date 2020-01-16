package com.yyg.sbt.algorithm;

import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: yyg
 * @Date: 2020/1/13 10:30
 * @Description:  找到最长的回文子串
 */
public class Solution {

    public static String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for(int i=0;i<len;i++){
            for(int j=i+1;j<=len;j++){
                String substring = s.substring(i, j);
                //是回文并且长度是最长的
                if(isPalindromic(substring) && substring.length()>max){
                    ans = s.substring(i,j);
                    max = Math.max(max, ans.length());
                }
            }
        }

        return ans;
    }

    //判断一个字符串是否是回文
    public static boolean isPalindromic(String s){
        int len = s.length();
        for (int i=0;i<len/2;i++){
            if(s.charAt(i)!=s.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }

    //判断一个整数是否是回文

    public static void main(String[] args) {
        String s = "baaad";
        longestPalindrome(s);
        System.out.println(longestPalindrome(s));
    }
}
