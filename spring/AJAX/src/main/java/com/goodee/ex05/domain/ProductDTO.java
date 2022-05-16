package com.goodee.ex05.domain;

public class ProductDTO {

	private long no;
	private String name;
	private String maker;
	private int price;

	public ProductDTO(long no, String name, String maker, int price) {
		super();
		this.no = no;
		this.name = name;
		this.maker = maker;
		this.price = price;
	}

	public ProductDTO() {
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

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}