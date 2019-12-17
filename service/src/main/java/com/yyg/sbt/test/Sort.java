package com.yyg.sbt.test;

/**
 * @Auther: duanjinlong
 * @Date: 2019/12/11 16:22
 * @Description:
 */
import java.util.Comparator;

public class Sort implements Comparator{
    public int compare(Object arg0,Object arg1){
        User user0 = (User)arg0;
        User user1 = (User)arg1;
        int flag = user0.getBirthday().compareTo(user1.getBirthday());
        return flag;
    }
}
