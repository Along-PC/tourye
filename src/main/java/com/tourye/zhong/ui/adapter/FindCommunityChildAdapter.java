package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.ui.activity.ImageDetailActivity;
import com.tourye.zhong.utils.DensityUtils;

import java.util.ArrayList;

/**
 * Created by longlongren on 2018/9/27.
 * <p>
 * introduce:发现---社区图片适配器
 */

public class FindCommunityChildAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mStrings = new ArrayList<>();

    public FindCommunityChildAdapter(Context context, ArrayList<String> strings) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mStrings = strings;
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public Object getItem(int i) {
        return mStrings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        FindCommunityChildHolder findCommunityChildHolder = null;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_community_image, viewGroup, false);
            findCommunityChildHolder = new FindCommunityChildHolder(view);
            view.setTag(findCommunityChildHolder);
        } else {
            findCommunityChildHolder = (FindCommunityChildHolder) view.getTag();
        }
        int size = mStrings.size();
        //屏幕宽度
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        switch (size) {
            case 1:
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(widthPixels, widthPixels);
                findCommunityChildHolder.mImgItemCommunityImage.setLayoutParams(layoutParams);
                break;
            case 2:
            case 3:
            case 4:
                int widthPixelTwo = widthPixels - DensityUtils.dp2px(mContext, 10);
                widthPixelTwo=widthPixelTwo/2;
                RelativeLayout.LayoutParams layoutParamsTwo = new RelativeLayout.LayoutParams(widthPixelTwo, widthPixelTwo);
                findCommunityChildHolder.mImgItemCommunityImage.setLayoutParams(layoutParamsTwo);
                break;
            default:
                int widthPixelThree = widthPixels - DensityUtils.dp2px(mContext, 10)*2;
                widthPixelThree=widthPixelThree/3;
                RelativeLayout.LayoutParams layoutParamsThree = new RelativeLayout.LayoutParams(widthPixelThree, widthPixelThree);
                findCommunityChildHolder.mImgItemCommunityImage.setLayoutParams(layoutParamsThree);
                break;
        }
        final String url = mStrings.get(i);
        findCommunityChildHolder.mImgItemCommunityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putStringArrayListExtra("data",mStrings);
                intent.putExtra("pos",i);
                mContext.startActivity(intent);
            }
        });
        RequestOptions override = new RequestOptions();
        override.override(300,300);
        Glide.with(BaseApplication.mApplicationContext).load(mStrings.get(i)).apply(override).into(findCommunityChildHolder.mImgItemCommunityImage);
//        Glide.with(BaseApplication.mApplicationContext).asBitmap().load(mStrings.get(i)).into(new SimpleTarget<Bitmap>() {
//
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                int byteCount = resource.getByteCount();
//                Log.e("along","byteCount:"+byteCount+"       当前条目压缩钱："+i);
//                Log.e("along","宽："+resource.getWidth()+"高："+resource.getHeight()+"       当前条目："+i);
//            }
//        });
//
//        Glide.with(BaseApplication.mApplicationContext).asBitmap().load(mStrings.get(i)).apply(override).into(new SimpleTarget<Bitmap>() {
//
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                int byteCount = resource.getByteCount();
//                Log.e("along","byteCount:"+byteCount+"       当前条目压缩后："+i);
//                Log.e("along","宽："+resource.getWidth()+"高："+resource.getHeight()+"       当前条目："+i);
//            }
//        });
        return view;
    }

    public class FindCommunityChildHolder {
        private ImageView mImgItemCommunityImage;

        public FindCommunityChildHolder(View view) {
            mImgItemCommunityImage = (ImageView) view.findViewById(R.id.img_item_community_image);
        }
    }
}
