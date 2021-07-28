package com.lti.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.lti.models.User;

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
				
				System.out.println(brand + " " + size + " " + shoeType + " " +color + " " + price);
				input = "6";
				break;
			case "2":
				input = "6";
				break;
			case "3":
				input = "6";
				break;
			case "4":
				input = "6";
				break;
			case "5":
				input = "6";
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


