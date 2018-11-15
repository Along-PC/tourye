package com.tourye.zhong.net;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Title:HttpCallback
 * <p>
 * Description:网络请求回调
 * </p>
 * Author dragon.
 * Date 2018/4/16 11:29
 */

public abstract class HttpCallback<T> implements Callback {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(Call call, IOException e) {

        Log.d("onFailure", "#######################################################################");
        Log.d("onFailure", "错误信息："+e.getMessage());
        Log.d("onFailure", "#######################################################################");

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailureExecute();
            }
        });

    }

    /**
     * 网络请求失败
     */
    public void onFailureExecute(){

    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        if (response.isSuccessful()) {

            Log.d("onResponse", "#######################################################################");
            final String responseText = response.body().string();
            Log.d("onResponse", responseText);
            Logger.json(responseText);
            Log.d("onResponse", "#######################################################################");

            Gson gson = new Gson();
            //获取泛型的class对象
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            final T t = gson.fromJson(responseText, entityClass);

            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    onPreExcute();

                    onForceExcute(responseText);

                    onSuccessExecute(t);

                }
            });

        } else {

            Log.d("onResponse", "#######################################################################");
            Log.d("onResponse", "响应码：" + response.code() + "响应信息：" + response.message());
            Log.d("onResponse", "#######################################################################");
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    onPreExcute();

                    onErrorResponse(response);

                }
            });

        }
    }

    /**
     * 异地登录，强制退出
     * @param response
     */
    private void onForceExcute(String response) {
        try {
//            JSONObject jsonObject = new JSONObject(response);
//            int status = jsonObject.getInt("status");
//            String message = jsonObject.getString("message");
//            if (status==10001) {
//                if (TextUtils.isEmpty(SaveUtil.getString("Authorization",""))) {
//                    Toast.makeText(BaseApplication.mApplicationContext, message+"", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(BaseApplication.mApplicationContext, LoginActivity.class);
//                    intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK );
//                    BaseApplication.mApplicationContext.startActivity(intent);
//                }
//                SaveUtil.putString("Authorization", "");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 得到服务器响应，统一处理一些操作，比如：终止刷新、加载更多。。。
     */
    public void onPreExcute(){

    }

    /**
     * 网络请求成功，http状态码不为200
     */
    public void onErrorResponse(Response response){
//        if (response.code()==401) {
//            SaveUtil.putString("Authorization", "");
//            Intent intent = new Intent(BaseApplication.mApplicationContext, LoginActivity.class);
//            intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK );
//            BaseApplication.mApplicationContext.startActivity(intent);
//        }

    }

    /**
     * 网络请求成功，http状态码200
     * @param t
     */
    public abstract void onSuccessExecute(T t);

}
