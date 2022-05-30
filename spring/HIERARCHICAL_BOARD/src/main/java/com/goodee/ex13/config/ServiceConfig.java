package com.goodee.ex13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex13.service.FreeBoardServiceImpl;
import com.goodee.ex13.service.FreeBoardService;

@Configuration
public class ServiceConfig {

	@Bean
	public FreeBoardService freeBoardService() {
		return new FreeBoardServiceImpl();
	}

}