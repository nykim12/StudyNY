package com.goodee.ex12.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.ex12.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/board/list")
	public String list(HttpServletRequest request, Model model) {
		boardService.findBoards(request, model);
		return "board/list";
	}

	@GetMapping("/board/savePage")
	public String savePage() {
		return "board/save";
	}

	@PostMapping("/board/save")
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("res", boardService.save(request));
		return "redirect:/board/result";
	}

	@GetMapping("/board/result")
	public String result() {
		return "/board/result";
	}

}