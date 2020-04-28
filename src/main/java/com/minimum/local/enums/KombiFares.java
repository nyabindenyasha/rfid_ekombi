package com.minimum.local.enums;

public enum KombiFares {
	
	Fare1(1.00),
	Fare2(2.00),
	Fare3(5.00);
	
	private final double fare;
	
	KombiFares(double fare){
		this.fare = fare;
	}
	
	public double getKombiFare() {
		return fare;
	}

}
