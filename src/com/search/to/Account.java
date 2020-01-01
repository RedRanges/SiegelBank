package com.search.to;

public class Account {
	private int userId;
	private int checkingAccount;
	private int savingAccount;
	private char registered;
	
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
	public int getSavingAccount() {
		return savingAccount;
	}
	public void setSavingAccount(int savingAccount) {
		this.savingAccount = savingAccount;
	}
	public char getRegistered() {
		return registered;
	}
	public void setRegistered(char registered) {
		this.registered = registered;
	}
}
