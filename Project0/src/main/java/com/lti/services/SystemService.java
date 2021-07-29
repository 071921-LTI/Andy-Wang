package com.lti.services;

import com.lti.models.Shoes;

public interface SystemService {
	public abstract void displayItems();
	public abstract String getItemStatus(int shoe_id);
	public abstract void setItemStatus(String item, String status);
	public abstract double getWeeklyPayments();
	public abstract Shoes getItemById(int id);
	
}
