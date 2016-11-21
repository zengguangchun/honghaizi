package com.honghaizi.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.honghaizi.test.fragment.Content_Fragment_Comment;
import com.honghaizi.test.fragment.Content_Fragment_Shopping;
import com.honghaizi.test.fragment.Content_Fragment_Xiangxi;

/**
 * Created by asus on 2016/11/17.
 */
public class MyFragmentViewPager extends FragmentPagerAdapter {

    public MyFragmentViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Content_Fragment_Shopping();
                break;
            case 1:
                fragment = new Content_Fragment_Xiangxi();
                break;
            case 2:
                fragment = new Content_Fragment_Comment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
