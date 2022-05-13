package com.goodee.ex05.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goodee.ex05.domain.MemberDTO;

//	기능별로 메소드 사용

public interface MemberService {

	public String detail1(HttpServletRequest request);

	public MemberDTO detail2(String id, String pw);
	
	public Map<String, Object> detail3(MemberDTO member);
	
	public Map<String, Object> detail4(MemberDTO member);

	public MemberDTO detail5(Map<String, Object> map);
}