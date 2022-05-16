package com.goodee.ex05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")			//	${contextPath}/index 요청이 오면
	public String index() {
		return "index";			//	index.jsp 로 이동
	}

	@GetMapping("member")		//	${contextPath}/member 요청이 오면
	public String member() {
		return "member";		//	member.jsp 로 이동
	}

	@GetMapping("board")		//	${contextPath}/board 요청이 오면
	public String board() {
		return "board";			//	board.jsp 로 이동
	}
	
	@GetMapping("product")		//	${contextPath}/product 요청이 오면
	public String product() {
		return "product";		//	product.jsp 로 이동
	}
	
	@GetMapping("reservation")
	public String reservation() {
		return "reservation";
	}

}