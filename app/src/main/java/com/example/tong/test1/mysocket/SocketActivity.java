package com.example.tong.test1.mysocket;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.test1.ActivityMain;
import com.example.tong.test1.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tong on 17-6-12.
 */

public class SocketActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int port = 12345;
    private EditText socketEdit;
    private Button socketBtn;
    private SharedPreferences spf;
    private EditText printEdit;
    private Button printBtn;
    private Socket socket;
    private String line;
    private ListView socketLv;
    private List<Map<String,Object>> list = new ArrayList<>();
    SocketAdapter socketAdapter = new SocketAdapter();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_study);
        initView();

        spf = this.getSharedPreferences("socketip", MODE_PRIVATE);

        String thisip = spf.getString("socketip", "0.0.0.0");
        socketEdit.setText(thisip);

        socketBtn.setOnClickListener(this);
        printBtn.setOnClickListener(this);

        socketLv.setAdapter(socketAdapter);
        socketLv.setDivider(null);
    }

    private void initView() {
        socketEdit = (EditText) findViewById(R.id.socket_edit);
        socketBtn = (Button) findViewById(R.id.socket_btn);
        printEdit = (EditText) findViewById(R.id.print_edit);
        printBtn = (Button) findViewById(R.id.print_btn);
        socketLv = (ListView) findViewById(R.id.socket_lv);
    }

    @Override
    public void onClick(View v) {
        String s = socketEdit.getText().toString();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "请输入IP", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (v.getId()) {
            case R.id.socket_btn:
                //启动线程
                ThreadPoolTool.getInstance().execute(new MyThread(s));
                SharedPreferences.Editor editor = spf.edit();
                editor.putString("socketip", s);
                editor.commit();
                break;
            case R.id.print_btn:
                String p = printEdit.getText().toString();
                if (TextUtils.isEmpty(p)) return;
                ThreadPoolTool.getInstance().execute(new MySendMess(p));
                Map<String,Object> map = new HashMap<>();
                map.put("flag",false);
                map.put("text",p);
                list.add(map);
                socketAdapter.notifyDataSetChanged();
                break;
        }
    }

    //处理消息的接收
    private class MyThread implements Runnable {
        String host;

        MyThread(String host) {
            this.host = host;
        }

        //发送消息
        public void out(String out) {

        }

        @Override
        public void run() {
            try {
                //实例化socket对象
                socket = new Socket(host, port);
                //是否连接成功
                boolean flag = socket.isConnected();
                if (flag) {
                    Log.e("MyThread", "连接成功");
                    //获取输入流对象
                    InputStream is = socket.getInputStream();
                    //获取输出流对象
                    //OutputStream os = socket.getOutputStream();
                    //缓冲区输入流
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    //缓冲区输出流
                    //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
                    //接收服务器发送的数据
                    while ((line = bufferedReader.readLine()) != null) {
                        handler.sendEmptyMessage(0x123);
                    }
                    socket.close();
                } else {
                    Log.e("MyThread", "连接失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //处理消息的发送
    private class MySendMess implements Runnable {

        String mess;

        MySendMess(String mess) {
            this.mess = mess;
        }

        @Override
        public void run() {
            if (socket == null) return;
            try {
                //获取输出流对象
                OutputStream os = socket.getOutputStream();
                //缓冲区输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
                bufferedWriter.write(mess);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SocketAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = LayoutInflater.from(SocketActivity.this).inflate(R.layout.socket_item, null);
                holder.fuwu = (LinearLayout) view.findViewById(R.id.item_fuwu);
                holder.kehu = (LinearLayout) view.findViewById(R.id.item_kehu);
                holder.fuwuTv = (TextView) view.findViewById(R.id.item_fuwu_put);
                holder.kehuTv = (TextView) view.findViewById(R.id.item_kehu_put);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }

            Map<String,Object> map = list.get(i);

            //flag == true : 接收到服务器数据,flag == false : 客户端发送数据
            if ((boolean)map.get("flag") == true){
                holder.kehu.setVisibility(View.GONE);
                holder.fuwuTv.setText(map.get("text").toString());
            }else {
                holder.fuwu.setVisibility(View.GONE);
                holder.kehuTv.setText(map.get("text").toString());
            }

            return view;
        }

        class ViewHolder{
            LinearLayout fuwu;
            LinearLayout kehu;
            TextView fuwuTv;
            TextView kehuTv;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                Map<String,Object> map = new HashMap<>();
                map.put("flag",true);
                map.put("text",line);
                list.add(map);
                socketAdapter.notifyDataSetChanged();
            }
        }
    };

}
