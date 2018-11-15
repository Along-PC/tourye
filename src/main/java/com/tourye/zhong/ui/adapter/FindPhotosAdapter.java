package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
import com.tourye.zhong.beans.AlbumListBean;
import com.tourye.zhong.ui.activity.ChildAlbumActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/26.
 * <p>
 * introduce:发现--相册适配器
 */

public class FindPhotosAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<AlbumListBean.DataBean> mDataBeans=new ArrayList<>();

    public FindPhotosAdapter(Context context, List<AlbumListBean.DataBean> dataBeans) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
        mDataBeans = dataBeans;
    }

    public void setDataBeans(List<AlbumListBean.DataBean> dataBeans) {
        mDataBeans = dataBeans;
        notifyDataSetChanged();
    }

    public void addDataBeans(List<AlbumListBean.DataBean> dataBeans) {
        mDataBeans.addAll(dataBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataBeans.size();
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
        FindPhotosHolder findPhotosHolder=null;
        if (view==null) {
            view=mLayoutInflater.inflate(R.layout.item_fragment_find_photos,viewGroup,false);
            findPhotosHolder=new FindPhotosHolder(view);
            view.setTag(findPhotosHolder);
        }else{
            findPhotosHolder= (FindPhotosHolder) view.getTag();
        }
        final AlbumListBean.DataBean dataBean = mDataBeans.get(i);
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.holder).error(R.drawable.holder);
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getCover()).apply(requestOptions).into(findPhotosHolder.mImgItemFragmentFindPhotos);
        findPhotosHolder.mTvItemFragmentFindPhotosCount.setText(dataBean.getCount()+"");
        findPhotosHolder.mTvItemFragmentFindPhotosTitle.setText(dataBean.getName());
        findPhotosHolder.mCardItemFragmentFindPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ChildAlbumActivity.class);
                intent.putExtra("data",dataBean.getId()+"");
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    public class FindPhotosHolder{
        private CardView mCardItemFragmentFindPhotos;
        private ImageView mImgItemFragmentFindPhotos;
        private TextView mTvItemFragmentFindPhotosTitle;
        private TextView mTvItemFragmentFindPhotosCount;



        public FindPhotosHolder(View view){
            mCardItemFragmentFindPhotos = (CardView) view.findViewById(R.id.card_item_fragment_find_photos);
            mImgItemFragmentFindPhotos = (ImageView) view.findViewById(R.id.img_item_fragment_find_photos);
            mTvItemFragmentFindPhotosTitle = (TextView) view.findViewById(R.id.tv_item_fragment_find_photos_title);
            mTvItemFragmentFindPhotosCount = (TextView) view.findViewById(R.id.tv_item_fragment_find_photos_count);

        }
    }
}
