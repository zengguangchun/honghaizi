package com.honghaizi.test.honghaizi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.honghaizi.test.fragment.Fragment_Home;
import com.honghaizi.test.fragment.Fragment_My;
import com.honghaizi.test.fragment.Fragment_Shopping;
import com.honghaizi.test.fragment.Fragment_classify;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment home, classify, shoping, my;
    private RadioButton rb_home;
    private RadioButton rb_classs;
    private RadioButton rb_shopp;
    private RadioButton rb_user;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        //初始化控件
        findByID();

        getfragment();
        changefragment("home");
        Saver();//保存当前页面
    }

    private void changefragment(String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        if ("home".equals(tag)) {

            transaction.show(home);
            transaction.hide(classify);
            transaction.hide(shoping);
            transaction.hide(my);
        } else if ("classify".equals(tag)) {
            transaction.show(classify);
            transaction.hide(home);
            transaction.hide(shoping);
            transaction.hide(my);
        } else if ("shoping".equals(tag)) {
            transaction.show(shoping);
            transaction.hide(my);
            transaction.hide(classify);
            transaction.hide(home);
        } else if ("my".equals(tag)) {
            transaction.show(my);
            transaction.hide(shoping);
            transaction.hide(classify);
            transaction.hide(home);
        }
        transaction.commit();
    }



    private void findByID() {
        rb_home = (RadioButton)findViewById(R.id.rb_home);
        rb_classs = (RadioButton)findViewById(R.id.rb_classify);
        rb_shopp = (RadioButton)findViewById(R.id.rb_shopping);
        rb_user = (RadioButton)findViewById(R.id.rb_user);
        rb_home.setOnClickListener(this);
        rb_classs.setOnClickListener(this);
        rb_shopp.setOnClickListener(this);
        rb_user.setOnClickListener(this);
    }

    private void Saver() {
        SharedPreferences sp1 = getSharedPreferences("page",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp1.edit();
        editor.putBoolean("isFrist", false);
        editor.commit();
    }

    public void getfragment() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        home = new Fragment_Home();
        classify = new Fragment_classify();
        shoping = new Fragment_Shopping();
        my = new Fragment_My();
        transaction.add(R.id.fl, home, "home");
        transaction.add(R.id.fl, classify, "classify");
        transaction.add(R.id.fl, shoping, "shoping");
        transaction.add(R.id.fl, my, "my");
        transaction.commit();
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.rb_home:
                changefragment("home");
                break;
            case R.id.rb_classify:
                changefragment("classify");
                break;
            case R.id.rb_shopping:
                changefragment("shoping");

                break;
            case R.id.rb_user:
                changefragment("my");
                break;

            default:
                break;
        }
    }
}
