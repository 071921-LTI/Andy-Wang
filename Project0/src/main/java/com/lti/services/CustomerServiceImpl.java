package com.lti.services;

import java.util.List;

import com.lti.daos.BidDao;
import com.lti.daos.BidsDB;
import com.lti.daos.ItemsDB;
import com.lti.daos.ItemsDao;
import com.lti.models.BidList;

public class CustomerServiceImpl implements CustomerService{
	ItemsDao id = new ItemsDB();
	BidDao bd = new BidsDB();
	@Override
	public boolean makeOffer(int cust_id, int item_id, double price) {
		int res = bd.addItemBid(cust_id, item_id, price, "Pending");
		if (res == -1) {
			return false;
		}
		return true;
	}

	@Override
	public double remainingBalance(int cust_id, int item_id) {
		return 0;
	}

	@Override
	public int editItemBid(int shoe_id, int cust_id, double bid_price) {
		return bd.editItemBid(shoe_id, cust_id, bid_price);
	}

	@Override
	public boolean makePayment(int cust_id, int shoe_id, double amount) {
		BidList bid = bd.findBid(shoe_id, cust_id);
		double total = bid.getPaymentTotal();
		double offer = bid.getOfferPrice();
		if (offer - total < amount ) {
			amount = bid.getOfferPrice();
		}else {
			amount = total + amount;
		}
		return bd.makePayment(cust_id, shoe_id, amount);
	}

}
