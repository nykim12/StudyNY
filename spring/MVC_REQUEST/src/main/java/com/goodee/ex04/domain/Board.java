package com.goodee.ex04.domain;

public class Board {

	private String title;
	private Long hit;

	public Board() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public Board(Builder builder) {
		this.title = builder.title;
		this.hit = builder.hit;
	}

	public static Builder builder() {
		return new Builder();
	}

//	Builder 클래스 (클랫 안에 클래스를 넣는 내부 클래스)
	public static class Builder {

//		내부 field : 입력받은 값을 Board의 field로 보냄
		private String title;
		private Long hit;

//		title() 메소드
		public Builder title(String title) {
			this.title = title;
			return this; // this는 Builder 클래스를 의미
//							return this; 는 메소드 체이닝(메소드 계속 부르기)이 필요할 때 사용
		}

//		hit() 메소드
		public Builder hit(long hit) {
			this.hit = hit;
			return this;
		}

//		build() 메소드
		public Board build() {
			return new Board(this); // this는 Builder이므로 public Board(Builder builder) { } 생성자 필요
		}

	}

}