package com.goodee.ex01.java03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	@Bean
	public Calculator cal() {
		return new Calculator();
	}

	@Bean
	public Gugudan dan() {
		return new Gugudan(cal(), 3, 5);
	}

}