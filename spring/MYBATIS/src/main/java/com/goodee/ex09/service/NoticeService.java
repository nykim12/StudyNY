package com.goodee.ex09.service;

import java.util.List;

import com.goodee.ex09.domain.NoticeDTO;

public interface NoticeService {

	public List<NoticeDTO> findNotices();

	public NoticeDTO findNoticeByNo(long noticeNo);

	public int save(NoticeDTO notice);

	public int change(NoticeDTO notice);

	public int remove(long noticeNo);

}