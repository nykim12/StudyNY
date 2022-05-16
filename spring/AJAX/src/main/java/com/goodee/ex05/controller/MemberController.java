package com.goodee.ex05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex05.domain.MemberDTO;
import com.goodee.ex05.service.MemberService;

@Controller
public class MemberController {

/*
	컨트롤러는 언제나 Service를 호출, 그래서 Service를 field로 등록함

	DI를 사용안하는 경우
	개발자가 직접 필요한 Bean을 생성하는 방법
	private MemberService memberService = new MemberServiceImpl();

	DI를 사용하는 경우
	root-context.xml에 등록한 Bean을 스프링이 가져오는 방법
	필드, 생성자, setter 방식 중 필드 주입 방식 사용해보기
 */

	@Autowired
	private MemberService memberService;

//	Controller의 메소드는 기본적으로 JSP 이름을 반환
//	AJAX는 JSP 이름을 반환하는 것이 아니라, 자신을 호출한 JSP로 값을 반환하는 구조

//	값을 반환하기 위해서는 @ResponseBody 애너테이션 필요

	@GetMapping(value = "member/detail1",
			produces="text/plain; charset=UTF-8")	//	produces가 반환하는 것은 텍스트 (응답 타입 response.setContentType)
	@ResponseBody	//	ResponseBody가 반환하는 것은 JSP 이름이 아니라, 값(텍스트, XML, JSON 등)
	public String detail1(HttpServletRequest request) { // 파라미터 id, pw를 request로 받는다.
		String res = memberService.detail1(request);
		return res;	//	memberService의 detail1() 메소드에서 만든 텍스트를 member.jsp로 반환
	}
	
	@GetMapping(value="/member/detail2",
			produces="application/json; charset=UTF-8")
	@ResponseBody
	
//	반환타입 MemberDTO는 jackson에 의해 JSON 데이터로 자동 변환
	public MemberDTO detail2(@RequestParam(value="id") String id,	//	파라미터 id는 String id에 저장
			@RequestParam(value="pw") String pw){					//	파라미터 pw는 String pw에 저장

		MemberDTO member = memberService.detail2(id, pw);

//		Jackson이 하는 일
//		자바 객체 member -> JSON 형태 {"id" : 아이디, "pw" : 비밀번호} 로 만들어줌

			return member;	//	자바 객체 반환하는 데, 이 때 jackson이 개입해서 member를 {"id" : 아이디, "pw" : 비밀번호}
							//	JSON으로 바꿔 보내라고 요청해야 함 -> produces에서 처리
//			실제 return은 return {"id": 아이디, "pw": 비밀번호};
	}

	@GetMapping(value="member/detail3",
			produces="application/json; charset=UTF-8")
	@ResponseBody
//	반환 타입 Map은 jackson에 의해 JSON 데이터로 자동 변환
	public Map<String, Object> detail3(MemberDTO member) {

		Map<String, Object> res = memberService.detail3(member);
		return res;

//		Map을 반환하고 있지만 produces에서 반환타입이 JSON이라고 했기 때문에
//		jackson이 개입해서 Map을 JSON 데이터로 바꿔줌
	}

	@PostMapping(value="member/detail4",
			produces="application/json; charset=UTF-8")
/*
	JSON 데이터가 요청의 본문에 포함된 상태로 컨트롤러로 전달됨 → 컨트롤러는 해당 데이터를 파라미터로 처리 불가
	요청의 본문을 @RequestBody 애너테이션을 이용하면 요청의 본문에 포함된 데이터를 받을 수 있음
	jackson을 사용하고 있기 때문에 컨트롤러로 전달된 JSON 데이터는 MemberDTO 또는 Map으로 받으면 됨
*/
	@ResponseBody
	public Map<String, Object> detail4(@RequestBody MemberDTO member) {

		Map<String, Object> map = memberService.detail4(member);

		return map;

	}
	
	@PostMapping(value="member/detail5",
			produces="application/json; charset=UTF-8")
	@ResponseBody
	public MemberDTO detail5(@RequestBody Map<String, Object> map) {
		MemberDTO member = memberService.detail5(map);
		return member;
	}
	
}