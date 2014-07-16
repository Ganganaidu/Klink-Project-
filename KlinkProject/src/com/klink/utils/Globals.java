package com.klink.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import com.klink.activity.MyApplication;

public class Globals {

	public static String APP_NAME = "Klink";

	public static String extStorageDirectory = Environment.getExternalStorageDirectory() + "/Klink";
	public static int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	public static int targetVesrion = android.os.Build.VERSION_CODES.HONEYCOMB;
	public static final String APP_KEY = "ydah7q83jtjdttc";/* This is for you to fill in! */;

	/*
	 * Define a request code to send to Google Play services This code is
	 * returned in Activity.onActivityResult
	 */
	public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	/*
	 * Constants for location update parameters
	 */
	// Milliseconds per second
	public static final int MILLISECONDS_PER_SECOND = 100;
	// The update interval
	public static final int UPDATE_INTERVAL_IN_SECONDS = 4;
	// A fast interval ceiling
	public static final int FAST_CEILING_IN_SECONDS = 1;
	// Update interval in milliseconds
	public static final long UPDATE_INTERVAL_IN_MILLISECONDS = MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;

	// A fast ceiling of update intervals, used when the app is visible
	public static final long FAST_INTERVAL_CEILING_IN_MILLISECONDS = MILLISECONDS_PER_SECOND * FAST_CEILING_IN_SECONDS;

	public static int BACKTIME_INTERVAL = 3000;
	public static int PING_OUTTIME = 30000;
	public static int TIMEOUTCONNECTION = 30000;
	public static int TIMEOUTSOCKET = 30000;
	//public static int pageLimit = 8;
	public static int prefpageLimit = 10;//Preference page limit

