package com.lti.daos;

public interface SystemDao {
	public abstract void displayItems();
	public abstract void setItemStatus(String item, String status);
	public abstract String getItemStatus(String status);
	public abstract double getWeeklyPayments();
}
