package com.example.bl.Tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chyang on 16-7-8.
 */
public class ApkUtils {

  public static final boolean DEBUG = true;

  private static final String TAG = "DEBUG:" + DEBUG +"ApkUtils: " ;



  static {
    System.loadLibrary("Patcher");
  }

  /**
   *
   * @param oldApkFilePath   The path of the old version apk
   * @param newApkFilePath   The path of the new version apk
   * @param patchFilePath  After the synthesis path of the new apk
   * @return 0 success。 If flash back Make sure if the file read and write permissions
   */
  public static native int applyPatch(String oldApkFilePath, String newApkFilePath, String patchFilePath);


  /**
   * Being installed apk PackageInfo
   * @param context the use {@link Context}
   * @param packageName package name
   * @return PackageInfo
   */
  public static PackageInfo getInstalledApkPackageInfo(Context context, String packageName) {
    PackageManager pm = context.getPackageManager();
    List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
    Iterator<PackageInfo> it = apps.iterator();
    while (it.hasNext()) {
      PackageInfo packageinfo = it.next();
      String thisName = packageinfo.packageName;
      if (thisName.equals(packageName)) {
        return packageinfo;
      }
    }

    return null;
  }

  /**
   * Determining whether apk installed
   * @param context  the use {@link Context}
   * @param packageName apk package name
   * @return Returns true if the representatives have been installed, no installation is false
   */
  public static boolean isInstalled(Context context, String packageName) {
    PackageManager pm = context.getPackageManager();
    boolean installed = false;
    try {
      pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
      installed = true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return installed;
  }

  /**
   * 获取已安装Apk文件的源Apk文件
   * 如：/data/app/com.sina.weibo-1.apk
   *
   * @param context
   * @param packageName
   * @return
   */
  public static String getSourceApkPath(Context context, String packageName) {
    if (TextUtils.isEmpty(packageName))
      return null;

    try {
      ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
      return appInfo.sourceDir;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   *  install Apk
   *  Normal installation
   * @param context the use {@link  Context}
   * @param apkPath  install apk path
   */
  public static void installApk(Context context, String apkPath) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.parse("file://" + apkPath),
            "application/vnd.android.package-archive");
    context.startActivity(intent);
  }


  private static String bytes2Hex(byte[] src) {
    char[] res = new char[src.length * 2];
    final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    for (int i = 0, j = 0; i < src.length; i++) {
      res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
      res[j++] = hexDigits[src[i] & 0x0f];
    }

    return new String(res);
  }

  private static String getMd5ByFile(File file) {
    String value = null;
    FileInputStream in = null;
    try {
      in = new FileInputStream(file);

      MessageDigest digester = MessageDigest.getInstance("MD5");
      byte[] bytes = new byte[8192];
      int byteCount;
      while ((byteCount = in.read(bytes)) > 0) {
        digester.update(bytes, 0, byteCount);
      }
      value = bytes2Hex(digester.digest());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != in) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return value;
  }

  /**
   *
   * @param file The apk File path
   * @param md5 To be compared md5
   * @return Returns true if it is to represent them is the same, false if it is not the same
     */
  public static boolean checkMD5(File file, String md5) {
    if (TextUtils.isEmpty(md5)) {
      throw new RuntimeException("md5 cannot be empty");
    }

    String fileMd5 = getMd5ByFile(file);

    if (DEBUG) {
      Log.d(TAG, String.format("file's md5=%s, real md5=%s", fileMd5, md5));
    }

    if (md5.equals(fileMd5)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   *
   * @param filePath The apk File path
   * @param md5 To be compared md5
   * @return Returns true if it is to represent them is the same, false if it is not the same
   */
  public static boolean checkMD5(String filePath, String md5) {
    return checkMD5(new File(filePath), md5);
  }
}
