package com.goodee.ex09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex09.domain.NoticeDTO;
import com.goodee.ex09.repository.NoticeRepository;

@Service
public class NoticeSeriviceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;

	@Override
	public List<NoticeDTO> findNotices() {
		return noticeRepository.selectNoticeList();
	}

	@Override
	public NoticeDTO findNoticeByNo(long noticeNo) {
		return null;
	}

	@Override
	public int save(NoticeDTO notice) {
		return 0;
	}

	@Override
	public int change(NoticeDTO notice) {
		return 0;
	}

	@Override
	public int remove(long noticeNo) {
		return 0;
	}

}