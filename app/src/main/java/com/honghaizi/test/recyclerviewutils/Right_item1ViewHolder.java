package com.honghaizi.test.recyclerviewutils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.honghaizi.test.base.BaseViewHolder;
import com.honghaizi.test.bean.Classify_Bean;
import com.honghaizi.test.honghaizi.R;

import java.util.List;

/**
 * Created by asus on 2016/11/22.
 */
public class Right_item1ViewHolder extends BaseViewHolder {



    private List<Classify_Bean.RsBean.ChildrenBeanX> children;
    private final TextView tv;

    public Right_item1ViewHolder(View view, Context context) {
        super(view, context);
        tv = (TextView)view.findViewById(R.id.right_tv_item1);
    }
    public void initData(Object data){
      // children = (List<Classify_Bean.RsBean.ChildrenBeanX>) data;
        for (int i = 0; i < children.size(); i++) {
            tv.setText(children.get(i).getDirName());
        }

        //tv.setText("曾光春");

    }




}
