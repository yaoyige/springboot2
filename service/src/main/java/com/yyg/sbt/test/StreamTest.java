package com.yyg.sbt.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @Auther: yyg
 * @Date: 2019/12/16 16:05
 * @Description:
 */
public class StreamTest {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("张三","1",20,"19950429"));
        list.add(new User("李四","1",19,"19970505"));
        list.add(new User("王五","1",22,"19930529"));
        list.add(new User("赵六","1",16,"19980623"));
        list.add(new User("熊七","1",31,"19901002"));
      /*  list.stream().sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        }).forEach(user -> System.out.println(user.getBirthday()));
        */
        list.stream().sorted((user1,user2)->user1.getBirthday().compareTo(user2.getBirthday())).forEach(user -> System.out.println(user.getBirthday()));

        list.stream().filter(user -> user.getAge()<20).sorted((user1,user2)->user1.getBirthday().compareTo(user2.getBirthday())).forEach(user -> System.out.println(user.getBirthday()));
        list.forEach(System.out::println);
    }

}
