package com.goodee.ex01.xml06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml/context06.xml");
		GymMember member = ctx.getBean("gymMember", GymMember.class);
		member.info();

		ctx.close();

	}

}