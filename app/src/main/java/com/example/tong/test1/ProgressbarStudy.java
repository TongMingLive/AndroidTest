package com.example.tong.test1;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Tong on 2016/12/26.
 */

public class ProgressbarStudy extends AppCompatActivity {
    private ProgressBar pb;
    private Button btn;
    private String path;//下载路径
    private int length;//文件的总长度
    private int sum;//用于累加每次下载的长度

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        pb = (ProgressBar) findViewById(R.id.pg);
        btn = (Button) findViewById(R.id.pg_btn);
        pb.setMax(100);
        btn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.pg_btn) {
                //实例化线程类
                Change change = new Change("http://qiniu.marvel.ac.cn/1%20-%20LiZBPs8.png");
                //启动线程
                change.start();
            }
        }
    };

    class Change extends Thread {
        private String urlPath;

        public Change(String urlPath) {
            this.urlPath = urlPath;
        }

        @Override
        public void run() {
            super.run();
            try {
                //获取url对象
                URL url = new URL(urlPath);
                //打开链接对象，获取HttpURLConnection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置链接超时
                conn.setConnectTimeout(5000);
                //设置响应超时
                conn.setReadTimeout(5000);
                //设置请求方式
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() == 200) {
                    length = conn.getContentLength();//获取文件长度
                    InputStream is = conn.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    File sdFile = new File(getSDPathIsRootPath(), urlPath.substring(urlPath.lastIndexOf("/") + 1));
                    OutputStream os = new FileOutputStream(sdFile);
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = bis.read(b)) != -1) {
                        bos.write(b, 0, len);
                        sum += len;
                        handler.sendEmptyMessage(sum);
                    }
                    path = sdFile.getAbsolutePath();
                    Log.d("Change", "下载完成" + path);
                    //关闭流
                    bos.close();
                    os.close();
                    bis.close();
                    is.close();
                    //关闭链接
                    conn.disconnect();
                } else {
                    Log.e("TAG", "请检查网络");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            pb.setProgress((int) (msg.what * 1.0) / length * 100);
            if (msg.what>=length){
                Toast.makeText(ProgressbarStudy.this, "下载完成,下载地址：\n"+path, Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * 判断手机SD卡是否可读可写
     * 如果可以返回手机SD卡的根目录
     * 如果不可以返回手机的根目录
     */
    private String getSDPathIsRootPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //SD存在，可读可写，返回SD卡的根目录
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            //返回手机根目录
            return Environment.getRootDirectory().getAbsolutePath();
        }
    }
}
