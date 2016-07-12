// IUpgradeApkInterface.aidl
package com.lewa.bl;

// Declare any non-default types here with import statements

interface IUpgradeApkInterface {

     void toAcquireUpgradeInfo(String pkgName);
    void setUpgradeStateListen(IBinder binder);
}
