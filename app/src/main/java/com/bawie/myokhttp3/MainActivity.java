package com.bawie.myokhttp3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bawie.myokhttp3.presenter.UserPresenter;
import com.bawie.myokhttp3.view.UserView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements UserView {

    private Button main_btn;
    private RecyclerView main_rv;
    private SharedPreferences con;
    private MyListAdapter myListAdapter;
    private MyGridAdapter myGridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_btn = (Button) findViewById(R.id.main_btn);

        UserPresenter presenter = new UserPresenter(MainActivity.this,this);
        presenter.requestUser();

        con = getSharedPreferences("isTrue", MODE_PRIVATE);
        con.edit().commit();


    }

    @Override
    public void getUserSuccess(final List<Bean.DataBean> data2) {

          //默认的列表模式
            main_rv = (RecyclerView) findViewById(R.id.main_rv);
            LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
            main_rv.setLayoutManager(manager);
            myListAdapter = new MyListAdapter(MainActivity.this, data2);
            main_rv.setAdapter(myListAdapter);


        //添加头部
        HeaderAndFooterWrapper myHeaderAndFooterWrapper = new HeaderAndFooterWrapper(myListAdapter);

        View view = View.inflate(MainActivity.this,R.layout.head_item,null);
        myHeaderAndFooterWrapper.addHeaderView(view);
        main_rv.setAdapter(myHeaderAndFooterWrapper);
        myHeaderAndFooterWrapper.notifyDataSetChanged();


        //按钮的点击事件，点击切换布局
        main_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isTrue = con.getBoolean("isTrue", true);
                    if(isTrue){
                        main_rv = (RecyclerView) findViewById(R.id.main_rv);
                        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                        main_rv.setLayoutManager(manager);
                        myListAdapter = new MyListAdapter(MainActivity.this, data2);
                        main_rv.setAdapter(myListAdapter);

                        //添加头部
                        HeaderAndFooterWrapper myHeaderAndFooterWrapper = new HeaderAndFooterWrapper(myListAdapter);

                        View view2 = View.inflate(MainActivity.this,R.layout.head_item,null);
                        myHeaderAndFooterWrapper.addHeaderView(view2);
                        main_rv.setAdapter(myHeaderAndFooterWrapper);
                        myHeaderAndFooterWrapper.notifyDataSetChanged();

                        //不要忘记提交
                        con.edit().putBoolean("isTrue",false).commit();


                    }else{
                        main_rv = (RecyclerView) findViewById(R.id.main_rv);
                        GridLayoutManager manager = new GridLayoutManager(MainActivity.this,2);
                        main_rv.setLayoutManager(manager);
                        myGridAdapter = new MyGridAdapter(MainActivity.this, data2);
                        main_rv.setAdapter(myGridAdapter);

                        //添加头部
                        HeaderAndFooterWrapper myHeaderAndFooterWrapper = new HeaderAndFooterWrapper(myGridAdapter);

                        View view3 = View.inflate(MainActivity.this,R.layout.head_item,null);
                        myHeaderAndFooterWrapper.addHeaderView(view3);
                        main_rv.setAdapter(myHeaderAndFooterWrapper);
                        myHeaderAndFooterWrapper.notifyDataSetChanged();

                        //不要忘记提交
                        con.edit().putBoolean("isTrue",true).commit();
                    }
                }
            });

    }//请求成功

    @Override
    public void getUserFaliure(String msg) {

    }

    @Override
    public void onFaliure(Call call, IOException e) {

    }
}
