package com.klink.loaders;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.klink.database.KlinkDbHelper;
import com.klink.interfaces.CallBackListener;
import com.klink.model.CatalogProductListModel;
import com.klink.utils.AppPreferences;
import com.klink.utils.AppUrls;
import com.klink.utils.Globals;

public class ApplicationDataLoader {

	private static final String TAG = "ApplicationDataLoader";

	private AppPreferences mAppPreferences;
	private CallBackListener mCallBackListener;

	public ApplicationDataLoader(AppPreferences mAppPreferences,CallBackListener mCallBackListener){
		this.mAppPreferences = mAppPreferences;
		this.mCallBackListener = mCallBackListener;
	}

	public void loadLignTask(){
		new LoginAsyncTask().execute();
	}

	public void loadCreateCustomerTask(){
		new CustomerCreateAsyncTask().execute();
	}

	public void loadCheckCustomerTask(){
		new CheckCustomerEmailAsyncTask().execute();
	}

	public void loadCatalogProductslist(){

		KlinkDbHelper.getInstance().deleteCatalogProductlist();
		new CatalogProductListAsyncTask().execute();
	}

	public void loadCatalogCategoryInfoTask() {
		new CatalogCategoryInfoAsyncTask().execute();
	}

	public void loadRouteRoderTask(){
		new RouteOrderAsyncTask().execute();
	}

	public void loadDelivaryLocationTask() {
		new SetDeliveryLocationAsyncTask().execute();
	}

	//TODO
	private class LoginAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				String keyVal = URLEncoder.encode("2hQRGgJ%jk4M8~9I(4!hC7M;>nK,z.", "UTF-8");
				String valueVal = URLEncoder.encode("webservices", "UTF-8");
				String url = AppUrls.BASE_URL+"?q=login"+"&user="+valueVal+"&key="+keyVal;
				Log.e(TAG, " url "+url);

