package com.openclassrooms.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testAddTwoPositiveNumbers() {
		//ARRANGE
		int a=2;
		int b=3;
		Calculator calculator=new Calculator();
		
		//ACT
		int sum=calculator.add(a,b);
		int product = calculator.mult(a,b);
		
		// ASSERT
		assertEquals(5,sum);
		assertEquals(product, 6);
	}

}
