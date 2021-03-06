package com.lti;

import com.lti.controllers.MenuScreen;
import com.lti.daos.BidDao;
import com.lti.daos.BidsDB;
import com.lti.daos.ItemsDB;
import com.lti.daos.ItemsDao;
import com.lti.daos.UserDB;
import com.lti.daos.UserDao;
import com.lti.models.Shoes;

public class Driver {
	public static void test() {
		UserDao ud = new UserDB("customer");
		ItemsDao id = new ItemsDB();
		BidDao bd = new BidsDB();
		MenuScreen.display();
		Shoes shoe = new Shoes();
		
		shoe = id.getItemById(4);
		System.out.println(shoe.getId() + shoe.getBrand() + shoe.getColor() + shoe.getShoeType() + shoe.getSize());
		id.getItems();
		String res = bd.showStatus(2, 1);
		System.out.println(res);
		System.out.println(res.length() > 1);
		
		res = bd.showStatus(1, 1);
		System.out.println(res);
		System.out.println(res.length() > 1);

		bd.setItemStatus(2, 1, "Pending");
	}
	public static void main(String[] args) {
//		test();
		MenuScreen.display();

	}

}