	public static String DAYS[] = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri","Sat" };
	public static final String[] MONTHS = { " ", "Jan", "Feb", "Mar", "Apr",
		"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	/**  Generate 4 digit random number */
	public static int genCode() {
		Random r = new Random(System.currentTimeMillis());
		return (1 + r.nextInt(2)) * 1000 + r.nextInt(1000);
	}


	/** turn gps on */
	public static void turnGPSOn(Context mContext,int gps_count) {

		int version = android.os.Build.VERSION.SDK_INT;

		@SuppressWarnings("static-access")
		LocationManager manager = (LocationManager) MyApplication.getInstance().getSystemService(MyApplication.getInstance().LOCATION_SERVICE );
		boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		Log.e("Globals","statusOfGPS "+statusOfGPS);

		if(statusOfGPS == false){
			if(version >= 19){

				if(gps_count == 1) {
					//					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					//					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//					MyApplication.getInstance().startActivity(intent);
				}
			} else {
				Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
				intent.putExtra("enabled", true);
				mContext.sendBroadcast(intent);
				@SuppressWarnings("deprecation")
				String provider = Settings.Secure.getString(mContext.getContentResolver(),Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
				if (!provider.contains("gps")) {
					// if GPS is disabled
					final Intent poke = new Intent();
					poke.setClassName("com.android.settings","com.android.settings.widget.SettingsAppWidgetProvider");
					poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
					poke.setData(Uri.parse("3"));
					mContext.sendBroadcast(poke);
				}
			}
		}
	}

	/** turn gps off */
	@SuppressWarnings("unused")
	public static void turnGPSOff(Context mContext) 

	{
		int version = android.os.Build.VERSION.SDK_INT;

		LocationManager manager = (LocationManager) MyApplication.getInstance().getSystemService(MyApplication.LOCATION_SERVICE );
		boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if(version >=19)
		{

		}
		else
		{
			Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
			intent.putExtra("enabled", false);
			mContext.sendBroadcast(intent);

			@SuppressWarnings("deprecation")
			String provider = Settings.Secure.getString(mContext.getContentResolver(),Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
			if (provider.contains("gps")) 
			{ 
				// if gps is enabled
				final Intent poke = new Intent();
				poke.setClassName("com.android.settings","com.android.settings.widget.SettingsAppWidgetProvider");
				poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
				poke.setData(Uri.parse("3"));
				mContext.sendBroadcast(poke);
			}
		}
	}

	/*public static CountryCodeModel addCountryCodes(int id, String name, String code, int minLength, int maxLength){
		return new CountryCodeModel(id, name, code, minLength, maxLength);
	}*/

	/**
	 * method is used for checking valid email id format.
	 * 
	 * @param email
	 * @return boolean TRUE for valid FLASE for invalid
	 */
	public static boolean isEmailAddressValid(String email) {
		boolean isEmailValid = false;

		String strExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern objPattern = Pattern.compile(strExpression , Pattern.CASE_INSENSITIVE);
		Matcher objMatcher = objPattern .matcher(inputStr);
		if (objMatcher .matches()) {
			isEmailValid = true;
		}
		return isEmailValid ;
	}

	public static int HTTPSTATE = 1;

	/** HTTP post call for all web service request of the application 
	 * @param httppost HttpPost for URL
	 * @param nameValuePairs for entry parameters
	 * @return response */
	public static String httpClientCall(HttpPost httppost,List<NameValuePair> nameValuePairs){

		String response = "";
		HttpResponse httpResponse = null;
		HttpClient httpClient;	

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
			HttpParams httpParameters = new BasicHttpParams();
			// Set the timeout in milliseconds until a connection is established.
			HttpConnectionParams.setConnectionTimeout(httpParameters, Globals.TIMEOUTCONNECTION);
			// Set the default socket timeout (SO_TIMEOUT) 
			// in milliseconds which is the timeout for waiting for data.
			HttpConnectionParams.setSoTimeout(httpParameters, Globals.TIMEOUTSOCKET);

			// ================= SSL CERTIFICATION
			httpClient = ExSSLSocketFactory.getHttpsClient(new DefaultHttpClient());
			HttpProtocolParams.setVersion(httpParameters, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(httpParameters, HTTP.UTF_8);
			// =================
			httpResponse = httpClient.execute(httppost);

			// According to the JAVA API, InputStream constructor do nothing. 
			//So we can't initialize InputStream although it is not an interface
			InputStream inputStream = httpResponse.getEntity().getContent();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder stringBuilder = new StringBuilder();
			String bufferedStrChunk = null;
			while((bufferedStrChunk = bufferedReader.readLine()) != null){
				stringBuilder.append(bufferedStrChunk);
			}
			response = stringBuilder.toString();
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}


	public static String httpGetService(String url){

		HttpClient httpclient;
		BufferedReader in = null;
		String line = "";

		try {
			HttpGet request = new HttpGet();
			URI website = new URI(url);
			request.setURI(website);

			// ================= SSL CERTIFICATION
			httpclient = ExSSLSocketFactory.getHttpsClient(new DefaultHttpClient());
			HttpResponse httpResponse = httpclient.execute(request);
			in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			line = in.readLine();

		} catch(Exception e){}
		return line;
	}

	//
	//	public static String httpGetService(String url){
	//		BufferedReader in = null;
	//		String line = "";
	//		try {
	//			HttpClient httpclient = new DefaultHttpClient();
	//			HttpGet request = new HttpGet();
	//			URI website = new URI(url);
	//			request.setURI(website);
	//			HttpResponse response = httpclient.execute(request);
	//			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	//			line = in.readLine();
	//		} catch(Exception e){
	//		}
	//		return line;
	//	}

	// is Gps service running
	public static boolean isGpsServiceRunning(Context mContext)  {
		boolean serviceRunning = false;
		ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> l = am.getRunningServices(50);
		Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
		while (i.hasNext())  {
			ActivityManager.RunningServiceInfo runningServiceInfo = (ActivityManager.RunningServiceInfo) i.next();
			if (runningServiceInfo.service.getClassName().equals("com.klink.services.GpsService"))  {
				serviceRunning = true;
			}
		}
		return serviceRunning;
	}
}
