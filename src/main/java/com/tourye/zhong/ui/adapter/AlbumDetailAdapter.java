package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.PhotoBean;
import com.tourye.zhong.ui.activity.ImageDetailActivity;
import com.tourye.zhong.utils.GlideRoundTransform;
import com.tourye.zhong.views.ScaleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/26.
 * <p>
 * introduce:相册详情----瀑布流
 */

public class AlbumDetailAdapter extends RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<PhotoBean> mPhotoBeans=new ArrayList<>();

    public AlbumDetailAdapter(Context context, List<PhotoBean> photoBeans) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
        mPhotoBeans = photoBeans;
    }

    public void setPhotoBeans(List<PhotoBean> photoBeans) {
        mPhotoBeans = photoBeans;
        notifyDataSetChanged();
    }

    public void addPhotoBeans(List<PhotoBean> photoBeans) {
        int size = mPhotoBeans.size();
        mPhotoBeans.addAll(photoBeans);
//        notifyItemInserted(photoBeans.size());
        //防止recyclerview位置移动
        notifyItemRangeInserted(size,photoBeans.size());

    }

    @Override
    public AlbumDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_activity_album_detail, parent, false);
        return new AlbumDetailHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final AlbumDetailHolder holder, final int position) {
        PhotoBean photoBean = mPhotoBeans.get(position);
        ViewGroup.LayoutParams layoutParams = holder.mImgItemAlibumDetail.getLayoutParams();
        layoutParams.height=photoBean.getHeight();
        layoutParams.width=photoBean.getWidth();
        holder.mImgItemAlibumDetail.setLayoutParams(layoutParams);
//        holder.mImgItemAlibumDetail.setInitSize(photoBean.getWidth(),photoBean.getHeight());
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .dontAnimate()
                .transform(new GlideRoundTransform(BaseApplication.mApplicationContext, 5));
        Glide.with(BaseApplication.mApplicationContext).load(photoBean.getUrl()).apply(requestOptions).into(holder.mImgItemAlibumDetail);
        holder.mImgItemAlibumDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < mPhotoBeans.size(); i++) {
                    PhotoBean photoBeanTemp = mPhotoBeans.get(i);
                    strings.add(photoBeanTemp.getUrl());
                }
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("pos",position);
                intent.putStringArrayListExtra("data",strings);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mPhotoBeans.size();
    }

    public class AlbumDetailHolder extends RecyclerView.ViewHolder{
        private ScaleImageView mImgItemAlibumDetail;


        public AlbumDetailHolder(View itemView) {
            super(itemView);
            mImgItemAlibumDetail = (ScaleImageView) itemView.findViewById(R.id.img_item_alibum_detail);

        }
    }
}
