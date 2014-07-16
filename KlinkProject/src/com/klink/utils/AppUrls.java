package com.klink.utils;

public class AppUrls {

	//public static String BASE_URL = "https://drinkdriverstest.com/services_v4.php/";
	public static String BASE_URL = "https://klinkdelivery.com/services_v4.php/";
    
	public static String LOGIN_URl = BASE_URL+"login";
	public static String CHECK_CUSTOMEREMAIL_URL = BASE_URL+"checkCustomerEmail";
	public static String CREATE_CUSTOMER_URL = BASE_URL+"customerCustomerCreate";
	public static String ROUTE_ORDER_URL = BASE_URL+"routeOrder";
	public static String DELIVARY_LOCATION_URL = BASE_URL+"setDeliveryLocation";
	public static String CATALOG_PRODUCTS_URL = BASE_URL+"catalogProductList";
	public static String CATALOG_CATEGORY_URL = BASE_URL+"catalogCategoryInfo";
	public static String CART_FREE_URL = BASE_URL+"getCartFees";
	public static String CART_MINIMUM_URL = BASE_URL+"verifyCartMinimum";
	public static String USER_CREDITCARD_URL = BASE_URL+"saveUserCreditCard";
	public static String CUSTOMER_ADDRESS_URL = BASE_URL+"customerAddressCreate";
	public static String SHOPPING_CART_URL = BASE_URL+"shoppingCartCheckout";
	
	public static String createCustomerUrl(String sessionId){
		String url = BASE_URL+"?sessionId="+sessionId;
		return url;
	}
	
	
//	on check out API, here's the response - Here are the JSONs that I send from the iOS app:
//
//	Address: {"is_default_billing":"1","postcode":"51111","city":"Orlando City","region":"AL","is_default_shipping":"1","telephone":"1112341234","street":"Test Street"}
//
//	Customer: {"license":"1234511111","customer_id":"269","instructions":"","firstname":"Test","store_id":0,"email":"test100@test.com","lastname":"Testovsky","website_id":0}
//
//	Payment method: {"cc_exp_year":2018,"cc_exp_month":11,"cc_cid":"","cc_type":"Visa","cc_sid":"card_4MI93SH6hPePsG","cc_owner":"","cc_number":"4242","cc_scid":"cus_4MI10WKPIKNn70”}
//
//	Products: [{"product_id":"10","sku":"smirnoff1","qty":"2”}]
//
//	Line 284 indicates it’s some kind of problem with the credit card processing… 
//	The parameters you get directly from the user entering her credit card are used to save the card in the system (via the API call saveUserCreditCard), then you need to send something else to the server, when making a purchase - cc_sid and cc_scid - those are internal, server-side IDs of the user and the card.
}