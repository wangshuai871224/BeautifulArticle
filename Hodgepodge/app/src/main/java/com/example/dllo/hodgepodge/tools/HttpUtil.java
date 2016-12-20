package com.example.dllo.hodgepodge.tools;

import com.example.dllo.hodgepodge.listener.NetCallBack;

/**
 * Created by shuaiwang on 16/12/20.
 */

public class HttpUtil {

    // 画报一级界面数据
    public static <T> void getRequest(String url, Class<T> mClass, NetCallBack<T> netCallBack) {
        OkHttpManager.getInstance().get(url, mClass, netCallBack);
    }

    // 画报二级页面拼接
    public static <T> void connectRequest(int page, Class<T> mClass, NetCallBack<T> netCallBack) {
        String url = URLValues.PICTORIAL_ITEM_BEFORE + page + URLValues.PICTORIAL_ITEM_AFTER;
        OkHttpManager.getInstance().get(url, mClass, netCallBack);
    }


}
