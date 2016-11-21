package com.honghaizi.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.honghaizi.test.base.BaseViewHolder;
import com.honghaizi.test.bean.Home_Bean;
import com.honghaizi.test.honghaizi.R;
import com.honghaizi.test.recyclerviewutils.Item1ViewHolder;
import com.honghaizi.test.recyclerviewutils.Item2ViewHolder;
import com.honghaizi.test.recyclerviewutils.TypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/11/14.
 */
public class Home_RVS_Adapter extends RecyclerView.Adapter<BaseViewHolder> implements TypeUtil {
    private Context context;
    private Home_Bean hb;
    private LayoutInflater mLayoutInflater;
    /**
     * 类型集合，adapter对应的数据集合
     */
    List<Pair<Integer, Object>> superData;
    private List<Home_Bean.DataBean> data;

    public Home_RVS_Adapter(Context context, Home_Bean hb) {
        this.context = context;
        this.hb = hb;
        mLayoutInflater = LayoutInflater.from(context);

        superData=new ArrayList<Pair<Integer,Object>>();

        initOtherData();
    }

    private void initOtherData() {

        /**获取所有数据*/
        data = hb.getData();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null || data.get(i).getTag() == null)
                continue;
            if (data.get(i).getSequence() == 5) {
                superData.add(new Pair<Integer, Object>(COMMUNITY_TOP, data.get(i).getTag()));
            }
            if (data.get(i).getSequence() == 2) {
                superData.add(new Pair<Integer, Object>(COMMUNITY_OHTER, data.get(i).getTag()));
            }

        }
    }
    // 给数据打包，两个一块

    public Pair<Object, Object> wrapData(Object f, Object s) {
        return new Pair<Object, Object>(f, s);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == COMMUNITY_TOP) {
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.home_recy_item1, parent, false),context);

        } else {
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.home_recy_item2, parent, false),context);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (superData.get(position).first){
            case COMMUNITY_TOP:

                ((Item1ViewHolder)holder).initData(superData.get(position).second);

                break;
            case COMMUNITY_OHTER:
              ((Item2ViewHolder)holder).initData(superData.get(position).second);
                break;
        }
    }




    //设置ITEM类型，可以自由发挥，这里设置item position单数显示item1 偶数显示item2

    @Override
    public int getItemViewType(int position) {
        return superData.get(position).first;
    }

    @Override
    public int getItemCount() {
        return superData.size();
    }
    //item1 的ViewHolder
//    public static class Item1ViewHolder extends RecyclerView.ViewHolder{
//        ImageView home_recy_iv_1;
//        public Item1ViewHolder(View itemView) {
//            super(itemView);
//            home_recy_iv_1=(ImageView)itemView.findViewById(R.id.home_recy_iv_1);
//        }
//    }
//    //item2 的ViewHolder
//    public static class Item2ViewHolder extends RecyclerView.ViewHolder{
//        TextView tv;
//        ImageView home_recy_iv_2;
//        public Item2ViewHolder(View itemView) {
//            super(itemView);
//            home_recy_iv_2=(ImageView)itemView.findViewById(R.id.home_recy_iv_2);
//            tv = (TextView)itemView.findViewById(R.id.home_recy_tv_2s);
//        }
//    }
}
