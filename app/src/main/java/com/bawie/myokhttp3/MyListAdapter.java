package com.bawie.myokhttp3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/10/25.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder>{
    private final Context context;
    private final List<Bean.DataBean> data;

    public MyListAdapter(Context context, List<Bean.DataBean> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.list_item, null);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.list_tv.setText(data.get(position).getTitle());
        Glide.with(context).load(data.get(position).getImages().split("\\|")[0]).into(holder.list_iv);

    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }else{
            return 0;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView list_tv;
        private final ImageView list_iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            list_tv = itemView.findViewById(R.id.list_tv);
            list_iv = itemView.findViewById(R.id.list_iv);
        }
    }
}
