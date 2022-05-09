package com.goodee.ex01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@Configuration	: bean을 만드는 java class 파일 (Spring Bean Configuration File 과 동일)

*/

@Configuration
public class SpringBeanConfig {

//	메소드 1개 = bean 1개

//	bean을 만드는 메소드는 모두 @Bean 필요
//	1.	반환타입 : Song	<bean class="com.goodee.ex01.java01.Song">
//	2.	메소드명 : mySon	<bean id="mySong">
	@Bean
	public Song mySong() {
		Song song = new Song();
		song.setTitle("hello"); // setter injection <property name="title" value="hello" />
		song.setGenre("balad"); // setter injection <property name="genre" value="balad" />
		return song;
	}

	@Bean
	public Singer mySinger() {
		return new Singer("adele", mySong());
	}

}