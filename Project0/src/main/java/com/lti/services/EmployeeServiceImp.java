package com.lti.services;

import com.lti.daos.BidDao;
import com.lti.daos.BidsDB;
import com.lti.daos.ItemsDB;
import com.lti.daos.ItemsDao;
import com.lti.models.Shoes;

public class EmployeeServiceImp implements EmployeeService{
	private ItemsDao id= new ItemsDB();
	private BidDao bd = new BidsDB();
	@Override
	public boolean addShoes(Shoes item) {
		// TODO Auto-generated method stub
		return id.addItem(item);
	}

	@Override
	public boolean acceptOffer(Shoes item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectOffer(Shoes item) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public int removeShoes(Shoes item) {
		// TODO Auto-generated method stub
		return id.removeItem(item);
	}

	@Override
	public boolean updateShoes(Shoes item) {
		// TODO Auto-generated method stub
		return id.updateItem(item);
	}
	
}
