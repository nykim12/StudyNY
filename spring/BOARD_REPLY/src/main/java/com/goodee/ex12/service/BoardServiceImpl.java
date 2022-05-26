package com.goodee.ex12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.goodee.ex12.domain.Board;
import com.goodee.ex12.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public void findBoards(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findBoardByNo(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public int save(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int change(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Long boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}