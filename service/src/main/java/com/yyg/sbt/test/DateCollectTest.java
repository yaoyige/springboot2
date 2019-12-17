package com.yyg.sbt.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yyg.sbt.store.domain.Student;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: yyg
 * @Date: 2019/12/11 16:12
 * @Description:
 */
public class DateCollectTest {
    private static void ListSort(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User n1, User n2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                try {
                    Date dt1 = format.parse(n1.getBirthday());
                    Date dt2 = format.parse(n2.getBirthday());
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    public static void main(String[] args) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String id ="20190812";
        Date date = simpleDateFormat.parse(id);
        System.out.println(date);
        System.out.println(simpleDateFormat.format(date));
    }

   /* public static void main(String[] args) {
            List<User> userList = new ArrayList();
            userList.add(new User("吴光雷","男",25,"2019-00-00"));
            userList.add(new User("吴桥","男",22,"2018-01-00"));
            userList.add(new User("陆晓敏","男",25,"2018-02-02"));
            userList.add(new User("王佳俊","男",23,"2018-02"));
            userList.add(new User("秦菁","女",23,""));
            userList.add(new User("秦菁1","女",23,""));

            Sort sort = new Sort();
            Collections.sort(userList,sort);
            for(int i=0;i<userList.size();i++){
                User temp = (User)userList.get(i);
                System.out.println("姓名:"+temp.getName()+",生日:"+temp.getBirthday());
            }
                User user = userList.get(userList.size() - 1);
        System.out.println("最小的姓名:"+user.getName()+",生日:"+user.getBirthday());
        String date = "2019-02";

        if(date.contains("-")){
            String s = "";
            String[] split = date.split("-");
            for(int i=0;i<split.length;i++){
                s=s+split[i];
            }
            s=s.trim();
            date=s;
        }
        System.out.println("date是多少？？？"+date);
    }*/
}
