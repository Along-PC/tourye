package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.ChildAlbumBean;
import com.tourye.zhong.ui.activity.AlbumDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/26.
 * <p>
 * introduce:子相册适配器
 */

public class ChildAlbumAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ChildAlbumBean.DataBean.AlbumsBean> mAlbumsBeans=new ArrayList<>();

    public ChildAlbumAdapter(Context context, List<ChildAlbumBean.DataBean.AlbumsBean> albumsBeans) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
        mAlbumsBeans = albumsBeans;
    }

    //下拉刷新
    public void setAlbumsBeans(List<ChildAlbumBean.DataBean.AlbumsBean> albumsBeans) {
        mAlbumsBeans = albumsBeans;
        notifyDataSetChanged();
    }

    //加载更多
    public void addAlbumsBeans(List<ChildAlbumBean.DataBean.AlbumsBean> albumsBeans) {
        mAlbumsBeans.addAll(albumsBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAlbumsBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mAlbumsBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChildAlbumHolder childAlbumHolder=null;
        if (view==null) {
            view=mLayoutInflater.inflate(R.layout.item_activity_child_album,viewGroup,false);
            childAlbumHolder=new ChildAlbumHolder(view);
            view.setTag(childAlbumHolder);
        }else{
            childAlbumHolder= (ChildAlbumHolder) view.getTag();
        }
        final ChildAlbumBean.DataBean.AlbumsBean albumsBean = mAlbumsBeans.get(i);
        RequestOptions requestOptions = new RequestOptions().error(R.drawable.holder).placeholder(R.drawable.holder);
        Glide.with(BaseApplication.mApplicationContext).load(albumsBean.getCover()).apply(requestOptions).into(childAlbumHolder.mImgItemChildAlbum);
        childAlbumHolder.mTvItemChildAlbum.setText(albumsBean.getName());
        childAlbumHolder.mRlItemChildAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlbumDetailActivity.class);
                intent.putExtra("data",albumsBean.getId()+"");
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    public class ChildAlbumHolder{
        private RelativeLayout mRlItemChildAlbum;
        private ImageView mImgItemChildAlbum;
        private TextView mTvItemChildAlbum;

        public ChildAlbumHolder(View view){
            mRlItemChildAlbum = (RelativeLayout) view.findViewById(R.id.rl_item_child_album);
            mImgItemChildAlbum = (ImageView) view.findViewById(R.id.img_item_child_album);
            mTvItemChildAlbum = (TextView) view.findViewById(R.id.tv_item_child_album);
        }
    }
}
