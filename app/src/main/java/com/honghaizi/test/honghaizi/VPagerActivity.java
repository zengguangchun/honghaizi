package com.honghaizi.test.honghaizi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.honghaizi.test.adapter.VP_Adapter;

public class VPagerActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpager);
        getSupportActionBar().hide();
        vp = (ViewPager)findViewById(R.id.vp_guide);
// 设置设配器
        VP_Adapter adapter = new VP_Adapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        SharedPreferences sp = getSharedPreferences("page",
                Context.MODE_PRIVATE);
        boolean flag = sp.getBoolean("isFrist", true);
        if (!flag) {
            Intent intent = new Intent(VPagerActivity.this,
                    HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
