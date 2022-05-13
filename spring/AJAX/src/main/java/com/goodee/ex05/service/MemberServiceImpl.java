package com.goodee.ex05.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goodee.ex05.domain.MemberDTO;

public class MemberServiceImpl implements MemberService {

	@Override
	public String detail1(HttpServletRequest request) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		return "입력 ID : " + id + ", 입력 비밀번호 : " + pw; // Controller에 반환할 텍스트
	}

	@Override
	public MemberDTO detail2(String id, String pw) { // 컨트롤러에서 받아온 id와 pw

		MemberDTO member = new MemberDTO();

		member.setId(id);
		member.setPw(pw);

		return member; // 컨트롤러에서 받아온 member
	}

	@Override
	public Map<String, Object> detail3(MemberDTO member) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", member.getId());
		map.put("pw", member.getPw());

		return map;
	}

	@Override
	public Map<String, Object> detail4(MemberDTO member) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", member.getId());
		map.put("pw", member.getPw());

		return map;
	}

	@Override
	public MemberDTO detail5(Map<String, Object> map) {

		MemberDTO member = new MemberDTO();

		member.setId(map.get("id").toString());
		member.setPw((String)map.get("pw"));

		return member;

	}

}