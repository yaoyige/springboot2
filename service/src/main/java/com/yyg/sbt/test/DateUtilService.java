package com.yyg.sbt.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: yyg
 * @Date: 2019/12/13 17:49
 * @Description:
 */
@Slf4j
public class DateUtilService {
    /**
     * @Description:  判断一个字符串是时间戳还是时间，并转换成yyyyMMdd的格式存放
     * @param date
     * @return: java.lang.String
     * @auther: yyg
     * @date: 2019/12/13 17:49
     */
    public static int getDate(String date) {
        int i=0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date1 = simpleDateFormat.parse(date);
            date1.getTime();
            return i;
        }catch (Exception e){
            System.out.println("是时间戳");
             i =1;
             return i;
           // ts = new Long(date);
          //  return ts;
        }
    }

    private static String[] parsePatterns = {"yyyy-MM-dd","yyyy年MM月dd日",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"};

    public static Date parseDate(String string) {
        if (string == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(string, parsePatterns);
        } catch (ParseException e) {
            System.out.println("传过来的字符串不是符合时间的："+string);
            return null;
        }
    }

    public static void main(String[] args) {
        String date = "1575946475000";
        String date1 ="20190000";
        System.out.println("获取的时间是什么？？"+getDate(date1));
        String string = "43240505";
        System.out.println("输出的字符串是什么:"+ parseDate(string));
    }
}
