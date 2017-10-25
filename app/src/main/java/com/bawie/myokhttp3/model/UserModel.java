package com.bawie.myokhttp3.model;

import com.bawie.myokhttp3.Api;
import com.bawie.myokhttp3.Bean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 张肖肖 on 2017/10/25.
 */

public class UserModel {

    public void getUserData(){

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        Request request = new Request.Builder().url(Api.List_API).post(build).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onUser.onFaliure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful() && response != null){
                    final String result;
                    try{
                        result = response.body().string();
                        System.out.println("result的结果 = " + result);
                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(result, Bean.class);

                        //成功
                        List<Bean.DataBean> data2 = bean.getData();
                        String code = bean.getCode();
                        //失败
                        String msg = bean.getMsg();

                        if(code.equals("0")){
                            onUser.getUserSuccess(data2);

                        }else if(code.equals("1")){
                            onUser.getUserFaliure(msg);
                        }
                    }catch (Exception e){

                    }
                }
            }
        });


    }

    private onUser onUser;

    public void setOnUser(UserModel.onUser onUser) {
        this.onUser = onUser;
    }

    public interface onUser{
        //成功，data
        void getUserSuccess(List<Bean.DataBean> data2);
        //失败，吐司事件
        void getUserFaliure(String msg);
        //彻底失败
        void onFaliure(Call call, IOException e);
    }


}
