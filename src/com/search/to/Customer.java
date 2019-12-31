package com.search.to;
import java.util.Date;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private Date dob;
	private char registered;
	private String password;
	
	public Customer() {
		
	}
	
	@Override
	public String toString() {
		return "Customer name : " + this.firstName  + " " + this.lastName + " username : " + this.username + 
				" dob : " + this.dob + " is registered : " + this.registered;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public char getRegistered() {
		return registered;
	}

	public void setRegistered(char registered) {
		this.registered = registered;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
