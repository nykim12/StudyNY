package com.goodee.ex16.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDTO {

	private long memberNo;
	private String id;
	private String name;
	private String gender;
	private String address;

}