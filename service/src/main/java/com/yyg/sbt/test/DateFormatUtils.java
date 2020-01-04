package com.yyg.sbt.test;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @Auther: yyg
 * @Date: 2019/12/18 13:46
 * @Description:
 */
public class DateFormatUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd","yyyy年MM月dd日",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd","yyyy-MM","yyyy","yyyyMM","yyyy/MM"};

    public static boolean parseDate(String string) {
        boolean flag = false;
        if (string == null) {
            return flag;
        }
        try {
            DateUtils.parseDate(string, parsePatterns);
            flag = true;
            return flag;
        } catch (ParseException e) {
            return flag;
        }
    }

    public static void main(String[] args) {
        boolean b = parseDate("-2019-09");
        System.out.println("日期："+b);
    }
}
