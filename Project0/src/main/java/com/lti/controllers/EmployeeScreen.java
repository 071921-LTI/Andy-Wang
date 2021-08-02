package com.lti.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lti.models.BidList;
import com.lti.models.Shoes;
import com.lti.models.User;
import com.lti.services.CustomerService;
import com.lti.services.CustomerServiceImpl;
import com.lti.services.EmployeeService;
import com.lti.services.EmployeeServiceImp;
import com.lti.services.SystemService;
import com.lti.services.SystemServiceImp;

public class EmployeeScreen {
	static Scanner sc = new Scanner(System.in);
	static EmployeeService  es= new EmployeeServiceImp();
	static SystemService ss = new SystemServiceImp();
//	static CustomerService cs = new CustomerServiceImpl();
	static User employee;
	
	public static void display() {
		String input;
		System.out.println("\n----------------");
		System.out.println("Employee Portal");
		System.out.println("----------------");
		do {
			System.out.println("Enter the following number: \n1 to add item \n2 to view all items"
					+ "\n3 to see item offers \n4 to view all payments\n5 to exit");
			input = sc.nextLine();
		
			
			switch(input) {
			case "1":
				System.out.println("Enter shoe details to add: ");
				System.out.println("Brand:");
				String brand = sc.nextLine(); //Nike, Adidas, Vans, Converse, Under Armour, New Balance
				System.out.println("Size:");
				int size =  sc.nextInt();
				System.out.println("Shoe type:");
				sc.nextLine();
				String shoeType = sc.nextLine(); //sandals, sneakers, slides, cleats
				System.out.println("Color:");
				String color = sc.nextLine(); //red, blue, green, orange, black, white
				System.out.println("Price:");
				double price = sc.nextDouble();
				if(es.addShoes(new Shoes(brand,size,shoeType,color,price))) {
					System.out.println("Item added succefully\n");
				}else {
					System.out.println("Item could not be added\n");
				}
				sc.nextLine();
				//System.out.println(brand + " " + size + " " + shoeType + " " +color + " " + price);
				break;
			case "2":
				int choice;
				Shoes shoepicked;
				ss.displayItems();
				do {
					System.out.println("Enter shoe id number to show more actions: ");
					choice = sc.nextInt();
					shoepicked = ss.getItemById(choice);
				}while (shoepicked == null);
				System.out.println(shoepicked);
				System.out.println("Enter 1 to show bid offers, 2 to update shoe info, 3 to delete, 4 to go back");
				choice = sc.nextInt();
				switch (choice) {
					case 1:
						List <User> cust = ss.getCustomerBids(shoepicked.getId());
						for (User c : cust) {
							System.out.format("%-20s%-20s%-10s%-20s%-18s%s", "Item Status", "Customer","Offer","Payment Total","Shoe Id","Price\n");
							System.out.println(ss.getItemStatus(shoepicked.getId(), c.getId()));
							
						}
						sc.nextLine();
						break;
					case 2:
						System.out.println(shoepicked);
						System.out.println("Brand:");
						sc.nextLine();
						brand = sc.nextLine();
						System.out.println("Size:");
						size = sc.nextInt();
						System.out.println("Type:");
						sc.nextLine();
						shoeType = sc.nextLine(); 
						sc.nextLine();
						System.out.println("Color:");
						color = sc.nextLine();
						System.out.println("Price:");
						price = sc.nextDouble();
						
						shoepicked.setBrand(brand);
						shoepicked.setSize(size);
						shoepicked.setShoeType(shoeType);
						shoepicked.setColor(color);
						shoepicked.setPrice(price);
						if(es.updateShoes(shoepicked)) {
							System.out.println("Update successful");
						}else {
							System.out.println("Update unsuccessful");
						}
						sc.nextLine();
						display();

						break;
					case 3:
						int removed;
						if (ss.getCustomerBids(shoepicked.getId()).isEmpty()) {
							removed = es.removeShoes(shoepicked);
							System.out.println(removed + " item removed");
						}else {
							System.out.println("There are some offers for this item are you sure you want to delete?\nPress 1 for yes, 2 for no");
							int confirm = sc.nextInt();
							if (confirm == 1) {
								removed =ss.removeItemBids(shoepicked.getId());
								System.out.println(removed + " item removed");
							}
								sc.nextLine();
								input = "3";
						}
				
						break;
					case 4:
						sc.nextLine();
						input = "3";
						break;
					default:
						System.out.println("Invalid selection!");
						break;
				
				}
				
				break;
			case "3":
				List<BidList> allBids = ss.getAllBids();
				List <User> bidders;
				int buyerId, itemId;
				String status;
				System.out.println("Bids\n-----");
				for (BidList bids:allBids) {
					System.out.println(bids);
				}
				System.out.println("-----");
				System.out.println("Enter 1 to accept a bid offer or 2 to go back to menu ");
				choice = sc.nextInt();
				if (choice == 1) {
					System.out.println("Enter Item Id number: ");
					buyerId = sc.nextInt();
					System.out.println("Enter Buyer Id number: ");
					itemId = sc.nextInt();
					status = ss.getItemStatus(buyerId, itemId);
					while (status.length() <= 1) {
						System.out.println("Bid does not exist enter correct info");
						for (BidList bids:allBids) {
							System.out.println(bids);
						}
						System.out.println("Enter Item Id number: ");
						buyerId = sc.nextInt();
						System.out.println("Enter Buyer Id number: ");
						itemId = sc.nextInt();
						status = ss.getItemStatus(buyerId, itemId);
					}
					
					ss.setItemStatus(buyerId, itemId, "Accepted");
					bidders = ss.getCustomerBids(itemId);
					for (User bidder:bidders) {
						if (bidder.getId() != buyerId) {
							ss.removeItemBid(bidder.getId(), itemId);
						}
					}
					System.out.println("You have accepted an offer");
				}else {
					sc.nextLine();
					display();
				}
				break;
			case "4":
				input = "6";
				break;
			case "5":
				System.out.println("Thank you for using shoe shopping system!");
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		}while(!input.equals("5"));
		
	}
}


