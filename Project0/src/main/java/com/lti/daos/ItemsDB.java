package com.lti.daos;

import java.util.List;

import com.lti.models.Shoes;

public class ItemsDB implements ItemsDao{

	@Override
	public Shoes getItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shoes> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addItem(Shoes shoe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateItem(Shoes shoe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeItem(Shoes shoe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Shoes> getShoebyCustomerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
