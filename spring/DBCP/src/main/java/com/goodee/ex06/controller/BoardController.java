package com.goodee.ex06.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.service.BoardService;

@Controller
public class BoardController {

//	컨트롤러에서 HttpServeletRequest, HttpServeletResponse, HttpSession 선언 가능
	
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
	
	@GetMapping("board/addPage")
	public String addPage() {
		return "board/add";
	}
	
	@PostMapping("board/add")
	public void add(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		logger.info("add : " + board);
		boardService.save(board, request, response);
//		삽입 후에는 redirect를 해야 하는데, redirect 코드가 없다는 것은 boardServic의 save() 메소드에서 직접 이동했다는 것을 의미
//		응답을 만드는 response를 save() 메소드에 넘겨주면 save() 메소드에서 직접 이동 가능
	}
	
	@GetMapping("/board/remove")
	public void remove(@RequestParam(value = "board_no", required = false, defaultValue = "0") long board_no,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("remove(): " + board_no);
		boardService.remove(board_no, request, response);
//		save() 메소드와 같이 remove() 메소드에서 직접 이동
	}
	
	@PostMapping("/board/modifyPage")
	public String modifyPage(@ModelAttribute(value = "board") BoardDTO board) {
		logger.info("modifyPage(): " + board);
		return "board/modify";
	}
	
	@PostMapping("board/modify")
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		logger.info("modify() : " + board);
		boardService.modify(board, request, response);
//		save(), remove() 메소드와 같이 modify() 메소드에서 직접 이동
	}

}