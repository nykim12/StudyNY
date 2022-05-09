package com.goodee.ex01.xml05;

import java.util.Map;

public class Student {

	private String name;
	private Exam exam;
	private Map<String, String> pInfo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Map<String, String> getPInfo() {
		return pInfo;
	}

	public void setPInfo(Map<String, String> pInfo) {
		this.pInfo = pInfo;
	}

}