package com.example.dllo.hodgepodge.tools;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shuaiwang on 16/12/20.
 */

public class OkHttpManager {

    private static OkHttpManager manager;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;

    public OkHttpManager() {
        mHandler = new Handler(Looper.getMainLooper()); // 保持数据返回在主线程进行UI刷新
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();

    }

    public static OkHttpManager getInstance() {
        if (manager == null) {
            synchronized (OkHttpManager.class) {
                if (manager == null) {
                    manager = new OkHttpManager();
                }
            }
        }
        return manager;
    }

    public <T> void post(String url, Class<T> mClass, NetCallBack<T> netCallBack, HashMap<String, String> body) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String s : body.keySet()) {
            builder.add(s, body.get(s));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        postOkHttp(request, mClass, netCallBack);
    }

    private <T> void postOkHttp(Request request, final Class<T> mClass, final NetCallBack<T> netCallBack) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new ErrorRunnable<T>(netCallBack, e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 网络请求成功
                String result = response.body().string();
                try {
                    T bean = mGson.fromJson(result, mClass);
                    mHandler.post(new ResponseRunnable<T>(netCallBack, bean));
                }catch (Exception exception) {
                    mHandler.post(new ErrorRunnable<T>(netCallBack, exception));
                }

            }
        });
    }

    // 声明泛型
    public <T> void get(String url, final Class<T> mClass, final NetCallBack<T> callBack) {
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 网络请求失败
                mHandler.post(new ErrorRunnable<T>(callBack, e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 网络请求成功
                String result = response.body().string();
                try {
                    T bean = mGson.fromJson(result, mClass);
                    mHandler.post(new ResponseRunnable<T>(callBack, bean));
                }catch (Exception exception) {
                    exception.printStackTrace();
                    mHandler.post(new ErrorRunnable<T>(callBack, exception));
                }
            }
        });
    }

    // 为了省略成功和失败之后把数据传回主线程,而创建的类
    abstract class OkHttpRunnable<T> implements Runnable {

        protected NetCallBack<T> mNetCallBack;

        public OkHttpRunnable(NetCallBack netCallBack) {
            mNetCallBack = netCallBack;
        }
    }

    // 成功回调    继承上面父类
    class ResponseRunnable<T> extends OkHttpRunnable<T> {

        private T bean;
        public ResponseRunnable(NetCallBack netCallBack, T bean) {
            super(netCallBack);
            this.bean = bean;
        }

        @Override
        public void run() {
            mNetCallBack.onResponse(bean);
        }
    }

    class ErrorRunnable<T> extends OkHttpRunnable<T> {

        private Exception mException;
        public ErrorRunnable(NetCallBack netCallBack, Exception exception) {
            super(netCallBack);
            this.mException = exception;
        }

        @Override
        public void run() {
            mNetCallBack.onError(mException);
        }
    }


}
