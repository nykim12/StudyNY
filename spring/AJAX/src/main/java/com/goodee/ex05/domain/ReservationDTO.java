package com.goodee.ex05.domain;

public class ReservationDTO {

	private long no;
	private String name;

	public ReservationDTO(long no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public ReservationDTO() {
		super();
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}