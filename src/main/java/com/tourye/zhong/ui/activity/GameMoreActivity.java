package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;

/**
 * GameMoreActivity
 * author:along
 * 2018/10/29 下午2:37
 *
 * 描述:赛事---了解更多
 */

public class GameMoreActivity extends BaseActivity {

    private WebView mWebActivityGameMore;



    @Override
    public void initView() {
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mTvTitle.setText("了解更多");
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mWebActivityGameMore = (WebView) findViewById(R.id.web_activity_game_more);

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");

        if (!TextUtils.isEmpty(data)) {
            mWebActivityGameMore.getSettings().setJavaScriptEnabled(true);
            mWebActivityGameMore.loadDataWithBaseURL(null,data,"text/html", "utf-8",null);
            mWebActivityGameMore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            mWebActivityGameMore.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    imgReset();
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.toString());
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
        }

    }


    private void imgReset() {
        mWebActivityGameMore.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%';   " +
                "}" +
                "})()");
    }

    @Override
    public int getRootView() {
        return R.layout.activity_game_more;
    }
}
