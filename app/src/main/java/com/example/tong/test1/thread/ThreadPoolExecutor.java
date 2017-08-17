package com.example.tong.test1.thread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.tong.test1.R;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tong on 17-6-5.
 */

public class ThreadPoolExecutor extends AppCompatActivity implements View.OnClickListener {
    private android.widget.Button cachebtnmain;
    private android.widget.Button fixedbtnmain;
    private android.widget.Button scheduledbtnmain;
    private android.widget.Button singlebtnmain;
    private android.widget.Button resetbtnmain;
    private android.widget.ProgressBar pb1, pb2, pb3, pb4, pb5, pb6, pb7, pb8, pb9, pb10;

    private final int corePoolSize = 3;//核心线程数
    private final int maximumPoolSize = 10;//尚未运行的（队列中）最大线程数
    private final int keepAliveTime = 5;//非核心空闲线程到等待的最长时间
    private final TimeUnit unit = TimeUnit.SECONDS;//KeepAliveTime的单位
    private final ThreadFactory threadFactory = new ThreadFactory() {
        AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "Thread：" + integer.getAndIncrement());
        }
    };//创建线程的工厂
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(maximumPoolSize);//用于存储已提交但未执行即等候的任务
    private ExecutorService executorService;
    private ExecutorService fixedExecutorService;
    private Button doublebtnmain,my_thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_pool_executor);
        initView();

        executorService = Executors.newCachedThreadPool(threadFactory);//创建线程池
        fixedExecutorService = Executors.newFixedThreadPool(corePoolSize,threadFactory);//创建固定型线程池
    }

    private void initView() {
        this.pb10 = (ProgressBar) findViewById(R.id.pb10);
        this.pb9 = (ProgressBar) findViewById(R.id.pb9);
        this.pb8 = (ProgressBar) findViewById(R.id.pb8);
        this.pb7 = (ProgressBar) findViewById(R.id.pb7);
        this.pb6 = (ProgressBar) findViewById(R.id.pb6);
        this.pb5 = (ProgressBar) findViewById(R.id.pb5);
        this.pb4 = (ProgressBar) findViewById(R.id.pb4);
        this.pb3 = (ProgressBar) findViewById(R.id.pb3);
        this.pb2 = (ProgressBar) findViewById(R.id.pb2);
        this.pb1 = (ProgressBar) findViewById(R.id.pb1);
        this.resetbtnmain = (Button) findViewById(R.id.reset_btn_main);
        this.singlebtnmain = (Button) findViewById(R.id.single_btn_main);
        this.scheduledbtnmain = (Button) findViewById(R.id.scheduled_btn_main);
        this.fixedbtnmain = (Button) findViewById(R.id.fixed_btn_main);
        this.cachebtnmain = (Button) findViewById(R.id.cache_btn_main);
        this.doublebtnmain = (Button) findViewById(R.id.double_btn_main);
        this.my_thread = (Button) findViewById(R.id.my_thread_pool);

        resetbtnmain.setOnClickListener(this);
        singlebtnmain.setOnClickListener(this);
        scheduledbtnmain.setOnClickListener(this);
        fixedbtnmain.setOnClickListener(this);
        cachebtnmain.setOnClickListener(this);
        doublebtnmain.setOnClickListener(this);
        my_thread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_btn_main:
                break;
            case R.id.single_btn_main:
                break;
            case R.id.scheduled_btn_main:
                break;
            case R.id.fixed_btn_main:
                fixedExecutorService.execute(runnable1);
                fixedExecutorService.execute(runnable2);
                fixedExecutorService.execute(runnable3);
                fixedExecutorService.execute(runnable4);
                fixedExecutorService.execute(runnable5);
                fixedExecutorService.execute(runnable6);
                fixedExecutorService.execute(runnable7);
                fixedExecutorService.execute(runnable8);
                fixedExecutorService.execute(runnable9);
                break;
            case R.id.cache_btn_main:
                executorService.execute(runnable1);
                executorService.execute(runnable2);
                executorService.execute(runnable3);
                executorService.execute(runnable4);
                executorService.execute(runnable5);
                executorService.execute(runnable6);
                executorService.execute(runnable7);
                executorService.execute(runnable8);
                executorService.execute(runnable9);
                //executorService.execute(runnable10);
                break;
            case R.id.double_btn_main:
                //二次下载
                executorService.execute(runnable10);
                break;
            case R.id.my_thread_pool:
                ThreadPoolManager.getInstance().excute(runnable1);
                ThreadPoolManager.getInstance().excute(runnable2);
                ThreadPoolManager.getInstance().excute(runnable3);
                ThreadPoolManager.getInstance().excute(runnable4);
                ThreadPoolManager.getInstance().excute(runnable5);
                ThreadPoolManager.getInstance().excute(runnable6);
                ThreadPoolManager.getInstance().excute(runnable7);
                ThreadPoolManager.getInstance().excute(runnable8);
                ThreadPoolManager.getInstance().excute(runnable9);
                ThreadPoolManager.getInstance().excute(runnable10);
                break;
        }
    }

    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb1"+Thread.currentThread().getName());
                while (pb1.getProgress() < pb1.getMax()) {
                    Thread.sleep(100);
                    pb1.setProgress(pb1.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb2"+Thread.currentThread().getName());
                while (pb2.getProgress() < pb2.getMax()) {
                    Thread.sleep(100);
                    pb2.setProgress(pb2.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb3"+Thread.currentThread().getName());
                while (pb3.getProgress() < pb3.getMax()) {
                    Thread.sleep(100);
                    pb3.setProgress(pb3.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable4 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb4"+Thread.currentThread().getName());
                while (pb4.getProgress() < pb4.getMax()) {
                    Thread.sleep(100);
                    pb4.setProgress(pb4.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable5 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb5"+Thread.currentThread().getName());
                while (pb5.getProgress() < pb5.getMax()) {
                    Thread.sleep(100);
                    pb5.setProgress(pb5.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable6 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb6"+Thread.currentThread().getName());
                while (pb6.getProgress() < pb6.getMax()) {
                    Thread.sleep(100);
                    pb6.setProgress(pb6.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable7 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb7"+Thread.currentThread().getName());
                while (pb7.getProgress() < pb7.getMax()) {
                    Thread.sleep(100);
                    pb7.setProgress(pb7.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable8 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb8"+Thread.currentThread().getName());
                while (pb8.getProgress() < pb8.getMax()) {
                    Thread.sleep(100);
                    pb8.setProgress(pb8.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable9 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb9"+Thread.currentThread().getName());
                while (pb9.getProgress() < pb9.getMax()) {
                    Thread.sleep(100);
                    pb9.setProgress(pb9.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable10 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e("ThreadPoolExecutor", "pb10"+Thread.currentThread().getName());
                while (pb10.getProgress() < pb10.getMax()) {
                    Thread.sleep(100);
                    pb10.setProgress(pb10.getProgress()+5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
