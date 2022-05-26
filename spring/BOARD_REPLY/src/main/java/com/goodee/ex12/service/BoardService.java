package com.goodee.ex12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.goodee.ex12.domain.Board;

public interface BoardService {
	public void findBoards(HttpServletRequest request, Model model);

	public void findBoardByNo(HttpServletRequest request, Model model);

	public int save(HttpServletRequest request);

	public int change(Board board);

	public int remove(Long boardNo);

}