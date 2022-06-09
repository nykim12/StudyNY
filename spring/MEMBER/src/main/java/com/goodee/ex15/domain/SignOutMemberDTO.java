package com.goodee.ex15.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignOutMemberDTO {

	private long signOutNo;
	private long memberNo;
	private String id;
	private String name;
	private String email;
	private int agreeState;
	private Date signOut;

}