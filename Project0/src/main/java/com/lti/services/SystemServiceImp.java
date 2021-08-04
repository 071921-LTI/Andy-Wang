package com.lti.services;

import java.util.List;

import com.lti.daos.BidDao;
import com.lti.daos.BidsDB;
import com.lti.daos.ItemsDB;
import com.lti.daos.ItemsDao;
import com.lti.models.BidList;
import com.lti.models.Shoes;
import com.lti.models.User;

public class SystemServiceImp implements SystemService{
	ItemsDao id = new ItemsDB();
	BidDao bd = new BidsDB();
	@Override
	public Shoes getItemById(int shoeid) {
		Shoes shoe = id.getItemById(shoeid);
		return shoe;
	}
	@Override
	public void displayItems() {
		List<Shoes> items;
		items = id.getItems();
		System.out.format("%-10s%-20s%-10s%-20s%-1s", "ShoeID", "Brand","Size","Type","Color\n");
		System.out.println("--------------------------------------------------------------------------------");
		for (Shoes shoe:items) {
			System.out.format(" %-10d%-20s%-10d%-20s%-20s", shoe.getId(),shoe.getBrand(),shoe.getSize(),shoe.getShoeType(),shoe.getColor());
			System.out.println();
		}
	}

	@Override
	public String getItemStatus(int shoe_id,int cust_id) {
		return bd.showStatus(shoe_id,cust_id);
	}

	@Override
	public int setItemStatus(int shoe_id,int cust_id, String status) {
		return bd.setItemStatus(shoe_id,cust_id, status);
	}

	@Override
	public double getWeeklyPayments() {
		return bd.getWeeklyPayments();
	}
	@Override
	public double getTotalPayments() {
		return bd.totalPayments();
	}
	@Override
	public List<User> getCustomerBids(int shoe_id) {
		return bd.getCustomerBids(shoe_id);
	}
	@Override
	public List<BidList> getAllBids() {
		return bd.getAllBids();
	}
	@Override
	public int removeItemBid(int shoe_id, int cust_id) {
		return bd.removeItemBid(shoe_id, cust_id);
	}
	@Override
	public int removeItemBids(int shoe_id) {
		return bd.removeItemBids(shoe_id);
	}
	@Override
	public List<BidList> getAllBidsbyUser(int id) {
		return bd.getAllBidsByUser(id);
		
	}
	@Override
	public BidList findBid(int shoe_id, int cust_id) {
		return bd.findBid(shoe_id,cust_id);
	}



}
