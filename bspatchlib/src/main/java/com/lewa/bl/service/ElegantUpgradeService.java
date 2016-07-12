package com.lewa.bl.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lewa.bl.IUpgradeApkInterface;
import com.lewa.bl.IUpgradeStateListen;
import com.lewa.bl.Tools.OKHttpHelper;
import com.lzy.okhttputils.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class ElegantUpgradeService extends Service {

    private IUpgradeStateListen mIUpgradeStateListen;

    @Override
    public void onCreate() {
        super.onCreate();
        OKHttpHelper.OkHttpInit(getApplication());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public void toAcquireUpgradeInfo(String pkgName)  {
        OKHttpHelper.doPostUpgradeInfo(pkgName, new StringCallback() {

            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(isFromCache, call, response, e);

            }

            @Override
            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                try {
                    mIUpgradeStateListen.toAcquireUpgradeInfoComplete("com.lewa", "2.2", "213sdfsdf", s,"asdw3d");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setUpgradeStateListen(IBinder binder) {
        mIUpgradeStateListen = IUpgradeStateListen.Stub.asInterface(binder);
    }



    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
       // return null;
    }

    static class UpgradeServiceStub extends IUpgradeApkInterface.Stub {

        private ElegantUpgradeService sService;

        UpgradeServiceStub(ElegantUpgradeService sService) {
            this.sService = sService;
        }


        @Override
        public void toAcquireUpgradeInfo(String pkgName)  {
            sService.toAcquireUpgradeInfo(pkgName);
        }

        public void setUpgradeStateListen(IBinder binder) {
            sService.setUpgradeStateListen(binder);
        }


    }

    private final IBinder mBinder = new UpgradeServiceStub(this);

}
