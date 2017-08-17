package com.example.tong.test1.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tong.test1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tong on 17-6-8.
 */

public class AsynctaskStudy extends AppCompatActivity implements View.OnClickListener {
    private android.widget.ImageView asyncimg;
    private android.widget.Button asyncbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_study);
        this.asyncbtn = (Button) findViewById(R.id.async_btn);
        this.asyncimg = (ImageView) findViewById(R.id.async_img);
        asyncbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String urlpath = "http://img1.gtimg.com/sports/pics/hv1/78/154/2215/144069723.jpg";
        new MyAsy().execute(urlpath);
    }

    private class MyAsy extends AsyncTask<String,Integer,Bitmap>{

        @Override//在执行后台操作之前调用
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override//执行在后台（即子线程中）
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                if (conn.getResponseCode() == 200) {
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                } else {
                    Log.e("HTTP:ERROR", "请求失败，请检查网络");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override//在doInBackground方法中调用publishProgress来更新进度条（即运行在ui线程）
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override//后台任务执行完成后回调,更新UI操作(例如进度条的隐藏)
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null)
                asyncimg.setImageBitmap(bitmap);
        }
    }
}
