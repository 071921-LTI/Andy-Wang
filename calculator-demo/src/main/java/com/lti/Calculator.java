package com.lti;
import java.text.DecimalFormat;

import com.lti.exceptions.CalculatorException;
public class Calculator {

	/*
	 * Should be able to:
	 * 		- add
	 * 		- subtract
	 * 		- multiply
	 * 		- divide
	 * 			- throw a CalculatorException when attempting to divide by 0
	 * 		- isPrime: checks if a number is a prime number
	 * 		- compareThreeDecimals: returns true if the same up to 3 decimals
	 * 				- 3.123.compare...(3.1233445) should return true
	 */

	public double add(double x, double y) {
		return x + y;
	}

	public double subtract(double x, double y) {
		return x - y;
	}

	public double multiply(double x, double y) {
		return x * y;
	}

	public double divide(double x, double y){
		if (y == 0) {
			throw new CalculatorException();
		}else {
			double res = x / y;
			return res;
		}

	
		
	}

	public boolean isPrime(int x) {
		int count = x - 1;
		if (x == 1) {
			return false;
		}
		while (count > 1) {
			if (x % count == 0) {
				return false;
			}
			count --;
		}
		return true;
	}

	public boolean compareThreeDecimal(double x, double y) {
		DecimalFormat df = new DecimalFormat("#.###");
		
		return df.format(x).equals(df.format(y));

	}
	
//	public static void main(String[] args) {
//		Calculator calc = new Calculator();
//		System.out.println(calc.compareThreeDecimal(4.11111111, 4.11111));		
//		
//		DecimalFormat df = new DecimalFormat("#.###");
//		
//		System.out.println(df.format(4333.223438309));
//	}
}