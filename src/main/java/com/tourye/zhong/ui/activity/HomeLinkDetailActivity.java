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
 * HomeLinkDetailActivity
 * author:along
 * 2018/10/29 下午4:14
 *
 * 描述:首页顶部链接详情
 */

public class HomeLinkDetailActivity extends BaseActivity {
    private WebView mWebHomeLinkDetail;


    @Override
    public void initView() {
        mTvTitle.setText("详情");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mWebHomeLinkDetail = (WebView) findViewById(R.id.web_home_link_detail);

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("data");
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mWebHomeLinkDetail.loadUrl(url);
        mWebHomeLinkDetail.getSettings().setJavaScriptEnabled(true);
        mWebHomeLinkDetail.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebHomeLinkDetail.getSettings().setDomStorageEnabled(true);
        //以下配置不适用8。0
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        mWvActivityTicketShop.getSettings().setSafeBrowsingEnabled(false);
//                    }
        mWebHomeLinkDetail.setWebViewClient(new WebViewClient(){
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

    @Override
    public void onBackPressed() {
        if (mWebHomeLinkDetail.canGoBack()) {
            mWebHomeLinkDetail.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public int getRootView() {
        return R.layout.activity_home_link_detail;
    }
}
