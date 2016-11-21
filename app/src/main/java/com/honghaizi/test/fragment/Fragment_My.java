package com.honghaizi.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.honghaizi.test.baidu.Baidu_Activity;
import com.honghaizi.test.honghaizi.R;

/**
 * Created by asus on 2016/11/7.
 */
public class Fragment_My extends Fragment implements View.OnClickListener{

    private TextView tv_baidu_f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_my,null);


        Button baidu = (Button)view.findViewById(R.id.but_map);
        tv_baidu_f = (TextView)view.findViewById(R.id.tv_baidu_f);
        baidu.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==66){
            String name = data.getAction();
            tv_baidu_f.setText("我的地址："+name);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_map:
                startActivityForResult(new Intent(getActivity(), Baidu_Activity.class),66);
                break;
        }
    }
}
