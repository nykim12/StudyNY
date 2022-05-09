package com.goodee.ex01.java04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	@Bean
	public Publisher publisher2() {
		Publisher publisher = new Publisher();
		publisher.setName("시나공");
		publisher.setTel("02-111-1111");
		return publisher;
	}

	@Bean
	public Book book2() {
		Book book = new Book();
		book.setTitle("소나기");
		book.setAuthor("황순원");
		book.setPublisher(publisher2());
		return book;
	}

}