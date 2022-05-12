package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex04.domain.Member;

@Controller
public class ForwardController_Study {

	@GetMapping("/")		// http://localhost:9090/ex04 이 주소로 연결되면,
	public String index() {
		return "index";		// index.jsp 로 이동
	}

	@GetMapping("detail1")	// <a href="${contextPath}/detail1?name=김나연&age=30">
	public String detail(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		Member member = new Member(name, age);
		request.setAttribute("member", member);

		return "detail";	//	detail.jsp 로 데이터를 가지고 forward
	}
	
	@GetMapping("detail2")	//	<a href="${contextPath}/detail2?name=김나연&age=30">
	public String detail2(HttpServletRequest request, Model model) {

//		요청 : request 처리
//		forward할 데이터 : model 처리

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(name, age);
		
//		스프링에서 사용하는 데이터 전달용 Model
//		내부적으로는 request를 사용해서 전달
//		model을 사용함으로써 보안이 향상되며, 대부분 Model 사용
		model.addAttribute("member", member);	//	= request.setAttribute("member", member) 와 동일

		return "detail";
	}
	
	@GetMapping("detail3")	//	<a href="${contextPath}/detail3?name=김나연&age=30">
	public String detail3(@RequestParam(value="name", required = false) String name,	//	파라미터 name을 String name에 저장
			@RequestParam(value="age", required = false, defaultValue = "0") int age,
			Model model) {					//	파라미터 age를 int age에 저장

		System.out.println(name);
		System.out.println(age);

/*
	RequestParam 사용법

		value="파라미터"		: 전달되는 파라미터 이름
		required=false		: 꼭 필요한 파라미터가 아니다. 없어도 된다. (기본값은 required=true)
		defaultValue="기본값"	: 파라미터가 없으면 사용할 값
	
		@RequestParam 애너테이션을 지워도 잘 동작함
*/
		model.addAttribute("member", new Member(name, age));

		return "detail";

	}
	
	@GetMapping("detail4")					//	<a href="${contextPath}/detail4?name=김나연&age=30">
	public String detail4(Member member,	//	Member 클래스의 setter가 파라미터를 받아줌
			Model model) {
		
		model.addAttribute("memer", member);
		
		return "detail";
	}
	
	@GetMapping("detail5")
	public String detail5(@ModelAttribute(value="member") Member member) {
		
//		@ModelAttribute(value="member") Member member
		
//		파라미터 name과 age를 Member member에 저장하고,
//		Model에 Attribute으로 저장
		
		return "detail";
	}
}

