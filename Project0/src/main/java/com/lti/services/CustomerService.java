package com.lti.services;

import com.lti.models.BidList;
import com.lti.models.User;

public interface CustomerService {
	//public abstract User getUserInfo();
	public abstract boolean makeOffer(int cust_id, int item_id,double price);
	public abstract double remainingBalance(int cust_id,int item_id);
	public abstract int editItemBid(int cust_id, int shoe_id, double bid_price); 
	
}
