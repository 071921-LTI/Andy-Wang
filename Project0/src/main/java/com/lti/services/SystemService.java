package com.lti.services;

public interface SystemService {
	public abstract void displayItems();
	public abstract String getItemStatus(int shoe_id);
	public abstract void setItemStatus(String item, String status);
	public abstract double getWeeklyPayments();
	
}
