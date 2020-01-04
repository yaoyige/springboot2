package com.yyg.sbt.thread;
//import com.joy.error.BusinessException;
//import com.spider.utils.InnoErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Auther: yyg
 * @Date: 2019/11/29 10:14
 * @Description:
 */
@Slf4j
public class IPThreadService implements Callable {

    public IPThreadService() {
    }

    @Override
    public Map<String, String> call() throws Exception {
        // 此处是线程要处理的业务代码，此处实现的是对count变量加1的操作
        ThreadTest threadTest = new ThreadTest();
//        HTTPRequest httpRequest = new HTTPRequest(HTTPMethod.GET,"http://www.jsnt.lss.gov.cn:1002/query/");
        Map<String, String> stringStringMap = null;
        String s = null;
        String code = "utf-8";
        try {
//            stringStringMap = threadTest.getData("http://www.jsnt.lss.gov.cn:1002/query/", code);
             s = threadTest.threadTest();
        } catch (Exception e) {
            System.out.println("执行错误。。。");
//            throw new BusinessException(InnoErrorCode.OFFICIAL_ERROR_MESSAGE, "C04_网络走丢了，请重新操作！");
            throw new Exception("hahh");
        }
        Thread.sleep(1000);
        return stringStringMap;
    }

    /**
     *
     * 功能描述: 
     *
     * @param: 获取数据不会出现线程问题，所以不用枷锁
     * @return: 
     * @auther: yyg
     * @date: 2019/12/6 10:53
     */
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        // 创建线程实例
        IPThreadService iPThread = new IPThreadService();
        // 创建一个大小为10的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //存放future的集合
        List<Future<String>> list = new ArrayList<>();
        //执行次数
        for (int i = 0; i < 50; i++) {
            // 将线程提交到线程池执行
            Future<String> future = executor.submit(iPThread);
            list.add(future);
        }
        //线程池关闭
        executor.shutdown();
        //线程运行完成
        int i=1;
        for (Future< String> future : list) {
            while (true) {
                if (future.isDone() && !future.isCancelled()) {
                    //System.out.println(Thread.currentThread().getName() + "正在获取数据。。。");
                    //调用get方法获取返回值
                    String stringStringMap = null;
                    try {
                        stringStringMap = future.get();
                    } catch (Exception e) {
                        log.info("第"+i+"次开始抓取时异常" + "网络走丢,继续下一次循环" + e);
                        // i++;
                        // throw new BusinessException(InnoErrorCode.OFFICIAL_ERROR_MESSAGE, "C04_网络走丢了，请重新操作！");
                        break;
                    }
                    System.out.println("执行完毕");
//                    String code = stringStringMap.get("state");
//                    if (!"200".equals(code)) {
//                        System.out.println("第"+i+"次code码不是200,返回内容如下:" + stringStringMap.get("state"));
//                    }
//                    System.out.println("第"+i+"次的code码值是:" + code);
                    break;
                }else {
                    try {
                        Thread.sleep(1);//每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU---》新手别忘记这个
                    }catch (Exception e){
                        System.out.println("cpu轮询休息出错。。。"+e);
                    }
                }
            }
            i++;
        }
        System.out.println(Thread.currentThread().getName() + "执行时间：" + (System.currentTimeMillis() - l));
    }
}
