package com.goodee.ex09.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

	private long noticeNo;
	private String title;
	private String content;
	private int hit;
	private Timestamp created;
	private Timestamp lastModified;

}