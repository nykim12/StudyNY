package com.goodee.ex15.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex15.domain.MemberDTO;

@Mapper
public interface MemberMapper {

	public MemberDTO selectMemberById(String id);

	public MemberDTO selectMemberByEmail(String email);

	public int insertMember(MemberDTO member);

	public int deleteMember(long memberNo);

	public MemberDTO selectMemberByAccount(MemberDTO member);

	public int insertMemberLog(String id);

}