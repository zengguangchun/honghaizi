package com.honghaizi.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honghaizi.test.honghaizi.R;

/**
 * Created by asus on 2016/11/17.
 */
public class Content_Fragment_Xiangxi extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.content_fragment_xiangxi,null);

        return view;
    }
}
