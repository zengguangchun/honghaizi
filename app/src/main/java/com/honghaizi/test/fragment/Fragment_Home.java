package com.honghaizi.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.honghaizi.test.adapter.Home_RVS_Adapter;
import com.honghaizi.test.adapter.Home_VP_Adapter;
import com.honghaizi.test.bean.Home_Bean;
import com.honghaizi.test.erweima.Erweima_Activity;
import com.honghaizi.test.honghaizi.R;
import com.honghaizi.test.honghaizi.SearchActivity;
import com.honghaizi.test.okhttputils.OkHttp;
import com.honghaizi.test.okhttputils.Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by asus on 2016/11/7.
 */
public class Fragment_Home extends Fragment implements View.OnClickListener {

    private String path="http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga";
    private View view;
    private LinearLayout ll_dot;
    private ViewPager home_vp;
    private List<ImageView> imgList = new ArrayList<ImageView>();
    List<Home_Bean.DataBean.TagBean> iv_list;
    List<Home_Bean.DataBean.TagBean> tag1;
    Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            int num = msg.what;
            switch (num) {
                case 0:
                    setdot();
                    home_vp.setCurrentItem(iv_list.size());
                    Home_VP_Adapter adapter = new Home_VP_Adapter(getActivity(),iv_list);
                    home_vp.setAdapter(adapter);
                    sendDelayMessage();
                    break;
                case 1:

                    int currentItem = home_vp.getCurrentItem();
                    currentItem++;
                    home_vp.setCurrentItem(currentItem);
                    sendDelayMessage();
                    break;

                default:
                    break;
            }
        };
    };
    private RecyclerView recy_s;


    private void sendDelayMessage() {
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    private void setdot() {
        if (imgList != null) {
            imgList.clear();
        }

        for (int i = 0; i < iv_list.size(); i++) {
            ImageView img_dot = new ImageView(getActivity());
            if (i == 0) {
                img_dot.setImageResource(R.drawable.page_shape);
            } else {
                img_dot.setImageResource(R.drawable.page_shape);

            }
            LayoutParams params = new LayoutParams(10, 5);
            params.setMargins(5, 0, 5, 0);

            imgList.add(img_dot);
            ll_dot.addView(img_dot, params);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_home, null);

        //初始化控件
        initCtrl();

        //数据请求
        okHttp();

        //设置布局管理
        LayoutManager();


        return view;
    }

    private void LayoutManager() {

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recy_s.setLayoutManager(layoutManager);

    }

    private void okHttp() {
        OkHttp.getAsync(path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                String json = result;
                Gson gson = new Gson();
                Home_Bean db = gson.fromJson(json, Home_Bean.class);
                iv_list= db.getData().get(0).getTag();
                tag1=db.getData().get(1).getTag();
                Home_RVS_Adapter home_rvs_adapter = new Home_RVS_Adapter(getActivity(),db);
                recy_s.setAdapter(home_rvs_adapter);
                handler.sendEmptyMessage(0);

            }
        });

    }

    private void initCtrl() {
        ImageView ivScan = (ImageView) view.findViewById(R.id.iv_scan);
        EditText edtSearch = (EditText) view.findViewById(R.id.edt_search);
        ImageView ivMes = (ImageView) view.findViewById(R.id.iv_mes);
        home_vp = (ViewPager)view.findViewById(R.id.home_vp);
        ll_dot = (LinearLayout)view.findViewById(R.id.ll_dot);

        recy_s = (RecyclerView)view.findViewById(R.id.home_s_recy);

        ivScan.setOnClickListener(this);
        edtSearch.setOnClickListener(this);
        ivMes.setOnClickListener(this);
        //动态设置vp高度
        int height = Tool.getScreenHeight(getActivity());
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) home_vp.getLayoutParams(); // 取控件mGrid当前的布局参数
        linearParams.height = height*1 / 4;// 当控件的高强制设成50象素
        home_vp.setLayoutParams(linearParams);

        home_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			/*
			 * 此方法为滑动结束时(non-Javadoc)
			 *
			 * @see
			 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
			 * (int)
			 */

            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < imgList.size(); i++) {
                    if (arg0 % iv_list.size() == i) {
                        imgList.get(arg0 % iv_list.size()).setImageResource(
                                R.drawable.page_shape);
                    } else {
                        imgList.get(i).setImageResource(
                                R.drawable.page_shape_no);
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
        //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_scan:
                startActivity(new Intent(getActivity(), Erweima_Activity.class));
                break;
            case R.id.edt_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_mes:

                break;
            default:
                break;
        }
    }
}
