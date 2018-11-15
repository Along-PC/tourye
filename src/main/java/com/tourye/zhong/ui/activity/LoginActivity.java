package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tac.TACApplication;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.beans.LoginBean;
import com.tourye.zhong.beans.VerifyCodeBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.utils.SaveUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginActivity
 * author:along
 * 2018/9/18 下午2:02
 * <p>
 * 描述:登录界面
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvActivityLoginCasual;
    private LinearLayout mLlActivityLoginArea;
    private EditText mEdtActivityLoginPhone;
    private EditText mEdtActivityLoginCode;
    private TextView mTvActivityLoginGet;
    private TextView mTvActivityLogin;
    private TextView mTvActivityLoginVoice;
    private TextView mTvActivityLoginArea;
    private TextView mTvActivityLoginAreacode;
    private int mArea = 0;//区域选择编码  0-大陆  1-香港  2-澳门  3-台湾
    private String[] area = {"中国大陆", "中国香港", "中国澳门", "中国台湾"};
    private String[] areaCode = {"86", "852", "853", "886"};
    private int verify_code_time = 60;//短信验证码下次可用时间
    private LinearLayout mLlActivityLogin;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    verify_code_time--;
                    if (verify_code_time > 0) {
                        mTvActivityLoginGet.setText("剩余"+verify_code_time + "s");
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        verify_code_time=60;
                        mTvActivityLoginGet.setText("获取动态密码");
                        mTvActivityLoginGet.setOnClickListener(LoginActivity.this);
                    }
                    break;
            }
        }
    };

    @Override
    public void initView() {
        mTvActivityLoginCasual = (TextView) findViewById(R.id.tv_activity_login_casual);
        mLlActivityLoginArea = (LinearLayout) findViewById(R.id.ll_activity_login_area);
        mEdtActivityLoginPhone = (EditText) findViewById(R.id.edt_activity_login_phone);
        mEdtActivityLoginCode = (EditText) findViewById(R.id.edt_activity_login_code);
        mTvActivityLoginGet = (TextView) findViewById(R.id.tv_activity_login_get);
        mTvActivityLogin = (TextView) findViewById(R.id.tv_activity_login);
        mTvActivityLoginVoice = (TextView) findViewById(R.id.tv_activity_login_voice);
        mTvActivityLoginArea = (TextView) findViewById(R.id.tv_activity_login_area);
        mTvActivityLoginAreacode = (TextView) findViewById(R.id.tv_activity_login_areacode);
        mLlActivityLogin = (LinearLayout) findViewById(R.id.ll_activity_login);

        setListener();
    }

    private void setListener() {
        mLlActivityLoginArea.setOnClickListener(this);
        mTvActivityLoginGet.setOnClickListener(this);
        mEdtActivityLoginPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String phone = editable.toString();
                String code = mEdtActivityLoginCode.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    mTvActivityLogin.setSelected(true);
                    mTvActivityLogin.setOnClickListener(LoginActivity.this);
                } else {
                    mTvActivityLogin.setSelected(false);
                    mTvActivityLogin.setOnClickListener(null);
                }
            }
        });
        mEdtActivityLoginCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String code = editable.toString();
                String phone = mEdtActivityLoginPhone.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    mTvActivityLogin.setSelected(true);
                    mTvActivityLogin.setOnClickListener(LoginActivity.this);
                } else {
                    mTvActivityLogin.setSelected(false);
                    mTvActivityLogin.setOnClickListener(null);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mArea = data.getIntExtra("area", 0);
            mTvActivityLoginArea.setText(area[mArea]);
            mTvActivityLoginAreacode.setText("+" + areaCode[mArea]);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_activity_login_area:
                Intent intent = new Intent(mActivity, AreaActivity.class);
                startActivityForResult(intent, 10086);
                break;
            case R.id.tv_activity_login_get:
                getVerifyCode();
                break;
            case R.id.tv_activity_login:
                login();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLlActivityLogin!=null) {
            mLlActivityLogin.setBackground(null);
        }
        if (mTvActivityLogin!=null) {
            mTvActivityLogin.setBackground(null);
        }
        if (mEdtActivityLoginPhone!=null) {
            mEdtActivityLoginPhone.setBackground(null);
        }
        if (mLlActivityLoginArea!=null) {
            mLlActivityLoginArea.setBackground(null);
        }
    }

    /**
     * 登录
     */
    private void login() {
        String phone = mEdtActivityLoginPhone.getText().toString();
        String code = mEdtActivityLoginCode.getText().toString();
        String device_id = TACApplication.getDeviceId();//信鸽推送token
        Map<String,String> map=new HashMap<>();
        map.put("area_code",areaCode[mArea]);
        map.put("phone",phone);
        map.put("code",code);
        map.put("device_id", device_id);
        map.put("type", "android");
        HttpUtils.getInstance().post(Constants.USER_LOGIN, map, new HttpCallback<LoginBean>() {
            @Override
            public void onSuccessExecute(LoginBean loginBean) {
                if (loginBean.getStatus()==0) {
                    String data = loginBean.getData();
                    if (!TextUtils.isEmpty(data)) {
                        SaveUtil.putString("Authorization",data);
                        boolean hasLogin = SaveUtil.getBoolean("hasLogin", false);
                        if (hasLogin) {
                            startActivity(new Intent(mActivity,MainActivity.class));
                            finish();
                        }else{
                            startActivity(new Intent(mActivity,GuideActivity.class));
                            finish();
                        }

                    }
                }
            }
        });
    }

    /**
     * 获取验证码code
     */
    private void getVerifyCode() {
        String phone = mEdtActivityLoginPhone.getText().toString();
        if (!TextUtils.isEmpty(phone)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("phone", phone);
            map.put("area_code", areaCode[mArea]);
            HttpUtils.getInstance().post(Constants.GET_VERIFY_CODE, map, new HttpCallback<VerifyCodeBean>() {
                @Override
                public void onSuccessExecute(VerifyCodeBean verifyCodeBean) {
                    if (verifyCodeBean.getStatus() == 0) {
                        Toast.makeText(mActivity, "验证码发送成功", Toast.LENGTH_SHORT).show();
                        mTvActivityLoginGet.setText("剩余"+verify_code_time + "s");
                        mTvActivityLoginGet.setOnClickListener(null);
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                }
            });
        }
    }
}
