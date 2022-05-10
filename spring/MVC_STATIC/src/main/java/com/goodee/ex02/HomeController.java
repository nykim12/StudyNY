package com.goodee.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
	@Controller
	클래스로 만드는 컨트롤러

*/
@Controller
public class HomeController {

//	JSP처럼 *.do와 같이 공통 URLMapping을 가질 필요 X

//	메소드 1개 = 요청 1개, 응답 1개

	/*
	@RequestMapping
	1.	URLMapping을 처리하는 애너테이션
	2.	메소드마다 하나씩 가져야 함
	3.	속성
		1) value	: URLMapping 작성
		2) method	: 요청 메소드 작성(GET, POST)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)

//	value = "/" : contextPath 매핑을 의미 (http://localhost:9090/ex02)
//	프로젝트를 실행하면 가장 먼저 처리되는 메소드
//	일반적으로 index.jsp를 열어줌 (welcome file 작업하기)

//	반환타입 : String	(열어줄 View 이름 = JSP 이름)
//	메소드명 : index	(아무 일 X)
//	매개변수 : 없음		(요청, 응답을 처리하는 request, response 선언)

	public String index(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("res", "hello");

		return "index";

//		return"index"는 survlet-context.xml에 정의된 viewResolver에 의해 아래와 같이 처리됨
//		return	"/WEB-INF/views/index.jsp"
//				prefix(/WEB-INF/views/) + "index" + suffix(.jsp)

//		index.jsp 는 forward로 이동

	}

//	@RequestMapping은 메소드를 먼저 만들고 작성하는 것이 좋다.

//	@RequestMapping(value = "imageView", method = RequestMethod.GET)
//	@RequestMapping(value = "/imageView", method = RequestMethod.GET)	//	매핑은 슬래시(/)로 시작해도 됨

//	@RequestMapping(value = "/imageView")	Get 방식은 안적어도 됨
//	@RequestMappinng("imageView")			value 속성 하나만 작성할 땐 속성 이름을 안적어도 됨

	@RequestMapping(value = "imageView", method = RequestMethod.GET)
	public String imageView() {

		return "gallery/detail";
//		return "/WEB-INF/views/gallery/detail.jsp";
	}

	@RequestMapping("lionView")
	public String lionView() {
		return "gallery/lion";
	}

}