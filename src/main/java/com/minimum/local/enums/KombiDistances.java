package com.minimum.local.enums;

public enum KombiDistances {
	
	Distance1(20),
	Distance2(30),
	Distance3(50);
	
	private final int maximumDistance;
	
	KombiDistances(int maximumDistance){
		this.maximumDistance = maximumDistance;
	}
	
	public int getKombiDistance() {
		return maximumDistance;
	}

}
