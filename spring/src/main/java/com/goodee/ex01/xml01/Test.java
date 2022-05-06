package com.goodee.ex01.xml01;

class Parents {

	Parents() {
		System.out.println("super");
	}

}

class Child extends Parents {

	Child() {
		System.out.println("sub");
	}

}

public class Test {

	public static void main(String[] args) {

		Parents p = new Child();

	}

}