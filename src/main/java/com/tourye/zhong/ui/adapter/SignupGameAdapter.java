package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.RoadsListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/19.
 * <p>
 * introduce:报名页面赛事列表
 */

public class SignupGameAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<RoadsListBean.DataBean> mDataBeans=new ArrayList<>();

    public SignupGameAdapter(Context context) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDataBeans.size();
    }

    public void setDataBeans(List<RoadsListBean.DataBean> dataBeans) {
        mDataBeans = dataBeans;
    }

    @Override
    public Object getItem(int i) {
        return mDataBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MainGameHolder mainGameHolder=null;
        if (view==null) {
            view=mLayoutInflater.inflate(R.layout.item_fragment_signup_list,viewGroup,false);
            mainGameHolder=new MainGameHolder(view);
            view.setTag(mainGameHolder);
        }else{
            mainGameHolder= (MainGameHolder) view.getTag();
        }
        RoadsListBean.DataBean dataBean = mDataBeans.get(i);
        RequestOptions requestOptions=new RequestOptions();
//        requestOptions.override(600,300);
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getImage()).apply(requestOptions).into(mainGameHolder.mImgItemFragmentSignup);
        mainGameHolder.mTvItemFragmentSignupCount.setText(dataBean.getApplicant_count()+"人报名");
        mainGameHolder.mTvItemFragmentSignupEvent.setText(dataBean.getActivity_count()+"场赛事");
        mainGameHolder.mTvItemFragmentSignupAction.setText(dataBean.getAbstractX());
        mainGameHolder.mTvItemFragmentSignupTitle.setText(dataBean.getName());
        return view;
    }

    public class MainGameHolder{

        private TextView mTvItemFragmentSignupCount;
        private TextView mTvItemFragmentSignupTitle;
        private TextView mTvItemFragmentSignupAction;
        private TextView mTvItemFragmentSignupEvent;
        private ImageView mImgItemFragmentSignup;

        public MainGameHolder(View view){
            mTvItemFragmentSignupCount = (TextView) view.findViewById(R.id.tv_item_fragment_signup_count);
            mTvItemFragmentSignupTitle = (TextView) view.findViewById(R.id.tv_item_fragment_signup_title);
            mTvItemFragmentSignupAction = (TextView) view.findViewById(R.id.tv_item_fragment_signup_action);
            mTvItemFragmentSignupEvent = (TextView) view.findViewById(R.id.tv_item_fragment_signup_event);
            mImgItemFragmentSignup = (ImageView) view.findViewById(R.id.img_item_fragment_signup);

        }
    }
}
