package com.goodee.ex13.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.ex13.service.FreeBoardService;

@RestController
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/freeBoard/list")
	public String list(HttpServletRequest request, Model model) {
		freeBoardService.findFreeBoards(request, model);
		return "free/list";
	}

	@PostMapping("/freeBoard/saveFreeBoard")
	public void saveFreeBoard(HttpServletRequest request, HttpServletResponse response) {

		int res = freeBoardService.saveFreeBoard(request);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (res > 0) {
				out.println("<script>");
				out.println("alert('게시글이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/freeBoard/list'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 등록이 실패되었습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PostMapping("/freeBoard/saveReply")
	public void saveReply(HttpServletRequest request, HttpServletResponse response) {

		int res = freeBoardService.saveReply(request);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (res > 0) {
				out.println("<script>");
				out.println("alert('댓글이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/freeBoard/list'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('댓글 등록이 실패되었습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/freeBoard/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		long freeboardNo = Long.parseLong(request.getParameter("freeBoardNo"));
		int res = freeBoardService.removeFreeBoard(freeboardNo);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (res > 0) {
				out.println("<script>");
				out.println("alert('삭제 완료')");
				out.println("location.href='" + request.getContextPath() + "/freeBoard/list'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}