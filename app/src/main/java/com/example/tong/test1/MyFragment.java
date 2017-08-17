package com.example.tong.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tong on 2016/12/2.
 */

public class MyFragment extends Fragment {
    //创建Fragment对象，初始化成员变量
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //绘制ui界面，findViewById、注册监听事件
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //解析布局文件
        View v = inflater.inflate(R.layout.edit_text,null);
        Button button = (Button) v.findViewById(R.id.button1);
        Button back = (Button)v.findViewById(R.id.edit_text_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"此功能待开发，敬请期待！",Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                /*startActivity(new Intent(getActivity(),ActivityMain.class));*/
            }
        });
        //返回view对象
        return v;
    }
    //暂停，保存用户状态
    @Override
    public void onPause() {
        super.onPause();
    }
}
