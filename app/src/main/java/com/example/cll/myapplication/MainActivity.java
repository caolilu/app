package com.example.cll.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cll.myapplication.utils.XListView;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private List<String> list;
    private XListView lv;
    private Myadter myadter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myadter.notifyDataSetChanged();
            close();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        lv = (XListView) findViewById(R.id.lv);
        lv.setPullLoadEnable(true);
        lv.setPullRefreshEnable(true);
        lv.setXListViewListener(this);
        myadter = new Myadter();
        lv.setAdapter(myadter);
    }
    private void init(){

        list = new ArrayList<>();
        for (int a=0;a<10;a++){
            list.add("给我"+a+"个时光");
        }
//        new Thread(){
//            String path="http://v3.wufazhuce.com:8000/api/reading/index/";
//
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    URL url = new URL(path);
//                   HttpURLConnection connection= (HttpURLConnection) url.openConnection();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }
class  Myadter extends BaseAdapter{

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(MainActivity.this);
        view.setTextSize(25);
        view.setText(list.get(position));
        return view;
    }
}
    public  void close(){
        lv.stopLoadMore();
        //停止刷新
        lv.stopRefresh();
        lv.setRefreshTime("2017:9:26");
    }
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add(0,"恍惚齐了!!!");
                handler.sendEmptyMessage(0);
            }
        },2000);
    }

    @Override
    public void onLoadMore() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("时光齐了!!!");
                handler.sendEmptyMessage(0);
            }
        },2000);
    }
}
