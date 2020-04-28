package com.minimum.local.requests;

public class UpdateUserAccountRequest {

	private int userId;
	private int accountId;
	private double bankBalance;
	private double ecocashBalance;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public double getEcocashBalance() {
		return ecocashBalance;
	}

	public void setEcocashBalance(double ecocashBalance) {
		this.ecocashBalance = ecocashBalance;
	}
	
}
