package com.lti.services;

import com.lti.daos.BidDao;
import com.lti.daos.BidsDB;
import com.lti.daos.ItemsDB;
import com.lti.daos.ItemsDao;

public class CustomerServiceImpl implements CustomerService{
	ItemsDao id = new ItemsDB();
	BidDao bd = new BidsDB();
	@Override
	public boolean makeOffer(int cust_id, int item_id, double price) {
		// TODO Auto-generated method stub
		int res = bd.addItemBid(cust_id, item_id, price, "Pending");
		if (res == -1) {
			return false;
		}
		return true;
	}

	@Override
	public double remainingBalance(int cust_id, int item_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
