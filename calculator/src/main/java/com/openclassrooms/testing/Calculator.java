package com.openclassrooms.testing;

public class Calculator {

	public int add(int a, int b) {
	
		return a+b;
	}

	public int mult(int a, int b) {
		
		return a*b;
	}

	public void longCalculation() {
		try {
		    // Attendre 2 secondes
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
