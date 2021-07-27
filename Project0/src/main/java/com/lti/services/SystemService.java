package com.lti.services;

public interface SystemService {
	public abstract void displayItems();
	public abstract String getItemStatus(String item);
	public abstract void setItemStatus(String item, String status);
	public abstract double getWeeklyPayments();
	
}
