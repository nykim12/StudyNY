package com.goodee.ex15.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class PreventLoginInterceptor implements HandlerInterceptor {

//	로그인을 했는 데 재로그인 또는 회원가입을 수행하려고 할 때 방지하는 작업

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getSession().getAttribute("loginMember") != null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 로그인이 되어있는 상태입니다.')");
			out.println("location.href='" + request.getContextPath() + "'");
			out.println("</script>");
			out.close();
			return false;
		}

		return true;

	}

}