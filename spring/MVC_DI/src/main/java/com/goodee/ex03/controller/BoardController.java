package com.goodee.ex03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

/*
	1.	필드 주입 (@Autowired)
		필드마다 @Autowired 추가해야 함 (필드가 10개이면 @Autowired도 10개 추가)
		필드가 많은 경우 사용하지 않는 것이 좋음
		
		@Autowired
		private Board board1;

	2.	생성자 주입
		생성자의 매개변수로 주입된다.
		@Autowired를 사용하지 않아도 됨
		
		private Board board1;
		
		public BoardController(Board board2) {
			super();
			this.board1 = board2;
		}

	3.	setter 주입
		setter의 매개변수로 주입된다.
		@Autowired를 setter 앞에 작성
		@Autowired가 추가된 메소드의 매개변수는 모두 주입된다. (꼭 setter가 아니어도 됨)
*/

//	GetMapping("board/detail") 또는 GetMapping("/board/detail")
	@GetMapping("board/detail")
	public String board1(HttpServletRequest request) {
		request.setAttribute("board1", board1);
		request.setAttribute("board2", board2);

		return "board/detail";
	}

/*
	DI(Dependency Injection) : 의존성 주입

	1.	스프링 컨테이너에 있는 bean을 스프링이 주입하는 방법
	2.	방법
		1)	필드에 직접 주입
		2)	생성자를 이용하여 주입
		3)	setter를 이용하여 주입
	3.	@Inject, @Resource, @Autowired 등의 애너테이션을 이용하는 데
		그 중 @Autowired가 가장 널리 사용

	@Autowired

	1.	bean의 타입(class)이 일치하는 bean을 찾아서 주입하는 방식
		(주의. bean의 이름(id)를 찾는 방식이 아님)
	2.	필드, 생성자, setter에서 사용하는 데 상황에 따라 생략 가능
*/

/*
	동일한 타입(class)의 bean이 여러 개인 경우 @Autowired만으로는 bean이 주입이 되지 않기 때문에
	@Qualifier 애너테이션으로 bean을 구분한다.
*/
	@Autowired
	@Qualifier(value="board1")	//	<qualifier value="board1" />
	private Board board1;
	
	@Autowired
	@Qualifier(value="board2")
	private Board board2;
}