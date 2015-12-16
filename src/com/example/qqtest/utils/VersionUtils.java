package com.example.qqtest.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 版本升级
 * @author Administrator
 * <pre>
 * android:versionCode="1" 
 * android:versionName="1.0"
 * </pre>
 */
public class VersionUtils {

	/**
	 * 获取版本号
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 获取版本名
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 判断最新版本
	 * @param context
	 * @param newVersionCode
	 * @return
	 */
	public boolean isNewVersion(Context context, int newVersionCode){
		return getVersionCode(context) < newVersionCode;
	}
	
}
