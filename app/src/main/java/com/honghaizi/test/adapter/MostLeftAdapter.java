package com.honghaizi.test.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.honghaizi.test.bean.Classify_Bean;
import com.honghaizi.test.honghaizi.R;

import java.util.List;


/**
 * Created by asus on 2016/11/22.
 */
public class MostLeftAdapter extends RecyclerView.Adapter<MostLeftAdapter.MyViewHolder> {

    private Context context;
    private List<Classify_Bean.RsBean> rs;
    public MostLeftAdapter(Context context, List<Classify_Bean.RsBean> rs) {
        this.context = context;
        this.rs = rs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.most_left_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.tv_dir_name.setText(rs.get(position).getDirName());
    }

    @Override
    public int getItemCount() {
        return rs.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_dir_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_dir_name = (TextView)itemView.findViewById(R.id.most_left_text);
        }
    }
}