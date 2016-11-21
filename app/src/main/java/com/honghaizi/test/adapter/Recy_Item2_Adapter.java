package com.honghaizi.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.honghaizi.test.base.ImageUtils;
import com.honghaizi.test.bean.Home_Bean;
import com.honghaizi.test.honghaizi.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/16.
 */
public class Recy_Item2_Adapter extends RecyclerView.Adapter<Recy_Item2_Adapter.MyViewHolder>{
    private Context context;
    private List<Home_Bean.DataBean.TagBean> tag;

    public Recy_Item2_Adapter(Context context, List<Home_Bean.DataBean.TagBean> tag) {
        this.context = context;
        this.tag = tag;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.home_recy_item1_2, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.tv.setText(tag.get(position).getElementName());
        ImageLoader.getInstance().displayImage(ImageUtils.imageutil+tag.get(position).getPicUrl(),
                holder.iv);


    }



    @Override
    public int getItemCount() {
        return tag.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.home_tv_recy);
            iv = (ImageView) view.findViewById(R.id.home_iv_recy);
        }
    }
}
