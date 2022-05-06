package com.goodee.ex01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {

//		GenericXmlApplicationContext 클래스
//		1. spring bean configuration file에 등록된 <bean>을 가져오는 스프링 클래스
//		2. AbstractApplicationContext 클래스의 자식 클래스

//		<bean>을 가지고 올 context(xml) 지정하기
//		src/main/resources 아래 xml 폴더에 저장된 context01.xml을 의미한다.
		String resourceLocations = "classpath:xml/context01.xml";

		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);

//		<bean> 가지고 오기
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

		System.out.println();

//		<bean> 가져오기
		EngineerCalculator eCalc2 = ctx.getBean("eCalculator2", EngineerCalculator.class);

		eCalc2.add();
		eCalc2.sub();
		eCalc2.mul();
		eCalc2.div();
		eCalc2.mod();

		System.out.println();
		System.out.println();

		EngineerCalculator eCalc3 = ctx.getBean("eCalculator3", EngineerCalculator.class);

		eCalc3.add();
		eCalc3.sub();
		eCalc3.mul();
		eCalc3.div();
		eCalc3.mod();

		EngineerCalculator eCalc4 = ctx.getBean("eCalculator4", EngineerCalculator.class);

		eCalc4.add();
		eCalc4.sub();
		eCalc4.mul();
		eCalc4.div();
		eCalc4.mod();


//		스프링은 <bean> 태그를 보고 객체를 만들 때 기본적으로(디폴트) "singleton"으로 만듦
		Calculator c1 = ctx.getBean("calculator1", Calculator.class);
		Calculator c2 = ctx.getBean("calculator1", Calculator.class);

		System.out.println(c1 == c2);	//	true (싱글톤)

//		<bean>의 scope를 변경하려면 <bean> 태그에 scope 속성 추가

		Calculator c3 = ctx.getBean("calculator2", Calculator.class);
		Calculator c4 = ctx.getBean("calculator2", Calculator.class);

		System.out.println(c3 == c4);	//	false (프로토타입)

		ctx.close();

	}

}