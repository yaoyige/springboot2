package com.yyg.sbt.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.aspectj.weaver.ast.Var;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GuavaServiceImpl {
    /**
     * 利用guava技术实现list的分割
     * @return
     */
    public String getString(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String join = Joiner.on("-").join(list);
        System.out.print("join的输入值："+join);
        return  join;
    }

    /**
     * 根据String返回特定的list
     * @return
     */
    public List<String> getList(){
        String s = "a-b-d-g-f-h-g";
        List<String> strings1 = Splitter.on("-").splitToList(s);
        return strings1;
    }

    /**
     *String转获取map
     * @return
     */
    public Map<String,String> getMap(){
        String str = "xiaoming=11,xiaohong=23";
        Map<String,String> map = Splitter.on(",").withKeyValueSeparator("=").split(str);
        return map;
    }

    /**
     * 使用条件进行过滤
     * @return
     */
    public Iterable<String> getMultiList() {
        ImmutableList<String> immutableList =ImmutableList.of("java","mysql","guava","code","begin");
        Iterable<String> filter = Iterables.filter(immutableList, Predicates.or(Predicates.equalTo("java"), Predicates.equalTo("code")));

        return filter;
    }

}