package com.lti.controllers;

import java.util.Scanner;

import com.lti.models.Shoes;
import com.lti.services.AuthService;
import com.lti.services.SystemService;
import com.lti.services.SystemServiceImp;
import com.lti.services.UserService;

public class UserScreen {

	static Scanner sc = new Scanner(System.in);
	static SystemService ss = new SystemServiceImp();
	public static void display() {
		String input;
		System.out.println("\n----------------");
		System.out.println("Customer Portal");
		System.out.println("----------------");
		do {
			System.out.println("Enter the following number: \n1 to view products available \n2 to view owned products"
					+ "\n3 to exit");
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
					break;
				case 2:
					break;
				default:
					break;
				}
				input = "3";
				break;
			case "2":
				input = "3";
				break;
			case "3":
				System.out.println("Thank you using shoe shopping system!");
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}
		}while(!input.equals("3"));
		
	}
	
}
