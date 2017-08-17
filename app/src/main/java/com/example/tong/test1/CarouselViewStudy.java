package com.example.tong.test1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/11/29.
 */

public class CarouselViewStudy extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vp;
    private TextView tv;
    private String[] title = new String[]{"阿尔卑斯湖泊", "超人他爸", "蝙蝠侠", "超人"};
    private List<View> viewList = new ArrayList<>();
    private List<ImageView> imgList = new ArrayList<>();
    private int select_index = 3;//当前选中item的下标 0开始

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel_view);
        vp = (ViewPager) findViewById(R.id.carousel_vp);
        tv = (TextView) findViewById(R.id.carousel_tv);

        tv.setText(title[select_index]);

        LayoutInflater inflater = getLayoutInflater();
        viewList.add(inflater.inflate(R.layout.carousel_cun1, null));
        viewList.add(inflater.inflate(R.layout.carousel_cun2, null));
        viewList.add(inflater.inflate(R.layout.carousel_cun3, null));
        viewList.add(inflater.inflate(R.layout.carousel_cun4, null));
        viewList.add(inflater.inflate(R.layout.carousel_cun1, null));

        imgList.add((ImageView) findViewById(R.id.carousel_dot1));
        imgList.add((ImageView) findViewById(R.id.carousel_dot2));
        imgList.add((ImageView) findViewById(R.id.carousel_dot3));
        imgList.add((ImageView) findViewById(R.id.carousel_dot4));

        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        });

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv.setText(title[position % 4]);
                imgList.get(select_index % 4).setImageResource(R.mipmap.dot_full);
                imgList.get(position % 4).setImageResource(R.mipmap.dot_empty);
                select_index = position;
                if (position == viewList.size()-1) {
                    vp.setCurrentItem(0,false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        imgList.get(0).setOnClickListener(this);
        imgList.get(1).setOnClickListener(this);
        imgList.get(2).setOnClickListener(this);
        imgList.get(3).setOnClickListener(this);

        vp.setCurrentItem(select_index);
        //设置view Page的切换动画
        /*vp.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF,0.0f,TranslateAnimation.RELATIVE_TO_PARENT,0.0f,
                        TranslateAnimation.RELATIVE_TO_SELF,0.0f,TranslateAnimation.RELATIVE_TO_PARENT,1.0f);
                translateAnimation.setDuration(1000);
                page.setAnimation(translateAnimation);
            }
        });*/

        autoScroll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.carousel_dot1:
                vp.setCurrentItem(0);
                break;
            case R.id.carousel_dot2:
                vp.setCurrentItem(1);
                break;
            case R.id.carousel_dot3:
                vp.setCurrentItem(2);
                break;
            case R.id.carousel_dot4:
                vp.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    public void autoScroll() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0x123);
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123){
                //切换至当前item的下一个下标
                int thisIndex = vp.getCurrentItem();
                vp.setCurrentItem(thisIndex+1);
            }
            super.handleMessage(msg);
        }
    };
}