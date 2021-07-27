package com.lti.controllers;

import java.util.Scanner;

public class EmployeeScreen {
	static Scanner sc = new Scanner(System.in);
	public static void display() {
		String input;
		System.out.println("\n----------------");
		System.out.println("Employee Portal");
		System.out.println("----------------");
		do {
			System.out.println("Enter the following number: \n1 to add item \n2 to remove item"
					+ "\n3 to see item offers \n4 to view all items,\n5 to view all payments\n6 to exit");
			input = sc.nextLine();
		
			
			switch(input) {
			case "1":
				input = "3";
				break;
			case "2":
				input = "3";
				break;
			case "3":
				input = "3";
				break;
			case "4":
				input = "3";
				break;
			case "5":
				input = "3";
				break;
			case "6":
				System.out.println("Thank you for using shoe shopping system!");
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		}while(!input.equals("6"));
		
	}
}


