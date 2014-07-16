package com.klink.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.klink.interfaces.CallBackListener;
import com.klink.loaders.ApplicationDataLoader;
import com.klink.model.CatalogProductListModel;
import com.klink.networks.Networking;
import com.klink.utils.AppPreferences;

public class KlinkActivity extends FragmentActivity implements CallBackListener,OnClickListener{

	private String TAG = "KlinkActivity";

	private TextView textView1;
	private Button start_order_button;

	private AppPreferences mAppPreferences;
	private ApplicationDataLoader mApplicationDataLoader;
	private CallBackListener mCallBackListener;

	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_klink);

		mCallBackListener = this;
		mAppPreferences = new AppPreferences(this);
		mApplicationDataLoader = new ApplicationDataLoader(mAppPreferences,mCallBackListener);

		textView1 = (TextView)findViewById(R.id.textView1);
		start_order_button = (Button)findViewById(R.id.start_order_button);
		start_order_button.setOnClickListener(this);

		progress = new ProgressDialog(this);
		progress.setTitle("Klink");
		progress.setMessage("Loading...");
		progress.setCancelable(false);
		progress.show();

		if(Networking.isNetworkAvailable(this)){
			//mApplicationDataLoader.loadCatalogCategoryInfoTask();
			//mApplicationDataLoader.loadCatalogProductslist();
			mApplicationDataLoader.loadLignTask();
		} else {
			showToast(Networking.mNewtWorkState);
		}
	}

	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void productListcallback(CatalogProductListModel p,int cat_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void productListLoadCallback(String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public void hideProgressDialog() {
		if(progress != null){
			progress.dismiss();			
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_order_button:

			Intent in = new Intent(this,InventoryListActivity.class);
			startActivity(in);

			break;
		default:
			break;
		}
	}
}
