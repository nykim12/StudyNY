package com.goodee.ex15.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class RequiredLoginInterceptor implements HandlerInterceptor {

//	게시글 작성은 로그인 사용자만 가능

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getSession().getAttribute("loginMember") == null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("if(confirm('로그인이 필요한 서비스입니다. 로그인 페이지로 이동하겠습니까')){");
			out.println("location.href='" + request.getContextPath() + "/member/loginPage?url=" + request.getRequestURL() + "'");
			out.println("}else{");
			out.println("history.back()");
			out.println("}");
			out.println("</script>");
			out.close();
			return false;
		}

		return true;
	}

}