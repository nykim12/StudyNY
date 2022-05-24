package com.goodee.ex10.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex10.domain.NoticeDTO;

//	@Mapper
//	@Mapper를 호출하면 notice.xml의 쿼리문이 실행됨

@Mapper
public interface NoticeMapper {

//	notice.xml에 등록된 쿼리문의 id를 추상메소드로 작성

	public List<NoticeDTO> selectNoticeList();

	public int insertNotice(NoticeDTO notice);

	public NoticeDTO selectNoticeByNo(long noticeNo);

	public int updateHit(long noticeNo);

	public int updateNotice(NoticeDTO notice);

	public int deleteNoticeByNo(long noticeNo);

	public int deleteNoticeList(List<Long> noticeNo);

}