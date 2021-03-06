package com.lti.daos;

import java.util.List;

import com.lti.models.BidList;
import com.lti.models.User;

public interface BidDao {
	public abstract int addItemBid(int buyer_id, int shoe_id, double bid_price, String item_status);//return item id
	public abstract int removeItemBid(int shoe_id,int cust_id);
	public abstract List<User> getCustomerBids(int shoe_id);
	public abstract List<String> getBids(int cust_id);
	public abstract String showStatus(int shoe_id,int customer_id);
	public abstract double getWeeklyPayments();
	public abstract double totalPayments();
	public abstract int setItemStatus(int shoe_id,int cust_id, String status);
	public abstract int editItemBid(int shoe_id, int customer_id, double bid_price);
	public abstract List<BidList>getAllBids();
	public abstract int removeItemBids(int shoe_id);
	public abstract List<BidList> getAllBidsByUser(int cust_id);
	boolean makePayment(int cust_id, int shoe_id, double amount);
	public abstract BidList findBid(int shoe_id, int cust_id);

}
