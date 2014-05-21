package com.mario.diary.common;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class ConnectionAuthenticate {
	public static boolean isShowMessage = false;
	private Activity act;
	public boolean isConnected() {
		WifiManager connectivityManager = (WifiManager) act
				.getSystemService(Context.WIFI_SERVICE);
		if (connectivityManager != null) {
			if (connectivityManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
				final WifiInfo info = connectivityManager.getConnectionInfo();
				if (info != null && info.getSSID() != null) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isConnected(Activity activity) {
		NetworkInfo netInfo = ((ConnectivityManager) (activity
				.getSystemService(Context.CONNECTIVITY_SERVICE)))
				.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected())
			return true;
		return false;
	}
}
