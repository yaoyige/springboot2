package com.yyg.sbt.test;

/**
 * @Auther: yyg
 * @Date: 2019/12/18 18:31
 * @Description:  计算两个数组和与目标数组一致的下标
 */
public class SolutionTest {
    public static int[] getIndex(int[] nums,int target){
        int remainder = 0;
        int[] indexs = new int[2];
        for(int i=0;i<nums.length;i++){
            remainder  = target-nums[i];
            for(int j=i+1;j<nums.length;j++){
              if(nums[j]==remainder){
                 //添加到数组中去
                  indexs[0]=i;
                  indexs[1]=j;
                  return  indexs ;
              }
            }
        }
         return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        int target = 6;
        int[] index = getIndex(nums, target);
        System.out.println("["+index[0]+","+index[1]+"]");
    }
}
