package com.search.to;

public class Account {
	private String type;
	private int userId;
	private int checkingAccount;
	private int savingsAccount;
	private char registeredChecking;
	private char registeredSavings;
	private char appliedChecking;
	private char appliedSavings;
	
	@Override
	public String toString() {
		if ( this.type.equals( "Customer" ) ) {
			return "Account type : " + type + " checking account : " + checkingAccount + " savings account : " + savingsAccount + "\n" +
					"applied for checking : " + appliedChecking + " applied for savings : " + appliedSavings;
		} else {
			return "Account type : " + type;
		}
		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCheckingAccount() {
		return checkingAccount;
	}
	public void setCheckingAccount(int checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	public int getSavingsAccount() {
		return savingsAccount;
	}
	public void setSavingsAccount(int savingsAccount) {
		this.savingsAccount = savingsAccount;
	}
	public char getRegisteredChecking() {
		return registeredChecking;
	}
	public void setRegisteredChecking(char registeredChecking) {
		this.registeredChecking = registeredChecking;
	}
	public char getRegisteredSavings() {
		return registeredSavings;
	}
	public void setRegisteredSavings(char registeredSavings) {
		this.registeredSavings = registeredSavings;
	}
	public char getAppliedChecking() {
		return appliedChecking;
	}
	public void setAppliedChecking(char appliedChecking) {
		this.appliedChecking = appliedChecking;
	}
	public char getAppliedSavings() {
		return appliedSavings;
	}
	public void setAppliedSavings(char appliedSavings) {
		this.appliedSavings = appliedSavings;
	}
	public String getType() {
		return type;
	}
	public void setType( String type ) {
		this.type = type;
	}
	
	
}
