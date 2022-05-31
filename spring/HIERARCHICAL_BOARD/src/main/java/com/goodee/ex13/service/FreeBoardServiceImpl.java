package com.goodee.ex13.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.goodee.ex13.domain.FreeBoard;
import com.goodee.ex13.mapper.FreeBoardMapper;
import com.goodee.ex13.util.PageUtils;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardMapper freeBoardMapper;

	@Override
	public void findFreeBoards(HttpServletRequest request, Model model) {

//		totalRecord(DB), page(Parameter)
		int totalRecord = freeBoardMapper.selectFreeBoardCount();
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));

//		PageEntity 계산
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);

//		Map
		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());

//		목록 가져오기
		List<FreeBoard> freeBoards = freeBoardMapper.selectFreeBoardList(map);

//		free/list로 전달할 데이터
		model.addAttribute("freeBoards", freeBoards);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/freeBoard/list"));

	}

	@Override
	public int saveFreeBoard(HttpServletRequest request) {

		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());

		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setWriter(writer);
		freeBoard.setContent(content);
		freeBoard.setIp(ip);

		return freeBoardMapper.insertFreeBoard(freeBoard);
	}

//	saveReply 메소드 내부에서 update + insert 호출하고 있으므로 트랜잭션 대상
	@Transactional
	@Override
	public int saveReply(HttpServletRequest request) {

		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		int depth = Integer.parseInt(request.getParameter("depth"));
		long groupNo = Long.parseLong(request.getParameter("groupNo"));
		int groupOrd = Integer.parseInt(request.getParameter("groupOrd"));

		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());

//		원글의 depth, groupNo, groupOrd를 이용하여 댓글의 depth, groupNo, groupOrd 계산
//		댓글 depth 	 : 원글 depth + 1
//		댓글 groupNo	 : 원글 groupNo
//		댓글 groupOrd : 같은 그룹의 기존 댓글 모두 groupOrd + 1 처리 후 원글 groupOrd + 1

//		같은 그룹의 기존 댓글 모두 groupOrd + 1 처리를 위해
//		FreeBoardMapper에 원글의 정보를 전달해야 함

//		원글 DTO
		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setGroupNo(groupNo);
		freeBoard.setGroupOrd(groupOrd);
		freeBoardMapper.updatePreviousReply(freeBoard);

//		삽입할 댓글 DTO
		FreeBoard reply = new FreeBoard();
		reply.setWriter(writer);
		reply.setContent(content);
		reply.setDepth(depth + 1);
		reply.setGroupNo(groupNo);
		reply.setGroupOrd(groupOrd + 1);
		reply.setIp(ip);

		return freeBoardMapper.insertReply(reply);

	}

	@Override
	public int removeFreeBoard(long freeBoardNo) {

		return freeBoardMapper.deleteFreeBoard(freeBoardNo);
	}

}