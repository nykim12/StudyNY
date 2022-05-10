package com.goodee.ex01.java06;

import java.util.Map;

public class Customer {

	private String name; // 고객명
	private Map<String, String> info; // 고객정보
	private BankAccount bankaccount; // 계좌

	public Customer() { // default constructor + setter injection

	}

	public Customer(String name, Map<String, String> info, BankAccount bankaccount) {
		super();
		this.name = name;
		this.info = info;
		this.bankaccount = bankaccount;
	}

	public void info() {
		System.out.println("name : " + name);
		for (Map.Entry<String, String> entry : info.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("accNo : " + bankaccount.getAccNo());
		System.out.println("balance : " + bankaccount.getBalance());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getInfo() {
		return info;
	}

	public void setInfo(Map<String, String> info) {
		this.info = info;
	}

	public BankAccount getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(BankAccount bankaccount) {
		this.bankaccount = bankaccount;
	}

}