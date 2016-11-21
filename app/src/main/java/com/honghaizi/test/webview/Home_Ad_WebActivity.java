package com.honghaizi.test.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.honghaizi.test.honghaizi.R;

public class Home_Ad_WebActivity extends AppCompatActivity {
    WebView home_ad1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__ad__web);
        getSupportActionBar().hide();
        home_ad1 = (WebView) findViewById(R.id.home_ad1_web);
        Intent intent = getIntent();
        String url = intent.getStringExtra("Ad1");
        home_ad1.getSettings().setJavaScriptEnabled(true);
        home_ad1.clearCache(true);
        home_ad1.clearHistory();
        home_ad1.loadUrl(url);
        home_ad1.setWebViewClient(new WebViewClient());
    }
}
