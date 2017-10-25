package com.bawie.myokhttp3.presenter;

import android.content.Context;

import com.bawie.myokhttp3.Bean;
import com.bawie.myokhttp3.model.UserModel;
import com.bawie.myokhttp3.view.UserView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 张肖肖 on 2017/10/25.
 */

public class UserPresenter implements UserModel.onUser {
    private UserModel userModel;
    private Context context;
    private UserView userView;

    public UserPresenter(Context context, UserView userView) {
        this.context = context;
        this.userView = userView;
        userModel = new UserModel();
        userModel.setOnUser(this);//这里写的是Model类中的set方法
    }

    //自己定义的方法
    public void requestUser(){
        userModel.getUserData();
    }

    @Override
    public void getUserSuccess(List<Bean.DataBean> data2) {
        userView.getUserSuccess(data2);
    }

    @Override
    public void getUserFaliure(String msg) {
        userView.getUserFaliure(msg);
    }

    @Override
    public void onFaliure(Call call, IOException e) {
        userView.onFaliure(call,e);
    }
}
