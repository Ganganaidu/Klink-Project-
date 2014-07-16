package com.klink.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.klink.activity.MyApplication;
import com.klink.model.CatalogProductListModel;

public class KlinkDbHelper extends SQLiteOpenHelper {

	private static final String TAG = "KlinkDbHelper";

	/**
	 * The name of the database.
	 */
	public static final String DB_NAME = "Klnik.db";
	public static final String ACTIVITY = "";

	/**
	 * The DB's version number. This needs to be increased on schema changes.
	 */
	public static final int DB_VERSION = 1;

	/**
	 * Singleton instance of {@link KlinkDbHelper}.
	 */
	private static KlinkDbHelper instance = null;
	private SQLiteDatabase db;

	/**
	 * @return the {@link KlinkDbHelper} singleton.
	 */
	public static KlinkDbHelper getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new KlinkDbHelper();
		}
	}

	private KlinkDbHelper() {
		super(MyApplication.getInstance().getApplicationContext(), DB_NAME,null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String activity_asset_mapping = "CREATE TABLE cata_log_productlist("
				+"product_list_internal_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+"entity_id INTEGER ,"
				+"entity_type_id INTEGER ,"
				+"attribute_set_id INTEGER ,"
				+"type_id TEXT,"
				+"sku TEXT,"
				+"has_options INTEGER,"
				+"required_options INTEGER,"
				+"created_at TEXT DEFAULT NULL,"
				+"updated_at TEXT DEFAULT NULL,"
				+"name TEXT ,"
				+"description TEXT,"
				+"short_description TEXT,"
				+"price INTEGER,"
				+"image TEXT,"
				+"dd_iskeg INTEGER,"
				+"category_id INTEGER," 
				+"dd_deposit INTEGER NOT NULL,"
				+"status INTEGER,"
				+"entity1 TEXT,"
				+"entity2 TEXT,"
				+"entity3 TEXT,"
				+"entity4 TEXT)";
		db.execSQL(activity_asset_mapping);
	}

	public void insertCountryCodes(CatalogProductListModel mCatalogProductListModel) {

		db = getWritableDatabase();
		ContentValues cv = new ContentValues();

		try {

			db.beginTransaction();
			cv.put("entity_id", mCatalogProductListModel.getEntity_id());
			cv.put("entity_type_id", mCatalogProductListModel.getEntity_type_id());
			cv.put("attribute_set_id", mCatalogProductListModel.getAttribute_set_id()); 
			cv.put("type_id", mCatalogProductListModel.getType_id());
			cv.put("sku", mCatalogProductListModel.getSku());
			cv.put("has_options", mCatalogProductListModel.getHas_options());
			cv.put("required_options", mCatalogProductListModel.getRequired_options());
			cv.put("created_at", mCatalogProductListModel.getCreated_at());
			cv.put("updated_at", mCatalogProductListModel.getUpdated_at());
			cv.put("name", mCatalogProductListModel.getName());
			cv.put("description", mCatalogProductListModel.getDescription());
			cv.put("short_description", mCatalogProductListModel.getShort_description());
			cv.put("price", mCatalogProductListModel.getPrice());
			cv.put("image", mCatalogProductListModel.getImage());
			cv.put("dd_iskeg", mCatalogProductListModel.getDd_iskeg());
			cv.put("category_id", mCatalogProductListModel.getCategory_id());
			cv.put("dd_deposit", mCatalogProductListModel.getDd_deposit());
			cv.put("entity1", "");
			cv.put("entity2", "");
			cv.put("entity3", "");
			cv.put("entity4", "");

			db.insert("cata_log_productlist", null, cv);
			db.setTransactionSuccessful();

		} catch(Exception e) {
			e.printStackTrace();	
		}
		finally {
			db.endTransaction();
			db.close();	
		}
	}

	/**
	 * delete data
	 */
	public synchronized  void deleteCatalogProductlist(){

		db = getWritableDatabase();
		try {
			db.delete("cata_log_productlist",null,null);
		}catch (Exception e) {}
		finally {
			db.close();
		}
	}

	// This on upgrade is used for changes of version to version
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
