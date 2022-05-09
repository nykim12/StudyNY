package com.goodee.ex01.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	@Bean
	public Student stu() {

		Student stu = new Student();

		List<Integer> scores = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			scores.add((int) (Math.random() * 101));
		}

		Set<String> awards = new HashSet<String>();
		awards.add("플래티넘");
		awards.add("골드");
		awards.add("실버");

		Map<String, String> home = new HashMap<String, String>();
		home.put("address", "seoul");
		home.put("phone", "010-1111-2222");

		stu.setScores(scores);
		stu.setAwards(awards);
		stu.setHome(home);

		return stu;
	}
}