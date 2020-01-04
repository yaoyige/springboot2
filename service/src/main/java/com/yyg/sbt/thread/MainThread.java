package com.yyg.sbt.thread;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Scanner;

public class MainThread {


    public static void main(String [] args){
       /* Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        for(int i=0;i<s1.length;i++ ){
            if(i==s1.length-1){
                String s2 = s1[i];
                System.out.print("最后一个字符串的长度是："+s2.length());
            }
        }
        System.out.print("输入的数据是多少："+s.length());*/
      /* ThreadTickServiceImpl t1= new ThreadTickServiceImpl("1售票机");
        ThreadTickServiceImpl t2= new ThreadTickServiceImpl("2售票机");
        ThreadTickServiceImpl t3= new ThreadTickServiceImpl("3售票机");
        t1.start();
        t2.start();
        t3.start();*/
        JSONObject obj = new JSONObject();
        obj.put("obj",null);
        obj.put("ok","haha");
        //JSONObject.toJSONString(obj,SerializerFeature.WriteMapNullValue);
        System.out.print(JSONObject.toJSONString(obj,SerializerFeature.WriteMapNullValue));
    }
}
