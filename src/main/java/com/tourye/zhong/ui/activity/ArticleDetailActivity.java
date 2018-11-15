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
 * ArticleDetailActivity
 * author:along
 * 2018/10/30 上午10:29
 *
 * 描述:文章详情页面
 */

public class ArticleDetailActivity extends BaseActivity {

    private WebView mWebActivityArticleDetail;


    @Override
    public void initView() {

        mTvTitle.setText("详情");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebActivityArticleDetail.canGoBack()) {
                    mWebActivityArticleDetail.goBack();
                    return;
                }
                finish();
            }
        });
        mWebActivityArticleDetail = (WebView) findViewById(R.id.web_activity_article_detail);

    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        if (!TextUtils.isEmpty(data)) {
            mWebActivityArticleDetail.loadUrl(data);
            mWebActivityArticleDetail.getSettings().setJavaScriptEnabled(true);
            mWebActivityArticleDetail.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWebActivityArticleDetail.getSettings().setDomStorageEnabled(true);
            //以下配置不适用8。0
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        mWvActivityTicketShop.getSettings().setSafeBrowsingEnabled(false);
//                    }
            mWebActivityArticleDetail.setWebViewClient(new WebViewClient(){
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
    public void onBackPressed() {
        if (mWebActivityArticleDetail.canGoBack()) {
            mWebActivityArticleDetail.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public int getRootView() {
        return R.layout.activity_article_detail;
    }
}
