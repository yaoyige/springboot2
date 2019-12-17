package com.yyg.sbt.test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Auther: yyg
 * @Date: 2019/12/4 16:40
 * @Description:
 */
public class ArrayListTest {

    public static void getList(ArrayList<String> arrayList){
        arrayList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                if(s.length()>3)
                    System.out.println(s);
            }
        });
    }

    public static void getLamdaList(ArrayList<String> arrayList){
        arrayList.forEach(s -> {
            if(s.length()>=3) {
                System.out.println(s);
            }
        });
    }

    public static void removeList(ArrayList<String> arrayList){
        arrayList.removeIf(new Predicate<String>(){
            @Override
            public boolean test(String s) {
                return s.length()>3;
            }
        });
    }
    /**
     *
     * 功能描述: 
     *
     * @param: 获取map中的所有键值对信息
     * @return: 
     * @auther: yyg
     * @date: 2019/12/4 17:03
     */
    public static void getMap(HashMap<Integer, String> map){
        map.forEach(new BiConsumer<Integer, String>(){
            @Override
            public void accept(Integer k, String v){
                System.out.println(k + "=" + v);
            }
        });
        //判断给定的key是否存在map中，如果存在返回key对应的value值，如果不存在则返回 "NoValue"
        map.getOrDefault(4, "NoValue");
        map.getOrDefault(4, "不存在");
    }

/**
      * 8位日期(对属于YYYYMMDD不规则的处理)
      *
      * @param date
      * @return
      */
public static String get8VaildDate(String date) {
         String newDate = "";
          if(date.length() == 4){
             newDate = date.substring(0, 4) + "0000";
          }
            else if (date.length() == 5) {
                newDate = date.substring(0, 4) + "0" + date.substring(4) + "00";
             }
             else if (date.length() == 6) {
                 //判断第5个数字
                 if("1".equals(date.substring(4,5))){
                     //判断第6个数字
                     if(Integer.parseInt(date.substring(5,6))>2){
                         newDate = date.substring(0, 4) + "0" + date.substring(4,5) +"0"+date.substring(5,6);
                     }else{
                         newDate = date+"00";
                     }
                 }else{
                     newDate = date.substring(0, 4) + "0" + date.substring(4,5) +"0"+date.substring(5,6);
                 }
             }
             else if (date.length() == 7) {
             if ("1".equals(date.substring(4, 5))) {//2018119
                newDate = date.substring(0, 6) + "0" + date.substring(6);
                } else if("0".equals(date.substring(4, 5))){//2018017
                 newDate = date.substring(0, 6) + "0" + date.substring(6,7);
                 }
             }
             else if (date.length() == 8) {
                newDate = date;
             } else {
                 newDate = date.substring(0, 8);
             }
             return newDate;
 }

/**
 *
 * 功能描述: 
 *
 * @param: 测试list的新街口
 * @return: 
 * @auther: yyg
 * @date: 2019/12/4 16:42
 */
    public static void main(String[] args) {
      /*  ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        getList(list);
        getLamdaList(list);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        getMap(map);

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        //月份从0开始计算
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        String  s = "20181112";
        String s1 = "2018";
        String s2 = s1+"0000";
        System.out.println("长度"+s1.length());
        System.out.println("s2:"+s2);
        System.out.println(s.substring(0,4)+"-"+s.substring(4,6)+"-"+s.substring(6,8));
        System.out.println("当年是哪一年？："+year+"-"+month+"-"+date);*/
        String data = "2018017";
    /*    String data1 = "20180";
        String data2 = "201801";
        String data3 = "20181";
        String data4 = "2018100";
        String data5 = "20181011";
        String data6 = "2018102";
        String data7 = "2018103734567";
        String data8 = "201819";
        String data9 = "201890";
        String data10 = "201812";
        String data11 = "201811";
        System.out.println("输出的时间格式："+ get8VaildDate(data));
        System.out.println("输出的时间格式1："+ get8VaildDate(data1));
        System.out.println("输出的时间格式2："+ get8VaildDate(data2));
        System.out.println("输出的时间格式3："+ get8VaildDate(data3));
        System.out.println("输出的时间格式4："+ get8VaildDate(data4));
        System.out.println("输出的时间格式5："+ get8VaildDate(data5));
        System.out.println("输出的时间格式6："+ get8VaildDate(data6));
        System.out.println("输出的时间格式7："+ get8VaildDate(data7));
        System.out.println("输出的时间格式8："+ get8VaildDate(data8));
        System.out.println("输出的时间格式9："+ get8VaildDate(data9));
        System.out.println("输出的时间格式10："+ get8VaildDate(data10));*/
        System.out.println("输出的时间格式11："+ get8VaildDate(data));
        String s = "201819";
        System.out.println("测试："+s.substring(4,5));
        System.out.println("测试："+s.substring(5,6));
    }
}
