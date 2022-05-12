package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.ex04.domain.Board;

/*
ModelAndView 클래스

1.	스프링2 이전에 주로 사용된 클래스
2.	Model을 통해서 전달할 값을 저장하고, View를 통해 이동할 JSP 결정
3.	ModelAndView와 Model 비교

	1)	ModelAndView
		public ModelAndView 메소드() {
			ModelAndView mav = new ModelAndView();
			mav.addObject("속성", 전달할 값);
			mav.setViewName("JSP 이름");
			return mav;
		}

	2)	Model
		public String 메소드(Model model) {
			model.addAttribute("속성", 전달할 값);
			return "JSP 이름";
		}
*/

@Controller
public class RequestController2 {

	@GetMapping("/add1")
	public ModelAndView mav(HttpServletRequest request) {

		String title = request.getParameter("title");
		long hit = Long.parseLong(request.getParameter("hit"));

		Board board = Board.builder()
				.title(title)
				.hit(hit)
				.build();

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);	//	Model에 속성 board 저장
		mav.setViewName("add");			//	add.jsp 로 이동

		return mav;						//	forward 하기 때문에 add.jsp로 board를 전달한 상황
	}

	@GetMapping("/add2")	//	<a href="${contextPath}/add2?title=공지사항&hit=10">
	public ModelAndView add2(String title, Long hit) {	//	@RequestParam 애너테이션 생략

		Board board = Board.builder()
				.title(title)
				.hit(hit)
				.build();

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("add");

		return mav;

	}
	
	@GetMapping("/add3")
	public ModelAndView add3(Board board) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("add");

		return mav;
	}
}