package com.goodee.ex06.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodee.ex06.domain.BoardDTO;

//	Service의 메소드 명칭 : 사용자 친화적으로 작성
//	select/insert/update/delete와 같은 명칭 사용 X

public interface BoardService {

	public List<BoardDTO> findBoards();

	public BoardDTO findBoardByNo(long board_no);

	public void save(BoardDTO board, HttpServletRequest request, HttpServletResponse response);

	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response);

	public void remove(long board_no, HttpServletRequest request, HttpServletResponse response);

}