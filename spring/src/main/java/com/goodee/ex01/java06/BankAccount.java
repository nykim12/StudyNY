package com.goodee.ex01.java06;

public class BankAccount {

	private String accNo;
	private Long balance;

	public BankAccount() { // default constructor + setter injection

	}

	public BankAccount(String accNo, Long balance) { // constructor injection
		super();
		this.accNo = accNo;
		this.balance = balance;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}