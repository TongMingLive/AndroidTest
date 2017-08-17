package com.example.tong.test1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Tong on 2016/10/12.
 */

public class PhotoBrowse extends AppCompatActivity {

    private TextView tv;
    int i = 0;
    List<Integer> photoList = new ArrayList<Integer>();
    void addPhotoList(){
        photoList.add(R.mipmap.imga);
        photoList.add(R.mipmap.imgb);
        photoList.add(R.mipmap.imgc);
        photoList.add(R.mipmap.imgd);
    }

    //自定义toast显示时间方法
    public static void showToast(final Activity activity, final String word, final long time){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_browse);
        tv = (TextView) findViewById(R.id.tv_photo);
        addPhotoList();
    }

    public void btn_photo(View v){
        switch (v.getId()){
            case R.id.previousPhoto:
                i--;
                if(i >= 0){
                    tv.setBackgroundResource(photoList.get(i));
                }else{
                    showToast(this,"没有上一张了！",500);
                    i = 0;
                }
                break;
            case R.id.nextPhoto:
                i++;
                if(i <= photoList.size()-1){
                    tv.setBackgroundResource(photoList.get(i));
                }else{
                    showToast(this,"已经是最后一张了！",500);
                    i = photoList.size()-1;
                }
                break;
            default:
                break;
        }
    }

    public void back(View v){
        finish();
    }

}
