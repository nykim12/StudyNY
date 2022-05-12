package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectController {

/*
	redirect 하는 방법

	redirect는 JSP로 이동X
	redirect는 다른 매핑으로 이동함

	redirect : '매핑'으로 이동
	forward	 : 'JSP'로 이동
*/

	@GetMapping("list1")
	public String list1(HttpServletRequest request) {

//		forward
//		return "list";	list.jsp로 forward 이동하기

//		redirect는 request 전달 X, request나 model에 저장해도 최종 목적지에 전달되지 않음
		request.setAttribute("page", request.getParameter("page"));

//		redirect
		return "redirect:/list2";	//	@GetMapping("/list2") 매핑으로 이동하라는 의미

	}

	@GetMapping("list2")
	public String list2() {
		return "list";				//	list.jsp로 forward함
	}

	@GetMapping("list3")
	public String list3(int page) {
		return "redirect:/list4?page=" + page;
	}

	@GetMapping("list4")
	public String list4(int page, Model model) {
		model.addAttribute("page", page);
		return "list";
	}

/*
	redirect할 때 데이터 전달하는 스프링의 방법

	1.	Model 대신 RedirectAttributes 인터페이스 사용
	2.	addAttibute() 대신 addFlashAttribute() 메소드 사용

*/

	@GetMapping("list5")
	public String list5(int page, RedirectAttributes redirectattributes) {	//	RedirectAttributes redirectattributes : redirect할 곳으로 데이터를 전송해줄 수 있음

//		※ addFlashAttribute() 사용 필수!
		redirectattributes.addFlashAttribute("page", page);
		return "redirect:/list6"; // page 파라미터를 안붙여도 전달됨

	}

	@GetMapping("list6")
	public String list6() {
		return "list";
	}

}