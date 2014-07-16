package com.klink.interfaces;

import com.klink.model.CatalogProductListModel;

public interface CallBackListener {

	public void productListLoadCallback(String name);
	public void productListcallback(CatalogProductListModel p,int cat_id);
	public void hideProgressDialog();
}

