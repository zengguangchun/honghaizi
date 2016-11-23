package com.honghaizi.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.honghaizi.test.bean.Classify_Bean;
import com.honghaizi.test.honghaizi.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/22.
 */
public class MostRightAdapter extends RecyclerView.Adapter<MostRightAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Classify_Bean.RsBean.ChildrenBeanX> children1;


    public MostRightAdapter(Context context, List<Classify_Bean.RsBean.ChildrenBeanX> children1) {
        this.context = context;
        this.children1 = children1;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.series_right_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.right_list_tv.setText(children1.get(position).getDirName());
        ImageLoader.getInstance().displayImage(children1.get(position).getImgApp(),
                holder.right_list_iv);
    }




    @Override
    public int getItemCount() {
        return children1.size();
    }
class MyViewHolder extends RecyclerView.ViewHolder{

    private final ImageView right_list_iv;
    private final TextView right_list_tv;
    public MyViewHolder(View itemView) {
        super(itemView);
       right_list_iv =  (ImageView)itemView.findViewById(R.id.right_list_iv);
      right_list_tv = (TextView)itemView.findViewById(R.id.right_list_tv);
    }
}


}
