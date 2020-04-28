package com.minimum.local;

public class SmsRequest {
	
	private String receiverNumber;
	private String smsBody;
	
	public SmsRequest() {
	}
	
	public SmsRequest(String receiverNumber, String smsBody) {
		super();
		this.receiverNumber = receiverNumber;
		this.smsBody = smsBody;
	}
	
	public String getReceiverNumber() {
		return receiverNumber;
	}
	public void setReceiverNumber(String receiverNumber) {
		this.receiverNumber = receiverNumber;
	}
	public String getSmsBody() {
		return smsBody;
	}
	public void setSmsBody(String smsBody) {
		this.smsBody = smsBody;
	}
	
}
