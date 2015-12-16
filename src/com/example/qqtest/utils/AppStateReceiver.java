package com.example.qqtest.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Android 监控程序安装和删除的实现
 * 
 * android.intent.action.PACKAGE_ADDED 
 * android.intent.action.PACKAGE_CHANGED
 * android.intent.action.PACKAGE_DATA_CLEARED
 * android.intent.action.PACKAGE_INSTALL 
 * android.intent.action.PACKAGE_REMOVED
 * android.intent.action.PACKAGE_REPLACED
 * android.intent.action.PACKAGE_RESTARTED
 * 
 * @author Administrator
 *
 */
public class AppStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals("android.intent.action.PACKAGE_ADDED"))
			show(context, "PACKAGE_ADDED");
		else if (action.equals("android.intent.action.PACKAGE_CHANGED"))
			show(context, "PACKAGE_CHANGED");
		else if (action.equals("android.intent.action.PACKAGE_DATA_CLEARED"))
			show(context, "PACKAGE_DATA_CLEARED");
		else if (action.equals("android.intent.action.PACKAGE_INSTALL"))
			show(context, "PACKAGE_INSTALL");
		else if (action.equals("android.intent.action.PACKAGE_REPLACED"))
			show(context, "PACKAGE_REPLACED");
		else if (action.equals("android.intent.action.PACKAGE_REMOVED"))
			show(context, "PACKAGE_REMOVED");
		else if (action.equals("android.intent.action.PACKAGE_RESTARTED"))
			show(context, "PACKAGE_RESTARTED");
	}

	private void show(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

}
