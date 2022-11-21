package com.taxibooking.model;

public class Customer {
	private int id;
	private String userName;
	private String emailId;
	private String password;
	private String mode;
	private String lastAccessedTime;
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Customer(int id, String userName, String emailId, String password,String mode,String lastAccessedTime) {
		this.id = id;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.mode = mode;
		this.lastAccessedTime = lastAccessedTime;
	}
	
	public String getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(String lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public Customer(String userName, String emailId, String password) {
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
	}
	

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userName=" + userName + ", emailId=" + emailId + ", password=" + password
				+ ", mode=" + mode + ", lastAccessedTime=" + lastAccessedTime + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
