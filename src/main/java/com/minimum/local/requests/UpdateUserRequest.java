package com.minimum.local.requests;

public class UpdateUserRequest {
	
	private int id;
	private String mobileNumber;
	private String nationalId;
	private String rfidUniqueId;
	private String name;
	private String surname;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getRfidUniqueId() {
		return rfidUniqueId;
	}

	public void setRfidUniqueId(String rfidUniqueId) {
		this.rfidUniqueId = rfidUniqueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
