package com.klink.model;



public class CustomerController {
	private static CustomerController instance;
	private Customer customer;
	private boolean loggedIn;

	// Global variable

	// Restrict the constructor from being instantiated
	private CustomerController(){
		this.customer = new Customer();
		this.setLoggedIn(false);
	}


	public static synchronized CustomerController getInstance(){
		if(instance==null){
			instance=new CustomerController();
		}
		return instance;
	}

	public Customer getCustomer()
	{
		return this.customer;
	}


	public void finish()
	{
		setLoggedIn(true);
	}


	public boolean isLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
