package com.goodee.ex03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex03.domain.Member;

@Controller
public class MemberController {

	private Member member;
	
	public MemberController(Member member) {
		super();
		this.member = member;
	}

	@GetMapping("member/detail")
	public String member(HttpServletRequest request) {
		request.setAttribute("member", member);
		return "member/detail";
	}

}