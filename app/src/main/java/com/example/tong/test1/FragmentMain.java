package com.example.tong.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Tong on 2016/12/2.
 */

public class FragmentMain extends FragmentActivity {
    //Fragment管理者
    private FragmentManager manager;
    //Fragment事务处理
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        //获取Fragment管理者对象
        manager = getSupportFragmentManager();
        //获取事务对象
        transaction = manager.beginTransaction();
        /*向事务里面添加碎片，里面有两个参数，分别为：
        * 1、表示Fragment（碎片）显示到哪个容器组件上面（哪个布局控件里面）
        * 2、你要显示的Fragment（碎片）*/
        transaction.add(R.id.fragment_img,new FragmentImg1());
        transaction.add(R.id.fragment_content,new MyFragment());
        //提交事务
        transaction.commit();
    }
}
