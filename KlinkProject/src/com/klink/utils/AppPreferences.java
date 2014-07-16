package com.klink.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**Saving data across the application */
public class AppPreferences {

	private static final String APP_SHARED_PREFS = "com.klink.activity"; 
	private SharedPreferences appSharedPrefs;
	private Editor prefsEditor;

	/** Saving data in shared preferences which will store life time of Application */
	public AppPreferences(Context context)
	{
		this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
		this.prefsEditor = appSharedPrefs.edit();
	}

	/*
	 *    Delete the all the preferences
	 */
	public void deletePref() {
		this.prefsEditor.clear();
		this.prefsEditor.commit();
	}

	/**
	 * Save session id
	 */
	public void saveSessionid(String contactsCount) {
		prefsEditor.putString("saveSessionKey", contactsCount);
		prefsEditor.commit();
	}

	public String getSessionid() {
		return appSharedPrefs.getString("saveSessionKey","");
	}

	/**
	 * Save session id
	 */
	public void saveCustomerId(String customerid) {
		prefsEditor.putString("saveCustomerId", customerid);
		prefsEditor.commit();
	}

	public String getCustomerId() {
		return appSharedPrefs.getString("saveCustomerId","");
	}

	/** Saving  store id */
	public void saveStoreId(String saveStoreId){

		prefsEditor.putString("saveStoreId", saveStoreId);
		prefsEditor.commit();
	}

	/**Getting store id */
	public String getStoreId(){
		return appSharedPrefs.getString("saveStoreId", "");
	}

	/** Saving  Latitude */
	public void saveLatitude(String lat){

		prefsEditor.putString("latitude", lat);
		prefsEditor.commit();
	}

	/**Getting latitude*/
	public String getLatitude(){
		return appSharedPrefs.getString("latitude", "");
	}

	/** Saving  longitude */
	public void saveLongitude(String longitude){

		prefsEditor.putString("longitude", longitude);
		prefsEditor.commit();
	}

	/**Getting longitude*/
	public String getLongitude(){
		return appSharedPrefs.getString("longitude", "");
	}


	/** Saving  route id */
	public void saveRouteId(String routeId){

		prefsEditor.putString("routeId", routeId);
		prefsEditor.commit();
	}

	/**saving route id*/
	public String getRouteId(){
		return appSharedPrefs.getString("routeId", "");
	}


//	public void saveProdcutResponse(String reponse){
//
//		prefsEditor.putString("saveProdcutResponse", reponse);
//		prefsEditor.commit();
//	}
//
//	public String getProductResponse(){
//		return appSharedPrefs.getString("saveProdcutResponse", "");
//	}

}

