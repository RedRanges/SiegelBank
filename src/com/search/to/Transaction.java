package com.search.to;

public class Transaction {
	private String type;
	private double amount;
	private int userId;
	private int id;

	
	public String toString() {
		return "type : " + type + " amount : " + amount + "userID : " + userId + "transaction id : " + id; 
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

}
