package com.honghaizi.test.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.honghaizi.test.base.ImageUtils;
import com.honghaizi.test.bean.Home_Bean;
import com.honghaizi.test.honghaizi.R;
import com.honghaizi.test.webview.Home_Ad_WebActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/14.
 */
public class Home_VP_Adapter extends PagerAdapter{
    private Context context;
    private List<Home_Bean.DataBean.TagBean> tag;

    public Home_VP_Adapter(Context context, List<Home_Bean.DataBean.TagBean> tag) {
        this.context = context;
        this.tag = tag;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.home_vp_item, null);

        ImageView iv_home_page = (ImageView) view
                .findViewById(R.id.home_vp_image);

        iv_home_page.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               String ad = tag.get(position%tag.size()).getLinkUrl();
                Intent intent = new Intent(context, Home_Ad_WebActivity.class);
                intent.putExtra("Ad1", ad);
                Activity activity = (Activity) context;
                activity.startActivity(intent);

            }
        });


        ImageLoader.getInstance().displayImage(
                ImageUtils.imageutil+tag.get(position % tag.size()).getPicUrl(), iv_home_page);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
