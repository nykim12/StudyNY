package com.goodee.ex01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {

		// GenericXmlApplicationContext 클래스
		// 1. spring bean configuration file에 등록된 <bean>을 가져오는 스프링 클래스
		// 2. AbstractApplicationContext 클래스의 자식 클래스

		// <bean>을 가지고 올 context(xml) 지정하기
		String resourceLocations = "classpath:xml/context01.xml"; // src/main/resources 아래 xml 폴더에 저장된 context01.xml을 의미한다.

		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);

		// <bean> 가지고 오기
		Calculator calc1 = ctx.getBean("calculator1", Calculator.class);
		calc1.add(1, 1);
		calc1.sub(2, 1);
		calc1.add(3, 2);
		calc1.div(5, 2);
		calc1.mod(7, 3);
		
		System.out.println();

//		<bean> 가져오기
		EngineerCalculator eCalc1 = ctx.getBean("eCalculator1", EngineerCalculator.class);
		eCalc1.add();
		eCalc1.sub();
		eCalc1.mul();
		eCalc1.div();
		eCalc1.mod();

	}

}