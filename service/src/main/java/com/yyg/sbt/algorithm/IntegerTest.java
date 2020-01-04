package com.yyg.sbt.algorithm;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: yyg
 * @Date: 2019/12/19 14:37
 * @Description:
 */
public class IntegerTest {

    public static int reverse(int x){
        String s = Integer.toString(x);
        StringBuilder sb = new StringBuilder();
        if(x<0){
            sb.append(s.charAt(0));
            for(int i=s.length()-1;i>0;i--){
               sb.append(s.charAt(i));
            }
        }else{
            for(int i=s.length()-1;i>=0;i--){
                sb.append(s.charAt(i));
            }
        }
        try {
            int i = Integer.parseInt(sb.toString());
            return i;
        }catch (NumberFormatException e){
            return 0;
        }

    }

    //
    public static int getInteger(int x){
       int sum =  (x/10)%10;
       int sum1 =  (sum*10)+(sum%100);
       return sum1;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int [] nums = new int[10];
        for(int i=0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    if(list.contains(nums1[i])){
                        continue;
                    }
                    list.add(nums1[i]);
                }
            }
        }
        return  list.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
       /* reverse(-765432);
        System.out.println( reverse(1123456789));*/
      // System.out.println(getInteger(12));
       int[] num1 = new int[]{4,5,6,7};
       int[] num2 = new int[]{4,5,8,7};
        int[] intersection = intersection(num1, num2);
        System.out.println("shuzu:[");
        for(int i=0;i<intersection.length;i++){
            System.out.println(intersection[i]);
        }

    }
}
