package com.example.tong.test1.Download;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
 * Created by Tong on 2016/12/12.
 */

public class HtmlSocket extends AppCompatActivity implements View.OnClickListener {
    private Button getImg1, getImg2;
    private ImageView img;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.html_socket);
        getImg1 = (Button) findViewById(R.id.get_net_img_btn1);
        getImg2 = (Button) findViewById(R.id.get_net_img_btn2);
        img = (ImageView) findViewById(R.id.get_net_data_iv);

        getImg1.setOnClickListener(this);
        getImg2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_net_img_btn1:
                getData("http://qiniu.marvel.ac.cn/NM%E7%99%BD%E5%BA%95%E9%BB%91.png");
                break;
            case R.id.get_net_img_btn2:
                getData("http://qiniu.marvel.ac.cn/NM%E9%BB%91%E5%BA%95%E7%99%BD.png");
                break;
        }
    }

    public void getData(final String urlPath) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置超时时间
                    conn.setConnectTimeout(5000);
                    //设置请求方式，常见的有GET,POST,HEAD
                    conn.setRequestMethod("GET");
                    //获取相应码，如果是200，请求成功
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                        handler.sendEmptyMessage(0x123);
                    } else {
                        Log.e("HTTP:ERROR", "请求失败，请检查网络");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                img.setImageBitmap(bitmap);
            }
        }
    };

    /*public void getData() {
        new Thread(){
            public void run(){
                String urlPath = "http://qiniu.marvel.ac.cn/NM%E9%80%8F%E5%BA%95%E9%BB%91.png";
                try {
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    //设置超时时间
                    conn.setConnectTimeout(5000);
                    //设置请求方式，常见的有GET,POST,HEAD
                    conn.setRequestMethod("GET");
                    //获取相应码，如果是200，请求成功
                    if (conn.getResponseCode() == 200){
                        //请求成功
                        InputStream is = conn.getInputStream();
                        //定义一个byte字节数组，用于接收每次读取的内容，每次读取1024Byte，即1kb
                        byte[] b = new byte[1024];
                        //用于接收每次读取的长度
                        int len;
                        //循环读取输入流的内容
                        while ((len = is.read(b)) != -1){
                            //将读取到的字节数组转化为String,从下标0开始到len结束
                            String str = new String(b,0,len);
                            Log.i("HTML",str);
                        }
                    }else {
                        Log.e("HTTP:ERROR","请求失败，请检查网络");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }*/
}
