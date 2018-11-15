package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
/**
 * AreaActivity
 * author:along
 * 2018/9/18 下午5:06
 *
 * 描述:电话地区选择
 */

public class AreaActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLlActivityAreaMainland;
    private LinearLayout mLlActivityAreaHk;
    private LinearLayout mLlActivityAreaMacao;
    private LinearLayout mLlActivityAreaTw;
    private Intent mIntent;

    private int type=0;//0-大陆  1-香港  2-澳门  3-台湾


    @Override
    public void initView() {
        mImgReturn.setOnClickListener(this);
        mTvTitle.setText("选择区号");

        mLlActivityAreaMainland = (LinearLayout) findViewById(R.id.ll_activity_area_mainland);
        mLlActivityAreaHk = (LinearLayout) findViewById(R.id.ll_activity_area_hk);
        mLlActivityAreaMacao = (LinearLayout) findViewById(R.id.ll_activity_area_macao);
        mLlActivityAreaTw = (LinearLayout) findViewById(R.id.ll_activity_area_tw);

        mLlActivityAreaMainland.setOnClickListener(this);
        mLlActivityAreaHk.setOnClickListener(this);
        mLlActivityAreaMacao.setOnClickListener(this);
        mLlActivityAreaTw.setOnClickListener(this);
    }

    @Override
    public void initData() {

        mIntent = getIntent();

    }

    @Override
    public int getRootView() {
        return R.layout.activity_area;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
            case R.id.ll_activity_area_mainland:
                type=0;
                mIntent.putExtra("area",type);
                setResult(RESULT_OK,mIntent);
                finish();
                break;
            case R.id.ll_activity_area_hk:
                type=1;
                mIntent.putExtra("area",type);
                setResult(RESULT_OK,mIntent);
                finish();
                break;
            case R.id.ll_activity_area_macao:
                type=2;
                mIntent.putExtra("area",type);
                setResult(RESULT_OK,mIntent);
                finish();
                break;
            case R.id.ll_activity_area_tw:
                type=3;
                mIntent.putExtra("area",type);
                setResult(RESULT_OK,mIntent);
                finish();
                break;
        }
    }
}
