package com.baby.tech.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.baby.tech.R;
import com.baby.tech.activity.base.BaseActivity;

public class WeatherActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webView = (WebView) findViewById(R.id.Weather_webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(0);
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        openBrowser();
    }

    // 利用webView的loadUrl方法
    public void openBrowser() {
        webView.loadUrl("http://192.168.1.104/map/weather.html");
        // webView.loadUrl("http://www.baidu.com");
        // webView.loadUrl("http://192.168.1.104:8080/apk/com.baby.tech.apk");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // MainActivity.this.setTitle("加载完成");
                } else {
                    // MainActivity.this.setTitle("加载中.......");

                }
            }
        });

    }

    // @Override
    // public void onClick(View v) {
    // int iResid = v.getId();
    // switch (iResid) {
    // case R.id.id_layout_title_back:
    // finish();
    // break;
    // default:
    // break;
    // }
    //
    // }
    //
    // @Override
    // protected String setTitle() {
    // // TODO Auto-generated method stub
    // return "天气预报";
    //
    // }
    //
    // @Override
    // protected String setTitleBack() {
    // // TODO Auto-generated method stub
    // return "返回";
    // }

}
