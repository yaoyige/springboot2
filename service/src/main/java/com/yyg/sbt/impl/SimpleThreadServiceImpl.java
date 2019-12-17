package com.yyg.sbt.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 简单的java多线程介绍练习
 */
public class SimpleThreadServiceImpl {
    //默认的hashmap的初始容量   16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    //最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;
    //默认的加载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
   //使用红黑树的时  链表长度为 2-8  超过8就是使用红黑树结构
    static final int TREEIFY_THRESHOLD = 8;
    //
    static final int UNTREEIFY_THRESHOLD = 6;
    //存放箱子的最小容量
    static final int MIN_TREEIFY_CAPACITY = 64;
}
