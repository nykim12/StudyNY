package com.goodee.ex12.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex12.domain.Reply;

@Mapper
public interface ReplyMapper {

	public List<Reply> selectReplyList(long boardNo);

	public int selectReplyCount(long boardNo);

	public int insertReply(Reply reply);

	public int deleteReply(long replyNo);

}