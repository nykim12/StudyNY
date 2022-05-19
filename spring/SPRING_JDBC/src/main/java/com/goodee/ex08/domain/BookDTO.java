package com.goodee.ex08.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDTO {

	private long book_no;
	private String title;
	private String author;
	private int price;
	private String pubdate;
	private String regdate;

}