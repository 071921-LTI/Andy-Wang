package com.lti.daos;

import java.util.List;

import com.lti.models.Shoes;
import com.lti.models.User;

public class BidsDB implements BidDao {

	@Override
	public boolean updateStatus(int shoe_id, int customer_id, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addItemBid(int buyer_id, int shoe_id, int bid_price) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeItemBid(int shoe_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getCustomerBids(int shoe_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shoes> getBids(int cust_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showStatus(int shoe_id, int customer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getWeeklyPayments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalPayments() {
		// TODO Auto-generated method stub
		return 0;
	}

}
