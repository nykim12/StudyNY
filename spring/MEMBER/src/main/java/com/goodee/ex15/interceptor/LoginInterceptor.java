package com.goodee.ex15.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.ex15.domain.SignOutMemberDTO;
import com.goodee.ex15.service.MemberService;
import com.goodee.ex15.util.SecurityUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private MemberService memberService;

//	member/login 요청 이전에 처리
//	탈퇴회원 여부 확인
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		반환 타입이 true일 경우 @PostMapping("/member/login") 요청 수행
//		반환 타입이 false일 경우 @PostMapping("/member/login") 요청 수행X -> 작업 X

//		로그인된 정보가 있을 경우 기존 로그인 정보 제거
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}

//		탈퇴회원 여부 확인
		String id = SecurityUtils.xss(request.getParameter("id"));
		SignOutMemberDTO member = memberService.findSignOutMember(id);
		if (member != null) {
//			탈퇴한 회원의 정보를 가지고 재가입 페이지로 이동
			request.setAttribute("member", member);
			request.getRequestDispatcher("/member/reSignInPage").forward(request, response);
			return false;
		}
		return true;

	}

//	member/login 요청 이후에 처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//		ModelAndView를 Map으로 변환 후 loginMember 추출
		Map<String, Object> map = modelAndView.getModel();
		Object loginMember = map.get("loginMember");
		Object url = map.get("url");

//		loginMember가 있을 경우(로그인 성공) session 저장
		if (loginMember != null) {
//			session에 loginMember 저장
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
//			로그인 유지를 체크한 사용자는 "keepLogin"의 쿠키명으로 session_id 값을 저장해둔다.
			String keepLogin = request.getParameter("keepLogin");
			if(keepLogin != null && keepLogin.equals("keep")) {
//				keepLogin 쿠키 만들기
				Cookie cookie = new Cookie("keepLogin", session.getId());
				cookie.setMaxAge(60 * 60 * 24 * 7);
//				keepLogin 쿠키 저장하기
				response.addCookie(cookie);

//				로그인 유지를 체크하지 않은 사용자는 "keepLogin" 쿠키 제거
			} else {
//				keepLogin 쿠키 제거
				Cookie cookie = new Cookie("keepLogin", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

//			로그인 이전 화면 정보가 없을 경우
			if(url.toString().isEmpty()) {
				response.sendRedirect(request.getContextPath());
			} else {	// 로그인 이전 화면 정보가 있는 경우
				response.sendRedirect(url.toString());
			}
		} else {
			if(url.toString().isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/member/loginPage");
			} else {
				response.sendRedirect(request.getContextPath() + "/member/loginPage?url=" + url.toString());
			}
		}

	}

}