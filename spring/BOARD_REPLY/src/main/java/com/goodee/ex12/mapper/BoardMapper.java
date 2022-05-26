package com.goodee.ex12.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex12.domain.Board;

@Mapper
public interface BoardMapper {

	public List<Board> selectBoardList(Map<String, Object> map);

	public int selectBoardCount();

	public Board selectBoardByNo(Long boardNo);

	public int updateBoardHit(Long boardNo);

	public int insertBoard(Board board);

	public int updateBoard(Board board);

	public int deleteBoard(Long boardNo);

}