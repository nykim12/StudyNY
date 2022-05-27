package com.goodee.ex12.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goodee.ex12.mapper.BoardMapper;

@Component
public class BoardJob {

	@Autowired
	private BoardMapper boardMapper;

	@Scheduled(cron = "*/10 * * * * *")
	public void execute() {
		System.out.println("--쿼츠 동작 중--");
		System.out.println(boardMapper.selectBoardCount());
	}

}