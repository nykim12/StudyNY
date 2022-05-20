package com.goodee.ex09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex09.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping(value = { "/", "/notice/list" })
	public String list(Model model) {
		model.addAttribute("notices", noticeService.findNotices());
		return "notice/list";
	}

}