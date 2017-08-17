package com.example.tong.test1.mysocket;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tong on 17-6-12.
 */

public class ThreadPoolTool {
    private ExecutorService executorService = null;
    private final int coreThreadNum = 3;
    private static ThreadFactory factory = new ThreadFactory() {
        AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "Thread:" + integer.getAndIncrement());
        }
    };

    private static ThreadPoolTool threadPoolTool;

    private ThreadPoolTool() {
    }

    public static ThreadPoolTool getInstance() {
        if (threadPoolTool == null)
            threadPoolTool = new ThreadPoolTool();
        return threadPoolTool;
    }

    //获取线程池对象
    private void getExecutorService() {
        if (executorService == null)
            executorService = Executors.newFixedThreadPool(coreThreadNum, factory);
    }

    //执行
    public void execute(Runnable runnable) {
        getExecutorService();
        if (executorService != null)
            executorService.execute(runnable);
    }
}
