package com.lti.controllers;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lti.models.BidList;
import com.lti.models.Shoes;
import com.lti.models.User;
import com.lti.services.AuthService;
import com.lti.services.CustomerService;
import com.lti.services.CustomerServiceImpl;
import com.lti.services.SystemService;
import com.lti.services.SystemServiceImp;
import com.lti.services.UserService;
import com.lti.services.UserServiceImpl;
import com.sun.tools.sjavac.Log;

public class UserScreen {

	static Scanner sc = new Scanner(System.in);
	static SystemService ss = new SystemServiceImp();
	static CustomerService cs = new CustomerServiceImpl();
	static User currentUser;
	private static Logger log = LogManager.getRootLogger();
	
	public static void setCurrUser(User user) {
		currentUser = user;
	}
	public static void display() {
		String input;
		System.out.println("\n----------------");
		System.out.println("Customer Portal");
		System.out.println("----------------");
		do {
			System.out.println("Enter the following number: \n1 to view products available \n2 to view owned products"
					+ "\n3 view bids \n4 to exit");
			input = sc.nextLine();
			
			switch(input) {
			case "1":
				int choice;
				Shoes shoepicked;
				ss.displayItems();
				do {
					System.out.println("Enter shoe id number to show more actions: ");
					choice = sc.nextInt();
					shoepicked = ss.getItemById(choice);
				}while (shoepicked == null);
				System.out.println(shoepicked);
				System.out.println("Enter 1 to make bid, 2 to edit bid");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					String res;
					res = ss.getItemStatus(shoepicked.getId(), currentUser.getId());
					if (res.length() > 1) {
						System.out.println("You have a bid on this item already\n**********");
						System.out.format("%-15s%-17s%-12s%-18s%s", "Item Status", "Customer","Offer","Payment Total","Shoe Id\n");
						System.out.println(res + "\n**********");
					}else {
						System.out.println("Enter bid amount: ");
						double bid = sc.nextDouble();
						if(cs.makeOffer(currentUser.getId(), shoepicked.getId(), bid)) {
							System.out.println("Bid successful");
						}else {
							System.out.println("Bid unsuccessful");
						}
					}
					sc.nextLine();
					display();
					break;
				case 2:
					System.out.println("Enter new bid amount: ");
					double bid = sc.nextDouble();
					int updated = cs.editItemBid(currentUser.getId(),shoepicked.getId(), bid);
					System.out.println(updated + " Bid edited");
					sc.nextLine();
					display();
					break;
				default:
					break;
				}
				break;
			case "2":
				double amount;
				boolean payed;
				for (BidList bid: ss.getAllBidsbyUser(currentUser.getId())){
					if (bid.getItemStatus().equals("Accepted") || bid.getItemStatus().equals("Payed")) {
						System.out.println(bid + " Remaining Balance: " + (bid.getOfferPrice() - bid.getPaymentTotal()));
						
					}
				}
				
				do {
					System.out.println("Enter shoe id number to show more actions: ");
					choice = sc.nextInt();
					shoepicked = ss.getItemById(choice);
					payed = ss.findBid(choice, currentUser.getId()).getItemStatus().equals("Payed");
					if(payed) {
						System.out.println("Item has been payed");
					}
				}while (shoepicked == null || payed == true);
				System.out.println("Enter 1 to make payment, 2 to go back");
				int pick = sc.nextInt();
				if (pick == 1) {
					System.out.println("Enter amount to pay:");
					amount = sc.nextDouble();
					if (cs.makePayment(currentUser.getId(), choice, amount)) {
						System.out.println("Payment succesful");
						if (ss.findBid(choice,currentUser.getId()).getOfferPrice() ==ss.findBid(choice,currentUser.getId()).getPaymentTotal() ) {
							ss.setItemStatus(choice, currentUser.getId(),"Payed");
						}
					}else {
						System.out.println("Payment unsuccessful");
					}
				}
				sc.nextLine();
				display();
				input = "4";
				break;
			case "3":
				for (BidList bid: ss.getAllBidsbyUser(currentUser.getId())){
					if (!bid.getItemStatus().equals("Accepted") || !bid.getItemStatus().equals("Payed")) {
						System.out.println(bid);
					}
				}
				display();
				input = "4";
				break;
			case "4":
				System.out.println("Thank you using shoe shopping system!");
				log.info("Customer Id" + currentUser.getId() + " has logged out");
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}
		}while(!input.equals("4"));
		
	}
	
}
