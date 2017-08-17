package com.example.tong.test1.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tong on 17-6-8.
 */

public class ThreadPoolManager {
    //单例模式
    private static ThreadPoolManager threadPoolManager;
    private ThreadPoolManager(){}

    public static ThreadPoolManager getInstance(){
        if (threadPoolManager == null)
            threadPoolManager = new ThreadPoolManager();
        return threadPoolManager;
    }

    //核心线程数
    private final int coreThreadNum = 5;
    //非核心线程数
    private final int threadNum = 128;
    //非核心线程的空闲线程等待时间
    private final long threadTimeout = 15L;
    //非核心线程的空闲线程等待时间单位
    private final TimeUnit unit = TimeUnit.SECONDS;
    //储存非核心线程的队列
    private BlockingQueue<Runnable> blocking = new ArrayBlockingQueue<Runnable>(threadNum);
    //生产线程的工厂
    private static ThreadFactory factory = new ThreadFactory() {
        AtomicInteger integer = new AtomicInteger();
        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r,"生产线程的编号："+integer.getAndIncrement());
        }
    };
    //线程池对象
    private ExecutorService executorService;
    //初始化线程池
    private void initThreadPool(){
        if (executorService == null)
            executorService = new ThreadPoolExecutor(coreThreadNum,threadNum,threadTimeout,unit,blocking,factory);
    }

    //开始任务
    public void excute(Runnable runnable){
        if (executorService == null)
            initThreadPool();
        executorService.execute(runnable);
    }

    //关闭线程池
    public void close(){
        if (executorService != null)
            executorService.shutdown();
    }
}
