//package com.klink.model;
//
//import java.util.ArrayList;
//
//
//public class Cart {
//	
//	private static Cart instance;
//	private ArrayList<Product> shoppingCart;
//	private double serviceFees;
//	private double orderTotal;
//	
//	private Cart()
//	{
//		this.shoppingCart = new ArrayList<Product>();
//	}
//
//	public static synchronized Cart getInstance(){
//	     if(instance==null){
//	       instance=new Cart();
//	     }
//	     return instance;
//	   }
//	
//	public void add(Product p)
//	{
//		shoppingCart.add(p);
//	}
//	
//	public void remove(Product p)
//	{
//		shoppingCart.remove(p);
//	}
//	
//	public int count(Product p)
//	{
//		int count = 0;
//		for (int i = 0; i < shoppingCart.size(); i++)
//		{
//			if (shoppingCart.get(i).equals(p))
//				count++;
//		}
//		return count;
//	}
//	
//	public int size()
//	{
//		return shoppingCart.size();
//	}
//	
//	public Product get(int i)
//	{
//		return shoppingCart.get(i);
//	}
//
//	public double getServiceFees() {
//		return serviceFees;
//	}
//
//	public void setServiceFees(double serviceFees) {
//		this.serviceFees = serviceFees;
//	}
//
//	public double getOrderTotal() {
//		return orderTotal;
//	}
//
//	public void setOrderTotal(double orderTotal) {
//		this.orderTotal = orderTotal;
//	}
//	
//}
