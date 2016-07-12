// IUpgradeStateListen.aidl
package com.lewa.bl;

// Declare any non-default types here with import statements

interface IUpgradeStateListen {

  void  toAcquireUpgradeInfoError(String errorInfo);
  void  toAcquireUpgradeInfoComplete(String pkgName, String versionInfo, String rtNewApkMD5, String upPathAddress, String newApkAddress);
}
