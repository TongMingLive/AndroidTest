package com.example.tong.test1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/11/25.
 */

public class ViewPagerStudy extends AppCompatActivity {
    private ViewPager vp;
    private PagerTabStrip title;
    private String[] titles = new String[]{"推荐", "热点", "南昌"};
    private List<View> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        vp = (ViewPager) findViewById(R.id.view_pager_vp);
        title = (PagerTabStrip) findViewById(R.id.view_pager_title);

        title.setTextColor(Color.RED);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        title.setTabIndicatorColor(0x000000);//设置指示器颜色

        //获取布局解析器对象
        LayoutInflater inflater = getLayoutInflater();
        //解析布局
        View v1 = inflater.inflate(R.layout.view_pager_view1, null);
        View v2 = inflater.inflate(R.layout.view_pager_view2, null);
        View v3 = inflater.inflate(R.layout.view_pager_view3, null);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        //设置关联适配器
        myAdapter adapter = new myAdapter();
        vp.setAdapter(adapter);
        //viewpager page改变的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //position是当前页面选中的下标
                Toast.makeText(ViewPagerStudy.this,position+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class myAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //把目标View添加到容器里面
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从容器中移除上一个View对象
            container.removeView(list.get(position));
            //super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public void view_pager_in(View v) {
        Toast.makeText(ViewPagerStudy.this, "欢迎进入", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(ViewPagerStudy.this, ActivityMain.class));
    }
}
