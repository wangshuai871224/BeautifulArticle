package com.example.dllo.hodgepodge.listener;

/**
 * Created by shuaiwang on 16/12/20.
 */

public interface NetCallBack<T> {

    // 请求成功
    void onResponse(T bean);
    // 请求失败
    void onError(Exception e);

}
