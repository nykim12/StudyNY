package com.goodee.ex15.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/member/loginPage")
	public String loginPage(@RequestParam(required = false) String url, Model model) {
		model.addAttribute("url", url);
		return "member/login";
	}

//	login() 메소드 수행 이전 LoginInterceptor의 preHandle() 메소드가 호출
	@PostMapping("/member/login")
	public void login(HttpServletRequest request, Model model) {

		MemberDTO loginMember = memberService.login(request);

		if(loginMember != null) {
//			Model에 저장된 속성은 LoginInterceptor의 postHandle()메소드의 modelAndView 매개변수가 받음
			model.addAttribute("loginMember", loginMember);
		}
		model.addAttribute("url", request.getParameter("url"));

//		로그인 유지를 체크한 경우
//		1) check 상태	: 파라미터(keepLogin) = "keep"
//		2) uncheck 상태	: 파라미터(keepLogin) = NULL	
		String keepLogin = request.getParameter("keepLogin");
		if(keepLogin != null && keepLogin.equals("keep")) {
			memberService.keepLogin(request);
		}
	}

//	login() 메소드 수행 이후 LoginInterceptor의 postHandle() 메소드가 호출

//	LoginInterceptor의 preHandle() 메소드에서 탈퇴회원 조회 후 탈퇴회원의 경우 처리
	@PostMapping("/member/reSignInPage")
	public String reSignInPage() {
		return "member/reSignIn";
	}

	@PostMapping("/member/reSignIn")
	public void reSIgnIn(HttpServletRequest request, HttpServletResponse response) {
		memberService.reSignIn(request, response);
	}

	@GetMapping("/member/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
		if(loginMember != null) {
			session.invalidate();
		}
		
		Cookie cookie = new Cookie("keepLogin", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";	// contextPath 이동

	}

	@GetMapping("/member/agreePage")
	public String agreePage() {
		return "member/agree";
	}

	@GetMapping("/member/signInPage")
	public String signInPage(@RequestParam(required = false) String[] agreements, Model model) {
		model.addAttribute("agreements", agreements);
		return "member/signIn";
	}

	@ResponseBody
	@GetMapping(value = "/member/idCheck", produces = "application/json")
	public Map<String, Object> idCheck(@RequestParam String id) {
		return memberService.idCheck(id);
	}

	@ResponseBody
	@GetMapping(value = "/member/emailCheck", produces = "application/json")
	public Map<String, Object> emailCheck(@RequestParam String email) {
		return memberService.emailCheck(email);
	}

	@ResponseBody
	@GetMapping(value = "/member/sendAuthCode", produces = "application/json")
	public Map<String, Object> sendAuthCode(@RequestParam String email) {
		return memberService.sendAuthCode(email);
	}

	@PostMapping("/member/signIn")
	public void signIn(HttpServletRequest request, HttpServletResponse response) {
		memberService.signIn(request, response);
	}

	@GetMapping("/member/signOut")
	public void signOut(HttpServletRequest request, HttpServletResponse response) {
		memberService.signOut(request, response);
	}

	@GetMapping("/board/savePage")
	public String savePage() {
		return "board/save";
	}

}