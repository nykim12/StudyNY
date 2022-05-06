package com.goodee.ex01.xml02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {

		String resourceLocations = "classpath:xml/context02.xml";

		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);

		Car car = ctx.getBean("bmw x7", Car.class);

		car.info();

		ctx.close();
	}

}