package com.goodee.ex12.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex12.domain.Reply;
import com.goodee.ex12.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public Map<String, Object> findReplies(long boardNo) {

		Map<String, Object> map = new HashMap<>();

//		int replyCount = replyMapper.selectReplyCount(boardNo);
//		map.put("replyCount", replyCount);
		map.put("replyCount", replyMapper.selectReplyCount(boardNo));

//		List<Reply> replies = replyMapper.selectReplyList(boardNo);
//		map.put("replies", replies);
		map.put("replies", replyMapper.selectReplyList(boardNo));

		return map;
	}

	@Override
	public Map<String, Object> saveReply(HttpServletRequest request) {

		Reply reply = Reply.builder()
				.writer(request.getParameter("writer"))
				.content(request.getParameter("content"))
				.ip(request.getRemoteAddr())
				.boardNo(Long.parseLong(request.getParameter("boardNo")))
				.build();

		Map<String, Object> map = new HashMap<>();
		map.put("res", replyMapper.insertReply(reply));
		return map;

	}

	@Override
	public Map<String, Object> removeReply(long replyNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", replyMapper.deleteReply(replyNo));
		return map;
	}
}