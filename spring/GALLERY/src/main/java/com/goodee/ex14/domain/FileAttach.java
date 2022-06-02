package com.goodee.ex14.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileAttach {

	private long fileAttachNo;
	private String path;
	private String origin;
	private String saved;
	private int downloadCnt;
//	private long galleryNo;
	private Gallery gallery;

}