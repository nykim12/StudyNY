package com.goodee.ex14.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gallery {

	private long galleryNo;
	private String writer;
	private String title;
	private String content;
	private String ip;
	private int hit;
	private Date created;
	private Date modified;

}