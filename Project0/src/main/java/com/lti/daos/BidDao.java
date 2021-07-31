package com.lti.daos;

import java.util.List;

import com.lti.models.Shoes;
import com.lti.models.User;

public interface BidDao {
	public abstract boolean updateStatus(int shoe_id, int customer_id,String status);
	public abstract int addItemBid(int buyer_id, int shoe_id, double bid_price, String item_status);//return item id
	public abstract int removeItemBid(int shoe_id,int cust_id);
	public abstract List<User> getCustomerBids(int shoe_id);
	public abstract List<String> getBids(int cust_id);
	public abstract String showStatus(int shoe_id,int customer_id);
	public abstract double getWeeklyPayments();
	public abstract double totalPayments();
	public abstract boolean setItemStatus(int shoe_id,int cust_id, String status);
	public abstract boolean editItemBid(int shoe_id, int customer_id, double bid_price);

}
