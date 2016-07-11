package com.lewa.bl;

import android.os.Environment;

import java.io.File;

/**
 * 类说明：  	常量类
 *
 * @author 	Cundong
 * @date 	2013-9-6
 * @version 1.0
 */
public class Constants {

	public static final boolean DEBUG = true;

	//WeiboV5.5.apk 正确的MD5值，如果本地安装的apk MD5值不是TA，说明本地安装的是被二次打包的apk
	public static final String WEIBO_OLD_MD5 = "7dc46bd75c1042f943942a37d646afaa";

	//WeiboV5.6.apk 正确的MD5值
	public static final String WEIBO_NEW_MD5 = "c2da8edf4b796c1a393be38730ac93fe";

	//用于测试的packageName
	public static final String TEST_PACKAGENAME = "com.sina.weibo";

	public static final String PATH = Environment.getExternalStorageDirectory() + File.separator;

	//合成得到的新版apk
	public static final String NEW_APK_PATH = PATH + "weiboOldtoNew.apk";

	//从服务器下载来的差分包
	public static final String PATCH_PATH = PATH + "weibo.patch";
}