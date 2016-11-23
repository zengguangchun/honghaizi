package com.honghaizi.test.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.honghaizi.test.adapter.MostLeftAdapter;
import com.honghaizi.test.adapter.MostRightAdapter;
import com.honghaizi.test.bean.Classify_Bean;
import com.honghaizi.test.honghaizi.R;
import com.honghaizi.test.recyclerviewutils.RecyclerViewClickListener;
import com.honghaizi.test.util.Utilee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by asus on 2016/11/7.
 */
public class Fragment_classify extends Fragment {
    private RecyclerView left_list;
    private RecyclerView right_list;
    private View view;
    private MostLeftAdapter leftAdapter;
    private MostRightAdapter rightAdapter;

    private String TAG = "Fragment_classify";

    private Handler handler = new Handler(Looper.getMainLooper());
    private MostRightAdapter rightAdapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_classify, container, false);
        //初始化控件
        initCtrl();
        initData();
        return view;
    }


    public void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream stream = getResources().getAssets().open("category.txt");
                    final String json = Utilee.stream2String(stream);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            parseData(json);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void parseData(String json) {
        //布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        left_list.setLayoutManager(layoutManager);

        //布局管理
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(),3);
        right_list.setLayoutManager(layoutManager1);

        Gson gson = new Gson();
        final Classify_Bean cb = gson.fromJson(json, Classify_Bean.class);
        final List<Classify_Bean.RsBean> rss = cb.getRs();
        List<Classify_Bean.RsBean.ChildrenBeanX> children = cb.getRs().get(0).getChildren();
        //左边RecyclerView
        leftAdapter = new MostLeftAdapter(getActivity(), rss);
        left_list.setAdapter(leftAdapter);

        rightAdapter1 = new MostRightAdapter(getActivity(),children);
        right_list.setAdapter(rightAdapter1);
        left_list.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), left_list, new RecyclerViewClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                final List<Classify_Bean.RsBean.ChildrenBeanX> children = cb.getRs().get(position).getChildren();
                String name= rss.get(position).getDirName();
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 1:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 2:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 3:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 4:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 5:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 6:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 7:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                    case 8:
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        rightAdapter1 = new MostRightAdapter(getActivity(),children);
                        right_list.setAdapter(rightAdapter1);
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }

    private void initCtrl() {
        left_list = (RecyclerView) view.findViewById(R.id.left_list);
        right_list = (RecyclerView) view.findViewById(R.id.right_list);

    }


}
