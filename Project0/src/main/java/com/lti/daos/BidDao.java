package com.lti.daos;

import java.util.List;

import com.lti.models.Shoes;
import com.lti.models.User;

public interface BidDao {
	public abstract boolean updateStatus(int shoe_id, int customer_id,String status);
	public abstract int addItemBid(int buyer_id, int shoe_id,int bid_price); //return item id
	public abstract int removeItemBid(int shoe_id);
	public abstract List<User> getCustomerBids(int shoe_id);
	public abstract List<Shoes> getBids(int cust_id);
	public abstract String showStatus(int shoe_id,int customer_id);
	public abstract double getWeeklyPayments();
	public abstract double totalPayments();
}
