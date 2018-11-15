package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.utils.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/14.
 * <p>
 * introduce:首页图片轮播适配器
 */

public class MainImageAdapter extends RecyclerView.Adapter<MainImageAdapter.MainImageHolder> {

    private Context mContext;
    private List<String> mImages=new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public MainImageAdapter(Context context, List<String> images) {
        mContext = context;
        mImages = images;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public MainImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_activity_main_image, parent, false);
        MainImageHolder mainImageHolder = new MainImageHolder(inflate);
        return mainImageHolder;
    }

    @Override
    public void onBindViewHolder(MainImageHolder holder, int position) {
        if (mImages.size()==0) {
            return;
        }
        int newPos=position%mImages.size();
        String s = mImages.get(newPos);
        RequestOptions requestOptions=new RequestOptions();
//        requestOptions.override(300,200);
        requestOptions.transform(new GlideRoundTransform(BaseApplication.mApplicationContext,5));
        Glide.with(BaseApplication.mApplicationContext).load(mImages.get(newPos)).apply(requestOptions).into(holder.mImgItemActivityMainImage);

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class MainImageHolder extends RecyclerView.ViewHolder{
        private ImageView mImgItemActivityMainImage;

        public MainImageHolder(View itemView) {
            super(itemView);
            mImgItemActivityMainImage = (ImageView) itemView.findViewById(R.id.img_item_activity_main_image);
        }
    }

}
