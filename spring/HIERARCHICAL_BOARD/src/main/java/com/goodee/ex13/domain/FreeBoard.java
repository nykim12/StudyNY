package com.goodee.ex13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeBoard {

	private long rowNum;
	private long freeBoardNo;
	private String writer;
	private String content;
	private String ip;
	private Date created;
	private int state;
	private int depth;
	private long groupNo;
	private int groupOrd;

}