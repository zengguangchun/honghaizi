package com.honghaizi.test.recyclerviewutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.honghaizi.test.adapter.Recy_Item3_Adapter;
import com.honghaizi.test.base.BaseViewHolder;
import com.honghaizi.test.bean.Home_Bean;
import com.honghaizi.test.honghaizi.R;
import com.honghaizi.test.webview.Home_GV_WebActivity;

import java.util.List;

/**
 * Created by asus on 2016/11/15.
 */
public  class Item3ViewHolder extends BaseViewHolder{


    private final RecyclerView recyclerView;
    private List<Home_Bean.DataBean.TagBean> su;


    public Item3ViewHolder(View view, Context context) {
        super(view, context);

        recyclerView = (RecyclerView)view.findViewById(R.id.recy_item3);

    }

    private void ClickListener() {
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(context, recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, Home_GV_WebActivity.class);
                intent.putExtra("grid",su.get(position%su.size()).getLinkUrl());
                Activity activity = (Activity) context;
                activity.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    public void initData(Object data){
        su = (List<Home_Bean.DataBean.TagBean>) data;
       LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Recy_Item3_Adapter adapter = new Recy_Item3_Adapter(context, su);
        recyclerView.setAdapter(adapter);
        ClickListener();
    }
}