				String response  = Globals.httpGetService(url);
				if (response.length() > 0) {
					Log.e(TAG, "work status Response-->>"+response);
					JSONObject jsonObjct = new JSONObject(response);
					mAppPreferences.saveSessionid(jsonObjct.getString("result"));
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
			loadRouteRoderTask();

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	private class CustomerCustomerLoginAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				String email = URLEncoder.encode("ganga.kondati@gmail.com", "UTF-8");
				String password = URLEncoder.encode("ganga123", "UTF-8");
				String url = AppUrls.createCustomerUrl(mAppPreferences.getSessionid())+"?q=customerCustomerLogin"+
						"&email="+email+"&password="+password;
				Log.e(TAG, " url "+url);

				String response  = Globals.httpGetService(url);
				if (response.length() > 0) {
					Log.e(TAG, "CustomerCustomerLoginAsyncTask Response-->>"+response);
					JSONObject jsonObjct = new JSONObject(response);
					mAppPreferences.saveSessionid(jsonObjct.getString("result"));
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

			new CustomerCreateAsyncTask().execute();

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	private class CustomerAddressListAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				String customerId = URLEncoder.encode(mAppPreferences.getCustomerId(), "UTF-8");
				String url = AppUrls.createCustomerUrl(mAppPreferences.getSessionid())+"?q=customerAddressList"+
						"&customerId="+customerId;
				Log.e(TAG, " url "+url);

				String response  = Globals.httpGetService(url);
				if (response.length() > 0) {
					Log.e(TAG, "CustomerCustomerLoginAsyncTask Response-->>"+response);
					JSONObject jsonObjct = new JSONObject(response);
					mAppPreferences.saveSessionid(jsonObjct.getString("result"));
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

			new CustomerCreateAsyncTask().execute();

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	//TODO
	private class CatalogProductListAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			mCallBackListener.productListLoadCallback("");
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				String url = AppUrls.createCustomerUrl(mAppPreferences.getSessionid())+"&q=catalogProductList";
				Log.e(TAG, " url "+url);

				String response  = Globals.httpGetService(url);
				//mLocalCache.put("productlist", response);
				//mAppPreferences.saveProdcutResponse(response);
				if (response.length() > 0) {

					JSONObject jsonObjct = new JSONObject(response);
					JSONObject resultObject = jsonObjct.getJSONObject("result");
					JSONArray complexArrya1 = resultObject.getJSONArray("complexObjectArray");

					int length = complexArrya1.length();
					Log.e(TAG, "CustomerCustomerLoginAsyncTask length-->>"+length);

					for(int i = 0; i < length; ++i) {

						CatalogProductListModel p = new CatalogProductListModel();
						JSONObject product = complexArrya1.getJSONObject(i);

						p.setEntity_id(product.getInt("entity_id"));
						p.setEntity_type_id(product.getInt("entity_type_id"));
						p.setAttribute_set_id(product.getInt("attribute_set_id"));
						p.setType_id(product.getString("type_id"));
						p.setSku(product.getString("sku"));
						p.setHas_options(product.getInt("has_options"));
						p.setRequired_options(product.getInt("required_options"));
						p.setCreated_at(product.getString("created_at"));
						p.setUpdated_at(product.getString("updated_at"));
						p.setName(product.getString("name"));
						p.setUpdated_at(product.getString("updated_at"));
						p.setShort_description(product.getString("short_description"));
						p.setPrice(product.getInt("price"));
						p.setImage(product.getString("image"));
						p.setDd_iskeg(product.getInt("dd_iskeg"));
						p.setCategory_id(product.getInt("category_id"));
						p.setDd_deposit(product.getString("dd_deposit"));
						p.setStatus(product.getInt("status"));

						//mCallBackListener.productListcallback(p,0);
						KlinkDbHelper.getInstance().insertCountryCodes(p);
					}
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

			mCallBackListener.hideProgressDialog();
			//new CatalogCategoryInfoAsyncTask().execute();

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	private class CatalogCategoryInfoAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				//String sessionId = URLEncoder.encode(mAppPreferences.getSessionid(), "UTF-8");
				String url = AppUrls.createCustomerUrl(mAppPreferences.getSessionid())+"&q=catalogCategoryInfo"+
						"&categoryId="+"1";
				Log.e(TAG, "cat info url "+url);

				String response  = Globals.httpGetService(url);
				if (response.length() > 0) {
					Log.e(TAG, "CatalogCategoryInfoAsyncTask Response-->>"+response);
					//JSONObject jsonObjct = new JSONObject(response);
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

			mCallBackListener.hideProgressDialog();

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}


	//////////////////// TODO POST services //////////////////////////////////////////////
	//TODO customer create
	private class CustomerCreateAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			JSONObject jsonObject = new JSONObject();
			String jsonString = null ;
			try {

				jsonObject.put("email", "ganga.kondati@gmail.com");
				jsonObject.put("password", "ganga123");
				jsonObject.put("firstname", "Gangadhar");
				jsonObject.put("lastname", "Naidu");

				jsonString  = jsonObject.toString();

			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","customerCustomerCreate"));
			nameValuePairs.add(new BasicNameValuePair("customer",jsonString));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);

			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "CustomerCreateAsyncTask Response-->>"+response);

				if (response.length() > 0) {

					JSONObject jsonObjct = new JSONObject(response);
					mAppPreferences.saveCustomerId(jsonObjct.getString("result"));
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

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	//TODO customer email check
	private class CheckCustomerEmailAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.CHECK_CUSTOMEREMAIL_URL);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","checkCustomerEmail"));
			nameValuePairs.add(new BasicNameValuePair("email","ganga.kondati123@gmail.com"));
			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);

			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "CheckCustomerEmailAsyncTask Response-->>"+response);

				if (response.length() > 0) {
					JSONObject jsonObjct = new JSONObject(response);
					try {
						result = jsonObjct.getString("result");						
					} catch(Exception e){
						result = jsonObjct.getString("error");
					}
					//07-09 23:28:00.959: E/ApplicationDataLoader(11753): work status Response-->>{"error":"Customer with email ganga.kondati@gmail.com already exists. Try another email."}
					//07-09 23:28:37.672: E/ApplicationDataLoader(12260): work status Response-->>{"result":"true"}
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

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	//TODO customer email check
	private class RouteOrderAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		String errorResultMsg = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","routeOrder"));
			//28.601932,-81.198674
			nameValuePairs.add(new BasicNameValuePair("lat","26.381749"));
			nameValuePairs.add(new BasicNameValuePair("lng","-80.129732"));
			nameValuePairs.add(new BasicNameValuePair("state","FL"));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);
			Log.e(TAG, "URL-->>"+AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));

			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "RouteOrderAsyncTask Response-->>"+response);

				if (response.length() > 0) {
					JSONObject jsonObjct = new JSONObject(response);

					String status = jsonObjct.getString("status");
					if(status.equalsIgnoreCase("error")){
						String errorMsg = jsonObjct.getString("error");
						String errorCode = jsonObjct.getString("error_code");
						if(errorCode.equals("404")){
							result = errorMsg;
						} 
					} else {

						JSONObject storeJsonObject = jsonObjct.getJSONObject("stores");
						JSONArray deliveryArray = storeJsonObject.getJSONArray("deliverable");
						JSONArray opneArray = storeJsonObject.getJSONArray("open");
						JSONArray routedArray = storeJsonObject.getJSONArray("routed");

						for(int i = 0;i<deliveryArray.length();i++){

						}
						for(int i = 0;i<opneArray.length();i++){

						}
						for(int i = 0;i<routedArray.length();i++){

						}
					}
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

			if(result.equalsIgnoreCase("error")){

			} else if(result.equals("Exception")){
				this.cancel(true);

			} else {
				loadDelivaryLocationTask() ;
			}
		}
	}


	//TODO customer email check
	private class SetDeliveryLocationAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","setDeliveryLocation"));
			nameValuePairs.add(new BasicNameValuePair("storeId","3"));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);

			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "SetDeliveryLocationAsyncTask Response-->>"+response);


			} 
			catch (Exception e) {
				e.printStackTrace();
				result = "Exception";
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {

			loadCatalogProductslist();

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}


	private String customerAddressJson(){

		JSONObject jsonObject = new JSONObject();
		String jsonString = null ;
		try {

			jsonObject.put("street", "raod number 01");
			jsonObject.put("city", "Hyderabad");
			jsonObject.put("postcode", "500070");
			jsonObject.put("telephone", "123456789");
			jsonObject.put("region", "India");

			jsonString  = jsonObject.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	//TODO customer Address create
	private class CustomerAddressCreateAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","customerAddressCreate"));
			nameValuePairs.add(new BasicNameValuePair("customerId",mAppPreferences.getCustomerId()));
			nameValuePairs.add(new BasicNameValuePair("customerId",mAppPreferences.getCustomerId()));
			nameValuePairs.add(new BasicNameValuePair("shippingAddress",customerAddressJson()));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);


			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "CustomerAddressCreateAsyncTask Response-->>"+response);

				if (response.length() > 0) {
					JSONObject jsonObjct = new JSONObject(response);
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

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}


	private String createPaymentJson(){

		JSONObject jsonObject = new JSONObject();
		String jsonString = null ;
		try {

			jsonObject.put("cc_number", "raod number 01");
			jsonObject.put("cc_exp_year", "Hyderabad");//4_DIGIT_YEAR
			jsonObject.put("cc_exp_month", "123456789");//2_DIGITS_MONTH
			jsonObject.put("cc_cid", "India");//CARD_CODE

			jsonString  = jsonObject.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	//TODO customer Address create
	private class SaveUserCreditCardAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","saveUserCreditCard"));
			nameValuePairs.add(new BasicNameValuePair("customerId",mAppPreferences.getCustomerId()));
			nameValuePairs.add(new BasicNameValuePair("paymentMethod",createPaymentJson()));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);


			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "CustomerAddressCreateAsyncTask Response-->>"+response);

				if (response.length() > 0) {
					JSONObject jsonObjct = new JSONObject(response);
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

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	//TODO customer Address create
	private class ShoppingCartCheckoutAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","shoppingCartCheckout"));
			nameValuePairs.add(new BasicNameValuePair("shippingAddress",mAppPreferences.getCustomerId()));
			nameValuePairs.add(new BasicNameValuePair("customer",""));
			nameValuePairs.add(new BasicNameValuePair("paymentMethod",""));
			nameValuePairs.add(new BasicNameValuePair("products",""));
			nameValuePairs.add(new BasicNameValuePair("tip",""));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);

			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "CustomerAddressCreateAsyncTask Response-->>"+response);

				if (response.length() > 0) {
					JSONObject jsonObjct = new JSONObject(response);
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

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	private String prodcutsJson(){

		JSONObject jsonObject = new JSONObject();
		String jsonString = null ;
		try {

			jsonObject.put("product_id", "5");
			jsonObject.put("sku", "skol 126");
			jsonObject.put("qty", "1");

			jsonString  = jsonObject.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	//TODO customer Address create
	private class GetCartFeesAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","getCartFees"));
			nameValuePairs.add(new BasicNameValuePair("products",prodcutsJson()));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);

			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "CustomerAddressCreateAsyncTask Response-->>"+response);

				if (response.length() > 0) {
					JSONObject jsonObjct = new JSONObject(response);
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

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}

	//TODO verifyCartMinimum
	private class VerifyCartMinimumAsyncTask extends AsyncTask<String, String, String> {

		String result = "";
		@Override
		protected void onPreExecute() {
			//mOnDataloadListeners.onDataLoadPreEexcute("Loading Work status set "+mAppPreferences.getPagingCount());
		}

		@Override
		protected String doInBackground(String... params) {

			HttpPost httppost = new HttpPost(AppUrls.createCustomerUrl(mAppPreferences.getSessionid()));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q","verifyCartMinimum"));
			nameValuePairs.add(new BasicNameValuePair("products",prodcutsJson()));

			Log.e(TAG, "nameValuePairs-->>"+nameValuePairs);

			try {
				String response  = Globals.httpClientCall(httppost, nameValuePairs);
				Log.e(TAG, "CustomerAddressCreateAsyncTask Response-->>"+response);

				if (response.length() > 0) {
					JSONObject jsonObjct = new JSONObject(response);
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

			if(result.equals("No data")){
				//mOnDataloadListeners.onDataLoadExecuteMethod("Get Asset",result);
				//mAppPreferences.savePagingCount(0);

			} else if(result.equals("Exception")){
				this.cancel(true);
				//mOnDataloadListeners.onDataLoadExecuteMethod("Work status",result);
			} else {
				//Log.e(TAG,"page count "+mAppPreferences.getPagingCount());
				//new LoadWorkStatusTask().execute();	
			}
		}
	}
}
