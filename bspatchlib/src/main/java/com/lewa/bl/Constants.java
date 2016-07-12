package com.lewa.bl;

import android.os.Environment;

import java.io.File;

public class Constants {

	public static final boolean DEBUG = true;
	public static final String WEIBO_OLD_MD5 = "7dc46bd75c1042f943942a37d646afaa";
	public static final String WEIBO_NEW_MD5 = "c2da8edf4b796c1a393be38730ac93fe";
	public static final String TEST_PACKAGENAME = "com.sina.weibo";
	public static final String PATH = Environment.getExternalStorageDirectory() + File.separator;
	public static final String NEW_APK_PATH = PATH + "weiboOldtoNew.apk";
	public static final String PATCH_PATH = PATH + "weibo.patch";
}