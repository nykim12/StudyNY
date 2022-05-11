package com.goodee.ex03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex03.domain.Member;

@Configuration
public class MemberConfig {

	@Bean
	public Member member() {

		Member member = new Member();
		member.setId("admin");
		member.setPw("123456");
		return member;

	}
}