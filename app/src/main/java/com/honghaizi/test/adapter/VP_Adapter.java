package com.honghaizi.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by asus on 2016/11/7.
 */
public class VP_Adapter extends FragmentPagerAdapter {
    public VP_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        switch (position) {
            case 0:
                fragment = new Guide_pager1();
                break;
            case 1:
                fragment = new Guide_pager2();
                break;
            case 2:
                fragment = new Guide_pager3();
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
