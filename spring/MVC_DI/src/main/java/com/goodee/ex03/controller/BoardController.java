package com.goodee.ex03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex03.domain.Board;

@Controller
public class BoardController {

//	Spring4부터 새로운 애너테이션이 지원

//	@GetMapping
//	@PostMapping

	@GetMapping("/")
	public String index() {
		return "index"; // 뷰 리졸버에 의해 "/WEB-INF/views/index.jsp"로 변환
	}

	private Board board1;

//	GetMapping("board/detail") 또는 GetMapping("/board/detail")
	@GetMapping("board/detail")
	public String board1(HttpServletRequest request) {
		request.setAttribute("board1", board1);
		return "board/detail";
	}

}