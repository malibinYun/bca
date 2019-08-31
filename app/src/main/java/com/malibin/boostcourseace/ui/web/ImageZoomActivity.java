package com.malibin.boostcourseace.ui.web;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.malibin.boostcourseace.R;

/**
 * Created By Yun Hyeok
 * on 8월 31, 2019
 */

public class ImageZoomActivity extends AppCompatActivity {

    private String url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom);

        getIntentData();
        bindView();
        initToolbar();

        initWebViewSettings();

        webView.loadUrl(url);
    }

    private void getIntentData() {
        url = getIntent().getStringExtra("url");
    }

    private void bindView() {
        webView = findViewById(R.id.web_image_zoom_act);
    }

    private void initWebViewSettings() {
        WebSettings settings = webView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_image_zoom_act);
        toolbar.setTitle("사진 보기");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }


}
