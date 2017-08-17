package com.example.tong.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/12/5.
 */

public class FragmentImg3 extends Fragment {
    private List<View> list = new ArrayList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater(savedInstanceState);
        View v1 = inflater.inflate(R.layout.carousel_cun1, null);
        View v2 = inflater.inflate(R.layout.carousel_cun2, null);
        View v3 = inflater.inflate(R.layout.carousel_cun4, null);
        //list.clear();
        list.add(v1);
        list.add(v2);
        list.add(v3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.carousel_cun3, null);
        ViewPager vp = (ViewPager) v.findViewById(R.id.cun_vp);
        vp.setAdapter(new PagerAdapter() {
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
                container.addView(list.get(position));
                return list.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
