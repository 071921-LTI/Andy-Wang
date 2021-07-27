package com.lti.controllers;

import java.util.Scanner;

import com.lti.exceptions.AuthException;
import com.lti.exceptions.UserInvalidException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.services.AuthService;
import com.lti.services.AuthServiceImpl;
import com.lti.services.UserService;
import com.lti.services.UserServiceImpl;

public class MenuScreen {
	
	static Scanner sc = new Scanner(System.in);
	static UserService us;
	static AuthService as;
	
	// Menu logic
	public static void display() {
		String input, userType; 
		int choice;
		
		do {
			System.out.println("Enter 1 for customer 2 for employee");
			choice = sc.nextInt();
			if (choice == 1) {
				userType = "customers";
			}else {
				userType = "employees";
			}
		}while (choice > 2);
		
		input = sc.nextLine();
		
		do {
			as = new AuthServiceImpl(userType);
			us = new UserServiceImpl(userType);
			
			System.out.println("Enter: \n1 to login\n2 to register\n3 to exit");
			input = sc.nextLine();
			
			switch(input) {
			case "1":
				System.out.println("Enter username:");
				String username = sc.nextLine();
	
				try {
					User user = us.getUser(username);
					System.out.println("Enter password:");
					String password = sc.nextLine();
					User toBeChecked = new User(username, password);

					if(as.login(toBeChecked)) {
						System.out.println("Successfully logged in!");
						input = "3";
					} else {
						System.out.println("Wrong credentials");
					}
				} catch (UserNotFoundException e) {
					System.out.println("User was not found.");
				} catch (AuthException e) {
					System.out.println("Wrong credentials");
				}
				break;
			case "2":
				// Do stuff here
				System.out.println("Enter a username:");
				String usernameNew = sc.nextLine();
				try {
					boolean user = us.findUser(usernameNew);
				} catch (UserInvalidException e) {
					System.out.println("Username already taken!");
					break;
				}
				
				System.out.println("Enter password:");
				String passwordNew = sc.nextLine();
				if(us.addUser(new User(usernameNew, passwordNew))) {
					System.out.println("Register successful!");
				}else {
					System.out.println("Unable to accomplish operation.");
				}
				break;
			case "3":
				System.out.println("Thank you for using our shopping system!");
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		} while(!input.equals("3"));
	}
}
