package com.minimum.local.requests;

public class UpdateUserRFIDUIDRequest {

	private int id;
	private String rfidUniqueId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRfidUniqueId() {
		return rfidUniqueId;
	}

	public void setRfidUniqueId(String rfidUniqueId) {
		this.rfidUniqueId = rfidUniqueId;
	}

}
