package com.tourye.zhong.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.beans.CreateDynamicBean;
import com.tourye.zhong.beans.SubmitDynamicBean;
import com.tourye.zhong.beans.UploadImageBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.FullyGridLayoutManager;
import com.tourye.zhong.ui.adapter.GridImageAdapter;
import com.tourye.zhong.ui.fragment.FindCommunityFragment;
import com.tourye.zhong.utils.PermissionDialogUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.Response;

/**
 * CreateDynamicActivity
 * author:along
 * 2018/9/26 下午2:46
 * <p>
 * 描述:发表动态
 */

public class CreateDynamicActivity extends BaseActivity {
    private EditText mEdtActivityCreateDynamic;
    private TextView mTvActivityCreateDynamic;
    private RecyclerView mRecyclerActivityCreateDynamic;


    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;

    private int index=0;//上传完成的图片索引
    private Map<Integer,Integer> mMap=new HashMap<>();
    private List<Integer> ids=new ArrayList<>();

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10086:
                    if (index==selectList.size()) {
                        for (int i = 0; i < index; i++) {
                            Integer integer = mMap.get(i);
                            ids.add(integer);
                        }
                        String content = mEdtActivityCreateDynamic.getText().toString();
                        submitDynamic(content,ids);
                    }
                    break;
            }
        }
    };

    @Override
    public void initView() {
        mTvTitle.setText("发表动态");
        mTvCertain.setTextColor(getResources().getColor(R.color.color_font_red));
        mTvCertain.setVisibility(View.VISIBLE);
        mTvCertain.setText("发表");
        mTvCertain.setTextColor(Color.parseColor("#FF999999"));
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mEdtActivityCreateDynamic = (EditText) findViewById(R.id.edt_activity_create_dynamic);
        mTvActivityCreateDynamic = (TextView) findViewById(R.id.tv_activity_create_dynamic);
        mRecyclerActivityCreateDynamic = (RecyclerView) findViewById(R.id.recycler_activity_create_dynamic);
        mTvActivityCreateDynamic.setFocusable(true);//防止edittext抢占焦点

        mEdtActivityCreateDynamic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = mEdtActivityCreateDynamic.getText().toString();
                int length = s.length();
                mTvActivityCreateDynamic.setText(length + "/140");
                if (TextUtils.isEmpty(s)) {
                    if (selectList.size()<=0) {
                        mTvCertain.setTextColor(Color.parseColor("#FF999999"));
                        mTvCertain.setOnClickListener(null);
                    }
                }else{
                    mTvCertain.setTextColor(Color.parseColor("#FFCC1C24"));
                    mTvCertain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            index=0;
                            ids.clear();
                            String content = mEdtActivityCreateDynamic.getText().toString();
                            if (selectList.size()>0) {
                                uploadImage();
                            }else{
                                submitDynamic(content,new ArrayList<Integer>());
                            }
                        }
                    });
                }
            }
        });

        initRecycler();


    }

    public void uploadImage(){
        for (int i = 0; i < selectList.size(); i++) {
            LocalMedia localMedia = selectList.get(i);
            String compressPath = localMedia.getCompressPath();
            File file;
            if (!TextUtils.isEmpty(compressPath)) {
                file = new File(compressPath);
            }else{
                file = new File(localMedia.getPath());
            }

            Map<String,String> map=new HashMap<>();
            map.put("type","1");
            final int finalI = i;
            HttpUtils.getInstance().upload(Constants.UPLOAD_IMAGE, map, "file", file, new HttpCallback<UploadImageBean>() {
                @Override
                public void onSuccessExecute(UploadImageBean uploadImageBean) {
                    if (uploadImageBean.getStatus()==0) {
                        synchronized (String.class){
                            UploadImageBean.DataBean data = uploadImageBean.getData();
                            int id = data.getId();
                            mMap.put(finalI,id);
                            index++;
                            if (index==selectList.size()) {
                                mHandler.sendEmptyMessage(10086);
                            }
                        }
                    }
                }
            });
        }
    }

    //上传动态
    public void submitDynamic(String content, List<Integer> ids){
        SubmitDynamicBean submitDynamicBean = new SubmitDynamicBean();
        submitDynamicBean.setContent(content);
        submitDynamicBean.setImages(ids);
        Gson gson = new Gson();
        String json = gson.toJson(submitDynamicBean);

        HttpUtils.getInstance().post_json(Constants.CREATE_DYNAMIC, json, new HttpCallback<CreateDynamicBean>() {
            @Override
            public void onSuccessExecute(CreateDynamicBean createDynamicBean) {
                if (createDynamicBean.getStatus()==0) {
                    //刷新动态的列表
                    FindCommunityFragment.UpdateDynamicBean updateDynamicBean = new FindCommunityFragment.UpdateDynamicBean();
                    updateDynamicBean.setUpdate_list(true);
                    EventBus.getDefault().post(updateDynamicBean);

                    Toast.makeText(CreateDynamicActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onErrorResponse(Response response) {
                super.onErrorResponse(response);
            }
        });

    }

    public void initRecycler() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerActivityCreateDynamic.setLayoutManager(manager);
        adapter = new GridImageAdapter(mActivity, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(9);
        mRecyclerActivityCreateDynamic.setAdapter(adapter);
        //条目删除监听
        adapter.setOnItemDeleteListener(new GridImageAdapter.OnItemDeleteListener() {
            @Override
            public void OnItemDelete(List<LocalMedia> list) {
                selectList=list;
                String content = mEdtActivityCreateDynamic.getText().toString();
                if (selectList.size()<=0 && TextUtils.isEmpty(content)) {
                    mTvCertain.setTextColor(Color.parseColor("#FF999999"));
                    mTvCertain.setOnClickListener(null);
                }
            }
        });
        //条目点击监听
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(mActivity).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(mActivity).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(mActivity).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    /**
     * 获取图片选择结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    //更新发表状态
                    if (selectList.size()>0) {
                        mTvCertain.setTextColor(Color.parseColor("#FFCC1C24"));
                        mTvCertain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                index=0;
                                ids.clear();
                                String content = mEdtActivityCreateDynamic.getText().toString();
                                if (selectList.size()>0) {
                                    uploadImage();
                                }else{
                                    submitDynamic(content,new ArrayList<Integer>());
                                }
                            }
                        });
                    }
                    break;
            }
        }
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            RxPermissions rxPermissions = new RxPermissions(mActivity);
            rxPermissions
                    .request(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                goAlbum();
                            } else {
                                PermissionDialogUtil.showPermissionDialog(mActivity, "缺少存储权限，请前往手机设置开启");
                            }
                        }
                    });

        }

    };

    //跳转相册
    public void goAlbum() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
                //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .cropCompressQuality(20)// 裁剪压缩质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled(true) // 裁剪是否可旋转图片
                //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }


    @Override
    public void initData() {

    }

    @Override
    public int getRootView() {
        return R.layout.activity_create_dynamic;
    }
}
