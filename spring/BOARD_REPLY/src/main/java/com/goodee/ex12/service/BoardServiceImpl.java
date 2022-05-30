package com.goodee.ex12.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.goodee.ex12.domain.Board;
import com.goodee.ex12.mapper.BoardMapper;
import com.goodee.ex12.util.PageUtils;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public void findBoards(HttpServletRequest request, Model model) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));

		int totalRecord = boardMapper.selectBoardCount();

		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);

		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());

		List<Board> boards = boardMapper.selectBoardList(map);

		model.addAttribute("boards", boards);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/board/list"));

	}

	@Override
	public void findBoardByNo(HttpServletRequest request, HttpServletResponse response, Model model) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		long boardNo = Long.parseLong(opt.orElse("0"));

		String requestURI = request.getRequestURI();
		if (requestURI.endsWith("detail")) {
			boardMapper.updateBoardHit(boardNo);
		}

		Board board = boardMapper.selectBoardByNo(boardNo);

//		게시글이 존재하면 session에 두기
		if (board != null) {

			request.getSession().setAttribute("board", board);

		} else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('일치하는 게시글이 없습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Transactional // save() 에 있는 모든 쿼리문은 모두 성공해야 함
	@Override
	public int save(HttpServletRequest request) {

		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

//		어딘가를 경유하여 작성된 게시글의 IP는 요청헤더 X-Forwarded-For에 저장된다.

		String ip = request.getHeader("X-Forwarded-For");

//		직접 전달된 게시글은 요청헤더 X-forwarded-For 값이 없음
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		Board board = Board.builder().
				writer(writer).
				title(title).
				content(content).
				ip(ip).
				build();

		return boardMapper.insertBoard(board);

	}

	@Override
	public int change(Board board) {
		
		return boardMapper.updateBoard(board);
	}

	@Override
	public int remove(Long boardNo) {

		return boardMapper.deleteBoard(boardNo);

	}

}