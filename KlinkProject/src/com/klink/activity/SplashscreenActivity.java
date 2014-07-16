package com.klink.activity;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Window;
import android.widget.Toast;

import com.klink.services.GpsService;
import com.klink.utils.AppPreferences;
import com.klink.utils.Globals;
public class SplashscreenActivity extends Activity {

	private static final String TAG = "Splashscreen";

	//============= local objects ========
	private AppPreferences mAppPreferences;

	//====== Local variables ==============
	private String mImeiNumber = "";
	int timeInterval = 1000;
	private Handler handler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//hiding the top bar of the screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splashscreen);

		mAppPreferences = new AppPreferences(this);
		mImeiNumber = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

		//creating directory 
		File myNewFolder = new File(Globals.extStorageDirectory);
		if(myNewFolder.exists()) {
			myNewFolder.delete();
			myNewFolder.mkdir();
		} else {
			myNewFolder.mkdir();
		}
		startActivity();
	}

	private void startActivity(){
//
//		// Getting status
//		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
//
//		// Showing status
//		if(status == ConnectionResult.SUCCESS){
//
			doInit();
//			Globals.turnGPSOn(this,0);
//			TurnOn turnOn = new TurnOn();
//			if(Globals.currentapiVersion >= Globals.targetVesrion) {
//				turnOn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//			} else {
//				turnOn.execute();
//			}
//		}
//		else {
//
//			int requestCode = 10;
//			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
//			dialog.show();
//		}
	}

	@SuppressLint("InlinedApi")
	@Override
	public void onResume(){
		super.onResume();

	}

	@Override
	public void onPause(){
		super.onPause();
		stopServices();
	}

	private void stopServices() {
		Globals.turnGPSOff(this);//switch off gps
		stopService(new Intent(this, GpsService.class));//stop gps service
	}

	/** */
	protected void doInit() {
		timeInterval = 2000;
		handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				startNextActivity();
			}
		}, timeInterval);
	}

	private void startNextActivity() {

		Intent in = new Intent(this,KlinkActivity.class);
		startActivity(in);
		finish();
	}


	private class TurnOn extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			Globals.turnGPSOn(SplashscreenActivity.this,1);
			if(!Globals.isGpsServiceRunning(SplashscreenActivity.this)){
				startService(new Intent(SplashscreenActivity.this,GpsService.class));	
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			int version = android.os.Build.VERSION.SDK_INT;

			@SuppressWarnings("static-access")
			LocationManager manager = (LocationManager) MyApplication.getInstance().getSystemService(MyApplication.getInstance().LOCATION_SERVICE );
			boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if(statusOfGPS == false){
				if(version >= 19) {
					Toast.makeText(SplashscreenActivity.this, "Please turn on gps location service",Toast.LENGTH_LONG).show();
				}
			}
		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		Globals.turnGPSOff(this);//switch off gps
		stopService(new Intent(this, GpsService.class));//stop gps service
		System.exit(1);
	}
}