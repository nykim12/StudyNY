package com.goodee.ex06;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goodee.ex06.config.BoardConfig;
import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.repository.BoardRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BoardConfig.class })
//	context.xml에 bean을 생성했을 경우 아래와 같이 작성
//	@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardTestCase {

//	단위 테스트의 메소드명은 "한글"로도 가능

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void 목록테스트() {
		List<BoardDTO> boards = boardRepository.selectBoards();
		assertEquals(0, boards.size());
	}

}