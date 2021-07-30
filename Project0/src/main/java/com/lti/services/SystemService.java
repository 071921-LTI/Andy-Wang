package com.lti.services;

import java.util.List;

import com.lti.models.Shoes;
import com.lti.models.User;

public interface SystemService {
	public abstract void displayItems();
	public abstract String getItemStatus(int shoe_id, int cust_id);
	public abstract double getWeeklyPayments();
	public abstract Shoes getItemById(int id);
	public abstract boolean setItemStatus(int shoe_id,int cust_id, String status);
	public abstract List<User> getCustomerBids(int shoe_id);
}
