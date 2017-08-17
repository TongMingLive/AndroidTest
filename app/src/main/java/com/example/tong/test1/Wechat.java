package com.example.tong.test1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/12/7.
 */

public class Wechat extends FragmentActivity implements View.OnClickListener {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private LinearLayout wechat, tongxun, faxian, me;
    private ImageView wechat_iv, tongxun_iv, faxian_iv, me_iv;
    private TextView wechat_tv, tongxun_tv, faxian_tv, me_tv;
    private ViewPager vp;
    private List<Fragment> list = new ArrayList<>();
    private List<LinearLayout> bat = new ArrayList<>();
    //findViewById
    private void initView() {
        vp = (ViewPager) findViewById(R.id.wechat_vp);

        wechat = (LinearLayout) findViewById(R.id.tab_wechat);
        wechat_iv = (ImageView) findViewById(R.id.wechat_iv);
        wechat_tv = (TextView) findViewById(R.id.wechat_tv);

        tongxun = (LinearLayout) findViewById(R.id.tab_tongxun);
        tongxun_iv = (ImageView) findViewById(R.id.tongxun_iv);
        tongxun_tv = (TextView) findViewById(R.id.tongxun_tv);

        faxian = (LinearLayout) findViewById(R.id.tab_faxian);
        faxian_iv = (ImageView) findViewById(R.id.faxian_iv);
        faxian_tv = (TextView) findViewById(R.id.faxian_tv);

        me = (LinearLayout) findViewById(R.id.tab_me);
        me_iv = (ImageView) findViewById(R.id.me_iv);
        me_tv = (TextView) findViewById(R.id.me_tv);

        bat.add(wechat);
        bat.add(tongxun);
        bat.add(faxian);
        bat.add(me);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat_main);
        initView();
        //初始化碎片
        //获取到碎片的管理者对象
        manager = getSupportFragmentManager();
        //将碎片添加到list集合
        list.add(new FragmentImg1());
        list.add(new FragmentImg2());
        list.add(new FragmentImg3());
        list.add(new FragmentImg4());
        //注册监听事件
        initListener();
        vp.setAdapter(new FragmentPagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                //返回碎片
                return list.get(position);
            }

            @Override
            public int getCount() {
                //返回viewPager里面item的个数
                return list.size();
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onClick(bat.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        wechat_iv.setImageResource(R.mipmap.chats_green);
        wechat_tv.setTextColor(Color.rgb(59, 183, 21));
    }

    public void initListener(){
        wechat.setOnClickListener(this);
        tongxun.setOnClickListener(this);
        faxian.setOnClickListener(this);
        me.setOnClickListener(this);
    }
    //初始化底部tab
    public void initBat() {
        wechat_iv.setImageResource(R.mipmap.chats);
        tongxun_iv.setImageResource(R.mipmap.contacts);
        faxian_iv.setImageResource(R.mipmap.discover);
        me_iv.setImageResource(R.mipmap.about_me);
        wechat_tv.setTextColor(Color.GRAY);
        tongxun_tv.setTextColor(Color.GRAY);
        faxian_tv.setTextColor(Color.GRAY);
        me_tv.setTextColor(Color.GRAY);
    }

    @Override
    public void onClick(View v) {
        initBat();
        switch (v.getId()) {
            case R.id.tab_wechat:
                wechat_iv.setImageResource(R.mipmap.chats_green);
                wechat_tv.setTextColor(Color.rgb(59, 183, 21));
                vp.setCurrentItem(0,false);
                break;
            case R.id.tab_tongxun:
                tongxun_iv.setImageResource(R.mipmap.contacts_green);
                tongxun_tv.setTextColor(Color.rgb(59, 183, 21));
                vp.setCurrentItem(1,false);
                break;
            case R.id.tab_faxian:
                faxian_iv.setImageResource(R.mipmap.discover_green);
                faxian_tv.setTextColor(Color.rgb(59, 183, 21));
                vp.setCurrentItem(2,false);
                break;
            case R.id.tab_me:
                me_iv.setImageResource(R.mipmap.about_me_green);
                me_tv.setTextColor(Color.rgb(59, 183, 21));
                vp.setCurrentItem(3,false);
                break;
        }
    }
}
