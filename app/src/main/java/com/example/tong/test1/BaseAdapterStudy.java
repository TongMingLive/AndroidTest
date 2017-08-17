package com.example.tong.test1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/11/4.
 */

public class BaseAdapterStudy extends AppCompatActivity {
    private ListView lv;
    private List<Integer> list_img = new ArrayList<>();
    private List<String> list_text = new ArrayList<>();
    private List<Integer> list_money = new ArrayList<>();
    private TextView final_money;
    private double this_money;
    private Button shop;

    //自定义toast显示时间方法
    public static void showToast(final Activity activity, final String word, final long time) {
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
        setContentView(R.layout.base_adapter);
        lv = (ListView) findViewById(R.id.myAdapter_test_list1);
        final_money = (TextView) findViewById(R.id.myAdapter_test_money);
        shop = (Button) findViewById(R.id.myAdapter_test_shop);

        list_img.add(R.mipmap.imga);
        list_img.add(R.mipmap.imgb);
        list_img.add(R.mipmap.imgc);
        list_img.add(R.mipmap.imgd);

        list_text.add("末日浩劫");
        list_text.add("蝙蝠侠");
        list_text.add("蝙蝠侠Ⅱ");
        list_text.add("超人");

        list_money.add(35);
        list_money.add(25);
        list_money.add(55);
        list_money.add(45);

        MyAdapter myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (this_money == 0) {
                    showToast(BaseAdapterStudy.this, "您还没有选择商品哦", 1000);
                } else {
                    showToast(BaseAdapterStudy.this, "您正在发起一笔" + this_money + "元的交易", 1000);
                }
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
        //返回item个数
        @Override
        public int getCount() {
            return list_img.size();
        }

        //返回item对象
        @Override
        public Object getItem(int position) {
            return null;
        }

        //返回item的ID
        @Override
        public long getItemId(int position) {
            return 0;
        }

        //绘制item
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(BaseAdapterStudy.this);
            View v = layoutInflater.inflate(R.layout.base_adapter_item, null);
            ImageView img = (ImageView) v.findViewById(R.id.myAdapter_test_img);
            TextView title = (TextView) v.findViewById(R.id.myAdapter_test_title);
            TextView money = (TextView) v.findViewById(R.id.myAdapter_test_money);
            Button sub = (Button) v.findViewById(R.id.myAdapter_test_btnsub);
            Button add = (Button) v.findViewById(R.id.myAdapter_test_btnadd);
            final TextView sumTv = (TextView) v.findViewById(R.id.myAdapter_test_Edit);
            final int sum = Integer.parseInt(sumTv.getText().toString());
            img.setImageResource(list_img.get(position));
            title.setText(list_text.get(position));
            money.setText(list_money.get(position) + "");
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sum != 0) {
                        this_money -= list_money.get(position);
                        final_money.setText(this_money + "");
                        sumTv.setText(sum-1+"");
                    }
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    this_money += list_money.get(position);
                    final_money.setText(this_money + "");
                    sumTv.setText(sum+1+"");
                }
            });
            return v;
        }
    }
}
