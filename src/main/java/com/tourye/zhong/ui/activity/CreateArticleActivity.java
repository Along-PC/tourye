package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.beans.CommonBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * CreateArticleActivity
 * author:along
 * 2018/9/26 下午2:13
 *
 * 描述:创建文章页面
 */

public class CreateArticleActivity extends BaseActivity {
    private TextView mTvCreateArticleIntro;
    private TextView mTvCreateArticleCopy;
    private EditText mEditCreateArticleCopy;
    private TextView mTvActivityCreateArticleSubmit;
    private LinearLayout mLlActivityCreateArticleHelp;

    @Override
    public void initView() {
        mTvTitle.setText("创建文章");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTvCreateArticleIntro = (TextView) findViewById(R.id.tv_create_article_intro);
        mTvCreateArticleCopy = (TextView) findViewById(R.id.tv_create_article_copy);
        mEditCreateArticleCopy = (EditText) findViewById(R.id.edit_create_article_copy);
        mTvActivityCreateArticleSubmit = (TextView) findViewById(R.id.tv_activity_create_article_submit);
        mLlActivityCreateArticleHelp = (LinearLayout) findViewById(R.id.ll_activity_create_article_help);

    }

    @Override
    public void initData() {
        mTvActivityCreateArticleSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEditCreateArticleCopy.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    submitArticle(content);
                }else{
                    Toast.makeText(mActivity, "请输入链接", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mLlActivityCreateArticleHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, CommonWebActivity.class);
                intent.putExtra("data","https://mp.weixin.qq.com/s/fEnZzMewj8F74XvpIRfC1w");
                startActivity(intent);
            }
        });
    }

    /**
     * 上传文章
     * @param url 文章链接
     */
    public void submitArticle(String url){
        Map<String,String> map=new HashMap<>();
        map.put("url",url);
        HttpUtils.getInstance().post(Constants.CREATE_ARTICLE, map, new HttpCallback<CommonBean>() {
            @Override
            public void onSuccessExecute(CommonBean commonBean) {
                if (commonBean.getStatus()==0) {
                    Toast.makeText(mActivity, "提交完成，等待审核", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(mActivity, "上传失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getRootView() {
        return R.layout.activity_create_article;
    }
}
