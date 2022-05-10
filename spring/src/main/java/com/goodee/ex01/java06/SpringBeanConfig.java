package com.goodee.ex01.java06;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//	java/customer_context.xml 에 있는 bean 가져오기
@ImportResource("java/customer_context.xml")


@Configuration
public class SpringBeanConfig {

	@Bean
	public BankAccount bank1() {
		BankAccount bank = new BankAccount();
		bank.setAccNo("010-1111-2222");
		bank.setBalance(5000L);
		return bank;
	}

	@Bean
	public Customer customer1() {
		Customer customer = new Customer();
		customer.setName("김나연");
		Map<String, String> info = new HashMap<String, String>();
		info.put("tel", "010-1111-2222");
		info.put("grade", "vip");
		customer.setInfo(info);
		customer.setBankaccount(bank1());
		return customer;

	}

}