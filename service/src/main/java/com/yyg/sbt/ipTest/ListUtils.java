package com.yyg.sbt.ipTest;

import java.util.List;

/**
 * @Auther: yyg
 * @Date: 2019/12/25 09:33
 * @Description:
 */
public class ListUtils {

    public static boolean isNotEmpty(List<? extends InitAble> list){
        if(list.size()==0 || list==null || "".equals(list)){
            return false;
        }
        return true;

    }
}
