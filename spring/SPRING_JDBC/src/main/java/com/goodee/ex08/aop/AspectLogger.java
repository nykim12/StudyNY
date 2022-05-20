package com.goodee.ex08.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // bean
@Aspect
public class AspectLogger {

	private final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

//		[실행 시점]
//		@Before			: 메소드 호출 전
//		@After			: 메소드 호출 후 (실행의 성공 여부 상관 없음)
//		@AfterReturning : 메소드 호출 후 (실행의 성공)
//		@AfterThrowing	: 메소드 호출 후 (예외 발생)
//		@Around			: 메소드 호출 전후

//	패키지 com.goodee.ex08.controller 아래에 있는 Controller로 이름이 끝나는 클래스 (*Controller)
//	모든 메소드(*(..))에서 실행한다는 의미
	@Around("execution(* com.goodee.ex08.controller.*Controller.*(..))")
	public Object log(ProceedingJoinPoint point) throws Throwable {

		String name = point.getSignature().getName();

		logger.info(name + " : my log");

		return point.proceed();

	}
}