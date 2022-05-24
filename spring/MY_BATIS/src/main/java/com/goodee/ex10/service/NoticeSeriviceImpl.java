package com.goodee.ex10.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex10.domain.NoticeDTO;
import com.goodee.ex10.mapper.NoticeMapper;

@Service
public class NoticeSeriviceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<NoticeDTO> findNotices() {

		return noticeMapper.selectNoticeList();

	}

	@Override
	public NoticeDTO findNoticeByNo(HttpServletRequest request) {

		String requestURI = request.getRequestURI(); // /ex10/notice/detail
		String[] arr = requestURI.split("/"); // arr = { "", "ex10", "notice", "detail" }

		long noticeNo = Long.parseLong(request.getParameter("noticeNo"));

		if (arr[arr.length - 1].equals("detail")) { // arr.length-1 : 배열의 마지막 요소
			noticeMapper.updateHit(noticeNo); // 조회수 증가
		}

		return noticeMapper.selectNoticeByNo(noticeNo);

	}

	@Override
	public int save(HttpServletRequest request) {

		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeMapper.insertNotice(notice);

	}

	@Override
	public int change(HttpServletRequest request) {

		NoticeDTO notice = new NoticeDTO();
		notice.setNoticeNo(Long.parseLong(request.getParameter("noticeNo")));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeMapper.updateNotice(notice);

	}

	@Override
	public int removeOne(HttpServletRequest request) {

		long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		return noticeMapper.deleteNoticeByNo(noticeNo);

	}

	@Override
	public int removeList(HttpServletRequest request) {

//		하나씩 여러 번 지우기
//		DELETE FROM NOTICE WHERE NOTICE_NO = 1
//		DELETE FROM NOTICE WHERE NOTICE_NO = 4
		String[] noticeNoList = request.getParameterValues("noticeNoList"); // {"1", "4"}
		int res = 0;
		for (int i = 0; i < noticeNoList.length; i++) {
			long noticeNo = Long.parseLong(noticeNoList[i]); // Long.parseLong("1") -> Long.parseLong("4")
			res += noticeMapper.deleteNoticeByNo(noticeNo);
		}
		return (res == noticeNoList.length) ? 1 : 0; // 모두 삭제했다면 1 반환 아니면 0 반환

	}

	@Override
	public int removeList2(HttpServletRequest request) {

//		한 번에 여러 번 지우기
//		DELETE FROM NOTICE WHERE NOTICE_NO IN {1,4}
		String[] noticeNoList = request.getParameterValues("noticeNoList"); // {"1", "4"}
		List<Long> list = new ArrayList<>();
		for (int i = 0; i < noticeNoList.length; i++) {
			list.add(Long.parseLong(noticeNoList[i])); // list.add(1) -> list.add(4)
		}
		return noticeMapper.deleteNoticeList(list);

	}

	@Override
	public void transaction() {

		noticeMapper.insertNotice(new NoticeDTO(0, "테스트", "테스트", 0, null, null));

		noticeMapper.insertNotice(new NoticeDTO());

	}
}