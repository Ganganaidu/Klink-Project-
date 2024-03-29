package com.klink.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**Checking network access */
public class Networking {
	/**
	 *@return boolean return true if the application can access the Internet
	 */
	public static boolean isNetworkAvailable(Context context) {
		try{
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		}catch(Exception e){
		}
		return false;
	}

	public static String mNewtWorkState = "No network available";
	public static String mTimeOutConnection = "Connection timed out Please Try again later";
}
