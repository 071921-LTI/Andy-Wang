package com.lti.services;

import com.lti.daos.SystemDao;

public class SystemServiceImp implements SystemService{
	SystemDao sd;
	@Override
	public void displayItems() {
		// TODO Auto-generated method stub
		sd.displayItems();
	}

	@Override
	public String getItemStatus(String item) {
		// TODO Auto-generated method stub
		return sd.getItemStatus(item);
	}

	@Override
	public void setItemStatus(String item, String status) {
		// TODO Auto-generated method stub
		sd.setItemStatus(item, status);
	}

	@Override
	public double getWeeklyPayments() {
		// TODO Auto-generated method stub
		return sd.getWeeklyPayments();
	}

}
