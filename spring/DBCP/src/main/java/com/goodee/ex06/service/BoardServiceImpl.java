package com.goodee.ex06.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {

//	서비스는 사용자의 요청을 데이터베이스로 전달
//	데이터베이스 처리 결과를 사용자에게 응답

//	서비스는 BoardConfig.java에서 BoardRepository bean을 가져와야 함(DI)

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public List<BoardDTO> findBoards() {

		return boardRepository.selectBoards();

	}

	@Override
	public BoardDTO findBoardByNo(long board_no) {

		return boardRepository.selectBoardByNo(board_no);

	}

	@Override
	public void save(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {

		int res = boardRepository.insertBoard(board);
		if (res > 0) {
			
		} else {

		}

	}

	@Override
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {

		int res = boardRepository.updateBoard(board);
		if (res > 0) {

		} else {

		}

	}

	@Override
	public void remove(long board_no, HttpServletRequest request, HttpServletResponse response) {

		int res = boardRepository.deleteBoard(board_no);
		if (res > 0) {

		} else {

		}

	}

}