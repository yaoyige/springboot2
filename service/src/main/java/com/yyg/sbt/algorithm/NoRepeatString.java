package com.yyg.sbt.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: yyg
 * @Date: 2019/12/19 13:46
 * @Description: 无重复字符的最长子串
 */
public class NoRepeatString {
    /**
     * @Description: 获取最长子串
     * @param string
     * @return: java.lang.String
     * @auther: yyg
     * @date: 2019/12/19 13:48
     */
        public static int getNoRepeatString(String string){
            int n = string.length(), ans = 0;
            Map<Character, Integer> map = new HashMap<>(); // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                if (map.containsKey(string.charAt(j))) {
                    i = Math.max(map.get(string.charAt(j)), i);
                }
                ans = Math.max(ans, j - i + 1);
                map.put(string.charAt(j), j + 1);
            }
            return ans;
        }

    public static void main(String[] args) {
        getNoRepeatString("asd,f。gf。df,dg,a");
        String str = "asd,f。gf。df,dg,a";
        String splite[]=str.split("。|,");
        for(int i=0;i<splite.length;i++){
            System.out.println(splite[i]);
        }

        System.out.println("最长子串："+ getNoRepeatString("asdfgfdfdga"));
    }
        }
