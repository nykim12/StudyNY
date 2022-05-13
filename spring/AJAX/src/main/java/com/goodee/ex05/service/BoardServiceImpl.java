package com.goodee.ex05.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.goodee.ex05.domain.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Override
	public BoardDTO detail1(HttpServletRequest request) {
		String title = request.getParameter("title");
		Long hit = Long.parseLong(request.getParameter("hit"));
		return new BoardDTO(title, hit);
	}

	@Override
	public BoardDTO detail2(String title, Long hit) {

		BoardDTO board = new BoardDTO();

		board.setTitle(title);
		board.setHit(hit);

		return board;

	}

	@Override
	public Map<String, Object> detail3(BoardDTO board) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", board.getTitle());
		map.put("hit", board.getHit());
		return map;
	}

	@Override
	public BoardDTO detail4(Map<String, Object> map) {

		BoardDTO board = new BoardDTO();

		board.setTitle(map.get("title").toString());
		board.setHit(Long.parseLong((String)map.get("hit")));

		return board;
	}

}