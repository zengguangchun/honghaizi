package com.honghaizi.test.recyclerviewutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.honghaizi.test.adapter.Recy_Item1_Adapter;
import com.honghaizi.test.base.BaseViewHolder;
import com.honghaizi.test.base.ImageUtils;
import com.honghaizi.test.bean.Home_Bean;
import com.honghaizi.test.honghaizi.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/15.
 */
public  class Item1ViewHolder extends BaseViewHolder{


    private final RecyclerView recyclerView;
    private final ImageView imageView;
    private List<Home_Bean.DataBean.TagBean> tag;

    public Item1ViewHolder(View view, Context context) {
        super(view, context);
        recyclerView = (RecyclerView)view.findViewById(R.id.home_recy_iv_1);
        imageView = (ImageView)view.findViewById(R.id.h_recy_item1_iv1);

    }
    public void initData(Object data){
        tag = (List<Home_Bean.DataBean.TagBean>) data;
        ImageLoader.getInstance().displayImage(ImageUtils.imageutil+ tag.get(0).getPicUrl(), imageView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Recy_Item1_Adapter adapter = new Recy_Item1_Adapter(context, tag);
        recyclerView.setAdapter(adapter);
        ClickListener();
    }

    private void ClickListener() {
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(context, recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, Content_Activity.class);
                Activity activity = (Activity) context;
                activity.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }


}
