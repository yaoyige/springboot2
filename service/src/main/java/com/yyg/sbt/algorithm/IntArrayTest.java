package com.yyg.sbt.algorithm;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Auther: yyg
 * @Date: 2019/12/20 10:53
 * @Description: 两个有序数组的中位数
 */
public class IntArrayTest {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double dub;
        int[] ints;
        if(nums1.length<=0 || nums1.equals(null) || "".equals(nums1)){
            ints = nums2;
        }else if(nums2.length<=0 || nums2.equals(null) || "".equals(nums2)){
            ints = nums1;
        }
        else if(nums1[(nums1.length)-1]<nums2[0]) {
            //第一个数组的最后一个下标小于第二个数组的第一个小标
            //建立c数组，并将a添加进去
            ints= Arrays.copyOf(nums1, nums1.length+nums2.length);
            //将b数组添加到已经含有a数组的c数组中去
            System.arraycopy(nums2, 0, ints, nums1.length, nums2.length);
          //  ints = ArrayUtils.addAll(nums1, nums2);
        }else if(nums2[(nums2.length)-1]<nums1[0]){
            ints= Arrays.copyOf(nums2, nums1.length+nums2.length);
            //将b数组添加到已经含有a数组的c数组中去
            System.arraycopy(nums1, 0, ints, nums2.length, nums1.length);
//            ints = ArrayUtils.addAll(nums2, nums1);
        }else{
//            ints = ArrayUtils.addAll(nums1, nums2);
            ints= Arrays.copyOf(nums2, nums1.length+nums2.length);
            //将b数组添加到已经含有a数组的c数组中去
            System.arraycopy(nums1, 0, ints, nums2.length, nums1.length);
            Arrays.sort(ints);
        }
            if((ints.length)%2==0){//偶数
                int x = (ints.length)/2;
                int y = x-1;
               /* int a  = ints[x];
                int b = ints[y];*/
                double c =ints[x]+ints[y];
                dub = c/2;
            }else{//奇数
                int x = (ints.length)/2;
                dub = ints[x];
            }
            return dub;
        }




    public static void main(String[] args) {
        //判断两个数组之间的中位数是多少
        int[] arr = new int[]{};
        int[] arr1 = new int[]{2,4};
        double medianSortedArrays = findMedianSortedArrays(arr, arr1);
        System.out.println("和：：：："+medianSortedArrays);
        int[] ints = ArrayUtils.addAll(arr, arr1);
        for(int i=0;i<ints.length;i++){
            System.out.print("-"+ints[i]);
        }
        System.out.println("数组的中间下标是多少？？"+(arr.length+arr1.length));

    }
}
