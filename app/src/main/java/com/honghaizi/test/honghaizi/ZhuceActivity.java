package com.honghaizi.test.honghaizi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ZhuceActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_zuce_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        getSupportActionBar().hide();

        //初始化控件
        initCtrl();
    }

    private void initCtrl() {
        iv_zuce_back = (ImageView)findViewById(R.id.iv_zuce_back);
        iv_zuce_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_zuce_back:
                finish();
                break;
        }
    }
}
