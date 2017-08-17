package com.example.tong.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/11/14.
 */

public class GridViewStudy extends AppCompatActivity {
    private GridView gv;
    private List<GridTest> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);
        gv = (GridView) findViewById(R.id.grid_view_test1);

        list.add(new GridTest(R.mipmap.sony,"索"));
        list.add(new GridTest(R.mipmap.sony,"尼"));
        list.add(new GridTest(R.mipmap.sony,"大"));
        list.add(new GridTest(R.mipmap.sony,"法"));

        myAdapter adapter = new myAdapter();
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewStudy.this,position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class myAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(GridViewStudy.this).inflate(R.layout.grid_item_test1,null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.grid_iv_test1);
            TextView tv = (TextView) convertView.findViewById(R.id.grid_tv_test1);
            iv.setImageResource(list.get(position).getImage());
            tv.setText(list.get(position).getTitle());
            return convertView;
        }
    }

}
