package com.goodee.ex01.java03;

public class Gugudan {

	private Calculator calculator;
	private int begin;
	private int end;

	public Gugudan(Calculator calculator, int begin, int end) {
		super();
		this.calculator = calculator;
		this.begin = begin;
		this.end = end;
	}

	public void printGugudan() {

		for(int n = 1; n <= 9; n++) {
			for(int dan = begin; dan <= end; dan++) {
				System.out.print(dan + "*" + n + "=" + calculator.mul(dan, n) + "\t");
			}
			System.out.println();
		}
	}

}