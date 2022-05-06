package com.goodee.ex01.xml01;

public class EngineerCalculator {

	private int a;
	private int b;
	private Calculator calculator;

	public void add() {
		calculator.add(a, b);
	}

	public void sub() {
		calculator.sub(a, b);
	}

	public void mul() {
		calculator.mul(a, b);
	}

	public void div() {
		calculator.div(a, b);
	}

	public void mod() {
		calculator.mod(a, b);
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

}