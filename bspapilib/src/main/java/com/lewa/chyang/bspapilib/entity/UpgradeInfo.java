package com.lewa.chyang.bspapilib.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chyang on 16-7-12.
 */
public class UpgradeInfo implements Parcelable{

    private String errInfo;
    private String pkgName;
    private String versionInfo;
    private String rtNewApkMD5;
    private String upPathAddress;
    private String newApkAddress;


    public UpgradeInfo(String pkgName, String versionInfo, String rtNewApkMD5, String upPathAddress, String newApkAddress) {
        this.pkgName = pkgName;
        this.versionInfo = versionInfo;
        this.rtNewApkMD5 = rtNewApkMD5;
        this.upPathAddress = upPathAddress;
        this.newApkAddress = newApkAddress;
    }

    public UpgradeInfo(){

    }

    protected UpgradeInfo(Parcel in) {
        errInfo = in.readString();
        pkgName = in.readString();
        versionInfo = in.readString();
        rtNewApkMD5 = in.readString();
        upPathAddress = in.readString();
        newApkAddress = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(errInfo);
        dest.writeString(pkgName);
        dest.writeString(versionInfo);
        dest.writeString(rtNewApkMD5);
        dest.writeString(upPathAddress);
        dest.writeString(newApkAddress);
    }

    public static final Creator<UpgradeInfo> CREATOR = new Creator<UpgradeInfo>() {
        @Override
        public UpgradeInfo createFromParcel(Parcel in) {
            return new UpgradeInfo(in);
        }

        @Override
        public UpgradeInfo[] newArray(int size) {
            return new UpgradeInfo[size];
        }
    };

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getRtNewApkMD5() {
        return rtNewApkMD5;
    }

    public void setRtNewApkMD5(String rtNewApkMD5) {
        this.rtNewApkMD5 = rtNewApkMD5;
    }

    public String getUpPathAddress() {
        return upPathAddress;
    }

    public void setUpPathAddress(String upPathAddress) {
        this.upPathAddress = upPathAddress;
    }

    public String getNewApkAddress() {
        return newApkAddress;
    }

    public void setNewApkAddress(String newApkAddress) {
        this.newApkAddress = newApkAddress;
    }

    @Override
    public String toString() {
        return "UpgradeInfo{" +
                "errInfo='" + errInfo + '\'' +
                ", pkgName='" + pkgName + '\'' +
                ", versionInfo='" + versionInfo + '\'' +
                ", rtNewApkMD5='" + rtNewApkMD5 + '\'' +
                ", upPathAddress='" + upPathAddress + '\'' +
                ", newApkAddress='" + newApkAddress + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return pkgName.hashCode();
    }


}
