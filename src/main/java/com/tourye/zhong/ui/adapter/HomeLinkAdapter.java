package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.HomeLinkBean;
import com.tourye.zhong.ui.activity.HomeLinkDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/10/29.
 * <p>
 * introduce:
 */

public class HomeLinkAdapter extends RecyclerView.Adapter<HomeLinkAdapter.HomeLinkHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<HomeLinkBean.DataBean> mDataBeans=new ArrayList<>();

    public HomeLinkAdapter(Context context, List<HomeLinkBean.DataBean> dataBeans) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
        mDataBeans = dataBeans;
    }

    @Override
    public HomeLinkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeLinkHolder(mLayoutInflater.inflate(R.layout.item_fragment_signup_link,parent,false));
    }

    @Override
    public void onBindViewHolder(HomeLinkHolder holder, int position) {
        final HomeLinkBean.DataBean dataBean = mDataBeans.get(position);
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getImage()).into(holder.mTvItemSignupLinkIcon);
        holder.mTvItemSignupLinkTitle.setText(dataBean.getText());
        holder.mLlItemSignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HomeLinkDetailActivity.class);
                intent.putExtra("data",dataBean.getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    public class HomeLinkHolder extends RecyclerView.ViewHolder{
        private LinearLayout mLlItemSignupLink;
        private ImageView mTvItemSignupLinkIcon;
        private TextView mTvItemSignupLinkTitle;


        public HomeLinkHolder(View itemView) {
            super(itemView);
            mLlItemSignupLink = (LinearLayout) itemView.findViewById(R.id.ll_item_signup_link);
            mTvItemSignupLinkIcon = (ImageView) itemView.findViewById(R.id.tv_item_signup_link_icon);
            mTvItemSignupLinkTitle = (TextView) itemView.findViewById(R.id.tv_item_signup_link_title);

        }
    }
}
