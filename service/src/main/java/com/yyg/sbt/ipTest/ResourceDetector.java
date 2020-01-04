package com.yyg.sbt.ipTest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: yyg
 * @Date: 2019/12/24 10:19
 * @Description:
 */
public class ResourceDetector {
    /**待检測的资源*/
    private List<? extends InitAble> resources;

    /**创建该对象的线程*/
    private Thread mainThread;

    /**探測结果*/
    private InitAble result;

    /**用于并发探測可用资源的线程池。能够用java的ExecutorService取代*/
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
//    private ThreadPool pool = new ThreadPool(10);

    /**探測失败的记录数*/
    private Integer failCount = 0;

    public ResourceDetector(List<? extends InitAble> resources) {
        super();
        this.resources = resources;
        this.mainThread = Thread.currentThread();
    }

    /**
     * 探測器開始探測可用的资源
     *
     * @author yyg
     * @since 2014年10月29日 下午7:20:21
     */
    public InitAble detect(){
        if(ListUtils.isNotEmpty(resources)){
            for(InitAble i:resources){
                fixedThreadPool.execute(createDetectTask(i));
            }
            synchronized (mainThread) {
                try {
                    mainThread.wait();
                } catch (InterruptedException e) {
                }
            }
            return result;
        } else {
            return null;
        }
    }

    /**创建探測一个资源的子线程*/
    private Runnable createDetectTask(final InitAble i){
        return new Runnable() {
            @Override
            public void run() {
                try{

                    if(i.init()){
                        result = i;
                        synchronized (mainThread) {
                            mainThread.notify();
                        }
                    } else {
                        synchronized (failCount) {
                            if(++failCount == resources.size()){
                                synchronized (mainThread) {
                                    mainThread.notify();
                                }
                            }
                        }
                    }
                } finally {
                    i.destory();
                }
            }
        };
    }
}
