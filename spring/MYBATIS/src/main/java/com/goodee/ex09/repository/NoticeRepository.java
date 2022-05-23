package com.goodee.ex09.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodee.ex09.domain.NoticeDTO;

@Repository
public class NoticeRepository {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<NoticeDTO> selectNoticeList() {
		return sqlSessionTemplate.selectList("mybatis.mapper.notice.selectNoticeList");
	}

	public NoticeDTO selectNoticeByNo(long noticeNo) {
		return sqlSessionTemplate.selectOne("mybatis.mapper.notice.selectNoticeByNo", noticeNo);
	}

	public int insertNotice(NoticeDTO notice) {
		return sqlSessionTemplate.insert("mybatis.mapper.notice.insertNotice", notice);
	}

	public int updateHit(long noticeNo) {
		return sqlSessionTemplate.update("mybatis.mapper.notice.updateHit", noticeNo);
	}

	public int updateNotice(NoticeDTO notice) {
		return sqlSessionTemplate.update("mybatis.mapper.notice.updateNotice", notice);
	}

	public int deleteNotice(long noticeNo) {
		return sqlSessionTemplate.delete("mybatis.mapper.notice.deleteNoticeByNo", noticeNo);
	}

}