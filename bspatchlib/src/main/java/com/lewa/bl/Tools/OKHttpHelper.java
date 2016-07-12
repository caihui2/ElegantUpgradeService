package com.lewa.bl.Tools;

import android.app.Application;
import android.support.annotation.Nullable;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chyang on 16-7-12.
 */
public class OKHttpHelper  {

    private static final String SERVER_URI = "http://server.jeasonlzy.com/OkHttpUtils/";
    public static final String URL_METHOD = SERVER_URI + "method";

    public static void OkHttpInit(Application mApplication) {
        OkHttpUtils.init(mApplication);
        OkHttpUtils.getInstance()//
                .debug("OkHttpUtils")
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS);
    }

    public static void doPostUpgradeInfo(String pkgName, StringCallback mStringCallback) {
        OkHttpUtils.post(URL_METHOD)
                .params("param1", "paramValue1")
                .execute(mStringCallback);
    }
}
