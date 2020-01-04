package com.yyg.sbt.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: yyg
 * @Date: 2019/12/19 16:49
 * @Description:
 */
public class ListIntegerTest {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();

        int target = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<0){
                target = 0-(nums[i]);
            }else{
                target = 0-nums[i];
            }
            for(int j=i+1;j<nums.length-1;j++){
                if((nums[j]+nums[j+1])==target){
                    List<Integer> list = new ArrayList<>();
                    list.add( nums[i]);
                    list.add( nums[j]);
                    list.add( nums[j+1]);
                    if(lists.size()<=0){
                        lists.add(list);
                        continue;
                    }
                    for(List<Integer> list1 : lists){
                        if(list1.containsAll(list)){
                            continue;
                        }else{
                            lists.add(list);
                        }
                    }
                }

            }

        }
        return lists;
    }

    public static void main(String[] args) {
       int[] num =new int[]{-1,0,1,0};
        System.out.println(threeSum(num).size());
    }

}
