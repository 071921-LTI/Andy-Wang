package com.lti.services;

import java.util.List;

import com.lti.daos.BidDao;
import com.lti.daos.BidsDB;
import com.lti.daos.ItemsDB;
import com.lti.daos.ItemsDao;
import com.lti.models.Shoes;

public class SystemServiceImp implements SystemService{
	ItemsDao id = new ItemsDB();
	BidDao bd = new BidsDB();
	@Override
	public Shoes getItemById(int shoeid) {
		// TODO Auto-generated method stub
		Shoes shoe = id.getItemById(shoeid);
		return shoe;
	}
	@Override
	public void displayItems() {
		List<Shoes> items;
		items = id.getItems();
		System.out.format("%-10s%-20s%-10s%-20s%-20s%s", "ShoeID", "Brand","Size","Type","Color","Price\n");
		System.out.println("-----------------------------------------------------------------------------------------");
		for (Shoes shoe:items) {
			System.out.format(" %-10d%-20s%-10d%-20s%-20s%2.2f", shoe.getId(),shoe.getBrand(),shoe.getSize(),shoe.getShoeType(),shoe.getColor(),shoe.getPrice());
			System.out.println();
		}
	}

	@Override
	public String getItemStatus(int shoe_id) {
		// TODO Auto-generated method stub
		return "0";
	//	return bd.showStatus(shoe_id)
	}

	@Override
	public void setItemStatus(String item, String status) {
		// TODO Auto-generated method stub
		//sd.setItemStatus(item, status);
	}

	@Override
	public double getWeeklyPayments() {
		// TODO Auto-generated method stub
		return 0;
		//	return sd.getWeeklyPayments();
	}



}
