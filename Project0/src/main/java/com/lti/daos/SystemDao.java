package com.lti.daos;

import java.util.List;

import com.lti.models.Shoes;

public interface SystemDao {
	public abstract List<Shoes> displayItems();
	public abstract void setItemStatus(String item, String status);
	public abstract String getItemStatus(String status);
	public abstract double getWeeklyPayments();
}
