package com.example.qqtest.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

public class AppMarketUtil {
	public static ArrayList<String> showLocalMarket(Context mCtx) {
		ArrayList<String> markets = new ArrayList<String>();
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.APP_MARKET");
		PackageManager pm = mCtx.getPackageManager();
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
		int size = infos.size();
		for (int i = 0; i < size; i++) {
			ActivityInfo activityInfo = infos.get(i).activityInfo;
			String packageName = activityInfo.packageName;
			Log.e("MarketInfo:", packageName);
			markets.add(packageName);
		}
		return markets;
		
	}
	
	public static void openAppAtMarket(Context mCtx, String packageName, String marketName){
		Intent intent = new Intent(Intent. ACTION_VIEW);  
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.parse ("market://details?id=" + packageName);  
		intent.setData(uri);  
//		intent.setPackage(packages.get(position));  
		intent.setPackage(marketName);  
		mCtx.startActivity(intent);
	}
}
