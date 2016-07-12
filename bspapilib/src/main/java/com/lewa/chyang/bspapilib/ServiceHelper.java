package com.lewa.chyang.bspapilib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.lewa.bl.IUpgradeApkInterface;
import com.lewa.bl.IUpgradeStateListen;
import com.lewa.chyang.bspapilib.entity.UpgradeInfo;

import java.util.HashMap;

/**
 * Created by chyang on 16-7-11.
 */
public class ServiceHelper {

    private IUpgradeApkInterface sServices;
    private static HashMap<Context, ServiceBinder> sConnectionMap = new HashMap<Context, ServiceBinder>();

    private static final String  UPGRADE_SERVICE_CLASS_NAME = "com.lewa.bl.service.ElegantUpgradeService";
    private static final String UPGRADE_SERVICE_PACLAGE_NAME = "com.example.chyang.eus";

    /**
     * Access to updated information
     */
    public interface RequestUpgradeInfoListen {
        public void toAcquireUpgradeInfoError(String errorInfo);
        public void  toAcquireUpgradeInfoComplete(UpgradeInfo upgradeInfo);
    }

    private RequestUpgradeInfoListen mRequestUpgradeInfoListen;



    /**
     * @param context the use {@link Context}
     * @return service Token
     */
    public  ServiceToken bindToService(Activity context) {
        return bindToService(context, null);
    }

    /**
     *
     * @param context  the use {@link Context}
     * @param callBack the use {@link ServiceConnection}
     * @return  service Token {@link ServiceToken}
     */
    public ServiceToken bindToService(Activity context, ServiceConnection callBack) {
        Activity realActivity = context;
        if (realActivity == null) {
            realActivity = context;
        }
        ContextWrapper cw = new ContextWrapper(realActivity);
        Intent mIntent = new Intent();
        mIntent.setClassName(UPGRADE_SERVICE_PACLAGE_NAME, UPGRADE_SERVICE_CLASS_NAME);
        cw.startService(mIntent);
        ServiceBinder sb = new ServiceBinder(callBack);
        if (cw.bindService((mIntent), sb, 0)) {
            sConnectionMap.put(cw, sb);
            return new ServiceToken(cw);
        }
        return  null;
    }

    /**
     *
     * @param token service Token {@link ServiceToken}
     */
    public  void unbindFromService(ServiceToken token) {
        if (token == null) {
            return;
        }
        ContextWrapper cw = token.mWrappedContext;
        ServiceBinder sb = sConnectionMap.remove(cw);
        if (sb == null) {
            return;
        }
        cw.unbindService(sb);
        if (sConnectionMap.isEmpty()) {
           sServices = null;
        }
    }

    /**
     * Get a ContextWrapper {@link ContextWrapper}
     */
    public class ServiceToken {
        ContextWrapper mWrappedContext;

        ServiceToken(ContextWrapper context) {
            mWrappedContext = context;
        }
    }

    private class ServiceBinder implements ServiceConnection {

        private ServiceConnection mCallback;

        ServiceBinder(ServiceConnection mCallback) {
            this.mCallback = mCallback;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            sServices = IUpgradeApkInterface.Stub.asInterface(service);
            try {
                sServices.setUpgradeStateListen(new IUpgradeStateListens());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if(mCallback != null) {
                mCallback.onServiceConnected(name, service);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if(mCallback != null) {
                mCallback.onServiceDisconnected(name);
            }
            sServices = null;
        }
    }

    class IUpgradeStateListens extends IUpgradeStateListen.Stub {

        @Override
        public void toAcquireUpgradeInfoError(String errorInfo) throws RemoteException {
            if(mRequestUpgradeInfoListen != null) mRequestUpgradeInfoListen.toAcquireUpgradeInfoError(errorInfo);
        }

        @Override
       public void  toAcquireUpgradeInfoComplete(String pkgName, String versionInfo, String rtNewApkMD5, String upPathAddress, String newApkAddress){
            if(mRequestUpgradeInfoListen != null) {
                UpgradeInfo mUpgradeInfo = new UpgradeInfo(pkgName, versionInfo, rtNewApkMD5, upPathAddress, newApkAddress);
                mRequestUpgradeInfoListen.toAcquireUpgradeInfoComplete(mUpgradeInfo);
            }
        }
    }

    /**
     *
     * @param requestUpgradeInfoListen the use{@link RequestUpgradeInfoListen}
     */
    public void setRequestUpgradeInfoListen(RequestUpgradeInfoListen requestUpgradeInfoListen) {
        this.mRequestUpgradeInfoListen = requestUpgradeInfoListen;
    }

    /**
     * To request the server to pull update information, if you want to listen to the results of the return call
     * {@link ServiceHelper#setRequestUpgradeInfoListen(RequestUpgradeInfoListen)}
     * @param pkgName Ask for the name of the package
     */
    public void toAcquireUpgradeInfo(String pkgName) {
        if(sServices == null) throw  new NullPointerException(" service == null  please call bindToService() ");
        if(sServices != null) {
            try {
                sServices.toAcquireUpgradeInfo(pkgName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
