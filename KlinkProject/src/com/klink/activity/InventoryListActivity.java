package com.klink.activity;

import java.util.ArrayList;
import java.util.Vector;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.klink.adapters.CustomPagerAdapter;
import com.klink.adapters.InvenotryListAdapter;
import com.klink.database.KlinkDbHelper;
import com.klink.interfaces.CallBackListener;
import com.klink.loaders.ApplicationDataLoader;
import com.klink.model.CatalogProductListModel;
import com.klink.utils.AppPreferences;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class InventoryListActivity extends FragmentActivity implements CallBackListener {

	private String TAG = "InventoryListActivity";

	private CatalogProductListModel mCatalogProductListModel;
	private AppPreferences mAppPreferences;

	private ArrayList<CatalogProductListModel> popularList ;//Featured
	private ArrayList<CatalogProductListModel> allList ;
	private ArrayList<CatalogProductListModel> wineList ;
	private ArrayList<CatalogProductListModel> beerList ;
	private ArrayList<CatalogProductListModel> liquorList ;

	private int[] catIdArray = {14,8,12,11,5};

	private CallBackListener mCallBackListener;
	private ApplicationDataLoader mApplicationDataLoader;
	private ProgressDialog progress ;

	private ListView listview1;
	private ListView listview2;
	private ListView listview3;
	private ListView listview4;
	private ListView listview5;


	//private InvenotryListAdapter adapter ;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_inventory);

		mCallBackListener = this;
		mAppPreferences = new AppPreferences(this);
		mCatalogProductListModel = new CatalogProductListModel();
		mApplicationDataLoader = new ApplicationDataLoader(mAppPreferences, mCallBackListener);

		popularList = new ArrayList<CatalogProductListModel>();
		allList  = new ArrayList<CatalogProductListModel>();
		wineList = new ArrayList<CatalogProductListModel>();
		beerList  = new ArrayList<CatalogProductListModel>();
		liquorList  = new ArrayList<CatalogProductListModel>();
		progress = new ProgressDialog(this);

		//mApplicationDataLoader.loadCatalogProductslist();

		listview1 = new ListView(this);
		listview2 = new ListView(this);
		listview3 = new ListView(this);
		listview4 = new ListView(this);
		listview5 = new ListView(this);

		Vector<View> pages = new Vector<View>();

		pages.add(listview1);
		pages.add(listview2);
		pages.add(listview3);
		pages.add(listview4);
		pages.add(listview5);

		//new LoadProductsAsyncTask().execute();

		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		CustomPagerAdapter adapter = new CustomPagerAdapter(this,pages);
		viewPager.setAdapter(adapter);

		TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
		indicator.setTextSize(20);
		indicator.setBackgroundColor(Color.parseColor("#0B243B"));
		indicator.setViewPager(viewPager);
		indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);

		//		progress.setTitle("Klink");
		//		progress.setMessage("Loading please wait...");
		//		progress.setCancelable(false);
		//		progress.show();
	}

	//TODO
	private class LoadProductsAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			progress.setTitle("Klink");
			progress.setMessage("Loading please wait...");
			progress.setCancelable(false);
			progress.show();
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				for(int i=0;i<catIdArray.length;i++) {
					getOPenProductsList("", catIdArray[i]);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
				result = "Exception";
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			if(progress != null){
				progress.cancel();
			}

			listview1.setAdapter(new InvenotryListAdapter(InventoryListActivity.this, popularList));
			listview2.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,allList));
			listview3.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,wineList));
			listview4.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,beerList));
			listview5.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,liquorList));
		}
	}

	@Override
	public void productListLoadCallback(String name) {

	}

	@Override
	public void productListcallback(CatalogProductListModel mCatalogProductListModel,int cat_id) {

		if(mCatalogProductListModel.getCategory_id() == 14){
			popularList.add(mCatalogProductListModel);						
		} else if(mCatalogProductListModel.getCategory_id() == 8){
			allList.add(mCatalogProductListModel);
		} else if(mCatalogProductListModel.getCategory_id() == 12){
			wineList.add(mCatalogProductListModel);
		} else if(mCatalogProductListModel.getCategory_id() == 11){
			beerList.add(mCatalogProductListModel);
		}  else if(mCatalogProductListModel.getCategory_id() == 5){
			liquorList.add(mCatalogProductListModel);
		}
	}

	@Override
	public void hideProgressDialog() {

		if(progress != null){
			progress.cancel();
		}

		listview1.setAdapter(new InvenotryListAdapter(InventoryListActivity.this, popularList));
		listview2.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,allList));
		listview3.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,wineList));
		listview4.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,beerList));
		listview5.setAdapter(new InvenotryListAdapter(InventoryListActivity.this,liquorList));
	}

	//	private void getProdcutList(){
	//
	//		try {
	//
	//			String response = mAppPreferences.getProductResponse();
	//			JSONObject jsonObjct = new JSONObject(response);
	//			JSONObject resultObject = jsonObjct.getJSONObject("result");
	//			JSONArray complexArrya1 = resultObject.getJSONArray("complexObjectArray");
	//			int length = complexArrya1.length();
	//			Log.e(TAG, "CustomerCustomerLoginAsyncTask length-->>"+length);
	//			
	//			for(int i = 0; i < length; ++i) {
	//
	//				CatalogProductListModel p = new CatalogProductListModel();
	//				JSONObject product = complexArrya1.getJSONObject(i);
	//
	//				p.setEntity_id(product.getInt("entity_id"));
	//				p.setEntity_type_id(product.getInt("entity_type_id"));
	//				p.setAttribute_set_id(product.getInt("attribute_set_id"));
	//				p.setType_id(product.getString("type_id"));
	//				p.setSku(product.getString("sku"));
	//				p.setHas_options(product.getInt("has_options"));
	//				p.setRequired_options(product.getInt("required_options"));
	//				p.setCreated_at(product.getString("created_at"));
	//				p.setUpdated_at(product.getString("updated_at"));
	//				p.setName(product.getString("name"));
	//				p.setUpdated_at(product.getString("updated_at"));
	//				p.setShort_description(product.getString("short_description"));
	//				p.setPrice(product.getInt("price"));
	//				p.setImage(product.getString("image"));
	//				p.setDd_iskeg(product.getInt("dd_iskeg"));
	//				p.setCategory_id(product.getInt("category_id"));
	//				p.setDd_deposit(product.getString("dd_deposit"));
	//				p.setStatus(product.getInt("status"));
	//
	//				if(p.getCategory_id() == 14){
	//					popularList.add(mCatalogProductListModel);						
	//				} else if(p.getCategory_id() == 8){
	//					allList.add(mCatalogProductListModel);
	//				} else if(p.getCategory_id() == 12){
	//					wineList.add(mCatalogProductListModel);
	//				} else if(p.getCategory_id() == 11){
	//					beerList.add(mCatalogProductListModel);
	//				}  else if(p.getCategory_id() == 5){
	//					liquorList.add(mCatalogProductListModel);
	//				}
	//			} 
	//		} catch (Exception e){
	//			e.printStackTrace();	
	//		}
	//	}


	//using this only for displaying search details..
	private void getOPenProductsList(String strSearchString,int catId){

		int count = 0;
		SQLiteDatabase db = null;
		Cursor mCursor = null;
		String query = "";

		try {

			query = "SELECT name,price,image FROM cata_log_productlist WHERE category_id = "+catId;
			Log.v(TAG,"Search query-->"+query);

			db = KlinkDbHelper.getInstance().getReadableDatabase();
			mCursor = db.rawQuery(query, null);
			count = mCursor.getCount();

			if(count != 0) {
				while (mCursor.moveToNext()) {

					mCatalogProductListModel = new CatalogProductListModel();
					mCatalogProductListModel.name = mCursor.getString(mCursor.getColumnIndex("name"));
					mCatalogProductListModel.price = mCursor.getInt(mCursor.getColumnIndex("price"));
					mCatalogProductListModel.image = mCursor.getString(mCursor.getColumnIndex("image"));
					mCatalogProductListModel.status = 0;

					if(catId == 14){
						popularList.add(mCatalogProductListModel);						
					} else if(catId == 8){
						allList.add(mCatalogProductListModel);
					} else if(catId == 12){
						wineList.add(mCatalogProductListModel);
					} else if(catId == 11){
						beerList.add(mCatalogProductListModel);
					}  else if(catId == 5){
						liquorList.add(mCatalogProductListModel);
					}
				}
			} else {
				Toast.makeText(this, "No activites to display", Toast.LENGTH_SHORT).show();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(db.isOpen()){
				mCursor.close();
				db.close();				
			}
		}
	}
}

