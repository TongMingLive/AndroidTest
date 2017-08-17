package com.example.tong.test1;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by tong- on 2017/5/23.
 */

public class MyContentProvider extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_activity);
        getContentData();
    }

    //获取内容提供者的数据
    private void getContentData(){
        //获取内容的接收者对象
        ContentResolver cr = getContentResolver();
        //获取uri对象
        Uri uri = Uri.parse("content://com.example.tong.englishstudy/BOOKS");
        //查询
        Cursor cursor = cr.query(uri,null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String ID = cursor.getString(cursor.getColumnIndex("ID"));
                String NAME = cursor.getString(cursor.getColumnIndex("NAME"));
                Log.d("MyContentProvider", "ID:" + ID + "\tNAME:" + NAME);
            }
            cursor.close();
        }
    }
}
