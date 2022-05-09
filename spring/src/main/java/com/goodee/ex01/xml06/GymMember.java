package com.goodee.ex01.xml06;

import java.util.Set;

public class GymMember {

	private String name;
	private Set<String> course;
	private double height;
	private double weight;
	private BMICalculator bmicalculator;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getCourse() {
		return course;
	}

	public void setCourse(Set<String> course) {
		this.course = course;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public BMICalculator getBmicalculator() {
		return bmicalculator;
	}

	public void setBmicalculator(BMICalculator bmicalculator) {
		this.bmicalculator = bmicalculator;
	}

	public void info() {
		System.out.println("name : " + name);
		System.out.println("course : " + course.toString());
		bmicalculator.info();
	}

}