package com.search.to;

public class CheckingAccount {
	private int accountNumber;
	private double balance;
	private int userId;
	
	@Override
	public String toString() {
		return "account number : " + accountNumber + "balance : $" + balance;
		
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
