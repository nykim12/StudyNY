package com.goodee.ex10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex10.service.NoticeSeriviceImpl;
import com.goodee.ex10.service.NoticeService;

@Configuration
public class NoticeConfig {

	@Bean
	public NoticeService noticeService() {
		return new NoticeSeriviceImpl();
	}

}