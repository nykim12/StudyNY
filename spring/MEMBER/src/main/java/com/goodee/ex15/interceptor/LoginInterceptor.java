package com.goodee.ex15.interceptor;

import java.util.Map;

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

//		loginMember가 있을 경우(로그인 성공) session 저장
		if (loginMember != null) {
			request.getSession().setAttribute("loginMember", loginMember);
		} else {
			response.sendRedirect(request.getContextPath() + "/member/loginPage");
		}
	}

}