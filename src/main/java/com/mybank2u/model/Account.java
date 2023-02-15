package com.mybank2u.model;

import javax.xml.bind.annotation.XmlElement;

public class Account {
	
	private String id;
	private String name = "";
	private String balance = "";
	private String creation_date = "";
	private String last_transaction_seq = "";
	private String email = "";
	private String online_pin = "";
	private String otp = "";
	private String opt_expire = "";
	
	public Account toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBalance() {
		return balance;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public String getLast_transaction_seq() {
		return last_transaction_seq;
	}

	public String getEmail() {
		return email;
	}

	public String getOnline_pin() {
		return online_pin;
	}

	public String getOtp() {
		return otp;
	}

	public String getOpt_expire() {
		return opt_expire;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public void setLast_transaction_seq(String last_transaction_seq) {
		this.last_transaction_seq = last_transaction_seq;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOnline_pin(String online_pin) {
		this.online_pin = online_pin;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public void setOpt_expire(String opt_expire) {
		this.opt_expire = opt_expire;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + ", creation_date=" + creation_date
				+ ", last_transaction_seq=" + last_transaction_seq + ", email=" + email + ", online_pin=" + online_pin
				+ ", otp=" + otp + ", opt_expire=" + opt_expire + "]";
	}

}
