package com.lti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.lti.exceptions.CalculatorException;

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {

	/*
	 * JUnit annotations
	 * 	- @BeforeEach
	 * 	- @AfterEach
	 * 	- @BeforeAll
	 * 	- @AfterAll
	 * 	- @Test
	 * 	- @Ignore
	 *  - @Order
	 */
	
	private static Calculator calc;
	
	
	@BeforeAll
	public static void setUp() {
		calc = new Calculator();
	}
	
	@AfterAll
	public static void tearDown() {
	}
	
	@Order(1)
	@Test
	public void addTwoAndTwo() {
		double expected = 4;
		double actualResult = calc.add(2, 2);
		assertEquals(expected, actualResult,.0001, "Adding 2 and 2 should be 4");
	}
	
	@Order(2)
	@Test
	public void addNegandPos() {
		double expected = -10;
		double actualResult = calc.add(-20, 10);
		assertEquals(expected, actualResult, .0001, "Adding -20 and 10 should be -10");
	}
	
	
	@Order(3)
	@Test
	public void addNegandNeg() {
		double expected = -20;
		double actualResult = calc.add(-10, -10);
		assertEquals(expected, actualResult,.0001, "Adding -10 and -10 should be -20");
	}
		
	
	@Order(4)
	@Test
	public void primeNumThree() {
		boolean expected = true;
		boolean actualResult = calc.isPrime(3);
		assertEquals(expected, actualResult, "Testing if 3 is prime");
	}
	
	@Order(5)
	@Test
	public void primeNumTen() {
		boolean expected = false;
		boolean actualResult = calc.isPrime(10);
		assertEquals(expected, actualResult, "Testing if 10 is prime");
	}
	
	@Order(6)
	@Test
	public void primeNumOne() {
		boolean expected = false;
		boolean actualResult = calc.isPrime(1);
		assertEquals(expected, actualResult, "Testing if 1 is prime");
	}
	
	
	@Order(7)
	@Test
	public void divideBy0() {
		assertThrows(CalculatorException.class, () -> calc.divide(1,0));
	}
	
	
	@Order(8)
	@Test
	public void multTwoAndTwo() {
		double expected = 4;
		double actualResult = calc.multiply(2, 2);
		assertEquals(expected, actualResult, "Multiplying 2 and 2 should be 4");
	}
	
	@Order(9)
	@Test
	public void multNegTwoandPosTwo() {
		double expected = -4;
		double actualResult = calc.multiply(-2, 2);
		assertEquals(expected, actualResult, "Multiplying -2 and 2 should be -4");
	}
	
	
	@Order(10)
	@Test
	public void multNegTenandNegTwo() {
		double expected = 100;
		double actualResult = calc.multiply(-10, -10);
		assertEquals(expected, actualResult, "Multiplying -10 and -10 should be 100");
	}
	
	
	@Order(11)
	@Test
	public void divideNegTenandNegTwo() {
		double expected = 5;
		double actualResult = calc.divide(-10, -2);
		assertEquals(expected, actualResult, "Dividing -10 and -2 should be 5");
	}
	
	@Order(12)
	@Test
	public void divideFiveandTwo() {
		double expected = 2.5;
		double actualResult = calc.divide(5, 2);
		assertEquals(expected, actualResult, "Dividing 5 and 2 should be 2.5");
	}
	
	@Order(13)
	@Test
	public void divideNegTenandTwo() {
		double expected = -5;
		double actualResult = calc.divide(-10, 2);
		assertEquals(expected, actualResult, "Dividing -10 and 2 should be -5");
	}
	
	@Order(14)
	@Test 
	public void compareDecimal1() {
		boolean expected = false;
		boolean actual = calc.compareThreeDecimal(11111.111111, 1111.11111);
		assertEquals(expected, actual);
	}
	
	@Order(15)
	@Test 
	public void compareDecimal2() {
		boolean expected = false;
		boolean actual = calc.compareThreeDecimal(3.1242, 3.1234);
		assertEquals(expected, actual);
	}
	
	@Order(16)
	@Test 
	public void compareDecimal3() {
		boolean expected = true;
		boolean actual = calc.compareThreeDecimal(1.011, 1.01123);
		assertEquals(expected, actual);
	}
	
	@Order(17)
	@Test 
	public void addDecimals() {
		double expected = 2.75;
		double actual = calc.add(1.25, 1.50);
		assertEquals(expected, actual, .00001, "Adding 1.25 and 1.50 should be 2.75");
	}
	
	@Order(18)
	@Test
	public void subTenAndTwo() {
		double expected = 8;
		double actualResult = calc.subtract(10, 2);
		assertEquals(expected, actualResult,.0001, "Substracting and 10 should be 2");
	}
	
	
	@Order(19)
	@Test
	public void subNegTenAndNegTwo() {
		double expected = -8;
		double actualResult = calc.subtract(-10, -2);
		assertEquals(expected, actualResult,.0001, "Substracting and -10 should be -2");
	}
	
	
	@Order(20)
	@Test
	public void subDecimal() {
		double expected = 8.22;
		double actualResult = calc.subtract(10, 1.78);
		assertEquals(expected, actualResult,.0001, "Substracting and 10 should be 8.22");
	}
	
	@Order(21)
	@Test
	public void subDecimal1() {
		double expected = -8.22;
		double actualResult = calc.subtract(-10, -1.78);
		assertEquals(expected, actualResult,.0001, "Substra cting and 10 should be -8.22");
	}
	@Order(22)
	@Test 
	public void compareDecimal4() {
		boolean expected = true;
		boolean actual = calc.compareThreeDecimal(3333.0, 3333.0);
		assertEquals(expected, actual);
	}
	
}