package com.goodee.ex08.config;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect // 공통 모듈 담당
@EnableAspectJAutoProxy // Aspect의 동작이 자동으로 진행됨

@Configuration
public class TransactionConfig {

	@Autowired
	private TransactionManager transactionManager;

	@Bean
	public TransactionInterceptor interceptor() {

//	인터셉터 : 정상적인 흐름을 가로채 실행되는 것
//	모든 자바 예외(Exception)가 발생하면 Rollback 수행

		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute attribute = new RuleBasedTransactionAttribute();
		attribute.setName("*");
		attribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		source.setTransactionAttribute(attribute);
		return new TransactionInterceptor(transactionManager, source);

	}

	@Bean
	public Advisor advisor() {

		AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
		pointCut.setExpression("execution(* com.goodee.ex08.service.*Impl.*(..))");
		return new DefaultPointcutAdvisor(pointCut, interceptor());
	}

}