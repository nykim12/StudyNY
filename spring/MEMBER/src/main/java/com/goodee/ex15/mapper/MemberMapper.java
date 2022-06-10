package com.goodee.ex15.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.domain.SignOutMemberDTO;

@Mapper
public interface MemberMapper {

	public MemberDTO selectMemberById(String id);

	public MemberDTO selectMemberByEmail(String email);

	public MemberDTO selectMemberByAccount(MemberDTO member);

	public SignOutMemberDTO selectSignOutMemberById(String id);

	public int insertMember(MemberDTO member);

	public int reInsertMember(MemberDTO member);

	public int insertMemberLog(String id);

	public int deleteMember(long memberNo);

	public int deleteSignOutMember(String id);

	public int updateSessionInfo(MemberDTO member);

	public MemberDTO selectMemberBySessionId(String sessionId);

}