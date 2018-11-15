package com.tourye.zhong.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

/**
 * Created by longlongren on 2018/8/21.
 * <p>
 * introduce:拍照工具类
 *
 */

public class CameraUtils {

    public static final int TAKEING_PHOTO=10010;
    public static final int GET_PHOTO=10011;
    public static final int PHOTO_CROP =10012;
    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wake";


    /**
     * 打开相机
     */
    public static void openCamera(Activity activity) {
        //创建一个file，用来存储拍照后的照片
        File parent = new File(ROOT_PATH);
        if (!parent.exists()) {
            parent.mkdir();
        }
        File outputfile = new File(parent,"taking.png");
        try {
            if (outputfile.exists()){
                outputfile.delete();//删除
            }
            outputfile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageuri ;
        if (Build.VERSION.SDK_INT >= 24){
            imageuri = FileProvider.getUriForFile(activity,
                    "com.tourye.wake", //可以是任意字符串
                    outputfile);
        }else{
            imageuri = Uri.fromFile(outputfile);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);//指定拍照保存位置，直接获取的容易失真
        activity.startActivityForResult(intent,TAKEING_PHOTO);
    }

    /**
     *
     * @param fromUri  相册返回来的uri
     * @param context
     * @return
     */
    public static Intent photoBeginCrop(Uri fromUri,Context context){
        try {
            //创建一个file，用来存储拍照后的照片
            File parent = new File(ROOT_PATH);
            if (!parent.exists()) {
                parent.mkdir();
            }
            //直接裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //设置裁剪之后的图片路径文件
            File cutfile = new File(CameraUtils.ROOT_PATH,
                    "cutcamera.png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri outUri = Uri.fromFile(cutfile);

            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop",true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", DensityUtils.dp2px(context,200));
            intent.putExtra("outputY",DensityUtils.dp2px(context,200));
            intent.putExtra("scale",true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data",false);
            if (fromUri != null) {
                intent.setDataAndType(fromUri, "image/*");
            }
            if (outUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 相机跳转裁剪
     */
    public static Intent cameraBeginCrop(Context context){
        try{
            //设置裁剪之后的图片路径文件
            File parent = new File(ROOT_PATH);
            if (!parent.exists()) {
                parent.mkdir();
            }
            File cutfile = new File(parent,"cutcamera.png");//随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = null; //相机返回来的 uri
            Uri outputUri = null; //裁剪之后保存的 uri

            //拍照留下的图片
            File camerafile = new File(parent,"taking.png");
            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(context,
                        "com.tourye.wake",
                        camerafile);
            } else {
                imageUri = Uri.fromFile(camerafile);
            }
            //把这个 uri 提供出去，就可以解析成 bitmap了
            //*****一定要用这个api，不能用FileProvider.getUriForFile**********
            outputUri = Uri.fromFile(cutfile);

            Intent intent = new Intent("com.android.camera.action.CROP");
            context.grantUriPermission(context.getPackageName(),outputUri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
            }
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop",true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", DensityUtils.dp2px(context,200));
            intent.putExtra("outputY",DensityUtils.dp2px(context,200));
            intent.putExtra("scale",true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data",false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

            return intent;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Uri getOriginalUri(Context context){
        File camerafile = new File(ROOT_PATH,"taking.png");
        Uri imageUri=null;
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(context,
                    "com.tourye.wake",
                    camerafile);
        } else {
            imageUri = Uri.fromFile(camerafile);
        }
        return imageUri;
    }

    public static Uri getFinalUri(){
        File cutfile = new File(ROOT_PATH,"cutcamera.png");//这里不可以再次进行删除操作了
        //把这个 uri 提供出去，就可以解析成 bitmap了
        //*****一定要用这个api，不能用FileProvider.getUriForFile**********
        Uri uri = Uri.fromFile(cutfile);

        return uri;

    }
}
