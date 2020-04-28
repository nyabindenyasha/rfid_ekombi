package com.minimum.local;

public class MichelleRequest {
	
	private String meter_number;
	private String balance;

	public MichelleRequest() {
		super();
	}

	public MichelleRequest(String meter_number, String balance) {
		this.meter_number = meter_number;
		this.balance = balance;
	}

	public String getMeter_number() {
		return meter_number;
	}

	public void setMeter_number(String meter_number) {
		this.meter_number = meter_number;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

}
