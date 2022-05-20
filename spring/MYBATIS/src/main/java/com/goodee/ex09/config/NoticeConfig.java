package com.goodee.ex09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex09.repository.NoticeRepository;
import com.goodee.ex09.service.NoticeSeriviceImpl;
import com.goodee.ex09.service.NoticeService;

@Configuration
public class NoticeConfig {

	@Bean
	public NoticeService noticeService() {
		return new NoticeSeriviceImpl();
	}
	
	@Bean
	public NoticeRepository noticeRepository() {
		return new NoticeRepository();
	}

}