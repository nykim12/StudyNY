package com.goodee.ex13.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex13.domain.FreeBoard;

@Mapper
public interface FreeBoardMapper {

	public int selectFreeBoardCount();
	public List<FreeBoard> selectFreeBoardList(Map<String, Object> map);

	public int insertFreeBoard(FreeBoard freeboard);

	public int updatePreviousReply(FreeBoard freeboard);
	public int insertReply(FreeBoard freeboard);

	public int deleteFreeBoard(long freeBoardNo);

}