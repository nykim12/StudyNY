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

import com.goodee.ex05.domain.BoardDTO;
import com.goodee.ex05.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("board")
	public String board() {
		return "board";
	}

	@GetMapping(value = "board/detail1", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public BoardDTO detail1(HttpServletRequest request) {
		return boardService.detail1(request);
	}

	@GetMapping(value = "board/detail2", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public BoardDTO detail2(@RequestParam(value="title") String title,
			@RequestParam(value="hit") long hit) {
		
		BoardDTO board = boardService.detail2(title, hit);
		return board;
	}

	@PostMapping(value = "board/detail3",
			produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> detail3(@RequestBody BoardDTO board) {
		Map<String, Object> map = boardService.detail3(board);
		return map;
	}

	@PostMapping(value = "board/detail4", produces="application/json; charset=UTF-8")
	@ResponseBody
	public BoardDTO detail4(@RequestBody Map<String, Object> map) {
		BoardDTO board = boardService.detail4(map);
		return board;
	}

}