package com.example.tong.test1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tong on 2016/12/5.
 */

public class FragmentImg1 extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("fragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("fragment","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("fragment","onCreateView");
        return inflater.inflate(R.layout.carousel_cun1,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("fragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("fragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("fragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("fragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("fragment","onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("fragment","onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("fragment","onDestroy");
    }
}
