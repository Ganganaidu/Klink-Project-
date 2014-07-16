package com.klink.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.klink.utils.AppPreferences;
import com.klink.utils.Globals;

//using this for updating location in all classes
public class GpsService extends Service implements LocationListener, 
GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {

	private String TAG = "GpsService";
		
	private AppPreferences mAppPreferences;

	// A request to connect to Location Services
	private LocationRequest mLocationRequest;
	// Stores the current instantiation of the location client in this object
	private LocationClient mLocationClient;

	//private LocationManager mLocationManager;
	//boolean gps_enabled = false;
	//private boolean network_enabled = false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {

		mAppPreferences = new AppPreferences(getApplicationContext());
		//mActivityTransaction = new ActivityTransaction();

		// Create a new global location parameters object
		mLocationRequest = LocationRequest.create();
		/*
		 * Set the update interval
		 */
		mLocationRequest.setInterval(Globals.UPDATE_INTERVAL_IN_MILLISECONDS);

		// Use high accuracy
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

		// Set the interval ceiling to one minute
		mLocationRequest.setFastestInterval(Globals.FAST_INTERVAL_CEILING_IN_MILLISECONDS);
		/*
		 * Create a new location client, using the enclosing class to
		 * handle callback.
		 */
		mLocationClient = new LocationClient(this, this, this);
		// Note that location updates are off until the user turns them on
		/*
		 * Connect the client. Don't re-start any requests here;
		 * instead, wait for onResume()
		 */
		mLocationClient.connect();
		//getLocation();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		stopSelf();
		mLocationRequest = null;
		mLocationClient.disconnect();
	}

	
	/** We want this service to continue running until it is explicitly
	 stopped, so return sticky. */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return START_NOT_STICKY ;
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onConnected(Bundle arg0) {

		if(servicesConnected()) {
			startPeriodicUpdates();			
		}
	}

	@Override
	public void onDisconnected() {
	}

	@Override
	public void onLocationChanged(Location location) {

		String latitude =  Double.toString(location.getLatitude());
		String longitude = Double.toString(location.getLongitude());
		//String altitude = Double.toString(location.getAltitude());
		String speed = Double.toString(location.getSpeed());
		String entity_6 = Double.toString(location.getAccuracy());
		

//		mAppPreferences.saveLatitude(latitude);
//		mAppPreferences.saveLongitude(longitude);
//		mAppPreferences.saveAcuracy(entity_6);
//		mAppPreferences.saveSpeed(speed);
	}

	/**
	 * In response to a request to start updates, send a request
	 * to Location Services
	 */
	private void startPeriodicUpdates() {
		mLocationClient.requestLocationUpdates(mLocationRequest, this);
	}

	/**
	 * Verify that Google Play services is available before making a request.
	 *
	 * @return true if Google Play services is available, otherwise false
	 */
	private boolean servicesConnected() {

		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			// In debug mode, log the status
			Log.v(TAG,"location update  connected");
			// Continue
			return true;
			// Google Play services was not available for some reason
		} else {
			Log.w(TAG,"location update not  connected");
			return false;
		}
	}
}
