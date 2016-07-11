package com.lewa.bl.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.RemoteException;

import com.lewa.bl.IUpgradeApkInterface;
import com.lewa.bl.Tools.ApkUtils;

public class ElegantUpgradeService extends Service {


    public ElegantUpgradeService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void UpgradeApk(String pkgName, boolean IsSilentInstallation) {
        PackageInfo mPackageInfo = ApkUtils.getInstalledApkPackageInfo(this, pkgName);
        if(mPackageInfo == null) {

        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    static class UpgradeServiceStub extends IUpgradeApkInterface.Stub {

        private ElegantUpgradeService sService;

        UpgradeServiceStub(ElegantUpgradeService sService) {
            this.sService = sService;
        }

        @Override
        public void UpgradeApk(String pkgName, boolean IsSilentInstallation) throws RemoteException {
            sService.UpgradeApk(pkgName, IsSilentInstallation);
        }
    }

    private final IBinder mBinder = new UpgradeServiceStub(this);

}
