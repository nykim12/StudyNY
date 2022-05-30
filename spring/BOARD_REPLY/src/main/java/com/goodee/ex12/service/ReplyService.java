package com.goodee.ex12.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ReplyService {

	public Map<String, Object> findReplies(long boardNo);

	public Map<String, Object> saveReply(HttpServletRequest request);

	public Map<String, Object> removeReply(long replyNo);
}