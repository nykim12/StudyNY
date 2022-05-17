package com.goodee.ex06.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.service.BoardService;

@Controller
public class BoardController {

//	logger
//	System.out.println() 대체
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

//	BoardService boardService : DI (BoardConfig.java에 저장된 bean 가져오기)
//	1. 필드 주입		: @Autowired private BoardService boardService;
//	2. 생성자 주입		: BoardController에 @AllArgsConstructor 추가
//	3. setter 주입	: setter 코드를 추가한 후 @Autowired 추가

	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("board/list")
	public String index(Model model) {
		List<BoardDTO> boards = boardService.findBoards();
		logger.info("list(): " + boards);
		model.addAttribute("boards", boards);
		return "board/list";
	}
	
	@GetMapping("board/detail")
	public String detail(@RequestParam(value="board_no") long board_no, Model model) {
		BoardDTO board = boardService.findBoardByNo(board_no);
		model.addAttribute("board", board);
		logger.info("board : " + board);
		return "board/detail";
	}

}