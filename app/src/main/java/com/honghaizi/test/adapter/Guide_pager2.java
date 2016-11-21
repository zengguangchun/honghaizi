package com.honghaizi.test.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.honghaizi.test.honghaizi.HomeActivity;
import com.honghaizi.test.honghaizi.R;

/**
 * Created by asus on 2016/11/7.
 */
public class Guide_pager2 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.pager2,null);
        ImageView tv_jin = (ImageView)view.findViewById(R.id.iv_pager2);
        tv_jin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
