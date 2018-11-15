package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
/**
 * CommonWebActivity
 * author:along
 * 2018/10/30 上午10:40
 *
 * 描述:常规webview展示页面
 */

public class CommonWebActivity extends BaseActivity {
    private WebView mWebActivityCommonWeb;


    @Override
    public void initView() {
        mTvTitle.setText("详情");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebActivityCommonWeb.canGoBack()) {
                    mWebActivityCommonWeb.goBack();
                    return;
                }
                finish();
            }
        });
        mWebActivityCommonWeb = (WebView) findViewById(R.id.web_activity_common_web);

    }

    @Override
    public void onBackPressed() {
        if (mWebActivityCommonWeb.canGoBack()) {
            mWebActivityCommonWeb.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        if (!TextUtils.isEmpty(data)) {
            mWebActivityCommonWeb.loadUrl(data);
            mWebActivityCommonWeb.getSettings().setJavaScriptEnabled(true);
            mWebActivityCommonWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWebActivityCommonWeb.getSettings().setDomStorageEnabled(true);
            //以下配置不适用8。0
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        mWvActivityTicketShop.getSettings().setSafeBrowsingEnabled(false);
//                    }
            mWebActivityCommonWeb.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    //7。0之上需要更改设置，要不webview不显示
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    } else {
                        view.loadUrl(request.toString());
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public int getRootView() {
        return R.layout.activity_common_web;
    }
}
