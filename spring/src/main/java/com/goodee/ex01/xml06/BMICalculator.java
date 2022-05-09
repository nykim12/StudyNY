package com.goodee.ex01.xml06;

public class BMICalculator {

	private Calculator calculator;
	private double height;
	private double weight;
	private double bmi; // BMI : 몸무게 / (키 * 키)
	private String health;
	// 건강상태(BMI < 20 : 저체중 , 20 <= BMI < 25 : 정상체중 , 25 <= BMI < 30 : 과체중 , 30 <=BMI : 비만)

//	height, weight 정보를 이용하여 BMI, health 값을 구해야 하므로 BMI, health 값은 받아오지 않음
	public BMICalculator(Calculator calculator, double height, double weight) {

		this.calculator = calculator;
		this.height = height;
		this.weight = weight;

		height = calculator.div(height, 100);
		this.bmi = this.calculator.div(weight, this.calculator.mul(height, height));

		if (this.bmi < 20) {
			this.health = "저체중";
		} else if (bmi < 25) {
			this.health = "정상체중";
		} else if (bmi < 30) {
			this.health = "과체중";
		} else {
			this.health = "비만";
		}
	}

	public void info() {
		System.out.println("height : " + height + "cm");
		System.out.println("weight : " + weight + "kg");
		System.out.println("bmi : " + bmi);
		System.out.println("health : " + health);
	}

}